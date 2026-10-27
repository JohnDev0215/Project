package com.amazon.music.downloads;

/* JADX INFO: loaded from: classes4.dex */
public interface QueueWorker {
    void add(RequestItem requestItem);

    void cancel(RequestItem requestItem);

    void exit();

    boolean hasTerminated();

    boolean isDownloading();

    boolean isIdle();

    void pause(RequestItem requestItem);

    void start();
}
