package Y4;

import N1.C0420a;
import android.content.Context;
import android.content.res.ColorStateList;
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
import java.util.BitSet;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class g extends Drawable implements u {

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final Paint f10720F = new Paint(1);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final C0420a f10721A;
    public final m B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public PorterDuffColorFilter f10722C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public PorterDuffColorFilter f10723D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final RectF f10724E;
    public f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final s[] f10725m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final s[] f10726n;
    public final BitSet o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f10727p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Matrix f10728q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Path f10729r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Path f10730s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final RectF f10731t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final RectF f10732u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Region f10733v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final Region f10734w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public k f10735x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Paint f10736y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final Paint f10737z;

    public g() {
        this(new k());
    }

    public final void a(RectF rectF, Path path) {
        f fVar = this.l;
        this.B.b(fVar.f10708a, fVar.f10714i, rectF, this.f10721A, path);
        if (this.l.f10713h != 1.0f) {
            Matrix matrix = this.f10728q;
            matrix.reset();
            float f10 = this.l.f10713h;
            matrix.setScale(f10, f10, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(matrix);
        }
        path.computeBounds(this.f10724E, true);
    }

    public final int b(int i7) {
        f fVar = this.l;
        float fMin = 0.0f;
        float f10 = fVar.f10717m + 0.0f + fVar.l;
        T4.a aVar = fVar.f10709b;
        if (aVar == null || !aVar.f8645a || y1.a.h(i7, 255) != aVar.c) {
            return i7;
        }
        if (aVar.d > 0.0f && f10 > 0.0f) {
            fMin = Math.min(((((float) Math.log1p(f10 / r3)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
        }
        return y1.a.h(com.bumptech.glide.c.q0(y1.a.h(i7, 255), aVar.f8646b, fMin), Color.alpha(i7));
    }

    public final void c(Canvas canvas, Paint paint, Path path, k kVar, RectF rectF) {
        if (!kVar.c(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = kVar.f10751f.a(rectF) * this.l.f10714i;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    public final RectF d() {
        RectF rectF = this.f10731t;
        rectF.set(getBounds());
        return rectF;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        RectF rectF;
        Path path;
        Paint paint = this.f10736y;
        paint.setColorFilter(this.f10722C);
        int alpha = paint.getAlpha();
        int i7 = this.l.f10716k;
        paint.setAlpha(((i7 + (i7 >>> 7)) * alpha) >>> 8);
        Paint paint2 = this.f10737z;
        paint2.setColorFilter(this.f10723D);
        paint2.setStrokeWidth(this.l.f10715j);
        int alpha2 = paint2.getAlpha();
        int i10 = this.l.f10716k;
        paint2.setAlpha(((i10 + (i10 >>> 7)) * alpha2) >>> 8);
        boolean z6 = this.f10727p;
        Path path2 = this.f10730s;
        Path path3 = this.f10729r;
        RectF rectF2 = this.f10732u;
        if (z6) {
            float f10 = -(e() ? paint2.getStrokeWidth() / 2.0f : 0.0f);
            k kVar = this.l.f10708a;
            j jVarD = kVar.d();
            c bVar = kVar.f10750e;
            if (!(bVar instanceof h)) {
                bVar = new b(f10, bVar);
            }
            jVarD.f10741e = bVar;
            c bVar2 = kVar.f10751f;
            if (!(bVar2 instanceof h)) {
                bVar2 = new b(f10, bVar2);
            }
            jVarD.f10742f = bVar2;
            c bVar3 = kVar.f10753h;
            if (!(bVar3 instanceof h)) {
                bVar3 = new b(f10, bVar3);
            }
            jVarD.f10744h = bVar3;
            c bVar4 = kVar.f10752g;
            if (!(bVar4 instanceof h)) {
                bVar4 = new b(f10, bVar4);
            }
            jVarD.f10743g = bVar4;
            k kVarA = jVarD.a();
            this.f10735x = kVarA;
            float f11 = this.l.f10714i;
            rectF2.set(d());
            float strokeWidth = e() ? paint2.getStrokeWidth() / 2.0f : 0.0f;
            rectF2.inset(strokeWidth, strokeWidth);
            this.B.b(kVarA, f11, rectF2, null, path2);
            a(d(), path3);
            this.f10727p = false;
        }
        f fVar = this.l;
        fVar.getClass();
        if (fVar.f10718n > 0 && !this.l.f10708a.c(d())) {
            path3.isConvex();
        }
        f fVar2 = this.l;
        Paint.Style style = fVar2.f10719p;
        if (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL) {
            rectF = rectF2;
            path = path2;
            c(canvas, paint, path3, fVar2.f10708a, d());
        } else {
            rectF = rectF2;
            path = path2;
        }
        if (e()) {
            k kVar2 = this.f10735x;
            rectF.set(d());
            float strokeWidth2 = e() ? paint2.getStrokeWidth() / 2.0f : 0.0f;
            rectF.inset(strokeWidth2, strokeWidth2);
            c(canvas, paint2, path, kVar2, rectF);
        }
        paint.setAlpha(alpha);
        paint2.setAlpha(alpha2);
    }

    public final boolean e() {
        Paint.Style style = this.l.f10719p;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.f10737z.getStrokeWidth() > 0.0f;
    }

    public final void f(Context context) {
        this.l.f10709b = new T4.a(context);
        k();
    }

    public final void g(float f10) {
        f fVar = this.l;
        if (fVar.f10717m != f10) {
            fVar.f10717m = f10;
            k();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.l;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        this.l.getClass();
        if (this.l.f10708a.c(d())) {
            outline.setRoundRect(getBounds(), this.l.f10708a.f10750e.a(d()) * this.l.f10714i);
            return;
        }
        RectF rectFD = d();
        Path path = this.f10729r;
        a(rectFD, path);
        path.isConvex();
        try {
            outline.setConvexPath(path);
        } catch (IllegalArgumentException unused) {
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        Rect rect2 = this.l.f10712g;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Region getTransparentRegion() {
        Rect bounds = getBounds();
        Region region = this.f10733v;
        region.set(bounds);
        RectF rectFD = d();
        Path path = this.f10729r;
        a(rectFD, path);
        Region region2 = this.f10734w;
        region2.setPath(path, region);
        region.op(region2, Region.Op.DIFFERENCE);
        return region;
    }

    public final void h(ColorStateList colorStateList) {
        f fVar = this.l;
        if (fVar.c != colorStateList) {
            fVar.c = colorStateList;
            onStateChange(getState());
        }
    }

    public final boolean i(int[] iArr) {
        boolean z6;
        Paint paint;
        int color;
        int colorForState;
        Paint paint2;
        int color2;
        int colorForState2;
        if (this.l.c == null || color2 == (colorForState2 = this.l.c.getColorForState(iArr, (color2 = (paint2 = this.f10736y).getColor())))) {
            z6 = false;
        } else {
            paint2.setColor(colorForState2);
            z6 = true;
        }
        if (this.l.d == null || color == (colorForState = this.l.d.getColorForState(iArr, (color = (paint = this.f10737z).getColor())))) {
            return z6;
        }
        paint.setColor(colorForState);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        this.f10727p = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        if (!super.isStateful() && ((colorStateList = this.l.f10710e) == null || !colorStateList.isStateful())) {
            this.l.getClass();
            ColorStateList colorStateList3 = this.l.d;
            if ((colorStateList3 == null || !colorStateList3.isStateful()) && ((colorStateList2 = this.l.c) == null || !colorStateList2.isStateful())) {
                return false;
            }
        }
        return true;
    }

    public final boolean j() {
        PorterDuffColorFilter porterDuffColorFilter;
        PorterDuffColorFilter porterDuffColorFilter2 = this.f10722C;
        PorterDuffColorFilter porterDuffColorFilter3 = this.f10723D;
        f fVar = this.l;
        ColorStateList colorStateList = fVar.f10710e;
        PorterDuff.Mode mode = fVar.f10711f;
        Paint paint = this.f10736y;
        if (colorStateList == null || mode == null) {
            int color = paint.getColor();
            int iB = b(color);
            porterDuffColorFilter = iB != color ? new PorterDuffColorFilter(iB, PorterDuff.Mode.SRC_IN) : null;
        } else {
            porterDuffColorFilter = new PorterDuffColorFilter(b(colorStateList.getColorForState(getState(), 0)), mode);
        }
        this.f10722C = porterDuffColorFilter;
        this.l.getClass();
        this.f10723D = null;
        this.l.getClass();
        return (Objects.equals(porterDuffColorFilter2, this.f10722C) && Objects.equals(porterDuffColorFilter3, this.f10723D)) ? false : true;
    }

    public final void k() {
        f fVar = this.l;
        float f10 = fVar.f10717m + 0.0f;
        fVar.f10718n = (int) Math.ceil(0.75f * f10);
        this.l.o = (int) Math.ceil(f10 * 0.25f);
        j();
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        f fVar = this.l;
        f fVar2 = new f();
        fVar2.c = null;
        fVar2.d = null;
        fVar2.f10710e = null;
        fVar2.f10711f = PorterDuff.Mode.SRC_IN;
        fVar2.f10712g = null;
        fVar2.f10713h = 1.0f;
        fVar2.f10714i = 1.0f;
        fVar2.f10716k = 255;
        fVar2.l = 0.0f;
        fVar2.f10717m = 0.0f;
        fVar2.f10718n = 0;
        fVar2.o = 0;
        fVar2.f10719p = Paint.Style.FILL_AND_STROKE;
        fVar2.f10708a = fVar.f10708a;
        fVar2.f10709b = fVar.f10709b;
        fVar2.f10715j = fVar.f10715j;
        fVar2.c = fVar.c;
        fVar2.d = fVar.d;
        fVar2.f10711f = fVar.f10711f;
        fVar2.f10710e = fVar.f10710e;
        fVar2.f10716k = fVar.f10716k;
        fVar2.f10713h = fVar.f10713h;
        fVar2.o = fVar.o;
        fVar2.f10714i = fVar.f10714i;
        fVar2.l = fVar.l;
        fVar2.f10717m = fVar.f10717m;
        fVar2.f10718n = fVar.f10718n;
        fVar2.f10719p = fVar.f10719p;
        if (fVar.f10712g != null) {
            fVar2.f10712g = new Rect(fVar.f10712g);
        }
        this.l = fVar2;
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        this.f10727p = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean z6 = i(iArr) || j();
        if (z6) {
            invalidateSelf();
        }
        return z6;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i7) {
        f fVar = this.l;
        if (fVar.f10716k != i7) {
            fVar.f10716k = i7;
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.l.getClass();
        super.invalidateSelf();
    }

    @Override // Y4.u
    public final void setShapeAppearanceModel(k kVar) {
        this.l.f10708a = kVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i7) {
        setTintList(ColorStateList.valueOf(i7));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.l.f10710e = colorStateList;
        j();
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        f fVar = this.l;
        if (fVar.f10711f != mode) {
            fVar.f10711f = mode;
            j();
            super.invalidateSelf();
        }
    }

    public g(k kVar) {
        f fVar = new f();
        fVar.c = null;
        fVar.d = null;
        fVar.f10710e = null;
        fVar.f10711f = PorterDuff.Mode.SRC_IN;
        fVar.f10712g = null;
        fVar.f10713h = 1.0f;
        fVar.f10714i = 1.0f;
        fVar.f10716k = 255;
        fVar.l = 0.0f;
        fVar.f10717m = 0.0f;
        fVar.f10718n = 0;
        fVar.o = 0;
        fVar.f10719p = Paint.Style.FILL_AND_STROKE;
        fVar.f10708a = kVar;
        fVar.f10709b = null;
        this(fVar);
    }

    public g(f fVar) {
        m mVar;
        this.f10725m = new s[4];
        this.f10726n = new s[4];
        this.o = new BitSet(8);
        this.f10728q = new Matrix();
        this.f10729r = new Path();
        this.f10730s = new Path();
        this.f10731t = new RectF();
        this.f10732u = new RectF();
        this.f10733v = new Region();
        this.f10734w = new Region();
        Paint paint = new Paint(1);
        this.f10736y = paint;
        Paint paint2 = new Paint(1);
        this.f10737z = paint2;
        new X4.a();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            mVar = l.f10757a;
        } else {
            mVar = new m();
        }
        this.B = mVar;
        this.f10724E = new RectF();
        this.l = fVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = f10720F;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        j();
        i(getState());
        this.f10721A = new C0420a(this);
    }
}
