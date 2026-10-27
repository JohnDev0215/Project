package com.amazon.music.find.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.data.database.entities.SearchItem;
import com.amazon.music.platform.find.common.EntityIdType;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchEntityDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Dao
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH'J$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H'J&\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H'J4\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H'J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH'J,\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H'J\"\u0010\u0015\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018H'J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\bH'J\u001e\u0010\u001b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007H'J\u0018\u0010\u001c\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001eH'J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\nH'J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\bH\u0017J(\u0010\"\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0004\u001a\u00020\u0005H'J \u0010\"\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010#\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006$"}, d2 = {"Lcom/amazon/music/find/data/database/SearchEntityDao;", "", "deleteExpired", "", TetheredMessageKey.timestamp, "", "getAll", "", "Lcom/amazon/music/find/data/database/entities/SearchEntityData;", "profileId", "", "getExactMatches", "titles", "getRecentSearchEntities", "count", "", "lastModifiedAt", "entityTypes", "", "Lcom/amazon/music/platform/find/common/EntityType;", "getRecentSearchEntitiesFromSearch", "getSearchEntity", ContextMappingConstants.ENTITY_ID, ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/platform/find/common/EntityIdType;", "put", "searchEntityData", "removeByEntityType", "resetNavigationFromSearch", "to", "", "syncDefaultProfileHistory", "defaultProfileId", "update", "updateSearchEntityLastAccessTime", "contentUrl", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchEntityDao {
    @Query("delete from search_entity where timestamp < :timestamp")
    void deleteExpired(long timestamp);

    @Query("select * from search_entity where profile_id = :profileId")
    List<SearchItem> getAll(String profileId);

    @Query("select * from search_entity where profile_id = :profileId and title in (:titles)")
    List<SearchItem> getExactMatches(String profileId, List<String> titles);

    @Query("select * from search_entity where profile_id = :profileId and timestamp > :lastModifiedAt order by timestamp desc limit :count")
    List<SearchItem> getRecentSearchEntities(String profileId, int count, long lastModifiedAt);

    @Query("select * from search_entity where profile_id = :profileId and timestamp > :lastModifiedAt and entity_type in (:entityTypes) order by timestamp desc limit :count")
    List<SearchItem> getRecentSearchEntities(String profileId, int count, long lastModifiedAt, Set<? extends EntityType> entityTypes);

    @Query("select * from search_entity where profile_id = :profileId and did_navigate_from_search order by timestamp desc limit :count")
    List<SearchItem> getRecentSearchEntitiesFromSearch(String profileId, int count);

    @Query("select * from search_entity where profile_id = :profileId and did_navigate_from_search and entity_type in (:entityTypes) order by timestamp desc limit :count")
    List<SearchItem> getRecentSearchEntitiesFromSearch(String profileId, int count, Set<? extends EntityType> entityTypes);

    @Query("select * from search_entity where entity_id = :entityId and entity_id_type = :entityIdType and profile_id = :profileId")
    SearchItem getSearchEntity(String profileId, String entityId, EntityIdType entityIdType);

    @Insert(onConflict = 1)
    void put(SearchItem searchEntityData);

    @Query("delete from search_entity where profile_id = :profileId and entity_type in (:entityTypes)")
    void removeByEntityType(String profileId, List<? extends EntityType> entityTypes);

    @Query("update search_entity set did_navigate_from_search = :to where profile_id = :profileId")
    void resetNavigationFromSearch(String profileId, boolean to);

    @Query("update search_entity set profile_id = :defaultProfileId where profile_id = 'undefined'")
    void syncDefaultProfileHistory(String defaultProfileId);

    @Transaction
    void update(SearchItem searchEntityData);

    @Query("update search_entity set timestamp = :timestamp where content_url = :contentUrl and profile_id = :profileId and did_navigate_from_search")
    void updateSearchEntityLastAccessTime(String profileId, String contentUrl, long timestamp);

    @Query("update search_entity set timestamp = :timestamp where entity_id = :entityId and entity_id_type = :entityIdType and profile_id = :profileId and did_navigate_from_search")
    void updateSearchEntityLastAccessTime(String profileId, String entityId, EntityIdType entityIdType, long timestamp);

    /* JADX INFO: compiled from: SearchEntityDao.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        @Transaction
        public static void update(SearchEntityDao searchEntityDao, SearchItem searchEntityData) {
            Intrinsics.checkNotNullParameter(searchEntityDao, "this");
            Intrinsics.checkNotNullParameter(searchEntityData, "searchEntityData");
            if (searchEntityData.getDidNavigateFromSearch()) {
                searchEntityDao.put(searchEntityData);
            }
        }
    }
}
