package com.amazon.music.media.playback.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.amazon.music.media.playback.util.ListenerSet;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AndroidUtils {

    @SuppressLint({"StaticFieldLeak"})
    private static Application application;
    private static boolean configurationChanging;
    private static final Logger logger = LoggerFactory.getLogger(AndroidUtils.class.getSimpleName());
    private static final Object lock = new Object();
    private static List<Activity> activities = new ArrayList();
    private static final ListenerSet<ForegroundListener> listeners = new ListenerSet<>(ForegroundListener.class, ListenerSet.DispatchStrategy.MAIN_THREAD, ListenerSet.DuplicateStrategy.REMOVE_ALL_OTHERS);

    public interface ForegroundListener {
        void onApplicationInForegroundChanged(boolean z);
    }

    public static void init(Context context) {
        synchronized (lock) {
            if (application != null) {
                return;
            }
            if (context instanceof Application) {
                application = (Application) context;
            } else if (context instanceof Activity) {
                application = ((Activity) context).getApplication();
            } else if (context instanceof Service) {
                application = ((Service) context).getApplication();
            } else {
                application = (Application) context.getApplicationContext();
            }
            application.registerActivityLifecycleCallbacks(new LifecycleCallbacks());
        }
    }

    public static boolean isApplicationInForeground() {
        return configurationChanging || !activities.isEmpty();
    }

    public static Activity getCurrentActivity() {
        int size = activities.size();
        if (size == 0) {
            return null;
        }
        return activities.get(size - 1);
    }

    public static Activity getPreviousActivity() {
        int size = activities.size();
        if (size < 2) {
            return null;
        }
        return activities.get(size - 2);
    }

    public static Fragment getVisibleFragment(Activity activity) {
        if (!(activity instanceof AppCompatActivity)) {
            return null;
        }
        for (Fragment fragment : ((AppCompatActivity) activity).getSupportFragmentManager().getFragments()) {
            if (fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }

    private static class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        private LifecycleCallbacks() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (AndroidUtils.activities.contains(activity)) {
                AndroidUtils.logger.warn("Activity already started - this shouldn't happen!");
                return;
            }
            AndroidUtils.activities.add(activity);
            if (AndroidUtils.configurationChanging) {
                boolean unused = AndroidUtils.configurationChanging = false;
            } else if (AndroidUtils.activities.size() == 1) {
                AndroidUtils.logger.info("Application entering the foreground.");
                ((ForegroundListener) AndroidUtils.listeners.proxy()).onApplicationInForegroundChanged(true);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (!AndroidUtils.activities.remove(activity)) {
                AndroidUtils.logger.warn("Activity stopped that was not previously started - make sure to call init() in Application.onStart().");
                return;
            }
            if (AndroidUtils.configurationChanging) {
                return;
            }
            boolean unused = AndroidUtils.configurationChanging = activity.isChangingConfigurations();
            if (AndroidUtils.configurationChanging) {
                AndroidUtils.logger.info("Activity configuration changing.");
            } else if (AndroidUtils.activities.isEmpty()) {
                AndroidUtils.logger.info("Application going into the background.");
                ((ForegroundListener) AndroidUtils.listeners.proxy()).onApplicationInForegroundChanged(false);
            }
        }
    }

    public static void addForegroundListener(ForegroundListener foregroundListener) {
        listeners.add(foregroundListener);
    }

    public static void removeForegroundListener(ForegroundListener foregroundListener) {
        listeners.remove(foregroundListener);
    }

    private AndroidUtils() {
    }
}
