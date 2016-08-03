package feicui.mygitdroid.db;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DatabaseField;

import com.j256.ormlite.table.DatabaseTable;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by 1099057173 on 2016/8/3.
 */
@DatabaseTable(tableName = "repository")
public class RepoGroupTable {
    @DatabaseField(id = true)
    @SerializedName("id")
    private int id;
    @DatabaseField(columnName = "NAME")
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    private static List<RepoGroupTable> DEFAULT_GROUPS;

    public static List<RepoGroupTable> getdafaltGroups(Context context) {
        if (DEFAULT_GROUPS != null) {
            return DEFAULT_GROUPS;
        }
        try {
            InputStream inputStream=context.getAssets().open("repogroup.json");
            String repo= IOUtils.toString(inputStream);
            Gson gson=new Gson();
            DEFAULT_GROUPS= gson.fromJson(repo, new TypeToken<List<RepoGroupTable>>(){}.getType());
            return DEFAULT_GROUPS;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
