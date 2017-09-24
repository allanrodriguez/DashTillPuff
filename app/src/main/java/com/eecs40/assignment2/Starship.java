package com.eecs40.assignment2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Released by Nicki Minaj on 2/14/2012.
 */

class Starship {
	private DashTillPuffSurfaceView fly;
	private final int VELOCITY;
	private int vy;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Bitmap bitmap;

	Starship(DashTillPuffSurfaceView meantTo) {
		this.fly = meantTo;
		this.VELOCITY = meantTo.getGottaGoFast() + 5;
		x1 = y1 = x2 = y2 = vy = 0;
		BitmapFactory.Options options = new BitmapFactory.Options();
		bitmap = BitmapFactory.decodeResource(meantTo.getResources(), R.drawable.spaceship, options);
	}

	void setVy(boolean touched) {
		if (touched) {
			vy = -VELOCITY; //going up up and away
		} else {
			vy = VELOCITY; //not going up
		}
	}

	void init() {
		this.x1 = fly.getWidth() / 4 - fly.getShipSize() / 2;
		this.x2 = fly.getWidth() / 4 + fly.getShipSize() / 2;
		this.y1 = fly.getHeight() - fly.getShipSize();
		this.y2 = fly.getHeight();
	}

	Point itsOkayManny() {
		return new Point((x2 - x1) / 2 + x1, (y2 - y1) / 2 + y1);
	}

	void tick(Canvas c) {
		y1 += vy;
		y2 += vy;
		if (y2 > fly.getHeight()) {
			this.y2 = fly.getHeight();
			this.y1 = fly.getHeight() - fly.getShipSize();
		} else if (y1 < 0) {
			this.y2 = fly.getShipSize();
			this.y1 = 0;
		}
		draw(c);
	}

	void draw(Canvas c) {
		Paint paint = new Paint();
		paint.setAlpha(255); // Control transparency
		Rect dst = new Rect(x1, y1, x2, y2); // Where to draw .
		c.drawBitmap(bitmap, null, dst, paint);
	}
}