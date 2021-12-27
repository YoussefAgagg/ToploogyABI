package com.github.youssefagagg.topologyAPI;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
 * A topology is a set of electronic components that are connected together
 */

public class Topology {
	private String id;
	private List<Component>components;
	
	public Topology() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Topology(String id, List<Component> components) {
		super();
		this.id = id;
		this.components = components;
	}

	/*
	 * return a list of components connected to the netlistNodeID
	 */
	public List<Component> getComponentsWithNetlistNode(String netlisNodeID) {
		return components.stream().filter(component->component.isConnectedgetWithNetlistNode(netlisNodeID))
								  .collect(Collectors.toList());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	@Override
	public int hashCode() {
		return Objects.hash(components, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topology other = (Topology) obj;
		return Objects.equals(components, other.components) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Topology [id=" + id + ", components=" + components + "]";
	}

}
