package com.example.uitest;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class RailwayStation extends View {

	private Paint mPaint;
	private float px;
	private float py;
	private String name;
	private float sizex;
	private float sizey;
	private Point point;
	private Point originPoint;
	private float prepointx;
	private float prepointy;

	public RailwayStation(Context context, String name, int px, int py) {
		super(context);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(1);
		point = new Point();
		originPoint = new Point();
		originPoint.setX(0);
		originPoint.setY(0);
		point.setX(px).setY(py);
		this.name = name;
		sizex = 1;
		sizey = 1;
	}

	private void init() {
		this.px = (point.getX() + originPoint.getX()) * sizex;
		this.py = (point.getY() + originPoint.getY()) * sizey;
	}

	public float getSizex() {
		return sizex;
	}

	public void setSizex(float sizex) {
		this.sizex = sizex;
	}

	public float getSizey() {
		return sizey;
	}

	public void setSizey(float sizey) {
		this.sizey = sizey;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setOriginPoint(float x, float y) {
		this.originPoint.setX(x);
		this.originPoint.setY(y);
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
		init();
		canvas.drawCircle(px, py, 5, mPaint);
		canvas.drawCircle(px, py, 10, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// return super.onTouchEvent(event);
		int action = event.getAction();
		if (isPoint(event.getX(), event.getY())) {
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				prepointx = event.getX();
				prepointy = event.getY();
				Log.i("Station", name + "action down");
				new AlertDialog.Builder(getContext()).setTitle(name)
						.setMessage(name + "������Ϣ:").show();
				break;
			case MotionEvent.ACTION_CANCEL:
				Log.i("Station", name + "action up");
				break;
			case MotionEvent.ACTION_UP:
				Log.i("Station", name + "action cancel");
				break;
			}
		}
		return false;
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
