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
	private float px;
	private float py;
	private double distance;
	private double stopX;
	private double stopY;
	private String name;
	private Point startpoint;
	private Point endpoint;
	private float sizex;
	private float sizey;
	private Point originPoint;

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
		originPoint = new Point();
		originPoint.setX(0);
		originPoint.setY(0);
		endpoint = new Point();
		endpoint.setX(stopx).setY(stopy);
		sizex = 1;
		sizey = 1;
	}

	private void init() {
		this.px = (startpoint.getX() + originPoint.getX()) * sizex;
		this.py = (startpoint.getY() + originPoint.getY()) * sizey;
		this.stopX = (endpoint.getX() + originPoint.getX()) * sizex;
		this.stopY = (endpoint.getY() + originPoint.getY()) * sizey;
		this.distance = Math.sqrt((stopX - px) * (stopX - px) + (stopY - py)
				* (stopY - py));
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

	public Point getStartpoint() {
		return startpoint;
	}

	public void setStartpoint(Point startpoint) {
		this.startpoint = startpoint;
	}

	public Point getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(Point endpoint) {
		this.endpoint = endpoint;
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
		canvas.drawLine(px, py, (float) stopX, (float) stopY, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// return super.onTouchEvent(event);
		int action = event.getAction();
		if (isPoint(event.getX(), event.getY())) {
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				float pointX = event.getX();
				float pointY = event.getY();
				// Log.i("Tag", "点击x:" + pointX + ", y:" + pointY);
				new AlertDialog.Builder(getContext()).setTitle(name)
						.setMessage(name + "具体信息").show();
				Log.i("Line", name + "action down");
				break;
			case MotionEvent.ACTION_CANCEL:
				Log.i("Line", name + "action cancel");
				break;
			case MotionEvent.ACTION_UP:
				Log.i("Line", name + "action up");
			}
		}
		return false;
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
		// Log.i("Tag", "三边长：" + distostart + ", " + distostop + ", " + disa);
		if (disa < 25 && ((y - stopY) * (py - y)) > -50
				&& ((x - px) * (stopX - x)) > -50 && distostart > 25
				&& distostop > 25) {
			return true;
		}
		return false;
	}
}
