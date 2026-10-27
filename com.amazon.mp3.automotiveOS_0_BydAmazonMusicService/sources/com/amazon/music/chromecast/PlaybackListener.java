package com.amazon.music.chromecast;

/* JADX INFO: loaded from: classes4.dex */
public interface PlaybackListener {
    void onLoadError(int i);

    void onLoadSuccess();

    void onPlaybackComplete();

    void onRemotePause();

    void onRemotePlay();
}
