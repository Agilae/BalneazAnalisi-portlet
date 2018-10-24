package it.tobeup;

import facade.analisifacade;
import facade.configfacade;
import facade.descvaluefacade;
import facade.markerfacade;
import it.twobeup.liferay.service.ittwobeupServiceUtil;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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
import org.primefaces.event.SelectEvent;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;

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
import com.twobe.arpac.service.balneazione_analisiLocalServiceUtil;
import com.twobe.arpac.service.balneazione_pointLocalServiceUtil;
import com.twobe.arpac.service.configLocalServiceUtil;
import com.twobe.arpac.service.genericLocalServiceUtil;



@ManagedBean
@ViewScoped
public class search implements Serializable {
 
	ThemeDisplay td;
	String urlDashboard;

	markerfacade scf = new markerfacade();

	List<analisifacade> lstAnalisi = new ArrayList<analisifacade>();
	List<markerfacade> filteredRows = new ArrayList<markerfacade>();

	
	FacesContext context;
	
	configfacade conf = new configfacade();
	
    @PostConstruct
    public void init() 
    {
		context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		//ResourceBundle backendText = app.getResourceBundle(context, "labels");
		PortletRequest portletRequest = (PortletRequest)context.getExternalContext().getRequest();
		td = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		urlDashboard = td.getPortalURL() + td.getPathContext(); //+ PortletProps.get("urlnuovapratica");
		
		scf.setPathPopupPortlet(PortletProps.get("pathpopupportlet"));
		scf.setCloseOnSave(portletRequest.getParameter("closeOnSave"));
        scf.setPopupId(portletRequest.getParameter("popupId"));
        
        try {
			conf.setPathIcone(configLocalServiceUtil.getconfig("path.icone").getValore());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void leggiAnalisi()
    {

    	
    	
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		
		String filtroData="1=1";
		if (scf.getDataAnDa()!=null && scf.getDataAnA()!=null)
			filtroData = "((a.data_analisi BETWEEN '"+dt.format(scf.getDataAnDa())+" 00:00:00' AND '"+dt.format(scf.getDataAnA())+" 23:59:59'))";

    	String sSql = "select analisiId,data_analisi,ora_analisi,tipo,temperatura_aria,temperatura_acqua,enterococchi,escherichia,codPoint "+
				  "from arpac_balneazione_analisi a "+
				  "where "+
				  filtroData + " AND "+
				  "a.codPoint like '%"+scf.getCodPoint()+"%' "+
				  "order by data_analisi,ora_analisi desc";

		
    	
		System.out.println(sSql);
		List lst = genericLocalServiceUtil.genericQueryAsLocal(sSql,
				"analisiId,data_analisi,ora_analisi,tipo    ,temperatura_aria,temperatura_acqua,enterococchi,escherichia,codPoint",
				"long     ,date        ,String     ,String  ,double          ,double           ,double      ,double     ,String  ");
		
		lstAnalisi.clear();
		for (Object obj : lst)
		{
			Object[] arrayobject = (Object[])obj;
			analisifacade scf = new analisifacade();
			
			scf.setAnalisiId((Long)arrayobject[0]);
			scf.setData_analisi((Date)arrayobject[1]);
			scf.setOra_analisi(genericLocalServiceUtil.getStrAsLocal(arrayobject[2]));
			scf.setTipo(genericLocalServiceUtil.getStrAsLocal(arrayobject[3]));
			if (arrayobject[4]!=null)
				scf.setTemperatura_aria((double)arrayobject[4]);
			
			if (arrayobject[5]!=null)
				scf.setTemperatura_acqua((double)arrayobject[5]);
			
			if (arrayobject[6]!=null)
				scf.setEnterococchi((double)arrayobject[6]);
			
			if (arrayobject[7]!=null)
				scf.setEscherichia((double)arrayobject[7]);

			scf.setCodPoint(genericLocalServiceUtil.getStrAsLocal(arrayobject[8]));
			
			if (scf.getTipo().toUpperCase().trim().equals("R"))
				scf.setToolTipTipo("(Routinario) Prelievo programmato da calendario nel punto della rete di monitoraggio");
				
			if (scf.getTipo().toUpperCase().trim().equals("S"))
				scf.setToolTipTipo("(Supplementare) Prelievo nel punto di rete in caso di esito sfavorevole del campionamento routinario");
				
			if (scf.getTipo().toUpperCase().trim().equals("PS"))
				scf.setToolTipTipo("(Punti Studio) Prelievo aggiuntivo in punti predefiniti in corrispondenza di potenziali fonti di inquinamento");

			if (scf.getTipo().toUpperCase().trim().equals("DEL"))
				scf.setToolTipTipo("(DELimitazione) Prelievo a distanza crescente a dx e a sx rispetto al punto della rete per definire i limiti del tratto interessato dall'eventuale inquinamento");

			if (scf.getTipo().toUpperCase().trim().equals("EME"))
				scf.setToolTipTipo("(EMErgenza) Prelievo in caso di anomalie riscontrate da ARPAC o segnalate da altri Enti per la ricerca di IDROCARBURI, TENSIOATTIVI, MICROBIOLOGIA, FITOPLANCTON");
			
			if (scf.getTipo().toUpperCase().trim().equals("ANN"))
				scf.setToolTipTipo("(ANNullato) Prelievo inserito erroneamente");
			
			lstAnalisi.add(scf);
		}
		
		
		
    	
    }

    
	public void elimina(analisifacade ofc)
	{
		if (ofc.getAnalisiId()>0)
		{
			balneazione_analisiLocalServiceUtil.createbalneazione_analisi(ofc.getAnalisiId());
			leggiAnalisi();
		}
		
	}

    
	public void showAnalisi(analisifacade ofc)
	{
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);
		options.put("width", 800);
		options.put("height", 500);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("closable", true);		
		
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		List<String> paramValue = new ArrayList<String>();
		paramValue.add(LiferayWindowState.POP_UP.toString());
		params.put("p_p_state", paramValue);
		
		List<String> analisiId = new ArrayList<String>();
		analisiId.add(String.valueOf(ofc.getAnalisiId()));
		params.put("analisiId", analisiId);
		
		RequestContext.getCurrentInstance().openDialog("edit", options, params);
	}
	

	public void addAnalisi()
	{
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);
		options.put("width", 800);
		options.put("height", 500);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("closable", true);		
		
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		List<String> paramValue = new ArrayList<String>();
		paramValue.add(LiferayWindowState.POP_UP.toString());
		params.put("p_p_state", paramValue);
		
		List<String> analisiId = new ArrayList<String>();
		analisiId.add("0");
		params.put("analisiId", analisiId);
		
		RequestContext.getCurrentInstance().openDialog("edit", options, params);
	}
	

	public void dialogReturnEdit(SelectEvent event)
	{
		 System.out.println("ON DIALOG RETURN");
	}
    


	public markerfacade getScf() {
		return scf;
	}

	public void setScf(markerfacade scf) {
		this.scf = scf;
	}

	public List<markerfacade> getFilteredRows() {
		return filteredRows;
	}

	public void setFilteredRows(List<markerfacade> filteredRows) {
		this.filteredRows = filteredRows;
	}

	public List<analisifacade> getLstAnalisi() {
		return lstAnalisi;
	}

	public void setLstAnalisi(List<analisifacade> lstAnalisi) {
		this.lstAnalisi = lstAnalisi;
	}


	
}