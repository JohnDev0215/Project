package e6;

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
import e6.m;
import e6.n;
import e6.o;
import java.util.BitSet;

/* JADX INFO: compiled from: MaterialShapeDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public class h extends Drawable implements h0.b, p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f4653d = h.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Paint f4654e = new Paint(1);
    public boolean A;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f4655f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final o.g[] f4656g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final o.g[] f4657h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final BitSet f4658i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4659j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Matrix f4660k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Path f4661l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Path f4662m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final RectF f4663n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final RectF f4664o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Region f4665p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Region f4666q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public m f4667r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Paint f4668s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Paint f4669t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final d6.a f4670u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final n.a f4671v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final n f4672w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public PorterDuffColorFilter f4673x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public PorterDuffColorFilter f4674y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final RectF f4675z;

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public class a implements n.a {
        public a() {
        }

        @Override // e6.n.a
        public void a(o oVar, Matrix matrix, int i8) {
            h.this.f4658i.set(i8 + 4, oVar.e());
            h.this.f4657h[i8] = oVar.f(matrix);
        }

        @Override // e6.n.a
        public void b(o oVar, Matrix matrix, int i8) {
            h.this.f4658i.set(i8, oVar.e());
            h.this.f4656g[i8] = oVar.f(matrix);
        }
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public class b implements m.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ float f4677a;

        public b(float f8) {
            this.f4677a = f8;
        }

        @Override // e6.m.c
        public e6.c a(e6.c cVar) {
            return cVar instanceof k ? cVar : new e6.b(this.f4677a, cVar);
        }
    }

    public /* synthetic */ h(c cVar, a aVar) {
        this(cVar);
    }

    public static int S(int i8, int i9) {
        return (i8 * (i9 + (i9 >>> 7))) >>> 8;
    }

    public static h m(Context context, float f8) {
        int iB = t5.a.b(context, R$attr.colorSurface, h.class.getSimpleName());
        h hVar = new h();
        hVar.N(context);
        hVar.X(ColorStateList.valueOf(iB));
        hVar.W(f8);
        return hVar;
    }

    public int A() {
        return (int) (((double) this.f4655f.f4697s) * Math.sin(Math.toRadians(r0.f4698t)));
    }

    public int B() {
        return (int) (((double) this.f4655f.f4697s) * Math.cos(Math.toRadians(r0.f4698t)));
    }

    public int C() {
        return this.f4655f.f4696r;
    }

    public m D() {
        return this.f4655f.f4679a;
    }

    public final float E() {
        if (M()) {
            return this.f4669t.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    public ColorStateList F() {
        return this.f4655f.f4685g;
    }

    public float G() {
        return this.f4655f.f4679a.r().a(u());
    }

    public float H() {
        return this.f4655f.f4679a.t().a(u());
    }

    public float I() {
        return this.f4655f.f4694p;
    }

    public float J() {
        return w() + I();
    }

    public final boolean K() {
        c cVar = this.f4655f;
        int i8 = cVar.f4695q;
        return i8 != 1 && cVar.f4696r > 0 && (i8 == 2 || U());
    }

    public final boolean L() {
        Paint.Style style = this.f4655f.f4700v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    public final boolean M() {
        Paint.Style style = this.f4655f.f4700v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.f4669t.getStrokeWidth() > 0.0f;
    }

    public void N(Context context) {
        this.f4655f.f4680b = new w5.a(context);
        m0();
    }

    public final void O() {
        super.invalidateSelf();
    }

    public boolean P() {
        w5.a aVar = this.f4655f.f4680b;
        return aVar != null && aVar.d();
    }

    public boolean Q() {
        return this.f4655f.f4679a.u(u());
    }

    public final void R(Canvas canvas) {
        if (K()) {
            canvas.save();
            T(canvas);
            if (!this.A) {
                n(canvas);
                canvas.restore();
                return;
            }
            int iWidth = (int) (this.f4675z.width() - getBounds().width());
            int iHeight = (int) (this.f4675z.height() - getBounds().height());
            if (iWidth < 0 || iHeight < 0) {
                throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(((int) this.f4675z.width()) + (this.f4655f.f4696r * 2) + iWidth, ((int) this.f4675z.height()) + (this.f4655f.f4696r * 2) + iHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            float f8 = (getBounds().left - this.f4655f.f4696r) - iWidth;
            float f9 = (getBounds().top - this.f4655f.f4696r) - iHeight;
            canvas2.translate(-f8, -f9);
            n(canvas2);
            canvas.drawBitmap(bitmapCreateBitmap, f8, f9, (Paint) null);
            bitmapCreateBitmap.recycle();
            canvas.restore();
        }
    }

    public final void T(Canvas canvas) {
        int iA = A();
        int iB = B();
        if (Build.VERSION.SDK_INT < 21 && this.A) {
            Rect clipBounds = canvas.getClipBounds();
            int i8 = this.f4655f.f4696r;
            clipBounds.inset(-i8, -i8);
            clipBounds.offset(iA, iB);
            canvas.clipRect(clipBounds, Region.Op.REPLACE);
        }
        canvas.translate(iA, iB);
    }

    public boolean U() {
        int i8 = Build.VERSION.SDK_INT;
        return i8 < 21 || !(Q() || this.f4661l.isConvex() || i8 >= 29);
    }

    public void V(float f8) {
        setShapeAppearanceModel(this.f4655f.f4679a.w(f8));
    }

    public void W(float f8) {
        c cVar = this.f4655f;
        if (cVar.f4693o != f8) {
            cVar.f4693o = f8;
            m0();
        }
    }

    public void X(ColorStateList colorStateList) {
        c cVar = this.f4655f;
        if (cVar.f4682d != colorStateList) {
            cVar.f4682d = colorStateList;
            onStateChange(getState());
        }
    }

    public void Y(float f8) {
        c cVar = this.f4655f;
        if (cVar.f4689k != f8) {
            cVar.f4689k = f8;
            this.f4659j = true;
            invalidateSelf();
        }
    }

    public void Z(int i8, int i9, int i10, int i11) {
        c cVar = this.f4655f;
        if (cVar.f4687i == null) {
            cVar.f4687i = new Rect();
        }
        this.f4655f.f4687i.set(i8, i9, i10, i11);
        invalidateSelf();
    }

    public void a0(Paint.Style style) {
        this.f4655f.f4700v = style;
        O();
    }

    public void b0(float f8) {
        c cVar = this.f4655f;
        if (cVar.f4692n != f8) {
            cVar.f4692n = f8;
            m0();
        }
    }

    public void c0(boolean z7) {
        this.A = z7;
    }

    public void d0(int i8) {
        this.f4670u.d(i8);
        this.f4655f.f4699u = false;
        O();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.f4668s.setColorFilter(this.f4673x);
        int alpha = this.f4668s.getAlpha();
        this.f4668s.setAlpha(S(alpha, this.f4655f.f4691m));
        this.f4669t.setColorFilter(this.f4674y);
        this.f4669t.setStrokeWidth(this.f4655f.f4690l);
        int alpha2 = this.f4669t.getAlpha();
        this.f4669t.setAlpha(S(alpha2, this.f4655f.f4691m));
        if (this.f4659j) {
            i();
            g(u(), this.f4661l);
            this.f4659j = false;
        }
        R(canvas);
        if (L()) {
            o(canvas);
        }
        if (M()) {
            r(canvas);
        }
        this.f4668s.setAlpha(alpha);
        this.f4669t.setAlpha(alpha2);
    }

    public void e0(int i8) {
        c cVar = this.f4655f;
        if (cVar.f4698t != i8) {
            cVar.f4698t = i8;
            O();
        }
    }

    public final PorterDuffColorFilter f(Paint paint, boolean z7) {
        int color;
        int iL;
        if (!z7 || (iL = l((color = paint.getColor()))) == color) {
            return null;
        }
        return new PorterDuffColorFilter(iL, PorterDuff.Mode.SRC_IN);
    }

    public void f0(int i8) {
        c cVar = this.f4655f;
        if (cVar.f4695q != i8) {
            cVar.f4695q = i8;
            O();
        }
    }

    public final void g(RectF rectF, Path path) {
        h(rectF, path);
        if (this.f4655f.f4688j != 1.0f) {
            this.f4660k.reset();
            Matrix matrix = this.f4660k;
            float f8 = this.f4655f.f4688j;
            matrix.setScale(f8, f8, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.f4660k);
        }
        path.computeBounds(this.f4675z, true);
    }

    public void g0(float f8, int i8) {
        j0(f8);
        i0(ColorStateList.valueOf(i8));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f4655f;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.f4655f.f4695q == 2) {
            return;
        }
        if (Q()) {
            outline.setRoundRect(getBounds(), G() * this.f4655f.f4689k);
            return;
        }
        g(u(), this.f4661l);
        if (this.f4661l.isConvex() || Build.VERSION.SDK_INT >= 29) {
            try {
                outline.setConvexPath(this.f4661l);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        Rect rect2 = this.f4655f.f4687i;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        this.f4665p.set(getBounds());
        g(u(), this.f4661l);
        this.f4666q.setPath(this.f4661l, this.f4665p);
        this.f4665p.op(this.f4666q, Region.Op.DIFFERENCE);
        return this.f4665p;
    }

    public final void h(RectF rectF, Path path) {
        n nVar = this.f4672w;
        c cVar = this.f4655f;
        nVar.e(cVar.f4679a, cVar.f4689k, rectF, this.f4671v, path);
    }

    public void h0(float f8, ColorStateList colorStateList) {
        j0(f8);
        i0(colorStateList);
    }

    public final void i() {
        m mVarX = D().x(new b(-E()));
        this.f4667r = mVarX;
        this.f4672w.d(mVarX, this.f4655f.f4689k, v(), this.f4662m);
    }

    public void i0(ColorStateList colorStateList) {
        c cVar = this.f4655f;
        if (cVar.f4683e != colorStateList) {
            cVar.f4683e = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.f4659j = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.f4655f.f4685g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f4655f.f4684f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f4655f.f4683e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f4655f.f4682d) != null && colorStateList4.isStateful())));
    }

    public final PorterDuffColorFilter j(ColorStateList colorStateList, PorterDuff.Mode mode, boolean z7) {
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z7) {
            colorForState = l(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    public void j0(float f8) {
        this.f4655f.f4690l = f8;
        invalidateSelf();
    }

    public final PorterDuffColorFilter k(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z7) {
        return (colorStateList == null || mode == null) ? f(paint, z7) : j(colorStateList, mode, z7);
    }

    public final boolean k0(int[] iArr) {
        boolean z7;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.f4655f.f4682d == null || color2 == (colorForState2 = this.f4655f.f4682d.getColorForState(iArr, (color2 = this.f4668s.getColor())))) {
            z7 = false;
        } else {
            this.f4668s.setColor(colorForState2);
            z7 = true;
        }
        if (this.f4655f.f4683e == null || color == (colorForState = this.f4655f.f4683e.getColorForState(iArr, (color = this.f4669t.getColor())))) {
            return z7;
        }
        this.f4669t.setColor(colorForState);
        return true;
    }

    public final int l(int i8) {
        float fJ = J() + z();
        w5.a aVar = this.f4655f.f4680b;
        return aVar != null ? aVar.c(i8, fJ) : i8;
    }

    public final boolean l0() {
        PorterDuffColorFilter porterDuffColorFilter = this.f4673x;
        PorterDuffColorFilter porterDuffColorFilter2 = this.f4674y;
        c cVar = this.f4655f;
        this.f4673x = k(cVar.f4685g, cVar.f4686h, this.f4668s, true);
        c cVar2 = this.f4655f;
        this.f4674y = k(cVar2.f4684f, cVar2.f4686h, this.f4669t, false);
        c cVar3 = this.f4655f;
        if (cVar3.f4699u) {
            this.f4670u.d(cVar3.f4685g.getColorForState(getState(), 0));
        }
        return (n0.c.a(porterDuffColorFilter, this.f4673x) && n0.c.a(porterDuffColorFilter2, this.f4674y)) ? false : true;
    }

    public final void m0() {
        float fJ = J();
        this.f4655f.f4696r = (int) Math.ceil(0.75f * fJ);
        this.f4655f.f4697s = (int) Math.ceil(fJ * 0.25f);
        l0();
        O();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f4655f = new c(this.f4655f);
        return this;
    }

    public final void n(Canvas canvas) {
        if (this.f4658i.cardinality() > 0) {
            Log.w(f4653d, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.f4655f.f4697s != 0) {
            canvas.drawPath(this.f4661l, this.f4670u.c());
        }
        for (int i8 = 0; i8 < 4; i8++) {
            this.f4656g[i8].b(this.f4670u, this.f4655f.f4696r, canvas);
            this.f4657h[i8].b(this.f4670u, this.f4655f.f4696r, canvas);
        }
        if (this.A) {
            int iA = A();
            int iB = B();
            canvas.translate(-iA, -iB);
            canvas.drawPath(this.f4661l, f4654e);
            canvas.translate(iA, iB);
        }
    }

    public final void o(Canvas canvas) {
        q(canvas, this.f4668s, this.f4661l, this.f4655f.f4679a, u());
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.f4659j = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean z7 = k0(iArr) || l0();
        if (z7) {
            invalidateSelf();
        }
        return z7;
    }

    public void p(Canvas canvas, Paint paint, Path path, RectF rectF) {
        q(canvas, paint, path, this.f4655f.f4679a, rectF);
    }

    public final void q(Canvas canvas, Paint paint, Path path, m mVar, RectF rectF) {
        if (!mVar.u(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = mVar.t().a(rectF) * this.f4655f.f4689k;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    public final void r(Canvas canvas) {
        q(canvas, this.f4669t, this.f4662m, this.f4667r, v());
    }

    public float s() {
        return this.f4655f.f4679a.j().a(u());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        c cVar = this.f4655f;
        if (cVar.f4691m != i8) {
            cVar.f4691m = i8;
            O();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f4655f.f4681c = colorFilter;
        O();
    }

    @Override // e6.p
    public void setShapeAppearanceModel(m mVar) {
        this.f4655f.f4679a = mVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTint(int i8) {
        setTintList(ColorStateList.valueOf(i8));
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTintList(ColorStateList colorStateList) {
        this.f4655f.f4685g = colorStateList;
        l0();
        O();
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f4655f;
        if (cVar.f4686h != mode) {
            cVar.f4686h = mode;
            l0();
            O();
        }
    }

    public float t() {
        return this.f4655f.f4679a.l().a(u());
    }

    public RectF u() {
        this.f4663n.set(getBounds());
        return this.f4663n;
    }

    public final RectF v() {
        this.f4664o.set(u());
        float fE = E();
        this.f4664o.inset(fE, fE);
        return this.f4664o;
    }

    public float w() {
        return this.f4655f.f4693o;
    }

    public ColorStateList x() {
        return this.f4655f.f4682d;
    }

    public float y() {
        return this.f4655f.f4689k;
    }

    public float z() {
        return this.f4655f.f4692n;
    }

    public h() {
        this(new m());
    }

    public h(Context context, AttributeSet attributeSet, int i8, int i9) {
        this(m.e(context, attributeSet, i8, i9).m());
    }

    public h(m mVar) {
        this(new c(mVar, null));
    }

    public h(c cVar) {
        this.f4656g = new o.g[4];
        this.f4657h = new o.g[4];
        this.f4658i = new BitSet(8);
        this.f4660k = new Matrix();
        this.f4661l = new Path();
        this.f4662m = new Path();
        this.f4663n = new RectF();
        this.f4664o = new RectF();
        this.f4665p = new Region();
        this.f4666q = new Region();
        Paint paint = new Paint(1);
        this.f4668s = paint;
        Paint paint2 = new Paint(1);
        this.f4669t = paint2;
        this.f4670u = new d6.a();
        this.f4672w = new n();
        this.f4675z = new RectF();
        this.A = true;
        this.f4655f = cVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = f4654e;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        l0();
        k0(getState());
        this.f4671v = new a();
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public static final class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public m f4679a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public w5.a f4680b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorFilter f4681c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ColorStateList f4682d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ColorStateList f4683e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ColorStateList f4684f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ColorStateList f4685g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public PorterDuff.Mode f4686h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Rect f4687i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f4688j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f4689k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f4690l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f4691m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public float f4692n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public float f4693o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public float f4694p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f4695q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f4696r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f4697s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f4698t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public boolean f4699u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public Paint.Style f4700v;

        public c(m mVar, w5.a aVar) {
            this.f4682d = null;
            this.f4683e = null;
            this.f4684f = null;
            this.f4685g = null;
            this.f4686h = PorterDuff.Mode.SRC_IN;
            this.f4687i = null;
            this.f4688j = 1.0f;
            this.f4689k = 1.0f;
            this.f4691m = 255;
            this.f4692n = 0.0f;
            this.f4693o = 0.0f;
            this.f4694p = 0.0f;
            this.f4695q = 0;
            this.f4696r = 0;
            this.f4697s = 0;
            this.f4698t = 0;
            this.f4699u = false;
            this.f4700v = Paint.Style.FILL_AND_STROKE;
            this.f4679a = mVar;
            this.f4680b = aVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVar = new h(this, null);
            hVar.f4659j = true;
            return hVar;
        }

        public c(c cVar) {
            this.f4682d = null;
            this.f4683e = null;
            this.f4684f = null;
            this.f4685g = null;
            this.f4686h = PorterDuff.Mode.SRC_IN;
            this.f4687i = null;
            this.f4688j = 1.0f;
            this.f4689k = 1.0f;
            this.f4691m = 255;
            this.f4692n = 0.0f;
            this.f4693o = 0.0f;
            this.f4694p = 0.0f;
            this.f4695q = 0;
            this.f4696r = 0;
            this.f4697s = 0;
            this.f4698t = 0;
            this.f4699u = false;
            this.f4700v = Paint.Style.FILL_AND_STROKE;
            this.f4679a = cVar.f4679a;
            this.f4680b = cVar.f4680b;
            this.f4690l = cVar.f4690l;
            this.f4681c = cVar.f4681c;
            this.f4682d = cVar.f4682d;
            this.f4683e = cVar.f4683e;
            this.f4686h = cVar.f4686h;
            this.f4685g = cVar.f4685g;
            this.f4691m = cVar.f4691m;
            this.f4688j = cVar.f4688j;
            this.f4697s = cVar.f4697s;
            this.f4695q = cVar.f4695q;
            this.f4699u = cVar.f4699u;
            this.f4689k = cVar.f4689k;
            this.f4692n = cVar.f4692n;
            this.f4693o = cVar.f4693o;
            this.f4694p = cVar.f4694p;
            this.f4696r = cVar.f4696r;
            this.f4698t = cVar.f4698t;
            this.f4684f = cVar.f4684f;
            this.f4700v = cVar.f4700v;
            Rect rect = cVar.f4687i;
            if (rect != null) {
                this.f4687i = new Rect(rect);
            }
        }
    }
}
