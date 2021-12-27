package com.github.youssefagagg.topologyAPI;

import java.util.Map;
import java.util.Objects;

/*
 *  class represents an electronic component 
 */

public class Component {
	private String type;//example of component type (resistor,nmos)
	private String id;
	private ComponentValues componentValues;//class represents the default, min and max values of the component
	private Map<String, String>netlist;


	public Component() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Component(String type, String id, ComponentValues componentValues, Map<String, String> netlist) {
		super();
		this.type = type;
		this.id = id;
		this.componentValues = componentValues;
		this.netlist = netlist;
	}

	/*
	 * return true of the component connected to that netlistNodeID
	 */
	public boolean isConnectedgetWithNetlistNode(String netlisNodeID) {
		return netlist.values().stream()
				.filter(value->value.equals(netlisNodeID))
				.findAny()
				.isPresent();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ComponentValues getComponentValues() {
		return componentValues;
	}
	public void setComponentValues(ComponentValues componentValues) {
		this.componentValues = componentValues;
	}
	public Map<String, String> getNetlist() {
		return netlist;
	}
	public void setNetlist(Map<String, String> netlist) {
		this.netlist = netlist;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(componentValues, id, netlist, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Component other = (Component) obj;
		return Objects.equals(componentValues, other.componentValues) && Objects.equals(id, other.id)
				&& Objects.equals(netlist, other.netlist) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Component [type=" + type + ", id=" + id + ", componentValues=" + componentValues + ", netlist="
				+ netlist + "]";
	}


}
