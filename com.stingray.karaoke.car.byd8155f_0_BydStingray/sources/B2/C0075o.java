package B2;

import android.net.Uri;
import d5.C1078G;
import d5.d0;
import h3.C1351b;
import h3.C1353d;
import h3.C1354e;
import i3.C1405d;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: renamed from: B2.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0075o implements u {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final int[] f592p = {5, 4, 12, 8, 3, 10, 9, 11, 6, 2, 0, 1, 7, 16, 15, 14, 17, 18, 19, 20, 21};

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final M.t f593q = new M.t(new C0074n(0));

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final M.t f594r = new M.t(new C0074n(1));
    public d0 l;
    public int o;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public io.sentry.hints.i f596n = new io.sentry.hints.i(14);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f595m = true;

    public final void a(int i7, ArrayList arrayList) {
        switch (i7) {
            case 0:
                arrayList.add(new C1351b());
                break;
            case 1:
                arrayList.add(new C1353d());
                break;
            case 2:
                arrayList.add(new C1354e());
                break;
            case 3:
                arrayList.add(new C2.a());
                break;
            case 4:
                r rVarF = f593q.F(0);
                if (rVarF == null) {
                    arrayList.add(new G2.d());
                } else {
                    arrayList.add(rVarF);
                }
                break;
            case 5:
                arrayList.add(new H2.b());
                break;
            case 6:
                arrayList.add(new T2.d(this.f596n, this.f595m ? 0 : 2));
                break;
            case 7:
                arrayList.add(new U2.d());
                break;
            case 8:
                arrayList.add(new V2.i(this.f596n, this.f595m ? 0 : 32));
                arrayList.add(new V2.l(this.f596n, this.f595m ? 0 : 16));
                break;
            case 9:
                arrayList.add(new W2.d());
                break;
            case 10:
                arrayList.add(new h3.C());
                break;
            case 11:
                if (this.l == null) {
                    C1078G c1078g = d5.I.f13384m;
                    this.l = d0.f13415p;
                }
                arrayList.add(new h3.F(!this.f595m ? 1 : 0, this.f596n, new h2.y(0L), new e7.f(this.l, 5)));
                break;
            case 12:
                arrayList.add(new C1405d());
                break;
            case 14:
                arrayList.add(new J2.a(this.o));
                break;
            case 15:
                r rVarF2 = f594r.F(new Object[0]);
                if (rVarF2 != null) {
                    arrayList.add(rVarF2);
                }
                break;
            case 16:
                arrayList.add(new D2.b(!this.f595m ? 1 : 0, this.f596n));
                break;
            case 17:
                arrayList.add(new F2.a(1));
                break;
            case 18:
                arrayList.add(new E2.a(2));
                break;
            case 19:
                arrayList.add(new F2.a(0));
                break;
            case 20:
                arrayList.add(new E2.a(1));
                break;
            case 21:
                arrayList.add(new E2.a(0));
                break;
        }
    }

    @Override // B2.u
    public final synchronized r[] f() {
        return l(Uri.EMPTY, new HashMap());
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0043  */
    @Override // B2.u
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized B2.r[] l(android.net.Uri r22, java.util.Map r23) {
        /*
            Method dump skipped, instruction units count: 806
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: B2.C0075o.l(android.net.Uri, java.util.Map):B2.r[]");
    }
}
