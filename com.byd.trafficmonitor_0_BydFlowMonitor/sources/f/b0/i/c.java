package f.b0.i;

import java.io.IOException;
import okio.ByteString;

/* JADX INFO: compiled from: Http2.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ByteString f5147a = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f5148b = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f5149c = new String[64];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String[] f5150d = new String[256];

    static {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            String[] strArr = f5150d;
            if (i3 >= strArr.length) {
                break;
            }
            strArr[i3] = f.b0.c.l("%8s", Integer.toBinaryString(i3)).replace(' ', '0');
            i3++;
        }
        String[] strArr2 = f5149c;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        for (int i4 = 0; i4 < 1; i4++) {
            int i5 = iArr[i4];
            String[] strArr3 = f5149c;
            strArr3[i5 | 8] = strArr3[i5] + "|PADDED";
        }
        String[] strArr4 = f5149c;
        strArr4[4] = "END_HEADERS";
        strArr4[32] = "PRIORITY";
        strArr4[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i6 = 0; i6 < 3; i6++) {
            int i7 = iArr2[i6];
            for (int i8 = 0; i8 < 1; i8++) {
                int i9 = iArr[i8];
                String[] strArr5 = f5149c;
                int i10 = i9 | i7;
                strArr5[i10] = strArr5[i9] + '|' + strArr5[i7];
                strArr5[i10 | 8] = strArr5[i9] + '|' + strArr5[i7] + "|PADDED";
            }
        }
        while (true) {
            String[] strArr6 = f5149c;
            if (i2 >= strArr6.length) {
                return;
            }
            if (strArr6[i2] == null) {
                strArr6[i2] = f5150d[i2];
            }
            i2++;
        }
    }

    public static String a(byte b2, byte b3) {
        if (b3 == 0) {
            return "";
        }
        if (b2 != 2 && b2 != 3) {
            if (b2 == 4 || b2 == 6) {
                return b3 == 1 ? "ACK" : f5150d[b3];
            }
            if (b2 != 7 && b2 != 8) {
                String[] strArr = f5149c;
                String str = b3 < strArr.length ? strArr[b3] : f5150d[b3];
                return (b2 != 5 || (b3 & 4) == 0) ? (b2 != 0 || (b3 & 32) == 0) ? str : str.replace("PRIORITY", "COMPRESSED") : str.replace("HEADERS", "PUSH_PROMISE");
            }
        }
        return f5150d[b3];
    }

    public static String b(boolean z, int i2, int i3, byte b2, byte b3) {
        String[] strArr = f5148b;
        String strL = b2 < strArr.length ? strArr[b2] : f.b0.c.l("0x%02x", Byte.valueOf(b2));
        String strA = a(b2, b3);
        Object[] objArr = new Object[5];
        objArr[0] = z ? "<<" : ">>";
        objArr[1] = Integer.valueOf(i2);
        objArr[2] = Integer.valueOf(i3);
        objArr[3] = strL;
        objArr[4] = strA;
        return f.b0.c.l("%s 0x%08x %5d %-13s %s", objArr);
    }

    public static IllegalArgumentException c(String str, Object... objArr) {
        throw new IllegalArgumentException(f.b0.c.l(str, objArr));
    }

    public static IOException d(String str, Object... objArr) throws IOException {
        throw new IOException(f.b0.c.l(str, objArr));
    }
}
