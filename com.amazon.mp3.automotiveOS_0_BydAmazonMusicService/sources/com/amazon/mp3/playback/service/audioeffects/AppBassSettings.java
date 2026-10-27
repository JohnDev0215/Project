package com.amazon.mp3.playback.service.audioeffects;

/* JADX INFO: loaded from: classes3.dex */
public class AppBassSettings extends AppAudioFrequencySettings {
    @Override // com.amazon.mp3.playback.service.audioeffects.AppAudioFrequencySettings
    protected String getSettingsName() {
        return "BassBoost";
    }

    public AppBassSettings(final String settingsString) {
        super(settingsString);
    }

    public AppBassSettings(final int gainMillibels) {
        super(gainMillibels);
    }
}
