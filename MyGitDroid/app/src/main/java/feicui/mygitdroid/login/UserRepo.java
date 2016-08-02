package feicui.mygitdroid.login;

import java.io.Serializable;

/**
 * Created by 1099057173 on 2016/7/31.
 */
public class UserRepo  implements Serializable {
    private UserRepo(){}

    private static String accessToken;

    private static User user;

    public static boolean hasAccessToken(){
        return accessToken != null;
    }
    public static boolean isEmpty(){
        return accessToken == null || user == null;
    }

    public static void clear(){
        accessToken = null;
        user = null;
    }


    public static void setAccessToken(String accessToken) {
        UserRepo.accessToken = accessToken;
    }

    public static void setUser(User user) {
        UserRepo.user = user;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static User getUser() {
        return user;
    }
}
