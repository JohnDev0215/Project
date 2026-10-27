package com.spotify.eventsender.eventsender;

import android.os.SystemClock;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.spotify.messages.EventSenderStats2NonAuth;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import p.ai0;
import p.ei0;
import p.gj0;
import p.h02;
import p.hk0;
import p.ij0;
import p.lb2;
import p.mb2;
import p.nk0;
import p.ou;
import p.pr1;
import p.qk0;
import p.ri0;
import p.ti0;
import p.u11;
import p.ub0;
import p.uj0;
import p.v03;
import p.wb0;
import p.wc1;
import p.wj0;
import p.xt;
import p.xy1;
import p.zb0;
import p.zi0;
import p.zj0;
import p.zl1;

/* JADX INFO: loaded from: classes.dex */
public final class d implements qk0 {
    public static final byte[] s = ByteString.d("EventSenderStats2NonAuth").q();
    public static final long t = TimeUnit.HOURS.toMillis(23);
    public final hk0 a;
    public final h02 b;
    public final ri0 c;
    public final ub0 d;
    public final e e;
    public final lb2 f;
    public final xt g;
    public final nk0 h;
    public final ti0 i;
    public final zj0 j;
    public final wc1 k;
    public final zb0 l;
    public final xy1 m;
    public final c n;
    public final uj0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f51p;
    public final AtomicLong q;
    public final AtomicLong r;

    public d(hk0 hk0Var, h02 h02Var, ri0 ri0Var, wb0 wb0Var, e eVar, lb2 lb2Var, mb2 mb2Var, ei0 ei0Var, xt xtVar, gj0 gj0Var, ti0 ti0Var, zj0 zj0Var, wc1 wc1Var, zb0 zb0Var, ij0 ij0Var, int i) {
        v03.j(ri0Var, "eventOwnerProvider");
        v03.j(wc1Var, "logger");
        this.a = hk0Var;
        this.b = h02Var;
        this.c = ri0Var;
        this.d = wb0Var;
        this.e = eVar;
        this.f = lb2Var;
        this.g = xtVar;
        this.h = gj0Var;
        this.i = ti0Var;
        this.j = zj0Var;
        this.k = wc1Var;
        this.l = zb0Var;
        this.m = ij0Var;
        this.n = new c();
        this.o = new uj0(xtVar, zb0Var, mb2Var, ei0Var);
        this.f51p = TimeUnit.SECONDS.toMillis(i);
        this.q = new AtomicLong(0L);
        this.r = new AtomicLong(0L);
    }

    @Override // p.qk0
    public final void a(LinkedHashSet linkedHashSet, ArrayList arrayList) {
        Object next;
        if (!arrayList.isEmpty()) {
            AtomicLong atomicLong = this.q;
            xt xtVar = this.g;
            ((u11) xtVar).getClass();
            atomicLong.set(SystemClock.elapsedRealtime());
            ai0 ai0Var = (ai0) ou.c0(arrayList);
            zj0 zj0Var = this.j;
            if (v03.b(ai0Var.b, "EventSenderStats2NonAuth")) {
                Iterator it = ai0Var.e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    } else {
                        next = it.next();
                        if (v03.b(((pr1) next).a, "message")) {
                            break;
                        }
                    }
                }
                pr1 pr1Var = (pr1) next;
                if (pr1Var != null) {
                    try {
                        EventSenderStats2NonAuth eventSenderStats2NonAuthS = EventSenderStats2NonAuth.S((ByteString) pr1Var.b);
                        zi0 zi0Var = (zi0) this.m.get();
                        if (((zl1) zi0Var).c && eventSenderStats2NonAuthS.v() <= 0) {
                            return;
                        }
                        e eVar = this.e;
                        v03.i(eventSenderStats2NonAuthS, "ess2");
                        eVar.getClass();
                        wj0 wj0VarA = e.a(eventSenderStats2NonAuthS);
                        List list = wj0VarA.a;
                        Map map = wj0VarA.b;
                        List list2 = wj0VarA.c;
                        if (!((zl1) zi0Var).c) {
                            if (zj0Var.b(list, map, list2)) {
                                AtomicLong atomicLong2 = this.r;
                                ((u11) xtVar).getClass();
                                atomicLong2.set(SystemClock.elapsedRealtime());
                            }
                            zj0Var.c(list, map, list2);
                        }
                        ((wb0) this.d).l(list2);
                    } catch (InvalidProtocolBufferException e) {
                        ((u11) this.k).v("Couldn't parse ESS2: " + e.getMessage());
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01f0  */
    @Override // p.qk0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.l13 get() {
        /*
            Method dump skipped, instruction units count: 930
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.eventsender.eventsender.d.get():p.l13");
    }
}
