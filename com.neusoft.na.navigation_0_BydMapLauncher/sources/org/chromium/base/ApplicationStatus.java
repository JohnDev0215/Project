package org.chromium.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.chromium.base.ApplicationStatusManager;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("base::android")
public class ApplicationStatus {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Activity sActivity;
    private static Integer sCachedApplicationState;
    private static ApplicationStateListener sNativeApplicationStateListener;
    private static Object sCachedApplicationStateLock = new Object();
    private static final Map<Activity, ActivityInfo> sActivityInfo = new ConcurrentHashMap();
    private static final ObserverList<ActivityStateListener> sGeneralActivityStateListeners = new ObserverList<>();
    private static final ObserverList<ApplicationStateListener> sApplicationStateListeners = new ObserverList<>();

    public interface ActivityStateListener {
        void onActivityStateChange(Activity activity, int i);
    }

    public interface ApplicationStateListener {
        void onApplicationStateChange(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeOnApplicationStateChange(int i);

    private static class ActivityInfo {
        private ObserverList<ActivityStateListener> mListeners;
        private int mStatus;

        private ActivityInfo() {
            this.mStatus = 6;
            this.mListeners = new ObserverList<>();
        }

        public int getStatus() {
            return this.mStatus;
        }

        public void setStatus(int i) {
            this.mStatus = i;
        }

        public ObserverList<ActivityStateListener> getListeners() {
            return this.mListeners;
        }
    }

    private ApplicationStatus() {
    }

    public static void initialize(Application application) {
        ApplicationStatusManager.registerWindowFocusChangedListener(new ApplicationStatusManager.WindowFocusChangedListener() { // from class: org.chromium.base.ApplicationStatus.1
            @Override // org.chromium.base.ApplicationStatusManager.WindowFocusChangedListener
            public void onWindowFocusChanged(Activity activity, boolean z) {
                int stateForActivity;
                if (!z || activity == ApplicationStatus.sActivity || (stateForActivity = ApplicationStatus.getStateForActivity(activity)) == 6 || stateForActivity == 5) {
                    return;
                }
                Activity unused = ApplicationStatus.sActivity = activity;
            }
        });
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: org.chromium.base.ApplicationStatus.2
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
                ApplicationStatus.onStateChange(activity, 1);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                ApplicationStatus.onStateChange(activity, 6);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                ApplicationStatus.onStateChange(activity, 4);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                ApplicationStatus.onStateChange(activity, 3);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                ApplicationStatus.onStateChange(activity, 2);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                ApplicationStatus.onStateChange(activity, 5);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onStateChange(Activity activity, int i) {
        if (activity == null) {
            throw new IllegalArgumentException("null activity is not supported");
        }
        if (sActivity == null || i == 1 || i == 3 || i == 2) {
            sActivity = activity;
        }
        int stateForApplication = getStateForApplication();
        if (i == 1) {
            sActivityInfo.put(activity, new ActivityInfo());
        }
        synchronized (sCachedApplicationStateLock) {
            sCachedApplicationState = null;
        }
        ActivityInfo activityInfo = sActivityInfo.get(activity);
        if (activityInfo == null) {
            return;
        }
        activityInfo.setStatus(i);
        Iterator<ActivityStateListener> it = activityInfo.getListeners().iterator();
        while (it.hasNext()) {
            it.next().onActivityStateChange(activity, i);
        }
        Iterator<ActivityStateListener> it2 = sGeneralActivityStateListeners.iterator();
        while (it2.hasNext()) {
            it2.next().onActivityStateChange(activity, i);
        }
        int stateForApplication2 = getStateForApplication();
        if (stateForApplication2 != stateForApplication) {
            Iterator<ApplicationStateListener> it3 = sApplicationStateListeners.iterator();
            while (it3.hasNext()) {
                it3.next().onApplicationStateChange(stateForApplication2);
            }
        }
        if (i == 6) {
            sActivityInfo.remove(activity);
            if (activity == sActivity) {
                sActivity = null;
            }
        }
    }

    public static void onStateChangeForTesting(Activity activity, int i) {
        onStateChange(activity, i);
    }

    public static Activity getLastTrackedFocusedActivity() {
        return sActivity;
    }

    public static List<WeakReference<Activity>> getRunningActivities() {
        ArrayList arrayList = new ArrayList();
        Iterator<Activity> it = sActivityInfo.keySet().iterator();
        while (it.hasNext()) {
            arrayList.add(new WeakReference(it.next()));
        }
        return arrayList;
    }

    public static int getStateForActivity(Activity activity) {
        ActivityInfo activityInfo = sActivityInfo.get(activity);
        if (activityInfo != null) {
            return activityInfo.getStatus();
        }
        return 6;
    }

    public static int getStateForApplication() {
        int iIntValue;
        synchronized (sCachedApplicationStateLock) {
            if (sCachedApplicationState == null) {
                sCachedApplicationState = Integer.valueOf(determineApplicationState());
            }
            iIntValue = sCachedApplicationState.intValue();
        }
        return iIntValue;
    }

    public static boolean hasVisibleActivities() {
        int stateForApplication = getStateForApplication();
        return stateForApplication == 1 || stateForApplication == 2;
    }

    public static boolean isEveryActivityDestroyed() {
        return sActivityInfo.isEmpty();
    }

    public static void registerStateListenerForAllActivities(ActivityStateListener activityStateListener) {
        sGeneralActivityStateListeners.addObserver(activityStateListener);
    }

    public static void registerStateListenerForActivity(ActivityStateListener activityStateListener, Activity activity) {
        sActivityInfo.get(activity).getListeners().addObserver(activityStateListener);
    }

    public static void unregisterActivityStateListener(ActivityStateListener activityStateListener) {
        sGeneralActivityStateListeners.removeObserver(activityStateListener);
        Iterator<ActivityInfo> it = sActivityInfo.values().iterator();
        while (it.hasNext()) {
            it.next().getListeners().removeObserver(activityStateListener);
        }
    }

    public static void registerApplicationStateListener(ApplicationStateListener applicationStateListener) {
        sApplicationStateListeners.addObserver(applicationStateListener);
    }

    public static void unregisterApplicationStateListener(ApplicationStateListener applicationStateListener) {
        sApplicationStateListeners.removeObserver(applicationStateListener);
    }

    public static void informActivityStarted(Activity activity) {
        onStateChange(activity, 1);
        onStateChange(activity, 2);
        onStateChange(activity, 3);
    }

    public static void destroyForJUnitTests() {
        sApplicationStateListeners.clear();
        sGeneralActivityStateListeners.clear();
        sActivityInfo.clear();
        synchronized (sCachedApplicationStateLock) {
            sCachedApplicationState = null;
        }
        sActivity = null;
        sNativeApplicationStateListener = null;
    }

    private static void registerThreadSafeNativeApplicationStateListener() {
        ThreadUtils.runOnUiThread(new Runnable() { // from class: org.chromium.base.ApplicationStatus.3
            @Override // java.lang.Runnable
            public void run() {
                if (ApplicationStatus.sNativeApplicationStateListener != null) {
                    return;
                }
                ApplicationStateListener unused = ApplicationStatus.sNativeApplicationStateListener = new ApplicationStateListener() { // from class: org.chromium.base.ApplicationStatus.3.1
                    @Override // org.chromium.base.ApplicationStatus.ApplicationStateListener
                    public void onApplicationStateChange(int i) {
                        ApplicationStatus.nativeOnApplicationStateChange(i);
                    }
                };
                ApplicationStatus.registerApplicationStateListener(ApplicationStatus.sNativeApplicationStateListener);
            }
        });
    }

    private static int determineApplicationState() {
        Iterator<ActivityInfo> it = sActivityInfo.values().iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            int status = it.next().getStatus();
            if (status != 4 && status != 5 && status != 6) {
                return 1;
            }
            if (status == 4) {
                z = true;
            } else if (status == 5) {
                z2 = true;
            }
        }
        if (z) {
            return 2;
        }
        return z2 ? 3 : 4;
    }
}
