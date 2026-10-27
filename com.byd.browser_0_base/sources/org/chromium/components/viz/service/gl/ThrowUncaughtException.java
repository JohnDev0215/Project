package org.chromium.components.viz.service.gl;

import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ThrowUncaughtException {

    public class a implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
            throw new RuntimeException("Intentional exception not caught by JNI");
        }
    }

    @CalledByNative
    public static void post() {
        ThreadUtils.e(new a());
    }
}
