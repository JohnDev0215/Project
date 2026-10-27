package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Looper;
import android.util.AttributeSet;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public class qf1 extends Drawable implements zc2 {
    public static final Paint E;
    public PorterDuffColorFilter A;
    public PorterDuffColorFilter B;
    public final RectF C;
    public final boolean D;
    public pf1 c;
    public final xc2[] e;
    public final xc2[] k;
    public final BitSet l;
    public boolean m;
    public final Matrix n;
    public final Path o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Path f172p;
    public final RectF q;
    public final RectF r;
    public final Region s;
    public final Region t;
    public oc2 u;
    public final Paint v;
    public final Paint w;
    public final nc2 x;
    public final oh1 y;
    public final qc2 z;

    static {
        Paint paint = new Paint(1);
        E = paint;
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public qf1() {
        this(new oc2());
    }

    public final void a(RectF rectF, Path path) {
        qc2 qc2Var = this.z;
        pf1 pf1Var = this.c;
        qc2Var.a(pf1Var.a, pf1Var.j, rectF, this.y, path);
        if (this.c.i != 1.0f) {
            Matrix matrix = this.n;
            matrix.reset();
            float f = this.c.i;
            matrix.setScale(f, f, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(matrix);
        }
        path.computeBounds(this.C, true);
    }

    public final PorterDuffColorFilter b(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z) {
        int color;
        int iC;
        if (colorStateList == null || mode == null) {
            return (!z || (iC = c((color = paint.getColor()))) == color) ? null : new PorterDuffColorFilter(iC, PorterDuff.Mode.SRC_IN);
        }
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = c(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    public final int c(int i) {
        int i2;
        pf1 pf1Var = this.c;
        float f = pf1Var.n + pf1Var.o + pf1Var.m;
        cc0 cc0Var = pf1Var.b;
        if (cc0Var == null || !cc0Var.a || qu.d(i, 255) != cc0Var.d) {
            return i;
        }
        float fMin = (cc0Var.e <= 0.0f || f <= 0.0f) ? 0.0f : Math.min(((((float) Math.log1p(f / r3)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
        int iAlpha = Color.alpha(i);
        int iX = f60.x(qu.d(i, 255), cc0Var.b, fMin);
        if (fMin > 0.0f && (i2 = cc0Var.c) != 0) {
            iX = qu.b(qu.d(i2, cc0.f), iX);
        }
        return qu.d(iX, iAlpha);
    }

    public final void d(Canvas canvas) {
        this.l.cardinality();
        int i = this.c.r;
        Path path = this.o;
        nc2 nc2Var = this.x;
        if (i != 0) {
            canvas.drawPath(path, nc2Var.a);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            xc2 xc2Var = this.e[i2];
            int i3 = this.c.q;
            Matrix matrix = xc2.a;
            xc2Var.a(matrix, nc2Var, i3, canvas);
            this.k[i2].a(matrix, nc2Var, this.c.q, canvas);
        }
        if (this.D) {
            int iSin = (int) (Math.sin(Math.toRadians(r0.s)) * ((double) this.c.r));
            int iCos = (int) (Math.cos(Math.toRadians(r2.s)) * ((double) this.c.r));
            canvas.translate(-iSin, -iCos);
            canvas.drawPath(path, E);
            canvas.translate(iSin, iCos);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00dc  */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(android.graphics.Canvas r19) {
        /*
            Method dump skipped, instruction units count: 442
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.qf1.draw(android.graphics.Canvas):void");
    }

    public final void e(Canvas canvas, Paint paint, Path path, oc2 oc2Var, RectF rectF) {
        if (!oc2Var.d(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = oc2Var.f.a(rectF) * this.c.j;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    public void f(Canvas canvas) {
        Paint paint = this.w;
        Path path = this.f172p;
        oc2 oc2Var = this.u;
        RectF rectF = this.r;
        rectF.set(g());
        float strokeWidth = h() ? paint.getStrokeWidth() / 2.0f : 0.0f;
        rectF.inset(strokeWidth, strokeWidth);
        e(canvas, paint, path, oc2Var, rectF);
    }

    public final RectF g() {
        RectF rectF = this.q;
        rectF.set(getBounds());
        return rectF;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.c;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        pf1 pf1Var = this.c;
        if (pf1Var.f167p == 2) {
            return;
        }
        if (pf1Var.a.d(g())) {
            outline.setRoundRect(getBounds(), this.c.a.e.a(g()) * this.c.j);
            return;
        }
        RectF rectFG = g();
        Path path = this.o;
        a(rectFG, path);
        if (path.isConvex() || Build.VERSION.SDK_INT >= 29) {
            try {
                outline.setConvexPath(path);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        Rect rect2 = this.c.h;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Region getTransparentRegion() {
        Rect bounds = getBounds();
        Region region = this.s;
        region.set(bounds);
        RectF rectFG = g();
        Path path = this.o;
        a(rectFG, path);
        Region region2 = this.t;
        region2.setPath(path, region);
        region.op(region2, Region.Op.DIFFERENCE);
        return region;
    }

    public final boolean h() {
        Paint.Style style = this.c.u;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.w.getStrokeWidth() > 0.0f;
    }

    public final void i(Context context) {
        this.c.b = new cc0(context);
        n();
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        this.m = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.c.f) != null && colorStateList.isStateful()) || (((colorStateList2 = this.c.e) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.c.d) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.c.c) != null && colorStateList4.isStateful())));
    }

    public final void j(float f) {
        pf1 pf1Var = this.c;
        if (pf1Var.n != f) {
            pf1Var.n = f;
            n();
        }
    }

    public final void k(ColorStateList colorStateList) {
        pf1 pf1Var = this.c;
        if (pf1Var.c != colorStateList) {
            pf1Var.c = colorStateList;
            onStateChange(getState());
        }
    }

    public final boolean l(int[] iArr) {
        boolean z;
        Paint paint;
        int color;
        int colorForState;
        Paint paint2;
        int color2;
        int colorForState2;
        if (this.c.c == null || color2 == (colorForState2 = this.c.c.getColorForState(iArr, (color2 = (paint2 = this.v).getColor())))) {
            z = false;
        } else {
            paint2.setColor(colorForState2);
            z = true;
        }
        if (this.c.d == null || color == (colorForState = this.c.d.getColorForState(iArr, (color = (paint = this.w).getColor())))) {
            return z;
        }
        paint.setColor(colorForState);
        return true;
    }

    public final boolean m() {
        PorterDuffColorFilter porterDuffColorFilter = this.A;
        PorterDuffColorFilter porterDuffColorFilter2 = this.B;
        pf1 pf1Var = this.c;
        this.A = b(pf1Var.f, pf1Var.g, this.v, true);
        pf1 pf1Var2 = this.c;
        this.B = b(pf1Var2.e, pf1Var2.g, this.w, false);
        pf1 pf1Var3 = this.c;
        if (pf1Var3.t) {
            int colorForState = pf1Var3.f.getColorForState(getState(), 0);
            nc2 nc2Var = this.x;
            nc2Var.getClass();
            nc2Var.d = qu.d(colorForState, 68);
            nc2Var.e = qu.d(colorForState, 20);
            nc2Var.f = qu.d(colorForState, 0);
            nc2Var.a.setColor(nc2Var.d);
        }
        return (ap1.a(porterDuffColorFilter, this.A) && ap1.a(porterDuffColorFilter2, this.B)) ? false : true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        pf1 pf1Var = this.c;
        pf1 pf1Var2 = new pf1();
        pf1Var2.c = null;
        pf1Var2.d = null;
        pf1Var2.e = null;
        pf1Var2.f = null;
        pf1Var2.g = PorterDuff.Mode.SRC_IN;
        pf1Var2.h = null;
        pf1Var2.i = 1.0f;
        pf1Var2.j = 1.0f;
        pf1Var2.l = 255;
        pf1Var2.m = 0.0f;
        pf1Var2.n = 0.0f;
        pf1Var2.o = 0.0f;
        pf1Var2.f167p = 0;
        pf1Var2.q = 0;
        pf1Var2.r = 0;
        pf1Var2.s = 0;
        pf1Var2.t = false;
        pf1Var2.u = Paint.Style.FILL_AND_STROKE;
        pf1Var2.a = pf1Var.a;
        pf1Var2.b = pf1Var.b;
        pf1Var2.k = pf1Var.k;
        pf1Var2.c = pf1Var.c;
        pf1Var2.d = pf1Var.d;
        pf1Var2.g = pf1Var.g;
        pf1Var2.f = pf1Var.f;
        pf1Var2.l = pf1Var.l;
        pf1Var2.i = pf1Var.i;
        pf1Var2.r = pf1Var.r;
        pf1Var2.f167p = pf1Var.f167p;
        pf1Var2.t = pf1Var.t;
        pf1Var2.j = pf1Var.j;
        pf1Var2.m = pf1Var.m;
        pf1Var2.n = pf1Var.n;
        pf1Var2.o = pf1Var.o;
        pf1Var2.q = pf1Var.q;
        pf1Var2.s = pf1Var.s;
        pf1Var2.e = pf1Var.e;
        pf1Var2.u = pf1Var.u;
        if (pf1Var.h != null) {
            pf1Var2.h = new Rect(pf1Var.h);
        }
        this.c = pf1Var2;
        return this;
    }

    public final void n() {
        pf1 pf1Var = this.c;
        float f = pf1Var.n + pf1Var.o;
        pf1Var.q = (int) Math.ceil(0.75f * f);
        this.c.r = (int) Math.ceil(f * 0.25f);
        m();
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        this.m = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean z = l(iArr) || m();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        pf1 pf1Var = this.c;
        if (pf1Var.l != i) {
            pf1Var.l = i;
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.c.getClass();
        super.invalidateSelf();
    }

    @Override // p.zc2
    public final void setShapeAppearanceModel(oc2 oc2Var) {
        this.c.a = oc2Var;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.c.f = colorStateList;
        m();
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        pf1 pf1Var = this.c;
        if (pf1Var.g != mode) {
            pf1Var.g = mode;
            m();
            super.invalidateSelf();
        }
    }

    public qf1(Context context, AttributeSet attributeSet, int i, int i2) {
        this(oc2.b(context, attributeSet, i, i2).a());
    }

    public qf1(oc2 oc2Var) {
        pf1 pf1Var = new pf1();
        pf1Var.c = null;
        pf1Var.d = null;
        pf1Var.e = null;
        pf1Var.f = null;
        pf1Var.g = PorterDuff.Mode.SRC_IN;
        pf1Var.h = null;
        pf1Var.i = 1.0f;
        pf1Var.j = 1.0f;
        pf1Var.l = 255;
        pf1Var.m = 0.0f;
        pf1Var.n = 0.0f;
        pf1Var.o = 0.0f;
        pf1Var.f167p = 0;
        pf1Var.q = 0;
        pf1Var.r = 0;
        pf1Var.s = 0;
        pf1Var.t = false;
        pf1Var.u = Paint.Style.FILL_AND_STROKE;
        pf1Var.a = oc2Var;
        pf1Var.b = null;
        this(pf1Var);
    }

    public qf1(pf1 pf1Var) {
        qc2 qc2Var;
        this.e = new xc2[4];
        this.k = new xc2[4];
        this.l = new BitSet(8);
        this.n = new Matrix();
        this.o = new Path();
        this.f172p = new Path();
        this.q = new RectF();
        this.r = new RectF();
        this.s = new Region();
        this.t = new Region();
        Paint paint = new Paint(1);
        this.v = paint;
        Paint paint2 = new Paint(1);
        this.w = paint2;
        this.x = new nc2();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            qc2Var = pc2.a;
        } else {
            qc2Var = new qc2();
        }
        this.z = qc2Var;
        this.C = new RectF();
        this.D = true;
        this.c = pf1Var;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        m();
        l(getState());
        this.y = new oh1(15, this);
    }
}
