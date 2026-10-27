package com.amazon.mp3.skiplimit.impl;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import com.amazon.mp3.skiplimit.SkipLimitControllerProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.util.ThreadUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HardCodedRefreshStrategy.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\n\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/skiplimit/impl/RefreshSkipCountService;", "Landroid/app/job/JobService;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "onStartJob", "", "params", "Landroid/app/job/JobParameters;", "onStopJob", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefreshSkipCountService extends JobService {
    private final String TAG = RefreshSkipCountService.class.getSimpleName();

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters params) {
        Log.debug(this.TAG, "Stopping refreshing skip count.");
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters params) {
        ThreadUtils.runInBackground(new Runnable() { // from class: com.amazon.mp3.skiplimit.impl.RefreshSkipCountService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RefreshSkipCountService.m1635onStartJob$lambda0(this.f$0);
            }
        });
        HardCodedRefreshStrategy.INSTANCE.startRecurringRefresh(true);
        try {
            jobFinished(params, false);
        } catch (Exception e) {
            Log.error(this.TAG, "cannot stop service due to framework exception ", e);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onStartJob$lambda-0, reason: not valid java name */
    public static final void m1635onStartJob$lambda0(RefreshSkipCountService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.debug(this$0.TAG, "Refreshing skip count to 6.");
        DefaultSkipLimitController controller = SkipLimitControllerProvider.INSTANCE.getController();
        Context applicationContext = this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        controller.onUpdateCount(6, HardCodedRefreshStrategy.REFRESH_REASON, applicationContext);
    }
}
