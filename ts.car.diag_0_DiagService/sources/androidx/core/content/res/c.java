package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.R$styleable;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
final class c {
    private static a a(@Nullable a aVar, @ColorInt int i, @ColorInt int i2, boolean z, @ColorInt int i3) {
        return aVar != null ? aVar : z ? new a(i, i3, i2) : new a(i, i2);
    }

    static Shader b(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException {
        String name = xmlPullParser.getName();
        if (!name.equals("gradient")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid gradient color tag " + name);
        }
        TypedArray typedArrayS = f.s(resources, theme, attributeSet, R$styleable.GradientColor);
        float fJ = f.j(typedArrayS, xmlPullParser, "startX", R$styleable.GradientColor_android_startX, 0.0f);
        float fJ2 = f.j(typedArrayS, xmlPullParser, "startY", R$styleable.GradientColor_android_startY, 0.0f);
        float fJ3 = f.j(typedArrayS, xmlPullParser, "endX", R$styleable.GradientColor_android_endX, 0.0f);
        float fJ4 = f.j(typedArrayS, xmlPullParser, "endY", R$styleable.GradientColor_android_endY, 0.0f);
        float fJ5 = f.j(typedArrayS, xmlPullParser, "centerX", R$styleable.GradientColor_android_centerX, 0.0f);
        float fJ6 = f.j(typedArrayS, xmlPullParser, "centerY", R$styleable.GradientColor_android_centerY, 0.0f);
        int iK = f.k(typedArrayS, xmlPullParser, "type", R$styleable.GradientColor_android_type, 0);
        int iF = f.f(typedArrayS, xmlPullParser, "startColor", R$styleable.GradientColor_android_startColor, 0);
        boolean zR = f.r(xmlPullParser, "centerColor");
        int iF2 = f.f(typedArrayS, xmlPullParser, "centerColor", R$styleable.GradientColor_android_centerColor, 0);
        int iF3 = f.f(typedArrayS, xmlPullParser, "endColor", R$styleable.GradientColor_android_endColor, 0);
        int iK2 = f.k(typedArrayS, xmlPullParser, "tileMode", R$styleable.GradientColor_android_tileMode, 0);
        float fJ7 = f.j(typedArrayS, xmlPullParser, "gradientRadius", R$styleable.GradientColor_android_gradientRadius, 0.0f);
        typedArrayS.recycle();
        a aVarA = a(c(resources, xmlPullParser, attributeSet, theme), iF, iF3, zR, iF2);
        if (iK != 1) {
            return iK != 2 ? new LinearGradient(fJ, fJ2, fJ3, fJ4, aVarA.f463a, aVarA.f464b, d(iK2)) : new SweepGradient(fJ5, fJ6, aVarA.f463a, aVarA.f464b);
        }
        if (fJ7 > 0.0f) {
            return new RadialGradient(fJ5, fJ6, fJ7, aVarA.f463a, aVarA.f464b, d(iK2));
        }
        throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0089, code lost:
    
        if (r4.size() <= 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0090, code lost:
    
        return new androidx.core.content.res.c.a(r4, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0091, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static androidx.core.content.res.c.a c(@androidx.annotation.NonNull android.content.res.Resources r8, @androidx.annotation.NonNull org.xmlpull.v1.XmlPullParser r9, @androidx.annotation.NonNull android.util.AttributeSet r10, @androidx.annotation.Nullable android.content.res.Resources.Theme r11) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            int r0 = r9.getDepth()
            r1 = 1
            int r0 = r0 + r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 20
            r2.<init>(r3)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r3)
        L12:
            int r3 = r9.next()
            if (r3 == r1) goto L85
            int r5 = r9.getDepth()
            if (r5 >= r0) goto L21
            r6 = 3
            if (r3 == r6) goto L85
        L21:
            r6 = 2
            if (r3 == r6) goto L25
            goto L12
        L25:
            if (r5 > r0) goto L12
            java.lang.String r3 = r9.getName()
            java.lang.String r5 = "item"
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L34
            goto L12
        L34:
            int[] r3 = androidx.core.R$styleable.GradientColorItem
            android.content.res.TypedArray r3 = androidx.core.content.res.f.s(r8, r11, r10, r3)
            int r5 = androidx.core.R$styleable.GradientColorItem_android_color
            boolean r5 = r3.hasValue(r5)
            int r6 = androidx.core.R$styleable.GradientColorItem_android_offset
            boolean r6 = r3.hasValue(r6)
            if (r5 == 0) goto L6a
            if (r6 == 0) goto L6a
            int r5 = androidx.core.R$styleable.GradientColorItem_android_color
            r6 = 0
            int r5 = r3.getColor(r5, r6)
            int r6 = androidx.core.R$styleable.GradientColorItem_android_offset
            r7 = 0
            float r6 = r3.getFloat(r6, r7)
            r3.recycle()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            r4.add(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r6)
            r2.add(r3)
            goto L12
        L6a:
            org.xmlpull.v1.XmlPullParserException r8 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r9 = r9.getPositionDescription()
            r10.append(r9)
            java.lang.String r9 = ": <item> tag requires a 'color' attribute and a 'offset' attribute!"
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r8.<init>(r9)
            throw r8
        L85:
            int r8 = r4.size()
            if (r8 <= 0) goto L91
            androidx.core.content.res.c$a r8 = new androidx.core.content.res.c$a
            r8.<init>(r4, r2)
            return r8
        L91:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.c.c(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):androidx.core.content.res.c$a");
    }

    private static Shader.TileMode d(int i) {
        return i != 1 ? i != 2 ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR : Shader.TileMode.REPEAT;
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int[] f463a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final float[] f464b;

        a(@NonNull List<Integer> list, @NonNull List<Float> list2) {
            int size = list.size();
            this.f463a = new int[size];
            this.f464b = new float[size];
            for (int i = 0; i < size; i++) {
                this.f463a[i] = list.get(i).intValue();
                this.f464b[i] = list2.get(i).floatValue();
            }
        }

        a(@ColorInt int i, @ColorInt int i2) {
            this.f463a = new int[]{i, i2};
            this.f464b = new float[]{0.0f, 1.0f};
        }

        a(@ColorInt int i, @ColorInt int i2, @ColorInt int i3) {
            this.f463a = new int[]{i, i2, i3};
            this.f464b = new float[]{0.0f, 0.5f, 1.0f};
        }
    }
}
