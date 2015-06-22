/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.InventorySpecialistWorkArea;

import Business.Device;
import Business.DeviceCatalog;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.SupplierEnterprise;
import Business.MasterOrderCatalog;
import Business.Network.Network;
import Business.Order;
import Business.OrderItem;
import Business.Organization.Organization;
import Business.Organization.SalesOrganization;
import Business.Organization.SupplierOrganization;
import Business.Person.Person;
import Business.Person.Supplier;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.SalesSpecialistWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tushar
 */
public class BrowseDeviceJPanel extends javax.swing.JPanel {

    /**
     * Creates new form BrowseDeviceJPanel
     */
    private boolean isCheckedOut = false;
    private JPanel userProcessContainer;
    private Order order;
    private EcoSystem ecoSystem;
    private UserAccount userAccount;
    private Enterprise enterprise;
    private ArrayList<Device> deviceCatalog;
    private Device tempDevice;
    private SalesSpecialistWorkRequest salesSpecialistWorkRequest;
    private Supplier supplier;

    public BrowseDeviceJPanel(JPanel userProcessContainer, UserAccount userAccount, Enterprise enterprise, EcoSystem ecoSystem) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.ecoSystem = ecoSystem;
        this.userAccount = userAccount;
        this.enterprise = enterprise;

        deviceCatalog = new ArrayList<>();

        if (!isCheckedOut) {
            order = new Order();
        }
        populateNetworkComboBox();
        populateSupplierComboBox();
    }

    private void populateNetworkComboBox() {
        cmbNetwork.removeAllItems();

        for (Network network : ecoSystem.getNetworkList()) {
            cmbNetwork.addItem(network);
        }
    }

    private void populateSupplierComboBox() {
        cmbSupplier.removeAllItems();

        Network network = (Network) cmbNetwork.getSelectedItem();

        for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {

            if (enterprise instanceof SupplierEnterprise) {
                for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                    if (organization instanceof SupplierOrganization) {
                        for (Person person : organization.getPersonDirectory().getPersonList()) {
                            if (person instanceof Supplier) {
                                Supplier supplier = (Supplier) person;
                                if (supplier.getIsEnrolled().equalsIgnoreCase("YES")) {
                                    cmbSupplier.addItem(supplier);
                                }
                            }
                        }
                    }
                }
            }
        }
        populateDeviceTable();
    }

    private void populateSupplierComboBox(Network network) {
        cmbSupplier.removeAllItems();

        for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {

            if (enterprise instanceof SupplierEnterprise) {
                for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                    if (organization instanceof SupplierOrganization) {
                        for (Person person : organization.getPersonDirectory().getPersonList()) {
                            if (person instanceof Supplier) {
                                Supplier supplier = (Supplier) person;
                                if (supplier.getIsEnrolled().equalsIgnoreCase("YES")) {
                                    cmbSupplier.addItem(supplier);
                                }
                            }
                        }
                    }
                }
            }
        }
        populateDeviceTable();
    }

    private void populateDeviceTable() {
        Supplier supplier = (Supplier) cmbSupplier.getSelectedItem();

        if (supplier != null) {
            DefaultTableModel defaultTableModel = (DefaultTableModel) tblDeviceTable.getModel();
            int selectedRow = tblDeviceTable.getRowCount();
            for(int i = selectedRow -1; i >= 0; i--){
            defaultTableModel.removeRow(i);
            //System.out.println("Check");
        }
            defaultTableModel.setRowCount(0);
            deviceCatalog.removeAll(deviceCatalog);

            for (Device device : supplier.getDeviceCatalog().getDeviceList()) {
                if (device.getInStock().equalsIgnoreCase("YES")) {
                    int count = 0;

                    for (int i = 0; i < deviceCatalog.size(); i++) {
                        tempDevice = deviceCatalog.get(i);
                        if (device.getDeviceName().equals(tempDevice.getDeviceName())) {
                            count++;
                        }
                    }
                    if (count == 0) {
                        deviceCatalog.add(device);
                    }
                }
            }
            for (Device device : deviceCatalog) {
                Object[] row = new Object[4];

                row[0] = device;
                row[1] = device.getUnitPrice();
                row[2] = device.getAvailability();
                row[3] = supplier;

                defaultTableModel.addRow(row);
            }
        }
    }

    private void populateDeviceTable(Device device, DefaultTableModel defaultTableModel, Supplier supplier) {
        Object row[] = new Object[4];

        row[0] = device;
        row[1] = device.getUnitPrice();
        row[2] = device.getAvailability();
        row[3] = supplier;

        defaultTableModel.addRow(row);
    }

    private void populateOrderItemTable(Order order) {
        DefaultTableModel dtm = (DefaultTableModel) tblOrderItem.getModel();
        dtm.setRowCount(0);

        for (OrderItem orderItem : order.getOrderItem()) {
            Object row[] = new Object[4];
            row[0] = orderItem;
            row[1] = orderItem.getQuantity();

            Device device = orderItem.getDevice();
            row[2] = device.getUnitPrice();
            row[3] = device.getUnitPrice() * orderItem.getQuantity();
            dtm.addRow(row);
        }
    }

    private void resetavailability(Order order) {

        int availability;
        Device device;
        ArrayList<String> uIDList;

        for (OrderItem orderItem : order.getOrderItem()) {

            Supplier supplier = orderItem.getSupplier();
            device = orderItem.getDevice();
            availability = device.getAvailability() + orderItem.getQuantity();
            uIDList = orderItem.getuIDList();

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        lblSupplierWorkArea = new javax.swing.JLabel();
        cmbSupplier = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        lblEnterDeviceName = new javax.swing.JLabel();
        btnBrowse = new javax.swing.JButton();
        txtEnterDeviceName = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDeviceTable = new javax.swing.JTable();
        btnViewDeviceDetail = new javax.swing.JButton();
        lblQuantity = new javax.swing.JLabel();
        spnQuantity = new javax.swing.JSpinner();
        btnAddToCart = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblOrderItem = new javax.swing.JTable();
        btnViewItem = new javax.swing.JButton();
        txtModify = new javax.swing.JTextField();
        btnModify = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnCheckOut = new javax.swing.JButton();
        lblNetwork = new javax.swing.JLabel();
        cmbNetwork = new javax.swing.JComboBox();

        lblSupplierWorkArea.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSupplierWorkArea.setForeground(new java.awt.Color(102, 204, 255));
        lblSupplierWorkArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSupplierWorkArea.setText("Browse Product Catalog");

        cmbSupplier.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbSupplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSupplierActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Supplier :");

        lblEnterDeviceName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEnterDeviceName.setText("Enter Device Name");

        btnBrowse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBrowse.setText("Browse");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        tblDeviceTable.setBackground(new java.awt.Color(255, 153, 0));
        tblDeviceTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblDeviceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Price", "Availability", "Supplier"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDeviceTable);
        if (tblDeviceTable.getColumnModel().getColumnCount() > 0) {
            tblDeviceTable.getColumnModel().getColumn(0).setResizable(false);
            tblDeviceTable.getColumnModel().getColumn(1).setResizable(false);
            tblDeviceTable.getColumnModel().getColumn(2).setResizable(false);
            tblDeviceTable.getColumnModel().getColumn(3).setResizable(false);
        }

        btnViewDeviceDetail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnViewDeviceDetail.setText("View Device Detail");
        btnViewDeviceDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDeviceDetailActionPerformed(evt);
            }
        });

        lblQuantity.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblQuantity.setText("Quantity");

        spnQuantity.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btnAddToCart.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAddToCart.setText("Add to Cart");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });

        tblOrderItem.setBackground(new java.awt.Color(255, 153, 0));
        tblOrderItem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblOrderItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Quantity", "Unit Price", "Total Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblOrderItem);
        if (tblOrderItem.getColumnModel().getColumnCount() > 0) {
            tblOrderItem.getColumnModel().getColumn(0).setResizable(false);
            tblOrderItem.getColumnModel().getColumn(1).setResizable(false);
            tblOrderItem.getColumnModel().getColumn(2).setResizable(false);
            tblOrderItem.getColumnModel().getColumn(3).setResizable(false);
        }

        btnViewItem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnViewItem.setText("View Item");
        btnViewItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewItemActionPerformed(evt);
            }
        });

        btnModify.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnModify.setText("Modify");
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnCheckOut.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCheckOut.setText("Check Out");
        btnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutActionPerformed(evt);
            }
        });

        lblNetwork.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNetwork.setText("Network");

        cmbNetwork.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbNetwork.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbNetwork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNetworkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblSupplierWorkArea)
                .addGap(352, 352, 352))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(btnViewDeviceDetail)
                .addGap(128, 128, 128)
                .addComponent(lblQuantity)
                .addGap(18, 18, 18)
                .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddToCart)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnViewItem)
                            .addComponent(btnBack))
                        .addGap(142, 142, 142)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCheckOut)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtModify, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModify))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblNetwork)
                                .addGap(18, 18, 18)
                                .addComponent(cmbNetwork, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblEnterDeviceName)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtEnterDeviceName, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnBrowse)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnRefresh))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 139, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblSupplierWorkArea)
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNetwork)
                        .addComponent(cmbNetwork, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEnterDeviceName)
                    .addComponent(txtEnterDeviceName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowse)
                    .addComponent(btnRefresh))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewDeviceDetail)
                    .addComponent(lblQuantity)
                    .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddToCart))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewItem)
                    .addComponent(txtModify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModify))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnCheckOut))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbNetworkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNetworkActionPerformed
        Network network = (Network) cmbNetwork.getSelectedItem();

        if (network != null) {
            populateSupplierComboBox(network);
        }
    }//GEN-LAST:event_cmbNetworkActionPerformed

    private void cmbSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSupplierActionPerformed
        Supplier supplier = (Supplier) cmbSupplier.getSelectedItem();

        if (supplier != null) {
            populateDeviceTable();
        }
    }//GEN-LAST:event_cmbSupplierActionPerformed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed

        int count = 0;
        if (cmbSupplier.getSelectedItem() != null) {
            String deviceName = txtEnterDeviceName.getText().trim();

            if (deviceName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter device name to be searched");
            } else {
                deviceCatalog.removeAll(deviceCatalog);
                DefaultTableModel dtm = (DefaultTableModel) tblDeviceTable.getModel();
                dtm.setRowCount(0);
                for (Network network : ecoSystem.getNetworkList()) {
                    for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                        if (enterprise instanceof SupplierEnterprise) {
                            for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                                if (organization instanceof SupplierOrganization) {
                                    label:
                                    for (Person person : organization.getPersonDirectory().getPersonList()) {
                                        if (person instanceof Supplier) {
                                            Supplier supplier = (Supplier) person;
                                            for (Device device : supplier.getDeviceCatalog().getDeviceList()) {
                                                if (deviceName.equalsIgnoreCase(device.getDeviceName())) {
                                                    if (device.getInStock().equalsIgnoreCase("YES")) {
                                                        count++;
                                                        populateDeviceTable(device, dtm, supplier);
                                                        continue label;
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
        if (count == 0) {
            JOptionPane.showMessageDialog(null, "The entered device name doesnot exist");
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void btnViewDeviceDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDeviceDetailActionPerformed
        int selectedRow = tblDeviceTable.getSelectedRow();

        if (selectedRow >= 0) {
            Supplier supplier = (Supplier) tblDeviceTable.getValueAt(selectedRow, 3);
            Device device = (Device) tblDeviceTable.getValueAt(selectedRow, 0);
            ViewDeviceJPanel vdjp = new ViewDeviceJPanel(userProcessContainer, device, ecoSystem, supplier);
            userProcessContainer.add("View Device JPanel", vdjp);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_btnViewDeviceDetailActionPerformed

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed

        ArrayList<String> uIDList;
        Supplier supplier = (Supplier) cmbSupplier.getSelectedItem();
        int selectedRow = tblDeviceTable.getSelectedRow();
        int rowcount = tblDeviceTable.getRowCount();
        
        int quantity = (Integer) spnQuantity.getValue();

        if (selectedRow >= 0 && quantity > 0) {
            Device device = (Device) tblDeviceTable.getValueAt(selectedRow, 0);

            if (quantity <= 0 || quantity > device.getAvailability()) {
                JOptionPane.showMessageDialog(null, "Invalid quantity");
                return;
            } else {
                boolean isIncluded = false;
                for (OrderItem orderItem : order.getOrderItem()) {
                    if (orderItem.getDevice().getDeviceName().equals(device.getDeviceName())) {
                        int newQuantity = (Integer) spnQuantity.getValue();
                        if (newQuantity <= 0 || newQuantity > device.getAvailability()) {
                            JOptionPane.showMessageDialog(null, "Invalid quantity");
                            return;
                        }

                        int i = 0;
                        for (Device dev : supplier.getDeviceCatalog().getDeviceList()) {
                            if (dev.getDeviceName().equalsIgnoreCase(device.getDeviceName())) {
                                if (dev.getInStock().equalsIgnoreCase("YES")) {
                                    if (i < quantity) {
                                        orderItem.getuIDList().add(dev.getUniqueDeviceIdentifier());
                                        dev.setInStock("NO");
                                        i++;
                                    }
                                }
                            }
                        }

                        int availability = device.getAvailability() - quantity;

                        for (Device dev : supplier.getDeviceCatalog().getDeviceList()) {
                            if (dev.getDeviceName().equalsIgnoreCase(device.getDeviceName())) {
                                dev.setAvailability(availability);
                            }
                        }
                        orderItem.setQuantity(newQuantity + orderItem.getQuantity());
                        orderItem.setSupplier(supplier);
                        isIncluded = true;
                    }
                }
                if (!isIncluded) {
                    OrderItem orderItem = order.addOrderItem();

                    orderItem.setDevice(device);
                    orderItem.setQuantity(quantity);
                    orderItem.setSupplier(supplier);
                    int availability = device.getAvailability() - quantity;

                    for (Device dev : supplier.getDeviceCatalog().getDeviceList()) {
                        if (dev.getDeviceName().equalsIgnoreCase(device.getDeviceName())) {
                            dev.setAvailability(availability);
                        }
                    }

                    int i = 0;
                    for (Device dev : supplier.getDeviceCatalog().getDeviceList()) {
                        if (dev.getDeviceName().equalsIgnoreCase(device.getDeviceName())) {
                            if (dev.getInStock().equalsIgnoreCase("YES")) {
                                if (i < quantity) {
                                    orderItem.getuIDList().add(dev.getUniqueDeviceIdentifier());
                                    dev.setInStock("NO");
                                    i++;
                                }
                            }
                        }
                    }
                }
                populateOrderItemTable(order);
                populateDeviceTable();
            }
        } else {
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(null, "Please select quantity greater than zero");
            }
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(null, "Please select a row");
            }
        }
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed

        int check = tblOrderItem.getRowCount();

        if (check != 0) {

            //Seperating the orders of each suppliers
            MasterOrderCatalog moc = new MasterOrderCatalog();
            int masterCheck = 0;
            for (OrderItem orderItem : order.getOrderItem()) {
                masterCheck = 0;
                for (Order mocOrder : moc.getMasterOrderCatalog()) {
                    if (orderItem.getSupplier().equals(mocOrder.getOrderItem().get(0).getSupplier())) {
                        if (masterCheck == 0) {
                            mocOrder.addMocOrderItem(orderItem);
                        }
                        masterCheck++;
                    }
                }

                if (masterCheck == 0) {
                    Order mocOrder = new Order();
                    mocOrder.addMocOrderItem(orderItem);
                    moc.addOrder(mocOrder);
                }
            }

            //Sending order to specific SalesOrganization
            for (Order mocOrder : moc.getMasterOrderCatalog()) {

                supplier = mocOrder.getOrderItem().get(0).getSupplier();

                salesSpecialistWorkRequest = new SalesSpecialistWorkRequest();
                salesSpecialistWorkRequest.setStatus(WorkRequest.TypeOfStatus.Pending.getValue());
                salesSpecialistWorkRequest.setInventorySpecialist(userAccount);
                salesSpecialistWorkRequest.setSupplier(supplier);
                salesSpecialistWorkRequest.setOrder(mocOrder);
                salesSpecialistWorkRequest.setRequestDate(new Date());

                userAccount.getWorkQueue().getWorkRequestList().add(salesSpecialistWorkRequest);
                enterprise.getWorkQueue().getWorkRequestList().add(salesSpecialistWorkRequest);

                for (Network network : ecoSystem.getNetworkList()) {
                    for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                        if (enterprise instanceof SupplierEnterprise) {
                            for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                                if (organization instanceof SupplierOrganization) {
                                    for (Person person : organization.getPersonDirectory().getPersonList()) {
                                        if (supplier.equals((Supplier) person)) {
                                            for (Organization org1 : enterprise.getOrganizationDirectory().getOrganizationList()) {
                                                if (org1 instanceof SalesOrganization) {
                                                    org1.getWorkQueue().getWorkRequestList().add(salesSpecialistWorkRequest);
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

            isCheckedOut = true;
            order = new Order();
            populateOrderItemTable(order);
            populateNetworkComboBox();
        } else {
            JOptionPane.showMessageDialog(null, "No order has been selected");
        }
    }//GEN-LAST:event_btnCheckOutActionPerformed

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed

        int selectedRow = tblOrderItem.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");
            return;
        } else {
            try {
                OrderItem orderItem = (OrderItem) tblOrderItem.getValueAt(selectedRow, 0);
                int newquantity = Integer.parseInt(txtModify.getText().trim());

                if (newquantity > (orderItem.getDevice().getAvailability() + orderItem.getQuantity())) {
                    JOptionPane.showMessageDialog(null, "The entered quantity is more than the device availability");
                } else {
                    int availability = orderItem.getDevice().getAvailability() - (newquantity - orderItem.getQuantity());
                    int oldQuantity = orderItem.getQuantity();
                    supplier = orderItem.getSupplier();
                    tempDevice = orderItem.getDevice();

                    orderItem.setQuantity(newquantity);

                    for (Device device : supplier.getDeviceCatalog().getDeviceList()) {
                        if (tempDevice.getDeviceName().equalsIgnoreCase(device.getDeviceName())) {
                            device.setAvailability(availability);
                        }
                    }

                    if (newquantity > oldQuantity) {
                        int count = 0;
                        for (Device device : supplier.getDeviceCatalog().getDeviceList()) {
                            if (tempDevice.getDeviceName().equalsIgnoreCase(device.getDeviceName())) {
                                if (device.getInStock().equalsIgnoreCase("YES")) {
                                    if (count < (newquantity - oldQuantity)) {
                                        device.setInStock("NO");
                                        orderItem.getuIDList().add(device.getUniqueDeviceIdentifier());
                                        count++;
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    } else if (oldQuantity > newquantity) {
                        for (int i = 0; i < oldQuantity - newquantity; i++) {
                            String uID = orderItem.getuIDList().get(0);
                            for (Device device : supplier.getDeviceCatalog().getDeviceList()) {
                                if (device.getUniqueDeviceIdentifier().equalsIgnoreCase(uID)) {
                                    device.setInStock("YES");
                                }
                            }
                            orderItem.getuIDList().remove(0);

                        }
                    }

                    populateDeviceTable();
                    populateOrderItemTable(order);

                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter integer value to modify quantity");
            }
        }
    }//GEN-LAST:event_btnModifyActionPerformed

    private void btnViewItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewItemActionPerformed
        int selectedRow = tblOrderItem.getSelectedRow();

        if (selectedRow >= 0) {
            OrderItem orderItem = (OrderItem) tblOrderItem.getValueAt(selectedRow, 0);
            ViewOrderItemJPanel vpjp = new ViewOrderItemJPanel(userProcessContainer, orderItem);
            userProcessContainer.add("View Product JPanel", vpjp);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_btnViewItemActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed

        int check = tblOrderItem.getRowCount();

        if (check != 0) {
            resetavailability(order);
        }
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        populateDeviceTable();
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JButton btnModify;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnViewDeviceDetail;
    private javax.swing.JButton btnViewItem;
    private javax.swing.JComboBox cmbNetwork;
    private javax.swing.JComboBox cmbSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblEnterDeviceName;
    private javax.swing.JLabel lblNetwork;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblSupplierWorkArea;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTable tblDeviceTable;
    private javax.swing.JTable tblOrderItem;
    private javax.swing.JTextField txtEnterDeviceName;
    private javax.swing.JTextField txtModify;
    // End of variables declaration//GEN-END:variables
}
