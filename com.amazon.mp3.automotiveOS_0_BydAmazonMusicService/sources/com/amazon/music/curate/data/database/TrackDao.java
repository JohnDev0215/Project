package com.amazon.music.curate.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.amazon.music.curate.model.Track;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: TrackDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Dao
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0016\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H'¨\u0006\b"}, d2 = {"Lcom/amazon/music/curate/data/database/TrackDao;", "", "deleteAllTracks", "", "putTracks", "tracks", "", "Lcom/amazon/music/curate/model/Track;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface TrackDao {
    @Query("Delete from Track")
    void deleteAllTracks();

    @Insert(onConflict = 1)
    void putTracks(List<Track> tracks);
}
