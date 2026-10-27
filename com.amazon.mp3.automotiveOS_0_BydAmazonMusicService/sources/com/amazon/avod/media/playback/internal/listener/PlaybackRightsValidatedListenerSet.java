package com.amazon.avod.media.playback.internal.listener;

import com.amazon.avod.media.framework.event.EventListenerSet;
import com.amazon.avod.playback.PlaybackRightsValidatedListener;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class PlaybackRightsValidatedListenerSet extends EventListenerSet<PlaybackRightsValidatedListener> implements PlaybackRightsValidatedListener {
    @Override // com.amazon.avod.playback.PlaybackRightsValidatedListener
    public void onPlaybackRightsValidated(@Nonnull String str, @Nullable String str2) {
        Set<PlaybackRightsValidatedListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackRightsValidatedListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onPlaybackRightsValidated(str, str2);
            }
        }
    }
}
