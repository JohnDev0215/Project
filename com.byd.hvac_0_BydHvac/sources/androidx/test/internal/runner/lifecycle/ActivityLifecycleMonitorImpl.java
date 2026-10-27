package androidx.test.internal.runner.lifecycle;

import android.app.Activity;
import android.os.Looper;
import android.util.Log;
import androidx.test.internal.util.Checks;
import androidx.test.runner.lifecycle.ActivityLifecycleCallback;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor;
import androidx.test.runner.lifecycle.Stage;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class ActivityLifecycleMonitorImpl implements ActivityLifecycleMonitor {
    private static final String TAG = "LifecycleMonitor";
    private List<ActivityStatus> activityStatuses;
    private final List<WeakReference<ActivityLifecycleCallback>> callbacks;
    private final boolean declawThreadCheck;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class ActivityStatus {
        private final WeakReference<Activity> activityRef;
        private Stage lifecycleStage;

        public ActivityStatus(Activity activity, Stage stage) {
            this.activityRef = new WeakReference<>((Activity) Checks.checkNotNull(activity));
            this.lifecycleStage = (Stage) Checks.checkNotNull(stage);
        }
    }

    public ActivityLifecycleMonitorImpl() {
        this(false);
    }

    private void checkMainThread() {
        if (!this.declawThreadCheck && !Thread.currentThread().equals(Looper.getMainLooper().getThread())) {
            throw new IllegalStateException("Querying activity state off main thread is not allowed.");
        }
    }

    @Override // androidx.test.runner.lifecycle.ActivityLifecycleMonitor
    public void addLifecycleCallback(ActivityLifecycleCallback activityLifecycleCallback) {
        Checks.checkNotNull(activityLifecycleCallback);
        synchronized (this.callbacks) {
            boolean z = true;
            Iterator<WeakReference<ActivityLifecycleCallback>> it = this.callbacks.iterator();
            while (it.hasNext()) {
                ActivityLifecycleCallback activityLifecycleCallback2 = it.next().get();
                if (activityLifecycleCallback2 == null) {
                    it.remove();
                } else if (activityLifecycleCallback2 == activityLifecycleCallback) {
                    z = false;
                }
            }
            if (z) {
                this.callbacks.add(new WeakReference<>(activityLifecycleCallback));
            }
        }
    }

    @Override // androidx.test.runner.lifecycle.ActivityLifecycleMonitor
    public Collection<Activity> getActivitiesInStage(Stage stage) {
        checkMainThread();
        Checks.checkNotNull(stage);
        ArrayList arrayList = new ArrayList();
        Iterator<ActivityStatus> it = this.activityStatuses.iterator();
        while (it.hasNext()) {
            ActivityStatus next = it.next();
            Activity activity = (Activity) next.activityRef.get();
            if (activity == null) {
                it.remove();
            } else if (stage == next.lifecycleStage) {
                arrayList.add(activity);
            }
        }
        return arrayList;
    }

    @Override // androidx.test.runner.lifecycle.ActivityLifecycleMonitor
    public Stage getLifecycleStageOf(Activity activity) {
        checkMainThread();
        Checks.checkNotNull(activity);
        Iterator<ActivityStatus> it = this.activityStatuses.iterator();
        while (it.hasNext()) {
            ActivityStatus next = it.next();
            Activity activity2 = (Activity) next.activityRef.get();
            if (activity2 == null) {
                it.remove();
            } else if (activity == activity2) {
                return next.lifecycleStage;
            }
        }
        String strValueOf = String.valueOf(activity);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 18);
        sb.append("Unknown activity: ");
        sb.append(strValueOf);
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // androidx.test.runner.lifecycle.ActivityLifecycleMonitor
    public void removeLifecycleCallback(ActivityLifecycleCallback activityLifecycleCallback) {
        Checks.checkNotNull(activityLifecycleCallback);
        synchronized (this.callbacks) {
            Iterator<WeakReference<ActivityLifecycleCallback>> it = this.callbacks.iterator();
            while (it.hasNext()) {
                ActivityLifecycleCallback activityLifecycleCallback2 = it.next().get();
                if (activityLifecycleCallback2 == null) {
                    it.remove();
                } else if (activityLifecycleCallback2 == activityLifecycleCallback) {
                    it.remove();
                }
            }
        }
    }

    public void signalLifecycleChange(Stage stage, Activity activity) {
        String strValueOf = String.valueOf(activity);
        String strValueOf2 = String.valueOf(stage);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 30 + strValueOf2.length());
        sb.append("Lifecycle status change: ");
        sb.append(strValueOf);
        sb.append(" in: ");
        sb.append(strValueOf2);
        Log.d(TAG, sb.toString());
        Iterator<ActivityStatus> it = this.activityStatuses.iterator();
        boolean z = true;
        while (it.hasNext()) {
            ActivityStatus next = it.next();
            Activity activity2 = (Activity) next.activityRef.get();
            if (activity2 == null) {
                it.remove();
            } else if (activity == activity2) {
                next.lifecycleStage = stage;
                z = false;
            }
        }
        if (z) {
            this.activityStatuses.add(new ActivityStatus(activity, stage));
        }
        synchronized (this.callbacks) {
            Iterator<WeakReference<ActivityLifecycleCallback>> it2 = this.callbacks.iterator();
            while (it2.hasNext()) {
                ActivityLifecycleCallback activityLifecycleCallback = it2.next().get();
                if (activityLifecycleCallback == null) {
                    it2.remove();
                } else {
                    try {
                        String strValueOf3 = String.valueOf(activityLifecycleCallback);
                        StringBuilder sb2 = new StringBuilder(strValueOf3.length() + 18);
                        sb2.append("running callback: ");
                        sb2.append(strValueOf3);
                        Log.d(TAG, sb2.toString());
                        activityLifecycleCallback.onActivityLifecycleChanged(activity, stage);
                        String strValueOf4 = String.valueOf(activityLifecycleCallback);
                        StringBuilder sb3 = new StringBuilder(strValueOf4.length() + 20);
                        sb3.append("callback completes: ");
                        sb3.append(strValueOf4);
                        Log.d(TAG, sb3.toString());
                    } catch (RuntimeException e) {
                        Log.e(TAG, String.format("Callback threw exception! (callback: %s activity: %s stage: %s)", activityLifecycleCallback, activity, stage), e);
                    }
                }
            }
        }
    }

    public ActivityLifecycleMonitorImpl(boolean z) {
        this.callbacks = new ArrayList();
        this.activityStatuses = new ArrayList();
        this.declawThreadCheck = z;
    }
}
