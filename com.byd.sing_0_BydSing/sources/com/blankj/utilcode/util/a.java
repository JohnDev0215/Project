package com.blankj.utilcode.util;

import android.app.Activity;
import android.os.Build;

/* JADX INFO: compiled from: ActivityUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static boolean a(Activity activity) {
        return (activity == null || activity.isFinishing() || (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed())) ? false : true;
    }
}
