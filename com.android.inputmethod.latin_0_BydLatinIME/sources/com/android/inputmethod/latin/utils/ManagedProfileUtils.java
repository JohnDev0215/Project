package com.android.inputmethod.latin.utils;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class ManagedProfileUtils {
    private static ManagedProfileUtils INSTANCE = new ManagedProfileUtils();
    private static ManagedProfileUtils sTestInstance;

    public boolean hasWorkProfile(final Context context) {
        return false;
    }

    private ManagedProfileUtils() {
    }

    public static void setTestInstance(final ManagedProfileUtils testInstance) {
        sTestInstance = testInstance;
    }

    public static ManagedProfileUtils getInstance() {
        ManagedProfileUtils managedProfileUtils = sTestInstance;
        return managedProfileUtils == null ? INSTANCE : managedProfileUtils;
    }
}
