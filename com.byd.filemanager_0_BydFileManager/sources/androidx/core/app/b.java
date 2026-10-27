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
    protected static final Class<?> f951a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected static final Field f952b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected static final Field f953c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected static final Method f954d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected static final Method f955e;
    protected static final Method f;
    private static final Handler g = new Handler(Looper.getMainLooper());

    /* JADX INFO: compiled from: ActivityRecreator.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ d f956b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f957c;

        a(d dVar, Object obj) {
            this.f956b = dVar;
            this.f957c = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f956b.f962a = this.f957c;
        }
    }

    /* JADX INFO: renamed from: androidx.core.app.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityRecreator.java */
    class RunnableC0013b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Application f958b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f959c;

        RunnableC0013b(Application application, d dVar) {
            this.f958b = application;
            this.f959c = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f958b.unregisterActivityLifecycleCallbacks(this.f959c);
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f960b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f961c;

        c(Object obj, Object obj2) {
            this.f960b = obj;
            this.f961c = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Method method = b.f954d;
                if (method != null) {
                    method.invoke(this.f960b, this.f961c, Boolean.FALSE, "AppCompat recreation");
                } else {
                    b.f955e.invoke(this.f960b, this.f961c, Boolean.FALSE);
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
        Object f962a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Activity f963b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f964c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f965d = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f966e = false;

        d(Activity activity) {
            this.f963b = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.f963b == activity) {
                this.f963b = null;
                this.f965d = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.f965d || this.f966e || this.f964c || !b.h(this.f962a, activity)) {
                return;
            }
            this.f966e = true;
            this.f962a = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.f963b == activity) {
                this.f964c = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    static {
        Class<?> clsA = a();
        f951a = clsA;
        f952b = b();
        f953c = f();
        f954d = d(clsA);
        f955e = c(clsA);
        f = e(clsA);
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
            Object obj2 = f953c.get(activity);
            if (obj2 != obj) {
                return false;
            }
            g.postAtFrontOfQueue(new c(f952b.get(activity), obj2));
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
        if (g() && f == null) {
            return false;
        }
        if (f955e == null && f954d == null) {
            return false;
        }
        try {
            Object obj2 = f953c.get(activity);
            if (obj2 == null || (obj = f952b.get(activity)) == null) {
                return false;
            }
            Application application = activity.getApplication();
            d dVar = new d(activity);
            application.registerActivityLifecycleCallbacks(dVar);
            Handler handler = g;
            handler.post(new a(dVar, obj2));
            try {
                if (g()) {
                    Method method = f;
                    Boolean bool = Boolean.FALSE;
                    method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                } else {
                    activity.recreate();
                }
                handler.post(new RunnableC0013b(application, dVar));
                return true;
            } catch (Throwable th) {
                g.post(new RunnableC0013b(application, dVar));
                throw th;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}
