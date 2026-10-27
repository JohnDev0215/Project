package p;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class z52 implements lp {
    public final Type c;
    public final boolean e;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final boolean n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f213p;

    public z52(Type type, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.c = type;
        this.e = z;
        this.k = z2;
        this.l = z3;
        this.m = z4;
        this.n = z5;
        this.o = z6;
        this.f213p = z7;
    }

    @Override // p.lp
    public final Type f() {
        return this.c;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0031  */
    @Override // p.lp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object s(p.ip1 r4) {
        /*
            r3 = this;
            boolean r0 = r3.e
            r1 = 1
            r2 = 0
            if (r0 == 0) goto Lc
            p.np r0 = new p.np
            r0.<init>(r4, r2)
            goto L11
        Lc:
            p.np r0 = new p.np
            r0.<init>(r4, r1)
        L11:
            boolean r4 = r3.k
            if (r4 == 0) goto L1c
            p.jm r4 = new p.jm
            r4.<init>(r1, r0)
        L1a:
            r0 = r4
            goto L26
        L1c:
            boolean r4 = r3.l
            if (r4 == 0) goto L26
            p.jm r4 = new p.jm
            r4.<init>(r2, r0)
            goto L1a
        L26:
            boolean r4 = r3.m
            if (r4 == 0) goto L31
            io.reactivex.rxjava3.core.BackpressureStrategy r4 = io.reactivex.rxjava3.core.BackpressureStrategy.e
            io.reactivex.rxjava3.core.Flowable r4 = r0.toFlowable(r4)
            return r4
        L31:
            boolean r4 = r3.n
            if (r4 == 0) goto L3a
            io.reactivex.rxjava3.core.Single r4 = r0.singleOrError()
            return r4
        L3a:
            boolean r4 = r3.o
            if (r4 == 0) goto L43
            io.reactivex.rxjava3.core.Maybe r4 = r0.singleElement()
            return r4
        L43:
            boolean r4 = r3.f213p
            if (r4 == 0) goto L4c
            io.reactivex.rxjava3.core.Completable r4 = r0.ignoreElements()
            return r4
        L4c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.z52.s(p.ip1):java.lang.Object");
    }
}
