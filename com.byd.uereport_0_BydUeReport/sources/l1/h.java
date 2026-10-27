package l1;

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
import g0.c;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: VectorDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends l1.g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final PorterDuff.Mode f6096e = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public C0080h f6097f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public PorterDuffColorFilter f6098g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ColorFilter f6099h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f6100i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f6101j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Drawable.ConstantState f6102k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final float[] f6103l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Matrix f6104m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Rect f6105n;

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class b extends f {
        public b() {
        }

        @Override // l1.h.f
        public boolean c() {
            return true;
        }

        public void e(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (f0.g.j(xmlPullParser, "pathData")) {
                TypedArray typedArrayK = f0.g.k(resources, theme, attributeSet, l1.a.f6069d);
                f(typedArrayK, xmlPullParser);
                typedArrayK.recycle();
            }
        }

        public final void f(TypedArray typedArray, XmlPullParser xmlPullParser) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.f6132b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f6131a = g0.c.d(string2);
            }
            this.f6133c = f0.g.g(typedArray, xmlPullParser, "fillType", 2, 0);
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

    /* JADX INFO: renamed from: l1.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class C0080h extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6152a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public g f6153b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorStateList f6154c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public PorterDuff.Mode f6155d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f6156e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Bitmap f6157f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ColorStateList f6158g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public PorterDuff.Mode f6159h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f6160i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f6161j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public boolean f6162k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public Paint f6163l;

        public C0080h(C0080h c0080h) {
            this.f6154c = null;
            this.f6155d = h.f6096e;
            if (c0080h != null) {
                this.f6152a = c0080h.f6152a;
                g gVar = new g(c0080h.f6153b);
                this.f6153b = gVar;
                Paint paint = c0080h.f6153b.f6140f;
                if (paint != null) {
                    gVar.f6140f = new Paint(paint);
                }
                Paint paint2 = c0080h.f6153b.f6139e;
                if (paint2 != null) {
                    this.f6153b.f6139e = new Paint(paint2);
                }
                this.f6154c = c0080h.f6154c;
                this.f6155d = c0080h.f6155d;
                this.f6156e = c0080h.f6156e;
            }
        }

        public boolean a(int i8, int i9) {
            return i8 == this.f6157f.getWidth() && i9 == this.f6157f.getHeight();
        }

        public boolean b() {
            return !this.f6162k && this.f6158g == this.f6154c && this.f6159h == this.f6155d && this.f6161j == this.f6156e && this.f6160i == this.f6153b.getRootAlpha();
        }

        public void c(int i8, int i9) {
            if (this.f6157f == null || !a(i8, i9)) {
                this.f6157f = Bitmap.createBitmap(i8, i9, Bitmap.Config.ARGB_8888);
                this.f6162k = true;
            }
        }

        public void d(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f6157f, (Rect) null, rect, e(colorFilter));
        }

        public Paint e(ColorFilter colorFilter) {
            if (!f() && colorFilter == null) {
                return null;
            }
            if (this.f6163l == null) {
                Paint paint = new Paint();
                this.f6163l = paint;
                paint.setFilterBitmap(true);
            }
            this.f6163l.setAlpha(this.f6153b.getRootAlpha());
            this.f6163l.setColorFilter(colorFilter);
            return this.f6163l;
        }

        public boolean f() {
            return this.f6153b.getRootAlpha() < 255;
        }

        public boolean g() {
            return this.f6153b.f();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f6152a;
        }

        public boolean h(int[] iArr) {
            boolean zG = this.f6153b.g(iArr);
            this.f6162k |= zG;
            return zG;
        }

        public void i() {
            this.f6158g = this.f6154c;
            this.f6159h = this.f6155d;
            this.f6160i = this.f6153b.getRootAlpha();
            this.f6161j = this.f6156e;
            this.f6162k = false;
        }

        public void j(int i8, int i9) {
            this.f6157f.eraseColor(0);
            this.f6153b.b(new Canvas(this.f6157f), i8, i9, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new h(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new h(this);
        }

        public C0080h() {
            this.f6154c = null;
            this.f6155d = h.f6096e;
            this.f6153b = new g();
        }
    }

    public h() {
        this.f6101j = true;
        this.f6103l = new float[9];
        this.f6104m = new Matrix();
        this.f6105n = new Rect();
        this.f6097f = new C0080h();
    }

    public static int a(int i8, float f8) {
        return (i8 & 16777215) | (((int) (Color.alpha(i8) * f8)) << 24);
    }

    public static h b(Resources resources, int i8, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            h hVar = new h();
            Drawable drawableA = f0.f.a(resources, i8, theme);
            hVar.f6095d = drawableA;
            hVar.f6102k = new i(drawableA.getConstantState());
            return hVar;
        }
        try {
            XmlResourceParser xml = resources.getXml(i8);
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
        } catch (IOException e8) {
            Log.e("VectorDrawableCompat", "parser error", e8);
            return null;
        } catch (XmlPullParserException e9) {
            Log.e("VectorDrawableCompat", "parser error", e9);
            return null;
        }
    }

    public static h c(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        h hVar = new h();
        hVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return hVar;
    }

    public static PorterDuff.Mode g(int i8, PorterDuff.Mode mode) {
        if (i8 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i8 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i8 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i8) {
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

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f6095d;
        if (drawable == null) {
            return false;
        }
        h0.a.b(drawable);
        return false;
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public Object d(String str) {
        return this.f6097f.f6153b.f6151q.get(str);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.f6105n);
        if (this.f6105n.width() <= 0 || this.f6105n.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f6099h;
        if (colorFilter == null) {
            colorFilter = this.f6098g;
        }
        canvas.getMatrix(this.f6104m);
        this.f6104m.getValues(this.f6103l);
        float fAbs = Math.abs(this.f6103l[0]);
        float fAbs2 = Math.abs(this.f6103l[4]);
        float fAbs3 = Math.abs(this.f6103l[1]);
        float fAbs4 = Math.abs(this.f6103l[3]);
        if (fAbs3 != 0.0f || fAbs4 != 0.0f) {
            fAbs = 1.0f;
            fAbs2 = 1.0f;
        }
        int iMin = Math.min(2048, (int) (this.f6105n.width() * fAbs));
        int iMin2 = Math.min(2048, (int) (this.f6105n.height() * fAbs2));
        if (iMin <= 0 || iMin2 <= 0) {
            return;
        }
        int iSave = canvas.save();
        Rect rect = this.f6105n;
        canvas.translate(rect.left, rect.top);
        if (f()) {
            canvas.translate(this.f6105n.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.f6105n.offsetTo(0, 0);
        this.f6097f.c(iMin, iMin2);
        if (!this.f6101j) {
            this.f6097f.j(iMin, iMin2);
        } else if (!this.f6097f.b()) {
            this.f6097f.j(iMin, iMin2);
            this.f6097f.i();
        }
        this.f6097f.d(canvas, colorFilter, this.f6105n);
        canvas.restoreToCount(iSave);
    }

    public final void e(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        C0080h c0080h = this.f6097f;
        g gVar = c0080h.f6153b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(gVar.f6143i);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z7 = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                d dVar = (d) arrayDeque.peek();
                if ("path".equals(name)) {
                    c cVar = new c();
                    cVar.g(resources, attributeSet, theme, xmlPullParser);
                    dVar.f6119b.add(cVar);
                    if (cVar.getPathName() != null) {
                        gVar.f6151q.put(cVar.getPathName(), cVar);
                    }
                    z7 = false;
                    c0080h.f6152a = cVar.f6134d | c0080h.f6152a;
                } else if ("clip-path".equals(name)) {
                    b bVar = new b();
                    bVar.e(resources, attributeSet, theme, xmlPullParser);
                    dVar.f6119b.add(bVar);
                    if (bVar.getPathName() != null) {
                        gVar.f6151q.put(bVar.getPathName(), bVar);
                    }
                    c0080h.f6152a = bVar.f6134d | c0080h.f6152a;
                } else if ("group".equals(name)) {
                    d dVar2 = new d();
                    dVar2.c(resources, attributeSet, theme, xmlPullParser);
                    dVar.f6119b.add(dVar2);
                    arrayDeque.push(dVar2);
                    if (dVar2.getGroupName() != null) {
                        gVar.f6151q.put(dVar2.getGroupName(), dVar2);
                    }
                    c0080h.f6152a = dVar2.f6128k | c0080h.f6152a;
                }
            } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z7) {
            throw new XmlPullParserException("no path defined");
        }
    }

    public final boolean f() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && h0.a.f(this) == 1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f6095d;
        return drawable != null ? h0.a.d(drawable) : this.f6097f.f6153b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f6095d;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f6097f.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f6095d;
        return drawable != null ? h0.a.e(drawable) : this.f6099h;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f6095d;
        if (drawable != null && Build.VERSION.SDK_INT >= 24) {
            return new i(drawable.getConstantState());
        }
        this.f6097f.f6152a = getChangingConfigurations();
        return this.f6097f;
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f6095d;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f6097f.f6153b.f6145k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f6095d;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f6097f.f6153b.f6144j;
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void h(boolean z7) {
        this.f6101j = z7;
    }

    public final void i(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) throws XmlPullParserException {
        C0080h c0080h = this.f6097f;
        g gVar = c0080h.f6153b;
        c0080h.f6155d = g(f0.g.g(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateListC = f0.g.c(typedArray, xmlPullParser, theme, "tint", 1);
        if (colorStateListC != null) {
            c0080h.f6154c = colorStateListC;
        }
        c0080h.f6156e = f0.g.a(typedArray, xmlPullParser, "autoMirrored", 5, c0080h.f6156e);
        gVar.f6146l = f0.g.f(typedArray, xmlPullParser, "viewportWidth", 7, gVar.f6146l);
        float f8 = f0.g.f(typedArray, xmlPullParser, "viewportHeight", 8, gVar.f6147m);
        gVar.f6147m = f8;
        if (gVar.f6146l <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (f8 <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
        gVar.f6144j = typedArray.getDimension(3, gVar.f6144j);
        float dimension = typedArray.getDimension(2, gVar.f6145k);
        gVar.f6145k = dimension;
        if (gVar.f6144j <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
        }
        if (dimension <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
        }
        gVar.setAlpha(f0.g.f(typedArray, xmlPullParser, "alpha", 4, gVar.getAlpha()));
        String string = typedArray.getString(0);
        if (string != null) {
            gVar.f6149o = string;
            gVar.f6151q.put(string, gVar);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f6095d;
        return drawable != null ? h0.a.h(drawable) : this.f6097f.f6156e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        C0080h c0080h;
        ColorStateList colorStateList;
        Drawable drawable = this.f6095d;
        return drawable != null ? drawable.isStateful() : super.isStateful() || ((c0080h = this.f6097f) != null && (c0080h.g() || ((colorStateList = this.f6097f.f6154c) != null && colorStateList.isStateful())));
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f6100i && super.mutate() == this) {
            this.f6097f = new C0080h(this.f6097f);
            this.f6100i = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z7 = false;
        C0080h c0080h = this.f6097f;
        ColorStateList colorStateList = c0080h.f6154c;
        if (colorStateList != null && (mode = c0080h.f6155d) != null) {
            this.f6098g = updateTintFilter(this.f6098g, colorStateList, mode);
            invalidateSelf();
            z7 = true;
        }
        if (!c0080h.g() || !c0080h.h(iArr)) {
            return z7;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j8) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j8);
        } else {
            super.scheduleSelf(runnable, j8);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            drawable.setAlpha(i8);
        } else if (this.f6097f.f6153b.getRootAlpha() != i8) {
            this.f6097f.f6153b.setRootAlpha(i8);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z7) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            h0.a.j(drawable, z7);
        } else {
            this.f6097f.f6156e = z7;
        }
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i8) {
        super.setChangingConfigurations(i8);
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i8, PorterDuff.Mode mode) {
        super.setColorFilter(i8, mode);
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z7) {
        super.setFilterBitmap(z7);
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f8, float f9) {
        super.setHotspot(f8, f9);
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i8, int i9, int i10, int i11) {
        super.setHotspotBounds(i8, i9, i10, i11);
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTint(int i8) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            h0.a.n(drawable, i8);
        } else {
            setTintList(ColorStateList.valueOf(i8));
        }
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            h0.a.o(drawable, colorStateList);
            return;
        }
        C0080h c0080h = this.f6097f;
        if (c0080h.f6154c != colorStateList) {
            c0080h.f6154c = colorStateList;
            this.f6098g = updateTintFilter(this.f6098g, colorStateList, c0080h.f6155d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            h0.a.p(drawable, mode);
            return;
        }
        C0080h c0080h = this.f6097f;
        if (c0080h.f6155d != mode) {
            c0080h.f6155d = mode;
            this.f6098g = updateTintFilter(this.f6098g, c0080h.f6154c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z7, boolean z8) {
        Drawable drawable = this.f6095d;
        return drawable != null ? drawable.setVisible(z7, z8) : super.setVisible(z7, z8);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f6095d;
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
        public final Drawable.ConstantState f6164a;

        public i(Drawable.ConstantState constantState) {
            this.f6164a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f6164a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f6164a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVar = new h();
            hVar.f6095d = (VectorDrawable) this.f6164a.newDrawable();
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            h hVar = new h();
            hVar.f6095d = (VectorDrawable) this.f6164a.newDrawable(resources);
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            h hVar = new h();
            hVar.f6095d = (VectorDrawable) this.f6164a.newDrawable(resources, theme);
            return hVar;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f6099h = colorFilter;
            invalidateSelf();
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static abstract class f extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c.b[] f6131a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f6132b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f6133c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f6134d;

        public f() {
            super();
            this.f6131a = null;
            this.f6133c = 0;
        }

        public boolean c() {
            return false;
        }

        public void d(Path path) {
            path.reset();
            c.b[] bVarArr = this.f6131a;
            if (bVarArr != null) {
                c.b.e(bVarArr, path);
            }
        }

        public c.b[] getPathData() {
            return this.f6131a;
        }

        public String getPathName() {
            return this.f6132b;
        }

        public void setPathData(c.b[] bVarArr) {
            if (g0.c.b(this.f6131a, bVarArr)) {
                g0.c.j(this.f6131a, bVarArr);
            } else {
                this.f6131a = g0.c.f(bVarArr);
            }
        }

        public f(f fVar) {
            super();
            this.f6131a = null;
            this.f6133c = 0;
            this.f6132b = fVar.f6132b;
            this.f6134d = fVar.f6134d;
            this.f6131a = g0.c.f(fVar.f6131a);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            h0.a.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        C0080h c0080h = this.f6097f;
        c0080h.f6153b = new g();
        TypedArray typedArrayK = f0.g.k(resources, theme, attributeSet, l1.a.f6066a);
        i(typedArrayK, xmlPullParser, theme);
        typedArrayK.recycle();
        c0080h.f6152a = getChangingConfigurations();
        c0080h.f6162k = true;
        e(resources, xmlPullParser, attributeSet, theme);
        this.f6098g = updateTintFilter(this.f6098g, c0080h.f6154c, c0080h.f6155d);
    }

    public h(C0080h c0080h) {
        this.f6101j = true;
        this.f6103l = new float[9];
        this.f6104m = new Matrix();
        this.f6105n = new Rect();
        this.f6097f = c0080h;
        this.f6098g = updateTintFilter(this.f6098g, c0080h.f6154c, c0080h.f6155d);
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class c extends f {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int[] f6106e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public f0.b f6107f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f6108g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public f0.b f6109h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f6110i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f6111j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f6112k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f6113l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public float f6114m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public Paint.Cap f6115n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public Paint.Join f6116o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public float f6117p;

        public c() {
            this.f6108g = 0.0f;
            this.f6110i = 1.0f;
            this.f6111j = 1.0f;
            this.f6112k = 0.0f;
            this.f6113l = 1.0f;
            this.f6114m = 0.0f;
            this.f6115n = Paint.Cap.BUTT;
            this.f6116o = Paint.Join.MITER;
            this.f6117p = 4.0f;
        }

        @Override // l1.h.e
        public boolean a() {
            return this.f6109h.i() || this.f6107f.i();
        }

        @Override // l1.h.e
        public boolean b(int[] iArr) {
            return this.f6107f.j(iArr) | this.f6109h.j(iArr);
        }

        public final Paint.Cap e(int i8, Paint.Cap cap) {
            return i8 != 0 ? i8 != 1 ? i8 != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        public final Paint.Join f(int i8, Paint.Join join) {
            return i8 != 0 ? i8 != 1 ? i8 != 2 ? join : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER;
        }

        public void g(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = f0.g.k(resources, theme, attributeSet, l1.a.f6068c);
            h(typedArrayK, xmlPullParser, theme);
            typedArrayK.recycle();
        }

        public float getFillAlpha() {
            return this.f6111j;
        }

        public int getFillColor() {
            return this.f6109h.e();
        }

        public float getStrokeAlpha() {
            return this.f6110i;
        }

        public int getStrokeColor() {
            return this.f6107f.e();
        }

        public float getStrokeWidth() {
            return this.f6108g;
        }

        public float getTrimPathEnd() {
            return this.f6113l;
        }

        public float getTrimPathOffset() {
            return this.f6114m;
        }

        public float getTrimPathStart() {
            return this.f6112k;
        }

        public final void h(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.f6106e = null;
            if (f0.g.j(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f6132b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f6131a = g0.c.d(string2);
                }
                this.f6109h = f0.g.e(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.f6111j = f0.g.f(typedArray, xmlPullParser, "fillAlpha", 12, this.f6111j);
                this.f6115n = e(f0.g.g(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.f6115n);
                this.f6116o = f(f0.g.g(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.f6116o);
                this.f6117p = f0.g.f(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.f6117p);
                this.f6107f = f0.g.e(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.f6110i = f0.g.f(typedArray, xmlPullParser, "strokeAlpha", 11, this.f6110i);
                this.f6108g = f0.g.f(typedArray, xmlPullParser, "strokeWidth", 4, this.f6108g);
                this.f6113l = f0.g.f(typedArray, xmlPullParser, "trimPathEnd", 6, this.f6113l);
                this.f6114m = f0.g.f(typedArray, xmlPullParser, "trimPathOffset", 7, this.f6114m);
                this.f6112k = f0.g.f(typedArray, xmlPullParser, "trimPathStart", 5, this.f6112k);
                this.f6133c = f0.g.g(typedArray, xmlPullParser, "fillType", 13, this.f6133c);
            }
        }

        public void setFillAlpha(float f8) {
            this.f6111j = f8;
        }

        public void setFillColor(int i8) {
            this.f6109h.k(i8);
        }

        public void setStrokeAlpha(float f8) {
            this.f6110i = f8;
        }

        public void setStrokeColor(int i8) {
            this.f6107f.k(i8);
        }

        public void setStrokeWidth(float f8) {
            this.f6108g = f8;
        }

        public void setTrimPathEnd(float f8) {
            this.f6113l = f8;
        }

        public void setTrimPathOffset(float f8) {
            this.f6114m = f8;
        }

        public void setTrimPathStart(float f8) {
            this.f6112k = f8;
        }

        public c(c cVar) {
            super(cVar);
            this.f6108g = 0.0f;
            this.f6110i = 1.0f;
            this.f6111j = 1.0f;
            this.f6112k = 0.0f;
            this.f6113l = 1.0f;
            this.f6114m = 0.0f;
            this.f6115n = Paint.Cap.BUTT;
            this.f6116o = Paint.Join.MITER;
            this.f6117p = 4.0f;
            this.f6106e = cVar.f6106e;
            this.f6107f = cVar.f6107f;
            this.f6108g = cVar.f6108g;
            this.f6110i = cVar.f6110i;
            this.f6109h = cVar.f6109h;
            this.f6133c = cVar.f6133c;
            this.f6111j = cVar.f6111j;
            this.f6112k = cVar.f6112k;
            this.f6113l = cVar.f6113l;
            this.f6114m = cVar.f6114m;
            this.f6115n = cVar.f6115n;
            this.f6116o = cVar.f6116o;
            this.f6117p = cVar.f6117p;
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Matrix f6135a = new Matrix();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f6136b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Path f6137c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Matrix f6138d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Paint f6139e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Paint f6140f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public PathMeasure f6141g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f6142h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final d f6143i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f6144j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f6145k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f6146l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public float f6147m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f6148n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public String f6149o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public Boolean f6150p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public final v.a<String, Object> f6151q;

        public g() {
            this.f6138d = new Matrix();
            this.f6144j = 0.0f;
            this.f6145k = 0.0f;
            this.f6146l = 0.0f;
            this.f6147m = 0.0f;
            this.f6148n = 255;
            this.f6149o = null;
            this.f6150p = null;
            this.f6151q = new v.a<>();
            this.f6143i = new d();
            this.f6136b = new Path();
            this.f6137c = new Path();
        }

        public static float a(float f8, float f9, float f10, float f11) {
            return (f8 * f11) - (f9 * f10);
        }

        public void b(Canvas canvas, int i8, int i9, ColorFilter colorFilter) {
            c(this.f6143i, f6135a, canvas, i8, i9, colorFilter);
        }

        public final void c(d dVar, Matrix matrix, Canvas canvas, int i8, int i9, ColorFilter colorFilter) {
            dVar.f6118a.set(matrix);
            dVar.f6118a.preConcat(dVar.f6127j);
            canvas.save();
            for (int i10 = 0; i10 < dVar.f6119b.size(); i10++) {
                e eVar = dVar.f6119b.get(i10);
                if (eVar instanceof d) {
                    c((d) eVar, dVar.f6118a, canvas, i8, i9, colorFilter);
                } else if (eVar instanceof f) {
                    d(dVar, (f) eVar, canvas, i8, i9, colorFilter);
                }
            }
            canvas.restore();
        }

        public final void d(d dVar, f fVar, Canvas canvas, int i8, int i9, ColorFilter colorFilter) {
            float f8 = i8 / this.f6146l;
            float f9 = i9 / this.f6147m;
            float fMin = Math.min(f8, f9);
            Matrix matrix = dVar.f6118a;
            this.f6138d.set(matrix);
            this.f6138d.postScale(f8, f9);
            float fE = e(matrix);
            if (fE == 0.0f) {
                return;
            }
            fVar.d(this.f6136b);
            Path path = this.f6136b;
            this.f6137c.reset();
            if (fVar.c()) {
                this.f6137c.setFillType(fVar.f6133c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.f6137c.addPath(path, this.f6138d);
                canvas.clipPath(this.f6137c);
                return;
            }
            c cVar = (c) fVar;
            float f10 = cVar.f6112k;
            if (f10 != 0.0f || cVar.f6113l != 1.0f) {
                float f11 = cVar.f6114m;
                float f12 = (f10 + f11) % 1.0f;
                float f13 = (cVar.f6113l + f11) % 1.0f;
                if (this.f6141g == null) {
                    this.f6141g = new PathMeasure();
                }
                this.f6141g.setPath(this.f6136b, false);
                float length = this.f6141g.getLength();
                float f14 = f12 * length;
                float f15 = f13 * length;
                path.reset();
                if (f14 > f15) {
                    this.f6141g.getSegment(f14, length, path, true);
                    this.f6141g.getSegment(0.0f, f15, path, true);
                } else {
                    this.f6141g.getSegment(f14, f15, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.f6137c.addPath(path, this.f6138d);
            if (cVar.f6109h.l()) {
                f0.b bVar = cVar.f6109h;
                if (this.f6140f == null) {
                    Paint paint = new Paint(1);
                    this.f6140f = paint;
                    paint.setStyle(Paint.Style.FILL);
                }
                Paint paint2 = this.f6140f;
                if (bVar.h()) {
                    Shader shaderF = bVar.f();
                    shaderF.setLocalMatrix(this.f6138d);
                    paint2.setShader(shaderF);
                    paint2.setAlpha(Math.round(cVar.f6111j * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(h.a(bVar.e(), cVar.f6111j));
                }
                paint2.setColorFilter(colorFilter);
                this.f6137c.setFillType(cVar.f6133c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.f6137c, paint2);
            }
            if (cVar.f6107f.l()) {
                f0.b bVar2 = cVar.f6107f;
                if (this.f6139e == null) {
                    Paint paint3 = new Paint(1);
                    this.f6139e = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                }
                Paint paint4 = this.f6139e;
                Paint.Join join = cVar.f6116o;
                if (join != null) {
                    paint4.setStrokeJoin(join);
                }
                Paint.Cap cap = cVar.f6115n;
                if (cap != null) {
                    paint4.setStrokeCap(cap);
                }
                paint4.setStrokeMiter(cVar.f6117p);
                if (bVar2.h()) {
                    Shader shaderF2 = bVar2.f();
                    shaderF2.setLocalMatrix(this.f6138d);
                    paint4.setShader(shaderF2);
                    paint4.setAlpha(Math.round(cVar.f6110i * 255.0f));
                } else {
                    paint4.setShader(null);
                    paint4.setAlpha(255);
                    paint4.setColor(h.a(bVar2.e(), cVar.f6110i));
                }
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(cVar.f6108g * fMin * fE);
                canvas.drawPath(this.f6137c, paint4);
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
            if (this.f6150p == null) {
                this.f6150p = Boolean.valueOf(this.f6143i.a());
            }
            return this.f6150p.booleanValue();
        }

        public boolean g(int[] iArr) {
            return this.f6143i.b(iArr);
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.f6148n;
        }

        public void setAlpha(float f8) {
            setRootAlpha((int) (f8 * 255.0f));
        }

        public void setRootAlpha(int i8) {
            this.f6148n = i8;
        }

        public g(g gVar) {
            this.f6138d = new Matrix();
            this.f6144j = 0.0f;
            this.f6145k = 0.0f;
            this.f6146l = 0.0f;
            this.f6147m = 0.0f;
            this.f6148n = 255;
            this.f6149o = null;
            this.f6150p = null;
            v.a<String, Object> aVar = new v.a<>();
            this.f6151q = aVar;
            this.f6143i = new d(gVar.f6143i, aVar);
            this.f6136b = new Path(gVar.f6136b);
            this.f6137c = new Path(gVar.f6137c);
            this.f6144j = gVar.f6144j;
            this.f6145k = gVar.f6145k;
            this.f6146l = gVar.f6146l;
            this.f6147m = gVar.f6147m;
            this.f6142h = gVar.f6142h;
            this.f6148n = gVar.f6148n;
            this.f6149o = gVar.f6149o;
            String str = gVar.f6149o;
            if (str != null) {
                aVar.put(str, this);
            }
            this.f6150p = gVar.f6150p;
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class d extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f6118a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ArrayList<e> f6119b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f6120c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f6121d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f6122e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f6123f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f6124g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f6125h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f6126i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final Matrix f6127j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f6128k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int[] f6129l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public String f6130m;

        public d(d dVar, v.a<String, Object> aVar) {
            f bVar;
            super();
            this.f6118a = new Matrix();
            this.f6119b = new ArrayList<>();
            this.f6120c = 0.0f;
            this.f6121d = 0.0f;
            this.f6122e = 0.0f;
            this.f6123f = 1.0f;
            this.f6124g = 1.0f;
            this.f6125h = 0.0f;
            this.f6126i = 0.0f;
            Matrix matrix = new Matrix();
            this.f6127j = matrix;
            this.f6130m = null;
            this.f6120c = dVar.f6120c;
            this.f6121d = dVar.f6121d;
            this.f6122e = dVar.f6122e;
            this.f6123f = dVar.f6123f;
            this.f6124g = dVar.f6124g;
            this.f6125h = dVar.f6125h;
            this.f6126i = dVar.f6126i;
            this.f6129l = dVar.f6129l;
            String str = dVar.f6130m;
            this.f6130m = str;
            this.f6128k = dVar.f6128k;
            if (str != null) {
                aVar.put(str, this);
            }
            matrix.set(dVar.f6127j);
            ArrayList<e> arrayList = dVar.f6119b;
            for (int i8 = 0; i8 < arrayList.size(); i8++) {
                e eVar = arrayList.get(i8);
                if (eVar instanceof d) {
                    this.f6119b.add(new d((d) eVar, aVar));
                } else {
                    if (eVar instanceof c) {
                        bVar = new c((c) eVar);
                    } else {
                        if (!(eVar instanceof b)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        bVar = new b((b) eVar);
                    }
                    this.f6119b.add(bVar);
                    String str2 = bVar.f6132b;
                    if (str2 != null) {
                        aVar.put(str2, bVar);
                    }
                }
            }
        }

        @Override // l1.h.e
        public boolean a() {
            for (int i8 = 0; i8 < this.f6119b.size(); i8++) {
                if (this.f6119b.get(i8).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // l1.h.e
        public boolean b(int[] iArr) {
            boolean zB = false;
            for (int i8 = 0; i8 < this.f6119b.size(); i8++) {
                zB |= this.f6119b.get(i8).b(iArr);
            }
            return zB;
        }

        public void c(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = f0.g.k(resources, theme, attributeSet, l1.a.f6067b);
            e(typedArrayK, xmlPullParser);
            typedArrayK.recycle();
        }

        public final void d() {
            this.f6127j.reset();
            this.f6127j.postTranslate(-this.f6121d, -this.f6122e);
            this.f6127j.postScale(this.f6123f, this.f6124g);
            this.f6127j.postRotate(this.f6120c, 0.0f, 0.0f);
            this.f6127j.postTranslate(this.f6125h + this.f6121d, this.f6126i + this.f6122e);
        }

        public final void e(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.f6129l = null;
            this.f6120c = f0.g.f(typedArray, xmlPullParser, "rotation", 5, this.f6120c);
            this.f6121d = typedArray.getFloat(1, this.f6121d);
            this.f6122e = typedArray.getFloat(2, this.f6122e);
            this.f6123f = f0.g.f(typedArray, xmlPullParser, "scaleX", 3, this.f6123f);
            this.f6124g = f0.g.f(typedArray, xmlPullParser, "scaleY", 4, this.f6124g);
            this.f6125h = f0.g.f(typedArray, xmlPullParser, "translateX", 6, this.f6125h);
            this.f6126i = f0.g.f(typedArray, xmlPullParser, "translateY", 7, this.f6126i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.f6130m = string;
            }
            d();
        }

        public String getGroupName() {
            return this.f6130m;
        }

        public Matrix getLocalMatrix() {
            return this.f6127j;
        }

        public float getPivotX() {
            return this.f6121d;
        }

        public float getPivotY() {
            return this.f6122e;
        }

        public float getRotation() {
            return this.f6120c;
        }

        public float getScaleX() {
            return this.f6123f;
        }

        public float getScaleY() {
            return this.f6124g;
        }

        public float getTranslateX() {
            return this.f6125h;
        }

        public float getTranslateY() {
            return this.f6126i;
        }

        public void setPivotX(float f8) {
            if (f8 != this.f6121d) {
                this.f6121d = f8;
                d();
            }
        }

        public void setPivotY(float f8) {
            if (f8 != this.f6122e) {
                this.f6122e = f8;
                d();
            }
        }

        public void setRotation(float f8) {
            if (f8 != this.f6120c) {
                this.f6120c = f8;
                d();
            }
        }

        public void setScaleX(float f8) {
            if (f8 != this.f6123f) {
                this.f6123f = f8;
                d();
            }
        }

        public void setScaleY(float f8) {
            if (f8 != this.f6124g) {
                this.f6124g = f8;
                d();
            }
        }

        public void setTranslateX(float f8) {
            if (f8 != this.f6125h) {
                this.f6125h = f8;
                d();
            }
        }

        public void setTranslateY(float f8) {
            if (f8 != this.f6126i) {
                this.f6126i = f8;
                d();
            }
        }

        public d() {
            super();
            this.f6118a = new Matrix();
            this.f6119b = new ArrayList<>();
            this.f6120c = 0.0f;
            this.f6121d = 0.0f;
            this.f6122e = 0.0f;
            this.f6123f = 1.0f;
            this.f6124g = 1.0f;
            this.f6125h = 0.0f;
            this.f6126i = 0.0f;
            this.f6127j = new Matrix();
            this.f6130m = null;
        }
    }
}
