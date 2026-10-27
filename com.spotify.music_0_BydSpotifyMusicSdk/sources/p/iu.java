package p;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class iu {
    public float A;
    public float B;
    public int[] C;
    public boolean D;
    public final TextPaint E;
    public final TextPaint F;
    public TimeInterpolator G;
    public TimeInterpolator H;
    public float I;
    public float J;
    public float K;
    public ColorStateList L;
    public float M;
    public StaticLayout N;
    public float O;
    public CharSequence P;
    public final View a;
    public boolean b;
    public float c;
    public final Rect d;
    public final Rect e;
    public final RectF f;
    public int g = 16;
    public int h = 16;
    public float i = 15.0f;
    public float j = 15.0f;
    public ColorStateList k;
    public ColorStateList l;
    public float m;
    public float n;
    public float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f136p;
    public float q;
    public float r;
    public Typeface s;
    public Typeface t;
    public Typeface u;
    public wp v;
    public CharSequence w;
    public CharSequence x;
    public boolean y;
    public Bitmap z;

    public iu(View view) {
        this.a = view;
        TextPaint textPaint = new TextPaint(129);
        this.E = textPaint;
        this.F = new TextPaint(textPaint);
        this.e = new Rect();
        this.d = new Rect();
        this.f = new RectF();
    }

    public static int a(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.argb((int) ((Color.alpha(i2) * f) + (Color.alpha(i) * f2)), (int) ((Color.red(i2) * f) + (Color.red(i) * f2)), (int) ((Color.green(i2) * f) + (Color.green(i) * f2)), (int) ((Color.blue(i2) * f) + (Color.blue(i) * f2)));
    }

    public static float e(float f, float f2, float f3, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f3 = timeInterpolator.getInterpolation(f3);
        }
        LinearInterpolator linearInterpolator = e7.a;
        return ((f2 - f) * f3) + f;
    }

    public final void b(float f) {
        boolean z;
        float f2;
        boolean z2;
        if (this.w == null) {
            return;
        }
        float fWidth = this.e.width();
        float fWidth2 = this.d.width();
        if (Math.abs(f - this.j) < 0.001f) {
            f2 = this.j;
            this.A = 1.0f;
            Typeface typeface = this.u;
            Typeface typeface2 = this.s;
            if (typeface != typeface2) {
                this.u = typeface2;
                z2 = true;
            } else {
                z2 = false;
            }
        } else {
            float f3 = this.i;
            Typeface typeface3 = this.u;
            Typeface typeface4 = this.t;
            if (typeface3 != typeface4) {
                this.u = typeface4;
                z = true;
            } else {
                z = false;
            }
            if (Math.abs(f - f3) < 0.001f) {
                this.A = 1.0f;
            } else {
                this.A = f / this.i;
            }
            float f4 = this.j / this.i;
            fWidth = fWidth2 * f4 > fWidth ? Math.min(fWidth / f4, fWidth2) : fWidth2;
            f2 = f3;
            z2 = z;
        }
        if (fWidth > 0.0f) {
            z2 = this.B != f2 || this.D || z2;
            this.B = f2;
            this.D = false;
        }
        if (this.x == null || z2) {
            TextPaint textPaint = this.E;
            textPaint.setTextSize(this.B);
            textPaint.setTypeface(this.u);
            textPaint.setLinearText(this.A != 1.0f);
            CharSequence charSequence = this.w;
            WeakHashMap weakHashMap = zt2.a;
            boolean zC = (jt2.d(this.a) == 1 ? oj2.d : oj2.c).c(charSequence, charSequence.length());
            this.y = zC;
            zf2 zf2Var = new zf2(this.w, textPaint, (int) fWidth);
            zf2Var.l = TextUtils.TruncateAt.END;
            zf2Var.k = zC;
            zf2Var.e = Layout.Alignment.ALIGN_NORMAL;
            zf2Var.j = false;
            zf2Var.f = 1;
            zf2Var.g = 0.0f;
            zf2Var.h = 1.0f;
            zf2Var.i = 1;
            StaticLayout staticLayoutA = zf2Var.a();
            staticLayoutA.getClass();
            this.N = staticLayoutA;
            this.x = staticLayoutA.getText();
        }
    }

    public final float c() {
        TextPaint textPaint = this.F;
        textPaint.setTextSize(this.j);
        textPaint.setTypeface(this.s);
        textPaint.setLetterSpacing(this.M);
        return -textPaint.ascent();
    }

    public final int d(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.C;
        return iArr != null ? colorStateList.getColorForState(iArr, 0) : colorStateList.getDefaultColor();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f() {
        /*
            r2 = this;
            android.graphics.Rect r0 = r2.e
            int r1 = r0.width()
            if (r1 <= 0) goto L1e
            int r0 = r0.height()
            if (r0 <= 0) goto L1e
            android.graphics.Rect r0 = r2.d
            int r1 = r0.width()
            if (r1 <= 0) goto L1e
            int r0 = r0.height()
            if (r0 <= 0) goto L1e
            r0 = 1
            goto L1f
        L1e:
            r0 = 0
        L1f:
            r2.b = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.iu.f():void");
    }

    public final void g() {
        StaticLayout staticLayout;
        View view = this.a;
        if (view.getHeight() <= 0 || view.getWidth() <= 0) {
            return;
        }
        float f = this.B;
        b(this.j);
        CharSequence charSequence = this.x;
        TextPaint textPaint = this.E;
        if (charSequence != null && (staticLayout = this.N) != null) {
            this.P = TextUtils.ellipsize(charSequence, textPaint, staticLayout.getWidth(), TextUtils.TruncateAt.END);
        }
        if (this.P != null) {
            TextPaint textPaint2 = new TextPaint(textPaint);
            textPaint2.setLetterSpacing(this.M);
            CharSequence charSequence2 = this.P;
            this.O = textPaint2.measureText(charSequence2, 0, charSequence2.length());
        } else {
            this.O = 0.0f;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(this.h, this.y ? 1 : 0);
        int i = absoluteGravity & 112;
        Rect rect = this.e;
        if (i == 48) {
            this.n = rect.top;
        } else if (i != 80) {
            this.n = rect.centerY() - ((textPaint.descent() - textPaint.ascent()) / 2.0f);
        } else {
            this.n = textPaint.ascent() + rect.bottom;
        }
        int i2 = absoluteGravity & 8388615;
        if (i2 == 1) {
            this.f136p = rect.centerX() - (this.O / 2.0f);
        } else if (i2 != 5) {
            this.f136p = rect.left;
        } else {
            this.f136p = rect.right - this.O;
        }
        b(this.i);
        float height = this.N != null ? r3.getHeight() : 0.0f;
        StaticLayout staticLayout2 = this.N;
        if (staticLayout2 != null) {
            staticLayout2.getLineCount();
        }
        CharSequence charSequence3 = this.x;
        float fMeasureText = charSequence3 != null ? textPaint.measureText(charSequence3, 0, charSequence3.length()) : 0.0f;
        StaticLayout staticLayout3 = this.N;
        if (staticLayout3 != null) {
            staticLayout3.getLineLeft(0);
        }
        int absoluteGravity2 = Gravity.getAbsoluteGravity(this.g, this.y ? 1 : 0);
        int i3 = absoluteGravity2 & 112;
        Rect rect2 = this.d;
        if (i3 == 48) {
            this.m = rect2.top;
        } else if (i3 != 80) {
            this.m = rect2.centerY() - (height / 2.0f);
        } else {
            this.m = textPaint.descent() + (rect2.bottom - height);
        }
        int i4 = absoluteGravity2 & 8388615;
        if (i4 == 1) {
            this.o = rect2.centerX() - (fMeasureText / 2.0f);
        } else if (i4 != 5) {
            this.o = rect2.left;
        } else {
            this.o = rect2.right - fMeasureText;
        }
        Bitmap bitmap = this.z;
        if (bitmap != null) {
            bitmap.recycle();
            this.z = null;
        }
        j(f);
        float f2 = this.c;
        RectF rectF = this.f;
        rectF.left = e(rect2.left, rect.left, f2, this.G);
        rectF.top = e(this.m, this.n, f2, this.G);
        rectF.right = e(rect2.right, rect.right, f2, this.G);
        rectF.bottom = e(rect2.bottom, rect.bottom, f2, this.G);
        this.q = e(this.o, this.f136p, f2, this.G);
        this.r = e(this.m, this.n, f2, this.G);
        j(e(this.i, this.j, f2, this.H));
        am0 am0Var = e7.b;
        e(0.0f, 1.0f, 1.0f - f2, am0Var);
        WeakHashMap weakHashMap = zt2.a;
        it2.k(view);
        e(1.0f, 0.0f, f2, am0Var);
        it2.k(view);
        ColorStateList colorStateList = this.l;
        ColorStateList colorStateList2 = this.k;
        if (colorStateList != colorStateList2) {
            textPaint.setColor(a(d(colorStateList2), d(this.l), f2));
        } else {
            textPaint.setColor(d(colorStateList));
        }
        float f3 = this.M;
        if (f3 != 0.0f) {
            textPaint.setLetterSpacing(e(0.0f, f3, f2, am0Var));
        } else {
            textPaint.setLetterSpacing(f3);
        }
        textPaint.setShadowLayer(e(0.0f, this.I, f2, null), e(0.0f, this.J, f2, null), e(0.0f, this.K, f2, null), a(d(null), d(this.L), f2));
        it2.k(view);
    }

    public final void h(ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            g();
        }
    }

    public final void i(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 1.0f) {
            f = 1.0f;
        }
        if (f != this.c) {
            this.c = f;
            RectF rectF = this.f;
            float f2 = this.d.left;
            Rect rect = this.e;
            rectF.left = e(f2, rect.left, f, this.G);
            rectF.top = e(this.m, this.n, f, this.G);
            rectF.right = e(r3.right, rect.right, f, this.G);
            rectF.bottom = e(r3.bottom, rect.bottom, f, this.G);
            this.q = e(this.o, this.f136p, f, this.G);
            this.r = e(this.m, this.n, f, this.G);
            j(e(this.i, this.j, f, this.H));
            am0 am0Var = e7.b;
            e(0.0f, 1.0f, 1.0f - f, am0Var);
            WeakHashMap weakHashMap = zt2.a;
            View view = this.a;
            it2.k(view);
            e(1.0f, 0.0f, f, am0Var);
            it2.k(view);
            ColorStateList colorStateList = this.l;
            ColorStateList colorStateList2 = this.k;
            TextPaint textPaint = this.E;
            if (colorStateList != colorStateList2) {
                textPaint.setColor(a(d(colorStateList2), d(this.l), f));
            } else {
                textPaint.setColor(d(colorStateList));
            }
            float f3 = this.M;
            if (f3 != 0.0f) {
                textPaint.setLetterSpacing(e(0.0f, f3, f, am0Var));
            } else {
                textPaint.setLetterSpacing(f3);
            }
            textPaint.setShadowLayer(e(0.0f, this.I, f, null), e(0.0f, this.J, f, null), e(0.0f, this.K, f, null), a(d(null), d(this.L), f));
            it2.k(view);
        }
    }

    public final void j(float f) {
        b(f);
        WeakHashMap weakHashMap = zt2.a;
        it2.k(this.a);
    }
}
