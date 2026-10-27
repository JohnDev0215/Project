package com.amazon.mp3.cloudqueue;

import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.amazon.mp3.cloudqueue.CloudQueueNoNextException;
import com.amazon.mp3.cloudqueue.model.AdInformation;
import com.amazon.mp3.cloudqueue.model.MetricsContext;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.QueueMetadata;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoResponse;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
@AnyThread
final class CloudQueueCache {
    private final AdInformation adInformation;
    private ChangeReason changeReason;
    private final CloudQueueService cloudQueueService;
    private final boolean isExplicitFilterEnabled;
    private final boolean isOnDemandPlayMode;
    private final boolean isShuffled;
    private final boolean isVoiceInitiated;
    private final Marketplace marketplace;
    private MetricsContext metricsContext;

    @GuardedBy("this")
    private String pageToken;
    private final List<PlayableEntityIdentifier> playableEntityIdentifierList;
    private String queueId;

    @GuardedBy("this")
    private QueueMetadata queueMetadata;
    private final String remoteTargetId;
    private final String startAtAsin;

    @GuardedBy("this")
    private CloudQueueNoNextException terminatedException;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @GuardedBy("this")
    private final List<Object> trackItems = new ArrayList();

    @GuardedBy("this")
    private int numberOfTracksRequiredBeforeReplenishing = 1;

    @GuardedBy("this")
    private int currentPosition = -1;

    @GuardedBy("this")
    private Status workerStatus = Status.IDLE;
    private List<TerminateCloudQueueListener> terminatecloudQueueListeners = new CopyOnWriteArrayList();

    private enum Status {
        IDLE,
        RUNNING,
        INTERRUPTED,
        TERMINATED
    }

    public interface TerminateCloudQueueListener {
        void onCloudQueueTerminated();
    }

    public static final CloudQueueCache create(final Marketplace marketplace, final AdInformation adInformation, final CloudQueueService cloudQueueService, final boolean isOnDemandPlayMode, final boolean isExplicitFilterEnabled, final String remoteTargetId, final List<PlayableEntityIdentifier> playableEntityIdentifierList, final String pageToken, final boolean isVoiceInitiated, final boolean isShuffled, final String startAtAsin) {
        CloudQueueCache cloudQueueCache = new CloudQueueCache(marketplace, adInformation, cloudQueueService, isOnDemandPlayMode, isExplicitFilterEnabled, remoteTargetId, playableEntityIdentifierList, pageToken, isVoiceInitiated, isShuffled, startAtAsin);
        cloudQueueCache.exectuteStartCloudQueueWorker();
        return cloudQueueCache;
    }

    synchronized GetConnectionInfoResponse getConnectionInfo() throws VolleyError {
        return this.cloudQueueService.getConnectionInfo();
    }

    synchronized String getPageToken() {
        return this.pageToken;
    }

    synchronized int getNumberOfTracksRequiredBeforeReplenishing() {
        return this.numberOfTracksRequiredBeforeReplenishing;
    }

    synchronized QueueMetadata getQueueMetaData() {
        return this.queueMetadata;
    }

    synchronized List<PlayableEntityIdentifier> getPlayableEntityIdentifierList() {
        return this.playableEntityIdentifierList;
    }

    @Nullable
    synchronized String getQueueId() {
        return this.queueId;
    }

    synchronized Boolean getIsVoiceInitiated() {
        return Boolean.valueOf(this.isVoiceInitiated);
    }

    synchronized SynchronizedPlayableEntity nextTrack() throws CloudQueueNoNextException, InterruptedException {
        Object obj;
        while (!isNextReady()) {
            log("waiting for next track");
            wait();
        }
        startWorkerIfRequired();
        int i = this.currentPosition + 1;
        this.currentPosition = i;
        obj = this.trackItems.get(i);
        processInterruption(obj);
        log("returning next track");
        return (SynchronizedPlayableEntity) obj;
    }

    synchronized void onCacheWorkerActive() {
        this.workerStatus = Status.RUNNING;
        log("Worker running");
    }

    synchronized void onCacheUpdated(final String pageToken, final List<SynchronizedPlayableEntity> trackItems) {
        onCacheUpdated(this.queueId, pageToken, this.queueMetadata, trackItems);
    }

    synchronized void onCacheUpdated(final String queueId, final String pageToken, QueueMetadata queueMetadata, final List<SynchronizedPlayableEntity> trackItems) {
        this.pageToken = pageToken;
        this.queueId = queueId;
        if (queueMetadata != null) {
            this.queueMetadata = queueMetadata;
            this.numberOfTracksRequiredBeforeReplenishing = queueMetadata.getNumberOfTracksRemainingBeforeReplenishing();
        }
        addAll(trackItems);
        this.workerStatus = Status.IDLE;
        log("Worker done");
    }

    synchronized void interrupt(final CloudQueueNoNextException cloudQueueNoNextException) {
        this.workerStatus = Status.INTERRUPTED;
        CloudQueueNoNextException.Reason reason = cloudQueueNoNextException.getReason();
        if (CloudQueueNoNextException.Reason.DEVICE_OFFLINE == reason || CloudQueueNoNextException.Reason.INVALID_STATION_SEED == reason || CloudQueueNoNextException.Reason.CONCURRENT_STREAM_LIMIT == reason || CloudQueueNoNextException.Reason.UNKNOWN_FAILURE == reason || CloudQueueNoNextException.Reason.QUEUE_TERMINATED == reason || CloudQueueNoNextException.Reason.REACH_END_OF_QUEUE == reason) {
            this.workerStatus = Status.TERMINATED;
            this.terminatedException = cloudQueueNoNextException;
        }
        if (this.currentPosition < this.trackItems.size() - 1) {
            log("Ignoring Worker interruption");
            return;
        }
        log("Propagating Worker interruption");
        this.trackItems.add(cloudQueueNoNextException);
        for (TerminateCloudQueueListener terminateCloudQueueListener : this.terminatecloudQueueListeners) {
            terminateCloudQueueListener.onCloudQueueTerminated();
            unregisterListener(terminateCloudQueueListener);
        }
        notifyAll();
    }

    private CloudQueueCache(final Marketplace marketplace, final AdInformation adInformation, final CloudQueueService cloudQueueService, final boolean isOnDemandPlayMode, final boolean isExplicitFilterEnabled, final String remoteTargetId, final List<PlayableEntityIdentifier> playableEntityIdentifierList, final String pageToken, final boolean isVoiceInitiated, final boolean isShuffled, final String startAtAsin) {
        Validate.notNull(marketplace, "Marketplace can't be null", new Object[0]);
        Validate.notNull(cloudQueueService, "CloudQueueService can't be null", new Object[0]);
        this.marketplace = marketplace;
        this.adInformation = adInformation;
        this.cloudQueueService = cloudQueueService;
        this.isOnDemandPlayMode = isOnDemandPlayMode;
        this.isExplicitFilterEnabled = isExplicitFilterEnabled;
        this.remoteTargetId = remoteTargetId;
        this.playableEntityIdentifierList = playableEntityIdentifierList;
        if (pageToken != null) {
            this.pageToken = pageToken;
            initializeQueueId();
        }
        this.isVoiceInitiated = isVoiceInitiated;
        this.isShuffled = isShuffled;
        this.startAtAsin = startAtAsin;
    }

    private synchronized void initializeQueueId() {
        try {
            this.queueId = new JSONObject(this.pageToken).getString("queueId");
        } catch (JSONException e) {
            this.pageToken = null;
            log("QueueId could not be found as part of PageToken. The PageToken will not be used. " + e);
        }
    }

    private synchronized void addAll(final List<SynchronizedPlayableEntity> trackItems) {
        Validate.notNull(trackItems, "trackItems can't be null", new Object[0]);
        if (trackItems.size() > 0) {
            this.trackItems.addAll(trackItems);
            notifyAll();
        }
    }

    private synchronized void startWorkerIfRequired() {
        int size = this.trackItems.size();
        int i = this.currentPosition;
        int i2 = size - (i + 1);
        Status status = this.workerStatus;
        if (status == Status.TERMINATED) {
            executeTerminatedCloudQueueWorker();
        } else {
            Status status2 = Status.INTERRUPTED;
            if (status == status2 && i == -1) {
                exectuteStartCloudQueueWorker();
            } else if (status == status2 || (this.numberOfTracksRequiredBeforeReplenishing >= i2 && status == Status.IDLE)) {
                executeContinueCloudQueueWorker();
            }
        }
    }

    private synchronized void exectuteStartCloudQueueWorker() {
        if (this.pageToken != null) {
            executeContinueCloudQueueWorker();
        } else {
            this.executorService.submit(new StartCloudQueueWorker(this, this.adInformation, this.cloudQueueService, this.isOnDemandPlayMode, this.isExplicitFilterEnabled, this.remoteTargetId, this.playableEntityIdentifierList, this.isShuffled, this.startAtAsin));
        }
    }

    private synchronized void executeContinueCloudQueueWorker() {
        this.executorService.submit(new ContinueCloudQueueWorker(this, this.cloudQueueService, this.adInformation, this.changeReason));
    }

    private synchronized void executeTerminatedCloudQueueWorker() {
        this.executorService.submit(new Runnable() { // from class: com.amazon.mp3.cloudqueue.CloudQueueCache.1
            @Override // java.lang.Runnable
            public void run() {
                CloudQueueCache cloudQueueCache = CloudQueueCache.this;
                cloudQueueCache.interrupt(cloudQueueCache.terminatedException);
            }
        });
    }

    private synchronized void processInterruption(final Object object) throws CloudQueueNoNextException {
        if (object instanceof CloudQueueNoNextException) {
            log("processing interruption: " + object);
            List<Object> list = this.trackItems;
            int i = this.currentPosition;
            this.currentPosition = i + (-1);
            list.remove(i);
            throw ((CloudQueueNoNextException) object);
        }
    }

    private synchronized boolean isNextReady() {
        return this.currentPosition + 1 < this.trackItems.size();
    }

    private synchronized void log(final String msg) {
    }

    public void registerListener(final TerminateCloudQueueListener listener) {
        this.terminatecloudQueueListeners.add(listener);
    }

    public void unregisterListener(final TerminateCloudQueueListener listener) {
        this.terminatecloudQueueListeners.remove(listener);
    }

    MetricsContext getMetricsContext() {
        return this.metricsContext;
    }

    void setMetricsContext(final MetricsContext metricsContext) {
        this.metricsContext = metricsContext;
    }

    void setChangeReason(ChangeReason changeReason) {
        this.changeReason = changeReason;
    }
}
