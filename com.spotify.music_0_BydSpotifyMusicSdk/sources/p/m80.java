package p;

import android.graphics.Bitmap;
import coil.request.CachePolicy;
import coil.size.Precision;
import coil.size.Scale;

/* JADX INFO: loaded from: classes.dex */
public final class m80 {
    public final l91 a;
    public final rd2 b;
    public final Scale c;
    public final kotlinx.coroutines.b d;
    public final kotlinx.coroutines.b e;
    public final kotlinx.coroutines.b f;
    public final kotlinx.coroutines.b g;
    public final un2 h;
    public final Precision i;
    public final Bitmap.Config j;
    public final Boolean k;
    public final Boolean l;
    public final CachePolicy m;
    public final CachePolicy n;
    public final CachePolicy o;

    public m80(l91 l91Var, rd2 rd2Var, Scale scale, kotlinx.coroutines.b bVar, kotlinx.coroutines.b bVar2, kotlinx.coroutines.b bVar3, kotlinx.coroutines.b bVar4, un2 un2Var, Precision precision, Bitmap.Config config, Boolean bool, Boolean bool2, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3) {
        this.a = l91Var;
        this.b = rd2Var;
        this.c = scale;
        this.d = bVar;
        this.e = bVar2;
        this.f = bVar3;
        this.g = bVar4;
        this.h = un2Var;
        this.i = precision;
        this.j = config;
        this.k = bool;
        this.l = bool2;
        this.m = cachePolicy;
        this.n = cachePolicy2;
        this.o = cachePolicy3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof m80) {
            m80 m80Var = (m80) obj;
            if (v03.b(this.a, m80Var.a) && v03.b(this.b, m80Var.b) && this.c == m80Var.c && v03.b(this.d, m80Var.d) && v03.b(this.e, m80Var.e) && v03.b(this.f, m80Var.f) && v03.b(this.g, m80Var.g) && v03.b(this.h, m80Var.h) && this.i == m80Var.i && this.j == m80Var.j && v03.b(this.k, m80Var.k) && v03.b(this.l, m80Var.l) && this.m == m80Var.m && this.n == m80Var.n && this.o == m80Var.o) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        l91 l91Var = this.a;
        int iHashCode = (l91Var != null ? l91Var.hashCode() : 0) * 31;
        rd2 rd2Var = this.b;
        int iHashCode2 = (iHashCode + (rd2Var != null ? rd2Var.hashCode() : 0)) * 31;
        Scale scale = this.c;
        int iHashCode3 = (iHashCode2 + (scale != null ? scale.hashCode() : 0)) * 31;
        kotlinx.coroutines.b bVar = this.d;
        int iHashCode4 = (iHashCode3 + (bVar != null ? bVar.hashCode() : 0)) * 31;
        kotlinx.coroutines.b bVar2 = this.e;
        int iHashCode5 = (iHashCode4 + (bVar2 != null ? bVar2.hashCode() : 0)) * 31;
        kotlinx.coroutines.b bVar3 = this.f;
        int iHashCode6 = (iHashCode5 + (bVar3 != null ? bVar3.hashCode() : 0)) * 31;
        kotlinx.coroutines.b bVar4 = this.g;
        int iHashCode7 = (((iHashCode6 + (bVar4 != null ? bVar4.hashCode() : 0)) * 31) + (this.h != null ? tn1.class.hashCode() : 0)) * 31;
        Precision precision = this.i;
        int iHashCode8 = (iHashCode7 + (precision != null ? precision.hashCode() : 0)) * 31;
        Bitmap.Config config = this.j;
        int iHashCode9 = (iHashCode8 + (config != null ? config.hashCode() : 0)) * 31;
        Boolean bool = this.k;
        int iHashCode10 = (iHashCode9 + (bool != null ? bool.hashCode() : 0)) * 31;
        Boolean bool2 = this.l;
        int iHashCode11 = (iHashCode10 + (bool2 != null ? bool2.hashCode() : 0)) * 31;
        CachePolicy cachePolicy = this.m;
        int iHashCode12 = (iHashCode11 + (cachePolicy != null ? cachePolicy.hashCode() : 0)) * 31;
        CachePolicy cachePolicy2 = this.n;
        int iHashCode13 = (iHashCode12 + (cachePolicy2 != null ? cachePolicy2.hashCode() : 0)) * 31;
        CachePolicy cachePolicy3 = this.o;
        return iHashCode13 + (cachePolicy3 != null ? cachePolicy3.hashCode() : 0);
    }
}
