package com.amazon.mp3.config;

import android.content.Context;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.det.AutoLogUploader;
import com.amazon.mp3.util.Log;
import com.amazon.music.logger.AmazonMusicMarketLogger;
import java.util.Date;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class SystemConfigTask {
    private static final String TAG = "SystemConfigTask";
    private Context mContext;

    public SystemConfigTask(final Context context) {
        this.mContext = context;
    }

    public void handleTask() {
        AmazonMusicMarketLogger.setSystemLogLevel(SystemConfigRetriever.getSystemLogLevel());
        AmazonMusicMarketLogger.setLogLevelTagMap(SystemConfigRetriever.getLogLevelTagMap());
        if (AccountCredentialStorage.get(this.mContext).getDeviceId() != null) {
            submitLogsIfNeeded();
        } else {
            BootstrapSingletonTask.get().registerUserObserver().observeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.config.SystemConfigTask$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$handleTask$0((Void) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.config.SystemConfigTask$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    SystemConfigTask.lambda$handleTask$1((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleTask$0(Void r1) {
        submitLogsIfNeeded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleTask$1(Throwable th) {
        Log.debug(TAG, "Error checking if needed to auto-submit logs ");
    }

    private void submitLogsIfNeeded() {
        AutoLogUploader autoLogUploader = new AutoLogUploader();
        Date uploadLogRequestDate = SystemConfigRetriever.getUploadLogRequestDate(AccountCredentialStorage.get(this.mContext).getDeviceId());
        if (uploadLogRequestDate != null) {
            autoLogUploader.uploadLogsIfRequired(this.mContext, uploadLogRequestDate);
        }
    }
}
