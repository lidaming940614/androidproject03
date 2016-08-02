package feicui.mygitdroid.login;

/**
 * Created by 1099057173 on 2016/7/31.
 */
public class CurrentUser {
    private CurrentUser(){}

    private static String accessToken;
    private static User user;

    // 当前是否有token
    public static boolean hasAccessToken(){
        return accessToken != null;
    }
    // 当前是否是"空的"(还没有登陆)
    public static boolean isEmpty(){
        return accessToken == null || user == null;
    }
    // 清除信息
    public static void clear(){
        accessToken = null;
        user = null;
    }


    public static void setAccessToken(String accessToken) {
        CurrentUser.accessToken = accessToken;
    }

    public static void setUser(User user) {
        CurrentUser.user = user;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static User getUser() {
        return user;
    }
}
