package appl;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import sql.JDBCAdapter;







//import javax.swing.JComponent;

public class statTalon extends JDialog{
    public statTalon(int pData){
        try{
            //System.out.println("I'm in Form!");
            JFrame frame = new JFrame("Статистический талон");
            //exit
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		    
            final JPanel panel = new JPanel();
	        panel.setLayout(null);
//----------------
	        final JDBCAdapter adapter_id=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
	        adapter_id.executeQuery("select id, last_name, first_name, surname, sex, birthday, finance, card_number, service_place, ministerstvo, category, rank from people WHERE id='"+Integer.toString(pData)+"';");
	        
	        //System.out.println("all data="+adapter.rows.get(0).toString().substring(1,adapter.rows.get(0).toString().length()-1));
	        final String[] PD =  adapter_id.rows.get(0).toString().substring(1,adapter_id.rows.get(0).toString().length()-1).split(",");
	        
	        
	        
	        
	        JLabel last_name = new JLabel("Ф.И.О: "+PD[1]+PD[2]+PD[3]);
			last_name.setBounds(10, 5, 380, 20);
	        panel.add(last_name);
	        
	        JLabel sex = new JLabel("Пол: "+PD[4]);
			sex.setBounds(400, 5, 80, 20);
	        panel.add(sex);
	        
			JLabel birthday = new JLabel("Дата рождения: "+
					(new SimpleDateFormat("dd.MM.yyyy").format(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(PD[5].trim()))));
			birthday.setBounds(10, 20, 210, 20);
	        panel.add(birthday);
			
			JLabel ministerstvo = new JLabel("Министерство: "+PD[9]);
			ministerstvo.setBounds(400, 20, 210, 20);
	        panel.add(ministerstvo);
			//final JTextField birthday_str = new JTextField(30);
			//birthday_str.setBounds(120, 70, 240, 20);
			
			JLabel card_number = new JLabel("Номер карты: "+PD[7]);
			card_number.setBounds(10, 35, 210, 20);
	        panel.add(card_number);
			//final JTextField card_number_str = new JTextField(30);
			//card_number_str.setBounds(120, 90, 240, 20);

			JLabel service_place = new JLabel("Место службы: "+PD[8]);
			service_place.setBounds(10, 50, 510, 20);
	        panel.add(service_place);
			//final JTextField service_place_str = new JTextField(30);
			//service_place_str.setBounds(120, 110, 240, 20);
			
			JLabel rank = new JLabel("Должность: "+PD[11]);
			rank.setBounds(10, 65, 410, 20);
	        panel.add(rank);

			JLabel finance = new JLabel("Ист. финансирования: "+PD[6]);
			finance.setBounds(10, 80, 300, 20);
	        panel.add(finance);

			JLabel category = new JLabel("Категория: "+PD[10]);
			category.setBounds(10, 95, 300, 20);
	        panel.add(category);

	        
	        
	        
	        
//-----------------	        

//	        Box box0 = Box.createVerticalBox();
//            box0.setBounds(10, 50, 682, 400);

            
            JLabel come = new JLabel("1) Посещение:");
            come.setBounds(10, 120, 150, 20);
            panel.add(come);
            
			final JComboBox comeBox = new JComboBox(new String[]{"первичное","повторно"});
	        comeBox.setBounds(100, 120, 140, 20);
	        panel.add(comeBox);
            
	        
            JLabel come2 = new JLabel("2) Повод обращения:");
            come2.setBounds(10, 150, 150, 20);
            panel.add(come2);
            
			final JComboBox comeBox2 = new JComboBox(new String[]{"заболевание","проф. цель"});
	        comeBox2.setBounds(140, 150, 180, 20);
	        panel.add(comeBox2);
	        
            JLabel diagn = new JLabel("3) Диагноз устаноленный:");
            diagn.setBounds(10, 180, 160, 20);
            panel.add(diagn);
            
            final JTextField diagn_str = new JTextField(30);
            diagn_str.setBounds(170, 180, 200, 20);
            panel.add(diagn_str);
	        
            JLabel come3 = new JLabel("4) Установлен впервые:");
            come3.setBounds(10, 210, 150, 20);
            panel.add(come3);
            
			final JComboBox comeBox3 = new JComboBox(new String[]{"да","нет"});
	        comeBox3.setBounds(160, 210, 50, 20);
	        panel.add(comeBox3);

	        final JCheckBox come4 = new JCheckBox("5) Острое:");
	        come4.setBounds(5, 240, 100, 20);
            panel.add(come4);
            
            
	        come4.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent e) {
	                if (come4.isSelected() == true && !comeBox2.getSelectedItem().equals("проф. цель")){
		               // System.out.println("disable1");
		                comeBox3.setVisible(false);
	                }
	                if (come4.isSelected() == false && !comeBox2.getSelectedItem().equals("проф. цель")){
		              //  System.out.println("enable1");
		                comeBox3.setVisible(true);
	                }				                
	            }
	        }); 
            
//			final JComboBox comeBox4 = new JComboBox(new String[]{"","да"});
//	        comeBox4.setBounds(80, 130, 50, 20);
//	        panel.add(comeBox4);
	        
            JLabel come5 = new JLabel("6) Диспансерный:");
            come5.setBounds(10, 270, 140, 20);
            panel.add(come5);
            
			final JComboBox comeBox5 = new JComboBox(new String[]{"взят","состоит","не состоит","снят"});
	        comeBox5.setBounds(160, 270, 100, 20);
	        panel.add(comeBox5);
	        
            JLabel come6 = new JLabel("7) Травмы и отравления:");
            come6.setBounds(10, 300, 170, 20);
            panel.add(come6);
            
			final JComboBox comeBox6 = new JComboBox(new String[]{"да","нет"});
	        comeBox6.setBounds(180, 300, 160, 20);
	        panel.add(comeBox6);
	        
			JButton MKBButton = new JButton("Справочник МКБ");
			MKBButton.setBounds(380, 177, 140, 25);
	        panel.add(MKBButton);
	        
//            JLabel come7 = new JLabel("8) Увеличение нагрузки при проведении врачебных манипуляций:");
//            come7.setBounds(10, 330, 450, 20);
//            panel.add(come7);
	        
	        final JCheckBox come7 = new JCheckBox("8) Увеличение нагрузки при проведении врачебных манипуляций:");
	        come7.setBounds(5, 330, 450, 20);
            panel.add(come7);
          
            final JComboBox comeBox7 = new JComboBox(new String[]{"Врачом-акушером-гинекологом",
					"Врачом-отоларингологом",
					"Врачом-дерматовенерологом",
					"Врачом-хирургом",
					"Врачом-травмотологом"});
	        comeBox7.setBounds(10, 350, 260, 20);
	        panel.add(comeBox7);
	        
	        comeBox7.setVisible(false);
	        

	        
            final JComboBox comeBox8 = new JComboBox(new CBM());
	        comeBox8.setBounds(10, 380, 260, 20);
	        panel.add(comeBox8);
	        
	        comeBox8.setVisible(false);
	        
	        comeBox7.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent e) {
	                if(e.getStateChange() == ItemEvent.SELECTED){
	                if(comeBox7.getSelectedItem().equals("Врачом-акушером-гинекологом")){
	                	String[] items ={"аспирационная биопсия эндометрия", "биопсия шейки матки",
	                			"полипэктомия", "введение ВМС", "удаление ВМС", "аппаратное лечение эктопии шейки матки",
	                			"кольпоскопия"};
	                	((CBM) comeBox8.getModel()).setRubricList(items,items.length);
	                }	                	}
	                else if(comeBox7.getSelectedItem().equals("Врачом-отоларингологом")){
	                	String[] items ={"вскрытие абсцессов небных миндалин","промывание верхнечелюстных пазух ап-ом Гэйзер",
	                			"удаление серных пробок","пневмомассаж барабанной перепонки", "передняя носовая тампонада",
	                			"ультразвуковое исследование околоносовых пазух","пункция верхнечелюстных пазух"};
	                	((CBM) comeBox8.getModel()).setRubricList(items,items.length);}
	                else if(comeBox7.getSelectedItem().equals("Врачом-дерматовенерологом")){
	                	String[] items ={"диатермокоагуляция","криодеструкция","криомассаж волосистой части головы",
	                			"вскрытие фурункулов и атером"};
	                	((CBM) comeBox8.getModel()).setRubricList(items,items.length);}
	                else if(comeBox7.getSelectedItem().equals("Врачом-хирургом")){	
	                	String[] items ={"наложение швов","ПХО (первичное хир. обработка раны)","вскрытие фурункулов, абсцессов",
	                			"удаление ногтевой пластины", "краевая резекция ногтевой пластины","удаление фибром, липом и т.д.",
	                			"вскрытие гематом", "удаление инородных тел","вскрытие бурситов","внутресуставная пункция сустава",
	                			"вскрыие гидраденитов"};
	                	((CBM) comeBox8.getModel()).setRubricList(items,items.length);}
	                else if(comeBox7.getSelectedItem().equals("Врачом-травмотологом")){
	                	String[] items ={"внутрисуставная пункция","внутресуставная иньекция","параартикулярная блокада","ПХО (первичная хир. обработка раны)",
	                			"гипсовая иммобилизация","репозиция","вскрытие гематом","МОС (металостеосинтез мелких костей)","удаление ногтевой пластинки",
	                			"краевая резекция ногтевой пластины", "наложение швов"};
	                	((CBM) comeBox8.getModel()).setRubricList(items,items.length);}
	    		        }
	        });
	        
	        
	        
	        
	        
	        
	        come7.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent e) {
	                if (come7.isSelected() == true ){
		               // System.out.println("disable1");
		                comeBox7.setVisible(true);
		                comeBox8.setVisible(true);
	                }
	                if (come7.isSelected() == false ){
		              //  System.out.println("enable1");
		                comeBox7.setVisible(false);
		                comeBox8.setVisible(false);
	                }				                
	            }
	        }); 

                       
	            

			JButton setStatButton = new JButton("Заполнить статистические данные");
			setStatButton.setBounds(10, 400, 250, 25);
	        panel.add(setStatButton);
	        
//            final JDBCAdapter adapter=new JDBCAdapter("org.sqlite.JDBC","jdbc:sqlite:mkb.db");
//            final JTable Table=new JTable(adapter);
            

			frame.getContentPane().add(panel);
            frame.pack();
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

          //--------------------------------	        
   	     // find
   	        setStatButton.addActionListener(new java.awt.event.ActionListener(){
   				public void actionPerformed(java.awt.event.ActionEvent evt) {
   					try{
   			           SwingUtilities.invokeLater( new Runnable() {
   		                public void run() {
   		                	JDBCAdapter adapter=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
   		                	String come44 = "false";
   		                	if (come4.isSelected() == true){come44 = "true";}
								
   		                	
//   		                	if(diagn_str.getText().equals("") && !comeBox2.getSelectedItem().equals("проф. цель"))
//   		                	{
   		                	adapter.executeQueryIns("INSERT INTO stattalon (id_people, id_doctor, mkb, yst, ostroe, dispanser, tr_otr, created_at, coming, target) VALUES" +
									"('"+PD[0].trim()+"','"+new String().valueOf(index.id_doc)+"','"+
									diagn_str.getText().trim()+"','"+comeBox3.getSelectedItem().toString()+"','"+come44+"','"+
									comeBox5.getSelectedItem().toString()+"','"+comeBox6.getSelectedItem().toString()+"','"+
									(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(java.util.Calendar.getInstance().getTime())+
									"','"+comeBox.getSelectedItem().toString()+"','"+comeBox2.getSelectedItem().toString()+"');");
   		                	
   		                	//Form.setRowColor(1);
   		                	try {
								adapter.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
   	   					 catch (NullPointerException e)
    					{
    					
    					}
   							//System.out.println("End Stattalon");
   		            		Form.UpdTable();
   		                frame.dispose();
   		                }
  /*
   		                else
   	   		                {
   		                		JOptionPane.showMessageDialog(
				        				new JFrame(), 
				        		"Вы не заполнили поле диагноз!\nВам необходимо заполнить поле!");	
   	   		                }

   		                }
*/   		                

   		                
   		            });
   					
   					}
   					catch (Exception e1){
   						e1.printStackTrace();
   						System.out.println("Error ");
   					}	
   				}
   			});
//--------------------------------------
     	     // find
   	        MKBButton.addActionListener(new java.awt.event.ActionListener(){
   				public void actionPerformed(java.awt.event.ActionEvent evt) {
	        		// почистим переменную
	        		findMKB.nmkb_str = "";
	            	   new findMKB();
   				}
   			});
   	   ///------------------------------
	        diagn_str.addMouseListener(new MouseListener() {
	               public void mouseClicked(MouseEvent event) {
	               }
	               public void mouseEntered(MouseEvent event) {
	            	   
	               }
	               public void mouseExited(MouseEvent event) {
	            	  
	               }
	        
	               public void mousePressed(MouseEvent event)
	               {
//	        	if (event.getButton() == MouseEvent.BUTTON1)
//	        	{
	        		// почистим переменную
//	        		findMKB.nmkb_str = "";
//	            	   new findMKB();
//	        	}
	        	if (event.getButton() == MouseEvent.BUTTON3)
	        	{
	        		try{
		            	   if (!findMKB.nmkb_str.equals(diagn_str.getText().trim()))
		            	   diagn_str.setText(findMKB.nmkb_str);
		            	   }
		            	   catch (NullPointerException e)
		            	   {
		            		   // если у нас происходит сравнение с 0
		            	   }
	        	}
	        
	        
	        
	        
	        
	        }
	               public void mouseReleased(MouseEvent event) {
	               }
	          });
 //----------------------
	        comeBox2.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent e) {
	                if(e.getStateChange() == ItemEvent.SELECTED){
	                    //System.out.println("Get SPBox index "+(SPBox.getSelectedIndex()+1));
	    				// заполняем чекбокс ниже т.е. с врачами
	    				if (comeBox2.getSelectedItem().equals("проф. цель")){
	    					comeBox3.setVisible(false);
	    				}else comeBox3.setVisible(true);
	    				
	    		       	
	                }
	            }
	        });
//-------------------
	        frame.addWindowListener(new WindowListener() {
	            // ...
	            public void windowClosing(WindowEvent event) {
	                // [ТУТ ТВОИ ДЕЙСТВИЯ ПО ЗАКРЫТИЮ]
	            	try {
						//System.out.println("End Stattalon");
	            		Form.UpdTable();
						adapter_id.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Form.UpdTable();
						frame.dispose();
					} catch (NullPointerException e)
					{
						Form.UpdTable();
						frame.dispose();
					}

					frame.dispose();
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


