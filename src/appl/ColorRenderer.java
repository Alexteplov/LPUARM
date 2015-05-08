package appl;
 
/* 
 * ColorRenderer.java (compiles with releases 1.2, 1.3, and 1.4) is used by 
 * TableDialogEditDemo.java.
 */
 
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import com.javaswingcomponents.calendar.plaf.Cell;

import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import sql.JDBCAdapter;
import time.DatePickerCodeExample;
 
public class ColorRenderer extends DefaultTableCellRenderer{
   
 
    public ColorRenderer() {
       // setOpaque(true); //MUST do this for background to show up.
    }
 
    public Component getTableCellRendererComponent(
                            JTable table, Object value,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {

		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
// проверяем на то, что данные внесены т.е. появились в другой таблице
		JDBCAdapter adapter=new JDBCAdapter(index.connectDN,index.connectDP,index.connectLogin,index.connectPassword);
		JTable Table=new JTable(adapter);
		adapter.executeQuery("select time_work.\"id_people\", time_work.\"id\", time_work.\"start_time\" from public.time_work where time_work.\"id_doc\" = '"+index.id_doc+"' and time_work.\"start_time\"::text like '"+(new SimpleDateFormat("yyyy-MM-dd").format(Form.calendar_t.ddd))+"%' and time_work.\"print\"='TRUE'  ORDER BY time_work.\"start_time\" ASC;");
			//String[] PD =  Form.adapter.rows.get(row).toString().substring(1,Form.adapter.rows.get(row).toString().length()-1).split(",");
			adapter.executeQuery("select stattalon.\"id_people\" from public.stattalon where stattalon.\"id_people\"='"+Table.getValueAt(row, 0).toString().trim()+"' and stattalon.\"id_doctor\" = '"+index.id_doc+"' and stattalon.\"created_at\"::text  like '"+(new SimpleDateFormat("yyyy-MM-dd").format(Form.calendar_t.ddd))+"%';");
//			System.out.println("select stattalon.\"id_people\" from public.stattalon where stattalon.\"id_people\"='"+PD[0].trim()+"' and stattalon.\"id_doctor\" = '"+index.id_doc+"' and stattalon.\"created_at\" = '"+(new SimpleDateFormat("yyyy-MM-dd").format(Form.calendar_t.ddd))+"%';");
			// если такой чел есть, то помечаем его зеленым
			try{
				//System.out.println(adapter.rows.get(0).toString().substring(1,adapter.rows.get(0).toString().length()-1));
				if(!adapter.rows.get(0).toString().substring(1,adapter.rows.get(0).toString().length()-1).equals("")) {
				setBackground(Color.GREEN);
		        //table.repaint();
				//System.out.println("GREEN " +adapter.rows.get(0).toString().substring(1,adapter.rows.get(0).toString().length()-1));
			}else
			{
				setBackground(Color.WHITE);
				//System.out.println("WHITE " +adapter.rows.get(0).toString().substring(1,adapter.rows.get(0).toString().length()-1));
			}
				adapter.close();	
			}catch (ArrayIndexOutOfBoundsException e){
				setBackground(Color.WHITE);
				
			}
		//}
 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
        return this;
    }
}
