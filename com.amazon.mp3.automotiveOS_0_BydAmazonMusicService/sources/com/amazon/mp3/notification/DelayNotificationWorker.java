package com.amazon.mp3.notification;

import android.content.Context;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DelayNotificationWorker.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/notification/DelayNotificationWorker;", "Landroidx/work/Worker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DelayNotificationWorker extends Worker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DelayNotificationWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        EarlyUseNotificationHandler earlyUseNotificationHandler = EarlyUseNotificationHandler.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        Data inputData = getInputData();
        Intrinsics.checkNotNullExpressionValue(inputData, "inputData");
        earlyUseNotificationHandler.startNotification(applicationContext, inputData);
        ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(resultSuccess, "success()");
        return resultSuccess;
    }
}
