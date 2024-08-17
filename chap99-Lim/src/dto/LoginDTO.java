package dto;

public class LoginDTO {
    private long id;
    private String userId;
    private String userPw;

    public LoginDTO(long id, String userId, String userPw) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LoginDTO(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public LoginDTO() {
    }
}
