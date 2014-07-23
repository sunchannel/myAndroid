package com.example.uitest;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class RailwayLine extends View {

	private Paint mPaint;
	private int px;
	private int py;
	private double distance;
	private double stopX;
	private double stopY;
	private String name;
	private Point startpoint;
	private Point endpoint;
	private int size;

	// public RailwayLine(Context context, String name, int px, int py,
	// float distance, float angle) {
	// super(context);
	// this.px = px;
	// this.py = py;
	// this.name = name;
	// this.distance = distance;
	// double c = angle / 180;
	// this.stopX = px + distance * Math.cos(Math.PI * c);
	// this.stopY = py + distance * Math.sin(Math.PI * c);
	// init();
	// }

	public RailwayLine(Context context, String name, int px, int py, int stopx,
			int stopy) {
		super(context);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.BLUE);
		mPaint.setStrokeWidth(5);
		this.name = name;
		startpoint = new Point();
		startpoint.setX(px).setY(py);
		endpoint = new Point();
		endpoint.setX(stopx).setY(stopy);
		size = 1;
		init();
	}

	private void init() {
		this.px = startpoint.getX() * size;
		this.py = startpoint.getY() * size;
		this.stopX = endpoint.getX() * size;
		this.stopY = endpoint.getY() * size;
		this.distance = Math.sqrt((stopX - px) * (stopX - px) + (stopY - py)
				* (stopY - py));
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
		canvas.drawLine(px, py, (float) stopX, (float) stopY, mPaint);
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
			Log.i("Tag", "���x:" + pointX + ", y:" + pointY);
			if (isPoint(pointX, pointY)) {
				Log.i("Tag", "������Ϣ��");
				new AlertDialog.Builder(getContext()).setTitle(name)
						.setMessage(name + "������Ϣ").show();
				return true;
			} else {
				return false;
			}
		default:
			return false;
		}
	}

	private boolean isPoint(float x, float y) {

		double disx = x - px;
		double disy = y - py;
		double distostart = Math.sqrt(disx * disx + disy * disy);

		disx = x - stopX;
		disy = y - stopY;
		double distostop = Math.sqrt(disx * disx + disy * disy);

		double p = (distostart + distostop + distance) / 2;
		double s = Math.sqrt(p * (p - distostart) * (p - distostop)
				* (p - distance));

		double disa = s / distance;
		Log.i("Tag", "���߳���" + distostart + ", " + distostop + ", " + disa);
		if (disa < 25 && ((y - stopY) * (py - y)) > -50
				&& ((x - px) * (stopX - x)) > -50 && distostart > 25
				&& distostop > 25) {
			return true;
		}
		return false;
	}
}
