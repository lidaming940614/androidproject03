package feicui.mygitdroid.db;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 1099057173 on 2016/8/3.
 */
public class RepoDao {

    private final Dao<RepoGroupTable, Long> dao;

    public RepoDao(DBhelper dbHelper){
        try {
            dao = dbHelper.getDao(RepoGroupTable.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createOrUpdate(RepoGroupTable localRepo){
        try {
            dao.createOrUpdate(localRepo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createOrUpdateAll(List<RepoGroupTable> localRepos){
        for (RepoGroupTable localRepo : localRepos) {
            createOrUpdate(localRepo);
        }
    }

    public List<RepoGroupTable> queryForAll(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public RepoGroupTable queryForId(long id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(RepoGroupTable localRepo){
        try {
            dao.delete(localRepo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
