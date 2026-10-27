package com.amazon.avod.playback;

import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.VideoResolution;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackQualityChangedEventListener {
    void onAudioQualityChanged(int i, AudioFormat audioFormat, PlaybackEventContext playbackEventContext);

    void onDynamicRangeChange(boolean z);

    void onVideoQualityChanged(int i, VideoResolution videoResolution, VideoResolution[] videoResolutionArr, PlaybackEventContext playbackEventContext);
}
