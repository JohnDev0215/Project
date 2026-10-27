package c.a.a.g;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: StrUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends c.a.a.f.b {
    public static String r(Object obj, Charset charset) {
        if (obj == null) {
            return null;
        }
        return obj instanceof String ? (String) obj : obj instanceof byte[] ? t((byte[]) obj, charset) : obj instanceof Byte[] ? u((Byte[]) obj, charset) : obj instanceof ByteBuffer ? s((ByteBuffer) obj, charset) : a.e(obj) ? a.g(obj) : obj.toString();
    }

    public static String s(ByteBuffer byteBuffer, Charset charset) {
        if (charset == null) {
            charset = Charset.defaultCharset();
        }
        return charset.decode(byteBuffer).toString();
    }

    public static String t(byte[] bArr, Charset charset) {
        if (bArr == null) {
            return null;
        }
        return charset == null ? new String(bArr) : new String(bArr, charset);
    }

    public static String u(Byte[] bArr, Charset charset) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            Byte b2 = bArr[i2];
            bArr2[i2] = b2 == null ? (byte) -1 : b2.byteValue();
        }
        return t(bArr2, charset);
    }

    public static String v(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static String w(Object obj) {
        return r(obj, c.f3076b);
    }
}
