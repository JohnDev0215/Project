package com.amazon.mp3.init;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.det.NativeCrashReporter;
import com.amazon.mp3.util.Log;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import rx.Scheduler;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: HarleyTombstoneTask.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u000e\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000\u001a\u0018\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¨\u0006\u0007"}, d2 = {"Landroid/content/Context;", "context", "", "uploadHarleyTombstones", "Lrx/Scheduler;", "scheduler", "uploadHarleyTombstonesInternal", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0})
@JvmName(name = "HarleyTombstoneTask")
public final class HarleyTombstoneTask {
    public static final void uploadHarleyTombstones(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Scheduler schedulerIo = Schedulers.io();
        Intrinsics.checkNotNullExpressionValue(schedulerIo, "io()");
        uploadHarleyTombstonesInternal(context, schedulerIo);
    }

    @VisibleForTesting
    public static final void uploadHarleyTombstonesInternal(final Context context, Scheduler scheduler) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scheduler, "scheduler");
        BootstrapSingletonTask.get().registerUserObserver().delay(5L, TimeUnit.SECONDS, scheduler).subscribe(new Action1() { // from class: com.amazon.mp3.init.HarleyTombstoneTask$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                HarleyTombstoneTask.m1214uploadHarleyTombstonesInternal$lambda0(context, (Void) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.init.HarleyTombstoneTask$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                HarleyTombstoneTask.m1215uploadHarleyTombstonesInternal$lambda1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: uploadHarleyTombstonesInternal$lambda-0, reason: not valid java name */
    public static final void m1214uploadHarleyTombstonesInternal$lambda0(Context context, Void r1) {
        Intrinsics.checkNotNullParameter(context, "$context");
        NativeCrashReporter.uploadHarleyTombstones(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: uploadHarleyTombstonesInternal$lambda-1, reason: not valid java name */
    public static final void m1215uploadHarleyTombstonesInternal$lambda1(Throwable th) {
        Log.warning("HarleyTombstoneTask", Intrinsics.stringPlus("BootstrapSingletonTask error. Could not complete Harley tombstones: ", th.getMessage()));
    }
}
