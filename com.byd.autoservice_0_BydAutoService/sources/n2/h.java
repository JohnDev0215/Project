package n2;

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
import com.google.android.material.R$attr;
import java.util.BitSet;
import n2.m;
import n2.n;
import n2.o;

/* JADX INFO: compiled from: MaterialShapeDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends Drawable implements e0.b, p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f4099b = h.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Paint f4100c = new Paint(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f4101d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final o.g[] f4102e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final o.g[] f4103f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final BitSet f4104g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4105h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Matrix f4106i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Path f4107j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Path f4108k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final RectF f4109l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final RectF f4110m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Region f4111n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Region f4112o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public m f4113p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Paint f4114q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Paint f4115r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final m2.a f4116s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final n.a f4117t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final n f4118u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public PorterDuffColorFilter f4119v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public PorterDuffColorFilter f4120w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final RectF f4121x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f4122y;

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public class a implements n.a {
        public a() {
        }

        @Override // n2.n.a
        public void a(o oVar, Matrix matrix, int i3) {
            h.this.f4104g.set(i3 + 4, oVar.e());
            h.this.f4103f[i3] = oVar.f(matrix);
        }

        @Override // n2.n.a
        public void b(o oVar, Matrix matrix, int i3) {
            h.this.f4104g.set(i3, oVar.e());
            h.this.f4102e[i3] = oVar.f(matrix);
        }
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public class b implements m.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ float f4124a;

        public b(float f3) {
            this.f4124a = f3;
        }

        @Override // n2.m.c
        public n2.c a(n2.c cVar) {
            return cVar instanceof k ? cVar : new n2.b(this.f4124a, cVar);
        }
    }

    public /* synthetic */ h(c cVar, a aVar) {
        this(cVar);
    }

    public static int S(int i3, int i4) {
        return (i3 * (i4 + (i4 >>> 7))) >>> 8;
    }

    public static h m(Context context, float f3) {
        int iB = c2.a.b(context, R$attr.colorSurface, h.class.getSimpleName());
        h hVar = new h();
        hVar.N(context);
        hVar.X(ColorStateList.valueOf(iB));
        hVar.W(f3);
        return hVar;
    }

    public int A() {
        return (int) (((double) this.f4101d.f4144s) * Math.sin(Math.toRadians(r0.f4145t)));
    }

    public int B() {
        return (int) (((double) this.f4101d.f4144s) * Math.cos(Math.toRadians(r0.f4145t)));
    }

    public int C() {
        return this.f4101d.f4143r;
    }

    public m D() {
        return this.f4101d.f4126a;
    }

    public final float E() {
        if (M()) {
            return this.f4115r.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    public ColorStateList F() {
        return this.f4101d.f4132g;
    }

    public float G() {
        return this.f4101d.f4126a.r().a(u());
    }

    public float H() {
        return this.f4101d.f4126a.t().a(u());
    }

    public float I() {
        return this.f4101d.f4141p;
    }

    public float J() {
        return w() + I();
    }

    public final boolean K() {
        c cVar = this.f4101d;
        int i3 = cVar.f4142q;
        return i3 != 1 && cVar.f4143r > 0 && (i3 == 2 || U());
    }

    public final boolean L() {
        Paint.Style style = this.f4101d.f4147v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    public final boolean M() {
        Paint.Style style = this.f4101d.f4147v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.f4115r.getStrokeWidth() > 0.0f;
    }

    public void N(Context context) {
        this.f4101d.f4127b = new f2.a(context);
        m0();
    }

    public final void O() {
        super.invalidateSelf();
    }

    public boolean P() {
        f2.a aVar = this.f4101d.f4127b;
        return aVar != null && aVar.d();
    }

    public boolean Q() {
        return this.f4101d.f4126a.u(u());
    }

    public final void R(Canvas canvas) {
        if (K()) {
            canvas.save();
            T(canvas);
            if (!this.f4122y) {
                n(canvas);
                canvas.restore();
                return;
            }
            int iWidth = (int) (this.f4121x.width() - getBounds().width());
            int iHeight = (int) (this.f4121x.height() - getBounds().height());
            if (iWidth < 0 || iHeight < 0) {
                throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(((int) this.f4121x.width()) + (this.f4101d.f4143r * 2) + iWidth, ((int) this.f4121x.height()) + (this.f4101d.f4143r * 2) + iHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            float f3 = (getBounds().left - this.f4101d.f4143r) - iWidth;
            float f4 = (getBounds().top - this.f4101d.f4143r) - iHeight;
            canvas2.translate(-f3, -f4);
            n(canvas2);
            canvas.drawBitmap(bitmapCreateBitmap, f3, f4, (Paint) null);
            bitmapCreateBitmap.recycle();
            canvas.restore();
        }
    }

    public final void T(Canvas canvas) {
        int iA = A();
        int iB = B();
        if (Build.VERSION.SDK_INT < 21 && this.f4122y) {
            Rect clipBounds = canvas.getClipBounds();
            int i3 = this.f4101d.f4143r;
            clipBounds.inset(-i3, -i3);
            clipBounds.offset(iA, iB);
            canvas.clipRect(clipBounds, Region.Op.REPLACE);
        }
        canvas.translate(iA, iB);
    }

    public boolean U() {
        int i3 = Build.VERSION.SDK_INT;
        return i3 < 21 || !(Q() || this.f4107j.isConvex() || i3 >= 29);
    }

    public void V(float f3) {
        setShapeAppearanceModel(this.f4101d.f4126a.w(f3));
    }

    public void W(float f3) {
        c cVar = this.f4101d;
        if (cVar.f4140o != f3) {
            cVar.f4140o = f3;
            m0();
        }
    }

    public void X(ColorStateList colorStateList) {
        c cVar = this.f4101d;
        if (cVar.f4129d != colorStateList) {
            cVar.f4129d = colorStateList;
            onStateChange(getState());
        }
    }

    public void Y(float f3) {
        c cVar = this.f4101d;
        if (cVar.f4136k != f3) {
            cVar.f4136k = f3;
            this.f4105h = true;
            invalidateSelf();
        }
    }

    public void Z(int i3, int i4, int i5, int i6) {
        c cVar = this.f4101d;
        if (cVar.f4134i == null) {
            cVar.f4134i = new Rect();
        }
        this.f4101d.f4134i.set(i3, i4, i5, i6);
        invalidateSelf();
    }

    public void a0(Paint.Style style) {
        this.f4101d.f4147v = style;
        O();
    }

    public void b0(float f3) {
        c cVar = this.f4101d;
        if (cVar.f4139n != f3) {
            cVar.f4139n = f3;
            m0();
        }
    }

    public void c0(boolean z3) {
        this.f4122y = z3;
    }

    public void d0(int i3) {
        this.f4116s.d(i3);
        this.f4101d.f4146u = false;
        O();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.f4114q.setColorFilter(this.f4119v);
        int alpha = this.f4114q.getAlpha();
        this.f4114q.setAlpha(S(alpha, this.f4101d.f4138m));
        this.f4115r.setColorFilter(this.f4120w);
        this.f4115r.setStrokeWidth(this.f4101d.f4137l);
        int alpha2 = this.f4115r.getAlpha();
        this.f4115r.setAlpha(S(alpha2, this.f4101d.f4138m));
        if (this.f4105h) {
            i();
            g(u(), this.f4107j);
            this.f4105h = false;
        }
        R(canvas);
        if (L()) {
            o(canvas);
        }
        if (M()) {
            r(canvas);
        }
        this.f4114q.setAlpha(alpha);
        this.f4115r.setAlpha(alpha2);
    }

    public void e0(int i3) {
        c cVar = this.f4101d;
        if (cVar.f4145t != i3) {
            cVar.f4145t = i3;
            O();
        }
    }

    public final PorterDuffColorFilter f(Paint paint, boolean z3) {
        int color;
        int iL;
        if (!z3 || (iL = l((color = paint.getColor()))) == color) {
            return null;
        }
        return new PorterDuffColorFilter(iL, PorterDuff.Mode.SRC_IN);
    }

    public void f0(int i3) {
        c cVar = this.f4101d;
        if (cVar.f4142q != i3) {
            cVar.f4142q = i3;
            O();
        }
    }

    public final void g(RectF rectF, Path path) {
        h(rectF, path);
        if (this.f4101d.f4135j != 1.0f) {
            this.f4106i.reset();
            Matrix matrix = this.f4106i;
            float f3 = this.f4101d.f4135j;
            matrix.setScale(f3, f3, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.f4106i);
        }
        path.computeBounds(this.f4121x, true);
    }

    public void g0(float f3, int i3) {
        j0(f3);
        i0(ColorStateList.valueOf(i3));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f4101d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.f4101d.f4142q == 2) {
            return;
        }
        if (Q()) {
            outline.setRoundRect(getBounds(), G() * this.f4101d.f4136k);
            return;
        }
        g(u(), this.f4107j);
        if (this.f4107j.isConvex() || Build.VERSION.SDK_INT >= 29) {
            try {
                outline.setConvexPath(this.f4107j);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        Rect rect2 = this.f4101d.f4134i;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        this.f4111n.set(getBounds());
        g(u(), this.f4107j);
        this.f4112o.setPath(this.f4107j, this.f4111n);
        this.f4111n.op(this.f4112o, Region.Op.DIFFERENCE);
        return this.f4111n;
    }

    public final void h(RectF rectF, Path path) {
        n nVar = this.f4118u;
        c cVar = this.f4101d;
        nVar.e(cVar.f4126a, cVar.f4136k, rectF, this.f4117t, path);
    }

    public void h0(float f3, ColorStateList colorStateList) {
        j0(f3);
        i0(colorStateList);
    }

    public final void i() {
        m mVarX = D().x(new b(-E()));
        this.f4113p = mVarX;
        this.f4118u.d(mVarX, this.f4101d.f4136k, v(), this.f4108k);
    }

    public void i0(ColorStateList colorStateList) {
        c cVar = this.f4101d;
        if (cVar.f4130e != colorStateList) {
            cVar.f4130e = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.f4105h = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.f4101d.f4132g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f4101d.f4131f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f4101d.f4130e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f4101d.f4129d) != null && colorStateList4.isStateful())));
    }

    public final PorterDuffColorFilter j(ColorStateList colorStateList, PorterDuff.Mode mode, boolean z3) {
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z3) {
            colorForState = l(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    public void j0(float f3) {
        this.f4101d.f4137l = f3;
        invalidateSelf();
    }

    public final PorterDuffColorFilter k(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z3) {
        return (colorStateList == null || mode == null) ? f(paint, z3) : j(colorStateList, mode, z3);
    }

    public final boolean k0(int[] iArr) {
        boolean z3;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.f4101d.f4129d == null || color2 == (colorForState2 = this.f4101d.f4129d.getColorForState(iArr, (color2 = this.f4114q.getColor())))) {
            z3 = false;
        } else {
            this.f4114q.setColor(colorForState2);
            z3 = true;
        }
        if (this.f4101d.f4130e == null || color == (colorForState = this.f4101d.f4130e.getColorForState(iArr, (color = this.f4115r.getColor())))) {
            return z3;
        }
        this.f4115r.setColor(colorForState);
        return true;
    }

    public final int l(int i3) {
        float fJ = J() + z();
        f2.a aVar = this.f4101d.f4127b;
        return aVar != null ? aVar.c(i3, fJ) : i3;
    }

    public final boolean l0() {
        PorterDuffColorFilter porterDuffColorFilter = this.f4119v;
        PorterDuffColorFilter porterDuffColorFilter2 = this.f4120w;
        c cVar = this.f4101d;
        this.f4119v = k(cVar.f4132g, cVar.f4133h, this.f4114q, true);
        c cVar2 = this.f4101d;
        this.f4120w = k(cVar2.f4131f, cVar2.f4133h, this.f4115r, false);
        c cVar3 = this.f4101d;
        if (cVar3.f4146u) {
            this.f4116s.d(cVar3.f4132g.getColorForState(getState(), 0));
        }
        return (k0.c.a(porterDuffColorFilter, this.f4119v) && k0.c.a(porterDuffColorFilter2, this.f4120w)) ? false : true;
    }

    public final void m0() {
        float fJ = J();
        this.f4101d.f4143r = (int) Math.ceil(0.75f * fJ);
        this.f4101d.f4144s = (int) Math.ceil(fJ * 0.25f);
        l0();
        O();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f4101d = new c(this.f4101d);
        return this;
    }

    public final void n(Canvas canvas) {
        if (this.f4104g.cardinality() > 0) {
            Log.w(f4099b, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.f4101d.f4144s != 0) {
            canvas.drawPath(this.f4107j, this.f4116s.c());
        }
        for (int i3 = 0; i3 < 4; i3++) {
            this.f4102e[i3].b(this.f4116s, this.f4101d.f4143r, canvas);
            this.f4103f[i3].b(this.f4116s, this.f4101d.f4143r, canvas);
        }
        if (this.f4122y) {
            int iA = A();
            int iB = B();
            canvas.translate(-iA, -iB);
            canvas.drawPath(this.f4107j, f4100c);
            canvas.translate(iA, iB);
        }
    }

    public final void o(Canvas canvas) {
        q(canvas, this.f4114q, this.f4107j, this.f4101d.f4126a, u());
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.f4105h = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable, i2.i.b
    public boolean onStateChange(int[] iArr) {
        boolean z3 = k0(iArr) || l0();
        if (z3) {
            invalidateSelf();
        }
        return z3;
    }

    public void p(Canvas canvas, Paint paint, Path path, RectF rectF) {
        q(canvas, paint, path, this.f4101d.f4126a, rectF);
    }

    public final void q(Canvas canvas, Paint paint, Path path, m mVar, RectF rectF) {
        if (!mVar.u(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = mVar.t().a(rectF) * this.f4101d.f4136k;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    public final void r(Canvas canvas) {
        q(canvas, this.f4115r, this.f4108k, this.f4113p, v());
    }

    public float s() {
        return this.f4101d.f4126a.j().a(u());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i3) {
        c cVar = this.f4101d;
        if (cVar.f4138m != i3) {
            cVar.f4138m = i3;
            O();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f4101d.f4128c = colorFilter;
        O();
    }

    @Override // n2.p
    public void setShapeAppearanceModel(m mVar) {
        this.f4101d.f4126a = mVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTint(int i3) {
        setTintList(ColorStateList.valueOf(i3));
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTintList(ColorStateList colorStateList) {
        this.f4101d.f4132g = colorStateList;
        l0();
        O();
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f4101d;
        if (cVar.f4133h != mode) {
            cVar.f4133h = mode;
            l0();
            O();
        }
    }

    public float t() {
        return this.f4101d.f4126a.l().a(u());
    }

    public RectF u() {
        this.f4109l.set(getBounds());
        return this.f4109l;
    }

    public final RectF v() {
        this.f4110m.set(u());
        float fE = E();
        this.f4110m.inset(fE, fE);
        return this.f4110m;
    }

    public float w() {
        return this.f4101d.f4140o;
    }

    public ColorStateList x() {
        return this.f4101d.f4129d;
    }

    public float y() {
        return this.f4101d.f4136k;
    }

    public float z() {
        return this.f4101d.f4139n;
    }

    public h() {
        this(new m());
    }

    public h(Context context, AttributeSet attributeSet, int i3, int i4) {
        this(m.e(context, attributeSet, i3, i4).m());
    }

    public h(m mVar) {
        this(new c(mVar, null));
    }

    public h(c cVar) {
        this.f4102e = new o.g[4];
        this.f4103f = new o.g[4];
        this.f4104g = new BitSet(8);
        this.f4106i = new Matrix();
        this.f4107j = new Path();
        this.f4108k = new Path();
        this.f4109l = new RectF();
        this.f4110m = new RectF();
        this.f4111n = new Region();
        this.f4112o = new Region();
        Paint paint = new Paint(1);
        this.f4114q = paint;
        Paint paint2 = new Paint(1);
        this.f4115r = paint2;
        this.f4116s = new m2.a();
        this.f4118u = new n();
        this.f4121x = new RectF();
        this.f4122y = true;
        this.f4101d = cVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = f4100c;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        l0();
        k0(getState());
        this.f4117t = new a();
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public static final class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public m f4126a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public f2.a f4127b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorFilter f4128c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ColorStateList f4129d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ColorStateList f4130e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ColorStateList f4131f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ColorStateList f4132g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public PorterDuff.Mode f4133h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Rect f4134i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f4135j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f4136k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f4137l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f4138m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public float f4139n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public float f4140o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public float f4141p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f4142q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f4143r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f4144s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f4145t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public boolean f4146u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public Paint.Style f4147v;

        public c(m mVar, f2.a aVar) {
            this.f4129d = null;
            this.f4130e = null;
            this.f4131f = null;
            this.f4132g = null;
            this.f4133h = PorterDuff.Mode.SRC_IN;
            this.f4134i = null;
            this.f4135j = 1.0f;
            this.f4136k = 1.0f;
            this.f4138m = 255;
            this.f4139n = 0.0f;
            this.f4140o = 0.0f;
            this.f4141p = 0.0f;
            this.f4142q = 0;
            this.f4143r = 0;
            this.f4144s = 0;
            this.f4145t = 0;
            this.f4146u = false;
            this.f4147v = Paint.Style.FILL_AND_STROKE;
            this.f4126a = mVar;
            this.f4127b = aVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVar = new h(this, null);
            hVar.f4105h = true;
            return hVar;
        }

        public c(c cVar) {
            this.f4129d = null;
            this.f4130e = null;
            this.f4131f = null;
            this.f4132g = null;
            this.f4133h = PorterDuff.Mode.SRC_IN;
            this.f4134i = null;
            this.f4135j = 1.0f;
            this.f4136k = 1.0f;
            this.f4138m = 255;
            this.f4139n = 0.0f;
            this.f4140o = 0.0f;
            this.f4141p = 0.0f;
            this.f4142q = 0;
            this.f4143r = 0;
            this.f4144s = 0;
            this.f4145t = 0;
            this.f4146u = false;
            this.f4147v = Paint.Style.FILL_AND_STROKE;
            this.f4126a = cVar.f4126a;
            this.f4127b = cVar.f4127b;
            this.f4137l = cVar.f4137l;
            this.f4128c = cVar.f4128c;
            this.f4129d = cVar.f4129d;
            this.f4130e = cVar.f4130e;
            this.f4133h = cVar.f4133h;
            this.f4132g = cVar.f4132g;
            this.f4138m = cVar.f4138m;
            this.f4135j = cVar.f4135j;
            this.f4144s = cVar.f4144s;
            this.f4142q = cVar.f4142q;
            this.f4146u = cVar.f4146u;
            this.f4136k = cVar.f4136k;
            this.f4139n = cVar.f4139n;
            this.f4140o = cVar.f4140o;
            this.f4141p = cVar.f4141p;
            this.f4143r = cVar.f4143r;
            this.f4145t = cVar.f4145t;
            this.f4131f = cVar.f4131f;
            this.f4147v = cVar.f4147v;
            Rect rect = cVar.f4134i;
            if (rect != null) {
                this.f4134i = new Rect(rect);
            }
        }
    }
}
