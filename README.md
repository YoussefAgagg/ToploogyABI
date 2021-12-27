## TopologyAPI
solving this task [TopologyAPI](https://drive.google.com/file/d/1aaS5tMk67M53RSlXQGP2TpWg470htJK3/view?usp=sharing)
## Technologies
* java 11
* [junit5](https://github.com/junit-team/junit5)
* [Gson library](https://github.com/google/gson)

# docs
* the json file formate the api read and write
```
{
  "id": "top1",
  "components": [
    {
      "type": "resistor",
      "id": "res1",
      "resistance": {
        "default": 100,
        "min": 10,
        "max": 1000
      },
      "netlist": {
        "t1": "vdd",
        "t2": "n1"
      }
    },
    {
      "type": "nmos",
      "id": "m1",
      "m(1)": {
        "default": 1.5,
        "min": 1,
        "max": 2
      },
      "netlist": {
        "drain": "n1",
        "gate": "vin",
        "source": "vss"
      }
    }
  ]
}
```

# using the api
```
TopologyApi topologyAPI=new TopologyApi();
```
 * Read a topology from a given JSON file
 
```
 Result result=topologyAPI.readTopologyFromJsonFile(String path)
 if(result.getStatus==Result.Statuse.SUCCEED){
   Topology topology=result.getTopology();
   // 
 }else{
 //
 }
```
* Write  topology to a JSON file

```
Result result=topologyAPI.writeTopologyToJson(String topologyID)
 if(result.getStatus==Result.Statuse.SUCCEED){
   Topology topology=result.getTopology();
   // 
 }else{
 //
 }
```
 * to get alist of topologies are currently in the memory
 
```
List<Topology>topologies= topologyAPI.getTopologies();
```
* Delete a  topology

```
Result result=topologyAPI.deleteTopology(String topologyID)
```
* Query about which components are in a given topology

```
 List<Component>components = topologyAPI.getTopologyComponents(String topologyID);
```
* Query about which components are connected to a given netlist node in a given topology

```
List<Component>components = topologyAPI.getTopologyComponentsWithNetlistNode(String topologyID,String netlisNodeID);
```











