package facade;


import java.io.Serializable;

public class descvaluefacade implements Serializable {

	String descriz;
	String valore;
	public String getDescriz() {
		return descriz;
	}
	public void setDescriz(String descriz) {
		this.descriz = descriz;
	}
	public String getValore() {
		return valore;
	}
	public void setValore(String valore) {
		this.valore = valore;
	}
}
