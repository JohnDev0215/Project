package com.amazon.mp3.prime.cta;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.library.fragment.AlbumRemoteMetadataListener;
import com.amazon.mp3.prime.PrimeTracksCache;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.service.job.Job;
import com.amazon.music.media.playback.util.Clock;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeAlbumJob extends Job {
    private PrimeCollectionTask mPrimeCollectionTask;

    public PrimeAlbumJob(Context context, String albumAsin, boolean forceRefresh, final AlbumRemoteMetadataListener albumRemoteMetadataListener) {
        this.mPrimeCollectionTask = new PrimeCollectionTask(context, albumAsin, forceRefresh, PrimeCollectionTask.Task.NONE, new QueryAlbumByAsin(context), Clock.now(), albumRemoteMetadataListener);
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        return this.mPrimeCollectionTask.fetchCollection().intValue();
    }

    public boolean hasPrimeContent() {
        return this.mPrimeCollectionTask.hasPrimeContent();
    }

    public int getPrimeTrackCacheCount() {
        return this.mPrimeCollectionTask.getPrimeTrackCacheCount();
    }

    public PrimeTracksCache.PrimeUIState getPrimeUIState() {
        return this.mPrimeCollectionTask.getPrimeUIState();
    }

    public String getAlbumArtUrl() {
        return this.mPrimeCollectionTask.getCollectionArtUrl();
    }

    public Uri getAlbumUri() {
        return this.mPrimeCollectionTask.getUri();
    }
}
