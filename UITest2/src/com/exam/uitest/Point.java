package com.exam.uitest;

public class Point implements Cloneable {
	private float x;
	private float y;

	public float getX() {
		return x;
	}

	public Point setX(float x) {
		this.x = x;
		return this;
	}

	public float getY() {
		return y;
	}

	public Point setY(float y) {
		this.y = y;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}

	@Override
	public Object clone() {
		Point p = null;
		try {
			p = (Point) super.clone();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return p;
	}
}
