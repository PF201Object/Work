package SWIFT;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JButton;

public final class DashboardForm extends javax.swing.JFrame {

    // Internal Panels
    private UserRecords userPanel;
    private BookingRecords bookingPanel;
    private MenuRecords menuPanel;
    private EventRecords eventPanel;
    private LoginForm loginPanel; 
    private RegistrationForm registerPanel;
    
    private final int homeOriginalY;
    private String userRole = "Guest"; 
    private int mouseX, mouseY;

    public DashboardForm() {
        initComponents();
        customInit();
        showLogin(); 
        homeOriginalY = BOOK.getY();
    }

    private void customInit() {
        // Frame Setup
        this.setBackground(new Color(0, 0, 0, 0));
        this.setSize(930, 480);
        this.setLocationRelativeTo(null);

        // Window Dragging Logic
        Background.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                mouseX = evt.getX();
                mouseY = evt.getY();
            }
        });
        Background.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                setLocation(evt.getXOnScreen() - mouseX, evt.getYOnScreen() - mouseY);
            }
        });

        // Initialize Panels
        userPanel = new UserRecords();
        bookingPanel = new BookingRecords();
        menuPanel = new MenuRecords();
        eventPanel = new EventRecords();
        loginPanel = new LoginForm(this);      
        registerPanel = new RegistrationForm(this); 

        // Shared Layout Constraints
        int displayX = 220;
        int displayY = 30;
        int width = 570;
        int height = 350;

        // Add panels to Content Pane
        getContentPane().add(userPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(displayX, displayY, width, height));
        getContentPane().add(bookingPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(displayX, displayY, width, height));
        getContentPane().add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(displayX, displayY, width, height));
        getContentPane().add(eventPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(displayX, displayY, width, height));
        getContentPane().add(loginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(displayX, displayY, width, height));
        getContentPane().add(registerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(displayX, displayY, width, height));


        JButton[] navButtons = {BOOK, EVENT, CONTACTUS, MENU, btnUser, btnLogout, btnExit};
        for (JButton btn : navButtons) {
            btn.setFocusPainted(false); 
            btn.setBorderPainted(false);
            btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }

        // Layering
        getContentPane().setComponentZOrder(btnExit, 0);
        getContentPane().setComponentZOrder(btnUser, 0);
        getContentPane().setComponentZOrder(btnLogout, 0);
        getContentPane().setComponentZOrder(Background, getContentPane().getComponentCount() - 1);

        hideAllPanels();
    }

    public void hideAllPanels() {
        userPanel.setVisible(false);
        bookingPanel.setVisible(false);
        menuPanel.setVisible(false);
        eventPanel.setVisible(false);
        loginPanel.setVisible(false);
        registerPanel.setVisible(false);
        
        Book1.setVisible(true);
        Book.setVisible(true); 
        btnUser.setVisible(false);
        User.setVisible(false);   
        User1.setVisible(false);  
        BOOK.setVisible(false);
        bg2.setVisible(false);        
        btnLogout.setVisible(false);
        MENU.setVisible(false);
        CONTACTUS.setVisible(false);
        EVENT.setVisible(false);
    }

    private void showPanel(JPanel targetPanel) {
        if(loginPanel.isVisible() || registerPanel.isVisible()) return;

        boolean wasVisible = targetPanel.isVisible();
        
        // Clear all panels to prevent collision
        userPanel.setVisible(false);
        bookingPanel.setVisible(false);
        menuPanel.setVisible(false);
        eventPanel.setVisible(false);

        if (!wasVisible) {
            targetPanel.setVisible(true);
            Book.setVisible(false); 
            
            if (targetPanel instanceof BookingRecords) ((BookingRecords)targetPanel).loadBookingData();
            if (targetPanel instanceof MenuRecords) ((MenuRecords)targetPanel).loadMenuData();
            if (targetPanel instanceof EventRecords) ((EventRecords)targetPanel).loadEventData();
            if (targetPanel instanceof UserRecords) ((UserRecords)targetPanel).loadUserData();
        } else {
            Book.setVisible(true);
        }
        repaint();
    }

    public void showLogin() {
        hideAllPanels();
        Book.setVisible(false); 
        loginPanel.setVisible(true);
        repaint();
    }

    public void showRegister() {
        hideAllPanels();
        Book.setVisible(false); 
        registerPanel.setVisible(true);
        repaint();
    }

    public void loginSuccess(String role) {
        this.userRole = role;
        hideAllPanels(); 
        Book1.setVisible(false);
        Book.setVisible(true); 
        btnLogout.setVisible(true);
        BOOK.setVisible(true);
        MENU.setVisible(true);
        EVENT.setVisible(true);
        CONTACTUS.setVisible(true);
        bg2.setVisible(true);

        if ("Admin".equalsIgnoreCase(userRole)) {
            btnUser.setVisible(true);
            User1.setVisible(true); 
        }
        repaint();
    }

    private void runAnimation(javax.swing.JButton btn, boolean entering) {
        btn.setForeground(entering ? new Color(255, 102, 0) : Color.WHITE);
        Timer timer = new Timer(10, e -> {
            if (entering && btn.getY() > homeOriginalY - 6) {
                btn.setLocation(btn.getX(), btn.getY() - 1);
            } else if (!entering && btn.getY() < homeOriginalY) {
                btn.setLocation(btn.getX(), btn.getY() + 1);
            } else {
                ((Timer)e.getSource()).stop();
            }
        });
        timer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnExit = new javax.swing.JButton();
        BOOK = new javax.swing.JButton();
        EVENT = new javax.swing.JButton();
        CONTACTUS = new javax.swing.JButton();
        MENU = new javax.swing.JButton();
        Book1 = new javax.swing.JLabel();
        Book = new javax.swing.JLabel();
        btnUser = new javax.swing.JButton();
        User = new javax.swing.JLabel();
        User1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        bg2 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExit.setFont(new java.awt.Font("Serif", 1, 24)); 
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("X");
        btnExit.setContentAreaFilled(false);
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btnExitMouseEntered(evt); }
            public void mouseExited(java.awt.event.MouseEvent evt) { btnExitMouseExited(evt); }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { btnExitActionPerformed(evt); }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 50, 40));

        BOOK.setFont(new java.awt.Font("Serif", 1, 18)); 
        BOOK.setForeground(new java.awt.Color(255, 255, 255));
        BOOK.setText("BOOKING");
        BOOK.setContentAreaFilled(false);
        BOOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { BOOKMouseEntered(evt); }
            public void mouseExited(java.awt.event.MouseEvent evt) { BOOKMouseExited(evt); }
        });
        BOOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { BOOKActionPerformed(evt); }
        });
        getContentPane().add(BOOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 130, 40));

        EVENT.setFont(new java.awt.Font("Serif", 1, 18)); 
        EVENT.setForeground(new java.awt.Color(255, 255, 255));
        EVENT.setText("EVENT");
        EVENT.setContentAreaFilled(false);
        EVENT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { EVENTMouseEntered(evt); }
            public void mouseExited(java.awt.event.MouseEvent evt) { EVENTMouseExited(evt); }
        });
        EVENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { EVENTActionPerformed(evt); }
        });
        getContentPane().add(EVENT, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 100, 40));

        CONTACTUS.setFont(new java.awt.Font("Serif", 1, 18)); 
        CONTACTUS.setForeground(new java.awt.Color(255, 255, 255));
        CONTACTUS.setText("CONTACT US");
        CONTACTUS.setContentAreaFilled(false);
        CONTACTUS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { CONTACTUSMouseEntered(evt); }
            public void mouseExited(java.awt.event.MouseEvent evt) { CONTACTUSMouseExited(evt); }
        });
        getContentPane().add(CONTACTUS, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 150, 40));

        MENU.setFont(new java.awt.Font("Serif", 1, 18)); 
        MENU.setForeground(new java.awt.Color(255, 255, 255));
        MENU.setText("MENU");
        MENU.setContentAreaFilled(false);
        MENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { MENUMouseEntered(evt); }
            public void mouseExited(java.awt.event.MouseEvent evt) { MENUMouseExited(evt); }
        });
        MENU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { MENUActionPerformed(evt); }
        });
        getContentPane().add(MENU, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 40));

        btnLogout.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 18)); 
        btnLogout.setForeground(new java.awt.Color(255, 102, 102));
        btnLogout.setText("LOGOUT");
        btnLogout.setContentAreaFilled(false);
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btnLogoutMouseEntered(evt); }
            public void mouseExited(java.awt.event.MouseEvent evt) { btnLogoutMouseExited(evt); }
        });
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { btnLogoutActionPerformed(evt); }
        });
        getContentPane().add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 120, 50));

        // Background and Logos labels (simplified for code brevity)
        Book1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard.image/LOGO.png"))); 
        getContentPane().add(Book1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, 60, 620, 340));
        Book.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard.image/LOGO.png"))); 
        getContentPane().add(Book, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 620, 340));
        
        btnUser.setContentAreaFilled(false);
        btnUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) { btnUserMouseClicked(evt); }
            public void mouseEntered(java.awt.event.MouseEvent evt) { btnUserMouseEntered(evt); }
            public void mouseExited(java.awt.event.MouseEvent evt) { btnUserMouseExited(evt); }
        });
        getContentPane().add(btnUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 120, 40));

        User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard.image/User.gif"))); 
        getContentPane().add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 120, 60));
        User1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard.image/User.png"))); 
        getContentPane().add(User1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 180, 60));
        bg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/bg1.png"))); 
        getContentPane().add(bg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, 70, 350, 340));
        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard.image/Background1.png"))); 
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // --- EVENT HANDLERS ---
    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) { btnExit.setForeground(new Color(255, 102, 0)); }
    private void btnExitMouseExited(java.awt.event.MouseEvent evt) { btnExit.setForeground(Color.WHITE); }
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) { System.exit(0); }

    private void BOOKMouseEntered(java.awt.event.MouseEvent evt) { runAnimation(BOOK, true); }
    private void BOOKMouseExited(java.awt.event.MouseEvent evt) { runAnimation(BOOK, false); }
    private void BOOKActionPerformed(java.awt.event.ActionEvent evt) { showPanel(bookingPanel); }

    private void EVENTMouseEntered(java.awt.event.MouseEvent evt) { runAnimation(EVENT, true); }
    private void EVENTMouseExited(java.awt.event.MouseEvent evt) { runAnimation(EVENT, false); }
    private void EVENTActionPerformed(java.awt.event.ActionEvent evt) { showPanel(eventPanel); }

    private void MENUMouseEntered(java.awt.event.MouseEvent evt) { runAnimation(MENU, true); }
    private void MENUMouseExited(java.awt.event.MouseEvent evt) { runAnimation(MENU, false); }
    private void MENUActionPerformed(java.awt.event.ActionEvent evt) { showPanel(menuPanel); }

    private void CONTACTUSMouseEntered(java.awt.event.MouseEvent evt) { runAnimation(CONTACTUS, true); }
    private void CONTACTUSMouseExited(java.awt.event.MouseEvent evt) { runAnimation(CONTACTUS, false); }

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) { btnLogout.setForeground(Color.WHITE); }
    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) { btnLogout.setForeground(new Color(255, 102, 102)); }
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) { userRole = "Guest"; showLogin(); }

    private void btnUserMouseClicked(java.awt.event.MouseEvent evt) { if ("Admin".equalsIgnoreCase(userRole)) showPanel(userPanel); }
    private void btnUserMouseEntered(java.awt.event.MouseEvent evt) { if(btnUser.isVisible()) User.setVisible(true); }
    private void btnUserMouseExited(java.awt.event.MouseEvent evt) { User.setVisible(false); }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new DashboardForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BOOK;
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Book;
    private javax.swing.JLabel Book1;
    private javax.swing.JButton CONTACTUS;
    private javax.swing.JButton EVENT;
    private javax.swing.JButton MENU;
    private javax.swing.JLabel User;
    private javax.swing.JLabel User1;
    private javax.swing.JLabel bg2;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnUser;
    // End of variables declaration//GEN-END:variables
}