package com.amazon.avod.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.lang.ref.WeakReference;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class CurrentActivityTracker {
    private static CurrentActivityTracker mInstance = new CurrentActivityTracker();

    @Nonnull
    private WeakReference<Activity> mCurrentActivity = new WeakReference<>(null);

    @Nonnull
    private CurrentActivityTrackerLifecycleListener mLifecycleListener = new CurrentActivityTrackerLifecycleListener();

    private class CurrentActivityTrackerLifecycleListener implements Application.ActivityLifecycleCallbacks {
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        private CurrentActivityTrackerLifecycleListener() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            CurrentActivityTracker.this.tryUpdateActivity(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            CurrentActivityTracker.this.tryUpdateActivity(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            CurrentActivityTracker.this.tryUpdateActivity(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            CurrentActivityTracker.this.tryClearActivity(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            CurrentActivityTracker.this.tryClearActivity(activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryUpdateActivity(Activity activity) {
        this.mCurrentActivity = new WeakReference<>(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryClearActivity(Activity activity) {
        if (this.mCurrentActivity.get() == activity) {
            this.mCurrentActivity.clear();
        }
    }

    @VisibleForTesting
    CurrentActivityTracker() {
    }

    @Nonnull
    public Optional<Activity> getCurrentActivity() throws IllegalStateException {
        return Optional.fromNullable(this.mCurrentActivity.get());
    }

    public void attachActivityLifecycleTracker(@Nonnull Application application) {
        Preconditions.checkNotNull(application, "application");
        application.registerActivityLifecycleCallbacks(this.mLifecycleListener);
    }

    public static CurrentActivityTracker getInstance() {
        return mInstance;
    }
}
