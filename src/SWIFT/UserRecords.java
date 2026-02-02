package SWIFT;

import Config.Config;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class UserRecords extends javax.swing.JPanel {

    public UserRecords() {
        initComponents(); 
        setOpaque(false);
        applyDashboardTheme(); // Styling logic for Faded Black / Soft Edges
        loadUserData();        // Secured Data logic
    }

    private void applyDashboardTheme() {
        // 1. Setup ScrollPane: Transparent and borderless
        tableScroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        tableScroll.setOpaque(false);
        tableScroll.getViewport().setOpaque(false);
        tableScroll.setViewportBorder(null);

        // 2. Table Styling: Matching "Faded Black"
        tblUsers.setRowHeight(35);
        tblUsers.setShowGrid(false);
        tblUsers.setIntercellSpacing(new Dimension(0, 0));
        
        // Faded inner background (Glassmorphism)
        tblUsers.setBackground(new Color(15, 15, 15, 40)); 
        tblUsers.setForeground(new Color(220, 220, 220)); 
        tblUsers.setSelectionBackground(new Color(255, 255, 255, 30)); 
        tblUsers.setSelectionForeground(Color.WHITE);
        
        // 3. Header Styling: Dark and Bold
        tblUsers.getTableHeader().setReorderingAllowed(false);
        tblUsers.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblUsers.getTableHeader().setBackground(new Color(25, 25, 25)); 
        tblUsers.getTableHeader().setForeground(new Color(180, 180, 180));

        // 4. Center align text for all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false); // Transparency for renderer
        tblUsers.setDefaultRenderer(Object.class, centerRenderer);
    }

    /**
     * SECURED: Loads user data using PreparedStatement
     */
    public void loadUserData() {
        String[] columnNames = {"ID", "Full Name", "Email Address", "Contact #", "User Role", "Account Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        String sql = "SELECT u_id, u_name, u_email, u_contact, u_role, status FROM user";

        try (Connection conn = Config.connect();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("u_id"),
                    rs.getString("u_name"),
                    rs.getString("u_email"),
                    rs.getString("u_contact"),
                    rs.getString("u_role"),
                    rs.getString("status")
                });
            }
            tblUsers.setModel(model);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "User Load Error: " + e.getMessage());
        }
    }

    // Creates the Faded Black Rounded Container
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        tableScroll = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("USER RECORDS");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 400, -1));

        tableScroll.setBorder(null);
        tableScroll.setOpaque(false);

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblUsers.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblUsers.getTableHeader().setReorderingAllowed(false);
        tableScroll.setViewportView(tblUsers);

        add(tableScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 530, 240));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitle;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JTable tblUsers;
    // End of variables declaration//GEN-END:variables
}