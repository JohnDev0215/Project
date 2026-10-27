package com.nuance.android.vocalizer;

/* JADX INFO: loaded from: classes.dex */
public interface VocalizerEngineListener {
    void onSpeakElementCompleted(String str);

    void onSpeakElementStarted(String str);

    void onStateChanged(int i);

    void onVoiceListChanged();
}
