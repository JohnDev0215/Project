package com.amazon.mp3.playback.streaming.concurrency;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.dmls.ContentId;
import com.amazon.mp3.net.dmls.DMLSApiProvider;
import com.amazon.mp3.net.dmls.DMLSExceptions;
import com.amazon.mp3.net.dmls.IStreamingConcurrencyStatusResponse;
import com.amazon.mp3.net.dmls.IdentifierType;
import com.amazon.mp3.net.dmls.StatusCode;
import com.amazon.mp3.net.dmls.StreamingStatus;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.config.MediaStreamingStatusProvider;
import com.amazon.video.sdk.player.PlaybackState;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public class StreamingConcurrencyStatusConnector implements MediaStreamingStatusProvider {
    private static String TAG = "StreamingConcurrencyStatusConnector";
    private static StreamingConcurrencyStatusConnector mInstance;
    private Context mContext;
    private StreamingConcurrencyStatusObserver mObserver;

    private StreamingConcurrencyStatusConnector(final Context context) {
        this.mObserver = null;
        this.mObserver = new StreamingConcurrencyStatusObserver();
        this.mContext = context;
    }

    public static synchronized StreamingConcurrencyStatusConnector getInstance(final Context context) {
        if (mInstance == null) {
            mInstance = new StreamingConcurrencyStatusConnector(context);
        }
        return mInstance;
    }

    public void updateStreamingStatus(final ContentId contentId, final StreamingStatus streamingStatus, final long contentDuration, final long currentPlayTime) {
        Log.verbose(TAG, "PMB: updateStreamingStatus for content = " + contentId.toString());
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.mp3.playback.streaming.concurrency.StreamingConcurrencyStatusConnector.1
            IStreamingConcurrencyStatusResponse mResponse;

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... params) {
                try {
                    this.mResponse = DMLSApiProvider.get().updateStreamingStatus(contentId, streamingStatus, contentDuration, currentPlayTime);
                    StreamingConcurrencyStatusConnector.this.mObserver.onUpdateStatusReceived(this.mResponse, contentDuration);
                } catch (AbstractHttpClient.HttpClientException e) {
                    Log.warning(StreamingConcurrencyStatusConnector.TAG, "Caught HttpClientException: " + e.toString());
                } catch (DMLSExceptions.StatusCodeException e2) {
                    Log.warning(StreamingConcurrencyStatusConnector.TAG, "Caught StatusCodeException in updateStreamingStatus: " + e2.toString());
                    if (e2.getStatus() == StatusCode.MAX_CONCURRENCY_REACHED) {
                        StreamingConcurrencyStatusConnector.this.mObserver.onUpdateStatusReceived(e2.getResponse(), contentDuration);
                    }
                } catch (DMLSExceptions.DMLSException e3) {
                    Log.warning(StreamingConcurrencyStatusConnector.TAG, "Caught DMLSException: " + e3.toString());
                } catch (JSONException e4) {
                    Log.warning(StreamingConcurrencyStatusConnector.TAG, "Caught JSONException: " + e4.toString());
                } catch (Exception e5) {
                    Log.warning(StreamingConcurrencyStatusConnector.TAG, "Caught Exception: " + e5.toString());
                }
                if (this.mResponse == null) {
                    return null;
                }
                Intent intent = new Intent("com.amazon.mp3.inactivitythresholdretrieved");
                intent.putExtra("inactivityThreshold", this.mResponse.getInactivityThreshold());
                StreamingConcurrencyStatusConnector.this.mContext.sendBroadcast(intent);
                return null;
            }
        }.execute(new Void[0]);
    }

    public void terminateAndUpdateStreamingStatus(final ContentId contentId, final StreamingStatus streamingStatus, final long contentDuration, final long currentPlayTime, final String oldDeviceName) {
        Log.verbose(TAG, "called terminateAndUpdateStreamingStatus for content = " + contentId.toString());
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.mp3.playback.streaming.concurrency.StreamingConcurrencyStatusConnector.2
            IStreamingConcurrencyStatusResponse mResponse;

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... params) {
                try {
                    this.mResponse = DMLSApiProvider.get().terminateAndUpdateStreamingStatus(contentId, streamingStatus, contentDuration, currentPlayTime, oldDeviceName);
                    StreamingConcurrencyStatusConnector.this.mObserver.onTerminateAndUpdateStatusReceived(this.mResponse, contentDuration);
                    return null;
                } catch (AbstractHttpClient.HttpClientException e) {
                    Log.warning(StreamingConcurrencyStatusConnector.TAG, "Caught HttpClientException: " + e.toString());
                    return null;
                } catch (DMLSExceptions.DMLSException e2) {
                    Log.warning(StreamingConcurrencyStatusConnector.TAG, "Caught DMLSException: " + e2.toString());
                    return null;
                } catch (JSONException e3) {
                    Log.warning(StreamingConcurrencyStatusConnector.TAG, "Caught JSONException: " + e3.toString());
                    return null;
                } catch (Exception e4) {
                    Log.warning(StreamingConcurrencyStatusConnector.TAG, "Caught Exception: " + e4.toString());
                    return null;
                }
            }
        }.execute(new Void[0]);
    }

    @Override // com.amazon.music.media.playback.config.MediaStreamingStatusProvider
    public void updateCurrentStreamingStatus(final String trackId, final String trackIdType, final PlaybackState playbackState, final long contentDuration, final long currentPlaytime, final String oldDeviceName, final boolean forceUpdate) {
        try {
            ContentId contentId = new ContentId(IdentifierType.fromString(trackIdType), trackId);
            StreamingStatus streamingStatus = playbackState == PlaybackState.Playing ? StreamingStatus.PLAY : StreamingStatus.PAUSE;
            if (forceUpdate) {
                terminateAndUpdateStreamingStatus(contentId, streamingStatus, contentDuration, currentPlaytime, oldDeviceName);
            } else {
                updateStreamingStatus(contentId, streamingStatus, contentDuration, currentPlaytime);
            }
        } catch (DMLSExceptions.InvalidContentIdException e) {
            e.printStackTrace();
        }
    }
}
