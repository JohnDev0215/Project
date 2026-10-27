package com.byd.ime.subtype;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: SubtypeDebugger.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007J\u001c\u0010\u0007\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007J\u0012\u0010\n\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/byd/ime/subtype/SubtypeDebugger;", "", "()V", "TAG", "", "debug", "", "logE", "", "info", "logI", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SubtypeDebugger {
    public static final SubtypeDebugger INSTANCE = new SubtypeDebugger();
    private static String TAG = "SubtypeDebugger";
    private static final boolean debug = true;

    private SubtypeDebugger() {
    }

    @JvmStatic
    public static final void logE(String info) {
        Log.e(TAG, info);
    }

    @JvmStatic
    public static final void logE(String TAG2, String info) {
        Log.e(TAG2, info);
    }

    @JvmStatic
    public static final void logI(String info) {
        Log.i(TAG, info);
    }
}
