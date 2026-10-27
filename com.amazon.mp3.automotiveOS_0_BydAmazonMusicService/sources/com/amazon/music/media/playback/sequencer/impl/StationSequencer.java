package com.amazon.music.media.playback.sequencer.impl;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaLinkProvider;
import com.amazon.music.media.playback.NoPlayableContentException;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.sequencer.IndexedSequencer;
import com.amazon.music.media.playback.sequencer.IndexedSequencerBase;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.sequencer.SequencerListener;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.station.NoNextTrackException;
import com.amazon.music.station.RateTrackManager;
import com.amazon.music.station.Station;
import com.amazon.music.station.StationItem;
import com.amazon.music.station.StationService;
import com.amazon.music.station.SynchronizedTrackItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class StationSequencer extends IndexedSequencer {
    private static final Logger logger = LoggerFactory.getLogger(StationSequencer.class.getSimpleName());
    private final String IMAGE_URL_KEY;
    private final String STATION_FROM_ANYTHING_EMPTY_SEED_BASE_URL;
    private boolean fetchingUpcoming;
    private final MediaItem.ImageUriProvider imageUriProvider;
    private final boolean isVoiceInitiated;
    private final AtomicInteger mCurrentRetryCountForProcessingError;
    private final MediaLinkProvider mediaLinkProvider;
    private final PlaybackPageType playbackPageType;
    private final RateTrackManager rateTrackManager;
    private String remoteTargetId;
    private SequencerListener sequencerListener;
    private boolean startedTimer;
    private final Station station;
    private String stationFromAnythingSeedID;
    private String stationId;
    private final StationItem stationItem;

    public StationSequencer(@NonNull StationItem stationItem, @NonNull Station station, @NonNull StationService stationService, @Nullable MediaLinkProvider mediaLinkProvider, @Nullable MediaItem.ImageUriProvider imageUriProvider, @NonNull MediaCollectionType mediaCollectionType, @NonNull PlaybackPageType playbackPageType, boolean z) {
        super(true);
        this.fetchingUpcoming = false;
        this.STATION_FROM_ANYTHING_EMPTY_SEED_BASE_URL = "content://com.amazon.mp3.Media/library/cirrus/stations/seed/";
        this.IMAGE_URL_KEY = "FULL";
        this.mCurrentRetryCountForProcessingError = new AtomicInteger(0);
        this.sequencerListener = new SequencerListener() { // from class: com.amazon.music.media.playback.sequencer.impl.StationSequencer.1
            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMetadataChanged(MediaItem mediaItem) {
                StationSequencer.logger.debug("onMetadataChanged(" + mediaItem + ")");
            }

            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMediaCollectionInfoChanged(MediaCollectionInfo mediaCollectionInfo) {
                StationSequencer.logger.debug("onMediaCollectionInfoChanged(" + mediaCollectionInfo + ")");
            }

            @Override // com.amazon.music.media.playback.sequencer.SequencerListener
            public void onErrorFetchingMediaItems(Sequencer sequencer, PlaybackException playbackException) {
                StationSequencer.logger.error("error fetching media items", (Throwable) playbackException);
            }

            @Override // com.amazon.music.media.playback.sequencer.SequencerListener
            public void onSequencerInfoChanged(Sequencer sequencer) {
                StationSequencer.logger.debug("sequencer info changed");
            }
        };
        this.stationItem = (StationItem) Validate.notNull(stationItem);
        this.station = station;
        this.rateTrackManager = new RateTrackManager(stationService);
        this.mediaLinkProvider = mediaLinkProvider;
        this.imageUriProvider = imageUriProvider;
        this.playbackPageType = (PlaybackPageType) Validate.notNull(playbackPageType);
        this.isVoiceInitiated = z;
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        MediaCollectionInfo mediaCollectionInfo = new MediaCollectionInfo(stationItem.getTitle(), null, new MediaCollectionId(MediaCollectionId.Type.STATION_KEY, stationItem.getKey()), mediaCollectionType, MediaAccessInfo.forEligibility(false, true, false, true, false, stationItem.isPrime().booleanValue(), false, stationItem.isMusicSubscription().booleanValue(), false), new MediaCollectionId[0]);
        mediaCollectionInfo.addId(getPlaybackSessionId());
        sequencerInfoCopySequencerInfo.setMediaCollectionInfo(mediaCollectionInfo);
        sequencerInfoCopySequencerInfo.setCanShuffle(false);
        sequencerInfoCopySequencerInfo.setCanLoadMore(true);
        sequencerInfoCopySequencerInfo.setRepeatModes(RepeatMode.REPEAT_NONE_ONLY);
        setSequencerInfo(sequencerInfoCopySequencerInfo);
        addSequencerListener(this.sequencerListener);
        fetchUpcomingMediaItems(1);
    }

    public StationItem getStationItem() {
        return this.stationItem;
    }

    public String getRemoteTargetId() {
        return this.remoteTargetId;
    }

    public void setRemoteTargetId(String str) {
        this.remoteTargetId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaCollectionInfo getTitleAndImageUpdatedMediaCollectionInfo(IndexedSequencerBase.SequencerInfo sequencerInfo, MediaCollectionInfo mediaCollectionInfo) {
        String str;
        MediaCollectionInfo mediaCollectionInfo2 = new MediaCollectionInfo(StringUtils.isEmpty(this.stationItem.getTitle()) ? this.station.getQueueMetadata().getTitle() : this.stationItem.getTitle(), mediaCollectionInfo.getSubtitle(), mediaCollectionInfo.getId(), mediaCollectionInfo.getType(), mediaCollectionInfo.getMediaAccessInfo(), new MediaCollectionId[0]);
        Iterator<MediaCollectionId> it = mediaCollectionInfo.getIds().values().iterator();
        while (it.hasNext()) {
            mediaCollectionInfo2.addId(it.next());
        }
        if (StringUtils.isEmpty(this.stationItem.getImageUrl()) && this.station.getQueueMetadata().getImageUrlMap() != null && (str = this.station.getQueueMetadata().getImageUrlMap().get("FULL")) != null) {
            sequencerInfo.setImageUri(Uri.parse(str));
            mediaCollectionInfo2.addId(new MediaCollectionId(MediaCollectionId.Type.CLOUD_QUEUE_IMAGE_URL, str));
        }
        return mediaCollectionInfo2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMediaCollectionForSFA(MediaCollectionInfo mediaCollectionInfo) {
        mediaCollectionInfo.addId(new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, this.stationFromAnythingSeedID));
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public void fetchUpcomingMediaItems(final int i) {
        if (this.fetchingUpcoming) {
            return;
        }
        this.fetchingUpcoming = true;
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.music.media.playback.sequencer.impl.StationSequencer.2
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList(i);
                AtomicReference atomicReference = new AtomicReference();
                for (int i2 = 0; i2 < i; i2++) {
                    try {
                        try {
                            try {
                                if (!StationSequencer.this.startedTimer) {
                                    StationSequencer.this.startedTimer = true;
                                    StationSequencer.this.getMetricsTimer("EXT_LOAD_TIME").start();
                                }
                                SynchronizedTrackItem synchronizedTrackItemNextTrack = StationSequencer.this.station.nextTrack();
                                if (StationSequencer.this.station.getQueueMetadata() != null) {
                                    IndexedSequencerBase.SequencerInfo sequencerInfo = (IndexedSequencerBase.SequencerInfo) StationSequencer.this.copySequencerInfo();
                                    MediaCollectionInfo titleAndImageUpdatedMediaCollectionInfo = StationSequencer.this.getTitleAndImageUpdatedMediaCollectionInfo(sequencerInfo, sequencerInfo.getMediaCollectionInfo());
                                    if (StringUtils.isEmpty(StationSequencer.this.stationItem.getKey()) && StationSequencer.this.stationFromAnythingSeedID == null) {
                                        StationSequencer stationSequencer = StationSequencer.this;
                                        stationSequencer.stationFromAnythingSeedID = stationSequencer.station.getStationFromAnythingSeedID();
                                        if (StationSequencer.this.stationFromAnythingSeedID != null) {
                                            StationSequencer.this.updateMediaCollectionForSFA(titleAndImageUpdatedMediaCollectionInfo);
                                        }
                                    }
                                    sequencerInfo.setMediaCollectionInfo(titleAndImageUpdatedMediaCollectionInfo);
                                    StationSequencer.this.setSequencerInfo(sequencerInfo);
                                }
                                StationSequencer.this.stationId = synchronizedTrackItemNextTrack.getStationId();
                                StationSequencer.this.getMetricsTimer("EXT_LOAD_TIME").stop();
                                StationMediaItem stationMediaItem = new StationMediaItem(synchronizedTrackItemNextTrack, StationSequencer.this.stationItem, StationSequencer.this.rateTrackManager, StationSequencer.this.getMediaCollectionInfo(), StationSequencer.this.getRatingsProvider(), StationSequencer.this.mediaLinkProvider, StationSequencer.this.imageUriProvider, StationSequencer.this.playbackPageType);
                                stationMediaItem.setVoiceInitiated(StationSequencer.this.isVoiceInitiated);
                                if (!stationMediaItem.isInterlude()) {
                                    stationMediaItem.fetchAndInitializeLikeState();
                                    stationMediaItem.setCanLike(true);
                                }
                                arrayList.add(stationMediaItem);
                            } catch (NoNextTrackException e) {
                                atomicReference.set(StationSequencer.this.getPlaybackException(e, "unable to fetch the next track"));
                            }
                        } catch (InterruptedException e2) {
                            atomicReference.set(StationSequencer.this.getPlaybackException(e2, "interrupted while fetching the next track", "Station-NextTrackInterrupted"));
                        }
                    } finally {
                        StationSequencer.this.resetSequencerInfoWithMediaItemResults(arrayList, atomicReference);
                    }
                }
            }
        });
    }

    public boolean isStationAvailableBlocking() {
        try {
            this.station.peekTrack();
            return this.station.getStationFromAnythingSeedID() != null;
        } catch (NoNextTrackException | InterruptedException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetSequencerInfoWithMediaItemResults(final List<MediaItem> list, final AtomicReference<PlaybackException> atomicReference) {
        if (!list.isEmpty()) {
            getMetricsTimer("TIME_SINCE_READY").start();
        }
        ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.sequencer.impl.StationSequencer.3
            @Override // java.lang.Runnable
            public void run() {
                StationSequencer.this.fetchingUpcoming = false;
                IndexedSequencerBase.SequencerInfo sequencerInfo = (IndexedSequencerBase.SequencerInfo) StationSequencer.this.copySequencerInfo();
                MediaCollectionInfo mediaCollectionInfo = sequencerInfo.getMediaCollectionInfo();
                MediaCollectionId.Type type = MediaCollectionId.Type.STATION_INSTANCE_ID;
                if (mediaCollectionInfo.getId(type) == null && !StringUtils.isEmpty(StationSequencer.this.stationId)) {
                    mediaCollectionInfo.addId(new MediaCollectionId(type, StationSequencer.this.stationId));
                }
                MediaCollectionId.Type type2 = MediaCollectionId.Type.STATION_URI;
                if (mediaCollectionInfo.getId(type2) == null && !StringUtils.isEmpty(StationSequencer.this.stationFromAnythingSeedID)) {
                    mediaCollectionInfo.addId(new MediaCollectionId(type2, "content://com.amazon.mp3.Media/library/cirrus/stations/seed/" + StationSequencer.this.stationFromAnythingSeedID));
                }
                if (list.size() > 0) {
                    sequencerInfo.getQueue().addAll(list);
                    sequencerInfo.setCanLoadMore(true);
                    StationSequencer.this.setSequencerInfo(sequencerInfo);
                    StationSequencer.this.mCurrentRetryCountForProcessingError.set(0);
                    return;
                }
                PlaybackException playbackException = (PlaybackException) atomicReference.get();
                if (playbackException == null) {
                    playbackException = StationSequencer.this.getPlaybackException("no tracks fetched", "Station-NoTracksFetched");
                }
                if (playbackException.getPlaybackError() != PlaybackException.PlaybackError.StationEnd) {
                    StationSequencer.this.notifyErrorFetchingMediaItems(playbackException);
                }
                if (sequencerInfo.canLoadMore() != playbackException.canAutoRetry()) {
                    sequencerInfo.setCanLoadMore(playbackException.canAutoRetry());
                    StationSequencer.this.setSequencerInfo(sequencerInfo);
                }
            }
        });
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isFetchingUpcomingMediaItems() {
        return this.fetchingUpcoming;
    }

    protected PlaybackException getPlaybackException(String str, String str2) {
        PlaybackException.Config basePlaybackExceptionConfig = getBasePlaybackExceptionConfig(null, str, str2);
        basePlaybackExceptionConfig.autoRetry(true);
        return new PlaybackException(basePlaybackExceptionConfig);
    }

    protected PlaybackException getPlaybackException(InterruptedException interruptedException, String str, String str2) {
        PlaybackException.Config basePlaybackExceptionConfig = getBasePlaybackExceptionConfig(interruptedException, str, str2);
        basePlaybackExceptionConfig.autoRetry(true);
        return new PlaybackException(basePlaybackExceptionConfig);
    }

    protected PlaybackException getPlaybackException(NoNextTrackException noNextTrackException, String str) {
        PlaybackException.PlaybackError playbackError;
        PlaybackException.Config basePlaybackExceptionConfig = getBasePlaybackExceptionConfig(noNextTrackException, str, null);
        int i = AnonymousClass4.$SwitchMap$com$amazon$music$station$NoNextTrackException$Reason[noNextTrackException.getReason().ordinal()];
        if (i == 1) {
            return new NoPlayableContentException(null);
        }
        if (i == 2) {
            playbackError = PlaybackException.PlaybackError.StationNotFound;
        } else if (i == 3) {
            playbackError = PlaybackException.PlaybackError.StationOutOfTracks;
        } else if (i == 4) {
            playbackError = PlaybackException.PlaybackError.StationEnd;
        } else {
            if (i == 5) {
                playbackError = PlaybackException.PlaybackError.NetworkFailure;
            } else {
                if (noNextTrackException.getReason() != NoNextTrackException.Reason.PROCESSING_ERROR) {
                    logger.warn("getPlaybackException unexpected reason=" + noNextTrackException.getReason());
                }
                z = this.mCurrentRetryCountForProcessingError.getAndIncrement() < 5;
                if (getQueueEnd() <= 0) {
                    playbackError = PlaybackException.PlaybackError.StationCannotStart;
                } else {
                    playbackError = PlaybackException.PlaybackError.StationCannotContinue;
                }
            }
            basePlaybackExceptionConfig.autoRetry(z);
            basePlaybackExceptionConfig.playbackError(playbackError);
            return new PlaybackException(basePlaybackExceptionConfig);
        }
        z = false;
        basePlaybackExceptionConfig.autoRetry(z);
        basePlaybackExceptionConfig.playbackError(playbackError);
        return new PlaybackException(basePlaybackExceptionConfig);
    }

    /* JADX INFO: renamed from: com.amazon.music.media.playback.sequencer.impl.StationSequencer$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$station$NoNextTrackException$Reason;

        static {
            int[] iArr = new int[NoNextTrackException.Reason.values().length];
            $SwitchMap$com$amazon$music$station$NoNextTrackException$Reason = iArr;
            try {
                iArr[NoNextTrackException.Reason.CLEAN_CONTENT_UNAVAILABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$station$NoNextTrackException$Reason[NoNextTrackException.Reason.STATION_NOT_FOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$station$NoNextTrackException$Reason[NoNextTrackException.Reason.STATION_OUT_OF_TRACKS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$station$NoNextTrackException$Reason[NoNextTrackException.Reason.END_OF_STATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$station$NoNextTrackException$Reason[NoNextTrackException.Reason.NETWORK_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$station$NoNextTrackException$Reason[NoNextTrackException.Reason.PROCESSING_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    protected PlaybackException.Config getBasePlaybackExceptionConfig(Exception exc, String str, String str2) {
        PlaybackException.Config config = new PlaybackException.Config(PlaybackException.Recoverability.COLLECTION);
        config.source(exc);
        config.description(str);
        config.metricsName(str2);
        return config;
    }
}
