package b.f.e;

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
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?> f1560a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Field f1561b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Field f1562c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Method f1563d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Method f1564e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Method f1565f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Handler f1566g = new Handler(Looper.getMainLooper());

    /* JADX INFO: compiled from: ActivityRecreator.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ d f1567c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Object f1568d;

        public a(d dVar, Object obj) {
            this.f1567c = dVar;
            this.f1568d = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1567c.f1573a = this.f1568d;
        }
    }

    /* JADX INFO: renamed from: b.f.e.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityRecreator.java */
    public class RunnableC0020b implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Application f1569c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ d f1570d;

        public RunnableC0020b(Application application, d dVar) {
            this.f1569c = application;
            this.f1570d = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1569c.unregisterActivityLifecycleCallbacks(this.f1570d);
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Object f1571c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Object f1572d;

        public c(Object obj, Object obj2) {
            this.f1571c = obj;
            this.f1572d = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Method method = b.f1563d;
                if (method != null) {
                    method.invoke(this.f1571c, this.f1572d, Boolean.FALSE, "AppCompat recreation");
                } else {
                    b.f1564e.invoke(this.f1571c, this.f1572d, Boolean.FALSE);
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
        public Object f1573a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Activity f1574b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1575c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1576d = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1577e = false;

        public d(Activity activity) {
            this.f1574b = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.f1574b == activity) {
                this.f1574b = null;
                this.f1576d = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.f1576d || this.f1577e || this.f1575c || !b.h(this.f1573a, activity)) {
                return;
            }
            this.f1577e = true;
            this.f1573a = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.f1574b == activity) {
                this.f1575c = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    static {
        Class<?> clsA = a();
        f1560a = clsA;
        f1561b = b();
        f1562c = f();
        f1563d = d(clsA);
        f1564e = c(clsA);
        f1565f = e(clsA);
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
            Object obj2 = f1562c.get(activity);
            if (obj2 != obj) {
                return false;
            }
            f1566g.postAtFrontOfQueue(new c(f1561b.get(activity), obj2));
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
        if (g() && f1565f == null) {
            return false;
        }
        if (f1564e == null && f1563d == null) {
            return false;
        }
        try {
            Object obj2 = f1562c.get(activity);
            if (obj2 == null || (obj = f1561b.get(activity)) == null) {
                return false;
            }
            Application application = activity.getApplication();
            d dVar = new d(activity);
            application.registerActivityLifecycleCallbacks(dVar);
            Handler handler = f1566g;
            handler.post(new a(dVar, obj2));
            try {
                if (g()) {
                    Method method = f1565f;
                    Boolean bool = Boolean.FALSE;
                    method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                } else {
                    activity.recreate();
                }
                handler.post(new RunnableC0020b(application, dVar));
                return true;
            } catch (Throwable th) {
                f1566g.post(new RunnableC0020b(application, dVar));
                throw th;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}
