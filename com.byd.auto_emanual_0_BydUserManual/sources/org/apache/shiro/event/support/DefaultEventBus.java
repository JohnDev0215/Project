package org.apache.shiro.event.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.shiro.event.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class DefaultEventBus implements EventBus {
    private static final String EVENT_LISTENER_ERROR_MSG = "Event listener processing failed.  Listeners should generally handle exceptions directly and not propagate to the event bus.";
    private EventListenerResolver eventListenerResolver;
    private final Map<Object, Subscription> registry = new LinkedHashMap();
    private final Lock registryReadLock;
    private final Lock registryWriteLock;
    private static final Logger log = LoggerFactory.getLogger(DefaultEventBus.class);
    private static final EventListenerComparator EVENT_LISTENER_COMPARATOR = new EventListenerComparator();

    public DefaultEventBus() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.registryReadLock = reentrantReadWriteLock.readLock();
        this.registryWriteLock = reentrantReadWriteLock.writeLock();
        this.eventListenerResolver = new AnnotationEventListenerResolver();
    }

    public EventListenerResolver getEventListenerResolver() {
        return this.eventListenerResolver;
    }

    public void setEventListenerResolver(EventListenerResolver eventListenerResolver) {
        this.eventListenerResolver = eventListenerResolver;
    }

    @Override // org.apache.shiro.event.EventBus
    public void publish(Object obj) {
        if (obj == null) {
            log.info("Received null event for publishing.  Ignoring and returning.");
            return;
        }
        this.registryReadLock.lock();
        try {
            Iterator<Subscription> it = this.registry.values().iterator();
            while (it.hasNext()) {
                it.next().onEvent(obj);
            }
        } finally {
            this.registryReadLock.unlock();
        }
    }

    @Override // org.apache.shiro.event.EventBus
    public void register(Object obj) {
        if (obj == null) {
            log.info("Received null instance for event listener registration.  Ignoring registration request.");
            return;
        }
        unregister(obj);
        List<EventListener> eventListeners = getEventListenerResolver().getEventListeners(obj);
        if (eventListeners == null || eventListeners.isEmpty()) {
            log.warn("Unable to resolve event listeners for subscriber instance [{}]. Ignoring registration request.", obj);
            return;
        }
        Subscription subscription = new Subscription(eventListeners);
        this.registryWriteLock.lock();
        try {
            this.registry.put(obj, subscription);
        } finally {
            this.registryWriteLock.unlock();
        }
    }

    @Override // org.apache.shiro.event.EventBus
    public void unregister(Object obj) {
        if (obj == null) {
            return;
        }
        this.registryWriteLock.lock();
        try {
            this.registry.remove(obj);
        } finally {
            this.registryWriteLock.unlock();
        }
    }

    private class Subscription {
        private final List<EventListener> listeners;

        public Subscription(List<EventListener> list) {
            ArrayList arrayList = new ArrayList(list);
            Collections.sort(arrayList, DefaultEventBus.EVENT_LISTENER_COMPARATOR);
            this.listeners = arrayList;
        }

        public void onEvent(Object obj) {
            HashSet hashSet = new HashSet();
            for (EventListener eventListener : this.listeners) {
                Object target = eventListener instanceof SingleArgumentMethodEventListener ? ((SingleArgumentMethodEventListener) eventListener).getTarget() : eventListener;
                if (eventListener.accepts(obj) && !hashSet.contains(target)) {
                    try {
                        eventListener.onEvent(obj);
                    } catch (Throwable th) {
                        DefaultEventBus.log.warn(DefaultEventBus.EVENT_LISTENER_ERROR_MSG, th);
                    }
                    hashSet.add(target);
                }
            }
        }
    }
}
