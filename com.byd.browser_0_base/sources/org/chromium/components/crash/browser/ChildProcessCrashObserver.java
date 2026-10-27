package org.chromium.components.crash.browser;

import android.util.Log;
import defpackage.d10;
import defpackage.i9;
import defpackage.ia1;
import defpackage.xz;
import java.io.File;
import java.util.regex.Pattern;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class ChildProcessCrashObserver {
    public static a a;

    public interface a {
    }

    @CalledByNative
    public static void childCrashed(int i) {
        if (a == null) {
            Log.w("cr_ChildCrashObserver", "Ignoring crash observed before a callback was registered...");
            return;
        }
        d10 d10Var = new d10(xz.a.getCacheDir());
        d10Var.f();
        File[] fileArrG = d10Var.g(Pattern.compile("\\.dmp" + Integer.toString(i) + "\\z"));
        File file = fileArrG.length > 0 ? fileArrG[0] : null;
        if (file != null) {
            i9.e.execute(new ia1(file));
            return;
        }
        Log.e("cr_BrowserInitializer", "Missing dump for child " + i);
    }
}
