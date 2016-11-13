/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TableView.java
 *
 * Created on Jul 30, 2009, 1:23:06 AM
 */

package businessmanager.Common;
import datavirtue.*;
import javax.swing.table.*;

/**
 *
 * @author Data Virtue
 */
public class TableView extends javax.swing.JDialog {

    /** Creates new form TableView */
    public TableView(java.awt.Frame parent, boolean modal,
            DbEngine dbe, String table, int col, String message, int [] rowsToRemove) {
        super(parent, modal);
        initComponents();

        messageField.setText(message);
        valueCol = col;
        db = dbe;
        getTableModel(table);
        setView(rowsToRemove);
        this.setVisible(true);

    }

    public TableView(java.awt.Frame parent, boolean modal, DefaultTableModel tm) {
        super(parent, modal);
        initComponents();

        jTable1.setModel(tm);
        this.setVisible(true);

    }


    public void setView (int [] cols){
        if (cols == null) return;
        if (jTable1.getModel().getRowCount() > 0){
        TableColumnModel cm = jTable1.getColumnModel();
        TableColumn tc;
     
            for (int i =0; i < cols.length; i++){

              tc = cm.getColumn(cols[i]);
             jTable1.removeColumn(tc);

            }
        }
    }

    private void getTableModel(String table) {

        jTable1.setModel(db.createTableModel(table, jTable1));

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cancelButton = new javax.swing.JButton();
        selectButton = new javax.swing.JButton();
        messageField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Table View");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
        );

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/businessmanager/res/cancel.gif"))); // NOI18N
        cancelButton.setText("Close");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        selectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/businessmanager/res/ok.gif"))); // NOI18N
        selectButton.setText("Select");
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectButtonActionPerformed(evt);
            }
        });

        messageField.setEditable(false);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(cancelButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(selectButton))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, messageField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(messageField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(selectButton)
                    .add(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonActionPerformed

        int r = jTable1.getSelectedRow();

        value = jTable1.getModel().getValueAt(r, valueCol);  //get Key to return
        this.setVisible(false);

    }//GEN-LAST:event_selectButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        value = null;
        this.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    public Object getReturnValue() {

        return value;

    }

    public void kill() {

        this.dispose();
    }

   private Object value = null;
   private int valueCol = 0;
   private DbEngine db;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField messageField;
    private javax.swing.JButton selectButton;
    // End of variables declaration//GEN-END:variables

}
