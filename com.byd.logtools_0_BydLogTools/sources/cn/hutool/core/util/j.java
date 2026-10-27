package cn.hutool.core.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: StrUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends c.a.a.d.b {
    public static String s(Object obj, Charset charset) {
        if (obj == null) {
            return null;
        }
        return obj instanceof String ? (String) obj : obj instanceof byte[] ? u((byte[]) obj, charset) : obj instanceof Byte[] ? v((Byte[]) obj, charset) : obj instanceof ByteBuffer ? t((ByteBuffer) obj, charset) : a.e(obj) ? a.g(obj) : obj.toString();
    }

    public static String t(ByteBuffer byteBuffer, Charset charset) {
        if (charset == null) {
            charset = Charset.defaultCharset();
        }
        return charset.decode(byteBuffer).toString();
    }

    public static String u(byte[] bArr, Charset charset) {
        if (bArr == null) {
            return null;
        }
        return charset == null ? new String(bArr) : new String(bArr, charset);
    }

    public static String v(Byte[] bArr, Charset charset) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            Byte b2 = bArr[i];
            bArr2[i] = b2 == null ? (byte) -1 : b2.byteValue();
        }
        return u(bArr2, charset);
    }

    public static String w(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static String x(Object obj) {
        return s(obj, c.f2949b);
    }
}
