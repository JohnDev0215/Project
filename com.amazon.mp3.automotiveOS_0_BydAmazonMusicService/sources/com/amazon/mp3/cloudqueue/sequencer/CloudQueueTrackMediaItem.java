package com.amazon.mp3.cloudqueue.sequencer;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.mp3.cloudqueue.PlaybackActionCapability;
import com.amazon.mp3.cloudqueue.RateTrackManager;
import com.amazon.mp3.cloudqueue.Rating;
import com.amazon.mp3.cloudqueue.model.SongEntity;
import com.amazon.music.ContentAccessType;
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
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class CloudQueueTrackMediaItem extends MediaItem implements PlaybackActionCapabilityQueryable, PlaybackControlVisibilityQueryable {
    private final Uri albumArtUri;
    private final RatingsProvider.OnRatingsChangedListener listener;
    private final RateTrackManager rateTrackManager;
    private final SongEntity trackItem;

    public CloudQueueTrackMediaItem(final SongEntity trackItem, final RateTrackManager rateTrackManager, final MediaCollectionInfo collectionInfo, final RatingsProvider.OnRatingsChangedListener listener, final MediaLinkProvider mediaLinkProvider, final MediaItem.ImageUriProvider imageUriProvider, final PlaybackPageType playbackPageType) {
        super(new MediaItemId(MediaItemId.Type.ASIN, trackItem.getAsin()), trackItem.getTitle(), TimeUnit.SECONDS.toMillis(trackItem.getTrackDuration().longValue()), getMediaType(trackItem), new MediaAccessInfo.Builder().withAvailableOffline(false).withAllowedOnline(true).withCastable(trackItem.getPlaybackActionCapability("CASTING").getActionStatus() == 0).withExplicit(trackItem.isExplicit()).withContentAccessType(getContentAccessType(trackItem)).build(), null, mediaLinkProvider, imageUriProvider, playbackPageType, false, collectionInfo, new MediaCollectionInfo[0]);
        if (!StringUtils.isEmpty(trackItem.getArtistName())) {
            addMediaCollectionInfo(new MediaCollectionInfo(trackItem.getArtistName(), null, StringUtils.isEmpty(trackItem.getTrackArtistAsin()) ? null : new MediaCollectionId(MediaCollectionId.Type.ASIN, trackItem.getTrackArtistAsin()), MediaCollectionType.ARTIST, getMediaAccessInfo(), new MediaCollectionId[0]));
        }
        if (!StringUtils.isEmpty(trackItem.getAlbumTitle())) {
            addMediaCollectionInfo(new MediaCollectionInfo(trackItem.getAlbumTitle(), null, StringUtils.isEmpty(trackItem.getAlbumAsin()) ? null : new MediaCollectionId(MediaCollectionId.Type.ASIN, trackItem.getAlbumAsin()), MediaCollectionType.ALBUM, getMediaAccessInfo(), new MediaCollectionId[0]));
        }
        addMediaItemId(new MediaItemId(MediaItemId.Type.MPQS, trackItem.getTrackId()));
        this.albumArtUri = Uri.parse(trackItem.getAlbumArtImageUrl());
        this.trackItem = trackItem;
        this.rateTrackManager = rateTrackManager;
        this.listener = listener;
        Map<String, String> metricsContext = trackItem.getMetricsContext();
        setMetricsContext(metricsContext);
        if (metricsContext != null) {
            setSessionId(metricsContext.get("selectionSourceSessionId"));
        }
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public Uri getMediaItemImageUri(final int width, final int height) {
        return this.albumArtUri;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public Uri getArtistImageUri(final int width, final int height) {
        return this.albumArtUri;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public Uri getAlbumImageUri(final int width, final int height) {
        return this.albumArtUri;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean canRate() {
        return this.trackItem.getPlaybackActionCapability("RATE_TRACK").getActionStatus() != 1;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean canSeek() {
        return (this.trackItem.getPlaybackActionCapability("SCRUB_BACKWARD").getActionStatus() == 1 && this.trackItem.getPlaybackActionCapability("SCRUB_FORWARD").getActionStatus() == 1) ? false : true;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public int getRating() {
        return fromStationRating(this.trackItem.getTrackRating());
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public void setRating(final int rating, final long position) {
        Rating trackRating = this.trackItem.getTrackRating();
        Rating stationRating = toStationRating(rating);
        if (trackRating != stationRating) {
            updateLikeStateFromRating(rating);
            this.rateTrackManager.rateTrack(this.trackItem.getTrackId(), null, this.trackItem.changeTrackRating(stationRating), (int) position);
            Rating trackRating2 = this.trackItem.getTrackRating();
            if (trackRating != trackRating2) {
                this.listener.onRatingsChanged(this, fromStationRating(trackRating), fromStationRating(trackRating2));
            }
        }
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.PlaybackActionCapabilityQueryable
    public PlaybackActionCapability getPlaybackActionCapability(String action) {
        return this.trackItem.getPlaybackActionCapability(action);
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.PlaybackControlVisibilityQueryable
    public boolean canDisplay(String action) {
        PlaybackActionCapability playbackActionCapability = this.trackItem.getPlaybackActionCapability(action);
        if (playbackActionCapability != null) {
            return playbackActionCapability.canDisplay();
        }
        return false;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.cloudqueue.sequencer.CloudQueueTrackMediaItem$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$cloudqueue$Rating;

        static {
            int[] iArr = new int[Rating.values().length];
            $SwitchMap$com$amazon$mp3$cloudqueue$Rating = iArr;
            try {
                iArr[Rating.THUMBS_UP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$cloudqueue$Rating[Rating.THUMBS_DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$cloudqueue$Rating[Rating.NEUTRAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static int fromStationRating(final Rating stationRating) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$cloudqueue$Rating[stationRating.ordinal()];
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return i != 3 ? -1 : 0;
        }
        return 2;
    }

    private static Rating toStationRating(final int rating) {
        if (rating == 1) {
            return Rating.THUMBS_UP;
        }
        if (rating == 2) {
            return Rating.THUMBS_DOWN;
        }
        return Rating.NEUTRAL;
    }

    private static MediaItem.Type getMediaType(SongEntity songEntity) {
        if (songEntity.isNightWing().booleanValue()) {
            return MediaItem.Type.NIGHTWING_CONTENT;
        }
        if (songEntity.isPrime().booleanValue()) {
            return MediaItem.Type.PRIME_CONTENT;
        }
        if (songEntity.isMusicSubscription().booleanValue()) {
            return MediaItem.Type.UNLIMITED_CONTENT;
        }
        return MediaItem.Type.UNAVAILABLE;
    }

    @Nullable
    private static ContentAccessType getContentAccessType(SongEntity songEntity) {
        if (songEntity.isNightWing().booleanValue()) {
            return ContentAccessType.NIGHTWING;
        }
        if (songEntity.isPrime().booleanValue()) {
            return ContentAccessType.PRIME;
        }
        if (songEntity.isMusicSubscription().booleanValue()) {
            return ContentAccessType.HAWKFIRE;
        }
        return null;
    }
}
