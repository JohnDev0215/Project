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
import androidx.appcompat.widget.v;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: AppCompatResources.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<TypedValue> f1515a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final WeakHashMap<Context, SparseArray<C0028a>> f1516b = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f1517c = new Object();

    /* JADX INFO: renamed from: b.a.k.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppCompatResources.java */
    private static class C0028a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ColorStateList f1518a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Configuration f1519b;

        C0028a(ColorStateList colorStateList, Configuration configuration) {
            this.f1518a = colorStateList;
            this.f1519b = configuration;
        }
    }

    private static void a(Context context, int i, ColorStateList colorStateList) {
        synchronized (f1517c) {
            WeakHashMap<Context, SparseArray<C0028a>> weakHashMap = f1516b;
            SparseArray<C0028a> sparseArray = weakHashMap.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                weakHashMap.put(context, sparseArray);
            }
            sparseArray.append(i, new C0028a(colorStateList, context.getResources().getConfiguration()));
        }
    }

    private static ColorStateList b(Context context, int i) {
        C0028a c0028a;
        synchronized (f1517c) {
            SparseArray<C0028a> sparseArray = f1516b.get(context);
            if (sparseArray != null && sparseArray.size() > 0 && (c0028a = sparseArray.get(i)) != null) {
                if (c0028a.f1519b.equals(context.getResources().getConfiguration())) {
                    return c0028a.f1518a;
                }
                sparseArray.remove(i);
            }
            return null;
        }
    }

    public static ColorStateList c(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i);
        }
        ColorStateList colorStateListB = b(context, i);
        if (colorStateListB != null) {
            return colorStateListB;
        }
        ColorStateList colorStateListF = f(context, i);
        if (colorStateListF == null) {
            return androidx.core.content.a.c(context, i);
        }
        a(context, i, colorStateListF);
        return colorStateListF;
    }

    public static Drawable d(Context context, int i) {
        return v.h().j(context, i);
    }

    private static TypedValue e() {
        ThreadLocal<TypedValue> threadLocal = f1515a;
        TypedValue typedValue = threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    private static ColorStateList f(Context context, int i) {
        if (g(context, i)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return androidx.core.content.c.a.a(resources, resources.getXml(i), context.getTheme());
        } catch (Exception e) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e);
            return null;
        }
    }

    private static boolean g(Context context, int i) {
        Resources resources = context.getResources();
        TypedValue typedValueE = e();
        resources.getValue(i, typedValueE, true);
        int i2 = typedValueE.type;
        return i2 >= 28 && i2 <= 31;
    }
}
