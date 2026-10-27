package p;

/* JADX INFO: loaded from: classes.dex */
public final class ph extends y30 {
    public final z31 a;
    public final u30 b;
    public final m30 c;
    public final v30 d;
    public final z31 e;

    public ph(z31 z31Var, u30 u30Var, m30 m30Var, v30 v30Var, z31 z31Var2) {
        this.a = z31Var;
        this.b = u30Var;
        this.c = m30Var;
        this.d = v30Var;
        this.e = z31Var2;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L4
            return r0
        L4:
            boolean r1 = r5 instanceof p.y30
            r2 = 0
            if (r1 == 0) goto L6d
            p.y30 r5 = (p.y30) r5
            p.z31 r1 = r4.a
            if (r1 != 0) goto L17
            r1 = r5
            p.ph r1 = (p.ph) r1
            p.z31 r1 = r1.a
            if (r1 != 0) goto L6b
            goto L24
        L17:
            r3 = r5
            p.ph r3 = (p.ph) r3
            p.z31 r3 = r3.a
            java.util.List r1 = r1.c
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L6b
        L24:
            p.u30 r1 = r4.b
            if (r1 != 0) goto L30
            r1 = r5
            p.ph r1 = (p.ph) r1
            p.u30 r1 = r1.b
            if (r1 != 0) goto L6b
            goto L3b
        L30:
            r3 = r5
            p.ph r3 = (p.ph) r3
            p.u30 r3 = r3.b
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L6b
        L3b:
            p.m30 r1 = r4.c
            if (r1 != 0) goto L47
            r1 = r5
            p.ph r1 = (p.ph) r1
            p.m30 r1 = r1.c
            if (r1 != 0) goto L6b
            goto L52
        L47:
            r3 = r5
            p.ph r3 = (p.ph) r3
            p.m30 r3 = r3.c
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L6b
        L52:
            p.ph r5 = (p.ph) r5
            p.v30 r1 = r5.d
            p.v30 r3 = r4.d
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L6b
            p.z31 r1 = r4.e
            java.util.List r1 = r1.c
            p.z31 r5 = r5.e
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L6b
            goto L6c
        L6b:
            r0 = r2
        L6c:
            return r0
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ph.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        z31 z31Var = this.a;
        int iHashCode = ((z31Var == null ? 0 : z31Var.c.hashCode()) ^ 1000003) * 1000003;
        u30 u30Var = this.b;
        int iHashCode2 = (iHashCode ^ (u30Var == null ? 0 : u30Var.hashCode())) * 1000003;
        m30 m30Var = this.c;
        return (((((m30Var != null ? m30Var.hashCode() : 0) ^ iHashCode2) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.c.hashCode();
    }

    public final String toString() {
        return "Execution{threads=" + this.a + ", exception=" + this.b + ", appExitInfo=" + this.c + ", signal=" + this.d + ", binaries=" + this.e + "}";
    }
}
