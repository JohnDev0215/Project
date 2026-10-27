package com.amazon.mp3.performance;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.amazon.identity.auth.device.AuthPortalUIActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.account.activity.SignInFlowLauncherActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.firebase.FirebaseCustomTraces;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.launchlatencyinfo.LaunchLatencyCode;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.perf.ktx.PerformanceKt;
import com.google.firebase.perf.metrics.Trace;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppStartTracer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001+B\t\b\u0002¢\u0006\u0004\b)\u0010*J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\u0016\u0010\f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nJ\u001a\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0018\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u000fH\u0016J\u0010\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u0019\u001a\u00020\u0005H\u0007R\u001c\u0010\u001c\u001a\n \u001b*\u0004\u0018\u00010\u001a0\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010!R\u0016\u0010\"\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010 R\u0016\u0010#\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010 R\u0016\u0010$\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010!R\u0016\u0010\u0004\u001a\u00020\b8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0004\u0010%R\u0018\u0010'\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(¨\u0006,"}, d2 = {"Lcom/amazon/mp3/performance/AppStartTracer;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "Landroidx/lifecycle/LifecycleObserver;", "Landroid/app/Application;", "appContext", "", "registerActivityLifecycleCallbacks", "unregisterActivityLifecycleCallbacks", "Landroid/content/Context;", "context", "", "isFirebaseInitialized", "onColdStartInitiated", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/os/Bundle;", "savedInstanceState", "onActivityCreated", "onActivityStarted", "onActivityResumed", "onActivityPaused", "onActivityStopped", "outState", "onActivitySaveInstanceState", "onActivityDestroyed", "onEnterBackground", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "MAX_APP_START_TIME_THRESHOLD", "J", "Z", "appStartTime", "onCreateTime", "isRegisteredForLifecycleCallbacks", "Landroid/content/Context;", "Lcom/google/firebase/perf/metrics/Trace;", "coldStartTrace", "Lcom/google/firebase/perf/metrics/Trace;", "<init>", "()V", "StartFromBackgroundRunnable", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AppStartTracer implements Application.ActivityLifecycleCallbacks, LifecycleObserver {
    private static Context appContext;
    private static Trace coldStartTrace;
    private static boolean isFirebaseInitialized;
    private static boolean isRegisteredForLifecycleCallbacks;
    public static final AppStartTracer INSTANCE = new AppStartTracer();
    private static final String TAG = AppStartTracer.class.getSimpleName();
    private static final long MAX_APP_START_TIME_THRESHOLD = TimeUnit.SECONDS.toMillis(30);
    private static long appStartTime = -1;
    private static long onCreateTime = -1;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(outState, "outState");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    private AppStartTracer() {
    }

    public final void onColdStartInitiated(Context context, boolean isFirebaseInitialized2) {
        Intrinsics.checkNotNullParameter(context, "context");
        appStartTime = System.currentTimeMillis();
        isFirebaseInitialized = isFirebaseInitialized2;
        if (isFirebaseInitialized2) {
            Trace traceNewTrace = PerformanceKt.getPerformance(Firebase.INSTANCE).newTrace(FirebaseCustomTraces.COLD_STARTUP_TIME.name());
            coldStartTrace = traceNewTrace;
            if (traceNewTrace != null) {
                traceNewTrace.start();
            }
        } else {
            Log.debug(TAG, "firebase app not initialized");
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof Application) {
            registerActivityLifecycleCallbacks((Application) applicationContext);
            appContext = applicationContext;
        }
    }

    private final void registerActivityLifecycleCallbacks(Application appContext2) {
        Log.debug(TAG, "registerActivityLifecycleCallbacks");
        appContext2.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        isRegisteredForLifecycleCallbacks = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unregisterActivityLifecycleCallbacks() {
        Log.debug(TAG, "unregisterActivityLifecycleCallbacks");
        if (isRegisteredForLifecycleCallbacks) {
            Context context = appContext;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("appContext");
                context = null;
            }
            ((Application) context).unregisterActivityLifecycleCallbacks(this);
            ProcessLifecycleOwner.get().getLifecycle().removeObserver(this);
            isRegisteredForLifecycleCallbacks = false;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Log.debug(TAG, "onActivityCreated " + activity + " onCreateTime = " + onCreateTime);
        onCreateTime = System.currentTimeMillis();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Log.debug(TAG, Intrinsics.stringPlus("onActivityStarted ", activity));
        if ((activity instanceof SignInFlowLauncherActivity) || (activity instanceof AuthPortalUIActivity)) {
            unregisterActivityLifecycleCallbacks();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        String str = TAG;
        Log.debug(str, Intrinsics.stringPlus("onActivityResumed ", activity));
        if (activity instanceof MusicHomeActivity) {
            if (isFirebaseInitialized) {
                Trace trace = coldStartTrace;
                if (trace != null) {
                    trace.stop();
                }
                coldStartTrace = null;
            }
            long jCurrentTimeMillis = System.currentTimeMillis() - appStartTime;
            if (jCurrentTimeMillis < MAX_APP_START_TIME_THRESHOLD) {
                LatencyTracker latencyTracker = LatencyTracker.INSTANCE;
                LaunchLatencyCode launchLatencyCode = LaunchLatencyCode.COLD_START;
                latencyTracker.emitLatencyEvent(launchLatencyCode.getLatencyCode(), launchLatencyCode.getOperation(), launchLatencyCode.getSubtype(), jCurrentTimeMillis, null);
            } else {
                Log.error(str, "Skipping emitting cold start event as it's over threshold'");
            }
            if (isRegisteredForLifecycleCallbacks) {
                unregisterActivityLifecycleCallbacks();
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public final void onEnterBackground() {
        Log.debug(TAG, "onEnterBackground");
        unregisterActivityLifecycleCallbacks();
    }

    /* JADX INFO: compiled from: AppStartTracer.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/performance/AppStartTracer$StartFromBackgroundRunnable;", "Ljava/lang/Runnable;", "()V", "run", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class StartFromBackgroundRunnable implements Runnable {
        public static final StartFromBackgroundRunnable INSTANCE = new StartFromBackgroundRunnable();

        private StartFromBackgroundRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.debug(AppStartTracer.TAG, Intrinsics.stringPlus("StartFromBackgroundRunnable onCreateTime = ", Long.valueOf(AppStartTracer.onCreateTime)));
            if (AppStartTracer.onCreateTime == -1) {
                AppStartTracer.INSTANCE.unregisterActivityLifecycleCallbacks();
            }
        }
    }
}
