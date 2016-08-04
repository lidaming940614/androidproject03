package feicui.mygitdroid.db;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import feicui.mygitdroid.entity.Repo;

/**
 * Created by 1099057173 on 2016/8/4.
 */
public class RepoChange {
    private RepoChange() {
    }
    public  static LocalRepo change(Repo repo){
        LocalRepo localRepo=new LocalRepo();
        localRepo.setAvatar(repo.getOwner().getAvatar());
        localRepo.setName(repo.getName());
        localRepo.setDescription(repo.getDescription());
        localRepo.setRepoGroup(null);
        localRepo.setId(repo.getId());
        localRepo.setForkCount(repo.getForkCount());
        localRepo.setFullName(repo.getFullName());
      localRepo.setStartCount(repo.getStarCount());
        return  localRepo;
    }
    public static @NonNull List<LocalRepo> converAll(@NonNull List<Repo> repos) {
        ArrayList<LocalRepo> localRepos = new ArrayList<LocalRepo>();
        for (Repo repo : repos) {
            localRepos.add(change(repo));
        }
        return localRepos;
    }
}
