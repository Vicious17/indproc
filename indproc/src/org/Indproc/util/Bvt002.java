/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Indproc.util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import javax.faces.application.FacesMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



/**
 *
 * @author Andres
 */

	public class Bvt002 extends Utils implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		
		
	
	
	private String coduser = "";
	private String cluser = "";
	private String[][] arr;
	private int columns;

		/**
	 * @return the coduser
	 */
	public String getCoduser() {
		return coduser;
	}
	
	/**
	 * @param coduser the coduser to set
	 */
	public void setCoduser(String coduser) {
		this.coduser = coduser;
	}
	
	
	
	/**
	 * @return the cluser
	 */
	public String getCluser() {
		return cluser;
	}
	
	/**
	 * @param cluser the cluser to set
	 */
	public void setCluser(String cluser) {
		this.cluser = cluser;
	}
	
	
	

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	


	/**
	 * @return the arr
	 */
	public String[][] getArr() {
		return arr;
	}

	/**
	 * @param arr the arr to set
	 */
	public void setArr(String[][] arr) {
		this.arr = arr;
	}




	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Variables seran utilizadas para capturar mensajes de errores de Oracle y parametros de metodos
	FacesMessage msj = null;
	private int rows; //Registros de tabla

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Coneccion a base de datos
	//Pool de conecciones JNDI
	Connection con;
	PreparedStatement pstmt = null;
	ResultSet r;


     
     /**
      * Leer Datos de Usuarios
      *<p> Parametros del Metodo: String coduser, String desuser.
      * String pool
     * @throws IOException 
      **/
     public void selectLogin(String user, String pool) throws NamingException {

         //Pool de conecciones JNDI. Cambio de metodologia de conexion a Bd. Julio 2010
         Context initContext = new InitialContext();
         DataSource ds = (DataSource) initContext.lookup(JNDI);
         try {
             Statement stmt;
             ResultSet rs;
             Connection con = ds.getConnection();

             //Class.forName(getDriver());
             //con = DriverManager.getConnection(
             //        getUrl(), getUsuario(), getClave());
             stmt = con.createStatement(
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             String query = "";
             
             //System.out.println( productName );

            	 query = "SELECT trim(coduser), trim(cluser)";
                 query += " FROM BVT002";
                 query += " where coduser = '" + user.toUpperCase() + "'";

       		
             
            // System.out.println(query);
             try {
                 rs = stmt.executeQuery(query);
                 rows = 1;
                 rs.last();
                 rows = rs.getRow();
                 //System.out.println(rows);

                 ResultSetMetaData rsmd = rs.getMetaData();
                 columns = rsmd.getColumnCount();
                 //System.out.println(columns);
                 arr = new String[rows][columns];

                 int i = 0;
                 rs.beforeFirst();
                 while (rs.next()) {
                     for (int j = 0; j < columns; j++) {
                         arr[i][j] = rs.getString(j + 1);
                     }
                     i++;
                 }
             } catch (SQLException e) {
             }
             stmt.close();
             con.close();

         } catch (Exception e) {
             e.printStackTrace();
         }
     }

     /**
      * @return Retorna el arreglo
      **/
     public String[][] getArray(){
     	return arr;
     }

     /**
      * @return Retorna número de filas
      **/
     public int getRows(){
     	return rows;
     }
     /**
      * @return Retorna número de columnas
      **/
     public int getColumns(){
     	return columns;
     }

  


}
