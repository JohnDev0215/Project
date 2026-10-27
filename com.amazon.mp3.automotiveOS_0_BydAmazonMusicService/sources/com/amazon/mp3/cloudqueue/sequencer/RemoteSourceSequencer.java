package com.amazon.mp3.cloudqueue.sequencer;

import com.amazon.mp3.cloudqueue.CloudQueueNoNextException;
import com.amazon.mp3.cloudqueue.model.MetricsContext;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.NoPlayableContentException;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.sequencer.IndexedSequencer;
import com.amazon.music.media.playback.sequencer.IndexedSequencerBase;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.google.android.gms.cast.MediaTrack;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RemoteSourceSequencer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\rH&J,\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0010\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u0017\u001a\u00020\u0016H&J\u0016\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0019H&J\u0012\u0010\u001a\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0004J\n\u0010\u001d\u001a\u0004\u0018\u00010\u001cH&J\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0010\u001a\u00020 2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006H\u0014J.\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u000e\u0010\u0010\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006H\u0014J\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010!\u001a\u00020\u0006H&J\b\u0010\"\u001a\u00020\u0003H&J\b\u0010#\u001a\u00020\tH\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/mp3/cloudqueue/sequencer/RemoteSourceSequencer;", "Lcom/amazon/music/media/playback/sequencer/IndexedSequencer;", "canRate", "", "(Z)V", "IMAGE_URL_KEY", "", "hasUpdatedMediaCollectionInfo", "addMediaCollectionImageUrl", "", "mediaCollectionInfo", "Lcom/amazon/music/media/playback/MediaCollectionInfo;", "constructMediaAccessInfo", "Lcom/amazon/music/media/playback/access/MediaAccessInfo;", "getBasePlaybackExceptionConfig", "Lcom/amazon/music/media/playback/PlaybackException$Config;", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", MediaTrack.ROLE_DESCRIPTION, "metricsName", "getCollectionImageUrlIdType", "Lcom/amazon/music/media/playback/MediaCollectionId$Type;", "getCollectionPrimaryIdType", "getImageUrlMap", "", "getMediaCollectionPrimaryId", "metricsContext", "Lcom/amazon/mp3/cloudqueue/model/MetricsContext;", "getMetricsContext", "getPlaybackException", "Lcom/amazon/music/media/playback/PlaybackException;", "Lcom/amazon/mp3/cloudqueue/CloudQueueNoNextException;", "getQueueTitle", "hasInitTrackLoaded", "updateMediaCollectionInfo", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class RemoteSourceSequencer extends IndexedSequencer {
    private final String IMAGE_URL_KEY;
    private boolean hasUpdatedMediaCollectionInfo;

    /* JADX INFO: compiled from: RemoteSourceSequencer.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[SelectionSourceType.values().length];
            iArr[SelectionSourceType.GOLDEN_PLAYLIST.ordinal()] = 1;
            iArr[SelectionSourceType.AMF_STATION_SEED.ordinal()] = 2;
            iArr[SelectionSourceType.PRIME_STATION_SEED.ordinal()] = 3;
            iArr[SelectionSourceType.UNLIMITED_STATION_SEED.ordinal()] = 4;
            iArr[SelectionSourceType.AMF_STATION.ordinal()] = 5;
            iArr[SelectionSourceType.PRIME_STATION.ordinal()] = 6;
            iArr[SelectionSourceType.UNLIMITED_STATION.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[CloudQueueNoNextException.Reason.values().length];
            iArr2[CloudQueueNoNextException.Reason.INVALID_STATION_SEED.ordinal()] = 1;
            iArr2[CloudQueueNoNextException.Reason.DEVICE_OFFLINE.ordinal()] = 2;
            iArr2[CloudQueueNoNextException.Reason.CONCURRENT_STREAM_LIMIT.ordinal()] = 3;
            iArr2[CloudQueueNoNextException.Reason.THROTTLING_LIMIT_REACHED.ordinal()] = 4;
            iArr2[CloudQueueNoNextException.Reason.UNKNOWN_FAILURE.ordinal()] = 5;
            iArr2[CloudQueueNoNextException.Reason.QUEUE_TERMINATED.ordinal()] = 6;
            iArr2[CloudQueueNoNextException.Reason.CLEAN_CONTENT_UNAVAILABLE.ordinal()] = 7;
            iArr2[CloudQueueNoNextException.Reason.NETWORK_ERROR.ordinal()] = 8;
            iArr2[CloudQueueNoNextException.Reason.REACH_END_OF_QUEUE.ordinal()] = 9;
            iArr2[CloudQueueNoNextException.Reason.PROCESSING_ERROR.ordinal()] = 10;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public abstract MediaAccessInfo constructMediaAccessInfo();

    public abstract MediaCollectionId.Type getCollectionImageUrlIdType();

    public abstract MediaCollectionId.Type getCollectionPrimaryIdType();

    public abstract Map<String, String> getImageUrlMap();

    /* JADX INFO: renamed from: getMetricsContext */
    public abstract MetricsContext getCollectionMetricsContext();

    public abstract String getQueueTitle();

    public abstract boolean hasInitTrackLoaded();

    public RemoteSourceSequencer(boolean z) {
        super(z);
        this.IMAGE_URL_KEY = "FULL";
    }

    protected final String getMediaCollectionPrimaryId(MetricsContext metricsContext) {
        if (metricsContext == null) {
            return "";
        }
        SelectionSourceType selectionSourceType = metricsContext.getSelectionSourceType();
        switch (selectionSourceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[selectionSourceType.ordinal()]) {
            case 1:
                String string = MediaProvider.PrimePlaylists.getContentUri("cirrus", metricsContext.getSelectionSourceId()).toString();
                Intrinsics.checkNotNullExpressionValue(string, "getContentUri(CirrusMedi…_CIRRUS, asin).toString()");
                return string;
            case 2:
            case 3:
            case 4:
                String string2 = MediaProvider.Station.getContentUriWithSeeds(metricsContext.getSelectionSourceId()).toString();
                Intrinsics.checkNotNullExpressionValue(string2, "getContentUriWithSeeds(seedID).toString()");
                return string2;
            case 5:
            case 6:
            case 7:
                String string3 = MediaProvider.Station.getContentUri(metricsContext.getSelectionSourceId()).toString();
                Intrinsics.checkNotNullExpressionValue(string3, "getContentUri(stationKey).toString()");
                return string3;
            default:
                return "";
        }
    }

    protected final void updateMediaCollectionInfo() {
        MetricsContext collectionMetricsContext = getCollectionMetricsContext();
        if (collectionMetricsContext == null) {
            return;
        }
        SelectionSourceType selectionSourceType = collectionMetricsContext.getSelectionSourceType();
        MediaCollectionType collectionType = selectionSourceType == null ? null : MediaCollectionType.getCollectionType(selectionSourceType.getMetricsValue());
        if (hasInitTrackLoaded()) {
            IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
            MediaCollectionInfo mediaCollectionInfo = new MediaCollectionInfo(getQueueTitle(), sequencerInfoCopySequencerInfo.getMediaCollectionInfo().getSubtitle(), new MediaCollectionId(getCollectionPrimaryIdType(), getMediaCollectionPrimaryId(collectionMetricsContext)), collectionType, constructMediaAccessInfo(), new MediaCollectionId[0]);
            addMediaCollectionImageUrl(mediaCollectionInfo);
            if (collectionMetricsContext.getSelectionSourceId() != null) {
                mediaCollectionInfo.addId(new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, collectionMetricsContext.getSelectionSourceId()));
            }
            mediaCollectionInfo.addId(getPlaybackSessionId());
            sequencerInfoCopySequencerInfo.setMediaCollectionInfo(mediaCollectionInfo);
            setSequencerInfo(sequencerInfoCopySequencerInfo);
            return;
        }
        this.hasUpdatedMediaCollectionInfo = false;
    }

    private final void addMediaCollectionImageUrl(MediaCollectionInfo mediaCollectionInfo) {
        if (getImageUrlMap() == null) {
            return;
        }
        Map<String, String> imageUrlMap = getImageUrlMap();
        String str = imageUrlMap == null ? null : imageUrlMap.get(this.IMAGE_URL_KEY);
        if (str == null) {
            return;
        }
        mediaCollectionInfo.addId(new MediaCollectionId(getCollectionImageUrlIdType(), str));
    }

    protected PlaybackException getPlaybackException(String description, String metricsName) {
        PlaybackException.Config basePlaybackExceptionConfig = getBasePlaybackExceptionConfig(null, description, metricsName);
        basePlaybackExceptionConfig.autoRetry(true);
        return new PlaybackException(basePlaybackExceptionConfig);
    }

    protected PlaybackException getPlaybackException(Exception e, String description, String metricsName) {
        PlaybackException.Config basePlaybackExceptionConfig = getBasePlaybackExceptionConfig(e, description, metricsName);
        basePlaybackExceptionConfig.autoRetry(true);
        return new PlaybackException(basePlaybackExceptionConfig);
    }

    protected PlaybackException getPlaybackException(CloudQueueNoNextException e, String description) {
        PlaybackException.PlaybackError playbackError;
        Intrinsics.checkNotNullParameter(e, "e");
        PlaybackException.Config basePlaybackExceptionConfig = getBasePlaybackExceptionConfig(e, description, null);
        CloudQueueNoNextException.Reason reason = e.getReason();
        boolean z = true;
        switch (reason == null ? -1 : WhenMappings.$EnumSwitchMapping$1[reason.ordinal()]) {
            case 1:
                playbackError = PlaybackException.PlaybackError.InvalidStationSeed;
                z = false;
                basePlaybackExceptionConfig.autoRetry(z);
                basePlaybackExceptionConfig.playbackError(playbackError);
                return new PlaybackException(basePlaybackExceptionConfig);
            case 2:
                playbackError = PlaybackException.PlaybackError.DeviceOffline;
                z = false;
                basePlaybackExceptionConfig.autoRetry(z);
                basePlaybackExceptionConfig.playbackError(playbackError);
                return new PlaybackException(basePlaybackExceptionConfig);
            case 3:
                playbackError = PlaybackException.PlaybackError.ConcurrentStreamLimit;
                z = false;
                basePlaybackExceptionConfig.autoRetry(z);
                basePlaybackExceptionConfig.playbackError(playbackError);
                return new PlaybackException(basePlaybackExceptionConfig);
            case 4:
                playbackError = PlaybackException.PlaybackError.ThrottlingLimitReached;
                z = false;
                basePlaybackExceptionConfig.autoRetry(z);
                basePlaybackExceptionConfig.playbackError(playbackError);
                return new PlaybackException(basePlaybackExceptionConfig);
            case 5:
                playbackError = PlaybackException.PlaybackError.UnknownFailure;
                z = false;
                basePlaybackExceptionConfig.autoRetry(z);
                basePlaybackExceptionConfig.playbackError(playbackError);
                return new PlaybackException(basePlaybackExceptionConfig);
            case 6:
                playbackError = PlaybackException.PlaybackError.QueueTerminated;
                z = false;
                basePlaybackExceptionConfig.autoRetry(z);
                basePlaybackExceptionConfig.playbackError(playbackError);
                return new PlaybackException(basePlaybackExceptionConfig);
            case 7:
                return new NoPlayableContentException(null);
            case 8:
                playbackError = PlaybackException.PlaybackError.NetworkFailure;
                basePlaybackExceptionConfig.autoRetry(z);
                basePlaybackExceptionConfig.playbackError(playbackError);
                return new PlaybackException(basePlaybackExceptionConfig);
            case 9:
                playbackError = PlaybackException.PlaybackError.ReachEndOfQueue;
                z = false;
                basePlaybackExceptionConfig.autoRetry(z);
                basePlaybackExceptionConfig.playbackError(playbackError);
                return new PlaybackException(basePlaybackExceptionConfig);
            case 10:
                playbackError = PlaybackException.PlaybackError.UnknownFailure;
                basePlaybackExceptionConfig.autoRetry(z);
                basePlaybackExceptionConfig.playbackError(playbackError);
                return new PlaybackException(basePlaybackExceptionConfig);
            default:
                playbackError = PlaybackException.PlaybackError.UnknownFailure;
                basePlaybackExceptionConfig.autoRetry(z);
                basePlaybackExceptionConfig.playbackError(playbackError);
                return new PlaybackException(basePlaybackExceptionConfig);
        }
    }

    protected PlaybackException.Config getBasePlaybackExceptionConfig(Exception e, String description, String metricsName) {
        PlaybackException.Config config = new PlaybackException.Config(PlaybackException.Recoverability.COLLECTION);
        config.source(e);
        config.description(description);
        config.metricsName(metricsName);
        return config;
    }
}
