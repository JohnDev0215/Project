package com.amazon.mp3.playback.streaming.concurrency;

import android.content.Context;
import com.amazon.digitalmusicplayback.StreamingStatusResponse;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.net.dmls.DMLSStreamingStatusResponse;
import com.amazon.mp3.net.dmls.IStreamingConcurrencyStatusResponse;
import com.amazon.mp3.net.dmls.StatusCode;
import com.amazon.mp3.util.ConcurrencyUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.player.impl.PrimeVideoPlayer;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class StreamingConcurrencyStatusObserver {
    private static String TAG = "StreamingConcurrencyStatusObserver";
    private Context mContext = AmazonApplication.getContext();

    StreamingConcurrencyStatusObserver() {
    }

    public void onUpdateStatusReceived(IStreamingConcurrencyStatusResponse status, long trackDuration) {
        PlaybackConfig playbackConfig;
        Log.debug(TAG, "Updated streaming status: " + status.getStatusMessage());
        ArrayList arrayList = new ArrayList();
        for (DMLSStreamingStatusResponse dMLSStreamingStatusResponse : status.getStreamingResponseList()) {
            arrayList.add(new StreamingStatusResponse(dMLSStreamingStatusResponse.getDeviceToken().getDeviceId(), dMLSStreamingStatusResponse.getRemoteDeviceName(), dMLSStreamingStatusResponse.getStreamingStatus().getName(), dMLSStreamingStatusResponse.getElapsedTimeSeconds()));
        }
        if (!status.getStatusCode().equals(StatusCode.MAX_CONCURRENCY_REACHED) || (playbackConfig = Playback.getInstance().getPlaybackConfig()) == null) {
            return;
        }
        PrimeVideoPlayer primeVideoPlayer = (PrimeVideoPlayer) playbackConfig.getPrimeVideoPlayer();
        Playback.getInstance().getPlaybackController(ControlSource.NONE).pause();
        ConcurrencyUtil.INSTANCE.onMaximumConcurrencyReached(arrayList, primeVideoPlayer);
    }

    public void onTerminateAndUpdateStatusReceived(IStreamingConcurrencyStatusResponse status, long trackDuration) {
        Log.debug(TAG, "Terminated incoming stream and updated streaming status: " + status.getStatusMessage());
    }
}
