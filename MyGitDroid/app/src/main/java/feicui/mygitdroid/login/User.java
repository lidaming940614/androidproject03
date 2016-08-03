package feicui.mygitdroid.login;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 1099057173 on 2016/7/31.
 */
public class User implements Serializable{
    private String login;
    private String name;
    private int id;
    @SerializedName("avatar_url")
    private String avatar;

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
