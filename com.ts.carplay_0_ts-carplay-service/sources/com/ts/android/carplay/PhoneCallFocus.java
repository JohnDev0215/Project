package com.ts.android.carplay;

import com.ts.android.carplay.CarPlayResourceManager;

/* JADX INFO: loaded from: classes.dex */
public class PhoneCallFocus extends CarPlayResourceManager.AppFocus {
    public PhoneCallFocus() {
        this.mNative = CarPlayNative.getInstance();
    }

    @Override // com.ts.android.carplay.CarPlayResourceManager.AppFocus
    public int release() throws Exception {
        if (this.mNative != null) {
            return this.mNative.appstate(2, 0, false);
        }
        throw new Exception("mNative is null");
    }

    @Override // com.ts.android.carplay.CarPlayResourceManager.AppFocus
    public int request() throws Exception {
        if (this.mNative != null) {
            return this.mNative.appstate(2, 0, true);
        }
        throw new Exception("mNative is null");
    }
}
