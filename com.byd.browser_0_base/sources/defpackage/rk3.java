package defpackage;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: Utf8.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class rk3 {
    public static tk3 a;

    /* JADX INFO: compiled from: Utf8.java */
    public static class a {
        public static void a(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) {
            if (!d(b2)) {
                if ((((b2 + 112) + (b << 28)) >> 30) == 0 && !d(b3) && !d(b4)) {
                    int i2 = ((b & 7) << 18) | ((b2 & 63) << 12) | ((b3 & 63) << 6) | (b4 & 63);
                    cArr[i] = (char) ((i2 >>> 10) + 55232);
                    cArr[i + 1] = (char) ((i2 & 1023) + 56320);
                    return;
                }
            }
            throw new IllegalArgumentException("Invalid UTF-8");
        }

        public static void b(byte b, byte b2, byte b3, char[] cArr, int i) {
            if (d(b2) || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || d(b3)))) {
                throw new IllegalArgumentException("Invalid UTF-8");
            }
            cArr[i] = (char) (((b & 15) << 12) | ((b2 & 63) << 6) | (b3 & 63));
        }

        public static void c(byte b, byte b2, char[] cArr, int i) {
            if (b < -62) {
                throw new IllegalArgumentException("Invalid UTF-8: Illegal leading byte in 2 bytes utf");
            }
            if (d(b2)) {
                throw new IllegalArgumentException("Invalid UTF-8: Illegal trailing byte in 2 bytes utf");
            }
            cArr[i] = (char) (((b & 31) << 6) | (b2 & 63));
        }

        public static boolean d(byte b) {
            return b > -65;
        }
    }

    public abstract String a(ByteBuffer byteBuffer, int i, int i2);

    public abstract void b(String str, ByteBuffer byteBuffer);

    public abstract int c(String str);
}
