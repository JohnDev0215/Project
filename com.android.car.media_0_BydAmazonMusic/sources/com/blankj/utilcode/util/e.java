package com.blankj.utilcode.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.lifecycle.Lifecycle;
import com.blankj.utilcode.util.Utils;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: UtilsActivityLifecycleImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e implements Application.ActivityLifecycleCallbacks {
    public static final e g = new e();
    public static final Activity h = new Activity();
    public final LinkedList<Activity> a = new LinkedList<>();
    public final List<Utils.OnAppStatusChangedListener> b = new CopyOnWriteArrayList();
    public final Map<Activity, List<Utils.ActivityLifecycleCallbacks>> c = new ConcurrentHashMap();
    public int d = 0;
    public int e = 0;
    public boolean f = false;

    /* JADX INFO: compiled from: UtilsActivityLifecycleImpl.java */
    public class a implements Runnable {
        public final /* synthetic */ Activity a;
        public final /* synthetic */ Object b;

        public a(e eVar, Activity activity, Object obj) {
            this.a = activity;
            this.b = obj;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                Window window = this.a.getWindow();
                if (window != null) {
                    window.setSoftInputMode(((Integer) this.b).intValue());
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map<android.app.Activity, java.util.List<com.blankj.utilcode.util.Utils$ActivityLifecycleCallbacks>>, java.util.concurrent.ConcurrentHashMap] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.Map<android.app.Activity, java.util.List<com.blankj.utilcode.util.Utils$ActivityLifecycleCallbacks>>, java.util.concurrent.ConcurrentHashMap] */
    public final void a(Activity activity, Lifecycle.Event event) {
        b(activity, event, (List) this.c.get(activity));
        b(activity, event, (List) this.c.get(h));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List<com.blankj.utilcode.util.Utils$OnAppStatusChangedListener>, java.util.concurrent.CopyOnWriteArrayList] */
    public void addOnAppStatusChangedListener(Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        this.b.add(onAppStatusChangedListener);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.Map<android.app.Activity, java.util.List<com.blankj.utilcode.util.Utils$ActivityLifecycleCallbacks>>, java.util.concurrent.ConcurrentHashMap] */
    public final void b(Activity activity, Lifecycle.Event event, List<Utils.ActivityLifecycleCallbacks> list) {
        if (list == null) {
            return;
        }
        for (Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks : list) {
            activityLifecycleCallbacks.onLifecycleChanged(activity, event);
            if (event.equals(Lifecycle.Event.ON_CREATE)) {
                activityLifecycleCallbacks.onActivityCreated(activity);
            } else if (event.equals(Lifecycle.Event.ON_START)) {
                activityLifecycleCallbacks.onActivityStarted(activity);
            } else if (event.equals(Lifecycle.Event.ON_RESUME)) {
                activityLifecycleCallbacks.onActivityResumed(activity);
            } else if (event.equals(Lifecycle.Event.ON_PAUSE)) {
                activityLifecycleCallbacks.onActivityPaused(activity);
            } else if (event.equals(Lifecycle.Event.ON_STOP)) {
                activityLifecycleCallbacks.onActivityStopped(activity);
            } else if (event.equals(Lifecycle.Event.ON_DESTROY)) {
                activityLifecycleCallbacks.onActivityDestroyed(activity);
            }
        }
        if (event.equals(Lifecycle.Event.ON_DESTROY)) {
            this.c.remove(activity);
        }
    }

    public final List<Activity> c() {
        Object objD;
        if (!this.a.isEmpty()) {
            return new LinkedList(this.a);
        }
        LinkedList linkedList = new LinkedList();
        Activity activity = null;
        try {
            objD = d();
        } catch (Exception e) {
            StringBuilder sbK = spi.c.k("getActivitiesByReflect: ");
            sbK.append(e.getMessage());
            Log.e("UtilsActivityLifecycle", sbK.toString());
        }
        if (objD != null) {
            Field declaredField = objD.getClass().getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(objD);
            if (obj instanceof Map) {
                for (Object obj2 : ((Map) obj).values()) {
                    Class<?> cls = obj2.getClass();
                    Field declaredField2 = cls.getDeclaredField(ActivityChooserModel.ATTRIBUTE_ACTIVITY);
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
            }
            if (activity != null) {
                linkedList.addFirst(activity);
            }
        }
        this.a.addAll(linkedList);
        return new LinkedList(this.a);
    }

    public final Object d() {
        Object obj;
        try {
            Field declaredField = Class.forName("android.app.ActivityThread").getDeclaredField("sCurrentActivityThread");
            declaredField.setAccessible(true);
            obj = declaredField.get(null);
        } catch (Exception e) {
            StringBuilder sbK = spi.c.k("getActivityThreadInActivityThreadStaticField: ");
            sbK.append(e.getMessage());
            Log.e("UtilsActivityLifecycle", sbK.toString());
            obj = null;
        }
        if (obj != null) {
            return obj;
        }
        try {
            return Class.forName("android.app.ActivityThread").getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e2) {
            StringBuilder sbK2 = spi.c.k("getActivityThreadInActivityThreadStaticMethod: ");
            sbK2.append(e2.getMessage());
            Log.e("UtilsActivityLifecycle", sbK2.toString());
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.blankj.utilcode.util.Utils$OnAppStatusChangedListener>, java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List<com.blankj.utilcode.util.Utils$OnAppStatusChangedListener>, java.util.concurrent.CopyOnWriteArrayList] */
    public final void e(Activity activity, boolean z) {
        if (this.b.isEmpty()) {
            return;
        }
        for (Utils.OnAppStatusChangedListener onAppStatusChangedListener : this.b) {
            if (z) {
                onAppStatusChangedListener.onForeground(activity);
            } else {
                onAppStatusChangedListener.onBackground(activity);
            }
        }
    }

    public final void f(Activity activity, boolean z) {
        try {
            if (z) {
                Window window = activity.getWindow();
                window.getDecorView().setTag(-123, Integer.valueOf(window.getAttributes().softInputMode));
                window.setSoftInputMode(3);
            } else {
                Object tag = activity.getWindow().getDecorView().getTag(-123);
                if (!(tag instanceof Integer)) {
                } else {
                    ThreadUtils.runOnUiThreadDelayed(new a(this, activity, tag), 100L);
                }
            }
        } catch (Exception unused) {
        }
    }

    public final void g(Activity activity) {
        if (!this.a.contains(activity)) {
            this.a.addFirst(activity);
        } else {
            if (this.a.getFirst().equals(activity)) {
                return;
            }
            this.a.remove(activity);
            this.a.addFirst(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(@NonNull Activity activity, Bundle bundle) {
        if (this.a.size() == 0) {
            e(activity, true);
        }
        LanguageUtils.applyLanguage(activity);
        if (!ValueAnimator.areAnimatorsEnabled()) {
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
        g(activity);
        a(activity, Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(@NonNull Activity activity) {
        this.a.remove(activity);
        KeyboardUtils.fixSoftInputLeaks(activity);
        a(activity, Lifecycle.Event.ON_DESTROY);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(@NonNull Activity activity) {
        a(activity, Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostDestroyed(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostPaused(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostResumed(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostSaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostStarted(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostStopped(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPreDestroyed(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPrePaused(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPreResumed(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPreSaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPreStarted(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPreStopped(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(@NonNull Activity activity) {
        g(activity);
        if (this.f) {
            this.f = false;
            e(activity, true);
        }
        f(activity, false);
        a(activity, Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(@NonNull Activity activity) {
        if (!this.f) {
            g(activity);
        }
        int i = this.e;
        if (i < 0) {
            this.e = i + 1;
        } else {
            this.d++;
        }
        a(activity, Lifecycle.Event.ON_START);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        if (activity.isChangingConfigurations()) {
            this.e--;
        } else {
            int i = this.d - 1;
            this.d = i;
            if (i <= 0) {
                this.f = true;
                e(activity, false);
            }
        }
        f(activity, true);
        a(activity, Lifecycle.Event.ON_STOP);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List<com.blankj.utilcode.util.Utils$OnAppStatusChangedListener>, java.util.concurrent.CopyOnWriteArrayList] */
    public void removeOnAppStatusChangedListener(Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        this.b.remove(onAppStatusChangedListener);
    }
}
