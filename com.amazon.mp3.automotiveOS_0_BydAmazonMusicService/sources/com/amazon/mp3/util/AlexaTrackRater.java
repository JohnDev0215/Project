package com.amazon.mp3.util;

import android.content.Context;
import android.os.AsyncTask;
import com.amazon.mp3.library.provider.source.nowplaying.AlexaMediaItem;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.voice.MusicPlayQueueFactory;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.station.Rating;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaTrackRater {
    private static final String TAG = "AlexaTrackRater";
    private final Context mContext;

    public AlexaTrackRater(final Context context) {
        this.mContext = context;
    }

    public int rateTrack(final AlexaMediaItem alexaMediaItem, final int selectedRating) {
        if (alexaMediaItem == null) {
            Log.error(TAG, "AlexaMediaItem is null");
            return -1;
        }
        if (alexaMediaItem.canRate()) {
            int iDetermineNewRatingForTrack = TrackRatingUtil.determineNewRatingForTrack(selectedRating, alexaMediaItem.getRating());
            submitTrackRatingAsync(MediaItem.getMediaItemIdString(alexaMediaItem, MediaItemId.Type.MPQS, ""), toAlexaTrackRating(iDetermineNewRatingForTrack));
            return iDetermineNewRatingForTrack;
        }
        Log.error(TAG, "AlexaMediaItem cannot be rated, canRate(): " + alexaMediaItem.canRate());
        return -1;
    }

    private Rating toAlexaTrackRating(final int alexaMediaItemRating) {
        if (alexaMediaItemRating == 1) {
            return Rating.THUMBS_UP;
        }
        if (alexaMediaItemRating == 2) {
            return Rating.THUMBS_DOWN;
        }
        return Rating.NEUTRAL;
    }

    private void submitTrackRatingAsync(final String mpqsTrackId, final Rating finalRating) {
        new AsyncTask<Void, Void, Rating>() { // from class: com.amazon.mp3.util.AlexaTrackRater.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Rating doInBackground(final Void... params) {
                int iMax = Math.max(0, PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentIndex());
                long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                Log.debug(AlexaTrackRater.TAG, "Submitting rateEntity: " + finalRating + ", " + mpqsTrackId + ", " + iMax + ", " + seconds);
                MusicPlayQueueFactory.rateEntity(AlexaTrackRater.this.mContext, finalRating.toString(), mpqsTrackId, iMax, seconds);
                return finalRating;
            }
        }.execute(new Void[0]);
    }
}
