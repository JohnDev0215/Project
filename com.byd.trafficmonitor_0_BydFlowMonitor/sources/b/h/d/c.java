package b.h.d;

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
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?> f2352a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Field f2353b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Field f2354c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Method f2355d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Method f2356e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Method f2357f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Handler f2358g = new Handler(Looper.getMainLooper());

    /* JADX INFO: compiled from: ActivityRecreator.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ d f2359b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Object f2360c;

        public a(d dVar, Object obj) {
            this.f2359b = dVar;
            this.f2360c = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2359b.f2365a = this.f2360c;
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Application f2361b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ d f2362c;

        public b(Application application, d dVar) {
            this.f2361b = application;
            this.f2362c = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2361b.unregisterActivityLifecycleCallbacks(this.f2362c);
        }
    }

    /* JADX INFO: renamed from: b.h.d.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityRecreator.java */
    public class RunnableC0032c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Object f2363b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Object f2364c;

        public RunnableC0032c(Object obj, Object obj2) {
            this.f2363b = obj;
            this.f2364c = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Method method = c.f2355d;
                if (method != null) {
                    method.invoke(this.f2363b, this.f2364c, Boolean.FALSE, "AppCompat recreation");
                } else {
                    c.f2356e.invoke(this.f2363b, this.f2364c, Boolean.FALSE);
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
    public static final class d implements Application.ActivityLifecycleCallbacks {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f2365a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Activity f2366b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2367c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f2368d = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2369e = false;

        public d(Activity activity) {
            this.f2366b = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.f2366b == activity) {
                this.f2366b = null;
                this.f2368d = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.f2368d || this.f2369e || this.f2367c || !c.h(this.f2365a, activity)) {
                return;
            }
            this.f2369e = true;
            this.f2365a = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.f2366b == activity) {
                this.f2367c = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    static {
        Class<?> clsA = a();
        f2352a = clsA;
        f2353b = b();
        f2354c = f();
        f2355d = d(clsA);
        f2356e = c(clsA);
        f2357f = e(clsA);
    }

    public static Class<?> a() {
        try {
            return Class.forName("android.app.ActivityThread");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Field b() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mMainThread");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Method c(Class<?> cls) {
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

    public static Method d(Class<?> cls) {
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

    public static Method e(Class<?> cls) {
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

    public static Field f() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mToken");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean g() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 == 26 || i2 == 27;
    }

    public static boolean h(Object obj, Activity activity) {
        try {
            Object obj2 = f2354c.get(activity);
            if (obj2 != obj) {
                return false;
            }
            f2358g.postAtFrontOfQueue(new RunnableC0032c(f2353b.get(activity), obj2));
            return true;
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while fetching field values", th);
            return false;
        }
    }

    public static boolean i(Activity activity) {
        Object obj;
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
            return true;
        }
        if (g() && f2357f == null) {
            return false;
        }
        if (f2356e == null && f2355d == null) {
            return false;
        }
        try {
            Object obj2 = f2354c.get(activity);
            if (obj2 == null || (obj = f2353b.get(activity)) == null) {
                return false;
            }
            Application application = activity.getApplication();
            d dVar = new d(activity);
            application.registerActivityLifecycleCallbacks(dVar);
            Handler handler = f2358g;
            handler.post(new a(dVar, obj2));
            try {
                if (g()) {
                    Method method = f2357f;
                    Boolean bool = Boolean.FALSE;
                    method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                } else {
                    activity.recreate();
                }
                handler.post(new b(application, dVar));
                return true;
            } catch (Throwable th) {
                f2358g.post(new b(application, dVar));
                throw th;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}
