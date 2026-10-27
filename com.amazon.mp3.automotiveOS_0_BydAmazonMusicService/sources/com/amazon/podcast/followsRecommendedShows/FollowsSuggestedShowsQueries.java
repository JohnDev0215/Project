package com.amazon.podcast.followsRecommendedShows;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.follow.Follow;
import com.amazon.podcast.storage.PodcastRoomDatabase;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class FollowsSuggestedShowsQueries {
    private static final Long lowerBoundProgressMiliseconds = 60000L;
    private static final Integer upperBoundPercentage = Podcast.getBookmarksUpperBoundPercentage();
    private PodcastRoomDatabase database;

    public FollowsSuggestedShowsQueries(final Context context) {
        if (this.database == null) {
            this.database = PodcastRoomDatabase.get(context);
        }
    }

    public LiveData<List<Bookmark>> getBookmarksForFollowsRecommendedShows() {
        return this.database.bookmarksDao().getAllUniquePodcastEntries(lowerBoundProgressMiliseconds, upperBoundPercentage, Long.valueOf(convertDaysToMiliSeconds()));
    }

    public LiveData<List<Follow>> getAllFollowsForRecommendedShows() {
        return this.database.followsDao().getAllEntries();
    }

    private long convertDaysToMiliSeconds() {
        return System.currentTimeMillis() - TimeUnit.DAYS.toMillis(60L);
    }
}
