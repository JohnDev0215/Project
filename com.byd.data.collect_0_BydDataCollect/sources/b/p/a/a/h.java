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
import b.g.d.c;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: VectorDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends b.p.a.a.g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final PorterDuff.Mode f2967c = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private C0059h f2968d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private PorterDuffColorFilter f2969e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ColorFilter f2970f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2971g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2972h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Drawable.ConstantState f2973i;
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
                this.f2989b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f2988a = b.g.d.c.d(string2);
            }
            this.f2990c = androidx.core.content.c.g.g(typedArray, xmlPullParser, "fillType", 2, 0);
        }

        @Override // b.p.a.a.h.f
        public boolean c() {
            return true;
        }

        public void e(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (androidx.core.content.c.g.j(xmlPullParser, "pathData")) {
                TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, b.p.a.a.a.f2943d);
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

    /* JADX INFO: renamed from: b.p.a.a.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static class C0059h extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f3001a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        g f3002b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ColorStateList f3003c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        PorterDuff.Mode f3004d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f3005e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        Bitmap f3006f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        ColorStateList f3007g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        PorterDuff.Mode f3008h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        int f3009i;
        boolean j;
        boolean k;
        Paint l;

        public C0059h(C0059h c0059h) {
            this.f3003c = null;
            this.f3004d = h.f2967c;
            if (c0059h != null) {
                this.f3001a = c0059h.f3001a;
                g gVar = new g(c0059h.f3002b);
                this.f3002b = gVar;
                Paint paint = c0059h.f3002b.f2997f;
                if (paint != null) {
                    gVar.f2997f = new Paint(paint);
                }
                Paint paint2 = c0059h.f3002b.f2996e;
                if (paint2 != null) {
                    this.f3002b.f2996e = new Paint(paint2);
                }
                this.f3003c = c0059h.f3003c;
                this.f3004d = c0059h.f3004d;
                this.f3005e = c0059h.f3005e;
            }
        }

        public boolean a(int i2, int i3) {
            return i2 == this.f3006f.getWidth() && i3 == this.f3006f.getHeight();
        }

        public boolean b() {
            return !this.k && this.f3007g == this.f3003c && this.f3008h == this.f3004d && this.j == this.f3005e && this.f3009i == this.f3002b.getRootAlpha();
        }

        public void c(int i2, int i3) {
            if (this.f3006f == null || !a(i2, i3)) {
                this.f3006f = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
                this.k = true;
            }
        }

        public void d(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f3006f, (Rect) null, rect, e(colorFilter));
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
            this.l.setAlpha(this.f3002b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public boolean f() {
            return this.f3002b.getRootAlpha() < 255;
        }

        public boolean g() {
            return this.f3002b.f();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f3001a;
        }

        public boolean h(int[] iArr) {
            boolean zG = this.f3002b.g(iArr);
            this.k |= zG;
            return zG;
        }

        public void i() {
            this.f3007g = this.f3003c;
            this.f3008h = this.f3004d;
            this.f3009i = this.f3002b.getRootAlpha();
            this.j = this.f3005e;
            this.k = false;
        }

        public void j(int i2, int i3) {
            this.f3006f.eraseColor(0);
            this.f3002b.b(new Canvas(this.f3006f), i2, i3, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new h(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new h(this);
        }

        public C0059h() {
            this.f3003c = null;
            this.f3004d = h.f2967c;
            this.f3002b = new g();
        }
    }

    h() {
        this.f2972h = true;
        this.j = new float[9];
        this.k = new Matrix();
        this.l = new Rect();
        this.f2968d = new C0059h();
    }

    static int a(int i2, float f2) {
        return (i2 & 16777215) | (((int) (Color.alpha(i2) * f2)) << 24);
    }

    public static h b(Resources resources, int i2, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            h hVar = new h();
            Drawable drawableA = androidx.core.content.c.f.a(resources, i2, theme);
            hVar.f2966b = drawableA;
            hVar.f2973i = new i(drawableA.getConstantState());
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
        C0059h c0059h = this.f2968d;
        g gVar = c0059h.f3002b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(gVar.f3000i);
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
                    dVar.f2980b.add(cVar);
                    if (cVar.getPathName() != null) {
                        gVar.q.put(cVar.getPathName(), cVar);
                    }
                    z = false;
                    c0059h.f3001a = cVar.f2991d | c0059h.f3001a;
                } else if ("clip-path".equals(name)) {
                    b bVar = new b();
                    bVar.e(resources, attributeSet, theme, xmlPullParser);
                    dVar.f2980b.add(bVar);
                    if (bVar.getPathName() != null) {
                        gVar.q.put(bVar.getPathName(), bVar);
                    }
                    c0059h.f3001a = bVar.f2991d | c0059h.f3001a;
                } else if ("group".equals(name)) {
                    d dVar2 = new d();
                    dVar2.c(resources, attributeSet, theme, xmlPullParser);
                    dVar.f2980b.add(dVar2);
                    arrayDeque.push(dVar2);
                    if (dVar2.getGroupName() != null) {
                        gVar.q.put(dVar2.getGroupName(), dVar2);
                    }
                    c0059h.f3001a = dVar2.k | c0059h.f3001a;
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
        C0059h c0059h = this.f2968d;
        g gVar = c0059h.f3002b;
        c0059h.f3004d = g(androidx.core.content.c.g.g(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateListC = androidx.core.content.c.g.c(typedArray, xmlPullParser, theme, "tint", 1);
        if (colorStateListC != null) {
            c0059h.f3003c = colorStateListC;
        }
        c0059h.f3005e = androidx.core.content.c.g.a(typedArray, xmlPullParser, "autoMirrored", 5, c0059h.f3005e);
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

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f2966b;
        if (drawable == null) {
            return false;
        }
        androidx.core.graphics.drawable.a.b(drawable);
        return false;
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    Object d(String str) {
        return this.f2968d.f3002b.q.get(str);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.l);
        if (this.l.width() <= 0 || this.l.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f2970f;
        if (colorFilter == null) {
            colorFilter = this.f2969e;
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
        this.f2968d.c(iMin, iMin2);
        if (!this.f2972h) {
            this.f2968d.j(iMin, iMin2);
        } else if (!this.f2968d.b()) {
            this.f2968d.j(iMin, iMin2);
            this.f2968d.i();
        }
        this.f2968d.d(canvas, colorFilter, this.l);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f2966b;
        return drawable != null ? androidx.core.graphics.drawable.a.d(drawable) : this.f2968d.f3002b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f2966b;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f2968d.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f2966b;
        return drawable != null ? androidx.core.graphics.drawable.a.e(drawable) : this.f2970f;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f2966b;
        if (drawable != null && Build.VERSION.SDK_INT >= 24) {
            return new i(drawable.getConstantState());
        }
        this.f2968d.f3001a = getChangingConfigurations();
        return this.f2968d;
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f2966b;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f2968d.f3002b.k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f2966b;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f2968d.f3002b.j;
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
        Drawable drawable = this.f2966b;
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

    void h(boolean z) {
        this.f2972h = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f2966b;
        return drawable != null ? androidx.core.graphics.drawable.a.h(drawable) : this.f2968d.f3005e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        C0059h c0059h;
        ColorStateList colorStateList;
        Drawable drawable = this.f2966b;
        return drawable != null ? drawable.isStateful() : super.isStateful() || ((c0059h = this.f2968d) != null && (c0059h.g() || ((colorStateList = this.f2968d.f3003c) != null && colorStateList.isStateful())));
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f2971g && super.mutate() == this) {
            this.f2968d = new C0059h(this.f2968d);
            this.f2971g = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        C0059h c0059h = this.f2968d;
        ColorStateList colorStateList = c0059h.f3003c;
        if (colorStateList != null && (mode = c0059h.f3004d) != null) {
            this.f2969e = updateTintFilter(this.f2969e, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (!c0059h.g() || !c0059h.h(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else if (this.f2968d.f3002b.getRootAlpha() != i2) {
            this.f2968d.f3002b.setRootAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.j(drawable, z);
        } else {
            this.f2968d.f3005e = z;
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

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i2) {
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.n(drawable, i2);
        } else {
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.o(drawable, colorStateList);
            return;
        }
        C0059h c0059h = this.f2968d;
        if (c0059h.f3003c != colorStateList) {
            c0059h.f3003c = colorStateList;
            this.f2969e = updateTintFilter(this.f2969e, colorStateList, c0059h.f3004d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.p(drawable, mode);
            return;
        }
        C0059h c0059h = this.f2968d;
        if (c0059h.f3004d != mode) {
            c0059h.f3004d = mode;
            this.f2969e = updateTintFilter(this.f2969e, c0059h.f3003c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f2966b;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f2966b;
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
        private final Drawable.ConstantState f3010a;

        public i(Drawable.ConstantState constantState) {
            this.f3010a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f3010a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f3010a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVar = new h();
            hVar.f2966b = (VectorDrawable) this.f3010a.newDrawable();
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            h hVar = new h();
            hVar.f2966b = (VectorDrawable) this.f3010a.newDrawable(resources);
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            h hVar = new h();
            hVar.f2966b = (VectorDrawable) this.f3010a.newDrawable(resources, theme);
            return hVar;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f2970f = colorFilter;
            invalidateSelf();
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static abstract class f extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected c.b[] f2988a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f2989b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2990c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2991d;

        public f() {
            super();
            this.f2988a = null;
            this.f2990c = 0;
        }

        public boolean c() {
            return false;
        }

        public void d(Path path) {
            path.reset();
            c.b[] bVarArr = this.f2988a;
            if (bVarArr != null) {
                c.b.e(bVarArr, path);
            }
        }

        public c.b[] getPathData() {
            return this.f2988a;
        }

        public String getPathName() {
            return this.f2989b;
        }

        public void setPathData(c.b[] bVarArr) {
            if (b.g.d.c.b(this.f2988a, bVarArr)) {
                b.g.d.c.j(this.f2988a, bVarArr);
            } else {
                this.f2988a = b.g.d.c.f(bVarArr);
            }
        }

        public f(f fVar) {
            super();
            this.f2988a = null;
            this.f2990c = 0;
            this.f2989b = fVar.f2989b;
            this.f2991d = fVar.f2991d;
            this.f2988a = b.g.d.c.f(fVar.f2988a);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f2966b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        C0059h c0059h = this.f2968d;
        c0059h.f3002b = new g();
        TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, b.p.a.a.a.f2940a);
        i(typedArrayK, xmlPullParser, theme);
        typedArrayK.recycle();
        c0059h.f3001a = getChangingConfigurations();
        c0059h.k = true;
        e(resources, xmlPullParser, attributeSet, theme);
        this.f2969e = updateTintFilter(this.f2969e, c0059h.f3003c, c0059h.f3004d);
    }

    h(C0059h c0059h) {
        this.f2972h = true;
        this.j = new float[9];
        this.k = new Matrix();
        this.l = new Rect();
        this.f2968d = c0059h;
        this.f2969e = updateTintFilter(this.f2969e, c0059h.f3003c, c0059h.f3004d);
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static class c extends f {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int[] f2974e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        androidx.core.content.c.b f2975f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        float f2976g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        androidx.core.content.c.b f2977h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        float f2978i;
        float j;
        float k;
        float l;
        float m;
        Paint.Cap n;
        Paint.Join o;
        float p;

        c() {
            this.f2976g = 0.0f;
            this.f2978i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
        }

        private Paint.Cap e(int i2, Paint.Cap cap) {
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        private Paint.Join f(int i2, Paint.Join join) {
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? join : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER;
        }

        private void h(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.f2974e = null;
            if (androidx.core.content.c.g.j(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f2989b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f2988a = b.g.d.c.d(string2);
                }
                this.f2977h = androidx.core.content.c.g.e(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.j = androidx.core.content.c.g.f(typedArray, xmlPullParser, "fillAlpha", 12, this.j);
                this.n = e(androidx.core.content.c.g.g(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.n);
                this.o = f(androidx.core.content.c.g.g(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.o);
                this.p = androidx.core.content.c.g.f(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.p);
                this.f2975f = androidx.core.content.c.g.e(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.f2978i = androidx.core.content.c.g.f(typedArray, xmlPullParser, "strokeAlpha", 11, this.f2978i);
                this.f2976g = androidx.core.content.c.g.f(typedArray, xmlPullParser, "strokeWidth", 4, this.f2976g);
                this.l = androidx.core.content.c.g.f(typedArray, xmlPullParser, "trimPathEnd", 6, this.l);
                this.m = androidx.core.content.c.g.f(typedArray, xmlPullParser, "trimPathOffset", 7, this.m);
                this.k = androidx.core.content.c.g.f(typedArray, xmlPullParser, "trimPathStart", 5, this.k);
                this.f2990c = androidx.core.content.c.g.g(typedArray, xmlPullParser, "fillType", 13, this.f2990c);
            }
        }

        @Override // b.p.a.a.h.e
        public boolean a() {
            return this.f2977h.i() || this.f2975f.i();
        }

        @Override // b.p.a.a.h.e
        public boolean b(int[] iArr) {
            return this.f2975f.j(iArr) | this.f2977h.j(iArr);
        }

        public void g(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, b.p.a.a.a.f2942c);
            h(typedArrayK, xmlPullParser, theme);
            typedArrayK.recycle();
        }

        float getFillAlpha() {
            return this.j;
        }

        int getFillColor() {
            return this.f2977h.e();
        }

        float getStrokeAlpha() {
            return this.f2978i;
        }

        int getStrokeColor() {
            return this.f2975f.e();
        }

        float getStrokeWidth() {
            return this.f2976g;
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

        void setFillColor(int i2) {
            this.f2977h.k(i2);
        }

        void setStrokeAlpha(float f2) {
            this.f2978i = f2;
        }

        void setStrokeColor(int i2) {
            this.f2975f.k(i2);
        }

        void setStrokeWidth(float f2) {
            this.f2976g = f2;
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
            this.f2976g = 0.0f;
            this.f2978i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
            this.f2974e = cVar.f2974e;
            this.f2975f = cVar.f2975f;
            this.f2976g = cVar.f2976g;
            this.f2978i = cVar.f2978i;
            this.f2977h = cVar.f2977h;
            this.f2990c = cVar.f2990c;
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
        private static final Matrix f2992a = new Matrix();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Path f2993b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Path f2994c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Matrix f2995d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Paint f2996e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        Paint f2997f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private PathMeasure f2998g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f2999h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final d f3000i;
        float j;
        float k;
        float l;
        float m;
        int n;
        String o;
        Boolean p;
        final b.d.a<String, Object> q;

        public g() {
            this.f2995d = new Matrix();
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 255;
            this.o = null;
            this.p = null;
            this.q = new b.d.a<>();
            this.f3000i = new d();
            this.f2993b = new Path();
            this.f2994c = new Path();
        }

        private static float a(float f2, float f3, float f4, float f5) {
            return (f2 * f5) - (f3 * f4);
        }

        private void c(d dVar, Matrix matrix, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            dVar.f2979a.set(matrix);
            dVar.f2979a.preConcat(dVar.j);
            canvas.save();
            for (int i4 = 0; i4 < dVar.f2980b.size(); i4++) {
                e eVar = dVar.f2980b.get(i4);
                if (eVar instanceof d) {
                    c((d) eVar, dVar.f2979a, canvas, i2, i3, colorFilter);
                } else if (eVar instanceof f) {
                    d(dVar, (f) eVar, canvas, i2, i3, colorFilter);
                }
            }
            canvas.restore();
        }

        private void d(d dVar, f fVar, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            float f2 = i2 / this.l;
            float f3 = i3 / this.m;
            float fMin = Math.min(f2, f3);
            Matrix matrix = dVar.f2979a;
            this.f2995d.set(matrix);
            this.f2995d.postScale(f2, f3);
            float fE = e(matrix);
            if (fE == 0.0f) {
                return;
            }
            fVar.d(this.f2993b);
            Path path = this.f2993b;
            this.f2994c.reset();
            if (fVar.c()) {
                this.f2994c.setFillType(fVar.f2990c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.f2994c.addPath(path, this.f2995d);
                canvas.clipPath(this.f2994c);
                return;
            }
            c cVar = (c) fVar;
            float f4 = cVar.k;
            if (f4 != 0.0f || cVar.l != 1.0f) {
                float f5 = cVar.m;
                float f6 = (f4 + f5) % 1.0f;
                float f7 = (cVar.l + f5) % 1.0f;
                if (this.f2998g == null) {
                    this.f2998g = new PathMeasure();
                }
                this.f2998g.setPath(this.f2993b, false);
                float length = this.f2998g.getLength();
                float f8 = f6 * length;
                float f9 = f7 * length;
                path.reset();
                if (f8 > f9) {
                    this.f2998g.getSegment(f8, length, path, true);
                    this.f2998g.getSegment(0.0f, f9, path, true);
                } else {
                    this.f2998g.getSegment(f8, f9, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.f2994c.addPath(path, this.f2995d);
            if (cVar.f2977h.l()) {
                androidx.core.content.c.b bVar = cVar.f2977h;
                if (this.f2997f == null) {
                    Paint paint = new Paint(1);
                    this.f2997f = paint;
                    paint.setStyle(Paint.Style.FILL);
                }
                Paint paint2 = this.f2997f;
                if (bVar.h()) {
                    Shader shaderF = bVar.f();
                    shaderF.setLocalMatrix(this.f2995d);
                    paint2.setShader(shaderF);
                    paint2.setAlpha(Math.round(cVar.j * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(h.a(bVar.e(), cVar.j));
                }
                paint2.setColorFilter(colorFilter);
                this.f2994c.setFillType(cVar.f2990c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.f2994c, paint2);
            }
            if (cVar.f2975f.l()) {
                androidx.core.content.c.b bVar2 = cVar.f2975f;
                if (this.f2996e == null) {
                    Paint paint3 = new Paint(1);
                    this.f2996e = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                }
                Paint paint4 = this.f2996e;
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
                    shaderF2.setLocalMatrix(this.f2995d);
                    paint4.setShader(shaderF2);
                    paint4.setAlpha(Math.round(cVar.f2978i * 255.0f));
                } else {
                    paint4.setShader(null);
                    paint4.setAlpha(255);
                    paint4.setColor(h.a(bVar2.e(), cVar.f2978i));
                }
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(cVar.f2976g * fMin * fE);
                canvas.drawPath(this.f2994c, paint4);
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

        public void b(Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            c(this.f3000i, f2992a, canvas, i2, i3, colorFilter);
        }

        public boolean f() {
            if (this.p == null) {
                this.p = Boolean.valueOf(this.f3000i.a());
            }
            return this.p.booleanValue();
        }

        public boolean g(int[] iArr) {
            return this.f3000i.b(iArr);
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
            this.f2995d = new Matrix();
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 255;
            this.o = null;
            this.p = null;
            b.d.a<String, Object> aVar = new b.d.a<>();
            this.q = aVar;
            this.f3000i = new d(gVar.f3000i, aVar);
            this.f2993b = new Path(gVar.f2993b);
            this.f2994c = new Path(gVar.f2994c);
            this.j = gVar.j;
            this.k = gVar.k;
            this.l = gVar.l;
            this.m = gVar.m;
            this.f2999h = gVar.f2999h;
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
        final Matrix f2979a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ArrayList<e> f2980b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f2981c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f2982d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private float f2983e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private float f2984f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private float f2985g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private float f2986h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private float f2987i;
        final Matrix j;
        int k;
        private int[] l;
        private String m;

        public d(d dVar, b.d.a<String, Object> aVar) {
            f bVar;
            super();
            this.f2979a = new Matrix();
            this.f2980b = new ArrayList<>();
            this.f2981c = 0.0f;
            this.f2982d = 0.0f;
            this.f2983e = 0.0f;
            this.f2984f = 1.0f;
            this.f2985g = 1.0f;
            this.f2986h = 0.0f;
            this.f2987i = 0.0f;
            Matrix matrix = new Matrix();
            this.j = matrix;
            this.m = null;
            this.f2981c = dVar.f2981c;
            this.f2982d = dVar.f2982d;
            this.f2983e = dVar.f2983e;
            this.f2984f = dVar.f2984f;
            this.f2985g = dVar.f2985g;
            this.f2986h = dVar.f2986h;
            this.f2987i = dVar.f2987i;
            this.l = dVar.l;
            String str = dVar.m;
            this.m = str;
            this.k = dVar.k;
            if (str != null) {
                aVar.put(str, this);
            }
            matrix.set(dVar.j);
            ArrayList<e> arrayList = dVar.f2980b;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                e eVar = arrayList.get(i2);
                if (eVar instanceof d) {
                    this.f2980b.add(new d((d) eVar, aVar));
                } else {
                    if (eVar instanceof c) {
                        bVar = new c((c) eVar);
                    } else {
                        if (!(eVar instanceof b)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        bVar = new b((b) eVar);
                    }
                    this.f2980b.add(bVar);
                    String str2 = bVar.f2989b;
                    if (str2 != null) {
                        aVar.put(str2, bVar);
                    }
                }
            }
        }

        private void d() {
            this.j.reset();
            this.j.postTranslate(-this.f2982d, -this.f2983e);
            this.j.postScale(this.f2984f, this.f2985g);
            this.j.postRotate(this.f2981c, 0.0f, 0.0f);
            this.j.postTranslate(this.f2986h + this.f2982d, this.f2987i + this.f2983e);
        }

        private void e(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.l = null;
            this.f2981c = androidx.core.content.c.g.f(typedArray, xmlPullParser, "rotation", 5, this.f2981c);
            this.f2982d = typedArray.getFloat(1, this.f2982d);
            this.f2983e = typedArray.getFloat(2, this.f2983e);
            this.f2984f = androidx.core.content.c.g.f(typedArray, xmlPullParser, "scaleX", 3, this.f2984f);
            this.f2985g = androidx.core.content.c.g.f(typedArray, xmlPullParser, "scaleY", 4, this.f2985g);
            this.f2986h = androidx.core.content.c.g.f(typedArray, xmlPullParser, "translateX", 6, this.f2986h);
            this.f2987i = androidx.core.content.c.g.f(typedArray, xmlPullParser, "translateY", 7, this.f2987i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.m = string;
            }
            d();
        }

        @Override // b.p.a.a.h.e
        public boolean a() {
            for (int i2 = 0; i2 < this.f2980b.size(); i2++) {
                if (this.f2980b.get(i2).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // b.p.a.a.h.e
        public boolean b(int[] iArr) {
            boolean zB = false;
            for (int i2 = 0; i2 < this.f2980b.size(); i2++) {
                zB |= this.f2980b.get(i2).b(iArr);
            }
            return zB;
        }

        public void c(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, b.p.a.a.a.f2941b);
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
            return this.f2982d;
        }

        public float getPivotY() {
            return this.f2983e;
        }

        public float getRotation() {
            return this.f2981c;
        }

        public float getScaleX() {
            return this.f2984f;
        }

        public float getScaleY() {
            return this.f2985g;
        }

        public float getTranslateX() {
            return this.f2986h;
        }

        public float getTranslateY() {
            return this.f2987i;
        }

        public void setPivotX(float f2) {
            if (f2 != this.f2982d) {
                this.f2982d = f2;
                d();
            }
        }

        public void setPivotY(float f2) {
            if (f2 != this.f2983e) {
                this.f2983e = f2;
                d();
            }
        }

        public void setRotation(float f2) {
            if (f2 != this.f2981c) {
                this.f2981c = f2;
                d();
            }
        }

        public void setScaleX(float f2) {
            if (f2 != this.f2984f) {
                this.f2984f = f2;
                d();
            }
        }

        public void setScaleY(float f2) {
            if (f2 != this.f2985g) {
                this.f2985g = f2;
                d();
            }
        }

        public void setTranslateX(float f2) {
            if (f2 != this.f2986h) {
                this.f2986h = f2;
                d();
            }
        }

        public void setTranslateY(float f2) {
            if (f2 != this.f2987i) {
                this.f2987i = f2;
                d();
            }
        }

        public d() {
            super();
            this.f2979a = new Matrix();
            this.f2980b = new ArrayList<>();
            this.f2981c = 0.0f;
            this.f2982d = 0.0f;
            this.f2983e = 0.0f;
            this.f2984f = 1.0f;
            this.f2985g = 1.0f;
            this.f2986h = 0.0f;
            this.f2987i = 0.0f;
            this.j = new Matrix();
            this.m = null;
        }
    }
}
