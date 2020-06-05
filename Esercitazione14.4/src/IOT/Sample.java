package IOT;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="sample")

public class Sample {
	private double UltimoValRic;
	private Date data;
	private String SampleId;
	public Sample() {}
	public double getUltimoValRic() {
		return UltimoValRic;
	}
	public void setUltimoValRic(double ultimoValRic) {
		UltimoValRic = ultimoValRic;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getSampleId() {
		return SampleId;
	}
	public void setSampleId(String sampleId) {
		SampleId = sampleId;
	}
	public Sample(double ultimoValRic, Date data,String sampleId) {
		super();
		UltimoValRic = ultimoValRic;
		this.data = data;
		this.SampleId =sampleId;
	}
	@Override
	public String toString() {
		return "Sample [UltimoValRic=" + UltimoValRic + ", data=" + data + "]";
	}
	
	

}

