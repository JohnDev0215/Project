package com.amazon.mp3.library.provider.source.nowplaying;

import android.net.Uri;
import com.amazon.mp3.util.AlexaTrackRater;
import com.amazon.mp3.voice.AlexaTrack;
import com.amazon.mp3.voice.StreamInfo;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.ratings.RatingsProvider;
import com.amazon.music.metrics.mts.event.types.PlaybackInitiationInfo;
import com.amazon.music.station.Rating;

/* JADX INFO: loaded from: classes3.dex */
public class AlexaMediaItem extends LibraryMediaItem {
    private final String TAG;
    private final AlexaTrackRater mAlexaTrackRater;
    private final boolean mCanRate;
    private boolean mIsFirstTrackInQueue;
    private int mRating;
    private final RatingsProvider.OnRatingsChangedListener mRatingsListener;
    private final StreamInfo mStreamInfo;

    @Override // com.amazon.music.media.playback.MediaItem
    public Uri getAlbumImageUri(final int width, final int height) {
        return null;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public Uri getArtistImageUri(final int width, final int height) {
        return null;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public Uri getMediaItemImageUri(final int width, final int height) {
        return null;
    }

    public AlexaMediaItem(final AlexaTrack alexaTrack, final PlaybackInitiationInfo playbackInitiationInfo, final MediaCollectionInfo collectionInfo, final AlexaTrackRater trackRatingHelper, final RatingsProvider.OnRatingsChangedListener listener) {
        super(alexaTrack, playbackInitiationInfo, collectionInfo);
        this.TAG = AlexaMediaItem.class.getSimpleName();
        this.mCanRate = alexaTrack.canRateTrack();
        this.mRating = fromAlexaTrackRating(alexaTrack.getRating());
        this.mStreamInfo = alexaTrack.getAudioItemStreamInfo();
        this.mAlexaTrackRater = trackRatingHelper;
        this.mRatingsListener = listener;
        this.mIsFirstTrackInQueue = alexaTrack.isFirstTrackInQueue();
        addMediaItemId(new MediaItemId(MediaItemId.Type.MPQS, alexaTrack.getMpqsTrackId()));
        setMetricsContext(alexaTrack.getMetricsContext());
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public int getRating() {
        return this.mRating;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public void setRating(final int rating, final long position) {
        updateLikeStateFromRating(rating);
        int i = this.mRating;
        int iRateTrack = this.mAlexaTrackRater.rateTrack(this, rating);
        if (iRateTrack == -1) {
            return;
        }
        this.mRating = iRateTrack;
        if (i != iRateTrack) {
            this.mRatingsListener.onRatingsChanged(this, i, iRateTrack);
        }
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean canRate() {
        return this.mCanRate;
    }

    public StreamInfo getStreamInfo() {
        return this.mStreamInfo;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.provider.source.nowplaying.AlexaMediaItem$1, reason: invalid class name */
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

    private int fromAlexaTrackRating(final Rating alexaTrackRating) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$station$Rating[alexaTrackRating.ordinal()];
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return i != 3 ? -1 : 0;
        }
        return 2;
    }

    public boolean isFirstTrackInQueue() {
        return this.mIsFirstTrackInQueue;
    }
}
