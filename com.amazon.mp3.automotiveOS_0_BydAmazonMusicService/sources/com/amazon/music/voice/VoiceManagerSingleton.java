package com.amazon.music.voice;

import com.amazon.music.voice.VoiceManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
public class VoiceManagerSingleton {
    private static AtomicBoolean initialized = new AtomicBoolean(false);
    private static VoiceManager sVoiceManager;

    public static synchronized void initialize(VoiceManager.Builder builder) {
        initialized.set(true);
        VoiceManager voiceManagerBuild = builder.build();
        sVoiceManager = voiceManagerBuild;
        voiceManagerBuild.registerCapabilities();
    }

    public static synchronized boolean isInitialized() {
        return initialized.get();
    }

    public static synchronized VoiceManager getInstance() {
        if (initialized.get()) {
        } else {
            throw new IllegalStateException("Cannot call getInstance() without first calling initialize()");
        }
        return sVoiceManager;
    }
}
