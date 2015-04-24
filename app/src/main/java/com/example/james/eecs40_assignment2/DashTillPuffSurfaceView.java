package com.example.james.eecs40_assignment2;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by James on 4/22/2015.
 */

public class DashTillPuffSurfaceView extends SurfaceView implements SurfaceHolder.Callback, TimeConscious
{
	private DashTillPuffRenderThread renderThread;
        public Background wall;
        public Background paper;
	private Trajectory trajectory;

	public DashTillPuffSurfaceView(Context context)
	{
		super(context);
		getHolder().addCallback(this);
		trajectory = new Trajectory(this);

                wall = new Background(this);
                paper = new Background(this);
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
				break;
			case MotionEvent.ACTION_UP: // Let space ship fall freely .
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
                                wall.setX(wall.getX2() - getWidth() - 9, wall.getX2() - 10);
                                paper.setX(paper.getX2() - getWidth() - 9, paper.getX2() - 10);
                        }
                }
                renderGame(c);
		// Tick background , space ship , cosmic factory , and trajectory .
		// Draw everything ( restricted to the displayed rectangle ) .
	}

	private void renderGame(Canvas c)
	{
                wall.draw(c);
                paper.draw(c);
	}
}
