package com.exam.uitest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class RailwayLine extends View implements Cloneable {

	private Paint mPaint;
	private double distance;
	private String name;
	private Point startpoint;
	private Point endpoint;

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
	}

	private void init() {
		this.distance = Math.sqrt((endpoint.getX() - startpoint.getX())
				* (endpoint.getX() - startpoint.getX())
				+ (endpoint.getY() - endpoint.getY())
				* (endpoint.getY() - endpoint.getY()));
	}

	public double getDistance() {
		return distance;
	}

	public String getName() {
		return name;
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

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int measuredWidth = measureWidth(widthMeasureSpec);
		int measuredHeight = measureHeight(heightMeasureSpec);
		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	private int measureHeight(int measureSpec) {
		// int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		return specSize;
	}

	private int measureWidth(int measureSpec) {
		// int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		return specSize;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// super.onDraw(canvas);
		init();
		canvas.drawLine(startpoint.getX(), startpoint.getY(), endpoint.getX(),
				endpoint.getY(), mPaint);
	}

	@Override
	public Object clone() {
		RailwayLine rl = null;
		try {
			rl = (RailwayLine) super.clone();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		rl.endpoint = (Point) endpoint.clone();
		rl.startpoint = (Point) startpoint.clone();
		return rl;
	}
}
