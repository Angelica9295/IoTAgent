package IOT;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import IOT.Nodes;

@Consumes({"application/json","text/plain"})
@Produces({"application/json","text/plain"})

@Path("/nodes")
public interface IoTAgentService {

	
	// @GET	 	/nodes														/* invoked by consumers */
	@GET
	Nodes GetNodesDetail();
	// @GET  	/nodes/{nodeId}		/* invoked by consumers */
	@GET
	@Path("/{nodeId}")
	Node GetNodeDetail(@PathParam("nodeId") String nodeId);
	// @GET  	/nodes?{latitude}&{longitude}&{radius}						/* invoked by consumers */
	@GET
	@Path("?{latitude}&{longitude}") //questo non serve basta fare la query in modo diverso
	Node GetNodeBylatLongrad(@QueryParam("latitude") double latitude,@QueryParam("longitude") double longitude);	
	// @GET  	/nodes/{nodeId}/sensors/{attribute}							/* invoked by consumers */
	@Path("/{nodeId}/sensors/{attribute}")
	@GET
    Sensor getSensorByAttribute(@PathParam("nodeId") String nodeId,@PathParam("attribute") String attribute);
	// @GET  	/nodes/{nodeId}/sensors/{attribute}/samples/{sampleId}		/* invoked by consumers */
	@GET
	@Path("/{nodeId}/sensors/{attribute}/samples/{sampleId}")
	Sample GetSampleById(@PathParam("nodeId") String nodeId,@PathParam("attribute")String attribute,@PathParam("sampleId")String sampleId);
	@GET
	@Path("/{nodeId}/sensors/{attribute}/samples/last")
	Sample GetLastSample(@PathParam("nodeId") String nodeId,@PathParam("attribute")String attribute);
	// @GET		/nodes/{nodeId}/sensors/{attribute}/samples_average			/* invoked by consumers */
	@GET
	@Path("/{nodeId}/sensors/{attribute}/{samples_average}")
	String GetSampleEverage(@PathParam("nodeId") String nodeId,@PathParam("attribute")String attribute);
	// @POST 	/nodes		
	@POST/* invoked by nodes */
	Response CreateNodes(Nodes nodes);
	// @POST 	/nodes/{nodeId}												/* invoked by nodes */
	@POST
	@Path("/{nodeId}")
	Response CreateNode(@PathParam("nodeId")String nodeId,Node node);
	// @POST 	/nodes/{nodeId}/sensors										/* invoked by nodes */
	@POST
	@Path("/{nodeId}/sensors")
	Response  CreateSensors(@PathParam("nodeId")String nodeId, Sensors sensors);
	// @POST 	/nodes/{nodeId}/sensors/{attribute}/samples					/* invoked by nodes */
	@POST
	@Path("/{nodeId}/sensors/{attribute}/samples")
	Response    CreateSamples(@PathParam("nodeId") String nodeId,@PathParam("attribute")String attribute, Samples samples);
	// @PUT 	/nodes/{nodeId}												/* invoked by nodes */
	@PUT
	@Path("/{nodeId}")
	void SetNode(@PathParam("nodeId") String nodeId,Node node);
	// @PUT 	/nodes/{nodeId}/sensors/{attribute}							/* invoked by nodes */
	@PUT
	@Path("/{nodeId}/sensors/{attribute}")
	void setSensor(@PathParam("nodeId") String nodeId,@PathParam("attribute")String attribute,Sensor sensor);
	// @DELETE	/nodes/{nodeId}												/* invoked by nodes or infrastructure */
	@DELETE
	@Path("/{nodeId}")
	void DeleteNode(@PathParam("nodeId")String nodeId);
	// @DELETE  /nodes/{nodeId}/sensors/{attribute}							/* invoked by nodes */
	@Path("/{nodeId}/sensors/{attribute}")
	@DELETE
	void DeleteSensorByAttribute(@PathParam("nodeId")String nodeId,@PathParam("attribute")String attribute);
	// @DELETE	/nodes/{nodeId}/sensors/{attribute}/samples/{sampleId}		/* invoked by nodes or consumers */
	
}
