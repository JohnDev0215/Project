package com.byd.music.utils.task;

/* JADX INFO: loaded from: classes5.dex */
public interface TaskHandler {
    void cancel();

    boolean isCancelled();

    boolean isPaused();

    boolean isStopped();

    void pause();

    void resume();

    boolean supportCancel();

    boolean supportPause();

    boolean supportResume();
}
