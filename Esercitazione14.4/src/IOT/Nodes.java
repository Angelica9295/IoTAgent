package IOT;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="nodes")
@XmlAccessorType (XmlAccessType.FIELD)
public class Nodes {
	private List<Node> collection;
	@SuppressWarnings("unused")
	private int lenght;
	public Nodes() { this.collection = new ArrayList<>();this.lenght = 0;}
	public Nodes(List<Node> collection) { this.collection = collection; this.lenght = collection.size();}
	public List<Node> getCollection() {return this.collection;}
	public void setCollection(List<Node>collection) {this.collection = collection;}
	public String toString() {
		return "Nodes : [collection="  + "]";
	}
	public void AddNode(Node nd ) { this.collection.add(nd); this.lenght +=1;}

}