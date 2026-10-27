package com.amazon.mp3.voice;

import android.content.Context;
import android.os.AsyncTask;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.TrackRequester;
import com.amazon.mp3.util.Log;
import com.amazon.music.station.Rating;
import com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataResponse;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class GetTrackFromMpqsResponseTask extends AsyncTask<Void, Void, MusicTrack> {
    private static final String TAG = GetTrackFromMpqsResponseTask.class.getSimpleName();
    protected final Context mContext;
    private final GetTrackFromMpqsHandler mGetTrackFromMpqsHandler;
    private final GetTrackAndQueueMetadataResponse mResponse;
    private final StreamInfo mStreamInfo;

    public GetTrackFromMpqsResponseTask(final Context context, final StreamInfo streamInfo, final GetTrackAndQueueMetadataResponse response, final GetTrackFromMpqsHandler getTrackFromMpqsHandler) {
        this.mContext = context;
        this.mStreamInfo = streamInfo;
        this.mResponse = response;
        this.mGetTrackFromMpqsHandler = getTrackFromMpqsHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.AsyncTask
    public MusicTrack doInBackground(final Void... voids) {
        MusicTrack trackFromResponse;
        if (this.mResponse == null || (trackFromResponse = getTrackFromResponse()) == null) {
            return null;
        }
        AlexaTrack alexaTrack = new AlexaTrack(trackFromResponse);
        alexaTrack.setMpqsTrackId(this.mResponse.getTrackMetadata().getTrackId());
        alexaTrack.setCanRateTrack(this.mResponse.getTrackMetadata().isCanRateTrack());
        alexaTrack.setRating(getTrackRating(this.mResponse.getTrackMetadata().getRating()));
        alexaTrack.setIsFirstTrackInQueue(this.mResponse.getTrackMetadata().isIsNewQueue());
        alexaTrack.setMetricsContext(this.mResponse.getTrackMetadata().getMetricsContext());
        alexaTrack.setAudioItemStreamInfo(this.mStreamInfo);
        Log.debug(TAG, "Alexa track: " + alexaTrack.toString());
        return alexaTrack;
    }

    protected MusicTrack getTrackFromResponse() {
        String identifierType = getIdentifierType();
        String identifier = getIdentifier();
        if (identifierType.equals("OBJECT_ID")) {
            return getTrackFromObjectId(identifier);
        }
        if (identifierType.equals("ASIN")) {
            return new TrackRequester(this.mContext).getTrackFromAsin(identifier);
        }
        Log.error(TAG, "Unknown identifier type returned from getTrackAndQueueMetadata: " + identifierType);
        return null;
    }

    protected String getIdentifierType() {
        return this.mResponse.getTrackMetadata().getIdentifierType();
    }

    protected String getIdentifier() {
        return this.mResponse.getTrackMetadata().getIdentifier();
    }

    protected MusicTrack getTrackFromObjectId(final String objectId) {
        MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(MediaProvider.Tracks.getContentUri("cirrus", objectId));
        if (track == null) {
            Log.error(TAG, "Got OBJECT_ID from getTrackAndQueueMetadata but couldn't find track in our library: " + objectId);
        }
        return track;
    }

    protected Rating getTrackRating(final String rating) {
        Validate.matchesPattern(rating, "THUMBS_UP|THUMBS_DOWN|NEUTRAL");
        return Rating.valueOf(rating);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.AsyncTask
    public void onPostExecute(final MusicTrack track) {
        this.mGetTrackFromMpqsHandler.onCompleted(track);
    }
}
