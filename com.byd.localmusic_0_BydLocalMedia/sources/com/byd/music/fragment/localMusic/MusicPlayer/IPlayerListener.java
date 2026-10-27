package com.byd.music.fragment.localMusic.MusicPlayer;

/* JADX INFO: loaded from: classes5.dex */
public interface IPlayerListener {
    void onChangeState(int i);

    void onDuration(int i);

    void onProgress(int i);

    void playError(int i);

    void playPreNextError(boolean z);

    void reSet();
}
