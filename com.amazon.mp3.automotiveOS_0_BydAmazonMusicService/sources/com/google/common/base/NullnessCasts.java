package com.google.common.base;

import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
final class NullnessCasts {
    static <T> T uncheckedCastNullableTToT(@CheckForNull T t) {
        return t;
    }

    private NullnessCasts() {
    }
}
