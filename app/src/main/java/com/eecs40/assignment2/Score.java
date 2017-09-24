package com.eecs40.assignment2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/***** Created by James on 4/30/2015. *****/

class Score {
	private DashTillPuffSurfaceView view;
	private int size;
	private int x;
	private int y;
	private int score;
	private int score2;
	private int time;

	Score(DashTillPuffSurfaceView v) {
		view = v;
		score2 = score = x = y = size = 0;
	}

	void init() {
		x = view.getHeight() / 20;
		y = view.getHeight() / 15;
		size = view.getHeight() / 15;
		score = 0;
	}

	void tick() {
		time += 1;
		if (time % 10 == 0) {
			score++;
			score2 = score;
		}
	}

	void draw(Canvas c, boolean not_touched_yet) {
		if (not_touched_yet) {
			Paint p = new Paint();
			p.setColor(Color.WHITE);
			p.setStyle(Paint.Style.FILL);
			p.setTextSize(view.getHeight() / 10);
			c.drawText("Touch to start\n", view.getWidth() / 3, view.getHeight() / 3, p);
			if (score2 > 0) {
				c.drawText("Last Score: " + String.valueOf(score2),
						view.getWidth() / 3,
						view.getHeight() / 2, p);
			}
		}
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.FILL);
		paint.setTextSize(size);
		c.drawText(String.valueOf(score), x, y, paint);
	}
}