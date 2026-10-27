package com.amazon.alexa.mobilytics.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.amazon.alexa.mobilytics.util.Log;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

/* JADX INFO: loaded from: classes2.dex */
public class LifecycleCallbackListener implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = Log.tag(LifecycleCallbackListener.class);
    private int createdCount = 0;
    private int startedCount = 0;
    private int resumedCount = 0;
    private boolean destroySent = true;
    private boolean stopSent = true;
    private boolean pauseSent = true;
    private SerializedSubject<Lifecycle$Event, Lifecycle$Event> subject = new SerializedSubject<>(PublishSubject.create());

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        int i = this.createdCount + 1;
        this.createdCount = i;
        if (i == 1 && this.destroySent) {
            this.destroySent = false;
            this.subject.onNext(Lifecycle$Event.ON_CREATE);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        int i = this.startedCount + 1;
        this.startedCount = i;
        if (i == 1 && this.stopSent) {
            this.stopSent = false;
            this.subject.onNext(Lifecycle$Event.ON_START);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        int i = this.resumedCount + 1;
        this.resumedCount = i;
        if (i == 1 && this.pauseSent) {
            this.pauseSent = false;
            this.subject.onNext(Lifecycle$Event.ON_RESUME);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        int i = this.resumedCount - 1;
        this.resumedCount = i;
        if (i == 0) {
            this.pauseSent = true;
            this.subject.onNext(Lifecycle$Event.ON_PAUSE);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        int i = this.startedCount - 1;
        this.startedCount = i;
        if (i == 0) {
            this.stopSent = true;
            this.subject.onNext(Lifecycle$Event.ON_STOP);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        int i = this.createdCount - 1;
        this.createdCount = i;
        if (i == 0) {
            this.destroySent = true;
            this.subject.onNext(Lifecycle$Event.ON_DESTROY);
        }
    }
}
