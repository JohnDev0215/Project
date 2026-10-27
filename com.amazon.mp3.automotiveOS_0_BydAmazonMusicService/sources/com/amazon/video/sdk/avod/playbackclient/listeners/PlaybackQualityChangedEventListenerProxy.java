package com.amazon.video.sdk.avod.playbackclient.listeners;

import com.amazon.avod.listeners.SetListenerProxy;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackQualityChangedEventListener;
import java.util.Iterator;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public class PlaybackQualityChangedEventListenerProxy extends SetListenerProxy<PlaybackQualityChangedEventListener> implements PlaybackQualityChangedEventListener {
    @Override // com.amazon.avod.playback.PlaybackQualityChangedEventListener
    public void onVideoQualityChanged(int i, VideoResolution videoResolution, VideoResolution[] videoResolutionArr, PlaybackEventContext playbackEventContext) {
        Iterator<PlaybackQualityChangedEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onVideoQualityChanged(i, videoResolution, videoResolutionArr, playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackQualityChangedEventListener
    public void onAudioQualityChanged(int i, AudioFormat audioFormat, PlaybackEventContext playbackEventContext) {
        Iterator<PlaybackQualityChangedEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onAudioQualityChanged(i, audioFormat, playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackQualityChangedEventListener
    public void onDynamicRangeChange(boolean z) {
        Iterator<PlaybackQualityChangedEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onDynamicRangeChange(z);
        }
    }
}
