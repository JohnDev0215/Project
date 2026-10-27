package U4;

import F1.H;
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
import android.view.animation.LinearInterpolator;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public float f9017A;
    public float B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int[] f9018C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f9019D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final TextPaint f9020E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final TextPaint f9021F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public LinearInterpolator f9022G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public LinearInterpolator f9023H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public float f9024I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public float f9025J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public float f9026K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public ColorStateList f9027L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public float f9028M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public StaticLayout f9029N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public CharSequence f9030O;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextInputLayout f9031a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f9032b;
    public float c;
    public final Rect d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f9033e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RectF f9034f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f9035g = 16;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f9036h = 16;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f9037i = 15.0f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f9038j = 15.0f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ColorStateList f9039k;
    public ColorStateList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f9040m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f9041n;
    public float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f9042p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f9043q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f9044r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Typeface f9045s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Typeface f9046t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Typeface f9047u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public V4.a f9048v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public CharSequence f9049w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public CharSequence f9050x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f9051y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Bitmap f9052z;

    public b(TextInputLayout textInputLayout) {
        this.f9031a = textInputLayout;
        TextPaint textPaint = new TextPaint(129);
        this.f9020E = textPaint;
        this.f9021F = new TextPaint(textPaint);
        this.f9033e = new Rect();
        this.d = new Rect();
        this.f9034f = new RectF();
    }

    public static int a(int i7, int i10, float f10) {
        float f11 = 1.0f - f10;
        return Color.argb((int) ((Color.alpha(i10) * f10) + (Color.alpha(i7) * f11)), (int) ((Color.red(i10) * f10) + (Color.red(i7) * f11)), (int) ((Color.green(i10) * f10) + (Color.green(i7) * f11)), (int) ((Color.blue(i10) * f10) + (Color.blue(i7) * f11)));
    }

    public static float e(float f10, float f11, float f12, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f12 = timeInterpolator.getInterpolation(f12);
        }
        return L4.a.a(f10, f11, f12);
    }

    public final float b() {
        if (this.f9049w == null) {
            return 0.0f;
        }
        TextPaint textPaint = this.f9021F;
        textPaint.setTextSize(this.f9038j);
        textPaint.setTypeface(this.f9045s);
        textPaint.setLetterSpacing(this.f9028M);
        CharSequence charSequence = this.f9049w;
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    public final void c(float f10) {
        boolean z6;
        float f11;
        boolean z10;
        if (this.f9049w == null) {
            return;
        }
        float fWidth = this.f9033e.width();
        float fWidth2 = this.d.width();
        if (Math.abs(f10 - this.f9038j) < 0.001f) {
            f11 = this.f9038j;
            this.f9017A = 1.0f;
            Typeface typeface = this.f9047u;
            Typeface typeface2 = this.f9045s;
            if (typeface != typeface2) {
                this.f9047u = typeface2;
                z10 = true;
            } else {
                z10 = false;
            }
        } else {
            float f12 = this.f9037i;
            Typeface typeface3 = this.f9047u;
            Typeface typeface4 = this.f9046t;
            if (typeface3 != typeface4) {
                this.f9047u = typeface4;
                z6 = true;
            } else {
                z6 = false;
            }
            if (Math.abs(f10 - f12) < 0.001f) {
                this.f9017A = 1.0f;
            } else {
                this.f9017A = f10 / this.f9037i;
            }
            float f13 = this.f9038j / this.f9037i;
            fWidth = fWidth2 * f13 > fWidth ? Math.min(fWidth / f13, fWidth2) : fWidth2;
            f11 = f12;
            z10 = z6;
        }
        if (fWidth > 0.0f) {
            z10 = this.B != f11 || this.f9019D || z10;
            this.B = f11;
            this.f9019D = false;
        }
        if (this.f9050x == null || z10) {
            TextPaint textPaint = this.f9020E;
            textPaint.setTextSize(this.B);
            textPaint.setTypeface(this.f9047u);
            textPaint.setLinearText(this.f9017A != 1.0f);
            CharSequence charSequence = this.f9049w;
            int[] iArr = H.f2303a;
            boolean zO = (this.f9031a.getLayoutDirection() == 1 ? D1.f.d : D1.f.c).o(charSequence.length(), charSequence);
            this.f9051y = zO;
            g gVar = new g(this.f9049w, textPaint, (int) fWidth);
            gVar.f9070k = TextUtils.TruncateAt.END;
            gVar.f9069j = zO;
            gVar.f9064e = Layout.Alignment.ALIGN_NORMAL;
            gVar.f9068i = false;
            gVar.f9065f = 1;
            gVar.f9066g = 1.0f;
            gVar.f9067h = 1;
            StaticLayout staticLayoutA = gVar.a();
            staticLayoutA.getClass();
            this.f9029N = staticLayoutA;
            this.f9050x = staticLayoutA.getText();
        }
    }

    public final int d(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.f9018C;
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
            android.graphics.Rect r0 = r2.f9033e
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
            r2.f9032b = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: U4.b.f():void");
    }

    public final void g() {
        StaticLayout staticLayout;
        TextInputLayout textInputLayout = this.f9031a;
        if (textInputLayout.getHeight() <= 0 || textInputLayout.getWidth() <= 0) {
            return;
        }
        float f10 = this.B;
        c(this.f9038j);
        CharSequence charSequence = this.f9050x;
        TextPaint textPaint = this.f9020E;
        if (charSequence != null && (staticLayout = this.f9029N) != null) {
            this.f9030O = TextUtils.ellipsize(charSequence, textPaint, staticLayout.getWidth(), TextUtils.TruncateAt.END);
        }
        CharSequence charSequence2 = this.f9030O;
        float fMeasureText = charSequence2 != null ? textPaint.measureText(charSequence2, 0, charSequence2.length()) : 0.0f;
        int absoluteGravity = Gravity.getAbsoluteGravity(this.f9036h, this.f9051y ? 1 : 0);
        int i7 = absoluteGravity & 112;
        Rect rect = this.f9033e;
        if (i7 == 48) {
            this.f9041n = rect.top;
        } else if (i7 != 80) {
            this.f9041n = rect.centerY() - ((textPaint.descent() - textPaint.ascent()) / 2.0f);
        } else {
            this.f9041n = textPaint.ascent() + rect.bottom;
        }
        int i10 = absoluteGravity & 8388615;
        if (i10 == 1) {
            this.f9042p = rect.centerX() - (fMeasureText / 2.0f);
        } else if (i10 != 5) {
            this.f9042p = rect.left;
        } else {
            this.f9042p = rect.right - fMeasureText;
        }
        c(this.f9037i);
        float height = this.f9029N != null ? r3.getHeight() : 0.0f;
        CharSequence charSequence3 = this.f9050x;
        float fMeasureText2 = charSequence3 != null ? textPaint.measureText(charSequence3, 0, charSequence3.length()) : 0.0f;
        StaticLayout staticLayout2 = this.f9029N;
        if (staticLayout2 != null) {
            staticLayout2.getLineLeft(0);
        }
        int absoluteGravity2 = Gravity.getAbsoluteGravity(this.f9035g, this.f9051y ? 1 : 0);
        int i11 = absoluteGravity2 & 112;
        Rect rect2 = this.d;
        if (i11 == 48) {
            this.f9040m = rect2.top;
        } else if (i11 != 80) {
            this.f9040m = rect2.centerY() - (height / 2.0f);
        } else {
            this.f9040m = textPaint.descent() + (rect2.bottom - height);
        }
        int i12 = absoluteGravity2 & 8388615;
        if (i12 == 1) {
            this.o = rect2.centerX() - (fMeasureText2 / 2.0f);
        } else if (i12 != 5) {
            this.o = rect2.left;
        } else {
            this.o = rect2.right - fMeasureText2;
        }
        Bitmap bitmap = this.f9052z;
        if (bitmap != null) {
            bitmap.recycle();
            this.f9052z = null;
        }
        j(f10);
        float f11 = this.c;
        RectF rectF = this.f9034f;
        rectF.left = e(rect2.left, rect.left, f11, this.f9022G);
        rectF.top = e(this.f9040m, this.f9041n, f11, this.f9022G);
        rectF.right = e(rect2.right, rect.right, f11, this.f9022G);
        rectF.bottom = e(rect2.bottom, rect.bottom, f11, this.f9022G);
        this.f9043q = e(this.o, this.f9042p, f11, this.f9022G);
        this.f9044r = e(this.f9040m, this.f9041n, f11, this.f9022G);
        j(e(this.f9037i, this.f9038j, f11, this.f9023H));
        Z1.a aVar = L4.a.f4008b;
        e(0.0f, 1.0f, 1.0f - f11, aVar);
        int[] iArr = H.f2303a;
        textInputLayout.postInvalidateOnAnimation();
        e(1.0f, 0.0f, f11, aVar);
        textInputLayout.postInvalidateOnAnimation();
        ColorStateList colorStateList = this.l;
        ColorStateList colorStateList2 = this.f9039k;
        if (colorStateList != colorStateList2) {
            textPaint.setColor(a(d(colorStateList2), d(this.l), f11));
        } else {
            textPaint.setColor(d(colorStateList));
        }
        float f12 = this.f9028M;
        if (f12 != 0.0f) {
            textPaint.setLetterSpacing(e(0.0f, f12, f11, aVar));
        } else {
            textPaint.setLetterSpacing(f12);
        }
        textPaint.setShadowLayer(L4.a.a(0.0f, this.f9024I, f11), L4.a.a(0.0f, this.f9025J, f11), L4.a.a(0.0f, this.f9026K, f11), a(0, d(this.f9027L), f11));
        textInputLayout.postInvalidateOnAnimation();
    }

    public final void h(ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            g();
        }
    }

    public final void i(float f10) {
        if (f10 < 0.0f) {
            f10 = 0.0f;
        } else if (f10 > 1.0f) {
            f10 = 1.0f;
        }
        if (f10 != this.c) {
            this.c = f10;
            RectF rectF = this.f9034f;
            float f11 = this.d.left;
            Rect rect = this.f9033e;
            rectF.left = e(f11, rect.left, f10, this.f9022G);
            rectF.top = e(this.f9040m, this.f9041n, f10, this.f9022G);
            rectF.right = e(r3.right, rect.right, f10, this.f9022G);
            rectF.bottom = e(r3.bottom, rect.bottom, f10, this.f9022G);
            this.f9043q = e(this.o, this.f9042p, f10, this.f9022G);
            this.f9044r = e(this.f9040m, this.f9041n, f10, this.f9022G);
            j(e(this.f9037i, this.f9038j, f10, this.f9023H));
            Z1.a aVar = L4.a.f4008b;
            e(0.0f, 1.0f, 1.0f - f10, aVar);
            int[] iArr = H.f2303a;
            TextInputLayout textInputLayout = this.f9031a;
            textInputLayout.postInvalidateOnAnimation();
            e(1.0f, 0.0f, f10, aVar);
            textInputLayout.postInvalidateOnAnimation();
            ColorStateList colorStateList = this.l;
            ColorStateList colorStateList2 = this.f9039k;
            TextPaint textPaint = this.f9020E;
            if (colorStateList != colorStateList2) {
                textPaint.setColor(a(d(colorStateList2), d(this.l), f10));
            } else {
                textPaint.setColor(d(colorStateList));
            }
            float f12 = this.f9028M;
            if (f12 != 0.0f) {
                textPaint.setLetterSpacing(e(0.0f, f12, f10, aVar));
            } else {
                textPaint.setLetterSpacing(f12);
            }
            textPaint.setShadowLayer(L4.a.a(0.0f, this.f9024I, f10), L4.a.a(0.0f, this.f9025J, f10), L4.a.a(0.0f, this.f9026K, f10), a(0, d(this.f9027L), f10));
            textInputLayout.postInvalidateOnAnimation();
        }
    }

    public final void j(float f10) {
        c(f10);
        int[] iArr = H.f2303a;
        this.f9031a.postInvalidateOnAnimation();
    }
}
