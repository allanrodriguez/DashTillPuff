package com.example.james.eecs40_assignment2;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by our glorious leader on 4/22/2015.
 */

public class DashTillPuffSurfaceView extends SurfaceView implements SurfaceHolder.Callback, TimeConscious
{
	private DashTillPuffRenderThread renderThread;
        public Background wall;
        public Background paper;
	private Trajectory trajectory;
	private Starship starship;
	private boolean feels_like_the_first_time;	// for initializing
	private final int gotta_go_fast = 10; 		// speed of background
	private int ship_size;				// size as a of fraction of screen height, assigned in tick()

	public DashTillPuffSurfaceView(Context context)
	{
		super(context);
		getHolder().addCallback(this);
		feels_like_the_first_time = true;
		trajectory = new Trajectory(this);
                wall = new Background(this);
                paper = new Background(this);
		starship = new Starship(this);
	}

	public int getShip_size()
	{
		return ship_size;
	}

	public int getGotta_go_fast()
	{
		return this.gotta_go_fast;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		renderThread = new DashTillPuffRenderThread(this);
		renderThread.start();

		// Create the sliding background , cosmic factory , trajectory
		// and the space ship

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{
		// Respond to surface changes , e . g . , aspect ratio changes.
		// Ahmed did nothing here, so in his words, "TO DO" maybe
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		// The cleanest way to stop a thread is by interrupting it .
		// BubbleShooterThread regularly checks its interrupt flag .
		renderThread.interrupt();
	}

	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		switch (e.getAction())
		{
			case MotionEvent.ACTION_DOWN: // Thrust the space ship up .
				starship.setVy(true);
				break;
			case MotionEvent.ACTION_UP: // Let space ship fall freely .
				starship.setVy(false);
				break;
		}
		return true;
	}

	@Override
	public void onDraw(Canvas c)
	{
		super.onDraw(c);
		renderGame(c);
		// Draw everything ( restricted to the displayed rectangle ) .
	}

	@Override
	public void tick(Canvas c)
	{
                if (wall.drawn && paper.drawn)
                {
                        if (wall.getX2() == wall.getX1())
                        {
                                wall.setX(0, getWidth() - 1);
                                wall.setY2(getHeight() - 1);
                        }
                        if (paper.getX2() == paper.getX1())
                        {
                                paper.setX(getWidth(), 2 * getWidth() - 1);
                                paper.setY2(getHeight() - 1);
                        }

                        if (wall.getX2() <= 0)
                        {
                                wall.setX(getWidth() - 1, 2 * getWidth() - 1);
                        }
                        else if (paper.getX2() <= 0)
                        {
                                paper.setX(getWidth() - 1, 2 * getWidth() - 1);
                        }
                        else
                        {
                                wall.setX(wall.getX2() - getWidth() - (gotta_go_fast-1), wall.getX2() - gotta_go_fast);
                                paper.setX(paper.getX2() - getWidth() - (gotta_go_fast-1), paper.getX2() - gotta_go_fast);
                        }
                }
		if(feels_like_the_first_time)
		{
			ship_size = getHeight()/5;
			trajectory.initTrajectory();
			starship.init();
			feels_like_the_first_time = false;
		}
		trajectory.tick(c);
		starship.tick(c);
                renderGame(c);
		// Tick background , space ship , cosmic factory , and trajectory .
		// Draw everything ( restricted to the displayed rectangle ) .
	}

	private void renderGame(Canvas c)
	{
                wall.draw(c);
                paper.draw(c);
		trajectory.draw(c);
		starship.draw(c);
	}
}
