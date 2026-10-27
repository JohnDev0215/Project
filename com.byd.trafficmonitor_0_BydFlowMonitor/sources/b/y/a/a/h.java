package b.y.a.a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import b.h.f.c;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: VectorDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends b.y.a.a.g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final PorterDuff.Mode f3363c = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public C0068h f3364d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public PorterDuffColorFilter f3365e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorFilter f3366f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3367g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3368h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Drawable.ConstantState f3369i;
    public final float[] j;
    public final Matrix k;
    public final Rect l;

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class b extends f {
        public b() {
        }

        @Override // b.y.a.a.h.f
        public boolean c() {
            return true;
        }

        public void e(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (b.h.e.c.g.j(xmlPullParser, "pathData")) {
                TypedArray typedArrayK = b.h.e.c.g.k(resources, theme, attributeSet, b.y.a.a.a.f3339d);
                f(typedArrayK, xmlPullParser);
                typedArrayK.recycle();
            }
        }

        public final void f(TypedArray typedArray, XmlPullParser xmlPullParser) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.f3385b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f3384a = b.h.f.c.d(string2);
            }
            this.f3386c = b.h.e.c.g.g(typedArray, xmlPullParser, "fillType", 2, 0);
        }

        public b(b bVar) {
            super(bVar);
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static abstract class e {
        public e() {
        }

        public boolean a() {
            return false;
        }

        public boolean b(int[] iArr) {
            return false;
        }
    }

    /* JADX INFO: renamed from: b.y.a.a.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class C0068h extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3397a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public g f3398b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorStateList f3399c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public PorterDuff.Mode f3400d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f3401e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Bitmap f3402f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ColorStateList f3403g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public PorterDuff.Mode f3404h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f3405i;
        public boolean j;
        public boolean k;
        public Paint l;

        public C0068h(C0068h c0068h) {
            this.f3399c = null;
            this.f3400d = h.f3363c;
            if (c0068h != null) {
                this.f3397a = c0068h.f3397a;
                g gVar = new g(c0068h.f3398b);
                this.f3398b = gVar;
                Paint paint = c0068h.f3398b.f3393f;
                if (paint != null) {
                    gVar.f3393f = new Paint(paint);
                }
                Paint paint2 = c0068h.f3398b.f3392e;
                if (paint2 != null) {
                    this.f3398b.f3392e = new Paint(paint2);
                }
                this.f3399c = c0068h.f3399c;
                this.f3400d = c0068h.f3400d;
                this.f3401e = c0068h.f3401e;
            }
        }

        public boolean a(int i2, int i3) {
            return i2 == this.f3402f.getWidth() && i3 == this.f3402f.getHeight();
        }

        public boolean b() {
            return !this.k && this.f3403g == this.f3399c && this.f3404h == this.f3400d && this.j == this.f3401e && this.f3405i == this.f3398b.getRootAlpha();
        }

        public void c(int i2, int i3) {
            if (this.f3402f == null || !a(i2, i3)) {
                this.f3402f = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
                this.k = true;
            }
        }

        public void d(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f3402f, (Rect) null, rect, e(colorFilter));
        }

        public Paint e(ColorFilter colorFilter) {
            if (!f() && colorFilter == null) {
                return null;
            }
            if (this.l == null) {
                Paint paint = new Paint();
                this.l = paint;
                paint.setFilterBitmap(true);
            }
            this.l.setAlpha(this.f3398b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public boolean f() {
            return this.f3398b.getRootAlpha() < 255;
        }

        public boolean g() {
            return this.f3398b.f();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f3397a;
        }

        public boolean h(int[] iArr) {
            boolean zG = this.f3398b.g(iArr);
            this.k |= zG;
            return zG;
        }

        public void i() {
            this.f3403g = this.f3399c;
            this.f3404h = this.f3400d;
            this.f3405i = this.f3398b.getRootAlpha();
            this.j = this.f3401e;
            this.k = false;
        }

        public void j(int i2, int i3) {
            this.f3402f.eraseColor(0);
            this.f3398b.b(new Canvas(this.f3402f), i2, i3, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new h(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new h(this);
        }

        public C0068h() {
            this.f3399c = null;
            this.f3400d = h.f3363c;
            this.f3398b = new g();
        }
    }

    public h() {
        this.f3368h = true;
        this.j = new float[9];
        this.k = new Matrix();
        this.l = new Rect();
        this.f3364d = new C0068h();
    }

    public static int a(int i2, float f2) {
        return (i2 & 16777215) | (((int) (Color.alpha(i2) * f2)) << 24);
    }

    public static h b(Resources resources, int i2, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            h hVar = new h();
            Drawable drawableA = b.h.e.c.f.a(resources, i2, theme);
            hVar.f3362b = drawableA;
            hVar.f3369i = new i(drawableA.getConstantState());
            return hVar;
        }
        try {
            XmlResourceParser xml = resources.getXml(i2);
            AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next == 2) {
                return c(resources, xml, attributeSetAsAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException e2) {
            Log.e("VectorDrawableCompat", "parser error", e2);
            return null;
        } catch (XmlPullParserException e3) {
            Log.e("VectorDrawableCompat", "parser error", e3);
            return null;
        }
    }

    public static h c(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        h hVar = new h();
        hVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return hVar;
    }

    public static PorterDuff.Mode g(int i2, PorterDuff.Mode mode) {
        if (i2 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i2 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i2 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i2) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f3362b;
        if (drawable == null) {
            return false;
        }
        b.h.f.l.a.b(drawable);
        return false;
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public Object d(String str) {
        return this.f3364d.f3398b.q.get(str);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.l);
        if (this.l.width() <= 0 || this.l.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f3366f;
        if (colorFilter == null) {
            colorFilter = this.f3365e;
        }
        canvas.getMatrix(this.k);
        this.k.getValues(this.j);
        float fAbs = Math.abs(this.j[0]);
        float fAbs2 = Math.abs(this.j[4]);
        float fAbs3 = Math.abs(this.j[1]);
        float fAbs4 = Math.abs(this.j[3]);
        if (fAbs3 != 0.0f || fAbs4 != 0.0f) {
            fAbs = 1.0f;
            fAbs2 = 1.0f;
        }
        int iMin = Math.min(2048, (int) (this.l.width() * fAbs));
        int iMin2 = Math.min(2048, (int) (this.l.height() * fAbs2));
        if (iMin <= 0 || iMin2 <= 0) {
            return;
        }
        int iSave = canvas.save();
        Rect rect = this.l;
        canvas.translate(rect.left, rect.top);
        if (f()) {
            canvas.translate(this.l.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.l.offsetTo(0, 0);
        this.f3364d.c(iMin, iMin2);
        if (!this.f3368h) {
            this.f3364d.j(iMin, iMin2);
        } else if (!this.f3364d.b()) {
            this.f3364d.j(iMin, iMin2);
            this.f3364d.i();
        }
        this.f3364d.d(canvas, colorFilter, this.l);
        canvas.restoreToCount(iSave);
    }

    public final void e(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        C0068h c0068h = this.f3364d;
        g gVar = c0068h.f3398b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(gVar.f3396i);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                d dVar = (d) arrayDeque.peek();
                if ("path".equals(name)) {
                    c cVar = new c();
                    cVar.g(resources, attributeSet, theme, xmlPullParser);
                    dVar.f3376b.add(cVar);
                    if (cVar.getPathName() != null) {
                        gVar.q.put(cVar.getPathName(), cVar);
                    }
                    z = false;
                    c0068h.f3397a = cVar.f3387d | c0068h.f3397a;
                } else if ("clip-path".equals(name)) {
                    b bVar = new b();
                    bVar.e(resources, attributeSet, theme, xmlPullParser);
                    dVar.f3376b.add(bVar);
                    if (bVar.getPathName() != null) {
                        gVar.q.put(bVar.getPathName(), bVar);
                    }
                    c0068h.f3397a = bVar.f3387d | c0068h.f3397a;
                } else if ("group".equals(name)) {
                    d dVar2 = new d();
                    dVar2.c(resources, attributeSet, theme, xmlPullParser);
                    dVar.f3376b.add(dVar2);
                    arrayDeque.push(dVar2);
                    if (dVar2.getGroupName() != null) {
                        gVar.q.put(dVar2.getGroupName(), dVar2);
                    }
                    c0068h.f3397a = dVar2.k | c0068h.f3397a;
                }
            } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z) {
            throw new XmlPullParserException("no path defined");
        }
    }

    public final boolean f() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && b.h.f.l.a.f(this) == 1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f3362b;
        return drawable != null ? b.h.f.l.a.d(drawable) : this.f3364d.f3398b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f3362b;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f3364d.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f3362b;
        return drawable != null ? b.h.f.l.a.e(drawable) : this.f3366f;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f3362b;
        if (drawable != null && Build.VERSION.SDK_INT >= 24) {
            return new i(drawable.getConstantState());
        }
        this.f3364d.f3397a = getChangingConfigurations();
        return this.f3364d;
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f3362b;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f3364d.f3398b.k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f3362b;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f3364d.f3398b.j;
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void h(boolean z) {
        this.f3368h = z;
    }

    public final void i(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) throws XmlPullParserException {
        C0068h c0068h = this.f3364d;
        g gVar = c0068h.f3398b;
        c0068h.f3400d = g(b.h.e.c.g.g(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateListC = b.h.e.c.g.c(typedArray, xmlPullParser, theme, "tint", 1);
        if (colorStateListC != null) {
            c0068h.f3399c = colorStateListC;
        }
        c0068h.f3401e = b.h.e.c.g.a(typedArray, xmlPullParser, "autoMirrored", 5, c0068h.f3401e);
        gVar.l = b.h.e.c.g.f(typedArray, xmlPullParser, "viewportWidth", 7, gVar.l);
        float f2 = b.h.e.c.g.f(typedArray, xmlPullParser, "viewportHeight", 8, gVar.m);
        gVar.m = f2;
        if (gVar.l <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (f2 <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
        gVar.j = typedArray.getDimension(3, gVar.j);
        float dimension = typedArray.getDimension(2, gVar.k);
        gVar.k = dimension;
        if (gVar.j <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
        }
        if (dimension <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
        }
        gVar.setAlpha(b.h.e.c.g.f(typedArray, xmlPullParser, "alpha", 4, gVar.getAlpha()));
        String string = typedArray.getString(0);
        if (string != null) {
            gVar.o = string;
            gVar.q.put(string, gVar);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f3362b;
        return drawable != null ? b.h.f.l.a.h(drawable) : this.f3364d.f3401e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        C0068h c0068h;
        ColorStateList colorStateList;
        Drawable drawable = this.f3362b;
        return drawable != null ? drawable.isStateful() : super.isStateful() || ((c0068h = this.f3364d) != null && (c0068h.g() || ((colorStateList = this.f3364d.f3399c) != null && colorStateList.isStateful())));
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f3367g && super.mutate() == this) {
            this.f3364d = new C0068h(this.f3364d);
            this.f3367g = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        C0068h c0068h = this.f3364d;
        ColorStateList colorStateList = c0068h.f3399c;
        if (colorStateList != null && (mode = c0068h.f3400d) != null) {
            this.f3365e = updateTintFilter(this.f3365e, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (!c0068h.g() || !c0068h.h(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else if (this.f3364d.f3398b.getRootAlpha() != i2) {
            this.f3364d.f3398b.setRootAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            b.h.f.l.a.j(drawable, z);
        } else {
            this.f3364d.f3401e = z;
        }
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i2) {
        super.setChangingConfigurations(i2);
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i2, PorterDuff.Mode mode) {
        super.setColorFilter(i2, mode);
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i2, int i3, int i4, int i5) {
        super.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTint(int i2) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            b.h.f.l.a.n(drawable, i2);
        } else {
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            b.h.f.l.a.o(drawable, colorStateList);
            return;
        }
        C0068h c0068h = this.f3364d;
        if (c0068h.f3399c != colorStateList) {
            c0068h.f3399c = colorStateList;
            this.f3365e = updateTintFilter(this.f3365e, colorStateList, c0068h.f3400d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            b.h.f.l.a.p(drawable, mode);
            return;
        }
        C0068h c0068h = this.f3364d;
        if (c0068h.f3400d != mode) {
            c0068h.f3400d = mode;
            this.f3365e = updateTintFilter(this.f3365e, c0068h.f3399c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f3362b;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    public PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class i extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Drawable.ConstantState f3406a;

        public i(Drawable.ConstantState constantState) {
            this.f3406a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f3406a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f3406a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVar = new h();
            hVar.f3362b = (VectorDrawable) this.f3406a.newDrawable();
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            h hVar = new h();
            hVar.f3362b = (VectorDrawable) this.f3406a.newDrawable(resources);
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            h hVar = new h();
            hVar.f3362b = (VectorDrawable) this.f3406a.newDrawable(resources, theme);
            return hVar;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f3366f = colorFilter;
            invalidateSelf();
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static abstract class f extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c.b[] f3384a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3385b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3386c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3387d;

        public f() {
            super();
            this.f3384a = null;
            this.f3386c = 0;
        }

        public boolean c() {
            return false;
        }

        public void d(Path path) {
            path.reset();
            c.b[] bVarArr = this.f3384a;
            if (bVarArr != null) {
                c.b.e(bVarArr, path);
            }
        }

        public c.b[] getPathData() {
            return this.f3384a;
        }

        public String getPathName() {
            return this.f3385b;
        }

        public void setPathData(c.b[] bVarArr) {
            if (b.h.f.c.b(this.f3384a, bVarArr)) {
                b.h.f.c.j(this.f3384a, bVarArr);
            } else {
                this.f3384a = b.h.f.c.f(bVarArr);
            }
        }

        public f(f fVar) {
            super();
            this.f3384a = null;
            this.f3386c = 0;
            this.f3385b = fVar.f3385b;
            this.f3387d = fVar.f3387d;
            this.f3384a = b.h.f.c.f(fVar.f3384a);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            b.h.f.l.a.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        C0068h c0068h = this.f3364d;
        c0068h.f3398b = new g();
        TypedArray typedArrayK = b.h.e.c.g.k(resources, theme, attributeSet, b.y.a.a.a.f3336a);
        i(typedArrayK, xmlPullParser, theme);
        typedArrayK.recycle();
        c0068h.f3397a = getChangingConfigurations();
        c0068h.k = true;
        e(resources, xmlPullParser, attributeSet, theme);
        this.f3365e = updateTintFilter(this.f3365e, c0068h.f3399c, c0068h.f3400d);
    }

    public h(C0068h c0068h) {
        this.f3368h = true;
        this.j = new float[9];
        this.k = new Matrix();
        this.l = new Rect();
        this.f3364d = c0068h;
        this.f3365e = updateTintFilter(this.f3365e, c0068h.f3399c, c0068h.f3400d);
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class c extends f {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int[] f3370e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b.h.e.c.b f3371f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f3372g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public b.h.e.c.b f3373h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f3374i;
        public float j;
        public float k;
        public float l;
        public float m;
        public Paint.Cap n;
        public Paint.Join o;
        public float p;

        public c() {
            this.f3372g = 0.0f;
            this.f3374i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
        }

        @Override // b.y.a.a.h.e
        public boolean a() {
            return this.f3373h.i() || this.f3371f.i();
        }

        @Override // b.y.a.a.h.e
        public boolean b(int[] iArr) {
            return this.f3371f.j(iArr) | this.f3373h.j(iArr);
        }

        public final Paint.Cap e(int i2, Paint.Cap cap) {
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        public final Paint.Join f(int i2, Paint.Join join) {
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? join : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER;
        }

        public void g(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = b.h.e.c.g.k(resources, theme, attributeSet, b.y.a.a.a.f3338c);
            h(typedArrayK, xmlPullParser, theme);
            typedArrayK.recycle();
        }

        public float getFillAlpha() {
            return this.j;
        }

        public int getFillColor() {
            return this.f3373h.e();
        }

        public float getStrokeAlpha() {
            return this.f3374i;
        }

        public int getStrokeColor() {
            return this.f3371f.e();
        }

        public float getStrokeWidth() {
            return this.f3372g;
        }

        public float getTrimPathEnd() {
            return this.l;
        }

        public float getTrimPathOffset() {
            return this.m;
        }

        public float getTrimPathStart() {
            return this.k;
        }

        public final void h(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.f3370e = null;
            if (b.h.e.c.g.j(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f3385b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f3384a = b.h.f.c.d(string2);
                }
                this.f3373h = b.h.e.c.g.e(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.j = b.h.e.c.g.f(typedArray, xmlPullParser, "fillAlpha", 12, this.j);
                this.n = e(b.h.e.c.g.g(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.n);
                this.o = f(b.h.e.c.g.g(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.o);
                this.p = b.h.e.c.g.f(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.p);
                this.f3371f = b.h.e.c.g.e(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.f3374i = b.h.e.c.g.f(typedArray, xmlPullParser, "strokeAlpha", 11, this.f3374i);
                this.f3372g = b.h.e.c.g.f(typedArray, xmlPullParser, "strokeWidth", 4, this.f3372g);
                this.l = b.h.e.c.g.f(typedArray, xmlPullParser, "trimPathEnd", 6, this.l);
                this.m = b.h.e.c.g.f(typedArray, xmlPullParser, "trimPathOffset", 7, this.m);
                this.k = b.h.e.c.g.f(typedArray, xmlPullParser, "trimPathStart", 5, this.k);
                this.f3386c = b.h.e.c.g.g(typedArray, xmlPullParser, "fillType", 13, this.f3386c);
            }
        }

        public void setFillAlpha(float f2) {
            this.j = f2;
        }

        public void setFillColor(int i2) {
            this.f3373h.k(i2);
        }

        public void setStrokeAlpha(float f2) {
            this.f3374i = f2;
        }

        public void setStrokeColor(int i2) {
            this.f3371f.k(i2);
        }

        public void setStrokeWidth(float f2) {
            this.f3372g = f2;
        }

        public void setTrimPathEnd(float f2) {
            this.l = f2;
        }

        public void setTrimPathOffset(float f2) {
            this.m = f2;
        }

        public void setTrimPathStart(float f2) {
            this.k = f2;
        }

        public c(c cVar) {
            super(cVar);
            this.f3372g = 0.0f;
            this.f3374i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
            this.f3370e = cVar.f3370e;
            this.f3371f = cVar.f3371f;
            this.f3372g = cVar.f3372g;
            this.f3374i = cVar.f3374i;
            this.f3373h = cVar.f3373h;
            this.f3386c = cVar.f3386c;
            this.j = cVar.j;
            this.k = cVar.k;
            this.l = cVar.l;
            this.m = cVar.m;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Matrix f3388a = new Matrix();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f3389b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Path f3390c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Matrix f3391d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Paint f3392e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Paint f3393f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public PathMeasure f3394g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f3395h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final d f3396i;
        public float j;
        public float k;
        public float l;
        public float m;
        public int n;
        public String o;
        public Boolean p;
        public final b.e.a<String, Object> q;

        public g() {
            this.f3391d = new Matrix();
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 255;
            this.o = null;
            this.p = null;
            this.q = new b.e.a<>();
            this.f3396i = new d();
            this.f3389b = new Path();
            this.f3390c = new Path();
        }

        public static float a(float f2, float f3, float f4, float f5) {
            return (f2 * f5) - (f3 * f4);
        }

        public void b(Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            c(this.f3396i, f3388a, canvas, i2, i3, colorFilter);
        }

        public final void c(d dVar, Matrix matrix, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            dVar.f3375a.set(matrix);
            dVar.f3375a.preConcat(dVar.j);
            canvas.save();
            for (int i4 = 0; i4 < dVar.f3376b.size(); i4++) {
                e eVar = dVar.f3376b.get(i4);
                if (eVar instanceof d) {
                    c((d) eVar, dVar.f3375a, canvas, i2, i3, colorFilter);
                } else if (eVar instanceof f) {
                    d(dVar, (f) eVar, canvas, i2, i3, colorFilter);
                }
            }
            canvas.restore();
        }

        public final void d(d dVar, f fVar, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            float f2 = i2 / this.l;
            float f3 = i3 / this.m;
            float fMin = Math.min(f2, f3);
            Matrix matrix = dVar.f3375a;
            this.f3391d.set(matrix);
            this.f3391d.postScale(f2, f3);
            float fE = e(matrix);
            if (fE == 0.0f) {
                return;
            }
            fVar.d(this.f3389b);
            Path path = this.f3389b;
            this.f3390c.reset();
            if (fVar.c()) {
                this.f3390c.setFillType(fVar.f3386c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.f3390c.addPath(path, this.f3391d);
                canvas.clipPath(this.f3390c);
                return;
            }
            c cVar = (c) fVar;
            float f4 = cVar.k;
            if (f4 != 0.0f || cVar.l != 1.0f) {
                float f5 = cVar.m;
                float f6 = (f4 + f5) % 1.0f;
                float f7 = (cVar.l + f5) % 1.0f;
                if (this.f3394g == null) {
                    this.f3394g = new PathMeasure();
                }
                this.f3394g.setPath(this.f3389b, false);
                float length = this.f3394g.getLength();
                float f8 = f6 * length;
                float f9 = f7 * length;
                path.reset();
                if (f8 > f9) {
                    this.f3394g.getSegment(f8, length, path, true);
                    this.f3394g.getSegment(0.0f, f9, path, true);
                } else {
                    this.f3394g.getSegment(f8, f9, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.f3390c.addPath(path, this.f3391d);
            if (cVar.f3373h.l()) {
                b.h.e.c.b bVar = cVar.f3373h;
                if (this.f3393f == null) {
                    Paint paint = new Paint(1);
                    this.f3393f = paint;
                    paint.setStyle(Paint.Style.FILL);
                }
                Paint paint2 = this.f3393f;
                if (bVar.h()) {
                    Shader shaderF = bVar.f();
                    shaderF.setLocalMatrix(this.f3391d);
                    paint2.setShader(shaderF);
                    paint2.setAlpha(Math.round(cVar.j * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(h.a(bVar.e(), cVar.j));
                }
                paint2.setColorFilter(colorFilter);
                this.f3390c.setFillType(cVar.f3386c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.f3390c, paint2);
            }
            if (cVar.f3371f.l()) {
                b.h.e.c.b bVar2 = cVar.f3371f;
                if (this.f3392e == null) {
                    Paint paint3 = new Paint(1);
                    this.f3392e = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                }
                Paint paint4 = this.f3392e;
                Paint.Join join = cVar.o;
                if (join != null) {
                    paint4.setStrokeJoin(join);
                }
                Paint.Cap cap = cVar.n;
                if (cap != null) {
                    paint4.setStrokeCap(cap);
                }
                paint4.setStrokeMiter(cVar.p);
                if (bVar2.h()) {
                    Shader shaderF2 = bVar2.f();
                    shaderF2.setLocalMatrix(this.f3391d);
                    paint4.setShader(shaderF2);
                    paint4.setAlpha(Math.round(cVar.f3374i * 255.0f));
                } else {
                    paint4.setShader(null);
                    paint4.setAlpha(255);
                    paint4.setColor(h.a(bVar2.e(), cVar.f3374i));
                }
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(cVar.f3372g * fMin * fE);
                canvas.drawPath(this.f3390c, paint4);
            }
        }

        public final float e(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float fHypot = (float) Math.hypot(fArr[0], fArr[1]);
            float fHypot2 = (float) Math.hypot(fArr[2], fArr[3]);
            float fA = a(fArr[0], fArr[1], fArr[2], fArr[3]);
            float fMax = Math.max(fHypot, fHypot2);
            if (fMax > 0.0f) {
                return Math.abs(fA) / fMax;
            }
            return 0.0f;
        }

        public boolean f() {
            if (this.p == null) {
                this.p = Boolean.valueOf(this.f3396i.a());
            }
            return this.p.booleanValue();
        }

        public boolean g(int[] iArr) {
            return this.f3396i.b(iArr);
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.n;
        }

        public void setAlpha(float f2) {
            setRootAlpha((int) (f2 * 255.0f));
        }

        public void setRootAlpha(int i2) {
            this.n = i2;
        }

        public g(g gVar) {
            this.f3391d = new Matrix();
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 255;
            this.o = null;
            this.p = null;
            b.e.a<String, Object> aVar = new b.e.a<>();
            this.q = aVar;
            this.f3396i = new d(gVar.f3396i, aVar);
            this.f3389b = new Path(gVar.f3389b);
            this.f3390c = new Path(gVar.f3390c);
            this.j = gVar.j;
            this.k = gVar.k;
            this.l = gVar.l;
            this.m = gVar.m;
            this.f3395h = gVar.f3395h;
            this.n = gVar.n;
            this.o = gVar.o;
            String str = gVar.o;
            if (str != null) {
                aVar.put(str, this);
            }
            this.p = gVar.p;
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class d extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f3375a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ArrayList<e> f3376b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f3377c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f3378d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f3379e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f3380f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f3381g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f3382h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f3383i;
        public final Matrix j;
        public int k;
        public int[] l;
        public String m;

        public d(d dVar, b.e.a<String, Object> aVar) {
            f bVar;
            super();
            this.f3375a = new Matrix();
            this.f3376b = new ArrayList<>();
            this.f3377c = 0.0f;
            this.f3378d = 0.0f;
            this.f3379e = 0.0f;
            this.f3380f = 1.0f;
            this.f3381g = 1.0f;
            this.f3382h = 0.0f;
            this.f3383i = 0.0f;
            Matrix matrix = new Matrix();
            this.j = matrix;
            this.m = null;
            this.f3377c = dVar.f3377c;
            this.f3378d = dVar.f3378d;
            this.f3379e = dVar.f3379e;
            this.f3380f = dVar.f3380f;
            this.f3381g = dVar.f3381g;
            this.f3382h = dVar.f3382h;
            this.f3383i = dVar.f3383i;
            this.l = dVar.l;
            String str = dVar.m;
            this.m = str;
            this.k = dVar.k;
            if (str != null) {
                aVar.put(str, this);
            }
            matrix.set(dVar.j);
            ArrayList<e> arrayList = dVar.f3376b;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                e eVar = arrayList.get(i2);
                if (eVar instanceof d) {
                    this.f3376b.add(new d((d) eVar, aVar));
                } else {
                    if (eVar instanceof c) {
                        bVar = new c((c) eVar);
                    } else {
                        if (!(eVar instanceof b)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        bVar = new b((b) eVar);
                    }
                    this.f3376b.add(bVar);
                    String str2 = bVar.f3385b;
                    if (str2 != null) {
                        aVar.put(str2, bVar);
                    }
                }
            }
        }

        @Override // b.y.a.a.h.e
        public boolean a() {
            for (int i2 = 0; i2 < this.f3376b.size(); i2++) {
                if (this.f3376b.get(i2).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // b.y.a.a.h.e
        public boolean b(int[] iArr) {
            boolean zB = false;
            for (int i2 = 0; i2 < this.f3376b.size(); i2++) {
                zB |= this.f3376b.get(i2).b(iArr);
            }
            return zB;
        }

        public void c(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = b.h.e.c.g.k(resources, theme, attributeSet, b.y.a.a.a.f3337b);
            e(typedArrayK, xmlPullParser);
            typedArrayK.recycle();
        }

        public final void d() {
            this.j.reset();
            this.j.postTranslate(-this.f3378d, -this.f3379e);
            this.j.postScale(this.f3380f, this.f3381g);
            this.j.postRotate(this.f3377c, 0.0f, 0.0f);
            this.j.postTranslate(this.f3382h + this.f3378d, this.f3383i + this.f3379e);
        }

        public final void e(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.l = null;
            this.f3377c = b.h.e.c.g.f(typedArray, xmlPullParser, "rotation", 5, this.f3377c);
            this.f3378d = typedArray.getFloat(1, this.f3378d);
            this.f3379e = typedArray.getFloat(2, this.f3379e);
            this.f3380f = b.h.e.c.g.f(typedArray, xmlPullParser, "scaleX", 3, this.f3380f);
            this.f3381g = b.h.e.c.g.f(typedArray, xmlPullParser, "scaleY", 4, this.f3381g);
            this.f3382h = b.h.e.c.g.f(typedArray, xmlPullParser, "translateX", 6, this.f3382h);
            this.f3383i = b.h.e.c.g.f(typedArray, xmlPullParser, "translateY", 7, this.f3383i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.m = string;
            }
            d();
        }

        public String getGroupName() {
            return this.m;
        }

        public Matrix getLocalMatrix() {
            return this.j;
        }

        public float getPivotX() {
            return this.f3378d;
        }

        public float getPivotY() {
            return this.f3379e;
        }

        public float getRotation() {
            return this.f3377c;
        }

        public float getScaleX() {
            return this.f3380f;
        }

        public float getScaleY() {
            return this.f3381g;
        }

        public float getTranslateX() {
            return this.f3382h;
        }

        public float getTranslateY() {
            return this.f3383i;
        }

        public void setPivotX(float f2) {
            if (f2 != this.f3378d) {
                this.f3378d = f2;
                d();
            }
        }

        public void setPivotY(float f2) {
            if (f2 != this.f3379e) {
                this.f3379e = f2;
                d();
            }
        }

        public void setRotation(float f2) {
            if (f2 != this.f3377c) {
                this.f3377c = f2;
                d();
            }
        }

        public void setScaleX(float f2) {
            if (f2 != this.f3380f) {
                this.f3380f = f2;
                d();
            }
        }

        public void setScaleY(float f2) {
            if (f2 != this.f3381g) {
                this.f3381g = f2;
                d();
            }
        }

        public void setTranslateX(float f2) {
            if (f2 != this.f3382h) {
                this.f3382h = f2;
                d();
            }
        }

        public void setTranslateY(float f2) {
            if (f2 != this.f3383i) {
                this.f3383i = f2;
                d();
            }
        }

        public d() {
            super();
            this.f3375a = new Matrix();
            this.f3376b = new ArrayList<>();
            this.f3377c = 0.0f;
            this.f3378d = 0.0f;
            this.f3379e = 0.0f;
            this.f3380f = 1.0f;
            this.f3381g = 1.0f;
            this.f3382h = 0.0f;
            this.f3383i = 0.0f;
            this.j = new Matrix();
            this.m = null;
        }
    }
}
