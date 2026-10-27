package a0;

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
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?> f2a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Field f3b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Field f4c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Method f5d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Method f6e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Method f7f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Handler f8g = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: a0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityRecreator.java */
    public class RunnableC0000a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ d f9b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Object f10c;

        public RunnableC0000a(d dVar, Object obj) {
            this.f9b = dVar;
            this.f10c = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f9b.f15a = this.f10c;
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Application f11b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ d f12c;

        public b(Application application, d dVar) {
            this.f11b = application;
            this.f12c = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f11b.unregisterActivityLifecycleCallbacks(this.f12c);
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Object f13b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Object f14c;

        public c(Object obj, Object obj2) {
            this.f13b = obj;
            this.f14c = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Method method = a.f5d;
                if (method != null) {
                    method.invoke(this.f13b, this.f14c, Boolean.FALSE, "AppCompat recreation");
                } else {
                    a.f6e.invoke(this.f13b, this.f14c, Boolean.FALSE);
                }
            } catch (RuntimeException e3) {
                if (e3.getClass() == RuntimeException.class && e3.getMessage() != null && e3.getMessage().startsWith("Unable to stop")) {
                    throw e3;
                }
            } catch (Throwable th) {
                Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
            }
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    public static final class d implements Application.ActivityLifecycleCallbacks {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f15a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Activity f16b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f17c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f18d = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f19e = false;

        public d(Activity activity) {
            this.f16b = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.f16b == activity) {
                this.f16b = null;
                this.f18d = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.f18d || this.f19e || this.f17c || !a.h(this.f15a, activity)) {
                return;
            }
            this.f19e = true;
            this.f15a = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.f16b == activity) {
                this.f17c = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    static {
        Class<?> clsA = a();
        f2a = clsA;
        f3b = b();
        f4c = f();
        f5d = d(clsA);
        f6e = c(clsA);
        f7f = e(clsA);
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
        int i3 = Build.VERSION.SDK_INT;
        return i3 == 26 || i3 == 27;
    }

    public static boolean h(Object obj, Activity activity) {
        try {
            Object obj2 = f4c.get(activity);
            if (obj2 != obj) {
                return false;
            }
            f8g.postAtFrontOfQueue(new c(f3b.get(activity), obj2));
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
        if (g() && f7f == null) {
            return false;
        }
        if (f6e == null && f5d == null) {
            return false;
        }
        try {
            Object obj2 = f4c.get(activity);
            if (obj2 == null || (obj = f3b.get(activity)) == null) {
                return false;
            }
            Application application = activity.getApplication();
            d dVar = new d(activity);
            application.registerActivityLifecycleCallbacks(dVar);
            Handler handler = f8g;
            handler.post(new RunnableC0000a(dVar, obj2));
            try {
                if (g()) {
                    Method method = f7f;
                    Boolean bool = Boolean.FALSE;
                    method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                } else {
                    activity.recreate();
                }
                handler.post(new b(application, dVar));
                return true;
            } catch (Throwable th) {
                f8g.post(new b(application, dVar));
                throw th;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}
