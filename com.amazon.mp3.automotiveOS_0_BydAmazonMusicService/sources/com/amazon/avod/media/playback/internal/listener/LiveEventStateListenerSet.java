package com.amazon.avod.media.playback.internal.listener;

import com.amazon.avod.media.framework.event.EventListenerSet;
import com.amazon.avod.playback.LiveEventStateListener;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class LiveEventStateListenerSet extends EventListenerSet<LiveEventStateListener> implements LiveEventStateListener {
    @Override // com.amazon.avod.playback.LiveEventStateListener
    public void onEventEnd() {
        Set<LiveEventStateListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<LiveEventStateListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onEventEnd();
            }
        }
    }
}
