package com.amazon.mp3.library.provider.source.nowplaying;

import android.content.Context;
import android.net.Uri;
import android.util.Pair;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.GetTrackFromMpqsHandler;
import com.amazon.mp3.voice.GetTrackFromMpqsResponseTask;
import com.amazon.mp3.voice.StreamInfo;
import com.amazon.music.voice.AlexaErrorHandler;
import com.amazon.music.voice.VoiceManagerSingleton;
import com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataResponse;
import com.amazon.musicplayqueueservice.client.common.QueueMetadata;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class AlexaTrackProvider implements TrackProvider {
    private static final String TAG = "AlexaTrackProvider";
    protected final Context mContext;
    protected LoadCookie mCookie;
    private final GetTrackFromMpqsHandler mGetTrackFromMpqsHandler;
    protected volatile boolean mIsLoading;
    private GetTrackAndQueueMetadataResponse mResponse;
    private StreamInfo mStreamInfo;
    protected final List<MusicTrack> mTracks = new ArrayList();
    protected final CopyOnWriteArraySet<TrackProviderListener> mTrackProviderListeners = new CopyOnWriteArraySet<>();

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void cancel() {
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void close() {
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void restore(final Context context, final JSONObject object) throws JSONException {
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public boolean supportsRepeatMode() {
        return false;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public boolean supportsShuffle() {
        return false;
    }

    public AlexaTrackProvider(final Context context, final Pair<StreamInfo, GetTrackAndQueueMetadataResponse> responsePair) {
        GetTrackFromMpqsHandler getTrackFromMpqsHandler = new GetTrackFromMpqsHandler() { // from class: com.amazon.mp3.library.provider.source.nowplaying.AlexaTrackProvider.1
            @Override // com.amazon.mp3.voice.GetTrackFromMpqsHandler
            public void onCompleted(final MusicTrack track) {
                AlexaTrackProvider.this.onGetTrackFromResponseCompleted(track);
            }
        };
        this.mGetTrackFromMpqsHandler = getTrackFromMpqsHandler;
        this.mContext = context;
        if (responsePair != null) {
            this.mStreamInfo = (StreamInfo) responsePair.first;
            this.mResponse = (GetTrackAndQueueMetadataResponse) responsePair.second;
            this.mIsLoading = true;
            new GetTrackFromMpqsResponseTask(context, (StreamInfo) responsePair.first, this.mResponse, getTrackFromMpqsHandler).execute(new Void[0]);
        }
    }

    public StreamInfo getStreamInfo() {
        return this.mStreamInfo;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void loadCollection(final LoadCookie cookie) {
        this.mCookie = cookie;
        if (this.mIsLoading) {
            return;
        }
        Iterator<TrackProviderListener> it = this.mTrackProviderListeners.iterator();
        while (it.hasNext()) {
            it.next().onCollectionLoaded(this.mCookie);
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void loadCollectionName() {
        Iterator<TrackProviderListener> it = this.mTrackProviderListeners.iterator();
        while (it.hasNext()) {
            it.next().onCollectionNameLoaded(getCollectionName());
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public boolean isLoading() {
        return this.mIsLoading;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public MusicTrack getTrack(final int position) {
        int size = this.mTracks.size();
        if (position >= 0 && position < size) {
            return this.mTracks.get(position);
        }
        if (size > 0) {
            int i = size - 1;
            MusicTrack musicTrack = this.mTracks.get(i);
            Log.info(TAG, "Position %d is out of bounds. Returning track at last position %d", Integer.valueOf(position), Integer.valueOf(i));
            return musicTrack;
        }
        Log.info(TAG, "getTrack called but there are no tracks");
        return null;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public int getCount() {
        return this.mTracks.size();
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public String getCollectionName() {
        QueueMetadata queueMetadata = this.mResponse.getQueueMetadata();
        String title = queueMetadata != null ? queueMetadata.getTitle() : null;
        return title == null ? this.mContext.getResources().getString(R.string.alexa_now_playing_collection_name) : title;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void refresh(final LoadCookie cookie) {
        Iterator<TrackProviderListener> it = this.mTrackProviderListeners.iterator();
        while (it.hasNext()) {
            it.next().onCountChanged(this.mTracks.size());
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void registerListener(final TrackProviderListener listener) {
        this.mTrackProviderListeners.add(listener);
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void deregisterListener(final TrackProviderListener listener) {
        this.mTrackProviderListeners.remove(listener);
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public Uri getUri() {
        return MediaProvider.Alexa.getContentUri();
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void populateState(final JSONObject blob) throws JSONException {
        blob.put("uri", MediaProvider.AlexaRecentlyPlayed.getContentUri());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGetTrackFromResponseCompleted(final MusicTrack track) {
        if (track == null && VoiceManagerSingleton.isInitialized()) {
            VoiceManagerSingleton.getInstance().getAlexaErrorHandler().handleError(AlexaErrorHandler.AlexaErrorType.GET_TRACK_AND_QUEUE_METADATA_ERROR);
        } else {
            this.mTracks.add(track);
        }
        if (this.mIsLoading) {
            this.mIsLoading = false;
            if (this.mCookie != null) {
                Iterator<TrackProviderListener> it = this.mTrackProviderListeners.iterator();
                while (it.hasNext()) {
                    it.next().onCollectionLoaded(this.mCookie);
                }
            }
        }
    }
}
