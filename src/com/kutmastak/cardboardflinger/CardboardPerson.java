package com.kutmastak.cardboardflinger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class CardboardPerson extends Drawable {
	private int xStart;
	private int yStart;
	private Bitmap myBmp;
	public CardboardPerson()
	{
		
	}
	public CardboardPerson(Bitmap bmp, int xLoc, int yLoc)
	{
		xStart = xLoc;
		yStart = yLoc;
		myBmp = bmp;
	}
	public int x()
	{
		return xStart;
	}
	public int y()
	{
		return yStart;
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint myBrush = new Paint();
		myBrush.setColor(Color.BLUE);
		canvas.drawBitmap(myBmp, xStart, yStart, myBrush);
	}
	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub
		
	}
}
