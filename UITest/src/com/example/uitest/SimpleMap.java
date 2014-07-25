package com.example.uitest;

import java.util.ArrayList;
import java.util.HashSet;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

public class SimpleMap extends FrameLayout {

	private float sizex;
	private float sizey;
	private ArrayList<RailwayStation> stations;
	private ArrayList<RailwayLine> lines;
	private HashSet<Point> points;
	private boolean fixable = false; // 是否锁定，即是否启动自适应，false启动
	private Point originPoint;

	public SimpleMap(Context context) {
		super(context);
		init();
	}

	public SimpleMap(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SimpleMap(Context context, AttributeSet attrs, int defaultStyle) {
		super(context, attrs, defaultStyle);
	}

	private void init() {
		this.sizex = 1;
		this.sizey = 1;
		originPoint = new Point();
		originPoint.setX(0);
		originPoint.setY(0);
		this.stations = new ArrayList<RailwayStation>();
		this.lines = new ArrayList<RailwayLine>();
		this.points = new HashSet<Point>();
	}

	public float getSizex() {
		return sizex;
	}

	public void setSizex(float size) {
		this.sizex = size;
		this.invalidate();
	}

	public float getSizey() {
		return sizey;
	}

	public void setSizey(float sizey) {
		this.sizey = sizey;
	}

	public ArrayList<RailwayStation> getStations() {
		return stations;
	}

	public SimpleMap setStations(RailwayStation station) {
		station.setOriginPoint(this.originPoint.getX(), this.originPoint.getY());
		this.stations.add(station);
		this.points.add(station.getPoint());
		// this.addView(station);
		return this;
	}

	public ArrayList<RailwayLine> getLines() {
		return lines;
	}

	private void measureSize(Point p) {
		float my = getMeasuredHeight(), mx = getMeasuredWidth();
		float pointx = p.getX();
		float pointy = p.getY();
		if (pointy > my) {
			sizey = (my - 50) / pointy;
		}
		if (pointx > mx) {
			sizex = (mx - 50) / pointx;
		}
	}

	public SimpleMap setLines(RailwayLine line) {
		line.setOriginPoint(this.originPoint.getX(), this.originPoint.getY());
		this.lines.add(line);
		this.points.add(line.getStartpoint());
		this.points.add(line.getEndpoint());
		// this.addView(line);
		return this;
	}

	public void reDraw() {
		this.invalidate();
	}

	public void setFixable(boolean fixable) {
		this.fixable = fixable;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		removeAllViews();
		if (!fixable) {
			for (Point p : points) {
				measureSize(p);
			}
		}

		for (RailwayLine line : lines) {
			line.setSizex(sizex);
			line.setSizey(sizey);
			line.setOriginPoint(this.originPoint.getX(),
					this.originPoint.getY());
			this.addView(line);
		}
		for (RailwayStation station : stations) {
			station.setSizex(sizex);
			station.setSizey(sizey);
			station.setOriginPoint(this.originPoint.getX(),
					this.originPoint.getY());
			this.addView(station);
		}
		super.onDraw(canvas);
	}

}
