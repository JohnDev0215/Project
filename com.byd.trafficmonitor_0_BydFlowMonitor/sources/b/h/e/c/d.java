package b.h.e.c;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: GradientColorInflaterCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static a a(a aVar, int i2, int i3, boolean z, int i4) {
        return aVar != null ? aVar : z ? new a(i2, i4, i3) : new a(i2, i3);
    }

    public static Shader b(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (!name.equals("gradient")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid gradient color tag " + name);
        }
        TypedArray typedArrayK = g.k(resources, theme, attributeSet, b.h.c.GradientColor);
        float f2 = g.f(typedArrayK, xmlPullParser, "startX", b.h.c.GradientColor_android_startX, 0.0f);
        float f3 = g.f(typedArrayK, xmlPullParser, "startY", b.h.c.GradientColor_android_startY, 0.0f);
        float f4 = g.f(typedArrayK, xmlPullParser, "endX", b.h.c.GradientColor_android_endX, 0.0f);
        float f5 = g.f(typedArrayK, xmlPullParser, "endY", b.h.c.GradientColor_android_endY, 0.0f);
        float f6 = g.f(typedArrayK, xmlPullParser, "centerX", b.h.c.GradientColor_android_centerX, 0.0f);
        float f7 = g.f(typedArrayK, xmlPullParser, "centerY", b.h.c.GradientColor_android_centerY, 0.0f);
        int iG = g.g(typedArrayK, xmlPullParser, "type", b.h.c.GradientColor_android_type, 0);
        int iB = g.b(typedArrayK, xmlPullParser, "startColor", b.h.c.GradientColor_android_startColor, 0);
        boolean zJ = g.j(xmlPullParser, "centerColor");
        int iB2 = g.b(typedArrayK, xmlPullParser, "centerColor", b.h.c.GradientColor_android_centerColor, 0);
        int iB3 = g.b(typedArrayK, xmlPullParser, "endColor", b.h.c.GradientColor_android_endColor, 0);
        int iG2 = g.g(typedArrayK, xmlPullParser, "tileMode", b.h.c.GradientColor_android_tileMode, 0);
        float f8 = g.f(typedArrayK, xmlPullParser, "gradientRadius", b.h.c.GradientColor_android_gradientRadius, 0.0f);
        typedArrayK.recycle();
        a aVarA = a(c(resources, xmlPullParser, attributeSet, theme), iB, iB3, zJ, iB2);
        if (iG != 1) {
            return iG != 2 ? new LinearGradient(f2, f3, f4, f5, aVarA.f2421a, aVarA.f2422b, d(iG2)) : new SweepGradient(f6, f7, aVarA.f2421a, aVarA.f2422b);
        }
        if (f8 > 0.0f) {
            return new RadialGradient(f6, f7, f8, aVarA.f2421a, aVarA.f2422b, d(iG2));
        }
        throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0085, code lost:
    
        if (r4.size() <= 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008c, code lost:
    
        return new b.h.e.c.d.a(r4, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008d, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static b.h.e.c.d.a c(android.content.res.Resources r9, org.xmlpull.v1.XmlPullParser r10, android.util.AttributeSet r11, android.content.res.Resources.Theme r12) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            int r0 = r10.getDepth()
            r1 = 1
            int r0 = r0 + r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 20
            r2.<init>(r3)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r3)
        L12:
            int r3 = r10.next()
            if (r3 == r1) goto L81
            int r5 = r10.getDepth()
            if (r5 >= r0) goto L21
            r6 = 3
            if (r3 == r6) goto L81
        L21:
            r6 = 2
            if (r3 == r6) goto L25
            goto L12
        L25:
            if (r5 > r0) goto L12
            java.lang.String r3 = r10.getName()
            java.lang.String r5 = "item"
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L34
            goto L12
        L34:
            int[] r3 = b.h.c.GradientColorItem
            android.content.res.TypedArray r3 = b.h.e.c.g.k(r9, r12, r11, r3)
            int r5 = b.h.c.GradientColorItem_android_color
            boolean r6 = r3.hasValue(r5)
            int r7 = b.h.c.GradientColorItem_android_offset
            boolean r8 = r3.hasValue(r7)
            if (r6 == 0) goto L66
            if (r8 == 0) goto L66
            r6 = 0
            int r5 = r3.getColor(r5, r6)
            r6 = 0
            float r6 = r3.getFloat(r7, r6)
            r3.recycle()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            r4.add(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r6)
            r2.add(r3)
            goto L12
        L66:
            org.xmlpull.v1.XmlPullParserException r9 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r10 = r10.getPositionDescription()
            r11.append(r10)
            java.lang.String r10 = ": <item> tag requires a 'color' attribute and a 'offset' attribute!"
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            r9.<init>(r10)
            throw r9
        L81:
            int r9 = r4.size()
            if (r9 <= 0) goto L8d
            b.h.e.c.d$a r9 = new b.h.e.c.d$a
            r9.<init>(r4, r2)
            return r9
        L8d:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: b.h.e.c.d.c(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):b.h.e.c.d$a");
    }

    public static Shader.TileMode d(int i2) {
        return i2 != 1 ? i2 != 2 ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR : Shader.TileMode.REPEAT;
    }

    /* JADX INFO: compiled from: GradientColorInflaterCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f2421a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final float[] f2422b;

        public a(List<Integer> list, List<Float> list2) {
            int size = list.size();
            this.f2421a = new int[size];
            this.f2422b = new float[size];
            for (int i2 = 0; i2 < size; i2++) {
                this.f2421a[i2] = list.get(i2).intValue();
                this.f2422b[i2] = list2.get(i2).floatValue();
            }
        }

        public a(int i2, int i3) {
            this.f2421a = new int[]{i2, i3};
            this.f2422b = new float[]{0.0f, 1.0f};
        }

        public a(int i2, int i3, int i4) {
            this.f2421a = new int[]{i2, i3, i4};
            this.f2422b = new float[]{0.0f, 0.5f, 1.0f};
        }
    }
}
