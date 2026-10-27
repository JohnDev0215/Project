package com.amazon.mp3.playback.harley;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.model.MetricsContext;
import com.amazon.mp3.dagger.DaggerSubcomponents;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.nowplaying.LibraryMediaItem;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueHelper;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueTables;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.provider.source.nowplaying.PlayQueue;
import com.amazon.mp3.library.provider.source.nowplaying.PlayQueueHelper;
import com.amazon.mp3.likes.MediaItemLikesProviderImpl;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.service.metrics.mts.SelectionSourceHelper;
import com.amazon.mp3.starlight.provider.HybridQueueStateProvider;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.view.stage.StageAutomatedSequencingHandler;
import com.amazon.mp3.view.stage.overlay.OverlayLyricsStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayTypeStateProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.sequencer.IndexedSequencerBase;
import com.amazon.music.media.playback.sequencer.NoOpSequencer;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.sequencer.impl.AsyncSequencer;
import com.amazon.music.media.playback.sequencer.impl.CursorSequencerDataRequestHandler;
import com.amazon.music.media.playback.sequencer.impl.PlaceholderSequencer;
import com.amazon.music.media.playback.sequencer.impl.SequencerDataRequestHandler;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackInitiationInfo;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes3.dex */
public class PlayQueueSequencer extends AsyncSequencer {
    private static PlayQueueSequencer sInstance;
    private final String LEGACY_QUEUE_EVENT_NAME;
    private final ExecutorService backgroundExecutor;
    private MediaItem last;
    private boolean mAddingItems;
    private final Context mContext;
    private final DataRequestHandler mDataRequestHandler;
    protected HybridQueueStateProvider mHybridQueueStateProvider;
    private int mIndexOffset;
    private final Object mLock;
    private final PlayQueueHelper mNowPlayingQueueHelper;
    private int mNumAdding;
    protected OverlayLyricsStateProvider mOverlayLyricsStateProvider;
    protected OverlayTypeStateProvider mOverlayTypeStateProvider;
    private boolean mQueueReady;
    private boolean mReady;
    private String mSelection;
    private String[] mSelectionArgs;
    private boolean mSetupCalled;
    private MediaItem mSetupItem;
    private boolean mSetupPending;
    private String mSortOrder;
    protected StageAutomatedSequencingHandler mStageAutomatedSequencingHandler;
    private Uri mUri;
    private final ExecutorService setupExecutor;
    private static final Logger logger = LoggerFactory.getLogger("PlayQueueSequencer");
    private static final long INIT_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(10);

    public static synchronized PlayQueueSequencer getInstance() {
        if (sInstance == null) {
            sInstance = new PlayQueueSequencer(Playback.getInstance().getPlaybackConfig().getContext());
        }
        return sInstance;
    }

    private PlayQueueSequencer(final Context context) {
        super(false);
        this.LEGACY_QUEUE_EVENT_NAME = "legacyQueue";
        this.mLock = new Object();
        this.backgroundExecutor = ThreadUtils.createBackgroundExecutor(PlayQueueSequencer.class.getSimpleName() + "-background");
        this.setupExecutor = ThreadUtils.createBackgroundExecutor(PlayQueueSequencer.class.getSimpleName() + "-setup");
        this.mContext = context;
        DataRequestHandler dataRequestHandler = new DataRequestHandler(this, context);
        this.mDataRequestHandler = dataRequestHandler;
        this.mNowPlayingQueueHelper = new NowPlayingQueueHelper(context);
        setSequencerDataRequestHandler(dataRequestHandler);
        this.mQueueReady = true;
        DaggerSubcomponents.INSTANCE.getNowPlayingComponent().inject(this);
    }

    public void start(final ControlSource controlSource, final Uri uri, @Nullable final String selection, @Nullable final String[] selectionArgs, @Nullable final String sortOrder, final int index, final boolean shuffled, final PlaybackMetricInformation metricInformation, final long startTime) {
        setAsCurrentSequencer(controlSource, uri, selection, selectionArgs, sortOrder, index, shuffled, true, metricInformation, null, null, startTime);
    }

    public void start(final ControlSource controlSource, final Uri uri, @Nullable final String selection, @Nullable final String[] selectionArgs, @Nullable final String sortOrder, final int index, final boolean shuffled, final PlaybackMetricInformation metricInformation, @Nullable final MusicTrack track, final long startTime) {
        setAsCurrentSequencer(controlSource, uri, selection, selectionArgs, sortOrder, index, shuffled, true, metricInformation, null, track, startTime);
    }

    private void onSettingUp(final MediaItem mediaItem) {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlayQueueSequencer$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onSettingUp$0(mediaItem);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSettingUp$0(MediaItem mediaItem) {
        this.mReady = false;
        this.mSetupItem = mediaItem;
        this.mSetupCalled = true;
        this.mSetupPending = false;
        if (mediaItem != null) {
            this.mDataRequestHandler.mDirectedPlay = DirectedPlayStatus.NOT_DIRECTED;
        }
        notifySequencerInfoChanged();
    }

    @WorkerThread
    public void setup(final Uri uri, @Nullable final String selection, @Nullable final String[] selectionArgs, @Nullable final String sortOrder, final int index, final boolean shuffled, final PlaybackMetricInformation metricInformation, @Nullable final MusicTrack track, final boolean replaceQueue) {
        logger.debug("setup(" + uri + ", " + selection + ", ..., " + sortOrder + ",  " + index + ", " + shuffled + ", " + metricInformation + ", " + track + ")");
        if (index >= 1000000) {
            throw new OutOfMemoryError("setup(" + uri + ", " + index + ", " + shuffled + ")");
        }
        StrictMode.validateNotNull(metricInformation.getPageType(), "PlaybackPageType should not be null - use PlaybackPageType.UNKNOWN if necessary.");
        final boolean z = !shuffled && this.mNowPlayingQueueHelper.isUnlimitedCollection(uri);
        final Object obj = new Object();
        synchronized (obj) {
            if (replaceQueue) {
                this.mQueueReady = false;
                this.setupExecutor.execute(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlayQueueSequencer$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$setup$1(uri, selection, selectionArgs, sortOrder, z, index, metricInformation, shuffled, replaceQueue, track, obj);
                    }
                });
                try {
                    obj.wait();
                } catch (InterruptedException unused) {
                }
            } else {
                this.setupExecutor.execute(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlayQueueSequencer$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$setup$1(uri, selection, selectionArgs, sortOrder, z, index, metricInformation, shuffled, replaceQueue, track, obj);
                    }
                });
                obj.wait();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00ed A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00e1 A[Catch: all -> 0x0190, PHI: r6
  0x00e1: PHI (r6v1 int) = (r6v0 int), (r6v3 int), (r6v4 int), (r6v4 int) binds: [B:34:0x0090, B:54:0x00da, B:45:0x00bb, B:50:0x00d6] A[DONT_GENERATE, DONT_INLINE], TryCatch #6 {all -> 0x0190, blocks: (B:36:0x0094, B:42:0x00a8, B:44:0x00b9, B:46:0x00bd, B:47:0x00d1, B:53:0x00d9, B:43:0x00ab, B:54:0x00da, B:55:0x00e1, B:56:0x00ec, B:63:0x0113, B:64:0x0122, B:82:0x017a, B:83:0x018c, B:70:0x0130, B:86:0x018f, B:32:0x008b, B:59:0x00f1, B:61:0x0104, B:62:0x010c, B:69:0x0129, B:57:0x00ed, B:58:0x00f0), top: B:126:0x008b, outer: #3, inners: #1, #4, #12 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ void lambda$setup$1(android.net.Uri r16, java.lang.String r17, java.lang.String[] r18, java.lang.String r19, boolean r20, int r21, com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation r22, boolean r23, boolean r24, com.amazon.mp3.library.item.MusicTrack r25, java.lang.Object r26) {
        /*
            Method dump skipped, instruction units count: 441
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.harley.PlayQueueSequencer.lambda$setup$1(android.net.Uri, java.lang.String, java.lang.String[], java.lang.String, boolean, int, com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation, boolean, boolean, com.amazon.mp3.library.item.MusicTrack, java.lang.Object):void");
    }

    public void setAsCurrentSequencer(final ControlSource controlSource, final Uri uri, @Nullable final String selection, @Nullable final String[] selectionArgs, @Nullable final String sortOrder, final int index, final boolean shuffled, final boolean startPlayback, final PlaybackMetricInformation metricInformation, final MediaCollectionId mediaCollectionId, @Nullable final MusicTrack track, final long startTime) {
        Logger logger2 = logger;
        logger2.debug("setAsCurrentSequencer(" + uri + ", " + selection + ", ..., " + sortOrder + ",  " + index + ", " + shuffled + ", " + startPlayback + ", " + metricInformation + ", " + startTime + ", " + track + ")\n");
        if (index >= 1000000) {
            throw new OutOfMemoryError("setup(" + uri + ", " + index + ", " + shuffled + ")");
        }
        StrictMode.validateNotNull(metricInformation.getPageType(), "PlaybackPageType should not be null - use PlaybackPageType.UNKNOWN if necessary.");
        final SequencerPlaybackProvider sequencerPlaybackProvider = (SequencerPlaybackProvider) Playback.getInstance().getPlaybackProvider();
        final PlaceholderSequencer placeholderSequencer = new PlaceholderSequencer();
        this.mDataRequestHandler.isCurrentPlaybackVoiceInitiated = ControlSource.ALEXA.equals(controlSource);
        if (mediaCollectionId != null) {
            this.mDataRequestHandler.mediaCollectionIds.add(mediaCollectionId);
        }
        this.mDataRequestHandler.mediaCollectionIds.add(getPlaybackSessionId());
        if (startPlayback) {
            sequencerPlaybackProvider.startSequencer(placeholderSequencer, controlSource, startTime);
        } else {
            sequencerPlaybackProvider.setSequencer(placeholderSequencer, controlSource);
        }
        logger2.debug("after set/start sequencer");
        this.mHybridQueueStateProvider.reset();
        this.mOverlayTypeStateProvider.reset(null);
        this.mOverlayLyricsStateProvider.reset();
        this.mStageAutomatedSequencingHandler.resetAutomatedSequenceCurrentOverlay();
        this.backgroundExecutor.execute(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlayQueueSequencer.1
            @Override // java.lang.Runnable
            public void run() {
                PlayQueueSequencer.logger.debug("before setup");
                try {
                    PlayQueueSequencer.this.setup(uri, selection, selectionArgs, sortOrder, index, shuffled, metricInformation, track, true);
                    PlayQueueSequencer.logger.debug("after setup");
                    e = null;
                } catch (Exception e) {
                    e = e;
                    PlayQueueSequencer.logger.error("Exception in setup()", (Throwable) e);
                }
                final PlaybackException playbackException = e != null ? new PlaybackException(new PlaybackException.Config(PlaybackException.Recoverability.PLAYER).source(e)) : null;
                ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlayQueueSequencer.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Sequencer sequencer = sequencerPlaybackProvider.getSequencer();
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        if (sequencer != placeholderSequencer) {
                            PlayQueueSequencer.logger.debug("Current Sequencer is not the placeholder: " + sequencerPlaybackProvider.getSequencer());
                            return;
                        }
                        boolean zWillPlay = PlaybackControllerProvider.getController(controlSource).getPlayStatus().willPlay();
                        PlayQueueSequencer.logger.debug("correct placeholderSequencer - willPlay: " + zWillPlay);
                        PlaybackException playbackException2 = playbackException;
                        if (playbackException2 != null) {
                            if (zWillPlay) {
                                placeholderSequencer.onFailedToLoad(playbackException2);
                            }
                            sequencerPlaybackProvider.setSequencer(new NoOpSequencer(), controlSource);
                        } else if (zWillPlay) {
                            AnonymousClass1 anonymousClass12 = AnonymousClass1.this;
                            sequencerPlaybackProvider.startSequencer(PlayQueueSequencer.this, controlSource, startTime);
                        } else {
                            AnonymousClass1 anonymousClass13 = AnonymousClass1.this;
                            sequencerPlaybackProvider.setSequencer(PlayQueueSequencer.this, controlSource);
                        }
                    }
                });
            }
        });
    }

    @Override // com.amazon.music.media.playback.sequencer.impl.AsyncSequencer
    @CallSuper
    protected void prepareActiveStateRequest() {
        this.mSetupPending = this.mSetupCalled;
        super.prepareActiveStateRequest();
    }

    @Override // com.amazon.music.media.playback.sequencer.impl.AsyncSequencer
    @CallSuper
    @MainThread
    protected IndexedSequencerBase.SequencerInfo processResult() {
        IndexedSequencerBase.SequencerInfo sequencerInfoProcessResult = super.processResult();
        if (this.mSetupPending && !hasMoreToProcess()) {
            this.mReady = true;
        }
        return sequencerInfoProcessResult;
    }

    @Override // com.amazon.music.media.playback.sequencer.impl.AsyncSequencer, com.amazon.music.media.playback.sequencer.IndexedSequencerBase, com.amazon.music.media.playback.sequencer.Sequencer
    public MediaItem getCurrentMediaItem() {
        MediaItem currentMediaItem;
        if (this.mReady || (currentMediaItem = this.mSetupItem) == null) {
            currentMediaItem = super.getCurrentMediaItem();
        }
        if (currentMediaItem != null && currentMediaItem != this.last) {
            this.last = currentMediaItem;
        }
        if (currentMediaItem != null) {
            currentMediaItem.setLikesProvider(new MediaItemLikesProviderImpl(this.mContext));
        }
        return currentMediaItem;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public String getSelection() {
        return this.mSelection;
    }

    public String[] getSelectionArgs() {
        return this.mSelectionArgs;
    }

    public String getSortOrder() {
        return this.mSortOrder;
    }

    public int getActualIndex() {
        return getCurrentIndex() + this.mIndexOffset;
    }

    @WorkerThread
    public void move(final int fromIndex, final int toIndex) {
        synchronized (this.mLock) {
            logger.debug("move(" + fromIndex + ", " + toIndex + ")");
            boolean zIsShuffled = isShuffled();
            this.mDataRequestHandler.suspendToMove(fromIndex, toIndex, zIsShuffled);
            try {
                PlayQueue.getInstance().shift(fromIndex, toIndex, zIsShuffled);
            } catch (Exception unused) {
                logger.error("Error moving track from " + fromIndex + " to " + toIndex);
            }
            logger.info("move(), calling on CursorSequencerDataRequestHandler.invalidate -> shutdown(false)");
            this.mDataRequestHandler.invalidate();
            resume();
        }
        recordStatusChangedMetrics(this.mDataRequestHandler.mPlaybackPageType);
    }

    public void replace(final Uri uri, final int unshuffledIndex) {
        synchronized (this.mLock) {
            if (uri != null) {
                Cursor cursorFromUri = NowPlayingQueueUtil.getInstance().getCursorFromUri(uri, null, null, null);
                if (cursorFromUri != null) {
                    try {
                        if (cursorFromUri.getCount() != 0 && cursorFromUri.moveToFirst()) {
                            try {
                                MediaItem mediaItemCreateMediaItem = this.mDataRequestHandler.createMediaItem(AmazonApplication.getLibraryItemFactory().getTrack(cursorFromUri), uri, new PlaybackInitiationInfo(this.mDataRequestHandler.mDirectedPlay, isShuffled(), this.mDataRequestHandler.mPlaybackPageType));
                                this.mDataRequestHandler.suspendToReplaceItem(unshuffledIndex, mediaItemCreateMediaItem);
                                try {
                                    PlayQueue.getInstance().replaceTrack(cursorFromUri, uri, unshuffledIndex);
                                } catch (Exception e) {
                                    logger.error("Error when trying to replace a track in the now playing db: " + e);
                                }
                                this.mDataRequestHandler.invalidate();
                                if ((isShuffled() ? getShuffledIndex(unshuffledIndex) : unshuffledIndex) == getCurrentIndex()) {
                                    invalidate(unshuffledIndex, isShuffled() ? getShuffledIndex(unshuffledIndex) : -1, isShuffled(), mediaItemCreateMediaItem);
                                }
                                resume();
                            } catch (Exception e2) {
                                logger.error("Error when trying to replace a track in the queue: " + e2);
                            }
                        }
                    } finally {
                    }
                }
                if (cursorFromUri != null) {
                    cursorFromUri.close();
                }
            } else {
                logger.error("Cannot replace track with null uri");
            }
        }
        recordStatusChangedMetrics(this.mDataRequestHandler.mPlaybackPageType);
    }

    @WorkerThread
    public void removeFromPlayQueue(final int index) {
        int columnFromUdoIndex;
        int columnFromShuffleIndex;
        synchronized (this.mLock) {
            logger.debug("removeFromPlayQueue(" + index + ")");
            boolean zIsShuffled = isShuffled();
            if (zIsShuffled) {
                columnFromShuffleIndex = PlayQueue.getInstance().getColumnFromShuffleIndex("udo", index);
                columnFromUdoIndex = index;
            } else {
                columnFromUdoIndex = PlayQueue.getInstance().getColumnFromUdoIndex("shuffle", index);
                columnFromShuffleIndex = index;
            }
            this.mDataRequestHandler.suspendToRemove(columnFromUdoIndex, columnFromShuffleIndex, 1);
            try {
                PlayQueue.getInstance().remove(index, zIsShuffled);
            } catch (Exception unused) {
                logger.error("Error removing track " + index);
            }
            logger.info("removeFromPlayQueue, calling on CursorSequencerDataRequestHandler.invalidate -> shutdown(false)");
            this.mDataRequestHandler.invalidate();
            resume();
        }
        recordStatusChangedMetrics(this.mDataRequestHandler.mPlaybackPageType);
    }

    private void incrementNumAdding() {
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlayQueueSequencer$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$incrementNumAdding$2();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$incrementNumAdding$2() {
        int i = this.mNumAdding;
        this.mNumAdding = i + 1;
        if (i != 0 || this.mAddingItems) {
            return;
        }
        notifySequencerInfoChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decrementNumAdding() {
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlayQueueSequencer$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$decrementNumAdding$3();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$decrementNumAdding$3() {
        int i = this.mNumAdding - 1;
        this.mNumAdding = i;
        if (i != 0 || this.mAddingItems) {
            return;
        }
        notifySequencerInfoChanged();
    }

    public void setAddingItems(final boolean addingItems) {
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlayQueueSequencer$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setAddingItems$4(addingItems);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setAddingItems$4(boolean z) {
        if (this.mAddingItems != z) {
            this.mAddingItems = z;
            if (this.mNumAdding == 0) {
                notifySequencerInfoChanged();
            }
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.IndexedSequencerBase, com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public int getNumMediaItems() {
        if (this.mAddingItems || this.mNumAdding > 0 || !this.mReady) {
            return -1;
        }
        return super.getNumMediaItems();
    }

    @WorkerThread
    public void clear() {
        String playbackTypeTag = getPlaybackTypeTag(this.mDataRequestHandler.mPlaybackPageType);
        synchronized (this.mLock) {
            this.mHybridQueueStateProvider.reset();
            this.mOverlayTypeStateProvider.reset(null);
            this.mOverlayLyricsStateProvider.reset();
            this.mStageAutomatedSequencingHandler.resetAutomatedSequenceCurrentOverlay();
            logger.debug("clear()");
            this.mDataRequestHandler.suspendToReplace(0, isShuffled(), null);
            try {
                PlayQueue.getInstance().clear();
            } catch (Exception unused) {
                logger.error("Error clearing");
            }
            logger.info("clear(), calling on CursorSequencerDataRequestHandler.invalidate -> shutdown(false)");
            this.mDataRequestHandler.invalidate();
            resume();
        }
        if (playbackTypeTag != null) {
            MetricsLogger.sendEvent(FlexEvent.builder("legacyQueue").withFlexStr1(playbackTypeTag).withFlexStr2("cleared").build());
        }
    }

    public void addToPlayQueue(final Uri uri, final String selection, final String[] selectionArgs, final boolean playNext, final boolean showToast) {
        incrementNumAdding();
        this.backgroundExecutor.execute(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlayQueueSequencer.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (PlayQueueSequencer.this.mLock) {
                    PlayQueueSequencer.logger.debug("addToPlayQueue(" + uri + ")");
                    Cursor cursorFromUri = NowPlayingQueueUtil.getInstance().getCursorFromUri(uri, selection, selectionArgs);
                    try {
                        int count = cursorFromUri.getCount();
                        if (count == 0) {
                            PlayQueueSequencer.logger.error("No tracks to add for uri: " + uri + ", selection: " + selection + ", selectionArgs: " + Arrays.toString(selectionArgs));
                            PlayQueueSequencer.this.decrementNumAdding();
                            cursorFromUri.close();
                            return;
                        }
                        PlayQueueSequencer.logger.debug("adding " + count + " tracks");
                        Cursor cursorCreateCursor = PlayQueueSequencer.this.mDataRequestHandler.createCursor(false);
                        try {
                            int iMin = playNext ? Math.min(PlayQueueSequencer.this.getCurrentIndex() + 1, cursorCreateCursor.getCount()) : -1;
                            cursorCreateCursor.close();
                            PlayQueueSequencer.this.mDataRequestHandler.suspendToInsert(iMin, iMin, count, false);
                            try {
                                if (playNext) {
                                    PlayQueue.getInstance().insert(cursorFromUri, uri, iMin);
                                } else {
                                    PlayQueue.getInstance().insertTail(cursorFromUri, uri);
                                }
                                PlayQueueSequencer.logger.info("addToPlayQueue, calling on CursorSequencerDataRequestHandler.invalidate -> shutdown(false)");
                                PlayQueueSequencer.this.mDataRequestHandler.invalidate();
                            } catch (Exception e) {
                                PlayQueueSequencer.logger.error("Error inserting tracks for uri: " + uri + ", selection: " + selection + ", selectionArgs: " + Arrays.toString(selectionArgs), (Throwable) e);
                                if (PlayQueueSequencer.this.isShuffled()) {
                                    PlayQueueSequencer playQueueSequencer = PlayQueueSequencer.this;
                                    playQueueSequencer.invalidate(-1, playQueueSequencer.getCurrentIndex(), PlayQueueSequencer.this.isShuffled(), PlayQueueSequencer.this.getCurrentMediaItem());
                                } else {
                                    PlayQueueSequencer playQueueSequencer2 = PlayQueueSequencer.this;
                                    playQueueSequencer2.invalidate(playQueueSequencer2.getCurrentIndex(), -1, PlayQueueSequencer.this.isShuffled(), PlayQueueSequencer.this.getCurrentMediaItem());
                                }
                            }
                            PlayQueueSequencer.this.resume();
                            cursorFromUri.close();
                            PlayQueueSequencer.this.decrementNumAdding();
                            if (showToast) {
                                ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.mp3.playback.harley.PlayQueueSequencer.2.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                                        BauhausToastUtils.showDefaultToast(PlayQueueSequencer.this.mDataRequestHandler.mContext, playNext ? R.string.dmusic_now_playing_queue_toast_play_next_text : R.string.dmusic_now_playing_queue_toast_add_to_play_queue_text, 0);
                                    }
                                });
                                return;
                            }
                            return;
                        } finally {
                        }
                    } finally {
                    }
                }
            }
        });
        recordStatusChangedMetrics(this.mDataRequestHandler.mPlaybackPageType);
    }

    public List<String> getSfaTracksFromQueue() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        Cursor playQueueCursor = this.mDataRequestHandler.getPlayQueueCursor(false);
        try {
            if (playQueueCursor.moveToFirst()) {
                do {
                    arrayList.add(playQueueCursor.getString(playQueueCursor.getColumnIndex("asin")));
                    i++;
                    if (i >= 100) {
                        break;
                    }
                } while (playQueueCursor.moveToNext());
            }
            return arrayList;
        } finally {
            playQueueCursor.close();
        }
    }

    public boolean isQueueReady() {
        return this.mQueueReady;
    }

    @Override // com.amazon.music.media.playback.sequencer.IndexedSequencerBase, com.amazon.music.media.playback.sequencer.AbstractSequencer
    public int indexOf(final MediaItemId mediaItemId) {
        if (mediaItemId.getType() != MediaItemId.Type.ASIN) {
            return -1;
        }
        String id = mediaItemId.getId();
        Cursor cursorRawQuery = CirrusDatabase.getReadOnlyDatabase(this.mContext).rawQuery(NowPlayingQueueTables.PLAYQUEUE_QUERY + " WHERE asin = '" + id + "'", null);
        if (cursorRawQuery == null) {
            logger.error("indexOf - cursor is null");
            return -1;
        }
        try {
            if (cursorRawQuery.getCount() == 0) {
                return -1;
            }
            cursorRawQuery.moveToFirst();
            return cursorRawQuery.getInt(cursorRawQuery.getColumnIndex(isShuffled() ? "shuffle" : "udo"));
        } finally {
            cursorRawQuery.close();
        }
    }

    private static class DataRequestHandler extends CursorSequencerDataRequestHandler {
        private boolean isCurrentPlaybackVoiceInitiated;
        private final Context mContext;
        private DirectedPlayStatus mDirectedPlay;
        private PlaybackPageType mPlaybackPageType;
        private boolean mPreventReshuffle;
        private int mShuffleColumn;
        private int mUdoColumn;
        public List<MediaCollectionId> mediaCollectionIds;

        @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
        public MediaCollectionInfo loadMediaCollectionInfo() throws PlaybackException {
            return null;
        }

        public DataRequestHandler(final AsyncSequencer sequencer, final Context context) {
            super(sequencer, false);
            this.mediaCollectionIds = new ArrayList();
            this.mContext = context.getApplicationContext();
        }

        @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
        public MediaItem createMediaItem() throws PlaybackException {
            DirectedPlayStatus directedPlayStatus;
            int position = getCursor().getPosition();
            SequencerDataRequestHandler.SequenceData sequenceData = this.sequenceData;
            boolean z = sequenceData.shuffled;
            if (position == (z ? sequenceData.shuffledIndex : sequenceData.unshuffledIndex)) {
                directedPlayStatus = this.mDirectedPlay;
                this.mDirectedPlay = DirectedPlayStatus.NOT_DIRECTED;
            } else {
                directedPlayStatus = DirectedPlayStatus.NOT_DIRECTED;
            }
            PlaybackInitiationInfo playbackInitiationInfo = new PlaybackInitiationInfo(directedPlayStatus, z, this.mPlaybackPageType);
            MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(getCursor());
            return createMediaItem(track, track.getCollectionUri(), playbackInitiationInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MediaItem createMediaItem(final MusicTrack track, final Uri collectionUri, final PlaybackInitiationInfo playbackInitiationInfo) throws PlaybackException {
            if (collectionUri != null) {
                track.setCollectionUri(collectionUri);
            }
            if (track == null) {
                this.logger.error("createMediaItem() => null");
                PlaybackException.Config config = new PlaybackException.Config(PlaybackException.Recoverability.COLLECTION);
                config.autoRetry(false);
                throw new PlaybackException(config);
            }
            MediaCollectionInfo mediaCollectionInfo = MediaCollectionHelper.getMediaCollectionInfo(track.getCollectionUri());
            Iterator<MediaCollectionId> it = this.mediaCollectionIds.iterator();
            while (it.hasNext()) {
                mediaCollectionInfo.addId(it.next());
            }
            LibraryMediaItem libraryMediaItem = new LibraryMediaItem(track, playbackInitiationInfo, mediaCollectionInfo);
            libraryMediaItem.setMetricsContext(getMetricsContext(track, collectionUri, playbackInitiationInfo).toMap());
            libraryMediaItem.setVoiceInitiated(this.isCurrentPlaybackVoiceInitiated);
            libraryMediaItem.setLikesProvider(new MediaItemLikesProviderImpl(this.mContext));
            libraryMediaItem.fetchAndInitializeLikeState();
            libraryMediaItem.setCanLike(true);
            this.logger.debug("createMediaItem() => " + libraryMediaItem);
            return libraryMediaItem;
        }

        private MetricsContext getMetricsContext(final MusicTrack track, final Uri collectionUri, final PlaybackInitiationInfo playbackInitiationInfo) {
            return new MetricsContext(null, SelectionSourceHelper.getSelectionSourceType(collectionUri), playbackInitiationInfo.getPlaybackPageType(), Boolean.valueOf(Boolean.TRUE.equals(new EligibilityUtil().getOnDemand(track))));
        }

        @Override // com.amazon.music.media.playback.sequencer.impl.CursorSequencerDataRequestHandler
        public Cursor createCursor(final boolean shuffled) {
            this.logger.info("createCursor(" + shuffled + ")");
            if (shuffled && !this.mPreventReshuffle) {
                this.mPreventReshuffle = true;
                try {
                    int i = this.sequenceData.unshuffledIndex;
                    if (i >= 0) {
                        PlayQueue.getInstance().shuffle(this.sequenceData.unshuffledIndex);
                    } else if (i == -2) {
                        PlayQueue.getInstance().shuffle(new Random().nextInt(PlayQueue.getInstance().size()));
                    }
                } catch (Exception e) {
                    this.logger.error("Exception while shuffling", (Throwable) e);
                }
            }
            return getPlayQueueCursor(shuffled);
        }

        public Cursor getPlayQueueCursor(final boolean shuffled) {
            SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(this.mContext);
            StringBuilder sb = new StringBuilder();
            sb.append(NowPlayingQueueTables.PLAYQUEUE_QUERY);
            sb.append(shuffled ? " ORDER BY shuffle" : " ORDER BY udo");
            Cursor cursorRawQuery = readOnlyDatabase.rawQuery(sb.toString(), null);
            this.mUdoColumn = cursorRawQuery.getColumnIndex("udo");
            this.mShuffleColumn = cursorRawQuery.getColumnIndex("shuffle");
            return cursorRawQuery;
        }

        @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
        public int getShuffledIndex() {
            Cursor cursor = getCursor();
            if (cursor == null) {
                return -1;
            }
            return cursor.getInt(this.mShuffleColumn);
        }

        @Override // com.amazon.music.media.playback.sequencer.impl.CursorSequencerDataRequestHandler, com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
        public int getUnshuffledIndex() {
            return getCursor().getInt(this.mUdoColumn);
        }

        @Override // com.amazon.music.media.playback.sequencer.impl.CursorSequencerDataRequestHandler, com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
        public void moveToUnshuffledIndex(final int unshuffledIndex) {
            if (!this.sequenceData.shuffled) {
                getCursor().moveToPosition(unshuffledIndex);
            } else {
                moveToShuffledIndex(indexOf(unshuffledIndex, "udo", "shuffle"));
            }
        }

        @Override // com.amazon.music.media.playback.sequencer.impl.CursorSequencerDataRequestHandler, com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
        public void moveToShuffledIndex(final int shuffledIndex) {
            if (this.sequenceData.shuffled) {
                getCursor().moveToPosition(shuffledIndex);
            } else {
                moveToUnshuffledIndex(indexOf(shuffledIndex, "shuffle", "udo"));
            }
        }

        private int indexOf(final int index, final String searchColumn, final String resultColumn) {
            Cursor cursorRawQuery = CirrusDatabase.getReadOnlyDatabase(this.mContext).rawQuery(NowPlayingQueueTables.PLAYQUEUE_QUERY + " WHERE " + searchColumn + " = " + index, null);
            if (cursorRawQuery == null) {
                return -1;
            }
            try {
                if (cursorRawQuery.getCount() == 0) {
                    return -1;
                }
                cursorRawQuery.moveToFirst();
                return cursorRawQuery.getInt(cursorRawQuery.getColumnIndex(resultColumn));
            } finally {
                cursorRawQuery.close();
            }
        }

        @Override // com.amazon.music.media.playback.sequencer.impl.AbstractSequencerDataRequestHandler
        public void onShuffledChanged(final boolean shuffled) {
            this.logger.error("onShuffledChanged(" + shuffled + ")");
            if (!shuffled) {
                this.mPreventReshuffle = false;
            }
            invalidate();
        }
    }

    public ExecutorService getBackgroundExecutor() {
        return this.backgroundExecutor;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.harley.PlayQueueSequencer$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType;

        static {
            int[] iArr = new int[PlaybackPageType.values().length];
            $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType = iArr;
            try {
                iArr[PlaybackPageType.ALBUM_DETAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType[PlaybackPageType.ALBUMS_LIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType[PlaybackPageType.PRIME_BROWSE_ALBUMS_LIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType[PlaybackPageType.PRIME_BROWSE_SEARCH_ALBUMS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType[PlaybackPageType.ARTIST_DETAIL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType[PlaybackPageType.ARTISTS_LIST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType[PlaybackPageType.PRIME_BROWSE_ARTISTS_LIST.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType[PlaybackPageType.PRIME_BROWSE_SEARCH_ARTISTS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType[PlaybackPageType.PLAYLIST_DETAIL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType[PlaybackPageType.PLAYLISTS_LIST.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType[PlaybackPageType.PRIME_BROWSE_PLAYLISTS_LIST.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType[PlaybackPageType.PRIME_BROWSE_SEARCH_PLAYLISTS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType[PlaybackPageType.PRIME_PLAYLIST_DETAIL.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    private String getPlaybackTypeTag(final PlaybackPageType pageType) {
        if (pageType == null) {
            return null;
        }
        switch (AnonymousClass3.$SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackPageType[pageType.ordinal()]) {
        }
        return null;
    }

    private void recordStatusChangedMetrics(final PlaybackPageType pageType) {
        String playbackTypeTag = getPlaybackTypeTag(pageType);
        if (playbackTypeTag != null) {
            MetricsLogger.sendEvent(FlexEvent.builder("legacyQueue").withFlexStr1(playbackTypeTag).withFlexStr2("changed").build());
        }
    }
}
