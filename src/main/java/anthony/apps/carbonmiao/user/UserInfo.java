package anthony.apps.carbonmiao.user;

import org.springframework.data.annotation.Id;

public class UserInfo {

    @Id
    private String userId;
    private String passWord;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
