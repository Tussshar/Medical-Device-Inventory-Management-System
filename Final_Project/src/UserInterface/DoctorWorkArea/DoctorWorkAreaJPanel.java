/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.DoctorWorkArea;

import Business.Enterprise.Enterprise;
import Business.Organization.DoctorOrganization;
import Business.Organization.Organization;
import Business.Person.Doctor;
import Business.Person.Person;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import java.util.Date;
import javax.swing.JPanel;

/**
 *
 * @author Tushar
 */
public class DoctorWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form DoctorWorkAreaJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Enterprise enterprise;
    public DoctorWorkAreaJPanel(JPanel userProcessContainer, UserAccount userAccount, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.enterprise = enterprise;       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDoctorWorkArea = new javax.swing.JLabel();
        btnRaiseARequest = new javax.swing.JButton();

        lblDoctorWorkArea.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDoctorWorkArea.setForeground(new java.awt.Color(102, 204, 255));
        lblDoctorWorkArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoctorWorkArea.setText("Doctor Work Area");

        btnRaiseARequest.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRaiseARequest.setText("Raise a Request");
        btnRaiseARequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRaiseARequestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(lblDoctorWorkArea, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
                .addGap(87, 87, 87))
            .addGroup(layout.createSequentialGroup()
                .addGap(364, 364, 364)
                .addComponent(btnRaiseARequest, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblDoctorWorkArea)
                .addGap(129, 129, 129)
                .addComponent(btnRaiseARequest)
                .addContainerGap(289, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRaiseARequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRaiseARequestActionPerformed
        RaiseARequestJPanel rarjp = new RaiseARequestJPanel(userProcessContainer, userAccount, enterprise);
        userProcessContainer.add("Raise a Request JPanel",rarjp);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnRaiseARequestActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRaiseARequest;
    private javax.swing.JLabel lblDoctorWorkArea;
    // End of variables declaration//GEN-END:variables
}
