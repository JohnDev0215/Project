package com.byd.card.floatcard;

import android.os.Trace;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
public class TraceHelper {
    public static final int FLAG_ALLOW_BINDER_TRACKING = 1;
    public static final int FLAG_CHECK_FOR_RACE_CONDITIONS = 4;
    public static final int FLAG_IGNORE_BINDERS = 2;
    public static final int FLAG_UI_EVENT = 5;
    public static TraceHelper INSTANCE = new TraceHelper();

    public Object beginFlagsOverride(int flags) {
        return null;
    }

    public void endFlagsOverride(Object token) {
    }

    public Object beginSection(String sectionName) {
        return beginSection(sectionName, 0);
    }

    public Object beginSection(String sectionName, int flags) {
        Trace.beginSection(sectionName);
        return null;
    }

    public void endSection(Object token) {
        Trace.endSection();
    }

    public static <T> T allowIpcs(String rpcName, Supplier<T> supplier) {
        Object objBeginSection = INSTANCE.beginSection(rpcName, 2);
        try {
            return supplier.get();
        } finally {
            INSTANCE.endSection(objBeginSection);
        }
    }

    public static <T> T whitelistIpcs(String rpcName, Supplier<T> supplier) {
        Object objBeginSection = INSTANCE.beginSection(rpcName, 2);
        try {
            return supplier.get();
        } finally {
            INSTANCE.endSection(objBeginSection);
        }
    }
}
