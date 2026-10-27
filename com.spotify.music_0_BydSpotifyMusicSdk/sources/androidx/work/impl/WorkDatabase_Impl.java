package androidx.work.impl;

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
import p.ay2;
import p.c61;
import p.cy2;
import p.f02;
import p.g90;
import p.i52;
import p.ii2;
import p.l52;
import p.lx2;
import p.mx2;
import p.my2;
import p.nx2;
import p.oh2;
import p.oy2;
import p.p02;
import p.qh2;
import p.v03;
import p.ww1;

/* JADX INFO: loaded from: classes.dex */
public final class WorkDatabase_Impl extends WorkDatabase {
    public volatile my2 k;
    public volatile g90 l;
    public volatile f02 m;
    public volatile cy2 n;
    public volatile ay2 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile cy2 f23p;
    public volatile ww1 q;

    @Override // p.i52
    public final c61 e() {
        return new c61(this, new HashMap(0), new HashMap(0), "Dependency", "WorkSpec", "WorkTag", "SystemIdInfo", "WorkName", "WorkProgress", "Preference");
    }

    @Override // p.i52
    public final qh2 f(a60 a60Var) {
        l52 l52Var = new l52(a60Var, new nx2(this, 20, 0), "7d73d21f1bd82c9e5268b6dcf9fde2cb", "3071c8717539de5d5353f4c8cd59a032");
        Context context = a60Var.a;
        v03.j(context, "context");
        return a60Var.c.a(new oh2(context, a60Var.b, l52Var, false, false));
    }

    @Override // p.i52
    public final List g(LinkedHashMap linkedHashMap) {
        return Arrays.asList(new lx2(0), new mx2(0), new lx2(1), new lx2(2), new lx2(3), new mx2(1));
    }

    @Override // p.i52
    public final Set i() {
        return new HashSet();
    }

    @Override // p.i52
    public final Map j() {
        HashMap map = new HashMap();
        map.put(my2.class, Collections.emptyList());
        map.put(g90.class, Collections.emptyList());
        map.put(oy2.class, Collections.emptyList());
        map.put(ii2.class, Collections.emptyList());
        map.put(ay2.class, Collections.emptyList());
        map.put(cy2.class, Collections.emptyList());
        map.put(ww1.class, Collections.emptyList());
        map.put(p02.class, Collections.emptyList());
        return map;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final g90 q() {
        g90 g90Var;
        if (this.l != null) {
            return this.l;
        }
        synchronized (this) {
            try {
                if (this.l == null) {
                    this.l = new g90((i52) this);
                }
                g90Var = this.l;
            } catch (Throwable th) {
                throw th;
            }
        }
        return g90Var;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final ww1 r() {
        ww1 ww1Var;
        if (this.q != null) {
            return this.q;
        }
        synchronized (this) {
            try {
                if (this.q == null) {
                    this.q = new ww1(this);
                }
                ww1Var = this.q;
            } catch (Throwable th) {
                throw th;
            }
        }
        return ww1Var;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final ii2 s() {
        cy2 cy2Var;
        if (this.n != null) {
            return this.n;
        }
        synchronized (this) {
            try {
                if (this.n == null) {
                    this.n = new cy2(this, 1);
                }
                cy2Var = this.n;
            } catch (Throwable th) {
                throw th;
            }
        }
        return cy2Var;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final ay2 t() {
        ay2 ay2Var;
        if (this.o != null) {
            return this.o;
        }
        synchronized (this) {
            try {
                if (this.o == null) {
                    this.o = new ay2(this, 0);
                }
                ay2Var = this.o;
            } catch (Throwable th) {
                throw th;
            }
        }
        return ay2Var;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final cy2 u() {
        cy2 cy2Var;
        if (this.f23p != null) {
            return this.f23p;
        }
        synchronized (this) {
            try {
                if (this.f23p == null) {
                    this.f23p = new cy2(this, 0);
                }
                cy2Var = this.f23p;
            } catch (Throwable th) {
                throw th;
            }
        }
        return cy2Var;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final my2 v() {
        my2 my2Var;
        if (this.k != null) {
            return this.k;
        }
        synchronized (this) {
            try {
                if (this.k == null) {
                    this.k = new my2(this);
                }
                my2Var = this.k;
            } catch (Throwable th) {
                throw th;
            }
        }
        return my2Var;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final oy2 w() {
        f02 f02Var;
        if (this.m != null) {
            return this.m;
        }
        synchronized (this) {
            try {
                if (this.m == null) {
                    this.m = new f02(this, 1);
                }
                f02Var = this.m;
            } catch (Throwable th) {
                throw th;
            }
        }
        return f02Var;
    }
}
