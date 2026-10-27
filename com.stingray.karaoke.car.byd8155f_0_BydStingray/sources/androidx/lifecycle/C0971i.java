package androidx.lifecycle;

import S8.AbstractC0727y;
import java.util.Map;
import t.C2418b;
import t.C2422f;

/* JADX INFO: renamed from: androidx.lifecycle.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0971i extends J {
    public C2422f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C0966d f11992m;

    @Override // androidx.lifecycle.J
    public final void e() {
        C2418b c2418b = (C2418b) this.l.iterator();
        if (c2418b.hasNext()) {
            throw android.hardware.bydauto.ac.tsimpl.a.c((Map.Entry) c2418b.next());
        }
        C0966d c0966d = this.f11992m;
        if (c0966d != null) {
            S8.W w10 = c0966d.f11976g;
            if (w10 != null) {
                w10.g(null);
            }
            c0966d.f11976g = null;
            if (c0966d.f11975f != null) {
                return;
            }
            c0966d.f11975f = AbstractC0727y.w(c0966d.d, null, null, new C0965c(c0966d, null), 3);
        }
    }

    @Override // androidx.lifecycle.J
    public final void f() {
        C2418b c2418b = (C2418b) this.l.iterator();
        if (c2418b.hasNext()) {
            throw android.hardware.bydauto.ac.tsimpl.a.c((Map.Entry) c2418b.next());
        }
        C0966d c0966d = this.f11992m;
        if (c0966d != null) {
            if (c0966d.f11976g != null) {
                throw new IllegalStateException("Cancel call cannot happen without a maybeRun");
            }
            Z8.e eVar = S8.E.f8084a;
            c0966d.f11976g = AbstractC0727y.w(c0966d.d, X8.m.f10573a.f8932q, null, new C0964b(c0966d, null), 2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j(s7.AbstractC2410c r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof androidx.lifecycle.C0970h
            if (r0 == 0) goto L13
            r0 = r5
            androidx.lifecycle.h r0 = (androidx.lifecycle.C0970h) r0
            int r1 = r0.f11991n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f11991n = r1
            goto L18
        L13:
            androidx.lifecycle.h r0 = new androidx.lifecycle.h
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.l
            r7.a r1 = r7.EnumC2303a.l
            int r0 = r0.f11991n
            if (r0 == 0) goto L30
            r4 = 1
            if (r0 != r4) goto L28
            h8.AbstractC1386c.u(r5)
            r4 = 0
            goto L33
        L28:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L30:
            h8.AbstractC1386c.u(r5)
        L33:
            r4.getClass()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.C0971i.j(s7.c):void");
    }
}
