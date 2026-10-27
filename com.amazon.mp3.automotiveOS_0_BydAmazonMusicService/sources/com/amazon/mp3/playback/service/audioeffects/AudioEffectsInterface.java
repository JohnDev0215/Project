package com.amazon.mp3.playback.service.audioeffects;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioEffectsInterface {

    public interface BassEffectsInterface<BASS_SETTINGS extends FrequencySettings> {
        int getBassLevel();

        int getBassMaxLevel();

        BASS_SETTINGS getBassProperties();

        boolean isBassInitialized();

        void setBassEnabled(boolean enabled);

        void setBassLevel(int level);

        void setBassSettings(BASS_SETTINGS settings);
    }

    public interface EqualizerEffectsInterface<EQUALIZER_SETTINGS extends EqualizerSettings> {
        int getBandCount();

        int getBandLevel(int bandIndex);

        int[] getBandLevelRange();

        int getCenterFreq(int bandIndex);

        int getCurrentPreset();

        int getDefaultFlatPresetIndex();

        int getEqualizerPresetCount();

        EQUALIZER_SETTINGS getEqualizerProperties();

        String[] getPresets();

        AppEqualizerSettings getUpdateEqualizerProperties(int bandIndex, int gain);

        int getUserPresetIndex();

        boolean isEqualizerInitialized();

        void setBandLevel(final int bandIndex, final int bandValue);

        void setEqualizerEnabled(final boolean enabled);

        void setEqualizerSettings(EQUALIZER_SETTINGS settings);

        void useDefaultPreset();

        void usePreset(final int presetNum);
    }

    public interface EqualizerSettings {
        String getBandValueString();

        int getCurrPreset();

        String getStorageString();

        void setCurrPreset(int currPreset);
    }

    public interface FrequencySettings {
        String getStorageString();
    }

    public interface TrebleEffectsInterface<TREBLE_SETTINGS extends FrequencySettings> {
        int getTrebleLevel();

        int getTrebleMaxLevel();

        TREBLE_SETTINGS getTrebleProperties();

        boolean isTrebleInitialized();

        void setTrebleEnabled(boolean enabled);

        void setTrebleLevel(int level);

        void setTrebleSettings(TREBLE_SETTINGS settings);
    }

    BassEffectsInterface getBassEffects();

    EqualizerEffectsInterface getEqualizerEffects();

    TrebleEffectsInterface getTrebleEffects();
}
