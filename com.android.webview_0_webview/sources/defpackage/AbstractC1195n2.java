package defpackage;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: n2, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1195n2 {
    public static int a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        return !f(xmlPullParser, str) ? i2 : typedArray.getColor(i, i2);
    }

    public static C0638d2 b(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i, int i2) {
        C0638d2 c0638d2A;
        if (f(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i, typedValue);
            int i3 = typedValue.type;
            if (i3 >= 28 && i3 <= 31) {
                return new C0638d2(null, null, typedValue.data);
            }
            try {
                c0638d2A = C0638d2.a(typedArray.getResources(), typedArray.getResourceId(i, 0), theme);
            } catch (Exception e) {
                Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e);
                c0638d2A = null;
            }
            if (c0638d2A != null) {
                return c0638d2A;
            }
        }
        return new C0638d2(null, null, i2);
    }

    public static float c(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, float f) {
        return !f(xmlPullParser, str) ? f : typedArray.getFloat(i, f);
    }

    public static int d(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        return !f(xmlPullParser, str) ? i2 : typedArray.getInt(i, i2);
    }

    public static String e(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i) {
        if (f(xmlPullParser, str)) {
            return typedArray.getString(i);
        }
        return null;
    }

    public static boolean f(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    public static TypedArray g(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }
}
