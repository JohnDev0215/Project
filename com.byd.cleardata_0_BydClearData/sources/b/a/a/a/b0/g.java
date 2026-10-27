package b.a.a.a.b0;

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
import b.a.a.a.b0.k;
import b.a.a.a.b0.l;
import b.a.a.a.b0.m;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public class g extends Drawable implements androidx.core.graphics.drawable.b, n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f1650b = g.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Paint f1651c = new Paint(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f1652d;
    private final m.g[] e;
    private final m.g[] f;
    private final BitSet g;
    private boolean h;
    private final Matrix i;
    private final Path j;
    private final Path k;
    private final RectF l;
    private final RectF m;
    private final Region n;
    private final Region o;
    private k p;
    private final Paint q;
    private final Paint r;
    private final b.a.a.a.a0.a s;
    private final l.a t;
    private final l u;
    private PorterDuffColorFilter v;
    private PorterDuffColorFilter w;
    private final RectF x;
    private boolean y;

    class a implements l.a {
        a() {
        }

        @Override // b.a.a.a.b0.l.a
        public void a(m mVar, Matrix matrix, int i) {
            g.this.g.set(i + 4, mVar.e());
            g.this.f[i] = mVar.f(matrix);
        }

        @Override // b.a.a.a.b0.l.a
        public void b(m mVar, Matrix matrix, int i) {
            g.this.g.set(i, mVar.e());
            g.this.e[i] = mVar.f(matrix);
        }
    }

    class b implements k.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f1654a;

        b(float f) {
            this.f1654a = f;
        }

        @Override // b.a.a.a.b0.k.c
        public b.a.a.a.b0.c a(b.a.a.a.b0.c cVar) {
            return cVar instanceof i ? cVar : new b.a.a.a.b0.b(this.f1654a, cVar);
        }
    }

    static final class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public k f1656a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b.a.a.a.u.a f1657b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorFilter f1658c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ColorStateList f1659d;
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

        public c(c cVar) {
            this.f1659d = null;
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
            this.f1656a = cVar.f1656a;
            this.f1657b = cVar.f1657b;
            this.l = cVar.l;
            this.f1658c = cVar.f1658c;
            this.f1659d = cVar.f1659d;
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
            Rect rect = cVar.i;
            if (rect != null) {
                this.i = new Rect(rect);
            }
        }

        public c(k kVar, b.a.a.a.u.a aVar) {
            this.f1659d = null;
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
            this.f1656a = kVar;
            this.f1657b = aVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            g gVar = new g(this, null);
            gVar.h = true;
            return gVar;
        }
    }

    public g() {
        this(new k());
    }

    public g(Context context, AttributeSet attributeSet, int i, int i2) {
        this(k.e(context, attributeSet, i, i2).m());
    }

    private g(c cVar) {
        this.e = new m.g[4];
        this.f = new m.g[4];
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
        this.s = new b.a.a.a.a0.a();
        this.u = new l();
        this.x = new RectF();
        this.y = true;
        this.f1652d = cVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = f1651c;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        g0();
        f0(getState());
        this.t = new a();
    }

    /* synthetic */ g(c cVar, a aVar) {
        this(cVar);
    }

    public g(k kVar) {
        this(new c(kVar, null));
    }

    private float D() {
        if (L()) {
            return this.r.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    private boolean J() {
        c cVar = this.f1652d;
        int i = cVar.q;
        return i != 1 && cVar.r > 0 && (i == 2 || T());
    }

    private boolean K() {
        Paint.Style style = this.f1652d.v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    private boolean L() {
        Paint.Style style = this.f1652d.v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.r.getStrokeWidth() > 0.0f;
    }

    private void N() {
        super.invalidateSelf();
    }

    private void Q(Canvas canvas) {
        if (J()) {
            canvas.save();
            S(canvas);
            if (this.y) {
                int iWidth = (int) (this.x.width() - getBounds().width());
                int iHeight = (int) (this.x.height() - getBounds().height());
                if (iWidth < 0 || iHeight < 0) {
                    throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
                }
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(((int) this.x.width()) + (this.f1652d.r * 2) + iWidth, ((int) this.x.height()) + (this.f1652d.r * 2) + iHeight, Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(bitmapCreateBitmap);
                float f = (getBounds().left - this.f1652d.r) - iWidth;
                float f2 = (getBounds().top - this.f1652d.r) - iHeight;
                canvas2.translate(-f, -f2);
                n(canvas2);
                canvas.drawBitmap(bitmapCreateBitmap, f, f2, (Paint) null);
                bitmapCreateBitmap.recycle();
            } else {
                n(canvas);
            }
            canvas.restore();
        }
    }

    private static int R(int i, int i2) {
        return (i * (i2 + (i2 >>> 7))) >>> 8;
    }

    private void S(Canvas canvas) {
        int iZ = z();
        int iA = A();
        if (Build.VERSION.SDK_INT < 21 && this.y) {
            Rect clipBounds = canvas.getClipBounds();
            int i = this.f1652d.r;
            clipBounds.inset(-i, -i);
            clipBounds.offset(iZ, iA);
            canvas.clipRect(clipBounds, Region.Op.REPLACE);
        }
        canvas.translate(iZ, iA);
    }

    private PorterDuffColorFilter f(Paint paint, boolean z) {
        int color;
        int iL;
        if (!z || (iL = l((color = paint.getColor()))) == color) {
            return null;
        }
        return new PorterDuffColorFilter(iL, PorterDuff.Mode.SRC_IN);
    }

    private boolean f0(int[] iArr) {
        boolean z;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.f1652d.f1659d == null || color2 == (colorForState2 = this.f1652d.f1659d.getColorForState(iArr, (color2 = this.q.getColor())))) {
            z = false;
        } else {
            this.q.setColor(colorForState2);
            z = true;
        }
        if (this.f1652d.e == null || color == (colorForState = this.f1652d.e.getColorForState(iArr, (color = this.r.getColor())))) {
            return z;
        }
        this.r.setColor(colorForState);
        return true;
    }

    private void g(RectF rectF, Path path) {
        h(rectF, path);
        if (this.f1652d.j != 1.0f) {
            this.i.reset();
            Matrix matrix = this.i;
            float f = this.f1652d.j;
            matrix.setScale(f, f, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.i);
        }
        path.computeBounds(this.x, true);
    }

    private boolean g0() {
        PorterDuffColorFilter porterDuffColorFilter = this.v;
        PorterDuffColorFilter porterDuffColorFilter2 = this.w;
        c cVar = this.f1652d;
        this.v = k(cVar.g, cVar.h, this.q, true);
        c cVar2 = this.f1652d;
        this.w = k(cVar2.f, cVar2.h, this.r, false);
        c cVar3 = this.f1652d;
        if (cVar3.u) {
            this.s.d(cVar3.g.getColorForState(getState(), 0));
        }
        return (a.g.j.c.a(porterDuffColorFilter, this.v) && a.g.j.c.a(porterDuffColorFilter2, this.w)) ? false : true;
    }

    private void h0() {
        float fI = I();
        this.f1652d.r = (int) Math.ceil(0.75f * fI);
        this.f1652d.s = (int) Math.ceil(fI * 0.25f);
        g0();
        N();
    }

    private void i() {
        k kVarX = C().x(new b(-D()));
        this.p = kVarX;
        this.u.d(kVarX, this.f1652d.k, v(), this.k);
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

    private int l(int i) {
        float fI = I() + y();
        b.a.a.a.u.a aVar = this.f1652d.f1657b;
        return aVar != null ? aVar.c(i, fI) : i;
    }

    public static g m(Context context, float f) {
        int iB = b.a.a.a.s.a.b(context, b.a.a.a.b.l, g.class.getSimpleName());
        g gVar = new g();
        gVar.M(context);
        gVar.W(ColorStateList.valueOf(iB));
        gVar.V(f);
        return gVar;
    }

    private void n(Canvas canvas) {
        if (this.g.cardinality() > 0) {
            Log.w(f1650b, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.f1652d.s != 0) {
            canvas.drawPath(this.j, this.s.c());
        }
        for (int i = 0; i < 4; i++) {
            this.e[i].b(this.s, this.f1652d.r, canvas);
            this.f[i].b(this.s, this.f1652d.r, canvas);
        }
        if (this.y) {
            int iZ = z();
            int iA = A();
            canvas.translate(-iZ, -iA);
            canvas.drawPath(this.j, f1651c);
            canvas.translate(iZ, iA);
        }
    }

    private void o(Canvas canvas) {
        q(canvas, this.q, this.j, this.f1652d.f1656a, u());
    }

    private void q(Canvas canvas, Paint paint, Path path, k kVar, RectF rectF) {
        if (!kVar.u(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = kVar.t().a(rectF) * this.f1652d.k;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    private void r(Canvas canvas) {
        q(canvas, this.r, this.k, this.p, v());
    }

    private RectF v() {
        this.m.set(u());
        float fD = D();
        this.m.inset(fD, fD);
        return this.m;
    }

    public int A() {
        return (int) (((double) this.f1652d.s) * Math.cos(Math.toRadians(r0.t)));
    }

    public int B() {
        return this.f1652d.r;
    }

    public k C() {
        return this.f1652d.f1656a;
    }

    public ColorStateList E() {
        return this.f1652d.g;
    }

    public float F() {
        return this.f1652d.f1656a.r().a(u());
    }

    public float G() {
        return this.f1652d.f1656a.t().a(u());
    }

    public float H() {
        return this.f1652d.p;
    }

    public float I() {
        return w() + H();
    }

    public void M(Context context) {
        this.f1652d.f1657b = new b.a.a.a.u.a(context);
        h0();
    }

    public boolean O() {
        b.a.a.a.u.a aVar = this.f1652d.f1657b;
        return aVar != null && aVar.d();
    }

    public boolean P() {
        return this.f1652d.f1656a.u(u());
    }

    public boolean T() {
        int i = Build.VERSION.SDK_INT;
        return i < 21 || !(P() || this.j.isConvex() || i >= 29);
    }

    public void U(float f) {
        setShapeAppearanceModel(this.f1652d.f1656a.w(f));
    }

    public void V(float f) {
        c cVar = this.f1652d;
        if (cVar.o != f) {
            cVar.o = f;
            h0();
        }
    }

    public void W(ColorStateList colorStateList) {
        c cVar = this.f1652d;
        if (cVar.f1659d != colorStateList) {
            cVar.f1659d = colorStateList;
            onStateChange(getState());
        }
    }

    public void X(float f) {
        c cVar = this.f1652d;
        if (cVar.k != f) {
            cVar.k = f;
            this.h = true;
            invalidateSelf();
        }
    }

    public void Y(int i, int i2, int i3, int i4) {
        c cVar = this.f1652d;
        if (cVar.i == null) {
            cVar.i = new Rect();
        }
        this.f1652d.i.set(i, i2, i3, i4);
        invalidateSelf();
    }

    public void Z(float f) {
        c cVar = this.f1652d;
        if (cVar.n != f) {
            cVar.n = f;
            h0();
        }
    }

    public void a0(int i) {
        c cVar = this.f1652d;
        if (cVar.t != i) {
            cVar.t = i;
            N();
        }
    }

    public void b0(float f, int i) {
        e0(f);
        d0(ColorStateList.valueOf(i));
    }

    public void c0(float f, ColorStateList colorStateList) {
        e0(f);
        d0(colorStateList);
    }

    public void d0(ColorStateList colorStateList) {
        c cVar = this.f1652d;
        if (cVar.e != colorStateList) {
            cVar.e = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.q.setColorFilter(this.v);
        int alpha = this.q.getAlpha();
        this.q.setAlpha(R(alpha, this.f1652d.m));
        this.r.setColorFilter(this.w);
        this.r.setStrokeWidth(this.f1652d.l);
        int alpha2 = this.r.getAlpha();
        this.r.setAlpha(R(alpha2, this.f1652d.m));
        if (this.h) {
            i();
            g(u(), this.j);
            this.h = false;
        }
        Q(canvas);
        if (K()) {
            o(canvas);
        }
        if (L()) {
            r(canvas);
        }
        this.q.setAlpha(alpha);
        this.r.setAlpha(alpha2);
    }

    public void e0(float f) {
        this.f1652d.l = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f1652d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.f1652d.q == 2) {
            return;
        }
        if (P()) {
            outline.setRoundRect(getBounds(), F() * this.f1652d.k);
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
        Rect rect2 = this.f1652d.i;
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
        l lVar = this.u;
        c cVar = this.f1652d;
        lVar.e(cVar.f1656a, cVar.k, rectF, this.t, path);
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
        return super.isStateful() || ((colorStateList = this.f1652d.g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f1652d.f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f1652d.e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f1652d.f1659d) != null && colorStateList4.isStateful())));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f1652d = new c(this.f1652d);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.h = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean z = f0(iArr) || g0();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    protected void p(Canvas canvas, Paint paint, Path path, RectF rectF) {
        q(canvas, paint, path, this.f1652d.f1656a, rectF);
    }

    public float s() {
        return this.f1652d.f1656a.j().a(u());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        c cVar = this.f1652d;
        if (cVar.m != i) {
            cVar.m = i;
            N();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1652d.f1658c = colorFilter;
        N();
    }

    @Override // b.a.a.a.b0.n
    public void setShapeAppearanceModel(k kVar) {
        this.f1652d.f1656a = kVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        this.f1652d.g = colorStateList;
        g0();
        N();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f1652d;
        if (cVar.h != mode) {
            cVar.h = mode;
            g0();
            N();
        }
    }

    public float t() {
        return this.f1652d.f1656a.l().a(u());
    }

    protected RectF u() {
        this.l.set(getBounds());
        return this.l;
    }

    public float w() {
        return this.f1652d.o;
    }

    public ColorStateList x() {
        return this.f1652d.f1659d;
    }

    public float y() {
        return this.f1652d.n;
    }

    public int z() {
        return (int) (((double) this.f1652d.s) * Math.sin(Math.toRadians(r0.t)));
    }
}
