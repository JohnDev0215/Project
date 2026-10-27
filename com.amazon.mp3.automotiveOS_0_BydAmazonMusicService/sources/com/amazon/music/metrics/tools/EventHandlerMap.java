package com.amazon.music.metrics.tools;

import com.amazon.music.metrics.event.Event;
import com.amazon.music.metrics.event.EventHandler;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
public class EventHandlerMap {
    private Map<Class<? extends Event>, Set<EventHandler>> mMultiTool = new ConcurrentHashMap();

    public void register(EventHandler eventHandler) {
        getHandlerSet(eventHandler.getEventType(), true).add(eventHandler);
    }

    public void deregister(EventHandler eventHandler) {
        Class eventType = eventHandler.getEventType();
        Set<EventHandler> set = this.mMultiTool.get(eventType);
        if (set != null) {
            set.remove(eventHandler);
            if (set.isEmpty()) {
                this.mMultiTool.remove(eventType);
            }
        }
    }

    public Set<EventHandler> getHandlers(Class<? extends Event> cls) {
        return getHandlerSet(cls, false);
    }

    public Set<EventHandler> getAllHandlers() {
        Iterator<Map.Entry<Class<? extends Event>, Set<EventHandler>>> it = this.mMultiTool.entrySet().iterator();
        HashSet hashSet = new HashSet();
        while (it.hasNext()) {
            hashSet.addAll(it.next().getValue());
        }
        return hashSet;
    }

    private Set<EventHandler> getHandlerSet(Class<? extends Event> cls, boolean z) {
        Set<EventHandler> hashSet = this.mMultiTool.get(cls);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            if (z) {
                this.mMultiTool.put(cls, hashSet);
            }
        }
        return hashSet;
    }
}
