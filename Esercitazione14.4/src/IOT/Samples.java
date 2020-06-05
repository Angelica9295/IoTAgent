package IOT;


import java.util.HashMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="samples")
@XmlAccessorType (XmlAccessType.FIELD)

public class Samples {
	private HashMap<String, Sample> collection;
	private double ValoreMedio;
	private int lenght;
	public  Samples() {collection = new HashMap<String, Sample>();}
	public Samples(HashMap<String, Sample> nodes) { this.collection = nodes; this.lenght = collection.size();}
	public HashMap<String, Sample> getCollection() {return this.collection;}
	public void setCollection(HashMap<String, Sample>collection) {this.collection = collection;}
	@Override
	public String toString() {
		return "Samples [collection=" + collection + ", ValoreMedio=" + ValoreMedio + ", lenght=" + lenght + "]";
	}
	public Samples(HashMap<String, Sample> collection, double valoreMedio, int lenght) {
		super();
		this.collection = collection;
		ValoreMedio = valoreMedio;
		this.lenght = lenght;
	}
	public void AddSample(Sample sample ) { this.collection.put(sample.getSampleId(),sample); this.lenght +=1;}
	public double getValoreMedio() {
		return ValoreMedio;
	}
	public void setValoreMedio(double valoreMedio) {
		ValoreMedio = valoreMedio;
	}
	public int getLenght() {
		return lenght;
	}
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	
	
}
