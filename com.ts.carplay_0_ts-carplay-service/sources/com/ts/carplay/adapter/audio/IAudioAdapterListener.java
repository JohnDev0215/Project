package com.ts.carplay.adapter.audio;

/* JADX INFO: loaded from: classes.dex */
public interface IAudioAdapterListener {
    default void onAudioFocusChanged(int priority) {
    }

    default void onAudioFocusChanged(int priority, String borrowId) {
    }
}
