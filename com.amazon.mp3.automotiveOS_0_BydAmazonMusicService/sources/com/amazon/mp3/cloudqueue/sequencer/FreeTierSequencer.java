package com.amazon.mp3.cloudqueue.sequencer;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.cloudqueue.CloudQueue;
import com.amazon.mp3.cloudqueue.CloudQueueNoNextException;
import com.amazon.mp3.cloudqueue.CloudQueueService;
import com.amazon.mp3.cloudqueue.PlaybackActionCapability;
import com.amazon.mp3.cloudqueue.RateTrackManager;
import com.amazon.mp3.cloudqueue.SynchronizedPlayableEntity;
import com.amazon.mp3.cloudqueue.model.AdEntity;
import com.amazon.mp3.cloudqueue.model.ConnectionInfoMetadata;
import com.amazon.mp3.cloudqueue.model.MetricsContext;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.SongEntity;
import com.amazon.mp3.cloudqueue.sequencer.FreeTierSequencer;
import com.amazon.mp3.likes.MediaItemLikesProviderImpl;
import com.amazon.mp3.skiplimit.ErrorType;
import com.amazon.mp3.skiplimit.SkipLimitControllerProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.ContentAccessType;
import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaLinkProvider;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.sequencer.IndexedSequencerBase;
import com.amazon.music.media.playback.skiplimit.SkipLimitProvider;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.data.entity.EntityItem;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.Validate;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class FreeTierSequencer extends RemoteSourceSequencer {
    private static final String TAG = "FreeTierSequencer";
    private final String IMAGE_URL_KEY;
    private AccountDetailUtil accountDetailUtil;
    private final CloudQueue cloudQueue;
    private final Context context;
    private boolean fetchingUpcoming;
    private boolean hasUpdatedMediaCollectionInfo;
    private final MediaItem.ImageUriProvider imageUriProvider;
    private boolean isStationPlayback;
    private final boolean isVoiceInitiated;
    private MediaItem lastItemCapabilitiesApplied;
    private final MediaLinkProvider mediaLinkProvider;
    private final PlaybackPageType playbackPageType;
    private final String queueTitle;
    private final RateTrackManager rateTrackManager;
    private SkipLimitProvider skipLimitProvider;
    private boolean startedTimer;

    public FreeTierSequencer(@NonNull final CloudQueue cloudQueue, @NonNull final CloudQueueService cloudQueueService, @Nullable final MediaLinkProvider mediaLinkProvider, @Nullable final MediaItem.ImageUriProvider imageUriProvider, @NonNull final PlaybackPageType playbackPageType, @Nullable final SkipLimitProvider skipLimitProvider, @Nullable final Context context, final boolean isVoiceInitiated, final boolean isStation, final MediaCollectionType mediaCollectionType, @Nullable final String queueTitle) {
        super(true);
        this.fetchingUpcoming = false;
        this.lastItemCapabilitiesApplied = null;
        this.IMAGE_URL_KEY = "FULL";
        this.isStationPlayback = isStation;
        this.cloudQueue = (CloudQueue) Validate.notNull(cloudQueue);
        this.rateTrackManager = new RateTrackManager(cloudQueueService);
        this.mediaLinkProvider = mediaLinkProvider;
        this.imageUriProvider = imageUriProvider;
        this.playbackPageType = (PlaybackPageType) Validate.notNull(playbackPageType);
        this.isVoiceInitiated = isVoiceInitiated;
        this.skipLimitProvider = skipLimitProvider;
        this.context = context;
        this.accountDetailUtil = AccountDetailUtil.get(context);
        if (queueTitle != null) {
            this.queueTitle = queueTitle;
        } else {
            this.queueTitle = "";
        }
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        sequencerInfoCopySequencerInfo.setMediaCollectionInfo(new MediaCollectionInfo(null, null, null, AmazonApplication.getCapabilities().isFreeTierContextualUpsellsEnabled() ? mediaCollectionType : isStation ? null : MediaCollectionType.GOLDEN_PLAYLIST, constructMediaAccessInfo(), new MediaCollectionId[0]));
        sequencerInfoCopySequencerInfo.getMediaCollectionInfo().addId(getPlaybackSessionId());
        sequencerInfoCopySequencerInfo.setCanLoadMore(true);
        sequencerInfoCopySequencerInfo.setCanShuffle(false);
        setSequencerInfo(sequencerInfoCopySequencerInfo);
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    public MediaAccessInfo constructMediaAccessInfo() {
        return new MediaAccessInfo.Builder().withAllowedOnline(true).withAvailableOffline(false).withExplicit(true).withCastable(false).withContentAccessType(ContentAccessType.NIGHTWING).build();
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    @NonNull
    public String getQueueTitle() {
        if (StringUtils.isEmpty(this.queueTitle)) {
            return this.cloudQueue.getQueueMetadata() != null ? this.cloudQueue.getQueueMetadata().getTitle() : "";
        }
        return this.queueTitle;
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    @Nullable
    public Map<String, String> getImageUrlMap() {
        if (this.cloudQueue.getQueueMetadata() != null) {
            return this.cloudQueue.getQueueMetadata().getImageUrlMap();
        }
        return null;
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    @NonNull
    public MediaCollectionId.Type getCollectionImageUrlIdType() {
        return MediaCollectionId.Type.CLOUD_QUEUE_IMAGE_URL;
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    @Nullable
    /* JADX INFO: renamed from: getMetricsContext */
    public MetricsContext getCollectionMetricsContext() {
        return this.cloudQueue.getMetricsContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaCollectionInfo getAdvertisementCollectionInfo(@NonNull AdEntity adEntity) {
        return new MediaCollectionInfo(adEntity.getAdName(), adEntity.getProductName(), new MediaCollectionId(MediaCollectionId.Type.ADVERTISEMENT, adEntity.getAdId()), MediaCollectionType.getCollectionType(this.cloudQueue.getMetricsContext().getSelectionSourceType().name()), constructMediaAccessInfo(), new MediaCollectionId[0]);
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    @NonNull
    public MediaCollectionId.Type getCollectionPrimaryIdType() {
        return MediaCollectionId.Type.MPQS;
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    public boolean hasInitTrackLoaded() {
        return this.cloudQueue.getQueueMetadata() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaCollectionInfo getTitleAndImageUpdatedMediaCollectionInfo(final IndexedSequencerBase.SequencerInfo info, final MediaCollectionInfo currentMediaCollectionInfo) {
        String str;
        MediaCollectionInfo mediaCollectionInfo = new MediaCollectionInfo(this.cloudQueue.getQueueMetadata().getTitle(), currentMediaCollectionInfo.getSubtitle(), currentMediaCollectionInfo.getId(), currentMediaCollectionInfo.getType(), currentMediaCollectionInfo.getMediaAccessInfo(), new MediaCollectionId[0]);
        if (this.cloudQueue.getQueueMetadata().getImageUrlMap() != null && (str = this.cloudQueue.getQueueMetadata().getImageUrlMap().get("FULL")) != null) {
            info.setImageUri(Uri.parse(str));
            mediaCollectionInfo.addId(new MediaCollectionId(MediaCollectionId.Type.CLOUD_QUEUE_IMAGE_URL, str));
        }
        return mediaCollectionInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMediaCollectionInfoForSFA(final MediaCollectionInfo mediaCollectionInfo) {
        mediaCollectionInfo.addId(new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, this.cloudQueue.getQueueId()));
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public void fetchUpcomingMediaItems(final int numItems) {
        if (this.fetchingUpcoming) {
            return;
        }
        if (getQueueEnd() >= getCurrentIndex() + (hasInitTrackLoaded() ? this.cloudQueue.getQueueMetadata().getMaximumTrackLookAhead() : 1)) {
            return;
        }
        this.fetchingUpcoming = true;
        ThreadUtils.postInBackground(new AnonymousClass1());
    }

    /* JADX INFO: renamed from: com.amazon.mp3.cloudqueue.sequencer.FreeTierSequencer$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SynchronizedPlayableEntity synchronizedPlayableEntityNextTrack;
            ConnectionInfoMetadata connectionInfoMetadata;
            boolean z = !FreeTierSequencer.this.hasInitTrackLoaded();
            int currentIndex = z ? 1 : (FreeTierSequencer.this.getCurrentIndex() + FreeTierSequencer.this.cloudQueue.getQueueMetadata().getMaximumTrackLookAhead()) - FreeTierSequencer.this.getQueueEnd();
            ArrayList arrayList = new ArrayList(currentIndex);
            AtomicReference atomicReference = new AtomicReference();
            for (int i = 0; i < currentIndex; i++) {
                try {
                    try {
                        try {
                            try {
                                if (!FreeTierSequencer.this.startedTimer) {
                                    FreeTierSequencer.this.startedTimer = true;
                                    FreeTierSequencer.this.getMetricsTimer("EXT_LOAD_TIME").start();
                                }
                                MediaItem adMediaItem = null;
                                if (!z) {
                                    synchronizedPlayableEntityNextTrack = FreeTierSequencer.this.cloudQueue.nextTrack();
                                    IndexedSequencerBase.SequencerInfo sequencerInfo = (IndexedSequencerBase.SequencerInfo) FreeTierSequencer.this.copySequencerInfo();
                                    MediaCollectionInfo titleAndImageUpdatedMediaCollectionInfo = FreeTierSequencer.this.getTitleAndImageUpdatedMediaCollectionInfo(sequencerInfo, sequencerInfo.getMediaCollectionInfo());
                                    if (FreeTierSequencer.this.cloudQueue.getQueueMetadata() == null) {
                                        FreeTierSequencer.this.updateMediaCollectionInfoForSFA(titleAndImageUpdatedMediaCollectionInfo);
                                    }
                                    sequencerInfo.setMediaCollectionInfo(titleAndImageUpdatedMediaCollectionInfo);
                                    FreeTierSequencer.this.setSequencerInfo(sequencerInfo);
                                    connectionInfoMetadata = null;
                                } else {
                                    Pair pair = (Pair) FreeTierSequencer.this.getTrackObservable().zipWith(FreeTierSequencer.this.getConnectionInfo(), new Func2() { // from class: com.amazon.mp3.cloudqueue.sequencer.FreeTierSequencer$1$$ExternalSyntheticLambda0
                                        @Override // rx.functions.Func2
                                        public final Object call(Object obj, Object obj2) {
                                            return FreeTierSequencer.AnonymousClass1.lambda$run$0((SynchronizedPlayableEntity) obj, (ConnectionInfoMetadata) obj2);
                                        }
                                    }).toBlocking().toFuture().get();
                                    synchronizedPlayableEntityNextTrack = (SynchronizedPlayableEntity) pair.first;
                                    connectionInfoMetadata = (ConnectionInfoMetadata) pair.second;
                                }
                                if (!FreeTierSequencer.this.hasUpdatedMediaCollectionInfo && FreeTierSequencer.this.cloudQueue.getQueueMetadata() != null) {
                                    FreeTierSequencer.this.hasUpdatedMediaCollectionInfo = true;
                                    FreeTierSequencer.this.updateMediaCollectionInfo();
                                }
                                FreeTierSequencer.this.getMetricsTimer("EXT_LOAD_TIME").stop();
                                if (synchronizedPlayableEntityNextTrack instanceof SongEntity) {
                                    adMediaItem = new CloudQueueTrackMediaItem((SongEntity) synchronizedPlayableEntityNextTrack, FreeTierSequencer.this.rateTrackManager, FreeTierSequencer.this.getMediaCollectionInfo(), FreeTierSequencer.this.getRatingsProvider(), FreeTierSequencer.this.mediaLinkProvider, FreeTierSequencer.this.imageUriProvider, FreeTierSequencer.this.playbackPageType);
                                    Log.debug(FreeTierSequencer.TAG, "receive a Track " + ((SongEntity) synchronizedPlayableEntityNextTrack).getAsin());
                                } else if (synchronizedPlayableEntityNextTrack instanceof AdEntity) {
                                    adMediaItem = new AdMediaItem((AdEntity) synchronizedPlayableEntityNextTrack, FreeTierSequencer.this.getAdvertisementCollectionInfo((AdEntity) synchronizedPlayableEntityNextTrack), FreeTierSequencer.this.mediaLinkProvider, FreeTierSequencer.this.playbackPageType);
                                    Log.debug(FreeTierSequencer.TAG, "receive an Advertisement " + ((AdEntity) synchronizedPlayableEntityNextTrack).getAdName());
                                }
                                if (adMediaItem != null) {
                                    adMediaItem.setVoiceInitiated(FreeTierSequencer.this.isVoiceInitiated);
                                    if (synchronizedPlayableEntityNextTrack instanceof SongEntity) {
                                        adMediaItem.setLikesProvider(new MediaItemLikesProviderImpl(FreeTierSequencer.this.context));
                                        adMediaItem.fetchAndInitializeLikeState();
                                        adMediaItem.setCanLike(true);
                                    }
                                    arrayList.add(adMediaItem);
                                    if (z) {
                                        FreeTierSequencer freeTierSequencer = FreeTierSequencer.this;
                                        freeTierSequencer.initiateIoTSkipLimitRefresh(adMediaItem, connectionInfoMetadata, freeTierSequencer.context);
                                    }
                                }
                            } catch (ExecutionException e) {
                                if (e.getCause() instanceof CloudQueueNoNextException) {
                                    atomicReference.set(FreeTierSequencer.this.getPlaybackException((CloudQueueNoNextException) e.getCause(), "unable to fetch the next track"));
                                } else {
                                    atomicReference.set(FreeTierSequencer.this.getPlaybackException(e, "execution exception fetching the next track", "Station-NextTrackInterrupted"));
                                }
                            }
                        } catch (InterruptedException e2) {
                            atomicReference.set(FreeTierSequencer.this.getPlaybackException(e2, "interrupted while fetching the next track", "Station-NextTrackInterrupted"));
                        }
                    } catch (CloudQueueNoNextException e3) {
                        atomicReference.set(FreeTierSequencer.this.getPlaybackException(e3, "unable to fetch the next track"));
                    }
                } finally {
                    FreeTierSequencer.this.resetSequencerInfoWithMediaItemResults(arrayList, atomicReference);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Pair lambda$run$0(SynchronizedPlayableEntity synchronizedPlayableEntity, ConnectionInfoMetadata connectionInfoMetadata) {
            return new Pair(synchronizedPlayableEntity, connectionInfoMetadata);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void initiateIoTSkipLimitRefresh(final MediaItem firstTrack, @Nullable final ConnectionInfoMetadata response, final Context context) {
        if (!(firstTrack instanceof PlaybackActionCapabilityQueryable)) {
            Log.error(TAG, "First track in the cloud queue is not of type PlaybackActionCapabilityQueryable.");
            notifyIoTError();
            return;
        }
        PlaybackActionCapability playbackActionCapability = ((PlaybackActionCapabilityQueryable) firstTrack).getPlaybackActionCapability("NEXT");
        if (playbackActionCapability == null) {
            Log.error(TAG, "First track in the cloud queue has null PlaybackActionCapability.");
            notifyIoTError();
            return;
        }
        String iotThingShadowName = playbackActionCapability.getIotThingShadowName();
        if (TextUtils.isEmpty(iotThingShadowName)) {
            Log.error(TAG, "Received null or empty thing shadow name.");
            notifyIoTError();
        } else {
            SkipLimitControllerProvider skipLimitControllerProvider = SkipLimitControllerProvider.INSTANCE;
            skipLimitControllerProvider.getController().onReceivedIotConfig(response);
            skipLimitControllerProvider.getController().onReceivedThingShadowName(iotThingShadowName);
            skipLimitControllerProvider.getController().onQueueStarted(context);
        }
    }

    private void notifyIoTError() {
        SkipLimitControllerProvider.INSTANCE.getController().onRefreshError(ErrorType.IoT, this.context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetSequencerInfoWithMediaItemResults(final List<MediaItem> fetchedItems, final AtomicReference<PlaybackException> exception) {
        if (!fetchedItems.isEmpty()) {
            getMetricsTimer("TIME_SINCE_READY").start();
        }
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.mp3.cloudqueue.sequencer.FreeTierSequencer.2
            @Override // java.lang.Runnable
            public void run() {
                FreeTierSequencer.this.fetchingUpcoming = false;
                IndexedSequencerBase.SequencerInfo sequencerInfo = (IndexedSequencerBase.SequencerInfo) FreeTierSequencer.this.copySequencerInfo();
                if (fetchedItems.size() > 0) {
                    sequencerInfo.getQueue().addAll(fetchedItems);
                    sequencerInfo.setCanLoadMore(true);
                    FreeTierSequencer.this.setSequencerInfo(sequencerInfo);
                } else {
                    PlaybackException playbackException = (PlaybackException) exception.get();
                    if (playbackException == null) {
                        playbackException = FreeTierSequencer.this.getPlaybackException("no tracks fetched", "Station-NoTracksFetched");
                    }
                    FreeTierSequencer.this.notifyErrorFetchingMediaItems(playbackException);
                    if (sequencerInfo.canLoadMore() != playbackException.canAutoRetry()) {
                        sequencerInfo.setCanLoadMore(playbackException.canAutoRetry());
                        FreeTierSequencer.this.setSequencerInfo(sequencerInfo);
                    }
                }
                FreeTierSequencer.this.applyCapabilities();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<SynchronizedPlayableEntity> getTrackObservable() {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.cloudqueue.sequencer.FreeTierSequencer$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$getTrackObservable$0((Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getTrackObservable$0(Subscriber subscriber) {
        try {
            subscriber.onNext(this.cloudQueue.nextTrack());
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<ConnectionInfoMetadata> getConnectionInfo() {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.cloudqueue.sequencer.FreeTierSequencer$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$getConnectionInfo$1((Subscriber) obj);
            }
        }).onErrorResumeNext(new Func1() { // from class: com.amazon.mp3.cloudqueue.sequencer.FreeTierSequencer$$ExternalSyntheticLambda2
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return FreeTierSequencer.lambda$getConnectionInfo$2((Throwable) obj);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getConnectionInfo$1(Subscriber subscriber) {
        try {
            subscriber.onNext(this.cloudQueue.getConnectionInfo());
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Observable lambda$getConnectionInfo$2(Throwable th) {
        return Observable.just(null);
    }

    @Override // com.amazon.music.media.playback.sequencer.IndexedSequencerBase, com.amazon.music.media.playback.sequencer.Sequencer
    public ActionAvailability getSkipNextAvailability() {
        EntityItem currentMediaItem = getCurrentMediaItem();
        if (currentMediaItem instanceof PlaybackActionCapabilityQueryable) {
            int actionStatus = ((PlaybackActionCapabilityQueryable) currentMediaItem).getPlaybackActionCapability("NEXT").getActionStatus();
            if (actionStatus == 1) {
                if ((AmazonApplication.getCapabilities().isFreeTierSkipLimitEducationalTooltipEnabled() || AmazonApplication.getCapabilities().isFreeTierSkipsRemainingTooltipEnabled()) && (currentMediaItem instanceof AdMediaItem)) {
                    if (this.skipLimitProvider.getCount() <= 0) {
                        return ActionAvailability.UNAVAILABLE_SKIP_LIMIT;
                    }
                    return ActionAvailability.UNAVAILABLE;
                }
                return ActionAvailability.UNAVAILABLE;
            }
            if (actionStatus == 2) {
                if (this.skipLimitProvider.getCount() <= 0) {
                    return ActionAvailability.UNAVAILABLE_SKIP_LIMIT;
                }
                return super.getSkipNextAvailability();
            }
        }
        return ActionAvailability.UNAVAILABLE;
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isFetchingUpcomingMediaItems() {
        return this.fetchingUpcoming;
    }

    @Override // com.amazon.music.media.playback.sequencer.IndexedSequencerBase, com.amazon.music.media.playback.sequencer.Sequencer
    public void setCurrentIndex(int index, ChangeReason changeReason) {
        this.cloudQueue.setChangeReason(changeReason);
        super.setCurrentIndex(index, changeReason);
        applyCapabilities();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @MainThread
    public void applyCapabilities() {
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.mp3.cloudqueue.sequencer.FreeTierSequencer.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                MediaItem currentMediaItem = FreeTierSequencer.this.getCurrentMediaItem();
                if (FreeTierSequencer.this.lastItemCapabilitiesApplied == currentMediaItem) {
                    return;
                }
                Log.debug(FreeTierSequencer.TAG, "applying capability rules for: " + currentMediaItem);
                if (currentMediaItem instanceof PlaybackActionCapabilityQueryable) {
                    PlaybackActionCapabilityQueryable playbackActionCapabilityQueryable = (PlaybackActionCapabilityQueryable) currentMediaItem;
                    boolean z = playbackActionCapabilityQueryable.getPlaybackActionCapability("SHUFFLE").getActionStatus() == 0;
                    boolean z2 = playbackActionCapabilityQueryable.getPlaybackActionCapability("PREVIOUS").getActionStatus() == 0;
                    boolean z3 = FreeTierSequencer.this.getSkipNextAvailability() == ActionAvailability.AVAILABLE;
                    IndexedSequencerBase.SequencerInfo sequencerInfo = (IndexedSequencerBase.SequencerInfo) FreeTierSequencer.this.copySequencerInfo();
                    sequencerInfo.setCanSkipNext(z3);
                    sequencerInfo.setCanSkipPrevious(z2);
                    sequencerInfo.setCanShuffle(z);
                    EnumSet<RepeatMode> repeatModes = FreeTierSequencer.this.getRepeatModes(playbackActionCapabilityQueryable);
                    sequencerInfo.setRepeatModes(repeatModes);
                    Log.debug(FreeTierSequencer.TAG, "canshuffle: " + z + ", canSkipPrev: " + z2 + ", canskipNext: " + z3 + "repeatModes: " + repeatModes);
                    FreeTierSequencer.this.setSequencerInfo(sequencerInfo);
                    FreeTierSequencer.this.lastItemCapabilitiesApplied = currentMediaItem;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EnumSet<RepeatMode> getRepeatModes(final PlaybackActionCapabilityQueryable mediaItem) {
        boolean z = mediaItem.getPlaybackActionCapability("LOOP").getActionStatus() == 0;
        boolean z2 = mediaItem.getPlaybackActionCapability("LOOP_ONE").getActionStatus() == 0;
        EnumSet<RepeatMode> enumSetOf = EnumSet.of(RepeatMode.REPEAT_NONE);
        if (!z && !z2) {
            return enumSetOf;
        }
        if (z) {
            enumSetOf.add(RepeatMode.REPEAT_ALL);
        }
        if (z2) {
            enumSetOf.add(RepeatMode.REPEAT_ONE);
        }
        return enumSetOf;
    }

    @Override // com.amazon.music.media.playback.sequencer.IndexedSequencerBase, com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canSkipPrevious() {
        EntityItem currentMediaItem = getCurrentMediaItem();
        if (currentMediaItem instanceof PlaybackActionCapabilityQueryable) {
            return (((PlaybackActionCapabilityQueryable) currentMediaItem).getPlaybackActionCapability("PREVIOUS").getActionStatus() == 0) && super.canSkipPrevious();
        }
        return false;
    }

    public boolean isStationPlayback() {
        return this.isStationPlayback;
    }

    public List<PlayableEntityIdentifier> getQueuePlayableEntityIdentifierList() {
        return this.cloudQueue.getPlayableEntityIdentifierList();
    }

    public int getSkipsRemaining() {
        return this.skipLimitProvider.getCount();
    }
}
