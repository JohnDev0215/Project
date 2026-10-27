package androidx.datastore.preferences.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0959w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f11890a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f11891b;

    static {
        Charset.forName("US-ASCII");
        f11890a = Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        f11891b = bArr;
        ByteBuffer.wrap(bArr);
        try {
            new C0945h(bArr, 0, 0, false).e(0);
        } catch (C0961y e10) {
            throw new IllegalArgumentException(e10);
        }
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static int b(long j3) {
        return (int) (j3 ^ (j3 >>> 32));
    }
}
