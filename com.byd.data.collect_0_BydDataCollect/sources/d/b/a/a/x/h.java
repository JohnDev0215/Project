package d.b.a.a.x;

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
import d.b.a.a.x.m;
import d.b.a.a.x.n;
import d.b.a.a.x.o;
import java.util.BitSet;

/* JADX INFO: compiled from: MaterialShapeDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends Drawable implements androidx.core.graphics.drawable.b, p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f4752b = h.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Paint f4753c = new Paint(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f4754d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final o.g[] f4755e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final o.g[] f4756f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final BitSet f4757g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f4758h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Matrix f4759i;
    private final Path j;
    private final Path k;
    private final RectF l;
    private final RectF m;
    private final Region n;
    private final Region o;
    private m p;
    private final Paint q;
    private final Paint r;
    private final d.b.a.a.w.a s;
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

        @Override // d.b.a.a.x.n.a
        public void a(o oVar, Matrix matrix, int i2) {
            h.this.f4757g.set(i2 + 4, oVar.e());
            h.this.f4756f[i2] = oVar.f(matrix);
        }

        @Override // d.b.a.a.x.n.a
        public void b(o oVar, Matrix matrix, int i2) {
            h.this.f4757g.set(i2, oVar.e());
            h.this.f4755e[i2] = oVar.f(matrix);
        }
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    class b implements m.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f4761a;

        b(float f2) {
            this.f4761a = f2;
        }

        @Override // d.b.a.a.x.m.c
        public d.b.a.a.x.c a(d.b.a.a.x.c cVar) {
            return cVar instanceof k ? cVar : new d.b.a.a.x.b(this.f4761a, cVar);
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
        c cVar = this.f4754d;
        int i2 = cVar.q;
        return i2 != 1 && cVar.r > 0 && (i2 == 2 || U());
    }

    private boolean L() {
        Paint.Style style = this.f4754d.v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    private boolean M() {
        Paint.Style style = this.f4754d.v;
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
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(((int) this.x.width()) + (this.f4754d.r * 2) + iWidth, ((int) this.x.height()) + (this.f4754d.r * 2) + iHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            float f2 = (getBounds().left - this.f4754d.r) - iWidth;
            float f3 = (getBounds().top - this.f4754d.r) - iHeight;
            canvas2.translate(-f2, -f3);
            n(canvas2);
            canvas.drawBitmap(bitmapCreateBitmap, f2, f3, (Paint) null);
            bitmapCreateBitmap.recycle();
            canvas.restore();
        }
    }

    private static int S(int i2, int i3) {
        return (i2 * (i3 + (i3 >>> 7))) >>> 8;
    }

    private void T(Canvas canvas) {
        int iA = A();
        int iB = B();
        if (Build.VERSION.SDK_INT < 21 && this.y) {
            Rect clipBounds = canvas.getClipBounds();
            int i2 = this.f4754d.r;
            clipBounds.inset(-i2, -i2);
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
        if (this.f4754d.j != 1.0f) {
            this.f4759i.reset();
            Matrix matrix = this.f4759i;
            float f2 = this.f4754d.j;
            matrix.setScale(f2, f2, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.f4759i);
        }
        path.computeBounds(this.x, true);
    }

    private void i() {
        m mVarX = D().x(new b(-E()));
        this.p = mVarX;
        this.u.d(mVarX, this.f4754d.k, v(), this.k);
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
        if (this.f4754d.f4766d == null || color2 == (colorForState2 = this.f4754d.f4766d.getColorForState(iArr, (color2 = this.q.getColor())))) {
            z = false;
        } else {
            this.q.setColor(colorForState2);
            z = true;
        }
        if (this.f4754d.f4767e == null || color == (colorForState = this.f4754d.f4767e.getColorForState(iArr, (color = this.r.getColor())))) {
            return z;
        }
        this.r.setColor(colorForState);
        return true;
    }

    private int l(int i2) {
        float fJ = J() + z();
        d.b.a.a.r.a aVar = this.f4754d.f4764b;
        return aVar != null ? aVar.c(i2, fJ) : i2;
    }

    private boolean l0() {
        PorterDuffColorFilter porterDuffColorFilter = this.v;
        PorterDuffColorFilter porterDuffColorFilter2 = this.w;
        c cVar = this.f4754d;
        this.v = k(cVar.f4769g, cVar.f4770h, this.q, true);
        c cVar2 = this.f4754d;
        this.w = k(cVar2.f4768f, cVar2.f4770h, this.r, false);
        c cVar3 = this.f4754d;
        if (cVar3.u) {
            this.s.d(cVar3.f4769g.getColorForState(getState(), 0));
        }
        return (b.g.j.c.a(porterDuffColorFilter, this.v) && b.g.j.c.a(porterDuffColorFilter2, this.w)) ? false : true;
    }

    public static h m(Context context, float f2) {
        int iB = d.b.a.a.o.a.b(context, d.b.a.a.b.colorSurface, h.class.getSimpleName());
        h hVar = new h();
        hVar.N(context);
        hVar.X(ColorStateList.valueOf(iB));
        hVar.W(f2);
        return hVar;
    }

    private void m0() {
        float fJ = J();
        this.f4754d.r = (int) Math.ceil(0.75f * fJ);
        this.f4754d.s = (int) Math.ceil(fJ * 0.25f);
        l0();
        O();
    }

    private void n(Canvas canvas) {
        if (this.f4757g.cardinality() > 0) {
            Log.w(f4752b, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.f4754d.s != 0) {
            canvas.drawPath(this.j, this.s.c());
        }
        for (int i2 = 0; i2 < 4; i2++) {
            this.f4755e[i2].b(this.s, this.f4754d.r, canvas);
            this.f4756f[i2].b(this.s, this.f4754d.r, canvas);
        }
        if (this.y) {
            int iA = A();
            int iB = B();
            canvas.translate(-iA, -iB);
            canvas.drawPath(this.j, f4753c);
            canvas.translate(iA, iB);
        }
    }

    private void o(Canvas canvas) {
        q(canvas, this.q, this.j, this.f4754d.f4763a, u());
    }

    private void q(Canvas canvas, Paint paint, Path path, m mVar, RectF rectF) {
        if (!mVar.u(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = mVar.t().a(rectF) * this.f4754d.k;
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
        return (int) (((double) this.f4754d.s) * Math.sin(Math.toRadians(r0.t)));
    }

    public int B() {
        return (int) (((double) this.f4754d.s) * Math.cos(Math.toRadians(r0.t)));
    }

    public int C() {
        return this.f4754d.r;
    }

    public m D() {
        return this.f4754d.f4763a;
    }

    public ColorStateList F() {
        return this.f4754d.f4769g;
    }

    public float G() {
        return this.f4754d.f4763a.r().a(u());
    }

    public float H() {
        return this.f4754d.f4763a.t().a(u());
    }

    public float I() {
        return this.f4754d.p;
    }

    public float J() {
        return w() + I();
    }

    public void N(Context context) {
        this.f4754d.f4764b = new d.b.a.a.r.a(context);
        m0();
    }

    public boolean P() {
        d.b.a.a.r.a aVar = this.f4754d.f4764b;
        return aVar != null && aVar.d();
    }

    public boolean Q() {
        return this.f4754d.f4763a.u(u());
    }

    public boolean U() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 < 21 || !(Q() || this.j.isConvex() || i2 >= 29);
    }

    public void V(float f2) {
        setShapeAppearanceModel(this.f4754d.f4763a.w(f2));
    }

    public void W(float f2) {
        c cVar = this.f4754d;
        if (cVar.o != f2) {
            cVar.o = f2;
            m0();
        }
    }

    public void X(ColorStateList colorStateList) {
        c cVar = this.f4754d;
        if (cVar.f4766d != colorStateList) {
            cVar.f4766d = colorStateList;
            onStateChange(getState());
        }
    }

    public void Y(float f2) {
        c cVar = this.f4754d;
        if (cVar.k != f2) {
            cVar.k = f2;
            this.f4758h = true;
            invalidateSelf();
        }
    }

    public void Z(int i2, int i3, int i4, int i5) {
        c cVar = this.f4754d;
        if (cVar.f4771i == null) {
            cVar.f4771i = new Rect();
        }
        this.f4754d.f4771i.set(i2, i3, i4, i5);
        invalidateSelf();
    }

    public void a0(Paint.Style style) {
        this.f4754d.v = style;
        O();
    }

    public void b0(float f2) {
        c cVar = this.f4754d;
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
        this.f4754d.u = false;
        O();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.q.setColorFilter(this.v);
        int alpha = this.q.getAlpha();
        this.q.setAlpha(S(alpha, this.f4754d.m));
        this.r.setColorFilter(this.w);
        this.r.setStrokeWidth(this.f4754d.l);
        int alpha2 = this.r.getAlpha();
        this.r.setAlpha(S(alpha2, this.f4754d.m));
        if (this.f4758h) {
            i();
            g(u(), this.j);
            this.f4758h = false;
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
        c cVar = this.f4754d;
        if (cVar.t != i2) {
            cVar.t = i2;
            O();
        }
    }

    public void f0(int i2) {
        c cVar = this.f4754d;
        if (cVar.q != i2) {
            cVar.q = i2;
            O();
        }
    }

    public void g0(float f2, int i2) {
        j0(f2);
        i0(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f4754d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.f4754d.q == 2) {
            return;
        }
        if (Q()) {
            outline.setRoundRect(getBounds(), G() * this.f4754d.k);
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
        Rect rect2 = this.f4754d.f4771i;
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
        c cVar = this.f4754d;
        nVar.e(cVar.f4763a, cVar.k, rectF, this.t, path);
    }

    public void h0(float f2, ColorStateList colorStateList) {
        j0(f2);
        i0(colorStateList);
    }

    public void i0(ColorStateList colorStateList) {
        c cVar = this.f4754d;
        if (cVar.f4767e != colorStateList) {
            cVar.f4767e = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.f4758h = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.f4754d.f4769g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f4754d.f4768f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f4754d.f4767e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f4754d.f4766d) != null && colorStateList4.isStateful())));
    }

    public void j0(float f2) {
        this.f4754d.l = f2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f4754d = new c(this.f4754d);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.f4758h = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.j.b
    protected boolean onStateChange(int[] iArr) {
        boolean z = k0(iArr) || l0();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    protected void p(Canvas canvas, Paint paint, Path path, RectF rectF) {
        q(canvas, paint, path, this.f4754d.f4763a, rectF);
    }

    public float s() {
        return this.f4754d.f4763a.j().a(u());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        c cVar = this.f4754d;
        if (cVar.m != i2) {
            cVar.m = i2;
            O();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f4754d.f4765c = colorFilter;
        O();
    }

    @Override // d.b.a.a.x.p
    public void setShapeAppearanceModel(m mVar) {
        this.f4754d.f4763a = mVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        this.f4754d.f4769g = colorStateList;
        l0();
        O();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f4754d;
        if (cVar.f4770h != mode) {
            cVar.f4770h = mode;
            l0();
            O();
        }
    }

    public float t() {
        return this.f4754d.f4763a.l().a(u());
    }

    protected RectF u() {
        this.l.set(getBounds());
        return this.l;
    }

    public float w() {
        return this.f4754d.o;
    }

    public ColorStateList x() {
        return this.f4754d.f4766d;
    }

    public float y() {
        return this.f4754d.k;
    }

    public float z() {
        return this.f4754d.n;
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

    private h(c cVar) {
        this.f4755e = new o.g[4];
        this.f4756f = new o.g[4];
        this.f4757g = new BitSet(8);
        this.f4759i = new Matrix();
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
        this.s = new d.b.a.a.w.a();
        this.u = new n();
        this.x = new RectF();
        this.y = true;
        this.f4754d = cVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = f4753c;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        l0();
        k0(getState());
        this.t = new a();
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    static final class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public m f4763a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public d.b.a.a.r.a f4764b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorFilter f4765c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ColorStateList f4766d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ColorStateList f4767e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ColorStateList f4768f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ColorStateList f4769g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public PorterDuff.Mode f4770h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Rect f4771i;
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

        public c(m mVar, d.b.a.a.r.a aVar) {
            this.f4766d = null;
            this.f4767e = null;
            this.f4768f = null;
            this.f4769g = null;
            this.f4770h = PorterDuff.Mode.SRC_IN;
            this.f4771i = null;
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
            this.f4763a = mVar;
            this.f4764b = aVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVar = new h(this, null);
            hVar.f4758h = true;
            return hVar;
        }

        public c(c cVar) {
            this.f4766d = null;
            this.f4767e = null;
            this.f4768f = null;
            this.f4769g = null;
            this.f4770h = PorterDuff.Mode.SRC_IN;
            this.f4771i = null;
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
            this.f4763a = cVar.f4763a;
            this.f4764b = cVar.f4764b;
            this.l = cVar.l;
            this.f4765c = cVar.f4765c;
            this.f4766d = cVar.f4766d;
            this.f4767e = cVar.f4767e;
            this.f4770h = cVar.f4770h;
            this.f4769g = cVar.f4769g;
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
            this.f4768f = cVar.f4768f;
            this.v = cVar.v;
            Rect rect = cVar.f4771i;
            if (rect != null) {
                this.f4771i = new Rect(rect);
            }
        }
    }
}
