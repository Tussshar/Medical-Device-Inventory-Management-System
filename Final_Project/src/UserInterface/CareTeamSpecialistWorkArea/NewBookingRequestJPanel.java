/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.CareTeamSpecialistWorkArea;

import Business.Device;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.HospitalEnterprise;
import Business.Enterprise.WareHouseEnterprise;
import Business.MasterReqDevCatalog;
import Business.Organization.Organization;
import Business.Person.Doctor;
import Business.Person.Person;
import Business.Person.Staff;
import Business.RequiredDeviceCatalog;
import Business.Room;
import Business.RoomDirectory;
import Business.Schedule;
import Business.ScheduleList;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.CareTeamLeaderWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.xml.datatype.DatatypeConstants;
//import org.joda.time.DateTime;
//import org.joda.time.Days;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Tushar
 */
public class NewBookingRequestJPanel extends javax.swing.JPanel {

    /**
     * Creates new form NewBookingRequestJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Organization organization;
    private CareTeamLeaderWorkRequest careTeamLeaderWorkRequest;
    private Enterprise enterprise;
    private EcoSystem ecoSystem;
    private String operationDate;
    private int numberOfStaff;
    private Room room;
    private HospitalEnterprise hospitalEnterprise;
    private ArrayList<Device> deviceList;
    private ArrayList<Device> deviceList1;
    private Device tempDevice;
    private String fromOperationDate;
    private String toOperationDate;
    private int count = 0;
    private String pattern;
    private boolean confirmResult;
    DateTimeFormatter formatter;

    public NewBookingRequestJPanel(JPanel userProcessContainer, UserAccount userAccount, Organization organization, Enterprise enterprise, EcoSystem ecoSystem, CareTeamLeaderWorkRequest careTeamLeaderWorkRequest) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.organization = organization;
        this.careTeamLeaderWorkRequest = careTeamLeaderWorkRequest;
        this.enterprise = enterprise;
        this.ecoSystem = ecoSystem;

        deviceList = new ArrayList<>();

        hospitalEnterprise = (HospitalEnterprise) enterprise;
        pattern = "MM/dd/yyyy";

        formatter = DateTimeFormat.forPattern(pattern);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        fromOperationDate = simpleDateFormat.format(careTeamLeaderWorkRequest.getFromOperationDate());
        toOperationDate = simpleDateFormat.format(careTeamLeaderWorkRequest.getToOperationDate());

        populateDateComboBox();
        populateFields();
        populateDeviceLists();

        if (careTeamLeaderWorkRequest.getStatus().equalsIgnoreCase(WorkRequest.TypeOfStatus.Completed.getValue())) {
            btnScheduleOperation.setEnabled(false);
        } else {
            btnScheduleOperation.setEnabled(true);
        }
    }

    private void populateDateComboBox() {
        cmbOperationDate.removeAllItems();

        DateTime startDate = formatter.parseDateTime(fromOperationDate);
        DateTime endDate = formatter.parseDateTime(toOperationDate);

        Days d = Days.daysBetween(startDate, endDate);
        int days = d.getDays() + 1;

        for (int i = 0; i < days; i++) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(careTeamLeaderWorkRequest.getFromOperationDate());
            calendar.add(GregorianCalendar.DAY_OF_MONTH, i);
            Date d1 = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            cmbOperationDate.addItem(sdf.format(d1));
        }
    }

    private void populateDoctorsAvailabilityComboBox(String date) {
        cmbDoctorsAvailability.removeAllItems();
        int count = 0;
        Doctor doc = (Doctor) careTeamLeaderWorkRequest.getDoctor().getPerson();

        for (String tempDate : doc.getBookedDate()) {
            if (tempDate.equalsIgnoreCase(date)) {
                count++;
            }
        }
        if (count == 0) {
            cmbDoctorsAvailability.addItem("Available");
        } else {
            cmbDoctorsAvailability.addItem("Not Available");
        }
    }

    private void populateRoomComboBox(String date) {
        cmbRoom.removeAllItems();
        int count = 0;

        for (Room room : hospitalEnterprise.getRoomDirectory().getRoomList()) {
            count = 0;
            for (String tempDate : room.getBookedDate()) {
                if (tempDate.equalsIgnoreCase(date)) {
                    count++;
                }
            }
            if (count == 0) {
                cmbRoom.addItem(room);
            }
        }
    }

    private void populateDeviceLists() {

        DefaultListModel defaultListModel = new DefaultListModel();
        defaultListModel.removeAllElements();
        deviceList.removeAll(deviceList);

        for (Enterprise enterprise : ecoSystem.getEnterpriseList()) {
            WareHouseEnterprise wareHouseEnterprise = (WareHouseEnterprise) enterprise;

            for (Device device : wareHouseEnterprise.getDevList()) {
                count = 0;
                for (int i = 0; i < deviceList.size(); i++) {
                    tempDevice = deviceList.get(i);
                    if (device.getDeviceName().equals(tempDevice.getDeviceName())) {
                        count++;
                    }
                }
                if (count == 0) {
                    deviceList.add(device);
                }
            }
        }
        for (Device device : deviceList) {
            defaultListModel.addElement(device);
        }
        jListDevice.setModel(defaultListModel);
    }

    private void populateFields() {
        txtAreaMessage.setText(careTeamLeaderWorkRequest.getMessage());
        txtNumberOfStaffMember.setText(String.valueOf(careTeamLeaderWorkRequest.getNumberOfStaffMembers()));
        txtPatientName.setText(careTeamLeaderWorkRequest.getPatientName());
        txtOperation.setText(careTeamLeaderWorkRequest.getOperation());
        jDCFromOperationDate.setDate(careTeamLeaderWorkRequest.getFromOperationDate());
        jDCToOperationDate.setDate(careTeamLeaderWorkRequest.getToOperationDate());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbpViewOperationDetail = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaMessage = new javax.swing.JTextArea();
        lblOperationInfo = new javax.swing.JLabel();
        lblNumberOfStaffMembers = new javax.swing.JLabel();
        txtNumberOfStaffMember = new javax.swing.JTextField();
        jDCFromOperationDate = new com.toedter.calendar.JDateChooser();
        lblFrom = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTo = new javax.swing.JLabel();
        jDCToOperationDate = new com.toedter.calendar.JDateChooser();
        lblOperationDetails = new javax.swing.JLabel();
        txtPatientName = new javax.swing.JTextField();
        txtOperation = new javax.swing.JTextField();
        lblPatientName = new javax.swing.JLabel();
        lblOperation = new javax.swing.JLabel();
        btnBack1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListDevice = new javax.swing.JList();
        lblOperationDate = new javax.swing.JLabel();
        lblNumberOfStaff = new javax.swing.JLabel();
        txtNumberOfStaff = new javax.swing.JTextField();
        lblRoom = new javax.swing.JLabel();
        cmbRoom = new javax.swing.JComboBox();
        btnScheduleOperation = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblDeviceList = new javax.swing.JLabel();
        cmbOperationDate = new javax.swing.JComboBox();
        cmbDoctorsAvailability = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        lblOperationDetails1 = new javax.swing.JLabel();
        lblComments = new javax.swing.JLabel();
        txtComments = new javax.swing.JTextField();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        txtAreaMessage.setColumns(20);
        txtAreaMessage.setRows(5);
        jScrollPane1.setViewportView(txtAreaMessage);

        lblOperationInfo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblOperationInfo.setText("Operation Info");

        lblNumberOfStaffMembers.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNumberOfStaffMembers.setText("Number of Staff Members");

        lblFrom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFrom.setText("From");

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDate.setText("Date");

        lblTo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTo.setText("To");

        lblOperationDetails.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblOperationDetails.setForeground(new java.awt.Color(102, 204, 255));
        lblOperationDetails.setText("Operation Details");

        lblPatientName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPatientName.setText("Patient Name");

        lblOperation.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblOperation.setText("Operation");

        btnBack1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack1.setText("<< Back");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(230, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDate)
                        .addGap(34, 34, 34)
                        .addComponent(lblFrom))
                    .addComponent(lblNumberOfStaffMembers)
                    .addComponent(lblTo))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOperationDetails)
                    .addComponent(jDCToOperationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumberOfStaffMember, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDCFromOperationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(423, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPatientName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOperation, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtOperation)
                    .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblOperationInfo)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(btnBack1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lblOperationDetails)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblOperationInfo)
                                .addGap(37, 37, 37))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPatientName))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOperation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOperation))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumberOfStaffMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumberOfStaffMembers))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFrom)
                        .addComponent(lblDate))
                    .addComponent(jDCFromOperationDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDCToOperationDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnBack1)
                .addGap(83, 83, 83))
        );

        tbpViewOperationDetail.addTab("View Operation Detail", jPanel1);

        jListDevice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane2.setViewportView(jListDevice);

        lblOperationDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblOperationDate.setText("Date");

        lblNumberOfStaff.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNumberOfStaff.setText("Number of Staff's");

        txtNumberOfStaff.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lblRoom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblRoom.setText("Room");

        cmbRoom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbRoom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnScheduleOperation.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnScheduleOperation.setText("Schedule Operation");
        btnScheduleOperation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScheduleOperationActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblDeviceList.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDeviceList.setText("Device List");

        cmbOperationDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbOperationDate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbOperationDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOperationDateActionPerformed(evt);
            }
        });

        cmbDoctorsAvailability.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbDoctorsAvailability.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Doctor's Availability");

        lblOperationDetails1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblOperationDetails1.setForeground(new java.awt.Color(102, 204, 255));
        lblOperationDetails1.setText("Schedule Operation");

        lblComments.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblComments.setText("Comments");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 261, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblOperationDetails1)
                        .addGap(395, 395, 395))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDeviceList)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(lblNumberOfStaff)
                                    .addComponent(lblRoom)
                                    .addComponent(lblOperationDate))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumberOfStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDoctorsAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbOperationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(214, 214, 214))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(btnBack)
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblComments)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnScheduleOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComments, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblOperationDetails1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lblDeviceList)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtComments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnScheduleOperation)
                        .addGap(103, 103, 103))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblOperationDate)
                                            .addComponent(cmbOperationDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cmbDoctorsAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblNumberOfStaff)
                                            .addComponent(txtNumberOfStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(36, 36, 36)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblRoom)
                                            .addComponent(cmbRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblComments)
                                .addGap(81, 81, 81)))
                        .addComponent(btnBack)
                        .addGap(57, 57, 57))))
        );

        tbpViewOperationDetail.addTab("ScheduleOperation", jPanel2);

        add(tbpViewOperationDetail);
    }// </editor-fold>//GEN-END:initComponents

    private void btnScheduleOperationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScheduleOperationActionPerformed
        confirmResult = true;
        int count = 0;
        String errorMessage = "";
        String availability = (String) cmbDoctorsAvailability.getSelectedItem();
        int staffCheck = 0;
        if (txtNumberOfStaff.getText().trim().isEmpty()) {
            count++;
            staffCheck++;
            errorMessage += "You have left the number of staffs required field as blank\n";
        }
        if (txtComments.getText().trim().isEmpty()) {
            count++;
            errorMessage += "You have left the comments field as blank, Please fill it.\n";
        }
        if (!(txtComments.getText().trim().matches("[a-zA-Z0-9 ]+"))) {
            count++;
            errorMessage += " Comments can contain only characters, numbers and spaces\n";
        }
        if (staffCheck == 0) {
            try {
                numberOfStaff = Integer.parseInt(txtNumberOfStaff.getText().trim());
            } catch (NumberFormatException numberFormatException) {
                count++;
                staffCheck++;
                errorMessage += "Invalid Number in Number of Staff\n";
            }
        }
        if (cmbRoom.getSelectedItem() != null) {
            room = (Room) cmbRoom.getSelectedItem();

            if (room.equals(null)) {

            }
        } else {
            errorMessage += "No rooms are available for the selectedDate\n";
            count++;
        }
        if (availability.equalsIgnoreCase("Not Available")) {
            count++;
            errorMessage += "Doctor already has an operation booked for the selected Date\n";
        }
        if (staffCheck == 0) {
            if (numberOfStaff != careTeamLeaderWorkRequest.getNumberOfStaffMembers()) {
                count++;
                staffCheck++;
                errorMessage += "The value entered in the number of staff is not equal to the number of staff's required\n";
            }
        }

        operationDate = (String) cmbOperationDate.getSelectedItem();

        int[] selectedDevice = jListDevice.getSelectedIndices();

        int selectedListCount = jListDevice.getSelectedIndex();
        if (selectedListCount >= 0) {
            jListDevice.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            deviceList = (ArrayList<Device>) jListDevice.getSelectedValuesList();
            deviceList1 = deviceList;
        } else {
            count++;
            errorMessage += "You have not selected any device, Please select it.";
        }

        MasterReqDevCatalog masterReqDevCatalog;
        RequiredDeviceCatalog requiredDeviceCatalog;
//        ArrayList<Device> tempArrList1 = new ArrayList<>();
//        ArrayList<Device> selectedDeviceList1 = new ArrayList<>();
        int check = 0;

        ArrayList<Staff> staffMembers = new ArrayList<>();

        for (int i = 0; i < numberOfStaff; i++) {
            for (Person person : organization.getPersonDirectory().getPersonList()) {
                if (person instanceof Staff) {
                    int num = 0;
                    Staff s1 = (Staff) person;
                    for (String date : s1.getBookedDate()) {
                        if (date.equalsIgnoreCase(operationDate)) {
                            num++;
                        }
                    }
                    if (num == 0) {
                        staffMembers.add(s1);
                    }
                }
            }
        }

        if (staffMembers.size() < numberOfStaff) {
            count++;
            errorMessage += "The required number of staff is not available.\n";
        }

        if (count == 0) {

            int staffCount = 0;

            for (Staff staff : staffMembers) {
                if (staffCount < numberOfStaff) {
                    staff.getBookedDate().add(operationDate);
                    staffCount++;
                }
            }

            masterReqDevCatalog = new MasterReqDevCatalog();
            requiredDeviceCatalog = new RequiredDeviceCatalog();
            MasterReqDevCatalog mrdc = new MasterReqDevCatalog();
            /*
             Search WareHouse Device Catalog
             suppose if two devices are required for surgical operation, then
             search in warehouse device catalog all the devices whose name is equal to required 1st device name and is in usable condition and whose next maintenance date is not equal to operation date
             put all such devices in first arraylist of device.
             similarly search for second required device.
            
             Then now put both the arraylist of device in masterdevice catalog
             --->RequiredDeviceList is arraylist of device
             --->Master Device Catalog is arraylist of Required Device List
            
             */
            for (Device dev : deviceList) {
                check = 0;
                for (Enterprise enter : ecoSystem.getEnterpriseList()) {
                    if (enter instanceof WareHouseEnterprise) {
                        WareHouseEnterprise whe = (WareHouseEnterprise) enter;
                        for (Device wheDev : whe.getDevList()) {

                            DateFormat date = new SimpleDateFormat(pattern);
                            String wheDevNextMaintenanceDate = date.format(wheDev.getNextMaintainedDate());

                            if (wheDev.getDeviceName().equalsIgnoreCase(dev.getDeviceName()) && wheDev.getDeviceStatus().equalsIgnoreCase(Device.DeviceStatus.Usable.getStatus()) && wheDevNextMaintenanceDate != operationDate) {
                                check++;
                                requiredDeviceCatalog.addDevice(wheDev);
                            }
                        }
                    }
                }
                if (check != 0) {
                    masterReqDevCatalog.addRequiredDevice(requiredDeviceCatalog);
                }
            }

            /*
             Schedule is a object which contains detail of the device which is booked.
             It tells us about which device is booked by which hospital on which date
             Schedule List is collection of schedule objects.
            
             Now for each device in masterRequiredDeviceCatalog,
             we will check if that particular device is already scheduled for the required operation date
             if it is the we will remove that particular device from the specific arralist of device in the masterrequired device catalog
            
             */
            MasterReqDevCatalog mrctemp = new MasterReqDevCatalog();
            for (RequiredDeviceCatalog tempArrayList1 : masterReqDevCatalog.getMasterReqDevCatalog()) {
                RequiredDeviceCatalog tempCatalog = new RequiredDeviceCatalog();

                for (Device tempdev : tempArrayList1.getDeviceList()) {
                    int tempCount = 0;
                    for (Enterprise enter : ecoSystem.getEnterpriseList()) {
                        if (enter instanceof WareHouseEnterprise) {
                            WareHouseEnterprise whe = (WareHouseEnterprise) enter;
                            for (Schedule schedule : whe.getScheduleList().getScheduleCatalog()) {
                                Device scheduledDevice = schedule.getDevice();
                                String scheduledDate = schedule.getDate();
                                if ((scheduledDevice.getUniqueDeviceIdentifier().equalsIgnoreCase(tempdev.getUniqueDeviceIdentifier())) && (scheduledDate.equalsIgnoreCase(operationDate))) {

                                    tempCatalog.addDevice(tempdev);
                                }
                            }
                        }
                    }
                }
                mrctemp.addRequiredDevice(tempCatalog);
            }

            for (RequiredDeviceCatalog catalog : mrctemp.getMasterReqDevCatalog()) {
                for (Device d : catalog.getDeviceList()) {
                    for (RequiredDeviceCatalog catalog1 : masterReqDevCatalog.getMasterReqDevCatalog()) {
                        catalog1.getDeviceList().remove(d);
                    }
                }
            }

            /*
             Now in the MaterRequiredDeviceCatalog, I have arraylists
             which consists of the required devices
             Now for each required device, find the device from the arraylist which is used least
             Then for the selected device make a schedule object and add it in the scheduleList
             and increase its availability by one which, in warehouse enterprise, indicates number of times the device is booked.
            
             deviceList is a arraylist of device which indicates the the devices required for surgical operation.
             Now for the device whose schedule object is created, delete it from the deviceList.
             So at the end if any device remains in devicelist then that device was not available in the warehouse for the required date. 
             */
            int mrdCatalog = 0;
            int deviceListCount = deviceList.size();
            for (RequiredDeviceCatalog tempArrayList1 : masterReqDevCatalog.getMasterReqDevCatalog()) {
                mrdCatalog++;
            }
            if (mrdCatalog == 0) {
                count++;
                JOptionPane.showMessageDialog(null, "No devices are available");
            } else {

                if (mrdCatalog < deviceListCount) {

                    for (RequiredDeviceCatalog tempArrayList1 : masterReqDevCatalog.getMasterReqDevCatalog()) {
                        Device selDev = tempArrayList1.getDeviceList().get(0);
                        for (int i = 0; i < tempArrayList1.getDeviceList().size(); i++) {
                            if (tempArrayList1.getDeviceList().get(i).getAvailability() < selDev.getAvailability()) {
                                selDev = tempArrayList1.getDeviceList().get(i);
                            }
                        }

                        ArrayList<Device> deviceList2 = new ArrayList<>();
                        for (Device dev : deviceList) {
                            if (dev.getDeviceName().equalsIgnoreCase(selDev.getDeviceName())) {
                                deviceList2.add(dev);
                            }
                        }

                        for (Device dev : deviceList2) {
                            deviceList1.remove(dev);
                        }

                    }
                    String deviceUnavailable = "";

                    if (!deviceList.isEmpty()) {
                        deviceUnavailable += "The below mentioned devices are not available\n";
                        for (Device dev : deviceList) {
                            deviceUnavailable += dev.getDeviceName() + "\n";
                        }

                    }
                    JOptionPane.showConfirmDialog(null, deviceUnavailable);
                    int confirmButton = JOptionPane.YES_NO_OPTION;

                    if (confirmButton == JOptionPane.YES_OPTION) {
                        confirmResult = true;
                    }
                }

                if (confirmResult) {
                    for (RequiredDeviceCatalog tempArrayList1 : masterReqDevCatalog.getMasterReqDevCatalog()) {
                        Device selDev = tempArrayList1.getDeviceList().get(0);
                        for (int i = 0; i < tempArrayList1.getDeviceList().size(); i++) {
                            if (tempArrayList1.getDeviceList().get(i).getAvailability() < selDev.getAvailability()) {
                                selDev = tempArrayList1.getDeviceList().get(i);
                            }
                        }
                        selDev.setAvailability(selDev.getAvailability() + 1);
                        Schedule schedule = new Schedule();
                        schedule.setDevice(selDev);
                        schedule.setEnterprise(hospitalEnterprise);
                        schedule.setDate(operationDate);
                        Doctor operatingDoctor = (Doctor) careTeamLeaderWorkRequest.getDoctor().getPerson();
                        operatingDoctor.getBookedDate().add(operationDate);
                        room.getBookedDate().add(operationDate);
                        careTeamLeaderWorkRequest.setResolveDate(new Date());
                        careTeamLeaderWorkRequest.setStatus(WorkRequest.TypeOfStatus.Completed.getValue());
                        careTeamLeaderWorkRequest.setTestResults(WorkRequest.TypeOfResult.Approve.getValue());
                        careTeamLeaderWorkRequest.setResolveDate(new Date());
                        careTeamLeaderWorkRequest.setRoomNumber(room.getRoomNumber());
                        careTeamLeaderWorkRequest.setStatus(WorkRequest.TypeOfStatus.Completed.getValue());
                        careTeamLeaderWorkRequest.setTestResults(WorkRequest.TypeOfResult.Approve.getValue());
                        careTeamLeaderWorkRequest.setOperationDate(operationDate);
                        careTeamLeaderWorkRequest.setMessage(txtComments.getText().trim());
                        UserAccount doc = careTeamLeaderWorkRequest.getDoctor();

                        doc.getWorkQueue().getWorkRequestList().add(careTeamLeaderWorkRequest);

                        ArrayList<Device> deviceList2 = new ArrayList<>();
                        for (Device dev : deviceList) {
                            if (dev.getDeviceName().equalsIgnoreCase(selDev.getDeviceName())) {
                                deviceList2.add(dev);
                            }
                        }

                        for (Device dev : deviceList2) {
                            deviceList.remove(dev);
                        }

                        for (Enterprise enter : ecoSystem.getEnterpriseList()) {
                            if (enter instanceof WareHouseEnterprise) {
                                WareHouseEnterprise whe = (WareHouseEnterprise) enter;
                                whe.getScheduleList().getScheduleCatalog().add(schedule);
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null, "The operation has been booked successfully.\n");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, errorMessage);
        }
    }//GEN-LAST:event_btnScheduleOperationActionPerformed

    private void cmbOperationDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOperationDateActionPerformed

        String operationDate = (String) cmbOperationDate.getSelectedItem();

        if (operationDate != null) {
            populateDoctorsAvailabilityComboBox(operationDate);
            populateRoomComboBox(operationDate);
        }
    }//GEN-LAST:event_cmbOperationDateActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnBack1ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnScheduleOperation;
    private javax.swing.JComboBox cmbDoctorsAvailability;
    private javax.swing.JComboBox cmbOperationDate;
    private javax.swing.JComboBox cmbRoom;
    private com.toedter.calendar.JDateChooser jDCFromOperationDate;
    private com.toedter.calendar.JDateChooser jDCToOperationDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jListDevice;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblComments;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDeviceList;
    private javax.swing.JLabel lblFrom;
    private javax.swing.JLabel lblNumberOfStaff;
    private javax.swing.JLabel lblNumberOfStaffMembers;
    private javax.swing.JLabel lblOperation;
    private javax.swing.JLabel lblOperationDate;
    private javax.swing.JLabel lblOperationDetails;
    private javax.swing.JLabel lblOperationDetails1;
    private javax.swing.JLabel lblOperationInfo;
    private javax.swing.JLabel lblPatientName;
    private javax.swing.JLabel lblRoom;
    private javax.swing.JLabel lblTo;
    private javax.swing.JTabbedPane tbpViewOperationDetail;
    private javax.swing.JTextArea txtAreaMessage;
    private javax.swing.JTextField txtComments;
    private javax.swing.JTextField txtNumberOfStaff;
    private javax.swing.JTextField txtNumberOfStaffMember;
    private javax.swing.JTextField txtOperation;
    private javax.swing.JTextField txtPatientName;
    // End of variables declaration//GEN-END:variables
}
