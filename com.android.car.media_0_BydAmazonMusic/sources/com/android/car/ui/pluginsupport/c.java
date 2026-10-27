package com.android.car.ui.pluginsupport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Trace;
import androidx.annotation.RequiresApi;
import spi.w8;

/* JADX INFO: compiled from: PluginFactorySingleton.java */
/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(28)
@SuppressLint({"LogConditional"})
public final class c {
    public static w8 a;
    public static b b = b.NOT_SET;

    /* JADX INFO: compiled from: PluginFactorySingleton.java */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[b.values().length];
            a = iArr;
            try {
                iArr[b.ENABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[b.DISABLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[b.NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: compiled from: PluginFactorySingleton.java */
    public enum b {
        NOT_SET,
        ENABLED,
        DISABLED
    }

    private c() {
    }

    public static w8 a(Context context) {
        try {
            Trace.beginSection("car-ui-plugin-load");
            return b(context);
        } finally {
            Trace.endSection();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static spi.w8 b(android.content.Context r15) {
        /*
            Method dump skipped, instruction units count: 564
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.ui.pluginsupport.c.b(android.content.Context):spi.w8");
    }
}
