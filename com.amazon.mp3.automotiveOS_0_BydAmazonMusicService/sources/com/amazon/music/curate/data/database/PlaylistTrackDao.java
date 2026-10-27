package com.amazon.music.curate.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.amazon.music.curate.model.PlaylistTrack;
import com.amazon.music.curate.model.Track;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistTrackDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Dao
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH'J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001c\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010\u0005\u001a\u00020\u0006H'J&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH'J\u0016\u0010\u0012\u001a\u00020\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\rH'J \u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0017¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/curate/data/database/PlaylistTrackDao;", "", "()V", "deletePlaylistTrack", "", "playlistId", "", "trackId", "index", "", "deletePlaylistTracks", "getPlaylistTracksLiveData", "Landroidx/lifecycle/LiveData;", "", "Lcom/amazon/music/curate/model/Track;", "getTracksForPlaylist", "limit", "offset", "putPlaylistTracks", "playlistTracks", "Lcom/amazon/music/curate/model/PlaylistTrack;", "removePlaylistTrackAndUpdateIndices", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class PlaylistTrackDao {
    @Query("Delete from PlaylistTrack where playlist_id = :playlistId and position_in_playlist = (select position_in_playlist from PlaylistTrack where playlist_id = :playlistId and track_asin = :trackId and position_in_playlist >= :index limit 1)")
    public abstract void deletePlaylistTrack(String playlistId, String trackId, int index);

    @Query("Delete from PlaylistTrack where PlaylistTrack.playlist_id = (:playlistId)")
    public abstract void deletePlaylistTracks(String playlistId);

    @Query("Select * from Track inner join PlaylistTrack on Track.asin = PlaylistTrack.track_asin where PlaylistTrack.playlist_id = (:playlistId) order by PlaylistTrack.position_in_playlist ")
    public abstract LiveData<List<Track>> getPlaylistTracksLiveData(String playlistId);

    @Query("Select * from Track inner join PlaylistTrack on Track.asin = PlaylistTrack.track_asin where PlaylistTrack.playlist_id = (:playlistId) order by PlaylistTrack.position_in_playlist limit (:limit) offset (:offset)")
    public abstract List<Track> getTracksForPlaylist(String playlistId, int limit, int offset);

    @Insert(onConflict = 1)
    public abstract void putPlaylistTracks(List<PlaylistTrack> playlistTracks);

    @Transaction
    public void removePlaylistTrackAndUpdateIndices(String playlistId, String trackId, int index) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Intrinsics.checkNotNullParameter(trackId, "trackId");
        deletePlaylistTrack(playlistId, trackId, index);
    }
}
