package com.amazon.mp3.cloudqueue.sequencer;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.digitalmusicxp.enums.RatingEnum;
import com.amazon.digitalmusicxp.types.ActionStrategy;
import com.amazon.digitalmusicxp.types.CuePoints;
import com.amazon.digitalmusicxp.types.QueueEntityCapability;
import com.amazon.mp3.cloudqueue.CloudQueueDigitalXPUtil;
import com.amazon.mp3.cloudqueue.QueueEntityCapabilityTranslator;
import com.amazon.mp3.cloudqueue.RateTrackManager;
import com.amazon.mp3.cloudqueue.Rating;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.MediaLinkProvider;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StormTrackMediaItem.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 H2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001HBé\u0001\u0012\b\u0010-\u001a\u0004\u0018\u00010,\u0012\b\u0010/\u001a\u0004\u0018\u00010.\u0012\b\u00101\u001a\u0004\u0018\u000100\u0012\b\u00103\u001a\u0004\u0018\u000102\u0012\u0006\u00104\u001a\u00020\u0004\u0012\u0006\u00105\u001a\u00020\u0004\u0012\b\u00106\u001a\u0004\u0018\u00010\u0004\u0012\b\u00107\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000408\u0012\b\u0010:\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010;\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010<\u001a\u00020\u001c\u0012\u0006\u0010=\u001a\u00020\u000f\u0012\b\u0010>\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010?\u001a\u00020\u0004\u0012\b\u0010A\u001a\u0004\u0018\u00010@\u0012\b\u0010$\u001a\u0004\u0018\u00010#\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\b\u0010B\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010D\u001a\u00020C\u0012\u0006\u0010)\u001a\u00020\u0004\u0012\u0006\u0010E\u001a\u00020\u001f¢\u0006\u0004\bF\u0010GJ\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J\u001a\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\u001a\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\bH\u0016J\u0012\u0010\u0014\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010\u0017\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0007J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0018\u001a\u00020\bH\u0007J\u0012\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0007J\u0018\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016J\b\u0010 \u001a\u00020\u001fH\u0016R\u0018\u0010!\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0018\u0010$\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010(R\u0014\u0010*\u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+¨\u0006I"}, d2 = {"Lcom/amazon/mp3/cloudqueue/sequencer/StormTrackMediaItem;", "Lcom/amazon/music/media/playback/MediaItem;", "Lcom/amazon/mp3/cloudqueue/sequencer/PlaybackControlVisibilityQueryable;", "Lcom/amazon/mp3/cloudqueue/sequencer/QueueEntityCapabilityQueryable;", "", "albumId", "", "addAlbumId", "", "width", "height", "Landroid/net/Uri;", "getMediaItemImageUri", "getArtistImageUri", "getAlbumImageUri", "", "canRate", "canSeek", "getRating", "action", "canDisplay", "Lcom/amazon/digitalmusicxp/enums/RatingEnum;", "originalRating", "fromCloudQueueRating", "rating", "toCloudQueueRating", "Lcom/amazon/mp3/cloudqueue/Rating;", "toServiceRating", "", "position", "setRating", "Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "getQueueEntityCapabilities", "albumArtUri", "Landroid/net/Uri;", "Lcom/amazon/mp3/cloudqueue/RateTrackManager;", "rateTrackManager", "Lcom/amazon/mp3/cloudqueue/RateTrackManager;", "Lcom/amazon/digitalmusicxp/enums/RatingEnum;", "trackId", "Ljava/lang/String;", "queueId", "capability", "Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "Lcom/amazon/music/media/playback/MediaCollectionInfo;", "collectionInfo", "Lcom/amazon/music/media/playback/MediaLinkProvider;", "mediaLinkProvider", "Lcom/amazon/music/media/playback/MediaItem$ImageUriProvider;", "imageUriProvider", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "trackTitle", "albumTitle", "albumAsin", "", "metricsContext", "artistName", "artistAsin", "durationInMilli", BrushV2Converter.KEY_IS_EXPLICIT, "albumArtImageUrl", "trackLuid", "Lcom/amazon/digitalmusicxp/types/CuePoints;", "cuePoints", "trackPid", "Lcom/amazon/music/media/playback/access/MediaAccessInfo;", "mediaAccessInfo", "queueEntityCapability", "<init>", "(Lcom/amazon/music/media/playback/MediaCollectionInfo;Lcom/amazon/music/media/playback/MediaLinkProvider;Lcom/amazon/music/media/playback/MediaItem$ImageUriProvider;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;JZLjava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/CuePoints;Lcom/amazon/mp3/cloudqueue/RateTrackManager;Lcom/amazon/digitalmusicxp/enums/RatingEnum;Ljava/lang/String;Lcom/amazon/music/media/playback/access/MediaAccessInfo;Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class StormTrackMediaItem extends MediaItem implements PlaybackControlVisibilityQueryable, QueueEntityCapabilityQueryable {
    private static final String TAG = StormTrackMediaItem.class.getSimpleName();
    private Uri albumArtUri;
    private final QueueEntityCapability capability;
    private final String queueId;
    private RateTrackManager rateTrackManager;
    private RatingEnum rating;
    private String trackId;

    /* JADX INFO: compiled from: StormTrackMediaItem.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RatingEnum.values().length];
            iArr[RatingEnum.THUMBS_UP.ordinal()] = 1;
            iArr[RatingEnum.THUMBS_DOWN.ordinal()] = 2;
            iArr[RatingEnum.NEUTRAL.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ StormTrackMediaItem(MediaCollectionInfo mediaCollectionInfo, MediaLinkProvider mediaLinkProvider, MediaItem.ImageUriProvider imageUriProvider, PlaybackPageType playbackPageType, String str, String str2, String str3, String str4, String str5, Map map, String str6, String str7, long j, boolean z, String str8, String str9, CuePoints cuePoints, RateTrackManager rateTrackManager, RatingEnum ratingEnum, String str10, MediaAccessInfo mediaAccessInfo, String str11, QueueEntityCapability queueEntityCapability, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mediaCollectionInfo, mediaLinkProvider, imageUriProvider, playbackPageType, str, str2, str3, str4, (i & 256) != 0 ? "" : str5, map, str6, str7, j, z, str8, str9, cuePoints, rateTrackManager, ratingEnum, str10, mediaAccessInfo, str11, queueEntityCapability);
    }

    public StormTrackMediaItem(MediaCollectionInfo mediaCollectionInfo, MediaLinkProvider mediaLinkProvider, MediaItem.ImageUriProvider imageUriProvider, PlaybackPageType playbackPageType, String trackAsin, String trackTitle, String str, String str2, String str3, Map<String, String> metricsContext, String str4, String str5, long j, boolean z, String str6, String trackLuid, CuePoints cuePoints, RateTrackManager rateTrackManager, RatingEnum ratingEnum, String str7, MediaAccessInfo mediaAccessInfo, String queueId, QueueEntityCapability queueEntityCapability) {
        int i;
        String str8;
        Intrinsics.checkNotNullParameter(trackAsin, "trackAsin");
        Intrinsics.checkNotNullParameter(trackTitle, "trackTitle");
        Intrinsics.checkNotNullParameter(metricsContext, "metricsContext");
        Intrinsics.checkNotNullParameter(trackLuid, "trackLuid");
        Intrinsics.checkNotNullParameter(mediaAccessInfo, "mediaAccessInfo");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(queueEntityCapability, "queueEntityCapability");
        MediaItemId.Type type = MediaItemId.Type.ASIN;
        MediaItemId mediaItemId = new MediaItemId(type, trackAsin);
        MediaItem.Type type2 = MediaItem.Type.PRIME_CONTENT;
        Intrinsics.checkNotNull(playbackPageType);
        super(mediaItemId, trackTitle, j, type2, mediaAccessInfo, null, mediaLinkProvider, imageUriProvider, playbackPageType, false, mediaCollectionInfo, new MediaCollectionInfo[0]);
        this.queueId = queueId;
        this.capability = queueEntityCapability;
        if (StringUtils.isEmpty(str4) || StringUtils.isEmpty(str5)) {
            i = 0;
        } else {
            i = 0;
            addMediaCollectionInfo(new MediaCollectionInfo(str4, null, new MediaCollectionId(MediaCollectionId.Type.ASIN, str5), MediaCollectionType.ARTIST, mediaAccessInfo, new MediaCollectionId[0]));
        }
        if (!StringUtils.isEmpty(str) && !StringUtils.isEmpty(str2)) {
            addMediaCollectionInfo(new MediaCollectionInfo(str, null, new MediaCollectionId(MediaCollectionId.Type.ASIN, str2), MediaCollectionType.ALBUM, mediaAccessInfo, new MediaCollectionId[i]));
        }
        if (!TextUtils.isEmpty(trackAsin)) {
            addMediaItemId(new MediaItemId(type, trackAsin));
        }
        if (TextUtils.isEmpty(trackLuid)) {
            str8 = trackLuid;
        } else {
            str8 = trackLuid;
            addMediaItemId(new MediaItemId(MediaItemId.Type.LUID, str8));
        }
        if (str7 != null && !TextUtils.isEmpty(str7)) {
            addMediaItemId(new MediaItemId(MediaItemId.Type.PID, str7));
        }
        if (rateTrackManager != null && cuePoints != null) {
            setOffSetPlaybackCuePoints(new com.amazon.music.media.playback.CuePoints(Integer.valueOf(cuePoints.getStartOffsetInMs()), Integer.valueOf(cuePoints.getEndOffsetInMs())));
        }
        addAlbumId(str3);
        if (str6 != null) {
            this.albumArtUri = Uri.parse(str6);
        }
        this.rateTrackManager = rateTrackManager;
        this.rating = ratingEnum;
        this.trackId = str8;
        setMetricsContext(metricsContext);
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public Uri getMediaItemImageUri(int width, int height) {
        return this.albumArtUri;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public Uri getArtistImageUri(int width, int height) {
        return this.albumArtUri;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public Uri getAlbumImageUri(int width, int height) {
        return this.albumArtUri;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean canRate() {
        return CloudQueueDigitalXPUtil.canPerformActionStrategy$default(this.capability.getRate(), null, 2, null);
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean canSeek() {
        return CloudQueueDigitalXPUtil.canPerformActionStrategy$default(this.capability.getScrubForward(), null, 2, null) && CloudQueueDigitalXPUtil.canPerformActionStrategy$default(this.capability.getScrubBackward(), null, 2, null);
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public int getRating() {
        return fromCloudQueueRating(this.rating);
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.PlaybackControlVisibilityQueryable
    public boolean canDisplay(String action) {
        ActionStrategy displayable;
        if (Intrinsics.areEqual(action, "PREVIOUS")) {
            displayable = this.capability.getPrevious();
        } else if (Intrinsics.areEqual(action, "NEXT")) {
            displayable = this.capability.getNext();
        } else if (Intrinsics.areEqual(action, "SCRUB_BACKWARD")) {
            displayable = this.capability.getScrubBackward();
        } else if (Intrinsics.areEqual(action, "SCRUB_FORWARD")) {
            displayable = this.capability.getScrubForward();
        } else if (Intrinsics.areEqual(action, "RATE_TRACK")) {
            displayable = this.capability.getRate();
        } else if (Intrinsics.areEqual(action, "LOOP_ONE")) {
            displayable = this.capability.getLoopOne();
        } else {
            if (Intrinsics.areEqual(action, "SHUFFLE") ? true : Intrinsics.areEqual(action, "LOOP") ? true : Intrinsics.areEqual(action, "CASTING")) {
                Log.warning(TAG, "displayable state should get from " + ((Object) action) + " at queue level, fallback to capability.displayable");
                displayable = this.capability.getDisplayable();
            } else if (Intrinsics.areEqual(action, QueueEntityCapabilityTranslator.QueueEntityCapability.DISPLAYABLE.name())) {
                displayable = this.capability.getDisplayable();
            } else {
                Log.warning(TAG, "unhandled " + ((Object) action) + ", set canDisplay to false");
                return false;
            }
        }
        if (!Intrinsics.areEqual(displayable, this.capability.getDisplayable())) {
            Log.error(TAG, "FIXME: " + ((Object) action) + " canDisplay is computed wrongly, temporarily use displayable=" + this.capability.getDisplayable() + ", it should be " + displayable);
        }
        return CloudQueueDigitalXPUtil.canPerformActionStrategy$default(this.capability.getDisplayable(), null, 2, null);
    }

    @VisibleForTesting
    public final int fromCloudQueueRating(RatingEnum originalRating) {
        int i = originalRating == null ? -1 : WhenMappings.$EnumSwitchMapping$0[originalRating.ordinal()];
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return i != 3 ? -1 : 0;
        }
        return 2;
    }

    @VisibleForTesting
    public final RatingEnum toCloudQueueRating(int rating) {
        if (rating == 0) {
            return RatingEnum.NEUTRAL;
        }
        if (rating == 1) {
            return RatingEnum.THUMBS_UP;
        }
        if (rating == 2) {
            return RatingEnum.THUMBS_DOWN;
        }
        return RatingEnum.NEUTRAL;
    }

    @VisibleForTesting
    public final Rating toServiceRating(RatingEnum originalRating) {
        int i = originalRating == null ? -1 : WhenMappings.$EnumSwitchMapping$0[originalRating.ordinal()];
        if (i == 1) {
            return Rating.THUMBS_UP;
        }
        if (i == 2) {
            return Rating.THUMBS_DOWN;
        }
        if (i == 3) {
            return Rating.NEUTRAL;
        }
        return Rating.NEUTRAL;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public void setRating(int rating, long position) {
        this.rating = toCloudQueueRating(rating);
        updateLikeStateFromRating(rating);
        RateTrackManager rateTrackManager = this.rateTrackManager;
        if (rateTrackManager == null) {
            return;
        }
        rateTrackManager.rateTrack(this.trackId, this.queueId, toServiceRating(this.rating), (int) position);
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.QueueEntityCapabilityQueryable
    /* JADX INFO: renamed from: getQueueEntityCapabilities, reason: from getter */
    public QueueEntityCapability getCapability() {
        return this.capability;
    }

    private final void addAlbumId(String albumId) {
        if (StringUtils.isEmpty(albumId)) {
            return;
        }
        MediaCollectionId mediaCollectionId = new MediaCollectionId(MediaCollectionId.Type.ALBUM_ART_ID, albumId);
        MediaCollectionInfo mediaCollectionInfo = getMediaCollectionInfo(MediaCollectionType.ALBUM, null);
        if (mediaCollectionInfo == null) {
            return;
        }
        mediaCollectionInfo.addId(mediaCollectionId);
    }
}
