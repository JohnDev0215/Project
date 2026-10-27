package com.google.android.material.internal;

import a.g.k.t;
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
import b.a.a.a.y.a;
import com.google.android.material.internal.g;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f3127a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Paint f3128b;
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
    private final View f3129c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3130d;
    private float e;
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
    private b.a.a.a.y.a x;
    private b.a.a.a.y.a y;
    private CharSequence z;
    private int i = 16;
    private int j = 16;
    private float k = 15.0f;
    private float l = 15.0f;
    private int a0 = 1;

    /* JADX INFO: renamed from: com.google.android.material.internal.a$a, reason: collision with other inner class name */
    class C0113a implements a.InterfaceC0054a {
        C0113a() {
        }

        @Override // b.a.a.a.y.a.InterfaceC0054a
        public void a(Typeface typeface) {
            a.this.M(typeface);
        }
    }

    static {
        f3127a = Build.VERSION.SDK_INT < 18;
        Paint paint = null;
        f3128b = null;
        if (0 != 0) {
            paint.setAntiAlias(true);
            paint.setColor(-65281);
        }
    }

    public a(View view) {
        this.f3129c = view;
        TextPaint textPaint = new TextPaint(129);
        this.J = textPaint;
        this.K = new TextPaint(textPaint);
        this.g = new Rect();
        this.f = new Rect();
        this.h = new RectF();
    }

    private boolean A() {
        return t.y(this.f3129c) == 1;
    }

    private static float C(float f, float f2, float f3, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f3 = timeInterpolator.getInterpolation(f3);
        }
        return b.a.a.a.m.a.a(f, f2, f3);
    }

    private static boolean F(Rect rect, int i, int i2, int i3, int i4) {
        return rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4;
    }

    private void J(float f) {
        this.W = f;
        t.Y(this.f3129c);
    }

    private boolean N(Typeface typeface) {
        b.a.a.a.y.a aVar = this.y;
        if (aVar != null) {
            aVar.c();
        }
        if (this.u == typeface) {
            return false;
        }
        this.u = typeface;
        return true;
    }

    private void Q(float f) {
        this.X = f;
        t.Y(this.f3129c);
    }

    private boolean U(Typeface typeface) {
        b.a.a.a.y.a aVar = this.x;
        if (aVar != null) {
            aVar.c();
        }
        if (this.v == typeface) {
            return false;
        }
        this.v = typeface;
        return true;
    }

    private void W(float f) {
        g(f);
        boolean z = f3127a && this.F != 1.0f;
        this.C = z;
        if (z) {
            l();
        }
        t.Y(this.f3129c);
    }

    private static int a(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.argb((int) ((Color.alpha(i) * f2) + (Color.alpha(i2) * f)), (int) ((Color.red(i) * f2) + (Color.red(i2) * f)), (int) ((Color.green(i) * f2) + (Color.green(i2) * f)), (int) ((Color.blue(i) * f2) + (Color.blue(i2) * f)));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0110  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b() {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.internal.a.b():void");
    }

    private boolean c0() {
        return (this.a0 <= 1 || this.B || this.C) ? false : true;
    }

    private void d() {
        f(this.e);
    }

    private boolean e(CharSequence charSequence) {
        return (A() ? a.g.i.e.f356d : a.g.i.e.f355c).a(charSequence, 0, charSequence.length());
    }

    private void f(float f) {
        TextPaint textPaint;
        int iR;
        y(f);
        this.s = C(this.q, this.r, f, this.L);
        this.t = C(this.o, this.p, f, this.L);
        W(C(this.k, this.l, f, this.M));
        TimeInterpolator timeInterpolator = b.a.a.a.m.a.f1733b;
        J(1.0f - C(0.0f, 1.0f, 1.0f - f, timeInterpolator));
        Q(C(1.0f, 0.0f, f, timeInterpolator));
        if (this.n != this.m) {
            textPaint = this.J;
            iR = a(t(), r(), f);
        } else {
            textPaint = this.J;
            iR = r();
        }
        textPaint.setColor(iR);
        this.J.setShadowLayer(C(this.R, this.N, f, null), C(this.S, this.O, f, null), C(this.T, this.P, f, null), a(s(this.U), s(this.Q), f));
        t.Y(this.f3129c);
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
        if (z(f, this.l)) {
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
            if (z(f, f3)) {
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
            StaticLayout staticLayoutI = i(c0() ? this.a0 : 1, fWidth, this.B);
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
            staticLayoutA = g.c(this.z, this.J, (int) f).e(TextUtils.TruncateAt.END).g(z).d(Layout.Alignment.ALIGN_NORMAL).f(false).h(i).a();
        } catch (g.a e) {
            Log.e("CollapsingTextHelper", e.getCause().getMessage(), e);
            staticLayoutA = null;
        }
        return (StaticLayout) a.g.j.h.c(staticLayoutA);
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

    private float p(int i, int i2) {
        return (i2 == 17 || (i2 & 7) == 1) ? (i / 2.0f) - (c() / 2.0f) : ((i2 & 8388613) == 8388613 || (i2 & 5) == 5) ? this.B ? this.g.left : this.g.right - c() : this.B ? this.g.right - c() : this.g.left;
    }

    private float q(RectF rectF, int i, int i2) {
        return (i2 == 17 || (i2 & 7) == 1) ? (i / 2.0f) + (c() / 2.0f) : ((i2 & 8388613) == 8388613 || (i2 & 5) == 5) ? this.B ? rectF.left + c() : this.g.right : this.B ? this.g.right : rectF.left + c();
    }

    private int s(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.H;
        return iArr != null ? colorStateList.getColorForState(iArr, 0) : colorStateList.getDefaultColor();
    }

    private int t() {
        return s(this.m);
    }

    private void w(TextPaint textPaint) {
        textPaint.setTextSize(this.l);
        textPaint.setTypeface(this.u);
    }

    private void x(TextPaint textPaint) {
        textPaint.setTextSize(this.k);
        textPaint.setTypeface(this.v);
    }

    private void y(float f) {
        this.h.left = C(this.f.left, this.g.left, f, this.L);
        this.h.top = C(this.o, this.p, f, this.L);
        this.h.right = C(this.f.right, this.g.right, f, this.L);
        this.h.bottom = C(this.f.bottom, this.g.bottom, f, this.L);
    }

    private static boolean z(float f, float f2) {
        return Math.abs(f - f2) < 0.001f;
    }

    public final boolean B() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.n;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.m) != null && colorStateList.isStateful());
    }

    void D() {
        this.f3130d = this.g.width() > 0 && this.g.height() > 0 && this.f.width() > 0 && this.f.height() > 0;
    }

    public void E() {
        if (this.f3129c.getHeight() <= 0 || this.f3129c.getWidth() <= 0) {
            return;
        }
        b();
        d();
    }

    public void G(int i, int i2, int i3, int i4) {
        if (F(this.g, i, i2, i3, i4)) {
            return;
        }
        this.g.set(i, i2, i3, i4);
        this.I = true;
        D();
    }

    public void H(Rect rect) {
        G(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void I(int i) {
        b.a.a.a.y.d dVar = new b.a.a.a.y.d(this.f3129c.getContext(), i);
        ColorStateList colorStateList = dVar.f1778b;
        if (colorStateList != null) {
            this.n = colorStateList;
        }
        float f = dVar.f1777a;
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
        b.a.a.a.y.a aVar = this.y;
        if (aVar != null) {
            aVar.c();
        }
        this.y = new b.a.a.a.y.a(new C0113a(), dVar.e());
        dVar.h(this.f3129c.getContext(), this.y);
        E();
    }

    public void K(ColorStateList colorStateList) {
        if (this.n != colorStateList) {
            this.n = colorStateList;
            E();
        }
    }

    public void L(int i) {
        if (this.j != i) {
            this.j = i;
            E();
        }
    }

    public void M(Typeface typeface) {
        if (N(typeface)) {
            E();
        }
    }

    public void O(int i, int i2, int i3, int i4) {
        if (F(this.f, i, i2, i3, i4)) {
            return;
        }
        this.f.set(i, i2, i3, i4);
        this.I = true;
        D();
    }

    public void P(Rect rect) {
        O(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void R(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            E();
        }
    }

    public void S(int i) {
        if (this.i != i) {
            this.i = i;
            E();
        }
    }

    public void T(float f) {
        if (this.k != f) {
            this.k = f;
            E();
        }
    }

    public void V(float f) {
        float fA = a.g.f.a.a(f, 0.0f, 1.0f);
        if (fA != this.e) {
            this.e = fA;
            d();
        }
    }

    public void X(TimeInterpolator timeInterpolator) {
        this.L = timeInterpolator;
        E();
    }

    public final boolean Y(int[] iArr) {
        this.H = iArr;
        if (!B()) {
            return false;
        }
        E();
        return true;
    }

    public void Z(CharSequence charSequence) {
        if (charSequence == null || !TextUtils.equals(this.z, charSequence)) {
            this.z = charSequence;
            this.A = null;
            h();
            E();
        }
    }

    public void a0(TimeInterpolator timeInterpolator) {
        this.M = timeInterpolator;
        E();
    }

    public void b0(Typeface typeface) {
        boolean zN = N(typeface);
        boolean zU = U(typeface);
        if (zN || zU) {
            E();
        }
    }

    public float c() {
        if (this.z == null) {
            return 0.0f;
        }
        w(this.K);
        TextPaint textPaint = this.K;
        CharSequence charSequence = this.z;
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    public void j(Canvas canvas) {
        int iSave = canvas.save();
        if (this.A == null || !this.f3130d) {
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
        if (c0()) {
            k(canvas, lineLeft, f2);
        } else {
            canvas.translate(f, f2);
            this.V.draw(canvas);
        }
        canvas.restoreToCount(iSave);
    }

    public void m(RectF rectF, int i, int i2) {
        this.B = e(this.z);
        rectF.left = p(i, i2);
        rectF.top = this.g.top;
        rectF.right = q(rectF, i, i2);
        rectF.bottom = this.g.top + o();
    }

    public ColorStateList n() {
        return this.n;
    }

    public float o() {
        w(this.K);
        return -this.K.ascent();
    }

    public int r() {
        return s(this.n);
    }

    public float u() {
        x(this.K);
        return -this.K.ascent();
    }

    public float v() {
        return this.e;
    }
}
