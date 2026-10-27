package com.amazon.music.voice.ui;

import com.amazon.music.voice.ui.scrim.ScrimProvider;

/* JADX INFO: loaded from: classes4.dex */
public class VoiceComponent extends Component {
    private final ScrimProvider scrimProvider;

    public VoiceComponent(ScrimProvider scrimProvider) {
        this.scrimProvider = scrimProvider;
    }

    @Override // com.amazon.music.voice.ui.Component
    public ScrimProvider getScrimProvider() {
        return this.scrimProvider;
    }
}
