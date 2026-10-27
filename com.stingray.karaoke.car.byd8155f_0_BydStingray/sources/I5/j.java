package I5;

import K5.F;
import K5.G;
import N5.C0449e;
import N5.Z;
import N5.q0;
import N5.t0;
import X5.C0886u;
import X5.D0;
import X5.G0;
import X5.H0;
import X5.V0;
import a.AbstractC0904a;
import a6.C0928a;
import android.content.Context;
import c6.C1034a;
import com.stingray.companion.CompanionManager;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import g6.C1306d;
import g6.C1310h;
import h6.C1382a;
import i6.C1415d;
import k6.C1691C;
import k6.C1696d;
import k6.C1699g;
import k6.C1704l;
import k6.C1705m;
import n6.C1967H;
import n6.C1972e;

/* JADX INFO: loaded from: classes.dex */
public final class j implements R6.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2956a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k f2957b;
    public final int c;

    public /* synthetic */ j(k kVar, int i7, int i10) {
        this.f2956a = i10;
        this.f2957b = kVar;
        this.c = i7;
    }

    @Override // R6.c
    public final Object get() {
        Object tVar;
        Object eVar;
        b6.f c0928a;
        switch (this.f2956a) {
            case 0:
                k kVar = this.f2957b;
                int i7 = this.c;
                switch (i7) {
                    case 0:
                        Context context = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context);
                        return new J5.c(context);
                    case 1:
                        Context context2 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context2);
                        return new J5.h(context2, (J5.c) kVar.c.get(), (C1699g) kVar.f2975e.get(), (A6.e) kVar.f2977g.get(), (K5.t) kVar.d.get());
                    case 2:
                        Context context3 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context3);
                        return new C1699g(context3, (K5.t) kVar.d.get());
                    case 3:
                        Context context4 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context4);
                        tVar = new K5.t(context4);
                        return tVar;
                    case 4:
                        Context context5 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context5);
                        A6.c cVar = (A6.c) kVar.f2976f.get();
                        A7.m.f(cVar, "logMessageManager");
                        eVar = new A6.e(context5, cVar);
                        return eVar;
                    case 5:
                        return new A6.c();
                    case 6:
                        return new C1696d((C1699g) kVar.f2975e.get(), (J5.h) kVar.f2978h.get(), (K5.t) kVar.d.get(), (A6.c) kVar.f2976f.get());
                    case 7:
                        return new C0449e((V0) kVar.f2964H.get(), (Z) kVar.f2961E.get());
                    case 8:
                        return new V0((Z) kVar.f2961E.get(), (J5.k) kVar.f2962F.get(), (C1310h) kVar.f2963G.get());
                    case 9:
                        Context context6 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context6);
                        return new Z(context6, (CompanionManager) kVar.f2980j.get(), (q0) kVar.f2984p.get(), (C1704l) kVar.f2985q.get(), (D6.e) kVar.f2986r.get(), (j6.n) kVar.f2987s.get(), (n6.u) kVar.f2989u.get(), (C1972e) kVar.f2992x.get(), (B6.b) kVar.l.get(), (C1691C) kVar.f2993y.get(), (G0) kVar.f2994z.get(), (W5.e) kVar.B.get(), (I6.d) kVar.f2959C.get(), (e6.k) kVar.f2960D.get());
                    case 10:
                        return new CompanionManager();
                    case 11:
                        return new q0((CompanionManager) kVar.f2980j.get(), (F) kVar.f2983n.get(), (F6.a) kVar.o.get(), (k6.r) kVar.f2982m.get(), (K5.t) kVar.d.get());
                    case 12:
                        return new F((J5.c) kVar.c.get(), (K5.u) kVar.f2981k.get(), (A6.e) kVar.f2977g.get(), (k6.r) kVar.f2982m.get());
                    case 13:
                        return new K5.u();
                    case 14:
                        return new k6.r((B6.b) kVar.l.get(), (CompanionManager) kVar.f2980j.get(), (K5.t) kVar.d.get());
                    case 15:
                        Context context7 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context7);
                        A6.c cVar2 = (A6.c) kVar.f2976f.get();
                        A7.m.f(cVar2, "logMessageManager");
                        eVar = new B6.b(context7, cVar2);
                        return eVar;
                    case 16:
                        Context context8 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context8);
                        A6.c cVar3 = (A6.c) kVar.f2976f.get();
                        A7.m.f(cVar3, "logMessageManager");
                        eVar = new F6.a(context8, cVar3);
                        return eVar;
                    case 17:
                        Context context9 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context9);
                        return new C1704l(context9, (C1696d) kVar.f2979i.get());
                    case 18:
                        Context context10 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context10);
                        A6.c cVar4 = (A6.c) kVar.f2976f.get();
                        A7.m.f(cVar4, "logMessageManager");
                        eVar = new D6.e(context10, cVar4);
                        return eVar;
                    case 19:
                        Context context11 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context11);
                        return new j6.n(context11, (J5.c) kVar.c.get());
                    case 20:
                        Context context12 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context12);
                        return new n6.u(context12, (n6.z) kVar.f2988t.get());
                    case 21:
                        return new n6.z();
                    case 22:
                        return new C1972e((n6.z) kVar.f2988t.get(), (C1967H) kVar.f2991w.get(), (n6.u) kVar.f2989u.get());
                    case 23:
                        return new C1967H((n6.z) kVar.f2988t.get(), (E6.b) kVar.f2990v.get(), (B6.b) kVar.l.get());
                    case 24:
                        Context context13 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context13);
                        A6.c cVar5 = (A6.c) kVar.f2976f.get();
                        A7.m.f(cVar5, "logMessageManager");
                        eVar = new E6.b(context13, cVar5);
                        return eVar;
                    case 25:
                        return new C1691C((C1704l) kVar.f2985q.get());
                    case 26:
                        return new G0();
                    case 27:
                        return new W5.e((C6.b) kVar.f2958A.get());
                    case 28:
                        Context context14 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context14);
                        A6.c cVar6 = (A6.c) kVar.f2976f.get();
                        A7.m.f(cVar6, "logMessageManager");
                        eVar = new C6.b(context14, cVar6);
                        return eVar;
                    case 29:
                        Context context15 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context15);
                        tVar = new I6.d(context15);
                        return tVar;
                    case 30:
                        F f10 = (F) kVar.f2983n.get();
                        q0 q0Var = (q0) kVar.f2984p.get();
                        W5.e eVar2 = (W5.e) kVar.B.get();
                        A7.m.f(f10, "repository");
                        A7.m.f(q0Var, "songQueueManager");
                        A7.m.f(eVar2, "appNavigationManager");
                        tVar = new e6.k(f10, q0Var, eVar2);
                        return tVar;
                    case 31:
                        Context context16 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context16);
                        return new J5.k(context16);
                    case 32:
                        return new C1310h((F) kVar.f2983n.get());
                    case 33:
                        Context context17 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context17);
                        return new C1705m(context17);
                    case 34:
                        Context context18 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context18);
                        return new L5.i(context18, (J5.c) kVar.c.get(), (K5.u) kVar.f2981k.get(), (q0) kVar.f2984p.get(), (C0886u) kVar.f2967K.get(), (e6.k) kVar.f2960D.get());
                    case 35:
                        return new C0886u((Z) kVar.f2961E.get());
                    case 36:
                        return new G();
                    case 37:
                        return new t0((q0) kVar.f2984p.get(), (Z) kVar.f2961E.get(), (CompanionManager) kVar.f2980j.get(), (F) kVar.f2983n.get(), (W5.e) kVar.B.get(), (C1704l) kVar.f2985q.get());
                    case 38:
                        F f11 = (F) kVar.f2983n.get();
                        q0 q0Var2 = (q0) kVar.f2984p.get();
                        W5.e eVar3 = (W5.e) kVar.B.get();
                        A7.m.f(f11, "repository");
                        A7.m.f(q0Var2, "songQueueManager");
                        A7.m.f(eVar3, "appNavigationManager");
                        tVar = new e6.k(f11, q0Var2, eVar3);
                        return tVar;
                    case 39:
                        Context context19 = kVar.f2973a.f5412m;
                        AbstractC0904a.u(context19);
                        return new Y5.d(context19);
                    default:
                        throw new AssertionError(i7);
                }
            default:
                k kVar2 = this.f2957b;
                int i10 = this.c;
                switch (i10) {
                    case 0:
                        Context context20 = kVar2.f2973a.f5412m;
                        AbstractC0904a.u(context20);
                        return new C1382a(context20, (C1705m) kVar2.f2966J.get(), (W5.e) kVar2.B.get(), (C1691C) kVar2.f2993y.get(), (K5.t) kVar2.d.get());
                    case 1:
                        C1705m c1705m = (C1705m) kVar2.f2966J.get();
                        W5.e eVar4 = (W5.e) kVar2.B.get();
                        F f12 = (F) kVar2.f2983n.get();
                        G g7 = (G) kVar2.f2969M.get();
                        C1691C c1691c = (C1691C) kVar2.f2993y.get();
                        K5.u uVar = (K5.u) kVar2.f2981k.get();
                        L5.i iVar = (L5.i) kVar2.f2968L.get();
                        j6.n nVar = (j6.n) kVar2.f2987s.get();
                        A7.m.f(c1705m, "languageManager");
                        A7.m.f(eVar4, "appNavigationManager");
                        A7.m.f(f12, "songsRepository");
                        A7.m.f(g7, "sortTypeRepository");
                        A7.m.f(c1691c, "uxRestrictionManager");
                        A7.m.f(uVar, "karaokeItemRepository");
                        A7.m.f(iVar, "deepLinkManager");
                        A7.m.f(nVar, "profileManager");
                        c0928a = new C0928a(c1705m, eVar4, f12, uVar, g7, c1691c, iVar, nVar);
                        break;
                    case 2:
                        return new C1034a((C1705m) kVar2.f2966J.get(), (W5.e) kVar2.B.get(), (C1691C) kVar2.f2993y.get());
                    case 3:
                        return new h6.b((C1705m) kVar2.f2966J.get(), (W5.e) kVar2.B.get(), (C1691C) kVar2.f2993y.get());
                    case 4:
                        return new h6.d((C1705m) kVar2.f2966J.get(), (W5.e) kVar2.B.get(), (C1696d) kVar2.f2979i.get());
                    case 5:
                        C1705m c1705m2 = (C1705m) kVar2.f2966J.get();
                        W5.e eVar5 = (W5.e) kVar2.B.get();
                        F f13 = (F) kVar2.f2983n.get();
                        C1691C c1691c2 = (C1691C) kVar2.f2993y.get();
                        k6.r rVar = (k6.r) kVar2.f2982m.get();
                        e6.k kVar3 = (e6.k) kVar2.f2960D.get();
                        K5.u uVar2 = (K5.u) kVar2.f2981k.get();
                        L5.i iVar2 = (L5.i) kVar2.f2968L.get();
                        j6.n nVar2 = (j6.n) kVar2.f2987s.get();
                        A7.m.f(c1705m2, "languageManager");
                        A7.m.f(eVar5, "appNavigationManager");
                        A7.m.f(f13, "repository");
                        A7.m.f(c1691c2, "uxRestrictionManager");
                        A7.m.f(rVar, "parentalControlsManager");
                        A7.m.f(kVar3, "searchManager");
                        A7.m.f(uVar2, "karaokeItemRepository");
                        A7.m.f(iVar2, "deepLinkManager");
                        A7.m.f(nVar2, "profileManager");
                        c0928a = new e6.h(f13, rVar, kVar3, c1705m2, eVar5, uVar2, iVar2, nVar2, c1691c2);
                        c0928a.m(NavigationType.TAB);
                        break;
                    case 6:
                        return new h6.h((C1705m) kVar2.f2966J.get(), (C1691C) kVar2.f2993y.get(), (J5.c) kVar2.c.get(), (W5.e) kVar2.B.get());
                    case 7:
                        return new f6.g((C1705m) kVar2.f2966J.get(), (W5.e) kVar2.B.get(), (F) kVar2.f2983n.get(), (K5.u) kVar2.f2981k.get(), (k6.r) kVar2.f2982m.get(), (j6.n) kVar2.f2987s.get(), (F) kVar2.f2983n.get(), (L5.i) kVar2.f2968L.get());
                    case 8:
                        return new f6.h((C1705m) kVar2.f2966J.get(), (W5.e) kVar2.B.get(), (C1704l) kVar2.f2985q.get(), (K5.u) kVar2.f2981k.get(), (J5.h) kVar2.f2978h.get(), (J5.c) kVar2.c.get(), (t0) kVar2.f2970N.get(), (K5.t) kVar2.d.get());
                    case 9:
                        return new f6.o((C1705m) kVar2.f2966J.get(), (j6.n) kVar2.f2987s.get(), (W5.e) kVar2.B.get(), (k6.r) kVar2.f2982m.get(), (K5.u) kVar2.f2981k.get(), (L5.i) kVar2.f2968L.get(), (F) kVar2.f2983n.get());
                    case 10:
                        C1705m c1705m3 = (C1705m) kVar2.f2966J.get();
                        W5.e eVar6 = (W5.e) kVar2.B.get();
                        F f14 = (F) kVar2.f2983n.get();
                        C1691C c1691c3 = (C1691C) kVar2.f2993y.get();
                        k6.r rVar2 = (k6.r) kVar2.f2982m.get();
                        K5.u uVar3 = (K5.u) kVar2.f2981k.get();
                        L5.i iVar3 = (L5.i) kVar2.f2968L.get();
                        j6.n nVar3 = (j6.n) kVar2.f2987s.get();
                        e6.k kVar4 = (e6.k) kVar2.f2971O.get();
                        A7.m.f(c1705m3, "languageManager");
                        A7.m.f(eVar6, "appNavigationManager");
                        A7.m.f(f14, "repository");
                        A7.m.f(c1691c3, "uxRestrictionManager");
                        A7.m.f(rVar2, "parentalControlsManager");
                        A7.m.f(uVar3, "karaokeItemRepository");
                        A7.m.f(iVar3, "deepLinkManager");
                        A7.m.f(nVar3, "profileManager");
                        A7.m.f(kVar4, "searchManager");
                        return new e6.i(f14, rVar2, kVar4, c1705m3, eVar6, uVar3, iVar3, nVar3, c1691c3);
                    case 11:
                        return new D0((C1705m) kVar2.f2966J.get(), (W5.e) kVar2.B.get(), (V0) kVar2.f2964H.get(), (C1704l) kVar2.f2985q.get(), (Y5.d) kVar2.f2972P.get(), (q0) kVar2.f2984p.get(), (C6.b) kVar2.f2958A.get(), (Z) kVar2.f2961E.get(), (C0886u) kVar2.f2967K.get(), (K5.u) kVar2.f2981k.get(), (j6.n) kVar2.f2987s.get(), (C1972e) kVar2.f2992x.get(), (n6.u) kVar2.f2989u.get(), (C1967H) kVar2.f2991w.get(), (n6.z) kVar2.f2988t.get(), (C1696d) kVar2.f2979i.get(), (L5.i) kVar2.f2968L.get(), (C1691C) kVar2.f2993y.get(), (K5.t) kVar2.d.get(), (I6.d) kVar2.f2959C.get(), (G0) kVar2.f2994z.get());
                    case 12:
                        return new h6.l((C1705m) kVar2.f2966J.get(), (W5.e) kVar2.B.get());
                    case 13:
                        return new C1306d((W5.e) kVar2.B.get(), (q0) kVar2.f2984p.get(), (C1705m) kVar2.f2966J.get(), (C1310h) kVar2.f2963G.get());
                    case 14:
                        C1705m c1705m4 = (C1705m) kVar2.f2966J.get();
                        j6.n nVar4 = (j6.n) kVar2.f2987s.get();
                        K5.u uVar4 = (K5.u) kVar2.f2981k.get();
                        W5.e eVar7 = (W5.e) kVar2.B.get();
                        k6.r rVar3 = (k6.r) kVar2.f2982m.get();
                        L5.i iVar4 = (L5.i) kVar2.f2968L.get();
                        A7.m.f(c1705m4, "languageManager");
                        A7.m.f(nVar4, "profileManager");
                        A7.m.f(uVar4, "karaokeItemRepository");
                        A7.m.f(eVar7, "appNavigationManager");
                        A7.m.f(rVar3, "parentalControlsManager");
                        A7.m.f(iVar4, "deepLinkManager");
                        return new f6.p(c1705m4, nVar4, uVar4, eVar7, rVar3, iVar4);
                    case 15:
                        Context context21 = kVar2.f2973a.f5412m;
                        AbstractC0904a.u(context21);
                        return new d6.j(context21, (C1705m) kVar2.f2966J.get(), (W5.e) kVar2.B.get(), (F) kVar2.f2983n.get(), (q0) kVar2.f2984p.get(), (Z) kVar2.f2961E.get(), (n6.u) kVar2.f2989u.get(), (K5.u) kVar2.f2981k.get(), (j6.n) kVar2.f2987s.get(), (L5.i) kVar2.f2968L.get(), (C1704l) kVar2.f2985q.get());
                    case 16:
                        return new H0((C1705m) kVar2.f2966J.get(), (W5.e) kVar2.B.get(), (q0) kVar2.f2984p.get(), (Z) kVar2.f2961E.get(), (C1696d) kVar2.f2979i.get());
                    case 17:
                        return new C1415d((C1705m) kVar2.f2966J.get(), (W5.e) kVar2.B.get(), (F) kVar2.f2983n.get(), (q0) kVar2.f2984p.get(), (G) kVar2.f2969M.get(), (C1691C) kVar2.f2993y.get(), (K5.u) kVar2.f2981k.get(), (j6.n) kVar2.f2987s.get(), (Z) kVar2.f2961E.get(), (L5.i) kVar2.f2968L.get(), (k6.r) kVar2.f2982m.get());
                    case 18:
                        return new Z5.c((C1705m) kVar2.f2966J.get(), (W5.e) kVar2.B.get(), (J5.h) kVar2.f2978h.get(), (Z) kVar2.f2961E.get(), (C1310h) kVar2.f2963G.get());
                    case 19:
                        return new h6.p((C1705m) kVar2.f2966J.get(), (W5.e) kVar2.B.get());
                    default:
                        throw new AssertionError(i10);
                }
                return c0928a;
        }
    }
}
