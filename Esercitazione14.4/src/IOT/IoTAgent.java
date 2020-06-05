package IOT;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.ws.rs.core.Response;



public class IoTAgent  implements IoTAgentService{
	private HashMap<String, Node> nodescollection = new HashMap<String,Node>();
	;
	 public IoTAgent() {
	}
	@Override
	public Nodes GetNodesDetail() {
		return new Nodes(new ArrayList<Node>(nodescollection.values()));
	}
	@Override
	public Node GetNodeDetail(String nodeId) {
		return nodescollection.get(nodeId);
	}
	@Override
	public void DeleteNode(String nodeId) {
		nodescollection.remove(nodeId);
		
	}
	@Override
	public Node GetNodeBylatLongrad(double latitude, double longitude) {
		for ( Node nd : nodescollection.values()) {
			if( nd.getLatitude() == latitude && nd.getLongitude() == longitude)
				return nd;
		}
		return null;
	}
	@Override
	public Sensor getSensorByAttribute(String nodeId, String attribute) {
		Node nd = nodescollection.get(nodeId);
	  return nd.getSensors().get(attribute);
	}
	@Override
	public void DeleteSensorByAttribute(String nodeId, String attribute) {
		// TODO Auto-generated method stub
		Node nd = nodescollection.get(nodeId);
		nd.getSensors().remove(attribute);
		
	}
	@Override
	public Response CreateNodes(Nodes nodes) {
		for( Node nd : nodes.getCollection())
			nodescollection.put(nd.getId(), nd);
		URI uri = null;
		try {
			uri = new URI("/nodes");
		}catch(URISyntaxException e) {}
		return Response.created(uri).build();
		
			
		
	}
	@Override
	public Sample GetSampleById(String nodeId, String attribute, String sampleId) {
		return nodescollection.get(nodeId).getSensors().get(attribute).getSamples().get(sampleId);
	
	}
	@Override
	public String GetSampleEverage(String nodeId, String attribute) {
		HashMap<String, Sample> samples =  nodescollection.get(nodeId).getSensors().get(attribute).getSamples();
		double everage = 0;
		for(Sample s : samples.values())
			everage += s.getUltimoValRic();
		return String.valueOf(everage/samples.size());
	}
	@Override
	public Response CreateNode(String nodeId, Node node) {
		nodescollection.put(nodeId, node);
		URI uri = null;
		try {
			uri = new URI("/nodes/"+nodeId);
		}catch(URISyntaxException e) {}
		return Response.created(uri).build();
	}
	@Override
	public Response CreateSensors(String nodeId, Sensors sensors) {
	    
		Node nd = nodescollection.get(nodeId);
	 nd.setSensors(sensors.getSensors());
		
		URI uri = null;
		try {
			uri = new URI("/nodes/"+nodeId+"/sensors");
		}catch(URISyntaxException e) {}
		return Response.created(uri).build();
	}
	@Override
	public Response CreateSamples(String nodeId, String attribute, Samples samples) {
		Sensor sr = nodescollection.get(nodeId).getSensors().get(attribute);
		for(Sample sp : samples.getCollection().values()) {
			sr.AddSample(sp);
		}
			URI uri = null;
			try {
				uri = new URI("/nodes/"+nodeId+"/sensors");
			}catch(URISyntaxException e) {}
			return Response.created(uri).build();
	}
	@Override
	public void SetNode(String nodeId,Node node) {
		nodescollection.get(nodeId).setAddress(node.getAddress());
		nodescollection.get(nodeId).setLatitude(node.getLatitude());
		nodescollection.get(nodeId).setLongitude(node.getLongitude());
		nodescollection.get(nodeId).setState(node.getState());
		nodescollection.get(nodeId).setSensors(node.getSensors());		
	}
	@Override
	public void setSensor(String nodeId, String attribute,Sensor sensor) {
		Sensor sr =nodescollection.get(nodeId).getSensors().get(attribute);
		sr.setCode(sensor.getCode());
		sr.setContextAttribute(sensor.getContextAttribute());
		sr.setId(sensor.getId());
		sr.setPrecision(sensor.getPrecision());
		sr.setSamplePeriod(sensor.getSamplePeriod());
		sr.setState(sensor.getState());
		sr.setSamples(sensor.getSamples());
	
		// TODO Auto-generated method stub
		
	}
	@Override
	public Sample GetLastSample(String nodeId, String attribute) {
		if(nodescollection.get(nodeId).getSensors().get(attribute).getSamples() != null && nodescollection.get(nodeId).getSensors().get(attribute).getSamples().size() != 0 ) {
			System.out.println("restituisco per " + nodeId + " te " + attribute);
			System.out.println(nodescollection.get(nodeId).getSensors().get(attribute).getSamples().size());
			NavigableMap<String, Sample> map =new TreeMap<>( nodescollection.get(nodeId).getSensors().get(attribute).getSamples());
			return  map.lastEntry() != null ? map.lastEntry().getValue()  : null;


		}
	return null;
	}

}

