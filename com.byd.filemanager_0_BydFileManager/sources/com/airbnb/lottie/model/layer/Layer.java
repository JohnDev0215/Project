package com.airbnb.lottie.model.layer;

import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.i.j;
import com.airbnb.lottie.model.i.k;
import com.airbnb.lottie.model.i.l;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class Layer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<com.airbnb.lottie.model.content.b> f2624a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.airbnb.lottie.d f2625b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f2626c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final long f2627d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final LayerType f2628e;
    private final long f;
    private final String g;
    private final List<Mask> h;
    private final l i;
    private final int j;
    private final int k;
    private final int l;
    private final float m;
    private final float n;
    private final int o;
    private final int p;
    private final j q;
    private final k r;
    private final com.airbnb.lottie.model.i.b s;
    private final List<com.airbnb.lottie.v.a<Float>> t;
    private final MatteType u;
    private final boolean v;

    public enum LayerType {
        PRE_COMP,
        SOLID,
        IMAGE,
        NULL,
        SHAPE,
        TEXT,
        UNKNOWN
    }

    public enum MatteType {
        NONE,
        ADD,
        INVERT,
        UNKNOWN
    }

    public Layer(List<com.airbnb.lottie.model.content.b> list, com.airbnb.lottie.d dVar, String str, long j, LayerType layerType, long j2, String str2, List<Mask> list2, l lVar, int i, int i2, int i3, float f, float f2, int i4, int i5, j jVar, k kVar, List<com.airbnb.lottie.v.a<Float>> list3, MatteType matteType, com.airbnb.lottie.model.i.b bVar, boolean z) {
        this.f2624a = list;
        this.f2625b = dVar;
        this.f2626c = str;
        this.f2627d = j;
        this.f2628e = layerType;
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
        this.u = matteType;
        this.s = bVar;
        this.v = z;
    }

    com.airbnb.lottie.d a() {
        return this.f2625b;
    }

    public long b() {
        return this.f2627d;
    }

    List<com.airbnb.lottie.v.a<Float>> c() {
        return this.t;
    }

    public LayerType d() {
        return this.f2628e;
    }

    List<Mask> e() {
        return this.h;
    }

    MatteType f() {
        return this.u;
    }

    String g() {
        return this.f2626c;
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

    String k() {
        return this.g;
    }

    List<com.airbnb.lottie.model.content.b> l() {
        return this.f2624a;
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
        return this.n / this.f2625b.e();
    }

    j q() {
        return this.q;
    }

    k r() {
        return this.r;
    }

    com.airbnb.lottie.model.i.b s() {
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
        Layer layerS = this.f2625b.s(h());
        if (layerS != null) {
            sb.append("\t\tParents: ");
            sb.append(layerS.g());
            Layer layerS2 = this.f2625b.s(layerS.h());
            while (layerS2 != null) {
                sb.append("->");
                sb.append(layerS2.g());
                layerS2 = this.f2625b.s(layerS2.h());
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
        if (!this.f2624a.isEmpty()) {
            sb.append(str);
            sb.append("\tShapes:\n");
            for (com.airbnb.lottie.model.content.b bVar : this.f2624a) {
                sb.append(str);
                sb.append("\t\t");
                sb.append(bVar);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
