package com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear;

/* JADX INFO: loaded from: classes5.dex */
public final class Checks {
    public static void checkNotNull(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static void checkArgument(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    private Checks() {
    }
}
