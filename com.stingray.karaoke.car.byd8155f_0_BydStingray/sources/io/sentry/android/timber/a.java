package io.sentry.android.timber;

import A7.m;
import B9.b;
import io.sentry.C1457d;
import io.sentry.C1480k1;
import io.sentry.C1523x;
import io.sentry.E;
import io.sentry.EnumC1495p1;
import io.sentry.protocol.k;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class a extends b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E f16472b;
    public final EnumC1495p1 c;
    public final EnumC1495p1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ThreadLocal f16473e;

    public a(EnumC1495p1 enumC1495p1, EnumC1495p1 enumC1495p12) {
        E e10 = E.f15834a;
        m.f(enumC1495p1, "minEventLevel");
        m.f(enumC1495p12, "minBreadcrumbLevel");
        this.f16472b = e10;
        this.c = enumC1495p1;
        this.d = enumC1495p12;
        this.f16473e = new ThreadLocal();
    }

    @Override // B9.b
    public final void a(Exception exc) {
        super.a(exc);
        e(exc, null, new Object[0]);
    }

    @Override // B9.b
    public final void b(String str, Object... objArr) {
        m.f(objArr, "args");
        super.b(str, Arrays.copyOf(objArr, objArr.length));
        e(null, str, Arrays.copyOf(objArr, objArr.length));
    }

    @Override // B9.b
    public final void c(String str, String str2) {
        m.f(str2, "message");
        this.f16473e.set(str);
    }

    public final void e(Exception exc, String str, Object... objArr) {
        ThreadLocal threadLocal = this.f16473e;
        String str2 = (String) threadLocal.get();
        if (str2 != null) {
            threadLocal.remove();
        }
        if ((str == null || str.length() == 0) && exc == null) {
            return;
        }
        EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
        k kVar = new k();
        kVar.f16739m = str;
        if (str != null && str.length() != 0 && objArr.length != 0) {
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
            kVar.l = String.format(str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
        }
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(String.valueOf(obj));
        }
        kVar.f16740n = new ArrayList(arrayList);
        boolean z6 = enumC1495p1.ordinal() >= this.c.ordinal();
        E e10 = this.f16472b;
        if (z6) {
            C1480k1 c1480k1 = new C1480k1();
            c1480k1.f16601F = enumC1495p1;
            if (exc != null) {
                c1480k1.f15985u = exc;
            }
            if (str2 != null) {
                c1480k1.a("TimberTag", str2);
            }
            c1480k1.B = kVar;
            c1480k1.f16598C = "Timber";
            e10.getClass();
            e10.s(c1480k1, new C1523x());
        }
        if (enumC1495p1.ordinal() >= this.d.ordinal()) {
            C1457d c1457d = null;
            String message = exc != null ? exc.getMessage() : null;
            if (kVar.f16739m != null) {
                c1457d = new C1457d();
                c1457d.f16517s = enumC1495p1;
                c1457d.f16515q = "Timber";
                String str3 = kVar.l;
                if (str3 == null) {
                    str3 = kVar.f16739m;
                }
                c1457d.f16513n = str3;
            } else if (message != null) {
                c1457d = new C1457d();
                c1457d.o = "error";
                c1457d.f16513n = message;
                c1457d.f16517s = EnumC1495p1.ERROR;
                c1457d.f16515q = "exception";
            }
            if (c1457d != null) {
                e10.e(c1457d);
            }
        }
    }
}
