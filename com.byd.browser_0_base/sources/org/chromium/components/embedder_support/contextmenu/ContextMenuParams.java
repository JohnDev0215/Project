package org.chromium.components.embedder_support.contextmenu;

import defpackage.on2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class ContextMenuParams {
    public final long a;
    public final GURL b;
    public final GURL c;
    public final String d;
    public final String e;
    public final GURL f;
    public final GURL g;
    public final on2 h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final int m;
    public final int n;
    public final int o;
    public final boolean p;

    public ContextMenuParams(long j, int i, GURL gurl, GURL gurl2, String str, GURL gurl3, GURL gurl4, String str2, on2 on2Var, boolean z, int i2, int i3, int i4, boolean z2) {
        this.a = j;
        this.b = gurl;
        this.c = gurl2;
        this.d = str;
        this.e = str2;
        this.f = gurl3;
        this.g = gurl4;
        this.h = on2Var;
        this.i = !gurl2.k();
        this.j = i == 1;
        this.k = i == 2;
        this.l = z;
        this.m = i2;
        this.n = i3;
        this.o = i4;
        this.p = z2;
    }

    @CalledByNative
    public static ContextMenuParams create(long j, int i, GURL gurl, GURL gurl2, String str, GURL gurl3, GURL gurl4, String str2, GURL gurl5, int i2, boolean z, int i3, int i4, int i5, boolean z2) {
        return new ContextMenuParams(j, i, gurl, gurl2, str, gurl3, gurl4, str2, gurl5.k() ? null : new on2(gurl5.j(), i2), z, i3, i4, i5, z2);
    }

    public final GURL a() {
        if (this.i) {
            GURL gurl = this.c;
            if (!gurl.k()) {
                return gurl;
            }
        }
        return this.g;
    }

    @CalledByNative
    public final long getNativePointer() {
        return this.a;
    }
}
