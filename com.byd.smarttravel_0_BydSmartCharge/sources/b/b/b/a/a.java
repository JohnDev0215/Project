package b.b.b.a;

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
import b.b.f.v;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: AppCompatResources.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<TypedValue> f622a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final WeakHashMap<Context, SparseArray<C0002a>> f623b = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f624c = new Object();

    /* JADX INFO: renamed from: b.b.b.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppCompatResources.java */
    public static class C0002a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ColorStateList f625a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Configuration f626b;

        public C0002a(ColorStateList colorStateList, Configuration configuration) {
            this.f625a = colorStateList;
            this.f626b = configuration;
        }
    }

    public static void a(Context context, int i, ColorStateList colorStateList) {
        synchronized (f624c) {
            WeakHashMap<Context, SparseArray<C0002a>> weakHashMap = f623b;
            SparseArray<C0002a> sparseArray = weakHashMap.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                weakHashMap.put(context, sparseArray);
            }
            sparseArray.append(i, new C0002a(colorStateList, context.getResources().getConfiguration()));
        }
    }

    public static ColorStateList b(Context context, int i) {
        C0002a c0002a;
        synchronized (f624c) {
            SparseArray<C0002a> sparseArray = f623b.get(context);
            if (sparseArray != null && sparseArray.size() > 0 && (c0002a = sparseArray.get(i)) != null) {
                if (c0002a.f626b.equals(context.getResources().getConfiguration())) {
                    return c0002a.f625a;
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
            return b.g.b.a.c(context, i);
        }
        a(context, i, colorStateListF);
        return colorStateListF;
    }

    public static Drawable d(Context context, int i) {
        return v.h().j(context, i);
    }

    public static TypedValue e() {
        ThreadLocal<TypedValue> threadLocal = f622a;
        TypedValue typedValue = threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    public static ColorStateList f(Context context, int i) {
        if (g(context, i)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return b.g.b.c.a.a(resources, resources.getXml(i), context.getTheme());
        } catch (Exception e) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e);
            return null;
        }
    }

    public static boolean g(Context context, int i) {
        Resources resources = context.getResources();
        TypedValue typedValueE = e();
        resources.getValue(i, typedValueE, true);
        int i2 = typedValueE.type;
        return i2 >= 28 && i2 <= 31;
    }
}
