package com.example.james.eecs40_assignment2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by James on 4/22/2015.
 */
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

	private void draw ( Canvas c )
	{
		Paint paint = new Paint() ;
		paint.setAlpha(1) ; // Control transparency
		Rect dst = new Rect ( x1 , y1 , x2 , y2 ) ; // Where to draw .
		c.drawBitmap(bitmap, null, dst, paint) ;
	}
}
