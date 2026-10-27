package com.google.android.material.internal;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import b.g.k.t;
import c.a.a.a.t.a;
import com.google.android.material.internal.StaticLayoutBuilderCompat;

/* JADX INFO: compiled from: CollapsingTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f4298a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Paint f4299b;
    private CharSequence A;
    private boolean B;
    private boolean C;
    private Bitmap D;
    private Paint E;
    private float F;
    private float G;
    private int[] H;
    private boolean I;
    private final TextPaint J;
    private final TextPaint K;
    private TimeInterpolator L;
    private TimeInterpolator M;
    private float N;
    private float O;
    private float P;
    private ColorStateList Q;
    private float R;
    private float S;
    private float T;
    private ColorStateList U;
    private StaticLayout V;
    private float W;
    private float X;
    private float Y;
    private CharSequence Z;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final View f4300c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4301d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f4302e;
    private final Rect f;
    private final Rect g;
    private final RectF h;
    private ColorStateList m;
    private ColorStateList n;
    private float o;
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;
    private Typeface u;
    private Typeface v;
    private Typeface w;
    private c.a.a.a.t.a x;
    private c.a.a.a.t.a y;
    private CharSequence z;
    private int i = 16;
    private int j = 16;
    private float k = 15.0f;
    private float l = 15.0f;
    private int a0 = 1;

    /* JADX INFO: renamed from: com.google.android.material.internal.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CollapsingTextHelper.java */
    class C0106a implements a.InterfaceC0051a {
        C0106a() {
        }

        @Override // c.a.a.a.t.a.InterfaceC0051a
        public void a(Typeface typeface) {
            a.this.S(typeface);
        }
    }

    /* JADX INFO: compiled from: CollapsingTextHelper.java */
    class b implements a.InterfaceC0051a {
        b() {
        }

        @Override // c.a.a.a.t.a.InterfaceC0051a
        public void a(Typeface typeface) {
            a.this.b0(typeface);
        }
    }

    static {
        f4298a = Build.VERSION.SDK_INT < 18;
        Paint paint = null;
        f4299b = null;
        if (0 != 0) {
            paint.setAntiAlias(true);
            paint.setColor(-65281);
        }
    }

    public a(View view) {
        this.f4300c = view;
        TextPaint textPaint = new TextPaint(129);
        this.J = textPaint;
        this.K = new TextPaint(textPaint);
        this.g = new Rect();
        this.f = new Rect();
        this.h = new RectF();
    }

    private void C(TextPaint textPaint) {
        textPaint.setTextSize(this.l);
        textPaint.setTypeface(this.u);
    }

    private void D(TextPaint textPaint) {
        textPaint.setTextSize(this.k);
        textPaint.setTypeface(this.v);
    }

    private void E(float f) {
        this.h.left = I(this.f.left, this.g.left, f, this.L);
        this.h.top = I(this.o, this.p, f, this.L);
        this.h.right = I(this.f.right, this.g.right, f, this.L);
        this.h.bottom = I(this.f.bottom, this.g.bottom, f, this.L);
    }

    private static boolean F(float f, float f2) {
        return Math.abs(f - f2) < 0.001f;
    }

    private boolean G() {
        return t.z(this.f4300c) == 1;
    }

    private static float I(float f, float f2, float f3, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f3 = timeInterpolator.getInterpolation(f3);
        }
        return c.a.a.a.m.a.a(f, f2, f3);
    }

    private static boolean L(Rect rect, int i, int i2, int i3, int i4) {
        return rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4;
    }

    private void P(float f) {
        this.W = f;
        t.a0(this.f4300c);
    }

    private boolean T(Typeface typeface) {
        c.a.a.a.t.a aVar = this.y;
        if (aVar != null) {
            aVar.c();
        }
        if (this.u == typeface) {
            return false;
        }
        this.u = typeface;
        return true;
    }

    private void X(float f) {
        this.X = f;
        t.a0(this.f4300c);
    }

    private static int a(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.argb((int) ((Color.alpha(i) * f2) + (Color.alpha(i2) * f)), (int) ((Color.red(i) * f2) + (Color.red(i2) * f)), (int) ((Color.green(i) * f2) + (Color.green(i2) * f)), (int) ((Color.blue(i) * f2) + (Color.blue(i2) * f)));
    }

    private void b() {
        StaticLayout staticLayout;
        float f = this.G;
        g(this.l);
        CharSequence charSequence = this.A;
        if (charSequence != null && (staticLayout = this.V) != null) {
            this.Z = TextUtils.ellipsize(charSequence, this.J, staticLayout.getWidth(), TextUtils.TruncateAt.END);
        }
        CharSequence charSequence2 = this.Z;
        float fMeasureText = charSequence2 != null ? this.J.measureText(charSequence2, 0, charSequence2.length()) : 0.0f;
        int iB = b.g.k.d.b(this.j, this.B ? 1 : 0);
        int i = iB & 112;
        if (i == 48) {
            this.p = this.g.top;
        } else if (i != 80) {
            this.p = this.g.centerY() - ((this.J.descent() - this.J.ascent()) / 2.0f);
        } else {
            this.p = this.g.bottom + this.J.ascent();
        }
        int i2 = iB & 8388615;
        if (i2 == 1) {
            this.r = this.g.centerX() - (fMeasureText / 2.0f);
        } else if (i2 != 5) {
            this.r = this.g.left;
        } else {
            this.r = this.g.right - fMeasureText;
        }
        g(this.k);
        float height = this.V != null ? r1.getHeight() : 0.0f;
        CharSequence charSequence3 = this.A;
        float fMeasureText2 = charSequence3 != null ? this.J.measureText(charSequence3, 0, charSequence3.length()) : 0.0f;
        StaticLayout staticLayout2 = this.V;
        if (staticLayout2 != null && this.a0 > 1 && !this.B) {
            fMeasureText2 = staticLayout2.getWidth();
        }
        StaticLayout staticLayout3 = this.V;
        this.Y = staticLayout3 != null ? staticLayout3.getLineLeft(0) : 0.0f;
        int iB2 = b.g.k.d.b(this.i, this.B ? 1 : 0);
        int i3 = iB2 & 112;
        if (i3 == 48) {
            this.o = this.f.top;
        } else if (i3 != 80) {
            this.o = this.f.centerY() - (height / 2.0f);
        } else {
            this.o = (this.f.bottom - height) + this.J.descent();
        }
        int i4 = iB2 & 8388615;
        if (i4 == 1) {
            this.q = this.f.centerX() - (fMeasureText2 / 2.0f);
        } else if (i4 != 5) {
            this.q = this.f.left;
        } else {
            this.q = this.f.right - fMeasureText2;
        }
        h();
        e0(f);
    }

    private boolean c0(Typeface typeface) {
        c.a.a.a.t.a aVar = this.x;
        if (aVar != null) {
            aVar.c();
        }
        if (this.v == typeface) {
            return false;
        }
        this.v = typeface;
        return true;
    }

    private void d() {
        f(this.f4302e);
    }

    private boolean e(CharSequence charSequence) {
        return (G() ? b.g.i.e.f1925d : b.g.i.e.f1924c).a(charSequence, 0, charSequence.length());
    }

    private void e0(float f) {
        g(f);
        boolean z = f4298a && this.F != 1.0f;
        this.C = z;
        if (z) {
            l();
        }
        t.a0(this.f4300c);
    }

    private void f(float f) {
        E(f);
        this.s = I(this.q, this.r, f, this.L);
        this.t = I(this.o, this.p, f, this.L);
        e0(I(this.k, this.l, f, this.M));
        TimeInterpolator timeInterpolator = c.a.a.a.m.a.f2306b;
        P(1.0f - I(0.0f, 1.0f, 1.0f - f, timeInterpolator));
        X(I(1.0f, 0.0f, f, timeInterpolator));
        if (this.n != this.m) {
            this.J.setColor(a(v(), t(), f));
        } else {
            this.J.setColor(t());
        }
        this.J.setShadowLayer(I(this.R, this.N, f, null), I(this.S, this.O, f, null), I(this.T, this.P, f, null), a(u(this.U), u(this.Q), f));
        t.a0(this.f4300c);
    }

    private void g(float f) {
        boolean z;
        float f2;
        boolean z2;
        if (this.z == null) {
            return;
        }
        float fWidth = this.g.width();
        float fWidth2 = this.f.width();
        if (F(f, this.l)) {
            f2 = this.l;
            this.F = 1.0f;
            Typeface typeface = this.w;
            Typeface typeface2 = this.u;
            if (typeface != typeface2) {
                this.w = typeface2;
                z2 = true;
            } else {
                z2 = false;
            }
        } else {
            float f3 = this.k;
            Typeface typeface3 = this.w;
            Typeface typeface4 = this.v;
            if (typeface3 != typeface4) {
                this.w = typeface4;
                z = true;
            } else {
                z = false;
            }
            if (F(f, f3)) {
                this.F = 1.0f;
            } else {
                this.F = f / this.k;
            }
            float f4 = this.l / this.k;
            fWidth = fWidth2 * f4 > fWidth ? Math.min(fWidth / f4, fWidth2) : fWidth2;
            f2 = f3;
            z2 = z;
        }
        if (fWidth > 0.0f) {
            z2 = this.G != f2 || this.I || z2;
            this.G = f2;
            this.I = false;
        }
        if (this.A == null || z2) {
            this.J.setTextSize(this.G);
            this.J.setTypeface(this.w);
            this.J.setLinearText(this.F != 1.0f);
            this.B = e(this.z);
            StaticLayout staticLayoutI = i(l0() ? this.a0 : 1, fWidth, this.B);
            this.V = staticLayoutI;
            this.A = staticLayoutI.getText();
        }
    }

    private void h() {
        Bitmap bitmap = this.D;
        if (bitmap != null) {
            bitmap.recycle();
            this.D = null;
        }
    }

    private StaticLayout i(int i, float f, boolean z) {
        StaticLayout staticLayoutA;
        try {
            staticLayoutA = StaticLayoutBuilderCompat.c(this.z, this.J, (int) f).e(TextUtils.TruncateAt.END).g(z).d(Layout.Alignment.ALIGN_NORMAL).f(false).h(i).a();
        } catch (StaticLayoutBuilderCompat.StaticLayoutBuilderCompatException e2) {
            Log.e("CollapsingTextHelper", e2.getCause().getMessage(), e2);
            staticLayoutA = null;
        }
        return (StaticLayout) b.g.j.h.c(staticLayoutA);
    }

    private void k(Canvas canvas, float f, float f2) {
        int alpha = this.J.getAlpha();
        canvas.translate(f, f2);
        float f3 = alpha;
        this.J.setAlpha((int) (this.X * f3));
        this.V.draw(canvas);
        this.J.setAlpha((int) (this.W * f3));
        int lineBaseline = this.V.getLineBaseline(0);
        CharSequence charSequence = this.Z;
        float f4 = lineBaseline;
        canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, f4, this.J);
        String strTrim = this.Z.toString().trim();
        if (strTrim.endsWith("…")) {
            strTrim = strTrim.substring(0, strTrim.length() - 1);
        }
        String str = strTrim;
        this.J.setAlpha(alpha);
        canvas.drawText(str, 0, Math.min(this.V.getLineEnd(0), str.length()), 0.0f, f4, (Paint) this.J);
    }

    private void l() {
        if (this.D != null || this.f.isEmpty() || TextUtils.isEmpty(this.A)) {
            return;
        }
        f(0.0f);
        int width = this.V.getWidth();
        int height = this.V.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.D = bitmapCreateBitmap;
        this.V.draw(new Canvas(bitmapCreateBitmap));
        if (this.E == null) {
            this.E = new Paint(3);
        }
    }

    private boolean l0() {
        return (this.a0 <= 1 || this.B || this.C) ? false : true;
    }

    private float q(int i, int i2) {
        return (i2 == 17 || (i2 & 7) == 1) ? (i / 2.0f) - (c() / 2.0f) : ((i2 & 8388613) == 8388613 || (i2 & 5) == 5) ? this.B ? this.g.left : this.g.right - c() : this.B ? this.g.right - c() : this.g.left;
    }

    private float r(RectF rectF, int i, int i2) {
        return (i2 == 17 || (i2 & 7) == 1) ? (i / 2.0f) + (c() / 2.0f) : ((i2 & 8388613) == 8388613 || (i2 & 5) == 5) ? this.B ? rectF.left + c() : this.g.right : this.B ? this.g.right : rectF.left + c();
    }

    private int u(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.H;
        return iArr != null ? colorStateList.getColorForState(iArr, 0) : colorStateList.getDefaultColor();
    }

    private int v() {
        return u(this.m);
    }

    public int A() {
        return this.a0;
    }

    public CharSequence B() {
        return this.z;
    }

    public final boolean H() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.n;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.m) != null && colorStateList.isStateful());
    }

    void J() {
        this.f4301d = this.g.width() > 0 && this.g.height() > 0 && this.f.width() > 0 && this.f.height() > 0;
    }

    public void K() {
        if (this.f4300c.getHeight() <= 0 || this.f4300c.getWidth() <= 0) {
            return;
        }
        b();
        d();
    }

    public void M(int i, int i2, int i3, int i4) {
        if (L(this.g, i, i2, i3, i4)) {
            return;
        }
        this.g.set(i, i2, i3, i4);
        this.I = true;
        J();
    }

    public void N(Rect rect) {
        M(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void O(int i) {
        c.a.a.a.t.d dVar = new c.a.a.a.t.d(this.f4300c.getContext(), i);
        ColorStateList colorStateList = dVar.f2339b;
        if (colorStateList != null) {
            this.n = colorStateList;
        }
        float f = dVar.f2338a;
        if (f != 0.0f) {
            this.l = f;
        }
        ColorStateList colorStateList2 = dVar.i;
        if (colorStateList2 != null) {
            this.Q = colorStateList2;
        }
        this.O = dVar.j;
        this.P = dVar.k;
        this.N = dVar.l;
        c.a.a.a.t.a aVar = this.y;
        if (aVar != null) {
            aVar.c();
        }
        this.y = new c.a.a.a.t.a(new C0106a(), dVar.e());
        dVar.h(this.f4300c.getContext(), this.y);
        K();
    }

    public void Q(ColorStateList colorStateList) {
        if (this.n != colorStateList) {
            this.n = colorStateList;
            K();
        }
    }

    public void R(int i) {
        if (this.j != i) {
            this.j = i;
            K();
        }
    }

    public void S(Typeface typeface) {
        if (T(typeface)) {
            K();
        }
    }

    public void U(int i, int i2, int i3, int i4) {
        if (L(this.f, i, i2, i3, i4)) {
            return;
        }
        this.f.set(i, i2, i3, i4);
        this.I = true;
        J();
    }

    public void V(Rect rect) {
        U(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void W(int i) {
        c.a.a.a.t.d dVar = new c.a.a.a.t.d(this.f4300c.getContext(), i);
        ColorStateList colorStateList = dVar.f2339b;
        if (colorStateList != null) {
            this.m = colorStateList;
        }
        float f = dVar.f2338a;
        if (f != 0.0f) {
            this.k = f;
        }
        ColorStateList colorStateList2 = dVar.i;
        if (colorStateList2 != null) {
            this.U = colorStateList2;
        }
        this.S = dVar.j;
        this.T = dVar.k;
        this.R = dVar.l;
        c.a.a.a.t.a aVar = this.x;
        if (aVar != null) {
            aVar.c();
        }
        this.x = new c.a.a.a.t.a(new b(), dVar.e());
        dVar.h(this.f4300c.getContext(), this.x);
        K();
    }

    public void Y(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            K();
        }
    }

    public void Z(int i) {
        if (this.i != i) {
            this.i = i;
            K();
        }
    }

    public void a0(float f) {
        if (this.k != f) {
            this.k = f;
            K();
        }
    }

    public void b0(Typeface typeface) {
        if (c0(typeface)) {
            K();
        }
    }

    public float c() {
        if (this.z == null) {
            return 0.0f;
        }
        C(this.K);
        TextPaint textPaint = this.K;
        CharSequence charSequence = this.z;
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    public void d0(float f) {
        float fA = b.g.f.a.a(f, 0.0f, 1.0f);
        if (fA != this.f4302e) {
            this.f4302e = fA;
            d();
        }
    }

    public void f0(int i) {
        if (i != this.a0) {
            this.a0 = i;
            h();
            K();
        }
    }

    public void g0(TimeInterpolator timeInterpolator) {
        this.L = timeInterpolator;
        K();
    }

    public final boolean h0(int[] iArr) {
        this.H = iArr;
        if (!H()) {
            return false;
        }
        K();
        return true;
    }

    public void i0(CharSequence charSequence) {
        if (charSequence == null || !TextUtils.equals(this.z, charSequence)) {
            this.z = charSequence;
            this.A = null;
            h();
            K();
        }
    }

    public void j(Canvas canvas) {
        int iSave = canvas.save();
        if (this.A == null || !this.f4301d) {
            return;
        }
        boolean z = false;
        float lineLeft = (this.s + this.V.getLineLeft(0)) - (this.Y * 2.0f);
        this.J.setTextSize(this.G);
        float f = this.s;
        float f2 = this.t;
        if (this.C && this.D != null) {
            z = true;
        }
        float f3 = this.F;
        if (f3 != 1.0f) {
            canvas.scale(f3, f3, f, f2);
        }
        if (z) {
            canvas.drawBitmap(this.D, f, f2, this.E);
            canvas.restoreToCount(iSave);
            return;
        }
        if (l0()) {
            k(canvas, lineLeft, f2);
        } else {
            canvas.translate(f, f2);
            this.V.draw(canvas);
        }
        canvas.restoreToCount(iSave);
    }

    public void j0(TimeInterpolator timeInterpolator) {
        this.M = timeInterpolator;
        K();
    }

    public void k0(Typeface typeface) {
        boolean zT = T(typeface);
        boolean zC0 = c0(typeface);
        if (zT || zC0) {
            K();
        }
    }

    public void m(RectF rectF, int i, int i2) {
        this.B = e(this.z);
        rectF.left = q(i, i2);
        rectF.top = this.g.top;
        rectF.right = r(rectF, i, i2);
        rectF.bottom = this.g.top + p();
    }

    public ColorStateList n() {
        return this.n;
    }

    public int o() {
        return this.j;
    }

    public float p() {
        C(this.K);
        return -this.K.ascent();
    }

    public Typeface s() {
        Typeface typeface = this.u;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public int t() {
        return u(this.n);
    }

    public int w() {
        return this.i;
    }

    public float x() {
        D(this.K);
        return -this.K.ascent();
    }

    public Typeface y() {
        Typeface typeface = this.v;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public float z() {
        return this.f4302e;
    }
}
