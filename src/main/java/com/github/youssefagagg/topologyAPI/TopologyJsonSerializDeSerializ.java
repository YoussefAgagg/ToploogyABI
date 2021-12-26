package com.github.youssefagagg.topologyAPI;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/*
 *  a custom serializer and deSerializer for Topology class 
 * the purpose of implement this class is to modify the name of the object ComponentValues befor serialize and deserialize
 *  the Component "type" property can be resistor or nmos and each type has different name property for the ComponentValues
 *  so before serialize the Topology object I change the name of The ComponentValues to m(1) or resistance as in the json example file
 *  and before deserialize the Topology object I change the name to ComponentValues so that the Gson library can initialize the object properly
 *    
 */

public class TopologyJsonSerializDeSerializ implements JsonSerializer<Topology>, JsonDeserializer<Topology> {

	private static final String M_1 = "m(1)";
	private static final String NMOS = "nmos";
	private static final String TYPE = "type";
	private static final String RESISTOR = "resistor";
	private static final String COMPONENT_VALUES = "componentValues";
	private static final String RESISTANCE = "resistance";

	@Override
	public Topology deserialize(JsonElement topologyJson, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject topologyJsonObject=topologyJson.getAsJsonObject();
		JsonArray components=topologyJsonObject.getAsJsonArray("components");

		for(JsonElement componentJsonElemnt:components) {
			JsonObject component=componentJsonElemnt.getAsJsonObject();
			String componentType=component.get(TYPE).getAsString();

			if(componentType.equals(RESISTOR)) {
				JsonElement componentValues=component.get(RESISTANCE);
				component.remove(RESISTANCE);
				component.add(COMPONENT_VALUES, componentValues);
				
			}else if(componentType.equals(NMOS)) {
				JsonElement componentValues=component.get(M_1);

				component.remove(M_1);
				component.add(COMPONENT_VALUES, componentValues);
			}
		}
		return new Gson().fromJson(topologyJsonObject, Topology.class);
	}

	@Override
	public JsonElement serialize(Topology src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject topologyJsonObject=new Gson().toJsonTree(src).getAsJsonObject();
		JsonArray components=topologyJsonObject.getAsJsonArray("components");

		for(JsonElement componentJsonElemnt:components) {
			JsonObject component=componentJsonElemnt.getAsJsonObject();
			String componentType=component.get(TYPE).getAsString();
			JsonElement componentValues=component.get(COMPONENT_VALUES);
			component.remove(COMPONENT_VALUES);
			if(componentType.equals(RESISTOR)) {
				component.add(RESISTANCE, componentValues);
				
			}else if(componentType.equals(NMOS)) {				
				component.add(M_1, componentValues);
			}
		}
		return topologyJsonObject;
	}


}
