/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.SupplierWorkArea;

import Business.Device;
import Business.Person.Supplier;
import java.awt.CardLayout;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Tushar
 */
public class CreateNewDeviceJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CreateNewProduct
     */
    private JPanel userProcessContainer;
    private Supplier supplier;
    private String deviceName;
    private String deviceType;
    private String companyName;
    private double unitPrice;
    private String countryOfOrigin;
    private Date nextMaintainedDate;
    private int availability;
    private String errorMessage = "";
    private int count = 0;
    
    public CreateNewDeviceJPanel(JPanel userProcessContainer, Supplier supplier) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.supplier = supplier;
        
        populateDeviceTypeComboBox();
        populateCountry();
    }

    private void populateDeviceTypeComboBox(){
        cmbType.removeAllItems();
        
        for(Device.TypeOfDevice type : Device.TypeOfDevice.values()){
            cmbType.addItem(type);
        }
    }
    
    private void populateCountry(){
        cmbCountryOfOrigin.removeAllItems();
        
        String[] countries = Locale.getISOCountries();
        
        for(String countryCode: countries){
            Locale locale = new Locale("", countryCode);
            
            cmbCountryOfOrigin.addItem(locale.getDisplayCountry());
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

        lblCreateNewDevice = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtCompanyName = new javax.swing.JTextField();
        cmbType = new javax.swing.JComboBox();
        txtUnitPrice = new javax.swing.JTextField();
        lblType = new javax.swing.JLabel();
        lblCompanyName = new javax.swing.JLabel();
        lblUnitPrice = new javax.swing.JLabel();
        lblCountryOfOrigin = new javax.swing.JLabel();
        lblNextMaintainedDate = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        btnAddDevice = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        cmbCountryOfOrigin = new javax.swing.JComboBox();
        lblAvailability = new javax.swing.JLabel();
        txtAvailability = new javax.swing.JTextField();

        lblCreateNewDevice.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCreateNewDevice.setForeground(new java.awt.Color(102, 204, 255));
        lblCreateNewDevice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCreateNewDevice.setText("Create New Device");

        lblName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblName.setText("Name");

        cmbType.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblType.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblType.setText("Type");

        lblCompanyName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCompanyName.setText("Company Name");

        lblUnitPrice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUnitPrice.setText("Unit Price");

        lblCountryOfOrigin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCountryOfOrigin.setText("Country Of Origin");

        lblNextMaintainedDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNextMaintainedDate.setText("Next Maintain Date");

        btnAddDevice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAddDevice.setText("Add Device");
        btnAddDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDeviceActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        cmbCountryOfOrigin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbCountryOfOrigin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblAvailability.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAvailability.setText("Availability");

        txtAvailability.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btnBack))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(332, 332, 332)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblName)
                                    .addComponent(lblType))
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCreateNewDevice)
                                        .addGap(8, 8, 8))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtUnitPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(cmbCountryOfOrigin, javax.swing.GroupLayout.Alignment.TRAILING, 0, 150, Short.MAX_VALUE)
                                .addComponent(jDateChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblUnitPrice)
                                    .addComponent(lblCompanyName)
                                    .addComponent(lblCountryOfOrigin)
                                    .addComponent(lblNextMaintainedDate)
                                    .addComponent(lblAvailability))
                                .addGap(39, 39, 39)
                                .addComponent(txtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnAddDevice)
                                .addComponent(txtAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(354, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblCreateNewDevice)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblType))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCompanyName))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUnitPrice))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCountryOfOrigin)
                    .addComponent(cmbCountryOfOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNextMaintainedDate)
                    .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAvailability)
                    .addComponent(txtAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAddDevice)
                .addGap(43, 43, 43)
                .addComponent(btnBack)
                .addContainerGap(67, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDeviceActionPerformed
        count = 0;
        errorMessage = "";
        deviceName = txtName.getText().trim();
        deviceType = String.valueOf(cmbType.getSelectedItem());
        companyName = txtCompanyName.getText().trim();
        countryOfOrigin = String.valueOf(cmbCountryOfOrigin.getSelectedItem());
        nextMaintainedDate = (Date)jDateChooser.getDate();
               
        if(deviceName.isEmpty() || deviceType.isEmpty() || companyName.isEmpty() || countryOfOrigin.isEmpty() || nextMaintainedDate == null || txtUnitPrice.getText().trim().isEmpty() || txtAvailability.getText().trim().isEmpty()){
            errorMessage += "You have left the below mentioned field as blank, Please fill them\n";
            
            if(deviceName.isEmpty()){
                count++;
                errorMessage += count+". Device Name\n"; 
            }
            if(deviceType.isEmpty()){
                count++;
                errorMessage += count+". Device Type\n";
            }
            if(companyName.isEmpty()){
                count++;
                errorMessage += count+". Company Name\n";
            }
            if(txtUnitPrice.getText().trim().isEmpty()){
                count++;
                errorMessage += count+". Unit Price\n";
            }
            if(countryOfOrigin.isEmpty()){
                count++;
                errorMessage += count+". Country Of Origin\n";
            }
            if(nextMaintainedDate == null){
                count++;
                errorMessage += count+". Next Manintained Date";
            }
            if(txtAvailability.getText().trim().isEmpty()){
                count++;
                errorMessage += count+". Availability";
            }
        }
        if(!deviceName.matches("[a-zA-Z]+")){
            count++;
            errorMessage += "Invalid Character in Device Name, Device Name can accept only alphabets\n";
        }
        if(!companyName.matches("[a-zA-Z]+")){
            count++;
            errorMessage += "Invalid Character in Company Name, Company Name can accept only alphabets\n";
        }
        
        try{
            unitPrice = Double.parseDouble(txtUnitPrice.getText());
        }catch(NumberFormatException numberFormatException){
            count++;
            errorMessage += "Invalid Character in unit Price Field, Please enter double value in unit Price\n";
        }
        try{
            availability = Integer.parseInt(txtAvailability.getText().trim());
        }
        catch(NumberFormatException numberFormatException){
            count++;
            errorMessage += "Invalid Character in availability Field, Please Enter integer value in availability\n";
        }
        if(nextMaintainedDate != null){
            if(nextMaintainedDate.before(new Date()) || nextMaintainedDate.equals(new Date())){
            count++;
            errorMessage += " Next Maintained Date should be greater than current date";
        }
        }
        
        if(count==0){
            for(int i = 0; i < availability; i++){
                Device device = supplier.getDeviceCatalog().addDevice(deviceName, deviceType, companyName, unitPrice, countryOfOrigin, nextMaintainedDate, availability);
            }
            JOptionPane.showMessageDialog(null, "Device has been created Successfully");
        }
        else{
            JOptionPane.showMessageDialog(null, errorMessage);
        }
    }//GEN-LAST:event_btnAddDeviceActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDevice;
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox cmbCountryOfOrigin;
    private javax.swing.JComboBox cmbType;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel lblAvailability;
    private javax.swing.JLabel lblCompanyName;
    private javax.swing.JLabel lblCountryOfOrigin;
    private javax.swing.JLabel lblCreateNewDevice;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNextMaintainedDate;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblUnitPrice;
    private javax.swing.JTextField txtAvailability;
    private javax.swing.JTextField txtCompanyName;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
