package b.a.k.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.appcompat.widget.w;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: AppCompatResources.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public final class a {

    /* JADX INFO: renamed from: a */
    private static final ThreadLocal<TypedValue> f2143a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b */
    private static final WeakHashMap<Context, SparseArray<C0031a>> f2144b = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: c */
    private static final Object f2145c = new Object();

    /* JADX INFO: renamed from: b.a.k.a.a$a */
    /* JADX INFO: compiled from: AppCompatResources.java */
    private static class C0031a {

        /* JADX INFO: renamed from: a */
        final ColorStateList f2146a;

        /* JADX INFO: renamed from: b */
        final Configuration f2147b;

        C0031a(ColorStateList colorStateList, Configuration configuration) {
            this.f2146a = colorStateList;
            this.f2147b = configuration;
        }
    }

    private static void a(Context context, int i2, ColorStateList colorStateList) {
        synchronized (f2145c) {
            WeakHashMap<Context, SparseArray<C0031a>> weakHashMap = f2144b;
            SparseArray<C0031a> sparseArray = weakHashMap.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                weakHashMap.put(context, sparseArray);
            }
            sparseArray.append(i2, new C0031a(colorStateList, context.getResources().getConfiguration()));
        }
    }

    private static ColorStateList b(Context context, int i2) {
        C0031a c0031a;
        synchronized (f2145c) {
            SparseArray<C0031a> sparseArray = f2144b.get(context);
            if (sparseArray != null && sparseArray.size() > 0 && (c0031a = sparseArray.get(i2)) != null) {
                if (c0031a.f2147b.equals(context.getResources().getConfiguration())) {
                    return c0031a.f2146a;
                }
                sparseArray.remove(i2);
            }
            return null;
        }
    }

    public static ColorStateList c(Context context, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i2);
        }
        ColorStateList colorStateListB = b(context, i2);
        if (colorStateListB != null) {
            return colorStateListB;
        }
        ColorStateList colorStateListF = f(context, i2);
        if (colorStateListF == null) {
            return androidx.core.content.a.c(context, i2);
        }
        a(context, i2, colorStateListF);
        return colorStateListF;
    }

    public static Drawable d(Context context, int i2) {
        return w.h().j(context, i2);
    }

    private static TypedValue e() {
        ThreadLocal<TypedValue> threadLocal = f2143a;
        TypedValue typedValue = threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    private static ColorStateList f(Context context, int i2) {
        if (g(context, i2)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return androidx.core.content.c.a.a(resources, resources.getXml(i2), context.getTheme());
        } catch (Exception e2) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e2);
            return null;
        }
    }

    private static boolean g(Context context, int i2) {
        Resources resources = context.getResources();
        TypedValue typedValueE = e();
        resources.getValue(i2, typedValueE, true);
        int i3 = typedValueE.type;
        return i3 >= 28 && i3 <= 31;
    }
}
