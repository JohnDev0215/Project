package c.b.a.a.v;

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
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import c.b.a.a.v.m;
import c.b.a.a.v.n;
import c.b.a.a.v.o;
import com.google.android.material.R$attr;
import java.util.BitSet;

/* JADX INFO: compiled from: MaterialShapeDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends Drawable implements b.g.c.l.b, p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f1851b = h.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Paint f1852c = new Paint(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f1853d;
    public final o.g[] e;
    public final o.g[] f;
    public final BitSet g;
    public boolean h;
    public final Matrix i;
    public final Path j;
    public final Path k;
    public final RectF l;
    public final RectF m;
    public final Region n;
    public final Region o;
    public m p;
    public final Paint q;
    public final Paint r;
    public final c.b.a.a.u.a s;
    public final n.a t;
    public final n u;
    public PorterDuffColorFilter v;
    public PorterDuffColorFilter w;
    public final RectF x;
    public boolean y;

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public class a implements n.a {
        public a() {
        }

        @Override // c.b.a.a.v.n.a
        public void a(o oVar, Matrix matrix, int i) {
            h.this.g.set(i + 4, oVar.e());
            h.this.f[i] = oVar.f(matrix);
        }

        @Override // c.b.a.a.v.n.a
        public void b(o oVar, Matrix matrix, int i) {
            h.this.g.set(i, oVar.e());
            h.this.e[i] = oVar.f(matrix);
        }
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public class b implements m.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ float f1855a;

        public b(float f) {
            this.f1855a = f;
        }

        @Override // c.b.a.a.v.m.c
        public c.b.a.a.v.c a(c.b.a.a.v.c cVar) {
            return cVar instanceof k ? cVar : new c.b.a.a.v.b(this.f1855a, cVar);
        }
    }

    public /* synthetic */ h(c cVar, a aVar) {
        this(cVar);
    }

    public static int S(int i, int i2) {
        return (i * (i2 + (i2 >>> 7))) >>> 8;
    }

    public static h m(Context context, float f) {
        int iB = c.b.a.a.k.a.b(context, R$attr.colorSurface, h.class.getSimpleName());
        h hVar = new h();
        hVar.N(context);
        hVar.X(ColorStateList.valueOf(iB));
        hVar.W(f);
        return hVar;
    }

    public int A() {
        return (int) (((double) this.f1853d.s) * Math.sin(Math.toRadians(r0.t)));
    }

    public int B() {
        return (int) (((double) this.f1853d.s) * Math.cos(Math.toRadians(r0.t)));
    }

    public int C() {
        return this.f1853d.r;
    }

    public m D() {
        return this.f1853d.f1857a;
    }

    public final float E() {
        if (M()) {
            return this.r.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    public ColorStateList F() {
        return this.f1853d.g;
    }

    public float G() {
        return this.f1853d.f1857a.r().a(u());
    }

    public float H() {
        return this.f1853d.f1857a.t().a(u());
    }

    public float I() {
        return this.f1853d.p;
    }

    public float J() {
        return w() + I();
    }

    public final boolean K() {
        c cVar = this.f1853d;
        int i = cVar.q;
        return i != 1 && cVar.r > 0 && (i == 2 || U());
    }

    public final boolean L() {
        Paint.Style style = this.f1853d.v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    public final boolean M() {
        Paint.Style style = this.f1853d.v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.r.getStrokeWidth() > 0.0f;
    }

    public void N(Context context) {
        this.f1853d.f1858b = new c.b.a.a.n.a(context);
        m0();
    }

    public final void O() {
        super.invalidateSelf();
    }

    public boolean P() {
        c.b.a.a.n.a aVar = this.f1853d.f1858b;
        return aVar != null && aVar.d();
    }

    public boolean Q() {
        return this.f1853d.f1857a.u(u());
    }

    public final void R(Canvas canvas) {
        if (K()) {
            canvas.save();
            T(canvas);
            if (!this.y) {
                n(canvas);
                canvas.restore();
                return;
            }
            int iWidth = (int) (this.x.width() - getBounds().width());
            int iHeight = (int) (this.x.height() - getBounds().height());
            if (iWidth < 0 || iHeight < 0) {
                throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(((int) this.x.width()) + (this.f1853d.r * 2) + iWidth, ((int) this.x.height()) + (this.f1853d.r * 2) + iHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            float f = (getBounds().left - this.f1853d.r) - iWidth;
            float f2 = (getBounds().top - this.f1853d.r) - iHeight;
            canvas2.translate(-f, -f2);
            n(canvas2);
            canvas.drawBitmap(bitmapCreateBitmap, f, f2, (Paint) null);
            bitmapCreateBitmap.recycle();
            canvas.restore();
        }
    }

    public final void T(Canvas canvas) {
        int iA = A();
        int iB = B();
        if (Build.VERSION.SDK_INT < 21 && this.y) {
            Rect clipBounds = canvas.getClipBounds();
            int i = this.f1853d.r;
            clipBounds.inset(-i, -i);
            clipBounds.offset(iA, iB);
            canvas.clipRect(clipBounds, Region.Op.REPLACE);
        }
        canvas.translate(iA, iB);
    }

    public boolean U() {
        int i = Build.VERSION.SDK_INT;
        return i < 21 || !(Q() || this.j.isConvex() || i >= 29);
    }

    public void V(float f) {
        setShapeAppearanceModel(this.f1853d.f1857a.w(f));
    }

    public void W(float f) {
        c cVar = this.f1853d;
        if (cVar.o != f) {
            cVar.o = f;
            m0();
        }
    }

    public void X(ColorStateList colorStateList) {
        c cVar = this.f1853d;
        if (cVar.f1860d != colorStateList) {
            cVar.f1860d = colorStateList;
            onStateChange(getState());
        }
    }

    public void Y(float f) {
        c cVar = this.f1853d;
        if (cVar.k != f) {
            cVar.k = f;
            this.h = true;
            invalidateSelf();
        }
    }

    public void Z(int i, int i2, int i3, int i4) {
        c cVar = this.f1853d;
        if (cVar.i == null) {
            cVar.i = new Rect();
        }
        this.f1853d.i.set(i, i2, i3, i4);
        invalidateSelf();
    }

    public void a0(Paint.Style style) {
        this.f1853d.v = style;
        O();
    }

    public void b0(float f) {
        c cVar = this.f1853d;
        if (cVar.n != f) {
            cVar.n = f;
            m0();
        }
    }

    public void c0(boolean z) {
        this.y = z;
    }

    public void d0(int i) {
        this.s.d(i);
        this.f1853d.u = false;
        O();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.q.setColorFilter(this.v);
        int alpha = this.q.getAlpha();
        this.q.setAlpha(S(alpha, this.f1853d.m));
        this.r.setColorFilter(this.w);
        this.r.setStrokeWidth(this.f1853d.l);
        int alpha2 = this.r.getAlpha();
        this.r.setAlpha(S(alpha2, this.f1853d.m));
        if (this.h) {
            i();
            g(u(), this.j);
            this.h = false;
        }
        R(canvas);
        if (L()) {
            o(canvas);
        }
        if (M()) {
            r(canvas);
        }
        this.q.setAlpha(alpha);
        this.r.setAlpha(alpha2);
    }

    public void e0(int i) {
        c cVar = this.f1853d;
        if (cVar.t != i) {
            cVar.t = i;
            O();
        }
    }

    public final PorterDuffColorFilter f(Paint paint, boolean z) {
        int color;
        int iL;
        if (!z || (iL = l((color = paint.getColor()))) == color) {
            return null;
        }
        return new PorterDuffColorFilter(iL, PorterDuff.Mode.SRC_IN);
    }

    public void f0(int i) {
        c cVar = this.f1853d;
        if (cVar.q != i) {
            cVar.q = i;
            O();
        }
    }

    public final void g(RectF rectF, Path path) {
        h(rectF, path);
        if (this.f1853d.j != 1.0f) {
            this.i.reset();
            Matrix matrix = this.i;
            float f = this.f1853d.j;
            matrix.setScale(f, f, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.i);
        }
        path.computeBounds(this.x, true);
    }

    public void g0(float f, int i) {
        j0(f);
        i0(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f1853d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.f1853d.q == 2) {
            return;
        }
        if (Q()) {
            outline.setRoundRect(getBounds(), G() * this.f1853d.k);
            return;
        }
        g(u(), this.j);
        if (this.j.isConvex() || Build.VERSION.SDK_INT >= 29) {
            try {
                outline.setConvexPath(this.j);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        Rect rect2 = this.f1853d.i;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        this.n.set(getBounds());
        g(u(), this.j);
        this.o.setPath(this.j, this.n);
        this.n.op(this.o, Region.Op.DIFFERENCE);
        return this.n;
    }

    public final void h(RectF rectF, Path path) {
        n nVar = this.u;
        c cVar = this.f1853d;
        nVar.e(cVar.f1857a, cVar.k, rectF, this.t, path);
    }

    public void h0(float f, ColorStateList colorStateList) {
        j0(f);
        i0(colorStateList);
    }

    public final void i() {
        m mVarX = D().x(new b(-E()));
        this.p = mVarX;
        this.u.d(mVarX, this.f1853d.k, v(), this.k);
    }

    public void i0(ColorStateList colorStateList) {
        c cVar = this.f1853d;
        if (cVar.e != colorStateList) {
            cVar.e = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.h = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.f1853d.g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f1853d.f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f1853d.e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f1853d.f1860d) != null && colorStateList4.isStateful())));
    }

    public final PorterDuffColorFilter j(ColorStateList colorStateList, PorterDuff.Mode mode, boolean z) {
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = l(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    public void j0(float f) {
        this.f1853d.l = f;
        invalidateSelf();
    }

    public final PorterDuffColorFilter k(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z) {
        return (colorStateList == null || mode == null) ? f(paint, z) : j(colorStateList, mode, z);
    }

    public final boolean k0(int[] iArr) {
        boolean z;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.f1853d.f1860d == null || color2 == (colorForState2 = this.f1853d.f1860d.getColorForState(iArr, (color2 = this.q.getColor())))) {
            z = false;
        } else {
            this.q.setColor(colorForState2);
            z = true;
        }
        if (this.f1853d.e == null || color == (colorForState = this.f1853d.e.getColorForState(iArr, (color = this.r.getColor())))) {
            return z;
        }
        this.r.setColor(colorForState);
        return true;
    }

    public final int l(int i) {
        float fJ = J() + z();
        c.b.a.a.n.a aVar = this.f1853d.f1858b;
        return aVar != null ? aVar.c(i, fJ) : i;
    }

    public final boolean l0() {
        PorterDuffColorFilter porterDuffColorFilter = this.v;
        PorterDuffColorFilter porterDuffColorFilter2 = this.w;
        c cVar = this.f1853d;
        this.v = k(cVar.g, cVar.h, this.q, true);
        c cVar2 = this.f1853d;
        this.w = k(cVar2.f, cVar2.h, this.r, false);
        c cVar3 = this.f1853d;
        if (cVar3.u) {
            this.s.d(cVar3.g.getColorForState(getState(), 0));
        }
        return (b.g.i.c.a(porterDuffColorFilter, this.v) && b.g.i.c.a(porterDuffColorFilter2, this.w)) ? false : true;
    }

    public final void m0() {
        float fJ = J();
        this.f1853d.r = (int) Math.ceil(0.75f * fJ);
        this.f1853d.s = (int) Math.ceil(fJ * 0.25f);
        l0();
        O();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f1853d = new c(this.f1853d);
        return this;
    }

    public final void n(Canvas canvas) {
        if (this.g.cardinality() > 0) {
            Log.w(f1851b, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.f1853d.s != 0) {
            canvas.drawPath(this.j, this.s.c());
        }
        for (int i = 0; i < 4; i++) {
            this.e[i].b(this.s, this.f1853d.r, canvas);
            this.f[i].b(this.s, this.f1853d.r, canvas);
        }
        if (this.y) {
            int iA = A();
            int iB = B();
            canvas.translate(-iA, -iB);
            canvas.drawPath(this.j, f1852c);
            canvas.translate(iA, iB);
        }
    }

    public final void o(Canvas canvas) {
        q(canvas, this.q, this.j, this.f1853d.f1857a, u());
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.h = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable, c.b.a.a.q.i.b
    public boolean onStateChange(int[] iArr) {
        boolean z = k0(iArr) || l0();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    public void p(Canvas canvas, Paint paint, Path path, RectF rectF) {
        q(canvas, paint, path, this.f1853d.f1857a, rectF);
    }

    public final void q(Canvas canvas, Paint paint, Path path, m mVar, RectF rectF) {
        if (!mVar.u(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = mVar.t().a(rectF) * this.f1853d.k;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    public final void r(Canvas canvas) {
        q(canvas, this.r, this.k, this.p, v());
    }

    public float s() {
        return this.f1853d.f1857a.j().a(u());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        c cVar = this.f1853d;
        if (cVar.m != i) {
            cVar.m = i;
            O();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1853d.f1859c = colorFilter;
        O();
    }

    @Override // c.b.a.a.v.p
    public void setShapeAppearanceModel(m mVar) {
        this.f1853d.f1857a = mVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, b.g.c.l.b
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable, b.g.c.l.b
    public void setTintList(ColorStateList colorStateList) {
        this.f1853d.g = colorStateList;
        l0();
        O();
    }

    @Override // android.graphics.drawable.Drawable, b.g.c.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f1853d;
        if (cVar.h != mode) {
            cVar.h = mode;
            l0();
            O();
        }
    }

    public float t() {
        return this.f1853d.f1857a.l().a(u());
    }

    public RectF u() {
        this.l.set(getBounds());
        return this.l;
    }

    public final RectF v() {
        this.m.set(u());
        float fE = E();
        this.m.inset(fE, fE);
        return this.m;
    }

    public float w() {
        return this.f1853d.o;
    }

    public ColorStateList x() {
        return this.f1853d.f1860d;
    }

    public float y() {
        return this.f1853d.k;
    }

    public float z() {
        return this.f1853d.n;
    }

    public h() {
        this(new m());
    }

    public h(Context context, AttributeSet attributeSet, int i, int i2) {
        this(m.e(context, attributeSet, i, i2).m());
    }

    public h(m mVar) {
        this(new c(mVar, null));
    }

    public h(c cVar) {
        this.e = new o.g[4];
        this.f = new o.g[4];
        this.g = new BitSet(8);
        this.i = new Matrix();
        this.j = new Path();
        this.k = new Path();
        this.l = new RectF();
        this.m = new RectF();
        this.n = new Region();
        this.o = new Region();
        Paint paint = new Paint(1);
        this.q = paint;
        Paint paint2 = new Paint(1);
        this.r = paint2;
        this.s = new c.b.a.a.u.a();
        this.u = new n();
        this.x = new RectF();
        this.y = true;
        this.f1853d = cVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = f1852c;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        l0();
        k0(getState());
        this.t = new a();
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public static final class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public m f1857a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c.b.a.a.n.a f1858b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorFilter f1859c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ColorStateList f1860d;
        public ColorStateList e;
        public ColorStateList f;
        public ColorStateList g;
        public PorterDuff.Mode h;
        public Rect i;
        public float j;
        public float k;
        public float l;
        public int m;
        public float n;
        public float o;
        public float p;
        public int q;
        public int r;
        public int s;
        public int t;
        public boolean u;
        public Paint.Style v;

        public c(m mVar, c.b.a.a.n.a aVar) {
            this.f1860d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = PorterDuff.Mode.SRC_IN;
            this.i = null;
            this.j = 1.0f;
            this.k = 1.0f;
            this.m = 255;
            this.n = 0.0f;
            this.o = 0.0f;
            this.p = 0.0f;
            this.q = 0;
            this.r = 0;
            this.s = 0;
            this.t = 0;
            this.u = false;
            this.v = Paint.Style.FILL_AND_STROKE;
            this.f1857a = mVar;
            this.f1858b = aVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVar = new h(this, null);
            hVar.h = true;
            return hVar;
        }

        public c(c cVar) {
            this.f1860d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = PorterDuff.Mode.SRC_IN;
            this.i = null;
            this.j = 1.0f;
            this.k = 1.0f;
            this.m = 255;
            this.n = 0.0f;
            this.o = 0.0f;
            this.p = 0.0f;
            this.q = 0;
            this.r = 0;
            this.s = 0;
            this.t = 0;
            this.u = false;
            this.v = Paint.Style.FILL_AND_STROKE;
            this.f1857a = cVar.f1857a;
            this.f1858b = cVar.f1858b;
            this.l = cVar.l;
            this.f1859c = cVar.f1859c;
            this.f1860d = cVar.f1860d;
            this.e = cVar.e;
            this.h = cVar.h;
            this.g = cVar.g;
            this.m = cVar.m;
            this.j = cVar.j;
            this.s = cVar.s;
            this.q = cVar.q;
            this.u = cVar.u;
            this.k = cVar.k;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
            this.r = cVar.r;
            this.t = cVar.t;
            this.f = cVar.f;
            this.v = cVar.v;
            if (cVar.i != null) {
                this.i = new Rect(cVar.i);
            }
        }
    }
}
