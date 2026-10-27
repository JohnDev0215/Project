package j9;

import r9.C2322l;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2322l f17518a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f17519b;
    public static final String[] c;
    public static final String[] d;

    static {
        C2322l c2322l = C2322l.o;
        f17518a = m6.b.p("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
        f17519b = new String[]{"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
        c = new String[64];
        String[] strArr = new String[256];
        for (int i7 = 0; i7 < 256; i7++) {
            String binaryString = Integer.toBinaryString(i7);
            A7.m.e(binaryString, "toBinaryString(it)");
            strArr[i7] = Q8.o.w0(d9.b.i("%8s", binaryString), ' ', '0');
        }
        d = strArr;
        String[] strArr2 = c;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        int i10 = iArr[0];
        strArr2[i10 | 8] = android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(), strArr2[i10], "|PADDED");
        strArr2[4] = "END_HEADERS";
        strArr2[32] = "PRIORITY";
        strArr2[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i11 = 0; i11 < 3; i11++) {
            int i12 = iArr2[i11];
            int i13 = iArr[0];
            String[] strArr3 = c;
            int i14 = i13 | i12;
            strArr3[i14] = strArr3[i13] + '|' + strArr3[i12];
            StringBuilder sb = new StringBuilder();
            sb.append(strArr3[i13]);
            sb.append('|');
            strArr3[i14 | 8] = android.hardware.bydauto.ac.tsimpl.a.l(sb, strArr3[i12], "|PADDED");
        }
        int length = c.length;
        for (int i15 = 0; i15 < length; i15++) {
            String[] strArr4 = c;
            if (strArr4[i15] == null) {
                strArr4[i15] = d[i15];
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(boolean r4, int r5, int r6, int r7, int r8) {
        /*
            java.lang.String[] r0 = j9.e.f17519b
            int r1 = r0.length
            if (r7 >= r1) goto L8
            r0 = r0[r7]
            goto L16
        L8:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "0x%02x"
            java.lang.String r0 = d9.b.i(r1, r0)
        L16:
            if (r8 != 0) goto L1b
            java.lang.String r7 = ""
            goto L69
        L1b:
            r1 = 2
            java.lang.String[] r2 = j9.e.d
            if (r7 == r1) goto L67
            r1 = 3
            if (r7 == r1) goto L67
            r1 = 4
            if (r7 == r1) goto L5e
            r1 = 6
            if (r7 == r1) goto L5e
            r1 = 7
            if (r7 == r1) goto L67
            r1 = 8
            if (r7 == r1) goto L67
            java.lang.String[] r1 = j9.e.c
            int r3 = r1.length
            if (r8 >= r3) goto L3b
            r1 = r1[r8]
            A7.m.c(r1)
            goto L3d
        L3b:
            r1 = r2[r8]
        L3d:
            r2 = 5
            if (r7 != r2) goto L4d
            r2 = r8 & 4
            if (r2 == 0) goto L4d
            java.lang.String r7 = "PUSH_PROMISE"
            java.lang.String r8 = "HEADERS"
            java.lang.String r7 = Q8.o.x0(r1, r8, r7)
            goto L69
        L4d:
            if (r7 != 0) goto L5c
            r7 = r8 & 32
            if (r7 == 0) goto L5c
            java.lang.String r7 = "COMPRESSED"
            java.lang.String r8 = "PRIORITY"
            java.lang.String r7 = Q8.o.x0(r1, r8, r7)
            goto L69
        L5c:
            r7 = r1
            goto L69
        L5e:
            r7 = 1
            if (r8 != r7) goto L64
            java.lang.String r7 = "ACK"
            goto L69
        L64:
            r7 = r2[r8]
            goto L69
        L67:
            r7 = r2[r8]
        L69:
            if (r4 == 0) goto L6e
            java.lang.String r4 = "<<"
            goto L70
        L6e:
            java.lang.String r4 = ">>"
        L70:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r5, r6, r0, r7}
            java.lang.String r5 = "%s 0x%08x %5d %-13s %s"
            java.lang.String r4 = d9.b.i(r5, r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: j9.e.a(boolean, int, int, int, int):java.lang.String");
    }
}
