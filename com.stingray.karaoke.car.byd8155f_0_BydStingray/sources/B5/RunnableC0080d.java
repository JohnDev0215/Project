package B5;

import android.os.Handler;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import k7.C1707a;

/* JADX INFO: renamed from: B5.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0080d implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f674m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f675n;
    public Object o;

    public /* synthetic */ RunnableC0080d() {
        this.l = 2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object objCall;
        long jLongValue;
        Object[] objArr;
        b7.q qVar;
        switch (this.l) {
            case 0:
                C0083g c0083g = (C0083g) this.f675n;
                y yVarA = (y) c0083g.f702m.c();
                if (D5.g.h(yVarA)) {
                    yVarA = c0083g.a();
                } else {
                    Object obj = yVarA.l.get("timestamp");
                    if (obj instanceof Long) {
                        jLongValue = ((Long) obj).longValue();
                    } else if (obj instanceof Number) {
                        jLongValue = ((Number) obj).longValue();
                    } else if (obj instanceof String) {
                        try {
                            jLongValue = Long.valueOf((String) obj).longValue();
                        } catch (NumberFormatException unused) {
                            jLongValue = 0;
                        }
                    } else {
                        jLongValue = 0;
                    }
                    if (jLongValue + (c0083g.f699i.l == 3 ? 60000L : 86400000L) <= System.currentTimeMillis()) {
                        y yVarA2 = c0083g.a();
                        if (!D5.g.h(yVarA2)) {
                            yVarA = yVarA2;
                        }
                    }
                }
                c0083g.f704p = yVarA;
                if (D5.g.h(yVarA)) {
                    L l = (L) this.o;
                    if (!l.l.containsKey("integrations")) {
                        l.f(new L(), "integrations");
                    }
                    if (!l.e("integrations").l.containsKey("Segment.io")) {
                        l.e("integrations").f(new L(), "Segment.io");
                    }
                    if (!l.e("integrations").e("Segment.io").l.containsKey("apiKey")) {
                        l.e("integrations").e("Segment.io").g(c0083g.f705q, "apiKey");
                    }
                    l.put("timestamp", Long.valueOf(System.currentTimeMillis()));
                    c0083g.f704p = new y(l);
                }
                if (!c0083g.f704p.e("integrations").e("Segment.io").l.containsKey("apiHost")) {
                    c0083g.f704p.e("integrations").e("Segment.io").g((String) this.f674m, "apiHost");
                }
                C0083g.f692z.post(new RunnableC0079c(this, 0));
                break;
            case 1:
                C0083g c0083g2 = (C0083g) this.f675n;
                x xVar = c0083g2.f697g;
                J j3 = (J) xVar.c();
                String str = (String) this.f674m;
                if (!D5.g.g(str)) {
                    j3.j(str);
                }
                xVar.d(j3);
                C0087k c0087k = c0083g2.f698h;
                c0087k.getClass();
                j3.getClass();
                c0087k.f(new J(Collections.unmodifiableMap(new LinkedHashMap(j3))), "traits");
                C5.e eVar = new C5.e();
                eVar.f998b = (Date) this.o;
                L lC = xVar.c();
                D5.g.a(lC, "traits");
                eVar.f1005h = Collections.unmodifiableMap(new LinkedHashMap(lC));
                c0083g2.b(eVar);
                break;
            case 2:
                try {
                    objCall = ((C1.f) this.o).call();
                } catch (Exception unused2) {
                    objCall = null;
                }
                ((Handler) this.f675n).post(new h5.t((C1.g) this.f674m, 5, objCall));
                break;
            default:
                Object[] objArr2 = (Object[]) this.o;
                int length = objArr2.length - 1;
                if (objArr2.length <= 0 || !(objArr2[length] instanceof b7.q)) {
                    objArr = objArr2;
                    qVar = null;
                } else {
                    Object[] objArr3 = new Object[length];
                    for (int i7 = 0; i7 < length; i7++) {
                        objArr3[i7] = objArr2[i7];
                    }
                    qVar = (b7.q) objArr2[length];
                    objArr = objArr3;
                }
                b7.r rVar = (b7.r) this.f675n;
                rVar.getClass();
                C1707a.a(new b7.p(rVar, (String) this.f674m, objArr, qVar, 0));
                break;
        }
    }

    public /* synthetic */ RunnableC0080d(int i7, Object obj, Object obj2, String str) {
        this.l = i7;
        this.f675n = obj;
        this.o = obj2;
        this.f674m = str;
    }

    public RunnableC0080d(C0083g c0083g, String str, Date date) {
        this.l = 1;
        this.f675n = c0083g;
        this.f674m = str;
        this.o = date;
    }
}
