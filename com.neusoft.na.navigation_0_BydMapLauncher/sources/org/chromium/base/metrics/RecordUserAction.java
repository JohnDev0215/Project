package org.chromium.base.metrics;

import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("base::android")
public class RecordUserAction {
    private static boolean sIsDisabledForTests = false;

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeRecordUserAction(String str);

    public static void disableForTests() {
        sIsDisabledForTests = true;
    }

    public static void record(final String str) {
        if (sIsDisabledForTests) {
            return;
        }
        if (ThreadUtils.runningOnUiThread()) {
            nativeRecordUserAction(str);
        } else {
            ThreadUtils.runOnUiThread(new Runnable() { // from class: org.chromium.base.metrics.RecordUserAction.1
                @Override // java.lang.Runnable
                public void run() {
                    RecordUserAction.nativeRecordUserAction(str);
                }
            });
        }
    }
}
