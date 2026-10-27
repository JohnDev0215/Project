package b.d.a.a.z;

import a.b.k.l;
import a.h.i.d;
import a.h.k.q;
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
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final Paint a0 = null;
    public boolean B;
    public Bitmap C;
    public Paint D;
    public float E;
    public float F;
    public int[] G;
    public boolean H;
    public final TextPaint I;
    public final TextPaint J;
    public TimeInterpolator K;
    public TimeInterpolator L;
    public float M;
    public float N;
    public float O;
    public ColorStateList P;
    public float Q;
    public StaticLayout R;
    public float S;
    public float T;
    public float U;
    public CharSequence V;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f2162a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2163b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f2164c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f2165d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f2166e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Rect f2167f;
    public final RectF g;
    public ColorStateList l;
    public ColorStateList m;
    public float n;
    public float o;
    public float p;
    public float q;
    public float r;
    public float s;
    public Typeface t;
    public Typeface u;
    public Typeface v;
    public b.d.a.a.b0.a w;
    public CharSequence x;
    public CharSequence y;
    public boolean z;
    public int h = 16;
    public int i = 16;
    public float j = 15.0f;
    public float k = 15.0f;
    public boolean A = true;
    public int W = 1;
    public float X = 0.0f;
    public float Y = 1.0f;
    public int Z = 1;

    static {
        Paint paint = null;
        if (0 != 0) {
            paint.setAntiAlias(true);
            a0.setColor(-65281);
        }
    }

    public c(View view) {
        this.f2162a = view;
        TextPaint textPaint = new TextPaint(129);
        this.I = textPaint;
        this.J = new TextPaint(textPaint);
        this.f2167f = new Rect();
        this.f2166e = new Rect();
        this.g = new RectF();
        this.f2165d = 0.5f;
    }

    public static int a(int i, int i2, float f2) {
        float f3 = 1.0f - f2;
        return Color.argb((int) ((Color.alpha(i2) * f2) + (Color.alpha(i) * f3)), (int) ((Color.red(i2) * f2) + (Color.red(i) * f3)), (int) ((Color.green(i2) * f2) + (Color.green(i) * f3)), (int) ((Color.blue(i2) * f2) + (Color.blue(i) * f3)));
    }

    public static float i(float f2, float f3, float f4, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f4 = timeInterpolator.getInterpolation(f4);
        }
        return b.d.a.a.l.a.a(f2, f3, f4);
    }

    public static boolean l(Rect rect, int i, int i2, int i3, int i4) {
        return rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4;
    }

    public float b() {
        if (this.x == null) {
            return 0.0f;
        }
        TextPaint textPaint = this.J;
        textPaint.setTextSize(this.k);
        textPaint.setTypeface(this.t);
        textPaint.setLetterSpacing(this.Q);
        TextPaint textPaint2 = this.J;
        CharSequence charSequence = this.x;
        return textPaint2.measureText(charSequence, 0, charSequence.length());
    }

    public final boolean c(CharSequence charSequence) {
        boolean z = q.p(this.f2162a) == 1;
        if (this.A) {
            return ((d.c) (z ? a.h.i.d.f794d : a.h.i.d.f793c)).b(charSequence, 0, charSequence.length());
        }
        return z;
    }

    public final void d(float f2) {
        TextPaint textPaint;
        int iG;
        TextPaint textPaint2;
        this.g.left = i(this.f2166e.left, this.f2167f.left, f2, this.K);
        this.g.top = i(this.n, this.o, f2, this.K);
        this.g.right = i(this.f2166e.right, this.f2167f.right, f2, this.K);
        this.g.bottom = i(this.f2166e.bottom, this.f2167f.bottom, f2, this.K);
        this.r = i(this.p, this.q, f2, this.K);
        this.s = i(this.n, this.o, f2, this.K);
        p(i(this.j, this.k, f2, this.L));
        this.S = 1.0f - i(0.0f, 1.0f, 1.0f - f2, b.d.a.a.l.a.f1967b);
        q.M(this.f2162a);
        this.T = i(1.0f, 0.0f, f2, b.d.a.a.l.a.f1967b);
        q.M(this.f2162a);
        ColorStateList colorStateList = this.m;
        ColorStateList colorStateList2 = this.l;
        if (colorStateList != colorStateList2) {
            textPaint = this.I;
            iG = a(h(colorStateList2), g(), f2);
        } else {
            textPaint = this.I;
            iG = g();
        }
        textPaint.setColor(iG);
        float fI = this.Q;
        if (fI != 0.0f) {
            textPaint2 = this.I;
            fI = i(0.0f, fI, f2, b.d.a.a.l.a.f1967b);
        } else {
            textPaint2 = this.I;
        }
        textPaint2.setLetterSpacing(fI);
        this.I.setShadowLayer(i(0.0f, this.M, f2, null), i(0.0f, this.N, f2, null), i(0.0f, this.O, f2, null), a(h(null), h(this.P), f2));
        this.f2162a.postInvalidateOnAnimation();
    }

    public final void e(float f2, boolean z) {
        boolean z2;
        float f3;
        StaticLayout staticLayoutBuild;
        if (this.x == null) {
            return;
        }
        float fWidth = this.f2167f.width();
        float fWidth2 = this.f2166e.width();
        if (Math.abs(f2 - this.k) < 0.001f) {
            f3 = this.k;
            this.E = 1.0f;
            Typeface typeface = this.v;
            Typeface typeface2 = this.t;
            if (typeface != typeface2) {
                this.v = typeface2;
                z2 = true;
            } else {
                z2 = false;
            }
        } else {
            float f4 = this.j;
            Typeface typeface3 = this.v;
            Typeface typeface4 = this.u;
            if (typeface3 != typeface4) {
                this.v = typeface4;
                z2 = true;
            } else {
                z2 = false;
            }
            if (Math.abs(f2 - this.j) < 0.001f) {
                this.E = 1.0f;
            } else {
                this.E = f2 / this.j;
            }
            float f5 = this.k / this.j;
            fWidth = (!z && fWidth2 * f5 > fWidth) ? Math.min(fWidth / f5, fWidth2) : fWidth2;
            f3 = f4;
        }
        if (fWidth > 0.0f) {
            z2 = this.F != f3 || this.H || z2;
            this.F = f3;
            this.H = false;
        }
        if (this.y == null || z2) {
            this.I.setTextSize(this.F);
            this.I.setTypeface(this.v);
            this.I.setLinearText(this.E != 1.0f);
            this.z = c(this.x);
            int i = r() ? this.W : 1;
            boolean z3 = this.z;
            try {
                CharSequence charSequenceEllipsize = this.x;
                TextPaint textPaint = this.I;
                int length = charSequenceEllipsize.length();
                TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
                Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
                float f6 = this.X;
                float f7 = this.Y;
                int i2 = this.Z;
                int iMax = Math.max(0, (int) fWidth);
                if (i == 1) {
                    charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, textPaint, iMax, truncateAt);
                }
                int iMin = Math.min(charSequenceEllipsize.length(), length);
                if (z3 && i == 1) {
                    alignment = Layout.Alignment.ALIGN_OPPOSITE;
                }
                StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequenceEllipsize, 0, iMin, textPaint, iMax);
                builderObtain.setAlignment(alignment);
                builderObtain.setIncludePad(false);
                builderObtain.setTextDirection(z3 ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
                if (truncateAt != null) {
                    builderObtain.setEllipsize(truncateAt);
                }
                builderObtain.setMaxLines(i);
                if (f6 != 0.0f || f7 != 1.0f) {
                    builderObtain.setLineSpacing(f6, f7);
                }
                if (i > 1) {
                    builderObtain.setHyphenationFrequency(i2);
                }
                staticLayoutBuild = builderObtain.build();
            } catch (h e2) {
                Log.e("CollapsingTextHelper", e2.getCause().getMessage(), e2);
                staticLayoutBuild = null;
            }
            l.i.h(staticLayoutBuild);
            this.R = staticLayoutBuild;
            this.y = staticLayoutBuild.getText();
        }
    }

    public float f() {
        TextPaint textPaint = this.J;
        textPaint.setTextSize(this.k);
        textPaint.setTypeface(this.t);
        textPaint.setLetterSpacing(this.Q);
        return -this.J.ascent();
    }

    public int g() {
        return h(this.m);
    }

    public final int h(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.G;
        return iArr != null ? colorStateList.getColorForState(iArr, 0) : colorStateList.getDefaultColor();
    }

    public void j() {
        this.f2163b = this.f2167f.width() > 0 && this.f2167f.height() > 0 && this.f2166e.width() > 0 && this.f2166e.height() > 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0137  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void k() {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.d.a.a.z.c.k():void");
    }

    public void m(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            k();
        }
    }

    public void n(int i) {
        if (this.i != i) {
            this.i = i;
            k();
        }
    }

    public void o(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        if (f2 != this.f2164c) {
            this.f2164c = f2;
            d(f2);
        }
    }

    public final void p(float f2) {
        e(f2, false);
        this.B = false;
        if (0 != 0 && this.C == null && !this.f2166e.isEmpty() && !TextUtils.isEmpty(this.y)) {
            d(0.0f);
            int width = this.R.getWidth();
            int height = this.R.getHeight();
            if (width > 0 && height > 0) {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                this.C = bitmapCreateBitmap;
                this.R.draw(new Canvas(bitmapCreateBitmap));
                if (this.D == null) {
                    this.D = new Paint(3);
                }
            }
        }
        q.M(this.f2162a);
    }

    public void q(Typeface typeface) {
        boolean z;
        b.d.a.a.b0.a aVar = this.w;
        boolean z2 = true;
        if (aVar != null) {
            aVar.f1803c = true;
        }
        if (this.t != typeface) {
            this.t = typeface;
            z = true;
        } else {
            z = false;
        }
        if (this.u != typeface) {
            this.u = typeface;
        } else {
            z2 = false;
        }
        if (z || z2) {
            k();
        }
    }

    public final boolean r() {
        return (this.W <= 1 || this.z || this.B) ? false : true;
    }
}
