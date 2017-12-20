package persistencia;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManejadorBD {
	
	private Connection conexion = null;

    public boolean conexion() {
    	boolean controlConexion= false;
    	try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conexion = DriverManager.getConnection("jdbc:mysql://localhost/spotif", "root", "");
            controlConexion = true;
          
        }catch(SQLException s){
        	controlConexion = false;

        }catch(Exception s){
        	controlConexion = false;
        }
  
    	return controlConexion;
    }
    
    public ResultSet leer(String SQLquery) throws SQLException{
    	
    	Statement comando = null;    
    	ResultSet resultados = null;
   
            try {
            	comando = conexion.createStatement();            
                resultados = comando.executeQuery(SQLquery);
                
            } catch (SQLException e) {            
                e.printStackTrace();            
                throw e;
            }
            
            return resultados;
    } 
}
