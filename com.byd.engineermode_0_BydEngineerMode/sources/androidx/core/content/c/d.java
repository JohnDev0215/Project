package androidx.core.content.c;

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
final class d {
    private static a a(a aVar, int i, int i2, boolean z, int i3) {
        return aVar != null ? aVar : z ? new a(i, i3, i2) : new a(i, i2);
    }

    static Shader b(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (!name.equals("gradient")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid gradient color tag " + name);
        }
        TypedArray typedArrayK = g.k(resources, theme, attributeSet, b.g.c.w);
        float f = g.f(typedArrayK, xmlPullParser, "startX", b.g.c.F, 0.0f);
        float f2 = g.f(typedArrayK, xmlPullParser, "startY", b.g.c.G, 0.0f);
        float f3 = g.f(typedArrayK, xmlPullParser, "endX", b.g.c.H, 0.0f);
        float f4 = g.f(typedArrayK, xmlPullParser, "endY", b.g.c.I, 0.0f);
        float f5 = g.f(typedArrayK, xmlPullParser, "centerX", b.g.c.A, 0.0f);
        float f6 = g.f(typedArrayK, xmlPullParser, "centerY", b.g.c.B, 0.0f);
        int iG = g.g(typedArrayK, xmlPullParser, "type", b.g.c.z, 0);
        int iB = g.b(typedArrayK, xmlPullParser, "startColor", b.g.c.x, 0);
        boolean zJ = g.j(xmlPullParser, "centerColor");
        int iB2 = g.b(typedArrayK, xmlPullParser, "centerColor", b.g.c.E, 0);
        int iB3 = g.b(typedArrayK, xmlPullParser, "endColor", b.g.c.y, 0);
        int iG2 = g.g(typedArrayK, xmlPullParser, "tileMode", b.g.c.D, 0);
        float f7 = g.f(typedArrayK, xmlPullParser, "gradientRadius", b.g.c.C, 0.0f);
        typedArrayK.recycle();
        a aVarA = a(c(resources, xmlPullParser, attributeSet, theme), iB, iB3, zJ, iB2);
        if (iG != 1) {
            return iG != 2 ? new LinearGradient(f, f2, f3, f4, aVarA.f862a, aVarA.f863b, d(iG2)) : new SweepGradient(f5, f6, aVarA.f862a, aVarA.f863b);
        }
        if (f7 > 0.0f) {
            return new RadialGradient(f5, f6, f7, aVarA.f862a, aVarA.f863b, d(iG2));
        }
        throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0085, code lost:
    
        if (r4.size() <= 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008c, code lost:
    
        return new androidx.core.content.c.d.a(r4, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008d, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static androidx.core.content.c.d.a c(android.content.res.Resources r9, org.xmlpull.v1.XmlPullParser r10, android.util.AttributeSet r11, android.content.res.Resources.Theme r12) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
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
            int[] r3 = b.g.c.J
            android.content.res.TypedArray r3 = androidx.core.content.c.g.k(r9, r12, r11, r3)
            int r5 = b.g.c.K
            boolean r6 = r3.hasValue(r5)
            int r7 = b.g.c.L
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
            androidx.core.content.c.d$a r9 = new androidx.core.content.c.d$a
            r9.<init>(r4, r2)
            return r9
        L8d:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.c.d.c(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):androidx.core.content.c.d$a");
    }

    private static Shader.TileMode d(int i) {
        return i != 1 ? i != 2 ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR : Shader.TileMode.REPEAT;
    }

    /* JADX INFO: compiled from: GradientColorInflaterCompat.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int[] f862a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final float[] f863b;

        a(List<Integer> list, List<Float> list2) {
            int size = list.size();
            this.f862a = new int[size];
            this.f863b = new float[size];
            for (int i = 0; i < size; i++) {
                this.f862a[i] = list.get(i).intValue();
                this.f863b[i] = list2.get(i).floatValue();
            }
        }

        a(int i, int i2) {
            this.f862a = new int[]{i, i2};
            this.f863b = new float[]{0.0f, 1.0f};
        }

        a(int i, int i2, int i3) {
            this.f862a = new int[]{i, i2, i3};
            this.f863b = new float[]{0.0f, 0.5f, 1.0f};
        }
    }
}
