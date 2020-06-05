package IOT;


import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="sensors")
@XmlAccessorType (XmlAccessType.FIELD)
public class Sensors {
		private HashMap<String,Sensor> collection;
		public Sensors() {collection = new HashMap<>();}
		public Sensors(HashMap<String,Sensor> bs) {collection = bs;}
		public void setCollection(HashMap<String,Sensor>c) {collection = c;}
		
		public HashMap<String, Sensor> getCollection() {
			return collection;
		}
		@Override
		public String toString() {
			return "Sensors [collection=" + collection + "]";
		}
		public void AddSensor(Sensor sr) { collection.put(sr.getContextAttribute(), sr);} 
		public HashMap<String,Sensor> getSensors(){
			return this.collection;
		}
}
