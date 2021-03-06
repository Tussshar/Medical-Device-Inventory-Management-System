/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.InventorySpecialistWorkArea;

import Business.Device;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.SupplierEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.SupplierOrganization;
import Business.Person.Person;
import Business.Person.Supplier;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tushar
 */
public class ViewDeviceJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ViewDeviceJPanel
     */
    private JPanel userProcessContainer;
    private Device device;
    private EcoSystem ecoSystem;
    private Supplier supplier;

    public ViewDeviceJPanel(JPanel userProcessContainer, Device device, EcoSystem ecoSystem, Supplier supplier) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.device = device;
        this.ecoSystem = ecoSystem;
        this.supplier = supplier;
        txtMessage.setText("Devices with name " + device.getDeviceName() + " are lsited below");
        searchProduct();
    }

    private void searchProduct() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblDevice.getModel();
        defaultTableModel.setRowCount(0);

//        for (Network network : ecoSystem.getNetworkList()) {
//            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
//                
//                if (enterprise instanceof SupplierEnterprise) {
//                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
//                        if (organization instanceof SupplierOrganization) {
//                            for (Person person : organization.getPersonDirectory().getPersonList()) {
//                                if (person instanceof Supplier) {
//                                    Supplier supplier = (Supplier) person;
//                                    for (Device dev : supplier.getDeviceCatalog().getDeviceList()) {
//                                        if (device.getDeviceName().equalsIgnoreCase(dev.getDeviceName())) {
//                                            populateDeviceTable(device, defaultTableModel);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
        for (Device dev : supplier.getDeviceCatalog().getDeviceList()) {
            if (device.getDeviceName().equalsIgnoreCase(dev.getDeviceName())) {
                if(dev.getInStock().equalsIgnoreCase("YES")){
                    populateDeviceTable(dev, defaultTableModel);
                }
            }
        }
    }

    private void populateDeviceTable(Device device, DefaultTableModel defaultTableModel) {

        Object[] row = new Object[4];

        row[0] = device;
        row[1] = device.getUniqueDeviceIdentifier();
        row[2] = device.getCompanyName();
        row[3] = device.getManufacuredDate();

        defaultTableModel.addRow(row);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSelectedDevice = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDevice = new javax.swing.JTable();
        txtMessage = new javax.swing.JTextField();
        btnView = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        lblSelectedDevice.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSelectedDevice.setForeground(new java.awt.Color(102, 204, 255));
        lblSelectedDevice.setText("Selected Device");

        tblDevice.setBackground(new java.awt.Color(255, 153, 0));
        tblDevice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblDevice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Device Name", "UniqueID", "Company Name", "Manufactured Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDevice);
        if (tblDevice.getColumnModel().getColumnCount() > 0) {
            tblDevice.getColumnModel().getColumn(0).setResizable(false);
            tblDevice.getColumnModel().getColumn(1).setResizable(false);
            tblDevice.getColumnModel().getColumn(2).setResizable(false);
            tblDevice.getColumnModel().getColumn(3).setResizable(false);
        }

        btnView.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnView.setText("View");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblSelectedDevice)
                .addGap(424, 424, 424))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(btnBack))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(434, 434, 434)
                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblSelectedDevice)
                .addGap(35, 35, 35)
                .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnView)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(67, 67, 67))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed

        int selectedRow = tblDevice.getSelectedRow();

        if (selectedRow >= 0) {
            Device device = (Device) tblDevice.getValueAt(selectedRow, 0);
            ViewSelectedDeviceJPanel vsdjp = new ViewSelectedDeviceJPanel(userProcessContainer, device);
            userProcessContainer.add("View Selected Device JPanel", vsdjp);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);

    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnView;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSelectedDevice;
    private javax.swing.JTable tblDevice;
    private javax.swing.JTextField txtMessage;
    // End of variables declaration//GEN-END:variables
}
