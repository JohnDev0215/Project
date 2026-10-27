package com.amazon.podcast.storage;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.podcast.biteBookmark.BiteBookmarkOperationsDoa;
import com.amazon.podcast.biteBookmark.BiteBookmarkOperationsDoa_Impl;
import com.amazon.podcast.biteBookmark.BiteBookmarksDao;
import com.amazon.podcast.biteBookmark.BiteBookmarksDao_Impl;
import com.amazon.podcast.bookmark.BookmarkOperationsDoa;
import com.amazon.podcast.bookmark.BookmarkOperationsDoa_Impl;
import com.amazon.podcast.bookmark.BookmarksDao;
import com.amazon.podcast.bookmark.BookmarksDao_Impl;
import com.amazon.podcast.categoryFollow.CategoryFollowOperationsDoa;
import com.amazon.podcast.categoryFollow.CategoryFollowOperationsDoa_Impl;
import com.amazon.podcast.categoryFollow.CategoryFollowsDao;
import com.amazon.podcast.categoryFollow.CategoryFollowsDao_Impl;
import com.amazon.podcast.completed.CompletedDao;
import com.amazon.podcast.completed.CompletedDao_Impl;
import com.amazon.podcast.completed.CompletedOperationsDoa;
import com.amazon.podcast.completed.CompletedOperationsDoa_Impl;
import com.amazon.podcast.downloads.DownloadOperationsDao;
import com.amazon.podcast.downloads.DownloadOperationsDao_Impl;
import com.amazon.podcast.downloads.DownloadsDao;
import com.amazon.podcast.downloads.DownloadsDao_Impl;
import com.amazon.podcast.follow.FollowOperationsDoa;
import com.amazon.podcast.follow.FollowOperationsDoa_Impl;
import com.amazon.podcast.follow.FollowsDao;
import com.amazon.podcast.follow.FollowsDao_Impl;
import com.amazon.podcast.metrics.playback.PlaybackMetricOperationsDao;
import com.amazon.podcast.metrics.playback.PlaybackMetricOperationsDao_Impl;
import com.amazon.podcast.metrics.ui.UiMetricOperationsDao;
import com.amazon.podcast.metrics.ui.UiMetricOperationsDao_Impl;
import com.amazon.podcast.save.SaveOperationsDoa;
import com.amazon.podcast.save.SaveOperationsDoa_Impl;
import com.amazon.podcast.save.SavesDao;
import com.amazon.podcast.save.SavesDao_Impl;
import com.google.android.gms.cast.MediaTrack;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastRoomDatabase_Impl extends PodcastRoomDatabase {
    private volatile BiteBookmarkOperationsDoa _biteBookmarkOperationsDoa;
    private volatile BiteBookmarksDao _biteBookmarksDao;
    private volatile BookmarkOperationsDoa _bookmarkOperationsDoa;
    private volatile BookmarksDao _bookmarksDao;
    private volatile CategoryFollowOperationsDoa _categoryFollowOperationsDoa;
    private volatile CategoryFollowsDao _categoryFollowsDao;
    private volatile CompletedDao _completedDao;
    private volatile CompletedOperationsDoa _completedOperationsDoa;
    private volatile DownloadOperationsDao _downloadOperationsDao;
    private volatile DownloadsDao _downloadsDao;
    private volatile FollowOperationsDoa _followOperationsDoa;
    private volatile FollowsDao _followsDao;
    private volatile PlaybackMetricOperationsDao _playbackMetricOperationsDao;
    private volatile SaveOperationsDoa _saveOperationsDoa;
    private volatile SavesDao _savesDao;
    private volatile UiMetricOperationsDao _uiMetricOperationsDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(10) { // from class: com.amazon.podcast.storage.PodcastRoomDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `follows` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `image` TEXT NOT NULL, `description` TEXT NOT NULL, `publisher` TEXT NOT NULL, `followed` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `podcastShowVariantId` TEXT NOT NULL, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `follow_operations` (`operationId` TEXT NOT NULL, `elementId` TEXT NOT NULL, `title` TEXT NOT NULL, `image` TEXT NOT NULL, `description` TEXT NOT NULL, `publisher` TEXT NOT NULL, `followed` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`operationId`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `bookmarks` (`id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `podcastTitle` TEXT NOT NULL, `title` TEXT NOT NULL, `image` TEXT NOT NULL, `podcastImage` TEXT NOT NULL, `publishDate` TEXT NOT NULL, `description` TEXT NOT NULL, `authors` TEXT NOT NULL, `seasonNumber` TEXT NOT NULL, `totalDurationMilliseconds` INTEGER NOT NULL, `progressMilliseconds` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `bookmark_operations` (`operationId` TEXT NOT NULL, `id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `podcastTitle` TEXT NOT NULL, `title` TEXT NOT NULL, `image` TEXT NOT NULL, `podcastImage` TEXT NOT NULL, `publishDate` TEXT NOT NULL, `description` TEXT NOT NULL, `authors` TEXT NOT NULL, `seasonNumber` TEXT NOT NULL, `totalDurationMilliseconds` INTEGER NOT NULL, `progressMilliseconds` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`operationId`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `bite_bookmarks` (`id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `episodeId` TEXT NOT NULL, `totalDurationMilliseconds` INTEGER NOT NULL, `progressMilliseconds` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `bite_bookmark_operations` (`operationId` TEXT NOT NULL, `id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `episodeId` TEXT NOT NULL, `totalDurationMilliseconds` INTEGER NOT NULL, `progressMilliseconds` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`operationId`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `saves` (`id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `podcastTitle` TEXT NOT NULL, `title` TEXT NOT NULL, `image` TEXT NOT NULL, `podcastImage` TEXT NOT NULL, `publishDate` TEXT NOT NULL, `description` TEXT NOT NULL, `authors` TEXT NOT NULL, `seasonNumber` TEXT NOT NULL, `totalDurationMilliseconds` INTEGER NOT NULL, `saved` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `playbackMode` TEXT NOT NULL, `contentTraits` TEXT NOT NULL, `availableUpsells` TEXT NOT NULL, `availabilityDate` TEXT NOT NULL, `podcastShowVariantId` TEXT NOT NULL, `podcastEpisodeVariantId` TEXT NOT NULL, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `save_operations` (`operationId` TEXT NOT NULL, `id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `podcastTitle` TEXT NOT NULL, `title` TEXT NOT NULL, `image` TEXT NOT NULL, `podcastImage` TEXT NOT NULL, `publishDate` TEXT NOT NULL, `description` TEXT NOT NULL, `authors` TEXT NOT NULL, `seasonNumber` TEXT NOT NULL, `totalDurationMilliseconds` INTEGER NOT NULL, `saved` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`operationId`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `downloads` (`id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `podcastTitle` TEXT NOT NULL, `title` TEXT NOT NULL, `image` TEXT NOT NULL, `podcastImage` TEXT NOT NULL, `publishDate` TEXT NOT NULL, `description` TEXT NOT NULL, `authors` TEXT NOT NULL, `seasonNumber` TEXT NOT NULL, `durationMilliseconds` INTEGER NOT NULL, `explicit` INTEGER NOT NULL, `state` TEXT NOT NULL, `localAudioUrl` TEXT NOT NULL, `updatedTime` INTEGER NOT NULL, `playbackMode` TEXT NOT NULL, `contentTraits` TEXT NOT NULL, `availableUpsells` TEXT NOT NULL, `availabilityDate` TEXT NOT NULL, `podcastShowVariantId` TEXT NOT NULL, `podcastEpisodeVariantId` TEXT NOT NULL, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `download_operations` (`operationId` TEXT NOT NULL, `id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `podcastTitle` TEXT NOT NULL, `title` TEXT NOT NULL, `image` TEXT NOT NULL, `podcastImage` TEXT NOT NULL, `publishDate` TEXT NOT NULL, `description` TEXT NOT NULL, `authors` TEXT NOT NULL, `seasonNumber` TEXT NOT NULL, `durationMilliseconds` INTEGER NOT NULL, `explicit` INTEGER NOT NULL, `state` TEXT NOT NULL, `localAudioUrl` TEXT NOT NULL, `updatedTime` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`operationId`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `playback_metric_operations` (`operationId` TEXT NOT NULL, `id` TEXT NOT NULL, `mediaId` TEXT NOT NULL, `mediaCollectionType` TEXT NOT NULL, `playbackSignalType` TEXT NOT NULL, `metricsPreset` TEXT NOT NULL, `playbackRequestedAtMillisecocnds` INTEGER NOT NULL, `playbackStartedAtMillisecocnds` INTEGER NOT NULL, `isMediaDownloaded` INTEGER NOT NULL, `currentProgressMilliseconds` INTEGER NOT NULL, `currentPlaybackSpeed` REAL NOT NULL, `playbackStartOffsetMilliseconds` INTEGER NOT NULL, `initialPlaybackStartDelayMilliseconds` INTEGER NOT NULL, `rebufferDurationMilliseconds` INTEGER NOT NULL, `rebufferCount` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `ingressSource` TEXT NOT NULL, `audioUri` TEXT NOT NULL, `errorException` TEXT NOT NULL, `mediaTitle` TEXT NOT NULL, `mediaCollectionId` TEXT NOT NULL, `mediaSubTitle` TEXT NOT NULL, `processed` INTEGER NOT NULL, `playbackInstanceId` TEXT NOT NULL, `playbackEngineType` TEXT NOT NULL, PRIMARY KEY(`operationId`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `ui_metric_operations` (`operationId` TEXT NOT NULL, `id` TEXT NOT NULL, `signalType` TEXT NOT NULL, `metricsPreset` TEXT NOT NULL, `pageType` TEXT NOT NULL, `contentSrc` TEXT NOT NULL, `containerType` TEXT NOT NULL, `entityType` TEXT NOT NULL, `entityIdType` TEXT NOT NULL, `entityId` TEXT NOT NULL, `detailPageItemId` TEXT NOT NULL, `detailPageItemIdType` TEXT NOT NULL, `refMarker` TEXT NOT NULL, `actionType` TEXT NOT NULL, `interactionType` TEXT NOT NULL, `contentName` TEXT NOT NULL, `containerIndex` INTEGER NOT NULL, `entityPos` INTEGER NOT NULL, `firstViewableIndex` INTEGER NOT NULL, `lastViewableIndex` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, `numItemsAccessible` INTEGER NOT NULL, `containerInstId` TEXT NOT NULL, PRIMARY KEY(`operationId`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `completions` (`id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `podcastTitle` TEXT NOT NULL, `title` TEXT NOT NULL, `image` TEXT NOT NULL, `podcastImage` TEXT NOT NULL, `publishDate` TEXT NOT NULL, `description` TEXT NOT NULL, `authors` TEXT NOT NULL, `seasonNumber` TEXT NOT NULL, `completed` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `completed_operations` (`operationId` TEXT NOT NULL, `id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `podcastTitle` TEXT NOT NULL, `title` TEXT NOT NULL, `image` TEXT NOT NULL, `podcastImage` TEXT NOT NULL, `publishDate` TEXT NOT NULL, `description` TEXT NOT NULL, `authors` TEXT NOT NULL, `seasonNumber` TEXT NOT NULL, `completed` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`operationId`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `category_follows` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `followed` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `category_follow_operations` (`operationId` TEXT NOT NULL, `elementId` TEXT NOT NULL, `title` TEXT NOT NULL, `followed` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`operationId`))");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '40b5e6d81caeea9ef753b02f66b36abb')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `follows`");
                _db.execSQL("DROP TABLE IF EXISTS `follow_operations`");
                _db.execSQL("DROP TABLE IF EXISTS `bookmarks`");
                _db.execSQL("DROP TABLE IF EXISTS `bookmark_operations`");
                _db.execSQL("DROP TABLE IF EXISTS `bite_bookmarks`");
                _db.execSQL("DROP TABLE IF EXISTS `bite_bookmark_operations`");
                _db.execSQL("DROP TABLE IF EXISTS `saves`");
                _db.execSQL("DROP TABLE IF EXISTS `save_operations`");
                _db.execSQL("DROP TABLE IF EXISTS `downloads`");
                _db.execSQL("DROP TABLE IF EXISTS `download_operations`");
                _db.execSQL("DROP TABLE IF EXISTS `playback_metric_operations`");
                _db.execSQL("DROP TABLE IF EXISTS `ui_metric_operations`");
                _db.execSQL("DROP TABLE IF EXISTS `completions`");
                _db.execSQL("DROP TABLE IF EXISTS `completed_operations`");
                _db.execSQL("DROP TABLE IF EXISTS `category_follows`");
                _db.execSQL("DROP TABLE IF EXISTS `category_follow_operations`");
                if (((RoomDatabase) PodcastRoomDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) PodcastRoomDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) PodcastRoomDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase _db) {
                if (((RoomDatabase) PodcastRoomDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) PodcastRoomDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) PodcastRoomDatabase_Impl.this).mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                ((RoomDatabase) PodcastRoomDatabase_Impl.this).mDatabase = _db;
                PodcastRoomDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (((RoomDatabase) PodcastRoomDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) PodcastRoomDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) PodcastRoomDatabase_Impl.this).mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap map = new HashMap(8);
                map.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                map.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map.put(ContextMappingConstants.IMAGE, new TableInfo.Column(ContextMappingConstants.IMAGE, "TEXT", true, 0, null, 1));
                map.put(MediaTrack.ROLE_DESCRIPTION, new TableInfo.Column(MediaTrack.ROLE_DESCRIPTION, "TEXT", true, 0, null, 1));
                map.put("publisher", new TableInfo.Column("publisher", "TEXT", true, 0, null, 1));
                map.put("followed", new TableInfo.Column("followed", "INTEGER", true, 0, null, 1));
                map.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                map.put("podcastShowVariantId", new TableInfo.Column("podcastShowVariantId", "TEXT", true, 0, null, 1));
                TableInfo tableInfo = new TableInfo("follows", map, new HashSet(0), new HashSet(0));
                TableInfo tableInfo2 = TableInfo.read(_db, "follows");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenHelper.ValidationResult(false, "follows(com.amazon.podcast.follow.Follow).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                HashMap map2 = new HashMap(9);
                map2.put("operationId", new TableInfo.Column("operationId", "TEXT", true, 1, null, 1));
                map2.put("elementId", new TableInfo.Column("elementId", "TEXT", true, 0, null, 1));
                map2.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map2.put(ContextMappingConstants.IMAGE, new TableInfo.Column(ContextMappingConstants.IMAGE, "TEXT", true, 0, null, 1));
                map2.put(MediaTrack.ROLE_DESCRIPTION, new TableInfo.Column(MediaTrack.ROLE_DESCRIPTION, "TEXT", true, 0, null, 1));
                map2.put("publisher", new TableInfo.Column("publisher", "TEXT", true, 0, null, 1));
                map2.put("followed", new TableInfo.Column("followed", "INTEGER", true, 0, null, 1));
                map2.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                map2.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo("follow_operations", map2, new HashSet(0), new HashSet(0));
                TableInfo tableInfo4 = TableInfo.read(_db, "follow_operations");
                if (!tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenHelper.ValidationResult(false, "follow_operations(com.amazon.podcast.follow.FollowOperations).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
                }
                HashMap map3 = new HashMap(13);
                map3.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                map3.put("podcastId", new TableInfo.Column("podcastId", "TEXT", true, 0, null, 1));
                map3.put("podcastTitle", new TableInfo.Column("podcastTitle", "TEXT", true, 0, null, 1));
                map3.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map3.put(ContextMappingConstants.IMAGE, new TableInfo.Column(ContextMappingConstants.IMAGE, "TEXT", true, 0, null, 1));
                map3.put("podcastImage", new TableInfo.Column("podcastImage", "TEXT", true, 0, null, 1));
                map3.put("publishDate", new TableInfo.Column("publishDate", "TEXT", true, 0, null, 1));
                map3.put(MediaTrack.ROLE_DESCRIPTION, new TableInfo.Column(MediaTrack.ROLE_DESCRIPTION, "TEXT", true, 0, null, 1));
                map3.put("authors", new TableInfo.Column("authors", "TEXT", true, 0, null, 1));
                map3.put("seasonNumber", new TableInfo.Column("seasonNumber", "TEXT", true, 0, null, 1));
                map3.put("totalDurationMilliseconds", new TableInfo.Column("totalDurationMilliseconds", "INTEGER", true, 0, null, 1));
                map3.put("progressMilliseconds", new TableInfo.Column("progressMilliseconds", "INTEGER", true, 0, null, 1));
                map3.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo5 = new TableInfo("bookmarks", map3, new HashSet(0), new HashSet(0));
                TableInfo tableInfo6 = TableInfo.read(_db, "bookmarks");
                if (!tableInfo5.equals(tableInfo6)) {
                    return new RoomOpenHelper.ValidationResult(false, "bookmarks(com.amazon.podcast.bookmark.Bookmark).\n Expected:\n" + tableInfo5 + "\n Found:\n" + tableInfo6);
                }
                HashMap map4 = new HashMap(15);
                map4.put("operationId", new TableInfo.Column("operationId", "TEXT", true, 1, null, 1));
                map4.put("id", new TableInfo.Column("id", "TEXT", true, 0, null, 1));
                map4.put("podcastId", new TableInfo.Column("podcastId", "TEXT", true, 0, null, 1));
                map4.put("podcastTitle", new TableInfo.Column("podcastTitle", "TEXT", true, 0, null, 1));
                map4.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map4.put(ContextMappingConstants.IMAGE, new TableInfo.Column(ContextMappingConstants.IMAGE, "TEXT", true, 0, null, 1));
                map4.put("podcastImage", new TableInfo.Column("podcastImage", "TEXT", true, 0, null, 1));
                map4.put("publishDate", new TableInfo.Column("publishDate", "TEXT", true, 0, null, 1));
                map4.put(MediaTrack.ROLE_DESCRIPTION, new TableInfo.Column(MediaTrack.ROLE_DESCRIPTION, "TEXT", true, 0, null, 1));
                map4.put("authors", new TableInfo.Column("authors", "TEXT", true, 0, null, 1));
                map4.put("seasonNumber", new TableInfo.Column("seasonNumber", "TEXT", true, 0, null, 1));
                map4.put("totalDurationMilliseconds", new TableInfo.Column("totalDurationMilliseconds", "INTEGER", true, 0, null, 1));
                map4.put("progressMilliseconds", new TableInfo.Column("progressMilliseconds", "INTEGER", true, 0, null, 1));
                map4.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                map4.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo7 = new TableInfo("bookmark_operations", map4, new HashSet(0), new HashSet(0));
                TableInfo tableInfo8 = TableInfo.read(_db, "bookmark_operations");
                if (!tableInfo7.equals(tableInfo8)) {
                    return new RoomOpenHelper.ValidationResult(false, "bookmark_operations(com.amazon.podcast.bookmark.BookmarkOperations).\n Expected:\n" + tableInfo7 + "\n Found:\n" + tableInfo8);
                }
                HashMap map5 = new HashMap(6);
                map5.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                map5.put("podcastId", new TableInfo.Column("podcastId", "TEXT", true, 0, null, 1));
                map5.put("episodeId", new TableInfo.Column("episodeId", "TEXT", true, 0, null, 1));
                map5.put("totalDurationMilliseconds", new TableInfo.Column("totalDurationMilliseconds", "INTEGER", true, 0, null, 1));
                map5.put("progressMilliseconds", new TableInfo.Column("progressMilliseconds", "INTEGER", true, 0, null, 1));
                map5.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo9 = new TableInfo("bite_bookmarks", map5, new HashSet(0), new HashSet(0));
                TableInfo tableInfo10 = TableInfo.read(_db, "bite_bookmarks");
                if (!tableInfo9.equals(tableInfo10)) {
                    return new RoomOpenHelper.ValidationResult(false, "bite_bookmarks(com.amazon.podcast.biteBookmark.BiteBookmark).\n Expected:\n" + tableInfo9 + "\n Found:\n" + tableInfo10);
                }
                HashMap map6 = new HashMap(8);
                map6.put("operationId", new TableInfo.Column("operationId", "TEXT", true, 1, null, 1));
                map6.put("id", new TableInfo.Column("id", "TEXT", true, 0, null, 1));
                map6.put("podcastId", new TableInfo.Column("podcastId", "TEXT", true, 0, null, 1));
                map6.put("episodeId", new TableInfo.Column("episodeId", "TEXT", true, 0, null, 1));
                map6.put("totalDurationMilliseconds", new TableInfo.Column("totalDurationMilliseconds", "INTEGER", true, 0, null, 1));
                map6.put("progressMilliseconds", new TableInfo.Column("progressMilliseconds", "INTEGER", true, 0, null, 1));
                map6.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                map6.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo11 = new TableInfo("bite_bookmark_operations", map6, new HashSet(0), new HashSet(0));
                TableInfo tableInfo12 = TableInfo.read(_db, "bite_bookmark_operations");
                if (!tableInfo11.equals(tableInfo12)) {
                    return new RoomOpenHelper.ValidationResult(false, "bite_bookmark_operations(com.amazon.podcast.biteBookmark.BiteBookmarkOperations).\n Expected:\n" + tableInfo11 + "\n Found:\n" + tableInfo12);
                }
                HashMap map7 = new HashMap(19);
                map7.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                map7.put("podcastId", new TableInfo.Column("podcastId", "TEXT", true, 0, null, 1));
                map7.put("podcastTitle", new TableInfo.Column("podcastTitle", "TEXT", true, 0, null, 1));
                map7.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map7.put(ContextMappingConstants.IMAGE, new TableInfo.Column(ContextMappingConstants.IMAGE, "TEXT", true, 0, null, 1));
                map7.put("podcastImage", new TableInfo.Column("podcastImage", "TEXT", true, 0, null, 1));
                map7.put("publishDate", new TableInfo.Column("publishDate", "TEXT", true, 0, null, 1));
                map7.put(MediaTrack.ROLE_DESCRIPTION, new TableInfo.Column(MediaTrack.ROLE_DESCRIPTION, "TEXT", true, 0, null, 1));
                map7.put("authors", new TableInfo.Column("authors", "TEXT", true, 0, null, 1));
                map7.put("seasonNumber", new TableInfo.Column("seasonNumber", "TEXT", true, 0, null, 1));
                map7.put("totalDurationMilliseconds", new TableInfo.Column("totalDurationMilliseconds", "INTEGER", true, 0, null, 1));
                map7.put("saved", new TableInfo.Column("saved", "INTEGER", true, 0, null, 1));
                map7.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                map7.put("playbackMode", new TableInfo.Column("playbackMode", "TEXT", true, 0, null, 1));
                map7.put("contentTraits", new TableInfo.Column("contentTraits", "TEXT", true, 0, null, 1));
                map7.put("availableUpsells", new TableInfo.Column("availableUpsells", "TEXT", true, 0, null, 1));
                map7.put("availabilityDate", new TableInfo.Column("availabilityDate", "TEXT", true, 0, null, 1));
                map7.put("podcastShowVariantId", new TableInfo.Column("podcastShowVariantId", "TEXT", true, 0, null, 1));
                map7.put("podcastEpisodeVariantId", new TableInfo.Column("podcastEpisodeVariantId", "TEXT", true, 0, null, 1));
                TableInfo tableInfo13 = new TableInfo("saves", map7, new HashSet(0), new HashSet(0));
                TableInfo tableInfo14 = TableInfo.read(_db, "saves");
                if (!tableInfo13.equals(tableInfo14)) {
                    return new RoomOpenHelper.ValidationResult(false, "saves(com.amazon.podcast.save.Save).\n Expected:\n" + tableInfo13 + "\n Found:\n" + tableInfo14);
                }
                HashMap map8 = new HashMap(15);
                map8.put("operationId", new TableInfo.Column("operationId", "TEXT", true, 1, null, 1));
                map8.put("id", new TableInfo.Column("id", "TEXT", true, 0, null, 1));
                map8.put("podcastId", new TableInfo.Column("podcastId", "TEXT", true, 0, null, 1));
                map8.put("podcastTitle", new TableInfo.Column("podcastTitle", "TEXT", true, 0, null, 1));
                map8.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map8.put(ContextMappingConstants.IMAGE, new TableInfo.Column(ContextMappingConstants.IMAGE, "TEXT", true, 0, null, 1));
                map8.put("podcastImage", new TableInfo.Column("podcastImage", "TEXT", true, 0, null, 1));
                map8.put("publishDate", new TableInfo.Column("publishDate", "TEXT", true, 0, null, 1));
                map8.put(MediaTrack.ROLE_DESCRIPTION, new TableInfo.Column(MediaTrack.ROLE_DESCRIPTION, "TEXT", true, 0, null, 1));
                map8.put("authors", new TableInfo.Column("authors", "TEXT", true, 0, null, 1));
                map8.put("seasonNumber", new TableInfo.Column("seasonNumber", "TEXT", true, 0, null, 1));
                map8.put("totalDurationMilliseconds", new TableInfo.Column("totalDurationMilliseconds", "INTEGER", true, 0, null, 1));
                map8.put("saved", new TableInfo.Column("saved", "INTEGER", true, 0, null, 1));
                map8.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                map8.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo15 = new TableInfo("save_operations", map8, new HashSet(0), new HashSet(0));
                TableInfo tableInfo16 = TableInfo.read(_db, "save_operations");
                if (!tableInfo15.equals(tableInfo16)) {
                    return new RoomOpenHelper.ValidationResult(false, "save_operations(com.amazon.podcast.save.SaveOperations).\n Expected:\n" + tableInfo15 + "\n Found:\n" + tableInfo16);
                }
                HashMap map9 = new HashMap(21);
                map9.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                map9.put("podcastId", new TableInfo.Column("podcastId", "TEXT", true, 0, null, 1));
                map9.put("podcastTitle", new TableInfo.Column("podcastTitle", "TEXT", true, 0, null, 1));
                map9.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map9.put(ContextMappingConstants.IMAGE, new TableInfo.Column(ContextMappingConstants.IMAGE, "TEXT", true, 0, null, 1));
                map9.put("podcastImage", new TableInfo.Column("podcastImage", "TEXT", true, 0, null, 1));
                map9.put("publishDate", new TableInfo.Column("publishDate", "TEXT", true, 0, null, 1));
                map9.put(MediaTrack.ROLE_DESCRIPTION, new TableInfo.Column(MediaTrack.ROLE_DESCRIPTION, "TEXT", true, 0, null, 1));
                map9.put("authors", new TableInfo.Column("authors", "TEXT", true, 0, null, 1));
                map9.put("seasonNumber", new TableInfo.Column("seasonNumber", "TEXT", true, 0, null, 1));
                map9.put("durationMilliseconds", new TableInfo.Column("durationMilliseconds", "INTEGER", true, 0, null, 1));
                map9.put("explicit", new TableInfo.Column("explicit", "INTEGER", true, 0, null, 1));
                map9.put("state", new TableInfo.Column("state", "TEXT", true, 0, null, 1));
                map9.put("localAudioUrl", new TableInfo.Column("localAudioUrl", "TEXT", true, 0, null, 1));
                map9.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                map9.put("playbackMode", new TableInfo.Column("playbackMode", "TEXT", true, 0, null, 1));
                map9.put("contentTraits", new TableInfo.Column("contentTraits", "TEXT", true, 0, null, 1));
                map9.put("availableUpsells", new TableInfo.Column("availableUpsells", "TEXT", true, 0, null, 1));
                map9.put("availabilityDate", new TableInfo.Column("availabilityDate", "TEXT", true, 0, null, 1));
                map9.put("podcastShowVariantId", new TableInfo.Column("podcastShowVariantId", "TEXT", true, 0, null, 1));
                map9.put("podcastEpisodeVariantId", new TableInfo.Column("podcastEpisodeVariantId", "TEXT", true, 0, null, 1));
                TableInfo tableInfo17 = new TableInfo("downloads", map9, new HashSet(0), new HashSet(0));
                TableInfo tableInfo18 = TableInfo.read(_db, "downloads");
                if (!tableInfo17.equals(tableInfo18)) {
                    return new RoomOpenHelper.ValidationResult(false, "downloads(com.amazon.podcast.downloads.Download).\n Expected:\n" + tableInfo17 + "\n Found:\n" + tableInfo18);
                }
                HashMap map10 = new HashMap(17);
                map10.put("operationId", new TableInfo.Column("operationId", "TEXT", true, 1, null, 1));
                map10.put("id", new TableInfo.Column("id", "TEXT", true, 0, null, 1));
                map10.put("podcastId", new TableInfo.Column("podcastId", "TEXT", true, 0, null, 1));
                map10.put("podcastTitle", new TableInfo.Column("podcastTitle", "TEXT", true, 0, null, 1));
                map10.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map10.put(ContextMappingConstants.IMAGE, new TableInfo.Column(ContextMappingConstants.IMAGE, "TEXT", true, 0, null, 1));
                map10.put("podcastImage", new TableInfo.Column("podcastImage", "TEXT", true, 0, null, 1));
                map10.put("publishDate", new TableInfo.Column("publishDate", "TEXT", true, 0, null, 1));
                map10.put(MediaTrack.ROLE_DESCRIPTION, new TableInfo.Column(MediaTrack.ROLE_DESCRIPTION, "TEXT", true, 0, null, 1));
                map10.put("authors", new TableInfo.Column("authors", "TEXT", true, 0, null, 1));
                map10.put("seasonNumber", new TableInfo.Column("seasonNumber", "TEXT", true, 0, null, 1));
                map10.put("durationMilliseconds", new TableInfo.Column("durationMilliseconds", "INTEGER", true, 0, null, 1));
                map10.put("explicit", new TableInfo.Column("explicit", "INTEGER", true, 0, null, 1));
                map10.put("state", new TableInfo.Column("state", "TEXT", true, 0, null, 1));
                map10.put("localAudioUrl", new TableInfo.Column("localAudioUrl", "TEXT", true, 0, null, 1));
                map10.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                map10.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo19 = new TableInfo("download_operations", map10, new HashSet(0), new HashSet(0));
                TableInfo tableInfo20 = TableInfo.read(_db, "download_operations");
                if (!tableInfo19.equals(tableInfo20)) {
                    return new RoomOpenHelper.ValidationResult(false, "download_operations(com.amazon.podcast.downloads.DownloadOperations).\n Expected:\n" + tableInfo19 + "\n Found:\n" + tableInfo20);
                }
                HashMap map11 = new HashMap(25);
                map11.put("operationId", new TableInfo.Column("operationId", "TEXT", true, 1, null, 1));
                map11.put("id", new TableInfo.Column("id", "TEXT", true, 0, null, 1));
                map11.put("mediaId", new TableInfo.Column("mediaId", "TEXT", true, 0, null, 1));
                map11.put("mediaCollectionType", new TableInfo.Column("mediaCollectionType", "TEXT", true, 0, null, 1));
                map11.put("playbackSignalType", new TableInfo.Column("playbackSignalType", "TEXT", true, 0, null, 1));
                map11.put("metricsPreset", new TableInfo.Column("metricsPreset", "TEXT", true, 0, null, 1));
                map11.put("playbackRequestedAtMillisecocnds", new TableInfo.Column("playbackRequestedAtMillisecocnds", "INTEGER", true, 0, null, 1));
                map11.put("playbackStartedAtMillisecocnds", new TableInfo.Column("playbackStartedAtMillisecocnds", "INTEGER", true, 0, null, 1));
                map11.put("isMediaDownloaded", new TableInfo.Column("isMediaDownloaded", "INTEGER", true, 0, null, 1));
                map11.put("currentProgressMilliseconds", new TableInfo.Column("currentProgressMilliseconds", "INTEGER", true, 0, null, 1));
                map11.put("currentPlaybackSpeed", new TableInfo.Column("currentPlaybackSpeed", "REAL", true, 0, null, 1));
                map11.put("playbackStartOffsetMilliseconds", new TableInfo.Column("playbackStartOffsetMilliseconds", "INTEGER", true, 0, null, 1));
                map11.put("initialPlaybackStartDelayMilliseconds", new TableInfo.Column("initialPlaybackStartDelayMilliseconds", "INTEGER", true, 0, null, 1));
                map11.put("rebufferDurationMilliseconds", new TableInfo.Column("rebufferDurationMilliseconds", "INTEGER", true, 0, null, 1));
                map11.put("rebufferCount", new TableInfo.Column("rebufferCount", "INTEGER", true, 0, null, 1));
                map11.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                map11.put("ingressSource", new TableInfo.Column("ingressSource", "TEXT", true, 0, null, 1));
                map11.put("audioUri", new TableInfo.Column("audioUri", "TEXT", true, 0, null, 1));
                map11.put("errorException", new TableInfo.Column("errorException", "TEXT", true, 0, null, 1));
                map11.put("mediaTitle", new TableInfo.Column("mediaTitle", "TEXT", true, 0, null, 1));
                map11.put("mediaCollectionId", new TableInfo.Column("mediaCollectionId", "TEXT", true, 0, null, 1));
                map11.put("mediaSubTitle", new TableInfo.Column("mediaSubTitle", "TEXT", true, 0, null, 1));
                map11.put("processed", new TableInfo.Column("processed", "INTEGER", true, 0, null, 1));
                map11.put("playbackInstanceId", new TableInfo.Column("playbackInstanceId", "TEXT", true, 0, null, 1));
                map11.put("playbackEngineType", new TableInfo.Column("playbackEngineType", "TEXT", true, 0, null, 1));
                TableInfo tableInfo21 = new TableInfo("playback_metric_operations", map11, new HashSet(0), new HashSet(0));
                TableInfo tableInfo22 = TableInfo.read(_db, "playback_metric_operations");
                if (!tableInfo21.equals(tableInfo22)) {
                    return new RoomOpenHelper.ValidationResult(false, "playback_metric_operations(com.amazon.podcast.metrics.playback.PlaybackMetricOperations).\n Expected:\n" + tableInfo21 + "\n Found:\n" + tableInfo22);
                }
                HashMap map12 = new HashMap(24);
                map12.put("operationId", new TableInfo.Column("operationId", "TEXT", true, 1, null, 1));
                map12.put("id", new TableInfo.Column("id", "TEXT", true, 0, null, 1));
                map12.put("signalType", new TableInfo.Column("signalType", "TEXT", true, 0, null, 1));
                map12.put("metricsPreset", new TableInfo.Column("metricsPreset", "TEXT", true, 0, null, 1));
                map12.put("pageType", new TableInfo.Column("pageType", "TEXT", true, 0, null, 1));
                map12.put(ContextMappingConstants.CONTENT_SRC, new TableInfo.Column(ContextMappingConstants.CONTENT_SRC, "TEXT", true, 0, null, 1));
                map12.put("containerType", new TableInfo.Column("containerType", "TEXT", true, 0, null, 1));
                map12.put(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, new TableInfo.Column(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "TEXT", true, 0, null, 1));
                map12.put(ContextMappingConstants.ENTITY_ID_TYPE, new TableInfo.Column(ContextMappingConstants.ENTITY_ID_TYPE, "TEXT", true, 0, null, 1));
                map12.put(ContextMappingConstants.ENTITY_ID, new TableInfo.Column(ContextMappingConstants.ENTITY_ID, "TEXT", true, 0, null, 1));
                map12.put("detailPageItemId", new TableInfo.Column("detailPageItemId", "TEXT", true, 0, null, 1));
                map12.put("detailPageItemIdType", new TableInfo.Column("detailPageItemIdType", "TEXT", true, 0, null, 1));
                map12.put("refMarker", new TableInfo.Column("refMarker", "TEXT", true, 0, null, 1));
                map12.put("actionType", new TableInfo.Column("actionType", "TEXT", true, 0, null, 1));
                map12.put("interactionType", new TableInfo.Column("interactionType", "TEXT", true, 0, null, 1));
                map12.put("contentName", new TableInfo.Column("contentName", "TEXT", true, 0, null, 1));
                map12.put("containerIndex", new TableInfo.Column("containerIndex", "INTEGER", true, 0, null, 1));
                map12.put("entityPos", new TableInfo.Column("entityPos", "INTEGER", true, 0, null, 1));
                map12.put("firstViewableIndex", new TableInfo.Column("firstViewableIndex", "INTEGER", true, 0, null, 1));
                map12.put("lastViewableIndex", new TableInfo.Column("lastViewableIndex", "INTEGER", true, 0, null, 1));
                map12.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                map12.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
                map12.put("numItemsAccessible", new TableInfo.Column("numItemsAccessible", "INTEGER", true, 0, null, 1));
                map12.put("containerInstId", new TableInfo.Column("containerInstId", "TEXT", true, 0, null, 1));
                TableInfo tableInfo23 = new TableInfo("ui_metric_operations", map12, new HashSet(0), new HashSet(0));
                TableInfo tableInfo24 = TableInfo.read(_db, "ui_metric_operations");
                if (!tableInfo23.equals(tableInfo24)) {
                    return new RoomOpenHelper.ValidationResult(false, "ui_metric_operations(com.amazon.podcast.metrics.ui.UiMetricOperations).\n Expected:\n" + tableInfo23 + "\n Found:\n" + tableInfo24);
                }
                HashMap map13 = new HashMap(12);
                map13.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                map13.put("podcastId", new TableInfo.Column("podcastId", "TEXT", true, 0, null, 1));
                map13.put("podcastTitle", new TableInfo.Column("podcastTitle", "TEXT", true, 0, null, 1));
                map13.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map13.put(ContextMappingConstants.IMAGE, new TableInfo.Column(ContextMappingConstants.IMAGE, "TEXT", true, 0, null, 1));
                map13.put("podcastImage", new TableInfo.Column("podcastImage", "TEXT", true, 0, null, 1));
                map13.put("publishDate", new TableInfo.Column("publishDate", "TEXT", true, 0, null, 1));
                map13.put(MediaTrack.ROLE_DESCRIPTION, new TableInfo.Column(MediaTrack.ROLE_DESCRIPTION, "TEXT", true, 0, null, 1));
                map13.put("authors", new TableInfo.Column("authors", "TEXT", true, 0, null, 1));
                map13.put("seasonNumber", new TableInfo.Column("seasonNumber", "TEXT", true, 0, null, 1));
                map13.put("completed", new TableInfo.Column("completed", "INTEGER", true, 0, null, 1));
                map13.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo25 = new TableInfo("completions", map13, new HashSet(0), new HashSet(0));
                TableInfo tableInfo26 = TableInfo.read(_db, "completions");
                if (!tableInfo25.equals(tableInfo26)) {
                    return new RoomOpenHelper.ValidationResult(false, "completions(com.amazon.podcast.completed.Completed).\n Expected:\n" + tableInfo25 + "\n Found:\n" + tableInfo26);
                }
                HashMap map14 = new HashMap(14);
                map14.put("operationId", new TableInfo.Column("operationId", "TEXT", true, 1, null, 1));
                map14.put("id", new TableInfo.Column("id", "TEXT", true, 0, null, 1));
                map14.put("podcastId", new TableInfo.Column("podcastId", "TEXT", true, 0, null, 1));
                map14.put("podcastTitle", new TableInfo.Column("podcastTitle", "TEXT", true, 0, null, 1));
                map14.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map14.put(ContextMappingConstants.IMAGE, new TableInfo.Column(ContextMappingConstants.IMAGE, "TEXT", true, 0, null, 1));
                map14.put("podcastImage", new TableInfo.Column("podcastImage", "TEXT", true, 0, null, 1));
                map14.put("publishDate", new TableInfo.Column("publishDate", "TEXT", true, 0, null, 1));
                map14.put(MediaTrack.ROLE_DESCRIPTION, new TableInfo.Column(MediaTrack.ROLE_DESCRIPTION, "TEXT", true, 0, null, 1));
                map14.put("authors", new TableInfo.Column("authors", "TEXT", true, 0, null, 1));
                map14.put("seasonNumber", new TableInfo.Column("seasonNumber", "TEXT", true, 0, null, 1));
                map14.put("completed", new TableInfo.Column("completed", "INTEGER", true, 0, null, 1));
                map14.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                map14.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo27 = new TableInfo("completed_operations", map14, new HashSet(0), new HashSet(0));
                TableInfo tableInfo28 = TableInfo.read(_db, "completed_operations");
                if (!tableInfo27.equals(tableInfo28)) {
                    return new RoomOpenHelper.ValidationResult(false, "completed_operations(com.amazon.podcast.completed.CompletedOperations).\n Expected:\n" + tableInfo27 + "\n Found:\n" + tableInfo28);
                }
                HashMap map15 = new HashMap(4);
                map15.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                map15.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map15.put("followed", new TableInfo.Column("followed", "INTEGER", true, 0, null, 1));
                map15.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo29 = new TableInfo("category_follows", map15, new HashSet(0), new HashSet(0));
                TableInfo tableInfo30 = TableInfo.read(_db, "category_follows");
                if (!tableInfo29.equals(tableInfo30)) {
                    return new RoomOpenHelper.ValidationResult(false, "category_follows(com.amazon.podcast.categoryFollow.CategoryFollow).\n Expected:\n" + tableInfo29 + "\n Found:\n" + tableInfo30);
                }
                HashMap map16 = new HashMap(6);
                map16.put("operationId", new TableInfo.Column("operationId", "TEXT", true, 1, null, 1));
                map16.put("elementId", new TableInfo.Column("elementId", "TEXT", true, 0, null, 1));
                map16.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                map16.put("followed", new TableInfo.Column("followed", "INTEGER", true, 0, null, 1));
                map16.put("updatedTime", new TableInfo.Column("updatedTime", "INTEGER", true, 0, null, 1));
                map16.put("isProcessed", new TableInfo.Column("isProcessed", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo31 = new TableInfo("category_follow_operations", map16, new HashSet(0), new HashSet(0));
                TableInfo tableInfo32 = TableInfo.read(_db, "category_follow_operations");
                if (!tableInfo31.equals(tableInfo32)) {
                    return new RoomOpenHelper.ValidationResult(false, "category_follow_operations(com.amazon.podcast.categoryFollow.CategoryFollowOperations).\n Expected:\n" + tableInfo31 + "\n Found:\n" + tableInfo32);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "40b5e6d81caeea9ef753b02f66b36abb", "0401eb031ac304e366d105ad14cf5fef")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "follows", "follow_operations", "bookmarks", "bookmark_operations", "bite_bookmarks", "bite_bookmark_operations", "saves", "save_operations", "downloads", "download_operations", "playback_metric_operations", "ui_metric_operations", "completions", "completed_operations", "category_follows", "category_follow_operations");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `follows`");
            writableDatabase.execSQL("DELETE FROM `follow_operations`");
            writableDatabase.execSQL("DELETE FROM `bookmarks`");
            writableDatabase.execSQL("DELETE FROM `bookmark_operations`");
            writableDatabase.execSQL("DELETE FROM `bite_bookmarks`");
            writableDatabase.execSQL("DELETE FROM `bite_bookmark_operations`");
            writableDatabase.execSQL("DELETE FROM `saves`");
            writableDatabase.execSQL("DELETE FROM `save_operations`");
            writableDatabase.execSQL("DELETE FROM `downloads`");
            writableDatabase.execSQL("DELETE FROM `download_operations`");
            writableDatabase.execSQL("DELETE FROM `playback_metric_operations`");
            writableDatabase.execSQL("DELETE FROM `ui_metric_operations`");
            writableDatabase.execSQL("DELETE FROM `completions`");
            writableDatabase.execSQL("DELETE FROM `completed_operations`");
            writableDatabase.execSQL("DELETE FROM `category_follows`");
            writableDatabase.execSQL("DELETE FROM `category_follow_operations`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(FollowsDao.class, FollowsDao_Impl.getRequiredConverters());
        map.put(FollowOperationsDoa.class, FollowOperationsDoa_Impl.getRequiredConverters());
        map.put(BookmarksDao.class, BookmarksDao_Impl.getRequiredConverters());
        map.put(BookmarkOperationsDoa.class, BookmarkOperationsDoa_Impl.getRequiredConverters());
        map.put(BiteBookmarksDao.class, BiteBookmarksDao_Impl.getRequiredConverters());
        map.put(BiteBookmarkOperationsDoa.class, BiteBookmarkOperationsDoa_Impl.getRequiredConverters());
        map.put(SavesDao.class, SavesDao_Impl.getRequiredConverters());
        map.put(SaveOperationsDoa.class, SaveOperationsDoa_Impl.getRequiredConverters());
        map.put(DownloadsDao.class, DownloadsDao_Impl.getRequiredConverters());
        map.put(DownloadOperationsDao.class, DownloadOperationsDao_Impl.getRequiredConverters());
        map.put(PlaybackMetricOperationsDao.class, PlaybackMetricOperationsDao_Impl.getRequiredConverters());
        map.put(UiMetricOperationsDao.class, UiMetricOperationsDao_Impl.getRequiredConverters());
        map.put(CompletedDao.class, CompletedDao_Impl.getRequiredConverters());
        map.put(CompletedOperationsDoa.class, CompletedOperationsDoa_Impl.getRequiredConverters());
        map.put(CategoryFollowsDao.class, CategoryFollowsDao_Impl.getRequiredConverters());
        map.put(CategoryFollowOperationsDoa.class, CategoryFollowOperationsDoa_Impl.getRequiredConverters());
        return map;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(@NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public FollowsDao followsDao() {
        FollowsDao followsDao;
        if (this._followsDao != null) {
            return this._followsDao;
        }
        synchronized (this) {
            if (this._followsDao == null) {
                this._followsDao = new FollowsDao_Impl(this);
            }
            followsDao = this._followsDao;
        }
        return followsDao;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public FollowOperationsDoa followOperationsDoa() {
        FollowOperationsDoa followOperationsDoa;
        if (this._followOperationsDoa != null) {
            return this._followOperationsDoa;
        }
        synchronized (this) {
            if (this._followOperationsDoa == null) {
                this._followOperationsDoa = new FollowOperationsDoa_Impl(this);
            }
            followOperationsDoa = this._followOperationsDoa;
        }
        return followOperationsDoa;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public BookmarksDao bookmarksDao() {
        BookmarksDao bookmarksDao;
        if (this._bookmarksDao != null) {
            return this._bookmarksDao;
        }
        synchronized (this) {
            if (this._bookmarksDao == null) {
                this._bookmarksDao = new BookmarksDao_Impl(this);
            }
            bookmarksDao = this._bookmarksDao;
        }
        return bookmarksDao;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public BookmarkOperationsDoa bookmarkOperationsDoa() {
        BookmarkOperationsDoa bookmarkOperationsDoa;
        if (this._bookmarkOperationsDoa != null) {
            return this._bookmarkOperationsDoa;
        }
        synchronized (this) {
            if (this._bookmarkOperationsDoa == null) {
                this._bookmarkOperationsDoa = new BookmarkOperationsDoa_Impl(this);
            }
            bookmarkOperationsDoa = this._bookmarkOperationsDoa;
        }
        return bookmarkOperationsDoa;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public BiteBookmarksDao biteBookmarksDao() {
        BiteBookmarksDao biteBookmarksDao;
        if (this._biteBookmarksDao != null) {
            return this._biteBookmarksDao;
        }
        synchronized (this) {
            if (this._biteBookmarksDao == null) {
                this._biteBookmarksDao = new BiteBookmarksDao_Impl(this);
            }
            biteBookmarksDao = this._biteBookmarksDao;
        }
        return biteBookmarksDao;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public BiteBookmarkOperationsDoa biteBookmarkOperationsDoa() {
        BiteBookmarkOperationsDoa biteBookmarkOperationsDoa;
        if (this._biteBookmarkOperationsDoa != null) {
            return this._biteBookmarkOperationsDoa;
        }
        synchronized (this) {
            if (this._biteBookmarkOperationsDoa == null) {
                this._biteBookmarkOperationsDoa = new BiteBookmarkOperationsDoa_Impl(this);
            }
            biteBookmarkOperationsDoa = this._biteBookmarkOperationsDoa;
        }
        return biteBookmarkOperationsDoa;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public SavesDao savesDao() {
        SavesDao savesDao;
        if (this._savesDao != null) {
            return this._savesDao;
        }
        synchronized (this) {
            if (this._savesDao == null) {
                this._savesDao = new SavesDao_Impl(this);
            }
            savesDao = this._savesDao;
        }
        return savesDao;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public SaveOperationsDoa saveOperationsDoa() {
        SaveOperationsDoa saveOperationsDoa;
        if (this._saveOperationsDoa != null) {
            return this._saveOperationsDoa;
        }
        synchronized (this) {
            if (this._saveOperationsDoa == null) {
                this._saveOperationsDoa = new SaveOperationsDoa_Impl(this);
            }
            saveOperationsDoa = this._saveOperationsDoa;
        }
        return saveOperationsDoa;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public DownloadsDao downloadsDao() {
        DownloadsDao downloadsDao;
        if (this._downloadsDao != null) {
            return this._downloadsDao;
        }
        synchronized (this) {
            if (this._downloadsDao == null) {
                this._downloadsDao = new DownloadsDao_Impl(this);
            }
            downloadsDao = this._downloadsDao;
        }
        return downloadsDao;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public DownloadOperationsDao downloadOperationsDao() {
        DownloadOperationsDao downloadOperationsDao;
        if (this._downloadOperationsDao != null) {
            return this._downloadOperationsDao;
        }
        synchronized (this) {
            if (this._downloadOperationsDao == null) {
                this._downloadOperationsDao = new DownloadOperationsDao_Impl(this);
            }
            downloadOperationsDao = this._downloadOperationsDao;
        }
        return downloadOperationsDao;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public PlaybackMetricOperationsDao playbackMetricOperationsDao() {
        PlaybackMetricOperationsDao playbackMetricOperationsDao;
        if (this._playbackMetricOperationsDao != null) {
            return this._playbackMetricOperationsDao;
        }
        synchronized (this) {
            if (this._playbackMetricOperationsDao == null) {
                this._playbackMetricOperationsDao = new PlaybackMetricOperationsDao_Impl(this);
            }
            playbackMetricOperationsDao = this._playbackMetricOperationsDao;
        }
        return playbackMetricOperationsDao;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public UiMetricOperationsDao uiMetricOperationsDao() {
        UiMetricOperationsDao uiMetricOperationsDao;
        if (this._uiMetricOperationsDao != null) {
            return this._uiMetricOperationsDao;
        }
        synchronized (this) {
            if (this._uiMetricOperationsDao == null) {
                this._uiMetricOperationsDao = new UiMetricOperationsDao_Impl(this);
            }
            uiMetricOperationsDao = this._uiMetricOperationsDao;
        }
        return uiMetricOperationsDao;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public CompletedDao completedDao() {
        CompletedDao completedDao;
        if (this._completedDao != null) {
            return this._completedDao;
        }
        synchronized (this) {
            if (this._completedDao == null) {
                this._completedDao = new CompletedDao_Impl(this);
            }
            completedDao = this._completedDao;
        }
        return completedDao;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public CompletedOperationsDoa completedOperationsDoa() {
        CompletedOperationsDoa completedOperationsDoa;
        if (this._completedOperationsDoa != null) {
            return this._completedOperationsDoa;
        }
        synchronized (this) {
            if (this._completedOperationsDoa == null) {
                this._completedOperationsDoa = new CompletedOperationsDoa_Impl(this);
            }
            completedOperationsDoa = this._completedOperationsDoa;
        }
        return completedOperationsDoa;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public CategoryFollowsDao categoryFollowsDao() {
        CategoryFollowsDao categoryFollowsDao;
        if (this._categoryFollowsDao != null) {
            return this._categoryFollowsDao;
        }
        synchronized (this) {
            if (this._categoryFollowsDao == null) {
                this._categoryFollowsDao = new CategoryFollowsDao_Impl(this);
            }
            categoryFollowsDao = this._categoryFollowsDao;
        }
        return categoryFollowsDao;
    }

    @Override // com.amazon.podcast.storage.PodcastRoomDatabase
    public CategoryFollowOperationsDoa categoryFollowOperationsDoa() {
        CategoryFollowOperationsDoa categoryFollowOperationsDoa;
        if (this._categoryFollowOperationsDoa != null) {
            return this._categoryFollowOperationsDoa;
        }
        synchronized (this) {
            if (this._categoryFollowOperationsDoa == null) {
                this._categoryFollowOperationsDoa = new CategoryFollowOperationsDoa_Impl(this);
            }
            categoryFollowOperationsDoa = this._categoryFollowOperationsDoa;
        }
        return categoryFollowOperationsDoa;
    }
}
