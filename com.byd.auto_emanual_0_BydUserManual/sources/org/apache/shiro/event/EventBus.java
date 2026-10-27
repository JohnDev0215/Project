package org.apache.shiro.event;

/* JADX INFO: loaded from: classes.dex */
public interface EventBus {
    void publish(Object obj);

    void register(Object obj);

    void unregister(Object obj);
}
