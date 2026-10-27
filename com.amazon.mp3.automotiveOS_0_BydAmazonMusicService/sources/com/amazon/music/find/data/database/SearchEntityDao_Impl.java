package com.amazon.music.find.data.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.music.find.data.database.SearchEntityDao;
import com.amazon.music.find.data.database.entities.SearchItem;
import com.amazon.music.platform.find.common.EntityIdType;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class SearchEntityDao_Impl implements SearchEntityDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<SearchItem> __insertionAdapterOfSearchEntityData;
    private final SharedSQLiteStatement __preparedStmtOfDeleteExpired;
    private final SharedSQLiteStatement __preparedStmtOfResetNavigationFromSearch;
    private final SharedSQLiteStatement __preparedStmtOfSyncDefaultProfileHistory;
    private final SharedSQLiteStatement __preparedStmtOfUpdateSearchEntityLastAccessTime;
    private final SharedSQLiteStatement __preparedStmtOfUpdateSearchEntityLastAccessTime_1;
    private final SearchTypeConverters __searchTypeConverters = new SearchTypeConverters();

    public SearchEntityDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSearchEntityData = new EntityInsertionAdapter<SearchItem>(__db) { // from class: com.amazon.music.find.data.database.SearchEntityDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `search_entity` (`title`,`entity_id`,`entity_id_type`,`entity_type`,`image_url`,`entity_property`,`timestamp`,`did_navigate_from_search`,`content_url`,`profile_id`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, SearchItem searchItem) {
                if (searchItem.getTitle() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, searchItem.getTitle());
                }
                if (searchItem.getEntityId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, searchItem.getEntityId());
                }
                String string = SearchEntityDao_Impl.this.__searchTypeConverters.toString(searchItem.getEntityIdType());
                if (string == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, string);
                }
                String string2 = SearchEntityDao_Impl.this.__searchTypeConverters.toString(searchItem.getEntityType());
                if (string2 == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, string2);
                }
                if (searchItem.getImageUrl() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, searchItem.getImageUrl());
                }
                String string3 = SearchEntityDao_Impl.this.__searchTypeConverters.toString(searchItem.getEntityProperties());
                if (string3 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, string3);
                }
                supportSQLiteStatement.bindLong(7, searchItem.getTimestamp());
                supportSQLiteStatement.bindLong(8, searchItem.getDidNavigateFromSearch() ? 1L : 0L);
                if (searchItem.getContentUrl() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, searchItem.getContentUrl());
                }
                if (searchItem.getPid() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, searchItem.getPid());
                }
            }
        };
        this.__preparedStmtOfDeleteExpired = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.find.data.database.SearchEntityDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from search_entity where timestamp < ?";
            }
        };
        this.__preparedStmtOfResetNavigationFromSearch = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.find.data.database.SearchEntityDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "update search_entity set did_navigate_from_search = ? where profile_id = ?";
            }
        };
        this.__preparedStmtOfUpdateSearchEntityLastAccessTime = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.find.data.database.SearchEntityDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "update search_entity set timestamp = ? where entity_id = ? and entity_id_type = ? and profile_id = ? and did_navigate_from_search";
            }
        };
        this.__preparedStmtOfUpdateSearchEntityLastAccessTime_1 = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.find.data.database.SearchEntityDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "update search_entity set timestamp = ? where content_url = ? and profile_id = ? and did_navigate_from_search";
            }
        };
        this.__preparedStmtOfSyncDefaultProfileHistory = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.find.data.database.SearchEntityDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "update search_entity set profile_id = ? where profile_id = 'undefined'";
            }
        };
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public void put(final SearchItem searchEntityData) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSearchEntityData.insert(searchEntityData);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public void update(final SearchItem searchEntityData) {
        this.__db.beginTransaction();
        try {
            SearchEntityDao.DefaultImpls.update(this, searchEntityData);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public void deleteExpired(final long timestamp) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteExpired.acquire();
        supportSQLiteStatementAcquire.bindLong(1, timestamp);
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteExpired.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public void resetNavigationFromSearch(String str, boolean z) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfResetNavigationFromSearch.acquire();
        supportSQLiteStatementAcquire.bindLong(1, z ? 1L : 0L);
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetNavigationFromSearch.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public void updateSearchEntityLastAccessTime(final String profileId, final String entityId, final EntityIdType entityIdType, final long timestamp) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateSearchEntityLastAccessTime.acquire();
        supportSQLiteStatementAcquire.bindLong(1, timestamp);
        if (entityId == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, entityId);
        }
        String string = this.__searchTypeConverters.toString(entityIdType);
        if (string == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, string);
        }
        if (profileId == null) {
            supportSQLiteStatementAcquire.bindNull(4);
        } else {
            supportSQLiteStatementAcquire.bindString(4, profileId);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateSearchEntityLastAccessTime.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public void updateSearchEntityLastAccessTime(final String profileId, final String contentUrl, final long timestamp) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateSearchEntityLastAccessTime_1.acquire();
        supportSQLiteStatementAcquire.bindLong(1, timestamp);
        if (contentUrl == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, contentUrl);
        }
        if (profileId == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, profileId);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateSearchEntityLastAccessTime_1.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public void syncDefaultProfileHistory(final String defaultProfileId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfSyncDefaultProfileHistory.acquire();
        if (defaultProfileId == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, defaultProfileId);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSyncDefaultProfileHistory.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public SearchItem getSearchEntity(String str, String str2, EntityIdType entityIdType) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from search_entity where entity_id = ? and entity_id_type = ? and profile_id = ?", 3);
        if (str2 == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str2);
        }
        String string = this.__searchTypeConverters.toString(entityIdType);
        if (string == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, string);
        }
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(3);
        } else {
            roomSQLiteQueryAcquire.bindString(3, str);
        }
        this.__db.assertNotSuspendingTransaction();
        SearchItem searchItem = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id_type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "image_url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_property");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TetheredMessageKey.timestamp);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "did_navigate_from_search");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "profile_id");
            if (cursorQuery.moveToFirst()) {
                searchItem = new SearchItem(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), this.__searchTypeConverters.toEntityIdType(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3)), this.__searchTypeConverters.toEntityType(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4)), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), this.__searchTypeConverters.toEntityProperties(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6)), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8) != 0, cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10));
            }
            return searchItem;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public List<SearchItem> getExactMatches(final String profileId, final List<String> titles) {
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("select * from search_entity where profile_id = ");
        sbNewStringBuilder.append("?");
        sbNewStringBuilder.append(" and title in (");
        int size = titles.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size + 1);
        if (profileId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, profileId);
        }
        int i = 2;
        for (String str : titles) {
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i);
            } else {
                roomSQLiteQueryAcquire.bindString(i, str);
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        String str2 = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id_type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "image_url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_property");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TetheredMessageKey.timestamp);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "did_navigate_from_search");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "profile_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SearchItem(cursorQuery.isNull(columnIndexOrThrow) ? str2 : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? str2 : cursorQuery.getString(columnIndexOrThrow2), this.__searchTypeConverters.toEntityIdType(cursorQuery.isNull(columnIndexOrThrow3) ? str2 : cursorQuery.getString(columnIndexOrThrow3)), this.__searchTypeConverters.toEntityType(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4)), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), this.__searchTypeConverters.toEntityProperties(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6)), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8) != 0, cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10)));
                str2 = null;
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public List<SearchItem> getRecentSearchEntities(final String profileId, final int count, final long lastModifiedAt) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from search_entity where profile_id = ? and timestamp > ? order by timestamp desc limit ?", 3);
        if (profileId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, profileId);
        }
        roomSQLiteQueryAcquire.bindLong(2, lastModifiedAt);
        roomSQLiteQueryAcquire.bindLong(3, count);
        this.__db.assertNotSuspendingTransaction();
        String str = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id_type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "image_url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_property");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TetheredMessageKey.timestamp);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "did_navigate_from_search");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "profile_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SearchItem(cursorQuery.isNull(columnIndexOrThrow) ? str : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? str : cursorQuery.getString(columnIndexOrThrow2), this.__searchTypeConverters.toEntityIdType(cursorQuery.isNull(columnIndexOrThrow3) ? str : cursorQuery.getString(columnIndexOrThrow3)), this.__searchTypeConverters.toEntityType(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4)), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), this.__searchTypeConverters.toEntityProperties(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6)), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8) != 0, cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10)));
                str = null;
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public List<SearchItem> getRecentSearchEntities(final String profileId, final int count, final long lastModifiedAt, final Set<? extends EntityType> entityTypes) {
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("select * from search_entity where profile_id = ");
        sbNewStringBuilder.append("?");
        sbNewStringBuilder.append(" and timestamp > ");
        sbNewStringBuilder.append("?");
        sbNewStringBuilder.append(" and entity_type in (");
        int size = entityTypes.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(") order by timestamp desc limit ");
        sbNewStringBuilder.append("?");
        int i = 3;
        int i2 = size + 3;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), i2);
        if (profileId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, profileId);
        }
        roomSQLiteQueryAcquire.bindLong(2, lastModifiedAt);
        Iterator<? extends EntityType> it = entityTypes.iterator();
        while (it.hasNext()) {
            String string = this.__searchTypeConverters.toString(it.next());
            if (string == null) {
                roomSQLiteQueryAcquire.bindNull(i);
            } else {
                roomSQLiteQueryAcquire.bindString(i, string);
            }
            i++;
        }
        roomSQLiteQueryAcquire.bindLong(i2, count);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id_type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "image_url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_property");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TetheredMessageKey.timestamp);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "did_navigate_from_search");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "profile_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SearchItem(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), this.__searchTypeConverters.toEntityIdType(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3)), this.__searchTypeConverters.toEntityType(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4)), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), this.__searchTypeConverters.toEntityProperties(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6)), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8) != 0, cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public List<SearchItem> getRecentSearchEntitiesFromSearch(final String profileId, final int count) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from search_entity where profile_id = ? and did_navigate_from_search order by timestamp desc limit ?", 2);
        if (profileId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, profileId);
        }
        roomSQLiteQueryAcquire.bindLong(2, count);
        this.__db.assertNotSuspendingTransaction();
        String str = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id_type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "image_url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_property");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TetheredMessageKey.timestamp);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "did_navigate_from_search");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "profile_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SearchItem(cursorQuery.isNull(columnIndexOrThrow) ? str : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? str : cursorQuery.getString(columnIndexOrThrow2), this.__searchTypeConverters.toEntityIdType(cursorQuery.isNull(columnIndexOrThrow3) ? str : cursorQuery.getString(columnIndexOrThrow3)), this.__searchTypeConverters.toEntityType(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4)), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), this.__searchTypeConverters.toEntityProperties(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6)), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8) != 0, cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10)));
                str = null;
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public List<SearchItem> getRecentSearchEntitiesFromSearch(final String profileId, final int count, final Set<? extends EntityType> entityTypes) {
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("select * from search_entity where profile_id = ");
        sbNewStringBuilder.append("?");
        sbNewStringBuilder.append(" and did_navigate_from_search and entity_type in (");
        int size = entityTypes.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(") order by timestamp desc limit ");
        sbNewStringBuilder.append("?");
        int i = 2;
        int i2 = size + 2;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), i2);
        if (profileId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, profileId);
        }
        Iterator<? extends EntityType> it = entityTypes.iterator();
        while (it.hasNext()) {
            String string = this.__searchTypeConverters.toString(it.next());
            if (string == null) {
                roomSQLiteQueryAcquire.bindNull(i);
            } else {
                roomSQLiteQueryAcquire.bindString(i, string);
            }
            i++;
        }
        roomSQLiteQueryAcquire.bindLong(i2, count);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id_type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "image_url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_property");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TetheredMessageKey.timestamp);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "did_navigate_from_search");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "profile_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SearchItem(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), this.__searchTypeConverters.toEntityIdType(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3)), this.__searchTypeConverters.toEntityType(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4)), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), this.__searchTypeConverters.toEntityProperties(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6)), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8) != 0, cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public List<SearchItem> getAll(final String profileId) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from search_entity where profile_id = ?", 1);
        if (profileId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, profileId);
        }
        this.__db.assertNotSuspendingTransaction();
        String str = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_id_type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "image_url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entity_property");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TetheredMessageKey.timestamp);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "did_navigate_from_search");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_url");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "profile_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SearchItem(cursorQuery.isNull(columnIndexOrThrow) ? str : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? str : cursorQuery.getString(columnIndexOrThrow2), this.__searchTypeConverters.toEntityIdType(cursorQuery.isNull(columnIndexOrThrow3) ? str : cursorQuery.getString(columnIndexOrThrow3)), this.__searchTypeConverters.toEntityType(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4)), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), this.__searchTypeConverters.toEntityProperties(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6)), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8) != 0, cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10)));
                str = null;
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.music.find.data.database.SearchEntityDao
    public void removeByEntityType(final String profileId, final List<? extends EntityType> entityTypes) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("delete from search_entity where profile_id = ");
        sbNewStringBuilder.append("?");
        sbNewStringBuilder.append(" and entity_type in (");
        StringUtil.appendPlaceholders(sbNewStringBuilder, entityTypes.size());
        sbNewStringBuilder.append(")");
        SupportSQLiteStatement supportSQLiteStatementCompileStatement = this.__db.compileStatement(sbNewStringBuilder.toString());
        if (profileId == null) {
            supportSQLiteStatementCompileStatement.bindNull(1);
        } else {
            supportSQLiteStatementCompileStatement.bindString(1, profileId);
        }
        int i = 2;
        Iterator<? extends EntityType> it = entityTypes.iterator();
        while (it.hasNext()) {
            String string = this.__searchTypeConverters.toString(it.next());
            if (string == null) {
                supportSQLiteStatementCompileStatement.bindNull(i);
            } else {
                supportSQLiteStatementCompileStatement.bindString(i, string);
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementCompileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
