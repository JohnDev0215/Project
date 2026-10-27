package com.amazon.avod.qos.listeners;

import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import com.amazon.avod.media.framework.event.EventDispatcher;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
public class QosEventListenerBase {
    private final AtomicReference<EventDispatcher> mEventDispatcher = new AtomicReference<>(null);

    protected void bind(EventDispatcher eventDispatcher) {
        if (CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.mEventDispatcher, null, eventDispatcher)) {
            eventDispatcher.registerEventBusHandler(this);
        }
    }

    public void release() {
        EventDispatcher andSet = this.mEventDispatcher.getAndSet(null);
        if (andSet != null) {
            andSet.unregisterEventBusHandler(this);
        }
    }
}
