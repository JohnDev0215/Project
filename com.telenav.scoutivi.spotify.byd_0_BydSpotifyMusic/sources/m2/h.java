package m2;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.Base64;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import m2.b;
import m2.e;
import m2.g;

/* JADX INFO: compiled from: SVGAndroidRenderer.java */
/* JADX INFO: loaded from: classes.dex */
class h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static HashSet<String> f8302i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Canvas f8303a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f8304b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private m2.g f8305c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private C0126h f8306d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Stack<C0126h> f8307e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Stack<g.j0> f8308f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Stack<Matrix> f8309g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private b.q f8310h = null;

    /* JADX INFO: compiled from: SVGAndroidRenderer.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f8311a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f8312b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        static final /* synthetic */ int[] f8313c;

        static {
            int[] iArr = new int[g.e0.d.values().length];
            f8313c = iArr;
            try {
                iArr[g.e0.d.Miter.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8313c[g.e0.d.Round.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8313c[g.e0.d.Bevel.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[g.e0.c.values().length];
            f8312b = iArr2;
            try {
                iArr2[g.e0.c.Butt.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8312b[g.e0.c.Round.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8312b[g.e0.c.Square.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[e.a.values().length];
            f8311a = iArr3;
            try {
                iArr3[e.a.xMidYMin.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8311a[e.a.xMidYMid.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f8311a[e.a.xMidYMax.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f8311a[e.a.xMaxYMin.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f8311a[e.a.xMaxYMid.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f8311a[e.a.xMaxYMax.ordinal()] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f8311a[e.a.xMinYMid.ordinal()] = 7;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f8311a[e.a.xMinYMax.ordinal()] = 8;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    /* JADX INFO: compiled from: SVGAndroidRenderer.java */
    private class b implements g.x {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private float f8315b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f8316c;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f8321h;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private List<c> f8314a = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private c f8317d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f8318e = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f8319f = true;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f8320g = -1;

        b(g.w wVar) {
            if (wVar == null) {
                return;
            }
            wVar.h(this);
            if (this.f8321h) {
                this.f8317d.b(this.f8314a.get(this.f8320g));
                this.f8314a.set(this.f8320g, this.f8317d);
                this.f8321h = false;
            }
            c cVar = this.f8317d;
            if (cVar != null) {
                this.f8314a.add(cVar);
            }
        }

        @Override // m2.g.x
        public void a(float f7, float f8) {
            if (this.f8321h) {
                this.f8317d.b(this.f8314a.get(this.f8320g));
                this.f8314a.set(this.f8320g, this.f8317d);
                this.f8321h = false;
            }
            c cVar = this.f8317d;
            if (cVar != null) {
                this.f8314a.add(cVar);
            }
            this.f8315b = f7;
            this.f8316c = f8;
            this.f8317d = h.this.new c(f7, f8, 0.0f, 0.0f);
            this.f8320g = this.f8314a.size();
        }

        @Override // m2.g.x
        public void b(float f7, float f8, float f9, float f10) {
            this.f8317d.a(f7, f8);
            this.f8314a.add(this.f8317d);
            this.f8317d = h.this.new c(f9, f10, f9 - f7, f10 - f8);
            this.f8321h = false;
        }

        @Override // m2.g.x
        public void c(float f7, float f8, float f9, float f10, float f11, float f12) {
            if (this.f8319f || this.f8318e) {
                this.f8317d.a(f7, f8);
                this.f8314a.add(this.f8317d);
                this.f8318e = false;
            }
            this.f8317d = h.this.new c(f11, f12, f11 - f9, f12 - f10);
            this.f8321h = false;
        }

        @Override // m2.g.x
        public void close() {
            this.f8314a.add(this.f8317d);
            d(this.f8315b, this.f8316c);
            this.f8321h = true;
        }

        @Override // m2.g.x
        public void d(float f7, float f8) {
            this.f8317d.a(f7, f8);
            this.f8314a.add(this.f8317d);
            h hVar = h.this;
            c cVar = this.f8317d;
            this.f8317d = hVar.new c(f7, f8, f7 - cVar.f8323a, f8 - cVar.f8324b);
            this.f8321h = false;
        }

        @Override // m2.g.x
        public void e(float f7, float f8, float f9, boolean z6, boolean z7, float f10, float f11) {
            this.f8318e = true;
            this.f8319f = false;
            c cVar = this.f8317d;
            h.h(cVar.f8323a, cVar.f8324b, f7, f8, f9, z6, z7, f10, f11, this);
            this.f8319f = true;
            this.f8321h = false;
        }

        List<c> f() {
            return this.f8314a;
        }
    }

    /* JADX INFO: compiled from: SVGAndroidRenderer.java */
    private class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f8323a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f8324b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f8325c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f8326d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f8327e = false;

        c(float f7, float f8, float f9, float f10) {
            this.f8325c = 0.0f;
            this.f8326d = 0.0f;
            this.f8323a = f7;
            this.f8324b = f8;
            double dSqrt = Math.sqrt((f9 * f9) + (f10 * f10));
            if (dSqrt != 0.0d) {
                this.f8325c = (float) (((double) f9) / dSqrt);
                this.f8326d = (float) (((double) f10) / dSqrt);
            }
        }

        void a(float f7, float f8) {
            float f9 = f7 - this.f8323a;
            float f10 = f8 - this.f8324b;
            double dSqrt = Math.sqrt((f9 * f9) + (f10 * f10));
            if (dSqrt != 0.0d) {
                f9 = (float) (((double) f9) / dSqrt);
                f10 = (float) (((double) f10) / dSqrt);
            }
            float f11 = this.f8325c;
            if (f9 != (-f11) || f10 != (-this.f8326d)) {
                this.f8325c = f11 + f9;
                this.f8326d += f10;
            } else {
                this.f8327e = true;
                this.f8325c = -f10;
                this.f8326d = f9;
            }
        }

        void b(c cVar) {
            float f7 = cVar.f8325c;
            float f8 = this.f8325c;
            if (f7 == (-f8)) {
                float f9 = cVar.f8326d;
                if (f9 == (-this.f8326d)) {
                    this.f8327e = true;
                    this.f8325c = -f9;
                    this.f8326d = cVar.f8325c;
                    return;
                }
            }
            this.f8325c = f8 + f7;
            this.f8326d += cVar.f8326d;
        }

        public String toString() {
            return "(" + this.f8323a + "," + this.f8324b + " " + this.f8325c + "," + this.f8326d + ")";
        }
    }

    /* JADX INFO: compiled from: SVGAndroidRenderer.java */
    private class d implements g.x {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Path f8329a = new Path();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f8330b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f8331c;

        d(g.w wVar) {
            if (wVar == null) {
                return;
            }
            wVar.h(this);
        }

        @Override // m2.g.x
        public void a(float f7, float f8) {
            this.f8329a.moveTo(f7, f8);
            this.f8330b = f7;
            this.f8331c = f8;
        }

        @Override // m2.g.x
        public void b(float f7, float f8, float f9, float f10) {
            this.f8329a.quadTo(f7, f8, f9, f10);
            this.f8330b = f9;
            this.f8331c = f10;
        }

        @Override // m2.g.x
        public void c(float f7, float f8, float f9, float f10, float f11, float f12) {
            this.f8329a.cubicTo(f7, f8, f9, f10, f11, f12);
            this.f8330b = f11;
            this.f8331c = f12;
        }

        @Override // m2.g.x
        public void close() {
            this.f8329a.close();
        }

        @Override // m2.g.x
        public void d(float f7, float f8) {
            this.f8329a.lineTo(f7, f8);
            this.f8330b = f7;
            this.f8331c = f8;
        }

        @Override // m2.g.x
        public void e(float f7, float f8, float f9, boolean z6, boolean z7, float f10, float f11) {
            h.h(this.f8330b, this.f8331c, f7, f8, f9, z6, z7, f10, f11, this);
            this.f8330b = f10;
            this.f8331c = f11;
        }

        Path f() {
            return this.f8329a;
        }
    }

    /* JADX INFO: compiled from: SVGAndroidRenderer.java */
    private class e extends f {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Path f8333e;

        e(Path path, float f7, float f8) {
            super(f7, f8);
            this.f8333e = path;
        }

        @Override // m2.h.f, m2.h.j
        public void b(String str) {
            if (h.this.W0()) {
                if (h.this.f8306d.f8343b) {
                    h.this.f8303a.drawTextOnPath(str, this.f8333e, this.f8335b, this.f8336c, h.this.f8306d.f8345d);
                }
                if (h.this.f8306d.f8344c) {
                    h.this.f8303a.drawTextOnPath(str, this.f8333e, this.f8335b, this.f8336c, h.this.f8306d.f8346e);
                }
            }
            this.f8335b += h.this.f8306d.f8345d.measureText(str);
        }
    }

    /* JADX INFO: compiled from: SVGAndroidRenderer.java */
    private class f extends j {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f8335b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f8336c;

        f(float f7, float f8) {
            super(h.this, null);
            this.f8335b = f7;
            this.f8336c = f8;
        }

        @Override // m2.h.j
        public void b(String str) {
            h.x("TextSequence render", new Object[0]);
            if (h.this.W0()) {
                if (h.this.f8306d.f8343b) {
                    h.this.f8303a.drawText(str, this.f8335b, this.f8336c, h.this.f8306d.f8345d);
                }
                if (h.this.f8306d.f8344c) {
                    h.this.f8303a.drawText(str, this.f8335b, this.f8336c, h.this.f8306d.f8346e);
                }
            }
            this.f8335b += h.this.f8306d.f8345d.measureText(str);
        }
    }

    /* JADX INFO: compiled from: SVGAndroidRenderer.java */
    private class g extends j {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f8338b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f8339c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Path f8340d;

        g(float f7, float f8, Path path) {
            super(h.this, null);
            this.f8338b = f7;
            this.f8339c = f8;
            this.f8340d = path;
        }

        @Override // m2.h.j
        public boolean a(g.y0 y0Var) {
            if (!(y0Var instanceof g.z0)) {
                return true;
            }
            h.X0("Using <textPath> elements in a clip path is not supported.", new Object[0]);
            return false;
        }

        @Override // m2.h.j
        public void b(String str) {
            if (h.this.W0()) {
                Path path = new Path();
                h.this.f8306d.f8345d.getTextPath(str, 0, str.length(), this.f8338b, this.f8339c, path);
                this.f8340d.addPath(path);
            }
            this.f8338b += h.this.f8306d.f8345d.measureText(str);
        }
    }

    /* JADX INFO: compiled from: SVGAndroidRenderer.java */
    private class i extends j {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f8351b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f8352c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        RectF f8353d;

        i(float f7, float f8) {
            super(h.this, null);
            this.f8353d = new RectF();
            this.f8351b = f7;
            this.f8352c = f8;
        }

        @Override // m2.h.j
        public boolean a(g.y0 y0Var) {
            if (!(y0Var instanceof g.z0)) {
                return true;
            }
            g.z0 z0Var = (g.z0) y0Var;
            g.n0 n0VarR = y0Var.f8245a.r(z0Var.f8299o);
            if (n0VarR == null) {
                h.E("TextPath path reference '%s' not found", z0Var.f8299o);
                return false;
            }
            g.v vVar = (g.v) n0VarR;
            Path pathF = h.this.new d(vVar.f8282o).f();
            Matrix matrix = vVar.f8234n;
            if (matrix != null) {
                pathF.transform(matrix);
            }
            RectF rectF = new RectF();
            pathF.computeBounds(rectF, true);
            this.f8353d.union(rectF);
            return false;
        }

        @Override // m2.h.j
        public void b(String str) {
            if (h.this.W0()) {
                Rect rect = new Rect();
                h.this.f8306d.f8345d.getTextBounds(str, 0, str.length(), rect);
                RectF rectF = new RectF(rect);
                rectF.offset(this.f8351b, this.f8352c);
                this.f8353d.union(rectF);
            }
            this.f8351b += h.this.f8306d.f8345d.measureText(str);
        }
    }

    /* JADX INFO: compiled from: SVGAndroidRenderer.java */
    private abstract class j {
        private j() {
        }

        public boolean a(g.y0 y0Var) {
            return true;
        }

        public abstract void b(String str);

        /* synthetic */ j(h hVar, a aVar) {
            this();
        }
    }

    h(Canvas canvas, float f7) {
        this.f8303a = canvas;
        this.f8304b = f7;
    }

    private void A(g.k0 k0Var, Path path) {
        g.o0 o0Var = this.f8306d.f8342a.f8169g;
        if (o0Var instanceof g.u) {
            g.n0 n0VarR = this.f8305c.r(((g.u) o0Var).f8278e);
            if (n0VarR instanceof g.y) {
                K(k0Var, path, (g.y) n0VarR);
                return;
            }
        }
        this.f8303a.drawPath(path, this.f8306d.f8345d);
    }

    private void A0(g.t0 t0Var, g.b bVar) {
        x("Symbol render", new Object[0]);
        if (bVar.f8148c == 0.0f || bVar.f8149d == 0.0f) {
            return;
        }
        m2.e eVar = t0Var.f8255o;
        if (eVar == null) {
            eVar = m2.e.f8117e;
        }
        U0(this.f8306d, t0Var);
        C0126h c0126h = this.f8306d;
        c0126h.f8347f = bVar;
        if (!c0126h.f8342a.A.booleanValue()) {
            g.b bVar2 = this.f8306d.f8347f;
            M0(bVar2.f8146a, bVar2.f8147b, bVar2.f8148c, bVar2.f8149d);
        }
        g.b bVar3 = t0Var.f8271p;
        if (bVar3 != null) {
            this.f8303a.concat(o(this.f8306d.f8347f, bVar3, eVar));
            this.f8306d.f8348g = t0Var.f8271p;
        } else {
            Canvas canvas = this.f8303a;
            g.b bVar4 = this.f8306d.f8347f;
            canvas.translate(bVar4.f8146a, bVar4.f8147b);
        }
        boolean zK0 = k0();
        D0(t0Var, true);
        if (zK0) {
            i0(t0Var);
        }
        S0(t0Var);
    }

    private void B(Path path) {
        C0126h c0126h = this.f8306d;
        if (c0126h.f8342a.Q != g.e0.i.NonScalingStroke) {
            this.f8303a.drawPath(path, c0126h.f8346e);
            return;
        }
        Matrix matrix = this.f8303a.getMatrix();
        Path path2 = new Path();
        path.transform(matrix, path2);
        this.f8303a.setMatrix(new Matrix());
        Shader shader = this.f8306d.f8346e.getShader();
        Matrix matrix2 = new Matrix();
        if (shader != null) {
            shader.getLocalMatrix(matrix2);
            Matrix matrix3 = new Matrix(matrix2);
            matrix3.postConcat(matrix);
            shader.setLocalMatrix(matrix3);
        }
        this.f8303a.drawPath(path2, this.f8306d.f8346e);
        this.f8303a.setMatrix(matrix);
        if (shader != null) {
            shader.setLocalMatrix(matrix2);
        }
    }

    private void B0(g.w0 w0Var) {
        x("Text render", new Object[0]);
        U0(this.f8306d, w0Var);
        if (z()) {
            Matrix matrix = w0Var.f8289s;
            if (matrix != null) {
                this.f8303a.concat(matrix);
            }
            List<g.p> list = w0Var.f8142o;
            float f7 = 0.0f;
            float fE = (list == null || list.size() == 0) ? 0.0f : w0Var.f8142o.get(0).e(this);
            List<g.p> list2 = w0Var.f8143p;
            float f8 = (list2 == null || list2.size() == 0) ? 0.0f : w0Var.f8143p.get(0).f(this);
            List<g.p> list3 = w0Var.f8144q;
            float fE2 = (list3 == null || list3.size() == 0) ? 0.0f : w0Var.f8144q.get(0).e(this);
            List<g.p> list4 = w0Var.f8145r;
            if (list4 != null && list4.size() != 0) {
                f7 = w0Var.f8145r.get(0).f(this);
            }
            g.e0.f fVarN = N();
            if (fVarN != g.e0.f.Start) {
                float fN = n(w0Var);
                if (fVarN == g.e0.f.Middle) {
                    fN /= 2.0f;
                }
                fE -= fN;
            }
            if (w0Var.f8233h == null) {
                i iVar = new i(fE, f8);
                D(w0Var, iVar);
                RectF rectF = iVar.f8353d;
                w0Var.f8233h = new g.b(rectF.left, rectF.top, rectF.width(), iVar.f8353d.height());
            }
            S0(w0Var);
            r(w0Var);
            p(w0Var);
            boolean zK0 = k0();
            D(w0Var, new f(fE + fE2, f8 + f7));
            if (zK0) {
                i0(w0Var);
            }
        }
    }

    private float C(float f7, float f8, float f9, float f10) {
        return (f7 * f9) + (f8 * f10);
    }

    private void C0(g.e1 e1Var) {
        x("Use render", new Object[0]);
        g.p pVar = e1Var.f8201s;
        if (pVar == null || !pVar.h()) {
            g.p pVar2 = e1Var.f8202t;
            if (pVar2 == null || !pVar2.h()) {
                U0(this.f8306d, e1Var);
                if (z()) {
                    g.n0 n0VarR = e1Var.f8245a.r(e1Var.f8198p);
                    if (n0VarR == null) {
                        E("Use reference '%s' not found", e1Var.f8198p);
                        return;
                    }
                    Matrix matrix = e1Var.f8240o;
                    if (matrix != null) {
                        this.f8303a.concat(matrix);
                    }
                    g.p pVar3 = e1Var.f8199q;
                    float fE = pVar3 != null ? pVar3.e(this) : 0.0f;
                    g.p pVar4 = e1Var.f8200r;
                    this.f8303a.translate(fE, pVar4 != null ? pVar4.f(this) : 0.0f);
                    p(e1Var);
                    boolean zK0 = k0();
                    h0(e1Var);
                    if (n0VarR instanceof g.f0) {
                        g.b bVarE0 = e0(null, null, e1Var.f8201s, e1Var.f8202t);
                        Q0();
                        w0((g.f0) n0VarR, bVarE0);
                        P0();
                    } else if (n0VarR instanceof g.t0) {
                        g.p pVar5 = e1Var.f8201s;
                        if (pVar5 == null) {
                            pVar5 = new g.p(100.0f, g.d1.percent);
                        }
                        g.p pVar6 = e1Var.f8202t;
                        if (pVar6 == null) {
                            pVar6 = new g.p(100.0f, g.d1.percent);
                        }
                        g.b bVarE02 = e0(null, null, pVar5, pVar6);
                        Q0();
                        A0((g.t0) n0VarR, bVarE02);
                        P0();
                    } else {
                        y0(n0VarR);
                    }
                    g0();
                    if (zK0) {
                        i0(e1Var);
                    }
                    S0(e1Var);
                }
            }
        }
    }

    private void D(g.y0 y0Var, j jVar) {
        if (z()) {
            Iterator<g.n0> it = y0Var.f8212i.iterator();
            boolean z6 = true;
            while (it.hasNext()) {
                g.n0 next = it.next();
                if (next instanceof g.c1) {
                    jVar.b(R0(((g.c1) next).f8160c, z6, !it.hasNext()));
                } else {
                    j0(next, jVar);
                }
                z6 = false;
            }
        }
    }

    private void D0(g.j0 j0Var, boolean z6) {
        if (z6) {
            h0(j0Var);
        }
        Iterator<g.n0> it = j0Var.j().iterator();
        while (it.hasNext()) {
            y0(it.next());
        }
        if (z6) {
            g0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void E(String str, Object... objArr) {
        Log.e("SVGAndroidRenderer", String.format(str, objArr));
    }

    private void F(g.y0 y0Var, StringBuilder sb) {
        Iterator<g.n0> it = y0Var.f8212i.iterator();
        boolean z6 = true;
        while (it.hasNext()) {
            g.n0 next = it.next();
            if (next instanceof g.y0) {
                F((g.y0) next, sb);
            } else if (next instanceof g.c1) {
                sb.append(R0(((g.c1) next).f8160c, z6, !it.hasNext()));
            }
            z6 = false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x00ff, code lost:
    
        if (r7 != 8) goto L67;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void F0(m2.g.r r12, m2.h.c r13) {
        /*
            Method dump skipped, instruction units count: 350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: m2.h.F0(m2.g$r, m2.h$c):void");
    }

    private void G(g.j jVar, String str) {
        g.n0 n0VarR = jVar.f8245a.r(str);
        if (n0VarR == null) {
            X0("Gradient reference '%s' not found", str);
            return;
        }
        if (!(n0VarR instanceof g.j)) {
            E("Gradient href attributes must point to other gradient elements", new Object[0]);
            return;
        }
        if (n0VarR == jVar) {
            E("Circular reference in gradient href attribute '%s'", str);
            return;
        }
        g.j jVar2 = (g.j) n0VarR;
        if (jVar.f8228i == null) {
            jVar.f8228i = jVar2.f8228i;
        }
        if (jVar.f8229j == null) {
            jVar.f8229j = jVar2.f8229j;
        }
        if (jVar.f8230k == null) {
            jVar.f8230k = jVar2.f8230k;
        }
        if (jVar.f8227h.isEmpty()) {
            jVar.f8227h = jVar2.f8227h;
        }
        try {
            if (jVar instanceof g.m0) {
                H((g.m0) jVar, (g.m0) n0VarR);
            } else {
                I((g.q0) jVar, (g.q0) n0VarR);
            }
        } catch (ClassCastException unused) {
        }
        String str2 = jVar2.f8231l;
        if (str2 != null) {
            G(jVar, str2);
        }
    }

    private void G0(g.l lVar) {
        g.r rVar;
        g.r rVar2;
        g.r rVar3;
        int size;
        g.e0 e0Var = this.f8306d.f8342a;
        String str = e0Var.C;
        if (str == null && e0Var.D == null && e0Var.E == null) {
            return;
        }
        if (str == null) {
            rVar = null;
        } else {
            g.n0 n0VarR = lVar.f8245a.r(str);
            if (n0VarR != null) {
                rVar = (g.r) n0VarR;
            } else {
                E("Marker reference '%s' not found", this.f8306d.f8342a.C);
                rVar = null;
            }
        }
        String str2 = this.f8306d.f8342a.D;
        if (str2 == null) {
            rVar2 = null;
        } else {
            g.n0 n0VarR2 = lVar.f8245a.r(str2);
            if (n0VarR2 != null) {
                rVar2 = (g.r) n0VarR2;
            } else {
                E("Marker reference '%s' not found", this.f8306d.f8342a.D);
                rVar2 = null;
            }
        }
        String str3 = this.f8306d.f8342a.E;
        if (str3 == null) {
            rVar3 = null;
        } else {
            g.n0 n0VarR3 = lVar.f8245a.r(str3);
            if (n0VarR3 != null) {
                rVar3 = (g.r) n0VarR3;
            } else {
                E("Marker reference '%s' not found", this.f8306d.f8342a.E);
                rVar3 = null;
            }
        }
        List<c> listF = lVar instanceof g.v ? new b(((g.v) lVar).f8282o).f() : lVar instanceof g.q ? k((g.q) lVar) : l((g.z) lVar);
        if (listF == null || (size = listF.size()) == 0) {
            return;
        }
        g.e0 e0Var2 = this.f8306d.f8342a;
        e0Var2.E = null;
        e0Var2.D = null;
        e0Var2.C = null;
        if (rVar != null) {
            F0(rVar, listF.get(0));
        }
        if (rVar2 != null && listF.size() > 2) {
            c cVarL0 = listF.get(0);
            c cVar = listF.get(1);
            int i7 = 1;
            while (i7 < size - 1) {
                i7++;
                c cVar2 = listF.get(i7);
                cVarL0 = cVar.f8327e ? l0(cVarL0, cVar, cVar2) : cVar;
                F0(rVar2, cVarL0);
                cVar = cVar2;
            }
        }
        if (rVar3 != null) {
            F0(rVar3, listF.get(size - 1));
        }
    }

    private void H(g.m0 m0Var, g.m0 m0Var2) {
        if (m0Var.f8241m == null) {
            m0Var.f8241m = m0Var2.f8241m;
        }
        if (m0Var.f8242n == null) {
            m0Var.f8242n = m0Var2.f8242n;
        }
        if (m0Var.f8243o == null) {
            m0Var.f8243o = m0Var2.f8243o;
        }
        if (m0Var.f8244p == null) {
            m0Var.f8244p = m0Var2.f8244p;
        }
    }

    private void H0(g.s sVar, g.k0 k0Var) {
        float fE;
        float f7;
        x("Mask render", new Object[0]);
        Boolean bool = sVar.f8272o;
        boolean z6 = true;
        if (bool != null && bool.booleanValue()) {
            g.p pVar = sVar.f8276s;
            fE = pVar != null ? pVar.e(this) : k0Var.f8233h.f8148c;
            g.p pVar2 = sVar.f8277t;
            f7 = pVar2 != null ? pVar2.f(this) : k0Var.f8233h.f8149d;
        } else {
            g.p pVar3 = sVar.f8276s;
            float fD = pVar3 != null ? pVar3.d(this, 1.0f) : 1.2f;
            g.p pVar4 = sVar.f8277t;
            float fD2 = pVar4 != null ? pVar4.d(this, 1.0f) : 1.2f;
            g.b bVar = k0Var.f8233h;
            fE = fD * bVar.f8148c;
            f7 = fD2 * bVar.f8149d;
        }
        if (fE == 0.0f || f7 == 0.0f) {
            return;
        }
        Q0();
        C0126h c0126hL = L(sVar);
        this.f8306d = c0126hL;
        c0126hL.f8342a.f8180r = Float.valueOf(1.0f);
        Boolean bool2 = sVar.f8273p;
        if (bool2 != null && !bool2.booleanValue()) {
            z6 = false;
        }
        if (!z6) {
            Canvas canvas = this.f8303a;
            g.b bVar2 = k0Var.f8233h;
            canvas.translate(bVar2.f8146a, bVar2.f8147b);
            Canvas canvas2 = this.f8303a;
            g.b bVar3 = k0Var.f8233h;
            canvas2.scale(bVar3.f8148c, bVar3.f8149d);
        }
        D0(sVar, false);
        P0();
    }

    private void I(g.q0 q0Var, g.q0 q0Var2) {
        if (q0Var.f8260m == null) {
            q0Var.f8260m = q0Var2.f8260m;
        }
        if (q0Var.f8261n == null) {
            q0Var.f8261n = q0Var2.f8261n;
        }
        if (q0Var.f8262o == null) {
            q0Var.f8262o = q0Var2.f8262o;
        }
        if (q0Var.f8263p == null) {
            q0Var.f8263p = q0Var2.f8263p;
        }
        if (q0Var.f8264q == null) {
            q0Var.f8264q = q0Var2.f8264q;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void I0(g.s0 s0Var) {
        Set<String> setA;
        String language = Locale.getDefault().getLanguage();
        m2.g.k();
        for (g.n0 n0Var : s0Var.j()) {
            if (n0Var instanceof g.g0) {
                g.g0 g0Var = (g.g0) n0Var;
                if (g0Var.c() == null && ((setA = g0Var.a()) == null || (!setA.isEmpty() && setA.contains(language)))) {
                    Set<String> setF = g0Var.f();
                    if (setF != null) {
                        if (f8302i == null) {
                            U();
                        }
                        if (setF.isEmpty() || !f8302i.containsAll(setF)) {
                        }
                    }
                    Set<String> setM = g0Var.m();
                    if (setM != null) {
                        setM.isEmpty();
                    } else {
                        Set<String> setN = g0Var.n();
                        if (setN == null) {
                            y0(n0Var);
                            return;
                        }
                        setN.isEmpty();
                    }
                }
            }
        }
    }

    private void J(g.y yVar, String str) {
        g.n0 n0VarR = yVar.f8245a.r(str);
        if (n0VarR == null) {
            X0("Pattern reference '%s' not found", str);
            return;
        }
        if (!(n0VarR instanceof g.y)) {
            E("Pattern href attributes must point to other pattern elements", new Object[0]);
            return;
        }
        if (n0VarR == yVar) {
            E("Circular reference in pattern href attribute '%s'", str);
            return;
        }
        g.y yVar2 = (g.y) n0VarR;
        if (yVar.f8290q == null) {
            yVar.f8290q = yVar2.f8290q;
        }
        if (yVar.f8291r == null) {
            yVar.f8291r = yVar2.f8291r;
        }
        if (yVar.f8292s == null) {
            yVar.f8292s = yVar2.f8292s;
        }
        if (yVar.f8293t == null) {
            yVar.f8293t = yVar2.f8293t;
        }
        if (yVar.f8294u == null) {
            yVar.f8294u = yVar2.f8294u;
        }
        if (yVar.f8295v == null) {
            yVar.f8295v = yVar2.f8295v;
        }
        if (yVar.f8296w == null) {
            yVar.f8296w = yVar2.f8296w;
        }
        if (yVar.f8212i.isEmpty()) {
            yVar.f8212i = yVar2.f8212i;
        }
        if (yVar.f8271p == null) {
            yVar.f8271p = yVar2.f8271p;
        }
        if (yVar.f8255o == null) {
            yVar.f8255o = yVar2.f8255o;
        }
        String str2 = yVar2.f8297x;
        if (str2 != null) {
            J(yVar, str2);
        }
    }

    private void J0(g.z0 z0Var) {
        x("TextPath render", new Object[0]);
        U0(this.f8306d, z0Var);
        if (z() && W0()) {
            g.n0 n0VarR = z0Var.f8245a.r(z0Var.f8299o);
            if (n0VarR == null) {
                E("TextPath reference '%s' not found", z0Var.f8299o);
                return;
            }
            g.v vVar = (g.v) n0VarR;
            Path pathF = new d(vVar.f8282o).f();
            Matrix matrix = vVar.f8234n;
            if (matrix != null) {
                pathF.transform(matrix);
            }
            PathMeasure pathMeasure = new PathMeasure(pathF, false);
            g.p pVar = z0Var.f8300p;
            float fD = pVar != null ? pVar.d(this, pathMeasure.getLength()) : 0.0f;
            g.e0.f fVarN = N();
            if (fVarN != g.e0.f.Start) {
                float fN = n(z0Var);
                if (fVarN == g.e0.f.Middle) {
                    fN /= 2.0f;
                }
                fD -= fN;
            }
            r((g.k0) z0Var.h());
            boolean zK0 = k0();
            D(z0Var, new e(pathF, fD, 0.0f));
            if (zK0) {
                i0(z0Var);
            }
        }
    }

    private void K(g.k0 k0Var, Path path, g.y yVar) {
        float fE;
        float f7;
        float f8;
        float fE2;
        float f9;
        Boolean bool = yVar.f8290q;
        boolean z6 = bool != null && bool.booleanValue();
        String str = yVar.f8297x;
        if (str != null) {
            J(yVar, str);
        }
        if (z6) {
            g.p pVar = yVar.f8293t;
            fE = pVar != null ? pVar.e(this) : 0.0f;
            g.p pVar2 = yVar.f8294u;
            f8 = pVar2 != null ? pVar2.f(this) : 0.0f;
            g.p pVar3 = yVar.f8295v;
            fE2 = pVar3 != null ? pVar3.e(this) : 0.0f;
            g.p pVar4 = yVar.f8296w;
            f7 = pVar4 != null ? pVar4.f(this) : 0.0f;
        } else {
            g.p pVar5 = yVar.f8293t;
            float fD = pVar5 != null ? pVar5.d(this, 1.0f) : 0.0f;
            g.p pVar6 = yVar.f8294u;
            float fD2 = pVar6 != null ? pVar6.d(this, 1.0f) : 0.0f;
            g.p pVar7 = yVar.f8295v;
            float fD3 = pVar7 != null ? pVar7.d(this, 1.0f) : 0.0f;
            g.p pVar8 = yVar.f8296w;
            float fD4 = pVar8 != null ? pVar8.d(this, 1.0f) : 0.0f;
            g.b bVar = k0Var.f8233h;
            float f10 = bVar.f8146a;
            float f11 = bVar.f8148c;
            fE = (fD * f11) + f10;
            float f12 = bVar.f8147b;
            float f13 = bVar.f8149d;
            float f14 = fD3 * f11;
            f7 = fD4 * f13;
            f8 = (fD2 * f13) + f12;
            fE2 = f14;
        }
        if (fE2 == 0.0f || f7 == 0.0f) {
            return;
        }
        m2.e eVar = yVar.f8255o;
        if (eVar == null) {
            eVar = m2.e.f8117e;
        }
        Q0();
        this.f8303a.clipPath(path);
        C0126h c0126h = new C0126h();
        T0(c0126h, g.e0.a());
        c0126h.f8342a.A = Boolean.FALSE;
        this.f8306d = M(yVar, c0126h);
        g.b bVar2 = k0Var.f8233h;
        Matrix matrix = yVar.f8292s;
        if (matrix != null) {
            this.f8303a.concat(matrix);
            Matrix matrix2 = new Matrix();
            if (yVar.f8292s.invert(matrix2)) {
                g.b bVar3 = k0Var.f8233h;
                g.b bVar4 = k0Var.f8233h;
                g.b bVar5 = k0Var.f8233h;
                float[] fArr = {bVar3.f8146a, bVar3.f8147b, bVar3.b(), bVar4.f8147b, bVar4.b(), k0Var.f8233h.c(), bVar5.f8146a, bVar5.c()};
                matrix2.mapPoints(fArr);
                RectF rectF = new RectF(fArr[0], fArr[1], fArr[0], fArr[1]);
                for (int i7 = 2; i7 <= 6; i7 += 2) {
                    if (fArr[i7] < rectF.left) {
                        rectF.left = fArr[i7];
                    }
                    if (fArr[i7] > rectF.right) {
                        rectF.right = fArr[i7];
                    }
                    int i8 = i7 + 1;
                    if (fArr[i8] < rectF.top) {
                        rectF.top = fArr[i8];
                    }
                    if (fArr[i8] > rectF.bottom) {
                        rectF.bottom = fArr[i8];
                    }
                }
                float f15 = rectF.left;
                float f16 = rectF.top;
                bVar2 = new g.b(f15, f16, rectF.right - f15, rectF.bottom - f16);
            }
        }
        float fFloor = fE + (((float) Math.floor((bVar2.f8146a - fE) / fE2)) * fE2);
        float fB = bVar2.b();
        float fC = bVar2.c();
        g.b bVar6 = new g.b(0.0f, 0.0f, fE2, f7);
        boolean zK0 = k0();
        for (float fFloor2 = f8 + (((float) Math.floor((bVar2.f8147b - f8) / f7)) * f7); fFloor2 < fC; fFloor2 += f7) {
            float f17 = fFloor;
            while (f17 < fB) {
                bVar6.f8146a = f17;
                bVar6.f8147b = fFloor2;
                Q0();
                if (this.f8306d.f8342a.A.booleanValue()) {
                    f9 = fFloor;
                } else {
                    f9 = fFloor;
                    M0(bVar6.f8146a, bVar6.f8147b, bVar6.f8148c, bVar6.f8149d);
                }
                g.b bVar7 = yVar.f8271p;
                if (bVar7 != null) {
                    this.f8303a.concat(o(bVar6, bVar7, eVar));
                } else {
                    Boolean bool2 = yVar.f8291r;
                    boolean z7 = bool2 == null || bool2.booleanValue();
                    this.f8303a.translate(f17, fFloor2);
                    if (!z7) {
                        Canvas canvas = this.f8303a;
                        g.b bVar8 = k0Var.f8233h;
                        canvas.scale(bVar8.f8148c, bVar8.f8149d);
                    }
                }
                Iterator<g.n0> it = yVar.f8212i.iterator();
                while (it.hasNext()) {
                    y0(it.next());
                }
                P0();
                f17 += fE2;
                fFloor = f9;
            }
        }
        if (zK0) {
            i0(yVar);
        }
        P0();
    }

    private boolean K0() {
        return this.f8306d.f8342a.f8180r.floatValue() < 1.0f || this.f8306d.f8342a.L != null;
    }

    private C0126h L(g.n0 n0Var) {
        C0126h c0126h = new C0126h();
        T0(c0126h, g.e0.a());
        return M(n0Var, c0126h);
    }

    private void L0() {
        this.f8306d = new C0126h();
        this.f8307e = new Stack<>();
        T0(this.f8306d, g.e0.a());
        C0126h c0126h = this.f8306d;
        c0126h.f8347f = null;
        c0126h.f8349h = false;
        this.f8307e.push(new C0126h(c0126h));
        this.f8309g = new Stack<>();
        this.f8308f = new Stack<>();
    }

    private C0126h M(g.n0 n0Var, C0126h c0126h) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            if (n0Var instanceof g.l0) {
                arrayList.add(0, (g.l0) n0Var);
            }
            Object obj = n0Var.f8246b;
            if (obj == null) {
                break;
            }
            n0Var = (g.n0) obj;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            U0(c0126h, (g.l0) it.next());
        }
        C0126h c0126h2 = this.f8306d;
        c0126h.f8348g = c0126h2.f8348g;
        c0126h.f8347f = c0126h2.f8347f;
        return c0126h;
    }

    private void M0(float f7, float f8, float f9, float f10) {
        float fE = f9 + f7;
        float f11 = f10 + f8;
        g.c cVar = this.f8306d.f8342a.B;
        if (cVar != null) {
            f7 += cVar.f8159d.e(this);
            f8 += this.f8306d.f8342a.B.f8156a.f(this);
            fE -= this.f8306d.f8342a.B.f8157b.e(this);
            f11 -= this.f8306d.f8342a.B.f8158c.f(this);
        }
        this.f8303a.clipRect(f7, f8, fE, f11);
    }

    private g.e0.f N() {
        g.e0.f fVar;
        g.e0 e0Var = this.f8306d.f8342a;
        if (e0Var.f8187y == g.e0.h.LTR || (fVar = e0Var.f8188z) == g.e0.f.Middle) {
            return e0Var.f8188z;
        }
        g.e0.f fVar2 = g.e0.f.Start;
        return fVar == fVar2 ? g.e0.f.End : fVar2;
    }

    private void N0(C0126h c0126h, boolean z6, g.o0 o0Var) {
        int i7;
        g.e0 e0Var = c0126h.f8342a;
        float fFloatValue = (z6 ? e0Var.f8171i : e0Var.f8173k).floatValue();
        if (o0Var instanceof g.f) {
            i7 = ((g.f) o0Var).f8205e;
        } else if (!(o0Var instanceof g.C0125g)) {
            return;
        } else {
            i7 = c0126h.f8342a.f8181s.f8205e;
        }
        int iW = w(i7, fFloatValue);
        if (z6) {
            c0126h.f8345d.setColor(iW);
        } else {
            c0126h.f8346e.setColor(iW);
        }
    }

    private Path.FillType O() {
        g.e0.a aVar = this.f8306d.f8342a.K;
        return (aVar == null || aVar != g.e0.a.EvenOdd) ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
    }

    private void O0(boolean z6, g.c0 c0Var) {
        if (z6) {
            if (V(c0Var.f8237e, 2147483648L)) {
                C0126h c0126h = this.f8306d;
                g.e0 e0Var = c0126h.f8342a;
                g.o0 o0Var = c0Var.f8237e.M;
                e0Var.f8169g = o0Var;
                c0126h.f8343b = o0Var != null;
            }
            if (V(c0Var.f8237e, 4294967296L)) {
                this.f8306d.f8342a.f8171i = c0Var.f8237e.N;
            }
            if (V(c0Var.f8237e, 6442450944L)) {
                C0126h c0126h2 = this.f8306d;
                N0(c0126h2, z6, c0126h2.f8342a.f8169g);
                return;
            }
            return;
        }
        if (V(c0Var.f8237e, 2147483648L)) {
            C0126h c0126h3 = this.f8306d;
            g.e0 e0Var2 = c0126h3.f8342a;
            g.o0 o0Var2 = c0Var.f8237e.M;
            e0Var2.f8172j = o0Var2;
            c0126h3.f8344c = o0Var2 != null;
        }
        if (V(c0Var.f8237e, 4294967296L)) {
            this.f8306d.f8342a.f8173k = c0Var.f8237e.N;
        }
        if (V(c0Var.f8237e, 6442450944L)) {
            C0126h c0126h4 = this.f8306d;
            N0(c0126h4, z6, c0126h4.f8342a.f8172j);
        }
    }

    private void P0() {
        this.f8303a.restore();
        this.f8306d = this.f8307e.pop();
    }

    private void Q0() {
        this.f8303a.save();
        this.f8307e.push(this.f8306d);
        this.f8306d = new C0126h(this.f8306d);
    }

    private String R0(String str, boolean z6, boolean z7) {
        if (this.f8306d.f8349h) {
            return str.replaceAll("[\\n\\t]", " ");
        }
        String strReplaceAll = str.replaceAll("\\n", "").replaceAll("\\t", " ");
        if (z6) {
            strReplaceAll = strReplaceAll.replaceAll("^\\s+", "");
        }
        if (z7) {
            strReplaceAll = strReplaceAll.replaceAll("\\s+$", "");
        }
        return strReplaceAll.replaceAll("\\s{2,}", " ");
    }

    private void S0(g.k0 k0Var) {
        if (k0Var.f8246b == null || k0Var.f8233h == null) {
            return;
        }
        Matrix matrix = new Matrix();
        if (this.f8309g.peek().invert(matrix)) {
            g.b bVar = k0Var.f8233h;
            g.b bVar2 = k0Var.f8233h;
            g.b bVar3 = k0Var.f8233h;
            float[] fArr = {bVar.f8146a, bVar.f8147b, bVar.b(), bVar2.f8147b, bVar2.b(), k0Var.f8233h.c(), bVar3.f8146a, bVar3.c()};
            matrix.preConcat(this.f8303a.getMatrix());
            matrix.mapPoints(fArr);
            RectF rectF = new RectF(fArr[0], fArr[1], fArr[0], fArr[1]);
            for (int i7 = 2; i7 <= 6; i7 += 2) {
                if (fArr[i7] < rectF.left) {
                    rectF.left = fArr[i7];
                }
                if (fArr[i7] > rectF.right) {
                    rectF.right = fArr[i7];
                }
                int i8 = i7 + 1;
                if (fArr[i8] < rectF.top) {
                    rectF.top = fArr[i8];
                }
                if (fArr[i8] > rectF.bottom) {
                    rectF.bottom = fArr[i8];
                }
            }
            g.k0 k0Var2 = (g.k0) this.f8308f.peek();
            g.b bVar4 = k0Var2.f8233h;
            if (bVar4 == null) {
                k0Var2.f8233h = g.b.a(rectF.left, rectF.top, rectF.right, rectF.bottom);
            } else {
                bVar4.e(g.b.a(rectF.left, rectF.top, rectF.right, rectF.bottom));
            }
        }
    }

    private Path.FillType T() {
        g.e0.a aVar = this.f8306d.f8342a.f8170h;
        return (aVar == null || aVar != g.e0.a.EvenOdd) ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
    }

    private void T0(C0126h c0126h, g.e0 e0Var) {
        if (V(e0Var, 4096L)) {
            c0126h.f8342a.f8181s = e0Var.f8181s;
        }
        if (V(e0Var, 2048L)) {
            c0126h.f8342a.f8180r = e0Var.f8180r;
        }
        if (V(e0Var, 1L)) {
            c0126h.f8342a.f8169g = e0Var.f8169g;
            g.o0 o0Var = e0Var.f8169g;
            c0126h.f8343b = (o0Var == null || o0Var == g.f.f8204h) ? false : true;
        }
        if (V(e0Var, 4L)) {
            c0126h.f8342a.f8171i = e0Var.f8171i;
        }
        if (V(e0Var, 6149L)) {
            N0(c0126h, true, c0126h.f8342a.f8169g);
        }
        if (V(e0Var, 2L)) {
            c0126h.f8342a.f8170h = e0Var.f8170h;
        }
        if (V(e0Var, 8L)) {
            c0126h.f8342a.f8172j = e0Var.f8172j;
            g.o0 o0Var2 = e0Var.f8172j;
            c0126h.f8344c = (o0Var2 == null || o0Var2 == g.f.f8204h) ? false : true;
        }
        if (V(e0Var, 16L)) {
            c0126h.f8342a.f8173k = e0Var.f8173k;
        }
        if (V(e0Var, 6168L)) {
            N0(c0126h, false, c0126h.f8342a.f8172j);
        }
        if (V(e0Var, 34359738368L)) {
            c0126h.f8342a.Q = e0Var.Q;
        }
        if (V(e0Var, 32L)) {
            g.e0 e0Var2 = c0126h.f8342a;
            g.p pVar = e0Var.f8174l;
            e0Var2.f8174l = pVar;
            c0126h.f8346e.setStrokeWidth(pVar.c(this));
        }
        if (V(e0Var, 64L)) {
            c0126h.f8342a.f8175m = e0Var.f8175m;
            int i7 = a.f8312b[e0Var.f8175m.ordinal()];
            if (i7 == 1) {
                c0126h.f8346e.setStrokeCap(Paint.Cap.BUTT);
            } else if (i7 == 2) {
                c0126h.f8346e.setStrokeCap(Paint.Cap.ROUND);
            } else if (i7 == 3) {
                c0126h.f8346e.setStrokeCap(Paint.Cap.SQUARE);
            }
        }
        if (V(e0Var, 128L)) {
            c0126h.f8342a.f8176n = e0Var.f8176n;
            int i8 = a.f8313c[e0Var.f8176n.ordinal()];
            if (i8 == 1) {
                c0126h.f8346e.setStrokeJoin(Paint.Join.MITER);
            } else if (i8 == 2) {
                c0126h.f8346e.setStrokeJoin(Paint.Join.ROUND);
            } else if (i8 == 3) {
                c0126h.f8346e.setStrokeJoin(Paint.Join.BEVEL);
            }
        }
        if (V(e0Var, 256L)) {
            c0126h.f8342a.f8177o = e0Var.f8177o;
            c0126h.f8346e.setStrokeMiter(e0Var.f8177o.floatValue());
        }
        if (V(e0Var, 512L)) {
            c0126h.f8342a.f8178p = e0Var.f8178p;
        }
        if (V(e0Var, 1024L)) {
            c0126h.f8342a.f8179q = e0Var.f8179q;
        }
        Typeface typefaceT = null;
        if (V(e0Var, 1536L)) {
            g.p[] pVarArr = c0126h.f8342a.f8178p;
            if (pVarArr == null) {
                c0126h.f8346e.setPathEffect(null);
            } else {
                int length = pVarArr.length;
                int i9 = length % 2 == 0 ? length : length * 2;
                float[] fArr = new float[i9];
                float f7 = 0.0f;
                for (int i10 = 0; i10 < i9; i10++) {
                    fArr[i10] = c0126h.f8342a.f8178p[i10 % length].c(this);
                    f7 += fArr[i10];
                }
                if (f7 == 0.0f) {
                    c0126h.f8346e.setPathEffect(null);
                } else {
                    float fC = c0126h.f8342a.f8179q.c(this);
                    if (fC < 0.0f) {
                        fC = (fC % f7) + f7;
                    }
                    c0126h.f8346e.setPathEffect(new DashPathEffect(fArr, fC));
                }
            }
        }
        if (V(e0Var, 16384L)) {
            float fP = P();
            c0126h.f8342a.f8183u = e0Var.f8183u;
            c0126h.f8345d.setTextSize(e0Var.f8183u.d(this, fP));
            c0126h.f8346e.setTextSize(e0Var.f8183u.d(this, fP));
        }
        if (V(e0Var, 8192L)) {
            c0126h.f8342a.f8182t = e0Var.f8182t;
        }
        if (V(e0Var, 32768L)) {
            if (e0Var.f8184v.intValue() == -1 && c0126h.f8342a.f8184v.intValue() > 100) {
                g.e0 e0Var3 = c0126h.f8342a;
                e0Var3.f8184v = Integer.valueOf(e0Var3.f8184v.intValue() - 100);
            } else if (e0Var.f8184v.intValue() != 1 || c0126h.f8342a.f8184v.intValue() >= 900) {
                c0126h.f8342a.f8184v = e0Var.f8184v;
            } else {
                g.e0 e0Var4 = c0126h.f8342a;
                e0Var4.f8184v = Integer.valueOf(e0Var4.f8184v.intValue() + 100);
            }
        }
        if (V(e0Var, 65536L)) {
            c0126h.f8342a.f8185w = e0Var.f8185w;
        }
        if (V(e0Var, 106496L)) {
            if (c0126h.f8342a.f8182t != null && this.f8305c != null) {
                m2.g.k();
                for (String str : c0126h.f8342a.f8182t) {
                    g.e0 e0Var5 = c0126h.f8342a;
                    typefaceT = t(str, e0Var5.f8184v, e0Var5.f8185w);
                    if (typefaceT != null) {
                        break;
                    }
                }
            }
            if (typefaceT == null) {
                g.e0 e0Var6 = c0126h.f8342a;
                typefaceT = t("serif", e0Var6.f8184v, e0Var6.f8185w);
            }
            c0126h.f8345d.setTypeface(typefaceT);
            c0126h.f8346e.setTypeface(typefaceT);
        }
        if (V(e0Var, 131072L)) {
            c0126h.f8342a.f8186x = e0Var.f8186x;
            Paint paint = c0126h.f8345d;
            g.e0.EnumC0124g enumC0124g = e0Var.f8186x;
            g.e0.EnumC0124g enumC0124g2 = g.e0.EnumC0124g.LineThrough;
            paint.setStrikeThruText(enumC0124g == enumC0124g2);
            Paint paint2 = c0126h.f8345d;
            g.e0.EnumC0124g enumC0124g3 = e0Var.f8186x;
            g.e0.EnumC0124g enumC0124g4 = g.e0.EnumC0124g.Underline;
            paint2.setUnderlineText(enumC0124g3 == enumC0124g4);
            c0126h.f8346e.setStrikeThruText(e0Var.f8186x == enumC0124g2);
            c0126h.f8346e.setUnderlineText(e0Var.f8186x == enumC0124g4);
        }
        if (V(e0Var, 68719476736L)) {
            c0126h.f8342a.f8187y = e0Var.f8187y;
        }
        if (V(e0Var, 262144L)) {
            c0126h.f8342a.f8188z = e0Var.f8188z;
        }
        if (V(e0Var, 524288L)) {
            c0126h.f8342a.A = e0Var.A;
        }
        if (V(e0Var, 2097152L)) {
            c0126h.f8342a.C = e0Var.C;
        }
        if (V(e0Var, 4194304L)) {
            c0126h.f8342a.D = e0Var.D;
        }
        if (V(e0Var, 8388608L)) {
            c0126h.f8342a.E = e0Var.E;
        }
        if (V(e0Var, 16777216L)) {
            c0126h.f8342a.F = e0Var.F;
        }
        if (V(e0Var, 33554432L)) {
            c0126h.f8342a.G = e0Var.G;
        }
        if (V(e0Var, 1048576L)) {
            c0126h.f8342a.B = e0Var.B;
        }
        if (V(e0Var, 268435456L)) {
            c0126h.f8342a.J = e0Var.J;
        }
        if (V(e0Var, 536870912L)) {
            c0126h.f8342a.K = e0Var.K;
        }
        if (V(e0Var, 1073741824L)) {
            c0126h.f8342a.L = e0Var.L;
        }
        if (V(e0Var, 67108864L)) {
            c0126h.f8342a.H = e0Var.H;
        }
        if (V(e0Var, 134217728L)) {
            c0126h.f8342a.I = e0Var.I;
        }
        if (V(e0Var, 8589934592L)) {
            c0126h.f8342a.O = e0Var.O;
        }
        if (V(e0Var, 17179869184L)) {
            c0126h.f8342a.P = e0Var.P;
        }
        if (V(e0Var, 137438953472L)) {
            c0126h.f8342a.R = e0Var.R;
        }
    }

    private static synchronized void U() {
        HashSet<String> hashSet = new HashSet<>();
        f8302i = hashSet;
        hashSet.add("Structure");
        f8302i.add("BasicStructure");
        f8302i.add("ConditionalProcessing");
        f8302i.add("Image");
        f8302i.add("Style");
        f8302i.add("ViewportAttribute");
        f8302i.add("Shape");
        f8302i.add("BasicText");
        f8302i.add("PaintAttribute");
        f8302i.add("BasicPaintAttribute");
        f8302i.add("OpacityAttribute");
        f8302i.add("BasicGraphicsAttribute");
        f8302i.add("Marker");
        f8302i.add("Gradient");
        f8302i.add("Pattern");
        f8302i.add("Clip");
        f8302i.add("BasicClip");
        f8302i.add("Mask");
        f8302i.add("View");
    }

    private void U0(C0126h c0126h, g.l0 l0Var) {
        c0126h.f8342a.b(l0Var.f8246b == null);
        g.e0 e0Var = l0Var.f8237e;
        if (e0Var != null) {
            T0(c0126h, e0Var);
        }
        if (this.f8305c.n()) {
            for (b.p pVar : this.f8305c.d()) {
                if (m2.b.l(this.f8310h, pVar.f8098a, l0Var)) {
                    T0(c0126h, pVar.f8099b);
                }
            }
        }
        g.e0 e0Var2 = l0Var.f8238f;
        if (e0Var2 != null) {
            T0(c0126h, e0Var2);
        }
    }

    private boolean V(g.e0 e0Var, long j7) {
        return (e0Var.f8168e & j7) != 0;
    }

    private void V0() {
        int iW;
        g.e0 e0Var = this.f8306d.f8342a;
        g.o0 o0Var = e0Var.O;
        if (o0Var instanceof g.f) {
            iW = ((g.f) o0Var).f8205e;
        } else if (!(o0Var instanceof g.C0125g)) {
            return;
        } else {
            iW = e0Var.f8181s.f8205e;
        }
        Float f7 = e0Var.P;
        if (f7 != null) {
            iW = w(iW, f7.floatValue());
        }
        this.f8303a.drawColor(iW);
    }

    private void W(boolean z6, g.b bVar, g.m0 m0Var) {
        float f7;
        float fD;
        float f8;
        float f9;
        String str = m0Var.f8231l;
        if (str != null) {
            G(m0Var, str);
        }
        Boolean bool = m0Var.f8228i;
        int i7 = 0;
        boolean z7 = bool != null && bool.booleanValue();
        C0126h c0126h = this.f8306d;
        Paint paint = z6 ? c0126h.f8345d : c0126h.f8346e;
        if (z7) {
            g.b bVarR = R();
            g.p pVar = m0Var.f8241m;
            float fE = pVar != null ? pVar.e(this) : 0.0f;
            g.p pVar2 = m0Var.f8242n;
            float f10 = pVar2 != null ? pVar2.f(this) : 0.0f;
            g.p pVar3 = m0Var.f8243o;
            float fE2 = pVar3 != null ? pVar3.e(this) : bVarR.f8148c;
            g.p pVar4 = m0Var.f8244p;
            f9 = fE2;
            f7 = fE;
            f8 = f10;
            fD = pVar4 != null ? pVar4.f(this) : 0.0f;
        } else {
            g.p pVar5 = m0Var.f8241m;
            float fD2 = pVar5 != null ? pVar5.d(this, 1.0f) : 0.0f;
            g.p pVar6 = m0Var.f8242n;
            float fD3 = pVar6 != null ? pVar6.d(this, 1.0f) : 0.0f;
            g.p pVar7 = m0Var.f8243o;
            float fD4 = pVar7 != null ? pVar7.d(this, 1.0f) : 1.0f;
            g.p pVar8 = m0Var.f8244p;
            f7 = fD2;
            fD = pVar8 != null ? pVar8.d(this, 1.0f) : 0.0f;
            f8 = fD3;
            f9 = fD4;
        }
        Q0();
        this.f8306d = L(m0Var);
        Matrix matrix = new Matrix();
        if (!z7) {
            matrix.preTranslate(bVar.f8146a, bVar.f8147b);
            matrix.preScale(bVar.f8148c, bVar.f8149d);
        }
        Matrix matrix2 = m0Var.f8229j;
        if (matrix2 != null) {
            matrix.preConcat(matrix2);
        }
        int size = m0Var.f8227h.size();
        if (size == 0) {
            P0();
            if (z6) {
                this.f8306d.f8343b = false;
                return;
            } else {
                this.f8306d.f8344c = false;
                return;
            }
        }
        int[] iArr = new int[size];
        float[] fArr = new float[size];
        float f11 = -1.0f;
        Iterator<g.n0> it = m0Var.f8227h.iterator();
        while (it.hasNext()) {
            g.d0 d0Var = (g.d0) it.next();
            Float f12 = d0Var.f8165h;
            float fFloatValue = f12 != null ? f12.floatValue() : 0.0f;
            if (i7 == 0 || fFloatValue >= f11) {
                fArr[i7] = fFloatValue;
                f11 = fFloatValue;
            } else {
                fArr[i7] = f11;
            }
            Q0();
            U0(this.f8306d, d0Var);
            g.e0 e0Var = this.f8306d.f8342a;
            g.f fVar = (g.f) e0Var.H;
            if (fVar == null) {
                fVar = g.f.f8203g;
            }
            iArr[i7] = w(fVar.f8205e, e0Var.I.floatValue());
            i7++;
            P0();
        }
        if ((f7 == f9 && f8 == fD) || size == 1) {
            P0();
            paint.setColor(iArr[size - 1]);
            return;
        }
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        g.k kVar = m0Var.f8230k;
        if (kVar != null) {
            if (kVar == g.k.reflect) {
                tileMode = Shader.TileMode.MIRROR;
            } else if (kVar == g.k.repeat) {
                tileMode = Shader.TileMode.REPEAT;
            }
        }
        P0();
        LinearGradient linearGradient = new LinearGradient(f7, f8, f9, fD, iArr, fArr, tileMode);
        linearGradient.setLocalMatrix(matrix);
        paint.setShader(linearGradient);
        paint.setAlpha(v(this.f8306d.f8342a.f8171i.floatValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean W0() {
        Boolean bool = this.f8306d.f8342a.G;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    private Path X(g.d dVar) {
        g.p pVar = dVar.f8162o;
        float fE = pVar != null ? pVar.e(this) : 0.0f;
        g.p pVar2 = dVar.f8163p;
        float f7 = pVar2 != null ? pVar2.f(this) : 0.0f;
        float fC = dVar.f8164q.c(this);
        float f8 = fE - fC;
        float f9 = f7 - fC;
        float f10 = fE + fC;
        float f11 = f7 + fC;
        if (dVar.f8233h == null) {
            float f12 = 2.0f * fC;
            dVar.f8233h = new g.b(f8, f9, f12, f12);
        }
        float f13 = 0.5522848f * fC;
        Path path = new Path();
        path.moveTo(fE, f9);
        float f14 = fE + f13;
        float f15 = f7 - f13;
        path.cubicTo(f14, f9, f10, f15, f10, f7);
        float f16 = f7 + f13;
        path.cubicTo(f10, f16, f14, f11, fE, f11);
        float f17 = fE - f13;
        path.cubicTo(f17, f11, f8, f16, f8, f7);
        path.cubicTo(f8, f15, f17, f9, fE, f9);
        path.close();
        return path;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void X0(String str, Object... objArr) {
        Log.w("SVGAndroidRenderer", String.format(str, objArr));
    }

    private Path Y(g.i iVar) {
        g.p pVar = iVar.f8218o;
        float fE = pVar != null ? pVar.e(this) : 0.0f;
        g.p pVar2 = iVar.f8219p;
        float f7 = pVar2 != null ? pVar2.f(this) : 0.0f;
        float fE2 = iVar.f8220q.e(this);
        float f8 = iVar.f8221r.f(this);
        float f9 = fE - fE2;
        float f10 = f7 - f8;
        float f11 = fE + fE2;
        float f12 = f7 + f8;
        if (iVar.f8233h == null) {
            iVar.f8233h = new g.b(f9, f10, fE2 * 2.0f, 2.0f * f8);
        }
        float f13 = fE2 * 0.5522848f;
        float f14 = 0.5522848f * f8;
        Path path = new Path();
        path.moveTo(fE, f10);
        float f15 = fE + f13;
        float f16 = f7 - f14;
        path.cubicTo(f15, f10, f11, f16, f11, f7);
        float f17 = f14 + f7;
        path.cubicTo(f11, f17, f15, f12, fE, f12);
        float f18 = fE - f13;
        path.cubicTo(f18, f12, f9, f17, f9, f7);
        path.cubicTo(f9, f16, f18, f10, fE, f10);
        path.close();
        return path;
    }

    private Path Z(g.q qVar) {
        g.p pVar = qVar.f8256o;
        float fE = pVar == null ? 0.0f : pVar.e(this);
        g.p pVar2 = qVar.f8257p;
        float f7 = pVar2 == null ? 0.0f : pVar2.f(this);
        g.p pVar3 = qVar.f8258q;
        float fE2 = pVar3 == null ? 0.0f : pVar3.e(this);
        g.p pVar4 = qVar.f8259r;
        float f8 = pVar4 != null ? pVar4.f(this) : 0.0f;
        if (qVar.f8233h == null) {
            qVar.f8233h = new g.b(Math.min(fE, fE2), Math.min(f7, f8), Math.abs(fE2 - fE), Math.abs(f8 - f7));
        }
        Path path = new Path();
        path.moveTo(fE, f7);
        path.lineTo(fE2, f8);
        return path;
    }

    private Path a0(g.z zVar) {
        Path path = new Path();
        float[] fArr = zVar.f8298o;
        path.moveTo(fArr[0], fArr[1]);
        int i7 = 2;
        while (true) {
            float[] fArr2 = zVar.f8298o;
            if (i7 >= fArr2.length) {
                break;
            }
            path.lineTo(fArr2[i7], fArr2[i7 + 1]);
            i7 += 2;
        }
        if (zVar instanceof g.a0) {
            path.close();
        }
        if (zVar.f8233h == null) {
            zVar.f8233h = m(path);
        }
        return path;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.Path b0(m2.g.b0 r24) {
        /*
            Method dump skipped, instruction units count: 241
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: m2.h.b0(m2.g$b0):android.graphics.Path");
    }

    private Path c0(g.w0 w0Var) {
        List<g.p> list = w0Var.f8142o;
        float f7 = 0.0f;
        float fE = (list == null || list.size() == 0) ? 0.0f : w0Var.f8142o.get(0).e(this);
        List<g.p> list2 = w0Var.f8143p;
        float f8 = (list2 == null || list2.size() == 0) ? 0.0f : w0Var.f8143p.get(0).f(this);
        List<g.p> list3 = w0Var.f8144q;
        float fE2 = (list3 == null || list3.size() == 0) ? 0.0f : w0Var.f8144q.get(0).e(this);
        List<g.p> list4 = w0Var.f8145r;
        if (list4 != null && list4.size() != 0) {
            f7 = w0Var.f8145r.get(0).f(this);
        }
        if (this.f8306d.f8342a.f8188z != g.e0.f.Start) {
            float fN = n(w0Var);
            if (this.f8306d.f8342a.f8188z == g.e0.f.Middle) {
                fN /= 2.0f;
            }
            fE -= fN;
        }
        if (w0Var.f8233h == null) {
            i iVar = new i(fE, f8);
            D(w0Var, iVar);
            RectF rectF = iVar.f8353d;
            w0Var.f8233h = new g.b(rectF.left, rectF.top, rectF.width(), iVar.f8353d.height());
        }
        Path path = new Path();
        D(w0Var, new g(fE + fE2, f8 + f7, path));
        return path;
    }

    private void d0(boolean z6, g.b bVar, g.q0 q0Var) {
        float f7;
        float fD;
        float f8;
        String str = q0Var.f8231l;
        if (str != null) {
            G(q0Var, str);
        }
        Boolean bool = q0Var.f8228i;
        int i7 = 0;
        boolean z7 = bool != null && bool.booleanValue();
        C0126h c0126h = this.f8306d;
        Paint paint = z6 ? c0126h.f8345d : c0126h.f8346e;
        if (z7) {
            g.p pVar = new g.p(50.0f, g.d1.percent);
            g.p pVar2 = q0Var.f8260m;
            float fE = pVar2 != null ? pVar2.e(this) : pVar.e(this);
            g.p pVar3 = q0Var.f8261n;
            float f9 = pVar3 != null ? pVar3.f(this) : pVar.f(this);
            g.p pVar4 = q0Var.f8262o;
            fD = pVar4 != null ? pVar4.c(this) : pVar.c(this);
            f7 = fE;
            f8 = f9;
        } else {
            g.p pVar5 = q0Var.f8260m;
            float fD2 = pVar5 != null ? pVar5.d(this, 1.0f) : 0.5f;
            g.p pVar6 = q0Var.f8261n;
            float fD3 = pVar6 != null ? pVar6.d(this, 1.0f) : 0.5f;
            g.p pVar7 = q0Var.f8262o;
            f7 = fD2;
            fD = pVar7 != null ? pVar7.d(this, 1.0f) : 0.5f;
            f8 = fD3;
        }
        Q0();
        this.f8306d = L(q0Var);
        Matrix matrix = new Matrix();
        if (!z7) {
            matrix.preTranslate(bVar.f8146a, bVar.f8147b);
            matrix.preScale(bVar.f8148c, bVar.f8149d);
        }
        Matrix matrix2 = q0Var.f8229j;
        if (matrix2 != null) {
            matrix.preConcat(matrix2);
        }
        int size = q0Var.f8227h.size();
        if (size == 0) {
            P0();
            if (z6) {
                this.f8306d.f8343b = false;
                return;
            } else {
                this.f8306d.f8344c = false;
                return;
            }
        }
        int[] iArr = new int[size];
        float[] fArr = new float[size];
        float f10 = -1.0f;
        Iterator<g.n0> it = q0Var.f8227h.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            g.d0 d0Var = (g.d0) it.next();
            Float f11 = d0Var.f8165h;
            float fFloatValue = f11 != null ? f11.floatValue() : 0.0f;
            if (i7 == 0 || fFloatValue >= f10) {
                fArr[i7] = fFloatValue;
                f10 = fFloatValue;
            } else {
                fArr[i7] = f10;
            }
            Q0();
            U0(this.f8306d, d0Var);
            g.e0 e0Var = this.f8306d.f8342a;
            g.f fVar = (g.f) e0Var.H;
            if (fVar == null) {
                fVar = g.f.f8203g;
            }
            iArr[i7] = w(fVar.f8205e, e0Var.I.floatValue());
            i7++;
            P0();
        }
        if (fD == 0.0f || size == 1) {
            P0();
            paint.setColor(iArr[size - 1]);
            return;
        }
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        g.k kVar = q0Var.f8230k;
        if (kVar != null) {
            if (kVar == g.k.reflect) {
                tileMode = Shader.TileMode.MIRROR;
            } else if (kVar == g.k.repeat) {
                tileMode = Shader.TileMode.REPEAT;
            }
        }
        P0();
        RadialGradient radialGradient = new RadialGradient(f7, f8, fD, iArr, fArr, tileMode);
        radialGradient.setLocalMatrix(matrix);
        paint.setShader(radialGradient);
        paint.setAlpha(v(this.f8306d.f8342a.f8171i.floatValue()));
    }

    private g.b e0(g.p pVar, g.p pVar2, g.p pVar3, g.p pVar4) {
        float fE = pVar != null ? pVar.e(this) : 0.0f;
        float f7 = pVar2 != null ? pVar2.f(this) : 0.0f;
        g.b bVarR = R();
        return new g.b(fE, f7, pVar3 != null ? pVar3.e(this) : bVarR.f8148c, pVar4 != null ? pVar4.f(this) : bVarR.f8149d);
    }

    @TargetApi(19)
    private Path f0(g.k0 k0Var, boolean z6) {
        Path pathC0;
        Path pathJ;
        this.f8307e.push(this.f8306d);
        C0126h c0126h = new C0126h(this.f8306d);
        this.f8306d = c0126h;
        U0(c0126h, k0Var);
        if (!z() || !W0()) {
            this.f8306d = this.f8307e.pop();
            return null;
        }
        if (k0Var instanceof g.e1) {
            if (!z6) {
                E("<use> elements inside a <clipPath> cannot reference another <use>", new Object[0]);
            }
            g.e1 e1Var = (g.e1) k0Var;
            g.n0 n0VarR = k0Var.f8245a.r(e1Var.f8198p);
            if (n0VarR == null) {
                E("Use reference '%s' not found", e1Var.f8198p);
                this.f8306d = this.f8307e.pop();
                return null;
            }
            if (!(n0VarR instanceof g.k0)) {
                this.f8306d = this.f8307e.pop();
                return null;
            }
            pathC0 = f0((g.k0) n0VarR, false);
            if (pathC0 == null) {
                return null;
            }
            if (e1Var.f8233h == null) {
                e1Var.f8233h = m(pathC0);
            }
            Matrix matrix = e1Var.f8240o;
            if (matrix != null) {
                pathC0.transform(matrix);
            }
        } else if (k0Var instanceof g.l) {
            g.l lVar = (g.l) k0Var;
            if (k0Var instanceof g.v) {
                pathC0 = new d(((g.v) k0Var).f8282o).f();
                if (k0Var.f8233h == null) {
                    k0Var.f8233h = m(pathC0);
                }
            } else {
                pathC0 = k0Var instanceof g.b0 ? b0((g.b0) k0Var) : k0Var instanceof g.d ? X((g.d) k0Var) : k0Var instanceof g.i ? Y((g.i) k0Var) : k0Var instanceof g.z ? a0((g.z) k0Var) : null;
            }
            if (pathC0 == null) {
                return null;
            }
            if (lVar.f8233h == null) {
                lVar.f8233h = m(pathC0);
            }
            Matrix matrix2 = lVar.f8234n;
            if (matrix2 != null) {
                pathC0.transform(matrix2);
            }
            pathC0.setFillType(O());
        } else {
            if (!(k0Var instanceof g.w0)) {
                E("Invalid %s element found in clipPath definition", k0Var.getClass().getSimpleName());
                return null;
            }
            g.w0 w0Var = (g.w0) k0Var;
            pathC0 = c0(w0Var);
            if (pathC0 == null) {
                return null;
            }
            Matrix matrix3 = w0Var.f8289s;
            if (matrix3 != null) {
                pathC0.transform(matrix3);
            }
            pathC0.setFillType(O());
        }
        if (this.f8306d.f8342a.J != null && (pathJ = j(k0Var, k0Var.f8233h)) != null) {
            pathC0.op(pathJ, Path.Op.INTERSECT);
        }
        this.f8306d = this.f8307e.pop();
        return pathC0;
    }

    private void g0() {
        this.f8308f.pop();
        this.f8309g.pop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void h(float f7, float f8, float f9, float f10, float f11, boolean z6, boolean z7, float f12, float f13, g.x xVar) {
        float f14;
        double d7;
        if (f7 == f12 && f8 == f13) {
            return;
        }
        if (f9 == 0.0f || f10 == 0.0f) {
            xVar.d(f12, f13);
            return;
        }
        float fAbs = Math.abs(f9);
        float fAbs2 = Math.abs(f10);
        double radians = (float) Math.toRadians(((double) f11) % 360.0d);
        float fCos = (float) Math.cos(radians);
        float fSin = (float) Math.sin(radians);
        float f15 = (f7 - f12) / 2.0f;
        float f16 = (f8 - f13) / 2.0f;
        float f17 = (fCos * f15) + (fSin * f16);
        float f18 = ((-fSin) * f15) + (f16 * fCos);
        float f19 = fAbs * fAbs;
        float f20 = fAbs2 * fAbs2;
        float f21 = f17 * f17;
        float f22 = f18 * f18;
        float f23 = (f21 / f19) + (f22 / f20);
        if (f23 > 1.0f) {
            double d8 = f23;
            f14 = fCos;
            fAbs *= (float) Math.sqrt(d8);
            fAbs2 *= (float) Math.sqrt(d8);
            f19 = fAbs * fAbs;
            f20 = fAbs2 * fAbs2;
        } else {
            f14 = fCos;
        }
        float f24 = z6 == z7 ? -1.0f : 1.0f;
        float f25 = f19 * f20;
        float f26 = f19 * f22;
        float f27 = f20 * f21;
        float f28 = ((f25 - f26) - f27) / (f26 + f27);
        if (f28 < 0.0f) {
            f28 = 0.0f;
        }
        float fSqrt = (float) (((double) f24) * Math.sqrt(f28));
        float f29 = ((fAbs * f18) / fAbs2) * fSqrt;
        float f30 = fSqrt * (-((fAbs2 * f17) / fAbs));
        float f31 = ((f7 + f12) / 2.0f) + ((f14 * f29) - (fSin * f30));
        float f32 = ((f8 + f13) / 2.0f) + (fSin * f29) + (f14 * f30);
        float f33 = (f17 - f29) / fAbs;
        float f34 = (f18 - f30) / fAbs2;
        float f35 = ((-f17) - f29) / fAbs;
        float f36 = ((-f18) - f30) / fAbs2;
        float f37 = (f33 * f33) + (f34 * f34);
        float f38 = fAbs;
        float degrees = (float) Math.toDegrees(((double) (f34 < 0.0f ? -1.0f : 1.0f)) * Math.acos(f33 / ((float) Math.sqrt(f37))));
        double degrees2 = Math.toDegrees(((double) ((f33 * f36) - (f34 * f35) < 0.0f ? -1.0f : 1.0f)) * Math.acos(((f33 * f35) + (f34 * f36)) / ((float) Math.sqrt(f37 * ((f35 * f35) + (f36 * f36))))));
        if (z7 || degrees2 <= 0.0d) {
            d7 = 360.0d;
            if (z7 && degrees2 < 0.0d) {
                degrees2 += 360.0d;
            }
        } else {
            d7 = 360.0d;
            degrees2 -= 360.0d;
        }
        float[] fArrI = i(degrees % 360.0f, degrees2 % d7);
        Matrix matrix = new Matrix();
        matrix.postScale(f38, fAbs2);
        matrix.postRotate(f11);
        matrix.postTranslate(f31, f32);
        matrix.mapPoints(fArrI);
        fArrI[fArrI.length - 2] = f12;
        fArrI[fArrI.length - 1] = f13;
        for (int i7 = 0; i7 < fArrI.length; i7 += 6) {
            xVar.c(fArrI[i7], fArrI[i7 + 1], fArrI[i7 + 2], fArrI[i7 + 3], fArrI[i7 + 4], fArrI[i7 + 5]);
        }
    }

    private void h0(g.j0 j0Var) {
        this.f8308f.push(j0Var);
        this.f8309g.push(this.f8303a.getMatrix());
    }

    private static float[] i(double d7, double d8) {
        int iCeil = (int) Math.ceil(Math.abs(d8) / 90.0d);
        double radians = Math.toRadians(d7);
        float radians2 = (float) (Math.toRadians(d8) / ((double) iCeil));
        double d9 = radians2;
        double d10 = d9 / 2.0d;
        double dSin = (Math.sin(d10) * 1.3333333333333333d) / (Math.cos(d10) + 1.0d);
        float[] fArr = new float[iCeil * 6];
        int i7 = 0;
        int i8 = 0;
        while (i7 < iCeil) {
            double d11 = ((double) (i7 * radians2)) + radians;
            double dCos = Math.cos(d11);
            double dSin2 = Math.sin(d11);
            int i9 = i8 + 1;
            int i10 = iCeil;
            double d12 = radians;
            fArr[i8] = (float) (dCos - (dSin * dSin2));
            int i11 = i9 + 1;
            fArr[i9] = (float) (dSin2 + (dCos * dSin));
            double d13 = d11 + d9;
            double dCos2 = Math.cos(d13);
            double dSin3 = Math.sin(d13);
            int i12 = i11 + 1;
            fArr[i11] = (float) ((dSin * dSin3) + dCos2);
            int i13 = i12 + 1;
            fArr[i12] = (float) (dSin3 - (dSin * dCos2));
            int i14 = i13 + 1;
            fArr[i13] = (float) dCos2;
            fArr[i14] = (float) dSin3;
            i7++;
            radians = d12;
            i8 = i14 + 1;
            iCeil = i10;
        }
        return fArr;
    }

    private void i0(g.k0 k0Var) {
        if (this.f8306d.f8342a.L != null) {
            Paint paint = new Paint();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            this.f8303a.saveLayer(null, paint, 31);
            Paint paint2 = new Paint();
            paint2.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.2127f, 0.7151f, 0.0722f, 0.0f, 0.0f})));
            this.f8303a.saveLayer(null, paint2, 31);
            g.s sVar = (g.s) this.f8305c.r(this.f8306d.f8342a.L);
            H0(sVar, k0Var);
            this.f8303a.restore();
            Paint paint3 = new Paint();
            paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            this.f8303a.saveLayer(null, paint3, 31);
            H0(sVar, k0Var);
            this.f8303a.restore();
            this.f8303a.restore();
        }
        P0();
    }

    @TargetApi(19)
    private Path j(g.k0 k0Var, g.b bVar) {
        Path pathF0;
        g.n0 n0VarR = k0Var.f8245a.r(this.f8306d.f8342a.J);
        if (n0VarR == null) {
            E("ClipPath reference '%s' not found", this.f8306d.f8342a.J);
            return null;
        }
        g.e eVar = (g.e) n0VarR;
        this.f8307e.push(this.f8306d);
        this.f8306d = L(eVar);
        Boolean bool = eVar.f8167p;
        boolean z6 = bool == null || bool.booleanValue();
        Matrix matrix = new Matrix();
        if (!z6) {
            matrix.preTranslate(bVar.f8146a, bVar.f8147b);
            matrix.preScale(bVar.f8148c, bVar.f8149d);
        }
        Matrix matrix2 = eVar.f8240o;
        if (matrix2 != null) {
            matrix.preConcat(matrix2);
        }
        Path path = new Path();
        for (g.n0 n0Var : eVar.f8212i) {
            if ((n0Var instanceof g.k0) && (pathF0 = f0((g.k0) n0Var, true)) != null) {
                path.op(pathF0, Path.Op.UNION);
            }
        }
        if (this.f8306d.f8342a.J != null) {
            if (eVar.f8233h == null) {
                eVar.f8233h = m(path);
            }
            Path pathJ = j(eVar, eVar.f8233h);
            if (pathJ != null) {
                path.op(pathJ, Path.Op.INTERSECT);
            }
        }
        path.transform(matrix);
        this.f8306d = this.f8307e.pop();
        return path;
    }

    private void j0(g.n0 n0Var, j jVar) {
        float f7;
        float f8;
        float fE;
        g.e0.f fVarN;
        if (jVar.a((g.y0) n0Var)) {
            if (n0Var instanceof g.z0) {
                Q0();
                J0((g.z0) n0Var);
                P0();
                return;
            }
            if (!(n0Var instanceof g.v0)) {
                if (n0Var instanceof g.u0) {
                    Q0();
                    g.u0 u0Var = (g.u0) n0Var;
                    U0(this.f8306d, u0Var);
                    if (z()) {
                        r((g.k0) u0Var.h());
                        g.n0 n0VarR = n0Var.f8245a.r(u0Var.f8280o);
                        if (n0VarR == null || !(n0VarR instanceof g.y0)) {
                            E("Tref reference '%s' not found", u0Var.f8280o);
                        } else {
                            StringBuilder sb = new StringBuilder();
                            F((g.y0) n0VarR, sb);
                            if (sb.length() > 0) {
                                jVar.b(sb.toString());
                            }
                        }
                    }
                    P0();
                    return;
                }
                return;
            }
            x("TSpan render", new Object[0]);
            Q0();
            g.v0 v0Var = (g.v0) n0Var;
            U0(this.f8306d, v0Var);
            if (z()) {
                List<g.p> list = v0Var.f8142o;
                boolean z6 = list != null && list.size() > 0;
                boolean z7 = jVar instanceof f;
                float f9 = 0.0f;
                if (z7) {
                    float fE2 = !z6 ? ((f) jVar).f8335b : v0Var.f8142o.get(0).e(this);
                    List<g.p> list2 = v0Var.f8143p;
                    f8 = (list2 == null || list2.size() == 0) ? ((f) jVar).f8336c : v0Var.f8143p.get(0).f(this);
                    List<g.p> list3 = v0Var.f8144q;
                    fE = (list3 == null || list3.size() == 0) ? 0.0f : v0Var.f8144q.get(0).e(this);
                    List<g.p> list4 = v0Var.f8145r;
                    if (list4 != null && list4.size() != 0) {
                        f9 = v0Var.f8145r.get(0).f(this);
                    }
                    f7 = f9;
                    f9 = fE2;
                } else {
                    f7 = 0.0f;
                    f8 = 0.0f;
                    fE = 0.0f;
                }
                if (z6 && (fVarN = N()) != g.e0.f.Start) {
                    float fN = n(v0Var);
                    if (fVarN == g.e0.f.Middle) {
                        fN /= 2.0f;
                    }
                    f9 -= fN;
                }
                r((g.k0) v0Var.h());
                if (z7) {
                    f fVar = (f) jVar;
                    fVar.f8335b = f9 + fE;
                    fVar.f8336c = f8 + f7;
                }
                boolean zK0 = k0();
                D(v0Var, jVar);
                if (zK0) {
                    i0(v0Var);
                }
            }
            P0();
        }
    }

    private List<c> k(g.q qVar) {
        g.p pVar = qVar.f8256o;
        float fE = pVar != null ? pVar.e(this) : 0.0f;
        g.p pVar2 = qVar.f8257p;
        float f7 = pVar2 != null ? pVar2.f(this) : 0.0f;
        g.p pVar3 = qVar.f8258q;
        float fE2 = pVar3 != null ? pVar3.e(this) : 0.0f;
        g.p pVar4 = qVar.f8259r;
        float f8 = pVar4 != null ? pVar4.f(this) : 0.0f;
        ArrayList arrayList = new ArrayList(2);
        float f9 = fE2 - fE;
        float f10 = f8 - f7;
        arrayList.add(new c(fE, f7, f9, f10));
        arrayList.add(new c(fE2, f8, f9, f10));
        return arrayList;
    }

    private boolean k0() {
        g.n0 n0VarR;
        if (!K0()) {
            return false;
        }
        this.f8303a.saveLayerAlpha(null, v(this.f8306d.f8342a.f8180r.floatValue()), 31);
        this.f8307e.push(this.f8306d);
        C0126h c0126h = new C0126h(this.f8306d);
        this.f8306d = c0126h;
        String str = c0126h.f8342a.L;
        if (str != null && ((n0VarR = this.f8305c.r(str)) == null || !(n0VarR instanceof g.s))) {
            E("Mask reference '%s' not found", this.f8306d.f8342a.L);
            this.f8306d.f8342a.L = null;
        }
        return true;
    }

    private List<c> l(g.z zVar) {
        int length = zVar.f8298o.length;
        int i7 = 2;
        if (length < 2) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        float[] fArr = zVar.f8298o;
        c cVar = new c(fArr[0], fArr[1], 0.0f, 0.0f);
        float f7 = 0.0f;
        float f8 = 0.0f;
        while (i7 < length) {
            float[] fArr2 = zVar.f8298o;
            float f9 = fArr2[i7];
            float f10 = fArr2[i7 + 1];
            cVar.a(f9, f10);
            arrayList.add(cVar);
            i7 += 2;
            cVar = new c(f9, f10, f9 - cVar.f8323a, f10 - cVar.f8324b);
            f8 = f10;
            f7 = f9;
        }
        if (zVar instanceof g.a0) {
            float[] fArr3 = zVar.f8298o;
            if (f7 != fArr3[0] && f8 != fArr3[1]) {
                float f11 = fArr3[0];
                float f12 = fArr3[1];
                cVar.a(f11, f12);
                arrayList.add(cVar);
                c cVar2 = new c(f11, f12, f11 - cVar.f8323a, f12 - cVar.f8324b);
                cVar2.b((c) arrayList.get(0));
                arrayList.add(cVar2);
                arrayList.set(0, cVar2);
            }
        } else {
            arrayList.add(cVar);
        }
        return arrayList;
    }

    private c l0(c cVar, c cVar2, c cVar3) {
        float fC = C(cVar2.f8325c, cVar2.f8326d, cVar2.f8323a - cVar.f8323a, cVar2.f8324b - cVar.f8324b);
        if (fC == 0.0f) {
            fC = C(cVar2.f8325c, cVar2.f8326d, cVar3.f8323a - cVar2.f8323a, cVar3.f8324b - cVar2.f8324b);
        }
        if (fC > 0.0f) {
            return cVar2;
        }
        if (fC == 0.0f && (cVar2.f8325c > 0.0f || cVar2.f8326d >= 0.0f)) {
            return cVar2;
        }
        cVar2.f8325c = -cVar2.f8325c;
        cVar2.f8326d = -cVar2.f8326d;
        return cVar2;
    }

    private g.b m(Path path) {
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        return new g.b(rectF.left, rectF.top, rectF.width(), rectF.height());
    }

    private void m0(g.d dVar) {
        x("Circle render", new Object[0]);
        g.p pVar = dVar.f8164q;
        if (pVar == null || pVar.h()) {
            return;
        }
        U0(this.f8306d, dVar);
        if (z() && W0()) {
            Matrix matrix = dVar.f8234n;
            if (matrix != null) {
                this.f8303a.concat(matrix);
            }
            Path pathX = X(dVar);
            S0(dVar);
            r(dVar);
            p(dVar);
            boolean zK0 = k0();
            if (this.f8306d.f8343b) {
                A(dVar, pathX);
            }
            if (this.f8306d.f8344c) {
                B(pathX);
            }
            if (zK0) {
                i0(dVar);
            }
        }
    }

    private float n(g.y0 y0Var) {
        k kVar = new k(this, null);
        D(y0Var, kVar);
        return kVar.f8356b;
    }

    private void n0(g.i iVar) {
        x("Ellipse render", new Object[0]);
        g.p pVar = iVar.f8220q;
        if (pVar == null || iVar.f8221r == null || pVar.h() || iVar.f8221r.h()) {
            return;
        }
        U0(this.f8306d, iVar);
        if (z() && W0()) {
            Matrix matrix = iVar.f8234n;
            if (matrix != null) {
                this.f8303a.concat(matrix);
            }
            Path pathY = Y(iVar);
            S0(iVar);
            r(iVar);
            p(iVar);
            boolean zK0 = k0();
            if (this.f8306d.f8343b) {
                A(iVar, pathY);
            }
            if (this.f8306d.f8344c) {
                B(pathY);
            }
            if (zK0) {
                i0(iVar);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0082, code lost:
    
        if (r12 != 8) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.Matrix o(m2.g.b r10, m2.g.b r11, m2.e r12) {
        /*
            r9 = this;
            android.graphics.Matrix r0 = new android.graphics.Matrix
            r0.<init>()
            if (r12 == 0) goto L9b
            m2.e$a r1 = r12.a()
            if (r1 != 0) goto Lf
            goto L9b
        Lf:
            float r1 = r10.f8148c
            float r2 = r11.f8148c
            float r1 = r1 / r2
            float r2 = r10.f8149d
            float r3 = r11.f8149d
            float r2 = r2 / r3
            float r3 = r11.f8146a
            float r3 = -r3
            float r4 = r11.f8147b
            float r4 = -r4
            m2.e r5 = m2.e.f8116d
            boolean r5 = r12.equals(r5)
            if (r5 == 0) goto L35
            float r11 = r10.f8146a
            float r10 = r10.f8147b
            r0.preTranslate(r11, r10)
            r0.preScale(r1, r2)
            r0.preTranslate(r3, r4)
            return r0
        L35:
            m2.e$b r5 = r12.b()
            m2.e$b r6 = m2.e.b.slice
            if (r5 != r6) goto L42
            float r1 = java.lang.Math.max(r1, r2)
            goto L46
        L42:
            float r1 = java.lang.Math.min(r1, r2)
        L46:
            float r2 = r10.f8148c
            float r2 = r2 / r1
            float r5 = r10.f8149d
            float r5 = r5 / r1
            int[] r6 = m2.h.a.f8311a
            m2.e$a r7 = r12.a()
            int r7 = r7.ordinal()
            r7 = r6[r7]
            r8 = 1073741824(0x40000000, float:2.0)
            switch(r7) {
                case 1: goto L62;
                case 2: goto L62;
                case 3: goto L62;
                case 4: goto L5e;
                case 5: goto L5e;
                case 6: goto L5e;
                default: goto L5d;
            }
        L5d:
            goto L67
        L5e:
            float r7 = r11.f8148c
            float r7 = r7 - r2
            goto L66
        L62:
            float r7 = r11.f8148c
            float r7 = r7 - r2
            float r7 = r7 / r8
        L66:
            float r3 = r3 - r7
        L67:
            m2.e$a r12 = r12.a()
            int r12 = r12.ordinal()
            r12 = r6[r12]
            r2 = 2
            if (r12 == r2) goto L89
            r2 = 3
            if (r12 == r2) goto L85
            r2 = 5
            if (r12 == r2) goto L89
            r2 = 6
            if (r12 == r2) goto L85
            r2 = 7
            if (r12 == r2) goto L89
            r2 = 8
            if (r12 == r2) goto L85
            goto L8e
        L85:
            float r11 = r11.f8149d
            float r11 = r11 - r5
            goto L8d
        L89:
            float r11 = r11.f8149d
            float r11 = r11 - r5
            float r11 = r11 / r8
        L8d:
            float r4 = r4 - r11
        L8e:
            float r11 = r10.f8146a
            float r10 = r10.f8147b
            r0.preTranslate(r11, r10)
            r0.preScale(r1, r1)
            r0.preTranslate(r3, r4)
        L9b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m2.h.o(m2.g$b, m2.g$b, m2.e):android.graphics.Matrix");
    }

    private void o0(g.m mVar) {
        x("Group render", new Object[0]);
        U0(this.f8306d, mVar);
        if (z()) {
            Matrix matrix = mVar.f8240o;
            if (matrix != null) {
                this.f8303a.concat(matrix);
            }
            p(mVar);
            boolean zK0 = k0();
            D0(mVar, true);
            if (zK0) {
                i0(mVar);
            }
            S0(mVar);
        }
    }

    private void p(g.k0 k0Var) {
        q(k0Var, k0Var.f8233h);
    }

    private void p0(g.o oVar) {
        g.p pVar;
        String str;
        x("Image render", new Object[0]);
        g.p pVar2 = oVar.f8250s;
        if (pVar2 == null || pVar2.h() || (pVar = oVar.f8251t) == null || pVar.h() || (str = oVar.f8247p) == null) {
            return;
        }
        m2.e eVar = oVar.f8255o;
        if (eVar == null) {
            eVar = m2.e.f8117e;
        }
        Bitmap bitmapS = s(str);
        if (bitmapS == null) {
            m2.g.k();
            return;
        }
        g.b bVar = new g.b(0.0f, 0.0f, bitmapS.getWidth(), bitmapS.getHeight());
        U0(this.f8306d, oVar);
        if (z() && W0()) {
            Matrix matrix = oVar.f8252u;
            if (matrix != null) {
                this.f8303a.concat(matrix);
            }
            g.p pVar3 = oVar.f8248q;
            float fE = pVar3 != null ? pVar3.e(this) : 0.0f;
            g.p pVar4 = oVar.f8249r;
            this.f8306d.f8347f = new g.b(fE, pVar4 != null ? pVar4.f(this) : 0.0f, oVar.f8250s.e(this), oVar.f8251t.e(this));
            if (!this.f8306d.f8342a.A.booleanValue()) {
                g.b bVar2 = this.f8306d.f8347f;
                M0(bVar2.f8146a, bVar2.f8147b, bVar2.f8148c, bVar2.f8149d);
            }
            oVar.f8233h = this.f8306d.f8347f;
            S0(oVar);
            p(oVar);
            boolean zK0 = k0();
            V0();
            this.f8303a.save();
            this.f8303a.concat(o(this.f8306d.f8347f, bVar, eVar));
            this.f8303a.drawBitmap(bitmapS, 0.0f, 0.0f, new Paint(this.f8306d.f8342a.R != g.e0.e.optimizeSpeed ? 2 : 0));
            this.f8303a.restore();
            if (zK0) {
                i0(oVar);
            }
        }
    }

    private void q(g.k0 k0Var, g.b bVar) {
        Path pathJ;
        if (this.f8306d.f8342a.J == null || (pathJ = j(k0Var, bVar)) == null) {
            return;
        }
        this.f8303a.clipPath(pathJ);
    }

    private void q0(g.q qVar) {
        x("Line render", new Object[0]);
        U0(this.f8306d, qVar);
        if (z() && W0() && this.f8306d.f8344c) {
            Matrix matrix = qVar.f8234n;
            if (matrix != null) {
                this.f8303a.concat(matrix);
            }
            Path pathZ = Z(qVar);
            S0(qVar);
            r(qVar);
            p(qVar);
            boolean zK0 = k0();
            B(pathZ);
            G0(qVar);
            if (zK0) {
                i0(qVar);
            }
        }
    }

    private void r(g.k0 k0Var) {
        g.o0 o0Var = this.f8306d.f8342a.f8169g;
        if (o0Var instanceof g.u) {
            y(true, k0Var.f8233h, (g.u) o0Var);
        }
        g.o0 o0Var2 = this.f8306d.f8342a.f8172j;
        if (o0Var2 instanceof g.u) {
            y(false, k0Var.f8233h, (g.u) o0Var2);
        }
    }

    private void r0(g.v vVar) {
        x("Path render", new Object[0]);
        if (vVar.f8282o == null) {
            return;
        }
        U0(this.f8306d, vVar);
        if (z() && W0()) {
            C0126h c0126h = this.f8306d;
            if (c0126h.f8344c || c0126h.f8343b) {
                Matrix matrix = vVar.f8234n;
                if (matrix != null) {
                    this.f8303a.concat(matrix);
                }
                Path pathF = new d(vVar.f8282o).f();
                if (vVar.f8233h == null) {
                    vVar.f8233h = m(pathF);
                }
                S0(vVar);
                r(vVar);
                p(vVar);
                boolean zK0 = k0();
                if (this.f8306d.f8343b) {
                    pathF.setFillType(T());
                    A(vVar, pathF);
                }
                if (this.f8306d.f8344c) {
                    B(pathF);
                }
                G0(vVar);
                if (zK0) {
                    i0(vVar);
                }
            }
        }
    }

    private Bitmap s(String str) {
        int iIndexOf;
        if (!str.startsWith("data:") || str.length() < 14 || (iIndexOf = str.indexOf(44)) == -1 || iIndexOf < 12 || !";base64".equals(str.substring(iIndexOf - 7, iIndexOf))) {
            return null;
        }
        byte[] bArrDecode = Base64.decode(str.substring(iIndexOf + 1), 0);
        return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
    }

    private void s0(g.z zVar) {
        x("PolyLine render", new Object[0]);
        U0(this.f8306d, zVar);
        if (z() && W0()) {
            C0126h c0126h = this.f8306d;
            if (c0126h.f8344c || c0126h.f8343b) {
                Matrix matrix = zVar.f8234n;
                if (matrix != null) {
                    this.f8303a.concat(matrix);
                }
                if (zVar.f8298o.length < 2) {
                    return;
                }
                Path pathA0 = a0(zVar);
                S0(zVar);
                pathA0.setFillType(T());
                r(zVar);
                p(zVar);
                boolean zK0 = k0();
                if (this.f8306d.f8343b) {
                    A(zVar, pathA0);
                }
                if (this.f8306d.f8344c) {
                    B(pathA0);
                }
                G0(zVar);
                if (zK0) {
                    i0(zVar);
                }
            }
        }
    }

    private Typeface t(String str, Integer num, g.e0.b bVar) {
        boolean z6 = bVar == g.e0.b.Italic;
        int i7 = num.intValue() > 500 ? z6 ? 3 : 1 : z6 ? 2 : 0;
        str.hashCode();
        switch (str) {
        }
        return Typeface.create(Typeface.SANS_SERIF, i7);
    }

    private void t0(g.a0 a0Var) {
        x("Polygon render", new Object[0]);
        U0(this.f8306d, a0Var);
        if (z() && W0()) {
            C0126h c0126h = this.f8306d;
            if (c0126h.f8344c || c0126h.f8343b) {
                Matrix matrix = a0Var.f8234n;
                if (matrix != null) {
                    this.f8303a.concat(matrix);
                }
                if (a0Var.f8298o.length < 2) {
                    return;
                }
                Path pathA0 = a0(a0Var);
                S0(a0Var);
                r(a0Var);
                p(a0Var);
                boolean zK0 = k0();
                if (this.f8306d.f8343b) {
                    A(a0Var, pathA0);
                }
                if (this.f8306d.f8344c) {
                    B(pathA0);
                }
                G0(a0Var);
                if (zK0) {
                    i0(a0Var);
                }
            }
        }
    }

    private void u(g.n0 n0Var) {
        Boolean bool;
        if ((n0Var instanceof g.l0) && (bool = ((g.l0) n0Var).f8236d) != null) {
            this.f8306d.f8349h = bool.booleanValue();
        }
    }

    private void u0(g.b0 b0Var) {
        x("Rect render", new Object[0]);
        g.p pVar = b0Var.f8152q;
        if (pVar == null || b0Var.f8153r == null || pVar.h() || b0Var.f8153r.h()) {
            return;
        }
        U0(this.f8306d, b0Var);
        if (z() && W0()) {
            Matrix matrix = b0Var.f8234n;
            if (matrix != null) {
                this.f8303a.concat(matrix);
            }
            Path pathB0 = b0(b0Var);
            S0(b0Var);
            r(b0Var);
            p(b0Var);
            boolean zK0 = k0();
            if (this.f8306d.f8343b) {
                A(b0Var, pathB0);
            }
            if (this.f8306d.f8344c) {
                B(pathB0);
            }
            if (zK0) {
                i0(b0Var);
            }
        }
    }

    private static int v(float f7) {
        int i7 = (int) (f7 * 256.0f);
        if (i7 < 0) {
            return 0;
        }
        if (i7 > 255) {
            return 255;
        }
        return i7;
    }

    private void v0(g.f0 f0Var) {
        x0(f0Var, e0(f0Var.f8206q, f0Var.f8207r, f0Var.f8208s, f0Var.f8209t), f0Var.f8271p, f0Var.f8255o);
    }

    static int w(int i7, float f7) {
        int i8 = 255;
        int iRound = Math.round(((i7 >> 24) & 255) * f7);
        if (iRound < 0) {
            i8 = 0;
        } else if (iRound <= 255) {
            i8 = iRound;
        }
        return (i7 & 16777215) | (i8 << 24);
    }

    private void w0(g.f0 f0Var, g.b bVar) {
        x0(f0Var, bVar, f0Var.f8271p, f0Var.f8255o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void x(String str, Object... objArr) {
    }

    private void x0(g.f0 f0Var, g.b bVar, g.b bVar2, m2.e eVar) {
        x("Svg render", new Object[0]);
        if (bVar.f8148c == 0.0f || bVar.f8149d == 0.0f) {
            return;
        }
        if (eVar == null && (eVar = f0Var.f8255o) == null) {
            eVar = m2.e.f8117e;
        }
        U0(this.f8306d, f0Var);
        if (z()) {
            C0126h c0126h = this.f8306d;
            c0126h.f8347f = bVar;
            if (!c0126h.f8342a.A.booleanValue()) {
                g.b bVar3 = this.f8306d.f8347f;
                M0(bVar3.f8146a, bVar3.f8147b, bVar3.f8148c, bVar3.f8149d);
            }
            q(f0Var, this.f8306d.f8347f);
            if (bVar2 != null) {
                this.f8303a.concat(o(this.f8306d.f8347f, bVar2, eVar));
                this.f8306d.f8348g = f0Var.f8271p;
            } else {
                Canvas canvas = this.f8303a;
                g.b bVar4 = this.f8306d.f8347f;
                canvas.translate(bVar4.f8146a, bVar4.f8147b);
            }
            boolean zK0 = k0();
            V0();
            D0(f0Var, true);
            if (zK0) {
                i0(f0Var);
            }
            S0(f0Var);
        }
    }

    private void y(boolean z6, g.b bVar, g.u uVar) {
        g.n0 n0VarR = this.f8305c.r(uVar.f8278e);
        if (n0VarR != null) {
            if (n0VarR instanceof g.m0) {
                W(z6, bVar, (g.m0) n0VarR);
                return;
            } else if (n0VarR instanceof g.q0) {
                d0(z6, bVar, (g.q0) n0VarR);
                return;
            } else {
                if (n0VarR instanceof g.c0) {
                    O0(z6, (g.c0) n0VarR);
                    return;
                }
                return;
            }
        }
        Object[] objArr = new Object[2];
        objArr[0] = z6 ? "Fill" : "Stroke";
        objArr[1] = uVar.f8278e;
        E("%s reference '%s' not found", objArr);
        g.o0 o0Var = uVar.f8279g;
        if (o0Var != null) {
            N0(this.f8306d, z6, o0Var);
        } else if (z6) {
            this.f8306d.f8343b = false;
        } else {
            this.f8306d.f8344c = false;
        }
    }

    private void y0(g.n0 n0Var) {
        if (n0Var instanceof g.t) {
            return;
        }
        Q0();
        u(n0Var);
        if (n0Var instanceof g.f0) {
            v0((g.f0) n0Var);
        } else if (n0Var instanceof g.e1) {
            C0((g.e1) n0Var);
        } else if (n0Var instanceof g.s0) {
            z0((g.s0) n0Var);
        } else if (n0Var instanceof g.m) {
            o0((g.m) n0Var);
        } else if (n0Var instanceof g.o) {
            p0((g.o) n0Var);
        } else if (n0Var instanceof g.v) {
            r0((g.v) n0Var);
        } else if (n0Var instanceof g.b0) {
            u0((g.b0) n0Var);
        } else if (n0Var instanceof g.d) {
            m0((g.d) n0Var);
        } else if (n0Var instanceof g.i) {
            n0((g.i) n0Var);
        } else if (n0Var instanceof g.q) {
            q0((g.q) n0Var);
        } else if (n0Var instanceof g.a0) {
            t0((g.a0) n0Var);
        } else if (n0Var instanceof g.z) {
            s0((g.z) n0Var);
        } else if (n0Var instanceof g.w0) {
            B0((g.w0) n0Var);
        }
        P0();
    }

    private boolean z() {
        Boolean bool = this.f8306d.f8342a.F;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    private void z0(g.s0 s0Var) {
        x("Switch render", new Object[0]);
        U0(this.f8306d, s0Var);
        if (z()) {
            Matrix matrix = s0Var.f8240o;
            if (matrix != null) {
                this.f8303a.concat(matrix);
            }
            p(s0Var);
            boolean zK0 = k0();
            I0(s0Var);
            if (zK0) {
                i0(s0Var);
            }
            S0(s0Var);
        }
    }

    void E0(m2.g gVar, m2.f fVar) {
        g.b bVar;
        m2.e eVar;
        Objects.requireNonNull(fVar, "renderOptions shouldn't be null");
        this.f8305c = gVar;
        g.f0 f0VarM = gVar.m();
        if (f0VarM == null) {
            X0("Nothing to render. Document is empty.", new Object[0]);
            return;
        }
        if (fVar.d()) {
            g.l0 l0VarI = this.f8305c.i(fVar.f8132e);
            if (l0VarI == null || !(l0VarI instanceof g.f1)) {
                Log.w("SVGAndroidRenderer", String.format("View element with id \"%s\" not found.", fVar.f8132e));
                return;
            }
            g.f1 f1Var = (g.f1) l0VarI;
            bVar = f1Var.f8271p;
            if (bVar == null) {
                Log.w("SVGAndroidRenderer", String.format("View element with id \"%s\" is missing a viewBox attribute.", fVar.f8132e));
                return;
            }
            eVar = f1Var.f8255o;
        } else {
            bVar = fVar.e() ? fVar.f8131d : f0VarM.f8271p;
            eVar = fVar.b() ? fVar.f8129b : f0VarM.f8255o;
        }
        if (fVar.a()) {
            gVar.a(fVar.f8128a);
        }
        if (fVar.c()) {
            b.q qVar = new b.q();
            this.f8310h = qVar;
            qVar.f8101a = gVar.i(fVar.f8130c);
        }
        L0();
        u(f0VarM);
        Q0();
        g.b bVar2 = new g.b(fVar.f8133f);
        g.p pVar = f0VarM.f8208s;
        if (pVar != null) {
            bVar2.f8148c = pVar.d(this, bVar2.f8148c);
        }
        g.p pVar2 = f0VarM.f8209t;
        if (pVar2 != null) {
            bVar2.f8149d = pVar2.d(this, bVar2.f8149d);
        }
        x0(f0VarM, bVar2, bVar, eVar);
        P0();
        if (fVar.a()) {
            gVar.b();
        }
    }

    float P() {
        return this.f8306d.f8345d.getTextSize();
    }

    float Q() {
        return this.f8306d.f8345d.getTextSize() / 2.0f;
    }

    g.b R() {
        C0126h c0126h = this.f8306d;
        g.b bVar = c0126h.f8348g;
        return bVar != null ? bVar : c0126h.f8347f;
    }

    float S() {
        return this.f8304b;
    }

    /* JADX INFO: compiled from: SVGAndroidRenderer.java */
    private class k extends j {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f8356b;

        private k() {
            super(h.this, null);
            this.f8356b = 0.0f;
        }

        @Override // m2.h.j
        public void b(String str) {
            this.f8356b += h.this.f8306d.f8345d.measureText(str);
        }

        /* synthetic */ k(h hVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: renamed from: m2.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SVGAndroidRenderer.java */
    private class C0126h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        g.e0 f8342a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f8343b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f8344c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Paint f8345d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Paint f8346e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        g.b f8347f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        g.b f8348g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f8349h;

        C0126h() {
            Paint paint = new Paint();
            this.f8345d = paint;
            paint.setFlags(385);
            this.f8345d.setStyle(Paint.Style.FILL);
            this.f8345d.setTypeface(Typeface.DEFAULT);
            Paint paint2 = new Paint();
            this.f8346e = paint2;
            paint2.setFlags(385);
            this.f8346e.setStyle(Paint.Style.STROKE);
            this.f8346e.setTypeface(Typeface.DEFAULT);
            this.f8342a = g.e0.a();
        }

        C0126h(C0126h c0126h) {
            this.f8343b = c0126h.f8343b;
            this.f8344c = c0126h.f8344c;
            this.f8345d = new Paint(c0126h.f8345d);
            this.f8346e = new Paint(c0126h.f8346e);
            g.b bVar = c0126h.f8347f;
            if (bVar != null) {
                this.f8347f = new g.b(bVar);
            }
            g.b bVar2 = c0126h.f8348g;
            if (bVar2 != null) {
                this.f8348g = new g.b(bVar2);
            }
            this.f8349h = c0126h.f8349h;
            try {
                this.f8342a = (g.e0) c0126h.f8342a.clone();
            } catch (CloneNotSupportedException e7) {
                Log.e("SVGAndroidRenderer", "Unexpected clone error", e7);
                this.f8342a = g.e0.a();
            }
        }
    }
}
