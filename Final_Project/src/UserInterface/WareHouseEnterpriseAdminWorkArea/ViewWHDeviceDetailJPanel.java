/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.WareHouseEnterpriseAdminWorkArea;

import Business.Device;
import Business.DeviceCatalog;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.HospitalEnterprise;
import Business.Enterprise.WareHouseEnterprise;
import Business.Schedule;
import java.awt.CardLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tushar
 */
public class ViewWHDeviceDetailJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ViewWHDeviceDetailJPanel
     */
    private JPanel userProcessContainer;
    private Enterprise enterprise;
    private WareHouseEnterprise wareHouseEnterprise;

    public ViewWHDeviceDetailJPanel(JPanel userProcessContainer, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.enterprise = enterprise;
        wareHouseEnterprise = (WareHouseEnterprise) enterprise;

        populateScheduleHistoryTable();
        populateIndividualDeviceDetailTable();
        populateDeviceDetailTable();
        populateDevicesUnderMaintenanceTable();
    }

    private void populateIndividualDeviceDetailTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblIndividualDeviceDetail.getModel();
        defaultTableModel.setRowCount(0);

        for (Device device : wareHouseEnterprise.getDevList()) {

            Object[] row = new Object[4];

            row[0] = device;
            row[1] = device.getUniqueDeviceIdentifier();

            int count = 0;

            for (Schedule sch : wareHouseEnterprise.getScheduleList().getScheduleCatalog()) {
                Device dev = sch.getDevice();
                Enterprise enter = sch.getEnterprise();
                if (enter instanceof HospitalEnterprise) {
                    if (dev.getUniqueDeviceIdentifier().equalsIgnoreCase(device.getUniqueDeviceIdentifier())) {
                        count++;
                    }
                }

            }

            row[2] = count;
            row[3] = device.getNextMaintainedDate();

            defaultTableModel.addRow(row);
        }
    }

    private void populateScheduleHistoryTable() {

        DefaultTableModel defaultTableModel = (DefaultTableModel) tblScheduleHistory.getModel();
        defaultTableModel.setRowCount(0);

        for (Schedule schedule : wareHouseEnterprise.getScheduleList().getScheduleCatalog()) {
            Device dev = schedule.getDevice();
            Enterprise enter = schedule.getEnterprise();
            if (enter instanceof HospitalEnterprise) {
                Object[] row = new Object[4];

                row[0] = dev.getDeviceName();
                row[1] = dev.getUniqueDeviceIdentifier();
                row[2] = schedule.getEnterprise();
                row[3] = schedule.getDate();

                defaultTableModel.addRow(row);
            }

        }
    }

    private void populateDeviceDetailTable() {

        DefaultTableModel defaultTableModel = (DefaultTableModel) tblDeviceDetail.getModel();
        defaultTableModel.setRowCount(0);
        int check = 0;
        ArrayList<Device> devList = new ArrayList<>();
        for (Device device : wareHouseEnterprise.getDevList()) {
            check = 0;
            for (Device dev : devList) {
                if (dev.getDeviceName().equalsIgnoreCase(device.getDeviceName())) {
                    check++;
                }
            }
            if (check == 0) {
                devList.add(device);
            }
        }

        for (Device dev : devList) {
            Object[] row = new Object[2];

            row[0] = dev;

            int count = 0;

            for (Schedule sch : wareHouseEnterprise.getScheduleList().getScheduleCatalog()) {
                Device device = sch.getDevice();
                Enterprise enter = sch.getEnterprise();
                if (enter instanceof HospitalEnterprise) {
                    if (dev.getDeviceName().equalsIgnoreCase(device.getDeviceName())) {
                        count++;
                    }
                }

            }
            row[1] = count;

            defaultTableModel.addRow(row);
        }
    }

    private void populateDevicesUnderMaintenanceTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblDevicesUnderMaintenance.getModel();
        defaultTableModel.setRowCount(0);

        Date presentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        for (Schedule schedule : wareHouseEnterprise.getScheduleList().getScheduleCatalog()) {
            Enterprise enter = schedule.getEnterprise();
            if (enter instanceof WareHouseEnterprise) {
                if (schedule.getDate().equalsIgnoreCase(sdf.format(presentDate))) {
                    Object[] row = new Object[2];

                    row[0] = schedule.getDevice();
                    row[1] = schedule.getDevice().getUniqueDeviceIdentifier();

                    defaultTableModel.addRow(row);
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

        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblToBeMaintainedDevice = new javax.swing.JTable();
        lblScheduleHistory2 = new javax.swing.JLabel();
        btnSendAllDevicesForMaintenance = new javax.swing.JButton();
        btnBack3 = new javax.swing.JButton();
        tbpScheduleHistory = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblIndividualDeviceDetail = new javax.swing.JTable();
        lblIndividualDeviceDetail = new javax.swing.JLabel();
        btnViewDetail = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnSendDeviceForMaintenance = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblScheduleHistory = new javax.swing.JTable();
        lblScheduleHistory = new javax.swing.JLabel();
        btnBack1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDeviceDetail = new javax.swing.JTable();
        lblScheduleHistory1 = new javax.swing.JLabel();
        btnBack2 = new javax.swing.JButton();
        btnViewDeviceDetail = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDevicesUnderMaintenance = new javax.swing.JTable();
        lblScheduleHistory3 = new javax.swing.JLabel();
        btnBack4 = new javax.swing.JButton();

        tblToBeMaintainedDevice.setBackground(new java.awt.Color(255, 153, 0));
        tblToBeMaintainedDevice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblToBeMaintainedDevice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Device Name", "Unique Device ID", "Next Maintenance Date"
            }
        ));
        jScrollPane4.setViewportView(tblToBeMaintainedDevice);
        if (tblToBeMaintainedDevice.getColumnModel().getColumnCount() > 0) {
            tblToBeMaintainedDevice.getColumnModel().getColumn(2).setHeaderValue("Next Maintenance Date");
        }

        lblScheduleHistory2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblScheduleHistory2.setForeground(new java.awt.Color(102, 204, 255));
        lblScheduleHistory2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScheduleHistory2.setText("Devices to be maintained in next 7 Days");

        btnSendAllDevicesForMaintenance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSendAllDevicesForMaintenance.setText("Send all Devices for Maintenance");

        btnBack3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack3.setText("<< Back");
        btnBack3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(399, 399, 399)
                        .addComponent(lblScheduleHistory2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(635, 635, 635)
                        .addComponent(btnSendAllDevicesForMaintenance))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(btnBack3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(lblScheduleHistory2)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnSendAllDevicesForMaintenance)
                .addGap(2, 2, 2)
                .addComponent(btnBack3)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        tblIndividualDeviceDetail.setBackground(new java.awt.Color(255, 153, 0));
        tblIndividualDeviceDetail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblIndividualDeviceDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Device Name", "Unique Identifier", "Number of times Used", "Next Maintained Date"
            }
        ));
        jScrollPane1.setViewportView(tblIndividualDeviceDetail);

        lblIndividualDeviceDetail.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblIndividualDeviceDetail.setForeground(new java.awt.Color(102, 204, 255));
        lblIndividualDeviceDetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIndividualDeviceDetail.setText("Individual Device Detail");

        btnViewDetail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnViewDetail.setText("View Detail");
        btnViewDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnSendDeviceForMaintenance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSendDeviceForMaintenance.setText("Send a Device for maintenance");
        btnSendDeviceForMaintenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendDeviceForMaintenanceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(462, 462, 462)
                        .addComponent(lblIndividualDeviceDetail))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(btnSendDeviceForMaintenance)
                        .addGap(18, 18, 18)
                        .addComponent(btnViewDetail))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btnBack))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(lblIndividualDeviceDetail)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewDetail)
                    .addComponent(btnSendDeviceForMaintenance))
                .addGap(27, 27, 27)
                .addComponent(btnBack)
                .addGap(62, 62, 62))
        );

        tbpScheduleHistory.addTab("Individual Device Detail", jPanel1);

        tblScheduleHistory.setBackground(new java.awt.Color(255, 153, 0));
        tblScheduleHistory.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblScheduleHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Device Name", "Unique Device ID", "Hospital", "Date"
            }
        ));
        tblScheduleHistory.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(tblScheduleHistory);

        lblScheduleHistory.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblScheduleHistory.setForeground(new java.awt.Color(102, 204, 255));
        lblScheduleHistory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScheduleHistory.setText("Schedule History");

        btnBack1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack1.setText("<< Back");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(btnBack1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1097, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(503, 503, 503)
                        .addComponent(lblScheduleHistory)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(lblScheduleHistory)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnBack1)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        tbpScheduleHistory.addTab("Schedule History", jPanel2);

        tblDeviceDetail.setBackground(new java.awt.Color(255, 153, 0));
        tblDeviceDetail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblDeviceDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Device Name", "Number of times used"
            }
        ));
        jScrollPane3.setViewportView(tblDeviceDetail);

        lblScheduleHistory1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblScheduleHistory1.setForeground(new java.awt.Color(102, 204, 255));
        lblScheduleHistory1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScheduleHistory1.setText("Device Detail");

        btnBack2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack2.setText("<< Back");
        btnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2ActionPerformed(evt);
            }
        });

        btnViewDeviceDetail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnViewDeviceDetail.setText("View Detail");
        btnViewDeviceDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDeviceDetailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnViewDeviceDetail)
                        .addGap(556, 556, 556))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(btnBack2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(511, 511, 511)
                        .addComponent(lblScheduleHistory1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(lblScheduleHistory1)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnViewDeviceDetail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(btnBack2)
                .addGap(53, 53, 53))
        );

        tbpScheduleHistory.addTab("Device Detail", jPanel3);

        tblDevicesUnderMaintenance.setBackground(new java.awt.Color(255, 153, 0));
        tblDevicesUnderMaintenance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblDevicesUnderMaintenance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Device Name", "Unique Device ID"
            }
        ));
        jScrollPane5.setViewportView(tblDevicesUnderMaintenance);

        lblScheduleHistory3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblScheduleHistory3.setForeground(new java.awt.Color(102, 204, 255));
        lblScheduleHistory3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScheduleHistory3.setText("Devices under Maintenance");

        btnBack4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack4.setText("<< Back");
        btnBack4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 73, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(445, 445, 445)
                        .addComponent(lblScheduleHistory3))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(btnBack4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(lblScheduleHistory3)
                .addGap(56, 56, 56)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnBack4)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        tbpScheduleHistory.addTab("Devices Under Maintenance", jPanel5);

        add(tbpScheduleHistory);
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailActionPerformed
        int selectedRow = tblIndividualDeviceDetail.getSelectedRow();

        if (selectedRow >= 0) {
            Device dev = (Device) tblIndividualDeviceDetail.getValueAt(selectedRow, 0);
            int count = (int) tblIndividualDeviceDetail.getValueAt(selectedRow, 2);

            if (count > 0) {
                JFrame frame = new JFrame();
                IndividualDeviceBookingHistoryJDialog idbhjd = new IndividualDeviceBookingHistoryJDialog(frame, true, wareHouseEnterprise, dev, count);
                idbhjd.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "This device has not been booked yet");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_btnViewDetailActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnViewDeviceDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDeviceDetailActionPerformed
        int selectedRpw = tblDeviceDetail.getSelectedRow();

        if (selectedRpw >= 0) {
            Device dev = (Device) tblDeviceDetail.getValueAt(selectedRpw, 0);
            int count = (int) tblDeviceDetail.getValueAt(selectedRpw, 1);

            if (count > 0) {
                JFrame frame = new JFrame();
                DeviceBookingHistoryJDialog idbhjd = new DeviceBookingHistoryJDialog(frame, true, wareHouseEnterprise, dev, count);
                idbhjd.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "This device has not been booked yet");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_btnViewDeviceDetailActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBack1ActionPerformed

    private void btnBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2ActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBack2ActionPerformed

    private void btnBack3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack3ActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBack3ActionPerformed

    private void btnBack4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack4ActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBack4ActionPerformed

    private void btnSendDeviceForMaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendDeviceForMaintenanceActionPerformed
        int selectedRow = tblIndividualDeviceDetail.getSelectedRow();

        if (selectedRow >= 0) {
            Device device = (Device) tblIndividualDeviceDetail.getValueAt(selectedRow, 0);
            Schedule schedule = new Schedule();

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

            schedule.setDate(sdf.format(date));
            schedule.setDevice(device);
            schedule.setEnterprise(wareHouseEnterprise);

            Date d = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.DATE, 30);

            device.setNextMaintainedDate(cal.getTime());

            wareHouseEnterprise.getScheduleList().getScheduleCatalog().add(schedule);

            populateDevicesUnderMaintenanceTable();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_btnSendDeviceForMaintenanceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnBack2;
    private javax.swing.JButton btnBack3;
    private javax.swing.JButton btnBack4;
    private javax.swing.JButton btnSendAllDevicesForMaintenance;
    private javax.swing.JButton btnSendDeviceForMaintenance;
    private javax.swing.JButton btnViewDetail;
    private javax.swing.JButton btnViewDeviceDetail;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblIndividualDeviceDetail;
    private javax.swing.JLabel lblScheduleHistory;
    private javax.swing.JLabel lblScheduleHistory1;
    private javax.swing.JLabel lblScheduleHistory2;
    private javax.swing.JLabel lblScheduleHistory3;
    private javax.swing.JTable tblDeviceDetail;
    private javax.swing.JTable tblDevicesUnderMaintenance;
    private javax.swing.JTable tblIndividualDeviceDetail;
    private javax.swing.JTable tblScheduleHistory;
    private javax.swing.JTable tblToBeMaintainedDevice;
    private javax.swing.JTabbedPane tbpScheduleHistory;
    // End of variables declaration//GEN-END:variables
}
