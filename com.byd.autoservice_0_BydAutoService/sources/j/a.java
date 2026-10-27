package j;

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
import java.util.WeakHashMap;
import o.v;

/* JADX INFO: compiled from: AppCompatResources.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<TypedValue> f3389a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final WeakHashMap<Context, SparseArray<C0023a>> f3390b = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f3391c = new Object();

    /* JADX INFO: renamed from: j.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppCompatResources.java */
    public static class C0023a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ColorStateList f3392a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Configuration f3393b;

        public C0023a(ColorStateList colorStateList, Configuration configuration) {
            this.f3392a = colorStateList;
            this.f3393b = configuration;
        }
    }

    public static void a(Context context, int i3, ColorStateList colorStateList) {
        synchronized (f3391c) {
            WeakHashMap<Context, SparseArray<C0023a>> weakHashMap = f3390b;
            SparseArray<C0023a> sparseArray = weakHashMap.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                weakHashMap.put(context, sparseArray);
            }
            sparseArray.append(i3, new C0023a(colorStateList, context.getResources().getConfiguration()));
        }
    }

    public static ColorStateList b(Context context, int i3) {
        C0023a c0023a;
        synchronized (f3391c) {
            SparseArray<C0023a> sparseArray = f3390b.get(context);
            if (sparseArray != null && sparseArray.size() > 0 && (c0023a = sparseArray.get(i3)) != null) {
                if (c0023a.f3393b.equals(context.getResources().getConfiguration())) {
                    return c0023a.f3392a;
                }
                sparseArray.remove(i3);
            }
            return null;
        }
    }

    public static ColorStateList c(Context context, int i3) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i3);
        }
        ColorStateList colorStateListB = b(context, i3);
        if (colorStateListB != null) {
            return colorStateListB;
        }
        ColorStateList colorStateListF = f(context, i3);
        if (colorStateListF == null) {
            return b0.a.c(context, i3);
        }
        a(context, i3, colorStateListF);
        return colorStateListF;
    }

    public static Drawable d(Context context, int i3) {
        return v.h().j(context, i3);
    }

    public static TypedValue e() {
        ThreadLocal<TypedValue> threadLocal = f3389a;
        TypedValue typedValue = threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    public static ColorStateList f(Context context, int i3) {
        if (g(context, i3)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return c0.a.a(resources, resources.getXml(i3), context.getTheme());
        } catch (Exception e3) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e3);
            return null;
        }
    }

    public static boolean g(Context context, int i3) {
        Resources resources = context.getResources();
        TypedValue typedValueE = e();
        resources.getValue(i3, typedValueE, true);
        int i4 = typedValueE.type;
        return i4 >= 28 && i4 <= 31;
    }
}
