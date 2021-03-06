/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.SupplierEnterpriseAdminWorkArea;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Person.Person;
import Business.UserAccount.UserAccount;
import UserInterface.SignUpPersonDetailJDialog;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Tushar
 */
public class SupplierEnterpriseAdminWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form SupplierEnterpriseAdminWorkAreaJPanel
     */
    private JPanel userProcessContainer;
    private EcoSystem ecoSystem;
    private Organization organization;
    private UserAccount userAccount;
    private Enterprise enterprise;
    private ArrayList<Person> personDirectory;
    private ArrayList<UserAccount> userAccountDirectory;
    
    public SupplierEnterpriseAdminWorkAreaJPanel(JPanel userProcessContainer, Organization organization, EcoSystem ecoSystem, UserAccount userAccount, Enterprise enterprise, ArrayList<Person> personDirectory, ArrayList<UserAccount> userAccountDirectory) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.ecoSystem = ecoSystem;
        this.userAccount = userAccount;
        this.enterprise = enterprise;
        this.personDirectory = personDirectory;
        this.userAccountDirectory = userAccountDirectory;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSupplierAdminWorkArea = new javax.swing.JLabel();
        btnManageOrganization = new javax.swing.JButton();
        btnManageOrganizationAdmin = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSupplierAdminWorkArea.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSupplierAdminWorkArea.setForeground(new java.awt.Color(102, 204, 255));
        lblSupplierAdminWorkArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSupplierAdminWorkArea.setText("Supplier Enterprise Admin Work Area");
        add(lblSupplierAdminWorkArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, -1));

        btnManageOrganization.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnManageOrganization.setText("Manage Organization");
        btnManageOrganization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageOrganizationActionPerformed(evt);
            }
        });
        add(btnManageOrganization, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 205, -1));

        btnManageOrganizationAdmin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnManageOrganizationAdmin.setText("Manage Organization Admin");
        btnManageOrganizationAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageOrganizationAdminActionPerformed(evt);
            }
        });
        add(btnManageOrganizationAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnManageOrganizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageOrganizationActionPerformed
        ManageOrganizationJPanel mojp = new ManageOrganizationJPanel(userProcessContainer,userAccount, enterprise);
        userProcessContainer.add("Manage Organization JPanel",mojp);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnManageOrganizationActionPerformed

    private void btnManageOrganizationAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageOrganizationAdminActionPerformed
        ManageOrganizationAdminJPanel moajp = new ManageOrganizationAdminJPanel(userProcessContainer, userAccount, enterprise, ecoSystem);
        userProcessContainer.add("Manage Organization Admin",moajp);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.next(userProcessContainer);

//        ManageOrganizationAdminJPanel moajp = new ManageOrganizationAdminJPanel(userProcessContainer);
//        userProcessContainer.add("Manage Organization Admin JPanel",moajp);
//        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
//        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnManageOrganizationAdminActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnManageOrganization;
    private javax.swing.JButton btnManageOrganizationAdmin;
    private javax.swing.JLabel lblSupplierAdminWorkArea;
    // End of variables declaration//GEN-END:variables
}
