package org.chromium.chrome.browser;

import J.N;
import defpackage.c6;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class DevToolsServer {
    public final long a = N.M7TBtHQi(this, "chrome");

    @CalledByNative
    public static boolean checkDebugPermission(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(xz.a.getPackageName());
        sb.append(".permission.DEBUG");
        return c6.a(i, i2, xz.a, sb.toString()) == 0;
    }
}
