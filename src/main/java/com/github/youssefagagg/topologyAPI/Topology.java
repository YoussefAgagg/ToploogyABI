package com.github.youssefagagg.topologyAPI;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * A topology is a set of electronic components that are connected together
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topology {
	private String id;
	private List<Component>components;
	/*
	 * return a list of components connected to the netlistNodeID
	 */
	public List<Component> getComponentsWithNetlistNode(String netlisNodeID) {
		List<Component>list=components.stream()
									  .filter((component)->component.isConnectedgetWithNetlistNode(netlisNodeID))
									  .collect(Collectors.toList());
		return list;
	}

}
