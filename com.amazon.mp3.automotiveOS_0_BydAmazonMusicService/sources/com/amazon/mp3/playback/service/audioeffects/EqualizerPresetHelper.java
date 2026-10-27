package com.amazon.mp3.playback.service.audioeffects;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.service.audioeffects.AppEqualizerSettings;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class EqualizerPresetHelper {
    static EqualizerPreset getPreset(final int presetNum) {
        if (presetNum < 0 || presetNum >= EqualizerPreset.values().length) {
            return null;
        }
        return EqualizerPreset.values()[presetNum];
    }

    public static EqualizerPreset getFlatPreset() {
        return EqualizerPreset.valueOf(EqualizerPreset.FLAT.toString());
    }

    public enum EqualizerPreset {
        FLAT(R.string.equalizer_preset_flat, new int[]{0, 0, 0, 0, 0}),
        CLASSICAL(R.string.equalizer_preset_classical, new int[]{500, 300, -200, 0, 400}),
        DANCE(R.string.equalizer_preset_dance, new int[]{MediaError.DetailedErrorCode.TEXT_UNKNOWN, 0, 200, 400, 100}),
        FOLK(R.string.equalizer_preset_folk, new int[]{300, 0, 0, 200, -100}),
        HEAVY_METAL(R.string.equalizer_preset_heavy_metal, new int[]{400, 100, 900, 300, 300}),
        HIP_HOP(R.string.equalizer_preset_hip_hop, new int[]{500, 300, 0, 100, 300}),
        JAZZ(R.string.equalizer_preset_jazz, new int[]{400, 200, -200, 200, 500}),
        POP(R.string.equalizer_preset_pop, new int[]{-100, 200, 500, 100, -200}),
        ROCK(R.string.equalizer_preset_rock, new int[]{-500, -300, -100, 300, 500});

        private final List<AppEqualizerSettings.Band> bandList = new ArrayList();

        @StringRes
        private final int stringRes;

        EqualizerPreset(@StringRes final int stringRes, final int[] bandArr) {
            this.stringRes = stringRes;
            for (int i = 0; i < bandArr.length; i++) {
                this.bandList.add(new AppEqualizerSettings.Band(i, bandArr[i]));
            }
        }

        @NonNull
        public List<AppEqualizerSettings.Band> getBandList() {
            return this.bandList;
        }

        @StringRes
        public int getStringRes() {
            return this.stringRes;
        }
    }
}
