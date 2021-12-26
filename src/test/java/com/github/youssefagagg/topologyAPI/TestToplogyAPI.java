package com.github.youssefagagg.topologyAPI;

import static org.junit.jupiter.api.Assertions.*;


import java.io.File;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import static com.github.youssefagagg.topologyAPI.Result.Status.*;


@TestMethodOrder(OrderAnnotation.class)
public class TestToplogyAPI {
	private static TopologyAPI topologyAPI;//this object will be used by all tests

	@BeforeAll
	public static void init() {
		topologyAPI=new TopologyAPI();

	}
	@Test
	@Order(1)
	public void readToplogyFromJsonFileTest() {
		File top1=new File(TestToplogyAPI.class.getResource("top1.json").getFile());
		File top2=new File(TestToplogyAPI.class.getResource("top2.json").getFile());

		Result result=topologyAPI.readTopologyFromJsonFile(top1.getAbsolutePath());
		Topology topology=result.getTopology();
		assertEquals(result.getStatus(), SUCCEED);
		assertEquals("top1",topology.getId());
		assertEquals(2,topology.getComponents().size());

		result=topologyAPI.readTopologyFromJsonFile(top2.getAbsolutePath());
		assertEquals(result.getStatus(), SUCCEED);
		topology=result.getTopology();
		assertEquals("top2",topology.getId());
		assertEquals(4,topology.getComponents().size());

		result=topologyAPI.readTopologyFromJsonFile("not a path");
		assertEquals(result.getStatus(), FAIL);



	}
	@Test
	@Order(2)
	public void writeToplogyFromJsonFileTest() {
		//go to your user home directory you will find a file called top1.json
		Result result=topologyAPI.writeTopologyToJson("top1");
		Topology topology=result.getTopology();
		assertEquals(result.getStatus(), SUCCEED);
		assertEquals("top1",topology.getId());
		assertEquals(2,topology.getComponents().size());
		
		result=topologyAPI.writeTopologyToJson("top2");
		assertEquals(result.getStatus(), SUCCEED);
		topology=result.getTopology();
		assertEquals("top2",topology.getId());
		assertEquals(4,topology.getComponents().size());
		
		result=topologyAPI.writeTopologyToJson("not exist");
		assertEquals(result.getStatus(), FAIL);
		result=topologyAPI.writeTopologyToJson(null);
		
		assertEquals(result.getStatus(), FAIL);


	}
	@Test
	@Order(3)
	public void getTopologiesTest() {
		List<Topology>topologies=topologyAPI.getTopologies();

		assertEquals(2,topologies.size());
		assertEquals("top2",topologies.get(1).getId());
		assertNotEquals(0, topologies.get(0).getComponents());
		assertNotEquals(0, topologies.get(1).getComponents());



	}
	@Test
	@Order(4)
	public void getTopologyComponentsTest() {
		List<Component>components=topologyAPI.getTopologyComponents("top1");

		assertEquals(2,components.size());
		assertEquals("res1",components.get(0).getId());
		assertEquals(1.5, components.get(1).getComponentValues().getDefaultValue());
		
		components=topologyAPI.getTopologyComponents("top2");		
		assertEquals(4,components.size());
		assertEquals("res1",components.get(0).getId());
		assertEquals(200, components.get(1).getComponentValues().getDefaultValue());
		
		components=topologyAPI.getTopologyComponents("not exist");
		assertNull(components);


	}
	@Test
	@Order(5)
	public void getTopologyComponentsWithNetlistNode() {
		List<Component>components=topologyAPI.getTopologyComponentsWithNetlistNode("top1", "n1");
		assertEquals(2,components.size());
		assertEquals("res1",components.get(0).getId());
		assertEquals(1.5, components.get(1).getComponentValues().getDefaultValue());

		components=topologyAPI.getTopologyComponentsWithNetlistNode("top2", "vcc");
		assertEquals(3,components.size());
		assertEquals("res2",components.get(0).getId());
		assertEquals(1.5, components.get(1).getComponentValues().getDefaultValue());

		components=topologyAPI.getTopologyComponentsWithNetlistNode("not exist", "vcc");
		assertNull(components);

		components=topologyAPI.getTopologyComponentsWithNetlistNode("top1", "not a node");
		assertTrue(components.isEmpty());



	}
	@Test
	@Order(6)
	public void testDeleteTopolog() {
		List<Topology>topologies=topologyAPI.getTopologies();

		assertEquals(2,topologies.size());
		Result result=topologyAPI.deleteTopology("top1");
		assertEquals(result.getStatus(),SUCCEED);
		topologies=topologyAPI.getTopologies();
		assertEquals(1,topologies.size());
		assertNotEquals(0, topologies.get(0).getComponents());


		result=topologyAPI.deleteTopology("top1");
		assertEquals(result.getStatus(), FAIL);



	}

}
