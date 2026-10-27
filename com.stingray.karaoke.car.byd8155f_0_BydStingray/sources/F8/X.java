package F8;

/* JADX INFO: loaded from: classes.dex */
public final class X extends AbstractC0279n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f2456m;

    public X(String str) {
        this.f2456m = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void T0(int r9) {
        /*
            r0 = 4
            r1 = 1
            if (r9 == r1) goto L9
            if (r9 == r0) goto L9
            java.lang.String r2 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            goto Lb
        L9:
            java.lang.String r2 = "@NotNull method %s.%s must not return null"
        Lb:
            r3 = 3
            r4 = 2
            if (r9 == r1) goto L13
            if (r9 == r0) goto L13
            r5 = r3
            goto L14
        L13:
            r5 = r4
        L14:
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/types/TypeUtils$SpecialType"
            r7 = 0
            if (r9 == r1) goto L30
            if (r9 == r4) goto L2b
            if (r9 == r3) goto L26
            if (r9 == r0) goto L30
            java.lang.String r8 = "newAttributes"
            r5[r7] = r8
            goto L32
        L26:
            java.lang.String r8 = "kotlinTypeRefiner"
            r5[r7] = r8
            goto L32
        L2b:
            java.lang.String r8 = "delegate"
            r5[r7] = r8
            goto L32
        L30:
            r5[r7] = r6
        L32:
            java.lang.String r7 = "refine"
            if (r9 == r1) goto L3e
            if (r9 == r0) goto L3b
            r5[r1] = r6
            goto L42
        L3b:
            r5[r1] = r7
            goto L42
        L3e:
            java.lang.String r6 = "toString"
            r5[r1] = r6
        L42:
            if (r9 == r1) goto L56
            if (r9 == r4) goto L52
            if (r9 == r3) goto L4f
            if (r9 == r0) goto L56
            java.lang.String r3 = "replaceAttributes"
            r5[r4] = r3
            goto L56
        L4f:
            r5[r4] = r7
            goto L56
        L52:
            java.lang.String r3 = "replaceDelegate"
            r5[r4] = r3
        L56:
            java.lang.String r2 = java.lang.String.format(r2, r5)
            if (r9 == r1) goto L64
            if (r9 == r0) goto L64
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r9.<init>(r2)
            goto L69
        L64:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r2)
        L69:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: F8.X.T0(int):void");
    }

    @Override // F8.AbstractC0279n, F8.AbstractC0287w
    /* JADX INFO: renamed from: I0 */
    public final AbstractC0287w M0(G8.f fVar) {
        if (fVar != null) {
            return this;
        }
        T0(3);
        throw null;
    }

    @Override // F8.A, F8.a0
    public final /* bridge */ /* synthetic */ a0 L0(boolean z6) {
        L0(z6);
        throw null;
    }

    @Override // F8.AbstractC0279n, F8.a0
    public final a0 M0(G8.f fVar) {
        if (fVar != null) {
            return this;
        }
        T0(3);
        throw null;
    }

    @Override // F8.A, F8.a0
    public final /* bridge */ /* synthetic */ a0 N0(H h10) {
        N0(h10);
        throw null;
    }

    @Override // F8.A
    /* JADX INFO: renamed from: O0 */
    public final A L0(boolean z6) {
        throw new IllegalStateException(this.f2456m);
    }

    @Override // F8.A
    /* JADX INFO: renamed from: P0 */
    public final A N0(H h10) {
        if (h10 != null) {
            throw new IllegalStateException(this.f2456m);
        }
        T0(0);
        throw null;
    }

    @Override // F8.AbstractC0279n
    public final A Q0() {
        throw new IllegalStateException(this.f2456m);
    }

    @Override // F8.AbstractC0279n
    /* JADX INFO: renamed from: R0 */
    public final A I0(G8.f fVar) {
        if (fVar != null) {
            return this;
        }
        T0(3);
        throw null;
    }

    @Override // F8.AbstractC0279n
    public final AbstractC0279n S0(A a10) {
        throw new IllegalStateException(this.f2456m);
    }

    @Override // F8.A
    public final String toString() {
        String str = this.f2456m;
        if (str != null) {
            return str;
        }
        T0(1);
        throw null;
    }
}
