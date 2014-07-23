package com.example.uitest;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class RailwayStation extends View {

	private Paint mPaint;
	private int px;
	private int py;
	private String name;
	private int size;
	private Point point;

	public RailwayStation(Context context, String name, int px, int py) {
		super(context);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(1);
		point = new Point();
		point.setX(px).setY(py);
		this.name = name;
		size = 1;
		init();
	}

	private void init() {
		this.px = point.getX() * size;
		this.py = point.getY() * size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
		init();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int measuredWidth = measureWidth(widthMeasureSpec);
		int measuredHeight = measureHeight(heightMeasureSpec);
		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	private int measureHeight(int measureSpec) {
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		return specSize;
	}

	private int measureWidth(int measureSpec) {
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		return specSize;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// super.onDraw(canvas);
		canvas.drawCircle(px, py, 5, mPaint);
		canvas.drawCircle(px, py, 10, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// return super.onTouchEvent(event);
		int action = event.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			float pointX = event.getX();
			float pointY = event.getY();
			if (isPoint(pointX, pointY)) {
				new AlertDialog.Builder(getContext()).setTitle(name)
						.setMessage(name + "具体信息:").show();
				return true;
			} else {
				return false;
			}
		default:
			return false;
		}
	}

	private boolean isPoint(float x, float y) {

		float disx = x - px;
		float disy = y - py;

		float disa = disx * disx + disy * disy;
		if (disa > 625) {
			return false;
		} else {
			return true;
		}
	}
}
