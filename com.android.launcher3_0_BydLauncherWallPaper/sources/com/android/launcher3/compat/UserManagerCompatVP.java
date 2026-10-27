package com.android.launcher3.compat;

import android.content.Context;
import android.os.UserHandle;

/* JADX INFO: loaded from: classes4.dex */
public class UserManagerCompatVP extends UserManagerCompatVNMr1 {
    UserManagerCompatVP(Context context) {
        super(context);
    }

    @Override // com.android.launcher3.compat.UserManagerCompatVNMr1, com.android.launcher3.compat.UserManagerCompat
    public boolean requestQuietModeEnabled(boolean enableQuietMode, UserHandle user) {
        return this.mUserManager.requestQuietModeEnabled(enableQuietMode, user);
    }
}
