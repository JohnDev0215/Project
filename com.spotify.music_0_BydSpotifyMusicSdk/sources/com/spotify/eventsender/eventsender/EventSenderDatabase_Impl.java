package com.spotify.eventsender.eventsender;

import android.content.Context;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p.a60;
import p.bk0;
import p.c61;
import p.ck0;
import p.f02;
import p.hk0;
import p.ik1;
import p.kk0;
import p.l52;
import p.nx2;
import p.oh2;
import p.qh2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class EventSenderDatabase_Impl extends EventSenderDatabase {
    public volatile kk0 k;
    public volatile ck0 l;
    public volatile hk0 m;
    public volatile f02 n;

    @Override // p.i52
    public final c61 e() {
        return new c61(this, new HashMap(0), new HashMap(0), "Events", "EventSequenceNumbers", "RateLimitedEvents");
    }

    @Override // p.i52
    public final qh2 f(a60 a60Var) {
        l52 l52Var = new l52(a60Var, new nx2(this, 11, 1), "c6ffea087c1eb57023f5bbcc2c41e2ce", "3d91887fb6006f9b1f27d9dd076d75bd");
        Context context = a60Var.a;
        v03.j(context, "context");
        return a60Var.c.a(new oh2(context, a60Var.b, l52Var, false, false));
    }

    @Override // p.i52
    public final List g(LinkedHashMap linkedHashMap) {
        return Arrays.asList(new ik1[0]);
    }

    @Override // p.i52
    public final Set i() {
        return new HashSet();
    }

    @Override // p.i52
    public final Map j() {
        HashMap map = new HashMap();
        map.put(kk0.class, Collections.emptyList());
        map.put(bk0.class, Collections.emptyList());
        map.put(hk0.class, Collections.emptyList());
        map.put(f02.class, Collections.emptyList());
        return map;
    }

    @Override // com.spotify.eventsender.eventsender.EventSenderDatabase
    public final bk0 q() {
        ck0 ck0Var;
        if (this.l != null) {
            return this.l;
        }
        synchronized (this) {
            try {
                if (this.l == null) {
                    this.l = new ck0(this);
                }
                ck0Var = this.l;
            } catch (Throwable th) {
                throw th;
            }
        }
        return ck0Var;
    }

    @Override // com.spotify.eventsender.eventsender.EventSenderDatabase
    public final hk0 r() {
        hk0 hk0Var;
        if (this.m != null) {
            return this.m;
        }
        synchronized (this) {
            try {
                if (this.m == null) {
                    this.m = new hk0(0, this);
                }
                hk0Var = this.m;
            } catch (Throwable th) {
                throw th;
            }
        }
        return hk0Var;
    }

    @Override // com.spotify.eventsender.eventsender.EventSenderDatabase
    public final kk0 s() {
        kk0 kk0Var;
        if (this.k != null) {
            return this.k;
        }
        synchronized (this) {
            try {
                if (this.k == null) {
                    this.k = new kk0(this);
                }
                kk0Var = this.k;
            } catch (Throwable th) {
                throw th;
            }
        }
        return kk0Var;
    }

    @Override // com.spotify.eventsender.eventsender.EventSenderDatabase
    public final f02 t() {
        f02 f02Var;
        if (this.n != null) {
            return this.n;
        }
        synchronized (this) {
            try {
                if (this.n == null) {
                    this.n = new f02(this, 0);
                }
                f02Var = this.n;
            } catch (Throwable th) {
                throw th;
            }
        }
        return f02Var;
    }
}
