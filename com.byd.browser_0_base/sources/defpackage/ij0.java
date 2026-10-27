package defpackage;

import org.chromium.base.Callback;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ExternalNavigationParams.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ij0 {
    public final GURL a;
    public final boolean b;
    public final GURL c;
    public final int d;
    public final boolean e;
    public final boolean f;
    public final aa2 g;
    public final boolean h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final String l;
    public final boolean m;
    public final boolean n;
    public final boolean o;
    public final boolean p;
    public final Callback<a> q;
    public final boolean r;
    public lb2<a> s;

    public ij0(GURL gurl, boolean z, GURL gurl2, int i, boolean z2, boolean z3, aa2 aa2Var, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, Callback callback, boolean z9, boolean z10, boolean z11, boolean z12) {
        this.a = gurl;
        this.b = z;
        this.d = i;
        this.c = gurl2 == null ? GURL.emptyGURL() : gurl2;
        this.e = z2;
        this.f = z3;
        this.g = aa2Var;
        this.h = z4;
        this.i = z5;
        this.j = z6;
        this.k = z7;
        this.l = null;
        this.m = z8;
        this.q = callback;
        this.r = z9;
        this.n = z10;
        this.o = z11;
        this.p = z12;
    }

    /* JADX INFO: compiled from: ExternalNavigationParams.java */
    public static class a {
        public final int a = 0;
        public final boolean b;
        public final ij0 c;
        public final GURL d;

        public a() {
        }

        public a(GURL gurl, ij0 ij0Var) {
            this.d = gurl;
            this.c = ij0Var;
        }

        public a(ij0 ij0Var, boolean z) {
            this.b = z;
            this.c = ij0Var;
        }
    }
}
