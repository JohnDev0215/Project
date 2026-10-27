package I5;

import N5.Z;
import com.stingray.companion.CompanionManager;
import com.stingray.karaoke.car.MainActivity;
import com.stingray.karaoke.car.ui.splash.SplashActivity;
import j.InterfaceC1532a;
import k6.C1691C;
import k6.C1696d;
import k6.C1699g;
import k6.C1704l;
import k6.C1705m;
import n6.C1972e;

/* JADX INFO: loaded from: classes.dex */
public final class m implements InterfaceC1532a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ h.n f3012b;

    public /* synthetic */ m(h.n nVar, int i7) {
        this.f3011a = i7;
        this.f3012b = nVar;
    }

    @Override // j.InterfaceC1532a
    public final void a(h.n nVar) {
        switch (this.f3011a) {
            case 0:
                MainActivity mainActivity = (MainActivity) this.f3012b;
                if (!mainActivity.f12978I) {
                    mainActivity.f12978I = true;
                    k kVar = ((g) ((B) mainActivity.c())).f2952a;
                    mainActivity.f12991V = (Z) kVar.f2961E.get();
                    mainActivity.f12992W = (C1704l) kVar.f2985q.get();
                    mainActivity.f12993X = (C6.b) kVar.f2958A.get();
                    mainActivity.f12994Y = (CompanionManager) kVar.f2980j.get();
                    mainActivity.f12995Z = (C1696d) kVar.f2979i.get();
                    mainActivity.f12996a0 = (C1699g) kVar.f2975e.get();
                    mainActivity.f12997b0 = (C1705m) kVar.f2966J.get();
                    mainActivity.f12998c0 = (W5.e) kVar.B.get();
                    mainActivity.f12999d0 = (k6.r) kVar.f2982m.get();
                    mainActivity.f13000e0 = (L5.i) kVar.f2968L.get();
                    mainActivity.f13001f0 = (C1972e) kVar.f2992x.get();
                    mainActivity.f13002g0 = (K5.t) kVar.d.get();
                    mainActivity.f13003h0 = (C1691C) kVar.f2993y.get();
                }
                break;
            default:
                SplashActivity splashActivity = (SplashActivity) this.f3012b;
                if (!splashActivity.f13034I) {
                    splashActivity.f13034I = true;
                    ((Z5.a) splashActivity.c()).getClass();
                }
                break;
        }
    }
}
