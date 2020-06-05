package IOT;

import java.util.HashMap;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


	@XmlRootElement(name="sensor")
	@XmlAccessorType (XmlAccessType.FIELD)
	public class Sensor {
		public enum States {actuve,inactive,failed};
		private String ids;
		private String contextAttribute;
		private String code;
		private String precision;
		@Override
		public String toString() {
			return "Sensor [ids=" + ids + ", contextAttribute=" + contextAttribute + ", code=" + code + ", precision="
					+ precision + ", samplePeriod=" + samplePeriod + ", state=" + state + "]" + samples;
		}
		private double samplePeriod;
		private States state;
		private  HashMap<String,Sample> samples;
		private int lenght;
		public Sensor(String ids, String contextAttribute, String code, String precision, double samplePeriod) {
			this.ids = ids;
			this.contextAttribute = contextAttribute;
			this.code = code;
			this.precision = precision;
			this.samplePeriod = samplePeriod;
			state = States.actuve;
			samples = new HashMap<>();
			this.lenght = 0;
		}
		public Sensor(String ids, String contextAttribute, String code, String precision, double samplePeriod,States state) {
			this.ids = ids;
			this.contextAttribute = contextAttribute;
			this.code = code;
			this.precision = precision;
			this.samplePeriod = samplePeriod;
			this.state =state;
			samples = new HashMap<>();
			this.lenght = 0;
		}
		public Sensor() {}
		public String getId() {
			return ids;
		}
		public void AddSample(Sample sp) {
			// modificare con temper
			this.samples.put(sp.getSampleId(), sp); this.lenght +=1;

		}
		public void setId(String id) {
			this.ids = id;
		}
		public String getContextAttribute() {
			return contextAttribute;
		}
		public void setContextAttribute(String contextAttribute) {
			this.contextAttribute = contextAttribute;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getPrecision() {
			return precision;
		}
		public void setPrecision(String precision) {
			this.precision = precision;
		}
		public double getSamplePeriod() {
			return samplePeriod;
			

		}
		public void setSamplePeriod(double samplePeriod) {
			this.samplePeriod = samplePeriod;
		}
		public States getState() {
			return state;
		}
		public void setState(States state) {
			this.state = state;
		}
		public HashMap<String,Sample> getSamples() {
			return samples;
		}
		public void setSamples(HashMap<String,Sample>samples) {
			this.samples = samples;
		}
		public String getIds() {
			return ids;
		}
		public void setIds(String ids) {
			this.ids = ids;
		}
		
		public int getLenght() {
			return lenght;
		}
		public void setLenght(int lenght) {
			this.lenght = lenght;
		}
		
}
