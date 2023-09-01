

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.DBConnection;

/**
 * Servlet implementation class DBOperations
 */
public class DBOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBOperations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			
		      InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
		      Properties props = new Properties();
		      props.load(in);
		      
		      
		      DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("userid"), props.getProperty("password"));
		      Statement stmt = conn.getConnection().createStatement();
		      stmt.executeUpdate("create database mydatabase"); 
		      out.println("Created database: mydatabase<br>");
		      stmt.executeUpdate("use mydatabase");
		      out.println("Selected database: mydatabase<br>");
		      stmt.executeUpdate("drop database mydatabase"); 	//Checked it with commenting/uncommenting, Database is
		      													// being created
		      out.println("Dropped database: mydatabase<br>");
		      stmt.close();
		      
//		      conn.closeConnection(); // Duplicate Entry
		      
		      out.println("</body></html>");
		      conn.closeConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
