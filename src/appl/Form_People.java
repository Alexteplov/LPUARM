package appl;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import sql.JDBCAdapter;
import time.DatePickerCodeExample;

public class Form_People extends JDialog{
	public Form_People(){
		try{
			//System.out.println("I'm in Form!");
			JFrame frame_a = new JFrame("Приписанное население");
			 //exit
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		    
		    JPanel panel1_p = new JPanel();
	        panel1_p.setLayout(null);
	        
	        JLabel last_name = new JLabel("Фамилия");
			last_name.setBounds(10, 10, 80, 20);
			final JTextField last_name_str = new JTextField(30);
			last_name_str.setBounds(120, 10, 240, 20);
	        
			JLabel first_name = new JLabel("Имя");
			first_name.setBounds(10, 30, 80, 20);
			final JTextField first_name_str = new JTextField(30);
			first_name_str.setBounds(120, 30, 240, 20);
			
			JLabel surname = new JLabel("Отчество");
			surname.setBounds(10, 50, 80, 20);
			final JTextField surname_str = new JTextField(30);
			surname_str.setBounds(120, 50, 240, 20);
			
			JLabel birthday = new JLabel("Дата рождения");
			birthday.setBounds(10, 70, 110, 20);

			final DatePickerCodeExample calendar_p = new DatePickerCodeExample();
	        calendar_p.setBounds(245, 70, 110, 40);


			
//			final JTextField birthday_str = new JTextField(30);
//			birthday_str.setBounds(120, 70, 80, 20);

			
			
			
			JLabel card_number = new JLabel("Номер карты");
			card_number.setBounds(10, 110, 110, 20);
			final JTextField card_number_str = new JTextField(30);
			card_number_str.setBounds(120, 110, 240, 20);
/*
			JLabel service_place = new JLabel("Место службы");
			service_place.setBounds(10, 130, 110, 20);
			final JTextField service_place_str = new JTextField(30);
			service_place_str.setBounds(120, 130, 240, 20);
			
			JLabel rank = new JLabel("Должность");
			rank.setBounds(10, 150, 110, 20);
			final JTextField rank_str = new JTextField(30);
			rank_str.setBounds(120, 150, 240, 20);
*/
			JLabel sex = new JLabel("Пол");
			sex.setBounds(370, 10, 30, 20);

			String[] items = {
				    "муж",
				    "жен"		    
				};
			
			final JComboBox sexBox = new JComboBox(items);
	        sexBox.setBounds(400, 10, 80, 20);
			
			JLabel finance = new JLabel("Источник финансирования");
			finance.setBounds(370, 30, 160, 20);

			String[] items1 = {
				    "федеральный бюджет",
				    "ОМС",
				    "Платные"
				};
			
			final JComboBox financeBox = new JComboBox(items1);
	        financeBox.setBounds(540, 30, 160, 20);
	        
			JLabel ministerstvo = new JLabel("Министерство");
			ministerstvo.setBounds(370, 50, 100, 20);

			String[] items2 = {
				    "МВД",
				    "ФСИН",
				    "МЧС"
				};
			
			final JComboBox ministerstvoBox = new JComboBox(items2);
			ministerstvoBox.setBounds(470, 50, 80, 20);
	        

			JLabel category = new JLabel("Категория");
			category.setBounds(370, 70, 100, 20);

			String[] items3 = {
				    "сотрудник",
				    "сотрудник ВБД",
				    "работник",
				    "пенсионер",
				    "пенсионер ИВОВ",
				    "пенсионер УВОВ",
				    "член семьи сотрудника",
				    "член семьи пенсионера",
				    "член семьи погибшего"
				};
			
			final JComboBox categoryBox = new JComboBox(items3);
			categoryBox.setBounds(470, 70, 180, 20);
			
// ????? ? ????????????
			JLabel prasdel = new JLabel("Отдел:");
			prasdel.setBounds(10, 130, 100, 20);
			final JComboBox placeBox = new JComboBox(new CBM());
			placeBox.setBounds(120, 130, 250, 20);
			
			JLabel otdel = new JLabel("Подразделение:");
			otdel.setBounds(10, 160, 100, 20);
			final JComboBox placeOBox = new JComboBox(new CBM());
			placeOBox.setBounds(120, 160, 250, 20);
			
			
			//final JComboBox rankBox = new JComboBox(new CBM());
			//rankBox.setBounds(370, 150, 250, 20);		
			
			
			
			JButton add_record = new JButton("Добавить запись");
			add_record.setBounds(10, 190, 220, 30);
		    //Box box_p4 = Box.createVerticalBox();
			//box_p4.setBounds(20, 40, 20, 40);
	    
			//box_p4.add(BPeople);
			
			final JDBCAdapter Tadapter=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
			final JTable TPeople=new JTable(Tadapter);
			final JDBCAdapter Padapter=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
			final JTable TPlace=new JTable(Padapter);
			final JDBCAdapter POadapter=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
			final JTable TOPlace=new JTable(POadapter);
			final JDBCAdapter Radapter=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
			final JTable TRlace=new JTable(Radapter);
//			adapter.executeQuery("select data_reg,fio,num_kart,data_in_doc,doc_name from TALON");
			
	        SwingUtilities.invokeLater( new Runnable() {
	            public void run() {                
	                    //new Sql();
	        		//Tadapter.executeQuery("select last_name, first_name, surname, birthday, card_number, service_place, rank from people LIMIT 100");
//?????? ??????? ? ID 
//	        		Table.removeColumn(Table.getColumnModel().getColumn(0));
//	        		TPeople.getColumnModel().getColumn(0).setMaxWidth(0);
	        		Tadapter.executeQuery("select id, last_name, first_name, surname, sex, birthday, finance, card_number, service_place, ministerstvo, category, rank from public.people LIMIT 100");
//data_reg,fio,num_kart,data_in_doc,doc_name
	        		
	        		//System.out.println("Stream get data from people!");
//?????? ??????? ? ID 
	        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(0));
	        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(3));
	        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(4));
	        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(6));
	        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(6));
	        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(5));
	        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(5));

				    for (int i=0; i< TPeople.getRowCount(); i++ ){
				    	try {
							Tadapter.setValueAt((new SimpleDateFormat("dd.MM.yyyy").format(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(TPeople.getValueAt(i, 3).toString().trim()))), i, 5);
				    	} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	
			        }

	            
	            
	            }
	        });

	        SwingUtilities.invokeLater( new Runnable() {
	            public void run() {                
	                    //new Sql();
	        		Padapter.executeQuery("select id, prazdel from placeR ORDER BY prazdel ASC");
	    	// ????????? ?????? ? ????
	        		if (TPlace.getRowCount()>0){
	        	        String[] items4 = new String[TPlace.getRowCount()];
	        	        for (int i=0; i< TPlace.getRowCount(); i++ ){
	        	        	items4[i]=TPlace.getValueAt(i, 1).toString().trim();
	        	        }
	        	         ((CBM) placeBox.getModel()).setRubricList(items4,TPlace.getRowCount());
	        			}
	            }
	        });

	        placeBox.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent e) {
	                if(e.getStateChange() == ItemEvent.SELECTED){
	                    //System.out.println("Get SPBox index "+(placeBox.getSelectedIndex()+1));
	    				// ????????? ??????? ???? ?.?. ? ???????
	    				
	        	        POadapter.executeQuery("Select * from placeP WHERE id_prasdel='"+TPlace.getValueAt(placeBox.getSelectedIndex(), 0).toString().trim()+"' ORDER BY otdel ASC;");		
	    		       	if (TOPlace.getRowCount()>0){
	    		       		String[] Oitems = null;
	    		       		Oitems = new String[TOPlace.getRowCount()];
	    		        for (int i=0; i< TOPlace.getRowCount(); i++ ){
	    		        	Oitems[i]=TOPlace.getValueAt(i, 2).toString();
	    		        	// update talon table on canvas
	    		        	//adapter_tw.executeQuery("select room, reserv, start_time from time_work where id_doc="+ +";");
	    		        	
	    		        }
	    		        ((CBM) placeOBox.getModel()).setRubricList(Oitems,TOPlace.getRowCount());
//	    		        System.out.println(DTable.getValueAt(SPBox.getSelectedIndex()+1, 0).toString());
	    		       	}
	    		        else{
	    		        	String[] items = null;
	    		        	//items = new String[0];	
	    		        	((CBM) placeOBox.getModel()).setRubricList(items,TOPlace.getRowCount());
	    		        }
	                }
	            }
	        });

	        
	        
	        
	        
/*	        
	        SwingUtilities.invokeLater( new Runnable() {
	            public void run() {                
	                    //new Sql();
	        		Radapter.executeQuery("select distinct rank from people ORDER BY rank ASC");
// ????????? ?????? ? ????
	        		if (TRlace.getRowCount()>0){
	        	        String[] items5 = new String[TRlace.getRowCount()];
	        	        for (int i=0; i< TRlace.getRowCount(); i++ ){
	        	        	items5[i]=TRlace.getValueAt(i, 0).toString().trim();
	        	        }
	        	         ((CBM) rankBox.getModel()).setRubricList(items5,TRlace.getRowCount());
	        			}
	            }
	        });
*/
	        	        
			Box Pbox = Box.createVerticalBox();
			Pbox.setBounds(10, 220, 780, 350);

		    JScrollPane scrollPane = new JScrollPane(TPeople);
		    TPeople.setFillsViewportHeight(true);
		    Pbox.add(scrollPane);

			
			panel1_p.add(add_record);
			panel1_p.add(Pbox);
			
	        panel1_p.add(calendar_p);		
			
			panel1_p.add(last_name);
	        panel1_p.add(last_name_str);
	        panel1_p.add(first_name);
	        panel1_p.add(first_name_str);
	        panel1_p.add(surname);
	        panel1_p.add(surname_str);
	        panel1_p.add(birthday);
//	        panel1_p.add(birthday_str);
	        panel1_p.add(card_number);
	        panel1_p.add(card_number_str);
	        
//	        panel1_p.add(service_place);
//	        panel1_p.add(service_place_str);
//	        panel1_p.add(rank);
//	        panel1_p.add(rank_str);
	        
	        panel1_p.add(sex);
	        panel1_p.add(sexBox);
	        panel1_p.add(finance);
	        panel1_p.add(financeBox);
	        panel1_p.add(ministerstvo);
	        panel1_p.add(ministerstvoBox);
	        panel1_p.add(category);
	        panel1_p.add(categoryBox);
	        
	        panel1_p.add(prasdel);
	        panel1_p.add(placeBox);
	        panel1_p.add(otdel);
	        panel1_p.add(placeOBox);
//	        panel1_p.add(rankBox);
			
			frame_a.getContentPane().add(panel1_p);
			frame_a.pack();
			frame_a.setSize(800, 600);
			frame_a.setLocationRelativeTo(null);
	        frame_a.setVisible(true);
		    
	        //System.out.println(birthday_str.getText().toString());
	// add record
	        add_record.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					try{
						SwingUtilities.invokeLater( new Runnable() {
				            public void run() {                
				                    //new Sql();
				            	try {
									Tadapter.executeQueryIns("INSERT INTO people (first_name, last_name, surname, sex, birthday, finance, card_number, ministerstvo, category, created_at, updated_at, id_prasdel, id_otdel) VALUES" +
											"('"+first_name_str.getText()+"','"+last_name_str.getText()+"','"+
											surname_str.getText()+"','"+sexBox.getSelectedItem().toString()+"','"+
											(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(calendar_p.ddd.toString())))+"','"+financeBox.getSelectedItem().toString()+
											"','"+card_number_str.getText()+"','"+ministerstvoBox.getSelectedItem().toString()+"','"+categoryBox.getSelectedItem().toString()+"','"+
											(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(java.util.Calendar.getInstance().getTime()) +"','"+
											(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(java.util.Calendar.getInstance().getTime())+"','"+
											TPlace.getValueAt(placeBox.getSelectedIndex(), 0).toString()+"','"+
											TOPlace.getValueAt(placeOBox.getSelectedIndex(), 0).toString()+"');");
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				        			     ///new SimpleDateFormat("dd.MM.yyyy").format(calendar_p.ddd) ???? ????????
				            			//birthday_str.getText().toString()
		//data_reg,fio,num_kart,data_in_doc,doc_name
				        		
//				        		System.out.println("Stream get data from TALON!");
		//?????? ??????? ? ID  
//				        		Table.removeColumn(Table.getColumnModel().getColumn(0));
								//Tadapter.executeQuery("select id, last_name, first_name, surname, birthday, card_number, service_place, rank from people where first_name like '%"+first_name_str.getText()+"%'");
				        		Tadapter.executeQuery("select id, last_name, first_name, surname, sex, birthday, finance, card_number, service_place, ministerstvo, category, rank from people where first_name like '%"+first_name_str.getText()+"%' LIMIT 100");
				        		//data_reg,fio,num_kart,data_in_doc,doc_name
				        		//?????? ??????? ? ID 
				        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(0));
				        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(3));
				        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(4));
				        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(6));
				        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(6));
				        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(5));
				        		TPeople.removeColumn(TPeople.getColumnModel().getColumn(5));

							    for (int i=0; i< TPeople.getRowCount(); i++ ){
							    	try {
							    		//System.out.println((new SimpleDateFormat("dd.MM.yyyy").format(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(TPeople.getValueAt(i, 3).toString().trim()))));
							    		Tadapter.setValueAt((new SimpleDateFormat("dd.MM.yyyy").format(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(TPeople.getValueAt(i, 3).toString().trim()))), i, 5);
							    	} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
						        	
						        }
				        		
				            }
				        });

					}
					catch (Exception e1){
						e1.printStackTrace();
						System.out.println("Error ");
					}	
				}
			});
			
//-------------------
	        frame_a.addWindowListener(new WindowListener() {
	            // ...
	            public void windowClosing(WindowEvent event) {
	                // [ТУТ ТВОИ ДЕЙСТВИЯ ПО ЗАКРЫТИЮ]
					try {
						Tadapter.close();
						Padapter.close();
						POadapter.close();
						Radapter.close();
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

		
//---------------------- END		
		} catch (Exception e) {            
            e.printStackTrace();
		}
	}
}