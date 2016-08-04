package feicui.mygitdroid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by 1099057173 on 2016/8/3.
 */
public class DBhelper extends OrmLiteSqliteOpenHelper {
private  static  final  String TABNE_NAME="repo_favorites.db";
    private  static  final  int VERSION=1;
    private  final  Context context;
    private DBhelper(Context context) {
        super(context, TABNE_NAME, null, VERSION);
        this.context = context;

    }

    private static DBhelper sInstance;
    public static synchronized DBhelper getInstance(Context context){
        if (sInstance == null) {
            sInstance = new DBhelper(context.getApplicationContext());
        }
        return sInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource,RepoGroupTable.class);
            TableUtils.createTableIfNotExists(connectionSource, LocalRepo.class);
            new RepoDao(this).createOrUpdateAll(RepoGroupTable.getdafaltGroups(context));
            new LocalRepoDao(this).createOrUpdate(LocalRepo.getDefaultLocalRepos(context));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,RepoGroupTable.class, true);
            TableUtils.dropTable(connectionSource,LocalRepo.class, true);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
