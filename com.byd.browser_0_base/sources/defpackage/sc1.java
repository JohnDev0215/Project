package defpackage;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import com.google.android.material.elevation.ElevationOverlayProvider;
import defpackage.uj2;
import defpackage.vj2;
import defpackage.xj2;
import java.util.BitSet;
import java.util.Objects;

/* JADX INFO: compiled from: MaterialShapeDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class sc1 extends Drawable implements yj2 {
    public static final Paint A;
    public b e;
    public final xj2.f[] f;
    public final xj2.f[] g;
    public final BitSet h;
    public boolean i;
    public final Matrix j;
    public final Path k;
    public final Path l;
    public final RectF m;
    public final RectF n;
    public final Region o;
    public final Region p;
    public uj2 q;
    public final Paint r;
    public final Paint s;
    public final sj2 t;
    public final a u;
    public final vj2 v;
    public PorterDuffColorFilter w;
    public PorterDuffColorFilter x;
    public final RectF y;
    public boolean z;

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public class a {
        public a() {
        }
    }

    static {
        Paint paint = new Paint(1);
        A = paint;
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public sc1() {
        this(new uj2());
    }

    public final void b(RectF rectF, Path path) {
        vj2 vj2Var = this.v;
        b bVar = this.e;
        vj2Var.a(bVar.a, bVar.j, rectF, this.u, path);
        if (this.e.i != 1.0f) {
            Matrix matrix = this.j;
            matrix.reset();
            float f = this.e.i;
            matrix.setScale(f, f, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(matrix);
        }
        path.computeBounds(this.y, true);
    }

    public final PorterDuffColorFilter c(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z) {
        int color;
        int iD;
        if (colorStateList == null || mode == null) {
            if (!z || (iD = d((color = paint.getColor()))) == color) {
                return null;
            }
            return new PorterDuffColorFilter(iD, PorterDuff.Mode.SRC_IN);
        }
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = d(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    public final int d(int i) {
        b bVar = this.e;
        float f = bVar.n + bVar.o + bVar.m;
        ElevationOverlayProvider elevationOverlayProvider = bVar.b;
        return elevationOverlayProvider != null ? elevationOverlayProvider.b(f, i) : i;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z;
        Paint paint = this.r;
        paint.setColorFilter(this.w);
        int alpha = paint.getAlpha();
        int i = this.e.l;
        paint.setAlpha(((i + (i >>> 7)) * alpha) >>> 8);
        Paint paint2 = this.s;
        paint2.setColorFilter(this.x);
        paint2.setStrokeWidth(this.e.k);
        int alpha2 = paint2.getAlpha();
        int i2 = this.e.l;
        paint2.setAlpha(((i2 + (i2 >>> 7)) * alpha2) >>> 8);
        boolean z2 = this.i;
        Path path = this.k;
        if (z2) {
            Paint.Style style = this.e.u;
            float f = -((style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && (paint2.getStrokeWidth() > 0.0f ? 1 : (paint2.getStrokeWidth() == 0.0f ? 0 : -1)) > 0 ? paint2.getStrokeWidth() / 2.0f : 0.0f);
            uj2 uj2Var = this.e.a;
            uj2Var.getClass();
            uj2.a aVar = new uj2.a(uj2Var);
            l00 e4Var = uj2Var.e;
            if (!(e4Var instanceof ma2)) {
                e4Var = new e4(f, e4Var);
            }
            aVar.e = e4Var;
            l00 e4Var2 = uj2Var.f;
            if (!(e4Var2 instanceof ma2)) {
                e4Var2 = new e4(f, e4Var2);
            }
            aVar.f = e4Var2;
            l00 e4Var3 = uj2Var.h;
            if (!(e4Var3 instanceof ma2)) {
                e4Var3 = new e4(f, e4Var3);
            }
            aVar.h = e4Var3;
            l00 e4Var4 = uj2Var.g;
            if (!(e4Var4 instanceof ma2)) {
                e4Var4 = new e4(f, e4Var4);
            }
            aVar.g = e4Var4;
            uj2 uj2Var2 = new uj2(aVar);
            this.q = uj2Var2;
            float f2 = this.e.j;
            RectF rectF = this.n;
            rectF.set(h());
            Paint.Style style2 = this.e.u;
            float strokeWidth = (style2 == Paint.Style.FILL_AND_STROKE || style2 == Paint.Style.STROKE) && (paint2.getStrokeWidth() > 0.0f ? 1 : (paint2.getStrokeWidth() == 0.0f ? 0 : -1)) > 0 ? paint2.getStrokeWidth() / 2.0f : 0.0f;
            rectF.inset(strokeWidth, strokeWidth);
            this.v.a(uj2Var2, f2, rectF, null, this.l);
            b(h(), path);
            this.i = false;
        }
        b bVar = this.e;
        int i3 = bVar.p;
        if (i3 == 1 || bVar.q <= 0) {
            z = false;
        } else if (i3 != 2) {
            if (!l()) {
                path.isConvex();
            }
            z = false;
        } else {
            z = true;
        }
        if (z) {
            canvas.save();
            b bVar2 = this.e;
            canvas.translate((int) (Math.sin(Math.toRadians(bVar2.s)) * ((double) bVar2.r)), i());
            if (this.z) {
                RectF rectF2 = this.y;
                int iWidth = (int) (rectF2.width() - getBounds().width());
                int iHeight = (int) (rectF2.height() - getBounds().height());
                if (iWidth < 0 || iHeight < 0) {
                    throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
                }
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap((this.e.q * 2) + ((int) rectF2.width()) + iWidth, (this.e.q * 2) + ((int) rectF2.height()) + iHeight, Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(bitmapCreateBitmap);
                float f3 = (getBounds().left - this.e.q) - iWidth;
                float f4 = (getBounds().top - this.e.q) - iHeight;
                canvas2.translate(-f3, -f4);
                e(canvas2);
                canvas.drawBitmap(bitmapCreateBitmap, f3, f4, (Paint) null);
                bitmapCreateBitmap.recycle();
                canvas.restore();
            } else {
                e(canvas);
                canvas.restore();
            }
        }
        b bVar3 = this.e;
        Paint.Style style3 = bVar3.u;
        if (style3 == Paint.Style.FILL_AND_STROKE || style3 == Paint.Style.FILL) {
            f(canvas, paint, path, bVar3.a, h());
        }
        Paint.Style style4 = this.e.u;
        if ((style4 == Paint.Style.FILL_AND_STROKE || style4 == Paint.Style.STROKE) && paint2.getStrokeWidth() > 0.0f) {
            g(canvas);
        }
        paint.setAlpha(alpha);
        paint2.setAlpha(alpha2);
    }

    public final void e(Canvas canvas) {
        if (this.h.cardinality() > 0) {
            Log.w("sc1", "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        int i = this.e.r;
        Path path = this.k;
        sj2 sj2Var = this.t;
        if (i != 0) {
            canvas.drawPath(path, sj2Var.a);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            xj2.f fVar = this.f[i2];
            int i3 = this.e.q;
            Matrix matrix = xj2.f.a;
            fVar.a(matrix, sj2Var, i3, canvas);
            this.g[i2].a(matrix, sj2Var, this.e.q, canvas);
        }
        if (this.z) {
            int iSin = (int) (Math.sin(Math.toRadians(r0.s)) * ((double) this.e.r));
            int i4 = i();
            canvas.translate(-iSin, -i4);
            canvas.drawPath(path, A);
            canvas.translate(iSin, i4);
        }
    }

    public final void f(Canvas canvas, Paint paint, Path path, uj2 uj2Var, RectF rectF) {
        if (!uj2Var.e(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = uj2Var.f.a(rectF) * this.e.j;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    public void g(Canvas canvas) {
        Paint paint = this.s;
        Path path = this.l;
        uj2 uj2Var = this.q;
        RectF rectF = this.n;
        rectF.set(h());
        Paint.Style style = this.e.u;
        float strokeWidth = (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && (paint.getStrokeWidth() > 0.0f ? 1 : (paint.getStrokeWidth() == 0.0f ? 0 : -1)) > 0 ? paint.getStrokeWidth() / 2.0f : 0.0f;
        rectF.inset(strokeWidth, strokeWidth);
        f(canvas, paint, path, uj2Var, rectF);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.e.l;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.e.p == 2) {
            return;
        }
        if (l()) {
            outline.setRoundRect(getBounds(), j() * this.e.j);
            return;
        }
        RectF rectFH = h();
        Path path = this.k;
        b(rectFH, path);
        path.isConvex();
        try {
            outline.setConvexPath(path);
        } catch (IllegalArgumentException unused) {
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        Rect rect2 = this.e.h;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Region getTransparentRegion() {
        Rect bounds = getBounds();
        Region region = this.o;
        region.set(bounds);
        RectF rectFH = h();
        Path path = this.k;
        b(rectFH, path);
        Region region2 = this.p;
        region2.setPath(path, region);
        region.op(region2, Region.Op.DIFFERENCE);
        return region;
    }

    public final RectF h() {
        RectF rectF = this.m;
        rectF.set(getBounds());
        return rectF;
    }

    public final int i() {
        return (int) (Math.cos(Math.toRadians(r4.s)) * ((double) this.e.r));
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        this.i = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.e.f) != null && colorStateList.isStateful()) || (((colorStateList2 = this.e.e) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.e.d) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.e.c) != null && colorStateList4.isStateful())));
    }

    public final float j() {
        return this.e.a.e.a(h());
    }

    public final void k(Context context) {
        this.e.b = new ElevationOverlayProvider(context);
        v();
    }

    public final boolean l() {
        return this.e.a.e(h());
    }

    public final void m(float f) {
        b bVar = this.e;
        if (bVar.n != f) {
            bVar.n = f;
            v();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        this.e = new b(this.e);
        return this;
    }

    public final void n(ColorStateList colorStateList) {
        b bVar = this.e;
        if (bVar.c != colorStateList) {
            bVar.c = colorStateList;
            onStateChange(getState());
        }
    }

    public final void o(float f) {
        b bVar = this.e;
        if (bVar.j != f) {
            bVar.j = f;
            this.i = true;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.i = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable, c83.b
    public boolean onStateChange(int[] iArr) {
        boolean z = t(iArr) || u();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    public final void p(Paint.Style style) {
        this.e.u = style;
        super.invalidateSelf();
    }

    public final void q() {
        this.t.a(-12303292);
        this.e.t = false;
        super.invalidateSelf();
    }

    public final void r() {
        b bVar = this.e;
        if (bVar.p != 2) {
            bVar.p = 2;
            super.invalidateSelf();
        }
    }

    public final void s(ColorStateList colorStateList) {
        b bVar = this.e;
        if (bVar.d != colorStateList) {
            bVar.d = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        b bVar = this.e;
        if (bVar.l != i) {
            bVar.l = i;
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.e.getClass();
        super.invalidateSelf();
    }

    @Override // defpackage.yj2
    public final void setShapeAppearanceModel(uj2 uj2Var) {
        this.e.a = uj2Var;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.e.f = colorStateList;
        u();
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        b bVar = this.e;
        if (bVar.g != mode) {
            bVar.g = mode;
            u();
            super.invalidateSelf();
        }
    }

    public final boolean t(int[] iArr) {
        boolean z;
        Paint paint;
        int color;
        int colorForState;
        Paint paint2;
        int color2;
        int colorForState2;
        if (this.e.c == null || color2 == (colorForState2 = this.e.c.getColorForState(iArr, (color2 = (paint2 = this.r).getColor())))) {
            z = false;
        } else {
            paint2.setColor(colorForState2);
            z = true;
        }
        if (this.e.d == null || color == (colorForState = this.e.d.getColorForState(iArr, (color = (paint = this.s).getColor())))) {
            return z;
        }
        paint.setColor(colorForState);
        return true;
    }

    public final boolean u() {
        PorterDuffColorFilter porterDuffColorFilter = this.w;
        PorterDuffColorFilter porterDuffColorFilter2 = this.x;
        b bVar = this.e;
        this.w = c(bVar.f, bVar.g, this.r, true);
        b bVar2 = this.e;
        this.x = c(bVar2.e, bVar2.g, this.s, false);
        b bVar3 = this.e;
        if (bVar3.t) {
            this.t.a(bVar3.f.getColorForState(getState(), 0));
        }
        return (Objects.equals(porterDuffColorFilter, this.w) && Objects.equals(porterDuffColorFilter2, this.x)) ? false : true;
    }

    public final void v() {
        b bVar = this.e;
        float f = bVar.n + bVar.o;
        bVar.q = (int) Math.ceil(0.75f * f);
        this.e.r = (int) Math.ceil(f * 0.25f);
        u();
        super.invalidateSelf();
    }

    public sc1(Context context, AttributeSet attributeSet, int i, int i2) {
        this(uj2.b(context, attributeSet, i, i2).a());
    }

    public sc1(uj2 uj2Var) {
        this(new b(uj2Var));
    }

    public sc1(b bVar) {
        vj2 vj2Var;
        this.f = new xj2.f[4];
        this.g = new xj2.f[4];
        this.h = new BitSet(8);
        this.j = new Matrix();
        this.k = new Path();
        this.l = new Path();
        this.m = new RectF();
        this.n = new RectF();
        this.o = new Region();
        this.p = new Region();
        Paint paint = new Paint(1);
        this.r = paint;
        Paint paint2 = new Paint(1);
        this.s = paint2;
        this.t = new sj2();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            vj2Var = vj2.a.a;
        } else {
            vj2Var = new vj2();
        }
        this.v = vj2Var;
        this.y = new RectF();
        this.z = true;
        this.e = bVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        u();
        t(getState());
        this.u = new a();
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public static final class b extends Drawable.ConstantState {
        public uj2 a;
        public ElevationOverlayProvider b;
        public ColorStateList c;
        public ColorStateList d;
        public final ColorStateList e;
        public ColorStateList f;
        public PorterDuff.Mode g;
        public Rect h;
        public final float i;
        public float j;
        public float k;
        public int l;
        public float m;
        public float n;
        public final float o;
        public int p;
        public int q;
        public int r;
        public int s;
        public boolean t;
        public Paint.Style u;

        public b(uj2 uj2Var) {
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = PorterDuff.Mode.SRC_IN;
            this.h = null;
            this.i = 1.0f;
            this.j = 1.0f;
            this.l = 255;
            this.m = 0.0f;
            this.n = 0.0f;
            this.o = 0.0f;
            this.p = 0;
            this.q = 0;
            this.r = 0;
            this.s = 0;
            this.t = false;
            this.u = Paint.Style.FILL_AND_STROKE;
            this.a = uj2Var;
            this.b = null;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            sc1 sc1Var = new sc1(this);
            sc1Var.i = true;
            return sc1Var;
        }

        public b(b bVar) {
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = PorterDuff.Mode.SRC_IN;
            this.h = null;
            this.i = 1.0f;
            this.j = 1.0f;
            this.l = 255;
            this.m = 0.0f;
            this.n = 0.0f;
            this.o = 0.0f;
            this.p = 0;
            this.q = 0;
            this.r = 0;
            this.s = 0;
            this.t = false;
            this.u = Paint.Style.FILL_AND_STROKE;
            this.a = bVar.a;
            this.b = bVar.b;
            this.k = bVar.k;
            this.c = bVar.c;
            this.d = bVar.d;
            this.g = bVar.g;
            this.f = bVar.f;
            this.l = bVar.l;
            this.i = bVar.i;
            this.r = bVar.r;
            this.p = bVar.p;
            this.t = bVar.t;
            this.j = bVar.j;
            this.m = bVar.m;
            this.n = bVar.n;
            this.o = bVar.o;
            this.q = bVar.q;
            this.s = bVar.s;
            this.e = bVar.e;
            this.u = bVar.u;
            if (bVar.h != null) {
                this.h = new Rect(bVar.h);
            }
        }
    }
}
