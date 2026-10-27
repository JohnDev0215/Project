package T;

import e0.AbstractC1156C;
import u.AbstractC2454B;
import u.C2481u;

/* JADX INFO: loaded from: classes.dex */
public final class C extends AbstractC1156C {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Object f8177h = new Object();
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C2481u f8178e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f8179f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f8180g;

    public C() {
        C2481u c2481u = AbstractC2454B.f22582a;
        A7.m.d(c2481u, "null cannot be cast to non-null type androidx.collection.ObjectIntMap<K of androidx.collection.ObjectIntMapKt.emptyObjectIntMap>");
        this.f8178e = c2481u;
        this.f8179f = f8177h;
    }

    @Override // e0.AbstractC1156C
    public final void a(AbstractC1156C abstractC1156C) {
        A7.m.d(abstractC1156C, "null cannot be cast to non-null type androidx.compose.runtime.DerivedSnapshotState.ResultRecord<T of androidx.compose.runtime.DerivedSnapshotState.ResultRecord>");
        C c = (C) abstractC1156C;
        this.f8178e = c.f8178e;
        this.f8179f = c.f8179f;
        this.f8180g = c.f8180g;
    }

    @Override // e0.AbstractC1156C
    public final AbstractC1156C b() {
        return new C();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean c(T.D r7, e0.AbstractC1167g r8) {
        /*
            r6 = this;
            java.lang.Object r0 = e0.AbstractC1174n.f13721b
            monitor-enter(r0)
            int r1 = r6.c     // Catch: java.lang.Throwable -> L18
            int r2 = r8.d()     // Catch: java.lang.Throwable -> L18
            r3 = 1
            r4 = 0
            if (r1 != r2) goto L1a
            int r1 = r6.d     // Catch: java.lang.Throwable -> L18
            int r2 = r8.h()     // Catch: java.lang.Throwable -> L18
            if (r1 == r2) goto L16
            goto L1a
        L16:
            r1 = r4
            goto L1b
        L18:
            r6 = move-exception
            goto L45
        L1a:
            r1 = r3
        L1b:
            monitor-exit(r0)
            java.lang.Object r2 = r6.f8179f
            java.lang.Object r5 = T.C.f8177h
            if (r2 == r5) goto L2d
            if (r1 == 0) goto L2e
            int r2 = r6.f8180g
            int r7 = r6.d(r7, r8)
            if (r2 != r7) goto L2d
            goto L2e
        L2d:
            r3 = r4
        L2e:
            if (r3 == 0) goto L44
            if (r1 == 0) goto L44
            monitor-enter(r0)
            int r7 = r8.d()     // Catch: java.lang.Throwable -> L41
            r6.c = r7     // Catch: java.lang.Throwable -> L41
            int r7 = r8.h()     // Catch: java.lang.Throwable -> L41
            r6.d = r7     // Catch: java.lang.Throwable -> L41
            monitor-exit(r0)
            goto L44
        L41:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        L44:
            return r3
        L45:
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C.c(T.D, e0.g):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00a3 A[PHI: r10
  0x00a3: PHI (r10v2 int) = (r10v1 int), (r10v4 int) binds: [B:18:0x0040, B:38:0x009e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int d(T.D r21, e0.AbstractC1167g r22) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C.d(T.D, e0.g):int");
    }
}
