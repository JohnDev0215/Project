package p;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import coil.request.CachePolicy;
import coil.size.Precision;
import coil.size.Scale;
import java.util.Arrays;
import java.util.List;
import kotlin.Pair;
import okhttp3.Headers;

/* JADX INFO: loaded from: classes.dex */
public final class q31 {
    public final l91 A;
    public final rd2 B;
    public final Scale C;
    public final bs1 D;
    public final mi1 E;
    public final Integer F;
    public final Drawable G;
    public final Integer H;
    public final Drawable I;
    public final Integer J;
    public final Drawable K;
    public final m80 L;
    public final p70 M;
    public final Context a;
    public final Object b;
    public final yi2 c;
    public final p31 d;
    public final mi1 e;
    public final String f;
    public final Bitmap.Config g;
    public final ColorSpace h;
    public final Precision i;
    public final Pair j;
    public final am k;
    public final List l;
    public final un2 m;
    public final Headers n;
    public final xi2 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f170p;
    public final boolean q;
    public final boolean r;
    public final boolean s;
    public final CachePolicy t;
    public final CachePolicy u;
    public final CachePolicy v;
    public final kotlinx.coroutines.b w;
    public final kotlinx.coroutines.b x;
    public final kotlinx.coroutines.b y;
    public final kotlinx.coroutines.b z;

    public q31(Context context, Object obj, yi2 yi2Var, p31 p31Var, mi1 mi1Var, String str, Bitmap.Config config, ColorSpace colorSpace, Precision precision, Pair pair, am amVar, List list, un2 un2Var, Headers headers, xi2 xi2Var, boolean z, boolean z2, boolean z3, boolean z4, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3, kotlinx.coroutines.b bVar, kotlinx.coroutines.b bVar2, kotlinx.coroutines.b bVar3, kotlinx.coroutines.b bVar4, l91 l91Var, rd2 rd2Var, Scale scale, bs1 bs1Var, mi1 mi1Var2, Integer num, Drawable drawable, Integer num2, Drawable drawable2, Integer num3, Drawable drawable3, m80 m80Var, p70 p70Var) {
        this.a = context;
        this.b = obj;
        this.c = yi2Var;
        this.d = p31Var;
        this.e = mi1Var;
        this.f = str;
        this.g = config;
        this.h = colorSpace;
        this.i = precision;
        this.j = pair;
        this.k = amVar;
        this.l = list;
        this.m = un2Var;
        this.n = headers;
        this.o = xi2Var;
        this.f170p = z;
        this.q = z2;
        this.r = z3;
        this.s = z4;
        this.t = cachePolicy;
        this.u = cachePolicy2;
        this.v = cachePolicy3;
        this.w = bVar;
        this.x = bVar2;
        this.y = bVar3;
        this.z = bVar4;
        this.A = l91Var;
        this.B = rd2Var;
        this.C = scale;
        this.D = bs1Var;
        this.E = mi1Var2;
        this.F = num;
        this.G = drawable;
        this.H = num2;
        this.I = drawable2;
        this.J = num3;
        this.K = drawable3;
        this.L = m80Var;
        this.M = p70Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof q31) {
            q31 q31Var = (q31) obj;
            if (v03.b(this.a, q31Var.a) && v03.b(this.b, q31Var.b) && v03.b(this.c, q31Var.c) && v03.b(this.d, q31Var.d) && v03.b(this.e, q31Var.e) && v03.b(this.f, q31Var.f) && this.g == q31Var.g && v03.b(this.h, q31Var.h) && this.i == q31Var.i && v03.b(this.j, q31Var.j) && v03.b(this.k, q31Var.k) && v03.b(this.l, q31Var.l) && v03.b(this.m, q31Var.m) && v03.b(this.n, q31Var.n) && v03.b(this.o, q31Var.o) && this.f170p == q31Var.f170p && this.q == q31Var.q && this.r == q31Var.r && this.s == q31Var.s && this.t == q31Var.t && this.u == q31Var.u && this.v == q31Var.v && v03.b(this.w, q31Var.w) && v03.b(this.x, q31Var.x) && v03.b(this.y, q31Var.y) && v03.b(this.z, q31Var.z) && v03.b(this.E, q31Var.E) && v03.b(this.F, q31Var.F) && v03.b(this.G, q31Var.G) && v03.b(this.H, q31Var.H) && v03.b(this.I, q31Var.I) && v03.b(this.J, q31Var.J) && v03.b(this.K, q31Var.K) && v03.b(this.A, q31Var.A) && v03.b(this.B, q31Var.B) && this.C == q31Var.C && v03.b(this.D, q31Var.D) && v03.b(this.L, q31Var.L) && v03.b(this.M, q31Var.M)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.b.hashCode() + (this.a.hashCode() * 31)) * 31;
        yi2 yi2Var = this.c;
        int iHashCode2 = (iHashCode + (yi2Var != null ? yi2Var.hashCode() : 0)) * 31;
        p31 p31Var = this.d;
        int iHashCode3 = (iHashCode2 + (p31Var != null ? p31Var.hashCode() : 0)) * 31;
        mi1 mi1Var = this.e;
        int iHashCode4 = (iHashCode3 + (mi1Var != null ? mi1Var.hashCode() : 0)) * 31;
        String str = this.f;
        int iHashCode5 = (this.g.hashCode() + ((iHashCode4 + (str != null ? str.hashCode() : 0)) * 31)) * 31;
        ColorSpace colorSpace = this.h;
        int iHashCode6 = (this.i.hashCode() + ((iHashCode5 + (colorSpace != null ? colorSpace.hashCode() : 0)) * 31)) * 31;
        Pair pair = this.j;
        int iHashCode7 = (this.l.hashCode() + ((((iHashCode6 + (pair != null ? pair.hashCode() : 0)) * 31) + (this.k != null ? am.class.hashCode() : 0)) * 31)) * 31;
        ((tn1) this.m).getClass();
        int iHashCode8 = (this.D.c.hashCode() + ((this.C.hashCode() + ((this.B.hashCode() + ((this.A.hashCode() + ((this.z.hashCode() + ((this.y.hashCode() + ((this.x.hashCode() + ((this.w.hashCode() + ((this.v.hashCode() + ((this.u.hashCode() + ((this.t.hashCode() + ((Boolean.hashCode(this.s) + ((Boolean.hashCode(this.r) + ((Boolean.hashCode(this.q) + ((Boolean.hashCode(this.f170p) + ((this.o.a.hashCode() + ((((tn1.class.hashCode() + iHashCode7) * 31) + Arrays.hashCode(this.n.c)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        mi1 mi1Var2 = this.E;
        int iHashCode9 = (iHashCode8 + (mi1Var2 != null ? mi1Var2.hashCode() : 0)) * 31;
        Integer num = this.F;
        int iHashCode10 = (iHashCode9 + (num != null ? num.hashCode() : 0)) * 31;
        Drawable drawable = this.G;
        int iHashCode11 = (iHashCode10 + (drawable != null ? drawable.hashCode() : 0)) * 31;
        Integer num2 = this.H;
        int iHashCode12 = (iHashCode11 + (num2 != null ? num2.hashCode() : 0)) * 31;
        Drawable drawable2 = this.I;
        int iHashCode13 = (iHashCode12 + (drawable2 != null ? drawable2.hashCode() : 0)) * 31;
        Integer num3 = this.J;
        int iHashCode14 = (iHashCode13 + (num3 != null ? num3.hashCode() : 0)) * 31;
        Drawable drawable3 = this.K;
        return this.M.hashCode() + ((this.L.hashCode() + ((iHashCode14 + (drawable3 != null ? drawable3.hashCode() : 0)) * 31)) * 31);
    }
}
