package com.amazon.mp3.cloudqueue;

import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.WorkerThread;
import com.amazon.mp3.cloudqueue.CloudQueueCache;
import com.amazon.mp3.cloudqueue.model.AdInformation;
import com.amazon.mp3.cloudqueue.model.ConnectionInfoMetadata;
import com.amazon.mp3.cloudqueue.model.MetricsContext;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.QueueMetadata;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.media.playback.ChangeReason;
import com.android.volley.VolleyError;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@AnyThread
public final class CloudQueue {
    private final CloudQueueCache cloudQueueCache;

    public static CloudQueue create(final Marketplace marketplace, final CloudQueueService cloudQueueService, final boolean isOnDemandPlayMode, final AdInformation adInformation, final boolean isExplicitFilterEnabled, final String remoteTargetId, final List<PlayableEntityIdentifier> playableEntityIdentifierList, final String pageToken, final boolean isVoiceInitiated, final boolean isShuffled, final String startAtAsin) {
        return new CloudQueue(CloudQueueCache.create(marketplace, adInformation, cloudQueueService, isOnDemandPlayMode, isExplicitFilterEnabled, remoteTargetId, playableEntityIdentifierList, pageToken, isVoiceInitiated, isShuffled, startAtAsin));
    }

    @GuardedBy("this")
    public synchronized SynchronizedPlayableEntity nextTrack() throws CloudQueueNoNextException, InterruptedException {
        return this.cloudQueueCache.nextTrack();
    }

    @WorkerThread
    public synchronized ConnectionInfoMetadata getConnectionInfo() throws VolleyError {
        return QueueEntityUtil.convertConnectionInfoMetadata(this.cloudQueueCache.getConnectionInfo());
    }

    private CloudQueue(final CloudQueueCache cloudQueueCache) {
        this.cloudQueueCache = cloudQueueCache;
    }

    public QueueMetadata getQueueMetadata() {
        return this.cloudQueueCache.getQueueMetaData();
    }

    public String getQueueId() {
        return this.cloudQueueCache.getQueueId();
    }

    public void registerListener(final CloudQueueCache.TerminateCloudQueueListener listener) {
        this.cloudQueueCache.registerListener(listener);
    }

    public List<PlayableEntityIdentifier> getPlayableEntityIdentifierList() {
        return this.cloudQueueCache.getPlayableEntityIdentifierList();
    }

    public MetricsContext getMetricsContext() {
        return this.cloudQueueCache.getMetricsContext();
    }

    public void setChangeReason(ChangeReason changeReason) {
        this.cloudQueueCache.setChangeReason(changeReason);
    }
}
