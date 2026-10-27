package androidx.test.core.app;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.test.internal.platform.app.ActivityInvoker;
import androidx.test.internal.platform.app.ActivityInvoker$$CC;
import androidx.test.internal.platform.app.ActivityLifecycleTimeout;
import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class InstrumentationActivityInvoker implements ActivityInvoker {
    private static final String BOOTSTRAP_ACTIVITY_RESULT_CODE_KEY = "androidx.test.core.app.InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_CODE_KEY";
    private static final String BOOTSTRAP_ACTIVITY_RESULT_DATA_KEY = "androidx.test.core.app.InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_DATA_KEY";
    private static final String BOOTSTRAP_ACTIVITY_RESULT_RECEIVED = "androidx.test.core.app.InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_RECEIVED";
    private static final String EMPTY_ACTIVITY_RESUMED = "androidx.test.core.app.InstrumentationActivityInvoker.EMPTY_ACTIVITY_RESUMED";
    private static final String EMPTY_FLOATING_ACTIVITY_RESUMED = "androidx.test.core.app.InstrumentationActivityInvoker.EMPTY_FLOATING_ACTIVITY_RESUMED";
    private static final String FINISH_BOOTSTRAP_ACTIVITY = "androidx.test.core.app.InstrumentationActivityInvoker.FINISH_BOOTSTRAP_ACTIVITY";
    private static final String FINISH_EMPTY_ACTIVITIES = "androidx.test.core.app.InstrumentationActivityInvoker.FINISH_EMPTY_ACTIVITIES";
    private static final String TARGET_ACTIVITY_INTENT_KEY = "androidx.test.core.app.InstrumentationActivityInvoker.START_TARGET_ACTIVITY_INTENT_KEY";
    private static final String TARGET_ACTIVITY_OPTIONS_BUNDLE_KEY = "androidx.test.core.app.InstrumentationActivityInvoker.TARGET_ACTIVITY_OPTIONS_BUNDLE_KEY";

    @Nullable
    private ActivityResultWaiter activityResultWaiter;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class ActivityResultWaiter {
        private static final String TAG = "androidx.test.core.app.InstrumentationActivityInvoker$ActivityResultWaiter";

        @Nullable
        private Instrumentation.ActivityResult activityResult;
        private final CountDownLatch latch = new CountDownLatch(1);

        public ActivityResultWaiter(Context context) {
            context.registerReceiver(new BroadcastReceiver() { // from class: androidx.test.core.app.InstrumentationActivityInvoker.ActivityResultWaiter.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    context2.unregisterReceiver(this);
                    int intExtra = intent.getIntExtra(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_CODE_KEY, 0);
                    Intent intent2 = (Intent) intent.getParcelableExtra(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_DATA_KEY);
                    if (intent2 != null) {
                        intent2 = new Intent(intent2);
                    }
                    ActivityResultWaiter.this.activityResult = new Instrumentation.ActivityResult(intExtra, intent2);
                    ActivityResultWaiter.this.latch.countDown();
                }
            }, new IntentFilter(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_RECEIVED));
        }

        public Instrumentation.ActivityResult getActivityResult() {
            try {
                this.latch.await(ActivityLifecycleTimeout.getMillis(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Log.i(TAG, "Waiting activity result was interrupted", e);
            }
            Checks.checkNotNull(this.activityResult, "onActivityResult never be called after %d milliseconds", Long.valueOf(ActivityLifecycleTimeout.getMillis()));
            return this.activityResult;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class BootstrapActivity extends Activity {
        private static final String IS_TARGET_ACTIVITY_STARTED_KEY = "IS_TARGET_ACTIVITY_STARTED_KEY";
        private static final String TAG = BootstrapActivity.class.getName();
        private boolean isTargetActivityStarted;
        private final BroadcastReceiver receiver = new BroadcastReceiver() { // from class: androidx.test.core.app.InstrumentationActivityInvoker.BootstrapActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                BootstrapActivity.this.finishActivity(0);
                BootstrapActivity.this.finish();
            }
        };

        @Override // android.app.Activity
        public void finish() {
            super.finish();
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        public void onActivityResult(int i, int i2, @Nullable Intent intent) {
            if (i == 0) {
                Intent intent2 = new Intent(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_RECEIVED);
                intent2.putExtra(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_CODE_KEY, i2);
                if (intent != null) {
                    intent2.putExtra(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_DATA_KEY, intent);
                }
                sendBroadcast(intent2);
                finish();
            }
        }

        @Override // android.app.Activity
        public void onCreate(@Nullable Bundle bundle) {
            super.onCreate(bundle);
            registerReceiver(this.receiver, new IntentFilter(InstrumentationActivityInvoker.FINISH_BOOTSTRAP_ACTIVITY));
            this.isTargetActivityStarted = bundle != null && bundle.getBoolean(IS_TARGET_ACTIVITY_STARTED_KEY, false);
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        public void onDestroy() {
            super.onDestroy();
            unregisterReceiver(this.receiver);
        }

        @Override // android.app.Activity
        public void onResume() {
            super.onResume();
            if (this.isTargetActivityStarted) {
                return;
            }
            this.isTargetActivityStarted = true;
            PendingIntent pendingIntent = (PendingIntent) Checks.checkNotNull((PendingIntent) getIntent().getParcelableExtra(InstrumentationActivityInvoker.TARGET_ACTIVITY_INTENT_KEY));
            Bundle bundleExtra = getIntent().getBundleExtra(InstrumentationActivityInvoker.TARGET_ACTIVITY_OPTIONS_BUNDLE_KEY);
            if (bundleExtra != null) {
                try {
                    if (Build.VERSION.SDK_INT < 16) {
                        startIntentSenderForResult(pendingIntent.getIntentSender(), 0, null, 268435456, 0, 0);
                    } else {
                        startIntentSenderForResult(pendingIntent.getIntentSender(), 0, null, 268435456, 0, 0, bundleExtra);
                    }
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, "Failed to start target activity.", e);
                    throw new RuntimeException(e);
                }
            } else {
                startIntentSenderForResult(pendingIntent.getIntentSender(), 0, null, 268435456, 0, 0);
            }
        }

        @Override // android.app.Activity
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putBoolean(IS_TARGET_ACTIVITY_STARTED_KEY, this.isTargetActivityStarted);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class EmptyActivity extends Activity {
        private final BroadcastReceiver receiver = new BroadcastReceiver() { // from class: androidx.test.core.app.InstrumentationActivityInvoker.EmptyActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                EmptyActivity.this.finish();
            }
        };

        @Override // android.app.Activity
        public void finish() {
            super.finish();
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        public void onCreate(@Nullable Bundle bundle) {
            super.onCreate(bundle);
            registerReceiver(this.receiver, new IntentFilter(InstrumentationActivityInvoker.FINISH_EMPTY_ACTIVITIES));
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        public void onDestroy() {
            super.onDestroy();
            unregisterReceiver(this.receiver);
        }

        @Override // android.app.Activity
        public void onResume() {
            super.onResume();
            sendBroadcast(new Intent(InstrumentationActivityInvoker.EMPTY_ACTIVITY_RESUMED));
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class EmptyFloatingActivity extends Activity {
        private final BroadcastReceiver receiver = new BroadcastReceiver() { // from class: androidx.test.core.app.InstrumentationActivityInvoker.EmptyFloatingActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                EmptyFloatingActivity.this.finish();
            }
        };

        @Override // android.app.Activity
        public void finish() {
            super.finish();
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        public void onCreate(@Nullable Bundle bundle) {
            super.onCreate(bundle);
            registerReceiver(this.receiver, new IntentFilter(InstrumentationActivityInvoker.FINISH_EMPTY_ACTIVITIES));
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        public void onDestroy() {
            super.onDestroy();
            unregisterReceiver(this.receiver);
        }

        @Override // android.app.Activity
        public void onResume() {
            super.onResume();
            sendBroadcast(new Intent(InstrumentationActivityInvoker.EMPTY_FLOATING_ACTIVITY_RESUMED));
        }
    }

    private static void checkActivityStageIsIn(Activity activity, Stage... stageArr) {
        checkActivityStageIsIn(activity, new HashSet(Arrays.asList(stageArr)));
    }

    public static final /* synthetic */ void lambda$checkActivityStageIsIn$3$InstrumentationActivityInvoker(Activity activity, Set set) {
        Stage lifecycleStageOf = ActivityLifecycleMonitorRegistry.getInstance().getLifecycleStageOf(activity);
        Checks.checkState(set.contains(lifecycleStageOf), "Activity's stage must be %s but was %s", set, lifecycleStageOf);
    }

    private void startEmptyActivitySync() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver(this) { // from class: androidx.test.core.app.InstrumentationActivityInvoker.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                countDownLatch.countDown();
            }
        };
        ApplicationProvider.getApplicationContext().registerReceiver(broadcastReceiver, new IntentFilter(EMPTY_ACTIVITY_RESUMED));
        ApplicationProvider.getApplicationContext().startActivity(getIntentForActivity(EmptyActivity.class).setFlags(268435456));
        try {
            try {
                countDownLatch.await(ActivityLifecycleTimeout.getMillis(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new AssertionError("Failed to stop activity", e);
            }
        } finally {
            ApplicationProvider.getApplicationContext().unregisterReceiver(broadcastReceiver);
        }
    }

    private void startFloatingEmptyActivitySync() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver(this) { // from class: androidx.test.core.app.InstrumentationActivityInvoker.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                countDownLatch.countDown();
            }
        };
        ApplicationProvider.getApplicationContext().registerReceiver(broadcastReceiver, new IntentFilter(EMPTY_FLOATING_ACTIVITY_RESUMED));
        ApplicationProvider.getApplicationContext().startActivity(getIntentForActivity(EmptyFloatingActivity.class).setFlags(268435456));
        try {
            try {
                countDownLatch.await(ActivityLifecycleTimeout.getMillis(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new AssertionError("Failed to pause activity", e);
            }
        } finally {
            ApplicationProvider.getApplicationContext().unregisterReceiver(broadcastReceiver);
        }
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void finishActivity(final Activity activity) {
        startEmptyActivitySync();
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable(activity) { // from class: androidx.test.core.app.InstrumentationActivityInvoker$$Lambda$1
            private final Activity arg$1;

            {
                this.arg$1 = activity;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.arg$1.finish();
            }
        });
        ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_BOOTSTRAP_ACTIVITY));
        startEmptyActivitySync();
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable(activity) { // from class: androidx.test.core.app.InstrumentationActivityInvoker$$Lambda$2
            private final Activity arg$1;

            {
                this.arg$1 = activity;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.arg$1.finish();
            }
        });
        ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_EMPTY_ACTIVITIES));
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public Instrumentation.ActivityResult getActivityResult() {
        return ((ActivityResultWaiter) Checks.checkNotNull(this.activityResultWaiter, "You must start Activity first")).getActivityResult();
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public Intent getIntentForActivity(Class cls) {
        return ActivityInvoker$$CC.getIntentForActivity$$dflt$$(this, cls);
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void pauseActivity(Activity activity) {
        checkActivityStageIsIn(activity, Stage.RESUMED, Stage.PAUSED);
        startFloatingEmptyActivitySync();
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void recreateActivity(final Activity activity) {
        checkActivityStageIsIn(activity, Stage.RESUMED, Stage.PAUSED, Stage.STOPPED);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable(activity) { // from class: androidx.test.core.app.InstrumentationActivityInvoker$$Lambda$0
            private final Activity arg$1;

            {
                this.arg$1 = activity;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.arg$1.recreate();
            }
        });
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void resumeActivity(Activity activity) {
        checkActivityStageIsIn(activity, Stage.RESUMED, Stage.PAUSED, Stage.STOPPED);
        ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_EMPTY_ACTIVITIES));
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void startActivity(Intent intent, @Nullable Bundle bundle) {
        if (intent.resolveActivityInfo(ApplicationProvider.getApplicationContext().getPackageManager(), 0) != null) {
            ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_BOOTSTRAP_ACTIVITY));
            ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_EMPTY_ACTIVITIES));
            this.activityResultWaiter = new ActivityResultWaiter(ApplicationProvider.getApplicationContext());
            ApplicationProvider.getApplicationContext().startActivity(getIntentForActivity(BootstrapActivity.class).setFlags(268468224).putExtra(TARGET_ACTIVITY_INTENT_KEY, PendingIntent.getActivity(ApplicationProvider.getApplicationContext(), 0, intent, 134217728)).putExtra(TARGET_ACTIVITY_OPTIONS_BUNDLE_KEY, bundle));
            return;
        }
        String strValueOf = String.valueOf(intent);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 32);
        sb.append("Unable to resolve activity for: ");
        sb.append(strValueOf);
        throw new RuntimeException(sb.toString());
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void stopActivity(Activity activity) {
        checkActivityStageIsIn(activity, Stage.RESUMED, Stage.PAUSED, Stage.STOPPED);
        startEmptyActivitySync();
    }

    private static void checkActivityStageIsIn(final Activity activity, final Set<Stage> set) {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable(activity, set) { // from class: androidx.test.core.app.InstrumentationActivityInvoker$$Lambda$3
            private final Activity arg$1;
            private final Set arg$2;

            {
                this.arg$1 = activity;
                this.arg$2 = set;
            }

            @Override // java.lang.Runnable
            public void run() {
                InstrumentationActivityInvoker.lambda$checkActivityStageIsIn$3$InstrumentationActivityInvoker(this.arg$1, this.arg$2);
            }
        });
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void startActivity(Intent intent) {
        startActivity(intent, null);
    }
}
