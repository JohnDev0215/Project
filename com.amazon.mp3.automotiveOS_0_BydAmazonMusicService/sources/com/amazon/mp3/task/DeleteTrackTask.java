package com.amazon.mp3.task;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.source.cirrus.TrackDeleteRequestUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.util.Log;
import org.json.JSONException;

/* JADX INFO: loaded from: classes4.dex */
public class DeleteTrackTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = DeleteTrackTask.class.getSimpleName();
    private Context mContext;
    private final Uri mUri;
    private OnCompleteListener onCompleteListener;

    public interface OnCompleteListener {
        void onComplete();
    }

    public DeleteTrackTask(Context context, Uri uri) {
        this.mContext = context;
        this.mUri = uri;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(Void... params) {
        long jDeleteTracks;
        NowPlayingUtil.clearCollection(AmazonApplication.getContext(), this.mUri, PlayStateMutationReason.DELETING_TRACKS);
        try {
            jDeleteTracks = TrackDeleteRequestUtil.deleteTracks(this.mContext, this.mUri);
        } catch (AbstractHttpClient.HttpClientException e) {
            Log.error(TAG, "HttpClientException received: %s", e.getMessage());
            jDeleteTracks = -1;
        } catch (CirrusExceptions.CirrusException e2) {
            Log.error(TAG, "CirrusException received: %s", e2.getMessage());
            jDeleteTracks = -1;
        } catch (JSONException e3) {
            Log.error(TAG, "JSONException received: %s", e3.getMessage());
            jDeleteTracks = -1;
        }
        if (jDeleteTracks <= 0) {
            return null;
        }
        Log.info(TAG, "Successfully deleted %d tracks", Long.valueOf(jDeleteTracks));
        OnCompleteListener onCompleteListener = this.onCompleteListener;
        if (onCompleteListener == null) {
            return null;
        }
        onCompleteListener.onComplete();
        return null;
    }

    public void setOnCompleteListener(final OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }
}
