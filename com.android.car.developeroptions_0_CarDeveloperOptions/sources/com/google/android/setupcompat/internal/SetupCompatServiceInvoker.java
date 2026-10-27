package com.google.android.setupcompat.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.setupcompat.ISetupCompatService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public class SetupCompatServiceInvoker {
    private static final long MAX_WAIT_TIME_FOR_CONNECTION_MS = TimeUnit.SECONDS.toMillis(10);

    @SuppressLint({"StaticFieldLeak"})
    private static SetupCompatServiceInvoker instance;
    private final Context context;
    private final ExecutorService loggingExecutor = (ExecutorService) ExecutorProvider.setupCompatServiceInvoker.get();
    private final ExecutorService setupCompatExecutor = (ExecutorService) ExecutorProvider.setupCompatExecutor.get();
    private final long waitTimeInMillisForServiceConnection = MAX_WAIT_TIME_FOR_CONNECTION_MS;

    public void logMetricEvent(final int i, final Bundle bundle) {
        try {
            this.loggingExecutor.execute(new Runnable() { // from class: com.google.android.setupcompat.internal.-$$Lambda$SetupCompatServiceInvoker$GF-PjyrrRlO-rpKTzzcCmBJ0LIo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$logMetricEvent$0$SetupCompatServiceInvoker(i, bundle);
                }
            });
        } catch (RejectedExecutionException e) {
            Log.e("SucServiceInvoker", String.format("Metric of type %d dropped since queue is full.", Integer.valueOf(i)), e);
        }
    }

    public void bindBack(final String str, final Bundle bundle) {
        try {
            this.setupCompatExecutor.execute(new Runnable() { // from class: com.google.android.setupcompat.internal.-$$Lambda$SetupCompatServiceInvoker$VdTwkewDljNbUIk0fy0aGXN1QHk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$bindBack$1$SetupCompatServiceInvoker(str, bundle);
                }
            });
        } catch (RejectedExecutionException e) {
            Log.e("SucServiceInvoker", String.format("Screen %s bind back fail.", str), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: invokeLogMetric, reason: merged with bridge method [inline-methods] */
    public void lambda$logMetricEvent$0$SetupCompatServiceInvoker(int i, Bundle bundle) {
        try {
            ISetupCompatService iSetupCompatService = SetupCompatServiceProvider.get(this.context, this.waitTimeInMillisForServiceConnection, TimeUnit.MILLISECONDS);
            if (iSetupCompatService != null) {
                iSetupCompatService.logMetric(i, bundle, Bundle.EMPTY);
            } else {
                Log.w("SucServiceInvoker", "logMetric failed since service reference is null. Are the permissions valid?");
            }
        } catch (RemoteException | InterruptedException | TimeoutException e) {
            Log.e("SucServiceInvoker", String.format("Exception occurred while trying to log metric = [%s]", bundle), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: invokeBindBack, reason: merged with bridge method [inline-methods] */
    public void lambda$bindBack$1$SetupCompatServiceInvoker(String str, Bundle bundle) {
        try {
            ISetupCompatService iSetupCompatService = SetupCompatServiceProvider.get(this.context, this.waitTimeInMillisForServiceConnection, TimeUnit.MILLISECONDS);
            if (iSetupCompatService != null) {
                iSetupCompatService.validateActivity(str, bundle);
            } else {
                Log.w("SucServiceInvoker", "BindBack failed since service reference is null. Are the permissions valid?");
            }
        } catch (RemoteException | InterruptedException | TimeoutException e) {
            Log.e("SucServiceInvoker", String.format("Exception occurred while %s trying bind back to SetupWizard.", str), e);
        }
    }

    private SetupCompatServiceInvoker(Context context) {
        this.context = context;
    }

    public static synchronized SetupCompatServiceInvoker get(Context context) {
        if (instance == null) {
            instance = new SetupCompatServiceInvoker(context.getApplicationContext());
        }
        return instance;
    }

    static void setInstanceForTesting(SetupCompatServiceInvoker setupCompatServiceInvoker) {
        instance = setupCompatServiceInvoker;
    }
}
