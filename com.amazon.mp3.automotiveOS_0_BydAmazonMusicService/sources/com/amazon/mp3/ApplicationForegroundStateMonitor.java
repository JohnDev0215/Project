package com.amazon.mp3;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.configuration.PreferenceUpdateProvider;
import com.amazon.mp3.language.preference.util.LocaleManager;
import com.amazon.mp3.notification.NotificationEarlyUseHelper;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenWakeLock;
import com.amazon.mp3.widget.WidgetUtil;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes3.dex */
public final class ApplicationForegroundStateMonitor implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = ApplicationForegroundStateMonitor.class.getSimpleName();
    private NotificationEarlyUseHelper.CustomerDropFrom customerDropFrom;
    private int mActivityForegroundCount;
    private boolean mIsForeground;
    private final PublishSubject mForegroundSubject = PublishSubject.create();
    private final PublishSubject mBackgroundSubject = PublishSubject.create();

    public boolean isApplicationForeground() {
        return this.mActivityForegroundCount > 0;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreStarted(final Activity activity) {
        Log.debug(TAG, "%s.onPreStart()", activity.getClass().getSimpleName());
        if (AmazonApplication.getCapabilities().isAppDisplayLangSettingEnabled()) {
            manageAppLocaleIfRequired(activity);
        }
    }

    private void manageAppLocaleIfRequired(final Activity activity) {
        LocaleManager localeManager = LocaleManager.INSTANCE;
        if (localeManager.isLocaleUpdateRequired(activity.getBaseContext()) && AccountCredentialUtil.get(activity.getBaseContext()).isSignedIn()) {
            String str = TAG;
            Log.debug(str, "Locale used with onCreate is not equal to locale at onPreStart");
            if (!localeManager.isAppLocaleAvailable()) {
                Log.debug(str, "Recreating activity on onPreStart to refresh locale : %s", activity.getClass().getSimpleName());
                PreferenceUpdateProvider.dispatchPreferenceUpdateEvent();
                activity.recreate();
            } else {
                Log.debug(str, "Current locale is outdated, updating the activity instance locale: %s", activity.getClass().getSimpleName());
                localeManager.setLocale(activity.getBaseContext());
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(final Activity activity) {
        Log.debug(TAG, "%s.onResume()", activity.getClass().getSimpleName());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(final Activity activity) {
        Log.debug(TAG, "%s.onPause()", activity.getClass().getSimpleName());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(final Activity activity, final Bundle savedInstanceState) {
        logActivityLifecycleEvent(activity, "onCreate");
        AmazonApplication.changeLocaleIfNeeded(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(final Activity activity) {
        Log.debug(TAG, "%s.onStart()", activity.getClass().getSimpleName());
        if (WidgetUtil.isAlexaWidgetOrAccountStateCheckActivity(activity)) {
            return;
        }
        this.mActivityForegroundCount++;
        this.customerDropFrom = null;
        ScreenWakeLock.setActivity(activity);
        activity.sendBroadcast(new Intent("com.amazon.mp3.screenwakelock.activity.in.foreground"));
        if (this.mIsForeground || this.mActivityForegroundCount <= 0) {
            return;
        }
        handleEnteringForeground(activity);
    }

    private void handleEnteringForeground(final Activity activity) {
        this.mIsForeground = true;
        Log.warning(TAG, "Entered foreground");
        activity.sendBroadcast(new Intent("com.amazon.mp3.wakewordcommand.app.in.foreground"));
        if (AmazonApplication.getCapabilities().isEarlyUseNotificationEnabled()) {
            NotificationEarlyUseHelper.INSTANCE.cancelNotification(activity.getApplicationContext());
        }
        this.mForegroundSubject.onNext(null);
    }

    public void subscribeForegroundObserver(final Action1<Void> nextAction, final Action1<Throwable> errorAction) {
        Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.mp3.ApplicationForegroundStateMonitor.1
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super Void> subscriber) {
                if (!ApplicationForegroundStateMonitor.this.mIsForeground) {
                    ApplicationForegroundStateMonitor.this.mForegroundSubject.subscribe((Subscriber) subscriber);
                } else {
                    subscriber.onNext(null);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).take(1).subscribe(nextAction, errorAction);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(final Activity activity) {
        String str = TAG;
        Log.debug(str, "%s.onStop()", activity.getClass().getSimpleName());
        if (WidgetUtil.isAlexaWidgetOrAccountStateCheckActivity(activity)) {
            return;
        }
        this.mActivityForegroundCount--;
        NotificationEarlyUseHelper.CustomerDropFrom customerDropFrom = this.customerDropFrom;
        if (customerDropFrom == null || customerDropFrom == NotificationEarlyUseHelper.CustomerDropFrom.OTHERS) {
            this.customerDropFrom = NotificationEarlyUseHelper.INSTANCE.getCustomerDropFrom(activity);
        }
        if (!this.mIsForeground || this.mActivityForegroundCount > 0 || activity.isChangingConfigurations()) {
            return;
        }
        this.mActivityForegroundCount = Math.max(this.mActivityForegroundCount, 0);
        this.mIsForeground = false;
        if (AmazonApplication.getCapabilities().isEarlyUseNotificationEnabled()) {
            NotificationEarlyUseHelper.INSTANCE.triggerNotificationEarlyUseExperiment(activity, this.customerDropFrom);
            this.customerDropFrom = null;
        }
        this.mBackgroundSubject.onNext(null);
        Log.warning(str, "Entered background");
        activity.sendBroadcast(new Intent("com.amazon.mp3.wakewordcommand.app.in.background"));
    }

    public void subscribeBackgroundObserver(final Action1<Void> nextAction, final Action1<Throwable> errorAction) {
        Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.mp3.ApplicationForegroundStateMonitor.2
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super Void> subscriber) {
                if (ApplicationForegroundStateMonitor.this.mIsForeground) {
                    ApplicationForegroundStateMonitor.this.mBackgroundSubject.subscribe((Subscriber) subscriber);
                } else {
                    subscriber.onNext(null);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).take(1).subscribe(nextAction, errorAction);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(final Activity activity, final Bundle outState) {
        logActivityLifecycleEvent(activity, "onSaveInstanceState");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(final Activity activity) {
        logActivityLifecycleEvent(activity, "onDestroy");
    }

    private void logActivityLifecycleEvent(final Activity activity, final String event) {
        Log.debug(TAG, "%s.%s(), PID: %s", activity.getClass().getSimpleName(), event, Integer.valueOf(Process.myPid()));
    }
}
