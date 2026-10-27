package com.amazon.music.media.playback.audioeffects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface AudioEffectsControl {
    @Nullable
    GainControl getBassController();

    @NonNull
    List<EqualizerBandControl> getEqualizerBandControllerList();

    @Nullable
    GainControl getTrebleController();
}
