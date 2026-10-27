package y;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import h0.h;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ResourcesCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static abstract class a {

        /* JADX INFO: renamed from: y.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ResourcesCompat.java */
        class RunnableC0174a implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ Typeface f9880e;

            RunnableC0174a(Typeface typeface) {
                this.f9880e = typeface;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.e(this.f9880e);
            }
        }

        /* JADX INFO: renamed from: y.b$a$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ResourcesCompat.java */
        class RunnableC0175b implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ int f9882e;

            RunnableC0175b(int i7) {
                this.f9882e = i7;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.d(this.f9882e);
            }
        }

        public static Handler c(Handler handler) {
            return handler == null ? new Handler(Looper.getMainLooper()) : handler;
        }

        public final void a(int i7, Handler handler) {
            c(handler).post(new RunnableC0175b(i7));
        }

        public final void b(Typeface typeface, Handler handler) {
            c(handler).post(new RunnableC0174a(typeface));
        }

        public abstract void d(int i7);

        public abstract void e(Typeface typeface);
    }

    /* JADX INFO: renamed from: y.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static final class C0176b {

        /* JADX INFO: renamed from: y.b$b$a */
        /* JADX INFO: compiled from: ResourcesCompat.java */
        static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private static final Object f9884a = new Object();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private static Method f9885b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private static boolean f9886c;

            /* JADX WARN: Removed duplicated region for block: B:30:0x0027 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            static void a(android.content.res.Resources.Theme r6) {
                /*
                    java.lang.Object r0 = y.b.C0176b.a.f9884a
                    monitor-enter(r0)
                    boolean r1 = y.b.C0176b.a.f9886c     // Catch: java.lang.Throwable -> L3c
                    r2 = 0
                    if (r1 != 0) goto L23
                    r1 = 1
                    java.lang.Class<android.content.res.Resources$Theme> r3 = android.content.res.Resources.Theme.class
                    java.lang.String r4 = "rebase"
                    java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    y.b.C0176b.a.f9885b = r3     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    r3.setAccessible(r1)     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    goto L21
                L19:
                    r3 = move-exception
                    java.lang.String r4 = "ResourcesCompat"
                    java.lang.String r5 = "Failed to retrieve rebase() method"
                    android.util.Log.i(r4, r5, r3)     // Catch: java.lang.Throwable -> L3c
                L21:
                    y.b.C0176b.a.f9886c = r1     // Catch: java.lang.Throwable -> L3c
                L23:
                    java.lang.reflect.Method r1 = y.b.C0176b.a.f9885b     // Catch: java.lang.Throwable -> L3c
                    if (r1 == 0) goto L3a
                    java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.reflect.InvocationTargetException -> L2d java.lang.IllegalAccessException -> L2f java.lang.Throwable -> L3c
                    r1.invoke(r6, r2)     // Catch: java.lang.reflect.InvocationTargetException -> L2d java.lang.IllegalAccessException -> L2f java.lang.Throwable -> L3c
                    goto L3a
                L2d:
                    r6 = move-exception
                    goto L30
                L2f:
                    r6 = move-exception
                L30:
                    java.lang.String r1 = "ResourcesCompat"
                    java.lang.String r2 = "Failed to invoke rebase() method via reflection"
                    android.util.Log.i(r1, r2, r6)     // Catch: java.lang.Throwable -> L3c
                    r6 = 0
                    y.b.C0176b.a.f9885b = r6     // Catch: java.lang.Throwable -> L3c
                L3a:
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L3c
                    return
                L3c:
                    r6 = move-exception
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L3c
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: y.b.C0176b.a.a(android.content.res.Resources$Theme):void");
            }
        }

        /* JADX INFO: renamed from: y.b$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ResourcesCompat.java */
        static class C0177b {
            static void a(Resources.Theme theme) {
                theme.rebase();
            }
        }

        public static void a(Resources.Theme theme) {
            if (Build.VERSION.SDK_INT >= 29) {
                C0177b.a(theme);
            } else {
                a.a(theme);
            }
        }
    }

    public static Typeface a(Context context, int i7) {
        if (context.isRestricted()) {
            return null;
        }
        return f(context, i7, new TypedValue(), 0, null, null, false, true);
    }

    public static Drawable b(Resources resources, int i7, Resources.Theme theme) {
        return resources.getDrawable(i7, theme);
    }

    public static Typeface c(Context context, int i7) {
        if (context.isRestricted()) {
            return null;
        }
        return f(context, i7, new TypedValue(), 0, null, null, false, false);
    }

    public static Typeface d(Context context, int i7, TypedValue typedValue, int i8, a aVar) {
        if (context.isRestricted()) {
            return null;
        }
        return f(context, i7, typedValue, i8, aVar, null, true, false);
    }

    public static void e(Context context, int i7, a aVar, Handler handler) {
        h.f(aVar);
        if (context.isRestricted()) {
            aVar.a(-4, handler);
        } else {
            f(context, i7, new TypedValue(), 0, aVar, handler, false, false);
        }
    }

    private static Typeface f(Context context, int i7, TypedValue typedValue, int i8, a aVar, Handler handler, boolean z6, boolean z7) {
        Resources resources = context.getResources();
        resources.getValue(i7, typedValue, true);
        Typeface typefaceG = g(context, resources, typedValue, i7, i8, aVar, handler, z6, z7);
        if (typefaceG != null || aVar != null || z7) {
            return typefaceG;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i7) + " could not be retrieved.");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Typeface g(android.content.Context r15, android.content.res.Resources r16, android.util.TypedValue r17, int r18, int r19, y.b.a r20, android.os.Handler r21, boolean r22, boolean r23) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: y.b.g(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, y.b$a, android.os.Handler, boolean, boolean):android.graphics.Typeface");
    }
}
