/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import java.sql.*;
import Clases.ClaseLibros;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Hermilo
 */
public class Operaciones {
    
    static String bd = "Libreria";
    static String url ="jdbc:sqlserver://HERMILO-PC;"+"databaseName=Libreria;integratedSecurity=true;";
    static ClaseLibros libros;
    public   void AgregarLibro( ClaseLibros libros)
    {
       
            Connection conn=null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            conn = DriverManager.getConnection(url);
             if (conn != null) {
                Statement statement = conn.createStatement();
                String sentenciaSql = "INSERT INTO Libros(idlibro,Nombrelibro,Precio,Autor,Editorial)"
                        + "values" + "('" + libros.getIdLibro() + "'," + "'" + libros.getNombreLibro() + "', " + "'" +libros.getPrecio() + "', " + "'" + libros.getAutor() + "'," + "'" + libros.getEditorial()+ "')";
                statement.executeUpdate(sentenciaSql);
                statement.close();
                conn.close();
            }
            
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al conectar" + url);
        } catch (ClassNotFoundException ex) {
           System.out.println(ex);
        }
        
    }
    public static boolean  buscarLibro(int idLibro)
    {
        boolean existe = false;
        Connection conn = null;
        try {

            Class.forName("org.gjt.mm.sql.Driver");
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                Statement statement = conn.createStatement();
                String sentencia = "SELECT * FROM Libros WHERE idlibro ='" + idLibro + "'";
                ResultSet resulset = statement.executeQuery(sentencia);
                while (resulset.next()) {
                    libros=new ClaseLibros();
                    libros.setIdLibro(Integer.parseInt(resulset.getString(1).toString()));
                    libros.setNombreLibro(resulset.getString(2).toString());
                    libros.setPrecio(Integer.parseInt(resulset.getString(3).toString()));
                    libros.setAutor(resulset.getString(4).toString());
                    libros.setEditorial(resulset.getString(5).toString());
                    
                    existe = true;
                }
                resulset.close();
                statement.close();
                conn.close();

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al conectar" + url);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return existe;
    }
     public static  ClaseLibros obtenerLibro() {
        return libros;
    }

        
    
            
               
       
       
      
    
    
}
