package k6;

import y5.p;

/* JADX INFO: compiled from: Http2.kt */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String[] f7313d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e f7314e = new e();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p6.h f7310a = p6.h.f8870j.c("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String[] f7311b = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String[] f7312c = new String[64];

    static {
        String[] strArr = new String[256];
        for (int i7 = 0; i7 < 256; i7++) {
            String binaryString = Integer.toBinaryString(i7);
            kotlin.jvm.internal.l.d(binaryString, "Integer.toBinaryString(it)");
            strArr[i7] = p.v(d6.b.p("%8s", binaryString), ' ', '0', false, 4, null);
        }
        f7313d = strArr;
        String[] strArr2 = f7312c;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        for (int i8 = 0; i8 < 1; i8++) {
            int i9 = iArr[i8];
            String[] strArr3 = f7312c;
            strArr3[i9 | 8] = kotlin.jvm.internal.l.n(strArr3[i9], "|PADDED");
        }
        String[] strArr4 = f7312c;
        strArr4[4] = "END_HEADERS";
        strArr4[32] = "PRIORITY";
        strArr4[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i10 = 0; i10 < 3; i10++) {
            int i11 = iArr2[i10];
            for (int i12 = 0; i12 < 1; i12++) {
                int i13 = iArr[i12];
                String[] strArr5 = f7312c;
                int i14 = i13 | i11;
                strArr5[i14] = strArr5[i13] + "|" + strArr5[i11];
                strArr5[i14 | 8] = strArr5[i13] + "|" + strArr5[i11] + "|PADDED";
            }
        }
        int length = f7312c.length;
        for (int i15 = 0; i15 < length; i15++) {
            String[] strArr6 = f7312c;
            if (strArr6[i15] == null) {
                strArr6[i15] = f7313d[i15];
            }
        }
    }

    private e() {
    }

    public final String a(int i7, int i8) {
        String str;
        if (i8 == 0) {
            return "";
        }
        if (i7 != 2 && i7 != 3) {
            if (i7 == 4 || i7 == 6) {
                return i8 == 1 ? "ACK" : f7313d[i8];
            }
            if (i7 != 7 && i7 != 8) {
                String[] strArr = f7312c;
                if (i8 < strArr.length) {
                    str = strArr[i8];
                    kotlin.jvm.internal.l.c(str);
                } else {
                    str = f7313d[i8];
                }
                String str2 = str;
                return (i7 != 5 || (i8 & 4) == 0) ? (i7 != 0 || (i8 & 32) == 0) ? str2 : p.w(str2, "PRIORITY", "COMPRESSED", false, 4, null) : p.w(str2, "HEADERS", "PUSH_PROMISE", false, 4, null);
            }
        }
        return f7313d[i8];
    }

    public final String b(int i7) {
        String[] strArr = f7311b;
        return i7 < strArr.length ? strArr[i7] : d6.b.p("0x%02x", Integer.valueOf(i7));
    }

    public final String c(boolean z6, int i7, int i8, int i9, int i10) {
        return d6.b.p("%s 0x%08x %5d %-13s %s", z6 ? "<<" : ">>", Integer.valueOf(i7), Integer.valueOf(i8), b(i9), a(i9, i10));
    }
}
