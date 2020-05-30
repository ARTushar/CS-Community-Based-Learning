package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class UserInfo implements Serializable {
    private String infoType;
    private String upName;
    private String inName;
    private String upPassword;
    private String inPassword;
    private String upEmail;
    private String upRePassword;
    private String id;
    private HashMap<String,ArrayList<Integer>> likesTrack = new HashMap<>();

    public UserInfo(String infoType, String upName, String upPassword, String upRePassword, String upEmail) {
        this.infoType = infoType;
        this.upName = upName;
        this.upPassword = upPassword;
        this.upEmail = upEmail;
        this.upRePassword = upRePassword;
    }

    public UserInfo(String infoType, String inName, String inPassword) {
        this.infoType = infoType;
        this.inName = inName;
        this.inPassword = inPassword;
    }

    public UserInfo(String upName, String upPassword, String upEmail, String id) {
        this.upName = upName;
        this.upPassword = upPassword;
        this.upEmail = upEmail;
        this.id = id;
    }

    public UserInfo() {
    }

    public String getInfoType() {
        return infoType;
    }

    public String getUpName() {
        return upName;
    }

    public String getInName() {
        return inName;
    }

    public String getUpPassword() {
        return upPassword;
    }

    public String getInPassword() {
        return inPassword;
    }

    public String getUpEmail() {
        return upEmail;
    }

    public String getUpRePassword() {
        return upRePassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public void setUpName(String upName) {
        this.upName = upName;
    }

    public void setInName(String inName) {
        this.inName = inName;
    }

    public void setUpPassword(String upPassword) {
        this.upPassword = upPassword;
    }

    public void setInPassword(String inPassword) {
        this.inPassword = inPassword;
    }

    public void setUpEmail(String upEmail) {
        this.upEmail = upEmail;
    }

    public HashMap<String, ArrayList<Integer>> getLikesTrack() {
        return likesTrack;
    }

    public void setUpRePassword(String upRePassword) {
        this.upRePassword = upRePassword;
    }

    public void reload(UserInfo user) {
        this.upName = user.upName;
        this.upPassword = user.upPassword;
        this.upEmail = user.upEmail;
        this.id = user.id;
    }
}
