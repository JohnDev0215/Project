package com.amazon.avod.media.events;

import android.database.sqlite.SQLiteBlobTooBigException;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.avod.media.events.AloysiusCyclicEventMasterController;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaReportDispatcherServices;
import com.amazon.avod.media.events.clientapi.BatchConfig;
import com.amazon.avod.media.events.clientapi.EventConfig;
import com.amazon.avod.media.events.dao.MediaReportDAO;
import com.amazon.avod.media.events.dao.MediaReportRecord;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDiagnosticsEvent;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public class MediaEventDispatcher implements MediaEventQueueListener {
    private final AloysiusConfig mAloysiusConfig;
    private boolean mAreConfigsCached;
    private BatchConfig mBatchConfig;
    private Set<BatchDispatcher> mBatchListeners;
    private final AloysiusCyclicEventMasterController.AloysiusCyclicEventMasterListener mCyclicEventMasterListener;
    private final Map<MediaEvent.MediaEventType, EventConfig> mEventConfig;
    private final Executor mExecutor;
    private int mMaxEventsToSerializePerCall;
    private int mMaxRecordsAllowedInDB;
    private int mMaxRecordsToSendPerDispatch;
    private final MediaEventQueue mMediaEventQueue;
    private final MediaReportDAO mMediaReportDAO;
    private final MediaReportDispatcherServices mMediaReportDispatcherServices;
    private ScheduledFuture<?> mNextPeriodExecution;
    private PlaybackEventReporter mPlaybackEventReporter;
    private final Lock mQueueOperationsLock;
    private final NavigableSet<Long> mRecordsInFlight;
    private final ScheduledExecutorService mScheduledExecutorService;
    private boolean mShouldDropDatabaseOnGiantRow;
    private boolean mShouldDropGiantRowsInsteadOfInserting;

    public interface IRecordInflator {
        long getRecordId();

        @Nullable
        List<String> inflate() throws JSONException;
    }

    public MediaEventDispatcher(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull MediaReportDAO mediaReportDAO, @Nonnull MediaReportDispatcherServices mediaReportDispatcherServices, @Nonnull ScheduledExecutorService scheduledExecutorService, @Nonnull QosEventReporterFactory qosEventReporterFactory, @Nonnull BatchDispatcherListener batchDispatcherListener) {
        this(mediaEventQueue, mediaReportDAO, mediaReportDispatcherServices, scheduledExecutorService, ((QosEventReporterFactory) Preconditions.checkNotNull(qosEventReporterFactory, "reporterFactory")).newStandaloneEventReporter((String) null), batchDispatcherListener, AloysiusConfig.getInstance(), ScheduledExecutorBuilder.newBuilderFor(MediaEventDispatcher.class, new String[0]).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).withFixedThreadPoolSize(1).build());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @VisibleForTesting
    MediaEventDispatcher(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull MediaReportDAO mediaReportDAO, @Nonnull MediaReportDispatcherServices mediaReportDispatcherServices, @Nonnull ScheduledExecutorService scheduledExecutorService, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull BatchDispatcherListener batchDispatcherListener, @Nonnull AloysiusConfig aloysiusConfig, @Nonnull Executor executor) {
        this.mAreConfigsCached = false;
        this.mEventConfig = new HashMap();
        this.mCyclicEventMasterListener = new AloysiusCyclicEventMasterController.AloysiusCyclicEventMasterListener() { // from class: com.amazon.avod.media.events.MediaEventDispatcher$$ExternalSyntheticLambda2
            @Override // com.amazon.avod.media.events.AloysiusCyclicEventMasterController.AloysiusCyclicEventMasterListener
            public final void masterCyclicReporterStatusChanged(boolean z) {
                this.f$0.lambda$new$3(z);
            }
        };
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mMediaReportDAO = (MediaReportDAO) Preconditions.checkNotNull(mediaReportDAO, "mediaReportDAO");
        this.mMediaReportDispatcherServices = (MediaReportDispatcherServices) Preconditions.checkNotNull(mediaReportDispatcherServices, "mediaReportDispatcherServices");
        this.mScheduledExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService");
        this.mQueueOperationsLock = new ReentrantLock();
        Set<BatchDispatcher> setNewConcurrentHashSet = Sets.newConcurrentHashSet();
        this.mBatchListeners = setNewConcurrentHashSet;
        setNewConcurrentHashSet.add(Preconditions.checkNotNull(batchDispatcherListener, "batchDispatcherListener"));
        this.mPlaybackEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "reporter");
        this.mRecordsInFlight = Sets.newTreeSet();
        this.mAloysiusConfig = (AloysiusConfig) Preconditions.checkNotNull(aloysiusConfig, "aloysiusConfig");
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor, "executor");
    }

    public void bootstrap(final String str) {
        AloysiusCyclicEventMasterController.getInstance().registerListener(this.mCyclicEventMasterListener);
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.events.MediaEventDispatcher$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$bootstrap$0(str, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bootstrap$0(String str, MediaEventDispatcher mediaEventDispatcher) {
        validateDBOperationsRunOnThreadPool();
        this.mQueueOperationsLock.lock();
        try {
            reportNonfatalError(String.valueOf(str), "AloysiusBootstrapAppInstanceID");
            reportNonfatalError(String.valueOf(getAllRecordIds().size()), "AloysiusBootstrapTotalRecords");
            sanitizeRecordsAndDispatchNOldest();
            if (this.mAloysiusConfig.shouldDeleteOversizedEventDB()) {
                long jSizeIncludingNullAppInstanceId = this.mMediaEventQueue.sizeIncludingNullAppInstanceId();
                DLog.logf("aloysius bootstrap: total count on disk: %d", Long.valueOf(jSizeIncludingNullAppInstanceId));
                reportNonfatalError(String.valueOf(jSizeIncludingNullAppInstanceId), "AloysiusBootstrapTotalEvents");
                if (jSizeIncludingNullAppInstanceId > this.mAloysiusConfig.getMaxEventsToLoadAtBootstrap()) {
                    int iClear = this.mMediaEventQueue.clear();
                    reportNonfatalError(String.valueOf(iClear), "AloysiusBootstrapDroppedEvents");
                    DLog.logf("aloysius bootstrap: dropped: %d", Integer.valueOf(iClear));
                }
            }
            LinkedListMultimap linkedListMultimapCreate = LinkedListMultimap.create();
            for (MediaEventQueue.PersistentMediaEvent persistentMediaEvent : this.mMediaEventQueue.bootstrap()) {
                linkedListMultimapCreate.put(persistentMediaEvent.getAppInstanceId(), persistentMediaEvent);
            }
            for (String str2 : linkedListMultimapCreate.keySet()) {
                List<MediaEventQueue.PersistentMediaEvent> list = linkedListMultimapCreate.get(str2);
                DLog.logf("aloysius bootstrap: draining queue events, appInstanceId: %s, events: %d", str2, Integer.valueOf(list.size()));
                serializeEventsAndDispatch(str2, list);
            }
            this.mMediaEventQueue.registerMediaEventQueueListener(mediaEventDispatcher);
            this.mMediaEventQueue.setAppInstanceId(str);
        } finally {
            this.mQueueOperationsLock.unlock();
        }
    }

    @Override // com.amazon.avod.media.events.MediaEventQueueListener
    public void newEvent(@Nonnull final MediaEventQueue.PersistentMediaEvent persistentMediaEvent) {
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.events.MediaEventDispatcher$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$newEvent$1(persistentMediaEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$newEvent$1(MediaEventQueue.PersistentMediaEvent persistentMediaEvent) {
        validateDBOperationsRunOnThreadPool();
        this.mQueueOperationsLock.lock();
        try {
            long size = this.mMediaEventQueue.size();
            long jMin = Math.min(this.mBatchConfig != null ? r2.getDepth().intValue() : 2147483647L, this.mMaxEventsToSerializePerCall);
            if (shouldForceDispatchMediaEvent(this.mEventConfig.get(persistentMediaEvent.getEventType()))) {
                DLog.devf("Force dispatching the following event: %s", persistentMediaEvent.getEventType());
                serializeEventsAndDispatch(((MediaEventQueue.PersistentMediaEvent) Preconditions.checkNotNull(persistentMediaEvent, NotificationCompat.CATEGORY_EVENT)).getAppInstanceId(), this.mMediaEventQueue.peek(size));
            } else if (this.mBatchConfig != null && size >= jMin) {
                serializeEventsAndDispatch(((MediaEventQueue.PersistentMediaEvent) Preconditions.checkNotNull(persistentMediaEvent, NotificationCompat.CATEGORY_EVENT)).getAppInstanceId(), this.mMediaEventQueue.peek(jMin));
            }
        } finally {
            this.mQueueOperationsLock.unlock();
        }
    }

    private boolean shouldForceDispatchMediaEvent(@Nullable EventConfig eventConfig) {
        return eventConfig != null && eventConfig.getForceDispatch() && this.mAloysiusConfig.shouldForceDispatchAloysiusEventsByBootstrap();
    }

    public void flush() {
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.events.MediaEventDispatcher$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$flush$2();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$flush$2() {
        validateDBOperationsRunOnThreadPool();
        this.mQueueOperationsLock.lock();
        try {
            MediaEventQueue mediaEventQueue = this.mMediaEventQueue;
            List<MediaEventQueue.PersistentMediaEvent> listPeek = mediaEventQueue.peek(mediaEventQueue.size());
            if (!listPeek.isEmpty()) {
                serializeEventsAndDispatch(listPeek.get(0).getAppInstanceId(), listPeek);
            }
        } finally {
            this.mQueueOperationsLock.unlock();
        }
    }

    private void serializeEventsAndDispatch(@Nonnull String str, List<MediaEventQueue.PersistentMediaEvent> list) {
        boolean z;
        if (list.size() <= 0) {
            return;
        }
        Preconditions.checkNotNull(str, "appInstanceId");
        Iterator<MediaEventQueue.PersistentMediaEvent> it = list.iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                break;
            }
            MediaEventQueue.PersistentMediaEvent next = it.next();
            Preconditions2.checkArgumentWeakly(str.equalsIgnoreCase(next.getAppInstanceId()), "Attempted to add event with appinstanceid %s to batch with appinstanceid %s", next.getAppInstanceId(), str);
        }
        validateDBOperationsRunOnThreadPool();
        this.mQueueOperationsLock.lock();
        try {
            cancelPeriodicDispatchTrigger();
            MediaReportRecord mediaReportRecordCreateMediaReportRecord = createMediaReportRecord(str, list);
            if (this.mShouldDropGiantRowsInsteadOfInserting && mediaReportRecordCreateMediaReportRecord.getPayload().getBytes(StandardCharsets.UTF_8).length >= 1992294) {
                StringBuilder sb = new StringBuilder();
                for (MediaEventQueue.PersistentMediaEvent persistentMediaEvent : list) {
                    sb.append(persistentMediaEvent.getEventType());
                    sb.append(':');
                    sb.append(persistentMediaEvent.getPayload().length());
                    sb.append(',');
                }
                reportNonfatalError(sb.toString(), "AloysiusRecordInsertOversized");
                z = false;
            }
            if (z) {
                this.mMediaReportDAO.insert(mediaReportRecordCreateMediaReportRecord);
            }
            this.mMediaEventQueue.remove(str, getSequenceIds(list));
            notifyListeners();
            sanitizeRecordsAndDispatchNOldest();
        } finally {
            this.mQueueOperationsLock.unlock();
            setPeriodicDispatchTrigger();
        }
    }

    public void setEventConfig(@Nonnull Map<MediaEvent.MediaEventType, EventConfig> map) {
        this.mQueueOperationsLock.lock();
        try {
            this.mEventConfig.clear();
            this.mEventConfig.putAll(map);
            this.mMediaEventQueue.setEventConfig(map);
        } finally {
            this.mQueueOperationsLock.unlock();
        }
    }

    public class RecordInflator implements IRecordInflator {
        private final long mRecordId;

        public RecordInflator(long j) {
            this.mRecordId = j;
        }

        @Override // com.amazon.avod.media.events.MediaEventDispatcher.IRecordInflator
        @Nullable
        public List<String> inflate() throws JSONException {
            MediaEventDispatcher.this.validateDBOperationsRunOnThreadPool();
            return MediaEventDispatcher.this.getEventsFromRecord(this.mRecordId);
        }

        @Override // com.amazon.avod.media.events.MediaEventDispatcher.IRecordInflator
        public long getRecordId() {
            return this.mRecordId;
        }
    }

    private class RemoveFromDB implements MediaReportDispatcherServices.ExecutionCallback {
        private long mRecordId;

        public RemoveFromDB(long j) {
            this.mRecordId = j;
        }

        @Override // com.amazon.avod.media.events.MediaReportDispatcherServices.ExecutionCallback
        public void onComplete(@Nullable BatchConfig batchConfig) {
            MediaEventDispatcher.this.validateDBOperationsRunOnThreadPool();
            MediaEventDispatcher.this.mQueueOperationsLock.lock();
            try {
                DLog.devf("removing payload from the DB: %d", Long.valueOf(this.mRecordId));
                MediaEventDispatcher.this.mMediaReportDAO.remove(this.mRecordId);
                MediaEventDispatcher.this.mRecordsInFlight.remove(Long.valueOf(this.mRecordId));
                if (batchConfig != null) {
                    MediaEventDispatcher.this.mBatchConfig = batchConfig;
                }
            } finally {
                MediaEventDispatcher.this.mQueueOperationsLock.unlock();
            }
        }
    }

    private void dispatch(String str, long j) {
        validateDBOperationsRunOnThreadPool();
        this.mQueueOperationsLock.lock();
        try {
            this.mRecordsInFlight.add(Long.valueOf(j));
            RemoveFromDB removeFromDB = new RemoveFromDB(j);
            this.mMediaReportDispatcherServices.dispatch(str, new RecordInflator(j), removeFromDB, removeFromDB);
        } finally {
            this.mQueueOperationsLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public List<String> getEventsFromRecord(long j) throws JSONException {
        List<String> eventsFromPayload;
        validateDBOperationsRunOnThreadPool();
        this.mQueueOperationsLock.lock();
        try {
            MediaReportRecord byId = this.mMediaReportDAO.getById(j);
            if (byId != null) {
                eventsFromPayload = getEventsFromPayload(byId.getPayload());
            } else {
                DLog.errorf("Unexpected missing record %s in database.", Long.toString(j));
                eventsFromPayload = null;
            }
            return eventsFromPayload;
        } finally {
            this.mQueueOperationsLock.unlock();
        }
    }

    @VisibleForTesting
    static List<Long> getNOldestRecordsToDispatch(NavigableSet<Long> navigableSet, int i) {
        return Lists.newArrayList(Iterables.limit(navigableSet, i));
    }

    @VisibleForTesting
    static List<Long> getObsoleteRecordsToSanitizeDB(NavigableSet<Long> navigableSet, int i) {
        return Lists.newArrayList(Iterables.limit(navigableSet, navigableSet.size() - Math.min(i, navigableSet.size())));
    }

    private void sanitizeRecordsAndDispatchNOldest() {
        validateDBOperationsRunOnThreadPool();
        this.mQueueOperationsLock.lock();
        try {
            List<Long> obsoleteRecordsToSanitizeDB = getObsoleteRecordsToSanitizeDB(getAllRecordIdsNotInFlight(), this.mMaxRecordsAllowedInDB);
            if (obsoleteRecordsToSanitizeDB.size() > 0) {
                reportNonfatalError(String.valueOf(obsoleteRecordsToSanitizeDB.size()), "AloysiusDroppedRecords");
            }
            Iterator<Long> it = obsoleteRecordsToSanitizeDB.iterator();
            while (it.hasNext()) {
                this.mMediaReportDAO.remove(it.next().longValue());
            }
            Iterator<Long> it2 = getNOldestRecordsToDispatch(getAllRecordIdsNotInFlight(), this.mMaxRecordsToSendPerDispatch).iterator();
            while (it2.hasNext()) {
                try {
                    MediaReportRecord byId = this.mMediaReportDAO.getById(it2.next().longValue());
                    if (byId != null && byId.getId() != null) {
                        dispatch(byId.getAppInstanceId(), byId.getId().longValue());
                    }
                } catch (SQLiteBlobTooBigException | IllegalStateException e) {
                    if (this.mShouldDropDatabaseOnGiantRow) {
                        this.mMediaReportDAO.clear();
                        reportNonfatalError(e.getMessage(), "AloysiusRecordDroppedOversized");
                    }
                }
            }
            this.mMediaReportDAO.clearLegacyDatabase();
        } finally {
            this.mQueueOperationsLock.unlock();
        }
    }

    private NavigableSet<Long> getAllRecordIds() {
        validateDBOperationsRunOnThreadPool();
        this.mQueueOperationsLock.lock();
        try {
            return Sets.newTreeSet(this.mMediaReportDAO.getAllIds());
        } finally {
            this.mQueueOperationsLock.unlock();
        }
    }

    private NavigableSet<Long> getAllRecordIdsNotInFlight() {
        validateDBOperationsRunOnThreadPool();
        this.mQueueOperationsLock.lock();
        try {
            NavigableSet<Long> allRecordIds = getAllRecordIds();
            allRecordIds.removeAll(this.mRecordsInFlight);
            return allRecordIds;
        } finally {
            this.mQueueOperationsLock.unlock();
        }
    }

    private List<Long> getSequenceIds(List<MediaEventQueue.PersistentMediaEvent> list) {
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        Iterator<MediaEventQueue.PersistentMediaEvent> it = list.iterator();
        while (it.hasNext()) {
            arrayListNewArrayList.add(Long.valueOf(it.next().getSequence()));
        }
        return arrayListNewArrayList;
    }

    public void setBatchConfig(@Nullable BatchConfig batchConfig) {
        this.mQueueOperationsLock.lock();
        try {
            boolean z = this.mBatchConfig == null;
            this.mBatchConfig = batchConfig;
            if (z) {
                setPeriodicDispatchTrigger();
            }
        } finally {
            this.mQueueOperationsLock.unlock();
        }
    }

    private void cancelPeriodicDispatchTrigger() {
        this.mQueueOperationsLock.lock();
        try {
            ScheduledFuture<?> scheduledFuture = this.mNextPeriodExecution;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
                this.mNextPeriodExecution = null;
            }
        } finally {
            this.mQueueOperationsLock.unlock();
        }
    }

    private void setPeriodicDispatchTrigger() {
        this.mQueueOperationsLock.lock();
        try {
            if (this.mBatchConfig != null) {
                if (!AloysiusCyclicEventMasterController.getInstance().isEnabled()) {
                    return;
                }
                ScheduledFuture<?> scheduledFuture = this.mNextPeriodExecution;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                }
                this.mNextPeriodExecution = this.mScheduledExecutorService.schedule(new Runnable() { // from class: com.amazon.avod.media.events.MediaEventDispatcher$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.flush();
                    }
                }, this.mBatchConfig.getPeriod().longValue(), TimeUnit.MILLISECONDS);
            }
        } finally {
            this.mQueueOperationsLock.unlock();
        }
    }

    private MediaReportRecord createMediaReportRecord(String str, List<MediaEventQueue.PersistentMediaEvent> list) {
        return new MediaReportRecord(str, createMediaReportPayload(getEventPayloads(list)));
    }

    private static List<String> getEventsFromPayload(String str) throws JSONException {
        JSONArray jSONArray = new JSONArray(str);
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        for (int i = 0; i < jSONArray.length(); i++) {
            linkedListNewLinkedList.add(jSONArray.get(i).toString());
        }
        return linkedListNewLinkedList;
    }

    private String createMediaReportPayload(List<String> list) {
        return String.format(Locale.US, "[%s]", Joiner.on(",").join(list));
    }

    private List<String> getEventPayloads(List<MediaEventQueue.PersistentMediaEvent> list) {
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        Iterator<MediaEventQueue.PersistentMediaEvent> it = list.iterator();
        while (it.hasNext()) {
            linkedListNewLinkedList.add(it.next().getPayload());
        }
        return linkedListNewLinkedList;
    }

    private void notifyListeners() {
        Iterator<BatchDispatcher> it = this.mBatchListeners.iterator();
        while (it.hasNext()) {
            it.next().onNewBatchCreated();
        }
    }

    private void reportNonfatalError(String str, String str2) {
        DLog.warnf(str);
        if (this.mAloysiusConfig.shouldReportBootstrapInfoAsErrors()) {
            this.mPlaybackEventReporter.reportMetric(new MetricsBuilder().eventType(QOSEventName.Error.toString()).eventSubtype(str2).errorSeverity(4).note(str));
        } else {
            this.mPlaybackEventReporter.reportMetric(new MetricsBuilder().eventType(QOSEventName.Information.toString()).eventSubtype(str2).note(str));
        }
        if (this.mAloysiusConfig.shouldReportBootstrapInfoAsDiagnostics()) {
            AloysiusDiagnosticsEvent.Builder builder = new AloysiusDiagnosticsEvent.Builder();
            builder.diagValue(str);
            builder.diagLabel(str2);
            builder.diagState(AloysiusDiagnosticsState.Discrete);
            this.mMediaEventQueue.add(builder.build());
        }
    }

    public void initializeBatchDispatchers() {
        Iterator<BatchDispatcher> it = this.mBatchListeners.iterator();
        while (it.hasNext()) {
            it.next().initialize();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3(boolean z) {
        if (z) {
            setPeriodicDispatchTrigger();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validateDBOperationsRunOnThreadPool() {
        String name = Thread.currentThread().getName();
        Preconditions2.checkStateWeakly(name.startsWith(MediaEventDispatcher.class.getSimpleName()) || name.startsWith(MediaReportDispatcherServices.class.getSimpleName()), "Aloysius DB operation occurred off the appropriate thread pools");
        cacheConfigs();
    }

    private void cacheConfigs() {
        if (this.mAreConfigsCached) {
            return;
        }
        this.mShouldDropGiantRowsInsteadOfInserting = this.mAloysiusConfig.shouldDropGiantRowsInsteadOfInserting();
        this.mShouldDropDatabaseOnGiantRow = this.mAloysiusConfig.shouldDropDatabaseOnGiantRow();
        this.mMaxEventsToSerializePerCall = this.mAloysiusConfig.getMaxEventsToSerializePerCall();
        this.mMaxRecordsAllowedInDB = this.mAloysiusConfig.getMaxRecordsAllowedInDB();
        this.mMaxRecordsToSendPerDispatch = this.mAloysiusConfig.getMaxRecordsToSendPerDispatch();
        this.mAreConfigsCached = true;
    }
}
