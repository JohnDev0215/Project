package com.android.car.developeroptions.password;

import android.content.Context;
import android.content.Intent;
import com.android.internal.widget.LockscreenCredential;

/* JADX INFO: loaded from: classes.dex */
public class ManagedLockPasswordProvider {
    Intent createIntent(boolean z, LockscreenCredential lockscreenCredential) {
        return null;
    }

    CharSequence getPickerOptionTitle(boolean z) {
        return "";
    }

    boolean isManagedPasswordChoosable() {
        return false;
    }

    boolean isSettingManagedPasswordSupported() {
        return false;
    }

    public static ManagedLockPasswordProvider get(Context context, int i) {
        return new ManagedLockPasswordProvider();
    }

    protected ManagedLockPasswordProvider() {
    }
}
