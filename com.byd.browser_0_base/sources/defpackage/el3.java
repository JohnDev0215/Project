package defpackage;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
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
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import defpackage.ey1;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: VectorDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class el3 extends dl3 {
    public static final PorterDuff.Mode n = PorterDuff.Mode.SRC_IN;
    public g f;
    public PorterDuffColorFilter g;
    public ColorFilter h;
    public boolean i;
    public boolean j;
    public final float[] k;
    public final Matrix l;
    public final Rect m;

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class a extends e {
        public a() {
        }

        public a(a aVar) {
            super(aVar);
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static abstract class d {
        public boolean a() {
            return false;
        }

        public boolean b(int[] iArr) {
            return false;
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class g extends Drawable.ConstantState {
        public int a;
        public f b;
        public ColorStateList c;
        public PorterDuff.Mode d;
        public boolean e;
        public Bitmap f;
        public ColorStateList g;
        public PorterDuff.Mode h;
        public int i;
        public boolean j;
        public boolean k;
        public Paint l;

        public g(g gVar) {
            this.c = null;
            this.d = el3.n;
            if (gVar != null) {
                this.a = gVar.a;
                f fVar = new f(gVar.b);
                this.b = fVar;
                if (gVar.b.e != null) {
                    fVar.e = new Paint(gVar.b.e);
                }
                if (gVar.b.d != null) {
                    this.b.d = new Paint(gVar.b.d);
                }
                this.c = gVar.c;
                this.d = gVar.d;
                this.e = gVar.e;
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            return new el3(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable(Resources resources) {
            return new el3(this);
        }

        public g() {
            this.c = null;
            this.d = el3.n;
            this.b = new f();
        }
    }

    public el3() {
        this.j = true;
        this.k = new float[9];
        this.l = new Matrix();
        this.m = new Rect();
        this.f = new g();
    }

    public final PorterDuffColorFilter b(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        Drawable drawable = this.e;
        if (drawable == null) {
            return false;
        }
        drawable.canApplyTheme();
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00bd  */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void draw(android.graphics.Canvas r19) {
        /*
            Method dump skipped, instruction units count: 382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.el3.draw(android.graphics.Canvas):void");
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        Drawable drawable = this.e;
        return drawable != null ? drawable.getAlpha() : this.f.b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        Drawable drawable = this.e;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return this.f.getChangingConfigurations() | super.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        Drawable drawable = this.e;
        return drawable != null ? drawable.getColorFilter() : this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (this.e != null) {
            return new h(this.e.getConstantState());
        }
        this.f.a = getChangingConfigurations();
        return this.f;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        Drawable drawable = this.e;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f.b.i;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        Drawable drawable = this.e;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f.b.h;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.e;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        Drawable drawable = this.e;
        return drawable != null ? drawable.isAutoMirrored() : this.f.e;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        ColorStateList colorStateList;
        Drawable drawable = this.e;
        if (drawable != null) {
            return drawable.isStateful();
        }
        if (!super.isStateful()) {
            g gVar = this.f;
            if (gVar != null) {
                f fVar = gVar.b;
                if (fVar.n == null) {
                    fVar.n = Boolean.valueOf(fVar.g.a());
                }
                if (fVar.n.booleanValue() || ((colorStateList = this.f.c) != null && colorStateList.isStateful())) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.i && super.mutate() == this) {
            this.f = new g(this.f);
            this.i = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        boolean z;
        PorterDuff.Mode mode;
        Drawable drawable = this.e;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        g gVar = this.f;
        ColorStateList colorStateList = gVar.c;
        if (colorStateList == null || (mode = gVar.d) == null) {
            z = false;
        } else {
            this.g = b(colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        f fVar = gVar.b;
        if (fVar.n == null) {
            fVar.n = Boolean.valueOf(fVar.g.a());
        }
        if (fVar.n.booleanValue()) {
            boolean zB = gVar.b.g.b(iArr);
            gVar.k |= zB;
            if (zB) {
                invalidateSelf();
                return true;
            }
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public final void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.f.b.getRootAlpha() != i) {
            this.f.b.setRootAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z) {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.setAutoMirrored(z);
        } else {
            this.f.e = z;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.h = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        Drawable drawable = this.e;
        if (drawable != null) {
            sd0.a(i, drawable);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.setTintList(colorStateList);
            return;
        }
        g gVar = this.f;
        if (gVar.c != colorStateList) {
            gVar.c = colorStateList;
            this.g = b(colorStateList, gVar.d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.setTintMode(mode);
            return;
        }
        g gVar = this.f;
        if (gVar.d != mode) {
            gVar.d = mode;
            this.g = b(gVar.c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.e;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public final void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class h extends Drawable.ConstantState {
        public final Drawable.ConstantState a;

        public h(Drawable.ConstantState constantState) {
            this.a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final boolean canApplyTheme() {
            return this.a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            el3 el3Var = new el3();
            el3Var.e = (VectorDrawable) this.a.newDrawable();
            return el3Var;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable(Resources resources) {
            el3 el3Var = new el3();
            el3Var.e = (VectorDrawable) this.a.newDrawable(resources);
            return el3Var;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable(Resources resources, Resources.Theme theme) {
            el3 el3Var = new el3();
            el3Var.e = (VectorDrawable) this.a.newDrawable(resources, theme);
            return el3Var;
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static abstract class e extends d {
        public ey1.a[] a;
        public String b;
        public int c;
        public final int d;

        public e() {
            this.a = null;
            this.c = 0;
        }

        public ey1.a[] getPathData() {
            return this.a;
        }

        public String getPathName() {
            return this.b;
        }

        public void setPathData(ey1.a[] aVarArr) {
            if (!ey1.a(this.a, aVarArr)) {
                this.a = ey1.e(aVarArr);
                return;
            }
            ey1.a[] aVarArr2 = this.a;
            for (int i = 0; i < aVarArr.length; i++) {
                aVarArr2[i].a = aVarArr[i].a;
                int i2 = 0;
                while (true) {
                    float[] fArr = aVarArr[i].b;
                    if (i2 < fArr.length) {
                        aVarArr2[i].b[i2] = fArr[i2];
                        i2++;
                    }
                }
            }
        }

        public e(e eVar) {
            this.a = null;
            this.c = 0;
            this.b = eVar.b;
            this.d = eVar.d;
            this.a = ey1.e(eVar.a);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        ColorStateList colorStateListA;
        f fVar;
        int i;
        boolean z;
        int i2;
        char c2;
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
            return;
        }
        g gVar = this.f;
        gVar.b = new f();
        TypedArray typedArrayH = fh3.h(resources, theme, attributeSet, i5.a);
        g gVar2 = this.f;
        f fVar2 = gVar2.b;
        int iD = fh3.d(typedArrayH, xmlPullParser, "tintMode", 6, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        int i3 = 3;
        if (iD == 3) {
            mode = PorterDuff.Mode.SRC_OVER;
        } else if (iD != 5) {
            if (iD != 9) {
                switch (iD) {
                    case 14:
                        mode = PorterDuff.Mode.MULTIPLY;
                        break;
                    case 15:
                        mode = PorterDuff.Mode.SCREEN;
                        break;
                    case 16:
                        mode = PorterDuff.Mode.ADD;
                        break;
                }
            } else {
                mode = PorterDuff.Mode.SRC_ATOP;
            }
        }
        gVar2.d = mode;
        boolean z2 = false;
        if (fh3.g(xmlPullParser, "tint")) {
            TypedValue typedValue = new TypedValue();
            typedArrayH.getValue(1, typedValue);
            int i4 = typedValue.type;
            if (i4 == 2) {
                throw new UnsupportedOperationException("Failed to resolve attribute at index 1: " + typedValue);
            }
            if (i4 >= 28 && i4 <= 31) {
                colorStateListA = ColorStateList.valueOf(typedValue.data);
            } else {
                Resources resources2 = typedArrayH.getResources();
                int resourceId = typedArrayH.getResourceId(1, 0);
                ThreadLocal<TypedValue> threadLocal = ev.a;
                try {
                    colorStateListA = ev.a(resources2, resources2.getXml(resourceId), theme);
                } catch (Exception e2) {
                    Log.e("CSLCompat", "Failed to inflate ColorStateList.", e2);
                    colorStateListA = null;
                }
            }
        } else {
            colorStateListA = null;
        }
        if (colorStateListA != null) {
            gVar2.c = colorStateListA;
        }
        boolean z3 = gVar2.e;
        if (fh3.g(xmlPullParser, "autoMirrored")) {
            z3 = typedArrayH.getBoolean(5, z3);
        }
        gVar2.e = z3;
        fVar2.j = fh3.c(typedArrayH, xmlPullParser, "viewportWidth", 7, fVar2.j);
        float fC = fh3.c(typedArrayH, xmlPullParser, "viewportHeight", 8, fVar2.k);
        fVar2.k = fC;
        if (fVar2.j <= 0.0f) {
            throw new XmlPullParserException(typedArrayH.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (fC > 0.0f) {
            fVar2.h = typedArrayH.getDimension(3, fVar2.h);
            float dimension = typedArrayH.getDimension(2, fVar2.i);
            fVar2.i = dimension;
            if (fVar2.h <= 0.0f) {
                throw new XmlPullParserException(typedArrayH.getPositionDescription() + "<vector> tag requires width > 0");
            }
            if (dimension > 0.0f) {
                fVar2.setAlpha(fh3.c(typedArrayH, xmlPullParser, "alpha", 4, fVar2.getAlpha()));
                String string = typedArrayH.getString(0);
                if (string != null) {
                    fVar2.m = string;
                    fVar2.o.put(string, fVar2);
                }
                typedArrayH.recycle();
                gVar.a = getChangingConfigurations();
                gVar.k = true;
                g gVar3 = this.f;
                f fVar3 = gVar3.b;
                ArrayDeque arrayDeque = new ArrayDeque();
                arrayDeque.push(fVar3.g);
                int eventType = xmlPullParser.getEventType();
                int depth = xmlPullParser.getDepth() + 1;
                boolean z4 = true;
                for (int i5 = 1; eventType != i5 && (xmlPullParser.getDepth() >= depth || eventType != i3); i5 = 1) {
                    if (eventType == 2) {
                        String name = xmlPullParser.getName();
                        c cVar = (c) arrayDeque.peek();
                        if (cVar != null) {
                            boolean zEquals = "path".equals(name);
                            ArrayList<d> arrayList = cVar.b;
                            i = depth;
                            m8<String, Object> m8Var = fVar3.o;
                            if (zEquals) {
                                b bVar = new b();
                                TypedArray typedArrayH2 = fh3.h(resources, theme, attributeSet, i5.c);
                                if (fh3.g(xmlPullParser, "pathData")) {
                                    String string2 = typedArrayH2.getString(0);
                                    if (string2 != null) {
                                        bVar.b = string2;
                                    }
                                    String string3 = typedArrayH2.getString(2);
                                    if (string3 != null) {
                                        bVar.a = ey1.c(string3);
                                    }
                                    bVar.g = fh3.b(typedArrayH2, xmlPullParser, theme, "fillColor", 1);
                                    fVar = fVar3;
                                    bVar.i = fh3.c(typedArrayH2, xmlPullParser, "fillAlpha", 12, bVar.i);
                                    int iD2 = fh3.d(typedArrayH2, xmlPullParser, "strokeLineCap", 8, -1);
                                    Paint.Cap cap = bVar.m;
                                    if (iD2 == 0) {
                                        cap = Paint.Cap.BUTT;
                                    } else if (iD2 == 1) {
                                        cap = Paint.Cap.ROUND;
                                    } else if (iD2 == 2) {
                                        cap = Paint.Cap.SQUARE;
                                    }
                                    bVar.m = cap;
                                    int iD3 = fh3.d(typedArrayH2, xmlPullParser, "strokeLineJoin", 9, -1);
                                    Paint.Join join = bVar.n;
                                    if (iD3 == 0) {
                                        join = Paint.Join.MITER;
                                    } else if (iD3 == 1) {
                                        join = Paint.Join.ROUND;
                                    } else if (iD3 == 2) {
                                        join = Paint.Join.BEVEL;
                                    }
                                    bVar.n = join;
                                    bVar.o = fh3.c(typedArrayH2, xmlPullParser, "strokeMiterLimit", 10, bVar.o);
                                    bVar.e = fh3.b(typedArrayH2, xmlPullParser, theme, "strokeColor", 3);
                                    bVar.h = fh3.c(typedArrayH2, xmlPullParser, "strokeAlpha", 11, bVar.h);
                                    bVar.f = fh3.c(typedArrayH2, xmlPullParser, "strokeWidth", 4, bVar.f);
                                    bVar.k = fh3.c(typedArrayH2, xmlPullParser, "trimPathEnd", 6, bVar.k);
                                    bVar.l = fh3.c(typedArrayH2, xmlPullParser, "trimPathOffset", 7, bVar.l);
                                    bVar.j = fh3.c(typedArrayH2, xmlPullParser, "trimPathStart", 5, bVar.j);
                                    bVar.c = fh3.d(typedArrayH2, xmlPullParser, "fillType", 13, bVar.c);
                                } else {
                                    fVar = fVar3;
                                }
                                typedArrayH2.recycle();
                                arrayList.add(bVar);
                                if (bVar.getPathName() != null) {
                                    m8Var.put(bVar.getPathName(), bVar);
                                }
                                gVar3.a |= bVar.d;
                                z = false;
                                c2 = 5;
                                z4 = false;
                            } else {
                                fVar = fVar3;
                                if ("clip-path".equals(name)) {
                                    a aVar = new a();
                                    if (fh3.g(xmlPullParser, "pathData")) {
                                        TypedArray typedArrayH3 = fh3.h(resources, theme, attributeSet, i5.d);
                                        String string4 = typedArrayH3.getString(0);
                                        if (string4 != null) {
                                            aVar.b = string4;
                                        }
                                        String string5 = typedArrayH3.getString(1);
                                        if (string5 != null) {
                                            aVar.a = ey1.c(string5);
                                        }
                                        aVar.c = fh3.d(typedArrayH3, xmlPullParser, "fillType", 2, 0);
                                        typedArrayH3.recycle();
                                    }
                                    arrayList.add(aVar);
                                    if (aVar.getPathName() != null) {
                                        m8Var.put(aVar.getPathName(), aVar);
                                    }
                                    gVar3.a = aVar.d | gVar3.a;
                                } else if ("group".equals(name)) {
                                    c cVar2 = new c();
                                    TypedArray typedArrayH4 = fh3.h(resources, theme, attributeSet, i5.b);
                                    c2 = 5;
                                    cVar2.c = fh3.c(typedArrayH4, xmlPullParser, "rotation", 5, cVar2.c);
                                    cVar2.d = typedArrayH4.getFloat(1, cVar2.d);
                                    cVar2.e = typedArrayH4.getFloat(2, cVar2.e);
                                    cVar2.f = fh3.c(typedArrayH4, xmlPullParser, "scaleX", 3, cVar2.f);
                                    cVar2.g = fh3.c(typedArrayH4, xmlPullParser, "scaleY", 4, cVar2.g);
                                    cVar2.h = fh3.c(typedArrayH4, xmlPullParser, "translateX", 6, cVar2.h);
                                    cVar2.i = fh3.c(typedArrayH4, xmlPullParser, "translateY", 7, cVar2.i);
                                    z = false;
                                    String string6 = typedArrayH4.getString(0);
                                    if (string6 != null) {
                                        cVar2.l = string6;
                                    }
                                    cVar2.c();
                                    typedArrayH4.recycle();
                                    arrayList.add(cVar2);
                                    arrayDeque.push(cVar2);
                                    if (cVar2.getGroupName() != null) {
                                        m8Var.put(cVar2.getGroupName(), cVar2);
                                    }
                                    gVar3.a = cVar2.k | gVar3.a;
                                }
                                z = false;
                                c2 = 5;
                            }
                        } else {
                            fVar = fVar3;
                            i = depth;
                            z = z2;
                        }
                        i2 = 3;
                    } else {
                        fVar = fVar3;
                        i = depth;
                        z = z2;
                        i2 = 3;
                        if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                            arrayDeque.pop();
                        }
                    }
                    eventType = xmlPullParser.next();
                    z2 = z;
                    i3 = i2;
                    depth = i;
                    fVar3 = fVar;
                }
                if (!z4) {
                    this.g = b(gVar.c, gVar.d);
                    return;
                }
                throw new XmlPullParserException("no path defined");
            }
            throw new XmlPullParserException(typedArrayH.getPositionDescription() + "<vector> tag requires height > 0");
        }
        throw new XmlPullParserException(typedArrayH.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
    }

    public el3(g gVar) {
        this.j = true;
        this.k = new float[9];
        this.l = new Matrix();
        this.m = new Rect();
        this.f = gVar;
        this.g = b(gVar.c, gVar.d);
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class b extends e {
        public vv e;
        public float f;
        public vv g;
        public float h;
        public float i;
        public float j;
        public float k;
        public float l;
        public Paint.Cap m;
        public Paint.Join n;
        public float o;

        public b() {
            this.f = 0.0f;
            this.h = 1.0f;
            this.i = 1.0f;
            this.j = 0.0f;
            this.k = 1.0f;
            this.l = 0.0f;
            this.m = Paint.Cap.BUTT;
            this.n = Paint.Join.MITER;
            this.o = 4.0f;
        }

        @Override // el3.d
        public final boolean a() {
            return this.g.b() || this.e.b();
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0036  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
        @Override // el3.d
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean b(int[] r6) {
            /*
                r5 = this;
                vv r0 = r5.g
                boolean r1 = r0.b()
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L1c
                android.content.res.ColorStateList r1 = r0.b
                int r4 = r1.getDefaultColor()
                int r1 = r1.getColorForState(r6, r4)
                int r4 = r0.c
                if (r1 == r4) goto L1c
                r0.c = r1
                r0 = r2
                goto L1d
            L1c:
                r0 = r3
            L1d:
                vv r5 = r5.e
                boolean r1 = r5.b()
                if (r1 == 0) goto L36
                android.content.res.ColorStateList r1 = r5.b
                int r4 = r1.getDefaultColor()
                int r6 = r1.getColorForState(r6, r4)
                int r1 = r5.c
                if (r6 == r1) goto L36
                r5.c = r6
                goto L37
            L36:
                r2 = r3
            L37:
                r5 = r2 | r0
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: el3.b.b(int[]):boolean");
        }

        public float getFillAlpha() {
            return this.i;
        }

        public int getFillColor() {
            return this.g.c;
        }

        public float getStrokeAlpha() {
            return this.h;
        }

        public int getStrokeColor() {
            return this.e.c;
        }

        public float getStrokeWidth() {
            return this.f;
        }

        public float getTrimPathEnd() {
            return this.k;
        }

        public float getTrimPathOffset() {
            return this.l;
        }

        public float getTrimPathStart() {
            return this.j;
        }

        public void setFillAlpha(float f) {
            this.i = f;
        }

        public void setFillColor(int i) {
            this.g.c = i;
        }

        public void setStrokeAlpha(float f) {
            this.h = f;
        }

        public void setStrokeColor(int i) {
            this.e.c = i;
        }

        public void setStrokeWidth(float f) {
            this.f = f;
        }

        public void setTrimPathEnd(float f) {
            this.k = f;
        }

        public void setTrimPathOffset(float f) {
            this.l = f;
        }

        public void setTrimPathStart(float f) {
            this.j = f;
        }

        public b(b bVar) {
            super(bVar);
            this.f = 0.0f;
            this.h = 1.0f;
            this.i = 1.0f;
            this.j = 0.0f;
            this.k = 1.0f;
            this.l = 0.0f;
            this.m = Paint.Cap.BUTT;
            this.n = Paint.Join.MITER;
            this.o = 4.0f;
            this.e = bVar.e;
            this.f = bVar.f;
            this.h = bVar.h;
            this.g = bVar.g;
            this.c = bVar.c;
            this.i = bVar.i;
            this.j = bVar.j;
            this.k = bVar.k;
            this.l = bVar.l;
            this.m = bVar.m;
            this.n = bVar.n;
            this.o = bVar.o;
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class c extends d {
        public final Matrix a;
        public final ArrayList<d> b;
        public float c;
        public float d;
        public float e;
        public float f;
        public float g;
        public float h;
        public float i;
        public final Matrix j;
        public final int k;
        public String l;

        public c() {
            this.a = new Matrix();
            this.b = new ArrayList<>();
            this.c = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.l = null;
        }

        @Override // el3.d
        public final boolean a() {
            int i = 0;
            while (true) {
                ArrayList<d> arrayList = this.b;
                if (i >= arrayList.size()) {
                    return false;
                }
                if (arrayList.get(i).a()) {
                    return true;
                }
                i++;
            }
        }

        @Override // el3.d
        public final boolean b(int[] iArr) {
            int i = 0;
            boolean zB = false;
            while (true) {
                ArrayList<d> arrayList = this.b;
                if (i >= arrayList.size()) {
                    return zB;
                }
                zB |= arrayList.get(i).b(iArr);
                i++;
            }
        }

        public final void c() {
            Matrix matrix = this.j;
            matrix.reset();
            matrix.postTranslate(-this.d, -this.e);
            matrix.postScale(this.f, this.g);
            matrix.postRotate(this.c, 0.0f, 0.0f);
            matrix.postTranslate(this.h + this.d, this.i + this.e);
        }

        public String getGroupName() {
            return this.l;
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
            return this.c;
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
                c();
            }
        }

        public void setPivotY(float f) {
            if (f != this.e) {
                this.e = f;
                c();
            }
        }

        public void setRotation(float f) {
            if (f != this.c) {
                this.c = f;
                c();
            }
        }

        public void setScaleX(float f) {
            if (f != this.f) {
                this.f = f;
                c();
            }
        }

        public void setScaleY(float f) {
            if (f != this.g) {
                this.g = f;
                c();
            }
        }

        public void setTranslateX(float f) {
            if (f != this.h) {
                this.h = f;
                c();
            }
        }

        public void setTranslateY(float f) {
            if (f != this.i) {
                this.i = f;
                c();
            }
        }

        public c(c cVar, m8<String, Object> m8Var) {
            e aVar;
            this.a = new Matrix();
            this.b = new ArrayList<>();
            this.c = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            Matrix matrix = new Matrix();
            this.j = matrix;
            this.l = null;
            this.c = cVar.c;
            this.d = cVar.d;
            this.e = cVar.e;
            this.f = cVar.f;
            this.g = cVar.g;
            this.h = cVar.h;
            this.i = cVar.i;
            String str = cVar.l;
            this.l = str;
            this.k = cVar.k;
            if (str != null) {
                m8Var.put(str, this);
            }
            matrix.set(cVar.j);
            ArrayList<d> arrayList = cVar.b;
            for (int i = 0; i < arrayList.size(); i++) {
                d dVar = arrayList.get(i);
                if (dVar instanceof c) {
                    this.b.add(new c((c) dVar, m8Var));
                } else {
                    if (dVar instanceof b) {
                        aVar = new b((b) dVar);
                    } else if (dVar instanceof a) {
                        aVar = new a((a) dVar);
                    } else {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    this.b.add(aVar);
                    String str2 = aVar.b;
                    if (str2 != null) {
                        m8Var.put(str2, aVar);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class f {
        public static final Matrix p = new Matrix();
        public final Path a;
        public final Path b;
        public final Matrix c;
        public Paint d;
        public Paint e;
        public PathMeasure f;
        public final c g;
        public float h;
        public float i;
        public float j;
        public float k;
        public int l;
        public String m;
        public Boolean n;
        public final m8<String, Object> o;

        public f() {
            this.c = new Matrix();
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 255;
            this.m = null;
            this.n = null;
            this.o = new m8<>();
            this.g = new c();
            this.a = new Path();
            this.b = new Path();
        }

        public final void a(c cVar, Matrix matrix, Canvas canvas, int i, int i2) {
            int i3;
            float f;
            boolean z;
            cVar.a.set(matrix);
            Matrix matrix2 = cVar.j;
            Matrix matrix3 = cVar.a;
            matrix3.preConcat(matrix2);
            canvas.save();
            char c = 0;
            int i4 = 0;
            while (true) {
                ArrayList<d> arrayList = cVar.b;
                if (i4 >= arrayList.size()) {
                    canvas.restore();
                    return;
                }
                d dVar = arrayList.get(i4);
                if (dVar instanceof c) {
                    a((c) dVar, matrix3, canvas, i, i2);
                } else {
                    if (dVar instanceof e) {
                        e eVar = (e) dVar;
                        float f2 = i / this.j;
                        float f3 = i2 / this.k;
                        float fMin = Math.min(f2, f3);
                        Matrix matrix4 = this.c;
                        matrix4.set(matrix3);
                        matrix4.postScale(f2, f3);
                        float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
                        matrix3.mapVectors(fArr);
                        float fHypot = (float) Math.hypot(fArr[c], fArr[1]);
                        i3 = i4;
                        float fHypot2 = (float) Math.hypot(fArr[2], fArr[3]);
                        float f4 = (fArr[0] * fArr[3]) - (fArr[1] * fArr[2]);
                        float fMax = Math.max(fHypot, fHypot2);
                        float fAbs = fMax > 0.0f ? Math.abs(f4) / fMax : 0.0f;
                        if (fAbs != 0.0f) {
                            eVar.getClass();
                            Path path = this.a;
                            path.reset();
                            ey1.a[] aVarArr = eVar.a;
                            if (aVarArr != null) {
                                ey1.a.b(aVarArr, path);
                            }
                            Path path2 = this.b;
                            path2.reset();
                            if (eVar instanceof a) {
                                path2.setFillType(eVar.c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                                path2.addPath(path, matrix4);
                                canvas.clipPath(path2);
                            } else {
                                b bVar = (b) eVar;
                                float f5 = bVar.j;
                                if (f5 != 0.0f || bVar.k != 1.0f) {
                                    float f6 = bVar.l;
                                    float f7 = (f5 + f6) % 1.0f;
                                    float f8 = (bVar.k + f6) % 1.0f;
                                    if (this.f == null) {
                                        this.f = new PathMeasure();
                                    }
                                    this.f.setPath(path, false);
                                    float length = this.f.getLength();
                                    float f9 = f7 * length;
                                    float f10 = f8 * length;
                                    path.reset();
                                    if (f9 > f10) {
                                        this.f.getSegment(f9, length, path, true);
                                        f = 0.0f;
                                        this.f.getSegment(0.0f, f10, path, true);
                                    } else {
                                        f = 0.0f;
                                        this.f.getSegment(f9, f10, path, true);
                                    }
                                    path.rLineTo(f, f);
                                }
                                path2.addPath(path, matrix4);
                                vv vvVar = bVar.g;
                                if ((vvVar.a != null) || vvVar.c != 0) {
                                    if (this.e == null) {
                                        Paint paint = new Paint(1);
                                        this.e = paint;
                                        paint.setStyle(Paint.Style.FILL);
                                    }
                                    Paint paint2 = this.e;
                                    Shader shader = vvVar.a;
                                    if (shader != null) {
                                        shader.setLocalMatrix(matrix4);
                                        paint2.setShader(shader);
                                        paint2.setAlpha(Math.round(bVar.i * 255.0f));
                                    } else {
                                        paint2.setShader(null);
                                        paint2.setAlpha(255);
                                        int i5 = vvVar.c;
                                        float f11 = bVar.i;
                                        PorterDuff.Mode mode = el3.n;
                                        paint2.setColor((i5 & 16777215) | (((int) (Color.alpha(i5) * f11)) << 24));
                                    }
                                    paint2.setColorFilter(null);
                                    path2.setFillType(bVar.c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                                    canvas.drawPath(path2, paint2);
                                }
                                vv vvVar2 = bVar.e;
                                if ((vvVar2.a != null) || vvVar2.c != 0) {
                                    if (this.d == null) {
                                        z = true;
                                        Paint paint3 = new Paint(1);
                                        this.d = paint3;
                                        paint3.setStyle(Paint.Style.STROKE);
                                    } else {
                                        z = true;
                                    }
                                    Paint paint4 = this.d;
                                    Paint.Join join = bVar.n;
                                    if (join != null) {
                                        paint4.setStrokeJoin(join);
                                    }
                                    Paint.Cap cap = bVar.m;
                                    if (cap != null) {
                                        paint4.setStrokeCap(cap);
                                    }
                                    paint4.setStrokeMiter(bVar.o);
                                    Shader shader2 = vvVar2.a;
                                    if (shader2 == null) {
                                        z = false;
                                    }
                                    if (z) {
                                        shader2.setLocalMatrix(matrix4);
                                        paint4.setShader(shader2);
                                        paint4.setAlpha(Math.round(bVar.h * 255.0f));
                                    } else {
                                        paint4.setShader(null);
                                        paint4.setAlpha(255);
                                        int i6 = vvVar2.c;
                                        float f12 = bVar.h;
                                        PorterDuff.Mode mode2 = el3.n;
                                        paint4.setColor((i6 & 16777215) | (((int) (Color.alpha(i6) * f12)) << 24));
                                    }
                                    paint4.setColorFilter(null);
                                    paint4.setStrokeWidth(bVar.f * fAbs * fMin);
                                    canvas.drawPath(path2, paint4);
                                }
                            }
                        }
                    }
                    i4 = i3 + 1;
                    c = 0;
                }
                i3 = i4;
                i4 = i3 + 1;
                c = 0;
            }
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.l;
        }

        public void setAlpha(float f) {
            setRootAlpha((int) (f * 255.0f));
        }

        public void setRootAlpha(int i) {
            this.l = i;
        }

        public f(f fVar) {
            this.c = new Matrix();
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 255;
            this.m = null;
            this.n = null;
            m8<String, Object> m8Var = new m8<>();
            this.o = m8Var;
            this.g = new c(fVar.g, m8Var);
            this.a = new Path(fVar.a);
            this.b = new Path(fVar.b);
            this.h = fVar.h;
            this.i = fVar.i;
            this.j = fVar.j;
            this.k = fVar.k;
            this.l = fVar.l;
            this.m = fVar.m;
            String str = fVar.m;
            if (str != null) {
                m8Var.put(str, this);
            }
            this.n = fVar.n;
        }
    }
}
