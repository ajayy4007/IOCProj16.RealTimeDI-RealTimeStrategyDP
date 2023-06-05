package com.nt.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.nt.bo.CustomerBO;
public class CustomerDAOImpl implements ICustomerDAO {
	private final static String REALTIMEDI_CUSTOMER_INSERT_QUERY="INSERT INTO REALTIMEDI_CUSTOMER VALUES(CUSTID_SEQ.NEXTVAL,?,?,?,?,?,?)";
	private DataSource ds;
		public CustomerDAOImpl(DataSource ds) {
	System.out.println("CustomerDAOImpl.1 param-constructor");
		this.ds = ds;
	}
		@Override
		public int insert(CustomerBO bo) throws Exception {
			Connection con=null;
			PreparedStatement ps=null;
			int count=0;
			try {
				//get pooled jdbc connection
				con=ds.getConnection();
				//create prepared statement object having pre comiled sql query
				ps=con.prepareStatement(REALTIMEDI_CUSTOMER_INSERT_QUERY);
				//Set query param value
				ps.setString(1,bo.getCustName());
				ps.setString (2,bo.getCustAddrs());
				ps.setFloat (3,bo.getPamt());
				ps.setFloat (4,bo.getRate());
				ps.setFloat (5,bo.getTime());
				ps.setFloat(6, bo.getIntrestAmount());
				//execute query
				count =ps.executeUpdate();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
				throw se;//Execption rethrowing
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw e;//Exception re throwing
		}
		finally
		{
			//close jdbc obj
			try {
				if(ps!=null)
				ps.close();
				}
			catch(SQLException se)
			{
				se.printStackTrace();
				throw se;
			}
			try {
				if(con!=null)
				con.close();
				}
			catch(SQLException se)
			{
				se.printStackTrace();
				throw se;
			}
			}//finally block
			return count;
		}
}
