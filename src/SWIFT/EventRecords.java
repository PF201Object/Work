package SWIFT;

import Config.Config;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class EventRecords extends javax.swing.JPanel {

    public EventRecords() {
        initComponents();
        setOpaque(false);
        applyDashboardTheme(); 
        loadEventData();
    }

    private void applyDashboardTheme() {
        tableScroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        tableScroll.setOpaque(false);
        tableScroll.getViewport().setOpaque(false);

        tblEvents.setRowHeight(35);
        tblEvents.setShowGrid(false);
        tblEvents.setIntercellSpacing(new Dimension(0, 0));
        tblEvents.setBackground(new Color(15, 15, 15, 40)); 
        tblEvents.setForeground(new Color(220, 220, 220)); 
        tblEvents.setSelectionBackground(new Color(255, 255, 255, 30)); 
        
        tblEvents.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblEvents.getTableHeader().setBackground(new Color(25, 25, 25)); 
        tblEvents.getTableHeader().setForeground(new Color(180, 180, 180));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false); 
        tblEvents.setDefaultRenderer(Object.class, centerRenderer);
    }

    public void loadEventData() {
        String[] columnNames = {"ID", "Event Name", "Type", "Date", "Location", "Guests"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        // SQL using fields from your second ERD image
        String sql = "SELECT e_id, e_name, e_type, s_date, e_location, e_guest FROM event";

        try (Connection conn = Config.connect();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("e_id"),
                    rs.getString("e_name"),
                    rs.getString("e_type"),
                    rs.getString("s_date"),
                    rs.getString("e_location"),
                    rs.getInt("e_guest")
                });
            }
            tblEvents.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
        tblEvents = new javax.swing.JTable();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("EVENT RECORDS");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 290, 30));

        tableScroll.setBorder(null);
        tableScroll.setOpaque(false);

        tblEvents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableScroll.setViewportView(tblEvents);

        add(tableScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 530, 240));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitle;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JTable tblEvents;
    // End of variables declaration//GEN-END:variables
}