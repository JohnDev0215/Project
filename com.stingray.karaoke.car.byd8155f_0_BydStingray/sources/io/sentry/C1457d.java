package io.sentry;

import h2.AbstractC1347d;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import n7.AbstractC1985B;

/* JADX INFO: renamed from: io.sentry.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1457d implements InterfaceC1485m0 {
    public final Long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Date f16512m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16513n;
    public String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ConcurrentHashMap f16514p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f16515q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f16516r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public EnumC1495p1 f16517s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ConcurrentHashMap f16518t;

    public C1457d(Date date) {
        this.f16514p = new ConcurrentHashMap();
        this.f16512m = date;
        this.l = null;
    }

    public static C1457d b(String str, String str2) {
        C1457d c1457d = new C1457d();
        h3.y yVarA = io.sentry.util.j.a(str);
        c1457d.o = "http";
        c1457d.f16515q = "http";
        String str3 = (String) yVarA.f15499m;
        if (str3 != null) {
            c1457d.c(str3, "url");
        }
        c1457d.c(str2.toUpperCase(Locale.ROOT), "method");
        String str4 = (String) yVarA.f15500n;
        if (str4 != null) {
            c1457d.c(str4, "http.query");
        }
        String str5 = (String) yVarA.o;
        if (str5 != null) {
            c1457d.c(str5, "http.fragment");
        }
        return c1457d;
    }

    public final Date a() {
        Date date = this.f16512m;
        if (date != null) {
            return (Date) date.clone();
        }
        Long l = this.l;
        if (l == null) {
            throw new IllegalStateException("No timestamp set for breadcrumb");
        }
        Date dateG = AbstractC1985B.g(l.longValue());
        this.f16512m = dateG;
        return dateG;
    }

    public final void c(Object obj, String str) {
        this.f16514p.put(str, obj);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1457d.class != obj.getClass()) {
            return false;
        }
        C1457d c1457d = (C1457d) obj;
        return a().getTime() == c1457d.a().getTime() && h6.e.i(this.f16513n, c1457d.f16513n) && h6.e.i(this.o, c1457d.o) && h6.e.i(this.f16515q, c1457d.f16515q) && h6.e.i(this.f16516r, c1457d.f16516r) && this.f16517s == c1457d.f16517s;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16512m, this.f16513n, this.o, this.f16515q, this.f16516r, this.f16517s});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("timestamp");
        c1489n1.I(k10, a());
        if (this.f16513n != null) {
            c1489n1.w("message");
            c1489n1.L(this.f16513n);
        }
        if (this.o != null) {
            c1489n1.w("type");
            c1489n1.L(this.o);
        }
        c1489n1.w("data");
        c1489n1.I(k10, this.f16514p);
        if (this.f16515q != null) {
            c1489n1.w("category");
            c1489n1.L(this.f16515q);
        }
        if (this.f16516r != null) {
            c1489n1.w("origin");
            c1489n1.L(this.f16516r);
        }
        if (this.f16517s != null) {
            c1489n1.w("level");
            c1489n1.I(k10, this.f16517s);
        }
        ConcurrentHashMap concurrentHashMap = this.f16518t;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16518t, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }

    public C1457d(long j3) {
        this.f16514p = new ConcurrentHashMap();
        this.l = Long.valueOf(j3);
        this.f16512m = null;
    }

    public C1457d(C1457d c1457d) {
        this.f16514p = new ConcurrentHashMap();
        this.f16512m = c1457d.f16512m;
        this.l = c1457d.l;
        this.f16513n = c1457d.f16513n;
        this.o = c1457d.o;
        this.f16515q = c1457d.f16515q;
        this.f16516r = c1457d.f16516r;
        ConcurrentHashMap concurrentHashMapP = h6.r.p(c1457d.f16514p);
        if (concurrentHashMapP != null) {
            this.f16514p = concurrentHashMapP;
        }
        this.f16518t = h6.r.p(c1457d.f16518t);
        this.f16517s = c1457d.f16517s;
    }

    public C1457d() {
        this(System.currentTimeMillis());
    }
}
