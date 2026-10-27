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

/* JADX INFO: loaded from: classes5.dex */
public final class ActivityLifecycleMonitorImpl implements ActivityLifecycleMonitor {
    private static final String TAG = "LifecycleMonitor";
    private List<ActivityStatus> activityStatuses;
    private final List<WeakReference<ActivityLifecycleCallback>> callbacks;
    private final boolean declawThreadCheck;

    public ActivityLifecycleMonitorImpl() {
        this(false);
    }

    public ActivityLifecycleMonitorImpl(boolean declawThreadCheck) {
        this.callbacks = new ArrayList();
        this.activityStatuses = new ArrayList();
        this.declawThreadCheck = declawThreadCheck;
    }

    @Override // androidx.test.runner.lifecycle.ActivityLifecycleMonitor
    public void addLifecycleCallback(ActivityLifecycleCallback callback) {
        Checks.checkNotNull(callback);
        synchronized (this.callbacks) {
            boolean needsAdd = true;
            Iterator<WeakReference<ActivityLifecycleCallback>> refIter = this.callbacks.iterator();
            while (refIter.hasNext()) {
                ActivityLifecycleCallback storedCallback = refIter.next().get();
                if (storedCallback == null) {
                    refIter.remove();
                } else if (storedCallback == callback) {
                    needsAdd = false;
                }
            }
            if (needsAdd) {
                this.callbacks.add(new WeakReference<>(callback));
            }
        }
    }

    @Override // androidx.test.runner.lifecycle.ActivityLifecycleMonitor
    public void removeLifecycleCallback(ActivityLifecycleCallback callback) {
        Checks.checkNotNull(callback);
        synchronized (this.callbacks) {
            Iterator<WeakReference<ActivityLifecycleCallback>> refIter = this.callbacks.iterator();
            while (refIter.hasNext()) {
                ActivityLifecycleCallback storedCallback = refIter.next().get();
                if (storedCallback == null) {
                    refIter.remove();
                } else if (storedCallback == callback) {
                    refIter.remove();
                }
            }
        }
    }

    @Override // androidx.test.runner.lifecycle.ActivityLifecycleMonitor
    public Stage getLifecycleStageOf(Activity activity) {
        checkMainThread();
        Checks.checkNotNull(activity);
        Iterator<ActivityStatus> statusIterator = this.activityStatuses.iterator();
        while (statusIterator.hasNext()) {
            ActivityStatus status = statusIterator.next();
            Activity statusActivity = (Activity) status.activityRef.get();
            if (statusActivity == null) {
                statusIterator.remove();
            } else if (activity == statusActivity) {
                return status.lifecycleStage;
            }
        }
        String strValueOf = String.valueOf(activity);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 18);
        sb.append("Unknown activity: ");
        sb.append(strValueOf);
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // androidx.test.runner.lifecycle.ActivityLifecycleMonitor
    public Collection<Activity> getActivitiesInStage(Stage stage) {
        checkMainThread();
        Checks.checkNotNull(stage);
        List<Activity> activities = new ArrayList<>();
        Iterator<ActivityStatus> statusIterator = this.activityStatuses.iterator();
        while (statusIterator.hasNext()) {
            ActivityStatus status = statusIterator.next();
            Activity statusActivity = (Activity) status.activityRef.get();
            if (statusActivity == null) {
                statusIterator.remove();
            } else if (stage == status.lifecycleStage) {
                activities.add(statusActivity);
            }
        }
        return activities;
    }

    public void signalLifecycleChange(Stage stage, Activity activity) {
        String strValueOf = String.valueOf(activity);
        String strValueOf2 = String.valueOf(stage);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 30 + String.valueOf(strValueOf2).length());
        sb.append("Lifecycle status change: ");
        sb.append(strValueOf);
        sb.append(" in: ");
        sb.append(strValueOf2);
        Log.d(TAG, sb.toString());
        boolean needsAdd = true;
        Iterator<ActivityStatus> statusIterator = this.activityStatuses.iterator();
        while (statusIterator.hasNext()) {
            ActivityStatus status = statusIterator.next();
            Activity statusActivity = (Activity) status.activityRef.get();
            if (statusActivity == null) {
                statusIterator.remove();
            } else if (activity == statusActivity) {
                needsAdd = false;
                status.lifecycleStage = stage;
            }
        }
        if (needsAdd) {
            this.activityStatuses.add(new ActivityStatus(activity, stage));
        }
        synchronized (this.callbacks) {
            Iterator<WeakReference<ActivityLifecycleCallback>> refIter = this.callbacks.iterator();
            while (refIter.hasNext()) {
                ActivityLifecycleCallback callback = refIter.next().get();
                if (callback == null) {
                    refIter.remove();
                } else {
                    try {
                        String strValueOf3 = String.valueOf(callback);
                        StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf3).length() + 18);
                        sb2.append("running callback: ");
                        sb2.append(strValueOf3);
                        Log.d(TAG, sb2.toString());
                        callback.onActivityLifecycleChanged(activity, stage);
                        String strValueOf4 = String.valueOf(callback);
                        StringBuilder sb3 = new StringBuilder(String.valueOf(strValueOf4).length() + 20);
                        sb3.append("callback completes: ");
                        sb3.append(strValueOf4);
                        Log.d(TAG, sb3.toString());
                    } catch (RuntimeException re) {
                        Log.e(TAG, String.format("Callback threw exception! (callback: %s activity: %s stage: %s)", callback, activity, stage), re);
                    }
                }
            }
        }
    }

    private void checkMainThread() {
        if (!this.declawThreadCheck && !Thread.currentThread().equals(Looper.getMainLooper().getThread())) {
            throw new IllegalStateException("Querying activity state off main thread is not allowed.");
        }
    }

    private static class ActivityStatus {
        private final WeakReference<Activity> activityRef;
        private Stage lifecycleStage;

        ActivityStatus(Activity activity, Stage stage) {
            this.activityRef = new WeakReference<>((Activity) Checks.checkNotNull(activity));
            this.lifecycleStage = (Stage) Checks.checkNotNull(stage);
        }
    }
}
