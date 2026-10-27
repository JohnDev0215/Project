package c0;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import java.lang.reflect.Method;
import k0.h;

/* JADX INFO: compiled from: ResourcesCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static abstract class a {

        /* JADX INFO: renamed from: c0.f$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ResourcesCompat.java */
        public class RunnableC0007a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Typeface f1803b;

            public RunnableC0007a(Typeface typeface) {
                this.f1803b = typeface;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.d(this.f1803b);
            }
        }

        /* JADX INFO: compiled from: ResourcesCompat.java */
        public class b implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ int f1805b;

            public b(int i3) {
                this.f1805b = i3;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.c(this.f1805b);
            }
        }

        public final void a(int i3, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new b(i3));
        }

        public final void b(Typeface typeface, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new RunnableC0007a(typeface));
        }

        public abstract void c(int i3);

        public abstract void d(Typeface typeface);
    }

    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static final class b {

        /* JADX INFO: compiled from: ResourcesCompat.java */
        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final Object f1807a = new Object();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public static Method f1808b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public static boolean f1809c;

            /* JADX WARN: Removed duplicated region for block: B:30:0x0027 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public static void a(android.content.res.Resources.Theme r6) {
                /*
                    java.lang.Object r0 = c0.f.b.a.f1807a
                    monitor-enter(r0)
                    boolean r1 = c0.f.b.a.f1809c     // Catch: java.lang.Throwable -> L3c
                    r2 = 0
                    if (r1 != 0) goto L23
                    r1 = 1
                    java.lang.Class<android.content.res.Resources$Theme> r3 = android.content.res.Resources.Theme.class
                    java.lang.String r4 = "rebase"
                    java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    c0.f.b.a.f1808b = r3     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    r3.setAccessible(r1)     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3c
                    goto L21
                L19:
                    r3 = move-exception
                    java.lang.String r4 = "ResourcesCompat"
                    java.lang.String r5 = "Failed to retrieve rebase() method"
                    android.util.Log.i(r4, r5, r3)     // Catch: java.lang.Throwable -> L3c
                L21:
                    c0.f.b.a.f1809c = r1     // Catch: java.lang.Throwable -> L3c
                L23:
                    java.lang.reflect.Method r1 = c0.f.b.a.f1808b     // Catch: java.lang.Throwable -> L3c
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
                    c0.f.b.a.f1808b = r6     // Catch: java.lang.Throwable -> L3c
                L3a:
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L3c
                    return
                L3c:
                    r6 = move-exception
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L3c
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: c0.f.b.a.a(android.content.res.Resources$Theme):void");
            }
        }

        /* JADX INFO: renamed from: c0.f$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ResourcesCompat.java */
        public static class C0008b {
            public static void a(Resources.Theme theme) {
                theme.rebase();
            }
        }

        public static void a(Resources.Theme theme) {
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 29) {
                C0008b.a(theme);
            } else if (i3 >= 23) {
                a.a(theme);
            }
        }
    }

    public static Drawable a(Resources resources, int i3, Resources.Theme theme) throws Resources.NotFoundException {
        return Build.VERSION.SDK_INT >= 21 ? resources.getDrawable(i3, theme) : resources.getDrawable(i3);
    }

    public static Typeface b(Context context, int i3) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return e(context, i3, new TypedValue(), 0, null, null, false);
    }

    public static Typeface c(Context context, int i3, TypedValue typedValue, int i4, a aVar) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return e(context, i3, typedValue, i4, aVar, null, true);
    }

    public static void d(Context context, int i3, a aVar, Handler handler) throws Resources.NotFoundException {
        h.c(aVar);
        if (context.isRestricted()) {
            aVar.a(-4, handler);
        } else {
            e(context, i3, new TypedValue(), 0, aVar, handler, false);
        }
    }

    public static Typeface e(Context context, int i3, TypedValue typedValue, int i4, a aVar, Handler handler, boolean z3) {
        Resources resources = context.getResources();
        resources.getValue(i3, typedValue, true);
        Typeface typefaceF = f(context, resources, typedValue, i3, i4, aVar, handler, z3);
        if (typefaceF != null || aVar != null) {
            return typefaceF;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i3) + " could not be retrieved.");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Typeface f(android.content.Context r15, android.content.res.Resources r16, android.util.TypedValue r17, int r18, int r19, c0.f.a r20, android.os.Handler r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c0.f.f(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, c0.f$a, android.os.Handler, boolean):android.graphics.Typeface");
    }
}
