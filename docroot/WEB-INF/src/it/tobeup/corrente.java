package it.tobeup;

import facade.analisifacade;
import facade.configfacade;
import facade.descvaluefacade;
import facade.markerfacade;
import it.twobeup.liferay.service.ittwobeupServiceUtil;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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
import com.twobe.arpac.service.balneazione_pointLocalServiceUtil;
import com.twobe.arpac.service.configLocalServiceUtil;
import com.twobe.arpac.service.genericLocalServiceUtil;



@ManagedBean
@ViewScoped
public class corrente implements Serializable {
 
	ThemeDisplay td;
	String urlDashboard;
	String codPoint;
	String anno;
	
	
	markerfacade scf = new markerfacade();

	List<analisifacade> lstAnalisi = new ArrayList<analisifacade>();
	List<descvaluefacade> lstDett = new ArrayList<descvaluefacade>();
	
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

        codPoint = portletRequest.getParameter("codPoint");
        anno = portletRequest.getParameter("anno");
        
        try {
			conf.setPathIcone(configLocalServiceUtil.getconfig("path.icone").getValore());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       if (codPoint!= null && anno!= null)
    	   leggiAnalisi();

    }

    public void leggiAnalisi()
    {
		String sSql = "select analisiId,data_analisi,ora_analisi,tipo,temperatura_aria,temperatura_acqua,enterococchi,escherichia  "+
					  "from arpac_balneazione_analisi a "+
					  "where a.data_analisi like '"+anno+"%' and a.codPoint = '"+codPoint+"' "+
					  "order by data_analisi desc";
	
		
		System.out.println(sSql);
		List lst = genericLocalServiceUtil.genericQueryAsLocal(sSql,
				"analisiId,data_analisi,ora_analisi,tipo    ,temperatura_aria,temperatura_acqua,enterococchi,escherichia,schiuma_t,schiuma_d,schiuma_c,schiuma_e,mucillagine_t,mucillagine_d,mucillagine_c,mucillagine_e,meduse,rifiuti_solidi,residui_bitumosi",
				"long     ,date        ,String     ,String  ,double          ,double           ,double      ,double     ,String   ,String   ,String   ,String   ,String       ,String       ,String       ,String       ,String,Integer       ,Integer         ");
		
		lstAnalisi.clear();
		for (Object obj : lst)
		{
			Object[] arrayobject = (Object[])obj;
			analisifacade scf = new analisifacade();
			
			scf.setAnalisiId((Long)arrayobject[0]);
			scf.setData_analisi((Date)arrayobject[1]);
			scf.setOra_analisi(genericLocalServiceUtil.getStrAsLocal(arrayobject[2]));
			scf.setTipo(genericLocalServiceUtil.getStrAsLocal(arrayobject[3]));
			scf.setTemperatura_aria((double)arrayobject[4]);
			scf.setTemperatura_acqua((double)arrayobject[5]);
			scf.setEnterococchi((double)arrayobject[6]);
			scf.setEscherichia((double)arrayobject[7]);
			
			/*
			scf.setSchiuma_aspetto(schiuma_aspetto);
			scf.setSchiuma_colore(schiuma_colore);
			scf.setSchiuma_forma(schiuma_forma);
			
			scf.setMucillagine_aspetto(mucillagine_aspetto);
			scf.setMucillagine_colore(mucillagine_colore);
			scf.setMucillagine_forma(mucillagine_forma);
			
			scf.setMeduse(meduse);
			
			scf.setResidui(residui);
			scf.setRifiuti_solidi(rifiuti_solidi);
			*/
			
			
			
			
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
		
		
		
		sSql = "select b.codEx,b.codArea,b.nome,c.nome as provincia,d.nome as comune from arpac_balneazione_point b "+ 
					  "left join arpac_balneazione_province c on c.codice = b.codProvincia "+
					  "left join arpac_balneazione_comuni d on d.codice = b.codComune "+
					  "where b.codPointEx = '"+codPoint+"' or b.codPoint = '"+codPoint+"'";
		
		System.out.println(sSql);
		lst = genericLocalServiceUtil.genericQueryAsLocal(sSql,
				"codEx ,codArea,nome  ,provincia,comune",
				"String,String ,String,String   ,String");
		
		for (Object obj : lst)
		{
			Object[] arrayobject = (Object[])obj;

			descvaluefacade df = new descvaluefacade();
			df.setDescriz("Provincia");
			df.setValore(genericLocalServiceUtil.getStrAsLocal(arrayobject[3]));
			lstDett.add(df);
			
			df = new descvaluefacade();
			df.setDescriz("Comune");
			df.setValore(genericLocalServiceUtil.getStrAsLocal(arrayobject[4]));
			lstDett.add(df);

			df = new descvaluefacade();
			df.setDescriz("Codice Punto");
			df.setValore(genericLocalServiceUtil.getStrAsLocal(arrayobject[0]));
			lstDett.add(df);

			df = new descvaluefacade();
			df.setDescriz("Punto di prelievo");
			df.setValore(genericLocalServiceUtil.getStrAsLocal(arrayobject[2]));
			lstDett.add(df);

		
		}
    	
    }
    
    
	public markerfacade getScf() {
		return scf;
	}

	public void setScf(markerfacade scf) {
		this.scf = scf;
	}

	public List<analisifacade> getLstAnalisi() {
		return lstAnalisi;
	}

	public void setLstAnalisi(List<analisifacade> lstAnalisi) {
		this.lstAnalisi = lstAnalisi;
	}

	public List<descvaluefacade> getLstDett() {
		return lstDett;
	}

	public void setLstDett(List<descvaluefacade> lstDett) {
		this.lstDett = lstDett;
	}



	
}