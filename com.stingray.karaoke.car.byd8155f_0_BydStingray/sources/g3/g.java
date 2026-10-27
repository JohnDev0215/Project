package g3;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    public CharSequence c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f14835a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f14836b = 0;
    public int d = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f14837e = -3.4028235E38f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f14838f = 1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f14839g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f14840h = -3.4028235E38f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f14841i = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f14842j = 1.0f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f14843k = Integer.MIN_VALUE;

    /* JADX WARN: Removed duplicated region for block: B:20:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final g2.C1288a a() {
        /*
            r13 = this;
            float r0 = r13.f14840h
            r1 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            r3 = 0
            r4 = 1056964608(0x3f000000, float:0.5)
            r5 = 1065353216(0x3f800000, float:1.0)
            r6 = 5
            r7 = 4
            if (r2 == 0) goto L11
            goto L1c
        L11:
            int r0 = r13.d
            if (r0 == r7) goto L1b
            if (r0 == r6) goto L19
            r0 = r4
            goto L1c
        L19:
            r0 = r5
            goto L1c
        L1b:
            r0 = r3
        L1c:
            int r2 = r13.f14841i
            r8 = -2147483648(0xffffffff80000000, float:-0.0)
            r9 = 3
            r10 = 2
            r11 = 1
            if (r2 == r8) goto L26
            goto L35
        L26:
            int r2 = r13.d
            if (r2 == r11) goto L34
            if (r2 == r9) goto L32
            if (r2 == r7) goto L34
            if (r2 == r6) goto L32
            r2 = r11
            goto L35
        L32:
            r2 = r10
            goto L35
        L34:
            r2 = 0
        L35:
            g2.a r8 = new g2.a
            r8.<init>()
            int r12 = r13.d
            if (r12 == r11) goto L55
            if (r12 == r10) goto L52
            if (r12 == r9) goto L4f
            if (r12 == r7) goto L55
            if (r12 == r6) goto L4f
            java.lang.String r6 = "Unknown textAlignment: "
            java.lang.String r7 = "WebvttCueParser"
            h2.AbstractC1347d.p(r6, r12, r7)
            r6 = 0
            goto L57
        L4f:
            android.text.Layout$Alignment r6 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            goto L57
        L52:
            android.text.Layout$Alignment r6 = android.text.Layout.Alignment.ALIGN_CENTER
            goto L57
        L55:
            android.text.Layout$Alignment r6 = android.text.Layout.Alignment.ALIGN_NORMAL
        L57:
            r8.c = r6
            float r6 = r13.f14837e
            int r7 = r13.f14838f
            int r9 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r9 == 0) goto L6d
            if (r7 != 0) goto L6d
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 < 0) goto L6b
            int r3 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r3 <= 0) goto L6d
        L6b:
            r1 = r5
            goto L74
        L6d:
            if (r9 == 0) goto L71
            r1 = r6
            goto L74
        L71:
            if (r7 != 0) goto L74
            goto L6b
        L74:
            r8.f14758e = r1
            r8.f14759f = r7
            int r1 = r13.f14839g
            r8.f14760g = r1
            r8.f14761h = r0
            r8.f14762i = r2
            float r1 = r13.f14842j
            if (r2 == 0) goto L9f
            if (r2 == r11) goto L93
            if (r2 != r10) goto L89
            goto La1
        L89:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = java.lang.String.valueOf(r2)
            r13.<init>(r0)
            throw r13
        L93:
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            r3 = 1073741824(0x40000000, float:2.0)
            if (r2 > 0) goto L9b
            float r0 = r0 * r3
            goto La1
        L9b:
            float r5 = r5 - r0
            float r0 = r5 * r3
            goto La1
        L9f:
            float r0 = r5 - r0
        La1:
            float r0 = java.lang.Math.min(r1, r0)
            r8.l = r0
            int r0 = r13.f14843k
            r8.f14767p = r0
            java.lang.CharSequence r13 = r13.c
            if (r13 == 0) goto Lb1
            r8.f14756a = r13
        Lb1:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: g3.g.a():g2.a");
    }
}
