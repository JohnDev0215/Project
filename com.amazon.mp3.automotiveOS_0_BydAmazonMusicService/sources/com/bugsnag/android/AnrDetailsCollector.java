package com.bugsnag.android;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import androidx.core.app.NotificationCompat;
import com.amazon.device.crashmanager.CrashManagerActions;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: AnrDetailsCollector.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000bJ!\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0001¢\u0006\u0002\b\u0011J\u0017\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015J\u001d\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/bugsnag/android/AnrDetailsCollector;", "", "()V", "handlerThread", "Landroid/os/HandlerThread;", "addErrorStateInfo", "", NotificationCompat.CATEGORY_EVENT, "Lcom/bugsnag/android/Event;", "anrState", "Landroid/app/ActivityManager$ProcessErrorStateInfo;", "addErrorStateInfo$bugsnag_plugin_android_anr_release", "captureProcessErrorState", "am", "Landroid/app/ActivityManager;", "pid", "", "captureProcessErrorState$bugsnag_plugin_android_anr_release", "collectAnrDetails", "ctx", "Landroid/content/Context;", "collectAnrDetails$bugsnag_plugin_android_anr_release", "collectAnrErrorDetails", SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, "Lcom/bugsnag/android/Client;", "collectAnrErrorDetails$bugsnag_plugin_android_anr_release", "Companion", "bugsnag-plugin-android-anr_release"}, k = 1, mv = {1, 4, 2})
public final class AnrDetailsCollector {
    private final HandlerThread handlerThread;

    public AnrDetailsCollector() {
        HandlerThread handlerThread = new HandlerThread("bugsnag-anr-collector");
        this.handlerThread = handlerThread;
        handlerThread.start();
    }

    public final ActivityManager.ProcessErrorStateInfo collectAnrDetails$bugsnag_plugin_android_anr_release(Context ctx) {
        Object objM2810constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            Object systemService = ctx.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
            if (!(systemService instanceof ActivityManager)) {
                systemService = null;
            }
            objM2810constructorimpl = Result.m2810constructorimpl((ActivityManager) systemService);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(ResultKt.createFailure(th));
        }
        return captureProcessErrorState$bugsnag_plugin_android_anr_release((ActivityManager) (Result.m2816isFailureimpl(objM2810constructorimpl) ? null : objM2810constructorimpl), Process.myPid());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000a A[Catch: RuntimeException -> 0x002d, TryCatch #0 {RuntimeException -> 0x002d, blocks: (B:4:0x0003, B:8:0x000e, B:9:0x0012, B:11:0x0018, B:18:0x002a, B:7:0x000a), top: B:22:0x0003 }] */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.app.ActivityManager.ProcessErrorStateInfo captureProcessErrorState$bugsnag_plugin_android_anr_release(android.app.ActivityManager r4, int r5) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto La
            java.util.List r4 = r4.getProcessesInErrorState()     // Catch: java.lang.RuntimeException -> L2d
            if (r4 == 0) goto La
            goto Le
        La:
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()     // Catch: java.lang.RuntimeException -> L2d
        Le:
            java.util.Iterator r4 = r4.iterator()     // Catch: java.lang.RuntimeException -> L2d
        L12:
            boolean r1 = r4.hasNext()     // Catch: java.lang.RuntimeException -> L2d
            if (r1 == 0) goto L29
            java.lang.Object r1 = r4.next()     // Catch: java.lang.RuntimeException -> L2d
            r2 = r1
            android.app.ActivityManager$ProcessErrorStateInfo r2 = (android.app.ActivityManager.ProcessErrorStateInfo) r2     // Catch: java.lang.RuntimeException -> L2d
            int r2 = r2.pid     // Catch: java.lang.RuntimeException -> L2d
            if (r2 != r5) goto L25
            r2 = 1
            goto L26
        L25:
            r2 = 0
        L26:
            if (r2 == 0) goto L12
            goto L2a
        L29:
            r1 = r0
        L2a:
            android.app.ActivityManager$ProcessErrorStateInfo r1 = (android.app.ActivityManager.ProcessErrorStateInfo) r1     // Catch: java.lang.RuntimeException -> L2d
            r0 = r1
        L2d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bugsnag.android.AnrDetailsCollector.captureProcessErrorState$bugsnag_plugin_android_anr_release(android.app.ActivityManager, int):android.app.ActivityManager$ProcessErrorStateInfo");
    }

    public final void addErrorStateInfo$bugsnag_plugin_android_anr_release(Event event, ActivityManager.ProcessErrorStateInfo anrState) {
        String msg = anrState.shortMsg;
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(event.getErrors(), "event.errors");
        if (!r8.isEmpty()) {
            Error error = event.getErrors().get(0);
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(error, "event.errors[0]");
            Error error2 = error;
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(msg, "msg");
            if (StringsKt__StringsJVMKt.startsWith$default(msg, CrashManagerActions.ANR, false, 2, null)) {
                msg = StringsKt__StringsJVMKt.replaceFirst$default(msg, CrashManagerActions.ANR, "", false, 4, (Object) null);
            }
            error2.setErrorMessage(msg);
        }
    }

    public final void collectAnrErrorDetails$bugsnag_plugin_android_anr_release(final Client client, final Event event) {
        final Handler handler = new Handler(this.handlerThread.getLooper());
        final AtomicInteger atomicInteger = new AtomicInteger();
        handler.post(new Runnable() { // from class: com.bugsnag.android.AnrDetailsCollector$collectAnrErrorDetails$1
            @Override // java.lang.Runnable
            public void run() {
                AnrDetailsCollector anrDetailsCollector = this.this$0;
                Context context = client.appContext;
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(context, "client.appContext");
                ActivityManager.ProcessErrorStateInfo processErrorStateInfoCollectAnrDetails$bugsnag_plugin_android_anr_release = anrDetailsCollector.collectAnrDetails$bugsnag_plugin_android_anr_release(context);
                if (processErrorStateInfoCollectAnrDetails$bugsnag_plugin_android_anr_release == null) {
                    if (atomicInteger.getAndIncrement() < 300) {
                        handler.postDelayed(this, 100L);
                    }
                } else {
                    this.this$0.addErrorStateInfo$bugsnag_plugin_android_anr_release(event, processErrorStateInfoCollectAnrDetails$bugsnag_plugin_android_anr_release);
                    client.populateAndNotifyAndroidEvent(event, null);
                }
            }
        });
    }
}
