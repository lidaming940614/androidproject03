package feicui.mygitdroid.adapter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by 1099057173 on 2016/8/1.
 */
public class Language implements Serializable {
    private String name;
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private static List<Language> languages;

    public static List<Language> getDefaultLanguages(Context context) {
        if (languages != null) return languages;
        try {
            InputStream inputStream = context.getAssets().open("langs.json");
            String content = IOUtils.toString(inputStream);
            Gson gson = new Gson();
            languages = gson.fromJson(content, new TypeToken<List<Language>>() {
            }.getType());
            return languages;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
