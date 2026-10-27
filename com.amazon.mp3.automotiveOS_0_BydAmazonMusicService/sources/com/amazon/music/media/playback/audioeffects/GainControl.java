package com.amazon.music.media.playback.audioeffects;

/* JADX INFO: loaded from: classes4.dex */
public interface GainControl {
    int getGainMillibels();

    int getMaxGainMillibels();

    int getMinGainMillibels();

    void setGainMillibels(int i);
}
