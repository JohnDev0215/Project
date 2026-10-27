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
import com.google.android.material.internal.i;
import d.b.a.a.u.a;

/* JADX INFO: compiled from: CollapsingTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f4113a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Paint f4114b;
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
    private final View f4115c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4116d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f4117e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Rect f4118f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Rect f4119g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final RectF f4120h;
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
    private d.b.a.a.u.a x;
    private d.b.a.a.u.a y;
    private CharSequence z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f4121i = 16;
    private int j = 16;
    private float k = 15.0f;
    private float l = 15.0f;
    private int a0 = 1;

    /* JADX INFO: renamed from: com.google.android.material.internal.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CollapsingTextHelper.java */
    class C0077a implements a.InterfaceC0088a {
        C0077a() {
        }

        @Override // d.b.a.a.u.a.InterfaceC0088a
        public void a(Typeface typeface) {
            a.this.S(typeface);
        }
    }

    /* JADX INFO: compiled from: CollapsingTextHelper.java */
    class b implements a.InterfaceC0088a {
        b() {
        }

        @Override // d.b.a.a.u.a.InterfaceC0088a
        public void a(Typeface typeface) {
            a.this.b0(typeface);
        }
    }

    static {
        f4113a = Build.VERSION.SDK_INT < 18;
        Paint paint = null;
        f4114b = null;
        if (0 != 0) {
            paint.setAntiAlias(true);
            paint.setColor(-65281);
        }
    }

    public a(View view) {
        this.f4115c = view;
        TextPaint textPaint = new TextPaint(129);
        this.J = textPaint;
        this.K = new TextPaint(textPaint);
        this.f4119g = new Rect();
        this.f4118f = new Rect();
        this.f4120h = new RectF();
    }

    private void C(TextPaint textPaint) {
        textPaint.setTextSize(this.l);
        textPaint.setTypeface(this.u);
    }

    private void D(TextPaint textPaint) {
        textPaint.setTextSize(this.k);
        textPaint.setTypeface(this.v);
    }

    private void E(float f2) {
        this.f4120h.left = I(this.f4118f.left, this.f4119g.left, f2, this.L);
        this.f4120h.top = I(this.o, this.p, f2, this.L);
        this.f4120h.right = I(this.f4118f.right, this.f4119g.right, f2, this.L);
        this.f4120h.bottom = I(this.f4118f.bottom, this.f4119g.bottom, f2, this.L);
    }

    private static boolean F(float f2, float f3) {
        return Math.abs(f2 - f3) < 0.001f;
    }

    private boolean G() {
        return t.z(this.f4115c) == 1;
    }

    private static float I(float f2, float f3, float f4, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f4 = timeInterpolator.getInterpolation(f4);
        }
        return d.b.a.a.m.a.a(f2, f3, f4);
    }

    private static boolean L(Rect rect, int i2, int i3, int i4, int i5) {
        return rect.left == i2 && rect.top == i3 && rect.right == i4 && rect.bottom == i5;
    }

    private void P(float f2) {
        this.W = f2;
        t.a0(this.f4115c);
    }

    private boolean T(Typeface typeface) {
        d.b.a.a.u.a aVar = this.y;
        if (aVar != null) {
            aVar.c();
        }
        if (this.u == typeface) {
            return false;
        }
        this.u = typeface;
        return true;
    }

    private void X(float f2) {
        this.X = f2;
        t.a0(this.f4115c);
    }

    private static int a(int i2, int i3, float f2) {
        float f3 = 1.0f - f2;
        return Color.argb((int) ((Color.alpha(i2) * f3) + (Color.alpha(i3) * f2)), (int) ((Color.red(i2) * f3) + (Color.red(i3) * f2)), (int) ((Color.green(i2) * f3) + (Color.green(i3) * f2)), (int) ((Color.blue(i2) * f3) + (Color.blue(i3) * f2)));
    }

    private void b() {
        StaticLayout staticLayout;
        float f2 = this.G;
        g(this.l);
        CharSequence charSequence = this.A;
        if (charSequence != null && (staticLayout = this.V) != null) {
            this.Z = TextUtils.ellipsize(charSequence, this.J, staticLayout.getWidth(), TextUtils.TruncateAt.END);
        }
        CharSequence charSequence2 = this.Z;
        float fMeasureText = charSequence2 != null ? this.J.measureText(charSequence2, 0, charSequence2.length()) : 0.0f;
        int iB = b.g.k.d.b(this.j, this.B ? 1 : 0);
        int i2 = iB & 112;
        if (i2 == 48) {
            this.p = this.f4119g.top;
        } else if (i2 != 80) {
            this.p = this.f4119g.centerY() - ((this.J.descent() - this.J.ascent()) / 2.0f);
        } else {
            this.p = this.f4119g.bottom + this.J.ascent();
        }
        int i3 = iB & 8388615;
        if (i3 == 1) {
            this.r = this.f4119g.centerX() - (fMeasureText / 2.0f);
        } else if (i3 != 5) {
            this.r = this.f4119g.left;
        } else {
            this.r = this.f4119g.right - fMeasureText;
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
        int iB2 = b.g.k.d.b(this.f4121i, this.B ? 1 : 0);
        int i4 = iB2 & 112;
        if (i4 == 48) {
            this.o = this.f4118f.top;
        } else if (i4 != 80) {
            this.o = this.f4118f.centerY() - (height / 2.0f);
        } else {
            this.o = (this.f4118f.bottom - height) + this.J.descent();
        }
        int i5 = iB2 & 8388615;
        if (i5 == 1) {
            this.q = this.f4118f.centerX() - (fMeasureText2 / 2.0f);
        } else if (i5 != 5) {
            this.q = this.f4118f.left;
        } else {
            this.q = this.f4118f.right - fMeasureText2;
        }
        h();
        e0(f2);
    }

    private boolean c0(Typeface typeface) {
        d.b.a.a.u.a aVar = this.x;
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
        f(this.f4117e);
    }

    private boolean e(CharSequence charSequence) {
        return (G() ? b.g.i.e.f2647d : b.g.i.e.f2646c).a(charSequence, 0, charSequence.length());
    }

    private void e0(float f2) {
        g(f2);
        boolean z = f4113a && this.F != 1.0f;
        this.C = z;
        if (z) {
            l();
        }
        t.a0(this.f4115c);
    }

    private void f(float f2) {
        E(f2);
        this.s = I(this.q, this.r, f2, this.L);
        this.t = I(this.o, this.p, f2, this.L);
        e0(I(this.k, this.l, f2, this.M));
        TimeInterpolator timeInterpolator = d.b.a.a.m.a.f4675b;
        P(1.0f - I(0.0f, 1.0f, 1.0f - f2, timeInterpolator));
        X(I(1.0f, 0.0f, f2, timeInterpolator));
        if (this.n != this.m) {
            this.J.setColor(a(v(), t(), f2));
        } else {
            this.J.setColor(t());
        }
        this.J.setShadowLayer(I(this.R, this.N, f2, null), I(this.S, this.O, f2, null), I(this.T, this.P, f2, null), a(u(this.U), u(this.Q), f2));
        t.a0(this.f4115c);
    }

    private void g(float f2) {
        boolean z;
        float f3;
        boolean z2;
        if (this.z == null) {
            return;
        }
        float fWidth = this.f4119g.width();
        float fWidth2 = this.f4118f.width();
        if (F(f2, this.l)) {
            f3 = this.l;
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
            float f4 = this.k;
            Typeface typeface3 = this.w;
            Typeface typeface4 = this.v;
            if (typeface3 != typeface4) {
                this.w = typeface4;
                z = true;
            } else {
                z = false;
            }
            if (F(f2, f4)) {
                this.F = 1.0f;
            } else {
                this.F = f2 / this.k;
            }
            float f5 = this.l / this.k;
            fWidth = fWidth2 * f5 > fWidth ? Math.min(fWidth / f5, fWidth2) : fWidth2;
            f3 = f4;
            z2 = z;
        }
        if (fWidth > 0.0f) {
            z2 = this.G != f3 || this.I || z2;
            this.G = f3;
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

    private StaticLayout i(int i2, float f2, boolean z) {
        StaticLayout staticLayoutA;
        try {
            staticLayoutA = i.c(this.z, this.J, (int) f2).e(TextUtils.TruncateAt.END).g(z).d(Layout.Alignment.ALIGN_NORMAL).f(false).h(i2).a();
        } catch (i.a e2) {
            Log.e("CollapsingTextHelper", e2.getCause().getMessage(), e2);
            staticLayoutA = null;
        }
        return (StaticLayout) b.g.j.h.c(staticLayoutA);
    }

    private void k(Canvas canvas, float f2, float f3) {
        int alpha = this.J.getAlpha();
        canvas.translate(f2, f3);
        float f4 = alpha;
        this.J.setAlpha((int) (this.X * f4));
        this.V.draw(canvas);
        this.J.setAlpha((int) (this.W * f4));
        int lineBaseline = this.V.getLineBaseline(0);
        CharSequence charSequence = this.Z;
        float f5 = lineBaseline;
        canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, f5, this.J);
        String strTrim = this.Z.toString().trim();
        if (strTrim.endsWith("…")) {
            strTrim = strTrim.substring(0, strTrim.length() - 1);
        }
        String str = strTrim;
        this.J.setAlpha(alpha);
        canvas.drawText(str, 0, Math.min(this.V.getLineEnd(0), str.length()), 0.0f, f5, (Paint) this.J);
    }

    private void l() {
        if (this.D != null || this.f4118f.isEmpty() || TextUtils.isEmpty(this.A)) {
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

    private float q(int i2, int i3) {
        return (i3 == 17 || (i3 & 7) == 1) ? (i2 / 2.0f) - (c() / 2.0f) : ((i3 & 8388613) == 8388613 || (i3 & 5) == 5) ? this.B ? this.f4119g.left : this.f4119g.right - c() : this.B ? this.f4119g.right - c() : this.f4119g.left;
    }

    private float r(RectF rectF, int i2, int i3) {
        return (i3 == 17 || (i3 & 7) == 1) ? (i2 / 2.0f) + (c() / 2.0f) : ((i3 & 8388613) == 8388613 || (i3 & 5) == 5) ? this.B ? rectF.left + c() : this.f4119g.right : this.B ? this.f4119g.right : rectF.left + c();
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
        this.f4116d = this.f4119g.width() > 0 && this.f4119g.height() > 0 && this.f4118f.width() > 0 && this.f4118f.height() > 0;
    }

    public void K() {
        if (this.f4115c.getHeight() <= 0 || this.f4115c.getWidth() <= 0) {
            return;
        }
        b();
        d();
    }

    public void M(int i2, int i3, int i4, int i5) {
        if (L(this.f4119g, i2, i3, i4, i5)) {
            return;
        }
        this.f4119g.set(i2, i3, i4, i5);
        this.I = true;
        J();
    }

    public void N(Rect rect) {
        M(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void O(int i2) {
        d.b.a.a.u.d dVar = new d.b.a.a.u.d(this.f4115c.getContext(), i2);
        ColorStateList colorStateList = dVar.f4712b;
        if (colorStateList != null) {
            this.n = colorStateList;
        }
        float f2 = dVar.f4711a;
        if (f2 != 0.0f) {
            this.l = f2;
        }
        ColorStateList colorStateList2 = dVar.f4719i;
        if (colorStateList2 != null) {
            this.Q = colorStateList2;
        }
        this.O = dVar.j;
        this.P = dVar.k;
        this.N = dVar.l;
        d.b.a.a.u.a aVar = this.y;
        if (aVar != null) {
            aVar.c();
        }
        this.y = new d.b.a.a.u.a(new C0077a(), dVar.e());
        dVar.h(this.f4115c.getContext(), this.y);
        K();
    }

    public void Q(ColorStateList colorStateList) {
        if (this.n != colorStateList) {
            this.n = colorStateList;
            K();
        }
    }

    public void R(int i2) {
        if (this.j != i2) {
            this.j = i2;
            K();
        }
    }

    public void S(Typeface typeface) {
        if (T(typeface)) {
            K();
        }
    }

    public void U(int i2, int i3, int i4, int i5) {
        if (L(this.f4118f, i2, i3, i4, i5)) {
            return;
        }
        this.f4118f.set(i2, i3, i4, i5);
        this.I = true;
        J();
    }

    public void V(Rect rect) {
        U(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void W(int i2) {
        d.b.a.a.u.d dVar = new d.b.a.a.u.d(this.f4115c.getContext(), i2);
        ColorStateList colorStateList = dVar.f4712b;
        if (colorStateList != null) {
            this.m = colorStateList;
        }
        float f2 = dVar.f4711a;
        if (f2 != 0.0f) {
            this.k = f2;
        }
        ColorStateList colorStateList2 = dVar.f4719i;
        if (colorStateList2 != null) {
            this.U = colorStateList2;
        }
        this.S = dVar.j;
        this.T = dVar.k;
        this.R = dVar.l;
        d.b.a.a.u.a aVar = this.x;
        if (aVar != null) {
            aVar.c();
        }
        this.x = new d.b.a.a.u.a(new b(), dVar.e());
        dVar.h(this.f4115c.getContext(), this.x);
        K();
    }

    public void Y(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            K();
        }
    }

    public void Z(int i2) {
        if (this.f4121i != i2) {
            this.f4121i = i2;
            K();
        }
    }

    public void a0(float f2) {
        if (this.k != f2) {
            this.k = f2;
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

    public void d0(float f2) {
        float fA = b.g.f.a.a(f2, 0.0f, 1.0f);
        if (fA != this.f4117e) {
            this.f4117e = fA;
            d();
        }
    }

    public void f0(int i2) {
        if (i2 != this.a0) {
            this.a0 = i2;
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
        if (this.A == null || !this.f4116d) {
            return;
        }
        boolean z = false;
        float lineLeft = (this.s + this.V.getLineLeft(0)) - (this.Y * 2.0f);
        this.J.setTextSize(this.G);
        float f2 = this.s;
        float f3 = this.t;
        if (this.C && this.D != null) {
            z = true;
        }
        float f4 = this.F;
        if (f4 != 1.0f) {
            canvas.scale(f4, f4, f2, f3);
        }
        if (z) {
            canvas.drawBitmap(this.D, f2, f3, this.E);
            canvas.restoreToCount(iSave);
            return;
        }
        if (l0()) {
            k(canvas, lineLeft, f3);
        } else {
            canvas.translate(f2, f3);
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

    public void m(RectF rectF, int i2, int i3) {
        this.B = e(this.z);
        rectF.left = q(i2, i3);
        rectF.top = this.f4119g.top;
        rectF.right = r(rectF, i2, i3);
        rectF.bottom = this.f4119g.top + p();
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
        return this.f4121i;
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
        return this.f4117e;
    }
}
