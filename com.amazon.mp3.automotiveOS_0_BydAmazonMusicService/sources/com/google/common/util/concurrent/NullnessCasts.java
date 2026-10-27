package com.google.common.util.concurrent;

import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
final class NullnessCasts {
    static <T> T uncheckedCastNullableTToT(@CheckForNull T t) {
        return t;
    }

    static <T> T uncheckedNull() {
        return null;
    }

    private NullnessCasts() {
    }
}
