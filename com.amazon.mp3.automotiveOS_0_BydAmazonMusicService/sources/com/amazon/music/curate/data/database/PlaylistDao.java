package com.amazon.music.curate.data.database;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.amazon.music.curate.model.Playlist;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaylistDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Dao
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0014\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH'J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\r2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0014\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH'J\u0016\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u0011H'¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/curate/data/database/PlaylistDao;", "", "deletePersonalizedPlaylists", "", "deletePlaylist", "playlistId", "", "getPersonalizedPlaylists", "Landroidx/paging/DataSource$Factory;", "", "Lcom/amazon/music/curate/model/Playlist;", "getPlaylist", "getPlaylistLiveData", "Landroidx/lifecycle/LiveData;", "getPlaylists", "putPlaylists", "playlists", "", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface PlaylistDao {
    @Query("Delete from Playlist where is_personalized = 1")
    void deletePersonalizedPlaylists();

    @Query("Delete from Playlist where id = (:playlistId)")
    void deletePlaylist(String playlistId);

    @Query("Select * from Playlist where is_personalized = 1")
    DataSource.Factory<Integer, Playlist> getPersonalizedPlaylists();

    @Query("Select * from Playlist where id = (:playlistId)")
    Playlist getPlaylist(String playlistId);

    @Query("Select * from Playlist where id = (:playlistId)")
    LiveData<Playlist> getPlaylistLiveData(String playlistId);

    @Query("Select * from Playlist")
    DataSource.Factory<Integer, Playlist> getPlaylists();

    @Insert(onConflict = 1)
    void putPlaylists(List<? extends Playlist> playlists);
}
