import java.io.Serializable;

public class UserInfoItem implements Serializable {
    private String username;
    private String password;
    private int userType;
    public UserInfoItem(String username, String password, int userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserInfoItem{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
