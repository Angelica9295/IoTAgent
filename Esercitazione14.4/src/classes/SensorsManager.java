package classes;

import java.util.Date;
import java.util.HashMap;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.client.WebClient;

import IOT.Node;
import IOT.Sample;
import IOT.Samples;
import IOT.Sensor;

public class SensorsManager extends Thread {
private HashMap<String, Sample> samples;
private Sensor sr;
private String id ;
private Node parent;

public SensorsManager(Sensor sr,String id) {
	samples = new HashMap<>();
	this.sr = sr;
	this.id = id;
}

public String getid() {
	return this.id;
}
public void AddSample(Sample sr) {
	this.samples.put(sr.getSampleId(), sr);
}
public void SetParent(Node nd) {
	this.parent = nd;
}
@Override
public void run() {
	int i = 10;
	while(true) {
		try {
			// problema relativo ai diversi periodi di campionamento
			sleep((long)sr.getSamplePeriod()*60*1000);
			
			WebClient client = WebClient.create("http://localhost:8080/Esercitazione14.4/Agent/rest");
			client.accept("application/json").type("application/json");
			client.path("/nodes/"+parent.getId()+"/sensors/"+sr.getContextAttribute()+"/samples");
			//client.path("/nodes/"+parent.getId()+"/sensors/"+sr.getContextAttribute());
			Sample sm1 = new Sample(12.4, new Date(), String.valueOf(i++));
			
			Samples sms = new Samples();
			sms.AddSample(sm1);
			@SuppressWarnings("unused")
			Response res = client.post(sms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
/*
public HashMap<String, Sample> GetSinteSamples(){
	Sample sp = new Sample();
	
	for(Sample sp2 : this.samples.values()) {
		
	}
}

public HashMap<String, Sample> FilterByAttribute(String attribute){
	HashMap<String, Sample> sms= new HashMap<>();
	for( Sample sp : samples.values()) {
		
	}
}*/
public void start() {

	super.start();
}

}
