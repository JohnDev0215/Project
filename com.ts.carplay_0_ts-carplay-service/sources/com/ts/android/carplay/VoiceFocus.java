package com.ts.android.carplay;

import com.ts.android.carplay.CarPlayResourceManager;

/* JADX INFO: loaded from: classes.dex */
public class VoiceFocus extends CarPlayResourceManager.AppFocus {
    public VoiceFocus() {
        this.mNative = CarPlayNative.getInstance();
    }

    @Override // com.ts.android.carplay.CarPlayResourceManager.AppFocus
    public int request() throws Exception {
        if (this.mNative != null) {
            return this.mNative.appstate(1, -1, true);
        }
        throw new Exception("mNative is null");
    }

    public int request(int initialSpeechMode) throws Exception {
        if (this.mNative != null) {
            return this.mNative.appstate(1, initialSpeechMode, true);
        }
        throw new Exception("mNative is null");
    }

    public int changeMode(int speechMode) throws Exception {
        if (this.mNative != null) {
            return this.mNative.appstate(1, speechMode, true);
        }
        throw new Exception("mNative is null");
    }

    @Override // com.ts.android.carplay.CarPlayResourceManager.AppFocus
    public int release() throws Exception {
        if (this.mNative != null) {
            return this.mNative.appstate(1, -1, false);
        }
        throw new Exception("mNative is null");
    }
}
