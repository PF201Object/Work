package SWIFT;

import Config.Config;
import javax.swing.JOptionPane;
import java.awt.Color;

public class LoginForm extends javax.swing.JPanel { // Changed to JPanel

    private final DashboardForm dashboard;

    public LoginForm(DashboardForm parent) {
        this.dashboard = parent;        
        Config.initializeDB();
        initComponents();
        customInit(); 
    }

    private void customInit() {
        // Panels don't need setBackground(0,0,0,0) or setLocationRelativeTo
        // Hide hover effects initially
        Login1.setVisible(false);
        Register.setVisible(false); 
        
        // Ensure the panel is transparent so the Dashboard background shows through
        this.setOpaque(false);
    }

    private void handleLoginProcess() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String role = Config.getUserRole(username, password);

        if (role != null) {
            btnLog.setEnabled(false);
            
            new Thread(() -> {
                java.awt.EventQueue.invokeLater(() -> { 
                    Login1.setVisible(true); 
                    repaint(); 
                });
                
                try { Thread.sleep(500); } catch (InterruptedException e) {}

                java.awt.EventQueue.invokeLater(() -> {
                    // SECURED TRANSITION: Tells the Dashboard login was successful
                    dashboard.loginSuccess(role); 
                });
            }).start();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLog = new javax.swing.JButton();
        Login1 = new javax.swing.JLabel();
        Login = new javax.swing.JLabel();
        btnReg = new javax.swing.JButton();
        Register = new javax.swing.JLabel();
        Register1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(760, 560));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLog.setBorder(null);
        btnLog.setContentAreaFilled(false);
        btnLog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogMouseExited(evt);
            }
        });
        add(btnLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 150, 60));

        Login1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Log1i.gif"))); // NOI18N
        add(Login1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 170, 60));

        Login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Logi.png"))); // NOI18N
        add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 180, -1));

        btnReg.setBorder(null);
        btnReg.setContentAreaFilled(false);
        btnReg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegMouseExited(evt);
            }
        });
        add(btnReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 150, 40));

        Register.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Register.gif"))); // NOI18N
        add(Register, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, 50));

        Register1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Register.png"))); // NOI18N
        add(Register1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 200, 60));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 140, 40));
        add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 170, 30));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 140, 40));
        add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 170, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/bg2.png"))); // NOI18N
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, -80, 740, 520));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogMouseEntered(java.awt.event.MouseEvent evt) {                                    
        Login1.setVisible(true);
        repaint();
    }                                   

    private void btnLogMouseExited(java.awt.event.MouseEvent evt) {                                   
        Login1.setVisible(false);
        repaint();
    }                                  

    private void btnLogMouseClicked(java.awt.event.MouseEvent evt) {                                    
        handleLoginProcess();
    }                                   

    private void btnRegMouseEntered(java.awt.event.MouseEvent evt) {                                    
        Register.setVisible(true);
        repaint();
    }                                   

    private void btnRegMouseExited(java.awt.event.MouseEvent evt) {                                   
        Register.setVisible(false);
        repaint();
    }                                  

    private void btnRegMouseClicked(java.awt.event.MouseEvent evt) {                                    
        // Switch to registration panel instead of opening a new window
        dashboard.showRegister();
    }                                   
                                                                                          
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Login;
    private javax.swing.JLabel Login1;
    private javax.swing.JLabel Register;
    private javax.swing.JLabel Register1;
    private javax.swing.JButton btnLog;
    private javax.swing.JButton btnReg;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}