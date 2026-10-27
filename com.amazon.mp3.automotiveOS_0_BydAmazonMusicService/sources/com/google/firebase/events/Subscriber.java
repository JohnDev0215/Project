package com.google.firebase.events;

/* JADX INFO: loaded from: classes6.dex */
public interface Subscriber {
    <T> void subscribe(Class<T> cls, EventHandler<? super T> eventHandler);
}
