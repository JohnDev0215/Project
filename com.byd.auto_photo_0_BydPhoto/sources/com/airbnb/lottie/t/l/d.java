package com.airbnb.lottie.t.l;

import androidx.annotation.Nullable;
import com.airbnb.lottie.t.j.j;
import com.airbnb.lottie.t.j.k;
import com.airbnb.lottie.t.j.l;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<com.airbnb.lottie.t.k.b> f888a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.airbnb.lottie.d f889b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f890c;
    private final long d;
    private final a e;
    private final long f;

    @Nullable
    private final String g;
    private final List<com.airbnb.lottie.t.k.g> h;
    private final l i;
    private final int j;
    private final int k;
    private final int l;
    private final float m;
    private final float n;
    private final int o;
    private final int p;

    @Nullable
    private final j q;

    @Nullable
    private final k r;

    @Nullable
    private final com.airbnb.lottie.t.j.b s;
    private final List<com.airbnb.lottie.x.a<Float>> t;
    private final b u;
    private final boolean v;

    public enum a {
        PRE_COMP,
        SOLID,
        IMAGE,
        NULL,
        SHAPE,
        TEXT,
        UNKNOWN
    }

    public enum b {
        NONE,
        ADD,
        INVERT,
        UNKNOWN
    }

    public d(List<com.airbnb.lottie.t.k.b> list, com.airbnb.lottie.d dVar, String str, long j, a aVar, long j2, @Nullable String str2, List<com.airbnb.lottie.t.k.g> list2, l lVar, int i, int i2, int i3, float f, float f2, int i4, int i5, @Nullable j jVar, @Nullable k kVar, List<com.airbnb.lottie.x.a<Float>> list3, b bVar, @Nullable com.airbnb.lottie.t.j.b bVar2, boolean z) {
        this.f888a = list;
        this.f889b = dVar;
        this.f890c = str;
        this.d = j;
        this.e = aVar;
        this.f = j2;
        this.g = str2;
        this.h = list2;
        this.i = lVar;
        this.j = i;
        this.k = i2;
        this.l = i3;
        this.m = f;
        this.n = f2;
        this.o = i4;
        this.p = i5;
        this.q = jVar;
        this.r = kVar;
        this.t = list3;
        this.u = bVar;
        this.s = bVar2;
        this.v = z;
    }

    com.airbnb.lottie.d a() {
        return this.f889b;
    }

    public long b() {
        return this.d;
    }

    List<com.airbnb.lottie.x.a<Float>> c() {
        return this.t;
    }

    public a d() {
        return this.e;
    }

    List<com.airbnb.lottie.t.k.g> e() {
        return this.h;
    }

    b f() {
        return this.u;
    }

    String g() {
        return this.f890c;
    }

    long h() {
        return this.f;
    }

    int i() {
        return this.p;
    }

    int j() {
        return this.o;
    }

    @Nullable
    String k() {
        return this.g;
    }

    List<com.airbnb.lottie.t.k.b> l() {
        return this.f888a;
    }

    int m() {
        return this.l;
    }

    int n() {
        return this.k;
    }

    int o() {
        return this.j;
    }

    float p() {
        return this.n / this.f889b.e();
    }

    @Nullable
    j q() {
        return this.q;
    }

    @Nullable
    k r() {
        return this.r;
    }

    @Nullable
    com.airbnb.lottie.t.j.b s() {
        return this.s;
    }

    float t() {
        return this.m;
    }

    public String toString() {
        return w("");
    }

    l u() {
        return this.i;
    }

    public boolean v() {
        return this.v;
    }

    public String w(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(g());
        sb.append("\n");
        d dVarS = this.f889b.s(h());
        if (dVarS != null) {
            String str2 = "\t\tParents: ";
            while (true) {
                sb.append(str2);
                sb.append(dVarS.g());
                dVarS = this.f889b.s(dVarS.h());
                if (dVarS == null) {
                    break;
                }
                str2 = "->";
            }
            sb.append(str);
            sb.append("\n");
        }
        if (!e().isEmpty()) {
            sb.append(str);
            sb.append("\tMasks: ");
            sb.append(e().size());
            sb.append("\n");
        }
        if (o() != 0 && n() != 0) {
            sb.append(str);
            sb.append("\tBackground: ");
            sb.append(String.format(Locale.US, "%dx%d %X\n", Integer.valueOf(o()), Integer.valueOf(n()), Integer.valueOf(m())));
        }
        if (!this.f888a.isEmpty()) {
            sb.append(str);
            sb.append("\tShapes:\n");
            for (com.airbnb.lottie.t.k.b bVar : this.f888a) {
                sb.append(str);
                sb.append("\t\t");
                sb.append(bVar);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
