package com.ts.android.carplay;

import com.ts.android.carplay.CarPlayResourceManager;

/* JADX INFO: loaded from: classes.dex */
public class NavigationFocus extends CarPlayResourceManager.AppFocus {
    public NavigationFocus() {
        this.mNative = CarPlayNative.getInstance();
    }

    @Override // com.ts.android.carplay.CarPlayResourceManager.AppFocus
    public int release() throws Exception {
        if (this.mNative != null) {
            return this.mNative.appstate(3, 0, false);
        }
        throw new Exception("mNative is null");
    }

    @Override // com.ts.android.carplay.CarPlayResourceManager.AppFocus
    public int request() throws Exception {
        if (this.mNative != null) {
            return this.mNative.appstate(3, 0, true);
        }
        throw new Exception("mNative is null");
    }
}
