package facade;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class markerfacade implements Serializable 
{

	String popupId;
	String closeOnSave;
	String pathPopupPortlet;
	
	String urlIconTipo;
	String urlIconNoInq;
	String urlIconDivPerm;
	
	
	
	long pointId;
	String codPoint;
	String descrizione;
	long idTipologia;
	String tipologia;
	long idClass;
	String descClassif;
	String nomePoint;
	String snippet;
	long provinciaId;
	String Provincia;
	long comuneId;
	String Comune;
	String styleUrl;
	String anno;
	Date dataAnDa = new Date(); 
	Date dataAnA = new Date(); 
	
	String altitudeMode;
	long cod;
	String codArea;
	long codEx;
	String codPointEx;
	long extrude;
	
	String latitudine;
	String longitudine;
	String lat_inizio_area;
	String lon_inizio_area;
	String lat_fine_area;
	String lon_fine_area;
	
	long divietoPermanente;
	long divietoNoInquinamento;

	
	public String getCodPoint() {
		return codPoint;
	}
	public void setCodPoint(String codPoint) {
		this.codPoint = codPoint;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public long getIdTipologia() {
		return idTipologia;
	}
	public void setIdTipologia(long idTipologia) {
		this.idTipologia = idTipologia;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public String getDescClassif() {
		return descClassif;
	}
	public void setDescClassif(String descClassif) {
		this.descClassif = descClassif;
	}
	public String getProvincia() {
		return Provincia;
	}
	public void setProvincia(String provincia) {
		Provincia = provincia;
	}
	public String getComune() {
		return Comune;
	}
	public void setComune(String comune) {
		Comune = comune;
	}
	public long getPointId() {
		return pointId;
	}
	public void setPointId(long pointId) {
		this.pointId = pointId;
	}
	public long getIdClass() {
		return idClass;
	}
	public void setIdClass(long idClass) {
		this.idClass = idClass;
	}
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
	public String getAltitudeMode() {
		return altitudeMode;
	}
	public void setAltitudeMode(String altitudeMode) {
		this.altitudeMode = altitudeMode;
	}
	public String getCodArea() {
		return codArea;
	}
	public void setCodArea(String codArea) {
		this.codArea = codArea;
	}
	public String getCodPointEx() {
		return codPointEx;
	}
	public void setCodPointEx(String codPointEx) {
		this.codPointEx = codPointEx;
	}
	public long getCod() {
		return cod;
	}
	public void setCod(long cod) {
		this.cod = cod;
	}
	public long getCodEx() {
		return codEx;
	}
	public void setCodEx(long codEx) {
		this.codEx = codEx;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public String getStyleUrl() {
		return styleUrl;
	}
	public void setStyleUrl(String styleUrl) {
		this.styleUrl = styleUrl;
	}
	public long getExtrude() {
		return extrude;
	}
	public void setExtrude(long extrude) {
		this.extrude = extrude;
	}
	public long getProvinciaId() {
		return provinciaId;
	}
	public void setProvinciaId(long provinciaId) {
		this.provinciaId = provinciaId;
	}
	public long getComuneId() {
		return comuneId;
	}
	public void setComuneId(long comuneId) {
		this.comuneId = comuneId;
	}
	public String getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}
	public String getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}
	public String getLat_inizio_area() {
		return lat_inizio_area;
	}
	public void setLat_inizio_area(String lat_inizio_area) {
		this.lat_inizio_area = lat_inizio_area;
	}
	public String getLon_inizio_area() {
		return lon_inizio_area;
	}
	public void setLon_inizio_area(String lon_inizio_area) {
		this.lon_inizio_area = lon_inizio_area;
	}
	public String getLat_fine_area() {
		return lat_fine_area;
	}
	public void setLat_fine_area(String lat_fine_area) {
		this.lat_fine_area = lat_fine_area;
	}
	public String getLon_fine_area() {
		return lon_fine_area;
	}
	public void setLon_fine_area(String lon_fine_area) {
		this.lon_fine_area = lon_fine_area;
	}
	public long getDivietoPermanente() {
		return divietoPermanente;
	}
	public void setDivietoPermanente(long divietoPermanente) {
		this.divietoPermanente = divietoPermanente;
	}
	public long getDivietoNoInquinamento() {
		return divietoNoInquinamento;
	}
	public void setDivietoNoInquinamento(long divietoNoInquinamento) {
		this.divietoNoInquinamento = divietoNoInquinamento;
	}
	public String getNomePoint() {
		return nomePoint;
	}
	public void setNomePoint(String nomePoint) {
		this.nomePoint = nomePoint;
	}
	public String getUrlIconTipo() {
		return urlIconTipo;
	}
	public void setUrlIconTipo(String urlIconTipo) {
		this.urlIconTipo = urlIconTipo;
	}
	public String getUrlIconDivPerm() {
		return urlIconDivPerm;
	}
	public void setUrlIconDivPerm(String urlIconDivPerm) {
		this.urlIconDivPerm = urlIconDivPerm;
	}
	public String getUrlIconNoInq() {
		return urlIconNoInq;
	}
	public void setUrlIconNoInq(String urlIconNoInq) {
		this.urlIconNoInq = urlIconNoInq;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public Date getDataAnDa() {
		return dataAnDa;
	}
	public void setDataAnDa(Date dataAnDa) {
		this.dataAnDa = dataAnDa;
	}
	public Date getDataAnA() {
		return dataAnA;
	}
	public void setDataAnA(Date dataAnA) {
		this.dataAnA = dataAnA;
	}	
	
}