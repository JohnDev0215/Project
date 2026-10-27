package b.d.a.a.e0;

import a.b.k.l;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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
import android.os.Looper;
import android.util.Log;
import b.d.a.a.e0.j;
import b.d.a.a.e0.k;
import b.d.a.a.e0.m;
import java.util.BitSet;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class g extends Drawable implements n {
    public static final String w = g.class.getSimpleName();
    public static final Paint x = new Paint(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f1828a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m.f[] f1829b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m.f[] f1830c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final BitSet f1831d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1832e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Matrix f1833f;
    public final Path g;
    public final Path h;
    public final RectF i;
    public final RectF j;
    public final Region k;
    public final Region l;
    public j m;
    public final Paint n;
    public final Paint o;
    public final b.d.a.a.d0.a p;
    public final k.b q;
    public final k r;
    public PorterDuffColorFilter s;
    public PorterDuffColorFilter t;
    public final RectF u;
    public boolean v;

    public class a implements k.b {
        public a() {
        }
    }

    public static final class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public j f1835a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b.d.a.a.w.a f1836b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorFilter f1837c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ColorStateList f1838d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ColorStateList f1839e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ColorStateList f1840f;
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

        public b(b bVar) {
            this.f1838d = null;
            this.f1839e = null;
            this.f1840f = null;
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
            this.f1835a = bVar.f1835a;
            this.f1836b = bVar.f1836b;
            this.l = bVar.l;
            this.f1837c = bVar.f1837c;
            this.f1838d = bVar.f1838d;
            this.f1839e = bVar.f1839e;
            this.h = bVar.h;
            this.g = bVar.g;
            this.m = bVar.m;
            this.j = bVar.j;
            this.s = bVar.s;
            this.q = bVar.q;
            this.u = bVar.u;
            this.k = bVar.k;
            this.n = bVar.n;
            this.o = bVar.o;
            this.p = bVar.p;
            this.r = bVar.r;
            this.t = bVar.t;
            this.f1840f = bVar.f1840f;
            this.v = bVar.v;
            Rect rect = bVar.i;
            if (rect != null) {
                this.i = new Rect(rect);
            }
        }

        public b(j jVar, b.d.a.a.w.a aVar) {
            this.f1838d = null;
            this.f1839e = null;
            this.f1840f = null;
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
            this.f1835a = jVar;
            this.f1836b = null;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            g gVar = new g(this);
            gVar.f1832e = true;
            return gVar;
        }
    }

    public g() {
        this(new j());
    }

    public g(b bVar) {
        this.f1829b = new m.f[4];
        this.f1830c = new m.f[4];
        this.f1831d = new BitSet(8);
        this.f1833f = new Matrix();
        this.g = new Path();
        this.h = new Path();
        this.i = new RectF();
        this.j = new RectF();
        this.k = new Region();
        this.l = new Region();
        this.n = new Paint(1);
        this.o = new Paint(1);
        this.p = new b.d.a.a.d0.a();
        this.r = Looper.getMainLooper().getThread() == Thread.currentThread() ? k.a.f1861a : new k();
        this.u = new RectF();
        this.v = true;
        this.f1828a = bVar;
        this.o.setStyle(Paint.Style.STROKE);
        this.n.setStyle(Paint.Style.FILL);
        x.setColor(-1);
        x.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        w();
        v(getState());
        this.q = new a();
    }

    public g(j jVar) {
        this(new b(jVar, null));
    }

    public static g f(Context context, float f2) {
        int iJ0 = l.i.j0(context, b.d.a.a.b.colorSurface, g.class.getSimpleName());
        g gVar = new g();
        gVar.f1828a.f1836b = new b.d.a.a.w.a(context);
        gVar.x();
        gVar.q(ColorStateList.valueOf(iJ0));
        b bVar = gVar.f1828a;
        if (bVar.o != f2) {
            bVar.o = f2;
            gVar.x();
        }
        return gVar;
    }

    public final void b(RectF rectF, Path path) {
        c(rectF, path);
        if (this.f1828a.j != 1.0f) {
            this.f1833f.reset();
            Matrix matrix = this.f1833f;
            float f2 = this.f1828a.j;
            matrix.setScale(f2, f2, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.f1833f);
        }
        path.computeBounds(this.u, true);
    }

    public final void c(RectF rectF, Path path) {
        k kVar = this.r;
        b bVar = this.f1828a;
        kVar.a(bVar.f1835a, bVar.k, rectF, this.q, path);
    }

    public final PorterDuffColorFilter d(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z) {
        int color;
        int iE;
        if (colorStateList == null || mode == null) {
            if (!z || (iE = e((color = paint.getColor()))) == color) {
                return null;
            }
            return new PorterDuffColorFilter(iE, PorterDuff.Mode.SRC_IN);
        }
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = e(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z;
        this.n.setColorFilter(this.s);
        int alpha = this.n.getAlpha();
        Paint paint = this.n;
        int i = this.f1828a.m;
        paint.setAlpha(((i + (i >>> 7)) * alpha) >>> 8);
        this.o.setColorFilter(this.t);
        this.o.setStrokeWidth(this.f1828a.l);
        int alpha2 = this.o.getAlpha();
        Paint paint2 = this.o;
        int i2 = this.f1828a.m;
        paint2.setAlpha(((i2 + (i2 >>> 7)) * alpha2) >>> 8);
        if (this.f1832e) {
            float f2 = -l();
            j jVar = this.f1828a.f1835a;
            if (jVar == null) {
                throw null;
            }
            j.b bVar = new j.b(jVar);
            c bVar2 = jVar.f1847e;
            if (!(bVar2 instanceof h)) {
                bVar2 = new b.d.a.a.e0.b(f2, bVar2);
            }
            bVar.f1853e = bVar2;
            c bVar3 = jVar.f1848f;
            if (!(bVar3 instanceof h)) {
                bVar3 = new b.d.a.a.e0.b(f2, bVar3);
            }
            bVar.f1854f = bVar3;
            c bVar4 = jVar.h;
            if (!(bVar4 instanceof h)) {
                bVar4 = new b.d.a.a.e0.b(f2, bVar4);
            }
            bVar.h = bVar4;
            c bVar5 = jVar.g;
            if (!(bVar5 instanceof h)) {
                bVar5 = new b.d.a.a.e0.b(f2, bVar5);
            }
            bVar.g = bVar5;
            j jVarA = bVar.a();
            this.m = jVarA;
            k kVar = this.r;
            float f3 = this.f1828a.k;
            this.j.set(i());
            float fL = l();
            this.j.inset(fL, fL);
            kVar.a(jVarA, f3, this.j, null, this.h);
            b(i(), this.g);
            this.f1832e = false;
        }
        b bVar6 = this.f1828a;
        int i3 = bVar6.q;
        if (i3 == 1 || bVar6.r <= 0) {
            z = false;
        } else if (i3 != 2) {
            if (!bVar6.f1835a.d(i())) {
                this.g.isConvex();
            }
            z = false;
        } else {
            z = true;
        }
        if (z) {
            canvas.save();
            canvas.translate(j(), k());
            if (this.v) {
                int iWidth = (int) (this.u.width() - getBounds().width());
                int iHeight = (int) (this.u.height() - getBounds().height());
                if (iWidth < 0 || iHeight < 0) {
                    throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
                }
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap((this.f1828a.r * 2) + ((int) this.u.width()) + iWidth, (this.f1828a.r * 2) + ((int) this.u.height()) + iHeight, Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(bitmapCreateBitmap);
                float f4 = (getBounds().left - this.f1828a.r) - iWidth;
                float f5 = (getBounds().top - this.f1828a.r) - iHeight;
                canvas2.translate(-f4, -f5);
                g(canvas2);
                canvas.drawBitmap(bitmapCreateBitmap, f4, f5, (Paint) null);
                bitmapCreateBitmap.recycle();
            } else {
                g(canvas);
            }
            canvas.restore();
        }
        Paint.Style style = this.f1828a.v;
        if (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL) {
            h(canvas, this.n, this.g, this.f1828a.f1835a, i());
        }
        if (n()) {
            Paint paint3 = this.o;
            Path path = this.h;
            j jVar2 = this.m;
            this.j.set(i());
            float fL2 = l();
            this.j.inset(fL2, fL2);
            h(canvas, paint3, path, jVar2, this.j);
        }
        this.n.setAlpha(alpha);
        this.o.setAlpha(alpha2);
    }

    public int e(int i) {
        b bVar = this.f1828a;
        float f2 = bVar.o + bVar.p + bVar.n;
        b.d.a.a.w.a aVar = bVar.f1836b;
        if (aVar == null || !aVar.f2129a) {
            return i;
        }
        if (!(a.h.e.a.c(i, 255) == aVar.f2131c)) {
            return i;
        }
        float fMin = 0.0f;
        if (aVar.f2132d > 0.0f && f2 > 0.0f) {
            fMin = Math.min(((((float) Math.log1p(f2 / r2)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
        }
        return a.h.e.a.c(l.i.R(a.h.e.a.c(i, 255), aVar.f2130b, fMin), Color.alpha(i));
    }

    public final void g(Canvas canvas) {
        if (this.f1831d.cardinality() > 0) {
            Log.w(w, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.f1828a.s != 0) {
            canvas.drawPath(this.g, this.p.f1818a);
        }
        for (int i = 0; i < 4; i++) {
            this.f1829b[i].a(m.f.f1882a, this.p, this.f1828a.r, canvas);
            this.f1830c[i].a(m.f.f1882a, this.p, this.f1828a.r, canvas);
        }
        if (this.v) {
            int iJ = j();
            int iK = k();
            canvas.translate(-iJ, -iK);
            canvas.drawPath(this.g, x);
            canvas.translate(iJ, iK);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f1828a;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        b bVar = this.f1828a;
        if (bVar.q == 2) {
            return;
        }
        if (bVar.f1835a.d(i())) {
            outline.setRoundRect(getBounds(), m() * this.f1828a.k);
        } else {
            b(i(), this.g);
            this.g.isConvex();
            try {
                outline.setConvexPath(this.g);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        Rect rect2 = this.f1828a.i;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        this.k.set(getBounds());
        b(i(), this.g);
        this.l.setPath(this.g, this.k);
        this.k.op(this.l, Region.Op.DIFFERENCE);
        return this.k;
    }

    public final void h(Canvas canvas, Paint paint, Path path, j jVar, RectF rectF) {
        if (!jVar.d(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = jVar.f1848f.a(rectF) * this.f1828a.k;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    public RectF i() {
        this.i.set(getBounds());
        return this.i;
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.f1832e = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.f1828a.g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f1828a.f1840f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f1828a.f1839e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f1828a.f1838d) != null && colorStateList4.isStateful())));
    }

    public int j() {
        return (int) (Math.sin(Math.toRadians(r4.t)) * ((double) this.f1828a.s));
    }

    public int k() {
        return (int) (Math.cos(Math.toRadians(r4.t)) * ((double) this.f1828a.s));
    }

    public final float l() {
        if (n()) {
            return this.o.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    public float m() {
        return this.f1828a.f1835a.f1847e.a(i());
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f1828a = new b(this.f1828a);
        return this;
    }

    public final boolean n() {
        Paint.Style style = this.f1828a.v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.o.getStrokeWidth() > 0.0f;
    }

    public void o(Context context) {
        this.f1828a.f1836b = new b.d.a.a.w.a(context);
        x();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.f1832e = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean z = v(iArr) || w();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    public void p(float f2) {
        b bVar = this.f1828a;
        if (bVar.o != f2) {
            bVar.o = f2;
            x();
        }
    }

    public void q(ColorStateList colorStateList) {
        b bVar = this.f1828a;
        if (bVar.f1838d != colorStateList) {
            bVar.f1838d = colorStateList;
            onStateChange(getState());
        }
    }

    public void r(float f2) {
        b bVar = this.f1828a;
        if (bVar.k != f2) {
            bVar.k = f2;
            this.f1832e = true;
            invalidateSelf();
        }
    }

    public void s(float f2, int i) {
        this.f1828a.l = f2;
        invalidateSelf();
        u(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        b bVar = this.f1828a;
        if (bVar.m != i) {
            bVar.m = i;
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1828a.f1837c = colorFilter;
        super.invalidateSelf();
    }

    @Override // b.d.a.a.e0.n
    public void setShapeAppearanceModel(j jVar) {
        this.f1828a.f1835a = jVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f1828a.g = colorStateList;
        w();
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        b bVar = this.f1828a;
        if (bVar.h != mode) {
            bVar.h = mode;
            w();
            super.invalidateSelf();
        }
    }

    public void t(float f2, ColorStateList colorStateList) {
        this.f1828a.l = f2;
        invalidateSelf();
        u(colorStateList);
    }

    public void u(ColorStateList colorStateList) {
        b bVar = this.f1828a;
        if (bVar.f1839e != colorStateList) {
            bVar.f1839e = colorStateList;
            onStateChange(getState());
        }
    }

    public final boolean v(int[] iArr) {
        boolean z;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.f1828a.f1838d == null || color2 == (colorForState2 = this.f1828a.f1838d.getColorForState(iArr, (color2 = this.n.getColor())))) {
            z = false;
        } else {
            this.n.setColor(colorForState2);
            z = true;
        }
        if (this.f1828a.f1839e == null || color == (colorForState = this.f1828a.f1839e.getColorForState(iArr, (color = this.o.getColor())))) {
            return z;
        }
        this.o.setColor(colorForState);
        return true;
    }

    public final boolean w() {
        PorterDuffColorFilter porterDuffColorFilter = this.s;
        PorterDuffColorFilter porterDuffColorFilter2 = this.t;
        b bVar = this.f1828a;
        this.s = d(bVar.g, bVar.h, this.n, true);
        b bVar2 = this.f1828a;
        this.t = d(bVar2.f1840f, bVar2.h, this.o, false);
        b bVar3 = this.f1828a;
        if (bVar3.u) {
            this.p.a(bVar3.g.getColorForState(getState(), 0));
        }
        return (Objects.equals(porterDuffColorFilter, this.s) && Objects.equals(porterDuffColorFilter2, this.t)) ? false : true;
    }

    public final void x() {
        b bVar = this.f1828a;
        float f2 = bVar.o + bVar.p;
        bVar.r = (int) Math.ceil(0.75f * f2);
        this.f1828a.s = (int) Math.ceil(f2 * 0.25f);
        w();
        super.invalidateSelf();
    }
}
