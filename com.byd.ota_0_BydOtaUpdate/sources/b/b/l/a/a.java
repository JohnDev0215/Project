package b.b.l.a;

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
import b.b.q.v;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: AppCompatResources.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<TypedValue> f829a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final WeakHashMap<Context, SparseArray<C0006a>> f830b = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f831c = new Object();

    /* JADX INFO: renamed from: b.b.l.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppCompatResources.java */
    public static class C0006a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ColorStateList f832a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Configuration f833b;

        public C0006a(ColorStateList colorStateList, Configuration configuration) {
            this.f832a = colorStateList;
            this.f833b = configuration;
        }
    }

    public static void a(Context context, int i2, ColorStateList colorStateList) {
        synchronized (f831c) {
            WeakHashMap<Context, SparseArray<C0006a>> weakHashMap = f830b;
            SparseArray<C0006a> sparseArray = weakHashMap.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                weakHashMap.put(context, sparseArray);
            }
            sparseArray.append(i2, new C0006a(colorStateList, context.getResources().getConfiguration()));
        }
    }

    public static ColorStateList b(Context context, int i2) {
        C0006a c0006a;
        synchronized (f831c) {
            SparseArray<C0006a> sparseArray = f830b.get(context);
            if (sparseArray != null && sparseArray.size() > 0 && (c0006a = sparseArray.get(i2)) != null) {
                if (c0006a.f833b.equals(context.getResources().getConfiguration())) {
                    return c0006a.f832a;
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
            return b.f.f.a.b(context, i2);
        }
        a(context, i2, colorStateListF);
        return colorStateListF;
    }

    public static Drawable d(Context context, int i2) {
        return v.h().j(context, i2);
    }

    public static TypedValue e() {
        ThreadLocal<TypedValue> threadLocal = f829a;
        TypedValue typedValue = threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    public static ColorStateList f(Context context, int i2) {
        if (g(context, i2)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return b.f.f.c.a.a(resources, resources.getXml(i2), context.getTheme());
        } catch (Exception e2) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e2);
            return null;
        }
    }

    public static boolean g(Context context, int i2) {
        Resources resources = context.getResources();
        TypedValue typedValueE = e();
        resources.getValue(i2, typedValueE, true);
        int i3 = typedValueE.type;
        return i3 >= 28 && i3 <= 31;
    }
}
