package com.amazon.music.wakeword.config;

/* JADX INFO: loaded from: classes4.dex */
public class RemoteWakeWordConfiguration implements WakeWordConfiguration {
    private int detectionSensitivity;

    public RemoteWakeWordConfiguration(int i) {
        this.detectionSensitivity = i;
    }

    @Override // com.amazon.music.wakeword.config.WakeWordConfiguration
    public int getDetectionSensitivity() {
        return this.detectionSensitivity;
    }
}
