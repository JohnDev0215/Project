package com.amazon.mp3.prime.cta;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.mp3.external.api.uri.FindUriByAsinJob;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeTrackTask extends PrimeCollectionTask {
    public static PrimeTrackTask createPlaybackTask(final Context context, final String albumAsin, final String trackAsin, final PlaybackMetricInformation metricInformation, final PlaybackPageType pageType, final PrimeCollectionTask.Task playbackTask, final FindUriByAsinJob.QueryByAsin queryByAsin, final long startTime, final PrimeCollectionTask.OnUriReadyCallback callback, final boolean refreshCollection) {
        PrimeTrackTask primeTrackTask = new PrimeTrackTask(context, albumAsin, trackAsin, false, metricInformation, playbackTask, queryByAsin, startTime, callback, refreshCollection);
        primeTrackTask.mPageType = pageType;
        return primeTrackTask;
    }

    public static PrimeTrackTask createPlaybackTask(final Context context, final String albumAsin, final String trackAsin, @Nullable final PlaybackMetricInformation metricInformation, final PlaybackPageType pageType, final PrimeCollectionTask.Task playbackTask, final FindUriByAsinJob.QueryByAsin queryByAsin, @Nullable final PrimeCollectionTask.OnUriReadyCallback callback) {
        return createPlaybackTask(context, albumAsin, trackAsin, metricInformation, pageType, playbackTask, queryByAsin, callback, true);
    }

    public static PrimeTrackTask createPlaybackTask(final Context context, final String albumAsin, final String trackAsin, final PlaybackMetricInformation metricInformation, final PlaybackPageType pageType, final PrimeCollectionTask.Task playbackTask, final FindUriByAsinJob.QueryByAsin queryByAsin, final PrimeCollectionTask.OnUriReadyCallback callback, final boolean refreshCollection) {
        PrimeTrackTask primeTrackTask = new PrimeTrackTask(context, albumAsin, trackAsin, false, metricInformation, playbackTask, queryByAsin, Clock.now(), callback, refreshCollection);
        primeTrackTask.mPageType = pageType;
        return primeTrackTask;
    }

    private PrimeTrackTask(final Context context, final String albumAsin, final String trackAsin, final boolean forceRefresh, final PlaybackMetricInformation metricInformation, final PrimeCollectionTask.Task task, final FindUriByAsinJob.QueryByAsin queryByAsin, final long startTime, final PrimeCollectionTask.OnUriReadyCallback callback, final boolean refreshCollection) {
        super(context, albumAsin, trackAsin, forceRefresh, metricInformation, task, queryByAsin, startTime, callback, refreshCollection, null);
    }

    @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask
    public Uri getTracksUri() {
        return this.mCollectionUri;
    }

    @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask
    public String getAsinToQuery() {
        return this.mStartingTrackAsin;
    }
}
