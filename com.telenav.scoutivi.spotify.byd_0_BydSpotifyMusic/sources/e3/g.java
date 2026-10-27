package e3;

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
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import e3.k;
import e3.l;
import e3.m;
import java.util.BitSet;

/* JADX INFO: compiled from: MaterialShapeDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends Drawable implements n {
    private static final String B = g.class.getSimpleName();
    private static final Paint C = new Paint(1);
    private boolean A;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private c f6179e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final m.g[] f6180g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final m.g[] f6181h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final BitSet f6182i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f6183j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final Matrix f6184k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final Path f6185l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final Path f6186m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final RectF f6187n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final RectF f6188o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final Region f6189p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final Region f6190q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private k f6191r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private final Paint f6192s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private final Paint f6193t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private final d3.a f6194u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private final l.b f6195v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private final l f6196w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private PorterDuffColorFilter f6197x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private PorterDuffColorFilter f6198y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private final RectF f6199z;

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    class a implements l.b {
        a() {
        }

        @Override // e3.l.b
        public void a(m mVar, Matrix matrix, int i7) {
            g.this.f6182i.set(i7 + 4, mVar.e());
            g.this.f6181h[i7] = mVar.f(matrix);
        }

        @Override // e3.l.b
        public void b(m mVar, Matrix matrix, int i7) {
            g.this.f6182i.set(i7, mVar.e());
            g.this.f6180g[i7] = mVar.f(matrix);
        }
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    class b implements k.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f6201a;

        b(float f7) {
            this.f6201a = f7;
        }

        @Override // e3.k.c
        public e3.c a(e3.c cVar) {
            return cVar instanceof i ? cVar : new e3.b(this.f6201a, cVar);
        }
    }

    /* synthetic */ g(c cVar, a aVar) {
        this(cVar);
    }

    private float D() {
        if (L()) {
            return this.f6193t.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    private boolean J() {
        c cVar = this.f6179e;
        int i7 = cVar.f6219q;
        return i7 != 1 && cVar.f6220r > 0 && (i7 == 2 || T());
    }

    private boolean K() {
        Paint.Style style = this.f6179e.f6224v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    private boolean L() {
        Paint.Style style = this.f6179e.f6224v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.f6193t.getStrokeWidth() > 0.0f;
    }

    private void N() {
        super.invalidateSelf();
    }

    private void Q(Canvas canvas) {
        if (J()) {
            canvas.save();
            S(canvas);
            if (!this.A) {
                n(canvas);
                canvas.restore();
                return;
            }
            int iWidth = (int) (this.f6199z.width() - getBounds().width());
            int iHeight = (int) (this.f6199z.height() - getBounds().height());
            if (iWidth < 0 || iHeight < 0) {
                throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(((int) this.f6199z.width()) + (this.f6179e.f6220r * 2) + iWidth, ((int) this.f6199z.height()) + (this.f6179e.f6220r * 2) + iHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            float f7 = (getBounds().left - this.f6179e.f6220r) - iWidth;
            float f8 = (getBounds().top - this.f6179e.f6220r) - iHeight;
            canvas2.translate(-f7, -f8);
            n(canvas2);
            canvas.drawBitmap(bitmapCreateBitmap, f7, f8, (Paint) null);
            bitmapCreateBitmap.recycle();
            canvas.restore();
        }
    }

    private static int R(int i7, int i8) {
        return (i7 * (i8 + (i8 >>> 7))) >>> 8;
    }

    private void S(Canvas canvas) {
        canvas.translate(z(), A());
    }

    private PorterDuffColorFilter f(Paint paint, boolean z6) {
        int color;
        int iL;
        if (!z6 || (iL = l((color = paint.getColor()))) == color) {
            return null;
        }
        return new PorterDuffColorFilter(iL, PorterDuff.Mode.SRC_IN);
    }

    private boolean f0(int[] iArr) {
        boolean z6;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.f6179e.f6206d == null || color2 == (colorForState2 = this.f6179e.f6206d.getColorForState(iArr, (color2 = this.f6192s.getColor())))) {
            z6 = false;
        } else {
            this.f6192s.setColor(colorForState2);
            z6 = true;
        }
        if (this.f6179e.f6207e == null || color == (colorForState = this.f6179e.f6207e.getColorForState(iArr, (color = this.f6193t.getColor())))) {
            return z6;
        }
        this.f6193t.setColor(colorForState);
        return true;
    }

    private void g(RectF rectF, Path path) {
        h(rectF, path);
        if (this.f6179e.f6212j != 1.0f) {
            this.f6184k.reset();
            Matrix matrix = this.f6184k;
            float f7 = this.f6179e.f6212j;
            matrix.setScale(f7, f7, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.f6184k);
        }
        path.computeBounds(this.f6199z, true);
    }

    private boolean g0() {
        PorterDuffColorFilter porterDuffColorFilter = this.f6197x;
        PorterDuffColorFilter porterDuffColorFilter2 = this.f6198y;
        c cVar = this.f6179e;
        this.f6197x = k(cVar.f6209g, cVar.f6210h, this.f6192s, true);
        c cVar2 = this.f6179e;
        this.f6198y = k(cVar2.f6208f, cVar2.f6210h, this.f6193t, false);
        c cVar3 = this.f6179e;
        if (cVar3.f6223u) {
            this.f6194u.d(cVar3.f6209g.getColorForState(getState(), 0));
        }
        return (h0.c.a(porterDuffColorFilter, this.f6197x) && h0.c.a(porterDuffColorFilter2, this.f6198y)) ? false : true;
    }

    private void h0() {
        float fI = I();
        this.f6179e.f6220r = (int) Math.ceil(0.75f * fI);
        this.f6179e.f6221s = (int) Math.ceil(fI * 0.25f);
        g0();
        N();
    }

    private void i() {
        k kVarY = C().y(new b(-D()));
        this.f6191r = kVarY;
        this.f6196w.d(kVarY, this.f6179e.f6213k, v(), this.f6186m);
    }

    private PorterDuffColorFilter j(ColorStateList colorStateList, PorterDuff.Mode mode, boolean z6) {
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z6) {
            colorForState = l(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    private PorterDuffColorFilter k(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z6) {
        return (colorStateList == null || mode == null) ? f(paint, z6) : j(colorStateList, mode, z6);
    }

    public static g m(Context context, float f7) {
        int iB = u2.a.b(context, o2.b.colorSurface, g.class.getSimpleName());
        g gVar = new g();
        gVar.M(context);
        gVar.X(ColorStateList.valueOf(iB));
        gVar.W(f7);
        return gVar;
    }

    private void n(Canvas canvas) {
        if (this.f6182i.cardinality() > 0) {
            Log.w(B, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.f6179e.f6221s != 0) {
            canvas.drawPath(this.f6185l, this.f6194u.c());
        }
        for (int i7 = 0; i7 < 4; i7++) {
            this.f6180g[i7].b(this.f6194u, this.f6179e.f6220r, canvas);
            this.f6181h[i7].b(this.f6194u, this.f6179e.f6220r, canvas);
        }
        if (this.A) {
            int iZ = z();
            int iA = A();
            canvas.translate(-iZ, -iA);
            canvas.drawPath(this.f6185l, C);
            canvas.translate(iZ, iA);
        }
    }

    private void o(Canvas canvas) {
        q(canvas, this.f6192s, this.f6185l, this.f6179e.f6203a, u());
    }

    private void q(Canvas canvas, Paint paint, Path path, k kVar, RectF rectF) {
        if (!kVar.u(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = kVar.t().a(rectF) * this.f6179e.f6213k;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    private void r(Canvas canvas) {
        q(canvas, this.f6193t, this.f6186m, this.f6191r, v());
    }

    private RectF v() {
        this.f6188o.set(u());
        float fD = D();
        this.f6188o.inset(fD, fD);
        return this.f6188o;
    }

    public int A() {
        return (int) (((double) this.f6179e.f6221s) * Math.cos(Math.toRadians(r0.f6222t)));
    }

    public int B() {
        return this.f6179e.f6220r;
    }

    public k C() {
        return this.f6179e.f6203a;
    }

    public ColorStateList E() {
        return this.f6179e.f6209g;
    }

    public float F() {
        return this.f6179e.f6203a.r().a(u());
    }

    public float G() {
        return this.f6179e.f6203a.t().a(u());
    }

    public float H() {
        return this.f6179e.f6218p;
    }

    public float I() {
        return w() + H();
    }

    public void M(Context context) {
        this.f6179e.f6204b = new x2.a(context);
        h0();
    }

    public boolean O() {
        x2.a aVar = this.f6179e.f6204b;
        return aVar != null && aVar.d();
    }

    public boolean P() {
        return this.f6179e.f6203a.u(u());
    }

    public boolean T() {
        return (P() || this.f6185l.isConvex() || Build.VERSION.SDK_INT >= 29) ? false : true;
    }

    public void U(float f7) {
        setShapeAppearanceModel(this.f6179e.f6203a.w(f7));
    }

    public void V(e3.c cVar) {
        setShapeAppearanceModel(this.f6179e.f6203a.x(cVar));
    }

    public void W(float f7) {
        c cVar = this.f6179e;
        if (cVar.f6217o != f7) {
            cVar.f6217o = f7;
            h0();
        }
    }

    public void X(ColorStateList colorStateList) {
        c cVar = this.f6179e;
        if (cVar.f6206d != colorStateList) {
            cVar.f6206d = colorStateList;
            onStateChange(getState());
        }
    }

    public void Y(float f7) {
        c cVar = this.f6179e;
        if (cVar.f6213k != f7) {
            cVar.f6213k = f7;
            this.f6183j = true;
            invalidateSelf();
        }
    }

    public void Z(int i7, int i8, int i9, int i10) {
        c cVar = this.f6179e;
        if (cVar.f6211i == null) {
            cVar.f6211i = new Rect();
        }
        this.f6179e.f6211i.set(i7, i8, i9, i10);
        invalidateSelf();
    }

    public void a0(float f7) {
        c cVar = this.f6179e;
        if (cVar.f6216n != f7) {
            cVar.f6216n = f7;
            h0();
        }
    }

    public void b0(float f7, int i7) {
        e0(f7);
        d0(ColorStateList.valueOf(i7));
    }

    public void c0(float f7, ColorStateList colorStateList) {
        e0(f7);
        d0(colorStateList);
    }

    public void d0(ColorStateList colorStateList) {
        c cVar = this.f6179e;
        if (cVar.f6207e != colorStateList) {
            cVar.f6207e = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.f6192s.setColorFilter(this.f6197x);
        int alpha = this.f6192s.getAlpha();
        this.f6192s.setAlpha(R(alpha, this.f6179e.f6215m));
        this.f6193t.setColorFilter(this.f6198y);
        this.f6193t.setStrokeWidth(this.f6179e.f6214l);
        int alpha2 = this.f6193t.getAlpha();
        this.f6193t.setAlpha(R(alpha2, this.f6179e.f6215m));
        if (this.f6183j) {
            i();
            g(u(), this.f6185l);
            this.f6183j = false;
        }
        Q(canvas);
        if (K()) {
            o(canvas);
        }
        if (L()) {
            r(canvas);
        }
        this.f6192s.setAlpha(alpha);
        this.f6193t.setAlpha(alpha2);
    }

    public void e0(float f7) {
        this.f6179e.f6214l = f7;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f6179e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.f6179e.f6219q == 2) {
            return;
        }
        if (P()) {
            outline.setRoundRect(getBounds(), F() * this.f6179e.f6213k);
            return;
        }
        g(u(), this.f6185l);
        if (this.f6185l.isConvex() || Build.VERSION.SDK_INT >= 29) {
            try {
                outline.setConvexPath(this.f6185l);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        Rect rect2 = this.f6179e.f6211i;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        this.f6189p.set(getBounds());
        g(u(), this.f6185l);
        this.f6190q.setPath(this.f6185l, this.f6189p);
        this.f6189p.op(this.f6190q, Region.Op.DIFFERENCE);
        return this.f6189p;
    }

    protected final void h(RectF rectF, Path path) {
        l lVar = this.f6196w;
        c cVar = this.f6179e;
        lVar.e(cVar.f6203a, cVar.f6213k, rectF, this.f6195v, path);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.f6183j = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.f6179e.f6209g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f6179e.f6208f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f6179e.f6207e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f6179e.f6206d) != null && colorStateList4.isStateful())));
    }

    protected int l(int i7) {
        float fI = I() + y();
        x2.a aVar = this.f6179e.f6204b;
        return aVar != null ? aVar.c(i7, fI) : i7;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f6179e = new c(this.f6179e);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.f6183j = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.h.b
    protected boolean onStateChange(int[] iArr) {
        boolean z6 = f0(iArr) || g0();
        if (z6) {
            invalidateSelf();
        }
        return z6;
    }

    protected void p(Canvas canvas, Paint paint, Path path, RectF rectF) {
        q(canvas, paint, path, this.f6179e.f6203a, rectF);
    }

    public float s() {
        return this.f6179e.f6203a.j().a(u());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i7) {
        c cVar = this.f6179e;
        if (cVar.f6215m != i7) {
            cVar.f6215m = i7;
            N();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f6179e.f6205c = colorFilter;
        N();
    }

    @Override // e3.n
    public void setShapeAppearanceModel(k kVar) {
        this.f6179e.f6203a = kVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i7) {
        setTintList(ColorStateList.valueOf(i7));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f6179e.f6209g = colorStateList;
        g0();
        N();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f6179e;
        if (cVar.f6210h != mode) {
            cVar.f6210h = mode;
            g0();
            N();
        }
    }

    public float t() {
        return this.f6179e.f6203a.l().a(u());
    }

    protected RectF u() {
        this.f6187n.set(getBounds());
        return this.f6187n;
    }

    public float w() {
        return this.f6179e.f6217o;
    }

    public ColorStateList x() {
        return this.f6179e.f6206d;
    }

    public float y() {
        return this.f6179e.f6216n;
    }

    public int z() {
        return (int) (((double) this.f6179e.f6221s) * Math.sin(Math.toRadians(r0.f6222t)));
    }

    public g() {
        this(new k());
    }

    public g(Context context, AttributeSet attributeSet, int i7, int i8) {
        this(k.e(context, attributeSet, i7, i8).m());
    }

    public g(k kVar) {
        this(new c(kVar, null));
    }

    private g(c cVar) {
        this.f6180g = new m.g[4];
        this.f6181h = new m.g[4];
        this.f6182i = new BitSet(8);
        this.f6184k = new Matrix();
        this.f6185l = new Path();
        this.f6186m = new Path();
        this.f6187n = new RectF();
        this.f6188o = new RectF();
        this.f6189p = new Region();
        this.f6190q = new Region();
        Paint paint = new Paint(1);
        this.f6192s = paint;
        Paint paint2 = new Paint(1);
        this.f6193t = paint2;
        this.f6194u = new d3.a();
        this.f6196w = Looper.getMainLooper().getThread() == Thread.currentThread() ? l.k() : new l();
        this.f6199z = new RectF();
        this.A = true;
        this.f6179e = cVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = C;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        g0();
        f0(getState());
        this.f6195v = new a();
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    static final class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public k f6203a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public x2.a f6204b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorFilter f6205c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ColorStateList f6206d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ColorStateList f6207e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ColorStateList f6208f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ColorStateList f6209g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public PorterDuff.Mode f6210h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Rect f6211i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f6212j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f6213k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f6214l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f6215m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public float f6216n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public float f6217o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public float f6218p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f6219q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f6220r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f6221s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f6222t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public boolean f6223u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public Paint.Style f6224v;

        public c(k kVar, x2.a aVar) {
            this.f6206d = null;
            this.f6207e = null;
            this.f6208f = null;
            this.f6209g = null;
            this.f6210h = PorterDuff.Mode.SRC_IN;
            this.f6211i = null;
            this.f6212j = 1.0f;
            this.f6213k = 1.0f;
            this.f6215m = 255;
            this.f6216n = 0.0f;
            this.f6217o = 0.0f;
            this.f6218p = 0.0f;
            this.f6219q = 0;
            this.f6220r = 0;
            this.f6221s = 0;
            this.f6222t = 0;
            this.f6223u = false;
            this.f6224v = Paint.Style.FILL_AND_STROKE;
            this.f6203a = kVar;
            this.f6204b = aVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            g gVar = new g(this, null);
            gVar.f6183j = true;
            return gVar;
        }

        public c(c cVar) {
            this.f6206d = null;
            this.f6207e = null;
            this.f6208f = null;
            this.f6209g = null;
            this.f6210h = PorterDuff.Mode.SRC_IN;
            this.f6211i = null;
            this.f6212j = 1.0f;
            this.f6213k = 1.0f;
            this.f6215m = 255;
            this.f6216n = 0.0f;
            this.f6217o = 0.0f;
            this.f6218p = 0.0f;
            this.f6219q = 0;
            this.f6220r = 0;
            this.f6221s = 0;
            this.f6222t = 0;
            this.f6223u = false;
            this.f6224v = Paint.Style.FILL_AND_STROKE;
            this.f6203a = cVar.f6203a;
            this.f6204b = cVar.f6204b;
            this.f6214l = cVar.f6214l;
            this.f6205c = cVar.f6205c;
            this.f6206d = cVar.f6206d;
            this.f6207e = cVar.f6207e;
            this.f6210h = cVar.f6210h;
            this.f6209g = cVar.f6209g;
            this.f6215m = cVar.f6215m;
            this.f6212j = cVar.f6212j;
            this.f6221s = cVar.f6221s;
            this.f6219q = cVar.f6219q;
            this.f6223u = cVar.f6223u;
            this.f6213k = cVar.f6213k;
            this.f6216n = cVar.f6216n;
            this.f6217o = cVar.f6217o;
            this.f6218p = cVar.f6218p;
            this.f6220r = cVar.f6220r;
            this.f6222t = cVar.f6222t;
            this.f6208f = cVar.f6208f;
            this.f6224v = cVar.f6224v;
            if (cVar.f6211i != null) {
                this.f6211i = new Rect(cVar.f6211i);
            }
        }
    }
}
