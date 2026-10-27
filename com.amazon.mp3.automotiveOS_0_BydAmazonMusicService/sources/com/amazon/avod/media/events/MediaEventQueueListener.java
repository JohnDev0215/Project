package com.amazon.avod.media.events;

import com.amazon.avod.media.events.MediaEventQueue;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface MediaEventQueueListener {
    void newEvent(@Nonnull MediaEventQueue.PersistentMediaEvent persistentMediaEvent);
}
