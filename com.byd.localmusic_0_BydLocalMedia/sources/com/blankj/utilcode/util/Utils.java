package com.blankj.utilcode.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes4.dex */
public final class Utils {
    private static Application sApplication;
    private static final ActivityLifecycleImpl ACTIVITY_LIFECYCLE = new ActivityLifecycleImpl();
    private static final ExecutorService UTIL_POOL = ThreadUtils.getCachedPool();
    private static final Handler UTIL_HANDLER = new Handler(Looper.getMainLooper());

    public interface Callback<T> {
        void onCall(T t);
    }

    public interface Func1<Ret, Par> {
        Ret call(Par par);
    }

    public interface OnActivityDestroyedListener {
        void onActivityDestroyed(Activity activity);
    }

    public interface OnAppStatusChangedListener {
        void onBackground(Activity activity);

        void onForeground(Activity activity);
    }

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(Context context) {
        if (context == null) {
            init(getApplicationByReflect());
        } else {
            init((Application) context.getApplicationContext());
        }
    }

    public static void init(Application application) {
        if (sApplication == null) {
            if (application == null) {
                sApplication = getApplicationByReflect();
            } else {
                sApplication = application;
            }
            sApplication.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
            UTIL_POOL.execute(new Runnable() { // from class: com.blankj.utilcode.util.Utils.1
                @Override // java.lang.Runnable
                public void run() {
                    AdaptScreenUtils.preLoad();
                }
            });
            return;
        }
        if (application == null || application.getClass() == sApplication.getClass()) {
            return;
        }
        sApplication.unregisterActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
        ACTIVITY_LIFECYCLE.mActivityList.clear();
        sApplication = application;
        application.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
    }

    public static Application getApp() {
        Application application = sApplication;
        if (application != null) {
            return application;
        }
        Application applicationByReflect = getApplicationByReflect();
        init(applicationByReflect);
        return applicationByReflect;
    }

    static ActivityLifecycleImpl getActivityLifecycle() {
        return ACTIVITY_LIFECYCLE;
    }

    static LinkedList<Activity> getActivityList() {
        return ACTIVITY_LIFECYCLE.mActivityList;
    }

    static Context getTopActivityOrApp() {
        if (isAppForeground()) {
            Activity topActivity = ACTIVITY_LIFECYCLE.getTopActivity();
            return topActivity == null ? getApp() : topActivity;
        }
        return getApp();
    }

    static boolean isAppForeground() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) getApp().getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null && runningAppProcesses.size() != 0) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(getApp().getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }

    static <T> Task<T> doAsync(Task<T> task) {
        UTIL_POOL.execute(task);
        return task;
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            UTIL_HANDLER.post(runnable);
        }
    }

    public static void runOnUiThreadDelayed(Runnable runnable, long j) {
        UTIL_HANDLER.postDelayed(runnable, j);
    }

    static String getCurrentProcessName() {
        String currentProcessNameByFile = getCurrentProcessNameByFile();
        if (!TextUtils.isEmpty(currentProcessNameByFile)) {
            return currentProcessNameByFile;
        }
        String currentProcessNameByAms = getCurrentProcessNameByAms();
        return !TextUtils.isEmpty(currentProcessNameByAms) ? currentProcessNameByAms : getCurrentProcessNameByReflect();
    }

    static void fixSoftInputLeaks(Window window) {
        InputMethodManager inputMethodManager = (InputMethodManager) getApp().getSystemService(Context.INPUT_METHOD_SERVICE);
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

    static SPUtils getSpUtils4Utils() {
        return SPUtils.getInstance("Utils");
    }

    private static String getCurrentProcessNameByFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/" + Process.myPid() + "/cmdline")));
            String strTrim = bufferedReader.readLine().trim();
            bufferedReader.close();
            return strTrim;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getCurrentProcessNameByAms() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) getApp().getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null && runningAppProcesses.size() != 0) {
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid && runningAppProcessInfo.processName != null) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return "";
    }

    private static String getCurrentProcessNameByReflect() {
        try {
            Application app = getApp();
            Field field = app.getClass().getField("mLoadedApk");
            field.setAccessible(true);
            Object obj = field.get(app);
            Field declaredField = obj.getClass().getDeclaredField("mActivityThread");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            return (String) obj2.getClass().getDeclaredMethod("getProcessName", new Class[0]).invoke(obj2, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static Application getApplicationByReflect() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object objInvoke = cls.getMethod("getApplication", new Class[0]).invoke(cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            if (objInvoke == null) {
                throw new NullPointerException("u should init first");
            }
            return (Application) objInvoke;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            throw new NullPointerException("u should init first");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setAnimatorsEnabled() {
        if (Build.VERSION.SDK_INT < 26 || !ValueAnimator.areAnimatorsEnabled()) {
            try {
                Field declaredField = ValueAnimator.class.getDeclaredField("sDurationScale");
                declaredField.setAccessible(true);
                if (((Float) declaredField.get(null)).floatValue() == 0.0f) {
                    declaredField.set(null, Float.valueOf(1.0f));
                    Log.i("Utils", "setAnimatorsEnabled: Animators are enabled now!");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static final class TransActivity extends FragmentActivity {
        private static final Map<TransActivity, TransActivityDelegate> CALLBACK_MAP = new HashMap();
        private static TransActivityDelegate sDelegate;

        public static abstract class TransActivityDelegate {
            public boolean dispatchTouchEvent(Activity activity, MotionEvent motionEvent) {
                return false;
            }

            public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
            }

            public void onCreateBefore(Activity activity, Bundle bundle) {
            }

            public void onCreated(Activity activity, Bundle bundle) {
            }

            public void onDestroy(Activity activity) {
            }

            public void onPaused(Activity activity) {
            }

            public void onRequestPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr) {
            }

            public void onResumed(Activity activity) {
            }

            public void onSaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onStarted(Activity activity) {
            }

            public void onStopped(Activity activity) {
            }
        }

        public static void start(Func1<Void, Intent> func1, TransActivityDelegate transActivityDelegate) {
            if (transActivityDelegate == null) {
                return;
            }
            Intent intent = new Intent(Utils.getApp(), (Class<?>) TransActivity.class);
            intent.addFlags(268435456);
            if (func1 != null) {
                func1.call(intent);
            }
            Utils.getApp().startActivity(intent);
            sDelegate = transActivityDelegate;
        }

        @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
        protected void onCreate(Bundle bundle) {
            overridePendingTransition(0, 0);
            TransActivityDelegate transActivityDelegate = sDelegate;
            if (transActivityDelegate == null) {
                super.onCreate(bundle);
                finish();
                return;
            }
            CALLBACK_MAP.put(this, transActivityDelegate);
            sDelegate.onCreateBefore(this, bundle);
            super.onCreate(bundle);
            sDelegate.onCreated(this, bundle);
            sDelegate = null;
        }

        @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
        protected void onStart() {
            super.onStart();
            TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
            if (transActivityDelegate == null) {
                return;
            }
            transActivityDelegate.onStarted(this);
        }

        @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
        protected void onResume() {
            super.onResume();
            TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
            if (transActivityDelegate == null) {
                return;
            }
            transActivityDelegate.onResumed(this);
        }

        @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
        protected void onPause() {
            overridePendingTransition(0, 0);
            super.onPause();
            TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
            if (transActivityDelegate == null) {
                return;
            }
            transActivityDelegate.onPaused(this);
        }

        @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
        protected void onStop() {
            super.onStop();
            TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
            if (transActivityDelegate == null) {
                return;
            }
            transActivityDelegate.onStopped(this);
        }

        @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
        protected void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
            if (transActivityDelegate == null) {
                return;
            }
            transActivityDelegate.onSaveInstanceState(this, bundle);
        }

        @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
        protected void onDestroy() {
            super.onDestroy();
            TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
            if (transActivityDelegate == null) {
                return;
            }
            transActivityDelegate.onDestroy(this);
            CALLBACK_MAP.remove(this);
        }

        @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
        public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
            super.onRequestPermissionsResult(i, strArr, iArr);
            TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
            if (transActivityDelegate == null) {
                return;
            }
            transActivityDelegate.onRequestPermissionsResult(this, i, strArr, iArr);
        }

        @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
        protected void onActivityResult(int i, int i2, Intent intent) {
            super.onActivityResult(i, i2, intent);
            TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
            if (transActivityDelegate == null) {
                return;
            }
            transActivityDelegate.onActivityResult(this, i, i2, intent);
        }

        @Override // android.app.Activity, android.view.Window.Callback
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            TransActivityDelegate transActivityDelegate = CALLBACK_MAP.get(this);
            if (transActivityDelegate == null) {
                return super.dispatchTouchEvent(motionEvent);
            }
            if (transActivityDelegate.dispatchTouchEvent(this, motionEvent)) {
                return true;
            }
            return super.dispatchTouchEvent(motionEvent);
        }
    }

    static class ActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {
        final LinkedList<Activity> mActivityList = new LinkedList<>();
        final List<OnAppStatusChangedListener> mStatusListeners = new ArrayList();
        final Map<Activity, List<OnActivityDestroyedListener>> mDestroyedListenerMap = new HashMap();
        private int mForegroundCount = 0;
        private int mConfigCount = 0;
        private boolean mIsBackground = false;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        ActivityLifecycleImpl() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            LanguageUtils.applyLanguage(activity);
            Utils.setAnimatorsEnabled();
            setTopActivity(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (!this.mIsBackground) {
                setTopActivity(activity);
            }
            int i = this.mConfigCount;
            if (i < 0) {
                this.mConfigCount = i + 1;
            } else {
                this.mForegroundCount++;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            setTopActivity(activity);
            if (this.mIsBackground) {
                this.mIsBackground = false;
                postStatus(activity, true);
            }
            processHideSoftInputOnActivityDestroy(activity, false);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (activity.isChangingConfigurations()) {
                this.mConfigCount--;
            } else {
                int i = this.mForegroundCount - 1;
                this.mForegroundCount = i;
                if (i <= 0) {
                    this.mIsBackground = true;
                    postStatus(activity, false);
                }
            }
            processHideSoftInputOnActivityDestroy(activity, true);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            this.mActivityList.remove(activity);
            consumeOnActivityDestroyedListener(activity);
            Utils.fixSoftInputLeaks(activity.getWindow());
        }

        Activity getTopActivity() {
            if (!this.mActivityList.isEmpty()) {
                for (int size = this.mActivityList.size() - 1; size >= 0; size--) {
                    Activity activity = this.mActivityList.get(size);
                    if (activity != null && !activity.isFinishing() && (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed())) {
                        return activity;
                    }
                }
            }
            Activity topActivityByReflect = getTopActivityByReflect();
            if (topActivityByReflect != null) {
                setTopActivity(topActivityByReflect);
            }
            return topActivityByReflect;
        }

        void addOnAppStatusChangedListener(OnAppStatusChangedListener onAppStatusChangedListener) {
            this.mStatusListeners.add(onAppStatusChangedListener);
        }

        void removeOnAppStatusChangedListener(OnAppStatusChangedListener onAppStatusChangedListener) {
            this.mStatusListeners.remove(onAppStatusChangedListener);
        }

        void removeOnActivityDestroyedListener(Activity activity) {
            if (activity == null) {
                return;
            }
            this.mDestroyedListenerMap.remove(activity);
        }

        void addOnActivityDestroyedListener(Activity activity, OnActivityDestroyedListener onActivityDestroyedListener) {
            if (activity == null || onActivityDestroyedListener == null) {
                return;
            }
            List<OnActivityDestroyedListener> copyOnWriteArrayList = this.mDestroyedListenerMap.get(activity);
            if (copyOnWriteArrayList == null) {
                copyOnWriteArrayList = new CopyOnWriteArrayList<>();
                this.mDestroyedListenerMap.put(activity, copyOnWriteArrayList);
            } else if (copyOnWriteArrayList.contains(onActivityDestroyedListener)) {
                return;
            }
            copyOnWriteArrayList.add(onActivityDestroyedListener);
        }

        private void processHideSoftInputOnActivityDestroy(final Activity activity, boolean z) {
            if (z) {
                activity.getWindow().getDecorView().setTag(PackageManager.INSTALL_PARSE_FAILED_ONLY_COREAPP_ALLOWED, Integer.valueOf(activity.getWindow().getAttributes().softInputMode));
                activity.getWindow().setSoftInputMode(3);
            } else {
                final Object tag = activity.getWindow().getDecorView().getTag(PackageManager.INSTALL_PARSE_FAILED_ONLY_COREAPP_ALLOWED);
                if (tag instanceof Integer) {
                    Utils.runOnUiThreadDelayed(new Runnable() { // from class: com.blankj.utilcode.util.Utils.ActivityLifecycleImpl.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Window window = activity.getWindow();
                            if (window != null) {
                                window.setSoftInputMode(((Integer) tag).intValue());
                            }
                        }
                    }, 100L);
                }
            }
        }

        private void postStatus(Activity activity, boolean z) {
            if (this.mStatusListeners.isEmpty()) {
                return;
            }
            for (OnAppStatusChangedListener onAppStatusChangedListener : this.mStatusListeners) {
                if (z) {
                    onAppStatusChangedListener.onForeground(activity);
                } else {
                    onAppStatusChangedListener.onBackground(activity);
                }
            }
        }

        private void setTopActivity(Activity activity) {
            if (this.mActivityList.contains(activity)) {
                if (this.mActivityList.getLast().equals(activity)) {
                    return;
                }
                this.mActivityList.remove(activity);
                this.mActivityList.addLast(activity);
                return;
            }
            this.mActivityList.addLast(activity);
        }

        private void consumeOnActivityDestroyedListener(Activity activity) {
            Iterator<Map.Entry<Activity, List<OnActivityDestroyedListener>>> it = this.mDestroyedListenerMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Activity, List<OnActivityDestroyedListener>> next = it.next();
                if (next.getKey() == activity) {
                    Iterator<OnActivityDestroyedListener> it2 = next.getValue().iterator();
                    while (it2.hasNext()) {
                        it2.next().onActivityDestroyed(activity);
                    }
                    it.remove();
                }
            }
        }

        private Activity getTopActivityByReflect() {
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Object objInvoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
                Field declaredField = cls.getDeclaredField("mActivityList");
                declaredField.setAccessible(true);
                Map map = (Map) declaredField.get(objInvoke);
                if (map == null) {
                    return null;
                }
                for (Object obj : map.values()) {
                    Class<?> cls2 = obj.getClass();
                    Field declaredField2 = cls2.getDeclaredField("paused");
                    declaredField2.setAccessible(true);
                    if (!declaredField2.getBoolean(obj)) {
                        Field declaredField3 = cls2.getDeclaredField(Context.ACTIVITY_SERVICE);
                        declaredField3.setAccessible(true);
                        return (Activity) declaredField3.get(obj);
                    }
                }
            } catch (Exception e) {
                Log.e("Utils", e.getMessage());
            }
            return null;
        }
    }

    public static final class FileProvider4UtilCode extends FileProvider {
        @Override // androidx.core.content.FileProvider, android.content.ContentProvider
        public boolean onCreate() {
            Utils.init(getContext());
            try {
                Class.forName("com.blankj.utildebug.DebugUtils");
                return true;
            } catch (Exception unused) {
                return true;
            }
        }
    }

    public static abstract class Task<Result> implements Runnable {
        private static final int CANCELLED = 2;
        private static final int COMPLETING = 1;
        private static final int EXCEPTIONAL = 3;
        private static final int NEW = 0;
        private Callback<Result> mCallback;
        private volatile int state = 0;

        abstract Result doInBackground();

        public Task(Callback<Result> callback) {
            this.mCallback = callback;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                final Result resultDoInBackground = doInBackground();
                if (this.state != 0) {
                    return;
                }
                this.state = 1;
                Utils.UTIL_HANDLER.post(new Runnable() { // from class: com.blankj.utilcode.util.Utils.Task.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Task.this.mCallback.onCall(resultDoInBackground);
                    }
                });
            } catch (Throwable unused) {
                if (this.state != 0) {
                    return;
                }
                this.state = 3;
            }
        }

        public void cancel() {
            this.state = 2;
        }

        public boolean isDone() {
            return this.state != 0;
        }

        public boolean isCanceled() {
            return this.state == 2;
        }
    }
}
