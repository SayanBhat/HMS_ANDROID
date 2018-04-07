package project.sayan.hms.Model;

/**
 * Created by Sayan on 4/4/2018.
 */

public class UserModel {
    public final String USERID="userId";
    public final String NAME="name";
    public final String EMAIL="email";
    public final String RESULT="result";
    public final String MESSAGE="Message";
    public final String PASSWORD="password";

    private int userid;
    private String name;
    private String password;
    private String email;
    private String result;
    private String Message;


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
