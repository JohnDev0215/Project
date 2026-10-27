package b.p.a.a;

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
import b.f.g.c;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: VectorDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends b.p.a.a.g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final PorterDuff.Mode f2146d = PorterDuff.Mode.SRC_IN;
    public boolean E3;
    public boolean F3;
    public Drawable.ConstantState G3;
    public final float[] H3;
    public final Matrix I3;
    public final Rect J3;
    public C0043h s;
    public PorterDuffColorFilter x;
    public ColorFilter y;

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class b extends f {
        public b() {
        }

        @Override // b.p.a.a.h.f
        public boolean c() {
            return true;
        }

        public void e(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (b.f.f.c.g.j(xmlPullParser, "pathData")) {
                TypedArray typedArrayK = b.f.f.c.g.k(resources, theme, attributeSet, b.p.a.a.a.f2127d);
                f(typedArrayK, xmlPullParser);
                typedArrayK.recycle();
            }
        }

        public final void f(TypedArray typedArray, XmlPullParser xmlPullParser) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.f2162b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f2161a = b.f.g.c.d(string2);
            }
            this.f2163c = b.f.f.c.g.g(typedArray, xmlPullParser, "fillType", 2, 0);
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

    /* JADX INFO: renamed from: b.p.a.a.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class C0043h extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2174a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public g f2175b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorStateList f2176c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public PorterDuff.Mode f2177d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2178e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Bitmap f2179f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ColorStateList f2180g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public PorterDuff.Mode f2181h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f2182i;
        public boolean j;
        public boolean k;
        public Paint l;

        public C0043h(C0043h c0043h) {
            this.f2176c = null;
            this.f2177d = h.f2146d;
            if (c0043h != null) {
                this.f2174a = c0043h.f2174a;
                g gVar = new g(c0043h.f2175b);
                this.f2175b = gVar;
                Paint paint = c0043h.f2175b.f2170f;
                if (paint != null) {
                    gVar.f2170f = new Paint(paint);
                }
                Paint paint2 = c0043h.f2175b.f2169e;
                if (paint2 != null) {
                    this.f2175b.f2169e = new Paint(paint2);
                }
                this.f2176c = c0043h.f2176c;
                this.f2177d = c0043h.f2177d;
                this.f2178e = c0043h.f2178e;
            }
        }

        public boolean a(int i2, int i3) {
            return i2 == this.f2179f.getWidth() && i3 == this.f2179f.getHeight();
        }

        public boolean b() {
            return !this.k && this.f2180g == this.f2176c && this.f2181h == this.f2177d && this.j == this.f2178e && this.f2182i == this.f2175b.getRootAlpha();
        }

        public void c(int i2, int i3) {
            if (this.f2179f == null || !a(i2, i3)) {
                this.f2179f = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
                this.k = true;
            }
        }

        public void d(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f2179f, (Rect) null, rect, e(colorFilter));
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
            this.l.setAlpha(this.f2175b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public boolean f() {
            return this.f2175b.getRootAlpha() < 255;
        }

        public boolean g() {
            return this.f2175b.f();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f2174a;
        }

        public boolean h(int[] iArr) {
            boolean zG = this.f2175b.g(iArr);
            this.k |= zG;
            return zG;
        }

        public void i() {
            this.f2180g = this.f2176c;
            this.f2181h = this.f2177d;
            this.f2182i = this.f2175b.getRootAlpha();
            this.j = this.f2178e;
            this.k = false;
        }

        public void j(int i2, int i3) {
            this.f2179f.eraseColor(0);
            this.f2175b.b(new Canvas(this.f2179f), i2, i3, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new h(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new h(this);
        }

        public C0043h() {
            this.f2176c = null;
            this.f2177d = h.f2146d;
            this.f2175b = new g();
        }
    }

    public h() {
        this.F3 = true;
        this.H3 = new float[9];
        this.I3 = new Matrix();
        this.J3 = new Rect();
        this.s = new C0043h();
    }

    public static int a(int i2, float f2) {
        return (i2 & 16777215) | (((int) (Color.alpha(i2) * f2)) << 24);
    }

    public static h b(Resources resources, int i2, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            h hVar = new h();
            Drawable drawableA = b.f.f.c.f.a(resources, i2, theme);
            hVar.f2145c = drawableA;
            hVar.G3 = new i(drawableA.getConstantState());
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

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f2145c;
        if (drawable == null) {
            return false;
        }
        b.f.g.l.a.b(drawable);
        return false;
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public Object d(String str) {
        return this.s.f2175b.q.get(str);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.J3);
        if (this.J3.width() <= 0 || this.J3.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.y;
        if (colorFilter == null) {
            colorFilter = this.x;
        }
        canvas.getMatrix(this.I3);
        this.I3.getValues(this.H3);
        float fAbs = Math.abs(this.H3[0]);
        float fAbs2 = Math.abs(this.H3[4]);
        float fAbs3 = Math.abs(this.H3[1]);
        float fAbs4 = Math.abs(this.H3[3]);
        if (fAbs3 != 0.0f || fAbs4 != 0.0f) {
            fAbs = 1.0f;
            fAbs2 = 1.0f;
        }
        int iMin = Math.min(2048, (int) (this.J3.width() * fAbs));
        int iMin2 = Math.min(2048, (int) (this.J3.height() * fAbs2));
        if (iMin <= 0 || iMin2 <= 0) {
            return;
        }
        int iSave = canvas.save();
        Rect rect = this.J3;
        canvas.translate(rect.left, rect.top);
        if (f()) {
            canvas.translate(this.J3.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.J3.offsetTo(0, 0);
        this.s.c(iMin, iMin2);
        if (!this.F3) {
            this.s.j(iMin, iMin2);
        } else if (!this.s.b()) {
            this.s.j(iMin, iMin2);
            this.s.i();
        }
        this.s.d(canvas, colorFilter, this.J3);
        canvas.restoreToCount(iSave);
    }

    public final void e(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        C0043h c0043h = this.s;
        g gVar = c0043h.f2175b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(gVar.f2173i);
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
                    dVar.f2153b.add(cVar);
                    if (cVar.getPathName() != null) {
                        gVar.q.put(cVar.getPathName(), cVar);
                    }
                    z = false;
                    c0043h.f2174a = cVar.f2164d | c0043h.f2174a;
                } else if ("clip-path".equals(name)) {
                    b bVar = new b();
                    bVar.e(resources, attributeSet, theme, xmlPullParser);
                    dVar.f2153b.add(bVar);
                    if (bVar.getPathName() != null) {
                        gVar.q.put(bVar.getPathName(), bVar);
                    }
                    c0043h.f2174a = bVar.f2164d | c0043h.f2174a;
                } else if ("group".equals(name)) {
                    d dVar2 = new d();
                    dVar2.c(resources, attributeSet, theme, xmlPullParser);
                    dVar.f2153b.add(dVar2);
                    arrayDeque.push(dVar2);
                    if (dVar2.getGroupName() != null) {
                        gVar.q.put(dVar2.getGroupName(), dVar2);
                    }
                    c0043h.f2174a = dVar2.k | c0043h.f2174a;
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
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && b.f.g.l.a.e(this) == 1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f2145c;
        return drawable != null ? b.f.g.l.a.c(drawable) : this.s.f2175b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f2145c;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.s.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f2145c;
        return drawable != null ? b.f.g.l.a.d(drawable) : this.y;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f2145c;
        if (drawable != null && Build.VERSION.SDK_INT >= 24) {
            return new i(drawable.getConstantState());
        }
        this.s.f2174a = getChangingConfigurations();
        return this.s;
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f2145c;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.s.f2175b.k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f2145c;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.s.f2175b.j;
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void h(boolean z) {
        this.F3 = z;
    }

    public final void i(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) throws XmlPullParserException {
        C0043h c0043h = this.s;
        g gVar = c0043h.f2175b;
        c0043h.f2177d = g(b.f.f.c.g.g(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateListC = b.f.f.c.g.c(typedArray, xmlPullParser, theme, "tint", 1);
        if (colorStateListC != null) {
            c0043h.f2176c = colorStateListC;
        }
        c0043h.f2178e = b.f.f.c.g.a(typedArray, xmlPullParser, "autoMirrored", 5, c0043h.f2178e);
        gVar.l = b.f.f.c.g.f(typedArray, xmlPullParser, "viewportWidth", 7, gVar.l);
        float f2 = b.f.f.c.g.f(typedArray, xmlPullParser, "viewportHeight", 8, gVar.m);
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
        gVar.setAlpha(b.f.f.c.g.f(typedArray, xmlPullParser, "alpha", 4, gVar.getAlpha()));
        String string = typedArray.getString(0);
        if (string != null) {
            gVar.o = string;
            gVar.q.put(string, gVar);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f2145c;
        return drawable != null ? b.f.g.l.a.g(drawable) : this.s.f2178e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        C0043h c0043h;
        ColorStateList colorStateList;
        Drawable drawable = this.f2145c;
        return drawable != null ? drawable.isStateful() : super.isStateful() || ((c0043h = this.s) != null && (c0043h.g() || ((colorStateList = this.s.f2176c) != null && colorStateList.isStateful())));
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.E3 && super.mutate() == this) {
            this.s = new C0043h(this.s);
            this.E3 = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        C0043h c0043h = this.s;
        ColorStateList colorStateList = c0043h.f2176c;
        if (colorStateList != null && (mode = c0043h.f2177d) != null) {
            this.x = updateTintFilter(this.x, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (!c0043h.g() || !c0043h.h(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else if (this.s.f2175b.getRootAlpha() != i2) {
            this.s.f2175b.setRootAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            b.f.g.l.a.i(drawable, z);
        } else {
            this.s.f2178e = z;
        }
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i2) {
        super.setChangingConfigurations(i2);
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i2, PorterDuff.Mode mode) {
        super.setColorFilter(i2, mode);
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i2, int i3, int i4, int i5) {
        super.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, b.f.g.l.b
    public void setTint(int i2) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            b.f.g.l.a.m(drawable, i2);
        } else {
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    @Override // android.graphics.drawable.Drawable, b.f.g.l.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            b.f.g.l.a.n(drawable, colorStateList);
            return;
        }
        C0043h c0043h = this.s;
        if (c0043h.f2176c != colorStateList) {
            c0043h.f2176c = colorStateList;
            this.x = updateTintFilter(this.x, colorStateList, c0043h.f2177d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, b.f.g.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            b.f.g.l.a.o(drawable, mode);
            return;
        }
        C0043h c0043h = this.s;
        if (c0043h.f2177d != mode) {
            c0043h.f2177d = mode;
            this.x = updateTintFilter(this.x, c0043h.f2176c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f2145c;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f2145c;
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
        public final Drawable.ConstantState f2183a;

        public i(Drawable.ConstantState constantState) {
            this.f2183a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f2183a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f2183a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVar = new h();
            hVar.f2145c = (VectorDrawable) this.f2183a.newDrawable();
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            h hVar = new h();
            hVar.f2145c = (VectorDrawable) this.f2183a.newDrawable(resources);
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            h hVar = new h();
            hVar.f2145c = (VectorDrawable) this.f2183a.newDrawable(resources, theme);
            return hVar;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.y = colorFilter;
            invalidateSelf();
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static abstract class f extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c.b[] f2161a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f2162b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2163c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2164d;

        public f() {
            super();
            this.f2161a = null;
            this.f2163c = 0;
        }

        public boolean c() {
            return false;
        }

        public void d(Path path) {
            path.reset();
            c.b[] bVarArr = this.f2161a;
            if (bVarArr != null) {
                c.b.e(bVarArr, path);
            }
        }

        public c.b[] getPathData() {
            return this.f2161a;
        }

        public String getPathName() {
            return this.f2162b;
        }

        public void setPathData(c.b[] bVarArr) {
            if (b.f.g.c.b(this.f2161a, bVarArr)) {
                b.f.g.c.j(this.f2161a, bVarArr);
            } else {
                this.f2161a = b.f.g.c.f(bVarArr);
            }
        }

        public f(f fVar) {
            super();
            this.f2161a = null;
            this.f2163c = 0;
            this.f2162b = fVar.f2162b;
            this.f2164d = fVar.f2164d;
            this.f2161a = b.f.g.c.f(fVar.f2161a);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            b.f.g.l.a.f(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        C0043h c0043h = this.s;
        c0043h.f2175b = new g();
        TypedArray typedArrayK = b.f.f.c.g.k(resources, theme, attributeSet, b.p.a.a.a.f2124a);
        i(typedArrayK, xmlPullParser, theme);
        typedArrayK.recycle();
        c0043h.f2174a = getChangingConfigurations();
        c0043h.k = true;
        e(resources, xmlPullParser, attributeSet, theme);
        this.x = updateTintFilter(this.x, c0043h.f2176c, c0043h.f2177d);
    }

    public h(C0043h c0043h) {
        this.F3 = true;
        this.H3 = new float[9];
        this.I3 = new Matrix();
        this.J3 = new Rect();
        this.s = c0043h;
        this.x = updateTintFilter(this.x, c0043h.f2176c, c0043h.f2177d);
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class c extends f {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int[] f2147e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b.f.f.c.b f2148f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f2149g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public b.f.f.c.b f2150h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f2151i;
        public float j;
        public float k;
        public float l;
        public float m;
        public Paint.Cap n;
        public Paint.Join o;
        public float p;

        public c() {
            this.f2149g = 0.0f;
            this.f2151i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
        }

        @Override // b.p.a.a.h.e
        public boolean a() {
            return this.f2150h.i() || this.f2148f.i();
        }

        @Override // b.p.a.a.h.e
        public boolean b(int[] iArr) {
            return this.f2148f.j(iArr) | this.f2150h.j(iArr);
        }

        public final Paint.Cap e(int i2, Paint.Cap cap) {
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        public final Paint.Join f(int i2, Paint.Join join) {
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? join : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER;
        }

        public void g(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = b.f.f.c.g.k(resources, theme, attributeSet, b.p.a.a.a.f2126c);
            h(typedArrayK, xmlPullParser, theme);
            typedArrayK.recycle();
        }

        public float getFillAlpha() {
            return this.j;
        }

        public int getFillColor() {
            return this.f2150h.e();
        }

        public float getStrokeAlpha() {
            return this.f2151i;
        }

        public int getStrokeColor() {
            return this.f2148f.e();
        }

        public float getStrokeWidth() {
            return this.f2149g;
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
            this.f2147e = null;
            if (b.f.f.c.g.j(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f2162b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f2161a = b.f.g.c.d(string2);
                }
                this.f2150h = b.f.f.c.g.e(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.j = b.f.f.c.g.f(typedArray, xmlPullParser, "fillAlpha", 12, this.j);
                this.n = e(b.f.f.c.g.g(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.n);
                this.o = f(b.f.f.c.g.g(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.o);
                this.p = b.f.f.c.g.f(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.p);
                this.f2148f = b.f.f.c.g.e(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.f2151i = b.f.f.c.g.f(typedArray, xmlPullParser, "strokeAlpha", 11, this.f2151i);
                this.f2149g = b.f.f.c.g.f(typedArray, xmlPullParser, "strokeWidth", 4, this.f2149g);
                this.l = b.f.f.c.g.f(typedArray, xmlPullParser, "trimPathEnd", 6, this.l);
                this.m = b.f.f.c.g.f(typedArray, xmlPullParser, "trimPathOffset", 7, this.m);
                this.k = b.f.f.c.g.f(typedArray, xmlPullParser, "trimPathStart", 5, this.k);
                this.f2163c = b.f.f.c.g.g(typedArray, xmlPullParser, "fillType", 13, this.f2163c);
            }
        }

        public void setFillAlpha(float f2) {
            this.j = f2;
        }

        public void setFillColor(int i2) {
            this.f2150h.k(i2);
        }

        public void setStrokeAlpha(float f2) {
            this.f2151i = f2;
        }

        public void setStrokeColor(int i2) {
            this.f2148f.k(i2);
        }

        public void setStrokeWidth(float f2) {
            this.f2149g = f2;
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
            this.f2149g = 0.0f;
            this.f2151i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
            this.f2147e = cVar.f2147e;
            this.f2148f = cVar.f2148f;
            this.f2149g = cVar.f2149g;
            this.f2151i = cVar.f2151i;
            this.f2150h = cVar.f2150h;
            this.f2163c = cVar.f2163c;
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
        public static final Matrix f2165a = new Matrix();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f2166b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Path f2167c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Matrix f2168d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Paint f2169e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Paint f2170f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public PathMeasure f2171g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f2172h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final d f2173i;
        public float j;
        public float k;
        public float l;
        public float m;
        public int n;
        public String o;
        public Boolean p;
        public final b.d.a<String, Object> q;

        public g() {
            this.f2168d = new Matrix();
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 255;
            this.o = null;
            this.p = null;
            this.q = new b.d.a<>();
            this.f2173i = new d();
            this.f2166b = new Path();
            this.f2167c = new Path();
        }

        public static float a(float f2, float f3, float f4, float f5) {
            return (f2 * f5) - (f3 * f4);
        }

        public void b(Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            c(this.f2173i, f2165a, canvas, i2, i3, colorFilter);
        }

        public final void c(d dVar, Matrix matrix, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            dVar.f2152a.set(matrix);
            dVar.f2152a.preConcat(dVar.j);
            canvas.save();
            for (int i4 = 0; i4 < dVar.f2153b.size(); i4++) {
                e eVar = dVar.f2153b.get(i4);
                if (eVar instanceof d) {
                    c((d) eVar, dVar.f2152a, canvas, i2, i3, colorFilter);
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
            Matrix matrix = dVar.f2152a;
            this.f2168d.set(matrix);
            this.f2168d.postScale(f2, f3);
            float fE = e(matrix);
            if (fE == 0.0f) {
                return;
            }
            fVar.d(this.f2166b);
            Path path = this.f2166b;
            this.f2167c.reset();
            if (fVar.c()) {
                this.f2167c.setFillType(fVar.f2163c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.f2167c.addPath(path, this.f2168d);
                canvas.clipPath(this.f2167c);
                return;
            }
            c cVar = (c) fVar;
            float f4 = cVar.k;
            if (f4 != 0.0f || cVar.l != 1.0f) {
                float f5 = cVar.m;
                float f6 = (f4 + f5) % 1.0f;
                float f7 = (cVar.l + f5) % 1.0f;
                if (this.f2171g == null) {
                    this.f2171g = new PathMeasure();
                }
                this.f2171g.setPath(this.f2166b, false);
                float length = this.f2171g.getLength();
                float f8 = f6 * length;
                float f9 = f7 * length;
                path.reset();
                if (f8 > f9) {
                    this.f2171g.getSegment(f8, length, path, true);
                    this.f2171g.getSegment(0.0f, f9, path, true);
                } else {
                    this.f2171g.getSegment(f8, f9, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.f2167c.addPath(path, this.f2168d);
            if (cVar.f2150h.l()) {
                b.f.f.c.b bVar = cVar.f2150h;
                if (this.f2170f == null) {
                    Paint paint = new Paint(1);
                    this.f2170f = paint;
                    paint.setStyle(Paint.Style.FILL);
                }
                Paint paint2 = this.f2170f;
                if (bVar.h()) {
                    Shader shaderF = bVar.f();
                    shaderF.setLocalMatrix(this.f2168d);
                    paint2.setShader(shaderF);
                    paint2.setAlpha(Math.round(cVar.j * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(h.a(bVar.e(), cVar.j));
                }
                paint2.setColorFilter(colorFilter);
                this.f2167c.setFillType(cVar.f2163c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.f2167c, paint2);
            }
            if (cVar.f2148f.l()) {
                b.f.f.c.b bVar2 = cVar.f2148f;
                if (this.f2169e == null) {
                    Paint paint3 = new Paint(1);
                    this.f2169e = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                }
                Paint paint4 = this.f2169e;
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
                    shaderF2.setLocalMatrix(this.f2168d);
                    paint4.setShader(shaderF2);
                    paint4.setAlpha(Math.round(cVar.f2151i * 255.0f));
                } else {
                    paint4.setShader(null);
                    paint4.setAlpha(255);
                    paint4.setColor(h.a(bVar2.e(), cVar.f2151i));
                }
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(cVar.f2149g * fMin * fE);
                canvas.drawPath(this.f2167c, paint4);
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
                this.p = Boolean.valueOf(this.f2173i.a());
            }
            return this.p.booleanValue();
        }

        public boolean g(int[] iArr) {
            return this.f2173i.b(iArr);
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
            this.f2168d = new Matrix();
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 255;
            this.o = null;
            this.p = null;
            b.d.a<String, Object> aVar = new b.d.a<>();
            this.q = aVar;
            this.f2173i = new d(gVar.f2173i, aVar);
            this.f2166b = new Path(gVar.f2166b);
            this.f2167c = new Path(gVar.f2167c);
            this.j = gVar.j;
            this.k = gVar.k;
            this.l = gVar.l;
            this.m = gVar.m;
            this.f2172h = gVar.f2172h;
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
        public final Matrix f2152a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ArrayList<e> f2153b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f2154c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f2155d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f2156e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f2157f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f2158g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f2159h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f2160i;
        public final Matrix j;
        public int k;
        public int[] l;
        public String m;

        public d(d dVar, b.d.a<String, Object> aVar) {
            f bVar;
            super();
            this.f2152a = new Matrix();
            this.f2153b = new ArrayList<>();
            this.f2154c = 0.0f;
            this.f2155d = 0.0f;
            this.f2156e = 0.0f;
            this.f2157f = 1.0f;
            this.f2158g = 1.0f;
            this.f2159h = 0.0f;
            this.f2160i = 0.0f;
            Matrix matrix = new Matrix();
            this.j = matrix;
            this.m = null;
            this.f2154c = dVar.f2154c;
            this.f2155d = dVar.f2155d;
            this.f2156e = dVar.f2156e;
            this.f2157f = dVar.f2157f;
            this.f2158g = dVar.f2158g;
            this.f2159h = dVar.f2159h;
            this.f2160i = dVar.f2160i;
            this.l = dVar.l;
            String str = dVar.m;
            this.m = str;
            this.k = dVar.k;
            if (str != null) {
                aVar.put(str, this);
            }
            matrix.set(dVar.j);
            ArrayList<e> arrayList = dVar.f2153b;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                e eVar = arrayList.get(i2);
                if (eVar instanceof d) {
                    this.f2153b.add(new d((d) eVar, aVar));
                } else {
                    if (eVar instanceof c) {
                        bVar = new c((c) eVar);
                    } else {
                        if (!(eVar instanceof b)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        bVar = new b((b) eVar);
                    }
                    this.f2153b.add(bVar);
                    String str2 = bVar.f2162b;
                    if (str2 != null) {
                        aVar.put(str2, bVar);
                    }
                }
            }
        }

        @Override // b.p.a.a.h.e
        public boolean a() {
            for (int i2 = 0; i2 < this.f2153b.size(); i2++) {
                if (this.f2153b.get(i2).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // b.p.a.a.h.e
        public boolean b(int[] iArr) {
            boolean zB = false;
            for (int i2 = 0; i2 < this.f2153b.size(); i2++) {
                zB |= this.f2153b.get(i2).b(iArr);
            }
            return zB;
        }

        public void c(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = b.f.f.c.g.k(resources, theme, attributeSet, b.p.a.a.a.f2125b);
            e(typedArrayK, xmlPullParser);
            typedArrayK.recycle();
        }

        public final void d() {
            this.j.reset();
            this.j.postTranslate(-this.f2155d, -this.f2156e);
            this.j.postScale(this.f2157f, this.f2158g);
            this.j.postRotate(this.f2154c, 0.0f, 0.0f);
            this.j.postTranslate(this.f2159h + this.f2155d, this.f2160i + this.f2156e);
        }

        public final void e(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.l = null;
            this.f2154c = b.f.f.c.g.f(typedArray, xmlPullParser, "rotation", 5, this.f2154c);
            this.f2155d = typedArray.getFloat(1, this.f2155d);
            this.f2156e = typedArray.getFloat(2, this.f2156e);
            this.f2157f = b.f.f.c.g.f(typedArray, xmlPullParser, "scaleX", 3, this.f2157f);
            this.f2158g = b.f.f.c.g.f(typedArray, xmlPullParser, "scaleY", 4, this.f2158g);
            this.f2159h = b.f.f.c.g.f(typedArray, xmlPullParser, "translateX", 6, this.f2159h);
            this.f2160i = b.f.f.c.g.f(typedArray, xmlPullParser, "translateY", 7, this.f2160i);
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
            return this.f2155d;
        }

        public float getPivotY() {
            return this.f2156e;
        }

        public float getRotation() {
            return this.f2154c;
        }

        public float getScaleX() {
            return this.f2157f;
        }

        public float getScaleY() {
            return this.f2158g;
        }

        public float getTranslateX() {
            return this.f2159h;
        }

        public float getTranslateY() {
            return this.f2160i;
        }

        public void setPivotX(float f2) {
            if (f2 != this.f2155d) {
                this.f2155d = f2;
                d();
            }
        }

        public void setPivotY(float f2) {
            if (f2 != this.f2156e) {
                this.f2156e = f2;
                d();
            }
        }

        public void setRotation(float f2) {
            if (f2 != this.f2154c) {
                this.f2154c = f2;
                d();
            }
        }

        public void setScaleX(float f2) {
            if (f2 != this.f2157f) {
                this.f2157f = f2;
                d();
            }
        }

        public void setScaleY(float f2) {
            if (f2 != this.f2158g) {
                this.f2158g = f2;
                d();
            }
        }

        public void setTranslateX(float f2) {
            if (f2 != this.f2159h) {
                this.f2159h = f2;
                d();
            }
        }

        public void setTranslateY(float f2) {
            if (f2 != this.f2160i) {
                this.f2160i = f2;
                d();
            }
        }

        public d() {
            super();
            this.f2152a = new Matrix();
            this.f2153b = new ArrayList<>();
            this.f2154c = 0.0f;
            this.f2155d = 0.0f;
            this.f2156e = 0.0f;
            this.f2157f = 1.0f;
            this.f2158g = 1.0f;
            this.f2159h = 0.0f;
            this.f2160i = 0.0f;
            this.j = new Matrix();
            this.m = null;
        }
    }
}
