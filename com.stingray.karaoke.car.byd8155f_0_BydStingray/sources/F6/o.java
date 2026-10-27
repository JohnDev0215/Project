package f6;

import A7.v;
import K5.F;
import K5.u;
import S8.AbstractC0727y;
import T.C0735d;
import T.C0738e0;
import T.S;
import androidx.lifecycle.a0;
import b6.AbstractC0999b;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import k6.C1705m;
import k6.r;
import kotlin.Metadata;
import m7.q;
import q7.InterfaceC2189c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lf6/o;", "Lb6/b;", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class o extends AbstractC0999b {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final W5.e f14636v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final F f14637w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final C0738e0 f14638x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(C1705m c1705m, j6.n nVar, W5.e eVar, r rVar, u uVar, L5.i iVar, F f10) {
        super(c1705m, nVar, uVar, eVar, rVar, iVar);
        A7.m.f(c1705m, "languageManager");
        A7.m.f(nVar, "profileManager");
        A7.m.f(eVar, "appNavigationManager");
        A7.m.f(rVar, "parentalControlsManager");
        A7.m.f(uVar, "karaokeItemRepository");
        A7.m.f(iVar, "deepLinkManager");
        A7.m.f(f10, "repository");
        this.f14636v = eVar;
        this.f14637w = f10;
        this.f14638x = C0735d.O(n7.u.l, S.f8224q);
        m(NavigationType.TAB);
        final int i7 = 0;
        this.f12245m = new InterfaceC2782k(this) { // from class: f6.i

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ o f14625m;

            {
                this.f14625m = this;
            }

            @Override // z7.InterfaceC2782k
            public final Object b(Object obj) {
                switch (i7) {
                    case 0:
                        A7.m.f((j6.a) obj, "it");
                        this.f14625m.q();
                        break;
                    case 1:
                        A7.m.f((String) obj, "it");
                        this.f14625m.q();
                        break;
                    default:
                        A7.m.f((String) obj, "it");
                        this.f14625m.q();
                        break;
                }
                return q.f19267a;
            }
        };
        final int i10 = 1;
        this.f12246n = new InterfaceC2782k(this) { // from class: f6.i

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ o f14625m;

            {
                this.f14625m = this;
            }

            @Override // z7.InterfaceC2782k
            public final Object b(Object obj) {
                switch (i10) {
                    case 0:
                        A7.m.f((j6.a) obj, "it");
                        this.f14625m.q();
                        break;
                    case 1:
                        A7.m.f((String) obj, "it");
                        this.f14625m.q();
                        break;
                    default:
                        A7.m.f((String) obj, "it");
                        this.f14625m.q();
                        break;
                }
                return q.f19267a;
            }
        };
        final int i11 = 2;
        this.o = new InterfaceC2782k(this) { // from class: f6.i

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ o f14625m;

            {
                this.f14625m = this;
            }

            @Override // z7.InterfaceC2782k
            public final Object b(Object obj) {
                switch (i11) {
                    case 0:
                        A7.m.f((j6.a) obj, "it");
                        this.f14625m.q();
                        break;
                    case 1:
                        A7.m.f((String) obj, "it");
                        this.f14625m.q();
                        break;
                    default:
                        A7.m.f((String) obj, "it");
                        this.f14625m.q();
                        break;
                }
                return q.f19267a;
            }
        };
        AbstractC0727y.w(a0.i(this), null, null, new k(rVar, new v(), this, null), 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00a4 -> B:29:0x00a7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00ae -> B:31:0x00af). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object p(f6.o r14, s7.AbstractC2410c r15) throws java.lang.Throwable {
        /*
            r14.getClass()
            boolean r0 = r15 instanceof f6.n
            if (r0 == 0) goto L16
            r0 = r15
            f6.n r0 = (f6.n) r0
            int r1 = r0.f14635r
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.f14635r = r1
            goto L1b
        L16:
            f6.n r0 = new f6.n
            r0.<init>(r14, r15)
        L1b:
            java.lang.Object r15 = r0.f14633p
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f14635r
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L45
            if (r2 == r5) goto L3f
            if (r2 != r4) goto L37
            com.stingray.kca.model.KaraokeItem r14 = r0.o
            java.util.Iterator r2 = r0.f14632n
            java.util.List r5 = r0.f14631m
            f6.o r6 = r0.l
            h8.AbstractC1386c.u(r15)
            goto La7
        L37:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L3f:
            f6.o r14 = r0.l
            h8.AbstractC1386c.u(r15)
            goto L61
        L45:
            h8.AbstractC1386c.u(r15)
            r0.l = r14
            r0.f14635r = r5
            K5.F r15 = r14.f14637w
            r15.getClass()
            Z8.e r2 = S8.E.f8084a
            Z8.d r2 = Z8.d.f11010n
            K5.z r5 = new K5.z
            r5.<init>(r15, r3)
            java.lang.Object r15 = S8.AbstractC0727y.F(r2, r5, r0)
            if (r15 != r1) goto L61
            goto Lb9
        L61:
            java.util.List r15 = (java.util.List) r15
            java.util.Iterator r2 = r15.iterator()
            r6 = r14
            r5 = r15
        L69:
            boolean r14 = r2.hasNext()
            if (r14 == 0) goto Lb2
            java.lang.Object r14 = r2.next()
            com.stingray.kca.model.KaraokeItem r14 = (com.stingray.kca.model.KaraokeItem) r14
            java.lang.Boolean r15 = r14.l
            java.lang.Boolean r7 = java.lang.Boolean.TRUE
            boolean r15 = A7.m.a(r15, r7)
            if (r15 == 0) goto L69
            java.lang.String r9 = r14.f13107a
            if (r9 == 0) goto Lae
            K5.F r8 = r6.f14637w
            r0.l = r6
            r0.f14631m = r5
            r0.f14632n = r2
            r0.o = r14
            r0.f14635r = r4
            r8.getClass()
            Z8.e r15 = S8.E.f8084a
            Z8.d r15 = Z8.d.f11010n
            K5.w r13 = new K5.w
            r10 = 25
            r11 = 0
            r12 = 0
            r7 = r13
            r7.<init>(r8, r9, r10, r11, r12)
            java.lang.Object r15 = S8.AbstractC0727y.F(r15, r13, r0)
            if (r15 != r1) goto La7
            goto Lb9
        La7:
            java.util.Collection r15 = (java.util.Collection) r15
            java.util.ArrayList r15 = n7.AbstractC1998m.A0(r15)
            goto Laf
        Lae:
            r15 = r3
        Laf:
            r14.f13111g = r15
            goto L69
        Lb2:
            T.e0 r14 = r6.f14638x
            r14.setValue(r5)
            m7.q r1 = m7.q.f19267a
        Lb9:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: f6.o.p(f6.o, s7.c):java.lang.Object");
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        l(LoadingInterface$LoadingState.Loading);
        AbstractC0727y.w(this.f12236f, null, null, new l(this, null), 3);
        return q.f19267a;
    }

    @Override // b6.f
    public final void g() {
        i(true);
    }

    public final void q() {
        AbstractC0727y.w(this.f12236f, null, null, new m(this, null), 3);
    }
}
