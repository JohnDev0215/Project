package d0;

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
    public static final Class<?> f4261a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Field f4262b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Field f4263c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Method f4264d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Method f4265e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Method f4266f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Handler f4267g = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: d0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityRecreator.java */
    public class RunnableC0024a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ d f4268d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Object f4269e;

        public RunnableC0024a(d dVar, Object obj) {
            this.f4268d = dVar;
            this.f4269e = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4268d.f4274a = this.f4269e;
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Application f4270d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ d f4271e;

        public b(Application application, d dVar) {
            this.f4270d = application;
            this.f4271e = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4270d.unregisterActivityLifecycleCallbacks(this.f4271e);
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Object f4272d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Object f4273e;

        public c(Object obj, Object obj2) {
            this.f4272d = obj;
            this.f4273e = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Method method = a.f4264d;
                if (method != null) {
                    method.invoke(this.f4272d, this.f4273e, Boolean.FALSE, "AppCompat recreation");
                } else {
                    a.f4265e.invoke(this.f4272d, this.f4273e, Boolean.FALSE);
                }
            } catch (RuntimeException e8) {
                if (e8.getClass() == RuntimeException.class && e8.getMessage() != null && e8.getMessage().startsWith("Unable to stop")) {
                    throw e8;
                }
            } catch (Throwable th) {
                Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
            }
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    public static final class d implements Application.ActivityLifecycleCallbacks {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4274a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Activity f4275b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f4276c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f4277d = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f4278e = false;

        public d(Activity activity) {
            this.f4275b = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.f4275b == activity) {
                this.f4275b = null;
                this.f4277d = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.f4277d || this.f4278e || this.f4276c || !a.h(this.f4274a, activity)) {
                return;
            }
            this.f4278e = true;
            this.f4274a = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.f4275b == activity) {
                this.f4276c = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    static {
        Class<?> clsA = a();
        f4261a = clsA;
        f4262b = b();
        f4263c = f();
        f4264d = d(clsA);
        f4265e = c(clsA);
        f4266f = e(clsA);
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
        int i8 = Build.VERSION.SDK_INT;
        return i8 == 26 || i8 == 27;
    }

    public static boolean h(Object obj, Activity activity) {
        try {
            Object obj2 = f4263c.get(activity);
            if (obj2 != obj) {
                return false;
            }
            f4267g.postAtFrontOfQueue(new c(f4262b.get(activity), obj2));
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
        if (g() && f4266f == null) {
            return false;
        }
        if (f4265e == null && f4264d == null) {
            return false;
        }
        try {
            Object obj2 = f4263c.get(activity);
            if (obj2 == null || (obj = f4262b.get(activity)) == null) {
                return false;
            }
            Application application = activity.getApplication();
            d dVar = new d(activity);
            application.registerActivityLifecycleCallbacks(dVar);
            Handler handler = f4267g;
            handler.post(new RunnableC0024a(dVar, obj2));
            try {
                if (g()) {
                    Method method = f4266f;
                    Boolean bool = Boolean.FALSE;
                    method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                } else {
                    activity.recreate();
                }
                handler.post(new b(application, dVar));
                return true;
            } catch (Throwable th) {
                f4267g.post(new b(application, dVar));
                throw th;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}
