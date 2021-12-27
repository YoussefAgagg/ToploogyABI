package com.github.youssefagagg.topologyAPI;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

/*
 * the component values 
 */

public class ComponentValues {
	@SerializedName("default")
	private double defaultValue; //since java 8 default is a key word in java 
	private double min;
	private double max;
	public ComponentValues() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ComponentValues(double defaultValue, double min, double max) {
		super();
		this.defaultValue = defaultValue;
		this.min = min;
		this.max = max;
	}
	public double getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(double defaultValue) {
		this.defaultValue = defaultValue;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(defaultValue, max, min);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComponentValues other = (ComponentValues) obj;
		return Double.doubleToLongBits(defaultValue) == Double.doubleToLongBits(other.defaultValue)
				&& Double.doubleToLongBits(max) == Double.doubleToLongBits(other.max)
				&& Double.doubleToLongBits(min) == Double.doubleToLongBits(other.min);
	}
	@Override
	public String toString() {
		return "ComponentValues [defaultValue=" + defaultValue + ", min=" + min + ", max=" + max + "]";
	}
	
}
