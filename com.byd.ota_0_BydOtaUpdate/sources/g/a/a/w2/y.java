package g.a.a.w2;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class y {
    public boolean F;
    public g.a.a.n G;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g.a.a.m f4154a = new g.a.a.m("2.5.29.9");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final g.a.a.m f4155b = new g.a.a.m("2.5.29.14");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g.a.a.m f4156c = new g.a.a.m("2.5.29.15");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final g.a.a.m f4157d = new g.a.a.m("2.5.29.16");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final g.a.a.m f4158e = new g.a.a.m("2.5.29.17");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final g.a.a.m f4159f = new g.a.a.m("2.5.29.18");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final g.a.a.m f4160g = new g.a.a.m("2.5.29.19");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final g.a.a.m f4161h = new g.a.a.m("2.5.29.20");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final g.a.a.m f4162i = new g.a.a.m("2.5.29.21");
    public static final g.a.a.m j = new g.a.a.m("2.5.29.23");
    public static final g.a.a.m k = new g.a.a.m("2.5.29.24");
    public static final g.a.a.m l = new g.a.a.m("2.5.29.27");
    public static final g.a.a.m m = new g.a.a.m("2.5.29.28");
    public static final g.a.a.m n = new g.a.a.m("2.5.29.29");
    public static final g.a.a.m o = new g.a.a.m("2.5.29.30");
    public static final g.a.a.m p = new g.a.a.m("2.5.29.31");
    public static final g.a.a.m q = new g.a.a.m("2.5.29.32");
    public static final g.a.a.m r = new g.a.a.m("2.5.29.33");
    public static final g.a.a.m s = new g.a.a.m("2.5.29.35");
    public static final g.a.a.m t = new g.a.a.m("2.5.29.36");
    public static final g.a.a.m u = new g.a.a.m("2.5.29.37");
    public static final g.a.a.m v = new g.a.a.m("2.5.29.46");
    public static final g.a.a.m w = new g.a.a.m("2.5.29.54");
    public static final g.a.a.m x = new g.a.a.m("1.3.6.1.5.5.7.1.1");
    public static final g.a.a.m y = new g.a.a.m("1.3.6.1.5.5.7.1.11");
    public static final g.a.a.m z = new g.a.a.m("1.3.6.1.5.5.7.1.12");
    public static final g.a.a.m A = new g.a.a.m("1.3.6.1.5.5.7.1.2");
    public static final g.a.a.m B = new g.a.a.m("1.3.6.1.5.5.7.1.3");
    public static final g.a.a.m C = new g.a.a.m("1.3.6.1.5.5.7.1.4");
    public static final g.a.a.m D = new g.a.a.m("2.5.29.56");
    public static final g.a.a.m E = new g.a.a.m("2.5.29.55");

    public static g.a.a.q a(y yVar) throws IllegalArgumentException {
        try {
            return g.a.a.q.j(yVar.b().p());
        } catch (IOException e2) {
            throw new IllegalArgumentException("can't convert extension: " + e2);
        }
    }

    public g.a.a.n b() {
        return this.G;
    }

    public boolean c() {
        return this.F;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        return yVar.b().equals(b()) && yVar.c() == c();
    }

    public int hashCode() {
        return c() ? b().hashCode() : ~b().hashCode();
    }
}
