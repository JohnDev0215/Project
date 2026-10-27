package K5;

import A7.C0055c;
import K7.K;
import N1.C0420a;
import N1.C0424e;
import N1.InterfaceC0428i;
import N1.Q;
import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import S8.l0;
import android.content.Context;
import h8.AbstractC1387d;
import java.util.List;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3657a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final R1.a f3658b;
    public static final /* synthetic */ H7.w[] d = {A7.A.f443a.i(new A7.t(C0055c.l, t.class, "dataStore", "getDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", 0))};
    public static final io.sentry.hints.i c = new io.sentry.hints.i(3);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final S1.d f3648e = C7.a.a0("savedLoginInfo");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final S1.d f3649f = new S1.d("savedLoginExpiration");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final S1.d f3650g = C7.a.a0("song_queue_data");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final S1.d f3651h = new S1.d("song_queue_current_song_index_new");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final S1.d f3652i = C7.a.y("parentalControlsEnabled");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final S1.d f3653j = C7.a.a0("dev_settings_device_id");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final S1.d f3654k = C7.a.y("dev_settings_scoring_debug");
    public static final S1.d l = C7.a.a0("gen_device_id");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final S1.d f3655m = C7.a.y("legal_dialog");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final S1.d f3656n = C7.a.y("perfect_pitch_available");

    public t(Context context) {
        A7.m.f(context, "context");
        this.f3657a = context;
        A3.c cVar = new A3.c(9);
        Z8.e eVar = S8.E.f8084a;
        Z8.d dVar = Z8.d.f11010n;
        l0 l0VarC = AbstractC0727y.c();
        dVar.getClass();
        this.f3658b = new R1.a(cVar, AbstractC0727y.a(h6.q.x(dVar, l0VarC)));
    }

    public final InterfaceC0428i a(Context context) {
        C0420a c0420a;
        R1.a aVar = this.f3658b;
        H7.w wVar = d[0];
        aVar.getClass();
        A7.m.f(context, "thisRef");
        A7.m.f(wVar, "property");
        C0420a c0420a2 = aVar.d;
        if (c0420a2 != null) {
            return c0420a2;
        }
        synchronized (aVar.c) {
            try {
                if (aVar.d == null) {
                    Context applicationContext = context.getApplicationContext();
                    InterfaceC2782k interfaceC2782k = aVar.f7352a;
                    A7.m.e(applicationContext, "applicationContext");
                    List list = (List) interfaceC2782k.b(applicationContext);
                    InterfaceC0725w interfaceC0725w = aVar.f7353b;
                    B8.A a10 = new B8.A(applicationContext, 23, aVar);
                    A7.m.f(list, "migrations");
                    aVar.d = new C0420a(new C0420a(new Q(new P1.f(r9.o.f21939a, new K(a10, 16)), AbstractC1387d.j(new C0424e(list, null)), new C8.d(7), interfaceC0725w)));
                }
                c0420a = aVar.d;
                A7.m.c(c0420a);
            } catch (Throwable th) {
                throw th;
            }
        }
        return c0420a;
    }
}
