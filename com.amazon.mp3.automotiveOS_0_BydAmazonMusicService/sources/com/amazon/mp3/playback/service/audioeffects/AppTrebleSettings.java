package com.amazon.mp3.playback.service.audioeffects;

/* JADX INFO: loaded from: classes3.dex */
public class AppTrebleSettings extends AppAudioFrequencySettings {
    @Override // com.amazon.mp3.playback.service.audioeffects.AppAudioFrequencySettings
    protected String getSettingsName() {
        return "Treble";
    }

    public AppTrebleSettings(final String settingsString) {
        super(settingsString);
    }

    public AppTrebleSettings(final int gainMillibels) {
        super(gainMillibels);
    }
}
