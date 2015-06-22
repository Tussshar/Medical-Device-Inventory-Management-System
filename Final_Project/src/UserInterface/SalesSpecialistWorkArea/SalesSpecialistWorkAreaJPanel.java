/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.SalesSpecialistWorkArea;

import Business.Device;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.WareHouseEnterprise;
import Business.Order;
import Business.OrderItem;
import Business.Organization.AccountOrganization;
import Business.Organization.Organization;
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
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author Tushar
 */
public class SalesSpecialistWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form SalesSpecialistWorkAreaJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Organization organization;
    private Order order;
    private EcoSystem ecoSystem;
    private Device device;
    private Supplier supplier;
    private ArrayList<String> uIDList;

    public SalesSpecialistWorkAreaJPanel(JPanel userProcessContainer, UserAccount userAccount, Organization organization, EcoSystem ecoSystem) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.organization = organization;
        this.ecoSystem = ecoSystem;

        populatePendingWorkRequestTable();
        populateWorkRequestHistoryTable();
    }

    private void populatePendingWorkRequestTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblPendingWorkRequest.getModel();
        defaultTableModel.setRowCount(0);
        SalesSpecialistWorkRequest salesRequest;

        for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {

            salesRequest = (SalesSpecialistWorkRequest) request;
            if (salesRequest.getStatus().equals(WorkRequest.TypeOfStatus.Pending.getValue())) {

                Object[] row = new Object[4];
                row[0] = salesRequest;
                row[1] = salesRequest.getOrder().getOrderID();
                row[2] = salesRequest.getInventorySpecialist();
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
                    dev.setAvailability(availability);
                    for (String uID : uIDList) {
                        if (dev.getUniqueDeviceIdentifier().equalsIgnoreCase(uID)) {
                            dev.setInStock("YES");
                        }
                    }
                }
            }

        }
    }

    private String getInvoiceReportName() {
        return ("Invoice_Report_" + new Date() + ".docx");
    }

    private void generateInvoice(SalesSpecialistWorkRequest salesRequest) {

        double invoiceAmount = 0;
        order = salesRequest.getOrder();
        XWPFDocument document = new XWPFDocument();

        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun run1 = paragraph1.createRun();

        run1.setText("Invoice Report");
        run1.setBold(true);
        run1.setUnderline(UnderlinePatterns.DOUBLE);
        run1.setFontSize(30);
        paragraph1.setAlignment(ParagraphAlignment.CENTER);

        for (OrderItem orderItem : salesRequest.getOrder().getOrderItem()) {
            invoiceAmount = invoiceAmount + (orderItem.getQuantity() * orderItem.getDevice().getUnitPrice());
        }

        XWPFParagraph paragraph3 = document.createParagraph();
        XWPFRun run3 = paragraph3.createRun();

        run3.setText("Invoice Amount : " + invoiceAmount);
        run3.setBold(true);
        run3.setUnderline(UnderlinePatterns.DOUBLE);
        run3.setFontSize(30);
        paragraph3.setAlignment(ParagraphAlignment.LEFT);

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
        run2.setFontSize(30);
        paragraph2.setAlignment(ParagraphAlignment.LEFT);

        try {

            //Next 3 lines are to create a word file
            FileOutputStream output = new FileOutputStream("Invoice_Report.docx");
            document.write(output);

            //XWPFWordExtractor extract = new XWPFWordExtractor(document);
            output.close();

            Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + "C:\\Users\\Tushar\\Google Drive\\NetBeansProjects\\Word\\Invoice_Report.docx");
        } catch (Exception e) {
            e.printStackTrace();
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

        lblSalesSpecialistWorkArea = new javax.swing.JLabel();
        btnViewRequest = new javax.swing.JButton();
        btnApprove = new javax.swing.JButton();
        btnReject = new javax.swing.JButton();
        lblOrganizationsPendingWorkRequest = new javax.swing.JLabel();
        lblMyWorkRequestHistory = new javax.swing.JLabel();
        btnViewRequestFromHistory = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPendingWorkRequest = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblWorkRequestHistory = new javax.swing.JTable();
        btnSendInvoice = new javax.swing.JButton();

        lblSalesSpecialistWorkArea.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSalesSpecialistWorkArea.setForeground(new java.awt.Color(102, 204, 255));
        lblSalesSpecialistWorkArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSalesSpecialistWorkArea.setText("Sales Specialist Work Area");

        btnViewRequest.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnViewRequest.setText("View Request");
        btnViewRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewRequestActionPerformed(evt);
            }
        });

        btnApprove.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnApprove.setText("Approve Request");
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });

        btnReject.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnReject.setText("Reject Request");
        btnReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectActionPerformed(evt);
            }
        });

        lblOrganizationsPendingWorkRequest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblOrganizationsPendingWorkRequest.setForeground(new java.awt.Color(102, 204, 255));
        lblOrganizationsPendingWorkRequest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrganizationsPendingWorkRequest.setText("Organization's Pending Work Request");

        lblMyWorkRequestHistory.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMyWorkRequestHistory.setForeground(new java.awt.Color(102, 204, 255));
        lblMyWorkRequestHistory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMyWorkRequestHistory.setText("My Work Request History");

        btnViewRequestFromHistory.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnViewRequestFromHistory.setText("View Request");
        btnViewRequestFromHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewRequestFromHistoryActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        tblPendingWorkRequest.setBackground(new java.awt.Color(255, 153, 0));
        tblPendingWorkRequest.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblPendingWorkRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestID", "OrderID", "Inventory Specialist", "Request Date"
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
        if (tblPendingWorkRequest.getColumnModel().getColumnCount() > 0) {
            tblPendingWorkRequest.getColumnModel().getColumn(0).setResizable(false);
            tblPendingWorkRequest.getColumnModel().getColumn(1).setResizable(false);
            tblPendingWorkRequest.getColumnModel().getColumn(2).setResizable(false);
            tblPendingWorkRequest.getColumnModel().getColumn(3).setResizable(false);
        }

        jScrollPane3.setViewportView(jScrollPane1);

        tblWorkRequestHistory.setBackground(new java.awt.Color(255, 153, 0));
        tblWorkRequestHistory.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblWorkRequestHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestID", "OrderID", "Invoice Amount", "Request Date", "Status"
            }
        ));
        jScrollPane2.setViewportView(tblWorkRequestHistory);

        jScrollPane4.setViewportView(jScrollPane2);

        btnSendInvoice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSendInvoice.setText("Send Invoice");
        btnSendInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendInvoiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(412, 412, 412)
                        .addComponent(lblOrganizationsPendingWorkRequest)
                        .addGap(185, 185, 185)
                        .addComponent(btnRefresh)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMyWorkRequestHistory)
                        .addGap(306, 306, 306)))
                .addGap(63, 63, 63))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnViewRequest)
                        .addGap(94, 94, 94)
                        .addComponent(btnSendInvoice)
                        .addGap(327, 327, 327))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnViewRequestFromHistory)
                        .addGap(18, 18, 18)
                        .addComponent(btnApprove)
                        .addGap(18, 18, 18)
                        .addComponent(btnReject)
                        .addGap(238, 238, 238))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblSalesSpecialistWorkArea)
                .addGap(340, 340, 340))
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
                    .addComponent(btnSendInvoice))
                .addGap(18, 18, 18)
                .addComponent(lblMyWorkRequestHistory)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewRequestFromHistory)
                    .addComponent(btnApprove)
                    .addComponent(btnReject))
                .addContainerGap(91, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewRequestActionPerformed

        int selectedRow = tblPendingWorkRequest.getSelectedRow();
        if (selectedRow >= 0) {
            WorkRequest workRequest = (WorkRequest) tblPendingWorkRequest.getValueAt(selectedRow, 0);
            SalesSpecialistWorkRequest salesSpecialistWorkRequest = (SalesSpecialistWorkRequest) workRequest;
            ViewRequestJPanel vojpp = new ViewRequestJPanel(userProcessContainer, salesSpecialistWorkRequest);
            userProcessContainer.add("View Order JPanel", vojpp);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_btnViewRequestActionPerformed

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        int selectedRow = tblWorkRequestHistory.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
            WorkRequest request = (WorkRequest) tblWorkRequestHistory.getValueAt(selectedRow, 0);
            SalesSpecialistWorkRequest salesRequest = (SalesSpecialistWorkRequest) request;

            if (salesRequest.getStatus().equals(WorkRequest.TypeOfStatus.InProgress.getValue()) && salesRequest.getPaidAmount() != 0 && salesRequest.getInvoiceAmount() == salesRequest.getPaidAmount()) {
                salesRequest.setTestResults(WorkRequest.TypeOfResult.Approve.getValue());
                salesRequest.setStatus(WorkRequest.TypeOfStatus.Completed.getValue());
                salesRequest.setResolveDate(new Date());
                order = salesRequest.getOrder();

                for (OrderItem orderItem : order.getOrderItem()) {
                    for (String uID : orderItem.getuIDList()) {
                        Supplier supplier = salesRequest.getSupplier();

                        for (Device device : supplier.getDeviceCatalog().getDeviceList()) {
                            if (uID.equalsIgnoreCase(device.getUniqueDeviceIdentifier())) {
                                for (Enterprise enterprise : ecoSystem.getEnterpriseList()) {
                                    if (enterprise instanceof WareHouseEnterprise) {
                                        WareHouseEnterprise wareHouseEnterprise = (WareHouseEnterprise) enterprise;
                                        device.setAvailability(0);
                                        wareHouseEnterprise.getDevList().add(device);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "The payment has not been done completely");
            }
        }
        populatePendingWorkRequestTable();
        populateWorkRequestHistoryTable();
    }//GEN-LAST:event_btnApproveActionPerformed

    private void btnRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectActionPerformed
        int selectedRow = tblWorkRequestHistory.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
            WorkRequest request = (WorkRequest) tblWorkRequestHistory.getValueAt(selectedRow, 0);
            SalesSpecialistWorkRequest salesRequest = (SalesSpecialistWorkRequest) request;
            if (salesRequest.getStatus().equals(WorkRequest.TypeOfStatus.InProgress.getValue()) && salesRequest.getInvoiceAmount() != salesRequest.getPaidAmount() && (new Date().getDate() - salesRequest.getRequestDate().getDate() > 3)) {
                salesRequest.setTestResults(WorkRequest.TypeOfResult.Reject.getValue());
                salesRequest.setStatus(WorkRequest.TypeOfStatus.Completed.getValue());
                salesRequest.setResolveDate(new Date());
                resetavailability(salesRequest);
            } else {
                JOptionPane.showMessageDialog(null, "The payment has been done or it has not been more than 3 days, since the request was sent to the Account Organization for payment");
            }
        }
        populatePendingWorkRequestTable();
        populateWorkRequestHistoryTable();
    }//GEN-LAST:event_btnRejectActionPerformed

    private void btnViewRequestFromHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewRequestFromHistoryActionPerformed
        int selectedRow = tblPendingWorkRequest.getSelectedRow();
        if (selectedRow >= 0) {
            WorkRequest workRequest = (WorkRequest) tblWorkRequestHistory.getValueAt(selectedRow, 0);
            SalesSpecialistWorkRequest salesSpecialistWorkRequest = (SalesSpecialistWorkRequest) workRequest;
            ViewRequestJPanel vojpp = new ViewRequestJPanel(userProcessContainer, salesSpecialistWorkRequest);
            userProcessContainer.add("View Order JPanel", vojpp);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_btnViewRequestFromHistoryActionPerformed

    private void btnSendInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendInvoiceActionPerformed
        int selectedRow = tblPendingWorkRequest.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
            WorkRequest request = (WorkRequest) tblPendingWorkRequest.getValueAt(selectedRow, 0);
            SalesSpecialistWorkRequest salesRequest = (SalesSpecialistWorkRequest) request;
            double invoiceAmount = 0;

            for (OrderItem orderItem : salesRequest.getOrder().getOrderItem()) {
                invoiceAmount = invoiceAmount + (orderItem.getQuantity() * orderItem.getDevice().getUnitPrice());
            }

            salesRequest.setInvoiceAmount(invoiceAmount);
            salesRequest.setSalesSpecialist(userAccount);
            salesRequest.setStatus(WorkRequest.TypeOfStatus.InProgress.getValue());
            salesRequest.setRequestDate(new Date());
            userAccount.getWorkQueue().getWorkRequestList().add(salesRequest);

            for (Enterprise enterprise : ecoSystem.getEnterpriseList()) {
                if (enterprise instanceof WareHouseEnterprise) {
                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                        if (organization instanceof AccountOrganization) {
                            organization.getWorkQueue().getWorkRequestList().add(salesRequest);
                        }
                    }
                }
            }
        }
        populatePendingWorkRequestTable();
        populateWorkRequestHistoryTable();
    }//GEN-LAST:event_btnSendInvoiceActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        populatePendingWorkRequestTable();
        populateWorkRequestHistoryTable();
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnReject;
    private javax.swing.JButton btnSendInvoice;
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
