package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import com.blankj.utilcode.util.ThreadUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class Utils {

    @SuppressLint({"StaticFieldLeak"})
    private static Application sApp;

    public static class ActivityLifecycleCallbacks {
        public void onActivityCreated(@NonNull Activity activity) {
        }

        public void onActivityDestroyed(@NonNull Activity activity) {
        }

        public void onActivityPaused(@NonNull Activity activity) {
        }

        public void onActivityResumed(@NonNull Activity activity) {
        }

        public void onActivityStarted(@NonNull Activity activity) {
        }

        public void onActivityStopped(@NonNull Activity activity) {
        }

        public void onLifecycleChanged(@NonNull Activity activity, Lifecycle.Event event) {
        }
    }

    public interface Consumer<T> {
        void accept(T t);
    }

    public interface Func1<Ret, Par> {
        Ret call(Par par);
    }

    public interface OnAppStatusChangedListener {
        void onBackground(Activity activity);

        void onForeground(Activity activity);
    }

    public interface Supplier<T> {
        T get();
    }

    public static abstract class Task<Result> extends ThreadUtils.SimpleTask<Result> {
        private Consumer<Result> mConsumer;

        public Task(Consumer<Result> consumer) {
            this.mConsumer = consumer;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        public void onSuccess(Result result) {
            Consumer<Result> consumer = this.mConsumer;
            if (consumer != null) {
                consumer.accept(result);
            }
        }
    }

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Application getApp() {
        Object objInvoke;
        Application application = sApp;
        if (application != null) {
            return application;
        }
        e eVar = e.g;
        Objects.requireNonNull(eVar);
        Application application2 = null;
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object objD = eVar.d();
            if (objD != null && (objInvoke = cls.getMethod("getApplication", new Class[0]).invoke(objD, new Object[0])) != null) {
                application2 = (Application) objInvoke;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        init(application2);
        Objects.requireNonNull(sApp, "reflect failed.");
        Log.i("Utils", ProcessUtils.getCurrentProcessName() + " reflect app success.");
        return sApp;
    }

    public static void init(Application application) {
        if (application == null) {
            Log.e("Utils", "app is null.");
            return;
        }
        Application application2 = sApp;
        if (application2 == null) {
            sApp = application;
            Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = e.g;
            Objects.requireNonNull(activityLifecycleCallbacks);
            application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
            Runnable[] runnableArr = {AdaptScreenUtils.getPreLoadRunnable()};
            for (int i = 0; i < 1; i++) {
                ThreadUtils.getCachedPool().execute(runnableArr[i]);
            }
            return;
        }
        if (application2.equals(application)) {
            return;
        }
        Application application3 = sApp;
        e eVar = e.g;
        eVar.a.clear();
        application3.unregisterActivityLifecycleCallbacks(eVar);
        sApp = application;
        Objects.requireNonNull(eVar);
        application.registerActivityLifecycleCallbacks(eVar);
    }
}
