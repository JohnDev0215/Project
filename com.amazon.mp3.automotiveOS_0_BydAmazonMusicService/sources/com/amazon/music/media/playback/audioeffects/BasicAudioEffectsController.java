package com.amazon.music.media.playback.audioeffects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class BasicAudioEffectsController implements AudioEffectsControl {
    public static final AudioEffectsControl EMPTY_AUDIO_EFFECTS_CONTROL = new BasicAudioEffectsController(null, null, Collections.emptyList());
    private final GainControl bassControl;
    private final List<EqualizerBandControl> equalizerBandControlList;
    private final GainControl trebleControl;

    public BasicAudioEffectsController(@Nullable GainControl gainControl, @Nullable GainControl gainControl2, @NonNull List<EqualizerBandControl> list) {
        this.bassControl = gainControl;
        this.trebleControl = gainControl2;
        this.equalizerBandControlList = list;
    }

    @Override // com.amazon.music.media.playback.audioeffects.AudioEffectsControl
    @Nullable
    public GainControl getBassController() {
        return this.bassControl;
    }

    @Override // com.amazon.music.media.playback.audioeffects.AudioEffectsControl
    @Nullable
    public GainControl getTrebleController() {
        return this.trebleControl;
    }

    @Override // com.amazon.music.media.playback.audioeffects.AudioEffectsControl
    @NonNull
    public List<EqualizerBandControl> getEqualizerBandControllerList() {
        List<EqualizerBandControl> list = this.equalizerBandControlList;
        return list == null ? Collections.EMPTY_LIST : list;
    }
}
