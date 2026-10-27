package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: NfcBlocklist.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bn1 {
    public static final byte[][] b = {new byte[]{-128, 115, -64, 33, -64, 87, 89, 117, 98, 105, 75, 101, 121}, new byte[]{89, 117, 98, 105, 107, 101, 121, 78, 69, 79, 114, 51}};
    public static bn1 c;
    public final ArrayList a = new ArrayList();

    /* JADX WARN: Removed duplicated region for block: B:23:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0079 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public bn1() {
        /*
            r13 = this;
            r13.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r13.a = r0
            java.lang.String r0 = "WebNFCBlockList"
            java.lang.String r1 = "historical_bytes_additions"
            java.lang.String r0 = J.N.MOVY9QtZ(r0, r1)
            if (r0 == 0) goto L7c
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L1b
            goto L7c
        L1b:
            java.lang.String r1 = ","
            java.lang.String[] r0 = r0.split(r1)
            int r1 = r0.length
            r2 = 0
            r3 = r2
        L24:
            if (r3 >= r1) goto L7c
            r4 = r0[r3]
            int r5 = r4.length()
            int r6 = r5 % 2
            r7 = 1
            java.lang.String r8 = "NfcBlocklist"
            if (r6 != r7) goto L39
            java.lang.String r5 = "Length of %s is odd"
            defpackage.fa1.k(r8, r5, r4)
            goto L71
        L39:
            int r6 = r5 / 2
            byte[] r6 = new byte[r6]
            r7 = r2
        L3e:
            if (r7 >= r5) goto L72
            char r9 = r4.charAt(r7)
            int r10 = r7 + 1
            char r10 = r4.charAt(r10)
            r11 = 16
            int r12 = java.lang.Character.digit(r9, r11)
            if (r12 < 0) goto L6c
            int r12 = java.lang.Character.digit(r10, r11)
            if (r12 >= 0) goto L59
            goto L6c
        L59:
            int r12 = r7 / 2
            int r9 = java.lang.Character.digit(r9, r11)
            int r9 = r9 << 4
            int r10 = java.lang.Character.digit(r10, r11)
            int r10 = r10 + r9
            byte r9 = (byte) r10
            r6[r12] = r9
            int r7 = r7 + 2
            goto L3e
        L6c:
            java.lang.String r5 = "Invalid hex character found in %s"
            defpackage.fa1.k(r8, r5, r4)
        L71:
            r6 = 0
        L72:
            if (r6 == 0) goto L79
            java.util.ArrayList r4 = r13.a
            r4.add(r6)
        L79:
            int r3 = r3 + 1
            goto L24
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bn1.<init>():void");
    }
}
