package Q5;

import T.C0735d;
import T.C0761q;
import a.AbstractC0904a;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import java.util.List;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.u0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0625u0 implements InterfaceC2785n {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f7077m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ List f7078n;
    public final /* synthetic */ InterfaceC2782k o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f7079p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f7080q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ m7.c f7081r;

    public /* synthetic */ C0625u0(g0.o oVar, List list, D.L l, InterfaceC2785n interfaceC2785n, InterfaceC2782k interfaceC2782k, int i7) {
        this.f7077m = oVar;
        this.f7078n = list;
        this.f7080q = l;
        this.f7081r = interfaceC2785n;
        this.o = interfaceC2782k;
        this.f7079p = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f7079p | 1);
                InterfaceC2785n interfaceC2785n = (InterfaceC2785n) this.f7081r;
                InterfaceC2782k interfaceC2782k = this.o;
                com.bumptech.glide.d.g(this.f7077m, this.f7078n, (D.L) this.f7080q, interfaceC2785n, interfaceC2782k, (C0761q) obj, iZ);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f7079p | 1);
                StingrayKaraokeTabScreen stingrayKaraokeTabScreen = (StingrayKaraokeTabScreen) this.f7080q;
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.f7081r;
                AbstractC0904a.h(this.f7077m, this.f7078n, this.o, stingrayKaraokeTabScreen, interfaceC2772a, (C0761q) obj, iZ2);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ C0625u0(g0.o oVar, List list, InterfaceC2782k interfaceC2782k, StingrayKaraokeTabScreen stingrayKaraokeTabScreen, InterfaceC2772a interfaceC2772a, int i7) {
        this.f7077m = oVar;
        this.f7078n = list;
        this.o = interfaceC2782k;
        this.f7080q = stingrayKaraokeTabScreen;
        this.f7081r = interfaceC2772a;
        this.f7079p = i7;
    }
}
