package br.com.PdvFrontEnd.util;

import java.io.*;
import java.util.Properties;

public class SessionManager {
    private static final String CONFIG_FILE = "user_config.properties";
    private static final String ADMIN_CONFIG_FILE = "admin_config.properties";
    private static SessionManager instance;
    private String currentUsername;
    private String currentUserId;
    private boolean isLoggedIn;
    private String userRole; // "ADMIN" ou "FRENTISTA"

    private SessionManager() {
        this.isLoggedIn = false;
        this.userRole = "FRENTISTA";
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(String username, String userId, String role) {
        this.currentUsername = username;
        this.currentUserId = userId;
        this.isLoggedIn = true;
        this.userRole = role;
    }

    public void logout() {
        this.currentUsername = null;
        this.currentUserId = null;
        this.isLoggedIn = false;
        this.userRole = "FRENTISTA";
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean isAdmin() {
        return "ADMIN".equals(userRole);
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public String getUserRole() {
        return userRole;
    }

    // Salvar credenciais do Frentista
    public void saveCredentials(String username, String password) {
        Properties props = new Properties();
        try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
            props.setProperty("username", username);
            props.setProperty("password", password);
            props.setProperty("role", "FRENTISTA");
            props.store(out, "User Credentials");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Salvar credenciais do Admin
    public void saveAdminCredentials(String username, String password) {
        Properties props = new Properties();
        try (FileOutputStream out = new FileOutputStream(ADMIN_CONFIG_FILE)) {
            props.setProperty("admin_username", username);
            props.setProperty("admin_password", password);
            props.setProperty("role", "ADMIN");
            props.store(out, "Admin Credentials");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Verificar credenciais do Frentista
    public boolean checkCredentials(String username, String password) {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
            props.load(in);
            String savedUsername = props.getProperty("username");
            String savedPassword = props.getProperty("password");
            return username.equals(savedUsername) && password.equals(savedPassword);
        } catch (IOException e) {
            return false;
        }
    }

    // Verificar credenciais do Admin
    public boolean checkAdminCredentials(String username, String password) {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(ADMIN_CONFIG_FILE)) {
            props.load(in);
            String savedUsername = props.getProperty("admin_username");
            String savedPassword = props.getProperty("admin_password");
            return username.equals(savedUsername) && password.equals(savedPassword);
        } catch (IOException e) {
            return false;
        }
    }

    public boolean userExists() {
        File file = new File(CONFIG_FILE);
        return file.exists();
    }

    public boolean adminExists() {
        File file = new File(ADMIN_CONFIG_FILE);
        return file.exists();
    }
}

