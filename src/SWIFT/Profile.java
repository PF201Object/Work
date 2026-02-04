package SWIFT;

import Config.Config;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public final class Profile extends javax.swing.JPanel {

    public Profile() {
        initComponents();
        setOpaque(false);
    }

    /**
     * Loads user profile data from the Swift.db based on the username.
     * It checks the 'user' table first, then the 'admin' table.
     */
    public void loadUserProfile(String username) {
        // Query for regular users
        String userSql = "SELECT u_name, u_email, u_role, u_contact, status FROM user WHERE u_name = ?";
        // Query for admins
        String adminSql = "SELECT a_username, a_email FROM admin WHERE a_username = ?";

        try (Connection conn = Config.connect()) {
            if (conn == null) return;

            // 1. Try fetching from User table
            try (PreparedStatement pst = conn.prepareStatement(userSql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    lblUsername.setText(rs.getString("u_name").toUpperCase());
                    lblEmail.setText("EMAIL: " + rs.getString("u_email"));
                    lblRole.setText("POSITION: " + rs.getString("u_role"));
                    lblContact.setText("CONTACT: " + rs.getString("u_contact"));
                    lblStatus.setText("STATUS: " + rs.getString("status"));
                    return; // Found user, exit method
                }
            }

            // 2. If not found, try fetching from Admin table
            try (PreparedStatement pst = conn.prepareStatement(adminSql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    lblUsername.setText(rs.getString("a_username").toUpperCase());
                    lblEmail.setText("EMAIL: " + rs.getString("a_email"));
                    lblRole.setText("POSITION: System Administrator");
                    lblContact.setText("CONTACT: N/A");
                    lblStatus.setText("STATUS: Verified Admin");
                }
            }
        } catch (SQLException e) {
            System.err.println("Profile Loading Error: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Faded Black Background Shape
        g2.setColor(new Color(10, 10, 10, 160)); 
        // Sync these with your AbsoluteLayout: X, Y, Width, Height, ArcWidth, ArcHeight
        g2.fillRoundRect(25, 75, 550, 260, 30, 30); 
        
        g2.dispose();
        super.paintComponent(g);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents

    private void initComponents() {
        lblTitle = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        lblContact = new javax.swing.JLabel(); // Replaced Salary with Contact from DB
        lblStatus = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 22)); 
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setText("ACCOUNT PROFILE");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 25, -1, -1));

        lblUsername.setFont(new java.awt.Font("SansSerif", 1, 20)); 
        lblUsername.setForeground(new Color(0, 153, 255));
        add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 400, -1));

        lblRole.setFont(new java.awt.Font("SansSerif", 0, 14));
        lblRole.setForeground(Color.LIGHT_GRAY);
        add(lblRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 400, -1));

        lblEmail.setFont(new java.awt.Font("SansSerif", 0, 14));
        lblEmail.setForeground(Color.LIGHT_GRAY);
        add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 400, -1));

        lblContact.setFont(new java.awt.Font("SansSerif", 0, 14));
        lblContact.setForeground(Color.LIGHT_GRAY);
        add(lblContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 400, -1));

        lblStatus.setFont(new java.awt.Font("SansSerif", 1, 14));
        lblStatus.setForeground(new Color(255, 153, 0));
        add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 400, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
}// End of variables declaration//GEN-END:variables