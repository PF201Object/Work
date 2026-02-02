package SWIFT;

import java.awt.Color;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.Timer;

public class Serve extends javax.swing.JFrame {

    private final int originalY;
    private final int homeOriginalY;

public Serve() {
    initComponents();
    
    // 1. Transparency Fix
    btnEnter.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    btnEnter.setOpaque(false);
    btnEnter.setContentAreaFilled(false);
    btnEnter.setBorderPainted(false);
    btnEnter.setFocusPainted(false);
    btnEnter.setBackground(new Color(0, 0, 0, 0));
    btnEnter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnEnter.setText(""); 
	
	btnHome.setUI(new javax.swing.plaf.basic.BasicButtonUI());
	btnHome.setContentAreaFilled(false);
	btnHome.setBorderPainted(false); // Removes the permanent border
	btnHome.setFocusPainted(false);  // REMOVES THE OUTLINE when clicked or hovered
	btnHome.setOpaque(false);

    // 2. Hover Logic
    btnEnter.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            jLabel9.setVisible(true);   // The GIF
            jLabel10.setVisible(false); // The Static PNG
        }
        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            jLabel9.setVisible(false);
            jLabel10.setVisible(true);
        }
		
    });

    // 3. Initial Visibility
    jLabel9.setVisible(false);
    jLabel10.setVisible(true);
    loading.setVisible(false);
    
    // 4. Layer Management (Crucial for AbsoluteLayout)
    getContentPane().setComponentZOrder(btnEnter, 0); // Button stays on TOP to catch clicks
    getContentPane().setComponentZOrder(jLabel9, 1);   // GIF behind button
    getContentPane().setComponentZOrder(jLabel10, 2);  // Static image behind GIF
    
        JButton[] navButtons = {btnExit};
        for (JButton btn : navButtons) {
            btn.setFocusPainted(false); 
            btn.setBorderPainted(false);
        }
    
    // Window settings
    this.setBackground(new Color(0, 0, 0, 0)); 
    this.setLocationRelativeTo(null);
	this.pack(); // Ensures size is calculated
    this.setSize(930, 480); // Or your preferred fixed size
    this.setLocationRelativeTo(null); // Centers the window;
    originalY = btnEnter.getY();
    homeOriginalY = btnHome.getY();
}

    public void startAnimation() {
        new Thread(() -> {
            try {
                for (float i = 0f; i <= 1.0f; i += 0.02f) {
                    this.setOpacity(i);
                    Thread.sleep(20); 
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnExit = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        loading = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnEnter = new javax.swing.JButton();
        Background1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExit.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("X");
        btnExit.setContentAreaFilled(false);
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 50, 40));

        btnHome.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("ABOUT US");
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
        });
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        getContentPane().add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 40));

        loading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/LoadingF.gif"))); // NOI18N
        getContentPane().add(loading, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 200, 70));

        Logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/LOGO.png"))); // NOI18N
        getContentPane().add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 930, 170));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Serve.png"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 190, 80));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Serve.gif"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 190, 80));

        btnEnter.setContentAreaFilled(false);
        btnEnter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEnter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEnterMouseClicked(evt);
            }
        });
        getContentPane().add(btnEnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, 130, 40));

        Background1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Background.png"))); // NOI18N
        getContentPane().add(Background1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -120, 1370, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
       btnExit.setForeground(new Color(255, 102, 0));             
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
       btnExit.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
       System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
                                            
// --- HOME BUTTON ANIMATION METHODS ---
    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {                                     
        btnHome.setForeground(new Color(255, 102, 0)); 
        
        Timer timer = new Timer(10, new java.awt.event.ActionListener() {
            int distance = 0;
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (distance < 6) {
                    btnHome.setLocation(btnHome.getX(), btnHome.getY() - 1);
                    distance++;
                } else {
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();
    }                                    

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {                                    
        btnHome.setForeground(Color.WHITE);
        
        Timer timer = new Timer(10, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (btnHome.getY() < homeOriginalY) {
                    btnHome.setLocation(btnHome.getX(), btnHome.getY() + 1);
                } else {
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();
    }                                    

    // --- ENTER BUTTON CLICK LOGIC ---
    private void btnEnterMouseClicked(java.awt.event.MouseEvent evt) {                                      
        btnEnter.setEnabled(false);

        new Thread(() -> {
            try {
                java.awt.EventQueue.invokeLater(() -> {
                    loading.setVisible(true); 
                    repaint();
                });

                Thread.sleep(3000);

                java.awt.EventQueue.invokeLater(() -> {
                    this.dispose();
                    new DashboardForm().setVisible(true);
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }                                     

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            Serve s = new Serve();
            s.setOpacity(0f); 
            s.setLocationRelativeTo(null); // Double check centering before showing
            s.setVisible(true);
            s.startAnimation();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background1;
    private javax.swing.JLabel Logo;
    private javax.swing.JButton btnEnter;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHome;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel loading;
    // End of variables declaration//GEN-END:variables
}