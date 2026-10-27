package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<TypedValue> f465a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final WeakHashMap<Resources, SparseArray<a>> f466b = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f467c = new Object();

    /* JADX INFO: compiled from: caradapter-bug */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ColorStateList f468a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Configuration f469b;

        a(@NonNull ColorStateList colorStateList, @NonNull Configuration configuration) {
            this.f468a = colorStateList;
            this.f469b = configuration;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class b {

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ Typeface f470c;

            a(Typeface typeface) {
                this.f470c = typeface;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.d(this.f470c);
            }
        }

        /* JADX INFO: renamed from: androidx.core.content.res.e$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: caradapter-bug */
        class RunnableC0012b implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ int f471c;

            RunnableC0012b(int i) {
                this.f471c = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.c(this.f471c);
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public final void a(int i, @Nullable Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new RunnableC0012b(i));
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public final void b(Typeface typeface, @Nullable Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new a(typeface));
        }

        public abstract void c(int i);

        public abstract void d(@NonNull Typeface typeface);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class c {

        /* JADX INFO: compiled from: caradapter-bug */
        @RequiresApi(23)
        static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private static final Object f472a = new Object();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private static Method f473b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private static boolean f474c;

            /* JADX WARN: Removed duplicated region for block: B:19:0x003a A[Catch: all -> 0x003c, DONT_GENERATE, TryCatch #2 {, blocks: (B:4:0x0003, B:8:0x0009, B:12:0x0021, B:11:0x001a, B:13:0x0023, B:15:0x0027, B:19:0x003a, B:18:0x0030), top: B:28:0x0003, inners: #0, #1 }] */
            /* JADX WARN: Removed duplicated region for block: B:26:0x0027 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            static void a(@androidx.annotation.NonNull android.content.res.Resources.Theme r6) {
                /*
                    java.lang.Object r0 = androidx.core.content.res.e.c.a.f472a
                    monitor-enter(r0)
                    boolean r1 = androidx.core.content.res.e.c.a.f474c     // Catch: java.lang.Throwable -> L3c
                    r2 = 0
                    if (r1 != 0) goto L23
                    r1 = 1
                    java.lang.Class<android.content.res.Resources$Theme> r3 = android.content.res.Resources.Theme.class
                    java.lang.String r4 = "rebase"
                    java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    androidx.core.content.res.e.c.a.f473b = r3     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    r3.setAccessible(r1)     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    goto L21
                L19:
                    r3 = move-exception
                    java.lang.String r4 = "ResourcesCompat"
                    java.lang.String r5 = "Failed to retrieve rebase() method"
                    android.util.Log.i(r4, r5, r3)     // Catch: java.lang.Throwable -> L3c
                L21:
                    androidx.core.content.res.e.c.a.f474c = r1     // Catch: java.lang.Throwable -> L3c
                L23:
                    java.lang.reflect.Method r1 = androidx.core.content.res.e.c.a.f473b     // Catch: java.lang.Throwable -> L3c
                    if (r1 == 0) goto L3a
                    java.lang.reflect.Method r1 = androidx.core.content.res.e.c.a.f473b     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L3c
                    java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L3c
                    r1.invoke(r6, r2)     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L3c
                    goto L3a
                L2f:
                    r6 = move-exception
                    java.lang.String r1 = "ResourcesCompat"
                    java.lang.String r2 = "Failed to invoke rebase() method via reflection"
                    android.util.Log.i(r1, r2, r6)     // Catch: java.lang.Throwable -> L3c
                    r6 = 0
                    androidx.core.content.res.e.c.a.f473b = r6     // Catch: java.lang.Throwable -> L3c
                L3a:
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L3c
                    return
                L3c:
                    r6 = move-exception
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L3c
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.e.c.a.a(android.content.res.Resources$Theme):void");
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        @RequiresApi(29)
        static class b {
            static void a(@NonNull Resources.Theme theme) {
                theme.rebase();
            }
        }

        public static void a(@NonNull Resources.Theme theme) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                b.a(theme);
            } else if (i >= 23) {
                a.a(theme);
            }
        }
    }

    private static void a(@NonNull Resources resources, @ColorRes int i, @NonNull ColorStateList colorStateList) {
        synchronized (f467c) {
            SparseArray<a> sparseArray = f466b.get(resources);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                f466b.put(resources, sparseArray);
            }
            sparseArray.append(i, new a(colorStateList, resources.getConfiguration()));
        }
    }

    @Nullable
    private static ColorStateList b(@NonNull Resources resources, @ColorRes int i) {
        a aVar;
        synchronized (f467c) {
            SparseArray<a> sparseArray = f466b.get(resources);
            if (sparseArray != null && sparseArray.size() > 0 && (aVar = sparseArray.get(i)) != null) {
                if (aVar.f469b.equals(resources.getConfiguration())) {
                    return aVar.f468a;
                }
                sparseArray.remove(i);
            }
            return null;
        }
    }

    @Nullable
    public static ColorStateList c(@NonNull Resources resources, @ColorRes int i, @Nullable Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= 23) {
            return resources.getColorStateList(i, theme);
        }
        ColorStateList colorStateListB = b(resources, i);
        if (colorStateListB != null) {
            return colorStateListB;
        }
        ColorStateList colorStateListG = g(resources, i, theme);
        if (colorStateListG == null) {
            return resources.getColorStateList(i);
        }
        a(resources, i, colorStateListG);
        return colorStateListG;
    }

    @Nullable
    public static Drawable d(@NonNull Resources resources, @DrawableRes int i, @Nullable Resources.Theme theme) {
        return Build.VERSION.SDK_INT >= 21 ? resources.getDrawable(i, theme) : resources.getDrawable(i);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface e(@NonNull Context context, @FontRes int i, TypedValue typedValue, int i2, @Nullable b bVar) {
        if (context.isRestricted()) {
            return null;
        }
        return i(context, i, typedValue, i2, bVar, null, true);
    }

    @NonNull
    private static TypedValue f() {
        TypedValue typedValue = f465a.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        f465a.set(typedValue2);
        return typedValue2;
    }

    @Nullable
    private static ColorStateList g(Resources resources, int i, @Nullable Resources.Theme theme) {
        if (h(resources, i)) {
            return null;
        }
        try {
            return androidx.core.content.res.a.a(resources, resources.getXml(i), theme);
        } catch (Exception e) {
            Log.e("ResourcesCompat", "Failed to inflate ColorStateList, leaving it to the framework", e);
            return null;
        }
    }

    private static boolean h(@NonNull Resources resources, @ColorRes int i) {
        TypedValue typedValueF = f();
        resources.getValue(i, typedValueF, true);
        int i2 = typedValueF.type;
        return i2 >= 28 && i2 <= 31;
    }

    private static Typeface i(@NonNull Context context, int i, TypedValue typedValue, int i2, @Nullable b bVar, @Nullable Handler handler, boolean z) {
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        Typeface typefaceJ = j(context, resources, typedValue, i, i2, bVar, handler, z);
        if (typefaceJ != null || bVar != null) {
            return typefaceJ;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i) + " could not be retrieved.");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Typeface j(@androidx.annotation.NonNull android.content.Context r15, android.content.res.Resources r16, android.util.TypedValue r17, int r18, int r19, @androidx.annotation.Nullable androidx.core.content.res.e.b r20, @androidx.annotation.Nullable android.os.Handler r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.e.j(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, androidx.core.content.res.e$b, android.os.Handler, boolean):android.graphics.Typeface");
    }
}
