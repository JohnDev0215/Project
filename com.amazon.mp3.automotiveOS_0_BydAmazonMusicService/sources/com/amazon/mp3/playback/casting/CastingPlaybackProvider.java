package com.amazon.mp3.playback.casting;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.media.VolumeProviderCompat;
import com.amazon.digitalmusicxp.clients.CloudQueueAppClient;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.digitalmusicxp.inputs.QueueSeedContextInput;
import com.amazon.digitalmusicxp.models.Queue;
import com.amazon.digitalmusicxp.types.QueueSeed;
import com.amazon.digitalmusicxp.types.StartAt;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.basequeue.NoOpCloudQueueAppClient;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.cloudqueue.CloudQueueDigitalXPUtil;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.metrics.CloudQueueFlexMetricsManager;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.casting.CastingStateManager;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.casting.session.CastingQueueCallback;
import com.amazon.music.casting.session.CastingQueueResult;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.casting.session.things.NowPlayingThingShadow;
import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.PlayerCapabilities;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.config.PlaybackPersistenceProvider;
import com.amazon.music.media.playback.config.SequencerPersistenceProvider;
import com.amazon.music.media.playback.impl.AbstractPlaybackProvider;
import com.amazon.music.media.playback.sequencer.NoOpSequencer;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.sequencer.SequencerListener;
import com.amazon.music.media.playback.sequencer.impl.PlaceholderSequencer;
import com.amazon.music.media.playback.sequencer.impl.StationSequencer;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.platform.playback.data.PlayQueueInteractionPattern;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class CastingPlaybackProvider extends AbstractPlaybackProvider implements SequencerPlaybackProvider {
    private static final long REPORT_REPEAT_MAX_FROM_BEGINNING_MILLIS;
    private static final long REPORT_REPEAT_MAX_FROM_END_MILLIS;
    private static final long REPORT_REPEAT_MIN_DURATION_MILLIS;
    private static final String TAG = "CastingPlaybackProvider";
    private final CastingSessionManager mCastingManager;
    private final CastingQueueCallback mCastingQueueCallback;
    private final ExecutorService mExecutor;
    private MediaItem mPreviousMediaItem;
    private long mPreviousPosition;
    private final CastingQueueHandler mQueueHandler;
    private Sequencer mSavedSequencer;
    private boolean mSendPlayMode;
    private SequencerListener mSequencerListener;
    private long mStartPositionMillis;
    private final CastingStateManager.Callback mStateCallback;
    private final CastingStateManager mStateManager;
    private SequencerPlaybackProvider.MediaItemVerifier mVerifier;
    private final SequencerPersistenceProvider persistenceProvider;

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void autoResolveNextConcurrencyCheck() {
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canFastForward() {
        return false;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canPlay() {
        return true;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canRewind() {
        return false;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canSkipToIndex() {
        return false;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void fastForward(final ControlSource controlSource) {
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    public long getAmountBuffered() {
        return 0L;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected int getCurrentIndex() {
        return -1;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected MediaCollectionInfo getMediaCollectionInfo() {
        return null;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected String getMediaPlayerName() {
        return "CASTING";
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected float getPlaybackSpeed() {
        return 1.0f;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    public PlayerCapabilities getPlayerCapabilities() {
        return null;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected int getUnshuffledIndex(final int i) {
        return -1;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    public boolean isStopped() {
        return false;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    public boolean isStreaming() {
        return true;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void normalSpeed(final ControlSource controlSource) {
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void onUserInteraction(final ControlSource controlSource) {
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean prefersRestartOverPrevious() {
        return false;
    }

    @WorkerThread
    public void recoverSequencer() {
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void resolveMaxConcurrency(final String s, final boolean b, final ControlSource controlSource, final long l) {
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void rewind(final ControlSource controlSource) {
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void setPlaybackSpeed(final double speed) {
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    public boolean shouldPauseWhenTaskRemoved() {
        return false;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void skipToIndex(final int i, final ControlSource controlSource) {
    }

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        REPORT_REPEAT_MAX_FROM_END_MILLIS = timeUnit.toMillis(5L);
        REPORT_REPEAT_MAX_FROM_BEGINNING_MILLIS = timeUnit.toMillis(5L);
        REPORT_REPEAT_MIN_DURATION_MILLIS = timeUnit.toMillis(30L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(CastingQueueResult castingQueueResult) {
        this.mStateManager.trackCastingTransferQueue(LatencyTrackingLeg.LOAD_COMPLETE);
        if (castingQueueResult == CastingQueueResult.SUCCESS) {
            this.mSendPlayMode = true;
        }
        this.mStateManager.onQueueUpdated(castingQueueResult);
        if (castingQueueResult == CastingQueueResult.DEVICE_OFFLINE) {
            notifyPlaybackError(new PlaybackException(new PlaybackException.Config(PlaybackException.Recoverability.MEDIA_ITEM).description("Device offline").autoRetry(false)));
        } else if (castingQueueResult == CastingQueueResult.UNKNOWN_FAILURE) {
            notifyPlaybackError(new PlaybackException(new PlaybackException.Config(PlaybackException.Recoverability.MEDIA_ITEM).description("Casting error").autoRetry(false)));
        }
    }

    public CastingPlaybackProvider(final PlaybackConfig playbackConfig) {
        super(playbackConfig);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new ThreadFactory() { // from class: com.amazon.mp3.playback.casting.CastingPlaybackProvider.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(@NonNull final Runnable runnable) {
                return new Thread("CastingPlaybackProvider") { // from class: com.amazon.mp3.playback.casting.CastingPlaybackProvider.1.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        runnable.run();
                    }
                };
            }
        });
        this.mExecutor = threadPoolExecutor;
        this.mCastingManager = CastingSessionManager.getInstance();
        this.mStateManager = CastingStateManager.getInstance();
        this.mStateCallback = new CastingStateManager.Callback() { // from class: com.amazon.mp3.playback.casting.CastingPlaybackProvider.2
            @Override // com.amazon.mp3.playback.casting.CastingStateManager.Callback
            public void onPlayStateChanged() {
                MediaItem currentMediaItem = CastingPlaybackProvider.this.getCurrentMediaItem();
                long positionMillis = CastingPlaybackProvider.this.getPositionMillis();
                if (CastingPlaybackProvider.this.mPreviousMediaItem == currentMediaItem && currentMediaItem != null) {
                    long durationMillis = CastingPlaybackProvider.this.getDurationMillis();
                    if (positionMillis <= CastingPlaybackProvider.REPORT_REPEAT_MAX_FROM_BEGINNING_MILLIS && durationMillis - CastingPlaybackProvider.this.mPreviousPosition <= CastingPlaybackProvider.REPORT_REPEAT_MAX_FROM_END_MILLIS && durationMillis >= CastingPlaybackProvider.REPORT_REPEAT_MIN_DURATION_MILLIS) {
                        CastingPlaybackProvider.this.notifyPlaybackEvent(27, null);
                    }
                }
                CastingPlaybackProvider.this.mPreviousMediaItem = currentMediaItem;
                CastingPlaybackProvider.this.mPreviousPosition = positionMillis;
                CastingPlaybackProvider.this.notifyPlayStateChanged();
            }

            @Override // com.amazon.mp3.playback.casting.CastingStateManager.Callback
            public void onPositionChanged(final long positionMillis) {
                if (CastingPlaybackProvider.this.mPreviousPosition < positionMillis) {
                    CastingPlaybackProvider.this.mPreviousPosition = positionMillis;
                }
            }

            @Override // com.amazon.mp3.playback.casting.CastingStateManager.Callback
            public void onMediaItemChanged(MediaItem mediaItem) {
                if (CastingPlaybackProvider.this.mSendPlayMode && mediaItem != null) {
                    CastingPlaybackProvider.this.mSendPlayMode = false;
                    CastingPlaybackProvider castingPlaybackProvider = CastingPlaybackProvider.this;
                    castingPlaybackProvider.forceSetShuffled(castingPlaybackProvider.isShuffled());
                    CastingPlaybackProvider castingPlaybackProvider2 = CastingPlaybackProvider.this;
                    castingPlaybackProvider2.forceSetRepeatMode(castingPlaybackProvider2.getRepeatMode());
                }
                CastingPlaybackProvider.this.updateIndex();
            }
        };
        this.mCastingQueueCallback = new CastingQueueCallback() { // from class: com.amazon.mp3.playback.casting.CastingPlaybackProvider$$ExternalSyntheticLambda0
            @Override // com.amazon.music.casting.session.CastingQueueCallback
            public final void onQueueUpdated(CastingQueueResult castingQueueResult) {
                this.f$0.lambda$new$0(castingQueueResult);
            }
        };
        this.mQueueHandler = new CastingQueueHandler(threadPoolExecutor);
        PlaybackPersistenceProvider playbackPersistenceProvider = playbackConfig.getPlaybackPersistenceProvider();
        this.persistenceProvider = playbackPersistenceProvider instanceof SequencerPersistenceProvider ? (SequencerPersistenceProvider) playbackPersistenceProvider : null;
    }

    @Override // com.amazon.music.media.playback.SequencerPlaybackProvider
    public void setSequencer(final Sequencer sequencer, final ControlSource controlSource) {
        Log.debug(TAG, "setSequencer(" + sequencer + ")");
        setDisconnectSequencer(sequencer);
    }

    @Override // com.amazon.music.media.playback.SequencerPlaybackProvider
    public void setSequencerAndPosition(final Sequencer sequencer, final long positionMillis, final SequencerPlaybackProvider.MediaItemVerifier verifier, final ControlSource controlSource) {
        Log.debug(TAG, "setSequencerAndPosition(" + sequencer + ")");
        if ((this.mSavedSequencer instanceof PlaceholderSequencer) && this.mSequencerListener == null) {
            this.mSavedSequencer = sequencer;
            return;
        }
        if (Feature.casting_v2.isEnabled() && (sequencer instanceof CloudQueueSequencer)) {
            CloudQueueSequencer cloudQueueSequencer = (CloudQueueSequencer) sequencer;
            if (!cloudQueueSequencer.getIsOnlineInteraction()) {
                this.mStateManager.trackCastingTransferQueue(LatencyTrackingLeg.REQUEST);
                Integer numInitiateOnlineInteraction = initiateOnlineInteraction(cloudQueueSequencer, positionMillis);
                this.mStateManager.trackCastingTransferQueue(LatencyTrackingLeg.PROCESSING);
                if (numInitiateOnlineInteraction.intValue() == 1) {
                    CloudQueueSequencer cloudQueueSequencer2 = CloudQueueSequencer.getCloudQueueSequencer();
                    boolean z = cloudQueueSequencer2 != null && cloudQueueSequencer2.getIsOnlineInteraction();
                    CloudQueueFlexMetricsManager.trackEvent(CloudQueueFlexMetricsManager.Event.CASTING_INITIATE_ONLINE, null, null, null, z, !z);
                    if (z) {
                        Playback.getInstance().getPlaybackController(controlSource).stop(ChangeReason.SWITCHING_PLAYERS);
                        cast(cloudQueueSequencer2, positionMillis, verifier);
                        return;
                    } else if (!UserSubscriptionUtil.isHawkfire() && UserSubscriptionUtil.getUserSubscription().isSonicRush()) {
                        return;
                    }
                } else {
                    CloudQueueFlexMetricsManager.trackEvent(CloudQueueFlexMetricsManager.Event.CASTING_INITIATE_ONLINE, null, null, null, false, true);
                }
            }
        }
        cast(sequencer, positionMillis, verifier);
    }

    @Override // com.amazon.music.media.playback.SequencerPlaybackProvider
    public void startSequencer(final Sequencer sequencer, final ControlSource controlSource, final long startTime) {
        Log.debug(TAG, "startSequencer(" + sequencer + ")");
        cast(sequencer, 0L, null);
    }

    public void setDisconnectSequencer(final Sequencer sequencer) {
        Log.debug(TAG, "setDisconnectSequencer(" + sequencer + ")");
        if (isRemoteStation(sequencer)) {
            sequencer = new NoOpSequencer();
        }
        this.mSavedSequencer = sequencer;
        this.mVerifier = null;
        stopWaitingForMediaItem();
        this.mStateManager.onWillNotCast();
    }

    public void cast(final Sequencer sequencer, final long positionMillis, final SequencerPlaybackProvider.MediaItemVerifier verifier) {
        Log.info(TAG, "cast(" + sequencer + ", " + positionMillis + ")");
        boolean zIsRemoteStation = isRemoteStation(sequencer);
        if (zIsRemoteStation) {
            sequencer = new NoOpSequencer();
        }
        this.mSavedSequencer = sequencer;
        this.mStartPositionMillis = positionMillis;
        this.mVerifier = verifier;
        stopWaitingForMediaItem();
        setupFromSequencer();
        waitForMediaItem();
        if (zIsRemoteStation) {
            this.mStateManager.onRemoteCast();
        }
    }

    private Integer initiateOnlineInteraction(final CloudQueueSequencer cloudQueueSequencer, final long positionMillis) {
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        Uri contentUri = cloudQueueSequencer.getContentUri();
        Queue queue = cloudQueueSequencer.getQueue();
        List<QueueSeed> queueSeeds = queue.getQueueSeeds();
        if (contentUri == null || queueSeeds == null || queueSeeds.isEmpty()) {
            Log.warning(TAG, "initiateOnlineInteraction skipped, contentUri or queueSeeds is empty");
            return 3;
        }
        Uri nonTrackCollectionUri = MediaProvider.Tracks.getNonTrackCollectionUri(contentUri);
        if (nonTrackCollectionUri == null) {
            nonTrackCollectionUri = contentUri;
        }
        PlayModeEnum playMode = getPlayMode(AmazonApplication.getLibraryItemFactory().getItem(nonTrackCollectionUri));
        if (playMode == null) {
            Log.warning(TAG, "initiateOnlineInteraction skipped, play mode is null");
            return 3;
        }
        QueueSeedContextInput queueSeedContextInputGenerateQueueSeedContextInput = generateQueueSeedContextInput(queueSeeds, playMode, cloudQueueSequencer);
        if (queueSeedContextInputGenerateQueueSeedContextInput == null) {
            Log.warning(TAG, "initiateOnlineInteraction skipped, queueSeedContextInput is null, queueSeeds=" + queueSeeds);
            return 3;
        }
        StartAt startAtCreateInitiateQueueStartAt = createInitiateQueueStartAt(cloudQueueSequencer.getCurrentMediaItem(), queueSeedContextInputGenerateQueueSeedContextInput, (int) positionMillis);
        CloudQueueAppClient cloudQueueAppClientBlockingFirst = CloudQueueClientSingleton.INSTANCE.getCloudQueueAppClientObservable().blockingFirst();
        if (cloudQueueAppClientBlockingFirst instanceof NoOpCloudQueueAppClient) {
            Log.error(TAG, "initiateOnlineInteraction failed due to NoOpCloudQueueAppClient, continue existing flow");
            return 3;
        }
        CloudQueuePlaybackUtil.startOnlineQueueInteraction(queue.getTitle(), cloudQueueAppClientBlockingFirst, applicationContext, queueSeedContextInputGenerateQueueSeedContextInput, cloudQueueSequencer.getCollectionMetricsContext().getPageType(), null, cloudQueueSequencer.getControlSource(), contentUri, true, cloudQueueSequencer.getAdditionalMediaCollectionId(), null, false, startAtCreateInitiateQueueStartAt, CloudQueueDigitalXPUtil.getPlayBackOptions(queueSeedContextInputGenerateQueueSeedContextInput.getPlayMode(), cloudQueueSequencer.isShuffled(), cloudQueueSequencer.getRepeatMode()), Boolean.TRUE);
        return 1;
    }

    private PlayModeEnum getPlayMode(final EntityEligibilitiesProvider eligibilityProvider) {
        if (eligibilityProvider == null) {
            return null;
        }
        EligibilityUtil eligibilityUtil = new EligibilityUtil();
        PlayQueueInteractionPattern playQueueInteractionPattern = eligibilityUtil.getPlayQueueInteractionPattern(eligibilityProvider);
        if (playQueueInteractionPattern != PlayQueueInteractionPattern.ONLINE) {
            Log.warning(TAG, "initiateOnlineInteraction skipped, get interaction pattern " + playQueueInteractionPattern);
            return null;
        }
        PlayModeEnum playMode = eligibilityUtil.getPlayMode(eligibilityProvider);
        Log.debug(TAG, "playMode: " + playMode);
        return playMode;
    }

    private QueueSeedContextInput generateQueueSeedContextInput(final List<QueueSeed> queueSeeds, final PlayModeEnum playMode, final CloudQueueSequencer cloudQueueSequencer) {
        QueueSeedContextInput queueSeedContextInput = CloudQueueDigitalXPUtil.toQueueSeedContextInput(queueSeeds.get(0), playMode);
        return queueSeedContextInput == null ? CloudQueueDigitalXPUtil.getQueueSeedContextInputByEntityReferences(cloudQueueSequencer.getLocalQueueSequenceSlice().getEntityReferences(), playMode) : queueSeedContextInput;
    }

    private StartAt createInitiateQueueStartAt(final MediaItem currentMediaItem, final QueueSeedContextInput queueSeedContextInput, final int positionMillis) {
        StartAt startAtCreateStartAtForCatalogTrack;
        if (currentMediaItem == null) {
            return null;
        }
        if (currentMediaItem.isOwned()) {
            startAtCreateStartAtForCatalogTrack = createStartAtForOwnedTrack(currentMediaItem, queueSeedContextInput, positionMillis);
        } else {
            startAtCreateStartAtForCatalogTrack = createStartAtForCatalogTrack(currentMediaItem, positionMillis);
        }
        Log.debug(TAG, "startAt.getTrackIdentifier() = " + startAtCreateStartAtForCatalogTrack.getQueueEntityId() + " startAt.getTrackIdentifierType() = " + startAtCreateStartAtForCatalogTrack.getQueueEntityIdType());
        return startAtCreateStartAtForCatalogTrack;
    }

    private StartAt createStartAtForCatalogTrack(final MediaItem currentMediaItem, final int positionMillis) {
        String asin = MediaItemHelper.getAsin(currentMediaItem);
        if (PrimePlaylistUtil.isAsin(asin)) {
            return CloudQueueDigitalXPUtil.getStartAtWithEntityAsin(asin, positionMillis);
        }
        return CloudQueueDigitalXPUtil.getStartAtWithEntityObjectId(MediaItemHelper.getLuidOrOwnedId(currentMediaItem), positionMillis);
    }

    private StartAt createStartAtForOwnedTrack(final MediaItem currentMediaItem, final QueueSeedContextInput queueSeed, final int positionMillis) {
        if (queueSeed.getQueueSeedType() == QueueSeedTypeEnum.TRACK_LIST && PrimePlaylistUtil.isAsin(MediaItemHelper.getAsin(currentMediaItem))) {
            return CloudQueueDigitalXPUtil.getStartAtWithEntityAsin(MediaItemHelper.getAsin(currentMediaItem), positionMillis);
        }
        return CloudQueueDigitalXPUtil.getStartAtWithEntityObjectId(MediaItemHelper.getLuidOrOwnedId(currentMediaItem), positionMillis);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    @NonNull
    public PlaybackAttributes getCurrentPlaybackAttributes() {
        return PlaybackAttributes.UNKNOWN;
    }

    private boolean isRemoteStation(final Sequencer sequencer) {
        return (sequencer instanceof StationSequencer) && ((StationSequencer) sequencer).getRemoteTargetId() != null;
    }

    private void setupFromSequencer() {
        SequencerPlaybackProvider.MediaItemVerifier mediaItemVerifier;
        MediaItem currentMediaItem = this.mSavedSequencer.getCurrentMediaItem();
        long j = (currentMediaItem == null || (mediaItemVerifier = this.mVerifier) == null || mediaItemVerifier.isCorrectMediaItem(currentMediaItem)) ? this.mStartPositionMillis : 0L;
        this.mStateManager.onWillCast(this.mSavedSequencer, j);
        SequencerPersistenceProvider sequencerPersistenceProvider = this.persistenceProvider;
        if (sequencerPersistenceProvider != null) {
            Sequencer sequencer = this.mSavedSequencer;
            if ((sequencer instanceof PlaceholderSequencer) || (sequencer instanceof NoOpSequencer)) {
                return;
            }
            sequencerPersistenceProvider.saveSequencerState(sequencer);
            this.persistenceProvider.savePosition(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSequencerFailedToLoad() {
        CastingSessionManager.getInstance().disconnect(TerminationReason.NETWORK_ERROR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkForMediaItem() {
        Sequencer sequencer = this.mSavedSequencer;
        if (sequencer == null || (sequencer instanceof NoOpSequencer)) {
            stopWaitingForMediaItem();
            this.mStateManager.onWillNotCast();
            return;
        }
        if (sequencer instanceof PlaceholderSequencer) {
            return;
        }
        if (!(sequencer instanceof PlayQueueSequencer) || ((PlayQueueSequencer) sequencer).isQueueReady()) {
            if (this.mSavedSequencer.getCurrentMediaItem() == null) {
                if (this.mSavedSequencer.isFetchingCurrentMediaItem()) {
                    return;
                }
                if (this.mSavedSequencer.canFetchCurrentMediaItem()) {
                    this.mSavedSequencer.fetchCurrentMediaItem();
                    return;
                } else {
                    stopWaitingForMediaItem();
                    onSequencerFailedToLoad();
                    return;
                }
            }
            setupFromSequencer();
            stopWaitingForMediaItem();
            cast();
        }
    }

    private void cast() {
        String mediaCollectionIdString = MediaCollectionInfo.getMediaCollectionIdString(this.mSavedSequencer.getMediaCollectionInfo(), MediaCollectionId.Type.STATION_INSTANCE_ID, null);
        String mediaCollectionIdString2 = MediaCollectionInfo.getMediaCollectionIdString(this.mSavedSequencer.getMediaCollectionInfo(), MediaCollectionId.Type.STATION_KEY, null);
        if (AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
            Sequencer sequencer = this.mSavedSequencer;
            if (sequencer instanceof CloudQueueSequencer) {
                boolean isOnlineInteraction = ((CloudQueueSequencer) sequencer).getIsOnlineInteraction();
                this.mStateManager.onCast();
                if (isOnlineInteraction) {
                    this.mQueueHandler.transferQueue(((CloudQueueSequencer) this.mSavedSequencer).getQueue().getId(), this.mSavedSequencer.getCurrentMediaItem(), this.mStartPositionMillis, this.mCastingQueueCallback, this.mStateManager);
                    return;
                }
                if (CastingUtil.isSupportedContent(this.mSavedSequencer.getCurrentMediaItem())) {
                    Log.debug(TAG, "start PlayQueue");
                    CastingQueueHandler castingQueueHandler = this.mQueueHandler;
                    Sequencer sequencer2 = this.mSavedSequencer;
                    castingQueueHandler.startPlayQueue(sequencer2, this.mStartPositionMillis, sequencer2.isShuffled(), this.mCastingQueueCallback);
                } else {
                    Log.error(TAG, "unsupported Content on: " + this.mSavedSequencer);
                    this.mQueueHandler.handleUnsupportedContent(false);
                }
                this.mStateManager.onLocalSetShuffled(this.mSavedSequencer.isShuffled());
                this.mStateManager.onLocalSetRepeatMode(this.mSavedSequencer.getRepeatMode());
                return;
            }
        }
        if (mediaCollectionIdString != null) {
            Log.debug(TAG, "start Station");
            this.mStateManager.onCast();
            this.mQueueHandler.transferQueue(mediaCollectionIdString, this.mSavedSequencer.getCurrentMediaItem(), this.mStartPositionMillis, this.mCastingQueueCallback, null);
            return;
        }
        if (mediaCollectionIdString2 != null) {
            Log.error(TAG, "Sequencer has a stationKey but no stationId!");
            onSequencerFailedToLoad();
            return;
        }
        Sequencer sequencer3 = this.mSavedSequencer;
        if ((sequencer3 instanceof PlayQueueSequencer) || (sequencer3 instanceof CloudQueueSequencer)) {
            this.mStateManager.onCast();
            if (CastingUtil.isSupportedContent(this.mSavedSequencer.getCurrentMediaItem())) {
                Log.debug(TAG, "start PlayQueue");
                CastingQueueHandler castingQueueHandler2 = this.mQueueHandler;
                Sequencer sequencer4 = this.mSavedSequencer;
                castingQueueHandler2.startPlayQueue(sequencer4, this.mStartPositionMillis, sequencer4.isShuffled(), this.mCastingQueueCallback);
            } else {
                Log.error(TAG, "unsupported Content on: " + this.mSavedSequencer);
                this.mQueueHandler.handleUnsupportedContent(false);
            }
            this.mStateManager.onLocalSetShuffled(this.mSavedSequencer.isShuffled());
            this.mStateManager.onLocalSetRepeatMode(this.mSavedSequencer.getRepeatMode());
            return;
        }
        Log.warning(TAG, "unsupported Sequencer: " + this.mSavedSequencer);
        this.mQueueHandler.handleUnsupportedContent(true);
    }

    private void waitForMediaItem() {
        SequencerListener sequencerListener = new SequencerListener() { // from class: com.amazon.mp3.playback.casting.CastingPlaybackProvider.3
            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMediaCollectionInfoChanged(@Nullable final MediaCollectionInfo info) {
            }

            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMetadataChanged(final MediaItem mediaItem) {
            }

            @Override // com.amazon.music.media.playback.sequencer.SequencerListener
            public void onErrorFetchingMediaItems(final Sequencer sequencer, final PlaybackException exception) {
                String str = CastingPlaybackProvider.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onErrorFetchingMediaItems() - ");
                sb.append(CastingPlaybackProvider.this.mSequencerListener == this);
                sb.append(", mSequencerListener: ");
                sb.append(CastingPlaybackProvider.this.mSequencerListener);
                sb.append(", this: ");
                sb.append(this);
                Log.info(str, sb.toString(), exception);
                if (CastingPlaybackProvider.this.mSequencerListener == this) {
                    if (!exception.getRecoverability().canRecoverCollection() || !exception.canAutoRetry()) {
                        CastingPlaybackProvider.this.stopWaitingForMediaItem();
                        CastingPlaybackProvider.this.onSequencerFailedToLoad();
                    } else {
                        CastingPlaybackProvider.this.checkForMediaItem();
                    }
                }
            }

            @Override // com.amazon.music.media.playback.sequencer.SequencerListener
            public void onSequencerInfoChanged(final Sequencer sequencer) {
                String str = CastingPlaybackProvider.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onSequencerInfoChanged() - ");
                sb.append(CastingPlaybackProvider.this.mSequencerListener == this);
                sb.append(", mSequencerListener: ");
                sb.append(CastingPlaybackProvider.this.mSequencerListener);
                sb.append(", this: ");
                sb.append(this);
                Log.info(str, sb.toString());
                if (CastingPlaybackProvider.this.mSequencerListener == this) {
                    CastingPlaybackProvider.this.checkForMediaItem();
                }
            }
        };
        this.mSequencerListener = sequencerListener;
        this.mSavedSequencer.addSequencerListener(sequencerListener);
        checkForMediaItem();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopWaitingForMediaItem() {
        SequencerListener sequencerListener = this.mSequencerListener;
        if (sequencerListener != null) {
            Sequencer sequencer = this.mSavedSequencer;
            if (sequencer != null) {
                sequencer.removeSequencerListener(sequencerListener);
            }
            this.mSequencerListener = null;
        }
    }

    @Override // com.amazon.music.media.playback.SequencerPlaybackProvider
    public Sequencer getSequencer() {
        return this.mSavedSequencer;
    }

    public void onDisconnected() {
        Log.debug(TAG, "onDisconnected()");
        this.mStateManager.onDisconnected(this.mSavedSequencer);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void close() {
        Log.debug(TAG, "close()");
        stopWaitingForMediaItem();
        this.mStateManager.setCallback(null);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void open() {
        Log.debug(TAG, "open()");
        this.mStateManager.setCallback(this.mStateCallback);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void stop(final ChangeReason changeReason, final ControlSource controlSource) {
        pause(controlSource);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void play(final ControlSource controlSource, final long requestTime) {
        if (getPlayStatus().willPlay()) {
            return;
        }
        this.mStateManager.onLocalPlay();
        this.mCastingManager.play();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void pause(final ControlSource controlSource) {
        if (getPlayStatus().willPlay()) {
            this.mStateManager.onLocalPause();
            this.mCastingManager.pause();
        }
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected PlayStatus getPlayStatus() {
        return this.mStateManager.getPlayStatus();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void skip(final int numSkips, final ChangeReason changeReason, final ControlSource controlSource) {
        if (numSkips > 0 && canSkipNext()) {
            this.mStateManager.onLocalSkip();
            this.mStateManager.onLocalPlay();
            this.mCastingManager.next();
        } else {
            if (numSkips >= 0 || !canSkipPrevious()) {
                return;
            }
            this.mStateManager.onLocalSkip();
            this.mStateManager.onLocalPlay();
            this.mCastingManager.previous();
        }
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void restartMediaItem(final ControlSource controlSource) {
        seek(0L, ChangeReason.RESTART, controlSource);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canSkipPrevious() {
        return this.mStateManager.canSkipPrevious() || Feature.casting_previous_button_clickability_override.isEnabled();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canRestartItem() {
        return this.mStateManager.canSeek() && getPositionMillis() > 0;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected ActionAvailability getSkipNextAvailability() {
        return canSkipNext() ? ActionAvailability.AVAILABLE : ActionAvailability.UNAVAILABLE_SKIP_LIMIT;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0021  */
    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean canSkipNext() {
        /*
            r4 = this;
            com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer r0 = com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer.getCloudQueueSequencer()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L21
            com.amazon.music.media.playback.MediaItem r0 = r0.getCurrentMediaItem()
            boolean r3 = r0 instanceof com.amazon.mp3.cloudqueue.sequencer.QueueEntityCapabilityQueryable
            if (r3 == 0) goto L21
            com.amazon.mp3.cloudqueue.sequencer.QueueEntityCapabilityQueryable r0 = (com.amazon.mp3.cloudqueue.sequencer.QueueEntityCapabilityQueryable) r0
            com.amazon.mp3.cloudqueue.QueueEntityCapabilityTranslator r3 = com.amazon.mp3.cloudqueue.QueueEntityCapabilityTranslator.INSTANCE
            com.amazon.music.media.playback.ActionAvailability r0 = r3.getSkipNextAvailabilityFromCapability(r0)
            if (r0 == 0) goto L21
            com.amazon.music.media.playback.ActionAvailability r3 = com.amazon.music.media.playback.ActionAvailability.AVAILABLE
            if (r0 != r3) goto L1f
            goto L21
        L1f:
            r0 = r1
            goto L22
        L21:
            r0 = r2
        L22:
            com.amazon.mp3.playback.casting.CastingStateManager r3 = r4.mStateManager
            boolean r3 = r3.canSkipNext()
            if (r3 == 0) goto L2d
            if (r0 == 0) goto L2d
            r1 = r2
        L2d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.casting.CastingPlaybackProvider.canSkipNext():boolean");
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canShuffle() {
        return this.mStateManager.canShuffle();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean isShuffled() {
        return this.mStateManager.isShuffled();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void setShuffled(final boolean shuffled, final boolean preserveCurrent, final ControlSource controlSource) {
        if (!this.mStateManager.canShuffle() || this.mStateManager.isShuffled() == shuffled) {
            return;
        }
        forceSetShuffled(shuffled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forceSetShuffled(final boolean shuffled) {
        this.mStateManager.onLocalSetShuffled(shuffled);
        this.mCastingManager.setShuffled(shuffled);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected RepeatMode getRepeatMode() {
        return this.mStateManager.getRepeatMode();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void setRepeatMode(final RepeatMode repeatMode, final ControlSource controlSource) {
        if (getRepeatMode() == repeatMode || !getAvailableRepeatModes().contains(repeatMode)) {
            return;
        }
        forceSetRepeatMode(repeatMode);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.casting.CastingPlaybackProvider$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$RepeatMode;

        static {
            int[] iArr = new int[RepeatMode.values().length];
            $SwitchMap$com$amazon$music$media$playback$RepeatMode = iArr;
            try {
                iArr[RepeatMode.REPEAT_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$RepeatMode[RepeatMode.REPEAT_ONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forceSetRepeatMode(final RepeatMode repeatMode) {
        NowPlayingThingShadow.State.LoopMode loopMode;
        int i = AnonymousClass4.$SwitchMap$com$amazon$music$media$playback$RepeatMode[repeatMode.ordinal()];
        if (i == 1) {
            loopMode = NowPlayingThingShadow.State.LoopMode.LOOP_ALL;
        } else if (i == 2) {
            loopMode = NowPlayingThingShadow.State.LoopMode.LOOP_ONE;
        } else {
            loopMode = NowPlayingThingShadow.State.LoopMode.LOOP_OFF;
        }
        this.mStateManager.onLocalSetRepeatMode(repeatMode);
        this.mCastingManager.setLoopMode(loopMode);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected EnumSet<RepeatMode> getAvailableRepeatModes() {
        return this.mStateManager.getAvailableRepeatModes();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canSeek() {
        return this.mStateManager.canSeek();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected void seek(final long positionMillis, final ChangeReason changeReason, final ControlSource controlSource) {
        if (canSeek()) {
            this.mStateManager.onLocalSetPosition(positionMillis);
            this.mCastingManager.seek(positionMillis);
        }
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    public MediaItem getCurrentMediaItem() {
        return this.mStateManager.getCurrentMediaItem();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    public long getPositionMillis() {
        return this.mStateManager.getPositionMillis();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected long getDurationMillis() {
        MediaItem currentMediaItem = getCurrentMediaItem();
        if (currentMediaItem == null) {
            return 0L;
        }
        return currentMediaItem.getDurationMillis();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected MediaItem peek(final int numSkips) {
        if (numSkips == 0) {
            return getCurrentMediaItem();
        }
        return null;
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected List<MediaItem> getCurrentQueue() {
        MediaItem currentMediaItem = getCurrentMediaItem();
        return currentMediaItem == null ? Collections.emptyList() : Collections.singletonList(currentMediaItem);
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean isLive() {
        MediaItem currentMediaItem = getCurrentMediaItem();
        return currentMediaItem != null && currentMediaItem.getType().isLive();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected VolumeProviderCompat getVolumeProvider() {
        return CastingVolumeController.get().getCastingVolumeProvider();
    }

    @Override // com.amazon.music.media.playback.impl.AbstractPlaybackProvider
    protected boolean canRateMediaItems() {
        return this.mStateManager.canRateMediaItems();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIndex() {
        MediaItem currentMediaItem = getCurrentMediaItem();
        if (currentMediaItem != null) {
            updateIndexForSequencer(this.mSavedSequencer, currentMediaItem);
        }
    }

    private void updateIndexForSequencer(final Sequencer sequencer, final MediaItem mediaItem) {
        int iIndexOf = sequencer.indexOf(mediaItem);
        Log.info(TAG, "updateIndex() => " + iIndexOf);
        if (iIndexOf < 0 || iIndexOf == sequencer.getCurrentIndex()) {
            return;
        }
        sequencer.setCurrentIndex(iIndexOf, null);
        sequencer.setShuffled(isShuffled(), true);
        sequencer.setRepeatMode(getRepeatMode());
        SequencerPersistenceProvider sequencerPersistenceProvider = this.persistenceProvider;
        if (sequencerPersistenceProvider != null) {
            sequencerPersistenceProvider.saveSequencerState(sequencer);
            this.persistenceProvider.savePosition(getPositionMillis());
        }
    }
}
