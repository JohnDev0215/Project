package org.chromium.chrome.browser.lens;

import defpackage.s61;
import java.lang.reflect.Array;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class LensDebugBridge {
    @CalledByNative
    public static String[][] refreshDebugData() {
        s61.b.a.getClass();
        return (String[][]) Array.newInstance((Class<?>) String.class, 0, 0);
    }

    @CalledByNative
    public static void startProactiveDebugMode() {
        s61.b.a.getClass();
    }

    @CalledByNative
    public static void stopProactiveDebugMode() {
        s61.b.a.getClass();
    }
}
