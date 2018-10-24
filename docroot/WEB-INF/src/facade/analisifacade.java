package facade;






import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class analisifacade implements Serializable 
{

	String popupId;
	String closeOnSave;
	String pathPopupPortlet;

	long analisiId;
    Date data_analisi;
    double altezza_onde;
    String ora_analisi;   
    long intensita_corrente;
    double direzione_corrente;
    String codPoint;
    String pioggia;
    String nome;
    String nuvolosita;
    String direzione_vento;
    String intensita_vento;
    String data_routinaria;
    String data_calendario;
    String stato_mare;
    String direzione_onde;
    Date data_inserimento;
    String utente;
    String codEx;
    
    
    
    
    
    String tipo;   
    double temperatura_aria;   
    double temperatura_acqua;   
    double enterococchi;   
    double escherichia;
    String toolTipTipo;
    
    
    String schiuma_colore;
    String schiuma_tipo;
    String schiuma_densita;
    String schiuma_estensione;

    String mucillagine_colore;
    String mucillagine_tipo;
    String mucillagine_densita;
    String mucillagine_estensione;

    String meduse;
    int rifiuti_solidi;
    int residui;
    

    
    
    
    public String getPopupId() {
		return popupId;
	}
	public void setPopupId(String popupId) {
		this.popupId = popupId;
	}
	public String getCloseOnSave() {
		return closeOnSave;
	}
	public void setCloseOnSave(String closeOnSave) {
		this.closeOnSave = closeOnSave;
	}
	public String getPathPopupPortlet() {
		return pathPopupPortlet;
	}
	public void setPathPopupPortlet(String pathPopupPortlet) {
		this.pathPopupPortlet = pathPopupPortlet;
	}
	public Date getData_analisi() {
		return data_analisi;
	}
	public void setData_analisi(Date data_analisi) {
		this.data_analisi = data_analisi;
	}
	public String getOra_analisi() {
		return ora_analisi;
	}
	public void setOra_analisi(String ora_analisi) {
		this.ora_analisi = ora_analisi;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getTemperatura_aria() {
		return temperatura_aria;
	}
	public void setTemperatura_aria(double temperatura_aria) {
		this.temperatura_aria = temperatura_aria;
	}
	public double getTemperatura_acqua() {
		return temperatura_acqua;
	}
	public void setTemperatura_acqua(double temperatura_acqua) {
		this.temperatura_acqua = temperatura_acqua;
	}
	public double getEnterococchi() {
		return enterococchi;
	}
	public void setEnterococchi(double enterococchi) {
		this.enterococchi = enterococchi;
	}
	public double getEscherichia() {
		return escherichia;
	}
	public void setEscherichia(double escherichia) {
		this.escherichia = escherichia;
	}
	public long getAnalisiId() {
		return analisiId;
	}
	public void setAnalisiId(long analisiId) {
		this.analisiId = analisiId;
	}
	public String getToolTipTipo() {
		return toolTipTipo;
	}
	public void setToolTipTipo(String toolTipTipo) {
		this.toolTipTipo = toolTipTipo;
	}
	public String getSchiuma_colore() {
		return schiuma_colore;
	}
	public void setSchiuma_colore(String schiuma_colore) {
		this.schiuma_colore = schiuma_colore;
	}
	public String getMucillagine_colore() {
		return mucillagine_colore;
	}
	public void setMucillagine_colore(String mucillagine_colore) {
		this.mucillagine_colore = mucillagine_colore;
	}
	public String getMeduse() {
		return meduse;
	}
	public void setMeduse(String meduse) {
		this.meduse = meduse;
	}
	public int getRifiuti_solidi() {
		return rifiuti_solidi;
	}
	public void setRifiuti_solidi(int rifiuti_solidi) {
		this.rifiuti_solidi = rifiuti_solidi;
	}
	public int getResidui() {
		return residui;
	}
	public void setResidui(int residui) {
		this.residui = residui;
	}
	public double getAltezza_onde() {
		return altezza_onde;
	}
	public void setAltezza_onde(double altezza_onde) {
		this.altezza_onde = altezza_onde;
	}
	public double getDirezione_corrente() {
		return direzione_corrente;
	}
	public void setDirezione_corrente(double direzione_corrente) {
		this.direzione_corrente = direzione_corrente;
	}
	public String getCodPoint() {
		return codPoint;
	}
	public void setCodPoint(String codPoint) {
		this.codPoint = codPoint;
	}
	public String getPioggia() {
		return pioggia;
	}
	public void setPioggia(String pioggia) {
		this.pioggia = pioggia;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNuvolosita() {
		return nuvolosita;
	}
	public void setNuvolosita(String nuvolosita) {
		this.nuvolosita = nuvolosita;
	}
	public String getDirezione_vento() {
		return direzione_vento;
	}
	public void setDirezione_vento(String direzione_vento) {
		this.direzione_vento = direzione_vento;
	}
	public String getData_routinaria() {
		return data_routinaria;
	}
	public void setData_routinaria(String data_routinaria) {
		this.data_routinaria = data_routinaria;
	}
	public String getData_calendario() {
		return data_calendario;
	}
	public void setData_calendario(String data_calendario) {
		this.data_calendario = data_calendario;
	}
	public String getStato_mare() {
		return stato_mare;
	}
	public void setStato_mare(String stato_mare) {
		this.stato_mare = stato_mare;
	}
	public String getDirezione_onde() {
		return direzione_onde;
	}
	public void setDirezione_onde(String direzione_onde) {
		this.direzione_onde = direzione_onde;
	}
	public Date getData_inserimento() {
		return data_inserimento;
	}
	public void setData_inserimento(Date data_inserimento) {
		this.data_inserimento = data_inserimento;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public String getSchiuma_tipo() {
		return schiuma_tipo;
	}
	public void setSchiuma_tipo(String schiuma_tipo) {
		this.schiuma_tipo = schiuma_tipo;
	}
	public String getSchiuma_densita() {
		return schiuma_densita;
	}
	public void setSchiuma_densita(String schiuma_densita) {
		this.schiuma_densita = schiuma_densita;
	}
	public String getSchiuma_estensione() {
		return schiuma_estensione;
	}
	public void setSchiuma_estensione(String schiuma_estensione) {
		this.schiuma_estensione = schiuma_estensione;
	}
	public String getMucillagine_tipo() {
		return mucillagine_tipo;
	}
	public void setMucillagine_tipo(String mucillagine_tipo) {
		this.mucillagine_tipo = mucillagine_tipo;
	}
	public String getMucillagine_densita() {
		return mucillagine_densita;
	}
	public void setMucillagine_densita(String mucillagine_densita) {
		this.mucillagine_densita = mucillagine_densita;
	}
	public String getMucillagine_estensione() {
		return mucillagine_estensione;
	}
	public void setMucillagine_estensione(String mucillagine_estensione) {
		this.mucillagine_estensione = mucillagine_estensione;
	}
	public long getIntensita_corrente() {
		return intensita_corrente;
	}
	public void setIntensita_corrente(long intensita_corrente) {
		this.intensita_corrente = intensita_corrente;
	}
	public String getIntensita_vento() {
		return intensita_vento;
	}
	public void setIntensita_vento(String intensita_vento) {
		this.intensita_vento = intensita_vento;
	}
	public String getCodEx() {
		return codEx;
	}
	public void setCodEx(String codEx) {
		this.codEx = codEx;
	}   

	
	
}