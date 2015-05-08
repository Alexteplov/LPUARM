package appl;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import sql.JDBCAdapter;

class TEditor extends AbstractCellEditor implements TableCellEditor, ActionListener{
	JDialog dialog;
	JButton button;
	int PData;
	JDBCAdapter vec;
	protected static final String EDIT = "edit";

	public TEditor(JDBCAdapter ad){
	//	System.out.println("Run TableEditor Cell");
		button = new JButton();
	    button.setActionCommand(EDIT);
	    button.addActionListener(this);
	    button.setBorderPainted(false);
	    vec=ad;
//        System.out.println("PData1:"+ad.rows.get(0).toString());
//	    PData = n;
    
	}	
    public void actionPerformed(ActionEvent e) {
    	if (EDIT.equals(e.getActionCommand())) {
    	//System.out.println("Edit Cell");
		
    	new statTalon(PData);
	    fireEditingStopped();
    }
    }
    

  //Implement the one CellEditor method that AbstractCellEditor doesn't.
    public Object getCellEditorValue() {
 //       System.out.println("getCellEditorValue");
    	return Color.green;
    }

    //Implement the one method defined by TableCellEditor.
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column
                                                 ) {
       
        PData= Integer.parseInt(vec.rows.get(row).toString().substring(1,vec.rows.get(row).toString().length()-1).split(",")[0]);

        return button;
    }
	
}