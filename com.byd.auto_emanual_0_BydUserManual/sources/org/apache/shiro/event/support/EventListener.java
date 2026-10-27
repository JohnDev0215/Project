package org.apache.shiro.event.support;

/* JADX INFO: loaded from: classes.dex */
public interface EventListener {
    boolean accepts(Object obj);

    void onEvent(Object obj);
}
