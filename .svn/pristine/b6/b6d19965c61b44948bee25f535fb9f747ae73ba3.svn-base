package com.along.friendlyreminder.view;

import com.along.friendlyreminder.R;
import com.along.friendlyreminder.R.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class HeartRateView extends View {
	
	private static final Matrix matrix=new Matrix();//æÿ’Û
	
	private static final Paint paint =new Paint(Paint.ANTI_ALIAS_FLAG);
	
	private static Bitmap greenBitmap=null;
	
	private static Bitmap redBitmap=null;
	
	private static int parentWidth;
	private static int parentHeight;

	public HeartRateView(Context context) {

		super(context);
		greenBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.green_icon);
		redBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.red_icon);
		
	}

	public HeartRateView(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
		greenBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.green_icon);
		redBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.red_icon);
	}

	public HeartRateView(Context context, AttributeSet attrs) {

		super(context, attrs);
		greenBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.green_icon);
		redBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.red_icon);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		parentHeight=MeasureSpec.getSize(widthMeasureSpec);
		parentWidth=MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(parentWidth, parentHeight);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(canvas==null) throw new NullPointerException();
		
		Bitmap bitmap=null;
	}

}
