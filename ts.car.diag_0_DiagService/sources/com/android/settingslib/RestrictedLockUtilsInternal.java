package com.android.settingslib;

import android.content.Context;
import android.os.UserHandle;
import android.os.UserManager;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class RestrictedLockUtilsInternal extends a {

    @VisibleForTesting
    static Proxy sProxy = new Proxy();

    /* JADX INFO: compiled from: caradapter-bug */
    @VisibleForTesting
    static class Proxy {
        Proxy() {
        }
    }

    public static boolean d(Context context, String str, int i) {
        return ((UserManager) context.getSystemService("user")).hasBaseUserRestriction(str, UserHandle.of(i));
    }
}
