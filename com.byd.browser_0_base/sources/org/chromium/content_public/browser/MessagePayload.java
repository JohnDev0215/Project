package org.chromium.content_public.browser;

import defpackage.gq2;
import defpackage.uv;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class MessagePayload {
    public final int a;
    public final String b;
    public final byte[] c;

    public MessagePayload(String str) {
        this.a = 0;
        this.b = str;
        this.c = null;
    }

    public static String b(int i) {
        if (i == -1) {
            return "Invalid";
        }
        if (i == 0) {
            return "String";
        }
        if (i == 1) {
            return "ArrayBuffer";
        }
        throw new uv(gq2.e("Unknown type: ", i));
    }

    public final void a(int i) {
        int i2 = this.a;
        if (i2 == i) {
            return;
        }
        throw new IllegalStateException("Expected " + b(i) + ", but type is " + b(i2));
    }

    public MessagePayload(byte[] bArr) {
        Objects.requireNonNull(bArr, "arrayBuffer cannot be null.");
        this.a = 1;
        this.c = bArr;
        this.b = null;
    }
}
