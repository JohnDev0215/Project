package com.bumptech.glide.request;

/* JADX INFO: loaded from: classes5.dex */
public interface Request {
    void begin();

    void clear();

    boolean isAnyResourceSet();

    boolean isCleared();

    boolean isComplete();

    boolean isEquivalentTo(Request request);

    boolean isRunning();

    void pause();
}
