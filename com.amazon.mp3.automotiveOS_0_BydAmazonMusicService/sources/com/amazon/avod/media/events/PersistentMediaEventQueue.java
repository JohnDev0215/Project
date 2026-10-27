package com.amazon.avod.media.events;

import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.clientapi.EventConfig;
import com.amazon.avod.media.events.dao.MediaEventDAO;
import com.amazon.avod.media.events.dao.MediaEventRecord;
import com.amazon.avod.media.events.dao.MediaEventsDatabase;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.media.events.model.MediaEventEnvelop;
import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PersistentMediaEventQueue implements MediaEventQueue {
    private final AloysiusConfig mAloysiusConfig;
    private String mAppInstanceId;
    private boolean mAreConfigsCached;
    private final Map<MediaEvent.MediaEventType, EventConfig> mEventConfig;
    private final Executor mExecutor;
    private boolean mIsAloysiusEnabled;
    private long mMaxDBSearchSizeAtBootstrap;
    private final MediaEventDAO mMediaEventDAO;
    private long mMediaEventDAOSequenceStartID;
    private Set<MediaEventQueueListener> mMediaEventQueueListeners;
    private Set<MediaEvent.MediaEventType> mMediaEventTypeBlocklist;
    private final MediaEventsDatabase mMediaEventsDatabase;
    private final ObjectMapper mObjectMapper;
    private final AtomicLong mSessionLocalSequenceGenerator;
    private boolean mShouldDropAloysiusEventsOnInsufficientDiskSpace;

    public PersistentMediaEventQueue(@Nonnull MediaEventDAO mediaEventDAO, @Nonnull ObjectMapper objectMapper) {
        this(mediaEventDAO, objectMapper, Executors.newSingleThreadExecutor(), AloysiusConfig.getInstance(), MediaEventsDatabase.getInstance());
    }

    public PersistentMediaEventQueue(@Nonnull MediaEventDAO mediaEventDAO, @Nonnull ObjectMapper objectMapper, @Nonnull Executor executor, @Nonnull AloysiusConfig aloysiusConfig, @Nonnull MediaEventsDatabase mediaEventsDatabase) {
        this.mMediaEventQueueListeners = Sets.newHashSet(AloysiusEventHolder.INSTANCE);
        this.mAreConfigsCached = false;
        this.mMediaEventTypeBlocklist = null;
        this.mIsAloysiusEnabled = false;
        this.mShouldDropAloysiusEventsOnInsufficientDiskSpace = false;
        this.mEventConfig = new HashMap();
        this.mMediaEventDAO = (MediaEventDAO) Preconditions.checkNotNull(mediaEventDAO, "mediaEventDAO");
        this.mObjectMapper = (ObjectMapper) Preconditions.checkNotNull(objectMapper, "objectMapper");
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor, "executor");
        this.mAloysiusConfig = (AloysiusConfig) Preconditions.checkNotNull(aloysiusConfig, "aloysiusConfig");
        this.mMediaEventsDatabase = (MediaEventsDatabase) Preconditions.checkNotNull(mediaEventsDatabase, "mediaEventsDatabase");
        this.mSessionLocalSequenceGenerator = new AtomicLong(1L);
        this.mMediaEventDAOSequenceStartID = -1L;
    }

    @Override // com.amazon.avod.media.events.MediaEventQueue
    @Nonnull
    public List<MediaEventQueue.PersistentMediaEvent> bootstrap() {
        cacheConfigs();
        return toMediaEventList(this.mMediaEventDAO.getNonNullAppInstanceId());
    }

    private void cacheConfigs() {
        if (this.mAreConfigsCached) {
            return;
        }
        this.mMediaEventTypeBlocklist = this.mAloysiusConfig.getMediaEventTypeBlocklist();
        this.mIsAloysiusEnabled = this.mAloysiusConfig.isAloysiusEnabled();
        this.mMaxDBSearchSizeAtBootstrap = this.mAloysiusConfig.getMaxDBSearchSizeAtBootstrap();
        this.mShouldDropAloysiusEventsOnInsufficientDiskSpace = this.mAloysiusConfig.shouldDropAloysiusEventsOnInsufficientDiskSpace();
        this.mAreConfigsCached = true;
    }

    private boolean isMediaEventDisabled(@Nullable EventConfig eventConfig) {
        return (eventConfig == null || eventConfig.getEnabled() || !this.mAloysiusConfig.shouldEnableAloysiusEventsByBootstrap()) ? false : true;
    }

    @Override // com.amazon.avod.media.events.MediaEventQueue
    public void add(@Nonnull final MediaEvent mediaEvent) {
        Preconditions.checkNotNull(mediaEvent, "mediaEvent");
        EventConfig eventConfig = this.mEventConfig.get(mediaEvent.getMediaEventType());
        cacheConfigs();
        if (this.mMediaEventTypeBlocklist.contains(mediaEvent.getMediaEventType()) || isMediaEventDisabled(eventConfig)) {
            DLog.devf("Discarding blocked aloysius event type: %s", mediaEvent.getMediaEventType());
            return;
        }
        final long andIncrement = this.mSessionLocalSequenceGenerator.getAndIncrement();
        final long jCurrentTimeMillis = System.currentTimeMillis();
        DLog.devf("new event, type: %s, timestamp: %d", mediaEvent.getMediaEventType(), Long.valueOf(jCurrentTimeMillis));
        if (this.mIsAloysiusEnabled) {
            this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.events.PersistentMediaEventQueue$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$add$0(mediaEvent, andIncrement, jCurrentTimeMillis);
                }
            });
        } else {
            DLog.devf("aloysius is off, discarding event, type: %s", mediaEvent.getMediaEventType());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$add$0(MediaEvent mediaEvent, long j, long j2) {
        if (this.mShouldDropAloysiusEventsOnInsufficientDiskSpace && !this.mMediaEventsDatabase.hasSufficientDiskSpace()) {
            DLog.warnf("Discarding aloysius event type: %s due to insufficient disk space", mediaEvent.getMediaEventType());
            return;
        }
        if (this.mMediaEventDAOSequenceStartID < 0) {
            this.mMediaEventDAOSequenceStartID = this.mMediaEventDAO.getLatestId();
        }
        MediaEventRecord mediaEventRecordInsert = this.mMediaEventDAO.insert(toMediaEventRecord(new MediaEventEnvelop(this.mMediaEventDAOSequenceStartID + j, j2, mediaEvent)));
        DLog.devf("inserted aloysius payload: %s", mediaEventRecordInsert.getPayload());
        notifyListeners(mediaEventRecordInsert);
    }

    private void notifyListeners(final MediaEventRecord mediaEventRecord) {
        if (mediaEventRecord.getAppInstanceId() == null || this.mMediaEventQueueListeners.isEmpty()) {
            return;
        }
        for (final MediaEventQueueListener mediaEventQueueListener : this.mMediaEventQueueListeners) {
            this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.events.PersistentMediaEventQueue$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$notifyListeners$1(mediaEventQueueListener, mediaEventRecord);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyListeners$1(MediaEventQueueListener mediaEventQueueListener, MediaEventRecord mediaEventRecord) {
        mediaEventQueueListener.newEvent(toPersistentMediaEvent(mediaEventRecord));
    }

    @Override // com.amazon.avod.media.events.MediaEventQueue
    @Nonnull
    public List<MediaEventQueue.PersistentMediaEvent> peek(long j) {
        return toMediaEventList(this.mMediaEventDAO.getOldestEvents(j));
    }

    @Override // com.amazon.avod.media.events.MediaEventQueue
    public void remove(@Nonnull String str, @Nonnull List<Long> list) {
        Preconditions.checkNotNull(str, "mAppInstanceId");
        Preconditions.checkNotNull(list, "sequenceIds");
        this.mMediaEventDAO.remove(str, list);
    }

    @Override // com.amazon.avod.media.events.MediaEventQueue
    public long sizeIncludingNullAppInstanceId() {
        cacheConfigs();
        return this.mMediaEventDAO.fastGetDBSize(this.mMaxDBSearchSizeAtBootstrap, true);
    }

    @Override // com.amazon.avod.media.events.MediaEventQueue
    public long size() {
        cacheConfigs();
        return this.mMediaEventDAO.fastGetDBSize(this.mMaxDBSearchSizeAtBootstrap, false);
    }

    @Override // com.amazon.avod.media.events.MediaEventQueue
    public void registerMediaEventQueueListener(@Nonnull MediaEventQueueListener mediaEventQueueListener) {
        this.mMediaEventQueueListeners.add((MediaEventQueueListener) Preconditions.checkNotNull(mediaEventQueueListener, "mediaEventQueueListener"));
    }

    @Override // com.amazon.avod.media.events.MediaEventQueue
    public void setAppInstanceId(@Nonnull final String str) {
        this.mAppInstanceId = (String) Preconditions.checkNotNull(str, "appInstanceId");
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.events.PersistentMediaEventQueue$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setAppInstanceId$2(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setAppInstanceId$2(String str) {
        Iterator<MediaEventRecord> it = this.mMediaEventDAO.backfillAppInstanceId(str).iterator();
        while (it.hasNext()) {
            notifyListeners(it.next());
        }
    }

    @Override // com.amazon.avod.media.events.MediaEventQueue
    public void setEventConfig(@Nonnull Map<MediaEvent.MediaEventType, EventConfig> map) {
        this.mEventConfig.clear();
        this.mEventConfig.putAll((Map) Preconditions.checkNotNull(map, "eventConfig"));
    }

    @Override // com.amazon.avod.media.events.MediaEventQueue
    public String getAppInstanceId() {
        return this.mAppInstanceId;
    }

    @Override // com.amazon.avod.media.events.MediaEventQueue
    public int clear() {
        return this.mMediaEventDAO.clear();
    }

    @Nonnull
    private List<MediaEventQueue.PersistentMediaEvent> toMediaEventList(List<MediaEventRecord> list) {
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        Iterator<MediaEventRecord> it = list.iterator();
        while (it.hasNext()) {
            linkedListNewLinkedList.add(toPersistentMediaEvent(it.next()));
        }
        return linkedListNewLinkedList;
    }

    private MediaEventQueue.PersistentMediaEvent toPersistentMediaEvent(MediaEventRecord mediaEventRecord) {
        return new RecordBasedMediaEvent(mediaEventRecord.getAppInstanceId(), mediaEventRecord.getSequence(), mediaEventRecord.getTimeStamp(), MediaEvent.MediaEventType.valueOf(mediaEventRecord.getType()), mediaEventRecord.getPayload());
    }

    private MediaEventRecord toMediaEventRecord(MediaEventEnvelop mediaEventEnvelop) {
        try {
            return new MediaEventRecord(mediaEventEnvelop.getSequence(), this.mAppInstanceId, mediaEventEnvelop.getTimestamp(), mediaEventEnvelop.getType().name(), this.mObjectMapper.writeValueAsString(mediaEventEnvelop));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("error while converting event to json", e);
        }
    }

    private static class RecordBasedMediaEvent implements MediaEventQueue.PersistentMediaEvent {
        private final String mAppInstanceId;
        private final String mPayload;
        private final long mSequence;
        private final long mTimestamp;
        private final MediaEvent.MediaEventType mType;

        private RecordBasedMediaEvent(@Nonnull String str, long j, long j2, @Nonnull MediaEvent.MediaEventType mediaEventType, @Nonnull String str2) {
            this.mAppInstanceId = (String) Preconditions.checkNotNull(str, "appInstanceId");
            this.mSequence = j;
            this.mTimestamp = j2;
            this.mType = (MediaEvent.MediaEventType) Preconditions.checkNotNull(mediaEventType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            this.mPayload = (String) Preconditions.checkNotNull(str2, "payload");
        }

        @Override // com.amazon.avod.media.events.MediaEventQueue.PersistentMediaEvent
        public long getSequence() {
            return this.mSequence;
        }

        @Override // com.amazon.avod.media.events.MediaEventQueue.PersistentMediaEvent
        @Nonnull
        public MediaEvent.MediaEventType getEventType() {
            return this.mType;
        }

        @Override // com.amazon.avod.media.events.MediaEventQueue.PersistentMediaEvent
        @Nonnull
        public String getAppInstanceId() {
            return this.mAppInstanceId;
        }

        @Override // com.amazon.avod.media.events.MediaEventQueue.PersistentMediaEvent
        @Nonnull
        public String getPayload() {
            return this.mPayload;
        }
    }
}
