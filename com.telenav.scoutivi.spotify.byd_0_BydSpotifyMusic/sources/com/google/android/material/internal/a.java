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
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import b3.a;
import com.google.android.material.internal.g;
import i0.u;

/* JADX INFO: compiled from: CollapsingTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private static final boolean f4586j0 = false;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private static final Paint f4587k0 = null;
    private b3.a A;
    private CharSequence B;
    private CharSequence C;
    private boolean D;
    private boolean F;
    private Bitmap G;
    private Paint H;
    private float I;
    private float J;
    private int[] K;
    private boolean L;
    private final TextPaint M;
    private final TextPaint N;
    private TimeInterpolator O;
    private TimeInterpolator P;
    private float Q;
    private float R;
    private float S;
    private ColorStateList T;
    private float U;
    private float V;
    private float W;
    private ColorStateList X;
    private float Y;
    private float Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f4588a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private StaticLayout f4589a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4590b;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    private float f4591b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f4592c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private float f4593c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4594d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private float f4595d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f4596e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private CharSequence f4597e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f4598f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4600g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Rect f4602h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Rect f4604i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final RectF f4606j;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private ColorStateList f4611o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private ColorStateList f4612p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private float f4613q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private float f4614r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private float f4615s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private float f4616t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private float f4617u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private float f4618v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private Typeface f4619w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private Typeface f4620x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private Typeface f4621y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private b3.a f4622z;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f4607k = 16;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f4608l = 16;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private float f4609m = 15.0f;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private float f4610n = 15.0f;
    private boolean E = true;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private int f4599f0 = 1;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private float f4601g0 = 0.0f;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private float f4603h0 = 1.0f;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private int f4605i0 = g.f4643n;

    /* JADX INFO: renamed from: com.google.android.material.internal.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CollapsingTextHelper.java */
    class C0050a implements a.InterfaceC0033a {
        C0050a() {
        }

        @Override // b3.a.InterfaceC0033a
        public void a(Typeface typeface) {
            a.this.R(typeface);
        }
    }

    public a(View view) {
        this.f4588a = view;
        TextPaint textPaint = new TextPaint(129);
        this.M = textPaint;
        this.N = new TextPaint(textPaint);
        this.f4604i = new Rect();
        this.f4602h = new Rect();
        this.f4606j = new RectF();
        this.f4598f = f();
    }

    private void A(TextPaint textPaint) {
        textPaint.setTextSize(this.f4609m);
        textPaint.setTypeface(this.f4620x);
        textPaint.setLetterSpacing(this.Z);
    }

    private void B(float f7) {
        if (this.f4594d) {
            this.f4606j.set(f7 < this.f4598f ? this.f4602h : this.f4604i);
            return;
        }
        this.f4606j.left = G(this.f4602h.left, this.f4604i.left, f7, this.O);
        this.f4606j.top = G(this.f4613q, this.f4614r, f7, this.O);
        this.f4606j.right = G(this.f4602h.right, this.f4604i.right, f7, this.O);
        this.f4606j.bottom = G(this.f4602h.bottom, this.f4604i.bottom, f7, this.O);
    }

    private static boolean C(float f7, float f8) {
        return Math.abs(f7 - f8) < 0.001f;
    }

    private boolean D() {
        return u.y(this.f4588a) == 1;
    }

    private boolean F(CharSequence charSequence, boolean z6) {
        return (z6 ? g0.d.f6453d : g0.d.f6452c).a(charSequence, 0, charSequence.length());
    }

    private static float G(float f7, float f8, float f9, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f9 = timeInterpolator.getInterpolation(f9);
        }
        return p2.a.a(f7, f8, f9);
    }

    private static boolean K(Rect rect, int i7, int i8, int i9, int i10) {
        return rect.left == i7 && rect.top == i8 && rect.right == i9 && rect.bottom == i10;
    }

    private void O(float f7) {
        this.f4591b0 = f7;
        u.b0(this.f4588a);
    }

    private boolean S(Typeface typeface) {
        b3.a aVar = this.A;
        if (aVar != null) {
            aVar.c();
        }
        if (this.f4619w == typeface) {
            return false;
        }
        this.f4619w = typeface;
        return true;
    }

    private void V(float f7) {
        this.f4593c0 = f7;
        u.b0(this.f4588a);
    }

    private boolean Z(Typeface typeface) {
        b3.a aVar = this.f4622z;
        if (aVar != null) {
            aVar.c();
        }
        if (this.f4620x == typeface) {
            return false;
        }
        this.f4620x = typeface;
        return true;
    }

    private static int a(int i7, int i8, float f7) {
        float f8 = 1.0f - f7;
        return Color.argb((int) ((Color.alpha(i7) * f8) + (Color.alpha(i8) * f7)), (int) ((Color.red(i7) * f8) + (Color.red(i8) * f7)), (int) ((Color.green(i7) * f8) + (Color.green(i8) * f7)), (int) ((Color.blue(i7) * f8) + (Color.blue(i8) * f7)));
    }

    private void b(boolean z6) {
        StaticLayout staticLayout;
        float f7 = this.J;
        j(this.f4610n, z6);
        CharSequence charSequence = this.C;
        if (charSequence != null && (staticLayout = this.f4589a0) != null) {
            this.f4597e0 = TextUtils.ellipsize(charSequence, this.M, staticLayout.getWidth(), TextUtils.TruncateAt.END);
        }
        CharSequence charSequence2 = this.f4597e0;
        float fMeasureText = charSequence2 != null ? this.M.measureText(charSequence2, 0, charSequence2.length()) : 0.0f;
        int iB = i0.e.b(this.f4608l, this.D ? 1 : 0);
        int i7 = iB & 112;
        if (i7 == 48) {
            this.f4614r = this.f4604i.top;
        } else if (i7 != 80) {
            this.f4614r = this.f4604i.centerY() - ((this.M.descent() - this.M.ascent()) / 2.0f);
        } else {
            this.f4614r = this.f4604i.bottom + this.M.ascent();
        }
        int i8 = iB & 8388615;
        if (i8 == 1) {
            this.f4616t = this.f4604i.centerX() - (fMeasureText / 2.0f);
        } else if (i8 != 5) {
            this.f4616t = this.f4604i.left;
        } else {
            this.f4616t = this.f4604i.right - fMeasureText;
        }
        j(this.f4609m, z6);
        float height = this.f4589a0 != null ? r13.getHeight() : 0.0f;
        CharSequence charSequence3 = this.C;
        float fMeasureText2 = charSequence3 != null ? this.M.measureText(charSequence3, 0, charSequence3.length()) : 0.0f;
        StaticLayout staticLayout2 = this.f4589a0;
        if (staticLayout2 != null && this.f4599f0 > 1) {
            fMeasureText2 = staticLayout2.getWidth();
        }
        StaticLayout staticLayout3 = this.f4589a0;
        this.f4595d0 = staticLayout3 != null ? this.f4599f0 > 1 ? staticLayout3.getLineStart(0) : staticLayout3.getLineLeft(0) : 0.0f;
        int iB2 = i0.e.b(this.f4607k, this.D ? 1 : 0);
        int i9 = iB2 & 112;
        if (i9 == 48) {
            this.f4613q = this.f4602h.top;
        } else if (i9 != 80) {
            this.f4613q = this.f4602h.centerY() - (height / 2.0f);
        } else {
            this.f4613q = (this.f4602h.bottom - height) + this.M.descent();
        }
        int i10 = iB2 & 8388615;
        if (i10 == 1) {
            this.f4615s = this.f4602h.centerX() - (fMeasureText2 / 2.0f);
        } else if (i10 != 5) {
            this.f4615s = this.f4602h.left;
        } else {
            this.f4615s = this.f4602h.right - fMeasureText2;
        }
        k();
        b0(f7);
    }

    private void b0(float f7) {
        i(f7);
        boolean z6 = f4586j0 && this.I != 1.0f;
        this.F = z6;
        if (z6) {
            o();
        }
        u.b0(this.f4588a);
    }

    private void d() {
        h(this.f4592c);
    }

    private float e(float f7) {
        float f8 = this.f4598f;
        return f7 <= f8 ? p2.a.b(1.0f, 0.0f, this.f4596e, f8, f7) : p2.a.b(0.0f, 1.0f, f8, 1.0f, f7);
    }

    private float f() {
        float f7 = this.f4596e;
        return f7 + ((1.0f - f7) * 0.5f);
    }

    private boolean g(CharSequence charSequence) {
        boolean zD = D();
        return this.E ? F(charSequence, zD) : zD;
    }

    private void h(float f7) {
        float f8;
        B(f7);
        if (!this.f4594d) {
            this.f4617u = G(this.f4615s, this.f4616t, f7, this.O);
            this.f4618v = G(this.f4613q, this.f4614r, f7, this.O);
            b0(G(this.f4609m, this.f4610n, f7, this.P));
            f8 = f7;
        } else if (f7 < this.f4598f) {
            this.f4617u = this.f4615s;
            this.f4618v = this.f4613q;
            b0(this.f4609m);
            f8 = 0.0f;
        } else {
            this.f4617u = this.f4616t;
            this.f4618v = this.f4614r - Math.max(0, this.f4600g);
            b0(this.f4610n);
            f8 = 1.0f;
        }
        TimeInterpolator timeInterpolator = p2.a.f8821b;
        O(1.0f - G(0.0f, 1.0f, 1.0f - f7, timeInterpolator));
        V(G(1.0f, 0.0f, f7, timeInterpolator));
        if (this.f4612p != this.f4611o) {
            this.M.setColor(a(w(), u(), f8));
        } else {
            this.M.setColor(u());
        }
        float f9 = this.Y;
        float f10 = this.Z;
        if (f9 != f10) {
            this.M.setLetterSpacing(G(f10, f9, f7, timeInterpolator));
        } else {
            this.M.setLetterSpacing(f9);
        }
        this.M.setShadowLayer(G(this.U, this.Q, f7, null), G(this.V, this.R, f7, null), G(this.W, this.S, f7, null), a(v(this.X), v(this.T), f7));
        if (this.f4594d) {
            this.M.setAlpha((int) (e(f7) * 255.0f));
        }
        u.b0(this.f4588a);
    }

    private boolean h0() {
        return this.f4599f0 > 1 && (!this.D || this.f4594d) && !this.F;
    }

    private void i(float f7) {
        j(f7, false);
    }

    private void j(float f7, boolean z6) {
        boolean z7;
        float f8;
        boolean z8;
        if (this.B == null) {
            return;
        }
        float fWidth = this.f4604i.width();
        float fWidth2 = this.f4602h.width();
        if (C(f7, this.f4610n)) {
            f8 = this.f4610n;
            this.I = 1.0f;
            Typeface typeface = this.f4621y;
            Typeface typeface2 = this.f4619w;
            if (typeface != typeface2) {
                this.f4621y = typeface2;
                z8 = true;
            } else {
                z8 = false;
            }
        } else {
            float f9 = this.f4609m;
            Typeface typeface3 = this.f4621y;
            Typeface typeface4 = this.f4620x;
            if (typeface3 != typeface4) {
                this.f4621y = typeface4;
                z7 = true;
            } else {
                z7 = false;
            }
            if (C(f7, f9)) {
                this.I = 1.0f;
            } else {
                this.I = f7 / this.f4609m;
            }
            float f10 = this.f4610n / this.f4609m;
            fWidth = (!z6 && fWidth2 * f10 > fWidth) ? Math.min(fWidth / f10, fWidth2) : fWidth2;
            f8 = f9;
            z8 = z7;
        }
        if (fWidth > 0.0f) {
            z8 = this.J != f8 || this.L || z8;
            this.J = f8;
            this.L = false;
        }
        if (this.C == null || z8) {
            this.M.setTextSize(this.J);
            this.M.setTypeface(this.f4621y);
            this.M.setLinearText(this.I != 1.0f);
            this.D = g(this.B);
            StaticLayout staticLayoutL = l(h0() ? this.f4599f0 : 1, fWidth, this.D);
            this.f4589a0 = staticLayoutL;
            this.C = staticLayoutL.getText();
        }
    }

    private void k() {
        Bitmap bitmap = this.G;
        if (bitmap != null) {
            bitmap.recycle();
            this.G = null;
        }
    }

    private StaticLayout l(int i7, float f7, boolean z6) {
        StaticLayout staticLayoutA;
        try {
            staticLayoutA = g.b(this.B, this.M, (int) f7).d(TextUtils.TruncateAt.END).g(z6).c(Layout.Alignment.ALIGN_NORMAL).f(false).i(i7).h(this.f4601g0, this.f4603h0).e(this.f4605i0).a();
        } catch (g.a e7) {
            Log.e("CollapsingTextHelper", e7.getCause().getMessage(), e7);
            staticLayoutA = null;
        }
        return (StaticLayout) h0.h.f(staticLayoutA);
    }

    private void n(Canvas canvas, float f7, float f8) {
        int alpha = this.M.getAlpha();
        canvas.translate(f7, f8);
        float f9 = alpha;
        this.M.setAlpha((int) (this.f4593c0 * f9));
        this.f4589a0.draw(canvas);
        this.M.setAlpha((int) (this.f4591b0 * f9));
        int lineBaseline = this.f4589a0.getLineBaseline(0);
        CharSequence charSequence = this.f4597e0;
        float f10 = lineBaseline;
        canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, f10, this.M);
        if (this.f4594d) {
            return;
        }
        String strTrim = this.f4597e0.toString().trim();
        if (strTrim.endsWith("…")) {
            strTrim = strTrim.substring(0, strTrim.length() - 1);
        }
        String str = strTrim;
        this.M.setAlpha(alpha);
        canvas.drawText(str, 0, Math.min(this.f4589a0.getLineEnd(0), str.length()), 0.0f, f10, (Paint) this.M);
    }

    private void o() {
        if (this.G != null || this.f4602h.isEmpty() || TextUtils.isEmpty(this.C)) {
            return;
        }
        h(0.0f);
        int width = this.f4589a0.getWidth();
        int height = this.f4589a0.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        this.G = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.f4589a0.draw(new Canvas(this.G));
        if (this.H == null) {
            this.H = new Paint(3);
        }
    }

    private float s(int i7, int i8) {
        return (i8 == 17 || (i8 & 7) == 1) ? (i7 / 2.0f) - (c() / 2.0f) : ((i8 & 8388613) == 8388613 || (i8 & 5) == 5) ? this.D ? this.f4604i.left : this.f4604i.right - c() : this.D ? this.f4604i.right - c() : this.f4604i.left;
    }

    private float t(RectF rectF, int i7, int i8) {
        return (i8 == 17 || (i8 & 7) == 1) ? (i7 / 2.0f) + (c() / 2.0f) : ((i8 & 8388613) == 8388613 || (i8 & 5) == 5) ? this.D ? rectF.left + c() : this.f4604i.right : this.D ? this.f4604i.right : rectF.left + c();
    }

    private int v(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.K;
        return iArr != null ? colorStateList.getColorForState(iArr, 0) : colorStateList.getDefaultColor();
    }

    private int w() {
        return v(this.f4611o);
    }

    private void z(TextPaint textPaint) {
        textPaint.setTextSize(this.f4610n);
        textPaint.setTypeface(this.f4619w);
        textPaint.setLetterSpacing(this.Y);
    }

    public final boolean E() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.f4612p;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f4611o) != null && colorStateList.isStateful());
    }

    void H() {
        this.f4590b = this.f4604i.width() > 0 && this.f4604i.height() > 0 && this.f4602h.width() > 0 && this.f4602h.height() > 0;
    }

    public void I() {
        J(false);
    }

    public void J(boolean z6) {
        if ((this.f4588a.getHeight() <= 0 || this.f4588a.getWidth() <= 0) && !z6) {
            return;
        }
        b(z6);
        d();
    }

    public void L(int i7, int i8, int i9, int i10) {
        if (K(this.f4604i, i7, i8, i9, i10)) {
            return;
        }
        this.f4604i.set(i7, i8, i9, i10);
        this.L = true;
        H();
    }

    public void M(Rect rect) {
        L(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void N(int i7) {
        b3.d dVar = new b3.d(this.f4588a.getContext(), i7);
        ColorStateList colorStateList = dVar.f3530a;
        if (colorStateList != null) {
            this.f4612p = colorStateList;
        }
        float f7 = dVar.f3543n;
        if (f7 != 0.0f) {
            this.f4610n = f7;
        }
        ColorStateList colorStateList2 = dVar.f3533d;
        if (colorStateList2 != null) {
            this.T = colorStateList2;
        }
        this.R = dVar.f3538i;
        this.S = dVar.f3539j;
        this.Q = dVar.f3540k;
        this.Y = dVar.f3542m;
        b3.a aVar = this.A;
        if (aVar != null) {
            aVar.c();
        }
        this.A = new b3.a(new C0050a(), dVar.e());
        dVar.h(this.f4588a.getContext(), this.A);
        I();
    }

    public void P(ColorStateList colorStateList) {
        if (this.f4612p != colorStateList) {
            this.f4612p = colorStateList;
            I();
        }
    }

    public void Q(int i7) {
        if (this.f4608l != i7) {
            this.f4608l = i7;
            I();
        }
    }

    public void R(Typeface typeface) {
        if (S(typeface)) {
            I();
        }
    }

    public void T(int i7, int i8, int i9, int i10) {
        if (K(this.f4602h, i7, i8, i9, i10)) {
            return;
        }
        this.f4602h.set(i7, i8, i9, i10);
        this.L = true;
        H();
    }

    public void U(Rect rect) {
        T(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void W(ColorStateList colorStateList) {
        if (this.f4611o != colorStateList) {
            this.f4611o = colorStateList;
            I();
        }
    }

    public void X(int i7) {
        if (this.f4607k != i7) {
            this.f4607k = i7;
            I();
        }
    }

    public void Y(float f7) {
        if (this.f4609m != f7) {
            this.f4609m = f7;
            I();
        }
    }

    public void a0(float f7) {
        float fA = c0.a.a(f7, 0.0f, 1.0f);
        if (fA != this.f4592c) {
            this.f4592c = fA;
            d();
        }
    }

    public float c() {
        if (this.B == null) {
            return 0.0f;
        }
        z(this.N);
        TextPaint textPaint = this.N;
        CharSequence charSequence = this.B;
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    public void c0(TimeInterpolator timeInterpolator) {
        this.O = timeInterpolator;
        I();
    }

    public final boolean d0(int[] iArr) {
        this.K = iArr;
        if (!E()) {
            return false;
        }
        I();
        return true;
    }

    public void e0(CharSequence charSequence) {
        if (charSequence == null || !TextUtils.equals(this.B, charSequence)) {
            this.B = charSequence;
            this.C = null;
            k();
            I();
        }
    }

    public void f0(TimeInterpolator timeInterpolator) {
        this.P = timeInterpolator;
        I();
    }

    public void g0(Typeface typeface) {
        boolean zS = S(typeface);
        boolean Z = Z(typeface);
        if (zS || Z) {
            I();
        }
    }

    public void m(Canvas canvas) {
        int iSave = canvas.save();
        if (this.C == null || !this.f4590b) {
            return;
        }
        float lineStart = (this.f4617u + (this.f4599f0 > 1 ? this.f4589a0.getLineStart(0) : this.f4589a0.getLineLeft(0))) - (this.f4595d0 * 2.0f);
        this.M.setTextSize(this.J);
        float f7 = this.f4617u;
        float f8 = this.f4618v;
        boolean z6 = this.F && this.G != null;
        float f9 = this.I;
        if (f9 != 1.0f && !this.f4594d) {
            canvas.scale(f9, f9, f7, f8);
        }
        if (z6) {
            canvas.drawBitmap(this.G, f7, f8, this.H);
            canvas.restoreToCount(iSave);
            return;
        }
        if (!h0() || (this.f4594d && this.f4592c <= this.f4598f)) {
            canvas.translate(f7, f8);
            this.f4589a0.draw(canvas);
        } else {
            n(canvas, lineStart, f8);
        }
        canvas.restoreToCount(iSave);
    }

    public void p(RectF rectF, int i7, int i8) {
        this.D = g(this.B);
        rectF.left = s(i7, i8);
        rectF.top = this.f4604i.top;
        rectF.right = t(rectF, i7, i8);
        rectF.bottom = this.f4604i.top + r();
    }

    public ColorStateList q() {
        return this.f4612p;
    }

    public float r() {
        z(this.N);
        return -this.N.ascent();
    }

    public int u() {
        return v(this.f4612p);
    }

    public float x() {
        A(this.N);
        return -this.N.ascent();
    }

    public float y() {
        return this.f4592c;
    }
}
