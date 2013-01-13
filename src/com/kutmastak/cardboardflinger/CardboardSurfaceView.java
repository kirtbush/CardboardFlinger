package com.kutmastak.cardboardflinger;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CardboardSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	public SurfaceHolder mySurfaceHolder;
	public DrawThread myDrawingThread;
	public Context mycontext;
	public Bitmap myBmp;
	public int myCount;
	public Random myRand;
	public Paint paintBrush;
	public float xTouch = 0;
	public float yTouch = 0;
	public ArrayList<CardboardPerson> myPeople;
	public CardboardSurfaceView(Context context, int count) {
		super(context);
		mycontext = context;
		myBmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_delete);
		myRand = new Random();
		paintBrush = new Paint();
		paintBrush.setColor(Color.GREEN);
		// From the android guide: 
		// First step is to get the holder
		mySurfaceHolder = getHolder();
		// Add ourself to the callback for the SurfaceHolder
		mySurfaceHolder.addCallback(this);
		//mydrawingRunnable = new SurfaceChangedRunnable(this);
		myDrawingThread = new DrawThread(mySurfaceHolder);
		myCount = count;
		myPeople = new ArrayList<CardboardPerson>();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		//Log.d("onTouchEvent","x "+String.valueOf(x)+ " y "+String.valueOf(y));
		xTouch = event.getX();
		yTouch = event.getY();
		return true;
	}
	
	protected 
	void doDraw(Canvas canvas)
	{
		// This should clear the screen to black
		//canvas.drawColor(Color.BLACK);
		//Log.d("CardboardSurfaceView", "onDraw called!");
		
		//draw the number of people set in the activity
		for (int idx = 0; idx < myPeople.size(); idx++) {
			//draw to the canvas
			//canvas.drawBitmap(myBmp, myRand.nextInt(canvas.getWidth()), myRand.nextInt(canvas.getHeight()), paintBrush);
			myPeople.get(idx).draw(canvas);
			//canvas.drawCircle(5, 5, 5, paintBrush);
			//start_left += (bmp.getWidth() + 1); //draw the next one further to the right
		}
		
		if( xTouch != 0 && yTouch != 0 ) {
			Paint lineBrush = new Paint(paintBrush);
			lineBrush.setStrokeWidth(4);
			canvas.drawCircle(xTouch, yTouch, 4, paintBrush);
			canvas.drawLine(xTouch, yTouch - 5, xTouch, yTouch + 5, paintBrush); //vertical line
			canvas.drawLine(xTouch - 5, yTouch, xTouch + 5, yTouch, paintBrush); //horizontal line
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		Log.d("CardboardSurfaceView", "surfaceChanged Called!");
		//myDrawingThread.start();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d("CardboardSurfaceView", "surfaceCreated Called!");
		
		//initialize the cardboard people
		for (int idx = 0; idx < myCount;idx++)
		{
			myPeople.add(new CardboardPerson(myBmp, myRand.nextInt(getWidth()), myRand.nextInt(getHeight())));
		}		
		
		myDrawingThread.setRunning(true);
		myDrawingThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d("CardboardSurfaceView", "surfaceDestroyed Called!");
		myDrawingThread.setRunning(false);
        try {
            myDrawingThread.join();
        } catch (InterruptedException e) {
        }
	}
	
	protected class DrawThread extends Thread {
        private SurfaceHolder surfaceHolder;
        private boolean isRunning;

        public DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
            isRunning = false;
        }

        public void setRunning(boolean run) {
            isRunning = run;
        }

        public void run() {
            Canvas c;
            while (isRunning) {
                c = null;
                try {
                    c = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        doDraw(c);
                    }
                } finally {
                    // do this in a finally so that if an exception is thrown
                    // during the above, we don't leave the Surface in an
                    // inconsistent state
                    if (c != null) {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
//                    try {
//						sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
                }
            }
        }
    }
}

