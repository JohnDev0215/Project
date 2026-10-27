package defpackage;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: renamed from: Gl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0119Gl {
    public static final Charset a = Charset.forName("UTF-8");
    public static final byte[] b;

    static {
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        b = bArr;
        ByteBuffer.wrap(bArr);
        AbstractC1010jl.f(b);
    }

    public static Object a(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw null;
    }

    public static Object b(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }

    public static int c(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int d(byte[] bArr) {
        int length = bArr.length;
        int i = length;
        for (int i2 = 0; i2 < 0 + length; i2++) {
            i = (i * 31) + bArr[i2];
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    public static int e(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static Object f(Object obj, Object obj2) {
        AbstractC0065Dl abstractC0065Dl = (AbstractC0065Dl) ((InterfaceC0509am) obj);
        AbstractC1891zl abstractC1891zl = (AbstractC1891zl) abstractC0065Dl.c(EnumC0047Cl.NEW_BUILDER, null, null);
        abstractC1891zl.b();
        AbstractC0065Dl abstractC0065Dl2 = abstractC1891zl.t;
        C1011jm.c.b(abstractC0065Dl2).b(abstractC0065Dl2, abstractC0065Dl);
        InterfaceC0509am interfaceC0509am = (InterfaceC0509am) obj2;
        if (!abstractC1891zl.s.getClass().isInstance(interfaceC0509am)) {
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
        abstractC1891zl.b();
        AbstractC0065Dl abstractC0065Dl3 = abstractC1891zl.t;
        C1011jm.c.b(abstractC0065Dl3).b(abstractC0065Dl3, (AbstractC0065Dl) ((AbstractC0315Rk) interfaceC0509am));
        return abstractC1891zl.a();
    }

    public static int g(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    public static String h(byte[] bArr) {
        return new String(bArr, a);
    }
}
