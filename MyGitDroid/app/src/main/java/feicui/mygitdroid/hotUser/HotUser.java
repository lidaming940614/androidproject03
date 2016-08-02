package feicui.mygitdroid.hotUser;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 1099057173 on 2016/8/2.
 */
public class HotUser {
    @SerializedName("avatar_url")
    private String avatar_url;//作者图像
    private String login;
    private  int id;
    @SerializedName("score")//作者分数
    private String score;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
