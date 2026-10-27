package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class ju0 extends vr2 {
    /* JADX WARN: Removed duplicated region for block: B:31:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.io.File z(java.io.File r7) {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "image_cache"
            r0.<init>(r1)
            java.lang.String r1 = r0.getPath()
            java.lang.String r2 = "path"
            p.v03.i(r1, r2)
            char r2 = java.io.File.separatorChar
            r3 = 0
            r4 = 4
            int r5 = kotlin.text.b.n0(r1, r2, r3, r3, r4)
            r6 = 1
            if (r5 != 0) goto L3c
            int r5 = r1.length()
            if (r5 <= r6) goto L9f
            char r5 = r1.charAt(r6)
            if (r5 != r2) goto L9f
            r5 = 2
            int r5 = kotlin.text.b.n0(r1, r2, r5, r3, r4)
            if (r5 < 0) goto L9f
            int r5 = r5 + r6
            int r3 = kotlin.text.b.n0(r1, r2, r5, r3, r4)
            if (r3 < 0) goto L37
            int r3 = r3 + r6
            goto L58
        L37:
            int r3 = r1.length()
            goto L58
        L3c:
            r3 = 58
            if (r5 <= 0) goto L4b
            int r4 = r5 + (-1)
            char r4 = r1.charAt(r4)
            if (r4 != r3) goto L4b
            int r3 = r5 + 1
            goto L58
        L4b:
            r4 = -1
            if (r5 != r4) goto L5b
            boolean r3 = kotlin.text.b.j0(r1, r3)
            if (r3 == 0) goto L5b
            int r3 = r1.length()
        L58:
            if (r3 <= 0) goto L5b
            goto L9f
        L5b:
            java.lang.String r7 = r7.toString()
            java.lang.String r1 = "this.toString()"
            p.v03.i(r7, r1)
            int r1 = r7.length()
            if (r1 != 0) goto L6b
            goto L71
        L6b:
            boolean r1 = kotlin.text.b.j0(r7, r2)
            if (r1 == 0) goto L87
        L71:
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r7)
            r2.append(r0)
            java.lang.String r7 = r2.toString()
            r1.<init>(r7)
        L85:
            r0 = r1
            goto L9f
        L87:
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r7)
            r3.append(r2)
            r3.append(r0)
            java.lang.String r7 = r3.toString()
            r1.<init>(r7)
            goto L85
        L9f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ju0.z(java.io.File):java.io.File");
    }
}
