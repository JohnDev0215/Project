package com.spotify.automotive.base.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.auth.b;
import com.spotify.base.java.logging.Logger;
import com.spotify.playbacknative.AudioDriver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.functions.k;
import java.util.Objects;
import p.bi1;
import p.bk;
import p.cf;
import p.ci1;
import p.ck;
import p.dk;
import p.e0;
import p.fk;
import p.gd;
import p.q;
import p.qj;
import p.t50;
import p.tr2;
import p.u11;
import p.up1;
import p.vs1;
import p.yf2;

/* JADX INFO: loaded from: classes.dex */
public final class AutomotiveService extends t50 {
    public dk c;
    public yf2 e;

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return ((fk) this.c).b;
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02b3  */
    /* JADX WARN: Type inference failed for: r5v0, types: [p.aj0] */
    @Override // p.t50, android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onCreate() {
        /*
            Method dump skipped, instruction units count: 734
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.base.service.AutomotiveService.onCreate():void");
    }

    @Override // android.app.Service
    public final void onDestroy() {
        fk fkVar = (fk) this.c;
        if (!fkVar.A) {
            fkVar.w.a();
            cf cfVar = fkVar.r;
            if (!cfVar.c.isDisposed()) {
                cfVar.c.dispose();
            }
            cfVar.b.a();
            b bVar = (b) fkVar.f;
            bVar.i.dispose();
            bVar.j.dispose();
            bVar.k.dispose();
            ck ckVar = fkVar.z;
            ckVar.d.dispose();
            ckVar.b.c();
            fkVar.e.d.d();
            qj qjVar = fkVar.i;
            qjVar.m.dispose();
            e0 e0Var = (e0) qjVar.a;
            e0Var.n.d();
            e0Var.q.d();
            synchronized (ci1.class) {
                bi1 bi1Var = ci1.c;
                if (bi1Var != null) {
                    bi1Var.c();
                    ci1.c = null;
                }
            }
            tr2 tr2Var = fkVar.j;
            BroadcastReceiver broadcastReceiver = tr2Var.b;
            Context context = tr2Var.a;
            context.unregisterReceiver(broadcastReceiver);
            context.unregisterReceiver(tr2Var.c);
            fkVar.k.c.d();
            gd gdVar = fkVar.d;
            gdVar.b.getClass();
            com.spotify.audiofocus.coreinternal.a aVar = gdVar.a;
            aVar.abandonAudioFocus();
            AudioDriver.removeListener(aVar);
            AudioDriver.setAudioDriverCallbacks(null);
            aVar.d = null;
            ((up1) fkVar.o).a.d();
            com.spotify.eventsender.eventsender.b bVar2 = (com.spotify.eventsender.eventsender.b) fkVar.n.a;
            synchronized (bVar2) {
                try {
                    if (bVar2.i) {
                        ((u11) bVar2.g).getClass();
                        com.spotify.eventsender.eventsender.observability.a aVar2 = bVar2.f;
                        if (!aVar2.h.isDisposed()) {
                            aVar2.h.dispose();
                        }
                        vs1 vs1Var = bVar2.a;
                        synchronized (vs1Var) {
                            if (!vs1Var.d.isDisposed()) {
                                vs1Var.d.dispose();
                            }
                        }
                        com.spotify.eventsender.disk.a aVar3 = bVar2.d;
                        aVar3.h.d();
                        aVar3.g.dispose();
                        bVar2.i = false;
                    } else {
                        ((u11) bVar2.g).getClass();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            bk bkVar = fkVar.s;
            Disposable disposable = bkVar.c;
            if (disposable != null && !disposable.isDisposed()) {
                bkVar.c.dispose();
                bkVar.c = null;
            }
            fkVar.c.a();
            fkVar.A = true;
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        bk bkVar = ((fk) this.c).b.b.s;
        bkVar.getClass();
        if (intent == null || !"com.spotify.music.action.LOG_OUT".equals(intent.getAction())) {
            return 2;
        }
        Logger.e("Logout intent received, logging out.", new Object[0]);
        io.reactivex.rxjava3.internal.operators.completable.b bVar = new io.reactivex.rxjava3.internal.operators.completable.b(((b) bkVar.b).a(LoginState.k), k.g, 3);
        Service service = bkVar.a;
        Objects.requireNonNull(service);
        int i3 = 1;
        bkVar.c = new io.reactivex.rxjava3.internal.operators.completable.b(bVar, new q(i3, service), i3).subscribe();
        return 2;
    }
}
