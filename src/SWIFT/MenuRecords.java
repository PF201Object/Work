package SWIFT;

import Config.Config;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class MenuRecords extends javax.swing.JPanel {

    public MenuRecords() {
        initComponents();
        setOpaque(false);
        applyDashboardTheme(); 
        loadMenuData();
    }

    private void applyDashboardTheme() {
        // 1. Setup ScrollPane: Transparent and borderless
        tableScroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        tableScroll.setOpaque(false);
        tableScroll.getViewport().setOpaque(false);
        tableScroll.setViewportBorder(null);

        // 2. Table Styling: "Faded Black" Glassmorphism
        tblMenu.setRowHeight(35);
        tblMenu.setShowGrid(false);
        tblMenu.setIntercellSpacing(new Dimension(0, 0));
        
        tblMenu.setBackground(new Color(15, 15, 15, 40)); 
        tblMenu.setForeground(new Color(220, 220, 220)); 
        tblMenu.setSelectionBackground(new Color(255, 255, 255, 30)); 
        tblMenu.setSelectionForeground(Color.WHITE);
        
        // 3. Header Styling
        tblMenu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblMenu.getTableHeader().setBackground(new Color(25, 25, 25)); 
        tblMenu.getTableHeader().setForeground(new Color(180, 180, 180));
        tblMenu.getTableHeader().setBorder(new EmptyBorder(0,0,0,0));
        
        // 4. Align text to center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false); 
        tblMenu.setDefaultRenderer(Object.class, centerRenderer);
    }

    public void loadMenuData() {
        // Matching column names to your ERD image
        String[] columnNames = {"ID", "Item Name", "Description", "Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        // SQL using the table name and fields from your ERD image
        String sql = "SELECT m_id, m_name, m_desc, m_price FROM menu";

        try (Connection conn = Config.connect();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("m_id"),
                    rs.getString("m_name"),
                    rs.getString("m_desc"),
                    "₱ " + String.format("%.2f", rs.getDouble("m_price"))
                });
            }
            tblMenu.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Dark glass container background
        g2.setColor(new Color(10, 10, 10, 160)); 
        g2.fillRoundRect(25, 75, 550, 260, 30, 30); 
        
        g2.dispose();
        super.paintComponent(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        tableScroll = new javax.swing.JScrollPane();
        tblMenu = new javax.swing.JTable();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("MENU LIST");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 290, 30));

        tableScroll.setBorder(null);
        tableScroll.setOpaque(false);

        tblMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {}
        ));
        tableScroll.setViewportView(tblMenu);

        add(tableScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 530, 240));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitle;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JTable tblMenu;
    // End of variables declaration//GEN-END:variables
}