package com.byd.auto_emanual.video;

/* JADX INFO: loaded from: classes.dex */
public interface IPlayControl {
    int getAudioSessionId();

    int getCurrentPosition();

    int getDuration();

    void next();

    void pause();

    void previous();

    void seekTo(int pos);

    void setVolume(float volume);

    void start();

    void stop();

    void zoom(String zoomTag);
}
