package b.c.a.h.d;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f1780a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Handler f1781b = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    public static Application f1782c;

    public static class a implements Application.ActivityLifecycleCallbacks {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final LinkedList<Activity> f1783a = new LinkedList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List<c> f1784b = new ArrayList();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Map<Activity, List<InterfaceC0035b>> f1785c = new HashMap();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1786d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1787e = 0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1788f = false;

        /* JADX INFO: renamed from: b.c.a.h.d.b$a$a, reason: collision with other inner class name */
        public class RunnableC0034a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Activity f1789a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Object f1790b;

            public RunnableC0034a(a aVar, Activity activity, Object obj) {
                this.f1789a = activity;
                this.f1790b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                Window window = this.f1789a.getWindow();
                if (window != null) {
                    window.setSoftInputMode(((Integer) this.f1790b).intValue());
                }
            }
        }

        public final void a(Activity activity, boolean z) {
            if (this.f1784b.isEmpty()) {
                return;
            }
            for (c cVar : this.f1784b) {
                if (z) {
                    cVar.a(activity);
                } else {
                    cVar.b(activity);
                }
            }
        }

        public final void b(Activity activity, boolean z) {
            if (z) {
                activity.getWindow().getDecorView().setTag(-123, Integer.valueOf(activity.getWindow().getAttributes().softInputMode));
                activity.getWindow().setSoftInputMode(3);
            } else {
                Object tag = activity.getWindow().getDecorView().getTag(-123);
                if (tag instanceof Integer) {
                    b.f1781b.postDelayed(new RunnableC0034a(this, activity, tag), 100L);
                }
            }
        }

        public final void c(Activity activity) {
            if (this.f1783a.contains(activity)) {
                if (this.f1783a.getLast().equals(activity)) {
                    return;
                } else {
                    this.f1783a.remove(activity);
                }
            }
            this.f1783a.addLast(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (!ValueAnimator.areAnimatorsEnabled()) {
                try {
                    Field declaredField = ValueAnimator.class.getDeclaredField("sDurationScale");
                    declaredField.setAccessible(true);
                    if (((Float) declaredField.get(null)).floatValue() == 0.0f) {
                        declaredField.set(null, Float.valueOf(1.0f));
                        Log.i("Utils", "setAnimatorsEnabled: Animators are enabled now!");
                    }
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                } catch (NoSuchFieldException e3) {
                    e3.printStackTrace();
                }
            }
            c(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            this.f1783a.remove(activity);
            Iterator<Map.Entry<Activity, List<InterfaceC0035b>>> it = this.f1785c.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Activity, List<InterfaceC0035b>> next = it.next();
                if (next.getKey() == activity) {
                    Iterator<InterfaceC0035b> it2 = next.getValue().iterator();
                    while (it2.hasNext()) {
                        it2.next().onActivityDestroyed(activity);
                    }
                    it.remove();
                }
            }
            Window window = activity.getWindow();
            InputMethodManager inputMethodManager = (InputMethodManager) b.a().getSystemService("input_method");
            if (inputMethodManager == null) {
                return;
            }
            String[] strArr = {"mLastSrvView", "mCurRootView", "mServedView", "mNextServedView"};
            for (int i = 0; i < 4; i++) {
                try {
                    Field declaredField = InputMethodManager.class.getDeclaredField(strArr[i]);
                    if (!declaredField.isAccessible()) {
                        declaredField.setAccessible(true);
                    }
                    Object obj = declaredField.get(inputMethodManager);
                    if ((obj instanceof View) && ((View) obj).getRootView() == window.getDecorView().getRootView()) {
                        declaredField.set(inputMethodManager, null);
                    }
                } catch (Throwable unused) {
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            c(activity);
            if (this.f1788f) {
                this.f1788f = false;
                a(activity, true);
            }
            b(activity, false);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (!this.f1788f) {
                c(activity);
            }
            int i = this.f1787e;
            if (i < 0) {
                this.f1787e = i + 1;
            } else {
                this.f1786d++;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (activity.isChangingConfigurations()) {
                this.f1787e--;
            } else {
                int i = this.f1786d - 1;
                this.f1786d = i;
                if (i <= 0) {
                    this.f1788f = true;
                    a(activity, false);
                }
            }
            b(activity, true);
        }
    }

    /* JADX INFO: renamed from: b.c.a.h.d.b$b, reason: collision with other inner class name */
    public interface InterfaceC0035b {
        void onActivityDestroyed(Activity activity);
    }

    public interface c {
        void a(Activity activity);

        void b(Activity activity);
    }

    public static Application a() {
        Application application = f1782c;
        if (application != null) {
            return application;
        }
        Application applicationB = b();
        if (f1782c == null) {
            f1782c = applicationB;
            applicationB.registerActivityLifecycleCallbacks(f1780a);
        } else if (applicationB.getClass() != f1782c.getClass()) {
            f1782c.unregisterActivityLifecycleCallbacks(f1780a);
            f1780a.f1783a.clear();
            f1782c = applicationB;
            applicationB.registerActivityLifecycleCallbacks(f1780a);
        }
        return applicationB;
    }

    public static Application b() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object objInvoke = cls.getMethod("getApplication", new Class[0]).invoke(cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            if (objInvoke != null) {
                return (Application) objInvoke;
            }
            throw new NullPointerException("u should init first");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            throw new NullPointerException("u should init first");
        }
    }
}
