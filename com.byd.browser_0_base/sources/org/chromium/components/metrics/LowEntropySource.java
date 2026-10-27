package org.chromium.components.metrics;

import java.util.Random;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class LowEntropySource {

    public static final class a {
        public static final int a = new Random().nextInt(8000);
    }

    public static final class b {
        public static final int a = new Random().nextInt(8000);
    }

    @CalledByNative
    public static int generateLowEntropySource() {
        return a.a;
    }

    @CalledByNative
    public static int generatePseudoLowEntropySource() {
        return b.a;
    }
}
