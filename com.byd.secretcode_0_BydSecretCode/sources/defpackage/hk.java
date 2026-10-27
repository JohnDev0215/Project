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
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import com.google.android.material.R$attr;
import defpackage.mk;
import defpackage.nk;
import defpackage.ok;
import java.util.BitSet;

/* JADX INFO: compiled from: MaterialShapeDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class hk extends Drawable implements z7, pk {
    public static final String b = hk.class.getSimpleName();
    public static final Paint c = new Paint(1);
    public c d;
    public final ok.g[] e;
    public final ok.g[] f;
    public final BitSet g;
    public boolean h;
    public final Matrix i;
    public final Path j;
    public final Path k;
    public final RectF l;
    public final RectF m;
    public final Region n;
    public final Region o;
    public mk p;
    public final Paint q;
    public final Paint r;
    public final yj s;
    public final nk.a t;
    public final nk u;
    public PorterDuffColorFilter v;
    public PorterDuffColorFilter w;
    public final RectF x;
    public boolean y;

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public class a implements nk.a {
        public a() {
        }

        @Override // nk.a
        public void a(ok okVar, Matrix matrix, int i) {
            hk.this.g.set(i + 4, okVar.e());
            hk.this.f[i] = okVar.f(matrix);
        }

        @Override // nk.a
        public void b(ok okVar, Matrix matrix, int i) {
            hk.this.g.set(i, okVar.e());
            hk.this.e[i] = okVar.f(matrix);
        }
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public class b implements mk.c {
        public final /* synthetic */ float a;

        public b(float f) {
            this.a = f;
        }

        @Override // mk.c
        public ck a(ck ckVar) {
            return ckVar instanceof kk ? ckVar : new bk(this.a, ckVar);
        }
    }

    public /* synthetic */ hk(c cVar, a aVar) {
        this(cVar);
    }

    public static int S(int i, int i2) {
        return (i * (i2 + (i2 >>> 7))) >>> 8;
    }

    public static hk m(Context context, float f) {
        int iB = bi.b(context, R$attr.colorSurface, hk.class.getSimpleName());
        hk hkVar = new hk();
        hkVar.N(context);
        hkVar.X(ColorStateList.valueOf(iB));
        hkVar.W(f);
        return hkVar;
    }

    public int A() {
        return (int) (((double) this.d.s) * Math.sin(Math.toRadians(r0.t)));
    }

    public int B() {
        return (int) (((double) this.d.s) * Math.cos(Math.toRadians(r0.t)));
    }

    public int C() {
        return this.d.r;
    }

    public mk D() {
        return this.d.a;
    }

    public final float E() {
        if (M()) {
            return this.r.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    public ColorStateList F() {
        return this.d.g;
    }

    public float G() {
        return this.d.a.r().a(u());
    }

    public float H() {
        return this.d.a.t().a(u());
    }

    public float I() {
        return this.d.p;
    }

    public float J() {
        return w() + I();
    }

    public final boolean K() {
        c cVar = this.d;
        int i = cVar.q;
        return i != 1 && cVar.r > 0 && (i == 2 || U());
    }

    public final boolean L() {
        Paint.Style style = this.d.v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    public final boolean M() {
        Paint.Style style = this.d.v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.r.getStrokeWidth() > 0.0f;
    }

    public void N(Context context) {
        this.d.b = new qi(context);
        m0();
    }

    public final void O() {
        super.invalidateSelf();
    }

    public boolean P() {
        qi qiVar = this.d.b;
        return qiVar != null && qiVar.d();
    }

    public boolean Q() {
        return this.d.a.u(u());
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
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(((int) this.x.width()) + (this.d.r * 2) + iWidth, ((int) this.x.height()) + (this.d.r * 2) + iHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            float f = (getBounds().left - this.d.r) - iWidth;
            float f2 = (getBounds().top - this.d.r) - iHeight;
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
            int i = this.d.r;
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
        setShapeAppearanceModel(this.d.a.w(f));
    }

    public void W(float f) {
        c cVar = this.d;
        if (cVar.o != f) {
            cVar.o = f;
            m0();
        }
    }

    public void X(ColorStateList colorStateList) {
        c cVar = this.d;
        if (cVar.d != colorStateList) {
            cVar.d = colorStateList;
            onStateChange(getState());
        }
    }

    public void Y(float f) {
        c cVar = this.d;
        if (cVar.k != f) {
            cVar.k = f;
            this.h = true;
            invalidateSelf();
        }
    }

    public void Z(int i, int i2, int i3, int i4) {
        c cVar = this.d;
        if (cVar.i == null) {
            cVar.i = new Rect();
        }
        this.d.i.set(i, i2, i3, i4);
        invalidateSelf();
    }

    public void a0(Paint.Style style) {
        this.d.v = style;
        O();
    }

    public void b0(float f) {
        c cVar = this.d;
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
        this.d.u = false;
        O();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.q.setColorFilter(this.v);
        int alpha = this.q.getAlpha();
        this.q.setAlpha(S(alpha, this.d.m));
        this.r.setColorFilter(this.w);
        this.r.setStrokeWidth(this.d.l);
        int alpha2 = this.r.getAlpha();
        this.r.setAlpha(S(alpha2, this.d.m));
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
        c cVar = this.d;
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
        c cVar = this.d;
        if (cVar.q != i) {
            cVar.q = i;
            O();
        }
    }

    public final void g(RectF rectF, Path path) {
        h(rectF, path);
        if (this.d.j != 1.0f) {
            this.i.reset();
            Matrix matrix = this.i;
            float f = this.d.j;
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
        return this.d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.d.q == 2) {
            return;
        }
        if (Q()) {
            outline.setRoundRect(getBounds(), G() * this.d.k);
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
        Rect rect2 = this.d.i;
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
        nk nkVar = this.u;
        c cVar = this.d;
        nkVar.e(cVar.a, cVar.k, rectF, this.t, path);
    }

    public void h0(float f, ColorStateList colorStateList) {
        j0(f);
        i0(colorStateList);
    }

    public final void i() {
        mk mkVarX = D().x(new b(-E()));
        this.p = mkVarX;
        this.u.d(mkVarX, this.d.k, v(), this.k);
    }

    public void i0(ColorStateList colorStateList) {
        c cVar = this.d;
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
        return super.isStateful() || ((colorStateList = this.d.g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.d.f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.d.e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.d.d) != null && colorStateList4.isStateful())));
    }

    public final PorterDuffColorFilter j(ColorStateList colorStateList, PorterDuff.Mode mode, boolean z) {
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = l(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    public void j0(float f) {
        this.d.l = f;
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
        if (this.d.d == null || color2 == (colorForState2 = this.d.d.getColorForState(iArr, (color2 = this.q.getColor())))) {
            z = false;
        } else {
            this.q.setColor(colorForState2);
            z = true;
        }
        if (this.d.e == null || color == (colorForState = this.d.e.getColorForState(iArr, (color = this.r.getColor())))) {
            return z;
        }
        this.r.setColor(colorForState);
        return true;
    }

    public final int l(int i) {
        float fJ = J() + z();
        qi qiVar = this.d.b;
        return qiVar != null ? qiVar.c(i, fJ) : i;
    }

    public final boolean l0() {
        PorterDuffColorFilter porterDuffColorFilter = this.v;
        PorterDuffColorFilter porterDuffColorFilter2 = this.w;
        c cVar = this.d;
        this.v = k(cVar.g, cVar.h, this.q, true);
        c cVar2 = this.d;
        this.w = k(cVar2.f, cVar2.h, this.r, false);
        c cVar3 = this.d;
        if (cVar3.u) {
            this.s.d(cVar3.g.getColorForState(getState(), 0));
        }
        return (u8.a(porterDuffColorFilter, this.v) && u8.a(porterDuffColorFilter2, this.w)) ? false : true;
    }

    public final void m0() {
        float fJ = J();
        this.d.r = (int) Math.ceil(0.75f * fJ);
        this.d.s = (int) Math.ceil(fJ * 0.25f);
        l0();
        O();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.d = new c(this.d);
        return this;
    }

    public final void n(Canvas canvas) {
        if (this.g.cardinality() > 0) {
            Log.w(b, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.d.s != 0) {
            canvas.drawPath(this.j, this.s.c());
        }
        for (int i = 0; i < 4; i++) {
            this.e[i].b(this.s, this.d.r, canvas);
            this.f[i].b(this.s, this.d.r, canvas);
        }
        if (this.y) {
            int iA = A();
            int iB = B();
            canvas.translate(-iA, -iB);
            canvas.drawPath(this.j, c);
            canvas.translate(iA, iB);
        }
    }

    public final void o(Canvas canvas) {
        q(canvas, this.q, this.j, this.d.a, u());
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.h = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable, hj.b
    public boolean onStateChange(int[] iArr) {
        boolean z = k0(iArr) || l0();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    public void p(Canvas canvas, Paint paint, Path path, RectF rectF) {
        q(canvas, paint, path, this.d.a, rectF);
    }

    public final void q(Canvas canvas, Paint paint, Path path, mk mkVar, RectF rectF) {
        if (!mkVar.u(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = mkVar.t().a(rectF) * this.d.k;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    public final void r(Canvas canvas) {
        q(canvas, this.r, this.k, this.p, v());
    }

    public float s() {
        return this.d.a.j().a(u());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        c cVar = this.d;
        if (cVar.m != i) {
            cVar.m = i;
            O();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.d.c = colorFilter;
        O();
    }

    @Override // defpackage.pk
    public void setShapeAppearanceModel(mk mkVar) {
        this.d.a = mkVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, defpackage.z7
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable, defpackage.z7
    public void setTintList(ColorStateList colorStateList) {
        this.d.g = colorStateList;
        l0();
        O();
    }

    @Override // android.graphics.drawable.Drawable, defpackage.z7
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.d;
        if (cVar.h != mode) {
            cVar.h = mode;
            l0();
            O();
        }
    }

    public float t() {
        return this.d.a.l().a(u());
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
        return this.d.o;
    }

    public ColorStateList x() {
        return this.d.d;
    }

    public float y() {
        return this.d.k;
    }

    public float z() {
        return this.d.n;
    }

    public hk() {
        this(new mk());
    }

    public hk(Context context, AttributeSet attributeSet, int i, int i2) {
        this(mk.e(context, attributeSet, i, i2).m());
    }

    public hk(mk mkVar) {
        this(new c(mkVar, null));
    }

    public hk(c cVar) {
        this.e = new ok.g[4];
        this.f = new ok.g[4];
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
        this.s = new yj();
        this.u = new nk();
        this.x = new RectF();
        this.y = true;
        this.d = cVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = c;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        l0();
        k0(getState());
        this.t = new a();
    }

    /* JADX INFO: compiled from: MaterialShapeDrawable.java */
    public static final class c extends Drawable.ConstantState {
        public mk a;
        public qi b;
        public ColorFilter c;
        public ColorStateList d;
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

        public c(mk mkVar, qi qiVar) {
            this.d = null;
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
            this.a = mkVar;
            this.b = qiVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            hk hkVar = new hk(this, null);
            hkVar.h = true;
            return hkVar;
        }

        public c(c cVar) {
            this.d = null;
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
            this.a = cVar.a;
            this.b = cVar.b;
            this.l = cVar.l;
            this.c = cVar.c;
            this.d = cVar.d;
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
    }
}
