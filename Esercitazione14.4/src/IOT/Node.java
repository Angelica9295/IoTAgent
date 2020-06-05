package IOT;


import java.util.HashMap;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="node")
@XmlAccessorType (XmlAccessType.FIELD)
public class Node {

	public enum State{on,off,failed};
	private String id ;
	private String address;
	private double latitude;
	private double longitude;
	private int lenght;
	private State stat;
	private HashMap<String,Sensor> sensors = new HashMap<>();
	
	public Node(String id, String address, double latitude, double longitude,HashMap<String,Sensor> sensors) {
		this.id = id;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.stat =State.on;
		this.sensors = sensors;
		this.lenght = 0;
	}
	
	public Node(String id, String address, double latitude, double longitude ,State state) {
		this.id = id;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.stat =state;		
		this.lenght =0;
	}

	public int getlenght() {
		
		return lenght;
	}
	public Node() {}
	

		public void AddSensors(Sensor nd) {
		sensors.put(nd.getContextAttribute(), nd);
		lenght +=1;
		}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}

	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public State getState() {
		return stat;
	}
	public void setState(State state) {
		this.stat = state;
	}
	public HashMap<String, Sensor> getSensors() {
		return sensors;
	}
	public void setSensors(HashMap<String, Sensor> sensors) {
		this.sensors = sensors;
	}
	public String getId() {
		return id;
	}
	@Override
	public String toString() {
		for( Sensor ss : sensors.values()) System.out.println(ss.toString());
		return "Node [id=" + id + ", address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + "]"+ this.sensors;
	}
	
}
