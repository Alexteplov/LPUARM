package appl;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
//import java.text.SimpleDateFormat;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import sql.JDBCAdapter;


public class changeDoc extends JDialog{
	    public changeDoc(){
	        try{
				final JFrame frame_a = new JFrame("Войти в систему");
				 JPasswordField passwordField;
				 //exit
				setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			    
			    final JPanel panel1_t = new JPanel();
		        panel1_t.setLayout(null);
		        
				final JDBCAdapter SPadapter=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
				final JTable SPPeople=new JTable(SPadapter);
				
//				final JDBCAdapter adapter_tw=new JDBCAdapter("org.sqlite.JDBC","jdbc:sqlite:raspisanie.db");
//				final JTable TWTable=new JTable(adapter_tw);

				//final JDBCAdapter adapter_d=new JDBCAdapter("org.sqlite.JDBC","jdbc:sqlite:talon.db");
				final JDBCAdapter adapter_d=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
				final JTable DTable=new JTable(adapter_d);
				
				
		        SPadapter.executeQuery("select * from public.SP_podrasdel");
				//SPPeople.getColumnModel().getColumn(0).setMaxWidth(0);
				
				String[] SPitems = new String[SPPeople.getRowCount()];
		        for (int i=0; i< SPPeople.getRowCount(); i++ ){
		        	SPitems[i]=SPPeople.getValueAt(i, 1).toString();
		        }
		        
		        final JComboBox SPBox = new JComboBox(new CBM());
		        SPBox.setBounds(10, 10, 180, 20);
		        ((CBM) SPBox.getModel()).setRubricList(SPitems,SPPeople.getRowCount());
		        
		        //adapter_d.executeQuery("Select * from doctors WHERE id_sp_podrasdel='1';");
		        adapter_d.executeQuery("Select * from public.doctors WHERE doctors.\"id_sp_podrasdel\"='1';");
//		        items = null;
		       	String[] items = new String[DTable.getRowCount()];
		        for (int i=0; i< DTable.getRowCount(); i++ ){
		        	items[i]=DTable.getValueAt(i, 3).toString()+" "+DTable.getValueAt(i, 2).toString().substring(0, 1)+"."+DTable.getValueAt(i, 4).toString().substring(0, 1)+". "
		        			+DTable.getValueAt(i, 5).toString();
		        	
		        }
		        
		        
		        final JComboBox comboBox = new JComboBox(new CBM());
		        comboBox.setBounds(10, 40, 180, 20);
				
		        ((CBM) comboBox.getModel()).setRubricList(items,DTable.getRowCount());
		        
//-------------------
		        frame_a.addWindowListener(new WindowListener() {
		            // ...
		            public void windowClosing(WindowEvent event) {
		                // [ТУТ ТВОИ ДЕЙСТВИЯ ПО ЗАКРЫТИЮ]
						try {
							SPadapter.close();
							adapter_d.close();
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
		        
		        SPBox.addItemListener(new ItemListener() {
		            public void itemStateChanged(ItemEvent e) {
		                if(e.getStateChange() == ItemEvent.SELECTED){
		                    //System.out.println("Get SPBox index "+(SPBox.getSelectedIndex()+1));
		    				// заполняем чекбокс ниже т.е. с врачами
		    				
		                	adapter_d.executeQuery("Select * from public.doctors WHERE doctors.\"id_sp_podrasdel\"="+(SPPeople.getValueAt(SPBox.getSelectedIndex(), 0).toString())+";");		
		    		       	if (DTable.getRowCount()>0){
		    		       		String[] items = null;
		    		       		items = new String[DTable.getRowCount()];
		    		        for (int i=0; i< DTable.getRowCount(); i++ ){
		    		        	items[i]=DTable.getValueAt(i, 3).toString()+" "+DTable.getValueAt(i, 2).toString().substring(0, 1)+"."+DTable.getValueAt(i, 4).toString().substring(0, 1)+". "
		    		        			+DTable.getValueAt(i, 5).toString();
		    		        	//System.out.println(DTable.getValueAt(i, 3).toString()+" "+DTable.getValueAt(i, 2).toString().substring(0, 1)+"."+DTable.getValueAt(i, 4).toString().substring(0, 1)+". "
		    		        		//	+DTable.getValueAt(i, 5).toString());	
		    		        	((CBM) comboBox.getModel()).setRubricList(items,DTable.getRowCount());
		    		        	// update talon table on canvas
		    		        	//adapter_tw.executeQuery("select room, reserv, start_time from time_work where id_doc="+ +";");
		    		        	
		    		        }
//		    		        System.out.println(DTable.getValueAt(SPBox.getSelectedIndex()+1, 0).toString());
		    		       	}
		    		        else{
		    		        	String[] items = null;
		    		        	//items = new String[0];	
		    		        	((CBM) comboBox.getModel()).setRubricList(items,DTable.getRowCount());
		    		        }
		                }
		            }
		        });
		        
		        
		        passwordField = new JPasswordField(10);
		        passwordField.setBounds(10, 70, 180, 25);
		        
				JButton enterButton = new JButton("Войти");
				enterButton.setBounds(10,110, 180, 25);		        
		        
		        panel1_t.add(comboBox);
		        panel1_t.add(SPBox);
		        panel1_t.add(enterButton);
		        panel1_t.add(passwordField);
		        
		        
				frame_a.getContentPane().add(panel1_t);
				frame_a.pack();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

				
				pack();
				setResizable(false);
				setBounds((screenSize.width - 210) / 2,(screenSize.height - 210) / 2 , 210, 210);
				frame_a.setSize(210, 210);
				frame_a.setLocationRelativeTo(null);
		        frame_a.setVisible(true);
		        
		        
		      //--------------------------------	        
			     // add record
			        enterButton.addActionListener(new java.awt.event.ActionListener(){
						public void actionPerformed(java.awt.event.ActionEvent evt) {
							try{
								if (SPBox.getSelectedIndex() >= 0 && comboBox.getSelectedIndex() >= 0)
								{
									//System.out.println(DTable.getValueAt(comboBox.getSelectedIndex(),0));
									index.id_doc =  Integer.parseInt(new String().valueOf(DTable.getValueAt(comboBox.getSelectedIndex(),0)));

									JDBCAdapter adapter=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
									JTable Table=new JTable(adapter);
									
									
									adapter.executeQuery("select doctors.\"password\" from public.doctors where doctors.\"id\"='"+new String().valueOf(DTable.getValueAt(comboBox.getSelectedIndex(),0))+"';");
									adapter.close();
							       // System.out.println("Table out="+Table.getValueAt(0, 0).toString());
							        if (Table.getValueAt(0, 0).toString().equals(passwordField.getText()))
							        {
										new Form();
										frame_a.dispose();	//close form
							        }
							        else
							        {
							        	JOptionPane.showMessageDialog(
						        				new JFrame(), 
						        		"Вы не правильно ввели пароль!\nЕсли вы забыли свой пароль обратитесь к администратору!");
							        }
								}
							}
							catch (Exception e1){
								e1.printStackTrace();
								System.out.println("Error ");
							}	
						}
					});

				
	        }
	        catch (Exception e)
	        {
	        	JOptionPane.showConfirmDialog(
        				new JFrame(),
        			    "Ошибка в основном теле программы, обратитесь к разработчику!",
        			    "Ошибка",
        			    JOptionPane.YES_NO_OPTION);
	        }
			//return 0;

	    }
}
