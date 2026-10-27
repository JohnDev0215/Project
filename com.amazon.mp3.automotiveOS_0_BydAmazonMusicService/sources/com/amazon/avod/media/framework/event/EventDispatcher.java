package com.amazon.avod.media.framework.event;

import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class EventDispatcher {
    private final EventBus mBus;
    private final Set<Object> mListeners = new HashSet();
    private final Set<EventDispatcher> mEventDispatchers = new HashSet();

    protected EventDispatcher(EventBus eventBus) {
        this.mBus = eventBus;
    }

    public void unregisterEventBusHandler(Object obj) {
        synchronized (this.mListeners) {
            if (this.mListeners.contains(obj)) {
                this.mListeners.remove(obj);
                this.mBus.unregister(obj);
            }
        }
    }

    public void registerEventBusHandler(Object obj) {
        synchronized (this.mListeners) {
            if (this.mListeners.contains(obj)) {
                return;
            }
            this.mListeners.add(obj);
            this.mBus.register(obj);
        }
    }

    public void postEvent(Object obj) {
        synchronized (this.mListeners) {
            if (!this.mListeners.isEmpty()) {
                this.mBus.post(obj);
            }
        }
        synchronized (this.mEventDispatchers) {
            Iterator<EventDispatcher> it = this.mEventDispatchers.iterator();
            while (it.hasNext()) {
                it.next().postEvent(obj);
            }
        }
    }

    public void shutdown() {
        synchronized (this.mListeners) {
            Iterator<Object> it = this.mListeners.iterator();
            while (it.hasNext()) {
                this.mBus.unregister(it.next());
            }
            this.mListeners.clear();
        }
        synchronized (this.mEventDispatchers) {
            this.mEventDispatchers.clear();
        }
    }

    public void registerEventBus(@Nonnull EventDispatcher eventDispatcher) {
        Preconditions.checkNotNull(eventDispatcher, "eventDispatcher");
        synchronized (this.mEventDispatchers) {
            if (this.mEventDispatchers.contains(eventDispatcher)) {
                return;
            }
            this.mEventDispatchers.add(eventDispatcher);
        }
    }
}
