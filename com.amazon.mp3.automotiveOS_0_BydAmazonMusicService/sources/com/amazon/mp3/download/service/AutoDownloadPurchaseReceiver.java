package com.amazon.mp3.download.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.download.service.AutoDownloadThread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public final class AutoDownloadPurchaseReceiver extends BroadcastReceiver {
    ExecutorService mExecutor = new ThreadPoolExecutor(0, 1, 1, TimeUnit.MINUTES, new SynchronousQueue());

    public static void setComponentEnabled(final Context context, final boolean enabled) {
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, (Class<?>) AutoDownloadPurchaseReceiver.class), enabled ? 1 : 2, 1);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        int intExtra = intent.getIntExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_EVENT_TYPE", 0);
        int intExtra2 = intent.getIntExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_FLAGS", 0);
        if (intExtra != 5 || (intExtra2 & 2) == 0) {
            return;
        }
        this.mExecutor.submit(new AutoDownloadThread(context, AutoDownloadThread.DownloadRestrictionCriteria.AFTER_OR_EQUAL_LAST_DATE, false));
    }
}
