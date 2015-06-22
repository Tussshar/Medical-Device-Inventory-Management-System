/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.AccountSpecialistWorkArea;

import Business.Device;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.SupplierEnterprise;
import Business.Network.Network;
import Business.Order;
import Business.OrderItem;
import Business.Organization.Organization;
import Business.Organization.SalesOrganization;
import Business.Organization.SupplierOrganization;
import Business.Person.Person;
import Business.Person.SalesSpecialist;
import Business.Person.Supplier;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.SalesSpecialistWorkRequest;
import Business.WorkQueue.WorkRequest;
import UserInterface.InventorySpecialistWorkArea.ViewRequestJPanel;
import java.awt.CardLayout;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
//import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
//import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author Tushar
 */
public class AccountSpecialistWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form AccountSpecialistWorkAreaJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Organization organization;
    private EcoSystem ecoSystem;
    private Order order;
    private Device device;
    private Supplier supplier;
    private ArrayList<String> uIDList;
    
    public AccountSpecialistWorkAreaJPanel(JPanel userProcessContainer, UserAccount userAccount, Organization organization, EcoSystem ecoSystem) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.organization = organization;
        this.ecoSystem = ecoSystem;
        
        populatePendingWorkRequestTable();
        populateWorkRequestHistoryTable();
    }
    /*
    private void generateInvoice(SalesSpecialistWorkRequest salesRequest) {
        
        order = salesRequest.getOrder();
        XWPFDocument document = new XWPFDocument();
        
        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun run1 = paragraph1.createRun();
        
        run1.setText("Invoice Report");
        run1.setBold(true);
        run1.setUnderline(UnderlinePatterns.DOUBLE);
        run1.setFontSize(30);
        paragraph1.setAlignment(ParagraphAlignment.CENTER);
        
        XWPFParagraph paragraph2 = document.createParagraph();
        XWPFRun run2 = paragraph2.createRun();
        int count = 0;
        for (OrderItem orderItem : order.getOrderItem()) {
            run2.addBreak();
            run2.addBreak();
            count++;
            run2.setText("OrderItem " + count);
            run2.addBreak();
            Device device = orderItem.getDevice();
            run2.setText("Device Name : " + device.getDeviceName());
            run2.addBreak();
            run2.setText("Device Type : " + device.getDeviceType());
            run2.addBreak();
            run2.setText("Company Name : " + device.getCompanyName());
            run2.addBreak();
            run2.setText("Unit Price : " + device.getUnitPrice());
            run2.addBreak();
            run2.setText("Country of Origin : " + device.getCountryOfOrigin());
            run2.addBreak();
            run2.setText("Manufactured Date : " + device.getManufacuredDate());
            run2.addBreak();
            run2.setText("Quantity : " + orderItem.getQuantity());
            run2.addBreak();
            run2.setText("The unique identifiers for " + orderItem.getQuantity() + " device are listed below");
            run2.addBreak();
            for (String uID : orderItem.getuIDList()) {
                run2.setText(uID);
                run2.addBreak();
            }
        }
//        paragraph2.setAlignment(ParagraphAlignment.LEFT);
        
        try {

            //Next 3 lines are to create a word file
            FileOutputStream output = new FileOutputStream("Invoice_Report_" + new Date() + ".docx");
            document.write(output);

            //XWPFWordExtractor extract = new XWPFWordExtractor(document);
            output.close();
            
            Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + "C:\\Users\\Tushar\\Google Drive\\NetBeansProjects\\Word\\\"Invoice_Report_" + new Date() + ".docx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
    
    private void populatePendingWorkRequestTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblPendingWorkRequest.getModel();
        defaultTableModel.setRowCount(0);
        SalesSpecialistWorkRequest salesRequest;
        
        for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
            salesRequest = (SalesSpecialistWorkRequest) request;
            if (salesRequest.getStatus().equals(WorkRequest.TypeOfStatus.InProgress.getValue()) && (salesRequest.getPaidAmount() != salesRequest.getInvoiceAmount())) {
                
                Object[] row = new Object[4];
                row[0] = salesRequest;
                row[1] = salesRequest.getOrder().getOrderID();
                row[2] = salesRequest.getInvoiceAmount();
                row[3] = salesRequest.getRequestDate();
                
                defaultTableModel.addRow(row);
            }
        }
    }
    
    private void populateWorkRequestHistoryTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblWorkRequestHistory.getModel();
        defaultTableModel.setRowCount(0);
        SalesSpecialistWorkRequest salesRequest;
        
        for (WorkRequest request : userAccount.getWorkQueue().getWorkRequestList()) {
            
            salesRequest = (SalesSpecialistWorkRequest) request;
            
            Object[] row = new Object[5];
            row[0] = salesRequest;
            row[1] = salesRequest.getOrder().getOrderID();
            row[2] = salesRequest.getInvoiceAmount();
            row[3] = salesRequest.getRequestDate();
            row[4] = salesRequest.getStatus();
            defaultTableModel.addRow(row);
        }
    }
    
    private void resetavailability(SalesSpecialistWorkRequest salesRequest) {
        
        int availability;
        for (OrderItem orderItem : salesRequest.getOrder().getOrderItem()) {
            device = orderItem.getDevice();
            availability = device.getAvailability() + orderItem.getQuantity();
            uIDList = orderItem.getuIDList();
            supplier = salesRequest.getSupplier();
            
            for (Device dev : supplier.getDeviceCatalog().getDeviceList()) {
                if (dev.getDeviceName().equalsIgnoreCase(device.getDeviceName())) {
                    device.setAvailability(availability);
                    for (String uID : uIDList) {
                        if (dev.getUniqueDeviceIdentifier().equalsIgnoreCase(uID)) {
                            dev.setInStock("YES");
                        }
                    }
                }
            }
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblWorkRequestHistory = new javax.swing.JTable();
        lblSalesSpecialistWorkArea = new javax.swing.JLabel();
        btnViewRequest = new javax.swing.JButton();
        lblMyWorkRequestHistory = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        lblOrganizationsPendingWorkRequest = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        btnViewRequestFromHistory = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPendingWorkRequest = new javax.swing.JTable();
        btnPayment = new javax.swing.JButton();
        btnCancelOrder = new javax.swing.JButton();

        tblWorkRequestHistory.setBackground(new java.awt.Color(255, 153, 0));
        tblWorkRequestHistory.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblWorkRequestHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestID", "OrderID", "Invoice Amount", "Request Date", "Result"
            }
        ));
        jScrollPane2.setViewportView(tblWorkRequestHistory);

        jScrollPane4.setViewportView(jScrollPane2);

        lblSalesSpecialistWorkArea.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSalesSpecialistWorkArea.setForeground(new java.awt.Color(102, 204, 255));
        lblSalesSpecialistWorkArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSalesSpecialistWorkArea.setText("Account Specialist Work Area");

        btnViewRequest.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnViewRequest.setText("View Request");
        btnViewRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewRequestActionPerformed(evt);
            }
        });

        lblMyWorkRequestHistory.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMyWorkRequestHistory.setForeground(new java.awt.Color(102, 204, 255));
        lblMyWorkRequestHistory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMyWorkRequestHistory.setText("My Work Request History");

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblOrganizationsPendingWorkRequest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblOrganizationsPendingWorkRequest.setForeground(new java.awt.Color(102, 204, 255));
        lblOrganizationsPendingWorkRequest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrganizationsPendingWorkRequest.setText("Organization's Pending Work Request");

        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnViewRequestFromHistory.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnViewRequestFromHistory.setText("View Request");
        btnViewRequestFromHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewRequestFromHistoryActionPerformed(evt);
            }
        });

        tblPendingWorkRequest.setBackground(new java.awt.Color(255, 153, 0));
        tblPendingWorkRequest.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblPendingWorkRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestID", "OrderID", "Invoice Amount", "Request Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPendingWorkRequest);

        jScrollPane3.setViewportView(jScrollPane1);

        btnPayment.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPayment.setText("Payment");
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });

        btnCancelOrder.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelOrder.setText("Cancel Order");
        btnCancelOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBack)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(293, 293, 293)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblSalesSpecialistWorkArea)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblOrganizationsPendingWorkRequest)
                                                .addGap(182, 182, 182)
                                                .addComponent(btnRefresh)))))
                                .addGap(22, 22, 22))
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane4))
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnViewRequestFromHistory)
                        .addGap(427, 427, 427))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMyWorkRequestHistory)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnViewRequest)
                                .addGap(39, 39, 39)
                                .addComponent(btnPayment)
                                .addGap(39, 39, 39)))
                        .addGap(7, 7, 7)
                        .addComponent(btnCancelOrder)
                        .addGap(271, 271, 271))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSalesSpecialistWorkArea)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrganizationsPendingWorkRequest)
                    .addComponent(btnRefresh))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewRequest)
                    .addComponent(btnPayment)
                    .addComponent(btnCancelOrder))
                .addGap(18, 18, 18)
                .addComponent(lblMyWorkRequestHistory)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnViewRequestFromHistory)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewRequestActionPerformed
        
        int selectedRow = tblPendingWorkRequest.getSelectedRow();
        if (selectedRow >= 0) {
            WorkRequest workRequest = (WorkRequest) tblPendingWorkRequest.getValueAt(selectedRow, 0);
            SalesSpecialistWorkRequest salesSpecialistWorkRequest = (SalesSpecialistWorkRequest) workRequest;
            ViewRequestJPanel vojp = new ViewRequestJPanel(userProcessContainer, salesSpecialistWorkRequest);
            userProcessContainer.add("View Order JPanel", vojp);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_btnViewRequestActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnViewRequestFromHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewRequestFromHistoryActionPerformed
        int selectedRow = tblPendingWorkRequest.getSelectedRow();
        if (selectedRow >= 0) {
            WorkRequest workRequest = (WorkRequest) tblPendingWorkRequest.getValueAt(selectedRow, 0);
            SalesSpecialistWorkRequest salesSpecialistWorkRequest = (SalesSpecialistWorkRequest) workRequest;
            ViewRequestJPanel vojp = new ViewRequestJPanel(userProcessContainer, salesSpecialistWorkRequest);
            userProcessContainer.add("View Order JPanel", vojp);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_btnViewRequestFromHistoryActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        int selectedRow = tblPendingWorkRequest.getSelectedRow();
        
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
            
            WorkRequest request = (WorkRequest) tblPendingWorkRequest.getValueAt(selectedRow, 0);
            SalesSpecialistWorkRequest salesRequest = (SalesSpecialistWorkRequest) request;
            Order order = salesRequest.getOrder();
            supplier = salesRequest.getSupplier();
            
            if (salesRequest.getStatus().equals(WorkRequest.TypeOfStatus.InProgress.getValue())) {
                salesRequest.setPaidAmount(salesRequest.getInvoiceAmount());
                salesRequest.setAccountSpecialsit(userAccount);
                userAccount.getWorkQueue().getWorkRequestList().add(salesRequest);
                
                for (Network network : ecoSystem.getNetworkList()) {
                    for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                        if (enterprise instanceof SupplierEnterprise) {
                            for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                                if (organization instanceof SupplierOrganization) {
                                    for (Person person : organization.getPersonDirectory().getPersonList()) {
                                        Supplier sup = (Supplier) person;
                                        if (supplier.equals(sup)) {
                                            for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                                                if (org instanceof SalesOrganization) {
                                                    for (UserAccount ua : org.getUserAccountDirectory().getUserAccountList()) {
                                                        for (WorkRequest workRequest : ua.getWorkQueue().getWorkRequestList()) {
                                                            SalesSpecialistWorkRequest sswr = (SalesSpecialistWorkRequest) workRequest;
                                                            if (sswr.equals(salesRequest)) {
                                                                sswr.setPaidAmount(salesRequest.getInvoiceAmount());
                                                                sswr.setAccountSpecialsit(userAccount);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        populatePendingWorkRequestTable();
        populateWorkRequestHistoryTable();
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        populatePendingWorkRequestTable();
        populateWorkRequestHistoryTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnCancelOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelOrderActionPerformed
        int selectedRow = tblPendingWorkRequest.getSelectedRow();
        
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
            WorkRequest request = (WorkRequest) tblPendingWorkRequest.getValueAt(selectedRow, 0);
            SalesSpecialistWorkRequest salesRequest = (SalesSpecialistWorkRequest) request;
            if (salesRequest.getStatus().equals(WorkRequest.TypeOfStatus.InProgress.getValue())) {
                salesRequest.setAccountSpecialsit(userAccount);
                salesRequest.setResolveDate(new Date());
                salesRequest.setStatus(WorkRequest.TypeOfStatus.Completed.getValue());
                salesRequest.setTestResults(WorkRequest.TypeOfResult.Reject.getValue());
                userAccount.getWorkQueue().getWorkRequestList().add(salesRequest);
                resetavailability(salesRequest);
            }
            
        }
        populatePendingWorkRequestTable();
        populateWorkRequestHistoryTable();
    }//GEN-LAST:event_btnCancelOrderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancelOrder;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnViewRequest;
    private javax.swing.JButton btnViewRequestFromHistory;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblMyWorkRequestHistory;
    private javax.swing.JLabel lblOrganizationsPendingWorkRequest;
    private javax.swing.JLabel lblSalesSpecialistWorkArea;
    private javax.swing.JTable tblPendingWorkRequest;
    private javax.swing.JTable tblWorkRequestHistory;
    // End of variables declaration//GEN-END:variables
}
