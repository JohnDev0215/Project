package com.amazon.mp3.playback.service.audioeffects;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: classes3.dex */
public class AppEqualizerSettings implements AudioEffectsInterface.EqualizerSettings {
    private List<Band> bandList;
    private int currentPreset = -1;

    public AppEqualizerSettings(final String eqSettingsString) {
        loadSettingsFromStrings(eqSettingsString);
    }

    public AppEqualizerSettings(final List<Band> bandList, final int currPreset) {
        this.bandList = bandList;
        setCurrPreset(currPreset);
    }

    public void loadSettingsFromStrings(final String storedString) {
        if (TextUtils.isEmpty(storedString)) {
            return;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(storedString, "=;");
        if (stringTokenizer.countTokens() < 5) {
            throw new IllegalArgumentException("settings: " + storedString);
        }
        String strNextToken = stringTokenizer.nextToken();
        if (!strNextToken.equals("Equalizer")) {
            throw new IllegalArgumentException("invalid settings for Equalizer: " + strNextToken);
        }
        try {
            String strNextToken2 = stringTokenizer.nextToken();
            if (!strNextToken2.equals("curPreset")) {
                throw new IllegalArgumentException("invalid key name: " + strNextToken2);
            }
            this.currentPreset = Integer.parseInt(stringTokenizer.nextToken());
            String strNextToken3 = stringTokenizer.nextToken();
            if (!strNextToken3.equals("numBands")) {
                throw new IllegalArgumentException("invalid key name: " + strNextToken3);
            }
            int i = Integer.parseInt(stringTokenizer.nextToken());
            if (stringTokenizer.countTokens() != i * 2) {
                throw new IllegalArgumentException("settings: " + storedString);
            }
            this.bandList = new ArrayList(i);
            int i2 = 0;
            while (i2 < i) {
                String strNextToken4 = stringTokenizer.nextToken();
                StringBuilder sb = new StringBuilder();
                sb.append("band");
                int i3 = i2 + 1;
                sb.append(i3);
                sb.append("Level");
                if (!strNextToken4.equals(sb.toString())) {
                    throw new IllegalArgumentException("invalid key name: " + strNextToken4);
                }
                this.bandList.add(new Band(i2, Integer.parseInt(stringTokenizer.nextToken())));
                i2 = i3;
            }
        } catch (NumberFormatException unused) {
            throw new IllegalArgumentException("invalid value for key: " + strNextToken);
        }
    }

    @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerSettings
    public int getCurrPreset() {
        return this.currentPreset;
    }

    @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerSettings
    public void setCurrPreset(final int currPreset) {
        this.currentPreset = currPreset;
    }

    @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerSettings
    public String getStorageString() {
        return "Equalizer;curPreset=" + this.currentPreset + ";" + getBandValueString();
    }

    @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerSettings
    public String getBandValueString() {
        StringBuilder sb = new StringBuilder();
        sb.append("numBands");
        sb.append("=");
        sb.append(this.bandList.size());
        int i = 0;
        while (i < this.bandList.size()) {
            sb.append(";");
            sb.append("band");
            int i2 = i + 1;
            sb.append(i2);
            sb.append("Level");
            sb.append("=");
            sb.append(this.bandList.get(i).gainMillibels);
            i = i2;
        }
        return sb.toString();
    }

    @Nullable
    public List<Band> getBandList() {
        return this.bandList;
    }

    public static class Band {
        int bandIndex;
        int gainMillibels;

        Band(final int bandIndex, final int gainMillibels) {
            this.bandIndex = bandIndex;
            this.gainMillibels = gainMillibels;
        }
    }

    public static Band createBand(int bandIndex, int gainMilli) {
        return new Band(bandIndex, gainMilli);
    }
}
