package com.github.youssefagagg.topologyAPI;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static com.github.youssefagagg.topologyAPI.Result.Status.*;
/*
 * 
 */

public class TopologyAPI {
	//the user home directory the files will be written to this directory
	private static final String HOME_PATH = System.getProperty("user.home");
	//a map with the String key the ID of the topology and value is the Topology object 
	private Map<String,Topology>topologiesMap;
	/*
	 *an object of the main class in Gson Java library that can be used to convert Java Objects into their JSON representation.
	 *  It can also be used to convert a JSON string to an equivalent Java object.
	 */
	Gson gson;


	public TopologyAPI() {
		topologiesMap=new HashMap<String, Topology>();
		 gson = new GsonBuilder().registerTypeAdapter(Topology.class,
				 new TopologyJsonSerializDeSerializ()).create();
	}
	/*
	 * Read a topology from a given JSON file and store it in the map
	 * @param path the absolute path for the jsone file
	 * @return  a Result that wrap information about the query result succeed or fail a message and the topology that was read
	 */
	public Result readTopologyFromJsonFile(String path) {
		Result result;
		try {
			String json=Files.readString(Path.of(path));
			
			Topology topology=gson.fromJson(json, Topology.class);
			topologiesMap.put(topology.getId(), topology);
			result= new Result(SUCCEED, "topolgy added successfully",topology);
			
			
		}catch (Exception e) {
			
			result= new Result(FAIL, "failed to add topolgy, "+e.getMessage(),e);
			
		}
		return result;
		
	}
	/*
	 * Write  topology to a JSON file.
	 * @param topologyID the ID for the Topology that will be saved
	 * @return  a Result that wrap information about the query result succeed or fail a message and the topology that was written
	 */
	public Result writeTopologyToJson(String topologyID) {
		Result result;
		try {

			Gson gson = new GsonBuilder().registerTypeAdapter(Topology.class,
															 new TopologyJsonSerializDeSerializ()).create();
			
			Topology topology=topologiesMap.get(topologyID);
			if(topology==null) result= new Result(FAIL, "the ID doesn't exist.",new Exception("ID doesn't exist."));
			else {
			String json=gson.toJson(topology,Topology.class);
			Files.writeString(Path.of(HOME_PATH+"/"+topology.getId()+".json"), json);
	
			result= new Result(SUCCEED, "topolgy added successfully",topology);
			
			}
			
		}catch (Exception e) {
			result= new Result(FAIL, "failed to add topolgy, "+e.getMessage(),e);
			
		}
		return result;
	}
	/*
	 * Query about which topologies are currently in the memory
	 * @return a list of Topology
	 */
	public List<Topology> getTopologies(){
		return new ArrayList<Topology>(topologiesMap.values());
	}
	/*
	 * Delete a  topology from memory 
	 * @param topologyID
	 * @return  a Result that wrap information about the query result succeed or fail a message and the topology that was deleted
	 */
	public Result deleteTopology(String topologyID) {
		Topology topology=topologiesMap.remove(topologyID);
		Result result;
		if(topology!=null) {
			result= new Result(SUCCEED, "Topology deleted successfuly",topology);
			
		}else {
		 result= new Result(FAIL, "the ID doesn't exist ",new Exception("\"the ID doesn't exist \""));
		
		}
		
		return result;
		
	}
/*
 * Query about which components are in a given topology
 * @param topology ID
 * @return a list of components in the given toplogy or null if the id is not exist
 */
	public List<Component> getTopologyComponents(String topologyID){
		Topology topology=topologiesMap.get(topologyID);
		return topology!=null?topology.getComponents():null;
	}
	/*
	 * Query about which components are connected to a given netlist node in a given topology
	 * @param topology ID
	 * @param netlisNodeID
	 * @return a list of components that are connected to a given netlist node or null if the topology id is not exist 
 	*/
	public List<Component> getTopologyComponentsWithNetlistNode(String topologyID,String netlisNodeID){
		Topology topology=topologiesMap.get(topologyID);
		
		return topology!=null?topology.getComponentsWithNetlistNode(netlisNodeID):null;

	}

}
