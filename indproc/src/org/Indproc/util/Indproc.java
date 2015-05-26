package org.Indproc.util;

import java.io.IOException;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
public class Indproc extends Utils implements Serializable {
	
	private LazyDataModel<Indproc> lazyModel;  
	
	/**
	 * @return the lazyModel
	 */
	public LazyDataModel<Indproc> getLazyModel() {
		return lazyModel;
	}
	
	@PostConstruct
	public void init(){

		lazyModel  = new LazyDataModel<Indproc>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 7217573531435419432L;
			
            @Override
			public List<Indproc> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
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
	private String cat1 = "";
	private String cat2 = "";
	private String cat3 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cat3"); 
	private String dia = "";
	private String monto = "";
	private String unidad = "UND";
	private String semcon = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("semcon"); 
	private String mescon = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mescon"); 
    private String anocon = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("anocon"); 
	private String tipo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tipo"); 
	private String codalm = "";
	private String descat3 = "";
    private String pesop = "";
	private String pesopdes = "";
	private String diades = "";
	private String montodes = "";
	private String unidaddes = "UND";
	private String semcondes = "";
	private String mescondes = "";
	private String anocondes = "";
	private String codalm_o = "";
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
	private String zpesop = "";
	private String zuno = "";
	private String zdos = "";
	private String ztres = "";
	private String zcodalm = "";
	private String Ztipo= "";
	private String zcat1 = "";
	private String zcat2 = "";
	private String zcat3 = "";
	private String zdia = "";
	private String zmonto = "";
	private String zunidad = "";
	private String zsemcon = "";
	private String zmescon = "";
	private String zanocon = "";
	private String zdescat3 = "";
	private String ztiponum = "";
	private String ztip = "";
	private String zpesopdes = "";
	private String zdia1 = "";
	private String zdia2 = "";
	private String zdia3 = "";
	private String zdia4 = "";
	private String zdia5 = "";
	private String zdia6 = "";
	private String zdia7 = "";
	private String zcodcat3 = "";



	//GETTER'S Y SETTER'S//
    private int validarOperacion = 0;//Param guardar para validar si guarda o actualiza
    private Object filterValue = "";
    private List<Indproc> list = new ArrayList<Indproc>();//LIstar paises
    private List<Indproc> list2 = new ArrayList<Indproc>();//LIstar paises
    FacesMessage msj = null; 
    private int rows; //Registros de tabla
    PntGenerica consulta = new PntGenerica();
     
    //Formateador de la fecha sdfecha
    java.text.SimpleDateFormat sdfecha = new java.text.SimpleDateFormat("dd/MM/yyyy", locale);
    String fecha = sdfecha.format(fecact); //Fecha formateada para insertar en tablas

	public String getAnocon() {
		return anocon;
	}

	public void setAnocon(String anocon) {
		this.anocon = anocon;
	}

	public String getPesop() {
		return pesop;
	}

	public void setPesop(String pesop) {
		this.pesop = pesop;
	}

	public String getZcodcat3() {
		return zcodcat3;
	}

	public void setZcodcat3(String zcodcat3) {
		this.zcodcat3 = zcodcat3;
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

	public List<Indproc> getList2() {
		return list2;
	}

	public void setList2(List<Indproc> list2) {
		this.list2 = list2;
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

	public String getZtip() {
		return ztip;
	}

	public void setZtip(String ztip) {
		this.ztip = ztip;
	}

	public String getZpesop() {
		return zpesop;
	}

	public void setZpesop(String zpesop) {
		this.zpesop = zpesop;
	}

	public String getDescat3() {
		return descat3;
	}

	public void setDescat3(String descat3) {
		this.descat3 = descat3;
	}

	public String getZtiponum() {
		return ztiponum;
	}

	public void setZtiponum(String ztiponum) {
		this.ztiponum = ztiponum;
	}

	public String getZdescat3() {
		return zdescat3;
	}

	public void setZdescat3(String zdescat3) {
		this.zdescat3 = zdescat3;
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

	public String getCodalm() {
		return codalm;
	}


	public void setCodalm(String codalm) {
		this.codalm = codalm;
	}


	public String getZcodalm() {
		return zcodalm;
	}


	public void setZcodalm(String zcodalm) {
		this.zcodalm = zcodalm;
	}

    public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getZtipo() {
		return Ztipo;
	}


	public void setZtipo(String ztipo) {
		Ztipo = ztipo;
	}

	public String getCat1() {
		return cat1;
	}


	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}


	public String getCat2() {
		return cat2;
	}


	public void setCat2(String cat2) {
		this.cat2 = cat2;
	}


	public String getCat3() {
		return cat3;
	}


	public void setCat3(String cat3) {
		this.cat3 = cat3;
	}


	public String getZcat1() {
		return zcat1;
	}


	public void setZcat1(String zcat1) {
		this.zcat1 = zcat1;
	}


	public String getZcat2() {
		return zcat2;
	}


	public void setZcat2(String zcat2) {
		this.zcat2 = zcat2;
	}


	public String getZcat3() {
		return zcat3;
	}


	public void setZcat3(String zcat3) {
		this.zcat3 = zcat3;
	}

	public String getDia() {
		return dia;
	}


	public void setDia(String dia) {
		this.dia = dia;
	}


	public String getMonto() {
		return monto;
	}


	public void setMonto(String monto) {
		this.monto = monto;
	}


	public String getUnidad() {
		return unidad;
	}


	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}


	public String getZdia() {
		return zdia;
	}


	public void setZdia(String zdia) {
		this.zdia = zdia;
	}


	public String getZmonto() {
		return zmonto;
	}


	public void setZmonto(String zmonto) {
		this.zmonto = zmonto;
	}


	public String getZunidad() {
		return zunidad;
	}


	public void setZunidad(String zunidad) {
		this.zunidad = zunidad;
	}


	public String getMescon() {
		return mescon;
	}


	public void setMescon(String mescon) {
		this.mescon = mescon;
	}


	public String getZmescon() {
		return zmescon;
	}


	public void setZmescon(String zmescon) {
		this.zmescon = zmescon;
	}

	public String getSemcon() {
		return semcon;
	}


	public void setSemcon(String semcon) {
		this.semcon = semcon;
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

	public String getZsemcon() {
		return zsemcon;
	}


	public void setZsemcon(String zsemcon) {
		this.zsemcon = zsemcon;
	}


	public String getZanocon() {
		return zanocon;
	}


	public void setZanocon(String zanocon) {
		this.zanocon = zanocon;
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////!!!GETTER'S FIJOS DE PROCESOS!!!////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////

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
	public List<Indproc> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<Indproc> list) {
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
	
	private void insert() throws NamingException, SQLException {
      try {
    	String[] vecod1 = zuno.split("\\ - ", -1);
  		String[] vecod2 = zdos.split("\\ - ", -1);
  		String[] vecod3 = cat3.split("\\ - ", -1);
  		String[] vectip = tipo.split("\\ - ", -1);
  		String condicion = vectip[0];
  		String diastemp = dialun + '|' + diamar + '|' + diamie + '|' + diajue + '|' + diavie + '|' + diasab + '|' + diadom;
  		String[] montos = diastemp.split("\\|");
  		String diasnombtemp = "LUNES" + '|' + "MARTES" + '|' + "MIERCOLES" + '|' + "JUEVES" + '|' + "VIERNES" + '|' + "SABADO" + '|' + "DOMINGO";
  		String[] diasnomb = diasnombtemp.split("\\|");
  		Context initContext = new InitialContext();     
   		DataSource ds = (DataSource) initContext.lookup(JNDI);
          con = ds.getConnection();
          
          //System.out.println("ENTRE:" + diastemp);
          //System.out.println("ENTRE:" + vectip[0]);
          if (condicion.equals("1")){
        	  try {
        	  for (int i = 0; i <= montos.length - 1; i++){
        		 //System.out.println("Tama;o del arreglo:" + montos.length);
        		 //System.out.println("ENTRE:" + diasnomb[i]);	
                 //System.out.println("ENTRE:" + montos[i]);	    	        		  
           	  String query = "INSERT INTO INGRESO_SEMANAL_PRESUPUESTADO VALUES (?,?,'NA','NA',?,?,?,?,?,?,?,?,'','')";
              pstmt = con.prepareStatement(query);
              pstmt.setString(1, vecod1[0].toUpperCase());
              pstmt.setString(2, vecod2[0].toUpperCase());
              pstmt.setInt(3, Integer.parseInt(vectip[0]));
              pstmt.setInt(4, Integer.parseInt(anocon));
              pstmt.setInt(5, Integer.parseInt(mescon));
              pstmt.setInt(6, Integer.parseInt(semcon));
              pstmt.setString(7, diasnomb[i].toUpperCase());
              pstmt.setString(8, unidad.toUpperCase());
              pstmt.setInt(9, Integer.parseInt(montos[i]));
              pstmt.setInt(10, Integer.parseInt(pesop));
              pstmt.executeUpdate();
        	  }// fin del ciclo FOR
        	  
  	          //System.out.println("ENTRE A LOS MSGS");
              msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro insertado", "");
              limpiarValores();        	  
              } catch (SQLException e)  {
                  // e.printStackTrace();
                   msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
              }
          } //fin de la condicion IF
                      else {
          try {	  
               for (int i = 0; i <= montos.length - 1; i++){
               //System.out.println("Tama;o del arreglo:" + montos.length);
               //System.out.println("ENTRE:" + diasnomb[i]);
               //System.out.println("ENTRE:" + montos[i]);	
                  			                 		  
          String query = "INSERT INTO INGRESO_SEMANAL_PRESUPUESTADO VALUES (?,?,?,?,?,?,?,?,?,?,?,?,'','')";
          pstmt = con.prepareStatement(query);
          pstmt.setString(1, vecod1[0].toUpperCase());
          pstmt.setString(2, vecod2[0].toUpperCase());
          pstmt.setString(3, vecod3[0].toUpperCase());
          pstmt.setString(4, ztres.toUpperCase());
          pstmt.setString(5, vectip[0].toUpperCase());
          pstmt.setInt(6, Integer.parseInt(anocon));
          pstmt.setInt(7, Integer.parseInt(mescon));
          pstmt.setInt(8, Integer.parseInt(semcon));
          pstmt.setString(9, diasnomb[i].toUpperCase());
          pstmt.setString(10, unidad.toUpperCase());
          pstmt.setInt(11, Integer.parseInt(montos[i]));
          pstmt.setInt(12, Integer.parseInt(pesop));
          pstmt.executeUpdate();
          }// fin del ciclo FOR
               
           //System.out.println("cat1:" + vecod1[0]);
           //System.out.println("cat2:" + vecod2[0]);
           //System.out.println("cat3:" + vecod3[0]);
           //System.out.println("codalm:" + ztres);
           //System.out.println("tipo:" + vectip[0]);
           //System.out.println("anocon:" + anocon);
           //System.out.println("mescon:" + mescon);
           //System.out.println("semcon:" + semcon);
           //System.out.println("dia:" + dia);
           //System.out.println("unidad:" + unidad);
           //System.out.println("monto:" + monto);
           //System.out.println("perop:" + pesop);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro insertado", "");
                //limpiarValores();
            } catch (SQLException e)  {
                // e.printStackTrace();
                 msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
            }
            } // fin de la condicion ELSE
            pstmt.close();
            con.close();           
        } catch (Exception e) {
            //e.printStackTrace();
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
	
	        	String query = "DELETE INGRESO_SEMANAL_PRESUPUESTADO WHERE cat3||codalm||anocon||mescon||semcon||tipo in (" + param + ")";
	        		        	
	            pstmt = con.prepareStatement(query);
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
     * Actualiza Dotarticulos
     * <b>Parametros del Metodo:<b> String cod_art, String descripcion, String tip_art, String talla.<br>
     * String pool, String login.<br><br>
	 * @throws SQLException 
     **/
    public void update() throws  NamingException, SQLException {
     	//System.out.println("Entre a update");
     	String[] vecod1 = zuno.split("\\ - ", -1);
      	String[] vecod2 = zdos.split("\\ - ", -1);
      	String[] vecod3 = cat3.split("\\ - ", -1);
      	String[] vectip = tipo.split("\\ - ", -1);
      	String diastemp = dialun + '|' + diamar + '|' + diamie + '|' + diajue + '|' + diavie + '|' + diasab + '|' + diadom;
  		String[] montos = diastemp.split("\\|");
  		String diasnombtemp = "LUNES" + '|' + "MARTES" + '|' + "MIERCOLES" + '|' + "JUEVES" + '|' + "VIERNES" + '|' + "SABADO" + '|' + "DOMINGO";
  		String[] diasnomb = diasnombtemp.split("\\|");

  		try {
    	Context initContext = new InitialContext();     
 		DataSource ds = (DataSource) initContext.lookup(JNDI);
 		con = ds.getConnection();		
 		
 		for (int i = 0; i <= montos.length - 1; i++){
 	   		 //System.out.println("Tama;o del arreglo:" + montos.length);
 	   		 //System.out.println("ENTRE AL DIA:" + diasnomb[i]);	
 	   	     //System.out.println("SU MONTO ES:" + montos[i]);
 	 			//System.out.println("cat1:" + vecod1[0]);
 	 	        //System.out.println("cat2:" + vecod2[0]);
 	 	        //System.out.println("cat3:" + vecod3[0]);
 	 	        //System.out.println("codalm:" + ztres);
 	 	        //System.out.println("tipo:" + vectip[0]);
 	 	        //System.out.println("anocon:" + anocon);
 	 	        //System.out.println("mescon:" + mescon);
 	 	        //System.out.println("semcon:" + semcon);
 	 	        //System.out.println("dia:" + diasnomb[i]);
 	 	        //System.out.println("unidad:" + unidad);
 	 	        //System.out.println("monto:" + montos[i]);
 	 	        //System.out.println("pesop:" + pesop);
 	 	        
        String query = "UPDATE ingreso_semanal_presupuestado";
         query += " SET unidad = '" + unidad.toUpperCase() + "'";
         query += " , monto = '" + Integer.parseInt(montos[i]) + "'";
         query += " , pesop = '" + Integer.parseInt(pesop) + "'";
         query += " WHERE cat1 = '" + vecod1[0].toUpperCase() + "'";
         query += " AND cat2 = '" + vecod2[0].toUpperCase() + "'";
         query += " AND cat3 = '" +  vecod3[0].toUpperCase() + "'";
         query += " AND codalm = '" +  ztres.toUpperCase() + "'";
         query += " AND tipo = '" + Integer.parseInt(vectip[0]) + "'";
         query += " AND anocon = '" + Integer.parseInt(anocon) + "'";
         query += " AND mescon = '" + Integer.parseInt(mescon) + "'";
         query += " AND semcon = '" + Integer.parseInt(semcon) + "'";
         query += " AND dia = '" + diasnomb[i].toUpperCase() + "'";
        //System.out.println(query);
        pstmt = con.prepareStatement(query);
        pstmt.executeUpdate();
        //System.out.println("cat1:" + vecod1[0]);
        //System.out.println("cat2:" + vecod2[0]);
        //System.out.println("cat3:" + vecod3[0]);
        //System.out.println("codalm:" + ztres);
        //System.out.println("tipo:" + vectip[0]);
        //System.out.println("anocon:" + anocon);
        //System.out.println("mescon:" + mescon);
        //System.out.println("semcon:" + semcon);
        //System.out.println("dia:" + diasnomb[i]);
        //System.out.println("unidad:" + unidad);
        //System.out.println("monto:" + montos[i]);
        //System.out.println("pesop:" + pesop);      
 		}
        try {
            //Avisando
            if(pstmt.getUpdateCount()==0){
            	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existen registros que coincidan para modificar", "");
            } else {
            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  "Registros modificados con éxito", "");
            }
            //limpiarValores();

        } catch (SQLException e) {
        	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), "");
        }
        pstmt.close();
        con.close();
    } catch (Exception e) {
    }
    FacesContext.getCurrentInstance().addMessage(null, msj);
  
    }	  	 		 		
     /**
     * Genera las operaciones de guardar o modificar
     * @throws NamingException 
     * @throws SQLException 
     * @throws IOException 
     **/ 
	 public void guardar() throws NamingException, SQLException{   	
	    	if(validarOperacion==0){
	    		insert();
	    	} else {
	    		//System.out.println("Entre a guardar");
	    		update();
	    	}
	    }
	       
    public void select(int first, int pageSize, String sortField, Object filterValue) throws SQLException, NamingException {
    	try {	
            if(cat3==null){
            	cat3 = " - ";
            }

            if(tipo == null){
            	tipo = " - ";
            }
            if(anocon == null){
            	anocon = "";
            }
            if(mescon == null){
            	mescon = "";
            }
            if(semcon == null){
            	semcon = "";
            }

      		String[] vecod3 = cat3.split("\\ - ", -1);
      		String[] vectip = tipo.split("\\ - ", -1);
    		//System.out.println("JNDI: " + JNDIFARM);
        	Context initContext = new InitialContext();     
    		DataSource ds = (DataSource) initContext.lookup(JNDI);
    		con = ds.getConnection();	
      		
      	//Consulta paginada
      	    String query =  "SELECT * FROM"; 
		      	   query +=	"(select query.*,rownum as rn from";
     	     	   query +=	" (select * from (select A.cat1, A.cat2, A.cat3, A.dia, A.monto, A.unidad, A.semcon, A.mescon, A.anocon, B.DESCAT1, B.DESCAT2, B.DESCAT3, E.tipo, A.tipo as tiponum, A.codalm, A.pesop";
		           query +=	" FROM INGRESO_SEMANAL_PRESUPUESTADO A, CATEGORIAS_PRESUPUESTOS B, tipos_presupuesto E";
		           query +=	" WHERE A.cat1 = B.codcat1";
		     	   query +=	" AND A.cat2 = B.codcat2";
		     	   query +=	" AND A.cat3 = B.codcat3";
		     	   query +=	" AND A.tipo = E.CODPRES";
		           query +=	" AND A.tipo like '%" + vectip[0] + "%'";
		           query +=	" AND A.cat3 like '%" + vecod3[0].toUpperCase() + "%'";
		           query +=	" AND A.semcon like '" + semcon + "%'";
		           query +=	" AND A.mescon like '" + mescon + "%'";
		           query +=	" AND A.anocon like '" + anocon + "%'";
		           query +=	" AND B.DESCAT3 || A.SEMCON || A.mescon || A.anocon like '%" + ((String)filterValue).toUpperCase() + "%'";
		           query += " ORDER by 1,2,3)";
		           query += " pivot (sum(monto) for dia in ('LUNES' LUNES,'MARTES' MARTES,'MIERCOLES' MIERCOLES,'JUEVES' JUEVES,'VIERNES' VIERNES,'SABADO' SABADO,'DOMINGO' DOMINGO))";
		           query += " ORDER by " + sortField.replace("z","") +")query" ;
		           query += " )WHERE rownum <="+pageSize;
		           query += " AND rn > ("+ first +")";

           
           pstmt = con.prepareStatement(query);
           //System.out.println(query);
           r =  pstmt.executeQuery();

            while (r.next()){
           	Indproc select = new Indproc();
	    	select.setZcat1(r.getString(1) + " - " + r.getString(8));
	    	select.setZcat2(r.getString(2) + " - " + r.getString(9));
	    	select.setZcat3(r.getString(3) + " - " + r.getString(10));
        	select.setZcodcat3(r.getString(3));
        	select.setZunidad(r.getString(4));
        	select.setZsemcon(r.getString(5));
        	select.setZmescon(r.getString(6));
        	select.setZanocon(r.getString(7));
        	select.setZdescat3(r.getString(10));
        	select.setZtipo(r.getString(11));
        	select.setZtiponum(r.getString(12));
        	select.setZcodalm(r.getString(13));
        	select.setZpesop(r.getString(14));
	    	select.setZtip(r.getString(12) + " - " + r.getString(11));
        	select.setZdia1(r.getString(15));
        	select.setZdia2(r.getString(16));
        	select.setZdia3(r.getString(17));
        	select.setZdia4(r.getString(18));
        	select.setZdia5(r.getString(19));
        	select.setZdia6(r.getString(20));
        	select.setZdia7(r.getString(21));        	
           
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
 
    public void selectCategorias() {
        if(cat3==null){
        	cat3 = " - ";
        }  
        String[] veccat3 = cat3.split("\\ - ", -1);

      	//Consulta paginada
    		  String vlquery = "Select A.codcat1||' - '||A.descat1, A.codcat2||' - '||A.descat2, A.codalm, B.PESOP " +
			          " FROM CATEGORIAS_PRESUPUESTOS A, INGRESO_SEMANAL_PRESUPUESTADO B" +
			          " WHERE  A.CODCAT3 = B.CAT3" +
			          " AND codcat3 = '" + veccat3[0] + "'" +
					  " GROUP BY A.CODCAT1, A.descat1, A.codcat2, A.descat2, A.codalm, B.PESOP" +
					  " ORDER BY A.codcat1";

    		 PntGenerica select = new PntGenerica();
             try {
				select.selectPntGenerica(vlquery,JNDI);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             int rows = select.getRows();
             String vltabla[][] = select.getArray();
             if(rows>0){
            	 zuno = vltabla[0][0];
            	 zdos = vltabla[0][1];
            	 ztres = vltabla[0][2];
            	 pesop = vltabla[0][3];
             }
             //System.out.println("zuno:" + zuno);
             //System.out.println("zdos:" + zdos);
             //System.out.println("ztres:" + ztres);
             //System.out.println("pesop:" + pesop);
     	}
           
    public void counter(Object filterValue) throws SQLException, NamingException {
        try {	
      		
       	Context initContext = new InitialContext();     
      		DataSource ds = (DataSource) initContext.lookup(JNDI);
      		con = ds.getConnection();
      		
      		if(cat3==null){
            	cat3 = " - ";
            }
            if(cat3==""){
            	cat3 = " - ";
            }  
      		if(tipo == null){
            	tipo = " - ";
            }
      		if(anocon == null){
            	anocon = "";
            }
            if(mescon == null){
            	mescon = "";
            }
            if(semcon == null){
            	semcon = "";
            }
      		String[] vectip = tipo.split("\\ - ", -1);
      		String[] vecod3 = cat3.split("\\ - ", -1);

     		//Consulta paginada
     		String query = "SELECT count_ingreso_semanal('" + ((String)filterValue).toUpperCase() + "','" + vectip[0] + "','" + vecod3[0] + "','" + semcon + "','" + mescon + "','" + anocon + "')from dual "; 

           
           pstmt = con.prepareStatement(query);
           //System.out.println(query);
            r =  pstmt.executeQuery();
           
           
           while (r.next()){
           	rows = r.getInt(1);
            //System.out.println(rows);
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
        dia = "";
        monto = "";
        unidad = "UND";
        pesop = "";
        pesopdes = "";
        semcon = "";
        semcondes = "";
    	validarOperacion = 0;        
	}
	
	/* Se desactiva ya que se cra el login Andrés Dominguez 25/02/2015
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
         	}
	 */
	 
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
				      " from BAANDB.ttcmcs003100@BAAN_ORACLE A, CATEGORIAS_PRESUPUESTOS B " +
				      " where a.t$cwar = B.codalm" +
					  " and A.t$cwar like '%" + query.toUpperCase() + "%'" +
					  " group by A.t$cwar, A.t$dsca" +
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
						 String cat3 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cat3"); //Usuario logeado
					        if(cat3==null){
					        	cat3 = "";
					        }
					        if(cat3==""){
					        	cat3 = "";
					        }            
					  List<String> results = new ArrayList<String>(); 
					  String vlquery = "Select codcat3||' - '||descat3 " +
							      " from CATEGORIAS_PRESUPUESTOS where tipo = 'AVES' " +
							      " GROUP BY CODCAT3, DESCAT3"
							    + " order by codcat3";
					 consulta.selectPntGenerica(vlquery, JNDI);
					 //System.out.println("VARIABLE DE SESION:" + cat3);
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
							 String mescon = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mescon"); //Usuario logeado
								
						        if(mescon==null){
						        	mescon = "";
						        }
						        if(mescon==""){
						        	mescon = "";
						        }		        
						  List<String> results = new ArrayList<String>(); 
						  String vlquery = "Select SEMCON " +
								      " from T_RGIP001" +
								      " where semcon like '%" + query + "%' and mescon= '" + mescon + "'" +
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
						   	 * Setea periodo seleccionada para copias
						   	 * @param next
						   	 */
						   	public void setMes(String mescon){
						       	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mescon", mescon);
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
						 
						 
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						 //////////////////////////////////////FIN DE LOS METODOS AUTOCOMPLETES//////////////////////////////////////////////
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					 	
						 public void act_ttdrpr962100() throws  NamingException, SQLException {
						        //Pool de conecciones JNDI. Cambio de metodología de conexión a bd. Julio 2010
						        Context initContext = new InitialContext();     
						        DataSource ds = (DataSource) initContext.lookup(JNDI);
						        con = ds.getConnection();
						        try {
						            Connection con = ds.getConnection();
						            CallableStatement proc = null;

						            proc = con.prepareCall("{CALL act_ttdrpr962100}");
						            proc.execute();
						            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro cargado en Baan", "");
						            //
						            proc.close();
						            con.close();
						            proc.close();

						        } catch (Exception e) {
						            e.printStackTrace();
						            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), "");
						        }
						        FacesContext.getCurrentInstance().addMessage(null, msj);
						    }
						 					 
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						 /////////////////////////////////INICIO DE LOS SETTERS PARA VARIABLES DE SESION/////////////////////////////////////
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

						 
						 /**
							 * Setea cat3 lista Usa la del utils
							 * @param next
							 
							public void setcategoria3(String cat3){
							   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cat3", cat3);
							   	try {
							        if(cat3==null){
							        	cat3 = " - ";
							        }
							        if(cat3==""){
							        	cat3 = " - ";
							        }    
									selectCategorias(cat3);
								} catch (SQLException | NamingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							   	}*/
							
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						 ////////////////////////////////////FIN DE LOS SETTERS PARA VARIABLES DE SESION/////////////////////////////////////
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

							
     					 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    				 ////////////////////////////////////INICIO DEL METODO DE ACTUALIZACION MASIVA DE PESOS//////////////////////////////
		    			 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

						 /**
						     * Actualiza ingreso_semanal_presupuestado
						     * <b>Parametros del Metodo:<b> String cod_art, String descripcion, String tip_art, String talla.<br>
						     * String pool, String login.<br><br>
							 * @throws SQLException 
						     **/
						    public void pesosupdate() throws  NamingException, SQLException {

						    	try {
						    	Context initContext = new InitialContext();     
						 		DataSource ds = (DataSource) initContext.lookup(JNDI);

						 		con = ds.getConnection();		
						 		
						 		//System.out.println("anocon:" + anocon);
						        //System.out.println("pesop:" + pesop);
						        
						        String query = "UPDATE ingreso_semanal_presupuestado";
						         query += " SET pesop = '" + Integer.parseInt(pesop) + "'";
						         query += " WHERE anocon = '" + Integer.parseInt(anocon) + "'";
						         
						       // System.out.println(query);
						        pstmt = con.prepareStatement(query);

						        pstmt.executeUpdate();
						      
						        //System.out.println("anocon:" + anocon);
						        //System.out.println("pesop:" + pesop);
						        
						        try {
						            //Avisando
						            if(pstmt.getUpdateCount()==0){
						            	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existen registros que coincidan para modificar", "");
						            } else {
						            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  "Registros modificados con éxito", "");
						            }
						            //limpiarValores();

						        } catch (SQLException e) {
						        	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), "");
						        }
						        pstmt.close();
						        con.close();
						    } catch (Exception e) {
						    }
						    FacesContext.getCurrentInstance().addMessage(null, msj);
						  }
							
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						 ////////////////////////////////////FIN DEL METODO DE ACTUALIZACION MASIVA DE PESOS/////////////////////////////////
						 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

							
					       /**
						     * Limpia los valores
						     **/
							public void reset(){
						        zuno = null;     
						  		zdos = null;  
						  		cat3 = null;  
						  		ztres = null;
						  		tipo = null;
						  		anocon = null;
						  		semcon = null;
						  		mescon = null;
							}

}
