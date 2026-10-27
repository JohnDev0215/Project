package defpackage;

/* JADX INFO: compiled from: Strings.kt */
/* JADX INFO: loaded from: classes.dex */
public class tu2 extends su2 {
    /* JADX WARN: Code restructure failed: missing block: B:61:?, code lost:
    
        return r12;
     */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x008b A[EDGE_INSN: B:55:0x008b->B:47:0x008b BREAK  A[LOOP:1: B:31:0x0058->B:49:0x008f], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int w1(java.lang.CharSequence r10, java.lang.String r11, int r12, boolean r13) {
        /*
            if (r13 != 0) goto Lf
            boolean r0 = r10 instanceof java.lang.String
            if (r0 != 0) goto L7
            goto Lf
        L7:
            java.lang.String r10 = (java.lang.String) r10
            int r10 = r10.indexOf(r11, r12)
            goto L92
        Lf:
            int r0 = r10.length()
            o11 r1 = new o11
            r2 = 0
            if (r12 >= 0) goto L19
            r12 = r2
        L19:
            int r3 = r10.length()
            if (r0 <= r3) goto L20
            r0 = r3
        L20:
            r1.<init>(r12, r0)
            boolean r0 = r10 instanceof java.lang.String
            int r3 = r1.g
            int r1 = r1.f
            if (r0 == 0) goto L50
            if (r3 <= 0) goto L2f
            if (r12 <= r1) goto L33
        L2f:
            if (r3 >= 0) goto L91
            if (r1 > r12) goto L91
        L33:
            r7 = r10
            java.lang.String r7 = (java.lang.String) r7
            int r9 = r11.length()
            if (r13 != 0) goto L41
            boolean r0 = r11.regionMatches(r2, r7, r12, r9)
            goto L49
        L41:
            r6 = 0
            r4 = r11
            r5 = r13
            r8 = r12
            boolean r0 = r4.regionMatches(r5, r6, r7, r8, r9)
        L49:
            if (r0 == 0) goto L4c
            goto L8b
        L4c:
            if (r12 == r1) goto L91
            int r12 = r12 + r3
            goto L33
        L50:
            if (r3 <= 0) goto L54
            if (r12 <= r1) goto L58
        L54:
            if (r3 >= 0) goto L91
            if (r1 > r12) goto L91
        L58:
            int r0 = r11.length()
            if (r12 < 0) goto L88
            int r4 = r11.length()
            int r4 = r4 - r0
            if (r4 < 0) goto L88
            int r4 = r10.length()
            int r4 = r4 - r0
            if (r12 <= r4) goto L6d
            goto L88
        L6d:
            r4 = r2
        L6e:
            if (r4 >= r0) goto L86
            int r5 = r2 + r4
            char r5 = r11.charAt(r5)
            int r6 = r12 + r4
            char r6 = r10.charAt(r6)
            boolean r5 = defpackage.a8.x(r5, r6, r13)
            if (r5 != 0) goto L83
            goto L88
        L83:
            int r4 = r4 + 1
            goto L6e
        L86:
            r0 = 1
            goto L89
        L88:
            r0 = r2
        L89:
            if (r0 == 0) goto L8d
        L8b:
            r10 = r12
            goto L92
        L8d:
            if (r12 == r1) goto L91
            int r12 = r12 + r3
            goto L58
        L91:
            r10 = -1
        L92:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.tu2.w1(java.lang.CharSequence, java.lang.String, int, boolean):int");
    }

    public static int x1(String str, char c, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return str.indexOf(c, i);
    }

    public static String y1(String str) {
        i21.e(str, "<this>");
        i21.e(str, "missingDelimiterValue");
        int iLastIndexOf = str.lastIndexOf(46, str.length() - 1);
        if (iLastIndexOf == -1) {
            return str;
        }
        String strSubstring = str.substring(iLastIndexOf + 1, str.length());
        i21.d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }
}
