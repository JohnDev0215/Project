package z5;

/* JADX INFO: loaded from: classes.dex */
public final class m extends f {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f24416r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f24417s;

    /* JADX WARN: Removed duplicated region for block: B:6:0x000f  */
    @Override // java.lang.Comparable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int compareTo(java.lang.Object r8) {
        /*
            r7 = this;
            y5.c r8 = (y5.AbstractC2745c) r8
            long r0 = r8.l
            long r2 = r7.l
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            r1 = -1
            r2 = 1
            if (r0 == 0) goto L12
            if (r0 >= 0) goto Lf
            goto L10
        Lf:
            r1 = r2
        L10:
            r2 = r1
            goto L3d
        L12:
            A4.b r0 = r7.f24127m
            int r0 = r0.f413b
            long r3 = (long) r0
            A4.b r0 = r8.f24127m
            int r0 = r0.f413b
            long r5 = (long) r0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 == 0) goto L23
            if (r0 >= 0) goto L10
            goto Lf
        L23:
            boolean r0 = r8 instanceof z5.m
            if (r0 != 0) goto L28
            goto L3d
        L28:
            z5.m r8 = (z5.m) r8
            int r0 = r7.f24416r
            int r3 = r8.f24416r
            if (r0 == r3) goto L33
            if (r0 >= r3) goto Lf
            goto L10
        L33:
            int r7 = r7.f24417s
            int r8 = r8.f24417s
            if (r7 == r8) goto L3c
            if (r7 >= r8) goto Lf
            goto L10
        L3c:
            r2 = 0
        L3d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: z5.m.compareTo(java.lang.Object):int");
    }

    @Override // y5.AbstractC2745c
    public final String toString() {
        return super.toString() + " " + this.f24416r + "/" + ((int) Math.pow(2.0d, this.f24417s));
    }
}
