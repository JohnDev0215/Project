package b6;

import K5.F;
import K5.G;
import K5.u;
import S8.AbstractC0727y;
import T.C0735d;
import T.C0738e0;
import T.S;
import V8.AbstractC0828q;
import V8.C0832v;
import V8.InterfaceC0817f;
import V8.N;
import V8.V;
import V8.f0;
import androidx.lifecycle.a0;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import java.util.List;
import k6.C1691C;
import k6.C1705m;
import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public abstract class o extends i {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final f0 f12265A;
    public final N B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final f0 f12266C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final N f12267D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final N f12268E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final N f12269F;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final F f12270t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final G f12271u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f12272v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final f0 f12273w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final N f12274x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final C0738e0 f12275y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final C0738e0 f12276z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(C1705m c1705m, W5.e eVar, F f10, u uVar, G g7, C1691C c1691c, L5.i iVar, j6.n nVar) {
        super(c1705m, eVar, uVar, iVar, nVar);
        A7.m.f(c1705m, "languageManager");
        A7.m.f(eVar, "appNavigationManager");
        A7.m.f(f10, "songsRepository");
        A7.m.f(uVar, "karaokeItemRepository");
        A7.m.f(g7, "sortTypeRepository");
        A7.m.f(c1691c, "uxRestrictionManager");
        A7.m.f(iVar, "deepLinkManager");
        A7.m.f(nVar, "profileManager");
        this.f12270t = f10;
        this.f12271u = g7;
        f0 f0VarC = AbstractC0828q.c(n7.u.l);
        this.f12273w = f0VarC;
        this.f12274x = new N(f0VarC);
        LoadingInterface$LoadingState loadingInterface$LoadingState = LoadingInterface$LoadingState.Done;
        S s6 = S.f8224q;
        this.f12275y = C0735d.O(loadingInterface$LoadingState, s6);
        this.f12276z = C0735d.O("", s6);
        f0 f0VarC2 = AbstractC0828q.c(null);
        this.f12265A = f0VarC2;
        N n10 = new N(f0VarC2);
        this.B = n10;
        f0 f0VarC3 = AbstractC0828q.c(null);
        this.f12266C = f0VarC3;
        this.f12267D = new N(f0VarC3);
        this.f12268E = c1691c.f18234h;
        this.f12269F = AbstractC0828q.m(new C0832v(new InterfaceC0817f[]{c1691c.f18233g, f0VarC, n10}, new k(4, null)), a0.i(this), V.a(), Boolean.FALSE);
    }

    public static Object p(o oVar, boolean z6, AbstractC2410c abstractC2410c) {
        return oVar.o(z6, oVar.q(), ((List) ((f0) oVar.f12274x.l).getValue()).size(), abstractC2410c);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object r(b6.o r11, s7.AbstractC2410c r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b6.o.r(b6.o, s7.c):java.lang.Object");
    }

    @Override // b6.f
    public Object f(InterfaceC2189c interfaceC2189c) {
        return r(this, (AbstractC2410c) interfaceC2189c);
    }

    @Override // b6.f
    public final void g() {
        i(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00c0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object o(boolean r19, int r20, int r21, s7.AbstractC2410c r22) throws java.lang.Throwable {
        /*
            r18 = this;
            r0 = r18
            r1 = r22
            boolean r2 = r1 instanceof b6.j
            if (r2 == 0) goto L17
            r2 = r1
            b6.j r2 = (b6.j) r2
            int r3 = r2.f12255r
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.f12255r = r3
            goto L1c
        L17:
            b6.j r2 = new b6.j
            r2.<init>(r0, r1)
        L1c:
            java.lang.Object r1 = r2.f12253p
            r7.a r3 = r7.EnumC2303a.l
            int r4 = r2.f12255r
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L48
            if (r4 == r6) goto L37
            if (r4 != r5) goto L2f
            h8.AbstractC1386c.u(r1)
            goto Lc1
        L2f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L37:
            int r0 = r2.o
            int r4 = r2.f12252n
            java.lang.String r6 = r2.f12251m
            b6.o r7 = r2.l
            h8.AbstractC1386c.u(r1)
            r17 = r7
            r7 = r0
            r0 = r17
            goto L95
        L48:
            h8.AbstractC1386c.u(r1)
            java.lang.String r1 = r0.f12250s
            if (r1 != 0) goto L52
            n7.u r0 = n7.u.l
            return r0
        L52:
            V8.N r4 = r0.f12267D
            V8.L r4 = r4.l
            V8.f0 r4 = (V8.f0) r4
            java.lang.Object r4 = r4.getValue()
            com.stingray.kca.model.KaraokeItemSortTypeInfo r4 = (com.stingray.kca.model.KaraokeItemSortTypeInfo) r4
            if (r4 == 0) goto L9c
            java.lang.String r15 = r4.c
            if (r15 == 0) goto L9c
            r2.l = r0
            r2.f12251m = r1
            r4 = r20
            r2.f12252n = r4
            r7 = r21
            r2.o = r7
            r2.f12255r = r6
            K5.F r9 = r0.f12270t
            r9.getClass()
            Z8.e r6 = S8.E.f8084a
            Z8.d r6 = Z8.d.f11010n
            K5.D r14 = new K5.D
            r16 = 0
            r11 = 0
            r8 = r14
            r10 = r1
            r12 = r21
            r13 = r20
            r5 = r14
            r14 = r19
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16)
            java.lang.Object r5 = S8.AbstractC0727y.F(r6, r5, r2)
            if (r5 != r3) goto L93
            return r3
        L93:
            r6 = r1
            r1 = r5
        L95:
            java.util.List r1 = (java.util.List) r1
            if (r1 != 0) goto Lc3
        L99:
            r8 = r7
            r7 = r4
            goto La2
        L9c:
            r4 = r20
            r7 = r21
            r6 = r1
            goto L99
        La2:
            K5.F r5 = r0.f12270t
            r0 = 0
            r2.l = r0
            r2.f12251m = r0
            r0 = 2
            r2.f12255r = r0
            r5.getClass()
            Z8.e r0 = S8.E.f8084a
            Z8.d r0 = Z8.d.f11010n
            K5.w r1 = new K5.w
            r9 = 0
            r4 = r1
            r4.<init>(r5, r6, r7, r8, r9)
            java.lang.Object r1 = S8.AbstractC0727y.F(r0, r1, r2)
            if (r1 != r3) goto Lc1
            return r3
        Lc1:
            java.util.List r1 = (java.util.List) r1
        Lc3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: b6.o.o(boolean, int, int, s7.c):java.lang.Object");
    }

    public abstract int q();

    public final void s() {
        if (this.f12272v || ((f0) this.f12268E.l).getValue() != null) {
            return;
        }
        AbstractC0727y.w(this.f12236f, null, null, new m(this, null), 3);
    }

    public final void t(LoadingInterface$LoadingState loadingInterface$LoadingState) {
        A7.m.f(loadingInterface$LoadingState, "<set-?>");
        this.f12275y.setValue(loadingInterface$LoadingState);
    }
}
