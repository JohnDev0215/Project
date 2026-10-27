package com.stingray.karaoke.car;

import A6.a;
import A7.m;
import B2.C0074n;
import B2.O;
import B5.C0082f;
import B5.C0083g;
import I5.C;
import I5.k;
import J5.c;
import J5.h;
import J5.i;
import O6.f;
import Q6.b;
import S8.AbstractC0727y;
import android.app.Application;
import android.content.Context;
import android.os.SystemClock;
import c9.B;
import c9.C1045f;
import com.stingray.kca.model.SupportedFeatures;
import io.sentry.U0;
import io.sentry.android.core.performance.e;
import java.io.File;
import java.util.ArrayList;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/stingray/karaoke/car/MainApplication;", "Landroid/app/Application;", "<init>", "()V", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MainApplication extends Application implements b {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ int f13005p = 0;
    public boolean l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f f13006m = new f(new O(this, 23));

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public c f13007n;
    public h o;

    public final void a() {
        e eVar = e.f16276y;
        long jUptimeMillis = SystemClock.uptimeMillis();
        e eVarB = e.b();
        io.sentry.android.core.performance.f fVar = eVarB.f16279p;
        if (fVar.f16288n == 0) {
            fVar.d(jUptimeMillis);
            eVarB.f(this);
        }
        if (!this.l) {
            this.l = true;
            k kVar = (k) ((C) this.f13006m.c());
            this.f13007n = (c) kVar.c.get();
            this.o = (h) kVar.f2978h.get();
        }
        super.onCreate();
        e.c(this);
    }

    @Override // Q6.b
    public final Object c() {
        return this.f13006m.c();
    }

    @Override // android.app.Application
    public final void onCreate() {
        e eVar = e.f16276y;
        long jUptimeMillis = SystemClock.uptimeMillis();
        e eVarB = e.b();
        io.sentry.android.core.performance.f fVar = eVarB.f16279p;
        if (fVar.f16288n == 0) {
            fVar.d(jUptimeMillis);
            eVarB.f(this);
        }
        a();
        Context applicationContext = getApplicationContext();
        m.e(applicationContext, "getApplicationContext(...)");
        C0082f c0082f = new C0082f(applicationContext, "rFuQox9HMsCZgrJgQCYNgiK6zH7CH6ED");
        c0082f.f683g = new A6.f();
        c0082f.f686j = true;
        a aVar = new a();
        if (c0082f.f685i == null) {
            c0082f.f685i = new ArrayList();
        }
        if (c0082f.f685i.contains(aVar)) {
            throw new IllegalStateException("Source Middleware is already registered.");
        }
        c0082f.f685i.add(aVar);
        C0083g c0083gA = c0082f.a();
        synchronized (C0083g.class) {
            if (C0083g.B != null) {
                throw new IllegalStateException("Singleton instance already exists.");
            }
            C0083g.B = c0083gA;
        }
        U0.b().l(new C0074n(7));
        c cVar = this.f13007n;
        if (cVar == null) {
            m.j("KCAService");
            throw null;
        }
        h hVar = this.o;
        if (hVar == null) {
            m.j("loginManager");
            throw null;
        }
        B b10 = A9.a.d;
        Context context = cVar.f3090a;
        b10.f12452k = new C1045f(new File(context.getCacheDir(), "http_cache"), 90000000L);
        b10.c.add(new i(context));
        b10.d.add(new J5.a());
        b10.c.add(new i(hVar));
        b10.c.add(new C4.a());
        cVar.f3091b = new c9.C(b10);
        cVar.a();
        c.f3089i.add(SupportedFeatures.Profile);
        h hVar2 = this.o;
        if (hVar2 == null) {
            m.j("loginManager");
            throw null;
        }
        AbstractC0727y.w(hVar2.f3105h, null, null, new J5.f(hVar2, null), 3);
        e.c(this);
    }
}
