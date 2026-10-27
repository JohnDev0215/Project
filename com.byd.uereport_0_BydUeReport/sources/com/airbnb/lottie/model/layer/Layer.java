package com.airbnb.lottie.model.layer;

import b2.a;
import com.airbnb.lottie.model.content.Mask;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.List;
import java.util.Locale;
import o1.e;
import u1.j;
import u1.k;
import u1.l;
import v1.b;

/* JADX INFO: loaded from: classes.dex */
public class Layer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<b> f2492a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f2493b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f2494c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f2495d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LayerType f2496e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f2497f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f2498g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List<Mask> f2499h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final l f2500i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f2501j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f2502k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f2503l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f2504m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float f2505n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f2506o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f2507p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final j f2508q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final k f2509r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final u1.b f2510s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final List<a<Float>> f2511t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final MatteType f2512u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final boolean f2513v;

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

    public Layer(List<b> list, e eVar, String str, long j8, LayerType layerType, long j9, String str2, List<Mask> list2, l lVar, int i8, int i9, int i10, float f8, float f9, int i11, int i12, j jVar, k kVar, List<a<Float>> list3, MatteType matteType, u1.b bVar, boolean z7) {
        this.f2492a = list;
        this.f2493b = eVar;
        this.f2494c = str;
        this.f2495d = j8;
        this.f2496e = layerType;
        this.f2497f = j9;
        this.f2498g = str2;
        this.f2499h = list2;
        this.f2500i = lVar;
        this.f2501j = i8;
        this.f2502k = i9;
        this.f2503l = i10;
        this.f2504m = f8;
        this.f2505n = f9;
        this.f2506o = i11;
        this.f2507p = i12;
        this.f2508q = jVar;
        this.f2509r = kVar;
        this.f2511t = list3;
        this.f2512u = matteType;
        this.f2510s = bVar;
        this.f2513v = z7;
    }

    public e a() {
        return this.f2493b;
    }

    public long b() {
        return this.f2495d;
    }

    public List<a<Float>> c() {
        return this.f2511t;
    }

    public LayerType d() {
        return this.f2496e;
    }

    public List<Mask> e() {
        return this.f2499h;
    }

    public MatteType f() {
        return this.f2512u;
    }

    public String g() {
        return this.f2494c;
    }

    public long h() {
        return this.f2497f;
    }

    public int i() {
        return this.f2507p;
    }

    public int j() {
        return this.f2506o;
    }

    public String k() {
        return this.f2498g;
    }

    public List<b> l() {
        return this.f2492a;
    }

    public int m() {
        return this.f2503l;
    }

    public int n() {
        return this.f2502k;
    }

    public int o() {
        return this.f2501j;
    }

    public float p() {
        return this.f2505n / this.f2493b.e();
    }

    public j q() {
        return this.f2508q;
    }

    public k r() {
        return this.f2509r;
    }

    public u1.b s() {
        return this.f2510s;
    }

    public float t() {
        return this.f2504m;
    }

    public String toString() {
        return w("");
    }

    public l u() {
        return this.f2500i;
    }

    public boolean v() {
        return this.f2513v;
    }

    public String w(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(g());
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        Layer layerS = this.f2493b.s(h());
        if (layerS != null) {
            sb.append("\t\tParents: ");
            sb.append(layerS.g());
            Layer layerS2 = this.f2493b.s(layerS.h());
            while (layerS2 != null) {
                sb.append("->");
                sb.append(layerS2.g());
                layerS2 = this.f2493b.s(layerS2.h());
            }
            sb.append(str);
            sb.append(BydAdbHelper.COMMAND_LINE_END);
        }
        if (!e().isEmpty()) {
            sb.append(str);
            sb.append("\tMasks: ");
            sb.append(e().size());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
        }
        if (o() != 0 && n() != 0) {
            sb.append(str);
            sb.append("\tBackground: ");
            sb.append(String.format(Locale.US, "%dx%d %X\n", Integer.valueOf(o()), Integer.valueOf(n()), Integer.valueOf(m())));
        }
        if (!this.f2492a.isEmpty()) {
            sb.append(str);
            sb.append("\tShapes:\n");
            for (b bVar : this.f2492a) {
                sb.append(str);
                sb.append("\t\t");
                sb.append(bVar);
                sb.append(BydAdbHelper.COMMAND_LINE_END);
            }
        }
        return sb.toString();
    }
}
