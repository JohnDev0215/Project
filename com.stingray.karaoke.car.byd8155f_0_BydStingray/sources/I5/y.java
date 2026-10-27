package I5;

import A7.F;
import C0.K;
import D.AbstractC0120b;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.C0119a;
import D.C0124f;
import D.C0138u;
import D.L;
import D.P;
import D.T;
import E.C0147d;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import F0.C0221p;
import F0.C0223q;
import H7.InterfaceC0321f;
import N5.Z;
import Q.C0540x0;
import Q5.C0549b;
import Q5.G1;
import Q5.Q0;
import Q5.W1;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import T.S;
import T.X;
import V8.f0;
import X5.D0;
import a.AbstractC0904a;
import a6.C0928a;
import android.content.Context;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import c6.C1034a;
import com.stingray.drivemodedetector.DriveMode;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.MainActivity;
import com.stingray.karaoke.car.player.SongQueuePosition;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.kca.model.KaraokeItemChildType;
import com.stingray.kca.model.KaraokeSongResponseV2;
import g1.AbstractC1274a;
import g6.C1306d;
import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import k6.AbstractC1692D;
import n0.C1853C;
import n0.C1886o;
import n0.C1891t;
import n6.C1966G;
import n7.AbstractC1999n;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class y implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3027m;

    public y(DriveMode driveMode, InterfaceC2782k interfaceC2782k) {
        this.l = 9;
        this.f3027m = interfaceC2782k;
    }

    private final Object f(Object obj, Object obj2, Object obj3) {
        String str = (String) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(str, "id");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(str) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            String strX = AbstractC2673a.X(R.string.SongQueue_ClearSongQueue, c0761q);
            String strX2 = AbstractC2673a.X(R.string.SongQueue_ClearSongQueueConfirmation, c0761q);
            String strX3 = AbstractC2673a.X(R.string.SongQueue_ClearSongQueue, c0761q);
            String strX4 = AbstractC2673a.X(R.string.Global_Cancel, c0761q);
            c0761q.Q(175430512);
            InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.f3027m;
            boolean zF = c0761q.f(interfaceC2772a);
            Object objG = c0761q.G();
            if (zF || objG == C0753m.f8270a) {
                objG = new C0549b(interfaceC2772a, 11);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            com.bumptech.glide.d.d(str, strX, strX2, strX3, strX4, (InterfaceC2772a) objG, null, c0761q, iIntValue & 14);
        }
        return m7.q.f19267a;
    }

    private final Object h(Object obj, Object obj2, Object obj3) {
        L l = (L) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(l, "contentPadding");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(l) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            LoadingInterface$LoadingState loadingInterface$LoadingStateE = ((Z5.c) this.f3027m).e();
            c0761q.Q(-746236467);
            Z5.c cVar = (Z5.c) this.f3027m;
            boolean zH = c0761q.h(cVar);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                C0326b c0326b = new C0326b(0, cVar, Z5.c.class, "reloadData", "reloadData(Z)V", 0, 4);
                c0761q.a0(c0326b);
                objG = c0326b;
            }
            c0761q.p(false);
            AbstractC2673a.m(null, loadingInterface$LoadingStateE, (InterfaceC2772a) objG, l, c0761q, (iIntValue << 9) & 7168);
        }
        return m7.q.f19267a;
    }

    private final Object i(Object obj, Object obj2, Object obj3) {
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f((C0147d) obj, "$this$item");
        if ((iIntValue & 17) == 16 && c0761q.x()) {
            c0761q.K();
        } else {
            ((InterfaceC2785n) this.f3027m).invoke(c0761q, 0);
        }
        return m7.q.f19267a;
    }

    private final Object j(Object obj, Object obj2, Object obj3) {
        L l = (L) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(l, "contentPadding");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(l) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.l lVar = g0.l.f14684a;
            c0761q.Q(-476422188);
            C1306d c1306d = (C1306d) this.f3027m;
            boolean zH = c0761q.h(c1306d);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (zH || objG == s6) {
                objG = new C0223q(1, c1306d, C1306d.class, "onSelectSong", "onSelectSong(Lcom/stingray/kca/model/KaraokeSongResponseV2;)V", 0, 27);
                c0761q.a0(objG);
            }
            InterfaceC0321f interfaceC0321f = (InterfaceC0321f) objG;
            c0761q.p(false);
            c0761q.Q(-476420623);
            boolean zH2 = c0761q.h(c1306d);
            Object objG2 = c0761q.G();
            if (zH2 || objG2 == s6) {
                C0221p c0221p = new C0221p(0, c1306d, C1306d.class, "onSingAll", "onSingAll()V", 0, 24);
                c0761q.a0(c0221p);
                objG2 = c0221p;
            }
            InterfaceC0321f interfaceC0321f2 = (InterfaceC0321f) objG2;
            c0761q.p(false);
            c0761q.Q(-476419149);
            boolean zH3 = c0761q.h(c1306d);
            Object objG3 = c0761q.G();
            if (zH3 || objG3 == s6) {
                C0221p c0221p2 = new C0221p(0, c1306d, C1306d.class, "refreshData", "refreshData()V", 0, 25);
                c0761q.a0(c0221p2);
                objG3 = c0221p2;
            }
            InterfaceC0321f interfaceC0321f3 = (InterfaceC0321f) objG3;
            c0761q.p(false);
            List list = (List) com.bumptech.glide.c.R(c1306d.f14987n, c0761q, 0).getValue();
            if (list == null) {
                list = n7.u.l;
            }
            l9.d.q(lVar, (InterfaceC2782k) interfaceC0321f, list, (InterfaceC2772a) interfaceC0321f2, (InterfaceC2772a) interfaceC0321f3, l, c0761q, ((iIntValue << 15) & 458752) | 6);
        }
        return m7.q.f19267a;
    }

    private final Object l(Object obj, Object obj2, Object obj3) {
        String str = (String) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(str, "id");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(str) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(-2006223737);
            W5.e eVar = (W5.e) this.f3027m;
            boolean zH = c0761q.h(eVar);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                objG = new A3.g(eVar, 5);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            io.sentry.config.a.u(str, (InterfaceC2772a) objG, c0761q, iIntValue & 14);
        }
        return m7.q.f19267a;
    }

    private final Object m(Object obj, Object obj2, Object obj3) {
        C0761q c0761q = (C0761q) obj2;
        ((Number) obj3).intValue();
        A7.m.f((w.q) obj, "$this$AnimatedVisibility");
        g0.o oVarB = androidx.compose.foundation.layout.c.b(androidx.compose.foundation.layout.c.d(g0.l.f14684a, 1.0f), 0.5f);
        g0.g gVar = g0.b.f14662m;
        ((C0138u) this.f3027m).getClass();
        g0.o oVarA = androidx.compose.foundation.layout.b.f11474a.a(oVarB, gVar);
        c0761q.Q(-488760352);
        c0761q.Q(-276358916);
        List listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.0f), new C1891t(l6.e.o)), new m7.i(Float.valueOf(1.0f), new C1891t(C1891t.f19451j)));
        c0761q.p(false);
        m7.i[] iVarArr = (m7.i[]) listD.toArray(new m7.i[0]);
        C1853C c1853cN = C1886o.n((m7.i[]) Arrays.copyOf(iVarArr, iVarArr.length));
        c0761q.p(false);
        AbstractC0133o.a(androidx.compose.foundation.a.a(oVarA, c1853cN, null, 6), c0761q, 0);
        return m7.q.f19267a;
    }

    private final Object n(Object obj, Object obj2, Object obj3) {
        String str = (String) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(str, "id");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(str) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            io.sentry.config.a.v(str, (D0) this.f3027m, c0761q, iIntValue & 14);
        }
        return m7.q.f19267a;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        g0.l lVar = g0.l.f14684a;
        S s6 = C0753m.f8270a;
        m7.q qVar = m7.q.f19267a;
        int i7 = 0;
        Object obj4 = this.f3027m;
        switch (this.l) {
            case 0:
                String str = (String) obj;
                C0761q c0761q = (C0761q) obj2;
                int iIntValue = ((Number) obj3).intValue();
                A7.m.f(str, "id");
                if ((iIntValue & 6) == 0) {
                    iIntValue |= c0761q.f(str) ? 4 : 2;
                }
                if ((iIntValue & 19) == 18 && c0761q.x()) {
                    c0761q.K();
                } else {
                    c0761q.Q(-519426606);
                    MainActivity mainActivity = (MainActivity) obj4;
                    boolean zH = c0761q.h(mainActivity);
                    Object objG = c0761q.G();
                    if (zH || objG == s6) {
                        objG = new q(mainActivity, i);
                        c0761q.a0(objG);
                    }
                    c0761q.p(false);
                    l9.l.e(str, (InterfaceC2772a) objG, c0761q, iIntValue & 14);
                }
                return qVar;
            case 1:
                String str2 = (String) obj;
                C0761q c0761q2 = (C0761q) obj2;
                int iIntValue2 = ((Number) obj3).intValue();
                A7.m.f(str2, "id");
                if ((iIntValue2 & 6) == 0) {
                    iIntValue2 |= c0761q2.f(str2) ? 4 : 2;
                }
                if ((iIntValue2 & 19) == 18 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    io.sentry.config.a.s(str2, ((Boolean) C0735d.x(((Z) obj4).f4658g.c, c0761q2, 0).getValue()).booleanValue(), c0761q2, iIntValue2 & 14);
                }
                return qVar;
            case 2:
                L l = (L) obj;
                C0761q c0761q3 = (C0761q) obj2;
                int iIntValue3 = ((Number) obj3).intValue();
                A7.m.f(l, "contentPadding");
                if ((iIntValue3 & 6) == 0) {
                    iIntValue3 |= c0761q3.f(l) ? 4 : 2;
                }
                if ((iIntValue3 & 19) == 18 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    C0928a c0928a = (C0928a) obj4;
                    List list = (List) com.bumptech.glide.c.R(c0928a.f12274x, c0761q3, 0).getValue();
                    KaraokeItemChildType karaokeItemChildType = KaraokeItemChildType.ARTIST;
                    c0761q3.Q(-1389848015);
                    boolean zF = c0761q3.f(c0928a);
                    Object objG2 = c0761q3.G();
                    if (zF || objG2 == s6) {
                        objG2 = new F(c0928a, 5);
                        c0761q3.a0(objG2);
                    }
                    c0761q3.p(false);
                    ArrayList arrayListV = com.bumptech.glide.c.V(list, karaokeItemChildType, false, false, (InterfaceC2782k) objG2, 0, c0761q3, 40);
                    boolean z6 = ((LoadingInterface$LoadingState) c0928a.f12275y.getValue()) == LoadingInterface$LoadingState.Loading;
                    c0761q3.Q(-1389833559);
                    boolean zF2 = c0761q3.f(c0928a);
                    Object objG3 = c0761q3.G();
                    if (zF2 || objG3 == s6) {
                        objG3 = new C0221p(0, c0928a, C0928a.class, "loadMoreItems", "loadMoreItems()V", 0, 7);
                        c0761q3.a0(objG3);
                    }
                    c0761q3.p(false);
                    InterfaceC2772a interfaceC2772a = (InterfaceC2772a) ((InterfaceC0321f) objG3);
                    c0761q3.Q(-1389831665);
                    boolean zF3 = c0761q3.f(c0928a);
                    Object objG4 = c0761q3.G();
                    if (zF3 || objG4 == s6) {
                        objG4 = new F0.r(2, c0928a, C0928a.class, "setFirstVisibleItem", "setFirstVisibleItem(II)V", 0, 1);
                        c0761q3.a0(objG4);
                    }
                    c0761q3.p(false);
                    AbstractC1274a.c(null, arrayListV, z6, interfaceC2772a, (InterfaceC2785n) ((InterfaceC0321f) objG4), (b6.g) c0928a.f12241k.getValue(), (Integer) com.bumptech.glide.c.R(c0928a.f12268E, c0761q3, 0).getValue(), l, c0761q3, (iIntValue3 << 21) & 29360128);
                }
                return qVar;
            case 3:
                L l10 = (L) obj;
                C0761q c0761q4 = (C0761q) obj2;
                int iIntValue4 = ((Number) obj3).intValue();
                A7.m.f(l10, "contentPadding");
                if ((iIntValue4 & 6) == 0) {
                    iIntValue4 |= c0761q4.f(l10) ? 4 : 2;
                }
                if ((iIntValue4 & 19) == 18 && c0761q4.x()) {
                    c0761q4.K();
                } else {
                    C1034a c1034a = (C1034a) obj4;
                    l9.d.e(null, (String) com.bumptech.glide.c.R(c1034a.f12408m, c0761q4, 0).getValue(), ((Boolean) com.bumptech.glide.c.R(c1034a.l.f18232f, c0761q4, 0).getValue()).booleanValue(), l10, c0761q4, (iIntValue4 << 9) & 7168);
                }
                return qVar;
            case 4:
                L l11 = (L) obj;
                C0761q c0761q5 = (C0761q) obj2;
                int iIntValue5 = ((Number) obj3).intValue();
                A7.m.f(l11, "contentPadding");
                if ((iIntValue5 & 6) == 0) {
                    iIntValue5 |= c0761q5.f(l11) ? 4 : 2;
                }
                if ((iIntValue5 & 19) == 18 && c0761q5.x()) {
                    c0761q5.K();
                } else {
                    e6.g gVar = (e6.g) obj4;
                    List list2 = (List) gVar.f14451x.getValue();
                    String str3 = (String) com.bumptech.glide.c.R(gVar.f14450w, c0761q5, 0).getValue();
                    List list3 = (List) com.bumptech.glide.c.R(gVar.f14452y, c0761q5, 0).getValue();
                    int i10 = gVar.f14445A.i();
                    c0761q5.Q(-1701225108);
                    e6.g gVar2 = (e6.g) obj4;
                    boolean zH2 = c0761q5.h(gVar2);
                    Object objG5 = c0761q5.G();
                    if (zH2 || objG5 == s6) {
                        objG5 = new C0223q(1, gVar2, e6.g.class, "changeTab", "changeTab(I)V", 0, 5);
                        c0761q5.a0(objG5);
                    }
                    InterfaceC0321f interfaceC0321f = (InterfaceC0321f) objG5;
                    c0761q5.p(false);
                    LoadingInterface$LoadingState loadingInterface$LoadingState = (LoadingInterface$LoadingState) com.bumptech.glide.c.R(gVar.f14453z, c0761q5, 0).getValue();
                    c0761q5.Q(-1701220375);
                    e6.g gVar3 = (e6.g) obj4;
                    boolean zH3 = c0761q5.h(gVar3);
                    Object objG6 = c0761q5.G();
                    if (zH3 || objG6 == s6) {
                        objG6 = new F0.r(2, gVar3, e6.g.class, "search", "search(Ljava/lang/String;J)V", 0, 2);
                        c0761q5.a0(objG6);
                    }
                    InterfaceC0321f interfaceC0321f2 = (InterfaceC0321f) objG6;
                    c0761q5.p(false);
                    c0761q5.Q(-1701218792);
                    e6.g gVar4 = (e6.g) obj4;
                    boolean zH4 = c0761q5.h(gVar4);
                    Object objG7 = c0761q5.G();
                    if (zH4 || objG7 == s6) {
                        objG7 = new F0.r(2, gVar4, e6.g.class, "onKaraokeItemSelected", "onKaraokeItemSelected(Lcom/stingray/kca/model/KaraokeItem;Lcom/stingray/shared/logs/segment/songQueue/SourceLog;)V", 0, 3);
                        c0761q5.a0(objG7);
                    }
                    InterfaceC0321f interfaceC0321f3 = (InterfaceC0321f) objG7;
                    c0761q5.p(false);
                    c0761q5.Q(-1701216776);
                    e6.g gVar5 = (e6.g) obj4;
                    boolean zH5 = c0761q5.h(gVar5);
                    Object objG8 = c0761q5.G();
                    if (zH5 || objG8 == s6) {
                        objG8 = new K0.k(1, gVar5, e6.g.class, "onKaraokeItemSelected", "onKaraokeItemSelected(Lcom/stingray/kca/model/KaraokeItem;Lcom/stingray/shared/logs/segment/songQueue/SourceLog;)V", 0, 1);
                        c0761q5.a0(objG8);
                    }
                    InterfaceC2782k interfaceC2782k = (InterfaceC2782k) objG8;
                    c0761q5.p(false);
                    c0761q5.Q(-1701214634);
                    e6.g gVar6 = (e6.g) obj4;
                    boolean zH6 = c0761q5.h(gVar6);
                    Object objG9 = c0761q5.G();
                    if (zH6 || objG9 == s6) {
                        objG9 = new F0.r(2, gVar6, e6.g.class, "setFirstVisibleItem", "setFirstVisibleItem(II)V", 0, 4);
                        c0761q5.a0(objG9);
                    }
                    c0761q5.p(false);
                    AbstractC0904a.b(null, list2, list3, str3, i10, loadingInterface$LoadingState, (InterfaceC2785n) interfaceC0321f2, (InterfaceC2785n) interfaceC0321f3, interfaceC2782k, (InterfaceC2782k) interfaceC0321f, (InterfaceC2785n) ((InterfaceC0321f) objG9), (b6.g) gVar.f12241k.getValue(), l11, (Integer) com.bumptech.glide.c.R(gVar.B, c0761q5, 0).getValue(), ((Boolean) com.bumptech.glide.c.R(gVar.f14446C, c0761q5, 0).getValue()).booleanValue(), c0761q5, 0, (iIntValue5 << 6) & 896);
                }
                return qVar;
            case 5:
                L l12 = (L) obj;
                C0761q c0761q6 = (C0761q) obj2;
                int iIntValue6 = ((Number) obj3).intValue();
                A7.m.f(l12, "contentPadding");
                if ((iIntValue6 & 6) == 0) {
                    iIntValue6 |= c0761q6.f(l12) ? 4 : 2;
                }
                if ((iIntValue6 & 19) == 18 && c0761q6.x()) {
                    c0761q6.K();
                } else {
                    P8.n.b(null, (String) com.bumptech.glide.c.R(((h6.h) obj4).o, c0761q6, 0).getValue(), l12, c0761q6, (iIntValue6 << 6) & 896);
                }
                return qVar;
            case 6:
                L l13 = (L) obj;
                C0761q c0761q7 = (C0761q) obj2;
                int iIntValue7 = ((Number) obj3).intValue();
                A7.m.f(l13, "contentPadding");
                if ((iIntValue7 & 6) == 0) {
                    iIntValue7 |= c0761q7.f(l13) ? 4 : 2;
                }
                if ((iIntValue7 & 19) == 18 && c0761q7.x()) {
                    c0761q7.K();
                } else {
                    List list4 = (List) ((f6.g) obj4).f14616x.getValue();
                    c0761q7.Q(1521297296);
                    f6.g gVar7 = (f6.g) obj4;
                    boolean zH7 = c0761q7.h(gVar7);
                    Object objG10 = c0761q7.G();
                    if (zH7 || objG10 == s6) {
                        objG10 = new F0.r(2, gVar7, f6.g.class, "onKaraokeItemSelected", "onKaraokeItemSelected(Lcom/stingray/kca/model/KaraokeItem;Lcom/stingray/shared/logs/segment/songQueue/SourceLog;)V", 0, 5);
                        c0761q7.a0(objG10);
                    }
                    c0761q7.p(false);
                    InterfaceC2785n interfaceC2785n = (InterfaceC2785n) ((InterfaceC0321f) objG10);
                    c0761q7.Q(1521299280);
                    f6.g gVar8 = (f6.g) obj4;
                    boolean zH8 = c0761q7.h(gVar8);
                    Object objG11 = c0761q7.G();
                    if (zH8 || objG11 == s6) {
                        objG11 = new K0.k(1, gVar8, f6.g.class, "onKaraokeItemSelected", "onKaraokeItemSelected(Lcom/stingray/kca/model/KaraokeItem;Lcom/stingray/shared/logs/segment/songQueue/SourceLog;)V", 0, 2);
                        c0761q7.a0(objG11);
                    }
                    c0761q7.p(false);
                    com.bumptech.glide.d.g(null, list4, l13, interfaceC2785n, (InterfaceC2782k) objG11, c0761q7, (iIntValue7 << 6) & 896);
                }
                return qVar;
            case 7:
                L l14 = (L) obj;
                C0761q c0761q8 = (C0761q) obj2;
                int iIntValue8 = ((Number) obj3).intValue();
                A7.m.f(l14, "contentPadding");
                if ((iIntValue8 & 6) == 0) {
                    iIntValue8 |= c0761q8.f(l14) ? 4 : 2;
                }
                if ((iIntValue8 & 19) == 18 && c0761q8.x()) {
                    c0761q8.K();
                } else {
                    f6.o oVar = (f6.o) obj4;
                    List list5 = (List) oVar.f14638x.getValue();
                    c0761q8.Q(-1903923270);
                    f6.o oVar2 = (f6.o) obj4;
                    boolean zH9 = c0761q8.h(oVar2);
                    Object objG12 = c0761q8.G();
                    if (zH9 || objG12 == s6) {
                        objG12 = new C0221p(0, oVar2, f6.o.class, "navigateToSettings", "navigateToSettings()V", 0, 8);
                        c0761q8.a0(objG12);
                    }
                    c0761q8.p(false);
                    InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) ((InterfaceC0321f) objG12);
                    c0761q8.Q(-1903921315);
                    f6.o oVar3 = (f6.o) obj4;
                    boolean zH10 = c0761q8.h(oVar3);
                    Object objG13 = c0761q8.G();
                    if (zH10 || objG13 == s6) {
                        objG13 = new F0.r(2, oVar3, f6.o.class, "onKaraokeItemSelected", "onKaraokeItemSelected(Lcom/stingray/kca/model/KaraokeItem;Lcom/stingray/shared/logs/segment/songQueue/SourceLog;)V", 0, 6);
                        c0761q8.a0(objG13);
                    }
                    c0761q8.p(false);
                    InterfaceC2785n interfaceC2785n2 = (InterfaceC2785n) ((InterfaceC0321f) objG13);
                    c0761q8.Q(-1903919299);
                    f6.o oVar4 = (f6.o) obj4;
                    boolean zH11 = c0761q8.h(oVar4);
                    Object objG14 = c0761q8.G();
                    if (zH11 || objG14 == s6) {
                        objG14 = new K0.k(1, oVar4, f6.o.class, "onKaraokeItemSelected", "onKaraokeItemSelected(Lcom/stingray/kca/model/KaraokeItem;Lcom/stingray/shared/logs/segment/songQueue/SourceLog;)V", 0, 3);
                        c0761q8.a0(objG14);
                    }
                    InterfaceC2782k interfaceC2782k2 = (InterfaceC2782k) objG14;
                    c0761q8.p(false);
                    boolean zBooleanValue = ((Boolean) com.bumptech.glide.c.R(oVar.f12229u.f18294e, c0761q8, 0).getValue()).booleanValue();
                    c0761q8.Q(-1903913538);
                    f6.o oVar5 = (f6.o) obj4;
                    boolean zH12 = c0761q8.h(oVar5);
                    Object objG15 = c0761q8.G();
                    if (zH12 || objG15 == s6) {
                        objG15 = new C0223q(1, oVar5, f6.o.class, "toggleParentalControls", "toggleParentalControls(Z)V", 0, 6);
                        c0761q8.a0(objG15);
                    }
                    c0761q8.p(false);
                    InterfaceC2782k interfaceC2782k3 = (InterfaceC2782k) ((InterfaceC0321f) objG15);
                    c0761q8.Q(-1903911729);
                    f6.o oVar6 = (f6.o) obj4;
                    boolean zH13 = c0761q8.h(oVar6);
                    Object objG16 = c0761q8.G();
                    if (zH13 || objG16 == s6) {
                        objG16 = new C0221p(0, oVar6, f6.o.class, "openFaq", "openFaq()V", 0, 9);
                        c0761q8.a0(objG16);
                    }
                    c0761q8.p(false);
                    InterfaceC2772a interfaceC2772a3 = (InterfaceC2772a) ((InterfaceC0321f) objG16);
                    c0761q8.Q(-1903910187);
                    f6.o oVar7 = (f6.o) obj4;
                    boolean zH14 = c0761q8.h(oVar7);
                    Object objG17 = c0761q8.G();
                    if (zH14 || objG17 == s6) {
                        objG17 = new C0221p(0, oVar7, f6.o.class, "openContactUs", "openContactUs()V", 0, 10);
                        c0761q8.a0(objG17);
                    }
                    c0761q8.p(false);
                    InterfaceC2772a interfaceC2772a4 = (InterfaceC2772a) ((InterfaceC0321f) objG17);
                    c0761q8.Q(-1903908591);
                    f6.o oVar8 = (f6.o) obj4;
                    boolean zH15 = c0761q8.h(oVar8);
                    Object objG18 = c0761q8.G();
                    if (zH15 || objG18 == s6) {
                        objG18 = new C0221p(0, oVar8, f6.o.class, "openAbout", "openAbout()V", 0, 11);
                        c0761q8.a0(objG18);
                    }
                    c0761q8.p(false);
                    l9.l.m(null, list5, interfaceC2772a2, interfaceC2785n2, interfaceC2782k2, zBooleanValue, interfaceC2782k3, interfaceC2772a3, interfaceC2772a4, (InterfaceC2772a) ((InterfaceC0321f) objG18), l14, c0761q8, 0, iIntValue8 & 14);
                }
                return qVar;
            case 8:
                L l15 = (L) obj;
                C0761q c0761q9 = (C0761q) obj2;
                int iIntValue9 = ((Number) obj3).intValue();
                A7.m.f(l15, "contentPadding");
                if ((iIntValue9 & 6) == 0) {
                    iIntValue9 |= c0761q9.f(l15) ? 4 : 2;
                }
                if ((iIntValue9 & 19) == 18 && c0761q9.x()) {
                    c0761q9.K();
                } else {
                    c0761q9.Q(-1941641838);
                    f6.p pVar = (f6.p) obj4;
                    boolean zF4 = c0761q9.f(pVar);
                    Object objG19 = c0761q9.G();
                    if (zF4 || objG19 == s6) {
                        objG19 = new F0.r(2, pVar, f6.p.class, "setFirstVisibleItem", "setFirstVisibleItem(II)V", 0, 7);
                        c0761q9.a0(objG19);
                    }
                    c0761q9.p(false);
                    InterfaceC2785n interfaceC2785n3 = (InterfaceC2785n) ((InterfaceC0321f) objG19);
                    b6.g gVar9 = (b6.g) pVar.f12241k.getValue();
                    boolean zBooleanValue2 = ((Boolean) C0735d.x(pVar.f12229u.f18294e, c0761q9, 0).getValue()).booleanValue();
                    c0761q9.Q(-1941634347);
                    boolean zF5 = c0761q9.f(pVar);
                    Object objG20 = c0761q9.G();
                    if (zF5 || objG20 == s6) {
                        objG20 = new C0223q(1, pVar, f6.p.class, "toggleParentalControls", "toggleParentalControls(Z)V", 0, 8);
                        c0761q9.a0(objG20);
                    }
                    c0761q9.p(false);
                    InterfaceC2782k interfaceC2782k4 = (InterfaceC2782k) ((InterfaceC0321f) objG20);
                    c0761q9.Q(-1941632538);
                    boolean zF6 = c0761q9.f(pVar);
                    Object objG21 = c0761q9.G();
                    if (zF6 || objG21 == s6) {
                        objG21 = new C0221p(0, pVar, f6.p.class, "openFaq", "openFaq()V", 0, 14);
                        c0761q9.a0(objG21);
                    }
                    c0761q9.p(false);
                    InterfaceC2772a interfaceC2772a5 = (InterfaceC2772a) ((InterfaceC0321f) objG21);
                    c0761q9.Q(-1941630996);
                    boolean zF7 = c0761q9.f(pVar);
                    Object objG22 = c0761q9.G();
                    if (zF7 || objG22 == s6) {
                        objG22 = new C0221p(0, pVar, f6.p.class, "openContactUs", "openContactUs()V", 0, 15);
                        c0761q9.a0(objG22);
                    }
                    c0761q9.p(false);
                    InterfaceC2772a interfaceC2772a6 = (InterfaceC2772a) ((InterfaceC0321f) objG22);
                    c0761q9.Q(-1941629400);
                    boolean zF8 = c0761q9.f(pVar);
                    Object objG23 = c0761q9.G();
                    if (zF8 || objG23 == s6) {
                        C0221p c0221p = new C0221p(0, pVar, f6.p.class, "openAbout", "openAbout()V", 0, 16);
                        c0761q9.a0(c0221p);
                        objG23 = c0221p;
                    }
                    c0761q9.p(false);
                    AbstractC1274a.p(null, interfaceC2785n3, gVar9, zBooleanValue2, interfaceC2782k4, interfaceC2772a5, interfaceC2772a6, (InterfaceC2772a) ((InterfaceC0321f) objG23), l15, c0761q9, (iIntValue9 << 24) & 234881024);
                }
                return qVar;
            case 9:
                C0138u c0138u = (C0138u) obj;
                C0761q c0761q10 = (C0761q) obj2;
                int iIntValue10 = ((Number) obj3).intValue();
                A7.m.f(c0138u, "$this$BoxWithConstraints");
                if ((iIntValue10 & 6) == 0) {
                    iIntValue10 |= c0761q10.f(c0138u) ? 4 : 2;
                }
                if ((iIntValue10 & 19) == 18 && c0761q10.x()) {
                    c0761q10.K();
                } else {
                    float fB = c0138u.b() / 2;
                    m6.b bVar = m6.c.f19252a;
                    g0.o oVarQ = androidx.compose.foundation.layout.c.q(lVar, 0.0f, bVar.q0(c0761q10), 1);
                    g0.f fVar = g0.b.f14670v;
                    C0119a c0119a = AbstractC0127i.f1076a;
                    D.S sB = P.b(new C0124f(bVar.F(c0761q10)), fVar, c0761q10, 48);
                    int i11 = c0761q10.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M = c0761q10.m();
                    g0.o oVarD = g0.a.d(c0761q10, oVarQ);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i = C0160j.f1678b;
                    c0761q10.U();
                    if (c0761q10.f8303O) {
                        c0761q10.l(c0159i);
                    } else {
                        c0761q10.d0();
                    }
                    C0735d.V(c0761q10, sB, C0160j.f1679e);
                    C0735d.V(c0761q10, interfaceC0744h0M, C0160j.d);
                    C0158h c0158h = C0160j.f1680f;
                    if (c0761q10.f8303O || !A7.m.a(c0761q10.G(), Integer.valueOf(i11))) {
                        AbstractC1347d.l(i11, c0761q10, i11, c0158h);
                    }
                    C0735d.V(c0761q10, oVarD, C0160j.c);
                    c0761q10.Q(-1522567570);
                    c0761q10.p(false);
                    c0761q10.Q(46174546);
                    g0.o oVarP = androidx.compose.foundation.layout.c.p(lVar, bVar.A(c0761q10), fB);
                    Integer numValueOf = Integer.valueOf(R.drawable.ic_sing_now);
                    String strX = AbstractC2673a.X(R.string.Global_SingNow, c0761q10);
                    c0761q10.Q(-1522521122);
                    InterfaceC2782k interfaceC2782k5 = (InterfaceC2782k) obj4;
                    boolean zF9 = c0761q10.f(interfaceC2782k5);
                    Object objG24 = c0761q10.G();
                    if (zF9 || objG24 == s6) {
                        objG24 = new G1(2, interfaceC2782k5);
                        c0761q10.a0(objG24);
                    }
                    c0761q10.p(false);
                    io.sentry.config.a.p(oVarP, numValueOf, false, strX, 0L, null, null, 0L, 0.0f, (InterfaceC2772a) objG24, c0761q10, 0, 500);
                    c0761q10.p(false);
                    g0.o oVarP2 = androidx.compose.foundation.layout.c.p(lVar, bVar.A(c0761q10), fB);
                    Integer numValueOf2 = Integer.valueOf(R.drawable.ic_add);
                    String strX2 = AbstractC2673a.X(R.string.Global_SingLast, c0761q10);
                    c0761q10.Q(-1522509097);
                    boolean zF10 = c0761q10.f(interfaceC2782k5);
                    Object objG25 = c0761q10.G();
                    if (zF10 || objG25 == s6) {
                        objG25 = new G1(3, interfaceC2782k5);
                        c0761q10.a0(objG25);
                    }
                    c0761q10.p(false);
                    io.sentry.config.a.o(oVarP2, numValueOf2, false, strX2, 0L, null, false, null, null, 0L, 0.0f, (InterfaceC2772a) objG25, c0761q10, 0, 0, 2036);
                    c0761q10.Q(-1522506454);
                    AbstractC0120b.b(c0761q10, T.b(lVar, 1.0f));
                    c0761q10.p(false);
                    c0761q10.Q(-1522503218);
                    c0761q10.p(false);
                    c0761q10.p(true);
                }
                return qVar;
            case 10:
                L l16 = (L) obj;
                C0761q c0761q11 = (C0761q) obj2;
                int iIntValue11 = ((Number) obj3).intValue();
                A7.m.f(l16, "contentPadding");
                if ((iIntValue11 & 6) == 0) {
                    iIntValue11 |= c0761q11.f(l16) ? 4 : 2;
                }
                if ((iIntValue11 & 19) == 18 && c0761q11.x()) {
                    c0761q11.K();
                } else {
                    a1.b bVar2 = (a1.b) c0761q11.k(AbstractC0227s0.f2259f);
                    c0761q11.Q(966251539);
                    Object objG26 = c0761q11.G();
                    if (objG26 == s6) {
                        objG26 = C0735d.O(new a1.e(0), S.f8224q);
                        c0761q11.a0(objG26);
                    }
                    X x8 = (X) objG26;
                    c0761q11.p(false);
                    final boolean zBooleanValue3 = ((Boolean) c0761q11.k(AbstractC1692D.f18239b)).booleanValue();
                    final Context context = (Context) c0761q11.k(AndroidCompositionLocals_androidKt.f11688b);
                    c0761q11.Q(966257056);
                    final d6.j jVar = (d6.j) obj4;
                    boolean zH16 = c0761q11.h(context) | c0761q11.g(zBooleanValue3) | c0761q11.h(jVar);
                    Object objG27 = c0761q11.G();
                    if (zH16 || objG27 == s6) {
                        objG27 = new InterfaceC2782k() { // from class: Q5.V1
                            @Override // z7.InterfaceC2782k
                            public final Object b(Object obj5) {
                                SongQueuePosition songQueuePosition = (SongQueuePosition) obj5;
                                A7.m.f(songQueuePosition, "position");
                                d6.j jVar2 = jVar;
                                io.sentry.config.a.I(songQueuePosition, context, zBooleanValue3, new C0221p(0, jVar2, d6.j.class, "onClosePressed", "onClosePressed()V", 0, 18), new C0221p(0, jVar2, d6.j.class, "onBackPressed", "onBackPressed()V", 0, 19), new C0221p(0, jVar2, d6.j.class, "openPlayer", "openPlayer()V", 0, 20), new X1(4, 0, d6.j.class, jVar2, "addSongToQueue", "addSongToQueue(Lcom/stingray/kca/model/KaraokeSongResponseV2;Lcom/stingray/karaoke/car/player/SongQueuePosition;ZLcom/stingray/shared/logs/segment/songQueue/SourceLog;)Lcom/stingray/karaoke/car/ui/viewmodel/details/AddSongToQueueResult;"), (KaraokeSongResponseV2) jVar2.f13486y.getValue(), W5.e.d(jVar2.f12234b.b(jVar2.d)), jVar2.f13480E);
                                return m7.q.f19267a;
                            }
                        };
                        c0761q11.a0(objG27);
                    }
                    InterfaceC2782k interfaceC2782k6 = (InterfaceC2782k) objG27;
                    c0761q11.p(false);
                    c0761q11.Q(966276429);
                    boolean zH17 = c0761q11.h(jVar) | c0761q11.g(zBooleanValue3);
                    Object objG28 = c0761q11.G();
                    if (zH17 || objG28 == s6) {
                        objG28 = new W1(i7, jVar, zBooleanValue3);
                        c0761q11.a0(objG28);
                    }
                    InterfaceC2772a interfaceC2772a7 = (InterfaceC2772a) objG28;
                    c0761q11.p(false);
                    g0.o oVarC = androidx.compose.foundation.layout.c.c(lVar, 1.0f);
                    c0761q11.Q(966282821);
                    boolean zF11 = c0761q11.f(bVar2);
                    Object objG29 = c0761q11.G();
                    if (zF11 || objG29 == s6) {
                        objG29 = new Q0(bVar2, x8, 4);
                        c0761q11.a0(objG29);
                    }
                    c0761q11.p(false);
                    g0.o oVarE = androidx.compose.ui.layout.a.e(oVarC, (InterfaceC2782k) objG29);
                    K kE = AbstractC0133o.e(g0.b.l, false);
                    int i12 = c0761q11.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M2 = c0761q11.m();
                    g0.o oVarD2 = g0.a.d(c0761q11, oVarE);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i2 = C0160j.f1678b;
                    c0761q11.U();
                    if (c0761q11.f8303O) {
                        c0761q11.l(c0159i2);
                    } else {
                        c0761q11.d0();
                    }
                    C0735d.V(c0761q11, kE, C0160j.f1679e);
                    C0735d.V(c0761q11, interfaceC0744h0M2, C0160j.d);
                    C0158h c0158h2 = C0160j.f1680f;
                    if (c0761q11.f8303O || !A7.m.a(c0761q11.G(), Integer.valueOf(i12))) {
                        AbstractC1347d.l(i12, c0761q11, i12, c0158h2);
                    }
                    C0735d.V(c0761q11, oVarD2, C0160j.c);
                    KaraokeSongResponseV2 karaokeSongResponseV2 = (KaraokeSongResponseV2) jVar.f13486y.getValue();
                    C1966G c1966g = (C1966G) jVar.f13478C.getValue();
                    List list6 = (List) jVar.f13477A.getValue();
                    LoadingInterface$LoadingState loadingInterface$LoadingState2 = (LoadingInterface$LoadingState) jVar.B.getValue();
                    c0761q11.Q(1884129420);
                    boolean zH18 = c0761q11.h(jVar);
                    Object objG30 = c0761q11.G();
                    if (zH18 || objG30 == s6) {
                        objG30 = new C0221p(0, jVar, d6.j.class, "loadRelatedSongs", "loadRelatedSongs()V", 0, 17);
                        c0761q11.a0(objG30);
                    }
                    c0761q11.p(false);
                    InterfaceC2772a interfaceC2772a8 = (InterfaceC2772a) ((InterfaceC0321f) objG30);
                    c0761q11.Q(1884131409);
                    boolean zH19 = c0761q11.h(jVar);
                    Object objG31 = c0761q11.G();
                    if (zH19 || objG31 == s6) {
                        objG31 = new F0.r(2, jVar, d6.j.class, "onKaraokeItemSelected", "onKaraokeItemSelected(Lcom/stingray/kca/model/KaraokeItem;Lcom/stingray/shared/logs/segment/songQueue/SourceLog;)V", 0, 8);
                        c0761q11.a0(objG31);
                    }
                    c0761q11.p(false);
                    io.sentry.config.a.C(512, (iIntValue11 << 3) & 112, l16, c0761q11, (DriveMode) C0735d.x(jVar.f13479D, c0761q11, 0).getValue(), loadingInterface$LoadingState2, karaokeSongResponseV2, null, list6, c1966g, interfaceC2772a8, interfaceC2772a7, interfaceC2782k6, (InterfaceC2785n) ((InterfaceC0321f) objG31), ((Boolean) jVar.f13487z.getValue()).booleanValue());
                    c0761q11.p(true);
                }
                return qVar;
            case 11:
                String str4 = (String) obj;
                C0761q c0761q12 = (C0761q) obj2;
                int iIntValue12 = ((Number) obj3).intValue();
                A7.m.f(str4, "id");
                if ((iIntValue12 & 6) == 0) {
                    iIntValue12 |= c0761q12.f(str4) ? 4 : 2;
                }
                if ((iIntValue12 & 19) == 18 && c0761q12.x()) {
                    c0761q12.K();
                } else {
                    io.sentry.config.a.s(str4, ((Boolean) C0735d.x((f0) obj4, c0761q12, 0).getValue()).booleanValue(), c0761q12, iIntValue12 & 14);
                }
                return qVar;
            case 12:
                return f(obj, obj2, obj3);
            case 13:
                C0761q c0761q13 = (C0761q) obj2;
                int iIntValue13 = ((Number) obj3).intValue();
                A7.m.f((T) obj, "$this$SwipeToDismissBox");
                if ((iIntValue13 & 17) == 16 && c0761q13.x()) {
                    c0761q13.K();
                } else {
                    l9.l.x((C0540x0) obj4, c0761q13, 0);
                }
                return qVar;
            case 14:
                return h(obj, obj2, obj3);
            case 15:
                return i(obj, obj2, obj3);
            case 16:
                return j(obj, obj2, obj3);
            case 17:
                return l(obj, obj2, obj3);
            case 18:
                return m(obj, obj2, obj3);
            case 19:
                return n(obj, obj2, obj3);
            default:
                ((Number) obj3).intValue();
                A7.m.f((w.q) obj, "$this$AnimatedVisibility");
                ((k6.v) obj4).f18301b.invoke((C0761q) obj2, 0);
                return qVar;
        }
    }

    public /* synthetic */ y(Object obj, int i7) {
        this.l = i7;
        this.f3027m = obj;
    }
}
