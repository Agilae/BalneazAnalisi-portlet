package it.tobeup;

import facade.analisifacade;
import facade.combofacade;
import facade.markerfacade;
import it.twobeup.liferay.service.ittwobeupServiceUtil;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.faces.util.lang.StringPool;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;
import org.pentaho.reporting.engine.classic.core.layout.output.AbstractReportProcessor;
import org.pentaho.reporting.engine.classic.core.modules.output.pageable.pdf.PdfReportUtil;
import org.pentaho.reporting.engine.classic.core.parameters.ParameterDefinitionEntry;
import org.pentaho.reporting.engine.classic.core.parameters.ReportParameterDefinition;


import org.pentaho.reporting.libraries.repository.ContentIOException;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceCreationException;
import org.pentaho.reporting.libraries.resourceloader.ResourceException;
import org.pentaho.reporting.libraries.resourceloader.ResourceKeyCreationException;
import org.pentaho.reporting.libraries.resourceloader.ResourceLoadingException;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;


import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.portlet.PortletProps;
import com.twobe.arpac.model.balneazione_analisi;
import com.twobe.arpac.model.balneazione_classificazione;
import com.twobe.arpac.model.balneazione_point;
import com.twobe.arpac.model.balneazione_province;
import com.twobe.arpac.model.balneazione_tipologia;
import com.twobe.arpac.service.balneazione_analisiLocalServiceUtil;
import com.twobe.arpac.service.balneazione_classificazioneLocalServiceUtil;
import com.twobe.arpac.service.balneazione_pointLocalServiceUtil;
import com.twobe.arpac.service.balneazione_provinceLocalServiceUtil;
import com.twobe.arpac.service.balneazione_tipologiaLocalServiceUtil;
import com.twobe.arpac.service.genericLocalServiceUtil;


@ManagedBean
@ViewScoped
public class edit implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -1888510317744766055L;
	ThemeDisplay td;
	String urlDashboard;

	analisifacade sc = new analisifacade();

	List<balneazione_tipologia> lstTipo = new ArrayList<balneazione_tipologia>();
	List<balneazione_classificazione> lstClassif = new ArrayList<balneazione_classificazione>();
	List<balneazione_province> lstProvincia = new ArrayList<balneazione_province>();
	
	List<combofacade> lstComuni = new ArrayList<combofacade>();

	FacesContext context;
	
    @PostConstruct
    public void init() 
    {
		context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		//ResourceBundle backendText = app.getResourceBundle(context, "labels");
		PortletRequest portletRequest = (PortletRequest)context.getExternalContext().getRequest();
		td = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		urlDashboard = td.getPortalURL() + td.getPathContext(); //+ PortletProps.get("urlnuovapratica");
		
		sc.setPathPopupPortlet(PortletProps.get("pathpopupportlet"));
		sc.setCloseOnSave(portletRequest.getParameter("closeOnSave"));
        sc.setPopupId(portletRequest.getParameter("popupId"));

        sc.setAnalisiId(Long.parseLong(portletRequest.getParameter("analisiId")));
    
		try 
		{
			lstTipo = balneazione_tipologiaLocalServiceUtil.getbalneazione_tipologias(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			lstClassif = balneazione_classificazioneLocalServiceUtil.getbalneazione_classificaziones(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			lstProvincia = balneazione_provinceLocalServiceUtil.getbalneazione_provinces(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} 
		catch (SystemException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        if (sc.getCloseOnSave() == null)
        	sc.setCloseOnSave( "true");
        
        if (sc.getAnalisiId()>0)
        	leggiAnalisi();

    }

    

    
    public void handleClose(CloseEvent event) 
	{
		System.out.println("CLOSE EVENT");
	}	
	
	public void dialogReturn(SelectEvent event)
	{
		 System.out.println("ON DIALOG RETURN");

		 Object obj = event.getObject();
	     if(obj == null)
	     {
	    	 System.out.println("CHIUSOOOO");
	         // handle close
	     }
	     else
	     {
	         // handle object returned
	     }
	}	
    
    public void leggiAnalisi()
    {
    	try {
			
    		balneazione_analisi scu =balneazione_analisiLocalServiceUtil.getbalneazione_analisi(sc.getAnalisiId());
			if (scu!=null)
			{
				sc.setCodPoint(scu.getCodPoint());
				
				sc.setData_analisi(scu.getData_analisi());
				sc.setAltezza_onde(scu.getAltezza_onde());
				sc.setOra_analisi(scu.getOra_analisi());
				sc.setIntensita_corrente(scu.getIntensita_corrente());
				sc.setDirezione_corrente(scu.getDirezione_corrente());

				sc.setIntensita_vento(scu.getIntensita_vento());
				sc.setDirezione_vento(scu.getDirezione_vento());
				
				
				sc.setPioggia(scu.getPioggia());
				//sc.setNome(scu.get());
				sc.setNuvolosita(scu.getNuvolosita());
				sc.setDirezione_vento(scu.getDirezione_vento());
				sc.setData_routinaria(scu.getData_Calendario());
				sc.setData_calendario(scu.getData_Calendario());
				sc.setStato_mare(scu.getStato_mare());
				sc.setDirezione_onde(scu.getDirezione_onde());

				if (scu.getData_inserimento()!=null)
					sc.setData_inserimento(scu.getData_inserimento());
				else
				{
					SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
					Date da = dt.parse("1900-01-01");					
					sc.setData_inserimento(da);
				}
				
				sc.setUtente(scu.getUtente());
				sc.setTipo(scu.getTipo());   
				sc.setTemperatura_aria(scu.getTemperatura_aria());   
				sc.setTemperatura_acqua(scu.getTemperatura_acqua());   
				sc.setEnterococchi(scu.getEnterococchi());   
				sc.setEscherichia(scu.getEscherichia());
				sc.setSchiuma_colore(scu.getSchiuma_c());
				sc.setSchiuma_tipo(scu.getSchiuma_t());
				sc.setSchiuma_densita(scu.getSchiuma_d());
				sc.setSchiuma_estensione(scu.getSchiuma_e());

				sc.setMucillagine_colore(scu.getMucillagine_c());
				sc.setMucillagine_tipo(scu.getMucillagine_t());
				sc.setMucillagine_densita(scu.getMucillagine_d());
				sc.setMucillagine_estensione(scu.getMucillagine_e());

				sc.setMeduse(scu.getMeduse());
				sc.setRifiuti_solidi(0);
				if (scu.getRifiuti_solidi())
					sc.setRifiuti_solidi(1);

				sc.setResidui(0);
				if (scu.getResidui_bitumosi())
					sc.setResidui(1);
				
			}

		} catch (PortalException | SystemException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    
    
    public void saveAnalisi(boolean bClose)
    {
    	try {
			    	
    	if (sc.getAnalisiId()==0)
    	{
   			sc.setAnalisiId(CounterLocalServiceUtil.increment(balneazione_analisi.class.getName(), 1));
   			
   			balneazione_analisi scu =balneazione_analisiLocalServiceUtil.createbalneazione_analisi(sc.getAnalisiId());

			scu.setCodPoint(sc.getCodPoint());
			
			scu.setData_analisi(sc.getData_analisi());
			scu.setAltezza_onde(sc.getAltezza_onde());
			scu.setOra_analisi(sc.getOra_analisi());
			scu.setIntensita_corrente(sc.getIntensita_corrente());
			scu.setDirezione_corrente(sc.getDirezione_corrente());
			
			scu.setIntensita_vento(sc.getIntensita_vento());
			scu.setDirezione_vento(sc.getDirezione_vento());
			
			
			scu.setPioggia(sc.getPioggia());
			//sc.setNome(scu.get());
			scu.setNuvolosita(sc.getNuvolosita());
			scu.setDirezione_vento(sc.getDirezione_vento());
			scu.setData_routinaria(sc.getData_routinaria());
			scu.setData_Calendario(sc.getData_calendario());
			scu.setStato_mare(sc.getStato_mare());
			scu.setDirezione_onde(sc.getDirezione_onde());
			scu.setData_inserimento(new Date());
			scu.setUtente(td.getUser().getScreenName());
			scu.setTipo(sc.getTipo());   
			scu.setTemperatura_aria(sc.getTemperatura_aria());   
			scu.setTemperatura_acqua(sc.getTemperatura_acqua());   
			scu.setEnterococchi(sc.getEnterococchi());   
			scu.setEscherichia(sc.getEscherichia());
			scu.setSchiuma_c(sc.getSchiuma_colore());
			scu.setSchiuma_t(sc.getSchiuma_tipo());
			scu.setSchiuma_d(sc.getSchiuma_densita());
			scu.setSchiuma_e(sc.getSchiuma_estensione());

			scu.setMucillagine_c(sc.getMucillagine_colore());
			scu.setMucillagine_t(sc.getMucillagine_tipo());
			scu.setMucillagine_d(sc.getMucillagine_densita());
			scu.setMucillagine_e(sc.getMucillagine_estensione());

			scu.setMeduse(sc.getMeduse());
			
			scu.setRifiuti_solidi(false);
			if (sc.getRifiuti_solidi()==1)
				scu.setRifiuti_solidi(true);

			scu.setResidui_bitumosi(false);
			if (sc.getResidui()==1)
				scu.setResidui_bitumosi(true);
			
   			balneazione_analisiLocalServiceUtil.addbalneazione_analisi(scu);
    	}
    	else
    	{

    		balneazione_analisi scu =balneazione_analisiLocalServiceUtil.getbalneazione_analisi(sc.getAnalisiId());

			scu.setCodPoint(sc.getCodPoint());
			
			scu.setData_analisi(sc.getData_analisi());
			scu.setAltezza_onde(sc.getAltezza_onde());
			scu.setOra_analisi(sc.getOra_analisi());
			scu.setIntensita_corrente(sc.getIntensita_corrente());
			scu.setDirezione_corrente(sc.getDirezione_corrente());

			scu.setIntensita_vento(sc.getIntensita_vento());
			scu.setDirezione_vento(sc.getDirezione_vento());
			
			scu.setPioggia(sc.getPioggia());
			//sc.setNome(scu.get());
			scu.setNuvolosita(sc.getNuvolosita());
			scu.setDirezione_vento(sc.getDirezione_vento());
			scu.setData_routinaria(sc.getData_routinaria());
			scu.setData_Calendario(sc.getData_calendario());
			scu.setStato_mare(sc.getStato_mare());
			scu.setDirezione_onde(sc.getDirezione_onde());
			scu.setData_modifica(new Date());
			scu.setUtente(td.getUser().getScreenName());
			scu.setTipo(sc.getTipo());   
			scu.setTemperatura_aria(sc.getTemperatura_aria());   
			scu.setTemperatura_acqua(sc.getTemperatura_acqua());   
			scu.setEnterococchi(sc.getEnterococchi());   
			scu.setEscherichia(sc.getEscherichia());
			scu.setSchiuma_c(sc.getSchiuma_colore());
			scu.setSchiuma_t(sc.getSchiuma_tipo());
			scu.setSchiuma_d(sc.getSchiuma_densita());
			scu.setSchiuma_e(sc.getSchiuma_estensione());

			scu.setMucillagine_c(sc.getMucillagine_colore());
			scu.setMucillagine_t(sc.getMucillagine_tipo());
			scu.setMucillagine_d(sc.getMucillagine_densita());
			scu.setMucillagine_e(sc.getMucillagine_estensione());

			scu.setMeduse(sc.getMeduse());
			scu.setRifiuti_solidi(false);
			if (sc.getRifiuti_solidi()==1)
				scu.setRifiuti_solidi(true);

			scu.setResidui_bitumosi(false);
			if (sc.getResidui()==1)
				scu.setResidui_bitumosi(true);
			
   			balneazione_analisiLocalServiceUtil.updatebalneazione_analisi(scu);
   			
    	}
    		
		} catch (SystemException | PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    	if (bClose)
    		RequestContext.getCurrentInstance().closeDialog(null);		

    	
    }
    
    public void esci()
    {
    	System.out.println("IN ESCI");
		RequestContext.getCurrentInstance().closeDialog(null);		
    }
    


	public List<balneazione_province> getLstProvincia() {
		return lstProvincia;
	}


	public void setLstProvincia(List<balneazione_province> lstProvincia) {
		this.lstProvincia = lstProvincia;
	}



	public List<combofacade> getLstComuni() {
		return lstComuni;
	}



	public void setLstComuni(List<combofacade> lstComuni) {
		this.lstComuni = lstComuni;
	}



	public List<balneazione_tipologia> getLstTipo() {
		return lstTipo;
	}



	public void setLstTipo(List<balneazione_tipologia> lstTipo) {
		this.lstTipo = lstTipo;
	}



	public List<balneazione_classificazione> getLstClassif() {
		return lstClassif;
	}



	public void setLstClassif(List<balneazione_classificazione> lstClassif) {
		this.lstClassif = lstClassif;
	}




	public analisifacade getSc() {
		return sc;
	}




	public void setSc(analisifacade sc) {
		this.sc = sc;
	}

	
}