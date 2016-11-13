/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PaymentActivityDialog.java
 *
 * Created on Mar 25, 2011, 10:33:51 PM
 */

package businessmanager.InvoiceQuote;


import RuntimeManagement.GlobalApplicationDaemon;
import RuntimeManagement.KeyCard;
import businessmanager.Reports.ReportFactory;
import datavirtue.DV;
import datavirtue.DbEngine;
import datavirtue.FractionCellRenderer;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author dataVirtue
 */
public class PaymentActivityDialog extends javax.swing.JDialog {
    private KeyCard accessKey;
    private boolean debug = false;
    private GlobalApplicationDaemon application;
    private Image winIcon;
    private Invoice invoice;
    private String nl = System.getProperty("line.separator");

    /** Creates new form PaymentActivityDialog */
    public PaymentActivityDialog(java.awt.Frame parent, boolean modal, GlobalApplicationDaemon application, Invoice i) {
        super(parent, modal);
        Toolkit tools = Toolkit.getDefaultToolkit();
        winIcon = tools.getImage(getClass().getResource("/businessmanager/res/Orange.png"));
        initComponents();
        java.awt.Dimension dim = DV.computeCenter((java.awt.Window) this);
        this.setLocation(dim.width, dim.height);
        accessKey = application.getKey_card();
        this.application = application;
        this.invoice = i;
        this.setTitle("Payment Activity for Invoice Number: "+invoice.getInvoiceNumber());
        this.setPayments();
        //this.setView();
        this.setVisible(true);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        paymentTable = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();
        toolBar = new javax.swing.JToolBar();
        payButton = new javax.swing.JButton();
        returnButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Payment Activity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        paymentTable.setDefaultRenderer(java.lang.Float.class,  new FractionCellRenderer (10, 2, javax.swing.SwingConstants.RIGHT));

        paymentTable.setFont(new java.awt.Font("Tahoma", 0, 14));
        paymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "DATE", "TYPE", "REF", "AMOUNT", "BALANCE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        paymentTable.setToolTipText("Invoice Activity");
        paymentTable.setSelectionBackground(new java.awt.Color(204, 255, 204));
        paymentTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        paymentTable.setDefaultRenderer(java.lang.Float.class,  new FractionCellRenderer (10, 2, SwingConstants.RIGHT));
        jScrollPane3.setViewportView(paymentTable);

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/businessmanager/res/Aha-16/enabled/Delete.png"))); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.setToolTipText("Deletes entries from Payment Activity.");
        deleteButton.setMargin(new java.awt.Insets(2, 10, 2, 10));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addContainerGap())
        );

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        payButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/businessmanager/res/Aha-24/enabled/Payment.png"))); // NOI18N
        payButton.setText("Payments");
        payButton.setToolTipText("Take a Payment, Record a Credit or Add Fees to the Selected Invoice");
        payButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        payButton.setMargin(new java.awt.Insets(2, 6, 2, 6));
        payButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        payButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payButtonActionPerformed(evt);
            }
        });
        toolBar.add(payButton);

        returnButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/businessmanager/res/Aha-24/enabled/Undo v2.png"))); // NOI18N
        returnButton.setText("Returns");
        returnButton.setToolTipText("Process product returns from an invoice");
        returnButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        returnButton.setMargin(new java.awt.Insets(2, 6, 2, 6));
        returnButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });
        toolBar.add(returnButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toolBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed

        deletePayment();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void payButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payButtonActionPerformed

        takePayment();
    }//GEN-LAST:event_payButtonActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed

        if (!accessKey.checkManager(500)){
            accessKey.showMessage("Returns");
            return;
        }
            new ReturnDialog(null, true, invoice, application);
            this.setPayments();

    }//GEN-LAST:event_returnButtonActionPerformed

      private void setPayments () {

         paymentTable.setModel(new DefaultTableModel());
         paymentTable.setModel(invoice.getPayments());
         //System.out.println("DEGUG: 12"); //DEBUG
         if (paymentTable.getColumnModel().getColumnCount() == 8){
             setView();
         }
          
    }

private void setView(){
      /* Nasty bug unless we skip col mods on no payments */
            if (paymentTable.getRowCount() <= 0) return;

            //remove cols 0 1
            TableColumnModel cm = paymentTable.getColumnModel();
            TableColumn tc;

            //setup hold table view
            tc = cm.getColumn(0);
            paymentTable.removeColumn(tc);//remove key column
            tc = cm.getColumn(0);
            paymentTable.removeColumn(tc);//remove inv # column

            if (paymentTable.getRowCount() > 0){

                paymentTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
                int [] widths = new int [] {85, 60, 140, 50, 50, 50};

            for (int i = 0; i < widths.length; i++){

                 tc = paymentTable.getColumnModel().getColumn(i);
                    tc.setPreferredWidth(widths[i]);
            }
        }
}

    private void deletePayment(){

        int row = paymentTable.getSelectedRow();

        if (row < 0) return;

        int paymentKey = (Integer)paymentTable.getModel().getValueAt(row, 0);
        String paymentType = (String)paymentTable.getModel().getValueAt(row, 3);

        if (paymentType.toLowerCase().equals("return")){

            int a = javax.swing.JOptionPane.showConfirmDialog(null,
                    "Deleting a payment entry generated by a product Return will NOT reverse the return."+nl+
                    "The products will still show as being returned on this invoice."+ nl +
                    "This action is not recommended.  Do you still want to delete it?",
                    "(Return) Credit Delete",  JOptionPane.YES_NO_OPTION);
            if (a == 0){
            }else return;
        }

        if (paymentType.toLowerCase().equals("fee")){

            int a = javax.swing.JOptionPane.showConfirmDialog(null,
                    "The best way to reverse a fee is to issue a credit."+nl+
                    "Do you still want to delete it?","Fee Debit Delete",  JOptionPane.YES_NO_OPTION);
            if (a == 0){
            }else return;
        }

        /* Fall-through action */
        String iValue = javax.swing.JOptionPane.showInputDialog("Type DELETE to continue.");
        if (iValue != null && iValue.equalsIgnoreCase("delete")){
            application.getDb().removeRecord("payments", paymentKey);

        }

        /* Get an Invoice instance for this invoice and check balance */
        /* if the balance is over 0.00 mark unpaid, save and refresh tables */

        float balance = invoice.getInvoiceDueNow();

        if (balance > 0) {
            boolean prevPdStatus = invoice.isPaid();
            invoice.setPaid(false);
            invoice.saveInvoice();
          
            if (prevPdStatus){
            javax.swing.JOptionPane.showMessageDialog(null,
                    "The invoice now shows a balance due of " + DV.money(balance)+nl+
                    "The status of the invoice has been changed to unpaid.");
            }
            return;
        }

        if (balance < 0) {

            invoice.setPaid(false);
            invoice.saveInvoice();
            
            javax.swing.JOptionPane.showMessageDialog(null,
                    "The invoice now has a negative balance,"+nl+
                    "showing that the customer has overpaid."+nl+
                    "Its status has been changed to unpaid so that"+nl+
                    " you can reconcile the invoice by issuing a refund.");

            return;
        }

        setPayments();

    }

    private void takePayment() {

       
            if (invoice.isPaid()) {

                javax.swing.JOptionPane.showMessageDialog(this, "Invoice number " + invoice.getInvoiceNumber() + " is marked as paid.");

            }
            else{


                PaymentDialog pd = new PaymentDialog (null, true, invoice.getInvoiceKey(), application);
                pd.setVisible(true);
                
            }
            this.setPayments();
    }

    private void closeInvoice() {
        
        if (invoice.isPaid() || invoice.isVoid()){

          javax.swing.JOptionPane.showMessageDialog(null, "You cannot close an invoice which is already PAID or VOID.");
            return;

        }

        //get typed verification
        //pay out the invoice and record a refund against it
        //refund == total cost of all items minus the amount that has been paid already
        String iValue = JOptionPane.showInputDialog("To \"write off\" this invoice type CLOSE and click OK.");

        if (iValue != null && iValue.trim().equalsIgnoreCase("close")){

            invoice.setPaid(true);
            invoice.saveInvoice();

        }
        this.setPayments();

    }

    private void voidAction() {


            if (!accessKey.checkManager(500)){
                accessKey.showMessage("Void");
                return;
            }

            //int a = JOptionPane.showConfirmDialog(this, "Sure you want to VOID the selected invoice?" + System.getProperty("line.separator") +"VOID is Permanent!","V O I D",  JOptionPane.YES_NO_OPTION);
            String iValue = JOptionPane.showInputDialog("To void this invoice type VOID and click OK.");

            if (iValue != null && iValue.equalsIgnoreCase("void")){

                DbEngine db = application.getDb();

                invoice.setVoid(true);

                invoice.saveInvoice();
                /* Blast sales and payments for this invoice */

                String inum = invoice.getInvoiceNumber();
                String invoice_key = invoice.getInvoiceNumber();

                /* Kill invoice payments */
                ArrayList al = db.search("payments", 1, inum, false);

                if (al != null){

                    for (int i = 0; i < al.size(); i++){

                        db.removeRecord("payments", (Integer) al.get(i));
                    }
                }

                /* Kill invoice items */
                //can we speed this up by using a number search routine?
                al = db.search("invitems", 1, invoice_key, false);
                Object [] rec;
                String desc;
                String type;
                float qty;

                ArrayList temp;
                if (al != null){

                    for (int i = 0; i < al.size(); i++){
                        rec = db.getRecord("invitems", (Integer) al.get(i));
                        desc = (String) rec[5];  //desc
                        type = (String) rec[4];  //code
                        qty = (Float) rec[3]; //qty

                        temp = db.search("inventory", 3, desc , false);

                        if (temp != null) {
                            rec = db.getRecord("inventory", (Integer)temp.get(0));


                            if (type.equalsIgnoreCase("RETURN")){
                                rec[6] = (Float) rec[6] + (qty * -1);
                            }else {
                                rec[6] = (Float) rec[6] + qty;
                            }
                            db.removeRecord("invitems", (Integer)al.get(i));
                            db.saveRecord("inventory", rec, false);

                        }
                    }
                }

                JOptionPane.showMessageDialog(null, "Invoice was VOIDED.");

            }else {

                JOptionPane.showMessageDialog(null, "Invoice was NOT voided.");

            }
            this.setPayments();

    }

private void doHistoryReport(){
    if (!accessKey.checkReports(500)){
            accessKey.showMessage("Customer/Supplier Reports");
            return;
        }
       ReportFactory.generateCustomerStatement(application, invoice.getInvoiceKey());

}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton payButton;
    private javax.swing.JTable paymentTable;
    private javax.swing.JButton returnButton;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

}
