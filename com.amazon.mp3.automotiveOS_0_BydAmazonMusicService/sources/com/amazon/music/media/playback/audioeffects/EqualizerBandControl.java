package com.amazon.music.media.playback.audioeffects;

/* JADX INFO: loaded from: classes4.dex */
public interface EqualizerBandControl extends GainControl {
    int getBandIndex();

    int getFrequencyHz();

    @Override // com.amazon.music.media.playback.audioeffects.GainControl
    /* synthetic */ int getGainMillibels();

    @Override // com.amazon.music.media.playback.audioeffects.GainControl
    /* synthetic */ int getMaxGainMillibels();

    @Override // com.amazon.music.media.playback.audioeffects.GainControl
    /* synthetic */ int getMinGainMillibels();

    float getWidthHz();

    @Override // com.amazon.music.media.playback.audioeffects.GainControl
    /* synthetic */ void setGainMillibels(int i);
}
