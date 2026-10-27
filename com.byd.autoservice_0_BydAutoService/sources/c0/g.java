package c0;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: TypedArrayUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class g {
    public static boolean a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i3, boolean z3) {
        return !j(xmlPullParser, str) ? z3 : typedArray.getBoolean(i3, z3);
    }

    public static int b(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i3, int i4) {
        return !j(xmlPullParser, str) ? i4 : typedArray.getColor(i3, i4);
    }

    public static ColorStateList c(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i3) {
        if (!j(xmlPullParser, str)) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i3, typedValue);
        int i4 = typedValue.type;
        if (i4 != 2) {
            return (i4 < 28 || i4 > 31) ? a.c(typedArray.getResources(), typedArray.getResourceId(i3, 0), theme) : d(typedValue);
        }
        throw new UnsupportedOperationException("Failed to resolve attribute at index " + i3 + ": " + typedValue);
    }

    public static ColorStateList d(TypedValue typedValue) {
        return ColorStateList.valueOf(typedValue.data);
    }

    public static b e(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i3, int i4) {
        if (j(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i3, typedValue);
            int i5 = typedValue.type;
            if (i5 >= 28 && i5 <= 31) {
                return b.b(typedValue.data);
            }
            b bVarG = b.g(typedArray.getResources(), typedArray.getResourceId(i3, 0), theme);
            if (bVarG != null) {
                return bVarG;
            }
        }
        return b.b(i4);
    }

    public static float f(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i3, float f3) {
        return !j(xmlPullParser, str) ? f3 : typedArray.getFloat(i3, f3);
    }

    public static int g(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i3, int i4) {
        return !j(xmlPullParser, str) ? i4 : typedArray.getInt(i3, i4);
    }

    public static int h(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i3, int i4) {
        return !j(xmlPullParser, str) ? i4 : typedArray.getResourceId(i3, i4);
    }

    public static String i(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i3) {
        if (j(xmlPullParser, str)) {
            return typedArray.getString(i3);
        }
        return null;
    }

    public static boolean j(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    public static TypedArray k(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public static TypedValue l(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i3) {
        if (j(xmlPullParser, str)) {
            return typedArray.peekValue(i3);
        }
        return null;
    }
}
