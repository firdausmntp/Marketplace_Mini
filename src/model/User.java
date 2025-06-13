package model;

public abstract class User {
    protected String id;
    protected String nama;
    protected String email;
    protected String password;
    protected String alamat;
    protected String noTelp;
    
    public User(String id, String nama, String email, String password, String alamat, String noTelp) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
        this.noTelp = noTelp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public String getNama() {
        return nama;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getAlamat() {
        return alamat;
    }
    public String getNoTelp() {
        return noTelp;
    }
    public abstract void login();
    public abstract void logout();

}
