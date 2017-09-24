package com.eecs40.assignment2;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/***** Created by James on 4/22/2015. *****/

class DashTillPuffRenderThread extends Thread {
	private final DashTillPuffSurfaceView view;
	private static final int FRAME_PERIOD = 5; // In ms

	DashTillPuffRenderThread(DashTillPuffSurfaceView view) {
		this.view = view;
	}

	public void run() {
		SurfaceHolder sh = view.getHolder();
		// Main game loop .
		while (!Thread.interrupted()) {
			Canvas c = sh.lockCanvas(null);
			try {
				view.tick(c);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (c != null) {
					sh.unlockCanvasAndPost(c);
				}
			}
			try {
				Thread.sleep(FRAME_PERIOD);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
