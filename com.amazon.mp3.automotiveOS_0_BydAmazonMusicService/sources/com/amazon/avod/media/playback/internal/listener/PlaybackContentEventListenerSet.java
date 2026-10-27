package com.amazon.avod.media.playback.internal.listener;

import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.framework.event.EventListenerSet;
import com.amazon.avod.playback.PlaybackContentEventListener;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class PlaybackContentEventListenerSet extends EventListenerSet<PlaybackContentEventListener> implements PlaybackContentEventListener {
    @Override // com.amazon.avod.playback.PlaybackContentEventListener
    public void onContentMetadataAvailable(VideoResolution[] videoResolutionArr, double d) {
        Set<PlaybackContentEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackContentEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onContentMetadataAvailable(videoResolutionArr, d);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackContentEventListener
    public void onContentDownloaded() {
        Set<PlaybackContentEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackContentEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onContentDownloaded();
            }
        }
    }
}
