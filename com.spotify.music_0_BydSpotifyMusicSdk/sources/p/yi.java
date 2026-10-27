package p;

import android.app.Application;
import com.spotify.automotive.socialconnect.SocialConnectController;
import com.spotify.connectivity.MobileDeviceInfo;
import com.spotify.eventsender.api.EventSenderCoreBridge;
import com.spotify.jvm.jni.NativeClassLoader;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final class yi implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;
    public final xy1 c;
    public final xy1 d;
    public final xy1 e;
    public final xy1 f;
    public final xy1 g;
    public final xy1 h;
    public final xy1 i;
    public final xy1 j;
    public final xy1 k;

    public /* synthetic */ yi(wy1 wy1Var, wy1 wy1Var2, wy1 wy1Var3, wy1 wy1Var4, wy1 wy1Var5, wy1 wy1Var6, wy1 wy1Var7, wy1 wy1Var8, wy1 wy1Var9, wy1 wy1Var10, int i) {
        this.a = i;
        this.b = wy1Var;
        this.c = wy1Var2;
        this.d = wy1Var3;
        this.e = wy1Var4;
        this.f = wy1Var5;
        this.g = wy1Var6;
        this.h = wy1Var7;
        this.i = wy1Var8;
        this.j = wy1Var9;
        this.k = wy1Var10;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.k;
        xy1 xy1Var2 = this.j;
        xy1 xy1Var3 = this.i;
        xy1 xy1Var4 = this.h;
        xy1 xy1Var5 = this.g;
        xy1 xy1Var6 = this.f;
        xy1 xy1Var7 = this.e;
        xy1 xy1Var8 = this.d;
        xy1 xy1Var9 = this.c;
        xy1 xy1Var10 = this.b;
        switch (i) {
            case 0:
                return new xi((vx1) xy1Var10.get(), (as2) xy1Var9.get(), (pt) xy1Var8.get(), (st1) xy1Var7.get(), (bw2) xy1Var6.get(), (t) xy1Var5.get(), (xw1) xy1Var4.get(), (y22) xy1Var3.get(), (SocialConnectController) xy1Var2.get(), (z20) xy1Var.get());
            default:
                Application application = (Application) xy1Var10.get();
                OkHttpClient okHttpClient = (OkHttpClient) xy1Var9.get();
                ut utVar = (ut) xy1Var8.get();
                MobileDeviceInfo mobileDeviceInfo = (MobileDeviceInfo) xy1Var7.get();
                m10 m10Var = (m10) xy1Var6.get();
                zy2 zy2Var = (zy2) xy1Var5.get();
                EventSenderCoreBridge eventSenderCoreBridge = (EventSenderCoreBridge) xy1Var4.get();
                a42 a42Var = (a42) xy1Var3.get();
                Observable observable = (Observable) xy1Var2.get();
                k10 k10Var = (k10) xy1Var.get();
                v03.j(application, "context");
                v03.j(okHttpClient, "okHttpClient");
                v03.j(utVar, "clientInfo");
                v03.j(mobileDeviceInfo, "mobileDeviceInfo");
                v03.j(m10Var, "corePathResolver");
                v03.j(zy2Var, "wrappedRxRouter");
                v03.j(eventSenderCoreBridge, "eventSenderCoreBridge");
                v03.j(a42Var, "esperantoResolver");
                v03.j(observable, "connectionTypeObservable");
                v03.j(k10Var, "coreNativePlayerOptions");
                if (!f60.g) {
                    new r02().b(application, "full".concat("-jni-spotify"));
                    f60.g = true;
                }
                NativeClassLoader.Companion.load();
                return new d10(application, utVar, m10Var, zy2Var, eventSenderCoreBridge, mobileDeviceInfo, a42Var, okHttpClient, observable, k10Var);
        }
    }
}
