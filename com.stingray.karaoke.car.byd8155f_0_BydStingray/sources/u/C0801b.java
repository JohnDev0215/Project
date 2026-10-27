package U;

import E0.C0171v;
import T.C0735d;
import T.C0761q;
import java.util.ArrayList;

/* JADX INFO: renamed from: U.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0801b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0761q f8943a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0800a f8944b;
    public boolean c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f8946f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f8947g;
    public int l;
    public final C0171v d = new C0171v();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f8945e = true;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final A7.D f8948h = new A7.D(2, false);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f8949i = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f8950j = -1;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f8951k = -1;

    public C0801b(C0761q c0761q, C0800a c0800a) {
        this.f8943a = c0761q;
        this.f8944b = c0800a;
    }

    public final void a() {
        c();
        A7.D d = this.f8948h;
        if (d.l.isEmpty()) {
            this.f8947g++;
        } else {
            d.l.remove(r2.size() - 1);
        }
    }

    public final void b() {
        int i7 = this.f8947g;
        if (i7 > 0) {
            C0800a c0800a = this.f8944b;
            c0800a.getClass();
            A a10 = A.c;
            D d = c0800a.d;
            d.W(a10);
            com.bumptech.glide.c.B0(d, 0, i7);
            int i10 = d.f8941j;
            int i11 = a10.f8934a;
            int iP = D.P(d, i11);
            int i12 = a10.f8935b;
            if (i10 != iP || d.f8942k != D.P(d, i12)) {
                StringBuilder sb = new StringBuilder();
                int i13 = 0;
                for (int i14 = 0; i14 < i11; i14++) {
                    if (((1 << i14) & d.f8941j) != 0) {
                        if (i13 > 0) {
                            sb.append(", ");
                        }
                        sb.append(a10.b(i14));
                        i13++;
                    }
                }
                String string = sb.toString();
                StringBuilder sbK = M5.b.k(string, "StringBuilder().apply(builderAction).toString()");
                int i15 = 0;
                int i16 = 0;
                while (i15 < i12) {
                    int i17 = i12;
                    if (((1 << i15) & d.f8942k) != 0) {
                        if (i13 > 0) {
                            sbK.append(", ");
                        }
                        sbK.append(a10.c(i15));
                        i16++;
                    }
                    i15++;
                    i12 = i17;
                }
                String string2 = sbK.toString();
                A7.m.e(string2, "StringBuilder().apply(builderAction).toString()");
                StringBuilder sb2 = new StringBuilder("Error while pushing ");
                sb2.append(a10);
                sb2.append(". Not all arguments were provided. Missing ");
                M5.b.p(sb2, i13, " int arguments (", string, ") and ");
                M5.b.t(sb2, i16, " object arguments (", string2, ").");
                throw null;
            }
            this.f8947g = 0;
        }
        A7.D d10 = this.f8948h;
        if (d10.l.isEmpty()) {
            return;
        }
        C0800a c0800a2 = this.f8944b;
        ArrayList arrayList = d10.l;
        int size = arrayList.size();
        Object[] objArr = new Object[size];
        for (int i18 = 0; i18 < size; i18++) {
            objArr[i18] = arrayList.get(i18);
        }
        c0800a2.getClass();
        if (size != 0) {
            g gVar = g.c;
            D d11 = c0800a2.d;
            d11.W(gVar);
            com.bumptech.glide.c.C0(d11, 0, objArr);
            int i19 = d11.f8941j;
            int i20 = gVar.f8934a;
            int iP2 = D.P(d11, i20);
            int i21 = gVar.f8935b;
            if (i19 != iP2 || d11.f8942k != D.P(d11, i21)) {
                StringBuilder sb3 = new StringBuilder();
                int i22 = 0;
                for (int i23 = 0; i23 < i20; i23++) {
                    if (((1 << i23) & d11.f8941j) != 0) {
                        if (i22 > 0) {
                            sb3.append(", ");
                        }
                        sb3.append(gVar.b(i23));
                        i22++;
                    }
                }
                String string3 = sb3.toString();
                StringBuilder sbK2 = M5.b.k(string3, "StringBuilder().apply(builderAction).toString()");
                int i24 = 0;
                int i25 = 0;
                while (i24 < i21) {
                    int i26 = i21;
                    if (((1 << i24) & d11.f8942k) != 0) {
                        if (i22 > 0) {
                            sbK2.append(", ");
                        }
                        sbK2.append(gVar.c(i24));
                        i25++;
                    }
                    i24++;
                    i21 = i26;
                }
                String string4 = sbK2.toString();
                A7.m.e(string4, "StringBuilder().apply(builderAction).toString()");
                StringBuilder sb4 = new StringBuilder("Error while pushing ");
                sb4.append(gVar);
                sb4.append(". Not all arguments were provided. Missing ");
                M5.b.p(sb4, i22, " int arguments (", string3, ") and ");
                M5.b.t(sb4, i25, " object arguments (", string4, ").");
                throw null;
            }
        }
        arrayList.clear();
    }

    public final void c() {
        int i7 = this.l;
        if (i7 > 0) {
            int i10 = this.f8949i;
            if (i10 >= 0) {
                b();
                C0800a c0800a = this.f8944b;
                c0800a.getClass();
                t tVar = t.c;
                D d = c0800a.d;
                d.W(tVar);
                com.bumptech.glide.c.B0(d, 0, i10);
                com.bumptech.glide.c.B0(d, 1, i7);
                int i11 = d.f8941j;
                int i12 = tVar.f8934a;
                int iP = D.P(d, i12);
                int i13 = tVar.f8935b;
                if (i11 != iP || d.f8942k != D.P(d, i13)) {
                    StringBuilder sb = new StringBuilder();
                    int i14 = 0;
                    for (int i15 = 0; i15 < i12; i15++) {
                        if (((1 << i15) & d.f8941j) != 0) {
                            if (i14 > 0) {
                                sb.append(", ");
                            }
                            sb.append(tVar.b(i15));
                            i14++;
                        }
                    }
                    String string = sb.toString();
                    StringBuilder sbK = M5.b.k(string, "StringBuilder().apply(builderAction).toString()");
                    int i16 = 0;
                    int i17 = 0;
                    while (i17 < i13) {
                        int i18 = i13;
                        if (((1 << i17) & d.f8942k) != 0) {
                            if (i14 > 0) {
                                sbK.append(", ");
                            }
                            sbK.append(tVar.c(i17));
                            i16++;
                        }
                        i17++;
                        i13 = i18;
                    }
                    String string2 = sbK.toString();
                    A7.m.e(string2, "StringBuilder().apply(builderAction).toString()");
                    StringBuilder sb2 = new StringBuilder("Error while pushing ");
                    sb2.append(tVar);
                    sb2.append(". Not all arguments were provided. Missing ");
                    M5.b.p(sb2, i14, " int arguments (", string, ") and ");
                    M5.b.t(sb2, i16, " object arguments (", string2, ").");
                    throw null;
                }
                this.f8949i = -1;
            } else {
                int i19 = this.f8951k;
                int i20 = this.f8950j;
                b();
                C0800a c0800a2 = this.f8944b;
                c0800a2.getClass();
                q qVar = q.c;
                D d10 = c0800a2.d;
                d10.W(qVar);
                com.bumptech.glide.c.B0(d10, 1, i19);
                com.bumptech.glide.c.B0(d10, 0, i20);
                com.bumptech.glide.c.B0(d10, 2, i7);
                int i21 = d10.f8941j;
                int i22 = qVar.f8934a;
                int iP2 = D.P(d10, i22);
                int i23 = qVar.f8935b;
                if (i21 != iP2 || d10.f8942k != D.P(d10, i23)) {
                    StringBuilder sb3 = new StringBuilder();
                    int i24 = 0;
                    for (int i25 = 0; i25 < i22; i25++) {
                        if (((1 << i25) & d10.f8941j) != 0) {
                            if (i24 > 0) {
                                sb3.append(", ");
                            }
                            sb3.append(qVar.b(i25));
                            i24++;
                        }
                    }
                    String string3 = sb3.toString();
                    StringBuilder sbK2 = M5.b.k(string3, "StringBuilder().apply(builderAction).toString()");
                    int i26 = 0;
                    int i27 = 0;
                    while (i26 < i23) {
                        int i28 = i23;
                        if (((1 << i26) & d10.f8942k) != 0) {
                            if (i24 > 0) {
                                sbK2.append(", ");
                            }
                            sbK2.append(qVar.c(i26));
                            i27++;
                        }
                        i26++;
                        i23 = i28;
                    }
                    String string4 = sbK2.toString();
                    A7.m.e(string4, "StringBuilder().apply(builderAction).toString()");
                    StringBuilder sb4 = new StringBuilder("Error while pushing ");
                    sb4.append(qVar);
                    sb4.append(". Not all arguments were provided. Missing ");
                    M5.b.p(sb4, i24, " int arguments (", string3, ") and ");
                    M5.b.t(sb4, i27, " object arguments (", string4, ").");
                    throw null;
                }
                this.f8950j = -1;
                this.f8951k = -1;
            }
            this.l = 0;
        }
    }

    public final void d(boolean z6) {
        C0761q c0761q = this.f8943a;
        int i7 = z6 ? c0761q.f8294F.f8395i : c0761q.f8294F.f8393g;
        int i10 = i7 - this.f8946f;
        if (!(i10 >= 0)) {
            C0735d.y("Tried to seek backward");
            throw null;
        }
        if (i10 > 0) {
            C0800a c0800a = this.f8944b;
            c0800a.getClass();
            C0803d c0803d = C0803d.c;
            D d = c0800a.d;
            d.W(c0803d);
            com.bumptech.glide.c.B0(d, 0, i10);
            int i11 = d.f8941j;
            int i12 = c0803d.f8934a;
            int iP = D.P(d, i12);
            int i13 = c0803d.f8935b;
            if (i11 == iP && d.f8942k == D.P(d, i13)) {
                this.f8946f = i7;
                return;
            }
            StringBuilder sb = new StringBuilder();
            int i14 = 0;
            for (int i15 = 0; i15 < i12; i15++) {
                if (((1 << i15) & d.f8941j) != 0) {
                    if (i14 > 0) {
                        sb.append(", ");
                    }
                    sb.append(c0803d.b(i15));
                    i14++;
                }
            }
            String string = sb.toString();
            StringBuilder sbK = M5.b.k(string, "StringBuilder().apply(builderAction).toString()");
            int i16 = 0;
            for (int i17 = 0; i17 < i13; i17++) {
                if (((1 << i17) & d.f8942k) != 0) {
                    if (i14 > 0) {
                        sbK.append(", ");
                    }
                    sbK.append(c0803d.c(i17));
                    i16++;
                }
            }
            String string2 = sbK.toString();
            A7.m.e(string2, "StringBuilder().apply(builderAction).toString()");
            StringBuilder sb2 = new StringBuilder("Error while pushing ");
            sb2.append(c0803d);
            sb2.append(". Not all arguments were provided. Missing ");
            M5.b.p(sb2, i14, " int arguments (", string, ") and ");
            M5.b.t(sb2, i16, " object arguments (", string2, ").");
            throw null;
        }
    }

    public final void e(int i7, int i10) {
        if (i10 > 0) {
            if (!(i7 >= 0)) {
                C0735d.y("Invalid remove index " + i7);
                throw null;
            }
            if (this.f8949i == i7) {
                this.l += i10;
                return;
            }
            c();
            this.f8949i = i7;
            this.l = i10;
        }
    }
}
