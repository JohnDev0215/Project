package com.amazon.music.voice;

/* JADX INFO: loaded from: classes4.dex */
public interface AlexaTrackReceiver {
    void clearQueue();

    void enqueueTrack(String str, String str2);

    void playTrack(String str, String str2);
}
