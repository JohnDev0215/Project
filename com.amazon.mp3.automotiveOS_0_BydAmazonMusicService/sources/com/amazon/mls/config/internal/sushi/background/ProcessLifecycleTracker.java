package com.amazon.mls.config.internal.sushi.background;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class ProcessLifecycleTracker {
    private static ProcessLifecycleTracker sInstance;
    private final Runnable taskRealGoingToBackgroundDetected;
    private int resumedCounter = 0;
    private boolean backgroundMessageSent = true;
    private final CopyOnWriteArrayList<LifecycleListener> lifecycleListeners = new CopyOnWriteArrayList<>();
    private final Handler mainThreadLooperHandler = new Handler(Looper.getMainLooper());

    public interface LifecycleListener {
        void onBackground();

        void onForeground();
    }

    private ProcessLifecycleTracker(Application application) {
        application.registerActivityLifecycleCallbacks(new EmptyActivityLifecycleCallback() { // from class: com.amazon.mls.config.internal.sushi.background.ProcessLifecycleTracker.1
            @Override // com.amazon.mls.config.internal.sushi.background.EmptyActivityLifecycleCallback, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                ProcessLifecycleTracker.this.activityResumed();
            }

            @Override // com.amazon.mls.config.internal.sushi.background.EmptyActivityLifecycleCallback, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                ProcessLifecycleTracker.this.activityPaused();
            }
        });
        this.taskRealGoingToBackgroundDetected = new Runnable() { // from class: com.amazon.mls.config.internal.sushi.background.ProcessLifecycleTracker.2
            @Override // java.lang.Runnable
            public void run() {
                ProcessLifecycleTracker.this.dispatchPauseIfNeeded();
            }
        };
    }

    public static synchronized void init(Application application) {
        if (sInstance == null) {
            sInstance = new ProcessLifecycleTracker(application);
        }
    }

    private void notifyListener(LifecycleListener lifecycleListener, boolean z) {
        if (z) {
            lifecycleListener.onForeground();
        } else {
            lifecycleListener.onBackground();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activityResumed() {
        int i = this.resumedCounter + 1;
        this.resumedCounter = i;
        if (i == 1) {
            if (this.backgroundMessageSent) {
                handleNotification(true);
                this.backgroundMessageSent = false;
            } else {
                this.mainThreadLooperHandler.removeCallbacks(this.taskRealGoingToBackgroundDetected);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activityPaused() {
        int i = this.resumedCounter - 1;
        this.resumedCounter = i;
        if (i == 0) {
            this.mainThreadLooperHandler.postDelayed(this.taskRealGoingToBackgroundDetected, 700L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchPauseIfNeeded() {
        if (this.resumedCounter == 0) {
            this.backgroundMessageSent = true;
            handleNotification(false);
        }
    }

    private void handleNotification(boolean z) {
        Iterator<LifecycleListener> it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            notifyListener(it.next(), z);
        }
    }
}
