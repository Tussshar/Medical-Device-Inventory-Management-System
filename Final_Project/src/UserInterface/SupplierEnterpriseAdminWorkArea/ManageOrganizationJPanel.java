/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.SupplierEnterpriseAdminWorkArea;

import Business.Enterprise.Enterprise;
import Business.Enterprise.SupplierEnterprise;
import Business.Organization.Organization;
import Business.Organization.SalesOrganization;
import Business.Organization.SupplierOrganization;
import Business.UserAccount.UserAccount;
import com.sun.org.apache.xpath.internal.operations.Or;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tushar
 */
public class ManageOrganizationJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageOrganizationJPanel
     */
    private JPanel userProcessContainer;
    private Enterprise enterprise;

    public ManageOrganizationJPanel(JPanel userProcessContainer, UserAccount userAccount, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.enterprise = enterprise;

        populateOrganizationTable();
        populateOrganizationTypeComboBox();
    }

    private void populateOrganizationTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblOrganizationName.getModel();
        defaultTableModel.setRowCount(0);

        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            Object[] row = new Object[2];
            if (organization instanceof SalesOrganization) {
                SalesOrganization salesOrganization = (SalesOrganization) organization;
                row[0] = salesOrganization.getOrganizationID();
            }
            if (organization instanceof SupplierOrganization) {
                SupplierOrganization supplierOrganization = (SupplierOrganization) organization;
                row[0] = supplierOrganization.getOrganizationID();
            }
            row[1] = organization.getOrganizationName();

            defaultTableModel.addRow(row);
        }
    }

    private void populateOrganizationTypeComboBox() {
        cmbOrganizationType.removeAllItems();;

        for (Organization.OrganizationType type : Organization.OrganizationType.values()) {
            if (type.getValue().equals(Organization.OrganizationType.Sales.getValue()) || type.getValue().equals(Organization.OrganizationType.Supplier.getValue())) {
                cmbOrganizationType.addItem(type);
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
        tblOrganizationName = new javax.swing.JTable();
        lblOrganizationType = new javax.swing.JLabel();
        lblNewOrganization = new javax.swing.JLabel();
        cmbOrganizationType = new javax.swing.JComboBox();
        btnCreateOrganization = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        tblOrganizationName.setBackground(new java.awt.Color(255, 153, 0));
        tblOrganizationName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblOrganizationName.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblOrganizationName);
        if (tblOrganizationName.getColumnModel().getColumnCount() > 0) {
            tblOrganizationName.getColumnModel().getColumn(0).setResizable(false);
            tblOrganizationName.getColumnModel().getColumn(1).setResizable(false);
        }

        lblOrganizationType.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblOrganizationType.setText("Organization Type");

        lblNewOrganization.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNewOrganization.setForeground(new java.awt.Color(102, 204, 255));
        lblNewOrganization.setText("Create New Organization");

        cmbOrganizationType.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbOrganizationType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCreateOrganization.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCreateOrganization.setText("Create Organization");
        btnCreateOrganization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateOrganizationActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(340, 340, 340)
                                .addComponent(lblOrganizationType)
                                .addGap(18, 18, 18)
                                .addComponent(cmbOrganizationType, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(btnBack))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblNewOrganization)
                                    .addGap(255, 255, 255))
                                .addComponent(btnRefresh))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(408, 408, 408)
                        .addComponent(btnCreateOrganization)))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lblNewOrganization)
                .addGap(16, 16, 16)
                .addComponent(btnRefresh)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrganizationType)
                    .addComponent(cmbOrganizationType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btnCreateOrganization)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(70, 70, 70))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateOrganizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateOrganizationActionPerformed
        int count = 0;
        Organization.OrganizationType type = (Organization.OrganizationType) cmbOrganizationType.getSelectedItem();
        
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if(organization.getOrganizationName().equals(type.getValue())){
                count++;
            }
        }
        if(count == 0){
            enterprise.getOrganizationDirectory().createOrganization(type);
        }
        else{
            JOptionPane.showMessageDialog(null, "The selected Organization is already created!!!");
        }
        populateOrganizationTable();
    }//GEN-LAST:event_btnCreateOrganizationActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        populateOrganizationTable();
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreateOrganization;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox cmbOrganizationType;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNewOrganization;
    private javax.swing.JLabel lblOrganizationType;
    private javax.swing.JTable tblOrganizationName;
    // End of variables declaration//GEN-END:variables
}