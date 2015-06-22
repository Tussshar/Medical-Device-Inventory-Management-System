/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.SystemAdminWorkArea;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.WareHouseEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tushar
 */
public class ManageEnterpriseJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageNetworkJPanel
     */
    private JPanel userProcessContainer;
    private EcoSystem ecoSystem;

    public ManageEnterpriseJPanel(JPanel userProcessContainer, EcoSystem ecoSystem) {
        initComponents();
        this.ecoSystem = ecoSystem;
        this.userProcessContainer = userProcessContainer;

        populateEnterpriseTable();
        populateNetworkComboBox();
        populateEnterpriseTypeComboBox();
    }

    private void populateEnterpriseTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) enterpriseJTable.getModel();
        defaultTableModel.setRowCount(0);

        for (Network network : ecoSystem.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                Object[] row = new Object[3];
                row[0] = enterprise.getOrganizationName();
                row[1] = network.getName();
                row[2] = enterprise.getEnterpriseType().getValue();

                defaultTableModel.addRow(row);
            }
        }
    }

    private void populateNetworkComboBox() {
        cmbNetwork.removeAllItems();

        for (Network network : ecoSystem.getNetworkList()) {
            cmbNetwork.addItem(network);
        }

//        for (Enterprise.EnterpriseType type : Enterprise.EnterpriseType.values()) {
//            if(type.equals(Enterprise.EnterpriseType.WareHouse.getValue())){
//                
//            }
//            cmbEnterpriseType.addItem(type);
//        }
    }

    private void populateEnterpriseTypeComboBox() {
        cmbEnterpriseType.removeAllItems();

        for (Enterprise.EnterpriseType type : Enterprise.EnterpriseType.values()) {

            if (!type.getValue().equals(Enterprise.EnterpriseType.WareHouse.getValue())) {
                cmbEnterpriseType.addItem(type);
            }

//            if (type.equals(Enterprise.EnterpriseType.WareHouse.getValue())) {
//                for (Enterprise enterprise : netw.getEnterpriseDirectory().getEnterpriseList()) {
//                    if (enterprise instanceof WareHouseEnterprise) {
//                        if (enterprise.getOrganizationName() == null) {
//                            cmbEnterpriseType.addItem(type);
//                        }
//                    }
//                }
//            }
//            else{
//                cmbEnterpriseType.addItem(type);
//            }
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
        enterpriseJTable = new javax.swing.JTable();
        lblNetwork = new javax.swing.JLabel();
        txtEnterpriseName = new javax.swing.JTextField();
        cmbNetwork = new javax.swing.JComboBox();
        lblEnterpriseType = new javax.swing.JLabel();
        cmbEnterpriseType = new javax.swing.JComboBox();
        lblEnterpriseName = new javax.swing.JLabel();
        btnCreateEnterprise = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblEnterprises = new javax.swing.JLabel();
        lblNewEnterprise = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();

        enterpriseJTable.setBackground(new java.awt.Color(255, 153, 0));
        enterpriseJTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        enterpriseJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Enterprise Name", "Network", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(enterpriseJTable);

        lblNetwork.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNetwork.setText("Network");

        txtEnterpriseName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        cmbNetwork.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbNetwork.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblEnterpriseType.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEnterpriseType.setText("Enterprise Type");

        cmbEnterpriseType.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbEnterpriseType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblEnterpriseName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEnterpriseName.setText("Enterprise Name");

        btnCreateEnterprise.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCreateEnterprise.setText("Create Enterprise");
        btnCreateEnterprise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateEnterpriseActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack.setText("<<Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblEnterprises.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblEnterprises.setForeground(new java.awt.Color(102, 204, 255));
        lblEnterprises.setText("Enterprises");

        lblNewEnterprise.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNewEnterprise.setForeground(new java.awt.Color(102, 204, 255));
        lblNewEnterprise.setText("Create New Enterprise");

        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(btnBack)
                .addGap(228, 711, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addComponent(lblNewEnterprise))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(btnCreateEnterprise))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEnterprises)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRefresh))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(lblEnterpriseName)
                                .addGap(18, 18, 18)
                                .addComponent(txtEnterpriseName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(lblEnterpriseType)
                                .addGap(18, 18, 18)
                                .addComponent(cmbEnterpriseType, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(lblNetwork)
                                .addGap(18, 18, 18)
                                .addComponent(cmbNetwork, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEnterprises)
                    .addComponent(btnRefresh))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(lblNewEnterprise)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbNetwork, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNetwork))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbEnterpriseType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEnterpriseType))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEnterpriseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEnterpriseName))
                .addGap(18, 18, 18)
                .addComponent(btnCreateEnterprise)
                .addGap(26, 26, 26)
                .addComponent(btnBack)
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateEnterpriseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateEnterpriseActionPerformed

        String errorMessage = "";
        int count = 0;
        Network network = (Network) cmbNetwork.getSelectedItem();
        Enterprise.EnterpriseType enterpriseType = (Enterprise.EnterpriseType) cmbEnterpriseType.getSelectedItem();
        String name = txtEnterpriseName.getText().trim();

        if (network == null) {
            count++;
            errorMessage += "Network cannot be Null";
        }
        if (enterpriseType == null) {
            count++;
            errorMessage += "Enterprise cannot be Null";
        }
        if (name.isEmpty()) {
            count++;
            errorMessage += "Enterprise Name cannot be Null";
        } else if (!name.matches("[a-zA-Z]+")) {
            count++;
            errorMessage += "Name Should contain only Letters";
        }
        if (count == 0) {
            
            
            if (name.equalsIgnoreCase(ecoSystem.getOrganizationName())) {
                count++;
                errorMessage += " An organization with given name already exist, please give a unique name";
            }

            for (Network netw : ecoSystem.getNetworkList()) {
                for (Enterprise enterprise : netw.getEnterpriseDirectory().getEnterpriseList()) {
                    if (enterprise.getOrganizationName().equalsIgnoreCase(name)) {
                        count++;
                        errorMessage += " An organization with given name already exist, please give a unique name";
                        break;
                    }

                    for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                        if (org.getOrganizationName().equalsIgnoreCase(name)) {
                            count++;
                            errorMessage += " An organization with given name already exist, please give a unique name";
                            break;
                        }
                    }
                }
            }

            for (Enterprise enterprise : ecoSystem.getEnterpriseList()) {
                if (enterprise.getOrganizationName().equalsIgnoreCase(name)) {
                    count++;
                    errorMessage += "An organization with given name already exist, please give a unique name\n";
                    break;
                }
            }
            //To make enterprise name unique only for a particular netw, delete first for loop
            for (Network networkList : ecoSystem.getNetworkList()) {
                for (Enterprise enterprise : networkList.getEnterpriseDirectory().getEnterpriseList()) {
                    if (enterprise.getOrganizationName().equals(name)) {
                        count++;
                        errorMessage += "The Current enterprise already exists in the system";
                    }
                }
            }

            if (count == 0) {
                
                
                Enterprise enterprise = network.getEnterpriseDirectory().createAndAddEnterprise(name, enterpriseType);

                if (enterprise != null) {
                    JOptionPane.showMessageDialog(null, "The enterprise has been created successfully");
                }

            }

            populateEnterpriseTable();
        }
        if (count != 0) {
            JOptionPane.showMessageDialog(null, errorMessage);
        }
    }//GEN-LAST:event_btnCreateEnterpriseActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        populateEnterpriseTable();
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreateEnterprise;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox cmbEnterpriseType;
    private javax.swing.JComboBox cmbNetwork;
    private javax.swing.JTable enterpriseJTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEnterpriseName;
    private javax.swing.JLabel lblEnterpriseType;
    private javax.swing.JLabel lblEnterprises;
    private javax.swing.JLabel lblNetwork;
    private javax.swing.JLabel lblNewEnterprise;
    private javax.swing.JTextField txtEnterpriseName;
    // End of variables declaration//GEN-END:variables
}
