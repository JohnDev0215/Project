package c.b.a.a.q;

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
import b.g.j.t;
import c.b.a.a.s.a;
import com.google.android.material.internal.StaticLayoutBuilderCompat;

/* JADX INFO: compiled from: CollapsingTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f1773a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Paint f1774b;
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
    public final View f1775c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1776d;
    public float e;
    public final Rect f;
    public final Rect g;
    public final RectF h;
    public ColorStateList m;
    public ColorStateList n;
    public float o;
    public float p;
    public float q;
    public float r;
    public float s;
    public float t;
    public Typeface u;
    public Typeface v;
    public Typeface w;
    public c.b.a.a.s.a x;
    public c.b.a.a.s.a y;
    public CharSequence z;
    public int i = 16;
    public int j = 16;
    public float k = 15.0f;
    public float l = 15.0f;
    public int a0 = 1;

    /* JADX INFO: renamed from: c.b.a.a.q.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CollapsingTextHelper.java */
    public class C0055a implements a.InterfaceC0057a {
        public C0055a() {
        }

        @Override // c.b.a.a.s.a.InterfaceC0057a
        public void a(Typeface typeface) {
            a.this.S(typeface);
        }
    }

    /* JADX INFO: compiled from: CollapsingTextHelper.java */
    public class b implements a.InterfaceC0057a {
        public b() {
        }

        @Override // c.b.a.a.s.a.InterfaceC0057a
        public void a(Typeface typeface) {
            a.this.b0(typeface);
        }
    }

    static {
        f1773a = Build.VERSION.SDK_INT < 18;
        Paint paint = null;
        f1774b = null;
        if (0 != 0) {
            paint.setAntiAlias(true);
            paint.setColor(-65281);
        }
    }

    public a(View view) {
        this.f1775c = view;
        TextPaint textPaint = new TextPaint(129);
        this.J = textPaint;
        this.K = new TextPaint(textPaint);
        this.g = new Rect();
        this.f = new Rect();
        this.h = new RectF();
    }

    public static boolean F(float f, float f2) {
        return Math.abs(f - f2) < 0.001f;
    }

    public static float I(float f, float f2, float f3, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f3 = timeInterpolator.getInterpolation(f3);
        }
        return c.b.a.a.a.a.a(f, f2, f3);
    }

    public static boolean L(Rect rect, int i, int i2, int i3, int i4) {
        return rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4;
    }

    public static int a(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.argb((int) ((Color.alpha(i) * f2) + (Color.alpha(i2) * f)), (int) ((Color.red(i) * f2) + (Color.red(i2) * f)), (int) ((Color.green(i) * f2) + (Color.green(i2) * f)), (int) ((Color.blue(i) * f2) + (Color.blue(i2) * f)));
    }

    public int A() {
        return this.a0;
    }

    public CharSequence B() {
        return this.z;
    }

    public final void C(TextPaint textPaint) {
        textPaint.setTextSize(this.l);
        textPaint.setTypeface(this.u);
    }

    public final void D(TextPaint textPaint) {
        textPaint.setTextSize(this.k);
        textPaint.setTypeface(this.v);
    }

    public final void E(float f) {
        this.h.left = I(this.f.left, this.g.left, f, this.L);
        this.h.top = I(this.o, this.p, f, this.L);
        this.h.right = I(this.f.right, this.g.right, f, this.L);
        this.h.bottom = I(this.f.bottom, this.g.bottom, f, this.L);
    }

    public final boolean G() {
        return t.z(this.f1775c) == 1;
    }

    public final boolean H() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.n;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.m) != null && colorStateList.isStateful());
    }

    public void J() {
        this.f1776d = this.g.width() > 0 && this.g.height() > 0 && this.f.width() > 0 && this.f.height() > 0;
    }

    public void K() {
        if (this.f1775c.getHeight() <= 0 || this.f1775c.getWidth() <= 0) {
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
        c.b.a.a.s.d dVar = new c.b.a.a.s.d(this.f1775c.getContext(), i);
        ColorStateList colorStateList = dVar.f1826b;
        if (colorStateList != null) {
            this.n = colorStateList;
        }
        float f = dVar.f1825a;
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
        c.b.a.a.s.a aVar = this.y;
        if (aVar != null) {
            aVar.c();
        }
        this.y = new c.b.a.a.s.a(new C0055a(), dVar.e());
        dVar.h(this.f1775c.getContext(), this.y);
        K();
    }

    public final void P(float f) {
        this.W = f;
        t.a0(this.f1775c);
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

    public final boolean T(Typeface typeface) {
        c.b.a.a.s.a aVar = this.y;
        if (aVar != null) {
            aVar.c();
        }
        if (this.u == typeface) {
            return false;
        }
        this.u = typeface;
        return true;
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
        c.b.a.a.s.d dVar = new c.b.a.a.s.d(this.f1775c.getContext(), i);
        ColorStateList colorStateList = dVar.f1826b;
        if (colorStateList != null) {
            this.m = colorStateList;
        }
        float f = dVar.f1825a;
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
        c.b.a.a.s.a aVar = this.x;
        if (aVar != null) {
            aVar.c();
        }
        this.x = new c.b.a.a.s.a(new b(), dVar.e());
        dVar.h(this.f1775c.getContext(), this.x);
        K();
    }

    public final void X(float f) {
        this.X = f;
        t.a0(this.f1775c);
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

    public final void b() {
        StaticLayout staticLayout;
        float f = this.G;
        g(this.l);
        CharSequence charSequence = this.A;
        if (charSequence != null && (staticLayout = this.V) != null) {
            this.Z = TextUtils.ellipsize(charSequence, this.J, staticLayout.getWidth(), TextUtils.TruncateAt.END);
        }
        CharSequence charSequence2 = this.Z;
        float fMeasureText = charSequence2 != null ? this.J.measureText(charSequence2, 0, charSequence2.length()) : 0.0f;
        int iB = b.g.j.d.b(this.j, this.B ? 1 : 0);
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
        int iB2 = b.g.j.d.b(this.i, this.B ? 1 : 0);
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

    public final boolean c0(Typeface typeface) {
        c.b.a.a.s.a aVar = this.x;
        if (aVar != null) {
            aVar.c();
        }
        if (this.v == typeface) {
            return false;
        }
        this.v = typeface;
        return true;
    }

    public final void d() {
        f(this.e);
    }

    public void d0(float f) {
        float fA = b.g.e.a.a(f, 0.0f, 1.0f);
        if (fA != this.e) {
            this.e = fA;
            d();
        }
    }

    public final boolean e(CharSequence charSequence) {
        return (G() ? b.g.h.e.f1075d : b.g.h.e.f1074c).a(charSequence, 0, charSequence.length());
    }

    public final void e0(float f) {
        g(f);
        boolean z = f1773a && this.F != 1.0f;
        this.C = z;
        if (z) {
            l();
        }
        t.a0(this.f1775c);
    }

    public final void f(float f) {
        E(f);
        this.s = I(this.q, this.r, f, this.L);
        this.t = I(this.o, this.p, f, this.L);
        e0(I(this.k, this.l, f, this.M));
        TimeInterpolator timeInterpolator = c.b.a.a.a.a.f1649b;
        P(1.0f - I(0.0f, 1.0f, 1.0f - f, timeInterpolator));
        X(I(1.0f, 0.0f, f, timeInterpolator));
        if (this.n != this.m) {
            this.J.setColor(a(v(), t(), f));
        } else {
            this.J.setColor(t());
        }
        this.J.setShadowLayer(I(this.R, this.N, f, null), I(this.S, this.O, f, null), I(this.T, this.P, f, null), a(u(this.U), u(this.Q), f));
        t.a0(this.f1775c);
    }

    public void f0(int i) {
        if (i != this.a0) {
            this.a0 = i;
            h();
            K();
        }
    }

    public final void g(float f) {
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

    public final StaticLayout i(int i, float f, boolean z) {
        StaticLayout staticLayoutA;
        try {
            staticLayoutA = StaticLayoutBuilderCompat.c(this.z, this.J, (int) f).e(TextUtils.TruncateAt.END).g(z).d(Layout.Alignment.ALIGN_NORMAL).f(false).h(i).a();
        } catch (StaticLayoutBuilderCompat.StaticLayoutBuilderCompatException e) {
            Log.e("CollapsingTextHelper", e.getCause().getMessage(), e);
            staticLayoutA = null;
        }
        return (StaticLayout) b.g.i.h.c(staticLayoutA);
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
        if (this.A == null || !this.f1776d) {
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

    public final void k(Canvas canvas, float f, float f2) {
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

    public void k0(Typeface typeface) {
        boolean zT = T(typeface);
        boolean zC0 = c0(typeface);
        if (zT || zC0) {
            K();
        }
    }

    public final void l() {
        if (this.D != null || this.f.isEmpty() || TextUtils.isEmpty(this.A)) {
            return;
        }
        f(0.0f);
        int width = this.V.getWidth();
        int height = this.V.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        this.D = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.V.draw(new Canvas(this.D));
        if (this.E == null) {
            this.E = new Paint(3);
        }
    }

    public final boolean l0() {
        return (this.a0 <= 1 || this.B || this.C) ? false : true;
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

    public final float q(int i, int i2) {
        return (i2 == 17 || (i2 & 7) == 1) ? (i / 2.0f) - (c() / 2.0f) : ((i2 & 8388613) == 8388613 || (i2 & 5) == 5) ? this.B ? this.g.left : this.g.right - c() : this.B ? this.g.right - c() : this.g.left;
    }

    public final float r(RectF rectF, int i, int i2) {
        return (i2 == 17 || (i2 & 7) == 1) ? (i / 2.0f) + (c() / 2.0f) : ((i2 & 8388613) == 8388613 || (i2 & 5) == 5) ? this.B ? rectF.left + c() : this.g.right : this.B ? this.g.right : rectF.left + c();
    }

    public Typeface s() {
        Typeface typeface = this.u;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public int t() {
        return u(this.n);
    }

    public final int u(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.H;
        return iArr != null ? colorStateList.getColorForState(iArr, 0) : colorStateList.getDefaultColor();
    }

    public final int v() {
        return u(this.m);
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
        return this.e;
    }
}
