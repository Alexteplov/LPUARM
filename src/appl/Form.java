package appl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.w3c.dom.Text;

import sql.JDBCAdapter;
import time.DatePickerCodeExample;







//import javax.swing.JComponent;

public class Form extends JPanel{
	public static DatePickerCodeExample calendar_t = new DatePickerCodeExample();
	//public static Object colren[]=null;
	public static JDBCAdapter adapter=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
	public static JTable Table=new JTable(adapter);
	public Form(){
        try{
            //System.out.println("I'm in Form!");
            JFrame frame = new JFrame("ARM DOCTOR от 06 июля 2015г.");
            //exit
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
            JTabbedPane jtp = new JTabbedPane();

            final JPanel panel1 = new JPanel();
            panel1.setLayout(null);
            jtp.add("Прием пациентов", panel1);


            JPanel panel2 = new JPanel();
            panel2.setLayout(null);
            jtp.add("Административная панель", panel2);
            
            JButton FPeople = new JButton("Поиск пациента в базе");
            FPeople.setBounds(10, 10, 220, 30);
            panel2.add(FPeople);
            
            JButton BPeople = new JButton("Приписанное население");
            BPeople.setBounds(10, 40, 220, 30);
            panel2.add(BPeople);
            
            
            
 //           final JPanel panel = new JPanel();
//	        panel.setLayout(null);
            
			//final DatePickerCodeExample calendar_t = new DatePickerCodeExample();
	        calendar_t.setBounds(5, 5, 130, 40);
	        panel1.add(calendar_t);
	        
			JButton updateButton = new JButton("Обновить график приема");
			updateButton.setBounds(170, 10, 250, 25);
			
	        panel1.add(updateButton);

			JButton mkbButton = new JButton("Справиочник МКБ");
			mkbButton.setBounds(500, 10, 250, 25);
	        
			panel1.add(mkbButton);
			
            Box box0 = Box.createVerticalBox();
            box0.setBounds(10, 50, 750, 480);

//            final JDBCAdapter adapter=new JDBCAdapter("org.sqlite.JDBC","jdbc:sqlite:raspisanie.db");
//            final JTable Table=new JTable(adapter);


            
            
            JDBCAdapter adapter_cp=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
            JTable CPTable=new JTable(adapter_cp);
            
//				adapter.executeQuery("select data_reg,fio,num_kart,data_in_doc,doc_name from TALON");
//            adapter.executeQuery("select id, id_people, start_time from time_work where id = '"+index.id_doc+"' and and start_time like '"+(new SimpleDateFormat("dd.MM.yyyy").format(calendar_t.ddd))+"%' ORDER BY start_time ASC;");
//            JLabel rec_in_table = new JLabel("Количество пациентов в базе="+Table.getValueAt(0, 0).toString(), SwingConstants.RIGHT);
//            rec_in_table.setBounds(750, 520, 260, 20);

            UpdTable();



            JScrollPane scrollPane = new JScrollPane(Table);
            Table.setFillsViewportHeight(true);
            box0.add(scrollPane);

            panel1.add(box0);

//format date to input
//		        SimpleDateFormat formatter2 = new SimpleDateFormat("dd.MM.yyyy");
//		        String stDate = formatter2.format(calendar.ddd);
//				System.out.println(new SimpleDateFormat("dd.MM.yyyy").format(calendar.ddd));

            //Display the window.
//            frame.getContentPane().add(jtp);
			//frame.getContentPane().add(panel);
            frame.getContentPane().add(jtp);
            frame.pack();
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

          //--------------------------------	        
   	     // update
   	        updateButton.addActionListener(new java.awt.event.ActionListener(){
   				public void actionPerformed(java.awt.event.ActionEvent evt) {
   					UpdTable();
   				}
   			});
            //--------------------------------	        
      	     // MKB
      	        mkbButton.addActionListener(new java.awt.event.ActionListener(){
      				public void actionPerformed(java.awt.event.ActionEvent evt) {
      					try{
      			           SwingUtilities.invokeLater( new Runnable() {
      		                public void run() {
      		                		new findMKB();
      		                    }

      		                
      		            });
      					
      					}
      					catch (Exception e1){
      						e1.printStackTrace();
      						System.out.println("Error ");
      					}	
      				}
      			});

//--------------
                BPeople.addActionListener(new java.awt.event.ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try{

                            new Form_People();

                        }
                        catch (Exception e1){
                            e1.printStackTrace();
                            System.out.println("Error ");
                        }
                    }
                });
//--------------
                FPeople.addActionListener(new java.awt.event.ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try{

                            new Find_Form();

                        }
                        catch (Exception e1){
                            e1.printStackTrace();
                            System.out.println("Error ");
                        }
                    }
                });
                
                
//-------------------
    	        frame.addWindowListener(new WindowListener() {
    	            // ...
    	            public void windowClosing(WindowEvent event) {
    	                // [ТУТ ТВОИ ДЕЙСТВИЯ ПО ЗАКРЫТИЮ]
    					try {
    						adapter.close();
    						adapter_cp.close();
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    						System.exit(0);
    					} catch (NullPointerException e)
    					{
    						System.exit(0);
    					}

    	                System.exit(0);
    	            }

    				@Override
    				public void windowActivated(WindowEvent arg0) {
    					// TODO Auto-generated method stub
    					
    				}

    				@Override
    				public void windowClosed(WindowEvent arg0) {
    					// TODO Auto-generated method stub
    					
    				}

    				@Override
    				public void windowDeactivated(WindowEvent arg0) {
    					// TODO Auto-generated method stub
    					
    				}

    				@Override
    				public void windowDeiconified(WindowEvent arg0) {
    					// TODO Auto-generated method stub
    					
    				}

    				@Override
    				public void windowIconified(WindowEvent arg0) {
    					// TODO Auto-generated method stub
    					
    				}

    				@Override
    				public void windowOpened(WindowEvent arg0) {
    					// TODO Auto-generated method stub
    					
    				}
    	        });		        
    	        
    ///
                
///-------------------------------------	        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public static void UpdTable(){
    SwingUtilities.invokeLater( new Runnable() {
        public void run() {
            JDBCAdapter adapter_cp=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
            JTable CPTable=new JTable(adapter_cp);
        	adapter.executeQuery("select time_work.\"id_people\", time_work.\"id\" as \"ФИО\", time_work.\"start_time\" as \"Время приёма\", time_work.\"id_people\" as \"Посещение\" from public.time_work where time_work.\"id_doc\" = '"+index.id_doc+"' and time_work.\"start_time\"::text like '"+(new SimpleDateFormat("yyyy-MM-dd").format(calendar_t.ddd))+"%' and time_work.\"print\"='TRUE'  ORDER BY time_work.\"start_time\" ASC;");
            for (int i=0; i< Table.getRowCount(); i++ ){
                adapter_cp.executeQuery("SELECT people.\"first_name\",people.\"last_name\",people.\"surname\" from public.people where people.\"id\" ='"+Table.getValueAt(i, 0)+"';");
				adapter.setValueAt(CPTable.getValueAt(0, 1).toString()+ " " + CPTable.getValueAt(0, 0).toString() + " " +CPTable.getValueAt(0, 2).toString(), i, 1);
    			try{
    				adapter_cp.executeQuery("select stattalon.\"id_people\" from public.stattalon where stattalon.\"id_people\"='"+Table.getValueAt(i, 0).toString().trim()+"' and stattalon.\"id_doctor\" = '"+index.id_doc+"' and stattalon.\"created_at\"::text  like '"+(new SimpleDateFormat("yyyy-MM-dd").format(Form.calendar_t.ddd))+"%';");
    				if(!CPTable.getValueAt(0, 0).toString().equals("")) {
    					adapter.setValueAt("Принят", i, 3);
    			}else
    			{
    				adapter.setValueAt("", i, 3);
    			}
    					
    			}catch (ArrayIndexOutOfBoundsException e){
    				adapter.setValueAt("", i, 3);
    			}
        	

            }
            Table.removeColumn(Table.getColumnModel().getColumn(0));
        	Table.setDefaultEditor(String.class, new TEditor(adapter));
        	Table.setDefaultRenderer(Object.class,new ColorRenderer());
	
       	
        	try {
				adapter_cp.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    });

}
	
}


