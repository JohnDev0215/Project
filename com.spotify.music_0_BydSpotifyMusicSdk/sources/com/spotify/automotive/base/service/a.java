package com.spotify.automotive.base.service;

import android.content.Context;
import android.os.Bundle;
import com.spotify.automotive.ubi.Referrer;
import io.reactivex.rxjava3.android.schedulers.c;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.schedulers.e;
import p.e82;
import p.eg1;
import p.ek;
import p.fk;
import p.gh2;
import p.hw;
import p.if2;
import p.ij;
import p.ki1;
import p.kk0;
import p.kr1;
import p.np2;
import p.rd1;
import p.rp2;
import p.rx;
import p.sn;
import p.up2;
import p.v03;
import p.v4;
import p.vx1;
import p.xb1;
import p.yj;
import p.zn;

/* JADX INFO: loaded from: classes.dex */
public final class a extends ij {
    public final kr1 e;
    public final kk0 f;
    public final ki1 g;
    public zn h;
    public e82 i;
    public v4 j;
    public Disposable k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(Context context, eg1 eg1Var, hw hwVar, kk0 kk0Var, ki1 ki1Var) {
        super(context, eg1Var);
        v03.j(context, "context");
        v03.j(eg1Var, "mbsCapabilities");
        this.e = hwVar;
        this.f = kk0Var;
        this.g = ki1Var;
        this.k = Disposable.e(k.b);
    }

    public static Bundle a(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_ID", str);
        bundle.putString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_LABEL", str2);
        bundle.putString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_ICON_URI", str3);
        return bundle;
    }

    public final sn b() {
        yj yjVar = this.d;
        if (this.h == null && yjVar != null) {
            fk fkVar = ((ek) yjVar).b;
            rd1 rd1Var = fkVar.g;
            vx1 vx1Var = fkVar.u;
            v03.i(vx1Var, "productStateController");
            xb1 xb1Var = fkVar.v;
            v03.i(xb1Var, "loadersResolver");
            Scheduler scheduler = e.c;
            v03.i(scheduler, "io()");
            gh2 gh2Var = new gh2(vx1Var, xb1Var, this.f, scheduler);
            v03.i(rd1Var, "loginStateController");
            rx rxVar = fkVar.e;
            v03.i(rxVar, "connectionStateController");
            kk0 kk0Var = this.f;
            Scheduler schedulerA = c.a();
            up2 up2Var = fkVar.t;
            v03.i(up2Var, "binder.ubiLogger");
            this.h = new zn(rd1Var, rxVar, kk0Var, gh2Var, schedulerA, up2Var);
        }
        return this.h;
    }

    public final void c(if2 if2Var, Integer num, Referrer referrer) {
        yj yjVar = this.d;
        if (yjVar == null) {
            return;
        }
        String string = if2Var.toString();
        v03.i(string, "spotifyUri.toString()");
        rp2 rp2Var = new rp2(string, num, null, referrer);
        yj yjVar2 = this.d;
        if (yjVar2 == null || !((com.spotify.remoteconfig.a) ((ek) yjVar2).b.m.a.get()).f() || referrer != Referrer.PARTNER_VOICE) {
            ((ek) yjVar).b.t.f(rp2Var);
        }
        ((ek) yjVar).b.t.c(new np2(if2Var.toString(), referrer));
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(android.os.Bundle r25, p.vg1 r26, java.lang.String r27) {
        /*
            Method dump skipped, instruction units count: 509
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.base.service.a.d(android.os.Bundle, p.vg1, java.lang.String):void");
    }
}
