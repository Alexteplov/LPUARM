package appl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import sql.JDBCAdapter;


//import appl.JDBCAdapter;
//import appl.TEditor;

public class Find_Form extends JDialog{
    public Find_Form(){
    	
	JPanel panel1 = new JPanel();
    panel1.setLayout(null);
	
	JLabel fio = new JLabel("Фамилия:");
    fio.setBounds(10, 10, 60, 20);
    final JTextField fio_str = new JTextField(30);
    fio_str.setBounds(10, 30, 240, 20);

    JLabel name_ch = new JLabel("Имя:");
    name_ch.setBounds(10, 50, 30, 20);
    final JTextField name_ch_str = new JTextField(30);
    name_ch_str.setBounds(10, 70, 240, 20);

    JLabel num_card = new JLabel("Номер карты:");
    num_card.setBounds(10, 90, 130, 20);
    final JTextField num_card_str = new JTextField(30);
    num_card_str.setBounds(10, 110, 240, 20);

    JLabel fio_fnd = new JLabel("Поиск пациента в базе");
    fio_fnd.setBounds(300, 10, 178, 20);


    JLabel in_doc_table = new JLabel("Таблица приписанного населения:", SwingConstants.RIGHT);
    in_doc_table.setBounds(560, 10, 260, 20);

    Box box0 = Box.createVerticalBox();
    box0.setBounds(300, 40, 682, 400);

    JDBCAdapter adapterf=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
    JTable FTable=new JTable(adapterf);
//		adapter.executeQuery("select data_reg,fio,num_kart,data_in_doc,doc_name from TALON");
    adapterf.executeQuery("select count(*) from public.people;");
    JLabel rec_in_table = new JLabel("Количество пациентов в базе="+FTable.getValueAt(0, 0).toString(), SwingConstants.RIGHT);
    rec_in_table.setBounds(700, 520, 260, 20);



    SwingUtilities.invokeLater( new Runnable() {
        public void run() {
            //new Sql();


            adapterf.executeQuery("select people.\"id\", people.\"last_name\", people.\"first_name\", people.\"surname\", people.\"sex\", people.\"birthday\", people.\"finance\", people.\"card_number\", people.\"service_place\", people.\"ministerstvo\", people.\"category\", people.\"rank\" from public.people LIMIT 100;");
            FTable.removeColumn(FTable.getColumnModel().getColumn(0));
            FTable.removeColumn(FTable.getColumnModel().getColumn(3));
            FTable.removeColumn(FTable.getColumnModel().getColumn(4));
            FTable.removeColumn(FTable.getColumnModel().getColumn(6));
            FTable.removeColumn(FTable.getColumnModel().getColumn(6));
            FTable.removeColumn(FTable.getColumnModel().getColumn(5));
            FTable.removeColumn(FTable.getColumnModel().getColumn(5));
            //FTable.setDefaultEditor(String.class, new TEditor(adapterf));
            for (int i=0; i< FTable.getRowCount(); i++ ){
                try {
                    adapterf.setValueAt((new SimpleDateFormat("dd.MM.yyyy").format(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(FTable.getValueAt(i, 3).toString().trim()))), i, 5);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
    });


    JScrollPane scrollPane = new JScrollPane(FTable);
    FTable.setFillsViewportHeight(true);
    box0.add(scrollPane);

    final JButton Upd_Tab = new JButton("Обновление таблицы");
    Upd_Tab.setBounds(300, 460, 180, 30);

    final JButton Fnd_Chl = new JButton("Найти");
    Fnd_Chl.setBounds(440, 8, 80, 26);

    panel1.add(fio);
    panel1.add(fio_fnd);
    panel1.add(fio_str);

    panel1.add(name_ch);
    panel1.add(name_ch_str);

    panel1.add(num_card);
    panel1.add(num_card_str);
    panel1.add(in_doc_table);
    panel1.add(rec_in_table);

    panel1.add(box0);
    panel1.add(Upd_Tab);
    panel1.add(Fnd_Chl);
    
    JFrame frame = new JFrame("Поиск пациента в базе.");
    
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    
    frame.getContentPane().add(panel1);
   
    frame.pack();
    frame.setSize(1000, 600);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

//---------------------------------------------
    FTable.addMouseListener(new MouseListener() {
        	               public void mouseClicked(MouseEvent event) {
        	               }
        	               public void mouseEntered(MouseEvent event) {
        	               }
        	               public void mouseExited(MouseEvent event) {
        	               }
        	        
        	               public void mousePressed(MouseEvent event)
        	               {
        	        	if (event.getButton() == MouseEvent.BUTTON3)
        	        	{
        	        		
        	        		int n = JOptionPane.showConfirmDialog(
        	        				new JFrame(),
        	        			    "Выписать пациенту дополнительный талон к вам на прием?",
        	        			    "Дополнительный талон",
        	        			    JOptionPane.YES_NO_OPTION);
        	        		
        	        		
        	        		
        	        		//int column = TWTable.columnAtPoint(event.getPoint());
        	        		int row = FTable.rowAtPoint(event.getPoint());
        	        		//System.out.println("column="+column +", row=" + row + ", n =" + n);
        	        		// delete record from table TWTable
        	        		int idRec= Integer.parseInt(adapterf.rows.get(row).toString().substring(1,adapterf.rows.get(row).toString().length()-1).split(",")[0]);
        	        		//System.out.println("idRec = " +idRec);
        	        		if (n == 0)
        	        		{
        	        			JDBCAdapter adapteri=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
        	        			//JTable SHTable=new JTable(adapter_sh);
        	        			adapteri.executeQueryIns("INSERT INTO public.time_work (id_doc, room, reserv, start_time, print, id_people) VALUES" +
				        				"('"+new String().valueOf(index.id_doc)+
				        				"','NA','FALSE','"+(new java.text.SimpleDateFormat("yyyy-MM-dd")).format(java.util.Calendar.getInstance().getTime())+" 00:00"+"','"+
				        				"TRUE','"+ new String().valueOf(idRec) +"');"
				        				);
        	        			
        	        			//adapter_tw.executeQueryIns("DELETE FROM time_work WHERE id='"+idRec+"';");
        	        			//adapter_tw.executeQuery("select id, room, reserv, start_time from time_work where id_doc="+ DTable.getValueAt(comboBox.getSelectedIndex(), 0).toString()+" and start_time like '"+(new SimpleDateFormat("dd.MM.yyyy").format(calendar_t.ddd))+"%' ORDER BY start_time ASC;");
        	            		//TWTable.removeColumn(TWTable.getColumnModel().getColumn(0));
        	        			try {
									adapteri.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
        	        		}
        	        	}
       	        }
        	               public void mouseReleased(MouseEvent event) {
        	               }
        	          });
//--------------------------------------------------------------
    Fnd_Chl.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            try{
                SwingUtilities.invokeLater( new Runnable() {
                    public void run() {
                        //new Sql();
                        //System.out.println("%"+fio_str.getText().replaceAll(" ", "")+"%;");
                        adapterf.executeQuery("select people.\"id\", people.\"last_name\", people.\"first_name\", people.\"surname\", people.\"sex\", people.\"birthday\", people.\"finance\", people.\"card_number\", people.\"service_place\", people.\"ministerstvo\", people.\"category\", people.\"rank\" from public.people where people.\"last_name\" like '%"+fio_str.getText().replaceAll(" ", "")+"%' and people.\"first_name\" like '%"+name_ch_str.getText().trim()+"%' and people.\"card_number\" like '%" +num_card_str.getText().trim()+"%' ORDER BY people.\"last_name\", people.\"first_name\", people.\"surname\" ASC;");
//System.out.println("select people.\"id\", people.\"last_name\", people.\"first_name\", people.\"surname\", people.\"sex\", people.\"birthday\", people.\"finance\", people.\"card_number\", people.\"service_place\", people.\"ministerstvo\", people.\"category\", people.\"rank\" from public.people where people.\"last_name\" like '%"+fio_str.getText().replaceAll(" ", "")+"%' and people.\"first_name\" like '%"+name_ch_str.getText().trim()+"%' and people.\"card_number\" like '%" +num_card_str.getText().trim()+"%' ORDER BY people.\"last_name\", people.\"first_name\", people.\"surname\" ASC;");
                        //                                              adapter.executeQuery("select id, last_name, first_name, surname, sex, birthday, finance, card_number, service_place, ministerstvo, category, rank from people where last_name like '%"+fio_str.getText().replaceAll(" ", "")+"%' ORDER BY last_name, first_name, surname ASC;");
                        //data_reg,fio,num_kart,data_in_doc,doc_name

//		        		System.out.println("Stream get data from TALON!");
                        //?????? ??????? ? ID
//		        		Table.removeColumn(Table.getColumnModel().getColumn(0));
                        //?????? ??????? ? ID
                        FTable.removeColumn(FTable.getColumnModel().getColumn(0));
                        FTable.removeColumn(FTable.getColumnModel().getColumn(3));
                        FTable.removeColumn(FTable.getColumnModel().getColumn(4));
                        FTable.removeColumn(FTable.getColumnModel().getColumn(6));
                        FTable.removeColumn(FTable.getColumnModel().getColumn(6));
                        FTable.removeColumn(FTable.getColumnModel().getColumn(5));
                        FTable.removeColumn(FTable.getColumnModel().getColumn(5));
                        for (int i=0; i< FTable.getRowCount(); i++ ){
                            try {
                                adapterf.setValueAt((new SimpleDateFormat("dd.MM.yyyy").format(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(FTable.getValueAt(i, 3).toString().trim()))), i, 5);
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }


//Table.getColumnModel().getColumn(0).setMaxWidth(0);
                    }
                });
//-------------------
		        frame.addWindowListener(new WindowListener() {
		            // ...
		            public void windowClosing(WindowEvent event) {
		                // [ТУТ ТВОИ ДЕЙСТВИЯ ПО ЗАКРЫТИЮ]
						try {
							adapterf.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							frame.dispose();
						} catch (NullPointerException e)
						{
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

//-------------------------------------------------                
            }
            catch (Exception e1){
                e1.printStackTrace();
                System.out.println("Error ");
            }
        }
    });
///-------------------------------------------    
}
}
