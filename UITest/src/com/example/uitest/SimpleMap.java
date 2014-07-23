package com.example.uitest;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class SimpleMap extends FrameLayout {

	private int size;
	private ArrayList<RailwayStation> stations;
	private ArrayList<RailwayLine> lines;

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
		this.size = 1;
		this.stations = new ArrayList<RailwayStation>();
		this.lines = new ArrayList<RailwayLine>();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
		this.invalidate();
	}

	public ArrayList<RailwayStation> getStations() {
		return stations;
	}

	public SimpleMap setStations(RailwayStation station) {
		this.stations.add(station);
		// this.addView(station);
		return this;
	}

	public ArrayList<RailwayLine> getLines() {
		return lines;
	}

	public SimpleMap setLines(RailwayLine line) {
		this.lines.add(line);
		// this.addView(line);
		return this;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		removeAllViews();
		for (RailwayLine line : lines) {
			line.setSize(size);
			this.addView(line);
		}
		for (RailwayStation station : stations) {
			station.setSize(size);
			this.addView(station);
		}
		super.onDraw(canvas);
	}

}
