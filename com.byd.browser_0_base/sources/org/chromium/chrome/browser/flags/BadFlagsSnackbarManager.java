package org.chromium.chrome.browser.flags;

import defpackage.tp2;
import defpackage.wp2;
import defpackage.xp2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public class BadFlagsSnackbarManager {
    @CalledByNative
    public static void show(WindowAndroid windowAndroid, String str) {
        wp2 wp2VarA = xp2.a(windowAndroid);
        if (wp2VarA == null) {
            return;
        }
        tp2 tp2VarA = tp2.a(str, null, 1, 51);
        tp2VarA.i = false;
        tp2VarA.j = 8000;
        wp2VarA.c(tp2VarA);
    }
}
