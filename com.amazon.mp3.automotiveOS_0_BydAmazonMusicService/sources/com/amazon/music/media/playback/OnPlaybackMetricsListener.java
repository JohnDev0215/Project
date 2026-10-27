package com.amazon.music.media.playback;

import com.amazon.digitalmusicplayback.MTSAttributes;

/* JADX INFO: loaded from: classes4.dex */
public interface OnPlaybackMetricsListener {
    void onTrackFinished(int i, MTSAttributes mTSAttributes);

    void playbackTerminated(int i, MTSAttributes mTSAttributes);

    void streamingInitFailed(int i, MTSAttributes mTSAttributes);

    void streamingInitiated(int i, MTSAttributes mTSAttributes);

    void streamingRequiredRebuffering(int i, float f, MTSAttributes mTSAttributes);
}
