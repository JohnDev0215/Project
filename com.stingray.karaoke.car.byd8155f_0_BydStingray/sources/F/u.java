package F;

import G.C0297g;
import c9.H;
import java.util.ArrayList;
import n7.AbstractC1999n;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f1958a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1959b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1960e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1961f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f1962g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f1963h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f1964i;

    public u(g gVar) {
        this.f1962g = gVar;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new s(0, 0));
        this.f1958a = arrayList;
        this.f1960e = -1;
        this.f1963h = new ArrayList();
        this.f1964i = n7.u.l;
    }

    public static u a(u uVar, int i7, g9.e eVar, H0.b bVar, int i10) {
        if ((i10 & 1) != 0) {
            i7 = uVar.f1959b;
        }
        int i11 = i7;
        if ((i10 & 2) != 0) {
            eVar = (g9.e) uVar.f1963h;
        }
        g9.e eVar2 = eVar;
        if ((i10 & 4) != 0) {
            bVar = (H0.b) uVar.f1964i;
        }
        H0.b bVar2 = bVar;
        int i12 = uVar.c;
        int i13 = uVar.d;
        int i14 = uVar.f1960e;
        uVar.getClass();
        A7.m.f(bVar2, "request");
        return new u((g9.j) uVar.f1962g, uVar.f1958a, i11, eVar2, bVar2, i12, i13, i14);
    }

    public int b() {
        return ((int) Math.sqrt((((double) e()) * 1.0d) / ((double) this.f1961f))) + 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00a4  */
    /* JADX WARN: Type inference failed for: r4v15, types: [java.lang.Object, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public C1.j c(int r13) {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F.u.c(int):C1.j");
    }

    public int d(int i7) {
        int i10;
        if (e() <= 0) {
            return 0;
        }
        if (i7 >= e()) {
            throw new IllegalArgumentException("ItemIndex > total count");
        }
        if (!((g) this.f1962g).d) {
            return i7 / this.f1961f;
        }
        ArrayList arrayList = this.f1958a;
        int size = arrayList.size();
        AbstractC1999n.H(arrayList.size(), 0, size);
        int i11 = size - 1;
        int i12 = 0;
        while (true) {
            if (i12 > i11) {
                i10 = -(i12 + 1);
                break;
            }
            i10 = (i12 + i11) >>> 1;
            int i13 = ((s) arrayList.get(i10)).f1954a - i7;
            if (i13 >= 0) {
                if (i13 <= 0) {
                    break;
                }
                i11 = i10 - 1;
            } else {
                i12 = i10 + 1;
            }
        }
        if (i10 < 0) {
            i10 = (-i10) - 2;
        }
        int iB = b() * i10;
        int i14 = ((s) arrayList.get(i10)).f1954a;
        if (i14 > i7) {
            throw new IllegalArgumentException("currentItemIndex > itemIndex");
        }
        int i15 = 0;
        while (i14 < i7) {
            int i16 = i14 + 1;
            int iG = g(i14);
            i15 += iG;
            int i17 = this.f1961f;
            if (i15 >= i17) {
                if (i15 == i17) {
                    iB++;
                    i15 = 0;
                } else {
                    iB++;
                    i15 = iG;
                }
            }
            if (iB % b() == 0 && iB / b() >= arrayList.size()) {
                arrayList.add(new s(i16 - (i15 > 0 ? 1 : 0), 0));
            }
            i14 = i16;
        }
        return g(i7) + i15 > this.f1961f ? iB + 1 : iB;
    }

    public int e() {
        return ((g) this.f1962g).c.f735m;
    }

    public H f(H0.b bVar) {
        A7.m.f(bVar, "request");
        ArrayList arrayList = this.f1958a;
        int size = arrayList.size();
        int i7 = this.f1959b;
        if (i7 >= size) {
            throw new IllegalStateException("Check failed.");
        }
        this.f1961f++;
        g9.e eVar = (g9.e) this.f1963h;
        if (eVar != null) {
            if (!((g9.f) eVar.d).b((c9.w) bVar.f2765m)) {
                throw new IllegalStateException(("network interceptor " + arrayList.get(i7 - 1) + " must retain the same host and port").toString());
            }
            if (this.f1961f != 1) {
                throw new IllegalStateException(("network interceptor " + arrayList.get(i7 - 1) + " must call proceed() exactly once").toString());
            }
        }
        int i10 = i7 + 1;
        u uVarA = a(this, i10, null, bVar, 58);
        c9.x xVar = (c9.x) arrayList.get(i7);
        H hA = xVar.a(uVarA);
        if (hA == null) {
            throw new NullPointerException("interceptor " + xVar + " returned null");
        }
        if (eVar != null && i10 < arrayList.size() && uVarA.f1961f != 1) {
            throw new IllegalStateException(("network interceptor " + xVar + " must call proceed() exactly once").toString());
        }
        if (hA.f12518r != null) {
            return hA;
        }
        throw new IllegalStateException(("interceptor " + xVar + " returned a response with no body").toString());
    }

    public int g(int i7) {
        t tVar = t.f1956a;
        t.f1957b = this.f1961f;
        C0297g c0297gM = ((g) this.f1962g).c.m(i7);
        return (int) ((b) ((f) c0297gM.c).f1878a.invoke(tVar, Integer.valueOf(i7 - c0297gM.f2560a))).f1864a;
    }

    public u(g9.j jVar, ArrayList arrayList, int i7, g9.e eVar, H0.b bVar, int i10, int i11, int i12) {
        A7.m.f(jVar, "call");
        A7.m.f(bVar, "request");
        this.f1962g = jVar;
        this.f1958a = arrayList;
        this.f1959b = i7;
        this.f1963h = eVar;
        this.f1964i = bVar;
        this.c = i10;
        this.d = i11;
        this.f1960e = i12;
    }
}
