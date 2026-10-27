package N1;

import V8.AbstractC0828q;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class S {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final V8.f0 f4497a;

    public S(Context context) {
        A7.m.f(context, "context");
        this.f4497a = AbstractC0828q.c(Boolean.FALSE);
    }

    public c0 a() {
        return (c0) this.f4497a.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(N1.c0 r6) {
        /*
            r5 = this;
            java.lang.String r0 = "newState"
            A7.m.f(r6, r0)
        L5:
            V8.f0 r0 = r5.f4497a
            java.lang.Object r1 = r0.getValue()
            r2 = r1
            N1.c0 r2 = (N1.c0) r2
            boolean r3 = r2 instanceof N1.V
            if (r3 == 0) goto L14
            r3 = 1
            goto L1a
        L14:
            N1.e0 r3 = N1.e0.f4521b
            boolean r3 = A7.m.a(r2, r3)
        L1a:
            if (r3 == 0) goto L1d
            goto L27
        L1d:
            boolean r3 = r2 instanceof N1.C0423d
            if (r3 == 0) goto L29
            int r3 = r2.f4515a
            int r4 = r6.f4515a
            if (r4 <= r3) goto L2d
        L27:
            r2 = r6
            goto L2d
        L29:
            boolean r3 = r2 instanceof N1.T
            if (r3 == 0) goto L34
        L2d:
            boolean r0 = r0.k(r1, r2)
            if (r0 == 0) goto L5
            return
        L34:
            A9.k r5 = new A9.k
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: N1.S.b(N1.c0):void");
    }

    public S() {
        this.f4497a = AbstractC0828q.c(e0.f4521b);
    }
}
