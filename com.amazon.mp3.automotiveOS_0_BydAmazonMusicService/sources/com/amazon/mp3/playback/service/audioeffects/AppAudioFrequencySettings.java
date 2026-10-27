package com.amazon.mp3.playback.service.audioeffects;

import android.text.TextUtils;
import com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AppAudioFrequencySettings implements AudioEffectsInterface.FrequencySettings {
    private int gainMillibels;

    protected abstract String getSettingsName();

    protected AppAudioFrequencySettings(final int gainMillibels) {
        this.gainMillibels = gainMillibels;
    }

    protected AppAudioFrequencySettings(final String settingsString) {
        if (TextUtils.isEmpty(settingsString)) {
            return;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(settingsString, "=;");
        if (stringTokenizer.countTokens() != 3) {
            throw new IllegalArgumentException("settings: " + settingsString);
        }
        String strNextToken = stringTokenizer.nextToken();
        if (!strNextToken.equals(getSettingsName())) {
            throw new IllegalArgumentException("invalid settings for BassBoost: " + strNextToken);
        }
        try {
            String strNextToken2 = stringTokenizer.nextToken();
            if (!strNextToken2.equals("strength")) {
                throw new IllegalArgumentException("invalid key name: " + strNextToken2);
            }
            this.gainMillibels = Integer.parseInt(stringTokenizer.nextToken());
        } catch (NumberFormatException unused) {
            throw new IllegalArgumentException("invalid value for key: " + strNextToken);
        }
    }

    @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.FrequencySettings
    public String getStorageString() {
        return getSettingsName() + ";strength=" + this.gainMillibels;
    }

    public int getGainMillibels() {
        return this.gainMillibels;
    }
}
