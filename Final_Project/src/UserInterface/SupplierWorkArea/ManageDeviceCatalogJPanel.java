/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.SupplierWorkArea;

import static Business.Organization.Organization.OrganizationType.Supplier;
import Business.Person.Supplier;
import Business.Device;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tushar
 */
public class ManageDeviceCatalogJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageDeviceCatalogJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Supplier supplier;
    private Device device;
    private Device tempDevice;
    private int count = 0;
    private ArrayList<Device> deviceCatalog;
    
    public ManageDeviceCatalogJPanel(JPanel userProcessContainer, UserAccount userAccount) {
        initComponents();

        this.userAccount = userAccount;
        this.userProcessContainer = userProcessContainer;
        deviceCatalog = new ArrayList<>();
        supplier = (Supplier) userAccount.getPerson();
        txtSupplier.setText(supplier.getFirstName() + " " + supplier.getLastName());
        populateProductTable();
    }

    private void populateProductTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblProduct.getModel();
        defaultTableModel.setRowCount(0);
                
        for (Device device : supplier.getDeviceCatalog().getDeviceList()) {
            count = 0;

            for(int i = 0; i< deviceCatalog.size(); i++){
                    tempDevice = deviceCatalog.get(i);
                    if(device.getDeviceName().equals(tempDevice.getDeviceName())){
                        count++;
                        //return;
                    }
                }
            if(count == 0){
                deviceCatalog.add(device);
            }
        }

        for(Device device : deviceCatalog){
            Object[] row = new Object[4];

            row[0] = device;
            row[1] = device.getUnitPrice();
            row[2] = device.getCompanyName();
            row[3] = device.getAvailability();
            
            defaultTableModel.addRow(row);
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

        lblSupplier = new javax.swing.JLabel();
        lblManageDeviceCatalog = new javax.swing.JLabel();
        txtSupplier = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        btnSearchADevice = new javax.swing.JButton();
        btnViewDevice = new javax.swing.JButton();
        btnCreateNewDevice = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        lblMessage = new javax.swing.JLabel();

        lblSupplier.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSupplier.setForeground(new java.awt.Color(102, 204, 255));
        lblSupplier.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSupplier.setText("Supplier :");

        lblManageDeviceCatalog.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblManageDeviceCatalog.setForeground(new java.awt.Color(102, 204, 255));
        lblManageDeviceCatalog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManageDeviceCatalog.setText("Manage Device Catalog");

        txtSupplier.setEnabled(false);

        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnSearchADevice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearchADevice.setText("Search a Device");
        btnSearchADevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchADeviceActionPerformed(evt);
            }
        });

        btnViewDevice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnViewDevice.setText("View Device");
        btnViewDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDeviceActionPerformed(evt);
            }
        });

        btnCreateNewDevice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCreateNewDevice.setText("Create New Device");
        btnCreateNewDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNewDeviceActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        tblProduct.setBackground(new java.awt.Color(255, 153, 0));
        tblProduct.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Device Name", "Price", "Company", "Availability"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProduct);
        if (tblProduct.getColumnModel().getColumnCount() > 0) {
            tblProduct.getColumnModel().getColumn(0).setResizable(false);
            tblProduct.getColumnModel().getColumn(1).setResizable(false);
            tblProduct.getColumnModel().getColumn(2).setResizable(false);
            tblProduct.getColumnModel().getColumn(3).setResizable(false);
        }

        jScrollPane2.setViewportView(jScrollPane1);

        lblMessage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMessage.setForeground(new java.awt.Color(102, 204, 255));
        lblMessage.setText("Click View Device Button to view Device Detail");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(btnBack)
                .addContainerGap(759, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblSupplier)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblManageDeviceCatalog)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(128, 128, 128)
                                            .addComponent(btnRefresh))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnSearchADevice)
                                    .addGap(46, 46, 46)
                                    .addComponent(btnCreateNewDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(40, 40, 40)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(btnViewDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(264, 264, 264))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblManageDeviceCatalog)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefresh)))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateNewDevice)
                    .addComponent(btnSearchADevice))
                .addGap(18, 18, 18)
                .addComponent(lblMessage)
                .addGap(18, 18, 18)
                .addComponent(btnViewDevice)
                .addGap(43, 43, 43)
                .addComponent(btnBack)
                .addContainerGap(71, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        populateProductTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSearchADeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchADeviceActionPerformed
        SearchForDeviceJPanel sfpjp = new SearchForDeviceJPanel(userProcessContainer, supplier);
        userProcessContainer.add("Search For Product",sfpjp);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnSearchADeviceActionPerformed

    private void btnViewDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDeviceActionPerformed
        int selectedRow = tblProduct.getSelectedRow();
        
        if(selectedRow >= 0){
           
            device = (Device)tblProduct.getValueAt(selectedRow, 0);
            ManageSelectedDeviceJPanel msdjp = new ManageSelectedDeviceJPanel(userProcessContainer, device, supplier);
            userProcessContainer.add("ViewProductDetailJPanelSupplier", msdjp);
            CardLayout layout = (CardLayout)userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        }
        else{
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_btnViewDeviceActionPerformed

    private void btnCreateNewDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNewDeviceActionPerformed
        CreateNewDeviceJPanel cnpjp = new CreateNewDeviceJPanel(userProcessContainer, supplier);
        userProcessContainer.add("Create New Product",cnpjp);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnCreateNewDeviceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreateNewDevice;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearchADevice;
    private javax.swing.JButton btnViewDevice;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblManageDeviceCatalog;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblSupplier;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtSupplier;
    // End of variables declaration//GEN-END:variables
}