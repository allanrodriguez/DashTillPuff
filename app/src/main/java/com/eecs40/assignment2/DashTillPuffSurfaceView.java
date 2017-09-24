package com.eecs40.assignment2;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by our glorious leader on 4/22/2015.
 * and his friend on 4/24/2015.
 */

public class DashTillPuffSurfaceView extends SurfaceView implements SurfaceHolder.Callback, TimeConscious {
	private DashTillPuffRenderThread renderThread;
	private Background wall;
	private Background paper;
	private Trajectory trajectory;
	private Starship starship;
	private Score score;
	private CosmicFactory celestialBodies;
	private boolean feelsLikeTheFirstTime;        // for initializing
	private boolean touchedForTheVeryFirstTime;    // for starting game at first touchEvent
	private final int gottaGoFast = 10;            // speed of background
	private int shipSize;                    // size as a of fraction of screen height, assigned in tick()

	public DashTillPuffSurfaceView(Context context) {
		super(context);
		getHolder().addCallback(this);
		feelsLikeTheFirstTime = true;
		touchedForTheVeryFirstTime = false;
		trajectory = new Trajectory(this);
		wall = new Background(this);
		paper = new Background(this);
		starship = new Starship(this);
		score = new Score(this);
		celestialBodies = new CosmicFactory(this, trajectory);
	}

	public Starship getStarship() {
		return starship;
	}

	public int getShipSize() {
		return shipSize;
	}

	public int getGottaGoFast() {
		return this.gottaGoFast;
	}

	public boolean isTouchedForTheVeryFirstTime() {
		return touchedForTheVeryFirstTime;
	}

	public void setFeelsLikeTheFirstTime(boolean feelsLikeTheFirstTime) {
		this.feelsLikeTheFirstTime = feelsLikeTheFirstTime;
	}

	public void setTouchedForTheVeryFirstTime(boolean touchedForTheVeryFirstTime) {
		this.touchedForTheVeryFirstTime = touchedForTheVeryFirstTime;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		renderThread = new DashTillPuffRenderThread(this);
		renderThread.start();

		// Create the sliding background , cosmic factory , trajectory
		// and the space ship

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// Respond to surface changes , e . g . , aspect ratio changes.
		// Ahmed did nothing here, so in his words, "TO DO" maybe
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// The cleanest way to stop a thread is by interrupting it .
		// BubbleShooterThread regularly checks its interrupt flag .
		renderThread.interrupt();
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		switch (e.getAction()) {
			case MotionEvent.ACTION_DOWN: // Thrust the space ship up .
				touchedForTheVeryFirstTime = true;
				starship.setVy(true);
				break;
			case MotionEvent.ACTION_UP: // Let space ship fall freely .
				starship.setVy(false);
				break;
		}
		return true;
	}

	@Override
	public void onDraw(Canvas c) {
		// Draw everything ( restricted to the displayed rectangle ) .
		super.onDraw(c);
		renderGame(c);
	}

	@Override
	public void tick(Canvas c) {
		// Tick background , space ship , cosmic factory , and trajectory .
		// Draw everything ( restricted to the displayed rectangle ) .
		celestialBodies.tryBmp(); // check if player has lost, if so, game will re-initialize
		if (feelsLikeTheFirstTime) // initialize the game
		{
			wall.setX(0, getWidth() - 1);
			wall.setY2(getHeight());
			paper.setX(getWidth(), 2 * getWidth() - 1);
			paper.setY2(getHeight());
			shipSize = getHeight() / 6;
			trajectory.initTrajectory();
			starship.init();
			score.init();
			celestialBodies.init();
			feelsLikeTheFirstTime = false;
		}
		if (touchedForTheVeryFirstTime) // start the game
		{
			if (wall.getX2() <= 0) {
				wall.setX(getWidth() - 1, 2 * getWidth() - 1);
			} else if (paper.getX2() <= 0) {
				paper.setX(getWidth() - 1, 2 * getWidth() - 1);
			} else {
				wall.setX(wall.getX2() - getWidth() - gottaGoFast + 5, wall.getX2() - gottaGoFast + 8);
				paper.setX(paper.getX2() - getWidth() - gottaGoFast + 5, paper.getX2() - gottaGoFast + 8);
			}
			trajectory.tick(c);
			starship.tick(c);
			score.tick();
		}
		renderGame(c);
	}

	private void renderGame(Canvas c) {
		wall.draw(c);
		paper.draw(c);
		trajectory.draw(c, touchedForTheVeryFirstTime);
		starship.draw(c);
		if (touchedForTheVeryFirstTime) {
			celestialBodies.tick(c);
		}
		score.draw(c, !touchedForTheVeryFirstTime);
	}
}
