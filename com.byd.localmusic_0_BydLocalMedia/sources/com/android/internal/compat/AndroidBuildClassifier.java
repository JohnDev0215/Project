package com.android.internal.compat;

import android.os.Build;

/* JADX INFO: loaded from: classes4.dex */
public class AndroidBuildClassifier {
    public boolean isDebuggableBuild() {
        return Build.IS_DEBUGGABLE;
    }

    public boolean isFinalBuild() {
        return "REL".equals(Build.VERSION.CODENAME);
    }
}
