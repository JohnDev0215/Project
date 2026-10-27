package com.amazon.podcast.skills;

import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.storage.PodcastRoomDatabase;

/* JADX INFO: loaded from: classes5.dex */
enum PlayQueue {
    INSTANCE;

    private long currentUpdateTime;

    public Download startAt(final String episodeId, final PodcastRoomDatabase database) {
        Download download = database.downloadsDao().get(episodeId);
        if (download == null) {
            return null;
        }
        this.currentUpdateTime = download.getUpdatedTime();
        return download;
    }

    public Download next(final PodcastRoomDatabase database) {
        Download next = database.downloadsDao().getNext(this.currentUpdateTime);
        if (next == null) {
            return null;
        }
        this.currentUpdateTime = next.getUpdatedTime();
        return next;
    }

    public Download previous(final PodcastRoomDatabase database) {
        Download previous = database.downloadsDao().getPrevious(this.currentUpdateTime);
        if (previous == null) {
            return null;
        }
        this.currentUpdateTime = previous.getUpdatedTime();
        return previous;
    }
}
