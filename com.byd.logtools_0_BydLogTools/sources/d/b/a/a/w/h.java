package d.b.a.a.w;

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
import d.b.a.a.w.m;
import d.b.a.a.w.n;
import d.b.a.a.w.o;
import java.util.BitSet;

/* JADX INFO: compiled from: MaterialShapeDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends Drawable implements androidx.core.graphics.drawable.b, p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f4831b = h.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Paint f4832c = new Paint(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f4833d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final o.g[] f4834e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final o.g[] f4835f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final BitSet f4836g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f4837h;
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
    private final d.b.a.a.v.a s;
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

        @Override // d.b.a.a.w.n.a
        public void a(o oVar, Matrix matrix, int i) {
            h.this.f4836g.set(i + 4, oVar.e());
            h.this.f4835f[i] = oVar.f(matrix);
        }

        @Override // d.b.a.a.w.n.a
        public void b(o oVar, Matrix matrix, int i) {
            h.this.f4836g.set(i, oVar.e());
            h.this.f4834e[i] = oVar.f(matrix);
        }
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    class b implements m.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f4839a;

        b(float f2) {
            this.f4839a = f2;
        }

        @Override // d.b.a.a.w.m.c
        public d.b.a.a.w.c a(d.b.a.a.w.c cVar) {
            return cVar instanceof k ? cVar : new d.b.a.a.w.b(this.f4839a, cVar);
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
        c cVar = this.f4833d;
        int i = cVar.q;
        return i != 1 && cVar.r > 0 && (i == 2 || U());
    }

    private boolean L() {
        Paint.Style style = this.f4833d.v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    private boolean M() {
        Paint.Style style = this.f4833d.v;
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
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(((int) this.x.width()) + (this.f4833d.r * 2) + iWidth, ((int) this.x.height()) + (this.f4833d.r * 2) + iHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            float f2 = (getBounds().left - this.f4833d.r) - iWidth;
            float f3 = (getBounds().top - this.f4833d.r) - iHeight;
            canvas2.translate(-f2, -f3);
            n(canvas2);
            canvas.drawBitmap(bitmapCreateBitmap, f2, f3, (Paint) null);
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
            int i = this.f4833d.r;
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
        if (this.f4833d.j != 1.0f) {
            this.i.reset();
            Matrix matrix = this.i;
            float f2 = this.f4833d.j;
            matrix.setScale(f2, f2, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.i);
        }
        path.computeBounds(this.x, true);
    }

    private void i() {
        m mVarX = D().x(new b(-E()));
        this.p = mVarX;
        this.u.d(mVarX, this.f4833d.k, v(), this.k);
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
        if (this.f4833d.f4844d == null || color2 == (colorForState2 = this.f4833d.f4844d.getColorForState(iArr, (color2 = this.q.getColor())))) {
            z = false;
        } else {
            this.q.setColor(colorForState2);
            z = true;
        }
        if (this.f4833d.f4845e == null || color == (colorForState = this.f4833d.f4845e.getColorForState(iArr, (color = this.r.getColor())))) {
            return z;
        }
        this.r.setColor(colorForState);
        return true;
    }

    private int l(int i) {
        float fJ = J() + z();
        d.b.a.a.q.a aVar = this.f4833d.f4842b;
        return aVar != null ? aVar.c(i, fJ) : i;
    }

    private boolean l0() {
        PorterDuffColorFilter porterDuffColorFilter = this.v;
        PorterDuffColorFilter porterDuffColorFilter2 = this.w;
        c cVar = this.f4833d;
        this.v = k(cVar.f4847g, cVar.f4848h, this.q, true);
        c cVar2 = this.f4833d;
        this.w = k(cVar2.f4846f, cVar2.f4848h, this.r, false);
        c cVar3 = this.f4833d;
        if (cVar3.u) {
            this.s.d(cVar3.f4847g.getColorForState(getState(), 0));
        }
        return (b.g.j.c.a(porterDuffColorFilter, this.v) && b.g.j.c.a(porterDuffColorFilter2, this.w)) ? false : true;
    }

    public static h m(Context context, float f2) {
        int iB = d.b.a.a.o.a.b(context, d.b.a.a.b.p, h.class.getSimpleName());
        h hVar = new h();
        hVar.N(context);
        hVar.X(ColorStateList.valueOf(iB));
        hVar.W(f2);
        return hVar;
    }

    private void m0() {
        float fJ = J();
        this.f4833d.r = (int) Math.ceil(0.75f * fJ);
        this.f4833d.s = (int) Math.ceil(fJ * 0.25f);
        l0();
        O();
    }

    private void n(Canvas canvas) {
        if (this.f4836g.cardinality() > 0) {
            Log.w(f4831b, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.f4833d.s != 0) {
            canvas.drawPath(this.j, this.s.c());
        }
        for (int i = 0; i < 4; i++) {
            this.f4834e[i].b(this.s, this.f4833d.r, canvas);
            this.f4835f[i].b(this.s, this.f4833d.r, canvas);
        }
        if (this.y) {
            int iA = A();
            int iB = B();
            canvas.translate(-iA, -iB);
            canvas.drawPath(this.j, f4832c);
            canvas.translate(iA, iB);
        }
    }

    private void o(Canvas canvas) {
        q(canvas, this.q, this.j, this.f4833d.f4841a, u());
    }

    private void q(Canvas canvas, Paint paint, Path path, m mVar, RectF rectF) {
        if (!mVar.u(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = mVar.t().a(rectF) * this.f4833d.k;
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
        return (int) (((double) this.f4833d.s) * Math.sin(Math.toRadians(r0.t)));
    }

    public int B() {
        return (int) (((double) this.f4833d.s) * Math.cos(Math.toRadians(r0.t)));
    }

    public int C() {
        return this.f4833d.r;
    }

    public m D() {
        return this.f4833d.f4841a;
    }

    public ColorStateList F() {
        return this.f4833d.f4847g;
    }

    public float G() {
        return this.f4833d.f4841a.r().a(u());
    }

    public float H() {
        return this.f4833d.f4841a.t().a(u());
    }

    public float I() {
        return this.f4833d.p;
    }

    public float J() {
        return w() + I();
    }

    public void N(Context context) {
        this.f4833d.f4842b = new d.b.a.a.q.a(context);
        m0();
    }

    public boolean P() {
        d.b.a.a.q.a aVar = this.f4833d.f4842b;
        return aVar != null && aVar.d();
    }

    public boolean Q() {
        return this.f4833d.f4841a.u(u());
    }

    public boolean U() {
        int i = Build.VERSION.SDK_INT;
        return i < 21 || !(Q() || this.j.isConvex() || i >= 29);
    }

    public void V(float f2) {
        setShapeAppearanceModel(this.f4833d.f4841a.w(f2));
    }

    public void W(float f2) {
        c cVar = this.f4833d;
        if (cVar.o != f2) {
            cVar.o = f2;
            m0();
        }
    }

    public void X(ColorStateList colorStateList) {
        c cVar = this.f4833d;
        if (cVar.f4844d != colorStateList) {
            cVar.f4844d = colorStateList;
            onStateChange(getState());
        }
    }

    public void Y(float f2) {
        c cVar = this.f4833d;
        if (cVar.k != f2) {
            cVar.k = f2;
            this.f4837h = true;
            invalidateSelf();
        }
    }

    public void Z(int i, int i2, int i3, int i4) {
        c cVar = this.f4833d;
        if (cVar.i == null) {
            cVar.i = new Rect();
        }
        this.f4833d.i.set(i, i2, i3, i4);
        invalidateSelf();
    }

    public void a0(Paint.Style style) {
        this.f4833d.v = style;
        O();
    }

    public void b0(float f2) {
        c cVar = this.f4833d;
        if (cVar.n != f2) {
            cVar.n = f2;
            m0();
        }
    }

    public void c0(boolean z) {
        this.y = z;
    }

    public void d0(int i) {
        this.s.d(i);
        this.f4833d.u = false;
        O();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.q.setColorFilter(this.v);
        int alpha = this.q.getAlpha();
        this.q.setAlpha(S(alpha, this.f4833d.m));
        this.r.setColorFilter(this.w);
        this.r.setStrokeWidth(this.f4833d.l);
        int alpha2 = this.r.getAlpha();
        this.r.setAlpha(S(alpha2, this.f4833d.m));
        if (this.f4837h) {
            i();
            g(u(), this.j);
            this.f4837h = false;
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
        c cVar = this.f4833d;
        if (cVar.t != i) {
            cVar.t = i;
            O();
        }
    }

    public void f0(int i) {
        c cVar = this.f4833d;
        if (cVar.q != i) {
            cVar.q = i;
            O();
        }
    }

    public void g0(float f2, int i) {
        j0(f2);
        i0(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f4833d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.f4833d.q == 2) {
            return;
        }
        if (Q()) {
            outline.setRoundRect(getBounds(), G() * this.f4833d.k);
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
        Rect rect2 = this.f4833d.i;
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
        c cVar = this.f4833d;
        nVar.e(cVar.f4841a, cVar.k, rectF, this.t, path);
    }

    public void h0(float f2, ColorStateList colorStateList) {
        j0(f2);
        i0(colorStateList);
    }

    public void i0(ColorStateList colorStateList) {
        c cVar = this.f4833d;
        if (cVar.f4845e != colorStateList) {
            cVar.f4845e = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.f4837h = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.f4833d.f4847g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f4833d.f4846f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f4833d.f4845e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f4833d.f4844d) != null && colorStateList4.isStateful())));
    }

    public void j0(float f2) {
        this.f4833d.l = f2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f4833d = new c(this.f4833d);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.f4837h = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.i.b
    protected boolean onStateChange(int[] iArr) {
        boolean z = k0(iArr) || l0();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    protected void p(Canvas canvas, Paint paint, Path path, RectF rectF) {
        q(canvas, paint, path, this.f4833d.f4841a, rectF);
    }

    public float s() {
        return this.f4833d.f4841a.j().a(u());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        c cVar = this.f4833d;
        if (cVar.m != i) {
            cVar.m = i;
            O();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f4833d.f4843c = colorFilter;
        O();
    }

    @Override // d.b.a.a.w.p
    public void setShapeAppearanceModel(m mVar) {
        this.f4833d.f4841a = mVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        this.f4833d.f4847g = colorStateList;
        l0();
        O();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f4833d;
        if (cVar.f4848h != mode) {
            cVar.f4848h = mode;
            l0();
            O();
        }
    }

    public float t() {
        return this.f4833d.f4841a.l().a(u());
    }

    protected RectF u() {
        this.l.set(getBounds());
        return this.l;
    }

    public float w() {
        return this.f4833d.o;
    }

    public ColorStateList x() {
        return this.f4833d.f4844d;
    }

    public float y() {
        return this.f4833d.k;
    }

    public float z() {
        return this.f4833d.n;
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
        this.f4834e = new o.g[4];
        this.f4835f = new o.g[4];
        this.f4836g = new BitSet(8);
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
        this.s = new d.b.a.a.v.a();
        this.u = new n();
        this.x = new RectF();
        this.y = true;
        this.f4833d = cVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = f4832c;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        l0();
        k0(getState());
        this.t = new a();
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    static final class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public m f4841a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public d.b.a.a.q.a f4842b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorFilter f4843c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ColorStateList f4844d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ColorStateList f4845e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ColorStateList f4846f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ColorStateList f4847g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public PorterDuff.Mode f4848h;
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

        public c(m mVar, d.b.a.a.q.a aVar) {
            this.f4844d = null;
            this.f4845e = null;
            this.f4846f = null;
            this.f4847g = null;
            this.f4848h = PorterDuff.Mode.SRC_IN;
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
            this.f4841a = mVar;
            this.f4842b = aVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVar = new h(this, null);
            hVar.f4837h = true;
            return hVar;
        }

        public c(c cVar) {
            this.f4844d = null;
            this.f4845e = null;
            this.f4846f = null;
            this.f4847g = null;
            this.f4848h = PorterDuff.Mode.SRC_IN;
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
            this.f4841a = cVar.f4841a;
            this.f4842b = cVar.f4842b;
            this.l = cVar.l;
            this.f4843c = cVar.f4843c;
            this.f4844d = cVar.f4844d;
            this.f4845e = cVar.f4845e;
            this.f4848h = cVar.f4848h;
            this.f4847g = cVar.f4847g;
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
            this.f4846f = cVar.f4846f;
            this.v = cVar.v;
            Rect rect = cVar.i;
            if (rect != null) {
                this.i = new Rect(rect);
            }
        }
    }
}
