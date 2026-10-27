package okhttp3.internal.http2;

import okhttp3.internal.Util;
import okio.ByteString;
import p.qo;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Http2 {
    public static final Http2 a = new Http2();
    public static final ByteString b;
    public static final String[] c;
    public static final String[] d;
    public static final String[] e;

    static {
        ByteString byteString = ByteString.k;
        b = qo.p("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
        c = new String[]{"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
        d = new String[64];
        String[] strArr = new String[256];
        for (int i = 0; i < 256; i++) {
            String binaryString = Integer.toBinaryString(i);
            v03.i(binaryString, "toBinaryString(it)");
            String strReplace = Util.i("%8s", binaryString).replace(' ', '0');
            v03.i(strReplace, "this as java.lang.String…replace(oldChar, newChar)");
            strArr[i] = strReplace;
        }
        e = strArr;
        String[] strArr2 = d;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        int i2 = iArr[0];
        strArr2[i2 | 8] = u4.m(new StringBuilder(), strArr2[i2], "|PADDED");
        strArr2[4] = "END_HEADERS";
        strArr2[32] = "PRIORITY";
        strArr2[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i3 = 0; i3 < 3; i3++) {
            int i4 = iArr2[i3];
            int i5 = iArr[0];
            String[] strArr3 = d;
            int i6 = i5 | i4;
            strArr3[i6] = strArr3[i5] + '|' + strArr3[i4];
            StringBuilder sb = new StringBuilder();
            sb.append(strArr3[i5]);
            sb.append('|');
            strArr3[i6 | 8] = u4.m(sb, strArr3[i4], "|PADDED");
        }
        int length = d.length;
        for (int i7 = 0; i7 < length; i7++) {
            String[] strArr4 = d;
            if (strArr4[i7] == null) {
                strArr4[i7] = e[i7];
            }
        }
    }

    private Http2() {
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(int r4, int r5, int r6, int r7, boolean r8) {
        /*
            java.lang.String[] r0 = okhttp3.internal.http2.Http2.c
            int r1 = r0.length
            if (r6 >= r1) goto L8
            r0 = r0[r6]
            goto L16
        L8:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "0x%02x"
            java.lang.String r0 = okhttp3.internal.Util.i(r1, r0)
        L16:
            if (r7 != 0) goto L1b
            java.lang.String r6 = ""
            goto L69
        L1b:
            r1 = 2
            java.lang.String[] r2 = okhttp3.internal.http2.Http2.e
            if (r6 == r1) goto L67
            r1 = 3
            if (r6 == r1) goto L67
            r1 = 4
            if (r6 == r1) goto L5e
            r1 = 6
            if (r6 == r1) goto L5e
            r1 = 7
            if (r6 == r1) goto L67
            r1 = 8
            if (r6 == r1) goto L67
            java.lang.String[] r1 = okhttp3.internal.http2.Http2.d
            int r3 = r1.length
            if (r7 >= r3) goto L3b
            r1 = r1[r7]
            p.v03.h(r1)
            goto L3d
        L3b:
            r1 = r2[r7]
        L3d:
            r2 = 5
            if (r6 != r2) goto L4d
            r2 = r7 & 4
            if (r2 == 0) goto L4d
            java.lang.String r6 = "HEADERS"
            java.lang.String r7 = "PUSH_PROMISE"
            java.lang.String r6 = p.ng2.e0(r1, r6, r7)
            goto L69
        L4d:
            if (r6 != 0) goto L5c
            r6 = r7 & 32
            if (r6 == 0) goto L5c
            java.lang.String r6 = "PRIORITY"
            java.lang.String r7 = "COMPRESSED"
            java.lang.String r6 = p.ng2.e0(r1, r6, r7)
            goto L69
        L5c:
            r6 = r1
            goto L69
        L5e:
            r6 = 1
            if (r7 != r6) goto L64
            java.lang.String r6 = "ACK"
            goto L69
        L64:
            r6 = r2[r7]
            goto L69
        L67:
            r6 = r2[r7]
        L69:
            if (r8 == 0) goto L6e
            java.lang.String r7 = "<<"
            goto L70
        L6e:
            java.lang.String r7 = ">>"
        L70:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object[] r4 = new java.lang.Object[]{r7, r4, r5, r0, r6}
            java.lang.String r5 = "%s 0x%08x %5d %-13s %s"
            java.lang.String r4 = okhttp3.internal.Util.i(r5, r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2.a(int, int, int, int, boolean):java.lang.String");
    }
}
