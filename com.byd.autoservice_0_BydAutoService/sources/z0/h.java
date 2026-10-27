package z0;

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
import d0.c;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: VectorDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends z0.g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final PorterDuff.Mode f6041c = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public C0079h f6042d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public PorterDuffColorFilter f6043e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorFilter f6044f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f6045g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f6046h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Drawable.ConstantState f6047i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float[] f6048j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Matrix f6049k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Rect f6050l;

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class b extends f {
        public b() {
        }

        @Override // z0.h.f
        public boolean c() {
            return true;
        }

        public void e(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (c0.g.j(xmlPullParser, "pathData")) {
                TypedArray typedArrayK = c0.g.k(resources, theme, attributeSet, z0.a.f6014d);
                f(typedArrayK, xmlPullParser);
                typedArrayK.recycle();
            }
        }

        public final void f(TypedArray typedArray, XmlPullParser xmlPullParser) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.f6077b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f6076a = d0.c.d(string2);
            }
            this.f6078c = c0.g.g(typedArray, xmlPullParser, "fillType", 2, 0);
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

    /* JADX INFO: renamed from: z0.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class C0079h extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6097a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public g f6098b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorStateList f6099c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public PorterDuff.Mode f6100d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f6101e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Bitmap f6102f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ColorStateList f6103g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public PorterDuff.Mode f6104h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f6105i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f6106j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public boolean f6107k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public Paint f6108l;

        public C0079h(C0079h c0079h) {
            this.f6099c = null;
            this.f6100d = h.f6041c;
            if (c0079h != null) {
                this.f6097a = c0079h.f6097a;
                g gVar = new g(c0079h.f6098b);
                this.f6098b = gVar;
                Paint paint = c0079h.f6098b.f6085f;
                if (paint != null) {
                    gVar.f6085f = new Paint(paint);
                }
                Paint paint2 = c0079h.f6098b.f6084e;
                if (paint2 != null) {
                    this.f6098b.f6084e = new Paint(paint2);
                }
                this.f6099c = c0079h.f6099c;
                this.f6100d = c0079h.f6100d;
                this.f6101e = c0079h.f6101e;
            }
        }

        public boolean a(int i3, int i4) {
            return i3 == this.f6102f.getWidth() && i4 == this.f6102f.getHeight();
        }

        public boolean b() {
            return !this.f6107k && this.f6103g == this.f6099c && this.f6104h == this.f6100d && this.f6106j == this.f6101e && this.f6105i == this.f6098b.getRootAlpha();
        }

        public void c(int i3, int i4) {
            if (this.f6102f == null || !a(i3, i4)) {
                this.f6102f = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
                this.f6107k = true;
            }
        }

        public void d(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f6102f, (Rect) null, rect, e(colorFilter));
        }

        public Paint e(ColorFilter colorFilter) {
            if (!f() && colorFilter == null) {
                return null;
            }
            if (this.f6108l == null) {
                Paint paint = new Paint();
                this.f6108l = paint;
                paint.setFilterBitmap(true);
            }
            this.f6108l.setAlpha(this.f6098b.getRootAlpha());
            this.f6108l.setColorFilter(colorFilter);
            return this.f6108l;
        }

        public boolean f() {
            return this.f6098b.getRootAlpha() < 255;
        }

        public boolean g() {
            return this.f6098b.f();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f6097a;
        }

        public boolean h(int[] iArr) {
            boolean zG = this.f6098b.g(iArr);
            this.f6107k |= zG;
            return zG;
        }

        public void i() {
            this.f6103g = this.f6099c;
            this.f6104h = this.f6100d;
            this.f6105i = this.f6098b.getRootAlpha();
            this.f6106j = this.f6101e;
            this.f6107k = false;
        }

        public void j(int i3, int i4) {
            this.f6102f.eraseColor(0);
            this.f6098b.b(new Canvas(this.f6102f), i3, i4, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new h(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new h(this);
        }

        public C0079h() {
            this.f6099c = null;
            this.f6100d = h.f6041c;
            this.f6098b = new g();
        }
    }

    public h() {
        this.f6046h = true;
        this.f6048j = new float[9];
        this.f6049k = new Matrix();
        this.f6050l = new Rect();
        this.f6042d = new C0079h();
    }

    public static int a(int i3, float f3) {
        return (i3 & 16777215) | (((int) (Color.alpha(i3) * f3)) << 24);
    }

    public static h b(Resources resources, int i3, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            h hVar = new h();
            Drawable drawableA = c0.f.a(resources, i3, theme);
            hVar.f6040b = drawableA;
            hVar.f6047i = new i(drawableA.getConstantState());
            return hVar;
        }
        try {
            XmlResourceParser xml = resources.getXml(i3);
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
        } catch (IOException e3) {
            Log.e("VectorDrawableCompat", "parser error", e3);
            return null;
        } catch (XmlPullParserException e4) {
            Log.e("VectorDrawableCompat", "parser error", e4);
            return null;
        }
    }

    public static h c(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        h hVar = new h();
        hVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return hVar;
    }

    public static PorterDuff.Mode g(int i3, PorterDuff.Mode mode) {
        if (i3 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i3 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i3 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i3) {
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

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f6040b;
        if (drawable == null) {
            return false;
        }
        e0.a.b(drawable);
        return false;
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public Object d(String str) {
        return this.f6042d.f6098b.f6096q.get(str);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.f6050l);
        if (this.f6050l.width() <= 0 || this.f6050l.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f6044f;
        if (colorFilter == null) {
            colorFilter = this.f6043e;
        }
        canvas.getMatrix(this.f6049k);
        this.f6049k.getValues(this.f6048j);
        float fAbs = Math.abs(this.f6048j[0]);
        float fAbs2 = Math.abs(this.f6048j[4]);
        float fAbs3 = Math.abs(this.f6048j[1]);
        float fAbs4 = Math.abs(this.f6048j[3]);
        if (fAbs3 != 0.0f || fAbs4 != 0.0f) {
            fAbs = 1.0f;
            fAbs2 = 1.0f;
        }
        int iMin = Math.min(2048, (int) (this.f6050l.width() * fAbs));
        int iMin2 = Math.min(2048, (int) (this.f6050l.height() * fAbs2));
        if (iMin <= 0 || iMin2 <= 0) {
            return;
        }
        int iSave = canvas.save();
        Rect rect = this.f6050l;
        canvas.translate(rect.left, rect.top);
        if (f()) {
            canvas.translate(this.f6050l.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.f6050l.offsetTo(0, 0);
        this.f6042d.c(iMin, iMin2);
        if (!this.f6046h) {
            this.f6042d.j(iMin, iMin2);
        } else if (!this.f6042d.b()) {
            this.f6042d.j(iMin, iMin2);
            this.f6042d.i();
        }
        this.f6042d.d(canvas, colorFilter, this.f6050l);
        canvas.restoreToCount(iSave);
    }

    public final void e(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        C0079h c0079h = this.f6042d;
        g gVar = c0079h.f6098b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(gVar.f6088i);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z3 = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                d dVar = (d) arrayDeque.peek();
                if ("path".equals(name)) {
                    c cVar = new c();
                    cVar.g(resources, attributeSet, theme, xmlPullParser);
                    dVar.f6064b.add(cVar);
                    if (cVar.getPathName() != null) {
                        gVar.f6096q.put(cVar.getPathName(), cVar);
                    }
                    z3 = false;
                    c0079h.f6097a = cVar.f6079d | c0079h.f6097a;
                } else if ("clip-path".equals(name)) {
                    b bVar = new b();
                    bVar.e(resources, attributeSet, theme, xmlPullParser);
                    dVar.f6064b.add(bVar);
                    if (bVar.getPathName() != null) {
                        gVar.f6096q.put(bVar.getPathName(), bVar);
                    }
                    c0079h.f6097a = bVar.f6079d | c0079h.f6097a;
                } else if ("group".equals(name)) {
                    d dVar2 = new d();
                    dVar2.c(resources, attributeSet, theme, xmlPullParser);
                    dVar.f6064b.add(dVar2);
                    arrayDeque.push(dVar2);
                    if (dVar2.getGroupName() != null) {
                        gVar.f6096q.put(dVar2.getGroupName(), dVar2);
                    }
                    c0079h.f6097a = dVar2.f6073k | c0079h.f6097a;
                }
            } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z3) {
            throw new XmlPullParserException("no path defined");
        }
    }

    public final boolean f() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && e0.a.f(this) == 1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f6040b;
        return drawable != null ? e0.a.d(drawable) : this.f6042d.f6098b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f6040b;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f6042d.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f6040b;
        return drawable != null ? e0.a.e(drawable) : this.f6044f;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f6040b;
        if (drawable != null && Build.VERSION.SDK_INT >= 24) {
            return new i(drawable.getConstantState());
        }
        this.f6042d.f6097a = getChangingConfigurations();
        return this.f6042d;
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f6040b;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f6042d.f6098b.f6090k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f6040b;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f6042d.f6098b.f6089j;
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void h(boolean z3) {
        this.f6046h = z3;
    }

    public final void i(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) throws XmlPullParserException {
        C0079h c0079h = this.f6042d;
        g gVar = c0079h.f6098b;
        c0079h.f6100d = g(c0.g.g(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateListC = c0.g.c(typedArray, xmlPullParser, theme, "tint", 1);
        if (colorStateListC != null) {
            c0079h.f6099c = colorStateListC;
        }
        c0079h.f6101e = c0.g.a(typedArray, xmlPullParser, "autoMirrored", 5, c0079h.f6101e);
        gVar.f6091l = c0.g.f(typedArray, xmlPullParser, "viewportWidth", 7, gVar.f6091l);
        float f3 = c0.g.f(typedArray, xmlPullParser, "viewportHeight", 8, gVar.f6092m);
        gVar.f6092m = f3;
        if (gVar.f6091l <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (f3 <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
        gVar.f6089j = typedArray.getDimension(3, gVar.f6089j);
        float dimension = typedArray.getDimension(2, gVar.f6090k);
        gVar.f6090k = dimension;
        if (gVar.f6089j <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
        }
        if (dimension <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
        }
        gVar.setAlpha(c0.g.f(typedArray, xmlPullParser, "alpha", 4, gVar.getAlpha()));
        String string = typedArray.getString(0);
        if (string != null) {
            gVar.f6094o = string;
            gVar.f6096q.put(string, gVar);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f6040b;
        return drawable != null ? e0.a.h(drawable) : this.f6042d.f6101e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        C0079h c0079h;
        ColorStateList colorStateList;
        Drawable drawable = this.f6040b;
        return drawable != null ? drawable.isStateful() : super.isStateful() || ((c0079h = this.f6042d) != null && (c0079h.g() || ((colorStateList = this.f6042d.f6099c) != null && colorStateList.isStateful())));
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f6045g && super.mutate() == this) {
            this.f6042d = new C0079h(this.f6042d);
            this.f6045g = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z3 = false;
        C0079h c0079h = this.f6042d;
        ColorStateList colorStateList = c0079h.f6099c;
        if (colorStateList != null && (mode = c0079h.f6100d) != null) {
            this.f6043e = updateTintFilter(this.f6043e, colorStateList, mode);
            invalidateSelf();
            z3 = true;
        }
        if (!c0079h.g() || !c0079h.h(iArr)) {
            return z3;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j3) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j3);
        } else {
            super.scheduleSelf(runnable, j3);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i3) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            drawable.setAlpha(i3);
        } else if (this.f6042d.f6098b.getRootAlpha() != i3) {
            this.f6042d.f6098b.setRootAlpha(i3);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z3) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            e0.a.j(drawable, z3);
        } else {
            this.f6042d.f6101e = z3;
        }
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i3) {
        super.setChangingConfigurations(i3);
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i3, PorterDuff.Mode mode) {
        super.setColorFilter(i3, mode);
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z3) {
        super.setFilterBitmap(z3);
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f3, float f4) {
        super.setHotspot(f3, f4);
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i3, int i4, int i5, int i6) {
        super.setHotspotBounds(i3, i4, i5, i6);
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTint(int i3) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            e0.a.n(drawable, i3);
        } else {
            setTintList(ColorStateList.valueOf(i3));
        }
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            e0.a.o(drawable, colorStateList);
            return;
        }
        C0079h c0079h = this.f6042d;
        if (c0079h.f6099c != colorStateList) {
            c0079h.f6099c = colorStateList;
            this.f6043e = updateTintFilter(this.f6043e, colorStateList, c0079h.f6100d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            e0.a.p(drawable, mode);
            return;
        }
        C0079h c0079h = this.f6042d;
        if (c0079h.f6100d != mode) {
            c0079h.f6100d = mode;
            this.f6043e = updateTintFilter(this.f6043e, c0079h.f6099c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z3, boolean z4) {
        Drawable drawable = this.f6040b;
        return drawable != null ? drawable.setVisible(z3, z4) : super.setVisible(z3, z4);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f6040b;
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
        public final Drawable.ConstantState f6109a;

        public i(Drawable.ConstantState constantState) {
            this.f6109a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f6109a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f6109a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVar = new h();
            hVar.f6040b = (VectorDrawable) this.f6109a.newDrawable();
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            h hVar = new h();
            hVar.f6040b = (VectorDrawable) this.f6109a.newDrawable(resources);
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            h hVar = new h();
            hVar.f6040b = (VectorDrawable) this.f6109a.newDrawable(resources, theme);
            return hVar;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f6044f = colorFilter;
            invalidateSelf();
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static abstract class f extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c.b[] f6076a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f6077b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f6078c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f6079d;

        public f() {
            super();
            this.f6076a = null;
            this.f6078c = 0;
        }

        public boolean c() {
            return false;
        }

        public void d(Path path) {
            path.reset();
            c.b[] bVarArr = this.f6076a;
            if (bVarArr != null) {
                c.b.e(bVarArr, path);
            }
        }

        public c.b[] getPathData() {
            return this.f6076a;
        }

        public String getPathName() {
            return this.f6077b;
        }

        public void setPathData(c.b[] bVarArr) {
            if (d0.c.b(this.f6076a, bVarArr)) {
                d0.c.j(this.f6076a, bVarArr);
            } else {
                this.f6076a = d0.c.f(bVarArr);
            }
        }

        public f(f fVar) {
            super();
            this.f6076a = null;
            this.f6078c = 0;
            this.f6077b = fVar.f6077b;
            this.f6079d = fVar.f6079d;
            this.f6076a = d0.c.f(fVar.f6076a);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            e0.a.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        C0079h c0079h = this.f6042d;
        c0079h.f6098b = new g();
        TypedArray typedArrayK = c0.g.k(resources, theme, attributeSet, z0.a.f6011a);
        i(typedArrayK, xmlPullParser, theme);
        typedArrayK.recycle();
        c0079h.f6097a = getChangingConfigurations();
        c0079h.f6107k = true;
        e(resources, xmlPullParser, attributeSet, theme);
        this.f6043e = updateTintFilter(this.f6043e, c0079h.f6099c, c0079h.f6100d);
    }

    public h(C0079h c0079h) {
        this.f6046h = true;
        this.f6048j = new float[9];
        this.f6049k = new Matrix();
        this.f6050l = new Rect();
        this.f6042d = c0079h;
        this.f6043e = updateTintFilter(this.f6043e, c0079h.f6099c, c0079h.f6100d);
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class c extends f {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int[] f6051e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public c0.b f6052f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f6053g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public c0.b f6054h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f6055i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f6056j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f6057k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f6058l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public float f6059m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public Paint.Cap f6060n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public Paint.Join f6061o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public float f6062p;

        public c() {
            this.f6053g = 0.0f;
            this.f6055i = 1.0f;
            this.f6056j = 1.0f;
            this.f6057k = 0.0f;
            this.f6058l = 1.0f;
            this.f6059m = 0.0f;
            this.f6060n = Paint.Cap.BUTT;
            this.f6061o = Paint.Join.MITER;
            this.f6062p = 4.0f;
        }

        @Override // z0.h.e
        public boolean a() {
            return this.f6054h.i() || this.f6052f.i();
        }

        @Override // z0.h.e
        public boolean b(int[] iArr) {
            return this.f6052f.j(iArr) | this.f6054h.j(iArr);
        }

        public final Paint.Cap e(int i3, Paint.Cap cap) {
            return i3 != 0 ? i3 != 1 ? i3 != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        public final Paint.Join f(int i3, Paint.Join join) {
            return i3 != 0 ? i3 != 1 ? i3 != 2 ? join : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER;
        }

        public void g(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = c0.g.k(resources, theme, attributeSet, z0.a.f6013c);
            h(typedArrayK, xmlPullParser, theme);
            typedArrayK.recycle();
        }

        public float getFillAlpha() {
            return this.f6056j;
        }

        public int getFillColor() {
            return this.f6054h.e();
        }

        public float getStrokeAlpha() {
            return this.f6055i;
        }

        public int getStrokeColor() {
            return this.f6052f.e();
        }

        public float getStrokeWidth() {
            return this.f6053g;
        }

        public float getTrimPathEnd() {
            return this.f6058l;
        }

        public float getTrimPathOffset() {
            return this.f6059m;
        }

        public float getTrimPathStart() {
            return this.f6057k;
        }

        public final void h(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.f6051e = null;
            if (c0.g.j(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f6077b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f6076a = d0.c.d(string2);
                }
                this.f6054h = c0.g.e(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.f6056j = c0.g.f(typedArray, xmlPullParser, "fillAlpha", 12, this.f6056j);
                this.f6060n = e(c0.g.g(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.f6060n);
                this.f6061o = f(c0.g.g(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.f6061o);
                this.f6062p = c0.g.f(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.f6062p);
                this.f6052f = c0.g.e(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.f6055i = c0.g.f(typedArray, xmlPullParser, "strokeAlpha", 11, this.f6055i);
                this.f6053g = c0.g.f(typedArray, xmlPullParser, "strokeWidth", 4, this.f6053g);
                this.f6058l = c0.g.f(typedArray, xmlPullParser, "trimPathEnd", 6, this.f6058l);
                this.f6059m = c0.g.f(typedArray, xmlPullParser, "trimPathOffset", 7, this.f6059m);
                this.f6057k = c0.g.f(typedArray, xmlPullParser, "trimPathStart", 5, this.f6057k);
                this.f6078c = c0.g.g(typedArray, xmlPullParser, "fillType", 13, this.f6078c);
            }
        }

        public void setFillAlpha(float f3) {
            this.f6056j = f3;
        }

        public void setFillColor(int i3) {
            this.f6054h.k(i3);
        }

        public void setStrokeAlpha(float f3) {
            this.f6055i = f3;
        }

        public void setStrokeColor(int i3) {
            this.f6052f.k(i3);
        }

        public void setStrokeWidth(float f3) {
            this.f6053g = f3;
        }

        public void setTrimPathEnd(float f3) {
            this.f6058l = f3;
        }

        public void setTrimPathOffset(float f3) {
            this.f6059m = f3;
        }

        public void setTrimPathStart(float f3) {
            this.f6057k = f3;
        }

        public c(c cVar) {
            super(cVar);
            this.f6053g = 0.0f;
            this.f6055i = 1.0f;
            this.f6056j = 1.0f;
            this.f6057k = 0.0f;
            this.f6058l = 1.0f;
            this.f6059m = 0.0f;
            this.f6060n = Paint.Cap.BUTT;
            this.f6061o = Paint.Join.MITER;
            this.f6062p = 4.0f;
            this.f6051e = cVar.f6051e;
            this.f6052f = cVar.f6052f;
            this.f6053g = cVar.f6053g;
            this.f6055i = cVar.f6055i;
            this.f6054h = cVar.f6054h;
            this.f6078c = cVar.f6078c;
            this.f6056j = cVar.f6056j;
            this.f6057k = cVar.f6057k;
            this.f6058l = cVar.f6058l;
            this.f6059m = cVar.f6059m;
            this.f6060n = cVar.f6060n;
            this.f6061o = cVar.f6061o;
            this.f6062p = cVar.f6062p;
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Matrix f6080a = new Matrix();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f6081b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Path f6082c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Matrix f6083d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Paint f6084e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Paint f6085f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public PathMeasure f6086g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f6087h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final d f6088i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f6089j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f6090k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f6091l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public float f6092m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f6093n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public String f6094o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public Boolean f6095p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public final s.a<String, Object> f6096q;

        public g() {
            this.f6083d = new Matrix();
            this.f6089j = 0.0f;
            this.f6090k = 0.0f;
            this.f6091l = 0.0f;
            this.f6092m = 0.0f;
            this.f6093n = 255;
            this.f6094o = null;
            this.f6095p = null;
            this.f6096q = new s.a<>();
            this.f6088i = new d();
            this.f6081b = new Path();
            this.f6082c = new Path();
        }

        public static float a(float f3, float f4, float f5, float f6) {
            return (f3 * f6) - (f4 * f5);
        }

        public void b(Canvas canvas, int i3, int i4, ColorFilter colorFilter) {
            c(this.f6088i, f6080a, canvas, i3, i4, colorFilter);
        }

        public final void c(d dVar, Matrix matrix, Canvas canvas, int i3, int i4, ColorFilter colorFilter) {
            dVar.f6063a.set(matrix);
            dVar.f6063a.preConcat(dVar.f6072j);
            canvas.save();
            for (int i5 = 0; i5 < dVar.f6064b.size(); i5++) {
                e eVar = dVar.f6064b.get(i5);
                if (eVar instanceof d) {
                    c((d) eVar, dVar.f6063a, canvas, i3, i4, colorFilter);
                } else if (eVar instanceof f) {
                    d(dVar, (f) eVar, canvas, i3, i4, colorFilter);
                }
            }
            canvas.restore();
        }

        public final void d(d dVar, f fVar, Canvas canvas, int i3, int i4, ColorFilter colorFilter) {
            float f3 = i3 / this.f6091l;
            float f4 = i4 / this.f6092m;
            float fMin = Math.min(f3, f4);
            Matrix matrix = dVar.f6063a;
            this.f6083d.set(matrix);
            this.f6083d.postScale(f3, f4);
            float fE = e(matrix);
            if (fE == 0.0f) {
                return;
            }
            fVar.d(this.f6081b);
            Path path = this.f6081b;
            this.f6082c.reset();
            if (fVar.c()) {
                this.f6082c.setFillType(fVar.f6078c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.f6082c.addPath(path, this.f6083d);
                canvas.clipPath(this.f6082c);
                return;
            }
            c cVar = (c) fVar;
            float f5 = cVar.f6057k;
            if (f5 != 0.0f || cVar.f6058l != 1.0f) {
                float f6 = cVar.f6059m;
                float f7 = (f5 + f6) % 1.0f;
                float f8 = (cVar.f6058l + f6) % 1.0f;
                if (this.f6086g == null) {
                    this.f6086g = new PathMeasure();
                }
                this.f6086g.setPath(this.f6081b, false);
                float length = this.f6086g.getLength();
                float f9 = f7 * length;
                float f10 = f8 * length;
                path.reset();
                if (f9 > f10) {
                    this.f6086g.getSegment(f9, length, path, true);
                    this.f6086g.getSegment(0.0f, f10, path, true);
                } else {
                    this.f6086g.getSegment(f9, f10, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.f6082c.addPath(path, this.f6083d);
            if (cVar.f6054h.l()) {
                c0.b bVar = cVar.f6054h;
                if (this.f6085f == null) {
                    Paint paint = new Paint(1);
                    this.f6085f = paint;
                    paint.setStyle(Paint.Style.FILL);
                }
                Paint paint2 = this.f6085f;
                if (bVar.h()) {
                    Shader shaderF = bVar.f();
                    shaderF.setLocalMatrix(this.f6083d);
                    paint2.setShader(shaderF);
                    paint2.setAlpha(Math.round(cVar.f6056j * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(h.a(bVar.e(), cVar.f6056j));
                }
                paint2.setColorFilter(colorFilter);
                this.f6082c.setFillType(cVar.f6078c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.f6082c, paint2);
            }
            if (cVar.f6052f.l()) {
                c0.b bVar2 = cVar.f6052f;
                if (this.f6084e == null) {
                    Paint paint3 = new Paint(1);
                    this.f6084e = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                }
                Paint paint4 = this.f6084e;
                Paint.Join join = cVar.f6061o;
                if (join != null) {
                    paint4.setStrokeJoin(join);
                }
                Paint.Cap cap = cVar.f6060n;
                if (cap != null) {
                    paint4.setStrokeCap(cap);
                }
                paint4.setStrokeMiter(cVar.f6062p);
                if (bVar2.h()) {
                    Shader shaderF2 = bVar2.f();
                    shaderF2.setLocalMatrix(this.f6083d);
                    paint4.setShader(shaderF2);
                    paint4.setAlpha(Math.round(cVar.f6055i * 255.0f));
                } else {
                    paint4.setShader(null);
                    paint4.setAlpha(255);
                    paint4.setColor(h.a(bVar2.e(), cVar.f6055i));
                }
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(cVar.f6053g * fMin * fE);
                canvas.drawPath(this.f6082c, paint4);
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
            if (this.f6095p == null) {
                this.f6095p = Boolean.valueOf(this.f6088i.a());
            }
            return this.f6095p.booleanValue();
        }

        public boolean g(int[] iArr) {
            return this.f6088i.b(iArr);
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.f6093n;
        }

        public void setAlpha(float f3) {
            setRootAlpha((int) (f3 * 255.0f));
        }

        public void setRootAlpha(int i3) {
            this.f6093n = i3;
        }

        public g(g gVar) {
            this.f6083d = new Matrix();
            this.f6089j = 0.0f;
            this.f6090k = 0.0f;
            this.f6091l = 0.0f;
            this.f6092m = 0.0f;
            this.f6093n = 255;
            this.f6094o = null;
            this.f6095p = null;
            s.a<String, Object> aVar = new s.a<>();
            this.f6096q = aVar;
            this.f6088i = new d(gVar.f6088i, aVar);
            this.f6081b = new Path(gVar.f6081b);
            this.f6082c = new Path(gVar.f6082c);
            this.f6089j = gVar.f6089j;
            this.f6090k = gVar.f6090k;
            this.f6091l = gVar.f6091l;
            this.f6092m = gVar.f6092m;
            this.f6087h = gVar.f6087h;
            this.f6093n = gVar.f6093n;
            this.f6094o = gVar.f6094o;
            String str = gVar.f6094o;
            if (str != null) {
                aVar.put(str, this);
            }
            this.f6095p = gVar.f6095p;
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class d extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f6063a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ArrayList<e> f6064b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f6065c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f6066d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f6067e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f6068f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f6069g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f6070h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f6071i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final Matrix f6072j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f6073k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int[] f6074l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public String f6075m;

        public d(d dVar, s.a<String, Object> aVar) {
            f bVar;
            super();
            this.f6063a = new Matrix();
            this.f6064b = new ArrayList<>();
            this.f6065c = 0.0f;
            this.f6066d = 0.0f;
            this.f6067e = 0.0f;
            this.f6068f = 1.0f;
            this.f6069g = 1.0f;
            this.f6070h = 0.0f;
            this.f6071i = 0.0f;
            Matrix matrix = new Matrix();
            this.f6072j = matrix;
            this.f6075m = null;
            this.f6065c = dVar.f6065c;
            this.f6066d = dVar.f6066d;
            this.f6067e = dVar.f6067e;
            this.f6068f = dVar.f6068f;
            this.f6069g = dVar.f6069g;
            this.f6070h = dVar.f6070h;
            this.f6071i = dVar.f6071i;
            this.f6074l = dVar.f6074l;
            String str = dVar.f6075m;
            this.f6075m = str;
            this.f6073k = dVar.f6073k;
            if (str != null) {
                aVar.put(str, this);
            }
            matrix.set(dVar.f6072j);
            ArrayList<e> arrayList = dVar.f6064b;
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                e eVar = arrayList.get(i3);
                if (eVar instanceof d) {
                    this.f6064b.add(new d((d) eVar, aVar));
                } else {
                    if (eVar instanceof c) {
                        bVar = new c((c) eVar);
                    } else {
                        if (!(eVar instanceof b)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        bVar = new b((b) eVar);
                    }
                    this.f6064b.add(bVar);
                    String str2 = bVar.f6077b;
                    if (str2 != null) {
                        aVar.put(str2, bVar);
                    }
                }
            }
        }

        @Override // z0.h.e
        public boolean a() {
            for (int i3 = 0; i3 < this.f6064b.size(); i3++) {
                if (this.f6064b.get(i3).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // z0.h.e
        public boolean b(int[] iArr) {
            boolean zB = false;
            for (int i3 = 0; i3 < this.f6064b.size(); i3++) {
                zB |= this.f6064b.get(i3).b(iArr);
            }
            return zB;
        }

        public void c(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = c0.g.k(resources, theme, attributeSet, z0.a.f6012b);
            e(typedArrayK, xmlPullParser);
            typedArrayK.recycle();
        }

        public final void d() {
            this.f6072j.reset();
            this.f6072j.postTranslate(-this.f6066d, -this.f6067e);
            this.f6072j.postScale(this.f6068f, this.f6069g);
            this.f6072j.postRotate(this.f6065c, 0.0f, 0.0f);
            this.f6072j.postTranslate(this.f6070h + this.f6066d, this.f6071i + this.f6067e);
        }

        public final void e(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.f6074l = null;
            this.f6065c = c0.g.f(typedArray, xmlPullParser, "rotation", 5, this.f6065c);
            this.f6066d = typedArray.getFloat(1, this.f6066d);
            this.f6067e = typedArray.getFloat(2, this.f6067e);
            this.f6068f = c0.g.f(typedArray, xmlPullParser, "scaleX", 3, this.f6068f);
            this.f6069g = c0.g.f(typedArray, xmlPullParser, "scaleY", 4, this.f6069g);
            this.f6070h = c0.g.f(typedArray, xmlPullParser, "translateX", 6, this.f6070h);
            this.f6071i = c0.g.f(typedArray, xmlPullParser, "translateY", 7, this.f6071i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.f6075m = string;
            }
            d();
        }

        public String getGroupName() {
            return this.f6075m;
        }

        public Matrix getLocalMatrix() {
            return this.f6072j;
        }

        public float getPivotX() {
            return this.f6066d;
        }

        public float getPivotY() {
            return this.f6067e;
        }

        public float getRotation() {
            return this.f6065c;
        }

        public float getScaleX() {
            return this.f6068f;
        }

        public float getScaleY() {
            return this.f6069g;
        }

        public float getTranslateX() {
            return this.f6070h;
        }

        public float getTranslateY() {
            return this.f6071i;
        }

        public void setPivotX(float f3) {
            if (f3 != this.f6066d) {
                this.f6066d = f3;
                d();
            }
        }

        public void setPivotY(float f3) {
            if (f3 != this.f6067e) {
                this.f6067e = f3;
                d();
            }
        }

        public void setRotation(float f3) {
            if (f3 != this.f6065c) {
                this.f6065c = f3;
                d();
            }
        }

        public void setScaleX(float f3) {
            if (f3 != this.f6068f) {
                this.f6068f = f3;
                d();
            }
        }

        public void setScaleY(float f3) {
            if (f3 != this.f6069g) {
                this.f6069g = f3;
                d();
            }
        }

        public void setTranslateX(float f3) {
            if (f3 != this.f6070h) {
                this.f6070h = f3;
                d();
            }
        }

        public void setTranslateY(float f3) {
            if (f3 != this.f6071i) {
                this.f6071i = f3;
                d();
            }
        }

        public d() {
            super();
            this.f6063a = new Matrix();
            this.f6064b = new ArrayList<>();
            this.f6065c = 0.0f;
            this.f6066d = 0.0f;
            this.f6067e = 0.0f;
            this.f6068f = 1.0f;
            this.f6069g = 1.0f;
            this.f6070h = 0.0f;
            this.f6071i = 0.0f;
            this.f6072j = new Matrix();
            this.f6075m = null;
        }
    }
}
