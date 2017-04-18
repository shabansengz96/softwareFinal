package project;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import java.sql.Connection; 
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement; 
import java.text.Format;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.*;

public class jdbcconnection extends JFrame implements ActionListener
{
	public String url = "jdbc:oracle:thin:@db.ciu.edu.tr:1521:db";
    public String username = "c##20131563";
    public String password = "p20131563";
    
    //changed part 
    public String url = "jdbc:oracle:thin:@db.ciu.edu.tr:1521:db";
    public String username = "wezoeshaban";
    public String password = "dlaminisengendo";
    //changed part
    public jdbcconnection()
    {
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();
        String sql ="select * from transactions";
        try (
        		
        		
        		Connection con = DriverManager.getConnection( url, username, password );
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            //  Get row data
            while (rs.next())
            {
                ArrayList row = new ArrayList(columns);
               
                for (int i = 1; i <= columns; i++)
                {
                row.add( rs.getObject(i) );
                    }
               data.add( row );
            }
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }

    
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));
   
        JTable table  = new JTable(dataVector, columnNamesVector)
        {
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };

        JScrollPane scrollPane = new JScrollPane( table );
        getContentPane().add( scrollPane );

      
        
    }

    public static void main(String[] args)
    {
    	jdbcconnection frm1= new jdbcconnection();
    	
        jdbcconnection frame = new jdbcconnection();
        frame.setTitle("TRANSACTIONS DATABASE LIST");
        
        frame.setBackground(Color.red);
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible(true);
    }

    
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
