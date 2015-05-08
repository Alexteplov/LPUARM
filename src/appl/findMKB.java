package appl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.w3c.dom.Text;

import sql.JDBCAdapter;
import time.DatePickerCodeExample;







//import javax.swing.JComponent;

public class findMKB extends JDialog{
	public static String nmkb_str;
    public findMKB(){
        try{
        	//System.out.println("I'm in Form!");
            JFrame frame = new JFrame("Поиск по МКБ.");
            //exit
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		    
            final JPanel panel = new JPanel();
	        panel.setLayout(null);
            
       
			JButton findButton = new JButton("Найти");
			findButton.setBounds(350, 25, 80, 25);
			
	        panel.add(findButton);

            Box box0 = Box.createVerticalBox();
            box0.setBounds(10, 50, 682, 400);
            
            JLabel nmkb = new JLabel("Наименование мкб:");
            nmkb.setBounds(100, 10, 150, 20);
            final JTextField nmkb_str = new JTextField(30);
            nmkb_str.setBounds(100, 30, 240, 20);

            panel.add(nmkb);
            panel.add(nmkb_str);

            JLabel kmkb = new JLabel("Код мкб:");
            kmkb.setBounds(10, 10, 150, 20);
            final JTextField kmkb_str = new JTextField(20);
            kmkb_str.setBounds(10, 30, 50, 20);
            
            panel.add(kmkb);
            panel.add(kmkb_str);

            final JDBCAdapter adapter=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
            final JTable Table=new JTable(adapter);
            
//            final JDBCAdapter adapter_cp=new JDBCAdapter("org.sqlite.JDBC","jdbc:sqlite:talon.db");
//            final JTable CPTable=new JTable(adapter_cp);
            
//				adapter.executeQuery("select data_reg,fio,num_kart,data_in_doc,doc_name from TALON");
//            adapter.executeQuery("select id, id_people, start_time from time_work where id = '"+index.id_doc+"' and and start_time like '"+(new SimpleDateFormat("dd.MM.yyyy").format(calendar_t.ddd))+"%' ORDER BY start_time ASC;");
//            JLabel rec_in_table = new JLabel("Количество пациентов в базе="+Table.getValueAt(0, 0).toString(), SwingConstants.RIGHT);
//            rec_in_table.setBounds(750, 520, 260, 20);



            SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                    //new Sql();

                //	System.out.println("select id, id_people, start_time from time_work where id_doc = '"+index.id_doc+"' and start_time like '"+(new SimpleDateFormat("dd.MM.yyyy").format(calendar_t.ddd))+"%' and print='TRUE' ORDER BY start_time ASC;");
                	adapter.executeQuery("SELECT  mkb.\"KMKB\", mkb.\"NMKB\" FROM public.mkb order by mkb.\"KMKB\" ASC;");
//                    Table.removeColumn(Table.getColumnModel().getColumn(0));
//                    Table.setDefaultEditor(String.class, new TEditor(adapter));

                    }

                
            });


            JScrollPane scrollPane = new JScrollPane(Table);
            Table.setFillsViewportHeight(true);
            box0.add(scrollPane);

            panel.add(box0);

//format date to input
//		        SimpleDateFormat formatter2 = new SimpleDateFormat("dd.MM.yyyy");
//		        String stDate = formatter2.format(calendar.ddd);
//				System.out.println(new SimpleDateFormat("dd.MM.yyyy").format(calendar.ddd));

            //Display the window.
//            frame.getContentPane().add(jtp);
			frame.getContentPane().add(panel);
            frame.pack();
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

          //--------------------------------	        
   	     // find
   	        findButton.addActionListener(new java.awt.event.ActionListener(){
   				public void actionPerformed(java.awt.event.ActionEvent evt) {
   					try{
   			           SwingUtilities.invokeLater( new Runnable() {
   		                public void run() {
   		                    //new Sql();
   		                	adapter.executeQuery("SELECT  mkb.\"KMKB\", mkb.\"NMKB\" FROM public.mkb where mkb.\"NMKB\" like '"+nmkb_str.getText().trim()+"%' and mkb.\"KMKB\" like '"+kmkb_str.getText().toUpperCase().trim()+"%' ORDER BY mkb.\"KMKB\",mkb.\"NMKB\" ASC;");
   		                }

   		                
   		            });
   					
   					}
   					catch (Exception e1){
   						e1.printStackTrace();
   						System.out.println("Error ");
   					}	
   				}
   			});
   	   ///------------------------------

   	        Table.addMouseListener(new MouseListener() {
	               public void mouseClicked(MouseEvent event) {
	               }
	               public void mouseEntered(MouseEvent event) {
	               }
	               public void mouseExited(MouseEvent event) {
	               }
	        
	               public void mousePressed(MouseEvent event)
	               {
	        	if (event.getButton() == MouseEvent.BUTTON1)
	        	{
	        		
	        		//System.out.println(Table.getValueAt(Table.rowAtPoint(event.getPoint()), 0));
	        		findMKB.nmkb_str = (String) Table.getValueAt(Table.rowAtPoint(event.getPoint()), 0);
	        		frame.dispose();
	        	}
	        
	        
	        
	        
	        
	        
	        }
	               public void mouseReleased(MouseEvent event) {
	               }
	          });
   	   //-------------------
	        frame.addWindowListener(new WindowListener() {
	            // ...
	            public void windowClosing(WindowEvent event) {
	                // [ТУТ ТВОИ ДЕЙСТВИЯ ПО ЗАКРЫТИЮ]
					try {
						adapter.close();
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

}


