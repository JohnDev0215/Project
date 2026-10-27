package com.spotify.eventsender.eventsender.observability;

import android.os.SystemClock;
import com.google.protobuf.ByteString;
import com.google.protobuf.i;
import com.spotify.eventsender.Fragment;
import com.spotify.eventsender.gabo.EventEnvelope;
import com.spotify.eventsender.gabo.PublishEventsRequest;
import com.spotify.eventsender.gabo.PublishEventsResponse;
import com.spotify.eventsender.gabo.b;
import com.spotify.messages.EventSenderCanaryNonAuth;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import p.du;
import p.ei0;
import p.ij0;
import p.lu;
import p.ou;
import p.po;
import p.rj0;
import p.ry0;
import p.s81;
import p.tz0;
import p.u11;
import p.u4;
import p.u42;
import p.ux1;
import p.ux2;
import p.v03;
import p.v51;
import p.wc1;
import p.xt;
import p.xy1;
import p.yi0;
import p.zb0;
import p.zi0;
import p.zl1;
import p.zy1;
import p.zz0;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final s81 a;
    public final ei0 b;
    public final zb0 c;
    public final xt d;
    public final Scheduler e;
    public final wc1 f;
    public final xy1 g;
    public Disposable h;
    public long i;
    public final ByteString j;
    public long k;

    public a(zz0 zz0Var, ei0 ei0Var, zb0 zb0Var, xt xtVar, Scheduler scheduler, wc1 wc1Var, ij0 ij0Var) {
        v03.j(xtVar, "clock");
        v03.j(wc1Var, "logger");
        this.a = zz0Var;
        this.b = ei0Var;
        this.c = zb0Var;
        this.d = xtVar;
        this.e = scheduler;
        this.f = wc1Var;
        this.g = ij0Var;
        this.h = EmptyDisposable.c;
        this.j = ByteString.d(UUID.randomUUID().toString());
        this.k = 1L;
    }

    public static final void a(a aVar, tz0 tz0Var) throws EventSenderMonitorBackendError {
        aVar.getClass();
        yi0 yi0VarF = EventSenderCanaryNonAuth.f();
        yi0VarF.d();
        EventSenderCanaryNonAuth eventSenderCanaryNonAuth = (EventSenderCanaryNonAuth) yi0VarF.build();
        v03.i(eventSenderCanaryNonAuth, "event");
        byte[] byteArray = eventSenderCanaryNonAuth.toByteArray();
        v03.i(byteArray, "event.toByteArray()");
        ((u11) aVar.d).getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        ei0 ei0Var = aVar.b;
        zb0 zb0Var = aVar.c;
        zb0Var.getClass();
        v51<Fragment> v51VarH = ux2.f(byteArray, jCurrentTimeMillis, ei0Var, zb0Var, null).h();
        v03.i(v51VarH, "fragmentsContainer.fragmentList");
        ArrayList arrayList = new ArrayList(lu.W(v51VarH));
        for (Fragment fragment : v51VarH) {
            b bVarG = EventEnvelope.EventFragment.g();
            bVarG.e(fragment.getName());
            bVarG.d(fragment.getData());
            arrayList.add((EventEnvelope.EventFragment) bVarG.build());
        }
        com.spotify.eventsender.gabo.a aVarI = EventEnvelope.i();
        aVarI.e("EventSenderCanaryNonAuth");
        aVarI.d(arrayList);
        aVarI.f(aVar.j);
        long j = aVar.k;
        aVar.k = 1 + j;
        aVarI.g(j);
        i iVarBuild = aVarI.build();
        v03.i(iVarBuild, "newBuilder()\n           …r++)\n            .build()");
        zy1 zy1VarG = PublishEventsRequest.g();
        zy1VarG.e((EventEnvelope) iVarBuild);
        PublishEventsRequest publishEventsRequest = (PublishEventsRequest) zy1VarG.build();
        v03.i(publishEventsRequest, "request");
        u42 u42VarB = tz0Var.a(publishEventsRequest).b();
        int i = u42VarB.a.l;
        if (i != 200) {
            throw new EventSenderMonitorBackendError(u4.e("EventSender heartbeat event failed with HTTP code: ", i));
        }
        PublishEventsResponse publishEventsResponse = (PublishEventsResponse) u42VarB.b;
        if (publishEventsResponse != null && publishEventsResponse.e().size() > 0) {
            v51 v51VarE = publishEventsResponse.e();
            v03.i(v51VarE, "responseBody.errorList");
            throw new EventSenderMonitorBackendError("EventSender heartbeat event failed with reasons: ".concat(ou.h0(v51VarE, null, null, null, new ry0() { // from class: com.spotify.eventsender.eventsender.observability.EventSenderMonitorImpl$sendHeartbeatNow$errorReasons$1
                @Override // p.ry0
                public final Object invoke(Object obj) {
                    return String.valueOf(((PublishEventsResponse.EventError) obj).f());
                }
            }, 31)));
        }
        aVar.i = SystemClock.elapsedRealtime();
    }

    public final void b() {
        xy1 xy1Var = this.g;
        Object obj = xy1Var.get();
        v03.i(obj, "eventSenderConfigProvider.get()");
        if (((zl1) ((zi0) obj)).m > 0 && this.h.isDisposed()) {
            Object obj2 = xy1Var.get();
            v03.i(obj2, "eventSenderConfigProvider.get()");
            v03.i(xy1Var.get(), "eventSenderConfigProvider.get()");
            Disposable disposableSubscribe = Observable.combineLatest(Observable.interval(0L, ((zl1) ((zi0) r0)).n, TimeUnit.SECONDS), this.a.a(), po.k).observeOn(this.e).filter(new rj0(this, ((zl1) ((zi0) obj2)).m)).map(new ux1(8, this)).subscribe(zb0.n, new du(5, this));
            v03.i(disposableSubscribe, "private fun scheduleHear…eatEventFatalError)\n    }");
            this.h = disposableSubscribe;
        }
    }
}
