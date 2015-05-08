package appl;

import java.util.Vector;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

	class CBM extends AbstractListModel implements MutableComboBoxModel {
        private Vector rl;
        private Object selected=null;

        public CBM() {
           rl=new Vector();
        }

        
        public void setRubricList(String[] rl, int count) {
//            System.out.println("setList...");
            this.rl.removeAllElements();
            for(int r=0;r<count;r++) {
               // System.out.println("Rubric: "+r+" rl="+rl[r]);
                this.rl.add(rl[r]);
            }
            fireIntervalRemoved(this, 0, getSize());
            fireContentsChanged(this, 0, getSize());
            fireIntervalRemoved(this, 0, getSize());
        }


        public int getSize() {
            return rl.size();
        }

        public Object getElementAt(int index) {
            return rl.get(index);
        }

        public void setSelectedItem(Object anItem) {
            selected = anItem;
        }

        public Object getSelectedItem() {
            return selected;
        }

        public void addElement(Object obj) {
            rl.add(obj);
        }

        public void removeElement(Object obj) {
            rl.removeElement(obj);
        }

        public void insertElementAt(Object obj, int index) {
            addElement(obj);
        }

        public void removeElementAt(int index) {
            rl.removeElementAt(index);
        }
    }
