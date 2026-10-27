package E5;

import java.util.List;
import java.util.Map;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class L extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final H f1778a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f1779b;
    public final q c;
    public final q d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q f1780e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final q f1781f;

    public L(H h10) {
        this.f1778a = h10;
        this.f1779b = h10.a(List.class);
        this.c = h10.a(Map.class);
        this.d = h10.a(String.class);
        this.f1780e = h10.a(Double.class);
        this.f1781f = h10.a(Boolean.class);
    }

    @Override // E5.q
    public final Object a(u uVar) {
        int iC = AbstractC2641j.c(uVar.z());
        if (iC == 0) {
            return this.f1779b.a(uVar);
        }
        if (iC == 2) {
            return this.c.a(uVar);
        }
        if (iC == 5) {
            return this.d.a(uVar);
        }
        if (iC == 6) {
            return this.f1780e.a(uVar);
        }
        if (iC == 7) {
            return this.f1781f.a(uVar);
        }
        if (iC == 8) {
            uVar.v();
            return null;
        }
        throw new IllegalStateException("Expected a value but was " + A7.k.w(uVar.z()) + " at path " + uVar.k());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017 A[PHI: r1
  0x0017: PHI (r1v4 java.lang.Class<?>) = (r1v1 java.lang.Class<?>), (r1v2 java.lang.Class<?>) binds: [B:6:0x0015, B:9:0x001f] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // E5.q
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(E5.x r4, java.lang.Object r5) {
        /*
            r3 = this;
            java.lang.Class r0 = r5.getClass()
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            if (r0 != r1) goto Lf
            r4.g()
            r4.i()
            goto L2e
        Lf:
            java.lang.Class<java.util.Map> r1 = java.util.Map.class
            boolean r2 = r1.isAssignableFrom(r0)
            if (r2 == 0) goto L19
        L17:
            r0 = r1
            goto L22
        L19:
            java.lang.Class<java.util.Collection> r1 = java.util.Collection.class
            boolean r2 = r1.isAssignableFrom(r0)
            if (r2 == 0) goto L22
            goto L17
        L22:
            java.util.Set r1 = G5.f.f2685a
            r2 = 0
            E5.H r3 = r3.f1778a
            E5.q r3 = r3.c(r0, r1, r2)
            r3.d(r4, r5)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: E5.L.d(E5.x, java.lang.Object):void");
    }

    public final String toString() {
        return "JsonAdapter(Object)";
    }
}
