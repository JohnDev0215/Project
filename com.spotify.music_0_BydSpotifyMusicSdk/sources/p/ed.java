package p;

import android.content.Context;
import android.content.res.Resources;
import com.google.common.base.Optional;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.MobileDeviceInfo;
import com.spotify.connectivity.connectiontype.ConnectionApis;
import io.reactivex.rxjava3.core.Flowable;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final class ed implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;
    public final xy1 c;
    public final Object d;

    public /* synthetic */ ed(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3, int i) {
        this.a = i;
        this.b = xy1Var;
        this.c = xy1Var2;
        this.d = xy1Var3;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.c;
        xy1 xy1Var2 = this.b;
        Object obj = this.d;
        switch (i) {
            case 0:
                m mVar = (m) xy1Var2.get();
                fd fdVar = (fd) xy1Var.get();
                dd ddVar = (dd) ((xy1) obj).get();
                v03.j(mVar, "properties");
                v03.j(fdVar, "audioFocusPlayerCallbacks");
                v03.j(ddVar, "audioFocusControllerHolder");
                Logger.e("Initializing audio manager with greedy focus = %s.", Boolean.FALSE);
                com.spotify.audiofocus.coreinternal.a aVar = ddVar.a;
                aVar.g = false;
                return new gd(aVar, fdVar);
            case 1:
                return new ck((hk) xy1Var2.get(), (st1) xy1Var.get(), (rd1) ((xy1) obj).get());
            case 2:
                return new ei1((jf0) xy1Var2.get(), (Resources) xy1Var.get(), (up2) ((xy1) obj).get());
            case 3:
                return new v((Resources) xy1Var2.get(), (k0) xy1Var.get(), (up2) ((xy1) obj).get());
            case 4:
                return new s0((st1) xy1Var2.get(), (ki1) xy1Var.get(), (Resources) ((xy1) obj).get());
            case 5:
                n nVar = (n) xy1Var2.get();
                xa xaVar = (xa) xy1Var.get();
                MobileDeviceInfo mobileDeviceInfo = (MobileDeviceInfo) ((xy1) obj).get();
                lw1 lw1Var = new lw1(nVar.a);
                xaVar.getClass();
                lw1Var.a(new ft("4.4.1.0"));
                lw1Var.a(new gt(mobileDeviceInfo.getBrand()));
                lw1Var.a(new ht(mobileDeviceInfo.getManufacturer()));
                lw1Var.a(new jt(mobileDeviceInfo.getModel()));
                return lw1Var;
            case 6:
                return new rx((ConnectionApis) xy1Var2.get(), (jf0) xy1Var.get(), (f61) ((xy1) obj).get());
            case 7:
                return new ao((Resources) xy1Var2.get(), (vv2) xy1Var.get(), (ki1) ((xy1) obj).get());
            case 8:
                return new x91((vv2) xy1Var2.get(), (ki1) xy1Var.get(), (vj) ((xy1) obj).get());
            case 9:
                return new z20((FirebaseCrashlytics) xy1Var2.get(), (gs1) xy1Var.get(), (he2) ((xy1) obj).get());
            case 10:
                xi0 xi0Var = (xi0) xy1Var2.get();
                return new bj0(xi0Var, (m) ((xy1) obj).get());
            case 11:
                return new up1((x00) xy1Var2.get(), (jq1) xy1Var.get(), (vp1) ((xy1) obj).get());
            case 12:
                m mVar2 = (m) xy1Var2.get();
                com.spotify.automotive.playback.c cVar = (com.spotify.automotive.playback.c) xy1Var.get();
                ia1 ia1Var = (ia1) ((xy1) obj).get();
                v03.j(mVar2, "automotiveProperties");
                v03.j(cVar, "fullCorePlaybackControllerImpl");
                v03.j(ia1Var, "limitedPlayerPlaybackControllerImpl");
                return ((com.spotify.remoteconfig.a) mVar2.a.get()).e() ? cVar : ia1Var;
            case 13:
                return new y22((a42) xy1Var2.get(), (f32) xy1Var.get(), (aj) ((xy1) obj).get());
            case 14:
                return new ot1((st1) xy1Var2.get(), (as2) xy1Var.get(), (vv2) ((xy1) obj).get());
            case 15:
                return new vv2((zv2) xy1Var2.get(), (OkHttpClient) xy1Var.get(), (ps1) ((xy1) obj).get());
            case 16:
                Context context = (Context) xy1Var2.get();
                bc2 bc2Var = (bc2) xy1Var.get();
                Optional optional = (Optional) ((xy1) obj).get();
                v03.j(context, "context");
                v03.j(bc2Var, "settings");
                v03.j(optional, "remoteConfigNotifier");
                Context applicationContext = context.getApplicationContext();
                v03.i(applicationContext, "context.applicationContext");
                u4.z(optional.d() ? optional.c() : null);
                return new eg2(applicationContext, bc2Var);
            case 17:
                bt1 bt1Var = (bt1) xy1Var2.get();
                n0 n0Var = (n0) xy1Var.get();
                Flowable flowable = (Flowable) ((xy1) obj).get();
                v03.j(bt1Var, "pageLocationProvider");
                v03.j(n0Var, "playbackIdProvider");
                v03.j(flowable, "playerStateFlowable");
                return new gp2(bt1Var, n0Var, flowable);
            default:
                st1 st1Var = (st1) xy1Var2.get();
                m mVar3 = (m) xy1Var.get();
                ((z90) obj).getClass();
                v03.j(st1Var, "playbackController");
                v03.j(mVar3, "automotiveProperties");
                return new w40(st1Var, mVar3);
        }
    }

    public ed(z90 z90Var, wy1 wy1Var, wy1 wy1Var2) {
        this.a = 18;
        this.d = z90Var;
        this.b = wy1Var;
        this.c = wy1Var2;
    }
}
