package com.amazon.mp3.playback.service.audioeffects;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.service.audioeffects.AppEqualizerSettings;
import com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface;
import com.amazon.mp3.playback.service.audioeffects.EqualizerPresetHelper;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.audioeffects.EqualizerBandControl;
import com.amazon.music.media.playback.audioeffects.GainControl;
import com.amazon.music.media.playback.player.Player;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class HarleyAudioEffects implements AudioEffectsInterface {
    private static final String TAG = "HarleyAudioEffects";
    private final GainControl bassController;
    private final List<EqualizerBandControl> equalizerControllerList;
    private final GainControl trebleController;
    private final AudioEffectsInterface.EqualizerEffectsInterface equalizerEffect = new AudioEffectsInterface.EqualizerEffectsInterface<AppEqualizerSettings>() { // from class: com.amazon.mp3.playback.service.audioeffects.HarleyAudioEffects.1
        private EqualizerPresetHelper.EqualizerPreset currPreset;

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public void setEqualizerEnabled(final boolean enabled) {
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public void useDefaultPreset() {
            applyPreset(EqualizerPresetHelper.getFlatPreset());
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public int getBandCount() {
            return HarleyAudioEffects.this.equalizerControllerList.size();
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public int getCenterFreq(final int bandIndex) {
            return ((EqualizerBandControl) HarleyAudioEffects.this.equalizerControllerList.get(bandIndex)).getFrequencyHz();
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public int getBandLevel(final int bandIndex) {
            return ((EqualizerBandControl) HarleyAudioEffects.this.equalizerControllerList.get(bandIndex)).getGainMillibels();
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public int[] getBandLevelRange() {
            return new int[]{((EqualizerBandControl) HarleyAudioEffects.this.equalizerControllerList.get(0)).getMinGainMillibels(), ((EqualizerBandControl) HarleyAudioEffects.this.equalizerControllerList.get(0)).getMaxGainMillibels()};
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public int getEqualizerPresetCount() {
            return EqualizerPresetHelper.EqualizerPreset.values().length;
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public AppEqualizerSettings getEqualizerProperties() {
            ArrayList arrayList = new ArrayList(HarleyAudioEffects.this.equalizerControllerList.size());
            for (EqualizerBandControl equalizerBandControl : HarleyAudioEffects.this.equalizerControllerList) {
                arrayList.add(new AppEqualizerSettings.Band(equalizerBandControl.getBandIndex(), equalizerBandControl.getGainMillibels()));
            }
            return new AppEqualizerSettings(arrayList, getCurrentPreset());
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public AppEqualizerSettings getUpdateEqualizerProperties(int bandIndex, int gain) {
            ArrayList arrayList = new ArrayList(HarleyAudioEffects.this.equalizerControllerList.size());
            for (EqualizerBandControl equalizerBandControl : HarleyAudioEffects.this.equalizerControllerList) {
                if (equalizerBandControl.getBandIndex() == bandIndex) {
                    arrayList.add(new AppEqualizerSettings.Band(bandIndex, gain));
                } else {
                    arrayList.add(new AppEqualizerSettings.Band(equalizerBandControl.getBandIndex(), equalizerBandControl.getGainMillibels()));
                }
            }
            return new AppEqualizerSettings(arrayList, getCurrentPreset());
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public void setEqualizerSettings(final AppEqualizerSettings settings) {
            this.currPreset = settings.getCurrPreset() == -1 ? null : EqualizerPresetHelper.getPreset(settings.getCurrPreset());
            if (settings.getBandList() != null) {
                for (AppEqualizerSettings.Band band : settings.getBandList()) {
                    ((EqualizerBandControl) HarleyAudioEffects.this.equalizerControllerList.get(band.bandIndex)).setGainMillibels(band.gainMillibels);
                }
            }
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public boolean isEqualizerInitialized() {
            return HarleyAudioEffects.this.equalizerControllerList != null;
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public void setBandLevel(final int bandIndex, final int bandValue) {
            ((EqualizerBandControl) HarleyAudioEffects.this.equalizerControllerList.get(bandIndex)).setGainMillibels(bandValue);
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public int getDefaultFlatPresetIndex() {
            return findPresetIndex(AmazonApplication.getApplication().getString(EqualizerPresetHelper.getFlatPreset().getStringRes()));
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public int getUserPresetIndex() {
            return findPresetIndex(AmazonApplication.getApplication().getString(R.string.equalizer_user_preset));
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public String[] getPresets() {
            ArrayList arrayList = new ArrayList();
            for (EqualizerPresetHelper.EqualizerPreset equalizerPreset : EqualizerPresetHelper.EqualizerPreset.values()) {
                arrayList.add(AmazonApplication.getApplication().getString(equalizerPreset.getStringRes()));
            }
            arrayList.add(AmazonApplication.getApplication().getResources().getString(R.string.equalizer_user_preset));
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public void usePreset(final int presetNum) {
            EqualizerPresetHelper.EqualizerPreset preset = EqualizerPresetHelper.getPreset(presetNum);
            this.currPreset = preset;
            if (preset == null) {
                Log.d(HarleyAudioEffects.TAG, "userPreset() currPreset fetched is null, preseNum: " + presetNum);
                return;
            }
            applyPreset(preset);
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.EqualizerEffectsInterface
        public int getCurrentPreset() {
            EqualizerPresetHelper.EqualizerPreset equalizerPreset = this.currPreset;
            if (equalizerPreset == null) {
                return -1;
            }
            return equalizerPreset.ordinal();
        }

        private int findPresetIndex(String key) {
            for (int i = 0; i < getPresets().length; i++) {
                if (getPresets()[i].equals(key)) {
                    return i;
                }
            }
            return -1;
        }

        private void applyPreset(@NonNull EqualizerPresetHelper.EqualizerPreset preset) {
            this.currPreset = preset;
            for (AppEqualizerSettings.Band band : preset.getBandList()) {
                int i = band.bandIndex;
                if (i >= 0 && i < HarleyAudioEffects.this.equalizerControllerList.size()) {
                    ((EqualizerBandControl) HarleyAudioEffects.this.equalizerControllerList.get(band.bandIndex)).setGainMillibels(band.gainMillibels);
                } else {
                    Log.d(HarleyAudioEffects.TAG, "userPreset() band.bandIndex out of bounds: " + band.bandIndex + " controllerListSize:" + HarleyAudioEffects.this.equalizerControllerList.size());
                }
            }
        }
    };
    private final AudioEffectsInterface.BassEffectsInterface bassEffects = new AudioEffectsInterface.BassEffectsInterface<AppAudioFrequencySettings>() { // from class: com.amazon.mp3.playback.service.audioeffects.HarleyAudioEffects.2
        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.BassEffectsInterface
        public void setBassEnabled(final boolean enabled) {
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.BassEffectsInterface
        public int getBassLevel() {
            return HarleyAudioEffects.this.bassController.getGainMillibels();
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.BassEffectsInterface
        public int getBassMaxLevel() {
            return HarleyAudioEffects.this.bassController.getMaxGainMillibels();
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.BassEffectsInterface
        public AppAudioFrequencySettings getBassProperties() {
            return new AppBassSettings(HarleyAudioEffects.this.bassController.getGainMillibels());
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.BassEffectsInterface
        public void setBassSettings(final AppAudioFrequencySettings settings) {
            HarleyAudioEffects.this.bassController.setGainMillibels(settings.getGainMillibels());
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.BassEffectsInterface
        public boolean isBassInitialized() {
            return HarleyAudioEffects.this.bassController != null;
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.BassEffectsInterface
        public void setBassLevel(final int level) {
            HarleyAudioEffects.this.bassController.setGainMillibels(level);
        }
    };
    private final AudioEffectsInterface.TrebleEffectsInterface trebleEffects = new AudioEffectsInterface.TrebleEffectsInterface<AppAudioFrequencySettings>() { // from class: com.amazon.mp3.playback.service.audioeffects.HarleyAudioEffects.3
        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.TrebleEffectsInterface
        public void setTrebleEnabled(boolean enabled) {
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.TrebleEffectsInterface
        public int getTrebleLevel() {
            return HarleyAudioEffects.this.trebleController.getGainMillibels();
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.TrebleEffectsInterface
        public int getTrebleMaxLevel() {
            return HarleyAudioEffects.this.trebleController.getMaxGainMillibels();
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.TrebleEffectsInterface
        public AppAudioFrequencySettings getTrebleProperties() {
            return new AppTrebleSettings(HarleyAudioEffects.this.trebleController.getGainMillibels());
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.TrebleEffectsInterface
        public void setTrebleSettings(final AppAudioFrequencySettings settings) {
            HarleyAudioEffects.this.trebleController.setGainMillibels(settings.getGainMillibels());
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.TrebleEffectsInterface
        public boolean isTrebleInitialized() {
            return HarleyAudioEffects.this.trebleController != null;
        }

        @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface.TrebleEffectsInterface
        public void setTrebleLevel(final int level) {
            HarleyAudioEffects.this.trebleController.setGainMillibels(level);
        }
    };

    public HarleyAudioEffects() {
        Player localPlayer = Playback.getInstance().getPlaybackConfig().getLocalPlayer(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(AmazonApplication.getApplication().getApplicationContext()));
        this.equalizerControllerList = localPlayer.getAudioEffectsController().getEqualizerBandControllerList();
        this.bassController = localPlayer.getAudioEffectsController().getBassController();
        this.trebleController = localPlayer.getAudioEffectsController().getTrebleController();
    }

    @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface
    public AudioEffectsInterface.EqualizerEffectsInterface getEqualizerEffects() {
        return this.equalizerEffect;
    }

    @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface
    public AudioEffectsInterface.BassEffectsInterface getBassEffects() {
        return this.bassEffects;
    }

    @Override // com.amazon.mp3.playback.service.audioeffects.AudioEffectsInterface
    public AudioEffectsInterface.TrebleEffectsInterface getTrebleEffects() {
        return this.trebleEffects;
    }
}
