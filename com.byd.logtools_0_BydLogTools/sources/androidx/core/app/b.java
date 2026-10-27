package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: compiled from: ActivityRecreator.java */
/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected static final Class<?> f1262a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected static final Field f1263b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected static final Field f1264c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected static final Method f1265d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected static final Method f1266e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected static final Method f1267f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final Handler f1268g = new Handler(Looper.getMainLooper());

    /* JADX INFO: compiled from: ActivityRecreator.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ d f1269b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f1270c;

        a(d dVar, Object obj) {
            this.f1269b = dVar;
            this.f1270c = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1269b.f1275a = this.f1270c;
        }
    }

    /* JADX INFO: renamed from: androidx.core.app.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityRecreator.java */
    class RunnableC0015b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Application f1271b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f1272c;

        RunnableC0015b(Application application, d dVar) {
            this.f1271b = application;
            this.f1272c = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1271b.unregisterActivityLifecycleCallbacks(this.f1272c);
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f1273b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f1274c;

        c(Object obj, Object obj2) {
            this.f1273b = obj;
            this.f1274c = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Method method = b.f1265d;
                if (method != null) {
                    method.invoke(this.f1273b, this.f1274c, Boolean.FALSE, "AppCompat recreation");
                } else {
                    b.f1266e.invoke(this.f1273b, this.f1274c, Boolean.FALSE);
                }
            } catch (RuntimeException e2) {
                if (e2.getClass() == RuntimeException.class && e2.getMessage() != null && e2.getMessage().startsWith("Unable to stop")) {
                    throw e2;
                }
            } catch (Throwable th) {
                Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
            }
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    private static final class d implements Application.ActivityLifecycleCallbacks {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Object f1275a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Activity f1276b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1277c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1278d = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f1279e = false;

        d(Activity activity) {
            this.f1276b = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.f1276b == activity) {
                this.f1276b = null;
                this.f1278d = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.f1278d || this.f1279e || this.f1277c || !b.h(this.f1275a, activity)) {
                return;
            }
            this.f1279e = true;
            this.f1275a = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.f1276b == activity) {
                this.f1277c = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    static {
        Class<?> clsA = a();
        f1262a = clsA;
        f1263b = b();
        f1264c = f();
        f1265d = d(clsA);
        f1266e = c(clsA);
        f1267f = e(clsA);
    }

    private static Class<?> a() {
        try {
            return Class.forName("android.app.ActivityThread");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Field b() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mMainThread");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method c(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method d(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE, String.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method e(Class<?> cls) {
        if (g() && cls != null) {
            try {
                Class<?> cls2 = Boolean.TYPE;
                Method declaredMethod = cls.getDeclaredMethod("requestRelaunchActivity", IBinder.class, List.class, List.class, Integer.TYPE, cls2, Configuration.class, Configuration.class, cls2, cls2);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    private static Field f() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mToken");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean g() {
        int i = Build.VERSION.SDK_INT;
        return i == 26 || i == 27;
    }

    protected static boolean h(Object obj, Activity activity) {
        try {
            Object obj2 = f1264c.get(activity);
            if (obj2 != obj) {
                return false;
            }
            f1268g.postAtFrontOfQueue(new c(f1263b.get(activity), obj2));
            return true;
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while fetching field values", th);
            return false;
        }
    }

    static boolean i(Activity activity) {
        Object obj;
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
            return true;
        }
        if (g() && f1267f == null) {
            return false;
        }
        if (f1266e == null && f1265d == null) {
            return false;
        }
        try {
            Object obj2 = f1264c.get(activity);
            if (obj2 == null || (obj = f1263b.get(activity)) == null) {
                return false;
            }
            Application application = activity.getApplication();
            d dVar = new d(activity);
            application.registerActivityLifecycleCallbacks(dVar);
            Handler handler = f1268g;
            handler.post(new a(dVar, obj2));
            try {
                if (g()) {
                    Method method = f1267f;
                    Boolean bool = Boolean.FALSE;
                    method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                } else {
                    activity.recreate();
                }
                handler.post(new RunnableC0015b(application, dVar));
                return true;
            } catch (Throwable th) {
                f1268g.post(new RunnableC0015b(application, dVar));
                throw th;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}
