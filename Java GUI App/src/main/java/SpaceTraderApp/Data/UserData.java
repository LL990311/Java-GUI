package SpaceTraderApp.Data;

public class UserData {
    private String token;
    private User user;

    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return this.token;
    }
    public void setUser(User user){
        this.user = user;
    }
    public User getUser(){
        return this.user;
    }
}
