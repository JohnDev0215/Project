package Q5;

import T.C0735d;
import T.C0761q;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.player.SideMenuType;
import g1.AbstractC1274a;
import n3.C1930E;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class U2 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f6588m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6589n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f6590p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f6591q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f6592r;

    public /* synthetic */ U2(PlayerStatus playerStatus, InterfaceC2782k interfaceC2782k, SideMenuType sideMenuType, C1930E c1930e, boolean z6, int i7) {
        this.l = 3;
        this.f6588m = playerStatus;
        this.f6592r = interfaceC2782k;
        this.f6590p = sideMenuType;
        this.f6591q = c1930e;
        this.f6589n = z6;
        this.o = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.o | 1);
                boolean z6 = this.f6589n;
                InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f6592r;
                com.bumptech.glide.c.m((g0.o) this.f6588m, (String) this.f6590p, (String) this.f6591q, z6, interfaceC2782k, (C0761q) obj, iZ);
                break;
            case 1:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.o | 1);
                boolean z10 = this.f6589n;
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.f6592r;
                l9.l.s((g0.o) this.f6588m, (PlayerStatus) this.f6590p, (Float) this.f6591q, z10, interfaceC2772a, (C0761q) obj, iZ2);
                break;
            case 2:
                ((Integer) obj2).getClass();
                int iZ3 = C0735d.Z(this.o | 1);
                String str = (String) this.f6590p;
                String str2 = (String) this.f6591q;
                String str3 = (String) this.f6592r;
                boolean z11 = this.f6589n;
                AbstractC1274a.r((g0.o) this.f6588m, str, str2, str3, z11, (C0761q) obj, iZ3);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ4 = C0735d.Z(this.o | 1);
                C1930E c1930e = (C1930E) this.f6591q;
                boolean z12 = this.f6589n;
                H7.H.f((PlayerStatus) this.f6588m, (InterfaceC2782k) this.f6592r, (SideMenuType) this.f6590p, c1930e, z12, (C0761q) obj, iZ4);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ U2(g0.o oVar, Object obj, Object obj2, boolean z6, m7.c cVar, int i7, int i10) {
        this.l = i10;
        this.f6588m = oVar;
        this.f6590p = obj;
        this.f6591q = obj2;
        this.f6589n = z6;
        this.f6592r = cVar;
        this.o = i7;
    }

    public /* synthetic */ U2(g0.o oVar, String str, String str2, String str3, boolean z6, int i7) {
        this.l = 2;
        this.f6588m = oVar;
        this.f6590p = str;
        this.f6591q = str2;
        this.f6592r = str3;
        this.f6589n = z6;
        this.o = i7;
    }
}
