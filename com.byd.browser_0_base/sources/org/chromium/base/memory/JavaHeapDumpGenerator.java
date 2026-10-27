package org.chromium.base.memory;

import android.os.Debug;
import android.util.Log;
import defpackage.C0175b;
import java.io.IOException;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public final class JavaHeapDumpGenerator {
    @CalledByNative
    public static boolean generateHprof(String str) {
        try {
            Debug.dumpHprofData(str);
            return true;
        } catch (IOException e) {
            StringBuilder sbN = C0175b.n("Error writing to file ", str, ". Error: ");
            sbN.append(e.getMessage());
            Log.i("cr_JavaHprofGenerator", sbN.toString());
            return false;
        }
    }
}
