/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.SystemAdminWorkArea;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Tushar
 */
public class SystemAdminWorkAreaConatinerJPanel extends javax.swing.JPanel {

    /**
     * Creates new form SystemAdminWorkAreaConatinerJPanel
     */
    
    private EcoSystem ecoSystem;
    private Organization organization;
    
    public SystemAdminWorkAreaConatinerJPanel(Organization organization, EcoSystem ecoSystem) {
        initComponents();
        this.ecoSystem = ecoSystem;
        this.organization = organization;
        
        SystemAdminWorkAreaJPanel sawajp = new SystemAdminWorkAreaJPanel(userProcessContainer, organization, ecoSystem);
        userProcessContainer.add("System Admin Work Area JPanel",sawajp);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    
        populateTree();
    }

    private void populateTree() {

        DefaultTreeModel model = (DefaultTreeModel) JTree.getModel();

        ArrayList<Network> networkList = ecoSystem.getNetworkList();
        ArrayList<Enterprise> enterpriseList;
        ArrayList<Organization> organizationList;
        Network network;
        Enterprise enterprise;
        Organization organization;

        DefaultMutableTreeNode networks = new DefaultMutableTreeNode("Networks");
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.insert(networks, 0);

        DefaultMutableTreeNode networkNode;
        DefaultMutableTreeNode enterpriseNode;
        DefaultMutableTreeNode organizationNode;
        for (int i = 0; i < networkList.size(); i++) {
            network = networkList.get(i);
//            networkNode = new DefaultMutableTreeNode(network.getName());
            networkNode = new DefaultMutableTreeNode(network);
            networks.insert(networkNode, i);

            enterpriseList = network.getEnterpriseDirectory().getEnterpriseList();

            for (int j = 0; j < enterpriseList.size(); j++) {
                enterprise = enterpriseList.get(j);
                
                enterpriseNode = new DefaultMutableTreeNode(enterprise);
//                                enterpriseNode = new DefaultMutableTreeNode(enterprise.getName());
                networkNode.insert(enterpriseNode, j);

                organizationList = enterprise.getOrganizationDirectory().getOrganizationList();
                for (int k = 0; k < organizationList.size(); k++) {
                    organization = organizationList.get(k);
                    organizationNode = new DefaultMutableTreeNode(organization);
//                                        organizationNode = new DefaultMutableTreeNode(organization.getName());
                    enterpriseNode.insert(organizationNode, k);
                }
            }
        }
        model.reload();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTree = new javax.swing.JTree();
        lblSelectedNode = new javax.swing.JLabel();
        lblView = new javax.swing.JLabel();
        userProcessContainer = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(150);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("System");
        JTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        JTree.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                JTreevalueChangedAction(evt);
            }
        });
        jScrollPane1.setViewportView(JTree);

        lblSelectedNode.setText("                    Selected Node                                   ");

        lblView.setText("      <View Selected Node>            ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblView)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblSelectedNode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSelectedNode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblView)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        userProcessContainer.setLayout(new java.awt.CardLayout());
        jSplitPane1.setRightComponent(userProcessContainer);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void JTreevalueChangedAction(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_JTreevalueChangedAction
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) JTree.getLastSelectedPathComponent();
        if (selectedNode != null) {
            lblView.setText("                          "+selectedNode.toString());
        }
    }//GEN-LAST:event_JTreevalueChangedAction


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree JTree;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblSelectedNode;
    private javax.swing.JLabel lblView;
    private javax.swing.JPanel userProcessContainer;
    // End of variables declaration//GEN-END:variables
}
