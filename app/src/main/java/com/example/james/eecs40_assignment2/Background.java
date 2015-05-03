package com.example.james.eecs40_assignment2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Background
{
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Bitmap bitmap;

	public Background(DashTillPuffSurfaceView view)
	{
		this.x1 = 0;
		this.x2 = 0;
		this.y1 = 0;
		this.y2 = 0;
		BitmapFactory.Options options = new BitmapFactory.Options();
		bitmap = BitmapFactory.decodeResource(view.getResources(), R.drawable.wallpaper, options);
	}

	public void draw ( Canvas c )
	{
		Paint paint = new Paint() ;
		paint.setAlpha(255) ; // Control transparency
		Rect dst = new Rect (x1, y1, x2, y2) ; // Where to draw .
		c.drawBitmap(bitmap, null, dst, paint);
	}
	public int getX1()
	{
		return x1;
	}

	public int getX2()
	{
		return x2;
	}

	public void setX(int x1, int x2)
	{
		this.x1 = x1;
		this.x2 = x2;
	}

	public void setY2(int y2)
	{
		this.y2 = y2;
	}
}
