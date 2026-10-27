package i7;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f5534a = Charset.forName("UTF-8");

    public static boolean a(byte[] bArr, int i8, byte[] bArr2, int i9, int i10) {
        for (int i11 = 0; i11 < i10; i11++) {
            if (bArr[i11 + i8] != bArr2[i11 + i9]) {
                return false;
            }
        }
        return true;
    }

    public static void b(long j8, long j9, long j10) {
        if ((j9 | j10) < 0 || j9 > j8 || j8 - j9 < j10) {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", Long.valueOf(j8), Long.valueOf(j9), Long.valueOf(j10)));
        }
    }
}
