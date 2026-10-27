package androidx.test.core.app;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.platform.ServiceLoaderWrapper;
import androidx.test.internal.platform.app.ActivityInvoker;
import androidx.test.internal.platform.os.ControlledLooper;
import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.lifecycle.ActivityLifecycleCallback;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;
import java.io.Closeable;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes5.dex */
public final class ActivityScenario<A extends Activity> implements AutoCloseable, Closeable {
    private static final Map<Stage, Lifecycle.State> STEADY_STATES;
    private static final String TAG = ActivityScenario.class.getSimpleName();
    private static final long TIMEOUT_MILLISECONDS = 45000;
    private final ActivityInvoker activityInvoker;
    private final ActivityLifecycleCallback activityLifecycleObserver;
    private final ControlledLooper controlledLooper;
    private A currentActivity;
    private Stage currentActivityStage;
    private final ReentrantLock lock;
    private final Intent startActivityIntent;
    private final Condition stateChangedCondition;

    public interface ActivityAction<A extends Activity> {
        void perform(A activity);
    }

    static {
        EnumMap enumMap = new EnumMap(Stage.class);
        STEADY_STATES = enumMap;
        enumMap.put(Stage.RESUMED, Lifecycle.State.RESUMED);
        STEADY_STATES.put(Stage.PAUSED, Lifecycle.State.STARTED);
        STEADY_STATES.put(Stage.STOPPED, Lifecycle.State.CREATED);
        STEADY_STATES.put(Stage.DESTROYED, Lifecycle.State.DESTROYED);
    }

    static final /* synthetic */ ActivityInvoker lambda$new$0$ActivityScenario() {
        return new InstrumentationActivityInvoker();
    }

    private ActivityScenario(Intent startActivityIntent) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.stateChangedCondition = reentrantLock.newCondition();
        this.activityInvoker = (ActivityInvoker) ServiceLoaderWrapper.loadSingleService(ActivityInvoker.class, ActivityScenario$$Lambda$0.$instance);
        this.controlledLooper = (ControlledLooper) ServiceLoaderWrapper.loadSingleService(ControlledLooper.class, ActivityScenario$$Lambda$1.$instance);
        this.currentActivityStage = Stage.PRE_ON_CREATE;
        this.activityLifecycleObserver = new ActivityLifecycleCallback() { // from class: androidx.test.core.app.ActivityScenario.1
            @Override // androidx.test.runner.lifecycle.ActivityLifecycleCallback
            public void onActivityLifecycleChanged(Activity activity, Stage stage) {
                if (!ActivityScenario.activityMatchesIntent(ActivityScenario.this.startActivityIntent, activity)) {
                    Log.v(ActivityScenario.TAG, String.format("Activity lifecycle changed event received but ignored because the intent does not match. startActivityIntent=%s, activity.getIntent()=%s, activity=%s", ActivityScenario.this.startActivityIntent, activity.getIntent(), activity));
                    return;
                }
                ActivityScenario.this.lock.lock();
                try {
                    int i = AnonymousClass2.$SwitchMap$androidx$test$runner$lifecycle$Stage[ActivityScenario.this.currentActivityStage.ordinal()];
                    if (i == 1 || i == 2) {
                        if (stage != Stage.CREATED) {
                            Log.v(ActivityScenario.TAG, String.format("Activity lifecycle changed event received but ignored because the reported transition was not ON_CREATE while the last known transition was %s", ActivityScenario.this.currentActivityStage));
                            return;
                        }
                    } else if (ActivityScenario.this.currentActivity != activity) {
                        Log.v(ActivityScenario.TAG, String.format("Activity lifecycle changed event received but ignored because the activity instance does not match. currentActivity=%s, receivedActivity=%s", ActivityScenario.this.currentActivity, activity));
                        return;
                    }
                    ActivityScenario.this.currentActivityStage = stage;
                    ActivityScenario.this.currentActivity = stage != Stage.DESTROYED ? activity : null;
                    Log.v(ActivityScenario.TAG, String.format("Update currentActivityStage to %s, currentActivity=%s", ActivityScenario.this.currentActivityStage, ActivityScenario.this.currentActivity));
                    ActivityScenario.this.stateChangedCondition.signal();
                } finally {
                    ActivityScenario.this.lock.unlock();
                }
            }
        };
        this.startActivityIntent = (Intent) Checks.checkNotNull(startActivityIntent);
    }

    private ActivityScenario(Class<A> activityClass) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.stateChangedCondition = reentrantLock.newCondition();
        this.activityInvoker = (ActivityInvoker) ServiceLoaderWrapper.loadSingleService(ActivityInvoker.class, ActivityScenario$$Lambda$2.$instance);
        this.controlledLooper = (ControlledLooper) ServiceLoaderWrapper.loadSingleService(ControlledLooper.class, ActivityScenario$$Lambda$3.$instance);
        this.currentActivityStage = Stage.PRE_ON_CREATE;
        this.activityLifecycleObserver = new ActivityLifecycleCallback() { // from class: androidx.test.core.app.ActivityScenario.1
            @Override // androidx.test.runner.lifecycle.ActivityLifecycleCallback
            public void onActivityLifecycleChanged(Activity activity, Stage stage) {
                if (!ActivityScenario.activityMatchesIntent(ActivityScenario.this.startActivityIntent, activity)) {
                    Log.v(ActivityScenario.TAG, String.format("Activity lifecycle changed event received but ignored because the intent does not match. startActivityIntent=%s, activity.getIntent()=%s, activity=%s", ActivityScenario.this.startActivityIntent, activity.getIntent(), activity));
                    return;
                }
                ActivityScenario.this.lock.lock();
                try {
                    int i = AnonymousClass2.$SwitchMap$androidx$test$runner$lifecycle$Stage[ActivityScenario.this.currentActivityStage.ordinal()];
                    if (i == 1 || i == 2) {
                        if (stage != Stage.CREATED) {
                            Log.v(ActivityScenario.TAG, String.format("Activity lifecycle changed event received but ignored because the reported transition was not ON_CREATE while the last known transition was %s", ActivityScenario.this.currentActivityStage));
                            return;
                        }
                    } else if (ActivityScenario.this.currentActivity != activity) {
                        Log.v(ActivityScenario.TAG, String.format("Activity lifecycle changed event received but ignored because the activity instance does not match. currentActivity=%s, receivedActivity=%s", ActivityScenario.this.currentActivity, activity));
                        return;
                    }
                    ActivityScenario.this.currentActivityStage = stage;
                    ActivityScenario.this.currentActivity = stage != Stage.DESTROYED ? activity : null;
                    Log.v(ActivityScenario.TAG, String.format("Update currentActivityStage to %s, currentActivity=%s", ActivityScenario.this.currentActivityStage, ActivityScenario.this.currentActivity));
                    ActivityScenario.this.stateChangedCondition.signal();
                } finally {
                    ActivityScenario.this.lock.unlock();
                }
            }
        };
        this.startActivityIntent = (Intent) Checks.checkNotNull(this.activityInvoker.getIntentForActivity((Class) Checks.checkNotNull(activityClass)));
    }

    public static <A extends Activity> ActivityScenario<A> launch(Class<A> activityClass) {
        ActivityScenario<A> scenario = new ActivityScenario<>((Class) Checks.checkNotNull(activityClass));
        scenario.launchInternal(null);
        return scenario;
    }

    public static <A extends Activity> ActivityScenario<A> launch(Class<A> activityClass, Bundle activityOptions) {
        ActivityScenario<A> scenario = new ActivityScenario<>((Class) Checks.checkNotNull(activityClass));
        scenario.launchInternal(activityOptions);
        return scenario;
    }

    public static <A extends Activity> ActivityScenario<A> launch(Intent startActivityIntent) {
        ActivityScenario<A> scenario = new ActivityScenario<>((Intent) Checks.checkNotNull(startActivityIntent));
        scenario.launchInternal(null);
        return scenario;
    }

    public static <A extends Activity> ActivityScenario<A> launch(Intent startActivityIntent, Bundle activityOptions) {
        ActivityScenario<A> scenario = new ActivityScenario<>((Intent) Checks.checkNotNull(startActivityIntent));
        scenario.launchInternal(activityOptions);
        return scenario;
    }

    private void launchInternal(Bundle activityOptions) {
        Checks.checkState(Settings.System.getInt(InstrumentationRegistry.getInstrumentation().getTargetContext().getContentResolver(), "always_finish_activities", 0) == 0, "\"Don't keep activities\" developer options must be disabled for ActivityScenario");
        Checks.checkNotMainThread();
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback(this.activityLifecycleObserver);
        if (activityOptions == null) {
            this.activityInvoker.startActivity(this.startActivityIntent);
        } else {
            this.activityInvoker.startActivity(this.startActivityIntent, activityOptions);
        }
        waitForActivityToBecomeAnyOf((Lifecycle.State[]) STEADY_STATES.values().toArray(new Lifecycle.State[0]));
    }

    @Override // java.lang.AutoCloseable, java.io.Closeable
    public void close() {
        moveToState(Lifecycle.State.DESTROYED);
        ActivityLifecycleMonitorRegistry.getInstance().removeLifecycleCallback(this.activityLifecycleObserver);
    }

    private void waitForActivityToBecomeAnyOf(Lifecycle.State... expectedStates) {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        Set<Lifecycle.State> expectedStateSet = new HashSet<>(Arrays.asList(expectedStates));
        this.lock.lock();
        try {
            try {
                if (expectedStateSet.contains(STEADY_STATES.get(this.currentActivityStage))) {
                    return;
                }
                long now = System.currentTimeMillis();
                long deadline = TIMEOUT_MILLISECONDS + now;
                while (now < deadline && !expectedStateSet.contains(STEADY_STATES.get(this.currentActivityStage))) {
                    this.stateChangedCondition.await(deadline - now, TimeUnit.MILLISECONDS);
                    now = System.currentTimeMillis();
                }
                if (expectedStateSet.contains(STEADY_STATES.get(this.currentActivityStage))) {
                    return;
                } else {
                    throw new AssertionError(String.format("Activity never becomes requested state \"%s\" (last lifecycle transition = \"%s\")", expectedStateSet, this.currentActivityStage));
                }
            } catch (InterruptedException e) {
                throw new AssertionError(String.format("Activity never becomes requested state \"%s\" (last lifecycle transition = \"%s\")", expectedStateSet, this.currentActivityStage), e);
            }
        } finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean activityMatchesIntent(Intent startActivityIntent, Activity launchedActivity) {
        Intent activityIntent = launchedActivity.getIntent();
        if (!equals(startActivityIntent.getAction(), activityIntent.getAction()) || !equals(startActivityIntent.getData(), activityIntent.getData()) || !equals(startActivityIntent.getType(), activityIntent.getType()) || !equals(startActivityIntent.getPackage(), activityIntent.getPackage())) {
            return false;
        }
        if ((startActivityIntent.getComponent() == null || equals(startActivityIntent.getComponent(), activityIntent.getComponent())) && equals(startActivityIntent.getCategories(), activityIntent.getCategories())) {
            return Build.VERSION.SDK_INT < 29 || equals(startActivityIntent.getIdentifier(), activityIntent.getIdentifier());
        }
        return false;
    }

    private static boolean equals(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    private static class ActivityState<A extends Activity> {
        final A activity;
        final Stage stage;
        final Lifecycle.State state;

        ActivityState(A activity, Lifecycle.State state, Stage stage) {
            this.activity = activity;
            this.state = state;
            this.stage = stage;
        }
    }

    private ActivityState<A> getCurrentActivityState() {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        this.lock.lock();
        try {
            return new ActivityState<>(this.currentActivity, STEADY_STATES.get(this.currentActivityStage), this.currentActivityStage);
        } finally {
            this.lock.unlock();
        }
    }

    public ActivityScenario<A> moveToState(Lifecycle.State newState) {
        Checks.checkNotMainThread();
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        ActivityState<A> currentState = getCurrentActivityState();
        Checks.checkNotNull(currentState.state, String.format("Current state was null unexpectedly. Last stage = %s", currentState.stage));
        if (currentState.state == newState) {
            return this;
        }
        Checks.checkState((currentState.state == Lifecycle.State.DESTROYED || currentState.activity == null) ? false : true, String.format("Cannot move to state \"%s\" since the Activity has been destroyed already", newState));
        int i = AnonymousClass2.$SwitchMap$android$arch$lifecycle$Lifecycle$State[newState.ordinal()];
        if (i == 1) {
            this.activityInvoker.stopActivity(currentState.activity);
        } else if (i == 2) {
            moveToState(Lifecycle.State.RESUMED);
            this.activityInvoker.pauseActivity(currentState.activity);
        } else if (i == 3) {
            this.activityInvoker.resumeActivity(currentState.activity);
        } else if (i == 4) {
            this.activityInvoker.finishActivity(currentState.activity);
        } else {
            throw new IllegalArgumentException(String.format("A requested state \"%s\" is not supported", newState));
        }
        waitForActivityToBecomeAnyOf(newState);
        return this;
    }

    /* JADX INFO: renamed from: androidx.test.core.app.ActivityScenario$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$arch$lifecycle$Lifecycle$State;
        static final /* synthetic */ int[] $SwitchMap$androidx$test$runner$lifecycle$Stage;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            $SwitchMap$android$arch$lifecycle$Lifecycle$State = iArr;
            try {
                iArr[Lifecycle.State.CREATED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$State[Lifecycle.State.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$State[Lifecycle.State.RESUMED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$State[Lifecycle.State.DESTROYED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            int[] iArr2 = new int[Stage.values().length];
            $SwitchMap$androidx$test$runner$lifecycle$Stage = iArr2;
            try {
                iArr2[Stage.PRE_ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$androidx$test$runner$lifecycle$Stage[Stage.DESTROYED.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public ActivityScenario<A> recreate() {
        ActivityState<A> activityState;
        Checks.checkNotMainThread();
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        ActivityState<A> prevActivityState = getCurrentActivityState();
        Checks.checkNotNull(prevActivityState.activity);
        Checks.checkNotNull(prevActivityState.state);
        moveToState(Lifecycle.State.RESUMED);
        this.activityInvoker.recreateActivity(prevActivityState.activity);
        long now = System.currentTimeMillis();
        long deadline = TIMEOUT_MILLISECONDS + now;
        do {
            waitForActivityToBecomeAnyOf(Lifecycle.State.RESUMED);
            long now2 = System.currentTimeMillis();
            activityState = getCurrentActivityState();
            if (now2 >= deadline) {
                break;
            }
        } while (activityState.activity == prevActivityState.activity);
        if (activityState.activity == prevActivityState.activity) {
            throw new IllegalStateException("Requested a re-creation of Activity but didn't happen");
        }
        moveToState(prevActivityState.state);
        return this;
    }

    public ActivityScenario<A> onActivity(final ActivityAction<A> action) {
        Runnable runnableAction = new Runnable(this, action) { // from class: androidx.test.core.app.ActivityScenario$$Lambda$4
            private final ActivityScenario arg$1;
            private final ActivityScenario.ActivityAction arg$2;

            {
                this.arg$1 = this;
                this.arg$2 = action;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.arg$1.lambda$onActivity$2$ActivityScenario(this.arg$2);
            }
        };
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.controlledLooper.drainMainThreadUntilIdle();
            runnableAction.run();
        } else {
            InstrumentationRegistry.getInstrumentation().waitForIdleSync();
            InstrumentationRegistry.getInstrumentation().runOnMainSync(runnableAction);
        }
        return this;
    }

    final /* synthetic */ void lambda$onActivity$2$ActivityScenario(ActivityAction action) {
        Checks.checkMainThread();
        this.lock.lock();
        try {
            Checks.checkNotNull(this.currentActivity, "Cannot run onActivity since Activity has been destroyed already");
            action.perform(this.currentActivity);
        } finally {
            this.lock.unlock();
        }
    }

    public Instrumentation.ActivityResult getResult() {
        return this.activityInvoker.getActivityResult();
    }

    public Lifecycle.State getState() {
        ActivityState<A> currentActivityState = getCurrentActivityState();
        return (Lifecycle.State) Checks.checkNotNull(currentActivityState.state, "Could not get current state of activity %s due to the transition is incomplete. Current stage = %s", currentActivityState.activity, currentActivityState.stage);
    }
}
