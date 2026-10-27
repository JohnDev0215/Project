package defpackage;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import defpackage.wm3;

/* JADX INFO: compiled from: IPHCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gw0 {
    public final Resources a;
    public final String b;
    public final int c;
    public final Object[] d;
    public String e;
    public final int f;
    public final Object[] g;
    public String h;
    public final boolean i;
    public final View j;
    public final Runnable k;
    public final Runnable l;
    public final Runnable m;
    public Rect n;
    public final long o;
    public final nn3 p;
    public final wm3.a q;
    public final Rect r;
    public final boolean s;
    public final int t;

    public gw0(Resources resources, String str, int i, Object[] objArr, int i2, Object[] objArr2, boolean z, View view, Runnable runnable, Runnable runnable2, Runnable runnable3, long j, nn3 nn3Var, wm3.a aVar, int i3) {
        this.a = resources;
        this.b = str;
        this.c = i;
        this.d = objArr;
        this.f = i2;
        this.g = objArr2;
        this.i = z;
        this.j = view;
        this.k = runnable;
        this.l = runnable2;
        this.m = runnable3;
        this.o = j;
        this.p = nn3Var;
        this.q = aVar;
        this.r = null;
        this.s = false;
        this.t = i3;
    }

    public gw0(String str, String str2, String str3, boolean z, View view, Runnable runnable, Runnable runnable2, Runnable runnable3, Rect rect, long j, nn3 nn3Var, wm3.a aVar, int i) {
        this.c = 0;
        this.f = 0;
        this.b = str;
        this.e = str2;
        this.h = str3;
        this.i = z;
        this.j = view;
        this.k = runnable;
        this.l = runnable2;
        this.m = runnable3;
        this.n = rect;
        this.o = j;
        this.p = nn3Var;
        this.q = aVar;
        this.r = null;
        this.s = false;
        this.t = i;
    }
}
