package com.google.protobuf;

import java.nio.Buffer;

/* JADX INFO: loaded from: classes7.dex */
final class Java8Compatibility {
    static void limit(Buffer b, int limit) {
        b.limit(limit);
    }

    static void position(Buffer b, int position) {
        b.position(position);
    }

    private Java8Compatibility() {
    }
}
