package io.sentry.okhttp;

import A7.m;
import c9.H;
import c9.w;
import h3.y;
import h6.n;
import io.sentry.AbstractC1453b1;
import io.sentry.C1457d;
import io.sentry.C1523x;
import io.sentry.E;
import io.sentry.T1;
import io.sentry.U;
import io.sentry.util.f;
import io.sentry.util.j;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final H0.b f16628a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f16629b;
    public final C1457d c;
    public final U d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public H f16630e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public H f16631f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AtomicBoolean f16632g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AtomicBoolean f16633h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f16634i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f16635j;

    public a(H0.b bVar) {
        U uX;
        E e10 = E.f15834a;
        m.f(bVar, "request");
        this.f16628a = bVar;
        this.f16629b = new ConcurrentHashMap();
        this.f16632g = new AtomicBoolean(false);
        this.f16633h = new AtomicBoolean(false);
        w wVar = (w) bVar.f2765m;
        y yVarA = j.a(wVar.f12624h);
        String str = (String) yVarA.f15499m;
        str = str == null ? "unknown" : str;
        this.f16634i = str;
        String strB = wVar.b();
        String str2 = (String) bVar.f2766n;
        this.f16635j = str2;
        U uI = f.f16921a ? e10.i() : e10.b();
        if (uI != null) {
            uX = uI.x("http.client", str2 + ' ' + str);
        } else {
            uX = null;
        }
        this.d = uX;
        T1 t1P = uX != null ? uX.p() : null;
        if (t1P != null) {
            t1P.f15963t = "auto.http.okhttp";
        }
        if (uX != null) {
            String str3 = (String) yVarA.f15500n;
            if (str3 != null) {
                uX.A(str3, "http.query");
            }
            String str4 = (String) yVarA.o;
            if (str4 != null) {
                uX.A(str4, "http.fragment");
            }
        }
        C1457d c1457dB = C1457d.b(str, str2);
        this.c = c1457dB;
        String str5 = wVar.d;
        c1457dB.c(str5, "host");
        c1457dB.c(strB, "path");
        c1457dB.c(Long.valueOf(System.currentTimeMillis()), "http.start_timestamp");
        if (uX != null) {
            uX.A(str, "url");
        }
        if (uX != null) {
            uX.A(str5, "host");
        }
        if (uX != null) {
            uX.A(strB, "path");
        }
        if (uX != null) {
            Locale locale = Locale.ROOT;
            m.e(locale, "ROOT");
            String upperCase = str2.toUpperCase(locale);
            m.e(upperCase, "this as java.lang.String).toUpperCase(locale)");
            uX.A(upperCase, "http.request.method");
        }
    }

    public static void b(a aVar, AbstractC1453b1 abstractC1453b1, b bVar, int i7) {
        if ((i7 & 1) != 0) {
            abstractC1453b1 = null;
        }
        if ((i7 & 2) != 0) {
            bVar = null;
        }
        if (aVar.f16633h.getAndSet(true)) {
            return;
        }
        C1523x c1523x = new C1523x();
        c1523x.c(aVar.f16628a, "okHttp:request");
        H h10 = aVar.f16630e;
        if (h10 != null) {
            c1523x.c(h10, "okHttp:response");
        }
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        C1457d c1457d = aVar.c;
        c1457d.c(lValueOf, "http.end_timestamp");
        E e10 = E.f15834a;
        e10.k(c1457d, c1523x);
        U u4 = aVar.d;
        if (u4 == null) {
            H h11 = aVar.f16631f;
            if (h11 != null) {
                n.f(e10, h11.l, h11);
                return;
            }
            return;
        }
        Collection collectionValues = aVar.f16629b.values();
        ArrayList<U> arrayList = new ArrayList();
        for (Object obj : collectionValues) {
            if (!((U) obj).g()) {
                arrayList.add(obj);
            }
        }
        for (U u10 : arrayList) {
            aVar.d(u10);
            if (abstractC1453b1 != null) {
                u10.v(u10.s(), abstractC1453b1);
            } else {
                u10.z();
            }
        }
        if (bVar != null) {
            bVar.b(u4);
        }
        H h12 = aVar.f16631f;
        if (h12 != null) {
            n.f(e10, h12.l, h12);
        }
        if (abstractC1453b1 != null) {
            u4.v(u4.s(), abstractC1453b1);
        } else {
            u4.z();
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final U a(String str) {
        U u4;
        int iHashCode = str.hashCode();
        ConcurrentHashMap concurrentHashMap = this.f16629b;
        U u10 = this.d;
        switch (iHashCode) {
            case -1551625182:
                u4 = !str.equals("secure_connect") ? u10 : (U) concurrentHashMap.get("connect");
                break;
            case -21341816:
                u4 = !str.equals("response_headers") ? u10 : (U) concurrentHashMap.get("connection");
                break;
            case 1302741330:
                u4 = !str.equals("request_body") ? u10 : (U) concurrentHashMap.get("connection");
                break;
            case 1382943190:
                u4 = !str.equals("request_headers") ? u10 : (U) concurrentHashMap.get("connection");
                break;
            case 1676238560:
                u4 = !str.equals("response_body") ? u10 : (U) concurrentHashMap.get("connection");
                break;
            default:
                u4 = u10;
                break;
        }
        return u4 == null ? u10 : u4;
    }

    public final U c(String str, InterfaceC2782k interfaceC2782k) {
        U u4 = (U) this.f16629b.get(str);
        if (u4 == null) {
            return null;
        }
        U uA = a(str);
        if (interfaceC2782k != null) {
            interfaceC2782k.b(u4);
        }
        d(u4);
        U u10 = this.d;
        if (uA != null && !uA.equals(u10)) {
            if (interfaceC2782k != null) {
                interfaceC2782k.b(uA);
            }
            d(uA);
        }
        if (u10 != null && interfaceC2782k != null) {
            interfaceC2782k.b(u10);
        }
        u4.z();
        return u4;
    }

    public final void d(U u4) {
        U u10 = this.d;
        if (m.a(u4, u10) || u4.u() == null || u4.s() == null) {
            return;
        }
        if (u10 != null) {
            u10.o(u4.u());
        }
        if (u10 != null) {
            u10.b(u4.s());
        }
        u4.o(null);
    }

    public final void e(String str) {
        if (str != null) {
            this.c.c(str, "error_message");
            U u4 = this.d;
            if (u4 != null) {
                u4.A(str, "error_message");
            }
        }
    }

    public final void f(String str) {
        U uA = a(str);
        if (uA != null) {
            U uX = uA.x("http.client.".concat(str), this.f16635j + ' ' + this.f16634i);
            if (str.equals("response_body")) {
                this.f16632g.set(true);
            }
            uX.p().f15963t = "auto.http.okhttp";
            this.f16629b.put(str, uX);
        }
    }
}
