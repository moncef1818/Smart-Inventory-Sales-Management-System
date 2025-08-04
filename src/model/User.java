package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User  {
    private int id;
    private String email;
    private String username;
    private String password;
    private String role;
    private final String registerTime;





    public User(String email,String username,String password,int role){
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setRole(role);



        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy");
        registerTime = now.format(formatter);
    }

    public User(int id, String email, String username, String password, String role, String registerTime) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.registerTime = registerTime;
    }

    public void setId(int id){
        this.id = id;
    }
    private boolean verifyEmail(String username){
        if(!username.contains("@")) return false;
        String user = username.substring(0,username.indexOf("@")) ;
        String domain = username.substring(username.indexOf("@")+1) ;
        return  !user.isEmpty() && !domain.isEmpty() ;
    }

    private  boolean verifyPassword(String password){
        return (password.length() >= 8) ;
    }
    private void setRole(int role){
        this.role = (role==0)? "Admin" : "Customer";
    }

    public void setUsername(String username) {
        this.username = (username.isEmpty()) ? "Guest": username;
    }

    public void setEmail(String email) {
        if (!verifyEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }


    public void setPassword(String password) {
        if (!verifyPassword(password)) {
            throw new IllegalArgumentException("Password must be at least 8 characters.");
        }
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }
}
