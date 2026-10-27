package com.amazon.music.station;

import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.station.NoNextTrackException;
import com.amazon.musicplayqueueservice.client.common.QueueMetadata;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
@AnyThread
final class StationCache {
    private final boolean isExplicitFilterEnabled;
    private final Marketplace marketplace;

    @GuardedBy("this")
    private String pageToken;

    @Nullable
    @GuardedBy("this")
    private QueueMetadata queueMetadata;
    private final String remoteTargetId;

    @GuardedBy("this")
    private String stationFromAnythingSeedID;

    @GuardedBy("this")
    private String stationId;
    private final StationItem stationItem;
    private final StationService stationService;

    @GuardedBy("this")
    private NoNextTrackException terminatedReason;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @GuardedBy("this")
    private final List<Object> trackItems = new ArrayList();

    @GuardedBy("this")
    private int currentPosition = -1;

    @GuardedBy("this")
    private int numberOfTracksRequiredBeforeReplenishing = 2;

    @GuardedBy("this")
    private Status workerStatus = Status.IDLE;
    private volatile boolean isSTORMStation = false;

    private enum Status {
        IDLE,
        RUNNING,
        INTERRUPTED,
        TERMINATED
    }

    public static final StationCache create(Marketplace marketplace, StationItem stationItem, StationService stationService, boolean z, String str) {
        StationCache stationCache = new StationCache(marketplace, stationItem, stationService, z, str);
        stationCache.executeStartStationWorker();
        return stationCache;
    }

    Marketplace getMarketplace() {
        return this.marketplace;
    }

    StationItem getStationItem() {
        return this.stationItem;
    }

    synchronized String getStationId() {
        return this.stationId;
    }

    synchronized String getStationFromAnythingSeedID() {
        return this.stationFromAnythingSeedID;
    }

    synchronized QueueMetadata getQueueMetadata() {
        return this.queueMetadata;
    }

    synchronized String getPageToken() {
        return this.pageToken;
    }

    synchronized int getNumberOfTracksRequiredBeforeReplenishing() {
        return this.numberOfTracksRequiredBeforeReplenishing;
    }

    synchronized SynchronizedTrackItem nextTrack() throws NoNextTrackException, InterruptedException {
        Object obj;
        while (!isNextReady()) {
            debugLog("waiting for next track");
            wait();
        }
        startWorkerIfRequired();
        int i = this.currentPosition + 1;
        this.currentPosition = i;
        obj = this.trackItems.get(i);
        processInterruption(obj);
        debugLog("returning next track");
        return (SynchronizedTrackItem) obj;
    }

    synchronized SynchronizedTrackItem peekTrack() throws NoNextTrackException, InterruptedException {
        Object obj;
        while (!isNextReady()) {
            debugLog("waiting for peek track");
            wait();
        }
        startWorkerIfRequired();
        int i = this.currentPosition + 1;
        this.currentPosition = i;
        obj = this.trackItems.get(i);
        processInterruption(obj);
        this.currentPosition--;
        debugLog("returning peek track");
        return (SynchronizedTrackItem) obj;
    }

    synchronized void onCacheWorkerActive() {
        this.workerStatus = Status.RUNNING;
        debugLog("Worker running");
    }

    synchronized void onCacheUpdated(String str, List<SynchronizedTrackItem> list) {
        onCacheUpdated(this.stationId, str, this.queueMetadata, list);
    }

    synchronized void onCacheUpdated(String str, String str2, QueueMetadata queueMetadata, List<SynchronizedTrackItem> list) {
        this.stationId = str;
        this.pageToken = str2;
        if (queueMetadata != null) {
            this.queueMetadata = queueMetadata;
            this.numberOfTracksRequiredBeforeReplenishing = queueMetadata.getNumberOfTracksRemainingBeforeReplenishing();
        }
        parseStationFromAnythingSeedID(str2);
        addAll(list);
        this.workerStatus = Status.IDLE;
        debugLog("Worker done");
    }

    synchronized void interrupt(NoNextTrackException noNextTrackException) {
        this.workerStatus = Status.INTERRUPTED;
        if (NoNextTrackException.Reason.STATION_OUT_OF_TRACKS == noNextTrackException.getReason() || NoNextTrackException.Reason.STATION_NOT_FOUND == noNextTrackException.getReason()) {
            debugLog("Station Terminated:" + noNextTrackException.getReason().name());
            this.workerStatus = Status.TERMINATED;
            this.terminatedReason = noNextTrackException;
        }
        if (this.currentPosition < this.trackItems.size() - 1) {
            debugLog("Ignoring Worker interruption");
            return;
        }
        debugLog("Propagating Worker interruption");
        this.trackItems.add(noNextTrackException);
        notifyAll();
    }

    private StationCache(Marketplace marketplace, StationItem stationItem, StationService stationService, boolean z, String str) {
        Validate.notNull(marketplace, "Marketplace can't be null", new Object[0]);
        Validate.notNull(stationItem, "StationItem can't be null", new Object[0]);
        Validate.notNull(stationService, "StationService can't be null", new Object[0]);
        this.marketplace = marketplace;
        this.stationItem = stationItem;
        this.stationService = stationService;
        this.isExplicitFilterEnabled = z;
        this.remoteTargetId = str;
        if (stationItem.getNextToken() != null) {
            initializeToken();
        }
        debugLog("StationCache " + stationItem.getTitle() + " created");
    }

    private synchronized void initializeToken() {
        this.pageToken = this.stationItem.getNextToken();
        try {
            this.stationId = new JSONObject(this.pageToken).getString("queueId");
        } catch (JSONException e) {
            this.pageToken = null;
            errorLog("StationId could not be found as part of PageToken. The PageToken will not be used. " + e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0045 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void parseStationFromAnythingSeedID(java.lang.String r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 != 0) goto L5
            monitor-exit(r2)
            return
        L5:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30
            java.lang.String r3 = "additionalMetadata"
            boolean r3 = r0.has(r3)     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30
            if (r3 == 0) goto L45
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30
            java.lang.String r1 = "additionalMetadata"
            java.lang.String r0 = r0.getString(r1)     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30
            java.lang.String r0 = "SELECTION_SOURCE_ID"
            boolean r0 = r3.has(r0)     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30
            if (r0 == 0) goto L45
            java.lang.String r0 = "SELECTION_SOURCE_ID"
            java.lang.String r3 = r3.getString(r0)     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30
            r2.stationFromAnythingSeedID = r3     // Catch: java.lang.Throwable -> L2e org.json.JSONException -> L30
            goto L45
        L2e:
            r3 = move-exception
            goto L47
        L30:
            r3 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2e
            r0.<init>()     // Catch: java.lang.Throwable -> L2e
            java.lang.String r1 = "No Station From Anything Seed ID Found in the PageToken field"
            r0.append(r1)     // Catch: java.lang.Throwable -> L2e
            r0.append(r3)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r3 = r0.toString()     // Catch: java.lang.Throwable -> L2e
            r2.errorLog(r3)     // Catch: java.lang.Throwable -> L2e
        L45:
            monitor-exit(r2)
            return
        L47:
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.station.StationCache.parseStationFromAnythingSeedID(java.lang.String):void");
    }

    private synchronized void addAll(List<SynchronizedTrackItem> list) {
        Validate.notNull(list, "trackItems can't be null", new Object[0]);
        if (list.size() > 0) {
            Iterator<SynchronizedTrackItem> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if ("INTERLUDE_ID".equalsIgnoreCase(it.next().getIdentifierType())) {
                    this.isSTORMStation = true;
                    break;
                }
            }
            this.trackItems.addAll(list);
            notifyAll();
        }
    }

    private synchronized void startWorkerIfRequired() {
        int size = this.trackItems.size();
        int i = this.currentPosition;
        int i2 = size - (i + 1);
        Status status = this.workerStatus;
        if (status == Status.TERMINATED) {
            executeTerminatedStationWorker();
        } else {
            Status status2 = Status.INTERRUPTED;
            if (status == status2 && i == -1) {
                executeStartStationWorker();
            } else if (status == status2 || (this.numberOfTracksRequiredBeforeReplenishing >= i2 && status == Status.IDLE)) {
                executeContinueStationWorker();
            }
        }
    }

    private synchronized void executeStartStationWorker() {
        if (this.pageToken != null) {
            executeContinueStationWorker();
        } else {
            this.executorService.submit(new StartStationWorker(this, this.stationService, this.isExplicitFilterEnabled, this.remoteTargetId));
        }
    }

    private synchronized void executeContinueStationWorker() {
        this.executorService.submit(new ContinueStationWorker(this, this.stationService));
    }

    private synchronized void executeTerminatedStationWorker() {
        this.executorService.submit(new Runnable() { // from class: com.amazon.music.station.StationCache.1
            @Override // java.lang.Runnable
            public void run() {
                StationCache stationCache = StationCache.this;
                stationCache.interrupt(stationCache.terminatedReason);
            }
        });
    }

    private synchronized void processInterruption(Object obj) throws NoNextTrackException {
        if (obj instanceof NoNextTrackException) {
            errorLog("processing interruption: " + obj);
            List<Object> list = this.trackItems;
            int i = this.currentPosition;
            this.currentPosition = i + (-1);
            list.remove(i);
            throw ((NoNextTrackException) obj);
        }
    }

    private synchronized boolean isNextReady() {
        return this.currentPosition + 1 < this.trackItems.size();
    }

    private synchronized void debugLog(String str) {
        StationLog.debug(str, this.stationId, this.currentPosition, this.trackItems.size());
    }

    private synchronized void errorLog(String str) {
        StationLog.error(str, this.stationId, this.currentPosition, this.trackItems.size());
    }
}
