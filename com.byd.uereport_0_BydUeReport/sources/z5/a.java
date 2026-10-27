package z5;

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
import b6.a;
import com.google.android.material.internal.StaticLayoutBuilderCompat;
import o0.t;

/* JADX INFO: compiled from: CollapsingTextHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f9521a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Paint f9522b;
    public CharSequence A;
    public boolean B;
    public boolean C;
    public Bitmap D;
    public Paint E;
    public float F;
    public float G;
    public int[] H;
    public boolean I;
    public final TextPaint J;
    public final TextPaint K;
    public TimeInterpolator L;
    public TimeInterpolator M;
    public float N;
    public float O;
    public float P;
    public ColorStateList Q;
    public float R;
    public float S;
    public float T;
    public ColorStateList U;
    public StaticLayout V;
    public float W;
    public float X;
    public float Y;
    public CharSequence Z;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f9524c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f9525d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f9526e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Rect f9527f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Rect f9528g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final RectF f9529h;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ColorStateList f9534m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ColorStateList f9535n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f9536o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f9537p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f9538q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f9539r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f9540s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f9541t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Typeface f9542u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Typeface f9543v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Typeface f9544w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public b6.a f9545x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public b6.a f9546y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public CharSequence f9547z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f9530i = 16;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f9531j = 16;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f9532k = 15.0f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f9533l = 15.0f;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f9523a0 = 1;

    /* JADX INFO: renamed from: z5.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CollapsingTextHelper.java */
    public class C0154a implements a.InterfaceC0007a {
        public C0154a() {
        }

        @Override // b6.a.InterfaceC0007a
        public void a(Typeface typeface) {
            a.this.S(typeface);
        }
    }

    /* JADX INFO: compiled from: CollapsingTextHelper.java */
    public class b implements a.InterfaceC0007a {
        public b() {
        }

        @Override // b6.a.InterfaceC0007a
        public void a(Typeface typeface) {
            a.this.b0(typeface);
        }
    }

    static {
        f9521a = Build.VERSION.SDK_INT < 18;
        Paint paint = null;
        f9522b = null;
        if (0 != 0) {
            paint.setAntiAlias(true);
            paint.setColor(-65281);
        }
    }

    public a(View view) {
        this.f9524c = view;
        TextPaint textPaint = new TextPaint(129);
        this.J = textPaint;
        this.K = new TextPaint(textPaint);
        this.f9528g = new Rect();
        this.f9527f = new Rect();
        this.f9529h = new RectF();
    }

    public static boolean F(float f8, float f9) {
        return Math.abs(f8 - f9) < 0.001f;
    }

    public static float I(float f8, float f9, float f10, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f10 = timeInterpolator.getInterpolation(f10);
        }
        return j5.a.a(f8, f9, f10);
    }

    public static boolean L(Rect rect, int i8, int i9, int i10, int i11) {
        return rect.left == i8 && rect.top == i9 && rect.right == i10 && rect.bottom == i11;
    }

    public static int a(int i8, int i9, float f8) {
        float f9 = 1.0f - f8;
        return Color.argb((int) ((Color.alpha(i8) * f9) + (Color.alpha(i9) * f8)), (int) ((Color.red(i8) * f9) + (Color.red(i9) * f8)), (int) ((Color.green(i8) * f9) + (Color.green(i9) * f8)), (int) ((Color.blue(i8) * f9) + (Color.blue(i9) * f8)));
    }

    public int A() {
        return this.f9523a0;
    }

    public CharSequence B() {
        return this.f9547z;
    }

    public final void C(TextPaint textPaint) {
        textPaint.setTextSize(this.f9533l);
        textPaint.setTypeface(this.f9542u);
    }

    public final void D(TextPaint textPaint) {
        textPaint.setTextSize(this.f9532k);
        textPaint.setTypeface(this.f9543v);
    }

    public final void E(float f8) {
        this.f9529h.left = I(this.f9527f.left, this.f9528g.left, f8, this.L);
        this.f9529h.top = I(this.f9536o, this.f9537p, f8, this.L);
        this.f9529h.right = I(this.f9527f.right, this.f9528g.right, f8, this.L);
        this.f9529h.bottom = I(this.f9527f.bottom, this.f9528g.bottom, f8, this.L);
    }

    public final boolean G() {
        return t.A(this.f9524c) == 1;
    }

    public final boolean H() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.f9535n;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f9534m) != null && colorStateList.isStateful());
    }

    public void J() {
        this.f9525d = this.f9528g.width() > 0 && this.f9528g.height() > 0 && this.f9527f.width() > 0 && this.f9527f.height() > 0;
    }

    public void K() {
        if (this.f9524c.getHeight() <= 0 || this.f9524c.getWidth() <= 0) {
            return;
        }
        b();
        d();
    }

    public void M(int i8, int i9, int i10, int i11) {
        if (L(this.f9528g, i8, i9, i10, i11)) {
            return;
        }
        this.f9528g.set(i8, i9, i10, i11);
        this.I = true;
        J();
    }

    public void N(Rect rect) {
        M(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void O(int i8) {
        b6.d dVar = new b6.d(this.f9524c.getContext(), i8);
        ColorStateList colorStateList = dVar.f2106b;
        if (colorStateList != null) {
            this.f9535n = colorStateList;
        }
        float f8 = dVar.f2105a;
        if (f8 != 0.0f) {
            this.f9533l = f8;
        }
        ColorStateList colorStateList2 = dVar.f2113i;
        if (colorStateList2 != null) {
            this.Q = colorStateList2;
        }
        this.O = dVar.f2114j;
        this.P = dVar.f2115k;
        this.N = dVar.f2116l;
        b6.a aVar = this.f9546y;
        if (aVar != null) {
            aVar.c();
        }
        this.f9546y = new b6.a(new C0154a(), dVar.e());
        dVar.h(this.f9524c.getContext(), this.f9546y);
        K();
    }

    public final void P(float f8) {
        this.W = f8;
        t.b0(this.f9524c);
    }

    public void Q(ColorStateList colorStateList) {
        if (this.f9535n != colorStateList) {
            this.f9535n = colorStateList;
            K();
        }
    }

    public void R(int i8) {
        if (this.f9531j != i8) {
            this.f9531j = i8;
            K();
        }
    }

    public void S(Typeface typeface) {
        if (T(typeface)) {
            K();
        }
    }

    public final boolean T(Typeface typeface) {
        b6.a aVar = this.f9546y;
        if (aVar != null) {
            aVar.c();
        }
        if (this.f9542u == typeface) {
            return false;
        }
        this.f9542u = typeface;
        return true;
    }

    public void U(int i8, int i9, int i10, int i11) {
        if (L(this.f9527f, i8, i9, i10, i11)) {
            return;
        }
        this.f9527f.set(i8, i9, i10, i11);
        this.I = true;
        J();
    }

    public void V(Rect rect) {
        U(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void W(int i8) {
        b6.d dVar = new b6.d(this.f9524c.getContext(), i8);
        ColorStateList colorStateList = dVar.f2106b;
        if (colorStateList != null) {
            this.f9534m = colorStateList;
        }
        float f8 = dVar.f2105a;
        if (f8 != 0.0f) {
            this.f9532k = f8;
        }
        ColorStateList colorStateList2 = dVar.f2113i;
        if (colorStateList2 != null) {
            this.U = colorStateList2;
        }
        this.S = dVar.f2114j;
        this.T = dVar.f2115k;
        this.R = dVar.f2116l;
        b6.a aVar = this.f9545x;
        if (aVar != null) {
            aVar.c();
        }
        this.f9545x = new b6.a(new b(), dVar.e());
        dVar.h(this.f9524c.getContext(), this.f9545x);
        K();
    }

    public final void X(float f8) {
        this.X = f8;
        t.b0(this.f9524c);
    }

    public void Y(ColorStateList colorStateList) {
        if (this.f9534m != colorStateList) {
            this.f9534m = colorStateList;
            K();
        }
    }

    public void Z(int i8) {
        if (this.f9530i != i8) {
            this.f9530i = i8;
            K();
        }
    }

    public void a0(float f8) {
        if (this.f9532k != f8) {
            this.f9532k = f8;
            K();
        }
    }

    public final void b() {
        StaticLayout staticLayout;
        float f8 = this.G;
        g(this.f9533l);
        CharSequence charSequence = this.A;
        if (charSequence != null && (staticLayout = this.V) != null) {
            this.Z = TextUtils.ellipsize(charSequence, this.J, staticLayout.getWidth(), TextUtils.TruncateAt.END);
        }
        CharSequence charSequence2 = this.Z;
        float fMeasureText = charSequence2 != null ? this.J.measureText(charSequence2, 0, charSequence2.length()) : 0.0f;
        int iB = o0.d.b(this.f9531j, this.B ? 1 : 0);
        int i8 = iB & 112;
        if (i8 == 48) {
            this.f9537p = this.f9528g.top;
        } else if (i8 != 80) {
            this.f9537p = this.f9528g.centerY() - ((this.J.descent() - this.J.ascent()) / 2.0f);
        } else {
            this.f9537p = this.f9528g.bottom + this.J.ascent();
        }
        int i9 = iB & 8388615;
        if (i9 == 1) {
            this.f9539r = this.f9528g.centerX() - (fMeasureText / 2.0f);
        } else if (i9 != 5) {
            this.f9539r = this.f9528g.left;
        } else {
            this.f9539r = this.f9528g.right - fMeasureText;
        }
        g(this.f9532k);
        float height = this.V != null ? r1.getHeight() : 0.0f;
        CharSequence charSequence3 = this.A;
        float fMeasureText2 = charSequence3 != null ? this.J.measureText(charSequence3, 0, charSequence3.length()) : 0.0f;
        StaticLayout staticLayout2 = this.V;
        if (staticLayout2 != null && this.f9523a0 > 1 && !this.B) {
            fMeasureText2 = staticLayout2.getWidth();
        }
        StaticLayout staticLayout3 = this.V;
        this.Y = staticLayout3 != null ? staticLayout3.getLineLeft(0) : 0.0f;
        int iB2 = o0.d.b(this.f9530i, this.B ? 1 : 0);
        int i10 = iB2 & 112;
        if (i10 == 48) {
            this.f9536o = this.f9527f.top;
        } else if (i10 != 80) {
            this.f9536o = this.f9527f.centerY() - (height / 2.0f);
        } else {
            this.f9536o = (this.f9527f.bottom - height) + this.J.descent();
        }
        int i11 = iB2 & 8388615;
        if (i11 == 1) {
            this.f9538q = this.f9527f.centerX() - (fMeasureText2 / 2.0f);
        } else if (i11 != 5) {
            this.f9538q = this.f9527f.left;
        } else {
            this.f9538q = this.f9527f.right - fMeasureText2;
        }
        h();
        e0(f8);
    }

    public void b0(Typeface typeface) {
        if (c0(typeface)) {
            K();
        }
    }

    public float c() {
        if (this.f9547z == null) {
            return 0.0f;
        }
        C(this.K);
        TextPaint textPaint = this.K;
        CharSequence charSequence = this.f9547z;
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    public final boolean c0(Typeface typeface) {
        b6.a aVar = this.f9545x;
        if (aVar != null) {
            aVar.c();
        }
        if (this.f9543v == typeface) {
            return false;
        }
        this.f9543v = typeface;
        return true;
    }

    public final void d() {
        f(this.f9526e);
    }

    public void d0(float f8) {
        float fA = j0.a.a(f8, 0.0f, 1.0f);
        if (fA != this.f9526e) {
            this.f9526e = fA;
            d();
        }
    }

    public final boolean e(CharSequence charSequence) {
        return (G() ? m0.e.f6330d : m0.e.f6329c).a(charSequence, 0, charSequence.length());
    }

    public final void e0(float f8) {
        g(f8);
        boolean z7 = f9521a && this.F != 1.0f;
        this.C = z7;
        if (z7) {
            l();
        }
        t.b0(this.f9524c);
    }

    public final void f(float f8) {
        E(f8);
        this.f9540s = I(this.f9538q, this.f9539r, f8, this.L);
        this.f9541t = I(this.f9536o, this.f9537p, f8, this.L);
        e0(I(this.f9532k, this.f9533l, f8, this.M));
        TimeInterpolator timeInterpolator = j5.a.f5579b;
        P(1.0f - I(0.0f, 1.0f, 1.0f - f8, timeInterpolator));
        X(I(1.0f, 0.0f, f8, timeInterpolator));
        if (this.f9535n != this.f9534m) {
            this.J.setColor(a(v(), t(), f8));
        } else {
            this.J.setColor(t());
        }
        this.J.setShadowLayer(I(this.R, this.N, f8, null), I(this.S, this.O, f8, null), I(this.T, this.P, f8, null), a(u(this.U), u(this.Q), f8));
        t.b0(this.f9524c);
    }

    public void f0(int i8) {
        if (i8 != this.f9523a0) {
            this.f9523a0 = i8;
            h();
            K();
        }
    }

    public final void g(float f8) {
        boolean z7;
        float f9;
        boolean z8;
        if (this.f9547z == null) {
            return;
        }
        float fWidth = this.f9528g.width();
        float fWidth2 = this.f9527f.width();
        if (F(f8, this.f9533l)) {
            f9 = this.f9533l;
            this.F = 1.0f;
            Typeface typeface = this.f9544w;
            Typeface typeface2 = this.f9542u;
            if (typeface != typeface2) {
                this.f9544w = typeface2;
                z8 = true;
            } else {
                z8 = false;
            }
        } else {
            float f10 = this.f9532k;
            Typeface typeface3 = this.f9544w;
            Typeface typeface4 = this.f9543v;
            if (typeface3 != typeface4) {
                this.f9544w = typeface4;
                z7 = true;
            } else {
                z7 = false;
            }
            if (F(f8, f10)) {
                this.F = 1.0f;
            } else {
                this.F = f8 / this.f9532k;
            }
            float f11 = this.f9533l / this.f9532k;
            fWidth = fWidth2 * f11 > fWidth ? Math.min(fWidth / f11, fWidth2) : fWidth2;
            f9 = f10;
            z8 = z7;
        }
        if (fWidth > 0.0f) {
            z8 = this.G != f9 || this.I || z8;
            this.G = f9;
            this.I = false;
        }
        if (this.A == null || z8) {
            this.J.setTextSize(this.G);
            this.J.setTypeface(this.f9544w);
            this.J.setLinearText(this.F != 1.0f);
            this.B = e(this.f9547z);
            StaticLayout staticLayoutI = i(l0() ? this.f9523a0 : 1, fWidth, this.B);
            this.V = staticLayoutI;
            this.A = staticLayoutI.getText();
        }
    }

    public void g0(TimeInterpolator timeInterpolator) {
        this.L = timeInterpolator;
        K();
    }

    public final void h() {
        Bitmap bitmap = this.D;
        if (bitmap != null) {
            bitmap.recycle();
            this.D = null;
        }
    }

    public final boolean h0(int[] iArr) {
        this.H = iArr;
        if (!H()) {
            return false;
        }
        K();
        return true;
    }

    public final StaticLayout i(int i8, float f8, boolean z7) {
        StaticLayout staticLayoutA;
        try {
            staticLayoutA = StaticLayoutBuilderCompat.c(this.f9547z, this.J, (int) f8).e(TextUtils.TruncateAt.END).g(z7).d(Layout.Alignment.ALIGN_NORMAL).f(false).h(i8).a();
        } catch (StaticLayoutBuilderCompat.StaticLayoutBuilderCompatException e8) {
            Log.e("CollapsingTextHelper", e8.getCause().getMessage(), e8);
            staticLayoutA = null;
        }
        return (StaticLayout) n0.h.e(staticLayoutA);
    }

    public void i0(CharSequence charSequence) {
        if (charSequence == null || !TextUtils.equals(this.f9547z, charSequence)) {
            this.f9547z = charSequence;
            this.A = null;
            h();
            K();
        }
    }

    public void j(Canvas canvas) {
        int iSave = canvas.save();
        if (this.A == null || !this.f9525d) {
            return;
        }
        boolean z7 = false;
        float lineLeft = (this.f9540s + this.V.getLineLeft(0)) - (this.Y * 2.0f);
        this.J.setTextSize(this.G);
        float f8 = this.f9540s;
        float f9 = this.f9541t;
        if (this.C && this.D != null) {
            z7 = true;
        }
        float f10 = this.F;
        if (f10 != 1.0f) {
            canvas.scale(f10, f10, f8, f9);
        }
        if (z7) {
            canvas.drawBitmap(this.D, f8, f9, this.E);
            canvas.restoreToCount(iSave);
            return;
        }
        if (l0()) {
            k(canvas, lineLeft, f9);
        } else {
            canvas.translate(f8, f9);
            this.V.draw(canvas);
        }
        canvas.restoreToCount(iSave);
    }

    public void j0(TimeInterpolator timeInterpolator) {
        this.M = timeInterpolator;
        K();
    }

    public final void k(Canvas canvas, float f8, float f9) {
        int alpha = this.J.getAlpha();
        canvas.translate(f8, f9);
        float f10 = alpha;
        this.J.setAlpha((int) (this.X * f10));
        this.V.draw(canvas);
        this.J.setAlpha((int) (this.W * f10));
        int lineBaseline = this.V.getLineBaseline(0);
        CharSequence charSequence = this.Z;
        float f11 = lineBaseline;
        canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, f11, this.J);
        String strTrim = this.Z.toString().trim();
        if (strTrim.endsWith("…")) {
            strTrim = strTrim.substring(0, strTrim.length() - 1);
        }
        String str = strTrim;
        this.J.setAlpha(alpha);
        canvas.drawText(str, 0, Math.min(this.V.getLineEnd(0), str.length()), 0.0f, f11, (Paint) this.J);
    }

    public void k0(Typeface typeface) {
        boolean zT = T(typeface);
        boolean zC0 = c0(typeface);
        if (zT || zC0) {
            K();
        }
    }

    public final void l() {
        if (this.D != null || this.f9527f.isEmpty() || TextUtils.isEmpty(this.A)) {
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

    public final boolean l0() {
        return (this.f9523a0 <= 1 || this.B || this.C) ? false : true;
    }

    public void m(RectF rectF, int i8, int i9) {
        this.B = e(this.f9547z);
        rectF.left = q(i8, i9);
        rectF.top = this.f9528g.top;
        rectF.right = r(rectF, i8, i9);
        rectF.bottom = this.f9528g.top + p();
    }

    public ColorStateList n() {
        return this.f9535n;
    }

    public int o() {
        return this.f9531j;
    }

    public float p() {
        C(this.K);
        return -this.K.ascent();
    }

    public final float q(int i8, int i9) {
        return (i9 == 17 || (i9 & 7) == 1) ? (i8 / 2.0f) - (c() / 2.0f) : ((i9 & 8388613) == 8388613 || (i9 & 5) == 5) ? this.B ? this.f9528g.left : this.f9528g.right - c() : this.B ? this.f9528g.right - c() : this.f9528g.left;
    }

    public final float r(RectF rectF, int i8, int i9) {
        return (i9 == 17 || (i9 & 7) == 1) ? (i8 / 2.0f) + (c() / 2.0f) : ((i9 & 8388613) == 8388613 || (i9 & 5) == 5) ? this.B ? rectF.left + c() : this.f9528g.right : this.B ? this.f9528g.right : rectF.left + c();
    }

    public Typeface s() {
        Typeface typeface = this.f9542u;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public int t() {
        return u(this.f9535n);
    }

    public final int u(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.H;
        return iArr != null ? colorStateList.getColorForState(iArr, 0) : colorStateList.getDefaultColor();
    }

    public final int v() {
        return u(this.f9534m);
    }

    public int w() {
        return this.f9530i;
    }

    public float x() {
        D(this.K);
        return -this.K.ascent();
    }

    public Typeface y() {
        Typeface typeface = this.f9543v;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public float z() {
        return this.f9526e;
    }
}
