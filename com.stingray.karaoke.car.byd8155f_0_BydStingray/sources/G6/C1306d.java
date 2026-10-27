package g6;

import A7.m;
import N5.q0;
import V8.AbstractC0828q;
import V8.N;
import V8.V;
import X5.C0881r0;
import androidx.lifecycle.a0;
import k6.C1705m;
import kotlin.Metadata;

/* JADX INFO: renamed from: g6.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lg6/d;", "Lb6/f;", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class C1306d extends b6.f {
    public final q0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C1310h f14986m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final N f14987n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1306d(W5.e eVar, q0 q0Var, C1705m c1705m, C1310h c1310h) {
        super(c1705m, eVar);
        m.f(eVar, "appNavigationManager");
        m.f(q0Var, "songQueue");
        m.f(c1705m, "languageManager");
        m.f(c1310h, "scoringTestRepoManager");
        this.l = q0Var;
        this.f14986m = c1310h;
        this.f14987n = AbstractC0828q.m(new C0881r0(c1310h.c, 12), a0.i(this), V.a(), null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // b6.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object f(q7.InterfaceC2189c r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof g6.C1303a
            if (r0 == 0) goto L13
            r0 = r5
            g6.a r0 = (g6.C1303a) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L1a
        L13:
            g6.a r0 = new g6.a
            s7.c r5 = (s7.AbstractC2410c) r5
            r0.<init>(r4, r5)
        L1a:
            java.lang.Object r5 = r0.f14981m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            g6.d r4 = r0.l
            h8.AbstractC1386c.u(r5)
            goto L5a
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            h8.AbstractC1386c.u(r5)
            g6.h r5 = r4.f14986m
            V8.N r2 = r5.c
            V8.L r2 = r2.l
            V8.f0 r2 = (V8.f0) r2
            java.lang.Object r2 = r2.getValue()
            java.util.List r2 = (java.util.List) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L5a
            com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState r2 = com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState.Loading
            r4.l(r2)
            r0.l = r4
            r0.o = r3
            java.lang.Object r5 = r5.b(r0)
            if (r5 != r1) goto L5a
            return r1
        L5a:
            com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState r5 = com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState.Done
            r4.l(r5)
            m7.q r4 = m7.q.f19267a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: g6.C1306d.f(q7.c):java.lang.Object");
    }
}
