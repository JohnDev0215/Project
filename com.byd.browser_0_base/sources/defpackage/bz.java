package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import defpackage.rb2;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ContextCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateConstructorForUtilityClass"})
public class bz {
    public static final Object a = new Object();

    public static int a(Context context, String str) {
        if (str != null) {
            return (yj.a() || !TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) ? context.checkPermission(str, Process.myPid(), Process.myUid()) : new ro1(context).a() ? 0 : -1;
        }
        throw new NullPointerException("permission must be non-null");
    }

    public static ColorStateList b(Context context, int i) {
        ColorStateList colorStateListA;
        ColorStateList colorStateList;
        rb2.a aVar;
        Resources resources = context.getResources();
        Resources.Theme theme = context.getTheme();
        rb2.b bVar = new rb2.b(resources, theme);
        synchronized (rb2.c) {
            SparseArray<rb2.a> sparseArray = rb2.b.get(bVar);
            colorStateListA = null;
            if (sparseArray == null || sparseArray.size() <= 0 || (aVar = sparseArray.get(i)) == null) {
                colorStateList = null;
            } else if (!aVar.b.equals(resources.getConfiguration()) || (!(theme == null && aVar.c == 0) && (theme == null || aVar.c != theme.hashCode()))) {
                sparseArray.remove(i);
                colorStateList = null;
            } else {
                colorStateList = aVar.a;
            }
        }
        if (colorStateList != null) {
            return colorStateList;
        }
        ThreadLocal<TypedValue> threadLocal = rb2.a;
        TypedValue typedValue = threadLocal.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            threadLocal.set(typedValue);
        }
        resources.getValue(i, typedValue, true);
        int i2 = typedValue.type;
        if (!(i2 >= 28 && i2 <= 31)) {
            try {
                colorStateListA = ev.a(resources, resources.getXml(i), theme);
            } catch (Exception e) {
                Log.w("ResourcesCompat", "Failed to inflate ColorStateList, leaving it to the framework", e);
            }
        }
        if (colorStateListA == null) {
            return resources.getColorStateList(i, theme);
        }
        synchronized (rb2.c) {
            WeakHashMap<rb2.b, SparseArray<rb2.a>> weakHashMap = rb2.b;
            SparseArray<rb2.a> sparseArray2 = weakHashMap.get(bVar);
            if (sparseArray2 == null) {
                sparseArray2 = new SparseArray<>();
                weakHashMap.put(bVar, sparseArray2);
            }
            sparseArray2.append(i, new rb2.a(colorStateListA, bVar.a.getConfiguration(), theme));
        }
        return colorStateListA;
    }

    public static Drawable c(Context context, int i) {
        return context.getDrawable(i);
    }

    public static Executor d(Context context) {
        return context.getMainExecutor();
    }
}
