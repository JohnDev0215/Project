package com.facebook.soloader;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
class MergedSoMapping {
    @Nullable
    static String mapLibName(String str) {
        return null;
    }

    MergedSoMapping() {
    }

    static void invokeJniOnload(String str) {
        throw new IllegalArgumentException("Unknown library: " + str);
    }
}
