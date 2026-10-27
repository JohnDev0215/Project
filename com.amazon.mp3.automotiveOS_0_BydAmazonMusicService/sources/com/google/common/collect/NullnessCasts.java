package com.google.common.collect;

import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
final class NullnessCasts {
    static <T> T uncheckedCastNullableTToT(@CheckForNull T t) {
        return t;
    }

    static <T> T unsafeNull() {
        return null;
    }

    private NullnessCasts() {
    }
}
