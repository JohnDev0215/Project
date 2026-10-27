package androidx.core.content.c;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import b.g.j.h;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ResourcesCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static abstract class a {

        /* JADX INFO: renamed from: androidx.core.content.c.f$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ResourcesCompat.java */
        class RunnableC0016a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Typeface f890b;

            RunnableC0016a(Typeface typeface) {
                this.f890b = typeface;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.d(this.f890b);
            }
        }

        /* JADX INFO: compiled from: ResourcesCompat.java */
        class b implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f892b;

            b(int i) {
                this.f892b = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.c(this.f892b);
            }
        }

        public final void a(int i, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new b(i));
        }

        public final void b(Typeface typeface, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new RunnableC0016a(typeface));
        }

        public abstract void c(int i);

        public abstract void d(Typeface typeface);
    }

    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static final class b {

        /* JADX INFO: compiled from: ResourcesCompat.java */
        static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private static final Object f894a = new Object();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private static Method f895b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private static boolean f896c;

            /* JADX WARN: Removed duplicated region for block: B:19:0x0038 A[Catch: all -> 0x003a, DONT_GENERATE, TryCatch #2 {, blocks: (B:4:0x0003, B:8:0x0009, B:12:0x0021, B:11:0x001a, B:13:0x0023, B:15:0x0027, B:19:0x0038, B:18:0x002e), top: B:28:0x0003, inners: #0, #1 }] */
            /* JADX WARN: Removed duplicated region for block: B:26:0x0027 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            static void a(android.content.res.Resources.Theme r6) {
                /*
                    java.lang.Object r0 = androidx.core.content.c.f.b.a.f894a
                    monitor-enter(r0)
                    boolean r1 = androidx.core.content.c.f.b.a.f896c     // Catch: java.lang.Throwable -> L3a
                    r2 = 0
                    if (r1 != 0) goto L23
                    r1 = 1
                    java.lang.Class<android.content.res.Resources$Theme> r3 = android.content.res.Resources.Theme.class
                    java.lang.String r4 = "rebase"
                    java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3a
                    java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3a
                    androidx.core.content.c.f.b.a.f895b = r3     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3a
                    r3.setAccessible(r1)     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3a
                    goto L21
                L19:
                    r3 = move-exception
                    java.lang.String r4 = "ResourcesCompat"
                    java.lang.String r5 = "Failed to retrieve rebase() method"
                    android.util.Log.i(r4, r5, r3)     // Catch: java.lang.Throwable -> L3a
                L21:
                    androidx.core.content.c.f.b.a.f896c = r1     // Catch: java.lang.Throwable -> L3a
                L23:
                    java.lang.reflect.Method r1 = androidx.core.content.c.f.b.a.f895b     // Catch: java.lang.Throwable -> L3a
                    if (r1 == 0) goto L38
                    java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L3a
                    r1.invoke(r6, r2)     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L3a
                    goto L38
                L2d:
                    r6 = move-exception
                    java.lang.String r1 = "ResourcesCompat"
                    java.lang.String r2 = "Failed to invoke rebase() method via reflection"
                    android.util.Log.i(r1, r2, r6)     // Catch: java.lang.Throwable -> L3a
                    r6 = 0
                    androidx.core.content.c.f.b.a.f895b = r6     // Catch: java.lang.Throwable -> L3a
                L38:
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L3a
                    return
                L3a:
                    r6 = move-exception
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L3a
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.c.f.b.a.a(android.content.res.Resources$Theme):void");
            }
        }

        /* JADX INFO: renamed from: androidx.core.content.c.f$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ResourcesCompat.java */
        static class C0017b {
            static void a(Resources.Theme theme) {
                theme.rebase();
            }
        }

        public static void a(Resources.Theme theme) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                C0017b.a(theme);
            } else if (i >= 23) {
                a.a(theme);
            }
        }
    }

    public static Drawable a(Resources resources, int i, Resources.Theme theme) throws Resources.NotFoundException {
        return Build.VERSION.SDK_INT >= 21 ? resources.getDrawable(i, theme) : resources.getDrawable(i);
    }

    public static Typeface b(Context context, int i) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return e(context, i, new TypedValue(), 0, null, null, false);
    }

    public static Typeface c(Context context, int i, TypedValue typedValue, int i2, a aVar) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return e(context, i, typedValue, i2, aVar, null, true);
    }

    public static void d(Context context, int i, a aVar, Handler handler) throws Resources.NotFoundException {
        h.c(aVar);
        if (context.isRestricted()) {
            aVar.a(-4, handler);
        } else {
            e(context, i, new TypedValue(), 0, aVar, handler, false);
        }
    }

    private static Typeface e(Context context, int i, TypedValue typedValue, int i2, a aVar, Handler handler, boolean z) {
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        Typeface typefaceF = f(context, resources, typedValue, i, i2, aVar, handler, z);
        if (typefaceF != null || aVar != null) {
            return typefaceF;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i) + " could not be retrieved.");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Typeface f(android.content.Context r15, android.content.res.Resources r16, android.util.TypedValue r17, int r18, int r19, androidx.core.content.c.f.a r20, android.os.Handler r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.c.f.f(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, androidx.core.content.c.f$a, android.os.Handler, boolean):android.graphics.Typeface");
    }
}
