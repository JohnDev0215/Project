package defpackage;

import J.N;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import defpackage.wm3;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$dimen;

/* JADX INFO: compiled from: IPHCommandBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hw0 {
    public static final gg3 s = new gg3(12);
    public final Resources a;
    public final String b;
    public String c;
    public String d;
    public final int f;
    public final Object[] g;
    public final int h;
    public final Object[] i;
    public View j;
    public Runnable k;
    public Runnable l;
    public Runnable m;
    public Rect n;
    public nn3 p;
    public wm3.a q;
    public boolean e = true;
    public long o = 0;
    public int r = 0;

    public hw0(Resources resources, String str, int i, int i2) {
        this.a = resources;
        this.b = str;
        this.f = i;
        this.h = i2;
    }

    public final gw0 a() {
        sk skVar = bq.a;
        boolean zM09VlOh_ = N.M09VlOh_("AndroidScrollOptimizations");
        gg3 gg3Var = s;
        if (zM09VlOh_) {
            TraceEvent traceEventH0 = TraceEvent.H0("IPHCommandBuilder::buildLazy", null);
            try {
                if (this.m == null) {
                    this.m = gg3Var;
                }
                if (this.k == null) {
                    this.k = gg3Var;
                }
                if (this.l == null) {
                    this.l = gg3Var;
                }
                gw0 gw0Var = new gw0(this.a, this.b, this.f, this.g, this.h, this.i, this.e, this.j, this.m, this.k, this.l, this.o, this.p, this.q, this.r);
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return gw0Var;
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        }
        TraceEvent traceEventH02 = TraceEvent.H0("IPHCommandBuilder::build", null);
        try {
            if (this.m == null) {
                this.m = gg3Var;
            }
            if (this.k == null) {
                this.k = gg3Var;
            }
            if (this.l == null) {
                this.l = gg3Var;
            }
            String str = this.c;
            Resources resources = this.a;
            if (str == null) {
                Object[] objArr = this.g;
                int i = this.f;
                if (objArr != null) {
                    this.c = resources.getString(i, objArr);
                } else {
                    this.c = resources.getString(i);
                }
            }
            if (this.d == null) {
                Object[] objArr2 = this.i;
                int i2 = this.h;
                if (objArr2 != null) {
                    this.d = resources.getString(i2, objArr2);
                } else {
                    this.d = resources.getString(i2);
                }
            }
            if (this.n == null) {
                this.n = new Rect(0, 0, 0, resources.getDimensionPixelOffset(R$dimen.iph_text_bubble_menu_anchor_y_inset));
            }
            gw0 gw0Var2 = new gw0(this.b, this.c, this.d, this.e, this.j, this.m, this.k, this.l, this.n, this.o, this.p, this.q, this.r);
            if (traceEventH02 != null) {
                traceEventH02.close();
            }
            return gw0Var2;
        } catch (Throwable th2) {
            if (traceEventH02 != null) {
                try {
                    traceEventH02.close();
                } catch (Throwable unused2) {
                }
            }
            throw th2;
        }
    }

    public final void b(View view) {
        this.j = view;
    }

    public final void c(wq0 wq0Var) {
        this.m = wq0Var;
    }

    public final void d(aw awVar) {
        this.k = awVar;
    }

    public hw0(Resources resources, String str, int i, Object[] objArr, int i2, Object[] objArr2) {
        this.a = resources;
        this.b = str;
        this.f = i;
        this.g = objArr;
        this.h = i2;
        this.i = objArr2;
    }
}
