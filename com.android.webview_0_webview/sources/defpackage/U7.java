package defpackage;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayDeque;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class U7 extends K7 {
    public static final PorterDuff.Mode B = PorterDuff.Mode.SRC_IN;
    public final Rect A;
    public S7 t;
    public PorterDuffColorFilter u;
    public ColorFilter v;
    public boolean w;
    public boolean x;
    public final float[] y;
    public final Matrix z;

    public U7() {
        this.x = true;
        this.y = new float[9];
        this.z = new Matrix();
        this.A = new Rect();
        this.t = new S7();
    }

    public static int a(int i, float f) {
        return (i & 16777215) | (((int) (Color.alpha(i) * f)) << 24);
    }

    public static U7 b(Resources resources, int i, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            U7 u7 = new U7();
            u7.s = resources.getDrawable(i, theme);
            new T7(u7.s.getConstantState());
            return u7;
        }
        try {
            XmlResourceParser xml = resources.getXml(i);
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
        } catch (IOException e) {
            Log.e("VectorDrawableCompat", "parser error", e);
            return null;
        } catch (XmlPullParserException e2) {
            Log.e("VectorDrawableCompat", "parser error", e2);
            return null;
        }
    }

    public static U7 c(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        U7 u7 = new U7();
        u7.inflate(resources, xmlPullParser, attributeSet, theme);
        return u7;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.s;
        if (drawable == null) {
            return false;
        }
        drawable.canApplyTheme();
        return false;
    }

    public PorterDuffColorFilter d(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0115  */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(android.graphics.Canvas r14) {
        /*
            Method dump skipped, instruction units count: 427
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.U7.draw(android.graphics.Canvas):void");
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.s;
        return drawable != null ? drawable.getAlpha() : this.t.b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.s;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.t.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.s != null && Build.VERSION.SDK_INT >= 24) {
            return new T7(this.s.getConstantState());
        }
        this.t.a = getChangingConfigurations();
        return this.t;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.s;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.t.b.j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.s;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.t.b.i;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.s;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.s;
        return drawable != null ? drawable.isAutoMirrored() : this.t.e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        S7 s7;
        ColorStateList colorStateList;
        Drawable drawable = this.s;
        return drawable != null ? drawable.isStateful() : super.isStateful() || ((s7 = this.t) != null && (s7.a() || ((colorStateList = this.t.c) != null && colorStateList.isStateful())));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.w && super.mutate() == this) {
            this.t = new S7(this.t);
            this.w = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.s;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        S7 s7 = this.t;
        ColorStateList colorStateList = s7.c;
        if (colorStateList != null && (mode = s7.d) != null) {
            this.u = d(colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (s7.a()) {
            boolean zB = s7.b.h.b(iArr);
            s7.k |= zB;
            if (zB) {
                invalidateSelf();
                return true;
            }
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.t.b.getRootAlpha() != i) {
            this.t.b.setRootAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.setAutoMirrored(z);
        } else {
            this.t.e = z;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.v = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i) {
        Drawable drawable = this.s;
        if (drawable != null) {
            B2.a(drawable, i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.s;
        if (drawable != null) {
            B2.b(drawable, colorStateList);
            return;
        }
        S7 s7 = this.t;
        if (s7.c != colorStateList) {
            s7.c = colorStateList;
            this.u = d(colorStateList, s7.d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.s;
        if (drawable != null) {
            B2.c(drawable, mode);
            return;
        }
        S7 s7 = this.t;
        if (s7.d != mode) {
            s7.d = mode;
            this.u = d(s7.c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.s;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int i;
        ArrayDeque arrayDeque;
        R7 r7;
        ArrayDeque arrayDeque2;
        R7 r72;
        TypedArray typedArray;
        N7 n7;
        int i2;
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
            return;
        }
        S7 s7 = this.t;
        s7.b = new R7();
        TypedArray typedArrayG = AbstractC1195n2.g(resources, theme, attributeSet, C7.a);
        S7 s72 = this.t;
        R7 r73 = s72.b;
        int iD = AbstractC1195n2.d(typedArrayG, xmlPullParser, "tintMode", 6, -1);
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
        s72.d = mode;
        int i4 = 1;
        ColorStateList colorStateList = typedArrayG.getColorStateList(1);
        if (colorStateList != null) {
            s72.c = colorStateList;
        }
        boolean z = s72.e;
        if (AbstractC1195n2.f(xmlPullParser, "autoMirrored")) {
            z = typedArrayG.getBoolean(5, z);
        }
        s72.e = z;
        r73.k = AbstractC1195n2.c(typedArrayG, xmlPullParser, "viewportWidth", 7, r73.k);
        float fC = AbstractC1195n2.c(typedArrayG, xmlPullParser, "viewportHeight", 8, r73.l);
        r73.l = fC;
        if (r73.k <= 0.0f) {
            throw new XmlPullParserException(typedArrayG.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (fC > 0.0f) {
            r73.i = typedArrayG.getDimension(3, r73.i);
            int i5 = 2;
            float dimension = typedArrayG.getDimension(2, r73.j);
            r73.j = dimension;
            if (r73.i <= 0.0f) {
                throw new XmlPullParserException(typedArrayG.getPositionDescription() + "<vector> tag requires width > 0");
            }
            if (dimension > 0.0f) {
                r73.setAlpha(AbstractC1195n2.c(typedArrayG, xmlPullParser, "alpha", 4, r73.getAlpha()));
                String string = typedArrayG.getString(0);
                if (string != null) {
                    r73.n = string;
                    r73.p.put(string, r73);
                }
                typedArrayG.recycle();
                s7.a = getChangingConfigurations();
                s7.k = true;
                S7 s73 = this.t;
                R7 r74 = s73.b;
                ArrayDeque arrayDeque3 = new ArrayDeque();
                arrayDeque3.push(r74.h);
                int eventType = xmlPullParser.getEventType();
                int depth = xmlPullParser.getDepth() + 1;
                boolean z2 = true;
                while (eventType != i4 && (xmlPullParser.getDepth() >= depth || eventType != i3)) {
                    if (eventType == i5) {
                        String name = xmlPullParser.getName();
                        O7 o7 = (O7) arrayDeque3.peek();
                        if ("path".equals(name)) {
                            N7 n72 = new N7();
                            TypedArray typedArrayG2 = AbstractC1195n2.g(resources, theme, attributeSet, C7.c);
                            n72.d = null;
                            if (AbstractC1195n2.f(xmlPullParser, "pathData")) {
                                String string2 = typedArrayG2.getString(0);
                                if (string2 != null) {
                                    n72.b = string2;
                                }
                                String string3 = typedArrayG2.getString(2);
                                if (string3 != null) {
                                    n72.a = AbstractC1360q2.c(string3);
                                }
                                arrayDeque2 = arrayDeque3;
                                r72 = r74;
                                i = depth;
                                typedArray = typedArrayG2;
                                n7 = n72;
                                n7.g = AbstractC1195n2.b(typedArrayG2, xmlPullParser, theme, "fillColor", 1, 0);
                                n7.j = AbstractC1195n2.c(typedArray, xmlPullParser, "fillAlpha", 12, n7.j);
                                int iD2 = AbstractC1195n2.d(typedArray, xmlPullParser, "strokeLineCap", 8, -1);
                                Paint.Cap cap = n7.n;
                                if (iD2 == 0) {
                                    i2 = 2;
                                    cap = Paint.Cap.BUTT;
                                } else if (iD2 != 1) {
                                    i2 = 2;
                                    if (iD2 == 2) {
                                        cap = Paint.Cap.SQUARE;
                                    }
                                } else {
                                    i2 = 2;
                                    cap = Paint.Cap.ROUND;
                                }
                                n7.n = cap;
                                int iD3 = AbstractC1195n2.d(typedArray, xmlPullParser, "strokeLineJoin", 9, -1);
                                Paint.Join join = n7.o;
                                if (iD3 == 0) {
                                    join = Paint.Join.MITER;
                                } else if (iD3 == 1) {
                                    join = Paint.Join.ROUND;
                                } else if (iD3 == i2) {
                                    join = Paint.Join.BEVEL;
                                }
                                n7.o = join;
                                n7.p = AbstractC1195n2.c(typedArray, xmlPullParser, "strokeMiterLimit", 10, n7.p);
                                n7.e = AbstractC1195n2.b(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                                n7.h = AbstractC1195n2.c(typedArray, xmlPullParser, "strokeAlpha", 11, n7.h);
                                n7.f = AbstractC1195n2.c(typedArray, xmlPullParser, "strokeWidth", 4, n7.f);
                                n7.l = AbstractC1195n2.c(typedArray, xmlPullParser, "trimPathEnd", 6, n7.l);
                                n7.m = AbstractC1195n2.c(typedArray, xmlPullParser, "trimPathOffset", 7, n7.m);
                                n7.k = AbstractC1195n2.c(typedArray, xmlPullParser, "trimPathStart", 5, n7.k);
                                n7.i = AbstractC1195n2.d(typedArray, xmlPullParser, "fillType", 13, n7.i);
                            } else {
                                arrayDeque2 = arrayDeque3;
                                r72 = r74;
                                n7 = n72;
                                i = depth;
                                typedArray = typedArrayG2;
                            }
                            typedArray.recycle();
                            o7.b.add(n7);
                            r7 = r72;
                            if (n7.getPathName() != null) {
                                r7.p.put(n7.getPathName(), n7);
                            }
                            s73.a |= n7.c;
                            arrayDeque = arrayDeque2;
                            z2 = false;
                        } else {
                            ArrayDeque arrayDeque4 = arrayDeque3;
                            r7 = r74;
                            i = depth;
                            if ("clip-path".equals(name)) {
                                M7 m7 = new M7();
                                if (AbstractC1195n2.f(xmlPullParser, "pathData")) {
                                    TypedArray typedArrayG3 = AbstractC1195n2.g(resources, theme, attributeSet, C7.d);
                                    String string4 = typedArrayG3.getString(0);
                                    if (string4 != null) {
                                        m7.b = string4;
                                    }
                                    String string5 = typedArrayG3.getString(1);
                                    if (string5 != null) {
                                        m7.a = AbstractC1360q2.c(string5);
                                    }
                                    typedArrayG3.recycle();
                                }
                                o7.b.add(m7);
                                if (m7.getPathName() != null) {
                                    r7.p.put(m7.getPathName(), m7);
                                }
                                s73.a = m7.c | s73.a;
                            } else if ("group".equals(name)) {
                                O7 o72 = new O7();
                                TypedArray typedArrayG4 = AbstractC1195n2.g(resources, theme, attributeSet, C7.b);
                                o72.l = null;
                                o72.c = AbstractC1195n2.c(typedArrayG4, xmlPullParser, "rotation", 5, o72.c);
                                o72.d = typedArrayG4.getFloat(1, o72.d);
                                o72.e = typedArrayG4.getFloat(2, o72.e);
                                o72.f = AbstractC1195n2.c(typedArrayG4, xmlPullParser, "scaleX", 3, o72.f);
                                o72.g = AbstractC1195n2.c(typedArrayG4, xmlPullParser, "scaleY", 4, o72.g);
                                o72.h = AbstractC1195n2.c(typedArrayG4, xmlPullParser, "translateX", 6, o72.h);
                                o72.i = AbstractC1195n2.c(typedArrayG4, xmlPullParser, "translateY", 7, o72.i);
                                String string6 = typedArrayG4.getString(0);
                                if (string6 != null) {
                                    o72.m = string6;
                                }
                                o72.c();
                                typedArrayG4.recycle();
                                o7.b.add(o72);
                                arrayDeque = arrayDeque4;
                                arrayDeque.push(o72);
                                if (o72.getGroupName() != null) {
                                    r7.p.put(o72.getGroupName(), o72);
                                }
                                s73.a = o72.k | s73.a;
                            }
                            arrayDeque = arrayDeque4;
                        }
                    } else {
                        i = depth;
                        R7 r75 = r74;
                        arrayDeque = arrayDeque3;
                        r7 = r75;
                        if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                            arrayDeque.pop();
                        }
                    }
                    eventType = xmlPullParser.next();
                    i5 = 2;
                    i4 = 1;
                    i3 = 3;
                    depth = i;
                    ArrayDeque arrayDeque5 = arrayDeque;
                    r74 = r7;
                    arrayDeque3 = arrayDeque5;
                }
                if (!z2) {
                    this.u = d(s7.c, s7.d);
                    return;
                }
                throw new XmlPullParserException("no path defined");
            }
            throw new XmlPullParserException(typedArrayG.getPositionDescription() + "<vector> tag requires height > 0");
        }
        throw new XmlPullParserException(typedArrayG.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
    }

    public U7(S7 s7) {
        this.x = true;
        this.y = new float[9];
        this.z = new Matrix();
        this.A = new Rect();
        this.t = s7;
        this.u = d(s7.c, s7.d);
    }
}
