package com.spotify.eventsender.eventsender;

import android.content.Context;
import com.google.common.base.Optional;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.schedulers.i;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;
import p.bk0;
import p.e02;
import p.ef;
import p.ei0;
import p.f02;
import p.f60;
import p.g90;
import p.gj0;
import p.gu0;
import p.h02;
import p.h52;
import p.hk0;
import p.jw0;
import p.k02;
import p.kk0;
import p.lb2;
import p.lx2;
import p.mb2;
import p.oj0;
import p.qn1;
import p.ri0;
import p.rw;
import p.s72;
import p.s81;
import p.ti0;
import p.u11;
import p.u4;
import p.ux2;
import p.uz0;
import p.v03;
import p.v4;
import p.vs1;
import p.vz0;
import p.wb0;
import p.wc1;
import p.wg1;
import p.ww1;
import p.x51;
import p.xi0;
import p.xt;
import p.xy1;
import p.y3;
import p.zb0;
import p.zj0;
import p.zl1;
import p.zz0;

/* JADX INFO: loaded from: classes.dex */
public final class b implements xi0 {
    public final vs1 a;
    public final jw0 b;
    public final a c;
    public final com.spotify.eventsender.disk.a d;
    public final e02 e;
    public final com.spotify.eventsender.eventsender.observability.a f;
    public final wc1 g;
    public volatile zl1 h;
    public volatile boolean i;

    /* JADX WARN: Type inference failed for: r0v13, types: [p.ij0] */
    /* JADX WARN: Type inference failed for: r0v16, types: [p.ij0] */
    /* JADX WARN: Type inference failed for: r0v18, types: [p.ij0] */
    /* JADX WARN: Type inference failed for: r0v21, types: [p.ij0] */
    /* JADX WARN: Type inference failed for: r0v28, types: [p.ij0] */
    /* JADX WARN: Type inference failed for: r0v3, types: [p.ij0] */
    /* JADX WARN: Type inference failed for: r0v31, types: [p.ij0] */
    /* JADX WARN: Type inference failed for: r11v4, types: [p.hj0] */
    public b(Context context, s81 s81Var, s72 s72Var, ri0 ri0Var, Optional optional, Optional optional2, Optional optional3) {
        v03.j(context, "applicationContext");
        v03.j(s81Var, "transportObservable");
        v03.j(s72Var, "sdkConfiguration");
        v03.j(ri0Var, "eventOwnerProvider");
        v03.j(optional, "connectivityObservable");
        v03.j(optional2, "foregroundStateObservable");
        v03.j(optional3, "postInitializationListener");
        wc1 wc1Var = s72Var.b;
        this.g = wc1Var;
        this.h = new zl1(null, null, false, false, false, false, false, false, false, false, false, EmptyList.c, 0, 30, EmptySet.c);
        xt xtVar = s72Var.e;
        Context applicationContext = context.getApplicationContext();
        Boolean bool = Boolean.TRUE;
        Object objG = optional.g(Observable.just(bool));
        v03.i(objG, "connectivityObservable.or(Observable.just(true))");
        final EventSenderImpl$connectivityProvider$1 eventSenderImpl$connectivityProvider$1 = new EventSenderImpl$connectivityProvider$1(this, b.class, "eventSenderConfig", "getEventSenderConfig()Lcom/spotify/eventsender/config/MutableEventSenderConfig;", 0);
        rw rwVar = new rw((Observable) objG, new xy1() { // from class: p.ij0
            @Override // p.xy1
            public final /* synthetic */ Object get() {
                return eventSenderImpl$connectivityProvider$1.invoke();
            }
        });
        wg1 wg1Var = new wg1(new ww1(14, applicationContext));
        zz0 zz0Var = new zz0(s81Var, s72Var.c);
        uz0 uz0Var = new uz0(zz0Var, new vz0());
        h52 h52VarN = f60.n(applicationContext, EventSenderDatabase.class, "event-sender.db");
        h52VarN.a(new lx2(5), new lx2(6), new lx2(7), new lx2(8), new lx2(4));
        h52VarN.l = false;
        h52VarN.m = true;
        EventSenderDatabase eventSenderDatabase = (EventSenderDatabase) h52VarN.b();
        g90 g90Var = new g90(applicationContext, 12);
        v4 v4Var = new v4(wg1Var, g90Var);
        lb2 lb2Var = new lb2(g90Var, v4Var, new kk0(eventSenderDatabase.q(), wg1Var));
        bk0 bk0VarQ = eventSenderDatabase.q();
        v03.i(bk0VarQ, "database.eventSequenceNumbersDao()");
        mb2 mb2Var = new mb2(bk0VarQ);
        zb0 zb0Var = new zb0();
        List list = s72Var.a;
        final EventSenderImpl$eventContextRegistry$1 eventSenderImpl$eventContextRegistry$1 = new EventSenderImpl$eventContextRegistry$1(this, b.class, "eventSenderConfig", "getEventSenderConfig()Lcom/spotify/eventsender/config/MutableEventSenderConfig;", 0);
        ei0 ei0Var = new ei0(applicationContext, wg1Var, wc1Var, g90Var, v4Var, list, new xy1() { // from class: p.ij0
            @Override // p.xy1
            public final /* synthetic */ Object get() {
                return eventSenderImpl$eventContextRegistry$1.invoke();
            }
        });
        xt xtVar2 = s72Var.e;
        Scheduler scheduler = io.reactivex.rxjava3.schedulers.e.b;
        v03.i(scheduler, "computation()");
        final EventSenderImpl$1 eventSenderImpl$1 = new EventSenderImpl$1(this, b.class, "eventSenderConfig", "getEventSenderConfig()Lcom/spotify/eventsender/config/MutableEventSenderConfig;", 0);
        this.f = new com.spotify.eventsender.eventsender.observability.a(zz0Var, ei0Var, zb0Var, xtVar2, scheduler, wc1Var, new xy1() { // from class: p.ij0
            @Override // p.xy1
            public final /* synthetic */ Object get() {
                return eventSenderImpl$1.invoke();
            }
        });
        wc1 wc1Var2 = s72Var.b;
        final EventSenderImpl$2 eventSenderImpl$2 = new EventSenderImpl$2(this, b.class, "eventSenderConfig", "getEventSenderConfig()Lcom/spotify/eventsender/config/MutableEventSenderConfig;", 0);
        this.a = new vs1(wc1Var2, rwVar, new xy1() { // from class: p.ij0
            @Override // p.xy1
            public final /* synthetic */ Object get() {
                return eventSenderImpl$2.invoke();
            }
        });
        ti0 ti0Var = new ti0(lb2Var, mb2Var, ei0Var, eventSenderDatabase.s(), g90Var, wc1Var, zb0Var);
        wg1 wg1Var2 = new wg1(wc1Var);
        e02 e02Var = new e02(wc1Var);
        this.e = e02Var;
        f02 f02VarT = eventSenderDatabase.t();
        v03.i(f02VarT, "database.rateLimiterReportsDao()");
        h02 h02Var = new h02(new k02(f02VarT), wc1Var);
        final EventSenderImpl$statesCounters$1 eventSenderImpl$statesCounters$1 = new EventSenderImpl$statesCounters$1(this, b.class, "eventSenderConfig", "getEventSenderConfig()Lcom/spotify/eventsender/config/MutableEventSenderConfig;", 0);
        wb0 wb0Var = new wb0(wc1Var, new xy1() { // from class: p.ij0
            @Override // p.xy1
            public final /* synthetic */ Object get() {
                return eventSenderImpl$statesCounters$1.invoke();
            }
        });
        oj0 oj0Var = new oj0();
        y3 y3Var = new y3(applicationContext);
        Set setZ = ux2.z(wb0Var, oj0Var);
        Object objG2 = optional2.g(Observable.just(bool));
        v03.i(objG2, "foregroundStateObservabl…or(Observable.just(true))");
        this.d = new com.spotify.eventsender.disk.a(setZ, y3Var, wc1Var, (Observable) objG2);
        v03.j(xtVar, "clock");
        new HashMap();
        new AtomicLong();
        hk0 hk0VarR = eventSenderDatabase.r();
        v03.i(hk0VarR, "database.eventStatsDao()");
        e eVar = new e();
        gj0 gj0Var = new gj0(uz0Var, 3);
        zj0 zj0Var = new zj0();
        final EventSenderImpl$essDataSource$2 eventSenderImpl$essDataSource$2 = new EventSenderImpl$essDataSource$2(this, b.class, "eventSenderConfig", "getEventSenderConfig()Lcom/spotify/eventsender/config/MutableEventSenderConfig;", 0);
        d dVar = new d(hk0VarR, h02Var, ri0Var, wb0Var, eVar, lb2Var, mb2Var, ei0Var, xtVar, gj0Var, ti0Var, zj0Var, wc1Var, zb0Var, new xy1() { // from class: p.ij0
            @Override // p.xy1
            public final /* synthetic */ Object get() {
                return eventSenderImpl$essDataSource$2.invoke();
            }
        }, s72Var.d);
        x51 x51Var = new x51(oj0Var, new gj0(uz0Var, 4), mb2Var, xtVar, ei0Var, zb0Var, wc1Var);
        wg1 wg1Var3 = new wg1();
        i iVar = new i(Executors.newSingleThreadExecutor(), false, false);
        ?? r11 = new Runnable() { // from class: p.hj0
            @Override // java.lang.Runnable
            public final void run() {
                com.spotify.eventsender.eventsender.b bVar = this.c;
                if (bVar.h.h) {
                    bVar.c.run();
                }
            }
        };
        final EventSenderImpl$5 eventSenderImpl$5 = new EventSenderImpl$5(this, b.class, "eventSenderConfig", "getEventSenderConfig()Lcom/spotify/eventsender/config/MutableEventSenderConfig;", 0);
        this.b = new jw0(ti0Var, ri0Var, wb0Var, e02Var, h02Var, wc1Var, wg1Var3, zb0Var, iVar, xtVar, r11, new xy1() { // from class: p.ij0
            @Override // p.xy1
            public final /* synthetic */ Object get() {
                return eventSenderImpl$5.invoke();
            }
        }, oj0Var);
        List listC = v03.C(dVar, x51Var, new qn1(eventSenderDatabase.s(), wg1Var2, new gj0(uz0Var, 1), wc1Var), new ef(eventSenderDatabase.s(), wg1Var2, new gj0(uz0Var, 2), wc1Var, ri0Var));
        kk0 kk0VarS = eventSenderDatabase.s();
        bk0 bk0VarQ2 = eventSenderDatabase.q();
        gu0 gu0Var = new gu0();
        gu0Var.f = new AtomicBoolean(false);
        gu0Var.a = kk0VarS;
        gu0Var.b = bk0VarQ2;
        gu0Var.c = g90Var;
        gu0Var.d = wc1Var;
        gu0Var.e = oj0Var;
        this.c = new a(listC, gu0Var, wc1Var, wb0Var);
        if (optional3.d()) {
            u4.z(optional3.c());
            throw null;
        }
    }

    public final synchronized void a() {
        try {
            if (this.i) {
                ((u11) this.g).getClass();
            } else {
                this.i = true;
                this.a.a(this.c);
                this.d.c();
                this.f.b();
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
