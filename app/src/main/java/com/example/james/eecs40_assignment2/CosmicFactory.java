package com.example.james.eecs40_assignment2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by James on 4/22/2015.
 * Crafted by the wizard Allan's talented artisan hands on 4/30/2015.
 * this is fiyah
 */
public class CosmicFactory implements TimeConscious
{
        private DashTillPuffSurfaceView thehellcatspangledshalalala;
        private Trajectory trajectory;
        private int[] id = {R.drawable.blackhole, R.drawable.blueplanet, R.drawable.earth, R.drawable.glossyplanet,
                R.drawable.goldenstar, R.drawable.neutronstar, R.drawable.puffcloud, R.drawable.shinystar,
                R.drawable.starone, R.drawable.startwo};
        private ArrayList<Point[]> annie = new ArrayList<>(10);
        private int meter;
        private int count = 0;
        private int cosmicWidth;
        private boolean top = true;
        private final int VELOCITY;

        public CosmicFactory(DashTillPuffSurfaceView dtpv, Trajectory t)
        {
                int i;
                this.thehellcatspangledshalalala = dtpv;
                this.trajectory = t;
                this.meter = dtpv.getWidth();
                this.cosmicWidth = 0;
                this.VELOCITY = thehellcatspangledshalalala.getGotta_go_fast();
                for (i = 0; i < 10; i++)
                {
                        annie.add(new Point[30]);
                }
        }

        @Override
        public void tick(Canvas canvas)
        {
                int x, YatX, y, type, i, j;
                int height = thehellcatspangledshalalala.getHeight();
                int width = thehellcatspangledshalalala.getWidth();
                Random number = new Random();

                if (cosmicWidth <= 0)
                {
                        cosmicWidth = thehellcatspangledshalalala.getShip_size() / 2;
                }

                if (meter > width / 2)
                {
                        type = number.nextInt(id.length);
                        meter = 0;
                        for (i = 0; i < 10; i++)
                        {
                                x = number.nextInt(width / 2) + width;
                                for (j = 0; j < 30; j++)
                                {
                                        if (annie.get(type)[j] == null)
                                        {
                                                annie.get(type)[j] = new Point(x, -500);
                                                break;
                                        }
                                }
                        }
                }

                for (i = 0; i < annie.size(); i++)
                {
                        for (j = 0; j < annie.get(i).length; j++)
                        {
                                if (annie.get(i)[j] != null)
                                {
                                        if (annie.get(i)[j].x <= width && annie.get(i)[j].y < 0)
                                        {
                                                YatX = trajectory.getY(annie.get(i)[j].x);

                                                if (top)
                                                {
                                                        y = number.nextInt(YatX);
                                                        if (YatX - y < cosmicWidth)
                                                        {
                                                                y = YatX - 4 * cosmicWidth;
                                                        }
                                                        if (y < cosmicWidth / 2)
                                                        {
                                                                y = cosmicWidth / 2;
                                                        }
                                                }
                                                else
                                                {
                                                        y = number.nextInt(height - YatX) + YatX;
                                                        if (y - YatX < cosmicWidth)
                                                        {
                                                                y = YatX + 4 * cosmicWidth;
                                                        }
                                                        if (height - y < cosmicWidth / 2)
                                                        {
                                                                y = height - cosmicWidth / 2;
                                                        }
                                                }
                                                count++;
                                                if (count >= 10)
                                                {
                                                        top = !top;
                                                        count = 0;
                                                }
                                                annie.get(i)[j].y = y;
                                        }
                                        if (annie.get(i)[j].x <= width)
                                        {
                                                draw(canvas, i, j);
                                        }
                                        annie.get(i)[j].x -= VELOCITY;

                                        if (annie.get(i)[j].x < - (2 * cosmicWidth))
                                        {
                                                annie.get(i)[j] = null;
                                        }
                                }
                        }
                }
                meter += VELOCITY;
        }

        public void Trybmp()
        {
                int i, j, x, y;
                Point starship;
                for (i = 0; i < annie.size(); i++)
                {
                        for (j = 0; j < annie.get(i).length; j++)
                        {
                                if (annie.get(i)[j] != null)
                                {
                                        x = annie.get(i)[j].x;
                                        y = annie.get(i)[j].y;
                                        starship = thehellcatspangledshalalala.getStarship().its_okay_manny();          // Find the star-ship's location
                                        if (Math.sqrt((y - starship.y) * (y - starship.y) + (x - starship.x) * (x - starship.x)) <= 3 * cosmicWidth / 2)
                                        {
                                                // Restart game
						thehellcatspangledshalalala.setFeels_like_the_first_time(true);
						thehellcatspangledshalalala.setTouched_for_the_very_first_time(false);
                                        }
                                }
                        }
                }
        }

        public void draw(Canvas c, int i, int j)
        {
                int x = annie.get(i)[j].x, y = annie.get(i)[j].y;
                int width = cosmicWidth / 2;
                Bitmap st_vincent;
                Paint paint = new Paint();
                paint.setAlpha(255); // Control transparency
                Rect dst = new Rect(x - width, y - width, x + width, y + width); // Where to draw .
                BitmapFactory.Options options = new BitmapFactory.Options();
                st_vincent = BitmapFactory.decodeResource(thehellcatspangledshalalala.getResources(), id[i], options);
                c.drawBitmap(st_vincent, null, dst, paint);
        }
}
