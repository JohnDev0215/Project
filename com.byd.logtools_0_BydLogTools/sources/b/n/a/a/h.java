package b.n.a.a;

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
import b.g.d.c;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: VectorDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends b.n.a.a.g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final PorterDuff.Mode f2816c = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private C0052h f2817d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private PorterDuffColorFilter f2818e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ColorFilter f2819f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2820g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2821h;
    private Drawable.ConstantState i;
    private final float[] j;
    private final Matrix k;
    private final Rect l;

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static class b extends f {
        b() {
        }

        private void f(TypedArray typedArray, XmlPullParser xmlPullParser) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.f2835b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f2834a = b.g.d.c.d(string2);
            }
            this.f2836c = androidx.core.content.c.g.g(typedArray, xmlPullParser, "fillType", 2, 0);
        }

        @Override // b.n.a.a.h.f
        public boolean c() {
            return true;
        }

        public void e(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (androidx.core.content.c.g.j(xmlPullParser, "pathData")) {
                TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, b.n.a.a.a.f2793d);
                f(typedArrayK, xmlPullParser);
                typedArrayK.recycle();
            }
        }

        b(b bVar) {
            super(bVar);
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static abstract class e {
        private e() {
        }

        public boolean a() {
            return false;
        }

        public boolean b(int[] iArr) {
            return false;
        }
    }

    /* JADX INFO: renamed from: b.n.a.a.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static class C0052h extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2846a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        g f2847b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ColorStateList f2848c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        PorterDuff.Mode f2849d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2850e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        Bitmap f2851f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        ColorStateList f2852g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        PorterDuff.Mode f2853h;
        int i;
        boolean j;
        boolean k;
        Paint l;

        public C0052h(C0052h c0052h) {
            this.f2848c = null;
            this.f2849d = h.f2816c;
            if (c0052h != null) {
                this.f2846a = c0052h.f2846a;
                g gVar = new g(c0052h.f2847b);
                this.f2847b = gVar;
                Paint paint = c0052h.f2847b.f2843f;
                if (paint != null) {
                    gVar.f2843f = new Paint(paint);
                }
                Paint paint2 = c0052h.f2847b.f2842e;
                if (paint2 != null) {
                    this.f2847b.f2842e = new Paint(paint2);
                }
                this.f2848c = c0052h.f2848c;
                this.f2849d = c0052h.f2849d;
                this.f2850e = c0052h.f2850e;
            }
        }

        public boolean a(int i, int i2) {
            return i == this.f2851f.getWidth() && i2 == this.f2851f.getHeight();
        }

        public boolean b() {
            return !this.k && this.f2852g == this.f2848c && this.f2853h == this.f2849d && this.j == this.f2850e && this.i == this.f2847b.getRootAlpha();
        }

        public void c(int i, int i2) {
            if (this.f2851f == null || !a(i, i2)) {
                this.f2851f = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.k = true;
            }
        }

        public void d(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f2851f, (Rect) null, rect, e(colorFilter));
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
            this.l.setAlpha(this.f2847b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public boolean f() {
            return this.f2847b.getRootAlpha() < 255;
        }

        public boolean g() {
            return this.f2847b.f();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f2846a;
        }

        public boolean h(int[] iArr) {
            boolean zG = this.f2847b.g(iArr);
            this.k |= zG;
            return zG;
        }

        public void i() {
            this.f2852g = this.f2848c;
            this.f2853h = this.f2849d;
            this.i = this.f2847b.getRootAlpha();
            this.j = this.f2850e;
            this.k = false;
        }

        public void j(int i, int i2) {
            this.f2851f.eraseColor(0);
            this.f2847b.b(new Canvas(this.f2851f), i, i2, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new h(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new h(this);
        }

        public C0052h() {
            this.f2848c = null;
            this.f2849d = h.f2816c;
            this.f2847b = new g();
        }
    }

    h() {
        this.f2821h = true;
        this.j = new float[9];
        this.k = new Matrix();
        this.l = new Rect();
        this.f2817d = new C0052h();
    }

    static int a(int i2, float f2) {
        return (i2 & 16777215) | (((int) (Color.alpha(i2) * f2)) << 24);
    }

    public static h b(Resources resources, int i2, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            h hVar = new h();
            Drawable drawableA = androidx.core.content.c.f.a(resources, i2, theme);
            hVar.f2815b = drawableA;
            hVar.i = new i(drawableA.getConstantState());
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

    private void e(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        C0052h c0052h = this.f2817d;
        g gVar = c0052h.f2847b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(gVar.i);
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
                    dVar.f2827b.add(cVar);
                    if (cVar.getPathName() != null) {
                        gVar.q.put(cVar.getPathName(), cVar);
                    }
                    z = false;
                    c0052h.f2846a = cVar.f2837d | c0052h.f2846a;
                } else if ("clip-path".equals(name)) {
                    b bVar = new b();
                    bVar.e(resources, attributeSet, theme, xmlPullParser);
                    dVar.f2827b.add(bVar);
                    if (bVar.getPathName() != null) {
                        gVar.q.put(bVar.getPathName(), bVar);
                    }
                    c0052h.f2846a = bVar.f2837d | c0052h.f2846a;
                } else if ("group".equals(name)) {
                    d dVar2 = new d();
                    dVar2.c(resources, attributeSet, theme, xmlPullParser);
                    dVar.f2827b.add(dVar2);
                    arrayDeque.push(dVar2);
                    if (dVar2.getGroupName() != null) {
                        gVar.q.put(dVar2.getGroupName(), dVar2);
                    }
                    c0052h.f2846a = dVar2.k | c0052h.f2846a;
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

    private boolean f() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && androidx.core.graphics.drawable.a.f(this) == 1;
    }

    private static PorterDuff.Mode g(int i2, PorterDuff.Mode mode) {
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

    private void i(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) throws XmlPullParserException {
        C0052h c0052h = this.f2817d;
        g gVar = c0052h.f2847b;
        c0052h.f2849d = g(androidx.core.content.c.g.g(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateListC = androidx.core.content.c.g.c(typedArray, xmlPullParser, theme, "tint", 1);
        if (colorStateListC != null) {
            c0052h.f2848c = colorStateListC;
        }
        c0052h.f2850e = androidx.core.content.c.g.a(typedArray, xmlPullParser, "autoMirrored", 5, c0052h.f2850e);
        gVar.l = androidx.core.content.c.g.f(typedArray, xmlPullParser, "viewportWidth", 7, gVar.l);
        float f2 = androidx.core.content.c.g.f(typedArray, xmlPullParser, "viewportHeight", 8, gVar.m);
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
        gVar.setAlpha(androidx.core.content.c.g.f(typedArray, xmlPullParser, "alpha", 4, gVar.getAlpha()));
        String string = typedArray.getString(0);
        if (string != null) {
            gVar.o = string;
            gVar.q.put(string, gVar);
        }
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f2815b;
        if (drawable == null) {
            return false;
        }
        androidx.core.graphics.drawable.a.b(drawable);
        return false;
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    Object d(String str) {
        return this.f2817d.f2847b.q.get(str);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.l);
        if (this.l.width() <= 0 || this.l.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f2819f;
        if (colorFilter == null) {
            colorFilter = this.f2818e;
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
        this.f2817d.c(iMin, iMin2);
        if (!this.f2821h) {
            this.f2817d.j(iMin, iMin2);
        } else if (!this.f2817d.b()) {
            this.f2817d.j(iMin, iMin2);
            this.f2817d.i();
        }
        this.f2817d.d(canvas, colorFilter, this.l);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f2815b;
        return drawable != null ? androidx.core.graphics.drawable.a.d(drawable) : this.f2817d.f2847b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f2815b;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f2817d.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f2815b;
        return drawable != null ? androidx.core.graphics.drawable.a.e(drawable) : this.f2819f;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f2815b;
        if (drawable != null && Build.VERSION.SDK_INT >= 24) {
            return new i(drawable.getConstantState());
        }
        this.f2817d.f2846a = getChangingConfigurations();
        return this.f2817d;
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f2815b;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f2817d.f2847b.k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f2815b;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f2817d.f2847b.j;
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    void h(boolean z) {
        this.f2821h = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f2815b;
        return drawable != null ? androidx.core.graphics.drawable.a.h(drawable) : this.f2817d.f2850e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        C0052h c0052h;
        ColorStateList colorStateList;
        Drawable drawable = this.f2815b;
        return drawable != null ? drawable.isStateful() : super.isStateful() || ((c0052h = this.f2817d) != null && (c0052h.g() || ((colorStateList = this.f2817d.f2848c) != null && colorStateList.isStateful())));
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f2820g && super.mutate() == this) {
            this.f2817d = new C0052h(this.f2817d);
            this.f2820g = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        C0052h c0052h = this.f2817d;
        ColorStateList colorStateList = c0052h.f2848c;
        if (colorStateList != null && (mode = c0052h.f2849d) != null) {
            this.f2818e = updateTintFilter(this.f2818e, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (!c0052h.g() || !c0052h.h(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else if (this.f2817d.f2847b.getRootAlpha() != i2) {
            this.f2817d.f2847b.setRootAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.j(drawable, z);
        } else {
            this.f2817d.f2850e = z;
        }
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i2) {
        super.setChangingConfigurations(i2);
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i2, PorterDuff.Mode mode) {
        super.setColorFilter(i2, mode);
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i2, int i3, int i4, int i5) {
        super.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i2) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.n(drawable, i2);
        } else {
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.o(drawable, colorStateList);
            return;
        }
        C0052h c0052h = this.f2817d;
        if (c0052h.f2848c != colorStateList) {
            c0052h.f2848c = colorStateList;
            this.f2818e = updateTintFilter(this.f2818e, colorStateList, c0052h.f2849d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.p(drawable, mode);
            return;
        }
        C0052h c0052h = this.f2817d;
        if (c0052h.f2849d != mode) {
            c0052h.f2849d = mode;
            this.f2818e = updateTintFilter(this.f2818e, c0052h.f2848c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f2815b;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static class i extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Drawable.ConstantState f2854a;

        public i(Drawable.ConstantState constantState) {
            this.f2854a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f2854a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f2854a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVar = new h();
            hVar.f2815b = (VectorDrawable) this.f2854a.newDrawable();
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            h hVar = new h();
            hVar.f2815b = (VectorDrawable) this.f2854a.newDrawable(resources);
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            h hVar = new h();
            hVar.f2815b = (VectorDrawable) this.f2854a.newDrawable(resources, theme);
            return hVar;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f2819f = colorFilter;
            invalidateSelf();
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static abstract class f extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected c.b[] f2834a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f2835b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2836c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2837d;

        public f() {
            super();
            this.f2834a = null;
            this.f2836c = 0;
        }

        public boolean c() {
            return false;
        }

        public void d(Path path) {
            path.reset();
            c.b[] bVarArr = this.f2834a;
            if (bVarArr != null) {
                c.b.e(bVarArr, path);
            }
        }

        public c.b[] getPathData() {
            return this.f2834a;
        }

        public String getPathName() {
            return this.f2835b;
        }

        public void setPathData(c.b[] bVarArr) {
            if (b.g.d.c.b(this.f2834a, bVarArr)) {
                b.g.d.c.j(this.f2834a, bVarArr);
            } else {
                this.f2834a = b.g.d.c.f(bVarArr);
            }
        }

        public f(f fVar) {
            super();
            this.f2834a = null;
            this.f2836c = 0;
            this.f2835b = fVar.f2835b;
            this.f2837d = fVar.f2837d;
            this.f2834a = b.g.d.c.f(fVar.f2834a);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        C0052h c0052h = this.f2817d;
        c0052h.f2847b = new g();
        TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, b.n.a.a.a.f2790a);
        i(typedArrayK, xmlPullParser, theme);
        typedArrayK.recycle();
        c0052h.f2846a = getChangingConfigurations();
        c0052h.k = true;
        e(resources, xmlPullParser, attributeSet, theme);
        this.f2818e = updateTintFilter(this.f2818e, c0052h.f2848c, c0052h.f2849d);
    }

    h(C0052h c0052h) {
        this.f2821h = true;
        this.j = new float[9];
        this.k = new Matrix();
        this.l = new Rect();
        this.f2817d = c0052h;
        this.f2818e = updateTintFilter(this.f2818e, c0052h.f2848c, c0052h.f2849d);
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static class c extends f {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int[] f2822e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        androidx.core.content.c.b f2823f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        float f2824g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        androidx.core.content.c.b f2825h;
        float i;
        float j;
        float k;
        float l;
        float m;
        Paint.Cap n;
        Paint.Join o;
        float p;

        c() {
            this.f2824g = 0.0f;
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
        }

        private Paint.Cap e(int i, Paint.Cap cap) {
            return i != 0 ? i != 1 ? i != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        private Paint.Join f(int i, Paint.Join join) {
            return i != 0 ? i != 1 ? i != 2 ? join : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER;
        }

        private void h(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.f2822e = null;
            if (androidx.core.content.c.g.j(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f2835b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f2834a = b.g.d.c.d(string2);
                }
                this.f2825h = androidx.core.content.c.g.e(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.j = androidx.core.content.c.g.f(typedArray, xmlPullParser, "fillAlpha", 12, this.j);
                this.n = e(androidx.core.content.c.g.g(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.n);
                this.o = f(androidx.core.content.c.g.g(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.o);
                this.p = androidx.core.content.c.g.f(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.p);
                this.f2823f = androidx.core.content.c.g.e(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.i = androidx.core.content.c.g.f(typedArray, xmlPullParser, "strokeAlpha", 11, this.i);
                this.f2824g = androidx.core.content.c.g.f(typedArray, xmlPullParser, "strokeWidth", 4, this.f2824g);
                this.l = androidx.core.content.c.g.f(typedArray, xmlPullParser, "trimPathEnd", 6, this.l);
                this.m = androidx.core.content.c.g.f(typedArray, xmlPullParser, "trimPathOffset", 7, this.m);
                this.k = androidx.core.content.c.g.f(typedArray, xmlPullParser, "trimPathStart", 5, this.k);
                this.f2836c = androidx.core.content.c.g.g(typedArray, xmlPullParser, "fillType", 13, this.f2836c);
            }
        }

        @Override // b.n.a.a.h.e
        public boolean a() {
            return this.f2825h.i() || this.f2823f.i();
        }

        @Override // b.n.a.a.h.e
        public boolean b(int[] iArr) {
            return this.f2823f.j(iArr) | this.f2825h.j(iArr);
        }

        public void g(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, b.n.a.a.a.f2792c);
            h(typedArrayK, xmlPullParser, theme);
            typedArrayK.recycle();
        }

        float getFillAlpha() {
            return this.j;
        }

        int getFillColor() {
            return this.f2825h.e();
        }

        float getStrokeAlpha() {
            return this.i;
        }

        int getStrokeColor() {
            return this.f2823f.e();
        }

        float getStrokeWidth() {
            return this.f2824g;
        }

        float getTrimPathEnd() {
            return this.l;
        }

        float getTrimPathOffset() {
            return this.m;
        }

        float getTrimPathStart() {
            return this.k;
        }

        void setFillAlpha(float f2) {
            this.j = f2;
        }

        void setFillColor(int i) {
            this.f2825h.k(i);
        }

        void setStrokeAlpha(float f2) {
            this.i = f2;
        }

        void setStrokeColor(int i) {
            this.f2823f.k(i);
        }

        void setStrokeWidth(float f2) {
            this.f2824g = f2;
        }

        void setTrimPathEnd(float f2) {
            this.l = f2;
        }

        void setTrimPathOffset(float f2) {
            this.m = f2;
        }

        void setTrimPathStart(float f2) {
            this.k = f2;
        }

        c(c cVar) {
            super(cVar);
            this.f2824g = 0.0f;
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
            this.f2822e = cVar.f2822e;
            this.f2823f = cVar.f2823f;
            this.f2824g = cVar.f2824g;
            this.i = cVar.i;
            this.f2825h = cVar.f2825h;
            this.f2836c = cVar.f2836c;
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
    private static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final Matrix f2838a = new Matrix();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Path f2839b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Path f2840c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Matrix f2841d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Paint f2842e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        Paint f2843f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private PathMeasure f2844g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f2845h;
        final d i;
        float j;
        float k;
        float l;
        float m;
        int n;
        String o;
        Boolean p;
        final b.d.a<String, Object> q;

        public g() {
            this.f2841d = new Matrix();
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 255;
            this.o = null;
            this.p = null;
            this.q = new b.d.a<>();
            this.i = new d();
            this.f2839b = new Path();
            this.f2840c = new Path();
        }

        private static float a(float f2, float f3, float f4, float f5) {
            return (f2 * f5) - (f3 * f4);
        }

        private void c(d dVar, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            dVar.f2826a.set(matrix);
            dVar.f2826a.preConcat(dVar.j);
            canvas.save();
            for (int i3 = 0; i3 < dVar.f2827b.size(); i3++) {
                e eVar = dVar.f2827b.get(i3);
                if (eVar instanceof d) {
                    c((d) eVar, dVar.f2826a, canvas, i, i2, colorFilter);
                } else if (eVar instanceof f) {
                    d(dVar, (f) eVar, canvas, i, i2, colorFilter);
                }
            }
            canvas.restore();
        }

        private void d(d dVar, f fVar, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            float f2 = i / this.l;
            float f3 = i2 / this.m;
            float fMin = Math.min(f2, f3);
            Matrix matrix = dVar.f2826a;
            this.f2841d.set(matrix);
            this.f2841d.postScale(f2, f3);
            float fE = e(matrix);
            if (fE == 0.0f) {
                return;
            }
            fVar.d(this.f2839b);
            Path path = this.f2839b;
            this.f2840c.reset();
            if (fVar.c()) {
                this.f2840c.setFillType(fVar.f2836c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.f2840c.addPath(path, this.f2841d);
                canvas.clipPath(this.f2840c);
                return;
            }
            c cVar = (c) fVar;
            float f4 = cVar.k;
            if (f4 != 0.0f || cVar.l != 1.0f) {
                float f5 = cVar.m;
                float f6 = (f4 + f5) % 1.0f;
                float f7 = (cVar.l + f5) % 1.0f;
                if (this.f2844g == null) {
                    this.f2844g = new PathMeasure();
                }
                this.f2844g.setPath(this.f2839b, false);
                float length = this.f2844g.getLength();
                float f8 = f6 * length;
                float f9 = f7 * length;
                path.reset();
                if (f8 > f9) {
                    this.f2844g.getSegment(f8, length, path, true);
                    this.f2844g.getSegment(0.0f, f9, path, true);
                } else {
                    this.f2844g.getSegment(f8, f9, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.f2840c.addPath(path, this.f2841d);
            if (cVar.f2825h.l()) {
                androidx.core.content.c.b bVar = cVar.f2825h;
                if (this.f2843f == null) {
                    Paint paint = new Paint(1);
                    this.f2843f = paint;
                    paint.setStyle(Paint.Style.FILL);
                }
                Paint paint2 = this.f2843f;
                if (bVar.h()) {
                    Shader shaderF = bVar.f();
                    shaderF.setLocalMatrix(this.f2841d);
                    paint2.setShader(shaderF);
                    paint2.setAlpha(Math.round(cVar.j * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(h.a(bVar.e(), cVar.j));
                }
                paint2.setColorFilter(colorFilter);
                this.f2840c.setFillType(cVar.f2836c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.f2840c, paint2);
            }
            if (cVar.f2823f.l()) {
                androidx.core.content.c.b bVar2 = cVar.f2823f;
                if (this.f2842e == null) {
                    Paint paint3 = new Paint(1);
                    this.f2842e = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                }
                Paint paint4 = this.f2842e;
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
                    shaderF2.setLocalMatrix(this.f2841d);
                    paint4.setShader(shaderF2);
                    paint4.setAlpha(Math.round(cVar.i * 255.0f));
                } else {
                    paint4.setShader(null);
                    paint4.setAlpha(255);
                    paint4.setColor(h.a(bVar2.e(), cVar.i));
                }
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(cVar.f2824g * fMin * fE);
                canvas.drawPath(this.f2840c, paint4);
            }
        }

        private float e(Matrix matrix) {
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

        public void b(Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            c(this.i, f2838a, canvas, i, i2, colorFilter);
        }

        public boolean f() {
            if (this.p == null) {
                this.p = Boolean.valueOf(this.i.a());
            }
            return this.p.booleanValue();
        }

        public boolean g(int[] iArr) {
            return this.i.b(iArr);
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

        public void setRootAlpha(int i) {
            this.n = i;
        }

        public g(g gVar) {
            this.f2841d = new Matrix();
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 255;
            this.o = null;
            this.p = null;
            b.d.a<String, Object> aVar = new b.d.a<>();
            this.q = aVar;
            this.i = new d(gVar.i, aVar);
            this.f2839b = new Path(gVar.f2839b);
            this.f2840c = new Path(gVar.f2840c);
            this.j = gVar.j;
            this.k = gVar.k;
            this.l = gVar.l;
            this.m = gVar.m;
            this.f2845h = gVar.f2845h;
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
    private static class d extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Matrix f2826a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ArrayList<e> f2827b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f2828c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f2829d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private float f2830e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private float f2831f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private float f2832g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private float f2833h;
        private float i;
        final Matrix j;
        int k;
        private int[] l;
        private String m;

        public d(d dVar, b.d.a<String, Object> aVar) {
            f bVar;
            super();
            this.f2826a = new Matrix();
            this.f2827b = new ArrayList<>();
            this.f2828c = 0.0f;
            this.f2829d = 0.0f;
            this.f2830e = 0.0f;
            this.f2831f = 1.0f;
            this.f2832g = 1.0f;
            this.f2833h = 0.0f;
            this.i = 0.0f;
            Matrix matrix = new Matrix();
            this.j = matrix;
            this.m = null;
            this.f2828c = dVar.f2828c;
            this.f2829d = dVar.f2829d;
            this.f2830e = dVar.f2830e;
            this.f2831f = dVar.f2831f;
            this.f2832g = dVar.f2832g;
            this.f2833h = dVar.f2833h;
            this.i = dVar.i;
            this.l = dVar.l;
            String str = dVar.m;
            this.m = str;
            this.k = dVar.k;
            if (str != null) {
                aVar.put(str, this);
            }
            matrix.set(dVar.j);
            ArrayList<e> arrayList = dVar.f2827b;
            for (int i = 0; i < arrayList.size(); i++) {
                e eVar = arrayList.get(i);
                if (eVar instanceof d) {
                    this.f2827b.add(new d((d) eVar, aVar));
                } else {
                    if (eVar instanceof c) {
                        bVar = new c((c) eVar);
                    } else {
                        if (!(eVar instanceof b)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        bVar = new b((b) eVar);
                    }
                    this.f2827b.add(bVar);
                    String str2 = bVar.f2835b;
                    if (str2 != null) {
                        aVar.put(str2, bVar);
                    }
                }
            }
        }

        private void d() {
            this.j.reset();
            this.j.postTranslate(-this.f2829d, -this.f2830e);
            this.j.postScale(this.f2831f, this.f2832g);
            this.j.postRotate(this.f2828c, 0.0f, 0.0f);
            this.j.postTranslate(this.f2833h + this.f2829d, this.i + this.f2830e);
        }

        private void e(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.l = null;
            this.f2828c = androidx.core.content.c.g.f(typedArray, xmlPullParser, "rotation", 5, this.f2828c);
            this.f2829d = typedArray.getFloat(1, this.f2829d);
            this.f2830e = typedArray.getFloat(2, this.f2830e);
            this.f2831f = androidx.core.content.c.g.f(typedArray, xmlPullParser, "scaleX", 3, this.f2831f);
            this.f2832g = androidx.core.content.c.g.f(typedArray, xmlPullParser, "scaleY", 4, this.f2832g);
            this.f2833h = androidx.core.content.c.g.f(typedArray, xmlPullParser, "translateX", 6, this.f2833h);
            this.i = androidx.core.content.c.g.f(typedArray, xmlPullParser, "translateY", 7, this.i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.m = string;
            }
            d();
        }

        @Override // b.n.a.a.h.e
        public boolean a() {
            for (int i = 0; i < this.f2827b.size(); i++) {
                if (this.f2827b.get(i).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // b.n.a.a.h.e
        public boolean b(int[] iArr) {
            boolean zB = false;
            for (int i = 0; i < this.f2827b.size(); i++) {
                zB |= this.f2827b.get(i).b(iArr);
            }
            return zB;
        }

        public void c(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, b.n.a.a.a.f2791b);
            e(typedArrayK, xmlPullParser);
            typedArrayK.recycle();
        }

        public String getGroupName() {
            return this.m;
        }

        public Matrix getLocalMatrix() {
            return this.j;
        }

        public float getPivotX() {
            return this.f2829d;
        }

        public float getPivotY() {
            return this.f2830e;
        }

        public float getRotation() {
            return this.f2828c;
        }

        public float getScaleX() {
            return this.f2831f;
        }

        public float getScaleY() {
            return this.f2832g;
        }

        public float getTranslateX() {
            return this.f2833h;
        }

        public float getTranslateY() {
            return this.i;
        }

        public void setPivotX(float f2) {
            if (f2 != this.f2829d) {
                this.f2829d = f2;
                d();
            }
        }

        public void setPivotY(float f2) {
            if (f2 != this.f2830e) {
                this.f2830e = f2;
                d();
            }
        }

        public void setRotation(float f2) {
            if (f2 != this.f2828c) {
                this.f2828c = f2;
                d();
            }
        }

        public void setScaleX(float f2) {
            if (f2 != this.f2831f) {
                this.f2831f = f2;
                d();
            }
        }

        public void setScaleY(float f2) {
            if (f2 != this.f2832g) {
                this.f2832g = f2;
                d();
            }
        }

        public void setTranslateX(float f2) {
            if (f2 != this.f2833h) {
                this.f2833h = f2;
                d();
            }
        }

        public void setTranslateY(float f2) {
            if (f2 != this.i) {
                this.i = f2;
                d();
            }
        }

        public d() {
            super();
            this.f2826a = new Matrix();
            this.f2827b = new ArrayList<>();
            this.f2828c = 0.0f;
            this.f2829d = 0.0f;
            this.f2830e = 0.0f;
            this.f2831f = 1.0f;
            this.f2832g = 1.0f;
            this.f2833h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
        }
    }
}
