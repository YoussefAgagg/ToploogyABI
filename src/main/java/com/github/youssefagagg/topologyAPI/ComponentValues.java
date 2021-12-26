package com.github.youssefagagg.topologyAPI;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/*
 * the component values 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentValues {
	@ToString.Include(name="default")
	@SerializedName("default")
	private double defaultValue; //since java 8 default is a key word in java 
	private double min;
	private double max;

}
