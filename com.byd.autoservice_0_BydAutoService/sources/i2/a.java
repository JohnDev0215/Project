package i2;

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
import com.google.android.material.internal.StaticLayoutBuilderCompat;
import k2.a;
import l0.t;

/* JADX INFO: compiled from: CollapsingTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f3290a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Paint f3291b;
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
    public final View f3293c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3294d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f3295e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Rect f3296f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Rect f3297g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final RectF f3298h;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ColorStateList f3303m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ColorStateList f3304n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f3305o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f3306p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f3307q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f3308r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f3309s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f3310t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Typeface f3311u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Typeface f3312v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Typeface f3313w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public k2.a f3314x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public k2.a f3315y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public CharSequence f3316z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3299i = 16;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3300j = 16;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f3301k = 15.0f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f3302l = 15.0f;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f3292a0 = 1;

    /* JADX INFO: renamed from: i2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CollapsingTextHelper.java */
    public class C0021a implements a.InterfaceC0031a {
        public C0021a() {
        }

        @Override // k2.a.InterfaceC0031a
        public void a(Typeface typeface) {
            a.this.S(typeface);
        }
    }

    /* JADX INFO: compiled from: CollapsingTextHelper.java */
    public class b implements a.InterfaceC0031a {
        public b() {
        }

        @Override // k2.a.InterfaceC0031a
        public void a(Typeface typeface) {
            a.this.b0(typeface);
        }
    }

    static {
        f3290a = Build.VERSION.SDK_INT < 18;
        Paint paint = null;
        f3291b = null;
        if (0 != 0) {
            paint.setAntiAlias(true);
            paint.setColor(-65281);
        }
    }

    public a(View view) {
        this.f3293c = view;
        TextPaint textPaint = new TextPaint(129);
        this.J = textPaint;
        this.K = new TextPaint(textPaint);
        this.f3297g = new Rect();
        this.f3296f = new Rect();
        this.f3298h = new RectF();
    }

    public static boolean F(float f3, float f4) {
        return Math.abs(f3 - f4) < 0.001f;
    }

    public static float I(float f3, float f4, float f5, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f5 = timeInterpolator.getInterpolation(f5);
        }
        return s1.a.a(f3, f4, f5);
    }

    public static boolean L(Rect rect, int i3, int i4, int i5, int i6) {
        return rect.left == i3 && rect.top == i4 && rect.right == i5 && rect.bottom == i6;
    }

    public static int a(int i3, int i4, float f3) {
        float f4 = 1.0f - f3;
        return Color.argb((int) ((Color.alpha(i3) * f4) + (Color.alpha(i4) * f3)), (int) ((Color.red(i3) * f4) + (Color.red(i4) * f3)), (int) ((Color.green(i3) * f4) + (Color.green(i4) * f3)), (int) ((Color.blue(i3) * f4) + (Color.blue(i4) * f3)));
    }

    public int A() {
        return this.f3292a0;
    }

    public CharSequence B() {
        return this.f3316z;
    }

    public final void C(TextPaint textPaint) {
        textPaint.setTextSize(this.f3302l);
        textPaint.setTypeface(this.f3311u);
    }

    public final void D(TextPaint textPaint) {
        textPaint.setTextSize(this.f3301k);
        textPaint.setTypeface(this.f3312v);
    }

    public final void E(float f3) {
        this.f3298h.left = I(this.f3296f.left, this.f3297g.left, f3, this.L);
        this.f3298h.top = I(this.f3305o, this.f3306p, f3, this.L);
        this.f3298h.right = I(this.f3296f.right, this.f3297g.right, f3, this.L);
        this.f3298h.bottom = I(this.f3296f.bottom, this.f3297g.bottom, f3, this.L);
    }

    public final boolean G() {
        return t.z(this.f3293c) == 1;
    }

    public final boolean H() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.f3304n;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f3303m) != null && colorStateList.isStateful());
    }

    public void J() {
        this.f3294d = this.f3297g.width() > 0 && this.f3297g.height() > 0 && this.f3296f.width() > 0 && this.f3296f.height() > 0;
    }

    public void K() {
        if (this.f3293c.getHeight() <= 0 || this.f3293c.getWidth() <= 0) {
            return;
        }
        b();
        d();
    }

    public void M(int i3, int i4, int i5, int i6) {
        if (L(this.f3297g, i3, i4, i5, i6)) {
            return;
        }
        this.f3297g.set(i3, i4, i5, i6);
        this.I = true;
        J();
    }

    public void N(Rect rect) {
        M(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void O(int i3) {
        k2.d dVar = new k2.d(this.f3293c.getContext(), i3);
        ColorStateList colorStateList = dVar.f3588b;
        if (colorStateList != null) {
            this.f3304n = colorStateList;
        }
        float f3 = dVar.f3587a;
        if (f3 != 0.0f) {
            this.f3302l = f3;
        }
        ColorStateList colorStateList2 = dVar.f3595i;
        if (colorStateList2 != null) {
            this.Q = colorStateList2;
        }
        this.O = dVar.f3596j;
        this.P = dVar.f3597k;
        this.N = dVar.f3598l;
        k2.a aVar = this.f3315y;
        if (aVar != null) {
            aVar.c();
        }
        this.f3315y = new k2.a(new C0021a(), dVar.e());
        dVar.h(this.f3293c.getContext(), this.f3315y);
        K();
    }

    public final void P(float f3) {
        this.W = f3;
        t.a0(this.f3293c);
    }

    public void Q(ColorStateList colorStateList) {
        if (this.f3304n != colorStateList) {
            this.f3304n = colorStateList;
            K();
        }
    }

    public void R(int i3) {
        if (this.f3300j != i3) {
            this.f3300j = i3;
            K();
        }
    }

    public void S(Typeface typeface) {
        if (T(typeface)) {
            K();
        }
    }

    public final boolean T(Typeface typeface) {
        k2.a aVar = this.f3315y;
        if (aVar != null) {
            aVar.c();
        }
        if (this.f3311u == typeface) {
            return false;
        }
        this.f3311u = typeface;
        return true;
    }

    public void U(int i3, int i4, int i5, int i6) {
        if (L(this.f3296f, i3, i4, i5, i6)) {
            return;
        }
        this.f3296f.set(i3, i4, i5, i6);
        this.I = true;
        J();
    }

    public void V(Rect rect) {
        U(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void W(int i3) {
        k2.d dVar = new k2.d(this.f3293c.getContext(), i3);
        ColorStateList colorStateList = dVar.f3588b;
        if (colorStateList != null) {
            this.f3303m = colorStateList;
        }
        float f3 = dVar.f3587a;
        if (f3 != 0.0f) {
            this.f3301k = f3;
        }
        ColorStateList colorStateList2 = dVar.f3595i;
        if (colorStateList2 != null) {
            this.U = colorStateList2;
        }
        this.S = dVar.f3596j;
        this.T = dVar.f3597k;
        this.R = dVar.f3598l;
        k2.a aVar = this.f3314x;
        if (aVar != null) {
            aVar.c();
        }
        this.f3314x = new k2.a(new b(), dVar.e());
        dVar.h(this.f3293c.getContext(), this.f3314x);
        K();
    }

    public final void X(float f3) {
        this.X = f3;
        t.a0(this.f3293c);
    }

    public void Y(ColorStateList colorStateList) {
        if (this.f3303m != colorStateList) {
            this.f3303m = colorStateList;
            K();
        }
    }

    public void Z(int i3) {
        if (this.f3299i != i3) {
            this.f3299i = i3;
            K();
        }
    }

    public void a0(float f3) {
        if (this.f3301k != f3) {
            this.f3301k = f3;
            K();
        }
    }

    public final void b() {
        StaticLayout staticLayout;
        float f3 = this.G;
        g(this.f3302l);
        CharSequence charSequence = this.A;
        if (charSequence != null && (staticLayout = this.V) != null) {
            this.Z = TextUtils.ellipsize(charSequence, this.J, staticLayout.getWidth(), TextUtils.TruncateAt.END);
        }
        CharSequence charSequence2 = this.Z;
        float fMeasureText = charSequence2 != null ? this.J.measureText(charSequence2, 0, charSequence2.length()) : 0.0f;
        int iB = l0.d.b(this.f3300j, this.B ? 1 : 0);
        int i3 = iB & 112;
        if (i3 == 48) {
            this.f3306p = this.f3297g.top;
        } else if (i3 != 80) {
            this.f3306p = this.f3297g.centerY() - ((this.J.descent() - this.J.ascent()) / 2.0f);
        } else {
            this.f3306p = this.f3297g.bottom + this.J.ascent();
        }
        int i4 = iB & 8388615;
        if (i4 == 1) {
            this.f3308r = this.f3297g.centerX() - (fMeasureText / 2.0f);
        } else if (i4 != 5) {
            this.f3308r = this.f3297g.left;
        } else {
            this.f3308r = this.f3297g.right - fMeasureText;
        }
        g(this.f3301k);
        float height = this.V != null ? r1.getHeight() : 0.0f;
        CharSequence charSequence3 = this.A;
        float fMeasureText2 = charSequence3 != null ? this.J.measureText(charSequence3, 0, charSequence3.length()) : 0.0f;
        StaticLayout staticLayout2 = this.V;
        if (staticLayout2 != null && this.f3292a0 > 1 && !this.B) {
            fMeasureText2 = staticLayout2.getWidth();
        }
        StaticLayout staticLayout3 = this.V;
        this.Y = staticLayout3 != null ? staticLayout3.getLineLeft(0) : 0.0f;
        int iB2 = l0.d.b(this.f3299i, this.B ? 1 : 0);
        int i5 = iB2 & 112;
        if (i5 == 48) {
            this.f3305o = this.f3296f.top;
        } else if (i5 != 80) {
            this.f3305o = this.f3296f.centerY() - (height / 2.0f);
        } else {
            this.f3305o = (this.f3296f.bottom - height) + this.J.descent();
        }
        int i6 = iB2 & 8388615;
        if (i6 == 1) {
            this.f3307q = this.f3296f.centerX() - (fMeasureText2 / 2.0f);
        } else if (i6 != 5) {
            this.f3307q = this.f3296f.left;
        } else {
            this.f3307q = this.f3296f.right - fMeasureText2;
        }
        h();
        e0(f3);
    }

    public void b0(Typeface typeface) {
        if (c0(typeface)) {
            K();
        }
    }

    public float c() {
        if (this.f3316z == null) {
            return 0.0f;
        }
        C(this.K);
        TextPaint textPaint = this.K;
        CharSequence charSequence = this.f3316z;
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    public final boolean c0(Typeface typeface) {
        k2.a aVar = this.f3314x;
        if (aVar != null) {
            aVar.c();
        }
        if (this.f3312v == typeface) {
            return false;
        }
        this.f3312v = typeface;
        return true;
    }

    public final void d() {
        f(this.f3295e);
    }

    public void d0(float f3) {
        float fA = g0.a.a(f3, 0.0f, 1.0f);
        if (fA != this.f3295e) {
            this.f3295e = fA;
            d();
        }
    }

    public final boolean e(CharSequence charSequence) {
        return (G() ? j0.e.f3429d : j0.e.f3428c).a(charSequence, 0, charSequence.length());
    }

    public final void e0(float f3) {
        g(f3);
        boolean z3 = f3290a && this.F != 1.0f;
        this.C = z3;
        if (z3) {
            l();
        }
        t.a0(this.f3293c);
    }

    public final void f(float f3) {
        E(f3);
        this.f3309s = I(this.f3307q, this.f3308r, f3, this.L);
        this.f3310t = I(this.f3305o, this.f3306p, f3, this.L);
        e0(I(this.f3301k, this.f3302l, f3, this.M));
        TimeInterpolator timeInterpolator = s1.a.f4902b;
        P(1.0f - I(0.0f, 1.0f, 1.0f - f3, timeInterpolator));
        X(I(1.0f, 0.0f, f3, timeInterpolator));
        if (this.f3304n != this.f3303m) {
            this.J.setColor(a(v(), t(), f3));
        } else {
            this.J.setColor(t());
        }
        this.J.setShadowLayer(I(this.R, this.N, f3, null), I(this.S, this.O, f3, null), I(this.T, this.P, f3, null), a(u(this.U), u(this.Q), f3));
        t.a0(this.f3293c);
    }

    public void f0(int i3) {
        if (i3 != this.f3292a0) {
            this.f3292a0 = i3;
            h();
            K();
        }
    }

    public final void g(float f3) {
        boolean z3;
        float f4;
        boolean z4;
        if (this.f3316z == null) {
            return;
        }
        float fWidth = this.f3297g.width();
        float fWidth2 = this.f3296f.width();
        if (F(f3, this.f3302l)) {
            f4 = this.f3302l;
            this.F = 1.0f;
            Typeface typeface = this.f3313w;
            Typeface typeface2 = this.f3311u;
            if (typeface != typeface2) {
                this.f3313w = typeface2;
                z4 = true;
            } else {
                z4 = false;
            }
        } else {
            float f5 = this.f3301k;
            Typeface typeface3 = this.f3313w;
            Typeface typeface4 = this.f3312v;
            if (typeface3 != typeface4) {
                this.f3313w = typeface4;
                z3 = true;
            } else {
                z3 = false;
            }
            if (F(f3, f5)) {
                this.F = 1.0f;
            } else {
                this.F = f3 / this.f3301k;
            }
            float f6 = this.f3302l / this.f3301k;
            fWidth = fWidth2 * f6 > fWidth ? Math.min(fWidth / f6, fWidth2) : fWidth2;
            f4 = f5;
            z4 = z3;
        }
        if (fWidth > 0.0f) {
            z4 = this.G != f4 || this.I || z4;
            this.G = f4;
            this.I = false;
        }
        if (this.A == null || z4) {
            this.J.setTextSize(this.G);
            this.J.setTypeface(this.f3313w);
            this.J.setLinearText(this.F != 1.0f);
            this.B = e(this.f3316z);
            StaticLayout staticLayoutI = i(l0() ? this.f3292a0 : 1, fWidth, this.B);
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

    public final StaticLayout i(int i3, float f3, boolean z3) {
        StaticLayout staticLayoutA;
        try {
            staticLayoutA = StaticLayoutBuilderCompat.c(this.f3316z, this.J, (int) f3).e(TextUtils.TruncateAt.END).g(z3).d(Layout.Alignment.ALIGN_NORMAL).f(false).h(i3).a();
        } catch (StaticLayoutBuilderCompat.StaticLayoutBuilderCompatException e3) {
            Log.e("CollapsingTextHelper", e3.getCause().getMessage(), e3);
            staticLayoutA = null;
        }
        return (StaticLayout) k0.h.c(staticLayoutA);
    }

    public void i0(CharSequence charSequence) {
        if (charSequence == null || !TextUtils.equals(this.f3316z, charSequence)) {
            this.f3316z = charSequence;
            this.A = null;
            h();
            K();
        }
    }

    public void j(Canvas canvas) {
        int iSave = canvas.save();
        if (this.A == null || !this.f3294d) {
            return;
        }
        boolean z3 = false;
        float lineLeft = (this.f3309s + this.V.getLineLeft(0)) - (this.Y * 2.0f);
        this.J.setTextSize(this.G);
        float f3 = this.f3309s;
        float f4 = this.f3310t;
        if (this.C && this.D != null) {
            z3 = true;
        }
        float f5 = this.F;
        if (f5 != 1.0f) {
            canvas.scale(f5, f5, f3, f4);
        }
        if (z3) {
            canvas.drawBitmap(this.D, f3, f4, this.E);
            canvas.restoreToCount(iSave);
            return;
        }
        if (l0()) {
            k(canvas, lineLeft, f4);
        } else {
            canvas.translate(f3, f4);
            this.V.draw(canvas);
        }
        canvas.restoreToCount(iSave);
    }

    public void j0(TimeInterpolator timeInterpolator) {
        this.M = timeInterpolator;
        K();
    }

    public final void k(Canvas canvas, float f3, float f4) {
        int alpha = this.J.getAlpha();
        canvas.translate(f3, f4);
        float f5 = alpha;
        this.J.setAlpha((int) (this.X * f5));
        this.V.draw(canvas);
        this.J.setAlpha((int) (this.W * f5));
        int lineBaseline = this.V.getLineBaseline(0);
        CharSequence charSequence = this.Z;
        float f6 = lineBaseline;
        canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, f6, this.J);
        String strTrim = this.Z.toString().trim();
        if (strTrim.endsWith("…")) {
            strTrim = strTrim.substring(0, strTrim.length() - 1);
        }
        String str = strTrim;
        this.J.setAlpha(alpha);
        canvas.drawText(str, 0, Math.min(this.V.getLineEnd(0), str.length()), 0.0f, f6, (Paint) this.J);
    }

    public void k0(Typeface typeface) {
        boolean zT = T(typeface);
        boolean zC0 = c0(typeface);
        if (zT || zC0) {
            K();
        }
    }

    public final void l() {
        if (this.D != null || this.f3296f.isEmpty() || TextUtils.isEmpty(this.A)) {
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
        return (this.f3292a0 <= 1 || this.B || this.C) ? false : true;
    }

    public void m(RectF rectF, int i3, int i4) {
        this.B = e(this.f3316z);
        rectF.left = q(i3, i4);
        rectF.top = this.f3297g.top;
        rectF.right = r(rectF, i3, i4);
        rectF.bottom = this.f3297g.top + p();
    }

    public ColorStateList n() {
        return this.f3304n;
    }

    public int o() {
        return this.f3300j;
    }

    public float p() {
        C(this.K);
        return -this.K.ascent();
    }

    public final float q(int i3, int i4) {
        return (i4 == 17 || (i4 & 7) == 1) ? (i3 / 2.0f) - (c() / 2.0f) : ((i4 & 8388613) == 8388613 || (i4 & 5) == 5) ? this.B ? this.f3297g.left : this.f3297g.right - c() : this.B ? this.f3297g.right - c() : this.f3297g.left;
    }

    public final float r(RectF rectF, int i3, int i4) {
        return (i4 == 17 || (i4 & 7) == 1) ? (i3 / 2.0f) + (c() / 2.0f) : ((i4 & 8388613) == 8388613 || (i4 & 5) == 5) ? this.B ? rectF.left + c() : this.f3297g.right : this.B ? this.f3297g.right : rectF.left + c();
    }

    public Typeface s() {
        Typeface typeface = this.f3311u;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public int t() {
        return u(this.f3304n);
    }

    public final int u(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.H;
        return iArr != null ? colorStateList.getColorForState(iArr, 0) : colorStateList.getDefaultColor();
    }

    public final int v() {
        return u(this.f3303m);
    }

    public int w() {
        return this.f3299i;
    }

    public float x() {
        D(this.K);
        return -this.K.ascent();
    }

    public Typeface y() {
        Typeface typeface = this.f3312v;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public float z() {
        return this.f3295e;
    }
}
