package com.amazon.music.find.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.amazon.music.find.data.database.entities.SearchHistory;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchQueryDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Dao
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH'J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000bH'J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0005H'¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/find/data/database/SearchQueryDao;", "", "deleteAll", "", "profileId", "", "deleteExpired", TetheredMessageKey.timestamp, "", "getAll", "", "Lcom/amazon/music/find/data/database/entities/SearchQueryData;", "getRecentSearchHistory", "limit", "", "put", "searchQueryData", "syncDefaultProfileHistory", "defaultProfileId", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchQueryDao {
    @Query("delete from search_query where profile_id = :profileId")
    void deleteAll(String profileId);

    @Query("delete from search_query where timestamp < :timestamp")
    void deleteExpired(long timestamp);

    @Query("select * from search_query where profile_id = :profileId")
    List<SearchHistory> getAll(String profileId);

    @Query("select * from search_query where profile_id = :profileId order by timestamp desc limit :limit")
    List<SearchHistory> getRecentSearchHistory(String profileId, int limit);

    @Insert(onConflict = 1)
    void put(SearchHistory searchQueryData);

    @Query("update search_query set profile_id = :defaultProfileId where profile_id = 'undefined'")
    void syncDefaultProfileHistory(String defaultProfileId);
}
