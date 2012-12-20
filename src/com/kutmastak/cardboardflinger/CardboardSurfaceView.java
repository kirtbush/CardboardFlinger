package com.kutmastak.cardboardflinger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CardboardSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	public SurfaceHolder m_SurfaceHolder;
	public Thread m_DrawingThread;
	public SurfaceChangedRunnable m_drawingRunnable;
	public CardboardSurfaceView(Context context) {
		super(context);
		// From the android guide: 
		// First step is to get the holder
		m_SurfaceHolder = getHolder();
		// Add ourself to the callback for the SurfaceHolder
		m_SurfaceHolder.addCallback(this);
		m_drawingRunnable = new SurfaceChangedRunnable(this);
		m_DrawingThread = new Thread(m_drawingRunnable);
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		Log.d("CardboardSurfaceView", "surfaceChanged Called!");
		m_DrawingThread.start();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d("CardboardSurfaceView", "surfaceCreated Called!");		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d("CardboardSurfaceView", "surfaceDestroyed Called!");
	}

	class SurfaceChangedRunnable implements Runnable {
		public CardboardSurfaceView m_myView;
		public SurfaceChangedRunnable(CardboardSurfaceView view) {
			Log.d("SurfaceChangedRunnable", "Creating new SurfaceChangedRunnable");
			m_myView = view;
		}
		public void run() {
			Log.d("SurfaceChangedRunnable", "Running surfaceChanged event");
			SurfaceHolder holder = m_myView.getHolder();
			Canvas canvas = holder.lockCanvas();
			Paint paintBrush = new Paint();
			paintBrush.setColor(Color.GREEN);
			
			//draw to the canvas
			canvas.drawCircle(5, 5, 5, paintBrush);
			
			//now finish with the canvas
			holder.unlockCanvasAndPost(canvas);
		}
	}
}

