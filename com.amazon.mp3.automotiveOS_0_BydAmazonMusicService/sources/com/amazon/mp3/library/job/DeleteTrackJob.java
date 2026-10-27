package com.amazon.mp3.library.job;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.source.cirrus.TrackDeleteRequestUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.Log;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public class DeleteTrackJob extends Job {
    private static final String TAG = "DeleteTrackJob";
    private final Uri mUri;

    @Override // com.amazon.mp3.service.job.Job
    public String toString() {
        return "DeleteTrackJob";
    }

    public DeleteTrackJob(Uri uri) {
        this.mUri = uri;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        long jDeleteTracks;
        NowPlayingUtil.clearCollection(AmazonApplication.getContext(), this.mUri, PlayStateMutationReason.DELETING_TRACKS);
        try {
            jDeleteTracks = TrackDeleteRequestUtil.deleteTracks(getContext(), this.mUri);
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
            return 3;
        }
        Log.info(TAG, "Successfully deleted %d tracks", Long.valueOf(jDeleteTracks));
        return 1;
    }
}
