package SWIFT;

import Config.Config;
import java.awt.Color;
import javax.swing.JOptionPane;

public class RegistrationForm extends javax.swing.JPanel { // Changed to JPanel

    private final DashboardForm dashboard;

    // Updated Constructor
    public RegistrationForm(DashboardForm parent) {
        this.dashboard = parent;
        initComponents();
        customInit();
    }

private void customInit() {
        // Panels are transparent to let the Dashboard background shine through
        this.setOpaque(false);
        
        // Remove lines/frames from Submit Button
        btnSubmit.setFocusPainted(false);
        btnSubmit.setBorderPainted(false);
        btnSubmit.setContentAreaFilled(false);
        
        // Remove lines/frames from Admin Button
        btnAdminReg.setFocusPainted(false);
        btnAdminReg.setBorderPainted(false);
        btnAdminReg.setContentAreaFilled(false);
        
        // Remove lines/frames from Back Button
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        
        RegisterGIF.setVisible(false);
        RegisterGIF.setFocusable(false);
    }

        private void handleRegistration(boolean isAdminRequest) {
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword());
        String contact = txtContact.getText().trim();
        String role = isAdminRequest ? "Admin" : comboRole.getSelectedItem().toString();
        String status = "Active";

        // 1. Basic Empty Validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Email Validation (Must contain @)
        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Email address!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Contact Number Validation (Must be exactly 11 digits)
        // matches("\\d{11}") checks if the string contains only numbers and is 11 chars long
        if (!contact.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(this, "Contact number must be exactly 11 digits!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 4. Double Account Validation (Same Name)
        // Note: You need to implement 'isUserExists' in your Config class
        if (Config.isUserExists(name)) {
            JOptionPane.showMessageDialog(this, "An account with this name already exists!", "Registration Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 5. Admin Key Logic
        if (isAdminRequest) {
            String key = JOptionPane.showInputDialog(this, "Enter Admin Registration Key:", "Authentication", JOptionPane.WARNING_MESSAGE);
            if (!"Admin123".equals(key)) {
                JOptionPane.showMessageDialog(this, "Invalid Admin Key!", "Access Denied", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Save to Database
        boolean success = Config.registerUser(name, email, password, contact, role, status);
        
        if (success) {
            JOptionPane.showMessageDialog(this, role + " Registered Successfully!");
            dashboard.showLogin(); 
        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed!", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comboRole = new javax.swing.JComboBox<>();
        btnSubmit = new javax.swing.JButton();
        btnAdminReg = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        RegisterGIF = new javax.swing.JLabel();
        RegisterStatic = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Full Name:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 100, 30));
        add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 160, 25));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Email:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 100, 30));
        add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 160, 25));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 100, 30));
        add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 160, 25));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contact:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 100, 30));
        add(txtContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 160, 25));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Role:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 100, 30));

        comboRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Catering Manager", "Chef/Cook", "Waitstaff/Server", "Bartender", "Kitchen Assistant", "Event Coordinator" }));
        comboRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRoleActionPerformed(evt);
            }
        });
        add(comboRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 120, 25));

        btnSubmit.setContentAreaFilled(false);
        btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSubmitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSubmitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSubmitMouseExited(evt);
            }
        });
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 130, 40));

        btnAdminReg.setFont(new java.awt.Font("Segoe UI", 3, 10)); // NOI18N
        btnAdminReg.setForeground(new java.awt.Color(153, 255, 255));
        btnAdminReg.setText("Admin Registration");
        btnAdminReg.setContentAreaFilled(false);
        btnAdminReg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminRegActionPerformed(evt);
            }
        });
        add(btnAdminReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 140, 30));

        btnBack.setFont(new java.awt.Font("Visitor TT1 BRK", 0, 12)); // NOI18N
        btnBack.setForeground(new java.awt.Color(204, 204, 204));
        btnBack.setText("Back to Login");
        btnBack.setContentAreaFilled(false);
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 120, 20));

        RegisterGIF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Register.gif"))); // NOI18N
        add(RegisterGIF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 170, 60));

        RegisterStatic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Register.png"))); // NOI18N
        add(RegisterStatic, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, -1, 50));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/bg2.png"))); // NOI18N
        add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, -80, 740, 520));
    }// </editor-fold>//GEN-END:initComponents

    private void comboRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboRoleActionPerformed

    private void btnSubmitMouseClicked(java.awt.event.MouseEvent evt) {                                       
        handleRegistration(false);
    }                                      

    private void btnSubmitMouseEntered(java.awt.event.MouseEvent evt) {                                       
        RegisterGIF.setVisible(true); 
        repaint();
    }                                      

    private void btnSubmitMouseExited(java.awt.event.MouseEvent evt) {                                      
        RegisterGIF.setVisible(false); 
        repaint();
    }                                     

    private void btnAdminRegActionPerformed(java.awt.event.ActionEvent evt) {                                            
        handleRegistration(true);
    }                                           

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // Navigate back to Login panel
        dashboard.showLogin(); 
    }                                       

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel RegisterGIF;
    private javax.swing.JLabel RegisterStatic;
    private javax.swing.JButton btnAdminReg;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox<String> comboRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}