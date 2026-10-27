package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.Xml;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: renamed from: v, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1631v {
    public static final ThreadLocal a = new ThreadLocal();
    public static final WeakHashMap b = new WeakHashMap(0);
    public static final Object c = new Object();

    public static ColorStateList a(Context context, int i) {
        ColorStateList colorStateListA;
        ColorStateList colorStateList;
        int next;
        C1576u c1576u;
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i);
        }
        synchronized (c) {
            SparseArray sparseArray = (SparseArray) b.get(context);
            colorStateListA = null;
            if (sparseArray == null || sparseArray.size() <= 0 || (c1576u = (C1576u) sparseArray.get(i)) == null) {
                colorStateList = null;
            } else if (c1576u.b.equals(context.getResources().getConfiguration())) {
                colorStateList = c1576u.a;
            } else {
                sparseArray.remove(i);
                colorStateList = null;
            }
        }
        if (colorStateList != null) {
            return colorStateList;
        }
        Resources resources = context.getResources();
        TypedValue typedValue = (TypedValue) a.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            a.set(typedValue);
        }
        resources.getValue(i, typedValue, true);
        int i2 = typedValue.type;
        if (!(i2 >= 28 && i2 <= 31)) {
            Resources resources2 = context.getResources();
            XmlResourceParser xml = resources2.getXml(i);
            try {
                Resources.Theme theme = context.getTheme();
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                colorStateListA = AbstractC0582c2.a(resources2, xml, attributeSetAsAttributeSet, theme);
            } catch (Exception e) {
                Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e);
            }
        }
        if (colorStateListA == null) {
            return Y1.a(context, i);
        }
        synchronized (c) {
            SparseArray sparseArray2 = (SparseArray) b.get(context);
            if (sparseArray2 == null) {
                sparseArray2 = new SparseArray();
                b.put(context, sparseArray2);
            }
            sparseArray2.append(i, new C1576u(colorStateListA, context.getResources().getConfiguration()));
        }
        return colorStateListA;
    }

    public static Drawable b(Context context, int i) {
        return C0636d0.g().i(context, i);
    }
}
