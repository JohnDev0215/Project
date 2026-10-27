package com.google.common.io;

import java.nio.Buffer;

/* JADX INFO: loaded from: classes6.dex */
final class Java8Compatibility {
    static void clear(Buffer buffer) {
        buffer.clear();
    }

    static void flip(Buffer buffer) {
        buffer.flip();
    }

    private Java8Compatibility() {
    }
}
