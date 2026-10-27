package org.chromium.chrome.browser.notifications;

import android.graphics.Bitmap;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class ActionInfo {
    public final String a;
    public final Bitmap b;
    public final int c;
    public final String d;

    public ActionInfo(String str, Bitmap bitmap, int i, String str2) {
        this.a = str;
        this.b = bitmap;
        this.c = i;
        this.d = str2;
    }

    @CalledByNative
    public static ActionInfo createActionInfo(String str, Bitmap bitmap, int i, String str2) {
        return new ActionInfo(str, bitmap, i, str2);
    }
}
