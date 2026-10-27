package defpackage;

import android.app.Activity;
import android.content.Context;
import java.util.function.BooleanSupplier;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: NativePageFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lk1 {
    public final Activity a;
    public final ji b;
    public final fj c;
    public final nw2<Tab> d;
    public final nw2<wp2> e;
    public final q2 f;
    public final y13 g;
    public final nw2<ck2> h;
    public final WindowAndroid i;
    public final nw2<Long> j;
    public final BooleanSupplier k;
    public final i31 l;
    public final nw2<qb3> m;
    public um1 n;
    public a o;

    /* JADX INFO: compiled from: NativePageFactory.java */
    public static class a {
        public final Activity a;
        public final ji b;
        public final nw2<um1> c;
        public final fj d;
        public final nw2<Tab> e;
        public final nw2<wp2> f;
        public final q2 g;
        public final y13 h;
        public final nw2<ck2> i;
        public final WindowAndroid j;
        public final i31 k;
        public final nw2<qb3> l;

        public a(Activity activity, yo yoVar, ji jiVar, fj fjVar, nw2 nw2Var, nw2 nw2Var2, q2 q2Var, y13 y13Var, nw2 nw2Var3, WindowAndroid windowAndroid, i31 i31Var, nw2 nw2Var4) {
            this.a = activity;
            this.c = yoVar;
            this.b = jiVar;
            this.d = fjVar;
            this.e = nw2Var;
            this.f = nw2Var2;
            this.g = q2Var;
            this.h = y13Var;
            this.i = nw2Var3;
            this.j = windowAndroid;
            this.k = i31Var;
            this.l = nw2Var4;
        }
    }

    /* JADX INFO: compiled from: NativePageFactory.java */
    public static class b implements mk1 {
        public final Tab a;
        public final kj b;
        public final y13 c;

        public b(Tab tab, fj fjVar, y13 y13Var) {
            this.a = tab;
            this.b = fjVar;
            this.c = y13Var;
        }

        public final Context a() {
            return this.a.getContext();
        }

        public final void b(LoadUrlParams loadUrlParams, boolean z) {
            Tab tab = this.a;
            if (!z || tab.isIncognito()) {
                tab.b(loadUrlParams);
            } else {
                this.c.x(loadUrlParams, 4, tab, true);
            }
        }
    }

    public lk1(Activity activity, ji jiVar, fj fjVar, nw2<Tab> nw2Var, nw2<wp2> nw2Var2, q2 q2Var, y13 y13Var, nw2<ck2> nw2Var3, WindowAndroid windowAndroid, nw2<Long> nw2Var4, BooleanSupplier booleanSupplier, i31 i31Var, nw2<qb3> nw2Var5) {
        this.a = activity;
        this.b = jiVar;
        this.c = fjVar;
        this.d = nw2Var;
        this.e = nw2Var2;
        this.f = q2Var;
        this.g = y13Var;
        this.h = nw2Var3;
        this.i = windowAndroid;
        this.j = nw2Var4;
        this.k = booleanSupplier;
        this.l = i31Var;
        this.m = nw2Var5;
    }

    public final a a() {
        if (this.o == null) {
            this.o = new a(this.a, new yo(10, this), this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.l, this.m);
        }
        return this.o;
    }
}
