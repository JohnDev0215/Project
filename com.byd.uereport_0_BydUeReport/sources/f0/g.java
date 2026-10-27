package f0;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: TypedArrayUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class g {
    public static boolean a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i8, boolean z7) {
        return !j(xmlPullParser, str) ? z7 : typedArray.getBoolean(i8, z7);
    }

    public static int b(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i8, int i9) {
        return !j(xmlPullParser, str) ? i9 : typedArray.getColor(i8, i9);
    }

    public static ColorStateList c(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i8) {
        if (!j(xmlPullParser, str)) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i8, typedValue);
        int i9 = typedValue.type;
        if (i9 != 2) {
            return (i9 < 28 || i9 > 31) ? a.c(typedArray.getResources(), typedArray.getResourceId(i8, 0), theme) : d(typedValue);
        }
        throw new UnsupportedOperationException("Failed to resolve attribute at index " + i8 + ": " + typedValue);
    }

    public static ColorStateList d(TypedValue typedValue) {
        return ColorStateList.valueOf(typedValue.data);
    }

    public static b e(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i8, int i9) {
        if (j(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i8, typedValue);
            int i10 = typedValue.type;
            if (i10 >= 28 && i10 <= 31) {
                return b.b(typedValue.data);
            }
            b bVarG = b.g(typedArray.getResources(), typedArray.getResourceId(i8, 0), theme);
            if (bVarG != null) {
                return bVarG;
            }
        }
        return b.b(i9);
    }

    public static float f(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i8, float f8) {
        return !j(xmlPullParser, str) ? f8 : typedArray.getFloat(i8, f8);
    }

    public static int g(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i8, int i9) {
        return !j(xmlPullParser, str) ? i9 : typedArray.getInt(i8, i9);
    }

    public static int h(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i8, int i9) {
        return !j(xmlPullParser, str) ? i9 : typedArray.getResourceId(i8, i9);
    }

    public static String i(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i8) {
        if (j(xmlPullParser, str)) {
            return typedArray.getString(i8);
        }
        return null;
    }

    public static boolean j(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    public static TypedArray k(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public static TypedValue l(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i8) {
        if (j(xmlPullParser, str)) {
            return typedArray.peekValue(i8);
        }
        return null;
    }
}
