package p;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import coil.request.CachePolicy;
import coil.size.Scale;
import java.util.Arrays;
import okhttp3.Headers;

/* JADX INFO: loaded from: classes.dex */
public final class br1 {
    public final Context a;
    public final Bitmap.Config b;
    public final ColorSpace c;
    public final qd2 d;
    public final Scale e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final String i;
    public final Headers j;
    public final xi2 k;
    public final bs1 l;
    public final CachePolicy m;
    public final CachePolicy n;
    public final CachePolicy o;

    public br1(Context context, Bitmap.Config config, ColorSpace colorSpace, qd2 qd2Var, Scale scale, boolean z, boolean z2, boolean z3, String str, Headers headers, xi2 xi2Var, bs1 bs1Var, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3) {
        this.a = context;
        this.b = config;
        this.c = colorSpace;
        this.d = qd2Var;
        this.e = scale;
        this.f = z;
        this.g = z2;
        this.h = z3;
        this.i = str;
        this.j = headers;
        this.k = xi2Var;
        this.l = bs1Var;
        this.m = cachePolicy;
        this.n = cachePolicy2;
        this.o = cachePolicy3;
    }

    public static br1 a(br1 br1Var, Bitmap.Config config) {
        Context context = br1Var.a;
        ColorSpace colorSpace = br1Var.c;
        qd2 qd2Var = br1Var.d;
        Scale scale = br1Var.e;
        boolean z = br1Var.f;
        boolean z2 = br1Var.g;
        boolean z3 = br1Var.h;
        String str = br1Var.i;
        Headers headers = br1Var.j;
        xi2 xi2Var = br1Var.k;
        bs1 bs1Var = br1Var.l;
        CachePolicy cachePolicy = br1Var.m;
        CachePolicy cachePolicy2 = br1Var.n;
        CachePolicy cachePolicy3 = br1Var.o;
        br1Var.getClass();
        return new br1(context, config, colorSpace, qd2Var, scale, z, z2, z3, str, headers, xi2Var, bs1Var, cachePolicy, cachePolicy2, cachePolicy3);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof br1) {
            br1 br1Var = (br1) obj;
            if (v03.b(this.a, br1Var.a) && this.b == br1Var.b && v03.b(this.c, br1Var.c) && v03.b(this.d, br1Var.d) && this.e == br1Var.e && this.f == br1Var.f && this.g == br1Var.g && this.h == br1Var.h && v03.b(this.i, br1Var.i) && v03.b(this.j, br1Var.j) && v03.b(this.k, br1Var.k) && v03.b(this.l, br1Var.l) && this.m == br1Var.m && this.n == br1Var.n && this.o == br1Var.o) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.b.hashCode() + (this.a.hashCode() * 31)) * 31;
        ColorSpace colorSpace = this.c;
        int iHashCode2 = (Boolean.hashCode(this.h) + ((Boolean.hashCode(this.g) + ((Boolean.hashCode(this.f) + ((this.e.hashCode() + ((this.d.hashCode() + ((iHashCode + (colorSpace != null ? colorSpace.hashCode() : 0)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        String str = this.i;
        return this.o.hashCode() + ((this.n.hashCode() + ((this.m.hashCode() + ((this.l.c.hashCode() + ((this.k.a.hashCode() + ((((iHashCode2 + (str != null ? str.hashCode() : 0)) * 31) + Arrays.hashCode(this.j.c)) * 31)) * 31)) * 31)) * 31)) * 31);
    }
}
