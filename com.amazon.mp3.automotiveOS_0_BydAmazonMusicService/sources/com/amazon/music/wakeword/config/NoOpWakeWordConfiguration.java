package com.amazon.music.wakeword.config;

/* JADX INFO: loaded from: classes4.dex */
public class NoOpWakeWordConfiguration implements WakeWordConfiguration {
    @Override // com.amazon.music.wakeword.config.WakeWordConfiguration
    public int getDetectionSensitivity() {
        return 1;
    }
}
