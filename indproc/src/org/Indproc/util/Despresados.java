package org.Indproc.util;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.Indproc.util.Utils;


@ManagedBean
@ViewScoped
public class Despresados extends Utils implements Serializable {
	
	private LazyDataModel<Despresados> lazyModel;  
	
	/**
	 * @return the lazyModel
	 */
	public LazyDataModel<Despresados> getLazyModel() {
		return lazyModel;
	}
	
	public Despresados(){
 		
		lazyModel  = new LazyDataModel<Despresados>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 7217573531435419432L;
			
            @Override
			public List<Despresados> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
            	//Filtro
            	if (filters != null) {
               	 for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
               		 String filterProperty = it.next(); // table column name = field name
                     filterValue = filters.get(filterProperty);
               	 }
                }
            	try { 
            		//Consultas
            		select(first, pageSize,sortField, filterValue);
					//Counter
					counter(filterValue);
					//Contador lazy
					lazyModel.setRowCount(rows);  //Necesario para crear la paginación
				} catch (SQLException | NamingException e) {	
					e.printStackTrace();
				}             
				return list;  
            } 
            
            
            //Arregla bug de primefaces
            @Override
            /**
			 * Arregla el Issue 1544 de primefaces lazy loading porge generaba un error
			 * de divisor equal a cero, es solamente un override
			 */
            public void setRowIndex(int rowIndex) {
                /*
                 * The following is in ancestor (LazyDataModel):
                 * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
                 */
                if (rowIndex == -1 || getPageSize() == 0) {
                    super.setRowIndex(-1);
                }
                else
                    super.setRowIndex(rowIndex % getPageSize());
            }
            
		};
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Variables de la tabla
	private String pesopdes = "";
	private String diades = "";
	private String montodes = "";
	private String unidaddes = "UND";
	private String semcondes = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("semcon"); 
	private String mescondes = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mescon"); 
	private String anocondes = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("anocon"); 
	private String codalm_o = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("almaceno"); 
	private String codalm_d = "";
	private String dialun = "0";
	private String diamar = "0";
	private String diamie = "0";
	private String diajue = "0";
	private String diavie = "0";
	private String diasab = "0";
	private String diadom = "0";
	private String pcoduser = "";
	private String pclave = "";
	//GETTER'S Y SETTER'S//
	private String zdiades = "";
	private String zmontodes = "";
	private String zunidaddes = "";
	private String zsemcondes = "";
	private String zmescondes = "";
	private String zanocondes = "";
	private String zcodalm_o = "";
	private String zcodalm_d = "";
	private String zuno = "";
	private String zuno_1 = "";
	private String zuno_2 = "";
	private String zdos = "";
	private String zdos_1 = "";
	private String zdos_2 = "";
	private String ztres = "";
	private String ztres_1 = "";
	private String ztres_2 = "";
	private String zpesopdes = "";
	private String zdia1 = "";
	private String zdia2 = "";
	private String zdia3 = "";
	private String zdia4 = "";
	private String zdia5 = "";
	private String zdia6 = "";
	private String zdia7 = "";
	private String zcodigo_o = "";
	private String zcodigo_d = "";
	private String zalmadesc_o = "";
	private String zalmadesc_d = "";

	//GETTER'S Y SETTER'S//
    private int validarOperacion = 0;//Param guardar para validar si guarda o actualiza
    private Object filterValue = "";
    private List<Despresados> list = new ArrayList<Despresados>();//LIstar paises
     FacesMessage msj = null; 
    private int rows; //Registros de tabla
    PntGenerica consulta = new PntGenerica();
     
    //Formateador de la fecha sdfecha
    java.text.SimpleDateFormat sdfecha = new java.text.SimpleDateFormat("dd/MM/yyyy", locale);
    String fecha = sdfecha.format(fecact); //Fecha formateada para insertar en tablas

	public String getZalmadesc_o() {
		return zalmadesc_o;
	}

	public void setZalmadesc_o(String zalmadesc_o) {
		this.zalmadesc_o = zalmadesc_o;
	}

	public String getZalmadesc_d() {
		return zalmadesc_d;
	}

	public void setZalmadesc_d(String zalmadesc_d) {
		this.zalmadesc_d = zalmadesc_d;
	}

	public String getZcodigo_o() {
		return zcodigo_o;
	}

	public void setZcodigo_o(String zcodigo_o) {
		this.zcodigo_o = zcodigo_o;
	}

	public String getZcodigo_d() {
		return zcodigo_d;
	}

	public void setZcodigo_d(String zcodigo_d) {
		this.zcodigo_d = zcodigo_d;
	}

	public String getZdia1() {
		return zdia1;
	}

	public void setZdia1(String zdia1) {
		this.zdia1 = zdia1;
	}

	public String getZdia2() {
		return zdia2;
	}

	public void setZdia2(String zdia2) {
		this.zdia2 = zdia2;
	}

	public String getZdia3() {
		return zdia3;
	}

	public void setZdia3(String zdia3) {
		this.zdia3 = zdia3;
	}

	public String getZdia4() {
		return zdia4;
	}

	public void setZdia4(String zdia4) {
		this.zdia4 = zdia4;
	}

	public String getZdia5() {
		return zdia5;
	}

	public void setZdia5(String zdia5) {
		this.zdia5 = zdia5;
	}

	public String getZdia6() {
		return zdia6;
	}

	public void setZdia6(String zdia6) {
		this.zdia6 = zdia6;
	}

	public String getZdia7() {
		return zdia7;
	}

	public void setZdia7(String zdia7) {
		this.zdia7 = zdia7;
	}

	public String getZpesopdes() {
		return zpesopdes;
	}

	public void setZpesopdes(String zpesopdes) {
		this.zpesopdes = zpesopdes;
	}

	public String getPesopdes() {
		return pesopdes;
	}

	public void setPesopdes(String pesopdes) {
		this.pesopdes = pesopdes;
	}

	public String getDialun() {
		return dialun;
	}

	public void setDialun(String dialun) {
		this.dialun = dialun;
	}

	public String getDiamar() {
		return diamar;
	}

	public void setDiamar(String diamar) {
		this.diamar = diamar;
	}

	public String getDiamie() {
		return diamie;
	}

	public void setDiamie(String diamie) {
		this.diamie = diamie;
	}

	public String getDiajue() {
		return diajue;
	}

	public void setDiajue(String diajue) {
		this.diajue = diajue;
	}

	public String getDiavie() {
		return diavie;
	}

	public void setDiavie(String diavie) {
		this.diavie = diavie;
	}

	public String getDiasab() {
		return diasab;
	}

	public void setDiasab(String diasab) {
		this.diasab = diasab;
	}

	public String getDiadom() {
		return diadom;
	}

	public void setDiadom(String diadom) {
		this.diadom = diadom;
	}

	public String getDiades() {
		return diades;
	}

	public void setDiades(String diades) {
		this.diades = diades;
	}

	public String getMontodes() {
		return montodes;
	}

	public void setMontodes(String montodes) {
		this.montodes = montodes;
	}

	public String getUnidaddes() {
		return unidaddes;
	}

	public void setUnidaddes(String unidaddes) {
		this.unidaddes = unidaddes;
	}

	public String getSemcondes() {
		return semcondes;
	}

	public void setSemcondes(String semcondes) {
		this.semcondes = semcondes;
	}

	public String getMescondes() {
		return mescondes;
	}

	public void setMescondes(String mescondes) {
		this.mescondes = mescondes;
	}

	public String getAnocondes() {
		return anocondes;
	}

	public void setAnocondes(String anocondes) {
		this.anocondes = anocondes;
	}

	public String getCodalm_o() {
		return codalm_o;
	}

	public void setCodalm_o(String codalm_o) {
		this.codalm_o = codalm_o;
	}

	public String getCodalm_d() {
		return codalm_d;
	}

	public void setCodalm_d(String codalm_d) {
		this.codalm_d = codalm_d;
	}

	public String getZdiades() {
		return zdiades;
	}

	public void setZdiades(String zdiades) {
		this.zdiades = zdiades;
	}

	public String getZmontodes() {
		return zmontodes;
	}

	public void setZmontodes(String zmontodes) {
		this.zmontodes = zmontodes;
	}

	public String getZunidaddes() {
		return zunidaddes;
	}

	public void setZunidaddes(String zunidaddes) {
		this.zunidaddes = zunidaddes;
	}

	public String getZsemcondes() {
		return zsemcondes;
	}

	public void setZsemcondes(String zsemcondes) {
		this.zsemcondes = zsemcondes;
	}

	public String getZmescondes() {
		return zmescondes;
	}

	public void setZmescondes(String zmescondes) {
		this.zmescondes = zmescondes;
	}

	public String getZanocondes() {
		return zanocondes;
	}

	public void setZanocondes(String zanocondes) {
		this.zanocondes = zanocondes;
	}

	public String getZcodalm_o() {
		return zcodalm_o;
	}

	public void setZcodalm_o(String zcodalm_o) {
		this.zcodalm_o = zcodalm_o;
	}

	public String getZcodalm_d() {
		return zcodalm_d;
	}

	public void setZcodalm_d(String zcodalm_d) {
		this.zcodalm_d = zcodalm_d;
	}

	public String getZuno() {
		return zuno;
	}


	public void setZuno(String zuno) {
		this.zuno = zuno;
	}


	public String getZdos() {
		return zdos;
	}


	public void setZdos(String zdos) {
		this.zdos = zdos;
	}


	public String getZtres() {
		return ztres;
	}


	public void setZtres(String ztres) {
		this.ztres = ztres;
	}

	public String getPcoduser() {
		return pcoduser;
	}


	public void setPcoduser(String pcoduser) {
		this.pcoduser = pcoduser;
	}


	public String getPclave() {
		return pclave;
	}


	public void setPclave(String pclave) {
		this.pclave = pclave;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////!!!GETTER'S FIJOS DE PROCESOS!!!////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////

	public String getZuno_1() {
		return zuno_1;
	}

	public void setZuno_1(String zuno_1) {
		this.zuno_1 = zuno_1;
	}

	public String getZuno_2() {
		return zuno_2;
	}

	public void setZuno_2(String zuno_2) {
		this.zuno_2 = zuno_2;
	}

	public String getZdos_1() {
		return zdos_1;
	}

	public void setZdos_1(String zdos_1) {
		this.zdos_1 = zdos_1;
	}

	public String getZdos_2() {
		return zdos_2;
	}

	public void setZdos_2(String zdos_2) {
		this.zdos_2 = zdos_2;
	}

	public String getZtres_1() {
		return ztres_1;
	}

	public void setZtres_1(String ztres_1) {
		this.ztres_1 = ztres_1;
	}

	public String getZtres_2() {
		return ztres_2;
	}

	public void setZtres_2(String ztres_2) {
		this.ztres_2 = ztres_2;
	}

	/**
	 * @return the validarOperacion
	 */
	public int getValidarOperacion() {
		return validarOperacion;
	}
	/**
	 * @param validarOperacion the validarOperacion to set
	 */
	public void setValidarOperacion(int validarOperacion) {
		this.validarOperacion = validarOperacion;
	}
	
	/**
	 * @return the list
	 */
	public List<Despresados> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<Despresados> list) {
		this.list = list;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	//Coneccion a base de datos
	//Pool de conecciones JNDIFARM
	Connection con;
	PreparedStatement pstmt = null;
	ResultSet r;
	
	private void insertd() throws NamingException, SQLException {
	        	
	      try {
        	String[] vecalmo = codalm_o.split("\\ - ", -1);
         	String[] vecalmd = codalm_d.split("\\ - ", -1);
         	String diastemp = dialun + '|' + diamar + '|' + diamie + '|' + diajue + '|' + diavie + '|' + diasab + '|' + diadom;
      		String[] montos = diastemp.split("\\|");
      		String diasnombtemp = "LUNES" + '|' + "MARTES" + '|' + "MIERCOLES" + '|' + "JUEVES" + '|' + "VIERNES" + '|' + "SABADO" + '|' + "DOMINGO";
      		String[] diasnomb = diasnombtemp.split("\\|");
	      	Context initContext = new InitialContext();     
	   		DataSource ds = (DataSource) initContext.lookup(JNDI);	 
	          con = ds.getConnection();	         
	      try {
	          for (int i = 0; i <= montos.length - 1; i++){
	        		 //System.out.println("Tama;o del arreglo:" + montos.length);
	        		 //System.out.println("ENTRE:" + diasnomb[i]);	
	                 //System.out.println("ENTRE:" + montos[i]);	
	          String query = "INSERT INTO ENTRADA_DESPRESADOS VALUES (?,?,?,?,?,?,?,?,?,'','')";
	          pstmt = con.prepareStatement(query);
	          pstmt.setString(1, vecalmo[0].toUpperCase());
	          pstmt.setString(2, vecalmd[0].toUpperCase());
	          pstmt.setInt(3, Integer.parseInt(anocondes));
	          pstmt.setInt(4, Integer.parseInt(mescondes));
	          pstmt.setInt(5, Integer.parseInt(semcondes));
	          pstmt.setString(6, diasnomb[i].toUpperCase());
	          pstmt.setInt(7, Integer.parseInt(montos[i]));
	          pstmt.setString(8, unidaddes.toUpperCase());
	          pstmt.setBigDecimal(9, new BigDecimal(pesopdes));
	          pstmt.executeUpdate();
        	 

	           //System.out.println("Almacen origen:" + vecalmo[0]);
	           //System.out.println("Almacen destino:" + vecalmd[0]);
	           //System.out.println("anocon:" + anocondes);
	           //System.out.println("mescon:" + mescondes);
	           //System.out.println("semcon:" + semcondes);
	           //System.out.println("dia:" + diasnomb[i]);
	           //System.out.println("monto:" + montos[i]);
	           //System.out.println("unidad:" + unidaddes);
	           //System.out.println("pesop:" + pesopdes);
	          }// fin del ciclo FOR
	                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro insertado", "");
	                limpiarValores();
	            } catch (SQLException e)  {
	                // e.printStackTrace();
	                 msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
	            }
	            pstmt.close();
	            con.close();           
	        } catch (Exception e) {
	            //e.printStackTrace();
	      
	        }//fin de validacion de usuarios
	        FacesContext.getCurrentInstance().addMessage(null, msj);
	    }
		   
	 /**
     * Actualiza Dotarticulos
     * <b>Parametros del Metodo:<b> String cod_art, String descripcion, String tip_art, String talla.<br>
     * String pool, String login.<br><br>
	 * @throws SQLException 
     **/
    public void update() throws  NamingException, SQLException {
        	   
       String[] vecalmo = codalm_o.split("\\ - ", -1);
       String[] vecalmd = codalm_d.split("\\ - ", -1);
       String diastemp = dialun + '|' + diamar + '|' + diamie + '|' + diajue + '|' + diavie + '|' + diasab + '|' + diadom;
       String[] montos = diastemp.split("\\|");
       String diasnombtemp = "LUNES" + '|' + "MARTES" + '|' + "MIERCOLES" + '|' + "JUEVES" + '|' + "VIERNES" + '|' + "SABADO" + '|' + "DOMINGO";
       String[] diasnomb = diasnombtemp.split("\\|");
       
    	try {
    		//System.out.println("ENTRE AL UPDATE");
    	Context initContext = new InitialContext();     
 		DataSource ds = (DataSource) initContext.lookup(JNDI);

 		con = ds.getConnection();		
 		for (int i = 0; i <= montos.length - 1; i++){
 	   		 //System.out.println("Tama;o del arreglo:" + montos.length);
 	   		 //System.out.println("ENTRE AL DIA:" + diasnomb[i]);	
 	         //System.out.println("SU MONTO ES:" + montos[i]);
        String query = "UPDATE entrada_despresados";
         query += " SET unidad = ?, monto = ?, pesop = ?";
         query += " WHERE codalm_o = ?";
         query += " AND codalm_d = ?";
         query += " AND anocon = ?";
         query += " AND mescon = ?";
         query += " AND semcon = ?";
         query += " AND dia = ?";
         //System.out.println(query);
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, unidaddes.toUpperCase());
        pstmt.setInt(2, Integer.parseInt(montos[i]));
        pstmt.setInt(3, Integer.parseInt(pesopdes));
        pstmt.setString(4, vecalmo[0].toUpperCase());
        pstmt.setString(5, vecalmd[0].toUpperCase());
        pstmt.setInt(6, Integer.parseInt(anocondes));
        pstmt.setInt(7, Integer.parseInt(mescondes));
        pstmt.setInt(8, Integer.parseInt(semcondes));
        pstmt.setString(9, diasnomb[i].toUpperCase());
        pstmt.executeUpdate();
        
        //System.out.println("unidad:" + unidaddes);
        //System.out.println("monto:" + montos[i]);
        //System.out.println("pesop:" + pesopdes);
        //System.out.println("codalm_o:" + vecalmo[0]);
        //System.out.println("codalm_d:" + vecalmd[0]);
        //System.out.println("anocon:" + anocondes);
        //System.out.println("mescon:" + mescondes);
        //System.out.println("semcon:" + semcondes);
        //System.out.println("dia:" + diasnomb[i]);

 		}
        try {
            //Avisando
            if(pstmt.getUpdateCount()==0){
            	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existen registros que coincidan para modificar", "");
            } else {
            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  "Registro modificado con éxito", "");
            }
            limpiarValores();

        } catch (SQLException e) {
        	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), "");
        }
        pstmt.close();
        con.close();
    } catch (Exception e) {
    }
    FacesContext.getCurrentInstance().addMessage(null, msj);
}
		
	public void delete() throws NamingException, IOException, SQLException  {  
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	String[] chkbox = request.getParameterValues("toDelete");
    	
    	 //System.out.println("Entre al Delete");
 	     //System.out.println("Usuario: " + pcoduser + " Password: " + pclave );
    	if (chkbox==null){
    		msj = new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione al menos un check para eliminar", "");
    	} else {
	        try {
	       	
	        	Context initContext = new InitialContext();     
	     		DataSource ds = (DataSource) initContext.lookup(JNDI);

	     		con = ds.getConnection();		
	        	
	        	String param = "'" + StringUtils.join(chkbox, "','") + "'";
	
	        	String query = "DELETE entrada_despresados WHERE codalm_o||codalm_d||anocon||mescon||semcon in (" + param + ")";
	        		        	
	            pstmt = con.prepareStatement(query);
	            //System.out.println("Usuario: " + pcoduser + " Password: " + pclave );
	            //System.out.println(query);
	
	            try {
	                //Avisando
	                pstmt.executeUpdate();
	                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado exitosamente", "");
	                limpiarValores();	
	            } catch (SQLException e) {
	                e.printStackTrace();
	                msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
	            }
	
	            pstmt.close();
	            con.close();
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    	}

    	FacesContext.getCurrentInstance().addMessage(null, msj); 
    }
	
	 	/**
     * Genera las operaciones de guardar o modificar
     * @throws NamingException 
     * @throws SQLException 
     * @throws IOException 
     **/ 
	 public void despresar() throws NamingException, SQLException{   	
	    	if(validarOperacion==0){
	    		insertd();
	    	} else {
	    		update();
	    	}
	    }
	 
    public void select(int first, int pageSize, String sortField, Object filterValue) throws SQLException, NamingException {
        try {	
        	if(codalm_o==null){
        		codalm_o = " - ";
        	}
        	if(codalm_d==null){
        		codalm_d = " - ";
        	}
        	if(anocondes == null){
            	anocondes = "";
            }
            if(mescondes == null){
            	mescondes = "";
            }
            if(semcondes == null){
            	semcondes = "";
            }
        	String[] vecalmo = codalm_o.split("\\ - ", -1);
         	String[] vecalmd = codalm_d.split("\\ - ", -1);
        	//System.out.println("ENTRE AL SELECT");
      		//System.out.println("JNDI: " + JNDIFARM);
        	Context initContext = new InitialContext();     
    		DataSource ds = (DataSource) initContext.lookup(JNDI);
    		con = ds.getConnection();	
      		
      	//Consulta paginada
      	    String query =  "SELECT * FROM"; 
		      	   query +=	"(select query.*,rownum as rn from";
     	     	   query +=	"(select * from (select A.CODALM_O,";
     	     	   query +=	" case ";
     	           query +=	" when A.codalm_o = 'D651G1' then 'PTA EMB BEJUMA PROD TERM' ";
     	     	   query +=	" when A.codalm_o = 'P610G1' then 'PLTA POLLO SN MATEO PROD TERM'";
     	     	   query +=	" when A.codalm_o = 'P606G1' then 'MARACAIBO PRODUCTO TERMINADO' ";
     	     	   query +=	" when A.codalm_o = 'P604G1' then 'PLTA POLLO TEJERÍAS PROD TERMI' ";
     	     	   query +=	" when A.codalm_o = 'P602G1' then 'PLTA POLLO BEJUMA PROD TERMI'";
     	           query +=	" when A.codalm_o = 'P608G1' then 'PLTA POLLO ACARIGUA PROD TERM'"; 
     	     	   query +=	" when A.codalm_o = 'P609G1' then 'PLTA POLLO TEREPAIMA PROD TERM' ";
     	     	   query +=	" when A.codalm_o = 'E601G1' then 'PTA PRECOCIDO VAL PROD TERM' ";
     	           query +=	" when A.codalm_o = 'P614G1' then 'PLTA POLLO FONTANA P TERM CONT'"; 
     	     	   query +=	" when A.codalm_o = 'P617G1' then 'PLTA POLLO DEL CAMPO  PRO TERM'";
     	     	   query +=	" when A.codalm_o = 'D655G1' then 'EMBUTIDOS II PRODUCTO TERMINAD'";
     	     	   query +=	" when A.codalm_o = 'P626G1' then 'GRUPO VENEZUELA AVICOLA' ";
     	     	   query +=	" when A.codalm_o = 'P625G1' then 'PLANTA CHIVACOA PROD TERM'";
     	           query +=	" when A.codalm_o = 'P621G1' then 'SUMINISTROS P Y Z   PROD TERM'";
     	     	   query +=	" when A.codalm_o = 'P622G1' then 'ALMACENES TCN PRODUTCO TERM' ";
     	    	   query +=	" when A.codalm_o = 'QGOB01' then 'GOBIERNO' end as Almacen_O, ";
     			   query +=	" A.CODALM_D, case ";
     			   query +=	" when A.codalm_d = 'D651G1' then 'PTA EMB BEJUMA PROD TERM' ";
     			   query +=	" when A.codalm_d = 'P610G1' then 'PLTA POLLO SN MATEO PROD TERM' ";
     			   query +=	" when A.codalm_d = 'P606G1' then 'MARACAIBO PRODUCTO TERMINADO'";
     			   query +=	" when A.codalm_d = 'P604G1' then 'PLTA POLLO TEJERÍAS PROD TERMI'";
     			   query +=	" when A.codalm_d = 'P602G1' then 'PLTA POLLO BEJUMA PROD TERMI' ";
     			   query +=	" when A.codalm_d = 'P608G1' then 'PLTA POLLO ACARIGUA PROD TERM' ";
     			   query +=	" when A.codalm_d = 'P609G1' then 'PLTA POLLO TEREPAIMA PROD TERM' ";
     			   query +=	" when A.codalm_d = 'E601G1' then 'PTA PRECOCIDO VAL PROD TERM'";
  			       query +=	" when A.codalm_d = 'P614G1' then 'PLTA POLLO FONTANA P TERM CONT'"; 
     			   query +=	" when A.codalm_d = 'P617G1' then 'PLTA POLLO DEL CAMPO  PRO TERM'";
     			   query +=	" when A.codalm_d = 'D655G1' then 'EMBUTIDOS II PRODUCTO TERMINAD'";
     			   query +=	" when A.codalm_d = 'P626G1' then 'GRUPO VENEZUELA AVICOLA'";
     			   query +=	" when A.codalm_d = 'P625G1' then 'PLANTA CHIVACOA PROD TERM'"; 
     			   query +=	" when A.codalm_d = 'P621G1' then 'SUMINISTROS P Y Z   PROD TERM'"; 
     			   query +=	" when A.codalm_d = 'P622G1' then 'ALMACENES TCN PRODUTCO TERM'";
 				   query +=	" when A.codalm_d = 'QGOB01' then 'GOBIERNO' end as Almacen_D,";
 				   query +=	" A.anocon, ";
 				   query +=	" A.mescon,";
 				   query +=	" A.semcon, ";
 				   query +=	" A.dia,";
 				   query +=	" A.monto,"; 
 				   query +=	" A.unidad,";
 				   query +=	" A.pesop ";
 			       query +=	" FROM entrada_despresados A, BAANDB.ttcmcs003100@BAAN_ORACLE B, (Select A.t$cwar AS CODIGO_O ";
 			    		                                                           query +=	" from BAANDB.ttcmcs003100@BAAN_ORACLE A";
 			    		                                                           query +=	" where t$cwar IN ('P602G1', 'P604G1', 'P606G1', 'P608G1', 'P609G1', 'P617G1',";
 			    		                                                           query +=	" 'P614G1', 'P621G1', 'P622G1', 'P626G1', 'P610G1', 'P625G1', ";
 			    		                                                           query +=	" 'D651G1', 'D655G1', 'E601G1', 'QGOB01')";
 			    		                                                           query +=	"  GROUP BY A.T$CWAR) sq1";
     	     	   query +=	" WHERE B.t$cwar = A.codalm_o ";
 			       query +=	" AND sq1.codigo_o = A.codalm_o ";
 			       query +=	" AND A.CODALM_O like '" + vecalmo[0].toUpperCase() + "%'";
 			       query +=	" AND A.CODALM_D like '" + vecalmd[0].toUpperCase() + "%'";
		           query +=	" AND A.anocon like '" + anocondes + "%'";
		           query +=	" AND A.mescon like '" + mescondes + "%'";
 			       query +=	" AND A.semcon like '" + semcondes + "%'";
 			       query +=	" AND B.T$DSCA || A.semcon || A.mescon || A.anocon like '%" + ((String) filterValue).toUpperCase() + "%'";
 			       query +=	" ORDER BY 1,2,3 ) pivot ( sum(monto) for dia in ('LUNES' LUNES,'MARTES' MARTES,'MIERCOLES' MIERCOLES,'JUEVES' JUEVES,'VIERNES' VIERNES,'SABADO' SABADO,'DOMINGO' DOMINGO)";
 			       query +=	" ) ";
		           query += " ORDER by " + sortField.replace("z","") +")query" ;
		           query += " )WHERE rownum <="+pageSize;
		           query += " AND rn > ("+ first +")";

		           pstmt = con.prepareStatement(query);
		           //System.out.println(query);
		            r =  pstmt.executeQuery();
           
           
           while (r.next()){
           	Despresados select = new Despresados();
           	select.setZcodigo_o(r.getString(1));
           	select.setZcodalm_o(r.getString(2));
           	select.setZcodigo_d(r.getString(3));
           	select.setZcodalm_d(r.getString(4));
        	select.setZanocondes(r.getString(5));
        	select.setZmescondes(r.getString(6)); 
        	select.setZsemcondes(r.getString(7));
        	select.setZunidaddes(r.getString(8));
        	select.setZpesopdes(r.getString(9));
        	select.setZdia1(r.getString(10));
        	select.setZdia2(r.getString(11));
        	select.setZdia3(r.getString(12));
        	select.setZdia4(r.getString(13));
        	select.setZdia5(r.getString(14));
        	select.setZdia6(r.getString(15));
        	select.setZdia7(r.getString(16));
	    	select.setZalmadesc_o(r.getString(1) + " - " + r.getString(2));
	    	select.setZalmadesc_d(r.getString(3) + " - " + r.getString(4));
  	
           
           	//Agrega la lista
           	list.add(select);
           }
      	   } catch (SQLException e){
            e.printStackTrace();    
        }
           //Cierra las conecciones
           pstmt.close();
           con.close();
           r.close();
    	}
    
    public void counter(Object filterValue ) throws SQLException, NamingException {
        try {	
       	Context initContext = new InitialContext();     
      		DataSource ds = (DataSource) initContext.lookup(JNDI);
      		con = ds.getConnection();
      		
      		if(codalm_o==null){
        		codalm_o = " - ";
        	}
        	if(codalm_d==null){
        		codalm_d = " - ";
        	}
        	if(anocondes == null){
            	anocondes = "";
            }
            if(mescondes == null){
            	mescondes = "";
            }
            if(semcondes == null){
            	semcondes = "";
            }
        	String[] vecalmo = codalm_o.split("\\ - ", -1);
         	String[] vecalmd = codalm_d.split("\\ - ", -1);

     		//Consulta paginada
     		String query = "SELECT count_entrada_despresados('" + ((String)filterValue).toUpperCase() + "','" + vecalmo[0] + "','" + vecalmd[0] + "','"  + semcondes + "','" + mescondes + "','" + anocondes + "')from dual ";

           
           pstmt = con.prepareStatement(query);
           //System.out.println(query);

            r =  pstmt.executeQuery();
           
           
           while (r.next()){
           	rows = r.getInt(1);
           }
        } catch (SQLException e){
            e.printStackTrace();    
        }
           //Cierra las conecciones
           pstmt.close();
           con.close();
           r.close();

     	}
      
	/**
     * Limpia los valores
     **/
	public void limpiarValores(){
		diades = "";
		montodes = "";
		unidaddes = "UND";
		codalm_o = "";
		codalm_d = "";
        pesopdes = "";
        semcondes = "";
    	validarOperacion = 0;
        
	}
	
	/* Se desactiva porque se hace el login Andrés Dominguez 26/02/2015
	 private Boolean fun_validar(String pcoduser, String pclave) throws SQLException, NamingException {
         String valor = "0";
         if(pcoduser.equals("ADMIN") || pcoduser.equals("CONSULTOR") || pcoduser.equals("PCARRIZALE") || pcoduser.equals("HGONZALEZ")){
        	try {	
            	Context initContext = new InitialContext();     
          		DataSource ds = (DataSource) initContext.lookup(JNDI);

          		con = ds.getConnection();

         		//Consulta paginada
         		//String query = "SELECT DISTINCT FUN_VALIDAR ('" +pcoduser+ "','" +pclave+ "') FROM USUARIOS_GYP_MERMA";
          		String query = "SELECT FUN_VALIDAR('" + pcoduser.toUpperCase() + "', '" + pclave.toUpperCase() + "') from dual";   
                pstmt = con.prepareStatement(query);
                //System.out.println(query);

                r =  pstmt.executeQuery();
               
               
               while (r.next()){
               	valor = r.getString(1);
               }
            } catch (SQLException e){
                e.printStackTrace();    
            }
               //Cierra las conecciones
               pstmt.close();
               con.close();
               r.close();
         }//Valida lista de usuarios
               //System.out.println(valor);
               if(valor.equals("0")){
            	   return true;
               } else {
            	   return false;
               }
         	}*/
	 
	 
	 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 ///////////////////////////////////INICIO DE LOS METODOS AUTOCOMPLETES//////////////////////////////////////////////
	 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
	 /**
		 * Lista de alamcenes con descripcion.
		 * @throws NamingException 
		 * @return List String
		 * @throws IOException 
		 **/
		 public List<String> completeALMACENES(String query) throws NamingException, IOException { 
					        
		  List<String> results = new ArrayList<String>(); 
		  String vlquery = "Select A.t$cwar||' - '||A.t$dsca Almacen " +
				      " from BAANDB.ttcmcs003100@BAAN_ORACLE A" +
				      " where t$cwar IN ('P602G1', 'P604G1', 'P606G1', 'P608G1', 'P609G1', 'P617G1', 'P614G1', 'P621G1', 'P622G1', 'P626G1', 'P610G1', 'P625G1', 'D651G1', 'D655G1', 'E601G1', 'QGOB01') " +
					  " and t$cwar||t$dsca like '%" + query.toUpperCase() + "%'" +
		              " order by A.t$cwar";
		 consulta.selectPntGenerica(vlquery, JNDI);
		 //System.out.println(vlquery);
		 int vlrows = consulta.getRows();
		 String[][] vltabla = consulta.getArray();
		   for (int i = 0; i < vlrows; i++) {  
			   results.add(vltabla[i][0]);  
		    }  
		    return results;  
		} 
		 
	   /**
		 * Lista tipos de presupuestos.
		 * @throws NamingException 
		 * @return List String
		 * @throws IOException 
		 **/
		 public List<String> completeTIPRES(String query) throws NamingException, IOException { 
					        
		  List<String> results = new ArrayList<String>(); 
		  String vlquery = "Select CODPRES||' - '||TIPO " +
				      " from TIPOS_PRESUPUESTO " +
				      " order by codPRES";
		 consulta.selectPntGenerica(vlquery, JNDI);
		 //System.out.println(vlquery);
		 int vlrows = consulta.getRows();
		 String[][] vltabla = consulta.getArray();
		   for (int i = 0; i < vlrows; i++) {  
			   results.add(vltabla[i][0]);  
		    }  
		    return results;  
		}  
		 
		 /**
			 * Lista de escalas de medidas.
			 * @throws NamingException 
			 * @return List String
			 * @throws IOException 
			 **/
			 public List<String> completeESCALA(String query) throws NamingException, IOException { 
						        
			  List<String> results = new ArrayList<String>(); 
			  String vlquery = "Select descripcion " +
					      " from escalas_medidas " +
					      " order by descripcion";
			 consulta.selectPntGenerica(vlquery, JNDI);
			 //System.out.println(vlquery);
			 int vlrows = consulta.getRows();
			 String[][] vltabla = consulta.getArray();
			   for (int i = 0; i < vlrows; i++) {  
				   results.add(vltabla[i][0]);  
			    }  
			    return results;  
			}  
		 
	  
         			/**
					 * Lista categoria3.
					 * @throws NamingException 
					 * @return List String
					 * @throws IOException 
					 **/
					 public List<String> completeCat3(String query) throws NamingException, IOException { 
								        
					  List<String> results = new ArrayList<String>(); 
					  String vlquery = "Select codcat3||' - '||descat3 " +
							      " from CATEGORIAS_PRESUPUESTOS " +
							      " GROUP BY CODCAT3, DESCAT3"
							    + " order by codcat3";
					 consulta.selectPntGenerica(vlquery, JNDI);
					 //System.out.println(vlquery);
					 int vlrows = consulta.getRows();
					 String[][] vltabla = consulta.getArray();
					   for (int i = 0; i < vlrows; i++) {  
						   results.add(vltabla[i][0]);  
					    }  
					    return results;  
					}  
					 
					 /**
						 * Lista DIAS.
						 * @throws NamingException 
						 * @return List String
						 * @throws IOException 
						 **/
						 public List<String> completeDIAS(String query) throws NamingException, IOException { 
									        
						  List<String> results = new ArrayList<String>(); 
						  String vlquery = "Select dia " +
								      " from DIAS " +
								      " order by coddia";
						 consulta.selectPntGenerica(vlquery, JNDI);
						 //System.out.println(vlquery);
						 int vlrows = consulta.getRows();
						 String[][] vltabla = consulta.getArray();
						   for (int i = 0; i < vlrows; i++) {  
							   results.add(vltabla[i][0]);  
						    }  
						    return results;  
						}  
						 
						 /**
				          * Lista ANOCON.
						  * @throws NamingException 
						  * @return List String
						  * @throws IOException 
						  **/
						public List<String> completeANOCON(String query) throws NamingException, IOException { 
									        
						  List<String> results = new ArrayList<String>(); 
						  String vlquery = "Select anocon ";
								 vlquery += " from T_RGIP001 ";
								 vlquery += " WHERE ANOCON >= TO_NUMBER(TO_CHAR(SYSDATE, 'YYYY') - 1) AND ANOCON <= TO_NUMBER(TO_CHAR(SYSDATE, 'YYYY'))";
						         vlquery += " GROUP BY ANOCON";
						         vlquery +=	" ORDER BY ANOCON";
						 consulta.selectPntGenerica(vlquery, JNDI);
						 //System.out.println(vlquery);
						 int vlrows = consulta.getRows();
						 String[][] vltabla = consulta.getArray();
						   for (int i = 0; i < vlrows; i++) {  
							   results.add(vltabla[i][0]);  
						    }  
						    return results;  
						}  
						
						 /**
						 * Lista SEMCON.
						 * @throws NamingException 
						 * @return List String
						 * @throws IOException 
						 **/
						 public List<String> completeSEMCON(String query) throws NamingException, IOException { 
							 if(mescondes == null){
					            	mescondes = "";
					            }
					            if(semcondes == null){
					            	semcondes = "";
					            }
						  List<String> results = new ArrayList<String>(); 
						  String vlquery = "Select SEMCON " +
								      " from T_RGIP001" +
								      " where semcon like '%" + query + "%' and mescon= '" + mescondes + "'" +
								      " GROUP by semcon" +
								      " order by semcon";
						 consulta.selectPntGenerica(vlquery, JNDI);
						 //System.out.println(vlquery);
						 int vlrows = consulta.getRows();
						 String[][] vltabla = consulta.getArray();
						   for (int i = 0; i < vlrows; i++) {  
							   results.add(vltabla[i][0]);  
						    }  
						    return results;  
						}  
						 		
						 
						 /**
						 * Lista MESCON.
						 * @throws NamingException 
						 * @return List String
						 * @throws IOException 
						 **/
						 public List<String> completeMESCON(String query) throws NamingException, IOException { 
									        
						  List<String> results = new ArrayList<String>(); 
						  String vlquery = "Select MESCON " +
								      " from T_RGIP001" +
								      " GROUP by MEScon" +
								      " order by MEScon";
						 consulta.selectPntGenerica(vlquery, JNDI);
						 //System.out.println(vlquery);
						 int vlrows = consulta.getRows();
						 String[][] vltabla = consulta.getArray();
						   for (int i = 0; i < vlrows; i++) {  
							   results.add(vltabla[i][0]);  
						    }  
						    return results;  
						}  
						 

						/**
						 * Lista ALAMCENES.
						 * @throws NamingException 
						 * @return List String
						 * @throws IOException 
						 **/
						 public List<String> completeCODALM(String query) throws NamingException, IOException { 
							 String cat1 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cat1"); //Usuario logeado
							 String cat2 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cat2"); //Usuario logeado
							 String cat3 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cat3"); //Usuario logeado
								
							        if(cat1==null){
							        	cat1 = " - ";
							        }
							        if(cat1==""){
							        	cat1 = " - ";
							        }
							        if(cat2==null){
							        	cat2 = " - ";
							        }
							        if(cat2==""){
							        	cat2 = " - ";
							        }    
							        if(cat3==null){
							        	cat3 = " - ";
							        }
							        if(cat3==""){
							        	cat3 = " - ";
							        }    
							        String[] veccat1 = cat1.split("\\ - ", -1);
							        String[] veccat2 = cat2.split("\\ - ", -1);
							        String[] veccat3 = cat3.split("\\ - ", -1);
							        
						  List<String> results = new ArrayList<String>(); 
						  String vlquery = "Select CODALM " +
								      " from CATEGORIAS_PRESUPUESTOS " +
								      " where CODALM like '%" + query.toUpperCase() + "%' and "
								    + "  codcat1 = '" + veccat1[0] + "' and codcat2 = '" + veccat2[0] + "' and codcat3 = '" + veccat3[0] + "'"
						     	    + " GROUP BY CODALM"
								    + " order by CODALM";
						 consulta.selectPntGenerica(vlquery, JNDI);
						 //System.out.println(vlquery);
						 int vlrows = consulta.getRows();
						 String[][] vltabla = consulta.getArray();
						   for (int i = 0; i < vlrows; i++) {  
							   results.add(vltabla[i][0]);  
						    }  
						    return results;  
						}  
						 
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						 //////////////////////////////////////FIN DE LOS METODOS AUTOCOMPLETES//////////////////////////////////////////////
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					 	
						 
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						 /////////////////////////////////INICIO DE LOS SETTERS PARA VARIABLES DE SESION/////////////////////////////////////
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

						 
						 /**
							 * Setea codlam_o lista
							 * @param next
							 */
							public void setCodalmd(String codalm_d){
							   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codalm_d", codalm_d);
							}
							
						 /**
							 * Setea codlam_d lista
							 * @param next
							 */
							public void setCodalmo(String codalm_o){
							   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codalm_o", codalm_o);
							}
							
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						 ////////////////////////////////////FIN DE LOS SETTERS PARA VARIABLES DE SESION/////////////////////////////////////
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

					       /**
						     * Limpia los valores
						     **/
							public void reset(){
						  		codalm_o = null;
						  		codalm_d = null;
						  		anocondes = null;
						  		mescondes = null;
						  		semcondes = null;
						  		
							}
}
