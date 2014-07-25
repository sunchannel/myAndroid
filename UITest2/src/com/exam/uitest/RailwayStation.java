package com.exam.uitest;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class RailwayStation extends View implements Cloneable {

	private Paint mPaint;
	private String name;
	private Point point;

	public RailwayStation(Context context, String name, int px, int py) {
		super(context);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(1);
		point = new Point();
		point.setX(px).setY(py);
		this.name = name;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
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
		canvas.drawCircle(point.getX(), point.getY(), 5, mPaint);
		canvas.drawCircle(point.getX(), point.getY(), 10, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int action = event.getAction();
		if (isPoint(event.getX(), event.getY())) {
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				Log.i("Station", name + "action down");
				new AlertDialog.Builder(getContext()).setTitle(name)
						.setMessage(name + "具体信息:").show();
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

		float disx = x - point.getX();
		float disy = y - point.getY();

		float disa = disx * disx + disy * disy;
		if (disa > 625) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	protected Object clone() {
		RailwayStation rs = null;
		try {
			rs = (RailwayStation) super.clone();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		rs.point = (Point) point.clone();
		return rs;
	}
}
