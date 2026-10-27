package com.amazon.music.media.playback.sequencer.impl;

import android.net.Uri;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.MediaLinkProvider;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.ratings.RatingsProvider;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.station.RateTrackManager;
import com.amazon.music.station.Rating;
import com.amazon.music.station.StationItem;
import com.amazon.music.station.SynchronizedTrackItem;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class StationMediaItem extends MediaItem {
    private final Uri albumArtUri;
    private final boolean isInterlude;
    private final RatingsProvider.OnRatingsChangedListener listener;
    private final RateTrackManager rateTrackManager;
    private final StationItem stationItem;
    private final SynchronizedTrackItem trackItem;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v7 */
    public StationMediaItem(SynchronizedTrackItem synchronizedTrackItem, StationItem stationItem, RateTrackManager rateTrackManager, MediaCollectionInfo mediaCollectionInfo, RatingsProvider.OnRatingsChangedListener onRatingsChangedListener, MediaLinkProvider mediaLinkProvider, MediaItem.ImageUriProvider imageUriProvider, PlaybackPageType playbackPageType) {
        int i;
        super(null, synchronizedTrackItem.getTitle(), TimeUnit.SECONDS.toMillis(synchronizedTrackItem.getTrackDuration().longValue()), MediaItem.Type.UNLIMITED_CONTENT, MediaAccessInfo.forEligibility(synchronizedTrackItem.isExplicit(), true, false, true, getMediaItemIdType(synchronizedTrackItem.getIdentifierType()) == MediaItemId.Type.OWNED, stationItem.isPrime().booleanValue(), false, stationItem.isMusicSubscription().booleanValue(), false), null, mediaLinkProvider, imageUriProvider, playbackPageType, false, mediaCollectionInfo, new MediaCollectionInfo[0]);
        if (StringUtils.isEmpty(synchronizedTrackItem.getArtistName())) {
            i = 0;
        } else {
            i = 0;
            addMediaCollectionInfo(new MediaCollectionInfo(synchronizedTrackItem.getArtistName(), null, StringUtils.isEmpty(synchronizedTrackItem.getTrackArtistAsin()) ? null : new MediaCollectionId(MediaCollectionId.Type.ASIN, synchronizedTrackItem.getTrackArtistAsin()), MediaCollectionType.ARTIST, getMediaAccessInfo(), new MediaCollectionId[0]));
        }
        if (!StringUtils.isEmpty(synchronizedTrackItem.getAlbumTitle())) {
            addMediaCollectionInfo(new MediaCollectionInfo(synchronizedTrackItem.getAlbumTitle(), null, StringUtils.isEmpty(synchronizedTrackItem.getAlbumAsin()) ? null : new MediaCollectionId(MediaCollectionId.Type.ASIN, synchronizedTrackItem.getAlbumAsin()), MediaCollectionType.ALBUM, getMediaAccessInfo(), new MediaCollectionId[i]));
        }
        if ("INTERLUDE_ID".equals(synchronizedTrackItem.getIdentifierType())) {
            String streamingURL = synchronizedTrackItem.getStreamingURL();
            if (!StringUtils.isEmpty(streamingURL)) {
                addMediaItemId(new MediaItemId(MediaItemId.Type.URL, streamingURL));
                streamingURL = streamingURL.replaceFirst("https", "interlude");
            }
            addMediaItemId(new MediaItemId(MediaItemId.Type.INTERLUDE, streamingURL));
            this.isInterlude = true;
            if (synchronizedTrackItem.getMetricsContext() != null) {
                setMetricsContext(synchronizedTrackItem.getMetricsContext());
            }
        } else {
            addMediaItemId(new MediaItemId(getMediaItemIdType(synchronizedTrackItem.getIdentifierType()), synchronizedTrackItem.getIdentifier()));
            addMediaItemId(new MediaItemId(MediaItemId.Type.MPQS, synchronizedTrackItem.getTrackId()));
            this.isInterlude = i;
        }
        this.albumArtUri = Uri.parse(synchronizedTrackItem.getAlbumArtImageUrl());
        this.trackItem = synchronizedTrackItem;
        this.stationItem = stationItem;
        this.rateTrackManager = rateTrackManager;
        this.listener = onRatingsChangedListener;
        Map<String, String> metricsContext = synchronizedTrackItem.getMetricsContext();
        if (metricsContext != null) {
            setSessionId(metricsContext.get("selectionSourceSessionId"));
        }
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public Uri getMediaItemImageUri(int i, int i2) {
        return this.albumArtUri;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public Uri getArtistImageUri(int i, int i2) {
        return this.albumArtUri;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public Uri getAlbumImageUri(int i, int i2) {
        return this.albumArtUri;
    }

    public SynchronizedTrackItem getTrackItem() {
        return this.trackItem;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean canRate() {
        return !isInterlude();
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public int getRating() {
        return fromStationRating(this.trackItem.getTrackRating());
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public void setRating(int i, long j) {
        Rating trackRating = this.trackItem.getTrackRating();
        Rating stationRating = toStationRating(i);
        if (trackRating != stationRating) {
            this.rateTrackManager.rateTrack(this.trackItem, stationRating, (int) j);
            this.mLikeState = getLikeStateFromRating(i);
            Rating trackRating2 = this.trackItem.getTrackRating();
            if (trackRating != trackRating2) {
                this.listener.onRatingsChanged(this, fromStationRating(trackRating), fromStationRating(trackRating2));
            }
        }
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean isInterlude() {
        return this.isInterlude;
    }

    private static MediaItemId.Type getMediaItemIdType(String str) {
        if ("OBJECT_ID".equals(str)) {
            return MediaItemId.Type.OWNED;
        }
        return MediaItemId.Type.ASIN;
    }

    /* JADX INFO: renamed from: com.amazon.music.media.playback.sequencer.impl.StationMediaItem$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$station$Rating;

        static {
            int[] iArr = new int[Rating.values().length];
            $SwitchMap$com$amazon$music$station$Rating = iArr;
            try {
                iArr[Rating.THUMBS_UP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$station$Rating[Rating.THUMBS_DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$station$Rating[Rating.NEUTRAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static int fromStationRating(Rating rating) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$station$Rating[rating.ordinal()];
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return i != 3 ? -1 : 0;
        }
        return 2;
    }

    private static Rating toStationRating(int i) {
        if (i == 1) {
            return Rating.THUMBS_UP;
        }
        if (i == 2) {
            return Rating.THUMBS_DOWN;
        }
        return Rating.NEUTRAL;
    }
}
