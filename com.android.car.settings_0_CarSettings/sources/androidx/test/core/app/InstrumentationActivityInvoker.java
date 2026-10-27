package androidx.test.core.app;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.test.internal.platform.app.ActivityInvoker;
import androidx.test.internal.platform.app.ActivityInvoker$$CC;
import androidx.test.internal.platform.app.ActivityLifecycleTimeout;
import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;
import com.android.car.ui.utils.RotaryConstants;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
class InstrumentationActivityInvoker implements ActivityInvoker {
    private static final String BOOTSTRAP_ACTIVITY_RESULT_CODE_KEY = "androidx.test.core.app.InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_CODE_KEY";
    private static final String BOOTSTRAP_ACTIVITY_RESULT_DATA_KEY = "androidx.test.core.app.InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_DATA_KEY";
    private static final String BOOTSTRAP_ACTIVITY_RESULT_RECEIVED = "androidx.test.core.app.InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_RECEIVED";
    private static final String EMPTY_ACTIVITY_RESUMED = "androidx.test.core.app.InstrumentationActivityInvoker.EMPTY_ACTIVITY_RESUMED";
    private static final String EMPTY_FLOATING_ACTIVITY_RESUMED = "androidx.test.core.app.InstrumentationActivityInvoker.EMPTY_FLOATING_ACTIVITY_RESUMED";
    private static final String FINISH_BOOTSTRAP_ACTIVITY = "androidx.test.core.app.InstrumentationActivityInvoker.FINISH_BOOTSTRAP_ACTIVITY";
    private static final String FINISH_EMPTY_ACTIVITIES = "androidx.test.core.app.InstrumentationActivityInvoker.FINISH_EMPTY_ACTIVITIES";
    private static final String TARGET_ACTIVITY_INTENT_KEY = "androidx.test.core.app.InstrumentationActivityInvoker.START_TARGET_ACTIVITY_INTENT_KEY";
    private static final String TARGET_ACTIVITY_OPTIONS_BUNDLE_KEY = "androidx.test.core.app.InstrumentationActivityInvoker.TARGET_ACTIVITY_OPTIONS_BUNDLE_KEY";
    private ActivityResultWaiter activityResultWaiter;

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public Intent getIntentForActivity(Class cls) {
        return ActivityInvoker$$CC.getIntentForActivity$$dflt$$(this, cls);
    }

    InstrumentationActivityInvoker() {
    }

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
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            registerReceiver(this.receiver, new IntentFilter(InstrumentationActivityInvoker.FINISH_BOOTSTRAP_ACTIVITY));
            this.isTargetActivityStarted = savedInstanceState != null && savedInstanceState.getBoolean(IS_TARGET_ACTIVITY_STARTED_KEY, false);
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        public void finish() {
            super.finish();
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        protected void onResume() {
            super.onResume();
            if (!this.isTargetActivityStarted) {
                this.isTargetActivityStarted = true;
                PendingIntent startTargetActivityIntent = (PendingIntent) Checks.checkNotNull((PendingIntent) getIntent().getParcelableExtra(InstrumentationActivityInvoker.TARGET_ACTIVITY_INTENT_KEY));
                Bundle options = getIntent().getBundleExtra(InstrumentationActivityInvoker.TARGET_ACTIVITY_OPTIONS_BUNDLE_KEY);
                if (options == null) {
                    startIntentSenderForResult(startTargetActivityIntent.getIntentSender(), 0, null, 268435456, 0, 0);
                } else {
                    try {
                        if (Build.VERSION.SDK_INT < 16) {
                            startIntentSenderForResult(startTargetActivityIntent.getIntentSender(), 0, null, 268435456, 0, 0);
                        } else {
                            startIntentSenderForResult(startTargetActivityIntent.getIntentSender(), 0, null, 268435456, 0, 0, options);
                        }
                    } catch (IntentSender.SendIntentException e) {
                        Log.e(TAG, "Failed to start target activity.", e);
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        @Override // android.app.Activity
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putBoolean(IS_TARGET_ACTIVITY_STARTED_KEY, this.isTargetActivityStarted);
        }

        @Override // android.app.Activity
        protected void onDestroy() {
            super.onDestroy();
            unregisterReceiver(this.receiver);
        }

        @Override // android.app.Activity
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == 0) {
                Intent activityResultReceivedActionIntent = new Intent(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_RECEIVED);
                activityResultReceivedActionIntent.putExtra(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_CODE_KEY, resultCode);
                if (data != null) {
                    activityResultReceivedActionIntent.putExtra(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_DATA_KEY, data);
                }
                sendBroadcast(activityResultReceivedActionIntent);
                finish();
            }
        }
    }

    private static class ActivityResultWaiter {
        private static final String TAG = ActivityResultWaiter.class.getName();
        private Instrumentation.ActivityResult activityResult;
        private final CountDownLatch latch = new CountDownLatch(1);

        public ActivityResultWaiter(Context context) {
            BroadcastReceiver receiver = new BroadcastReceiver() { // from class: androidx.test.core.app.InstrumentationActivityInvoker.ActivityResultWaiter.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    context2.unregisterReceiver(this);
                    int resultCode = intent.getIntExtra(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_CODE_KEY, 0);
                    Intent resultData = (Intent) intent.getParcelableExtra(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_DATA_KEY);
                    if (resultData != null) {
                        resultData = new Intent(resultData);
                    }
                    ActivityResultWaiter.this.activityResult = new Instrumentation.ActivityResult(resultCode, resultData);
                    ActivityResultWaiter.this.latch.countDown();
                }
            };
            context.registerReceiver(receiver, new IntentFilter(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_RECEIVED));
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

    public static class EmptyActivity extends Activity {
        private final BroadcastReceiver receiver = new BroadcastReceiver() { // from class: androidx.test.core.app.InstrumentationActivityInvoker.EmptyActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                EmptyActivity.this.finish();
            }
        };

        @Override // android.app.Activity
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            registerReceiver(this.receiver, new IntentFilter(InstrumentationActivityInvoker.FINISH_EMPTY_ACTIVITIES));
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        public void finish() {
            super.finish();
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        protected void onResume() {
            super.onResume();
            sendBroadcast(new Intent(InstrumentationActivityInvoker.EMPTY_ACTIVITY_RESUMED));
        }

        @Override // android.app.Activity
        protected void onDestroy() {
            super.onDestroy();
            unregisterReceiver(this.receiver);
        }
    }

    public static class EmptyFloatingActivity extends Activity {
        private final BroadcastReceiver receiver = new BroadcastReceiver() { // from class: androidx.test.core.app.InstrumentationActivityInvoker.EmptyFloatingActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                EmptyFloatingActivity.this.finish();
            }
        };

        @Override // android.app.Activity
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            registerReceiver(this.receiver, new IntentFilter(InstrumentationActivityInvoker.FINISH_EMPTY_ACTIVITIES));
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        public void finish() {
            super.finish();
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        protected void onResume() {
            super.onResume();
            sendBroadcast(new Intent(InstrumentationActivityInvoker.EMPTY_FLOATING_ACTIVITY_RESUMED));
        }

        @Override // android.app.Activity
        protected void onDestroy() {
            super.onDestroy();
            unregisterReceiver(this.receiver);
        }
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void startActivity(Intent intent, Bundle activityOptions) {
        ActivityInfo ai = intent.resolveActivityInfo(ApplicationProvider.getApplicationContext().getPackageManager(), 0);
        if (ai == null) {
            String strValueOf = String.valueOf(intent);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 32);
            sb.append("Unable to resolve activity for: ");
            sb.append(strValueOf);
            throw new RuntimeException(sb.toString());
        }
        ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_BOOTSTRAP_ACTIVITY));
        ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_EMPTY_ACTIVITIES));
        this.activityResultWaiter = new ActivityResultWaiter(ApplicationProvider.getApplicationContext());
        ApplicationProvider.getApplicationContext().startActivity(getIntentForActivity(BootstrapActivity.class).setFlags(268468224).putExtra(TARGET_ACTIVITY_INTENT_KEY, PendingIntent.getActivity(ApplicationProvider.getApplicationContext(), 0, intent, RotaryConstants.ACTION_HIDE_IME)).putExtra(TARGET_ACTIVITY_OPTIONS_BUNDLE_KEY, activityOptions));
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void startActivity(Intent intent) {
        startActivity(intent, null);
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public Instrumentation.ActivityResult getActivityResult() {
        return ((ActivityResultWaiter) Checks.checkNotNull(this.activityResultWaiter, "You must start Activity first")).getActivityResult();
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void resumeActivity(Activity activity) {
        checkActivityStageIsIn(activity, Stage.RESUMED, Stage.PAUSED, Stage.STOPPED);
        ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_EMPTY_ACTIVITIES));
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void pauseActivity(Activity activity) {
        checkActivityStageIsIn(activity, Stage.RESUMED, Stage.PAUSED);
        startFloatingEmptyActivitySync();
    }

    private void startFloatingEmptyActivitySync() {
        final CountDownLatch latch = new CountDownLatch(1);
        BroadcastReceiver receiver = new BroadcastReceiver(this) { // from class: androidx.test.core.app.InstrumentationActivityInvoker.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                latch.countDown();
            }
        };
        ApplicationProvider.getApplicationContext().registerReceiver(receiver, new IntentFilter(EMPTY_FLOATING_ACTIVITY_RESUMED));
        ApplicationProvider.getApplicationContext().startActivity(getIntentForActivity(EmptyFloatingActivity.class).setFlags(268435456));
        try {
            try {
                latch.await(ActivityLifecycleTimeout.getMillis(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new AssertionError("Failed to pause activity", e);
            }
        } finally {
            ApplicationProvider.getApplicationContext().unregisterReceiver(receiver);
        }
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void stopActivity(Activity activity) {
        checkActivityStageIsIn(activity, Stage.RESUMED, Stage.PAUSED, Stage.STOPPED);
        startEmptyActivitySync();
    }

    private void startEmptyActivitySync() {
        final CountDownLatch latch = new CountDownLatch(1);
        BroadcastReceiver receiver = new BroadcastReceiver(this) { // from class: androidx.test.core.app.InstrumentationActivityInvoker.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                latch.countDown();
            }
        };
        ApplicationProvider.getApplicationContext().registerReceiver(receiver, new IntentFilter(EMPTY_ACTIVITY_RESUMED));
        ApplicationProvider.getApplicationContext().startActivity(getIntentForActivity(EmptyActivity.class).setFlags(268435456));
        try {
            try {
                latch.await(ActivityLifecycleTimeout.getMillis(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new AssertionError("Failed to stop activity", e);
            }
        } finally {
            ApplicationProvider.getApplicationContext().unregisterReceiver(receiver);
        }
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

    private static void checkActivityStageIsIn(Activity activity, Stage... expected) {
        checkActivityStageIsIn(activity, new HashSet(Arrays.asList(expected)));
    }

    private static void checkActivityStageIsIn(final Activity activity, final Set<Stage> expected) {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable(activity, expected) { // from class: androidx.test.core.app.InstrumentationActivityInvoker$$Lambda$3
            private final Activity arg$1;
            private final Set arg$2;

            {
                this.arg$1 = activity;
                this.arg$2 = expected;
            }

            @Override // java.lang.Runnable
            public void run() {
                InstrumentationActivityInvoker.lambda$checkActivityStageIsIn$3$InstrumentationActivityInvoker(this.arg$1, this.arg$2);
            }
        });
    }

    static final /* synthetic */ void lambda$checkActivityStageIsIn$3$InstrumentationActivityInvoker(Activity activity, Set expected) {
        Stage stage = ActivityLifecycleMonitorRegistry.getInstance().getLifecycleStageOf(activity);
        Checks.checkState(expected.contains(stage), "Activity's stage must be %s but was %s", expected, stage);
    }
}
