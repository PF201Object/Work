package Config;

import java.sql.*;

public class Config {
    private static final String URL = "jdbc:sqlite:Swift.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.err.println("Connection Failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * NEW: Checks if a name already exists in either the admin or user table.
     * This prevents "Double Accounts" as requested.
     */
    public static boolean isUserExists(String name) {
        // Check Admin Table
        String adminCheck = "SELECT COUNT(*) FROM admin WHERE a_username = ?";
        // Check User Table
        String userCheck = "SELECT COUNT(*) FROM user WHERE u_name = ?";

        try (Connection conn = connect()) {
            // Check admins first
            try (PreparedStatement pstmt = conn.prepareStatement(adminCheck)) {
                pstmt.setString(1, name);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) return true;
            }
            
            // Then check users
            try (PreparedStatement pstmt = conn.prepareStatement(userCheck)) {
                pstmt.setString(1, name);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void initializeDB() {
        String userTable = "CREATE TABLE IF NOT EXISTS user ("
                   + "u_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "u_name TEXT NOT NULL,"
                   + "u_email TEXT,"
                   + "u_password TEXT,"
                   + "u_contact TEXT,"
                   + "u_role TEXT,"
                   + "status TEXT DEFAULT 'Active');";

        String adminTable = "CREATE TABLE IF NOT EXISTS admin ("
                    + "a_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "a_username TEXT UNIQUE NOT NULL,"
                    + "a_password TEXT NOT NULL,"
                    + "a_email TEXT);";
        
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(adminTable);
            setupDefaultAdmin(); 
        } catch (SQLException e) {
            System.err.println("DB Init Error: " + e.getMessage());
        }
    }

    private static void setupDefaultAdmin() {
        String checkSql = "SELECT * FROM admin WHERE a_username = 'admin'";
        String insertSql = "INSERT INTO admin(a_username, a_password, a_email) VALUES('admin', 'admin123', 'admin@swift.com')";
        
        try (Connection conn = connect(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(checkSql)) {
            
            if (!rs.next()) {
                stmt.execute(insertSql);
                System.out.println("Dedicated Admin Table Initialized with default account.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getUserRole(String username, String password) {
        String adminSql = "SELECT 'Admin' as role FROM admin WHERE a_username = ? AND a_password = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(adminSql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return "Admin";
        } catch (SQLException e) { e.printStackTrace(); }

        String userSql = "SELECT u_role FROM user WHERE u_name = ? AND u_password = ? AND status = 'Active'";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(userSql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getString("u_role");
        } catch (SQLException e) { e.printStackTrace(); }

        return null; 
    }

    public static boolean registerUser(String name, String email, String pass, String contact, String role, String status) {
        String sql;
        
        if ("Admin".equalsIgnoreCase(role)) {
            sql = "INSERT INTO admin(a_username, a_password, a_email) VALUES(?,?,?)";
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, pass);
                pstmt.setString(3, email);
                return pstmt.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            sql = "INSERT INTO user(u_name, u_email, u_password, u_contact, u_role, status) VALUES(?,?,?,?,?,?)";
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, pass);
                pstmt.setString(4, contact);
                pstmt.setString(5, role);
                pstmt.setString(6, status);
                return pstmt.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static boolean validateLogin(String username, String password) {
        return getUserRole(username, password) != null;
    }
}