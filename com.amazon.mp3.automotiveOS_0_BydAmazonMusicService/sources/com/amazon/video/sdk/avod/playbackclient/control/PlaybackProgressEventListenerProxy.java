package com.amazon.video.sdk.avod.playbackclient.control;

import com.amazon.avod.listeners.SetListenerProxy;
import java.util.Iterator;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public class PlaybackProgressEventListenerProxy extends SetListenerProxy<PlaybackProgressEventListener> implements PlaybackProgressEventListener {
    @Override // com.amazon.video.sdk.avod.playbackclient.control.PlaybackProgressEventListener
    public void onProgressChanged() {
        Iterator<PlaybackProgressEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onProgressChanged();
        }
    }
}
