package com.amazon.avod.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.PowerManager;
import androidx.annotation.AnyThread;
import androidx.annotation.UiThread;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes2.dex */
@AnyThread
public class AppVisibilityTracker {

    @GuardedBy("mStateTransitionLock")
    private ApplicationVisibility mCurrentState;
    private Handler mHandler;
    private PowerManager mPowerManager;

    @GuardedBy("mStateTransitionLock")
    private ApplicationVisibility mPreviousState;
    private final Object mStateTransitionLock = new Object();
    private final Map<ApplicationVisibilityListener, Long> mListeners = Maps.newConcurrentMap();
    private final Map<ApplicationVisibilityListener, NotifyRunnable> mCurrentRunnables = Maps.newConcurrentMap();
    private final InitializationLatch mInitializationLatch = new InitializationLatch(this);
    private final AtomicInteger mNumRunningActivities = new AtomicInteger(0);

    public interface ApplicationVisibilityListener {
        @UiThread
        void onApplicationVisibilityChanged(@Nonnull ApplicationVisibility applicationVisibility, @Nonnull ApplicationVisibility applicationVisibility2);
    }

    private static class Holder {
        private static volatile AppVisibilityTracker sInstance = new AppVisibilityTracker();

        private Holder() {
        }
    }

    public static AppVisibilityTracker getInstance() {
        return Holder.sInstance;
    }

    AppVisibilityTracker() {
    }

    public void initializeOnAppCreate(@Nonnull Context context) {
        this.mInitializationLatch.start(120L, TimeUnit.SECONDS);
        Preconditions.checkNotNull(context, "context");
        this.mHandler = new Handler(context.getMainLooper());
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.registerReceiver(new DeviceInteractiveBroadcastReceiver(), intentFilter);
        synchronized (this.mStateTransitionLock) {
            this.mPreviousState = ApplicationVisibility.DEVICE_INACTIVE_IN_BACKGROUND;
            this.mCurrentState = ApplicationVisibility.fromValues(isAppInForeground(), this.mPowerManager.isInteractive());
        }
        this.mInitializationLatch.complete();
    }

    public void addAppVisibilityListenerWithImmediateCallback(@Nonnull ApplicationVisibilityListener applicationVisibilityListener) {
        addAppVisibilityListenerWithImmediateCallback(applicationVisibilityListener, 0L);
    }

    public void addAppVisibilityListenerWithImmediateCallback(@Nonnull ApplicationVisibilityListener applicationVisibilityListener, @Nonnegative long j) {
        Preconditions.checkNotNull(applicationVisibilityListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions2.checkNonNegative(j, "Delay must be >= 0");
        this.mListeners.put(applicationVisibilityListener, Long.valueOf(j));
        synchronized (this.mStateTransitionLock) {
            this.mHandler.post(new NotifyRunnable(applicationVisibilityListener, this.mPreviousState, this.mCurrentState, this));
        }
    }

    public void removeAppVisibilityListener(@Nonnull ApplicationVisibilityListener applicationVisibilityListener) {
        Preconditions.checkNotNull(applicationVisibilityListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.mListeners.remove(applicationVisibilityListener);
        this.mHandler.removeCallbacks(this.mCurrentRunnables.remove(applicationVisibilityListener));
    }

    @Nonnull
    public ApplicationVisibility getApplicationVisibility() {
        ApplicationVisibility applicationVisibility;
        this.mInitializationLatch.checkInitialized();
        synchronized (this.mStateTransitionLock) {
            applicationVisibility = this.mCurrentState;
        }
        return applicationVisibility;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transitionTo(@Nonnull ApplicationVisibility applicationVisibility) {
        synchronized (this.mStateTransitionLock) {
            ApplicationVisibility applicationVisibility2 = this.mCurrentState;
            this.mPreviousState = applicationVisibility2;
            this.mCurrentState = applicationVisibility;
            if (applicationVisibility == applicationVisibility2) {
                return;
            }
            startNotifyCallbacks(applicationVisibility2, applicationVisibility);
        }
    }

    private void startNotifyCallbacks(@Nonnull ApplicationVisibility applicationVisibility, @Nonnull ApplicationVisibility applicationVisibility2) {
        DLog.logf("%s: old visibility %s, new visibility %s", getClass().getSimpleName(), applicationVisibility, applicationVisibility2);
        boolean zIsAppInForeground = applicationVisibility.isAppInForeground();
        boolean zIsAppInForeground2 = applicationVisibility2.isAppInForeground();
        boolean z = zIsAppInForeground && !zIsAppInForeground2;
        boolean z2 = !zIsAppInForeground && zIsAppInForeground2;
        for (Map.Entry<ApplicationVisibilityListener, Long> entry : this.mListeners.entrySet()) {
            if (z || z2) {
                this.mHandler.removeCallbacks(this.mCurrentRunnables.remove(entry.getKey()));
            }
            NotifyRunnable notifyRunnable = new NotifyRunnable(entry.getKey(), applicationVisibility, applicationVisibility2, this);
            if (z && entry.getValue().longValue() > 0) {
                this.mHandler.postDelayed(notifyRunnable, entry.getValue().longValue());
                this.mCurrentRunnables.put(entry.getKey(), notifyRunnable);
            } else {
                this.mHandler.post(notifyRunnable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAppInForeground() {
        return this.mNumRunningActivities.get() > 0;
    }

    private static class NotifyRunnable implements Runnable {
        private final WeakReference<AppVisibilityTracker> mAppVisibilityTrackerRef;
        private final ApplicationVisibilityListener mListener;
        private final ApplicationVisibility mNewState;
        private final ApplicationVisibility mOldState;

        public NotifyRunnable(@Nonnull ApplicationVisibilityListener applicationVisibilityListener, @Nonnull ApplicationVisibility applicationVisibility, @Nonnull ApplicationVisibility applicationVisibility2, @Nonnull AppVisibilityTracker appVisibilityTracker) {
            this.mListener = (ApplicationVisibilityListener) Preconditions.checkNotNull(applicationVisibilityListener);
            this.mOldState = (ApplicationVisibility) Preconditions.checkNotNull(applicationVisibility);
            this.mNewState = (ApplicationVisibility) Preconditions.checkNotNull(applicationVisibility2);
            this.mAppVisibilityTrackerRef = new WeakReference<>(Preconditions.checkNotNull(appVisibilityTracker));
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mListener.onApplicationVisibilityChanged(this.mOldState, this.mNewState);
            AppVisibilityTracker appVisibilityTracker = this.mAppVisibilityTrackerRef.get();
            if (appVisibilityTracker != null) {
                appVisibilityTracker.mCurrentRunnables.remove(this.mListener);
            }
        }
    }

    private class DeviceInteractiveBroadcastReceiver extends BroadcastReceiver {
        private DeviceInteractiveBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean zEquals = "android.intent.action.SCREEN_OFF".equals(action);
            boolean zEquals2 = "android.intent.action.SCREEN_ON".equals(action);
            if (zEquals || zEquals2) {
                DLog.logf("%s: Received %s request.", getClass().getSimpleName(), intent.getAction());
                AppVisibilityTracker.this.transitionTo(ApplicationVisibility.fromValues(AppVisibilityTracker.this.isAppInForeground(), zEquals2));
            }
        }
    }
}
