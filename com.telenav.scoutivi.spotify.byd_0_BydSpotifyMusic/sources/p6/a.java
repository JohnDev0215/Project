package p6;

import p6.h;

/* JADX INFO: compiled from: -Base64.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final byte[] f8848a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final byte[] f8849b;

    static {
        h.a aVar = h.f8870j;
        f8848a = aVar.c("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").e();
        f8849b = aVar.c("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").e();
    }

    public static final String a(byte[] encodeBase64, byte[] map) {
        kotlin.jvm.internal.l.e(encodeBase64, "$this$encodeBase64");
        kotlin.jvm.internal.l.e(map, "map");
        byte[] bArr = new byte[((encodeBase64.length + 2) / 3) * 4];
        int length = encodeBase64.length - (encodeBase64.length % 3);
        int i7 = 0;
        int i8 = 0;
        while (i7 < length) {
            int i9 = i7 + 1;
            byte b7 = encodeBase64[i7];
            int i10 = i9 + 1;
            byte b8 = encodeBase64[i9];
            int i11 = i10 + 1;
            byte b9 = encodeBase64[i10];
            int i12 = i8 + 1;
            bArr[i8] = map[(b7 & 255) >> 2];
            int i13 = i12 + 1;
            bArr[i12] = map[((b7 & 3) << 4) | ((b8 & 255) >> 4)];
            int i14 = i13 + 1;
            bArr[i13] = map[((b8 & 15) << 2) | ((b9 & 255) >> 6)];
            i8 = i14 + 1;
            bArr[i14] = map[b9 & 63];
            i7 = i11;
        }
        int length2 = encodeBase64.length - length;
        if (length2 == 1) {
            byte b10 = encodeBase64[i7];
            int i15 = i8 + 1;
            bArr[i8] = map[(b10 & 255) >> 2];
            int i16 = i15 + 1;
            bArr[i15] = map[(b10 & 3) << 4];
            byte b11 = (byte) 61;
            bArr[i16] = b11;
            bArr[i16 + 1] = b11;
        } else if (length2 == 2) {
            int i17 = i7 + 1;
            byte b12 = encodeBase64[i7];
            byte b13 = encodeBase64[i17];
            int i18 = i8 + 1;
            bArr[i8] = map[(b12 & 255) >> 2];
            int i19 = i18 + 1;
            bArr[i18] = map[((b12 & 3) << 4) | ((b13 & 255) >> 4)];
            bArr[i19] = map[(b13 & 15) << 2];
            bArr[i19 + 1] = (byte) 61;
        }
        return b.b(bArr);
    }

    public static /* synthetic */ String b(byte[] bArr, byte[] bArr2, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            bArr2 = f8848a;
        }
        return a(bArr, bArr2);
    }
}
