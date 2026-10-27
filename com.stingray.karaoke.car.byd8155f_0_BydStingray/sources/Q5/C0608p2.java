package Q5;

import F0.C0221p;
import F0.C0223q;
import H7.InterfaceC0321f;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.player.PlayerSideMenuRoute;
import n3.C1930E;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: Q5.p2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0608p2 implements InterfaceC2786o {
    public final /* synthetic */ X5.H0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1930E f7024m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ PlayerStatus f7025n;

    public C0608p2(X5.H0 h02, C1930E c1930e, PlayerStatus playerStatus) {
        this.l = h02;
        this.f7024m = c1930e;
        this.f7025n = playerStatus;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        D.L l;
        T.S s6;
        boolean z6;
        InterfaceC2772a interfaceC2772a;
        V8.N n10;
        int i7;
        D.L l10 = (D.L) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(l10, "contentPadding");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(l10) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            X5.H0 h02 = this.l;
            T.X x8 = C0735d.x(h02.o, c0761q, 0);
            N5.q0 q0Var = h02.f10174m;
            V8.N n11 = q0Var.f4752v;
            int iIntValue2 = ((Number) com.bumptech.glide.c.R(q0Var.f4750t, c0761q, 0).getValue()).intValue();
            c0761q.Q(9821142);
            boolean zH = c0761q.h(h02);
            Object objG = c0761q.G();
            T.S s10 = C0753m.f8270a;
            if (zH || objG == s10) {
                l = l10;
                s6 = s10;
                C0221p c0221p = new C0221p(0, h02, X5.H0.class, "clearQueue", "clearQueue()V", 0, 21);
                c0761q.a0(c0221p);
                objG = c0221p;
            } else {
                l = l10;
                s6 = s10;
            }
            InterfaceC0321f interfaceC0321f = (InterfaceC0321f) objG;
            c0761q.p(false);
            c0761q.Q(9830717);
            boolean zBooleanValue = ((Boolean) x8.getValue()).booleanValue();
            final C1930E c1930e = this.f7024m;
            if (zBooleanValue) {
                c0761q.Q(9831901);
                boolean zH2 = c0761q.h(c1930e);
                Object objG2 = c0761q.G();
                if (zH2 || objG2 == s6) {
                    final int i10 = 0;
                    objG2 = new InterfaceC2772a() { // from class: Q5.o2
                        @Override // z7.InterfaceC2772a
                        public final Object a() {
                            switch (i10) {
                                case 0:
                                    c1930e.n(PlayerSideMenuRoute.Scoring.getValue(), new A3.c(22));
                                    break;
                                default:
                                    c1930e.n(PlayerSideMenuRoute.Explore.getValue(), new A3.c(23));
                                    break;
                            }
                            return m7.q.f19267a;
                        }
                    };
                    c0761q.a0(objG2);
                }
                interfaceC2772a = (InterfaceC2772a) objG2;
                z6 = false;
                c0761q.p(false);
            } else {
                z6 = false;
                interfaceC2772a = null;
            }
            InterfaceC2772a interfaceC2772a2 = interfaceC2772a;
            c0761q.p(z6);
            c0761q.Q(9841472);
            boolean zH3 = c0761q.h(h02);
            Object objG3 = c0761q.G();
            if (zH3 || objG3 == s6) {
                n10 = n11;
                C0223q c0223q = new C0223q(1, h02, X5.H0.class, "jumpToItemInPlaylist", "jumpToItemInPlaylist(Ljava/lang/String;)V", 0, 10);
                c0761q.a0(c0223q);
                objG3 = c0223q;
            } else {
                n10 = n11;
            }
            InterfaceC0321f interfaceC0321f2 = (InterfaceC0321f) objG3;
            c0761q.p(false);
            c0761q.Q(9843316);
            boolean zH4 = c0761q.h(h02);
            Object objG4 = c0761q.G();
            if (zH4 || objG4 == s6) {
                F0.r rVar = new F0.r(2, h02, X5.H0.class, "moveSong", "moveSong(Ljava/lang/String;I)V", 0, 9);
                c0761q.a0(rVar);
                objG4 = rVar;
            }
            InterfaceC0321f interfaceC0321f3 = (InterfaceC0321f) objG4;
            c0761q.p(false);
            c0761q.Q(9844757);
            boolean zH5 = c0761q.h(h02);
            Object objG5 = c0761q.G();
            if (zH5 || objG5 == s6) {
                i7 = iIntValue;
                F0.r rVar2 = new F0.r(2, h02, X5.H0.class, "onDragEnd", "onDragEnd(Ljava/lang/String;I)V", 0, 10);
                c0761q.a0(rVar2);
                objG5 = rVar2;
            } else {
                i7 = iIntValue;
            }
            InterfaceC0321f interfaceC0321f4 = (InterfaceC0321f) objG5;
            c0761q.p(false);
            c0761q.Q(9846334);
            boolean zH6 = c0761q.h(h02);
            Object objG6 = c0761q.G();
            if (zH6 || objG6 == s6) {
                C0223q c0223q2 = new C0223q(1, h02, X5.H0.class, "removeFromPlaylist", "removeFromPlaylist(Ljava/lang/String;)V", 0, 11);
                c0761q.a0(c0223q2);
                objG6 = c0223q2;
            }
            InterfaceC0321f interfaceC0321f5 = (InterfaceC0321f) objG6;
            c0761q.p(false);
            InterfaceC2772a interfaceC2772a3 = (InterfaceC2772a) interfaceC0321f;
            c0761q.Q(9822825);
            boolean zH7 = c0761q.h(c1930e);
            Object objG7 = c0761q.G();
            if (zH7 || objG7 == s6) {
                final int i11 = 1;
                objG7 = new InterfaceC2772a() { // from class: Q5.o2
                    @Override // z7.InterfaceC2772a
                    public final Object a() {
                        switch (i11) {
                            case 0:
                                c1930e.n(PlayerSideMenuRoute.Scoring.getValue(), new A3.c(22));
                                break;
                            default:
                                c1930e.n(PlayerSideMenuRoute.Explore.getValue(), new A3.c(23));
                                break;
                        }
                        return m7.q.f19267a;
                    }
                };
                c0761q.a0(objG7);
            }
            c0761q.p(false);
            l9.l.t(null, this.f7025n, n10, iIntValue2, interfaceC2772a3, (InterfaceC2772a) objG7, interfaceC2772a2, (InterfaceC2782k) interfaceC0321f2, (InterfaceC2782k) interfaceC0321f5, (InterfaceC2785n) interfaceC0321f3, (InterfaceC2785n) interfaceC0321f4, l, c0761q, 0, (i7 << 6) & 896);
        }
        return m7.q.f19267a;
    }
}
