package com.exam.uitest;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class SimpleMap extends FrameLayout {

	private float sizex;
	private float sizey;
	private ArrayList<RailwayStation> stations;// 存储所有点，变动的
	private ArrayList<RailwayLine> lines;// 存储所有线，变动的
	private ArrayList<RailwayStation> baseStations;// 存储所有点原始坐标
	private ArrayList<RailwayLine> baseLines;// 存储所有线的原始坐标
	private static String TAG = "MAP";
	private float prex = -1;
	private float prey = -1;
	private float movingdisx = 0;
	private float movingdisy = 0;
	private float predis_x = 0;
	private float predis_y = 0;
	private boolean move = false;
	private boolean sizeable = false;
	private float movingOriginx = 0;
	private float movingOriginy = 0;
	private float pre2fingerdis;
	private float size = 1;

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
		init();
	}

	private void init() {
		this.sizex = 1;
		this.sizey = 1;
		this.stations = new ArrayList<RailwayStation>();
		this.lines = new ArrayList<RailwayLine>();
		this.baseLines = new ArrayList<RailwayLine>();
		this.baseStations = new ArrayList<RailwayStation>();
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
		this.stations.add(station);
		this.baseStations.add((RailwayStation) station.clone());
		return this;
	}

	public ArrayList<RailwayLine> getLines() {
		return lines;
	}

	public void reset() {
		lines.clear();
		for (RailwayLine line : baseLines) {
			lines.add((RailwayLine) line.clone());
		}
		stations.clear();
		for (RailwayStation station : baseStations) {
			stations.add((RailwayStation) station.clone());
		}
		this.sizex = 1;
		this.sizey = 1;
		this.movingdisx = 0;
		this.movingdisy = 0;
		this.invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		removeAllViews();
		if (move)
			setMoveLocation();
		if (sizeable)
			setSizeLocation();
		for (RailwayLine line : lines) {
			this.addView(line);
		}
		for (RailwayStation station : stations) {
			this.addView(station);
		}
		super.onDraw(canvas);
	}

	// 设置移动时各个点具体的位置
	private void setMoveLocation() {
		Point startpoint = null, endpoint = null;
		float x = 0, y = 0;
		for (int i = 0; i < baseLines.size(); i++) {
			startpoint = lines.get(i).getStartpoint();
			x = startpoint.getX() + movingdisx;
			y = startpoint.getY() + movingdisy;
			startpoint.setX(x).setY(y);
			endpoint = lines.get(i).getEndpoint();
			endpoint.setX(endpoint.getX() + movingdisx).setY(
					endpoint.getY() + movingdisy);
		}
		for (int i = 0; i < baseStations.size(); i++) {
			startpoint = stations.get(i).getPoint();
			startpoint.setX(startpoint.getX() + movingdisx).setY(
					startpoint.getY() + movingdisy);
		}
	}

	// 设置变换大小时具体位置
	private void setSizeLocation() {
		Point startpoint = null, endpoint = null;
		float x = 0, y = 0;
		for (RailwayLine rl : lines) {
			startpoint = rl.getStartpoint();
			endpoint = rl.getEndpoint();
			x = startpoint.getX();
			y = startpoint.getY();
			startpoint.setX((x - movingOriginx) * size + movingOriginx).setY(
					(y - movingOriginy) * size + movingOriginy);
			x = endpoint.getX();
			y = endpoint.getY();
			endpoint.setX((x - movingOriginx) * size + movingOriginx).setY(
					(y - movingOriginy) * size + movingOriginy);
		}
		for (RailwayStation rs : stations) {
			startpoint = rs.getPoint();
			x = startpoint.getX();
			y = startpoint.getY();
			startpoint.setX((x - movingOriginx) * size + movingOriginx).setY(
					(y - movingOriginy) * size + movingOriginy);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 移动
		if (event.getPointerCount() == 1) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.i(TAG, "action down");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i(TAG, "action move");
				if (prex == -1 && prex == -1) {
					prex = event.getX();
					prey = event.getY();
				}
				move = true;
				// 获取每次偏移
				movingdisx = Math.abs(event.getX() - prex) > 5 ? event.getX()
						- prex : 0;
				movingdisy = Math.abs(event.getY() - prey) > 5 ? event.getY()
						- prey : 0;
				prex = movingdisx == 0 ? prex : event.getX();
				prey = movingdisy == 0 ? prey : event.getY();
				// 重绘
				this.invalidate();
				break;
			case MotionEvent.ACTION_UP:
				Log.i(TAG, "action up");
				prex = -1;
				prey = -1;
				move = false;
				break;
			}
			return true;
		} else if (event.getPointerCount() == 2) {
			int actionEvent = event.getAction() & MotionEvent.ACTION_MASK;
			switch (actionEvent) {
			case MotionEvent.ACTION_POINTER_DOWN:
				Log.i(TAG, "action pointer down");
				predis_x = event.getX(0) - event.getX(1);
				predis_y = event.getY(0) - event.getY(1);
				movingOriginx = (event.getX(0) + event.getX(1)) / 2;
				movingOriginy = (event.getY(0) + event.getY(1)) / 2;
				pre2fingerdis = FloatMath.sqrt(predis_x * predis_x + predis_y
						* predis_y);
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i(TAG, "action moving");
				sizeable = true;
				float dis_x = event.getX(0) - event.getX(1);
				float dis_y = event.getY(0) - event.getY(1);
				float fingersdis = FloatMath
						.sqrt(dis_x * dis_x + dis_y * dis_y);
				size = fingersdis / pre2fingerdis;
				pre2fingerdis = fingersdis;
				this.invalidate();
				break;
			case MotionEvent.ACTION_POINTER_UP:
				Log.i(TAG, "action pointer up");
				sizeable = false;
				break;
			}
			return true;
		}
		return false;
	}

	public SimpleMap setLines(RailwayLine line) {
		this.lines.add(line);
		this.baseLines.add((RailwayLine) line.clone());
		return this;
	}

	public void reDraw() {
		this.invalidate();
	}
}
