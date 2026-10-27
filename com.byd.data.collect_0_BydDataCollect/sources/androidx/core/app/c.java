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
final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected static final Class<?> f1184a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected static final Field f1185b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected static final Field f1186c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected static final Method f1187d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected static final Method f1188e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected static final Method f1189f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final Handler f1190g = new Handler(Looper.getMainLooper());

    /* JADX INFO: compiled from: ActivityRecreator.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ d f1191b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f1192c;

        a(d dVar, Object obj) {
            this.f1191b = dVar;
            this.f1192c = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1191b.f1197a = this.f1192c;
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Application f1193b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f1194c;

        b(Application application, d dVar) {
            this.f1193b = application;
            this.f1194c = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1193b.unregisterActivityLifecycleCallbacks(this.f1194c);
        }
    }

    /* JADX INFO: renamed from: androidx.core.app.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityRecreator.java */
    class RunnableC0014c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f1195b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f1196c;

        RunnableC0014c(Object obj, Object obj2) {
            this.f1195b = obj;
            this.f1196c = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Method method = c.f1187d;
                if (method != null) {
                    method.invoke(this.f1195b, this.f1196c, Boolean.FALSE, "AppCompat recreation");
                } else {
                    c.f1188e.invoke(this.f1195b, this.f1196c, Boolean.FALSE);
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
        Object f1197a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Activity f1198b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1199c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1200d = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f1201e = false;

        d(Activity activity) {
            this.f1198b = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.f1198b == activity) {
                this.f1198b = null;
                this.f1200d = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.f1200d || this.f1201e || this.f1199c || !c.h(this.f1197a, activity)) {
                return;
            }
            this.f1201e = true;
            this.f1197a = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.f1198b == activity) {
                this.f1199c = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    static {
        Class<?> clsA = a();
        f1184a = clsA;
        f1185b = b();
        f1186c = f();
        f1187d = d(clsA);
        f1188e = c(clsA);
        f1189f = e(clsA);
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
        int i2 = Build.VERSION.SDK_INT;
        return i2 == 26 || i2 == 27;
    }

    protected static boolean h(Object obj, Activity activity) {
        try {
            Object obj2 = f1186c.get(activity);
            if (obj2 != obj) {
                return false;
            }
            f1190g.postAtFrontOfQueue(new RunnableC0014c(f1185b.get(activity), obj2));
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
        if (g() && f1189f == null) {
            return false;
        }
        if (f1188e == null && f1187d == null) {
            return false;
        }
        try {
            Object obj2 = f1186c.get(activity);
            if (obj2 == null || (obj = f1185b.get(activity)) == null) {
                return false;
            }
            Application application = activity.getApplication();
            d dVar = new d(activity);
            application.registerActivityLifecycleCallbacks(dVar);
            Handler handler = f1190g;
            handler.post(new a(dVar, obj2));
            try {
                if (g()) {
                    Method method = f1189f;
                    Boolean bool = Boolean.FALSE;
                    method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                } else {
                    activity.recreate();
                }
                handler.post(new b(application, dVar));
                return true;
            } catch (Throwable th) {
                f1190g.post(new b(application, dVar));
                throw th;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}
