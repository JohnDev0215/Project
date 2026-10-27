package c.a.a.a.w;

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
import c.a.a.a.w.m;
import c.a.a.a.w.n;
import c.a.a.a.w.o;
import java.util.BitSet;

/* JADX INFO: compiled from: MaterialShapeDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends Drawable implements androidx.core.graphics.drawable.b, p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f2367b = h.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Paint f2368c = new Paint(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f2369d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final o.g[] f2370e;
    private final o.g[] f;
    private final BitSet g;
    private boolean h;
    private final Matrix i;
    private final Path j;
    private final Path k;
    private final RectF l;
    private final RectF m;
    private final Region n;
    private final Region o;
    private m p;
    private final Paint q;
    private final Paint r;
    private final c.a.a.a.v.a s;
    private final n.a t;
    private final n u;
    private PorterDuffColorFilter v;
    private PorterDuffColorFilter w;
    private final RectF x;
    private boolean y;

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    class a implements n.a {
        a() {
        }

        @Override // c.a.a.a.w.n.a
        public void a(o oVar, Matrix matrix, int i) {
            h.this.g.set(i + 4, oVar.e());
            h.this.f[i] = oVar.f(matrix);
        }

        @Override // c.a.a.a.w.n.a
        public void b(o oVar, Matrix matrix, int i) {
            h.this.g.set(i, oVar.e());
            h.this.f2370e[i] = oVar.f(matrix);
        }
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    class b implements m.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f2372a;

        b(float f) {
            this.f2372a = f;
        }

        @Override // c.a.a.a.w.m.c
        public c.a.a.a.w.c a(c.a.a.a.w.c cVar) {
            return cVar instanceof k ? cVar : new c.a.a.a.w.b(this.f2372a, cVar);
        }
    }

    /* synthetic */ h(c cVar, a aVar) {
        this(cVar);
    }

    private float E() {
        if (M()) {
            return this.r.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    private boolean K() {
        c cVar = this.f2369d;
        int i = cVar.q;
        return i != 1 && cVar.r > 0 && (i == 2 || U());
    }

    private boolean L() {
        Paint.Style style = this.f2369d.v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    private boolean M() {
        Paint.Style style = this.f2369d.v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.r.getStrokeWidth() > 0.0f;
    }

    private void O() {
        super.invalidateSelf();
    }

    private void R(Canvas canvas) {
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
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(((int) this.x.width()) + (this.f2369d.r * 2) + iWidth, ((int) this.x.height()) + (this.f2369d.r * 2) + iHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            float f = (getBounds().left - this.f2369d.r) - iWidth;
            float f2 = (getBounds().top - this.f2369d.r) - iHeight;
            canvas2.translate(-f, -f2);
            n(canvas2);
            canvas.drawBitmap(bitmapCreateBitmap, f, f2, (Paint) null);
            bitmapCreateBitmap.recycle();
            canvas.restore();
        }
    }

    private static int S(int i, int i2) {
        return (i * (i2 + (i2 >>> 7))) >>> 8;
    }

    private void T(Canvas canvas) {
        int iA = A();
        int iB = B();
        if (Build.VERSION.SDK_INT < 21 && this.y) {
            Rect clipBounds = canvas.getClipBounds();
            int i = this.f2369d.r;
            clipBounds.inset(-i, -i);
            clipBounds.offset(iA, iB);
            canvas.clipRect(clipBounds, Region.Op.REPLACE);
        }
        canvas.translate(iA, iB);
    }

    private PorterDuffColorFilter f(Paint paint, boolean z) {
        int color;
        int iL;
        if (!z || (iL = l((color = paint.getColor()))) == color) {
            return null;
        }
        return new PorterDuffColorFilter(iL, PorterDuff.Mode.SRC_IN);
    }

    private void g(RectF rectF, Path path) {
        h(rectF, path);
        if (this.f2369d.j != 1.0f) {
            this.i.reset();
            Matrix matrix = this.i;
            float f = this.f2369d.j;
            matrix.setScale(f, f, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.i);
        }
        path.computeBounds(this.x, true);
    }

    private void i() {
        m mVarX = D().x(new b(-E()));
        this.p = mVarX;
        this.u.d(mVarX, this.f2369d.k, v(), this.k);
    }

    private PorterDuffColorFilter j(ColorStateList colorStateList, PorterDuff.Mode mode, boolean z) {
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = l(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    private PorterDuffColorFilter k(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z) {
        return (colorStateList == null || mode == null) ? f(paint, z) : j(colorStateList, mode, z);
    }

    private boolean k0(int[] iArr) {
        boolean z;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.f2369d.f2377d == null || color2 == (colorForState2 = this.f2369d.f2377d.getColorForState(iArr, (color2 = this.q.getColor())))) {
            z = false;
        } else {
            this.q.setColor(colorForState2);
            z = true;
        }
        if (this.f2369d.f2378e == null || color == (colorForState = this.f2369d.f2378e.getColorForState(iArr, (color = this.r.getColor())))) {
            return z;
        }
        this.r.setColor(colorForState);
        return true;
    }

    private int l(int i) {
        float fJ = J() + z();
        c.a.a.a.q.a aVar = this.f2369d.f2375b;
        return aVar != null ? aVar.c(i, fJ) : i;
    }

    private boolean l0() {
        PorterDuffColorFilter porterDuffColorFilter = this.v;
        PorterDuffColorFilter porterDuffColorFilter2 = this.w;
        c cVar = this.f2369d;
        this.v = k(cVar.g, cVar.h, this.q, true);
        c cVar2 = this.f2369d;
        this.w = k(cVar2.f, cVar2.h, this.r, false);
        c cVar3 = this.f2369d;
        if (cVar3.u) {
            this.s.d(cVar3.g.getColorForState(getState(), 0));
        }
        return (b.g.j.c.a(porterDuffColorFilter, this.v) && b.g.j.c.a(porterDuffColorFilter2, this.w)) ? false : true;
    }

    public static h m(Context context, float f) {
        int iB = c.a.a.a.o.a.b(context, c.a.a.a.b.p, h.class.getSimpleName());
        h hVar = new h();
        hVar.N(context);
        hVar.X(ColorStateList.valueOf(iB));
        hVar.W(f);
        return hVar;
    }

    private void m0() {
        float fJ = J();
        this.f2369d.r = (int) Math.ceil(0.75f * fJ);
        this.f2369d.s = (int) Math.ceil(fJ * 0.25f);
        l0();
        O();
    }

    private void n(Canvas canvas) {
        if (this.g.cardinality() > 0) {
            Log.w(f2367b, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.f2369d.s != 0) {
            canvas.drawPath(this.j, this.s.c());
        }
        for (int i = 0; i < 4; i++) {
            this.f2370e[i].b(this.s, this.f2369d.r, canvas);
            this.f[i].b(this.s, this.f2369d.r, canvas);
        }
        if (this.y) {
            int iA = A();
            int iB = B();
            canvas.translate(-iA, -iB);
            canvas.drawPath(this.j, f2368c);
            canvas.translate(iA, iB);
        }
    }

    private void o(Canvas canvas) {
        q(canvas, this.q, this.j, this.f2369d.f2374a, u());
    }

    private void q(Canvas canvas, Paint paint, Path path, m mVar, RectF rectF) {
        if (!mVar.u(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = mVar.t().a(rectF) * this.f2369d.k;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    private void r(Canvas canvas) {
        q(canvas, this.r, this.k, this.p, v());
    }

    private RectF v() {
        this.m.set(u());
        float fE = E();
        this.m.inset(fE, fE);
        return this.m;
    }

    public int A() {
        return (int) (((double) this.f2369d.s) * Math.sin(Math.toRadians(r0.t)));
    }

    public int B() {
        return (int) (((double) this.f2369d.s) * Math.cos(Math.toRadians(r0.t)));
    }

    public int C() {
        return this.f2369d.r;
    }

    public m D() {
        return this.f2369d.f2374a;
    }

    public ColorStateList F() {
        return this.f2369d.g;
    }

    public float G() {
        return this.f2369d.f2374a.r().a(u());
    }

    public float H() {
        return this.f2369d.f2374a.t().a(u());
    }

    public float I() {
        return this.f2369d.p;
    }

    public float J() {
        return w() + I();
    }

    public void N(Context context) {
        this.f2369d.f2375b = new c.a.a.a.q.a(context);
        m0();
    }

    public boolean P() {
        c.a.a.a.q.a aVar = this.f2369d.f2375b;
        return aVar != null && aVar.d();
    }

    public boolean Q() {
        return this.f2369d.f2374a.u(u());
    }

    public boolean U() {
        int i = Build.VERSION.SDK_INT;
        return i < 21 || !(Q() || this.j.isConvex() || i >= 29);
    }

    public void V(float f) {
        setShapeAppearanceModel(this.f2369d.f2374a.w(f));
    }

    public void W(float f) {
        c cVar = this.f2369d;
        if (cVar.o != f) {
            cVar.o = f;
            m0();
        }
    }

    public void X(ColorStateList colorStateList) {
        c cVar = this.f2369d;
        if (cVar.f2377d != colorStateList) {
            cVar.f2377d = colorStateList;
            onStateChange(getState());
        }
    }

    public void Y(float f) {
        c cVar = this.f2369d;
        if (cVar.k != f) {
            cVar.k = f;
            this.h = true;
            invalidateSelf();
        }
    }

    public void Z(int i, int i2, int i3, int i4) {
        c cVar = this.f2369d;
        if (cVar.i == null) {
            cVar.i = new Rect();
        }
        this.f2369d.i.set(i, i2, i3, i4);
        invalidateSelf();
    }

    public void a0(Paint.Style style) {
        this.f2369d.v = style;
        O();
    }

    public void b0(float f) {
        c cVar = this.f2369d;
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
        this.f2369d.u = false;
        O();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.q.setColorFilter(this.v);
        int alpha = this.q.getAlpha();
        this.q.setAlpha(S(alpha, this.f2369d.m));
        this.r.setColorFilter(this.w);
        this.r.setStrokeWidth(this.f2369d.l);
        int alpha2 = this.r.getAlpha();
        this.r.setAlpha(S(alpha2, this.f2369d.m));
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
        c cVar = this.f2369d;
        if (cVar.t != i) {
            cVar.t = i;
            O();
        }
    }

    public void f0(int i) {
        c cVar = this.f2369d;
        if (cVar.q != i) {
            cVar.q = i;
            O();
        }
    }

    public void g0(float f, int i) {
        j0(f);
        i0(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f2369d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.f2369d.q == 2) {
            return;
        }
        if (Q()) {
            outline.setRoundRect(getBounds(), G() * this.f2369d.k);
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
        Rect rect2 = this.f2369d.i;
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

    protected final void h(RectF rectF, Path path) {
        n nVar = this.u;
        c cVar = this.f2369d;
        nVar.e(cVar.f2374a, cVar.k, rectF, this.t, path);
    }

    public void h0(float f, ColorStateList colorStateList) {
        j0(f);
        i0(colorStateList);
    }

    public void i0(ColorStateList colorStateList) {
        c cVar = this.f2369d;
        if (cVar.f2378e != colorStateList) {
            cVar.f2378e = colorStateList;
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
        return super.isStateful() || ((colorStateList = this.f2369d.g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f2369d.f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f2369d.f2378e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f2369d.f2377d) != null && colorStateList4.isStateful())));
    }

    public void j0(float f) {
        this.f2369d.l = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f2369d = new c(this.f2369d);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.h = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean z = k0(iArr) || l0();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    protected void p(Canvas canvas, Paint paint, Path path, RectF rectF) {
        q(canvas, paint, path, this.f2369d.f2374a, rectF);
    }

    public float s() {
        return this.f2369d.f2374a.j().a(u());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        c cVar = this.f2369d;
        if (cVar.m != i) {
            cVar.m = i;
            O();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f2369d.f2376c = colorFilter;
        O();
    }

    @Override // c.a.a.a.w.p
    public void setShapeAppearanceModel(m mVar) {
        this.f2369d.f2374a = mVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        this.f2369d.g = colorStateList;
        l0();
        O();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f2369d;
        if (cVar.h != mode) {
            cVar.h = mode;
            l0();
            O();
        }
    }

    public float t() {
        return this.f2369d.f2374a.l().a(u());
    }

    protected RectF u() {
        this.l.set(getBounds());
        return this.l;
    }

    public float w() {
        return this.f2369d.o;
    }

    public ColorStateList x() {
        return this.f2369d.f2377d;
    }

    public float y() {
        return this.f2369d.k;
    }

    public float z() {
        return this.f2369d.n;
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

    private h(c cVar) {
        this.f2370e = new o.g[4];
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
        this.s = new c.a.a.a.v.a();
        this.u = new n();
        this.x = new RectF();
        this.y = true;
        this.f2369d = cVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = f2368c;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        l0();
        k0(getState());
        this.t = new a();
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    static final class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public m f2374a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c.a.a.a.q.a f2375b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorFilter f2376c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ColorStateList f2377d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ColorStateList f2378e;
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

        public c(m mVar, c.a.a.a.q.a aVar) {
            this.f2377d = null;
            this.f2378e = null;
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
            this.f2374a = mVar;
            this.f2375b = aVar;
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
            this.f2377d = null;
            this.f2378e = null;
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
            this.f2374a = cVar.f2374a;
            this.f2375b = cVar.f2375b;
            this.l = cVar.l;
            this.f2376c = cVar.f2376c;
            this.f2377d = cVar.f2377d;
            this.f2378e = cVar.f2378e;
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
            Rect rect = cVar.i;
            if (rect != null) {
                this.i = new Rect(rect);
            }
        }
    }
}
