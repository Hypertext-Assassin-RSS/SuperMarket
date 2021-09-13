package model;

public class LogInModel {
    private String user;
    private String password;

    public LogInModel() {
    }

    public LogInModel(String user, String password) {
        this.setUser(user);
        this.setPassword(password);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
