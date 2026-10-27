package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: TypedArrayUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class fh3 {
    public static int a(Context context, int i, int i2) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId != 0 ? i : i2;
    }

    public static vv b(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i) {
        vv vvVarA;
        if (g(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i, typedValue);
            int i2 = typedValue.type;
            if (i2 >= 28 && i2 <= 31) {
                return new vv(null, null, typedValue.data);
            }
            try {
                vvVarA = vv.a(typedArray.getResources(), typedArray.getResourceId(i, 0), theme);
            } catch (Exception e) {
                Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e);
                vvVarA = null;
            }
            if (vvVarA != null) {
                return vvVarA;
            }
        }
        return new vv(null, null, 0);
    }

    public static float c(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, float f) {
        return !g(xmlPullParser, str) ? f : typedArray.getFloat(i, f);
    }

    public static int d(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        return !g(xmlPullParser, str) ? i2 : typedArray.getInt(i, i2);
    }

    public static String e(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i) {
        if (g(xmlPullParser, str)) {
            return typedArray.getString(i);
        }
        return null;
    }

    public static String f(TypedArray typedArray, int i, int i2) {
        String string = typedArray.getString(i);
        return string == null ? typedArray.getString(i2) : string;
    }

    public static boolean g(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    public static TypedArray h(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }
}
