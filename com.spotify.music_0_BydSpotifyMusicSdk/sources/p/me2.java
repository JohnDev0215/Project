package p;

import android.accounts.AccountManager;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.AudioManager;
import com.google.common.base.Optional;
import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.MobileDeviceInfo;
import com.spotify.connectivity.connectiontypeflags.ConnectionTypePropertiesWriter;
import com.spotify.connectivity.httpwebgate.WebgateHelper;
import com.spotify.connectivity.httpwebgate.WebgateTokenProvider;
import com.spotify.core.core.DeviceType;
import com.spotify.cosmos.converters.ByteArrayConverters;
import com.spotify.cosmos.converters.MoshiLiteConverters;
import com.spotify.cosmos.converters.ProtoJavaliteConverters;
import com.spotify.cosmos.converters.StringConverters;
import com.spotify.cosmos.cosmonautatoms.CosmonautImpl;
import com.spotify.cosmos.rxrouter.RxRouter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import kotlin.InitializedLazyImpl;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;

/* JADX INFO: loaded from: classes.dex */
public final class me2 implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;
    public final xy1 c;

    public /* synthetic */ me2(xy1 xy1Var, xy1 xy1Var2, int i) {
        this.a = i;
        this.b = xy1Var;
        this.c = xy1Var2;
    }

    @Override // p.xy1
    public final Object get() {
        com.spotify.appstorage.sharedpreferencesimpl.a aVar;
        switch (this.a) {
            case 0:
                Application application = (Application) this.b.get();
                ed2 ed2Var = (ed2) this.c.get();
                v03.j(application, "application");
                v03.j(ed2Var, "spSharedPreferencesFactory");
                HashMap map = je2.a;
                synchronized (map) {
                    Object sharedPreferences = map.get("spotify_preferences");
                    if (sharedPreferences == null) {
                        sharedPreferences = application.getSharedPreferences("spotify_preferences", 0);
                        v03.i(sharedPreferences, "context.getSharedPrefere…me, Context.MODE_PRIVATE)");
                        map.put("spotify_preferences", sharedPreferences);
                    }
                    aVar = new com.spotify.appstorage.sharedpreferencesimpl.a(new ie2(0, (SharedPreferences) sharedPreferences), false, je2.b);
                }
                return aVar;
            case 1:
                Context context = (Context) this.b.get();
                id idVar = (id) this.c.get();
                v03.j(context, "context");
                v03.j(idVar, "eventSender");
                Object systemService = context.getSystemService("audio");
                v03.g(systemService, "null cannot be cast to non-null type android.media.AudioManager");
                return new md((AudioManager) systemService, idVar);
            case 2:
                return new hf((AccountManager) this.b.get(), (k) this.c.get());
            case 3:
                return new ff2((Application) this.b.get(), (gf) this.c.get());
            case 4:
                return new sm((OkHttpClient) this.b.get(), (in) this.c.get());
            case 5:
                xa xaVar = (xa) this.b.get();
                lw1 lw1Var = (lw1) this.c.get();
                xaVar.getClass();
                return new nt(lw1Var);
            case 6:
                return new cf((rd1) this.b.get(), (xi) this.c.get());
            case 7:
                return new xw1((st1) this.b.get(), (m60) this.c.get());
            case 8:
                return new j((vv2) this.b.get(), (ef2) this.c.get());
            case 9:
                return new bk((Service) this.b.get(), (ad1) this.c.get());
            case 10:
                return new hk((Service) this.b.get(), (bi1) this.c.get());
            case 11:
                return new tr2((Context) this.b.get(), (qj) this.c.get());
            case 12:
                return new l((Resources) this.b.get(), (k0) this.c.get());
            case 13:
                return new bc2((xa) this.b.get(), (lw1) this.c.get());
            case 14:
                RxRouter rxRouter = (RxRouter) this.b.get();
                com.squareup.moshi.o oVar = (com.squareup.moshi.o) this.c.get();
                v03.j(rxRouter, "rxRouter");
                v03.j(oVar, "moshi");
                com.squareup.moshi.o oVarD = oVar.h().a(new jl1()).a(new w20()).d();
                v03.i(oVarD, "moshi\n                  …                 .build()");
                return new CosmonautImpl(rxRouter, v03.C(new ByteArrayConverters(), new StringConverters(), new ProtoJavaliteConverters(), new MoshiLiteConverters(oVarD)));
            case 15:
                return new bw2((WebgateTokenProvider) this.b.get(), (WebgateHelper) this.c.get());
            case 16:
                OkHttpClient okHttpClient = (OkHttpClient) this.b.get();
                fl1 fl1Var = (fl1) this.c.get();
                d52 d52Var = new d52();
                d52Var.c("https://spclient.wg.spotify.com/");
                Objects.requireNonNull(okHttpClient, "client == null");
                d52Var.b = okHttpClient;
                d52Var.b(new z62());
                d52Var.b(fl1Var);
                d52Var.b(new b11(0, new com.google.gson.a()));
                d52Var.a(new a62(true));
                return d52Var.e();
            case 17:
                Application application2 = (Application) this.b.get();
                Optional optional = (Optional) this.c.get();
                File file = new File(application2.getApplicationContext().getCacheDir(), "http_cache");
                if (!file.exists() && !file.mkdirs()) {
                    Logger.g("Failed to create cache folder.", new Object[0]);
                }
                Cache cache = new Cache(file);
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.k = cache;
                if (optional.d()) {
                    Logger.g("Forcing OkHttp to use HTTP protocol: %s", optional.c());
                    builder.a(Collections.singletonList((Protocol) optional.c()));
                }
                return new OkHttpClient(builder);
            case 18:
                return new mh1((tp1) this.b.get(), (m) this.c.get());
            case 19:
                return new aa1((vv2) this.b.get(), (ki1) this.c.get());
            case 20:
                return new t00((Scheduler) this.b.get(), (gw2) this.c.get());
            case 21:
                Application application3 = (Application) this.b.get();
                OkHttpClient okHttpClient2 = (OkHttpClient) this.c.get();
                v03.j(application3, "context");
                v03.j(okHttpClient2, "okHttpClient");
                coil.a aVar2 = new coil.a(application3);
                aVar2.c = new InitializedLazyImpl(okHttpClient2);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                ArrayList arrayList5 = new ArrayList();
                arrayList.add(new u31());
                aVar2.e = new ew(l13.q(arrayList), l13.q(arrayList2), l13.q(arrayList3), l13.q(arrayList4), l13.q(arrayList5));
                aVar2.d = new af2(1, new m31());
                return new m60(application3, aVar2.a());
            case 22:
                return new aj((ConnectionTypePropertiesWriter) this.b.get(), (com.spotify.automotive.remoteconfig.a) this.c.get());
            case 23:
                return new mk((st1) this.b.get(), (jf0) this.c.get());
            case 24:
                te2 te2Var = (te2) this.b.get();
                mk mkVar = (mk) this.c.get();
                Observable observableJust = Observable.just(Boolean.FALSE);
                te2Var.getClass();
                v03.j(mkVar, "voiceAssistantCallbacks");
                v03.j(observableJust, "includeAlternatives");
                return new xe2(te2Var.a, mkVar, te2Var.b, new od2(), new od2(), observableJust);
            case 25:
                MobileDeviceInfo mobileDeviceInfo = (MobileDeviceInfo) this.b.get();
                return new nk((pv2) this.c.get(), DeviceType.AUTOMOBILE, mobileDeviceInfo.getBrand(), mobileDeviceInfo.getModel());
            case 26:
                return new vj((u6) this.b.get(), (Context) this.c.get());
            case 27:
                return new ki1((Context) this.c.get(), (String) this.b.get());
            case 28:
                return new nh0((vz) ua0.a(this.b).get(), (yc1) this.c.get());
            default:
                return new jh0((vz) ua0.a(this.b).get(), (yc1) this.c.get());
        }
    }
}
