package com.amazon.music.voice;

/* JADX INFO: loaded from: classes4.dex */
public interface AlexaPlaystateProvider {
    long getCurrentPosition();

    long getDuration();

    boolean isBuffering();

    boolean isEnded();

    boolean isIdle();

    boolean isPaused();

    boolean isPlaying();
}
