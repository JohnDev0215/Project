package com.amazon.podcast.storage;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.amazon.podcast.biteBookmark.BiteBookmark;
import com.amazon.podcast.biteBookmark.BiteBookmarkOperations;
import com.amazon.podcast.biteBookmark.BiteBookmarkOperationsDoa;
import com.amazon.podcast.biteBookmark.BiteBookmarksDao;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.bookmark.BookmarkOperations;
import com.amazon.podcast.bookmark.BookmarkOperationsDoa;
import com.amazon.podcast.bookmark.BookmarksDao;
import com.amazon.podcast.categoryFollow.CategoryFollow;
import com.amazon.podcast.categoryFollow.CategoryFollowOperations;
import com.amazon.podcast.categoryFollow.CategoryFollowOperationsDoa;
import com.amazon.podcast.categoryFollow.CategoryFollowsDao;
import com.amazon.podcast.completed.Completed;
import com.amazon.podcast.completed.CompletedDao;
import com.amazon.podcast.completed.CompletedOperations;
import com.amazon.podcast.completed.CompletedOperationsDoa;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.downloads.DownloadOperations;
import com.amazon.podcast.downloads.DownloadOperationsDao;
import com.amazon.podcast.downloads.DownloadsDao;
import com.amazon.podcast.follow.Follow;
import com.amazon.podcast.follow.FollowOperations;
import com.amazon.podcast.follow.FollowOperationsDoa;
import com.amazon.podcast.follow.FollowsDao;
import com.amazon.podcast.metrics.playback.PlaybackMetricOperations;
import com.amazon.podcast.metrics.playback.PlaybackMetricOperationsDao;
import com.amazon.podcast.metrics.ui.UiMetricOperations;
import com.amazon.podcast.metrics.ui.UiMetricOperationsDao;
import com.amazon.podcast.save.Save;
import com.amazon.podcast.save.SaveOperations;
import com.amazon.podcast.save.SaveOperationsDoa;
import com.amazon.podcast.save.SavesDao;

/* JADX INFO: loaded from: classes5.dex */
@Database(entities = {Follow.class, FollowOperations.class, Bookmark.class, BookmarkOperations.class, BiteBookmark.class, BiteBookmarkOperations.class, Save.class, SaveOperations.class, Download.class, DownloadOperations.class, PlaybackMetricOperations.class, UiMetricOperations.class, Completed.class, CompletedOperations.class, CategoryFollow.class, CategoryFollowOperations.class}, exportSchema = true, version = 10)
public abstract class PodcastRoomDatabase extends RoomDatabase {
    private static volatile PodcastRoomDatabase INSTANCE;

    public abstract BiteBookmarkOperationsDoa biteBookmarkOperationsDoa();

    public abstract BiteBookmarksDao biteBookmarksDao();

    public abstract BookmarkOperationsDoa bookmarkOperationsDoa();

    public abstract BookmarksDao bookmarksDao();

    public abstract CategoryFollowOperationsDoa categoryFollowOperationsDoa();

    public abstract CategoryFollowsDao categoryFollowsDao();

    public abstract CompletedDao completedDao();

    public abstract CompletedOperationsDoa completedOperationsDoa();

    public abstract DownloadOperationsDao downloadOperationsDao();

    public abstract DownloadsDao downloadsDao();

    public abstract FollowOperationsDoa followOperationsDoa();

    public abstract FollowsDao followsDao();

    public abstract PlaybackMetricOperationsDao playbackMetricOperationsDao();

    public abstract SaveOperationsDoa saveOperationsDoa();

    public abstract SavesDao savesDao();

    public abstract UiMetricOperationsDao uiMetricOperationsDao();

    public static PodcastRoomDatabase get(final Context context) {
        if (INSTANCE == null) {
            synchronized (PodcastRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = (PodcastRoomDatabase) Room.databaseBuilder(context.getApplicationContext(), PodcastRoomDatabase.class, "podcast_database").addMigrations(PodcastRoomMigrations.MIGRATION_1_2, PodcastRoomMigrations.MIGRATION_2_3, PodcastRoomMigrations.MIGRATION_3_4, PodcastRoomMigrations.MIGRATION_4_5, PodcastRoomMigrations.MIGRATION_5_6, PodcastRoomMigrations.MIGRATION_6_7, PodcastRoomMigrations.MIGRATION_7_8, PodcastRoomMigrations.MIGRATION_8_9, PodcastRoomMigrations.MIGRATION_9_10).build();
                }
            }
        }
        return INSTANCE;
    }
}
