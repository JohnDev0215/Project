package androidx.vectordrawable.a.a;

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
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.c;
import com.byd.mediacenter.api.media.MediaControl;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class h extends androidx.vectordrawable.a.a.g {
    static final PorterDuff.Mode l = PorterDuff.Mode.SRC_IN;
    private C0043h d;
    private PorterDuffColorFilter e;
    private ColorFilter f;
    private boolean g;
    private boolean h;
    private final float[] i;
    private final Matrix j;
    private final Rect k;

    /* JADX INFO: compiled from: caradapter-bug */
    private static class b extends f {
        b() {
        }

        private void f(TypedArray typedArray, XmlPullParser xmlPullParser) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.f1250b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f1249a = androidx.core.graphics.c.d(string2);
            }
            this.f1251c = androidx.core.content.res.f.k(typedArray, xmlPullParser, "fillType", 2, 0);
        }

        @Override // androidx.vectordrawable.a.a.h.f
        public boolean c() {
            return true;
        }

        public void e(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (androidx.core.content.res.f.r(xmlPullParser, "pathData")) {
                TypedArray typedArrayS = androidx.core.content.res.f.s(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.d);
                f(typedArrayS, xmlPullParser);
                typedArrayS.recycle();
            }
        }

        b(b bVar) {
            super(bVar);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
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

    /* JADX INFO: renamed from: androidx.vectordrawable.a.a.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    private static class C0043h extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1255a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        g f1256b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ColorStateList f1257c;
        PorterDuff.Mode d;
        boolean e;
        Bitmap f;
        ColorStateList g;
        PorterDuff.Mode h;
        int i;
        boolean j;
        boolean k;
        Paint l;

        C0043h(C0043h c0043h) {
            this.f1257c = null;
            this.d = h.l;
            if (c0043h != null) {
                this.f1255a = c0043h.f1255a;
                g gVar = new g(c0043h.f1256b);
                this.f1256b = gVar;
                if (c0043h.f1256b.e != null) {
                    gVar.e = new Paint(c0043h.f1256b.e);
                }
                if (c0043h.f1256b.d != null) {
                    this.f1256b.d = new Paint(c0043h.f1256b.d);
                }
                this.f1257c = c0043h.f1257c;
                this.d = c0043h.d;
                this.e = c0043h.e;
            }
        }

        public boolean a(int i, int i2) {
            return i == this.f.getWidth() && i2 == this.f.getHeight();
        }

        public boolean b() {
            return !this.k && this.g == this.f1257c && this.h == this.d && this.j == this.e && this.i == this.f1256b.getRootAlpha();
        }

        public void c(int i, int i2) {
            if (this.f == null || !a(i, i2)) {
                this.f = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.k = true;
            }
        }

        public void d(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f, (Rect) null, rect, e(colorFilter));
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
            this.l.setAlpha(this.f1256b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public boolean f() {
            return this.f1256b.getRootAlpha() < 255;
        }

        public boolean g() {
            return this.f1256b.f();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1255a;
        }

        public boolean h(int[] iArr) {
            boolean zG = this.f1256b.g(iArr);
            this.k |= zG;
            return zG;
        }

        public void i() {
            this.g = this.f1257c;
            this.h = this.d;
            this.i = this.f1256b.getRootAlpha();
            this.j = this.e;
            this.k = false;
        }

        public void j(int i, int i2) {
            this.f.eraseColor(0);
            this.f1256b.b(new Canvas(this.f), i, i2, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return new h(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable(Resources resources) {
            return new h(this);
        }

        C0043h() {
            this.f1257c = null;
            this.d = h.l;
            this.f1256b = new g();
        }
    }

    h() {
        this.h = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.d = new C0043h();
    }

    static int a(int i2, float f2) {
        return (i2 & 16777215) | (((int) (Color.alpha(i2) * f2)) << 24);
    }

    @Nullable
    public static h b(@NonNull Resources resources, @DrawableRes int i2, @Nullable Resources.Theme theme) {
        if (Build.VERSION.SDK_INT < 24) {
            return d(resources, i2, theme);
        }
        h hVar = new h();
        hVar.f1245c = androidx.core.content.res.e.d(resources, i2, theme);
        return hVar;
    }

    public static h c(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        h hVar = new h();
        hVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return hVar;
    }

    static h d(@NonNull Resources resources, @DrawableRes int i2, @Nullable Resources.Theme theme) {
        int next;
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

    private void f(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        C0043h c0043h = this.d;
        g gVar = c0043h.f1256b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(gVar.h);
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
                    dVar.f1247b.add(cVar);
                    if (cVar.getPathName() != null) {
                        gVar.p.put(cVar.getPathName(), cVar);
                    }
                    z = false;
                    c0043h.f1255a = cVar.d | c0043h.f1255a;
                } else if ("clip-path".equals(name)) {
                    b bVar = new b();
                    bVar.e(resources, attributeSet, theme, xmlPullParser);
                    dVar.f1247b.add(bVar);
                    if (bVar.getPathName() != null) {
                        gVar.p.put(bVar.getPathName(), bVar);
                    }
                    c0043h.f1255a = bVar.d | c0043h.f1255a;
                } else if ("group".equals(name)) {
                    d dVar2 = new d();
                    dVar2.c(resources, attributeSet, theme, xmlPullParser);
                    dVar.f1247b.add(dVar2);
                    arrayDeque.push(dVar2);
                    if (dVar2.getGroupName() != null) {
                        gVar.p.put(dVar2.getGroupName(), dVar2);
                    }
                    c0043h.f1255a = dVar2.k | c0043h.f1255a;
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

    private boolean g() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && androidx.core.graphics.drawable.a.e(this) == 1;
    }

    private static PorterDuff.Mode h(int i2, PorterDuff.Mode mode) {
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

    private void j(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) throws XmlPullParserException {
        C0043h c0043h = this.d;
        g gVar = c0043h.f1256b;
        c0043h.d = h(androidx.core.content.res.f.k(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateListG = androidx.core.content.res.f.g(typedArray, xmlPullParser, theme, "tint", 1);
        if (colorStateListG != null) {
            c0043h.f1257c = colorStateListG;
        }
        c0043h.e = androidx.core.content.res.f.e(typedArray, xmlPullParser, "autoMirrored", 5, c0043h.e);
        gVar.k = androidx.core.content.res.f.j(typedArray, xmlPullParser, "viewportWidth", 7, gVar.k);
        float fJ = androidx.core.content.res.f.j(typedArray, xmlPullParser, "viewportHeight", 8, gVar.l);
        gVar.l = fJ;
        if (gVar.k <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (fJ <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
        gVar.i = typedArray.getDimension(3, gVar.i);
        float dimension = typedArray.getDimension(2, gVar.j);
        gVar.j = dimension;
        if (gVar.i <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
        }
        if (dimension <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
        }
        gVar.setAlpha(androidx.core.content.res.f.j(typedArray, xmlPullParser, "alpha", 4, gVar.getAlpha()));
        String string = typedArray.getString(0);
        if (string != null) {
            gVar.n = string;
            gVar.p.put(string, gVar);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f1245c;
        if (drawable == null) {
            return false;
        }
        androidx.core.graphics.drawable.a.b(drawable);
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.k);
        if (this.k.width() <= 0 || this.k.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f;
        if (colorFilter == null) {
            colorFilter = this.e;
        }
        canvas.getMatrix(this.j);
        this.j.getValues(this.i);
        float fAbs = Math.abs(this.i[0]);
        float fAbs2 = Math.abs(this.i[4]);
        float fAbs3 = Math.abs(this.i[1]);
        float fAbs4 = Math.abs(this.i[3]);
        if (fAbs3 != 0.0f || fAbs4 != 0.0f) {
            fAbs = 1.0f;
            fAbs2 = 1.0f;
        }
        int iMin = Math.min(2048, (int) (this.k.width() * fAbs));
        int iMin2 = Math.min(2048, (int) (this.k.height() * fAbs2));
        if (iMin <= 0 || iMin2 <= 0) {
            return;
        }
        int iSave = canvas.save();
        Rect rect = this.k;
        canvas.translate(rect.left, rect.top);
        if (g()) {
            canvas.translate(this.k.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.k.offsetTo(0, 0);
        this.d.c(iMin, iMin2);
        if (!this.h) {
            this.d.j(iMin, iMin2);
        } else if (!this.d.b()) {
            this.d.j(iMin, iMin2);
            this.d.i();
        }
        this.d.d(canvas, colorFilter, this.k);
        canvas.restoreToCount(iSave);
    }

    Object e(String str) {
        return this.d.f1256b.p.get(str);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f1245c;
        return drawable != null ? androidx.core.graphics.drawable.a.c(drawable) : this.d.f1256b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return this.d.getChangingConfigurations() | super.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f1245c;
        return drawable != null ? androidx.core.graphics.drawable.a.d(drawable) : this.f;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.f1245c != null && Build.VERSION.SDK_INT >= 24) {
            return new i(this.f1245c.getConstantState());
        }
        this.d.f1255a = getChangingConfigurations();
        return this.d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f1245c;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.d.f1256b.j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f1245c;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.d.f1256b.i;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    void i(boolean z) {
        this.h = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f1245c;
        return drawable != null ? androidx.core.graphics.drawable.a.g(drawable) : this.d.e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        C0043h c0043h;
        ColorStateList colorStateList;
        Drawable drawable = this.f1245c;
        return drawable != null ? drawable.isStateful() : super.isStateful() || ((c0043h = this.d) != null && (c0043h.g() || ((colorStateList = this.d.f1257c) != null && colorStateList.isStateful())));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.g && super.mutate() == this) {
            this.d = new C0043h(this.d);
            this.g = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        C0043h c0043h = this.d;
        ColorStateList colorStateList = c0043h.f1257c;
        if (colorStateList != null && (mode = c0043h.d) != null) {
            this.e = updateTintFilter(this.e, colorStateList, mode);
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
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else if (this.d.f1256b.getRootAlpha() != i2) {
            this.d.f1256b.setRootAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.i(drawable, z);
        } else {
            this.d.e = z;
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i2) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.m(drawable, i2);
        } else {
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.n(drawable, colorStateList);
            return;
        }
        C0043h c0043h = this.d;
        if (c0043h.f1257c != colorStateList) {
            c0043h.f1257c = colorStateList;
            this.e = updateTintFilter(this.e, colorStateList, c0043h.d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.o(drawable, mode);
            return;
        }
        C0043h c0043h = this.d;
        if (c0043h.d != mode) {
            c0043h.d = mode;
            this.e = updateTintFilter(this.e, c0043h.f1257c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f1245c;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f1245c;
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

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(24)
    private static class i extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Drawable.ConstantState f1258a;

        i(Drawable.ConstantState constantState) {
            this.f1258a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f1258a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1258a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVar = new h();
            hVar.f1245c = (VectorDrawable) this.f1258a.newDrawable();
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            h hVar = new h();
            hVar.f1245c = (VectorDrawable) this.f1258a.newDrawable(resources);
            return hVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            h hVar = new h();
            hVar.f1245c = (VectorDrawable) this.f1258a.newDrawable(resources, theme);
            return hVar;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f = colorFilter;
            invalidateSelf();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static abstract class f extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected c.b[] f1249a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f1250b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1251c;
        int d;

        f() {
            super();
            this.f1249a = null;
            this.f1251c = 0;
        }

        public boolean c() {
            return false;
        }

        public void d(Path path) {
            path.reset();
            c.b[] bVarArr = this.f1249a;
            if (bVarArr != null) {
                c.b.e(bVarArr, path);
            }
        }

        public c.b[] getPathData() {
            return this.f1249a;
        }

        public String getPathName() {
            return this.f1250b;
        }

        public void setPathData(c.b[] bVarArr) {
            if (androidx.core.graphics.c.b(this.f1249a, bVarArr)) {
                androidx.core.graphics.c.j(this.f1249a, bVarArr);
            } else {
                this.f1249a = androidx.core.graphics.c.f(bVarArr);
            }
        }

        f(f fVar) {
            super();
            this.f1249a = null;
            this.f1251c = 0;
            this.f1250b = fVar.f1250b;
            this.d = fVar.d;
            this.f1249a = androidx.core.graphics.c.f(fVar.f1249a);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.f(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        C0043h c0043h = this.d;
        c0043h.f1256b = new g();
        TypedArray typedArrayS = androidx.core.content.res.f.s(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.f1233a);
        j(typedArrayS, xmlPullParser, theme);
        typedArrayS.recycle();
        c0043h.f1255a = getChangingConfigurations();
        c0043h.k = true;
        f(resources, xmlPullParser, attributeSet, theme);
        this.e = updateTintFilter(this.e, c0043h.f1257c, c0043h.d);
    }

    h(@NonNull C0043h c0043h) {
        this.h = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.d = c0043h;
        this.e = updateTintFilter(this.e, c0043h.f1257c, c0043h.d);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class c extends f {
        private int[] e;
        androidx.core.content.res.b f;
        float g;
        androidx.core.content.res.b h;
        float i;
        float j;
        float k;
        float l;
        float m;
        Paint.Cap n;
        Paint.Join o;
        float p;

        c() {
            this.g = 0.0f;
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
            this.e = null;
            if (androidx.core.content.res.f.r(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f1250b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f1249a = androidx.core.graphics.c.d(string2);
                }
                this.h = androidx.core.content.res.f.i(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.j = androidx.core.content.res.f.j(typedArray, xmlPullParser, "fillAlpha", 12, this.j);
                this.n = e(androidx.core.content.res.f.k(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.n);
                this.o = f(androidx.core.content.res.f.k(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.o);
                this.p = androidx.core.content.res.f.j(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.p);
                this.f = androidx.core.content.res.f.i(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.i = androidx.core.content.res.f.j(typedArray, xmlPullParser, "strokeAlpha", 11, this.i);
                this.g = androidx.core.content.res.f.j(typedArray, xmlPullParser, "strokeWidth", 4, this.g);
                this.l = androidx.core.content.res.f.j(typedArray, xmlPullParser, "trimPathEnd", 6, this.l);
                this.m = androidx.core.content.res.f.j(typedArray, xmlPullParser, "trimPathOffset", 7, this.m);
                this.k = androidx.core.content.res.f.j(typedArray, xmlPullParser, "trimPathStart", 5, this.k);
                this.f1251c = androidx.core.content.res.f.k(typedArray, xmlPullParser, "fillType", 13, this.f1251c);
            }
        }

        @Override // androidx.vectordrawable.a.a.h.e
        public boolean a() {
            return this.h.i() || this.f.i();
        }

        @Override // androidx.vectordrawable.a.a.h.e
        public boolean b(int[] iArr) {
            return this.f.j(iArr) | this.h.j(iArr);
        }

        public void g(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayS = androidx.core.content.res.f.s(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.f1235c);
            h(typedArrayS, xmlPullParser, theme);
            typedArrayS.recycle();
        }

        float getFillAlpha() {
            return this.j;
        }

        @ColorInt
        int getFillColor() {
            return this.h.e();
        }

        float getStrokeAlpha() {
            return this.i;
        }

        @ColorInt
        int getStrokeColor() {
            return this.f.e();
        }

        float getStrokeWidth() {
            return this.g;
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

        void setFillAlpha(float f) {
            this.j = f;
        }

        void setFillColor(int i) {
            this.h.k(i);
        }

        void setStrokeAlpha(float f) {
            this.i = f;
        }

        void setStrokeColor(int i) {
            this.f.k(i);
        }

        void setStrokeWidth(float f) {
            this.g = f;
        }

        void setTrimPathEnd(float f) {
            this.l = f;
        }

        void setTrimPathOffset(float f) {
            this.m = f;
        }

        void setTrimPathStart(float f) {
            this.k = f;
        }

        c(c cVar) {
            super(cVar);
            this.g = 0.0f;
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
            this.e = cVar.e;
            this.f = cVar.f;
            this.g = cVar.g;
            this.i = cVar.i;
            this.h = cVar.h;
            this.f1251c = cVar.f1251c;
            this.j = cVar.j;
            this.k = cVar.k;
            this.l = cVar.l;
            this.m = cVar.m;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class g {
        private static final Matrix q = new Matrix();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Path f1252a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Path f1253b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Matrix f1254c;
        Paint d;
        Paint e;
        private PathMeasure f;
        private int g;
        final d h;
        float i;
        float j;
        float k;
        float l;
        int m;
        String n;
        Boolean o;
        final b.a.a<String, Object> p;

        g() {
            this.f1254c = new Matrix();
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = MediaControl.ACTION_NONE;
            this.n = null;
            this.o = null;
            this.p = new b.a.a<>();
            this.h = new d();
            this.f1252a = new Path();
            this.f1253b = new Path();
        }

        private static float a(float f, float f2, float f3, float f4) {
            return (f * f4) - (f2 * f3);
        }

        private void c(d dVar, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            dVar.f1246a.set(matrix);
            dVar.f1246a.preConcat(dVar.j);
            canvas.save();
            for (int i3 = 0; i3 < dVar.f1247b.size(); i3++) {
                e eVar = dVar.f1247b.get(i3);
                if (eVar instanceof d) {
                    c((d) eVar, dVar.f1246a, canvas, i, i2, colorFilter);
                } else if (eVar instanceof f) {
                    d(dVar, (f) eVar, canvas, i, i2, colorFilter);
                }
            }
            canvas.restore();
        }

        private void d(d dVar, f fVar, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            float f = i / this.k;
            float f2 = i2 / this.l;
            float fMin = Math.min(f, f2);
            Matrix matrix = dVar.f1246a;
            this.f1254c.set(matrix);
            this.f1254c.postScale(f, f2);
            float fE = e(matrix);
            if (fE == 0.0f) {
                return;
            }
            fVar.d(this.f1252a);
            Path path = this.f1252a;
            this.f1253b.reset();
            if (fVar.c()) {
                this.f1253b.setFillType(fVar.f1251c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.f1253b.addPath(path, this.f1254c);
                canvas.clipPath(this.f1253b);
                return;
            }
            c cVar = (c) fVar;
            if (cVar.k != 0.0f || cVar.l != 1.0f) {
                float f3 = cVar.k;
                float f4 = cVar.m;
                float f5 = (f3 + f4) % 1.0f;
                float f6 = (cVar.l + f4) % 1.0f;
                if (this.f == null) {
                    this.f = new PathMeasure();
                }
                this.f.setPath(this.f1252a, false);
                float length = this.f.getLength();
                float f7 = f5 * length;
                float f8 = f6 * length;
                path.reset();
                if (f7 > f8) {
                    this.f.getSegment(f7, length, path, true);
                    this.f.getSegment(0.0f, f8, path, true);
                } else {
                    this.f.getSegment(f7, f8, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.f1253b.addPath(path, this.f1254c);
            if (cVar.h.l()) {
                androidx.core.content.res.b bVar = cVar.h;
                if (this.e == null) {
                    Paint paint = new Paint(1);
                    this.e = paint;
                    paint.setStyle(Paint.Style.FILL);
                }
                Paint paint2 = this.e;
                if (bVar.h()) {
                    Shader shaderF = bVar.f();
                    shaderF.setLocalMatrix(this.f1254c);
                    paint2.setShader(shaderF);
                    paint2.setAlpha(Math.round(cVar.j * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(MediaControl.ACTION_NONE);
                    paint2.setColor(h.a(bVar.e(), cVar.j));
                }
                paint2.setColorFilter(colorFilter);
                this.f1253b.setFillType(cVar.f1251c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.f1253b, paint2);
            }
            if (cVar.f.l()) {
                androidx.core.content.res.b bVar2 = cVar.f;
                if (this.d == null) {
                    Paint paint3 = new Paint(1);
                    this.d = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                }
                Paint paint4 = this.d;
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
                    shaderF2.setLocalMatrix(this.f1254c);
                    paint4.setShader(shaderF2);
                    paint4.setAlpha(Math.round(cVar.i * 255.0f));
                } else {
                    paint4.setShader(null);
                    paint4.setAlpha(MediaControl.ACTION_NONE);
                    paint4.setColor(h.a(bVar2.e(), cVar.i));
                }
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(cVar.g * fMin * fE);
                canvas.drawPath(this.f1253b, paint4);
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
            c(this.h, q, canvas, i, i2, colorFilter);
        }

        public boolean f() {
            if (this.o == null) {
                this.o = Boolean.valueOf(this.h.a());
            }
            return this.o.booleanValue();
        }

        public boolean g(int[] iArr) {
            return this.h.b(iArr);
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.m;
        }

        public void setAlpha(float f) {
            setRootAlpha((int) (f * 255.0f));
        }

        public void setRootAlpha(int i) {
            this.m = i;
        }

        g(g gVar) {
            this.f1254c = new Matrix();
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = MediaControl.ACTION_NONE;
            this.n = null;
            this.o = null;
            b.a.a<String, Object> aVar = new b.a.a<>();
            this.p = aVar;
            this.h = new d(gVar.h, aVar);
            this.f1252a = new Path(gVar.f1252a);
            this.f1253b = new Path(gVar.f1253b);
            this.i = gVar.i;
            this.j = gVar.j;
            this.k = gVar.k;
            this.l = gVar.l;
            this.g = gVar.g;
            this.m = gVar.m;
            this.n = gVar.n;
            String str = gVar.n;
            if (str != null) {
                this.p.put(str, this);
            }
            this.o = gVar.o;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class d extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Matrix f1246a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ArrayList<e> f1247b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1248c;
        private float d;
        private float e;
        private float f;
        private float g;
        private float h;
        private float i;
        final Matrix j;
        int k;
        private int[] l;
        private String m;

        d(d dVar, b.a.a<String, Object> aVar) {
            f bVar;
            super();
            this.f1246a = new Matrix();
            this.f1247b = new ArrayList<>();
            this.f1248c = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
            this.f1248c = dVar.f1248c;
            this.d = dVar.d;
            this.e = dVar.e;
            this.f = dVar.f;
            this.g = dVar.g;
            this.h = dVar.h;
            this.i = dVar.i;
            this.l = dVar.l;
            String str = dVar.m;
            this.m = str;
            this.k = dVar.k;
            if (str != null) {
                aVar.put(str, this);
            }
            this.j.set(dVar.j);
            ArrayList<e> arrayList = dVar.f1247b;
            for (int i = 0; i < arrayList.size(); i++) {
                e eVar = arrayList.get(i);
                if (eVar instanceof d) {
                    this.f1247b.add(new d((d) eVar, aVar));
                } else {
                    if (eVar instanceof c) {
                        bVar = new c((c) eVar);
                    } else {
                        if (!(eVar instanceof b)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        bVar = new b((b) eVar);
                    }
                    this.f1247b.add(bVar);
                    String str2 = bVar.f1250b;
                    if (str2 != null) {
                        aVar.put(str2, bVar);
                    }
                }
            }
        }

        private void d() {
            this.j.reset();
            this.j.postTranslate(-this.d, -this.e);
            this.j.postScale(this.f, this.g);
            this.j.postRotate(this.f1248c, 0.0f, 0.0f);
            this.j.postTranslate(this.h + this.d, this.i + this.e);
        }

        private void e(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.l = null;
            this.f1248c = androidx.core.content.res.f.j(typedArray, xmlPullParser, "rotation", 5, this.f1248c);
            this.d = typedArray.getFloat(1, this.d);
            this.e = typedArray.getFloat(2, this.e);
            this.f = androidx.core.content.res.f.j(typedArray, xmlPullParser, "scaleX", 3, this.f);
            this.g = androidx.core.content.res.f.j(typedArray, xmlPullParser, "scaleY", 4, this.g);
            this.h = androidx.core.content.res.f.j(typedArray, xmlPullParser, "translateX", 6, this.h);
            this.i = androidx.core.content.res.f.j(typedArray, xmlPullParser, "translateY", 7, this.i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.m = string;
            }
            d();
        }

        @Override // androidx.vectordrawable.a.a.h.e
        public boolean a() {
            for (int i = 0; i < this.f1247b.size(); i++) {
                if (this.f1247b.get(i).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.vectordrawable.a.a.h.e
        public boolean b(int[] iArr) {
            boolean zB = false;
            for (int i = 0; i < this.f1247b.size(); i++) {
                zB |= this.f1247b.get(i).b(iArr);
            }
            return zB;
        }

        public void c(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayS = androidx.core.content.res.f.s(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.f1234b);
            e(typedArrayS, xmlPullParser);
            typedArrayS.recycle();
        }

        public String getGroupName() {
            return this.m;
        }

        public Matrix getLocalMatrix() {
            return this.j;
        }

        public float getPivotX() {
            return this.d;
        }

        public float getPivotY() {
            return this.e;
        }

        public float getRotation() {
            return this.f1248c;
        }

        public float getScaleX() {
            return this.f;
        }

        public float getScaleY() {
            return this.g;
        }

        public float getTranslateX() {
            return this.h;
        }

        public float getTranslateY() {
            return this.i;
        }

        public void setPivotX(float f) {
            if (f != this.d) {
                this.d = f;
                d();
            }
        }

        public void setPivotY(float f) {
            if (f != this.e) {
                this.e = f;
                d();
            }
        }

        public void setRotation(float f) {
            if (f != this.f1248c) {
                this.f1248c = f;
                d();
            }
        }

        public void setScaleX(float f) {
            if (f != this.f) {
                this.f = f;
                d();
            }
        }

        public void setScaleY(float f) {
            if (f != this.g) {
                this.g = f;
                d();
            }
        }

        public void setTranslateX(float f) {
            if (f != this.h) {
                this.h = f;
                d();
            }
        }

        public void setTranslateY(float f) {
            if (f != this.i) {
                this.i = f;
                d();
            }
        }

        d() {
            super();
            this.f1246a = new Matrix();
            this.f1247b = new ArrayList<>();
            this.f1248c = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
        }
    }
}
