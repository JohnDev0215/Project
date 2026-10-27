package org.apache.shiro.event.support;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface EventListenerResolver {
    List<EventListener> getEventListeners(Object obj);
}
