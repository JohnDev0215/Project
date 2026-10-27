package c.b.a.a.i0;

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
import c.b.a.a.i0.m;
import c.b.a.a.i0.n;
import c.b.a.a.i0.o;
import java.util.BitSet;

/* JADX INFO: compiled from: MaterialShapeDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends Drawable implements b.h.f.l.b, p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f3857b = h.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Paint f3858c = new Paint(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f3859d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final o.g[] f3860e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final o.g[] f3861f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final BitSet f3862g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3863h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Matrix f3864i;
    public final Path j;
    public final Path k;
    public final RectF l;
    public final RectF m;
    public final Region n;
    public final Region o;
    public m p;
    public final Paint q;
    public final Paint r;
    public final c.b.a.a.h0.a s;
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

        @Override // c.b.a.a.i0.n.a
        public void a(o oVar, Matrix matrix, int i2) {
            h.this.f3862g.set(i2 + 4, oVar.e());
            h.this.f3861f[i2] = oVar.f(matrix);
        }

        @Override // c.b.a.a.i0.n.a
        public void b(o oVar, Matrix matrix, int i2) {
            h.this.f3862g.set(i2, oVar.e());
            h.this.f3860e[i2] = oVar.f(matrix);
        }
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public class b implements m.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ float f3866a;

        public b(float f2) {
            this.f3866a = f2;
        }

        @Override // c.b.a.a.i0.m.c
        public c.b.a.a.i0.c a(c.b.a.a.i0.c cVar) {
            return cVar instanceof k ? cVar : new c.b.a.a.i0.b(this.f3866a, cVar);
        }
    }

    public /* synthetic */ h(c cVar, a aVar) {
        this(cVar);
    }

    public static int S(int i2, int i3) {
        return (i2 * (i3 + (i3 >>> 7))) >>> 8;
    }

    public static h m(Context context, float f2) {
        int iB = c.b.a.a.w.a.b(context, c.b.a.a.b.colorSurface, h.class.getSimpleName());
        h hVar = new h();
        hVar.N(context);
        hVar.X(ColorStateList.valueOf(iB));
        hVar.W(f2);
        return hVar;
    }

    public int A() {
        return (int) (((double) this.f3859d.s) * Math.sin(Math.toRadians(r0.t)));
    }

    public int B() {
        return (int) (((double) this.f3859d.s) * Math.cos(Math.toRadians(r0.t)));
    }

    public int C() {
        return this.f3859d.r;
    }

    public m D() {
        return this.f3859d.f3868a;
    }

    public final float E() {
        if (M()) {
            return this.r.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    public ColorStateList F() {
        return this.f3859d.f3874g;
    }

    public float G() {
        return this.f3859d.f3868a.r().a(u());
    }

    public float H() {
        return this.f3859d.f3868a.t().a(u());
    }

    public float I() {
        return this.f3859d.p;
    }

    public float J() {
        return w() + I();
    }

    public final boolean K() {
        c cVar = this.f3859d;
        int i2 = cVar.q;
        return i2 != 1 && cVar.r > 0 && (i2 == 2 || U());
    }

    public final boolean L() {
        Paint.Style style = this.f3859d.v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    public final boolean M() {
        Paint.Style style = this.f3859d.v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.r.getStrokeWidth() > 0.0f;
    }

    public void N(Context context) {
        this.f3859d.f3869b = new c.b.a.a.a0.a(context);
        m0();
    }

    public final void O() {
        super.invalidateSelf();
    }

    public boolean P() {
        c.b.a.a.a0.a aVar = this.f3859d.f3869b;
        return aVar != null && aVar.d();
    }

    public boolean Q() {
        return this.f3859d.f3868a.u(u());
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
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(((int) this.x.width()) + (this.f3859d.r * 2) + iWidth, ((int) this.x.height()) + (this.f3859d.r * 2) + iHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            float f2 = (getBounds().left - this.f3859d.r) - iWidth;
            float f3 = (getBounds().top - this.f3859d.r) - iHeight;
            canvas2.translate(-f2, -f3);
            n(canvas2);
            canvas.drawBitmap(bitmapCreateBitmap, f2, f3, (Paint) null);
            bitmapCreateBitmap.recycle();
            canvas.restore();
        }
    }

    public final void T(Canvas canvas) {
        int iA = A();
        int iB = B();
        if (Build.VERSION.SDK_INT < 21 && this.y) {
            Rect clipBounds = canvas.getClipBounds();
            int i2 = this.f3859d.r;
            clipBounds.inset(-i2, -i2);
            clipBounds.offset(iA, iB);
            canvas.clipRect(clipBounds, Region.Op.REPLACE);
        }
        canvas.translate(iA, iB);
    }

    public boolean U() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 < 21 || !(Q() || this.j.isConvex() || i2 >= 29);
    }

    public void V(float f2) {
        setShapeAppearanceModel(this.f3859d.f3868a.w(f2));
    }

    public void W(float f2) {
        c cVar = this.f3859d;
        if (cVar.o != f2) {
            cVar.o = f2;
            m0();
        }
    }

    public void X(ColorStateList colorStateList) {
        c cVar = this.f3859d;
        if (cVar.f3871d != colorStateList) {
            cVar.f3871d = colorStateList;
            onStateChange(getState());
        }
    }

    public void Y(float f2) {
        c cVar = this.f3859d;
        if (cVar.k != f2) {
            cVar.k = f2;
            this.f3863h = true;
            invalidateSelf();
        }
    }

    public void Z(int i2, int i3, int i4, int i5) {
        c cVar = this.f3859d;
        if (cVar.f3876i == null) {
            cVar.f3876i = new Rect();
        }
        this.f3859d.f3876i.set(i2, i3, i4, i5);
        invalidateSelf();
    }

    public void a0(Paint.Style style) {
        this.f3859d.v = style;
        O();
    }

    public void b0(float f2) {
        c cVar = this.f3859d;
        if (cVar.n != f2) {
            cVar.n = f2;
            m0();
        }
    }

    public void c0(boolean z) {
        this.y = z;
    }

    public void d0(int i2) {
        this.s.d(i2);
        this.f3859d.u = false;
        O();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.q.setColorFilter(this.v);
        int alpha = this.q.getAlpha();
        this.q.setAlpha(S(alpha, this.f3859d.m));
        this.r.setColorFilter(this.w);
        this.r.setStrokeWidth(this.f3859d.l);
        int alpha2 = this.r.getAlpha();
        this.r.setAlpha(S(alpha2, this.f3859d.m));
        if (this.f3863h) {
            i();
            g(u(), this.j);
            this.f3863h = false;
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

    public void e0(int i2) {
        c cVar = this.f3859d;
        if (cVar.t != i2) {
            cVar.t = i2;
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

    public void f0(int i2) {
        c cVar = this.f3859d;
        if (cVar.q != i2) {
            cVar.q = i2;
            O();
        }
    }

    public final void g(RectF rectF, Path path) {
        h(rectF, path);
        if (this.f3859d.j != 1.0f) {
            this.f3864i.reset();
            Matrix matrix = this.f3864i;
            float f2 = this.f3859d.j;
            matrix.setScale(f2, f2, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.f3864i);
        }
        path.computeBounds(this.x, true);
    }

    public void g0(float f2, int i2) {
        j0(f2);
        i0(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f3859d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.f3859d.q == 2) {
            return;
        }
        if (Q()) {
            outline.setRoundRect(getBounds(), G() * this.f3859d.k);
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
        Rect rect2 = this.f3859d.f3876i;
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
        c cVar = this.f3859d;
        nVar.e(cVar.f3868a, cVar.k, rectF, this.t, path);
    }

    public void h0(float f2, ColorStateList colorStateList) {
        j0(f2);
        i0(colorStateList);
    }

    public final void i() {
        m mVarX = D().x(new b(-E()));
        this.p = mVarX;
        this.u.d(mVarX, this.f3859d.k, v(), this.k);
    }

    public void i0(ColorStateList colorStateList) {
        c cVar = this.f3859d;
        if (cVar.f3872e != colorStateList) {
            cVar.f3872e = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.f3863h = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.f3859d.f3874g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f3859d.f3873f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f3859d.f3872e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f3859d.f3871d) != null && colorStateList4.isStateful())));
    }

    public final PorterDuffColorFilter j(ColorStateList colorStateList, PorterDuff.Mode mode, boolean z) {
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = l(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    public void j0(float f2) {
        this.f3859d.l = f2;
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
        if (this.f3859d.f3871d == null || color2 == (colorForState2 = this.f3859d.f3871d.getColorForState(iArr, (color2 = this.q.getColor())))) {
            z = false;
        } else {
            this.q.setColor(colorForState2);
            z = true;
        }
        if (this.f3859d.f3872e == null || color == (colorForState = this.f3859d.f3872e.getColorForState(iArr, (color = this.r.getColor())))) {
            return z;
        }
        this.r.setColor(colorForState);
        return true;
    }

    public final int l(int i2) {
        float fJ = J() + z();
        c.b.a.a.a0.a aVar = this.f3859d.f3869b;
        return aVar != null ? aVar.c(i2, fJ) : i2;
    }

    public final boolean l0() {
        PorterDuffColorFilter porterDuffColorFilter = this.v;
        PorterDuffColorFilter porterDuffColorFilter2 = this.w;
        c cVar = this.f3859d;
        this.v = k(cVar.f3874g, cVar.f3875h, this.q, true);
        c cVar2 = this.f3859d;
        this.w = k(cVar2.f3873f, cVar2.f3875h, this.r, false);
        c cVar3 = this.f3859d;
        if (cVar3.u) {
            this.s.d(cVar3.f3874g.getColorForState(getState(), 0));
        }
        return (b.h.l.b.a(porterDuffColorFilter, this.v) && b.h.l.b.a(porterDuffColorFilter2, this.w)) ? false : true;
    }

    public final void m0() {
        float fJ = J();
        this.f3859d.r = (int) Math.ceil(0.75f * fJ);
        this.f3859d.s = (int) Math.ceil(fJ * 0.25f);
        l0();
        O();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f3859d = new c(this.f3859d);
        return this;
    }

    public final void n(Canvas canvas) {
        if (this.f3862g.cardinality() > 0) {
            Log.w(f3857b, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.f3859d.s != 0) {
            canvas.drawPath(this.j, this.s.c());
        }
        for (int i2 = 0; i2 < 4; i2++) {
            this.f3860e[i2].b(this.s, this.f3859d.r, canvas);
            this.f3861f[i2].b(this.s, this.f3859d.r, canvas);
        }
        if (this.y) {
            int iA = A();
            int iB = B();
            canvas.translate(-iA, -iB);
            canvas.drawPath(this.j, f3858c);
            canvas.translate(iA, iB);
        }
    }

    public final void o(Canvas canvas) {
        q(canvas, this.q, this.j, this.f3859d.f3868a, u());
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.f3863h = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean z = k0(iArr) || l0();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    public void p(Canvas canvas, Paint paint, Path path, RectF rectF) {
        q(canvas, paint, path, this.f3859d.f3868a, rectF);
    }

    public final void q(Canvas canvas, Paint paint, Path path, m mVar, RectF rectF) {
        if (!mVar.u(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = mVar.t().a(rectF) * this.f3859d.k;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    public final void r(Canvas canvas) {
        q(canvas, this.r, this.k, this.p, v());
    }

    public float s() {
        return this.f3859d.f3868a.j().a(u());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        c cVar = this.f3859d;
        if (cVar.m != i2) {
            cVar.m = i2;
            O();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f3859d.f3870c = colorFilter;
        O();
    }

    @Override // c.b.a.a.i0.p
    public void setShapeAppearanceModel(m mVar) {
        this.f3859d.f3868a = mVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTint(int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTintList(ColorStateList colorStateList) {
        this.f3859d.f3874g = colorStateList;
        l0();
        O();
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f3859d;
        if (cVar.f3875h != mode) {
            cVar.f3875h = mode;
            l0();
            O();
        }
    }

    public float t() {
        return this.f3859d.f3868a.l().a(u());
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
        return this.f3859d.o;
    }

    public ColorStateList x() {
        return this.f3859d.f3871d;
    }

    public float y() {
        return this.f3859d.k;
    }

    public float z() {
        return this.f3859d.n;
    }

    public h() {
        this(new m());
    }

    public h(Context context, AttributeSet attributeSet, int i2, int i3) {
        this(m.e(context, attributeSet, i2, i3).m());
    }

    public h(m mVar) {
        this(new c(mVar, null));
    }

    public h(c cVar) {
        this.f3860e = new o.g[4];
        this.f3861f = new o.g[4];
        this.f3862g = new BitSet(8);
        this.f3864i = new Matrix();
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
        this.s = new c.b.a.a.h0.a();
        this.u = new n();
        this.x = new RectF();
        this.y = true;
        this.f3859d = cVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = f3858c;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        l0();
        k0(getState());
        this.t = new a();
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public static final class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public m f3868a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c.b.a.a.a0.a f3869b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorFilter f3870c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ColorStateList f3871d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ColorStateList f3872e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ColorStateList f3873f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ColorStateList f3874g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public PorterDuff.Mode f3875h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Rect f3876i;
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

        public c(m mVar, c.b.a.a.a0.a aVar) {
            this.f3871d = null;
            this.f3872e = null;
            this.f3873f = null;
            this.f3874g = null;
            this.f3875h = PorterDuff.Mode.SRC_IN;
            this.f3876i = null;
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
            this.f3868a = mVar;
            this.f3869b = aVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVar = new h(this, null);
            hVar.f3863h = true;
            return hVar;
        }

        public c(c cVar) {
            this.f3871d = null;
            this.f3872e = null;
            this.f3873f = null;
            this.f3874g = null;
            this.f3875h = PorterDuff.Mode.SRC_IN;
            this.f3876i = null;
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
            this.f3868a = cVar.f3868a;
            this.f3869b = cVar.f3869b;
            this.l = cVar.l;
            this.f3870c = cVar.f3870c;
            this.f3871d = cVar.f3871d;
            this.f3872e = cVar.f3872e;
            this.f3875h = cVar.f3875h;
            this.f3874g = cVar.f3874g;
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
            this.f3873f = cVar.f3873f;
            this.v = cVar.v;
            Rect rect = cVar.f3876i;
            if (rect != null) {
                this.f3876i = new Rect(rect);
            }
        }
    }
}
