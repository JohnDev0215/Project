package com.amazon.alexa.voice.core;

/* JADX INFO: loaded from: classes2.dex */
public interface Consumable<T> {
    void accept();

    T get();

    void reject(Throwable th);
}
