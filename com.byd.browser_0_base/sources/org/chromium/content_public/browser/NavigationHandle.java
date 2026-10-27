package org.chromium.content_public.browser;

import defpackage.mk3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* JADX INFO: loaded from: classes3.dex */
public class NavigationHandle {
    public boolean a;
    public boolean b;
    public boolean c;
    public int d;
    public GURL e;
    public GURL f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public int l;
    public int m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public mk3 s;

    @CalledByNative
    public NavigationHandle() {
    }

    @CalledByNative
    public void didFinish(GURL gurl, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, int i2, int i3, boolean z6) {
        this.e = gurl;
        this.i = z;
        this.g = z2;
        this.j = z3;
        this.h = z4;
        this.k = z5;
        this.d = i;
        this.l = i2;
        this.m = i3;
        this.p = z6;
    }

    @CalledByNative
    public void didRedirect(GURL gurl, boolean z) {
        this.e = gurl;
        this.o = true;
        this.p = z;
    }

    @CalledByNative
    public final void initialize(long j, GURL gurl, GURL gurl2, GURL gurl3, boolean z, boolean z2, boolean z3, Origin origin, int i, boolean z4, boolean z5, boolean z6, boolean z7, long j2, boolean z8, boolean z9) {
        this.e = gurl;
        this.f = gurl2;
        this.a = z;
        this.c = z2;
        this.b = z3;
        this.d = i;
        this.n = z5;
        this.o = z6;
        this.p = z7;
        this.q = z8;
        this.r = z9;
    }

    @CalledByNative
    public final void release() {
    }
}
