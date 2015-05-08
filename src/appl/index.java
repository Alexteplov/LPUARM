package appl;

import javax.swing.SwingUtilities;

public class index {
	public static int id_doc;
	public static String connectDN="org.postgresql.Driver";
	public static String connectDP="jdbc:postgresql://192.168.1.2:5432/msch";
	//public static String connectDP="jdbc:postgresql://127.0.0.1:5432/msch";
	public static String connectLogin="postgres";
	public static String connectPassword="alvbrisko";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new indexForm();
		new changeDoc();
    	System.out.println(index.id_doc);
    	
//    	if (index.id_doc >0 ) System.out.println("Hello");
/*    	
		 SwingUtilities.invokeLater( new Runnable() {
	            public void run() {                
	            	//Create Form
	            	
	            	//System.out.println("Stream1 FORM!");
	            }
	        });
*/	
	}



}
