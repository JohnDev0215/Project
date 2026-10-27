package io.sentry;

import h2.AbstractC1347d;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final class R1 implements InterfaceC1485m0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public ConcurrentHashMap f15928A;
    public final Date l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Date f15929m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AtomicInteger f15930n;
    public final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final UUID f15931p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Boolean f15932q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Q1 f15933r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Long f15934s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Double f15935t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final String f15936u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f15937v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final String f15938w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final String f15939x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f15940y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final Object f15941z = new Object();

    public R1(Q1 q12, Date date, Date date2, int i7, String str, UUID uuid, Boolean bool, Long l, Double d, String str2, String str3, String str4, String str5, String str6) {
        this.f15933r = q12;
        this.l = date;
        this.f15929m = date2;
        this.f15930n = new AtomicInteger(i7);
        this.o = str;
        this.f15931p = uuid;
        this.f15932q = bool;
        this.f15934s = l;
        this.f15935t = d;
        this.f15936u = str2;
        this.f15937v = str3;
        this.f15938w = str4;
        this.f15939x = str5;
        this.f15940y = str6;
    }

    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final R1 clone() {
        return new R1(this.f15933r, this.l, this.f15929m, this.f15930n.get(), this.o, this.f15931p, this.f15932q, this.f15934s, this.f15935t, this.f15936u, this.f15937v, this.f15938w, this.f15939x, this.f15940y);
    }

    public final void b(Date date) {
        synchronized (this.f15941z) {
            try {
                this.f15932q = null;
                if (this.f15933r == Q1.Ok) {
                    this.f15933r = Q1.Exited;
                }
                if (date != null) {
                    this.f15929m = date;
                } else {
                    this.f15929m = AbstractC1985B.f();
                }
                if (this.f15929m != null) {
                    this.f15935t = Double.valueOf(Math.abs(r6.getTime() - this.l.getTime()) / 1000.0d);
                    long time = this.f15929m.getTime();
                    if (time < 0) {
                        time = Math.abs(time);
                    }
                    this.f15934s = Long.valueOf(time);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean c(Q1 q12, String str, boolean z6, String str2) {
        boolean z10;
        boolean z11;
        synchronized (this.f15941z) {
            z10 = true;
            if (q12 != null) {
                try {
                    this.f15933r = q12;
                    z11 = true;
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                z11 = false;
            }
            if (str != null) {
                this.f15937v = str;
                z11 = true;
            }
            if (z6) {
                this.f15930n.addAndGet(1);
                z11 = true;
            }
            if (str2 != null) {
                this.f15940y = str2;
            } else {
                z10 = z11;
            }
            if (z10) {
                this.f15932q = null;
                Date dateF = AbstractC1985B.f();
                this.f15929m = dateF;
                if (dateF != null) {
                    long time = dateF.getTime();
                    if (time < 0) {
                        time = Math.abs(time);
                    }
                    this.f15934s = Long.valueOf(time);
                }
            }
        }
        return z10;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        UUID uuid = this.f15931p;
        if (uuid != null) {
            c1489n1.w("sid");
            c1489n1.L(uuid.toString());
        }
        String str = this.o;
        if (str != null) {
            c1489n1.w("did");
            c1489n1.L(str);
        }
        if (this.f15932q != null) {
            c1489n1.w("init");
            c1489n1.J(this.f15932q);
        }
        c1489n1.w("started");
        c1489n1.I(k10, this.l);
        c1489n1.w("status");
        c1489n1.I(k10, this.f15933r.name().toLowerCase(Locale.ROOT));
        if (this.f15934s != null) {
            c1489n1.w("seq");
            c1489n1.K(this.f15934s);
        }
        c1489n1.w("errors");
        c1489n1.H(this.f15930n.intValue());
        if (this.f15935t != null) {
            c1489n1.w("duration");
            c1489n1.K(this.f15935t);
        }
        if (this.f15929m != null) {
            c1489n1.w("timestamp");
            c1489n1.I(k10, this.f15929m);
        }
        if (this.f15940y != null) {
            c1489n1.w("abnormal_mechanism");
            c1489n1.I(k10, this.f15940y);
        }
        c1489n1.w("attrs");
        c1489n1.i();
        c1489n1.w("release");
        c1489n1.I(k10, this.f15939x);
        String str2 = this.f15938w;
        if (str2 != null) {
            c1489n1.w("environment");
            c1489n1.I(k10, str2);
        }
        String str3 = this.f15936u;
        if (str3 != null) {
            c1489n1.w("ip_address");
            c1489n1.I(k10, str3);
        }
        if (this.f15937v != null) {
            c1489n1.w("user_agent");
            c1489n1.I(k10, this.f15937v);
        }
        c1489n1.n();
        ConcurrentHashMap concurrentHashMap = this.f15928A;
        if (concurrentHashMap != null) {
            for (String str4 : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f15928A, str4, c1489n1, str4, k10);
            }
        }
        c1489n1.n();
    }
}
