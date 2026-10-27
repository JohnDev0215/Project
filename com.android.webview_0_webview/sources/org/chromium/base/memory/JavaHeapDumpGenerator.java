package org.chromium.base.memory;

import android.os.Debug;
import defpackage.Ts;
import java.io.IOException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class JavaHeapDumpGenerator {
    public static boolean generateHprof(String str) {
        try {
            Debug.dumpHprofData(str);
            return true;
        } catch (IOException e) {
            Ts.d("JavaHprofGenerator", "Error writing to file " + str + ". Error: " + e.getMessage(), new Object[0]);
            return false;
        }
    }
}
