package com.example.james.eecs40_assignment2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Starship
{
	private DashTillPuffSurfaceView fly;
	private int vy;
	private final int VELOCITY = 15;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int size; 	// size as a fraction of screen height
	private Bitmap bitmap;

	public Starship(DashTillPuffSurfaceView meant_to)
	{
		this.fly = meant_to;
		x1 = y1 = x2 = y2 = vy = 0;
		BitmapFactory.Options options = new BitmapFactory.Options();
		bitmap = BitmapFactory.decodeResource(meant_to.getResources(), R.drawable.spaceship, options);
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public void setVy(boolean touched)
	{
		if(touched)
		{
			vy = -VELOCITY; //going up up and away
		}
		else
		{
			vy = VELOCITY; //not going up
		}
	}

	public void initXs()
	{
		this.x1 = fly.getWidth() / 4 - fly.getShip_size() / 2;
		this.x2 = fly.getWidth() / 4 + fly.getShip_size() / 2;
	}

	public void initYs()
	{
		this.y1 = fly.getHeight() - fly.getShip_size();
		this.y2 = fly.getHeight();
	}

	public void tick(Canvas c)
	{
		y1 += vy;
		y2 += vy;
		if (y2 > fly.getHeight())
		{
			this.y2 = fly.getHeight();
			this.y1 = fly.getHeight() - fly.getShip_size();
		}
		else if (y1 < 0)
		{
			this.y2 = fly.getShip_size();
			this.y1 = 0;
		}
		draw(c);
	}

	public void draw(Canvas c)
	{
		Paint paint = new Paint() ;
		paint.setAlpha(255) ; // Control transparency
		Rect dst = new Rect (x1, y1, x2, y2) ; // Where to draw .
		c.drawBitmap(bitmap, null, dst, paint) ;
	}
}