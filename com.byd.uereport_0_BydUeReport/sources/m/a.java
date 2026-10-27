package m;

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
import r.v;

/* JADX INFO: compiled from: AppCompatResources.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<TypedValue> f6290a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final WeakHashMap<Context, SparseArray<C0085a>> f6291b = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f6292c = new Object();

    /* JADX INFO: renamed from: m.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppCompatResources.java */
    public static class C0085a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ColorStateList f6293a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Configuration f6294b;

        public C0085a(ColorStateList colorStateList, Configuration configuration) {
            this.f6293a = colorStateList;
            this.f6294b = configuration;
        }
    }

    public static void a(Context context, int i8, ColorStateList colorStateList) {
        synchronized (f6292c) {
            WeakHashMap<Context, SparseArray<C0085a>> weakHashMap = f6291b;
            SparseArray<C0085a> sparseArray = weakHashMap.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                weakHashMap.put(context, sparseArray);
            }
            sparseArray.append(i8, new C0085a(colorStateList, context.getResources().getConfiguration()));
        }
    }

    public static ColorStateList b(Context context, int i8) {
        C0085a c0085a;
        synchronized (f6292c) {
            SparseArray<C0085a> sparseArray = f6291b.get(context);
            if (sparseArray != null && sparseArray.size() > 0 && (c0085a = sparseArray.get(i8)) != null) {
                if (c0085a.f6294b.equals(context.getResources().getConfiguration())) {
                    return c0085a.f6293a;
                }
                sparseArray.remove(i8);
            }
            return null;
        }
    }

    public static ColorStateList c(Context context, int i8) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i8);
        }
        ColorStateList colorStateListB = b(context, i8);
        if (colorStateListB != null) {
            return colorStateListB;
        }
        ColorStateList colorStateListF = f(context, i8);
        if (colorStateListF == null) {
            return e0.a.c(context, i8);
        }
        a(context, i8, colorStateListF);
        return colorStateListF;
    }

    public static Drawable d(Context context, int i8) {
        return v.h().j(context, i8);
    }

    public static TypedValue e() {
        ThreadLocal<TypedValue> threadLocal = f6290a;
        TypedValue typedValue = threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    public static ColorStateList f(Context context, int i8) {
        if (g(context, i8)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return f0.a.a(resources, resources.getXml(i8), context.getTheme());
        } catch (Exception e8) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e8);
            return null;
        }
    }

    public static boolean g(Context context, int i8) {
        Resources resources = context.getResources();
        TypedValue typedValueE = e();
        resources.getValue(i8, typedValueE, true);
        int i9 = typedValueE.type;
        return i9 >= 28 && i9 <= 31;
    }
}
