package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.RectF;
import android.util.FloatProperty;
import android.util.SparseArray;
import defpackage.vu2;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: StripLayoutTab.java */
/* JADX INFO: loaded from: classes.dex */
public final class fv2 implements mo3 {
    public static final a C = new a();
    public static final b D = new b();
    public static final c E = new c();
    public static final d F = new d();
    public static final e G = new e();
    public static final g H;
    public final int a;
    public final j b;
    public final j13 c;
    public final j61 d;
    public final n61 e;
    public final ya3 f;
    public boolean h;
    public float k;
    public float l;
    public String n;
    public float o;
    public float p;
    public float q;
    public float r;
    public float s;
    public float t;
    public float u;
    public float v;
    public kw y;
    public boolean g = true;
    public boolean i = true;
    public boolean j = true;
    public float m = 1.0f;
    public final RectF w = new RectF();
    public boolean x = true;
    public float z = 1.0f;
    public final RectF A = new RectF();
    public final org.chromium.base.b<i> B = new org.chromium.base.b<>();

    /* JADX INFO: compiled from: StripLayoutTab.java */
    public class a extends FloatProperty<fv2> {
        public a() {
            super("offsetX");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((fv2) obj).p);
        }

        @Override // android.util.FloatProperty
        public final void setValue(fv2 fv2Var, float f) {
            fv2Var.p = f;
        }
    }

    /* JADX INFO: compiled from: StripLayoutTab.java */
    public class b extends FloatProperty<fv2> {
        public b() {
            super("offsetY");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((fv2) obj).q);
        }

        @Override // android.util.FloatProperty
        public final void setValue(fv2 fv2Var, float f) {
            fv2Var.q = f;
        }
    }

    /* JADX INFO: compiled from: StripLayoutTab.java */
    public class c extends FloatProperty<fv2> {
        public c() {
            super("width");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((fv2) obj).v);
        }

        @Override // android.util.FloatProperty
        public final void setValue(fv2 fv2Var, float f) {
            fv2 fv2Var2 = fv2Var;
            fv2Var2.u = f;
            fv2Var2.j();
            fv2Var2.w.right = fv2Var2.s + fv2Var2.u;
        }
    }

    /* JADX INFO: compiled from: StripLayoutTab.java */
    public class d extends FloatProperty<fv2> {
        public d() {
            super("drawX");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((fv2) obj).s);
        }

        @Override // android.util.FloatProperty
        public final void setValue(fv2 fv2Var, float f) {
            fv2Var.k(f);
        }
    }

    /* JADX INFO: compiled from: StripLayoutTab.java */
    public class e extends FloatProperty<fv2> {
        public e() {
            super("bottomMargin");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((fv2) obj).k);
        }

        @Override // android.util.FloatProperty
        public final void setValue(fv2 fv2Var, float f) {
            fv2Var.k = f;
        }
    }

    /* JADX INFO: compiled from: StripLayoutTab.java */
    public class f extends FloatProperty<fv2> {
        public f() {
            super("trailingMargin");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((fv2) obj).r);
        }

        @Override // android.util.FloatProperty
        public final void setValue(fv2 fv2Var, float f) {
            fv2Var.r = f;
        }
    }

    /* JADX INFO: compiled from: StripLayoutTab.java */
    public class g extends FloatProperty<fv2> {
        public g() {
            super("brightness");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((fv2) obj).z);
        }

        @Override // android.util.FloatProperty
        public final void setValue(fv2 fv2Var, float f) {
            fv2Var.z = f;
        }
    }

    /* JADX INFO: compiled from: StripLayoutTab.java */
    public class h extends AnimatorListenerAdapter {
        public h() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            fv2.this.y = null;
        }
    }

    /* JADX INFO: compiled from: StripLayoutTab.java */
    public interface i {
        void a();
    }

    /* JADX INFO: compiled from: StripLayoutTab.java */
    public interface j {
    }

    static {
        new f();
        H = new g();
    }

    public fv2(Context context, int i2, j jVar, vu2.f fVar, j61 j61Var, n61 n61Var, boolean z) {
        this.a = -1;
        this.a = i2;
        this.b = jVar;
        this.c = new j13(i2, fVar);
        this.d = j61Var;
        this.e = n61Var;
        ya3 ya3Var = new ya3(context, 0.0f, 0.0f, new gv2(this));
        this.f = ya3Var;
        ya3Var.g(R$color.default_icon_color_tint_list, R$color.default_icon_color_accent1_tint_list, R$color.default_icon_color_light, R$color.modern_blue_300);
        ya3Var.h = z;
        ya3Var.a.set(g());
        ya3Var.e = 0.0f;
    }

    @Override // defpackage.mo3
    public final void a(long j2) {
        vu2 vu2Var = (vu2) this.b;
        vu2Var.getClass();
        if (this.h) {
            return;
        }
        vu2Var.c.q(z7.G(vu2Var.c, this.a), 3, false);
    }

    @Override // defpackage.mo3
    public final void b(RectF rectF) {
        rectF.set(this.w);
    }

    @Override // defpackage.mo3
    public final boolean c(float f2, float f3) {
        if (f(f2, f3)) {
            return false;
        }
        return this.w.contains(f2, f3);
    }

    @Override // defpackage.mo3
    public final String d() {
        return this.n;
    }

    public final void e(boolean z) {
        boolean z2 = this.i && this.m > 0.99f;
        if (z2 != this.x) {
            float f2 = z2 ? 1.0f : 0.0f;
            ya3 ya3Var = this.f;
            if (z) {
                kw kwVar = this.y;
                if (kwVar != null) {
                    kwVar.end();
                }
                kw kwVarG = kw.g(((h61) this.e).G, ya3Var, lw.l, ya3Var.d, f2, 150L);
                this.y = kwVarG;
                kwVarG.addListener(new h());
                this.y.start();
            } else {
                ya3Var.d = f2;
            }
            this.x = z2;
            if (z2) {
                return;
            }
            ya3Var.f = false;
        }
    }

    public final boolean f(float f2, float f3) {
        if (this.x) {
            return this.f.c(f2, f3);
        }
        return false;
    }

    public final RectF g() {
        ResourceManager resourceManager;
        boolean zA = bq.e0.a();
        int i2 = zA ? 48 : 36;
        boolean zIsLayoutRtl = LocalizationUtils.isLayoutRtl();
        RectF rectF = this.A;
        float f2 = 0.0f;
        if (zIsLayoutRtl) {
            rectF.left = 0.0f;
            rectF.right = i2;
        } else {
            float f3 = i2;
            float f4 = this.u - f3;
            rectF.left = f4;
            rectF.right = f4 + f3;
        }
        rectF.top = 0.0f;
        rectF.bottom = this.v;
        if (!zA && (resourceManager = this.d.getResourceManager()) != null) {
            i41 i41Var = ar.d;
            StringBuilder sb = new StringBuilder();
            sb.append("TabStripRedesign");
            sb.append(":");
            sb.append("enable_detached");
            int i3 = (CachedFeatureFlags.b(i41Var.b(sb.toString()), true) || !this.j) ? R$drawable.bg_tabstrip_tab_detached : oj2.F() ? R$drawable.bg_tabstrip_tab_folio : R$drawable.bg_tabstrip_tab;
            SparseArray<k61> sparseArray = resourceManager.b.get(0);
            k61 k61Var = sparseArray != null ? sparseArray.get(i3) : null;
            if (k61Var != null) {
                boolean zIsLayoutRtl2 = LocalizationUtils.isLayoutRtl();
                RectF rectF2 = k61Var.a;
                f2 = zIsLayoutRtl2 ? rectF2.left : -(k61Var.b.width() - rectF2.right);
            }
        }
        rectF.offset(this.s + f2, this.t);
        return rectF;
    }

    public final float h() {
        if (bq.f0.a()) {
            return this.l;
        }
        return 1.0f;
    }

    public final float i() {
        return a8.o(1.0f - (this.t / this.v), 0.0f, 1.0f);
    }

    public final void j() {
        RectF rectFG = g();
        float fWidth = rectFG.width();
        ya3 ya3Var = this.f;
        RectF rectF = ya3Var.a;
        rectF.right = rectF.left + fWidth;
        float fHeight = rectFG.height();
        RectF rectF2 = ya3Var.a;
        rectF2.bottom = rectF2.top + fHeight;
        ya3Var.e(rectFG.left);
        ya3Var.f(rectFG.top);
    }

    public final void k(float f2) {
        ya3 ya3Var = this.f;
        ya3Var.e((f2 - this.s) + ya3Var.a.left);
        this.s = f2;
        RectF rectF = this.w;
        rectF.left = f2;
        rectF.right = f2 + this.u;
    }

    public final void l(boolean z) {
        this.g = z;
        if (!z) {
            this.e.l(this.a);
        }
        Iterator<i> it = this.B.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((i) aVar.next()).a();
            }
        }
    }
}
