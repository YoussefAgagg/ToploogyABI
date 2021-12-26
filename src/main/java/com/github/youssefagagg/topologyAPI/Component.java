package com.github.youssefagagg.topologyAPI;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 *  class represents an electronic component 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Component {
	private String type;//example of component type (resistor,nmos)
	private String id;
	private ComponentValues componentValues;//class represents the default, min and max values of the component
	private Map<String, String>netlist;
/*
 * return true of the component connected to that netlistNodeID
 */
	public boolean isConnectedgetWithNetlistNode(String netlisNodeID) {
		return netlist.values().stream()
						.filter(value->value.equals(netlisNodeID))
						.findAny()
						.isPresent();
	}
}
