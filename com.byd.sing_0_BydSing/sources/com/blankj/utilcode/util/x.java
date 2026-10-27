package com.blankj.utilcode.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import androidx.lifecycle.Lifecycle;
import com.blankj.utilcode.util.w;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: UtilsActivityLifecycleImpl.java */
/* JADX INFO: loaded from: classes.dex */
final class x implements Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final x f2623a = new x();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Activity f2624b = new Activity();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final LinkedList<Activity> f2625c = new LinkedList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<w.c> f2626d = new CopyOnWriteArrayList();
    private final Map<Activity, List<w.a>> e = new ConcurrentHashMap();
    private int f = 0;
    private int g = 0;
    private boolean h = false;

    /* JADX INFO: compiled from: UtilsActivityLifecycleImpl.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f2627b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ w.a f2628c;

        a(Activity activity, w.a aVar) {
            this.f2627b = activity;
            this.f2628c = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            x.this.e(this.f2627b, this.f2628c);
        }
    }

    /* JADX INFO: compiled from: UtilsActivityLifecycleImpl.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f2630b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ w.a f2631c;

        b(Activity activity, w.a aVar) {
            this.f2630b = activity;
            this.f2631c = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            x.this.t(this.f2630b, this.f2631c);
        }
    }

    /* JADX INFO: compiled from: UtilsActivityLifecycleImpl.java */
    class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f2633b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f2634c;

        c(Activity activity, Object obj) {
            this.f2633b = activity;
            this.f2634c = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Window window = this.f2633b.getWindow();
                if (window != null) {
                    window.setSoftInputMode(((Integer) this.f2634c).intValue());
                }
            } catch (Exception unused) {
            }
        }
    }

    x() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Activity activity, w.a aVar) {
        List<w.a> copyOnWriteArrayList = this.e.get(activity);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.e.put(activity, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(aVar)) {
            return;
        }
        copyOnWriteArrayList.add(aVar);
    }

    private void f(Activity activity, Lifecycle.Event event) {
        g(activity, event, this.e.get(activity));
        g(activity, event, this.e.get(f2624b));
    }

    private void g(Activity activity, Lifecycle.Event event, List<w.a> list) {
        if (list == null) {
            return;
        }
        for (w.a aVar : list) {
            aVar.g(activity, event);
            if (event.equals(Lifecycle.Event.ON_CREATE)) {
                aVar.a(activity);
            } else if (event.equals(Lifecycle.Event.ON_START)) {
                aVar.e(activity);
            } else if (event.equals(Lifecycle.Event.ON_RESUME)) {
                aVar.d(activity);
            } else if (event.equals(Lifecycle.Event.ON_PAUSE)) {
                aVar.c(activity);
            } else if (event.equals(Lifecycle.Event.ON_STOP)) {
                aVar.f(activity);
            } else if (event.equals(Lifecycle.Event.ON_DESTROY)) {
                aVar.b(activity);
            }
        }
        if (event.equals(Lifecycle.Event.ON_DESTROY)) {
            this.e.remove(activity);
        }
    }

    private List<Activity> h() {
        LinkedList linkedList = new LinkedList();
        Activity activity = null;
        try {
            Object objJ = j();
            if (objJ == null) {
                return linkedList;
            }
            Field declaredField = objJ.getClass().getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(objJ);
            if (!(obj instanceof Map)) {
                return linkedList;
            }
            for (Object obj2 : ((Map) obj).values()) {
                Class<?> cls = obj2.getClass();
                Field declaredField2 = cls.getDeclaredField("activity");
                declaredField2.setAccessible(true);
                Activity activity2 = (Activity) declaredField2.get(obj2);
                if (activity == null) {
                    Field declaredField3 = cls.getDeclaredField("paused");
                    declaredField3.setAccessible(true);
                    if (declaredField3.getBoolean(obj2)) {
                        linkedList.addFirst(activity2);
                    } else {
                        activity = activity2;
                    }
                } else {
                    linkedList.addFirst(activity2);
                }
            }
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivitiesByReflect: " + e.getMessage());
        }
        if (activity != null) {
            linkedList.addFirst(activity);
        }
        return linkedList;
    }

    private Object j() {
        Object objK = k();
        return objK != null ? objK : l();
    }

    private Object k() {
        try {
            Field declaredField = Class.forName("android.app.ActivityThread").getDeclaredField("sCurrentActivityThread");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivityThreadInActivityThreadStaticField: " + e.getMessage());
            return null;
        }
    }

    private Object l() {
        try {
            return Class.forName("android.app.ActivityThread").getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivityThreadInActivityThreadStaticMethod: " + e.getMessage());
            return null;
        }
    }

    private void p(Activity activity, boolean z) {
        if (this.f2626d.isEmpty()) {
            return;
        }
        for (w.c cVar : this.f2626d) {
            if (z) {
                cVar.a(activity);
            } else {
                cVar.b(activity);
            }
        }
    }

    private void q(Activity activity, boolean z) {
        try {
            if (z) {
                Window window = activity.getWindow();
                window.getDecorView().setTag(-123, Integer.valueOf(window.getAttributes().softInputMode));
                window.setSoftInputMode(3);
            } else {
                Object tag = activity.getWindow().getDecorView().getTag(-123);
                if (!(tag instanceof Integer)) {
                } else {
                    y.D(new c(activity, tag), 100L);
                }
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(Activity activity, w.a aVar) {
        List<w.a> list = this.e.get(activity);
        if (list == null || list.isEmpty()) {
            return;
        }
        list.remove(aVar);
    }

    private static void u() {
        if (Build.VERSION.SDK_INT < 26 || !ValueAnimator.areAnimatorsEnabled()) {
            try {
                Field declaredField = ValueAnimator.class.getDeclaredField("sDurationScale");
                declaredField.setAccessible(true);
                if (((Float) declaredField.get(null)).floatValue() == 0.0f) {
                    declaredField.set(null, Float.valueOf(1.0f));
                    Log.i("UtilsActivityLifecycle", "setAnimatorsEnabled: Animators are enabled now!");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void v(Activity activity) {
        if (!this.f2625c.contains(activity)) {
            this.f2625c.addFirst(activity);
        } else {
            if (this.f2625c.getFirst().equals(activity)) {
                return;
            }
            this.f2625c.remove(activity);
            this.f2625c.addFirst(activity);
        }
    }

    void c(Activity activity, w.a aVar) {
        if (activity == null || aVar == null) {
            return;
        }
        y.C(new a(activity, aVar));
    }

    void d(w.a aVar) {
        c(f2624b, aVar);
    }

    List<Activity> i() {
        if (!this.f2625c.isEmpty()) {
            return new LinkedList(this.f2625c);
        }
        this.f2625c.addAll(h());
        return new LinkedList(this.f2625c);
    }

    Application m() {
        Object objInvoke;
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object objJ = j();
            if (objJ == null || (objInvoke = cls.getMethod("getApplication", new Class[0]).invoke(objJ, new Object[0])) == null) {
                return null;
            }
            return (Application) objInvoke;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    void n(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    boolean o() {
        return !this.h;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (this.f2625c.size() == 0) {
            p(activity, true);
        }
        m.a(activity);
        u();
        v(activity);
        f(activity, Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        this.f2625c.remove(activity);
        y.e(activity);
        f(activity, Lifecycle.Event.ON_DESTROY);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        f(activity, Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostSaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPrePaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreSaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        v(activity);
        if (this.h) {
            this.h = false;
            p(activity, true);
        }
        q(activity, false);
        f(activity, Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        if (!this.h) {
            v(activity);
        }
        int i = this.g;
        if (i < 0) {
            this.g = i + 1;
        } else {
            this.f++;
        }
        f(activity, Lifecycle.Event.ON_START);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (activity.isChangingConfigurations()) {
            this.g--;
        } else {
            int i = this.f - 1;
            this.f = i;
            if (i <= 0) {
                this.h = true;
                p(activity, false);
            }
        }
        q(activity, true);
        f(activity, Lifecycle.Event.ON_STOP);
    }

    void r(Activity activity, w.a aVar) {
        if (activity == null || aVar == null) {
            return;
        }
        y.C(new b(activity, aVar));
    }

    void s(w.a aVar) {
        r(f2624b, aVar);
    }

    void w(Application application) {
        this.f2625c.clear();
        application.unregisterActivityLifecycleCallbacks(this);
    }
}
