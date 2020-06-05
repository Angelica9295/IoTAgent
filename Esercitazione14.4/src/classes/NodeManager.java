package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.client.WebClient;

import IOT.Node;
import IOT.Nodes;
import IOT.Sample;
import IOT.Sensor;

public class NodeManager  {
 private HashMap<String,SensorsManager> sensorsmanager ;
 HashMap<String, Node> nodes  = new HashMap<>();
 HashMap<String, Sensor> sensors  = new HashMap<>();
 HashMap<String, Sample> samples  = new HashMap<>();
 
 public NodeManager() {
	 sensorsmanager = new HashMap<>();
	
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(new File("nodes.txt"));
			while(sc.hasNextLine()) {
				String nodeId = sc.nextLine();
				String address = sc.nextLine();
				double latitude = sc.nextDouble(); sc.nextLine();
				double longitude = sc.nextDouble(); sc.nextLine();
				String state = sc.nextLine();
			
				
				Node nd = new Node(nodeId, address, latitude,longitude,IOT.Node.State.valueOf(state));
				nodes.put(nd.getId(),nd);
				
			}
			
			
			
			@SuppressWarnings("resource")
			Scanner sc1 = new Scanner(new File("sensors.txt"));
			while(sc1.hasNextLine()) {
				String id = sc1.nextLine();
				String ct = sc1.nextLine();
				String code = sc1.nextLine();
				String precision = sc1.nextLine();
				double samplePeriod = sc1.nextDouble(); sc1.nextLine();
				String state = sc1.nextLine();
				String nodeParent = sc1.nextLine();
				Sensor sr = new Sensor(id,ct,code,precision,samplePeriod,IOT.Sensor.States.valueOf(state));
				Node nd = SearchNode(nodeParent);
				nd.AddSensors(sr);
				sensors.put(sr.getId(), sr);
				SensorsManager sm = new SensorsManager(sr,sr.getId());
				sensorsmanager.put(sm.getid(), sm);
				sm.SetParent(nd);
				
				
			}
			
			
			@SuppressWarnings("resource")
			Scanner sc2 = new Scanner(new File("samples.txt"));
			while(sc2.hasNextLine()) {
				String id = sc2.nextLine();
				String data = sc2.nextLine();
				SimpleDateFormat sdf  = new SimpleDateFormat("dd/mm/yyyy");
				Date dt = sdf.parse(data);
				Double ult = sc2.nextDouble();sc2.nextLine();
				String idS = sc2.nextLine();
				Sample sp = new  Sample(ult,dt,id);
				samples.put(sp.getSampleId(), sp);
				
				SensorsManager sm = SearchSensorManager(idS);
				Sensor s = sensors.get(idS);
				s.AddSample(sp);
				sm.AddSample(sp);
			}
			
			WebClient client = WebClient.create("http://localhost:8080/Esercitazione14.4/Agent/rest");
			client.accept("application/json").type("application/json");
			client.path("/nodes");
			Nodes nds = new Nodes (new ArrayList<>(this.nodes.values()));
		    @SuppressWarnings("unused")
			Response res = client.post(nds);

		     
		   for(SensorsManager sm : this.sensorsmanager.values()) sm.start();
			
		} catch (FileNotFoundException e) {
		System.out.println("file non trovato ");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
 }
 
 public Node SearchNode(String nodeID) {
	 return this.nodes.get(nodeID);
 }
 public Sensor SearchSensor(String SensorId) {
	 return sensors.get(SensorId);
 }
 public SensorsManager SearchSensorManager(String SensorId) {
	 return sensorsmanager.get(SensorId);
 }
	public static void main(String[] args) {
	 new NodeManager();
	}
}
