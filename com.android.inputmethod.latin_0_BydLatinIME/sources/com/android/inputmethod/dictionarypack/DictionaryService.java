package com.android.inputmethod.dictionarypack;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.android.inputmethod.latin.BinaryDictionaryFileDumper;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.LocaleUtils;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class DictionaryService extends Service {
    private static final String DATE_CHANGED_INTENT_ACTION = "android.intent.action.DATE_CHANGED";
    static final String LOCALE_INTENT_ARGUMENT = "locale";
    private static final String PACKAGE_NAME = "com.android.inputmethod.latin";
    static final String SHOW_DOWNLOAD_TOAST_INTENT_ACTION = "com.android.inputmethod.latin.SHOW_DOWNLOAD_TOAST_INTENT_ACTION";
    private static final String TAG = "DictionaryService";
    private static final int WORKER_THREAD_TIMEOUT_SECONDS = 15;
    private ThreadPoolExecutor mExecutor;
    private static final long UPDATE_FREQUENCY_MILLIS = TimeUnit.DAYS.toMillis(4);
    private static final int MAX_ALARM_DELAY_MILLIS = (int) TimeUnit.HOURS.toMillis(6);
    private static final long VERY_LONG_TIME_MILLIS = TimeUnit.DAYS.toMillis(14);
    public static final long NO_CANCEL_DOWNLOAD_PERIOD_MILLIS = TimeUnit.SECONDS.toMillis(30);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
    }

    @Override // android.app.Service
    public void onCreate() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.mExecutor = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    @Override // android.app.Service
    public synchronized int onStartCommand(final Intent intent, final int flags, final int startId) {
        if (SHOW_DOWNLOAD_TOAST_INTENT_ACTION.equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("locale");
            if (stringExtra == null) {
                Log.e(TAG, "Received " + intent.getAction() + " without locale; skipped");
            } else {
                showStartDownloadingToast(this, LocaleUtils.constructLocaleFromString(stringExtra));
            }
        } else {
            this.mExecutor.submit(new Runnable() { // from class: com.android.inputmethod.dictionarypack.DictionaryService.1
                @Override // java.lang.Runnable
                public void run() throws Throwable {
                    DictionaryService.dispatchBroadcast(this, intent);
                    DictionaryService.this.stopSelfResult(startId);
                }
            });
        }
        return 3;
    }

    static void dispatchBroadcast(final Context context, final Intent intent) throws Throwable {
        String action = intent.getAction();
        if (DATE_CHANGED_INTENT_ACTION.equals(action)) {
            checkTimeAndMaybeSetupUpdateAlarm(context);
            return;
        }
        if (DictionaryPackConstants.UPDATE_NOW_INTENT_ACTION.equals(action)) {
            UpdateHandler.tryUpdate(context);
        } else if (DictionaryPackConstants.INIT_AND_UPDATE_NOW_INTENT_ACTION.equals(action)) {
            BinaryDictionaryFileDumper.initializeClientRecordHelper(context, context.getString(R.string.dictionary_pack_client_id));
            UpdateHandler.tryUpdate(context);
        } else {
            UpdateHandler.downloadFinished(context, intent);
        }
    }

    private static void checkTimeAndMaybeSetupUpdateAlarm(final Context context) {
        if (isLastUpdateAtLeastThisOld(context, UPDATE_FREQUENCY_MILLIS)) {
            PrivateLog.log("Date changed - registering alarm");
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            long jCurrentTimeMillis = System.currentTimeMillis() + ((long) new Random().nextInt(MAX_ALARM_DELAY_MILLIS));
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(DictionaryPackConstants.UPDATE_NOW_INTENT_ACTION), SuggestedWords.SuggestedWordInfo.KIND_FLAG_APPROPRIATE_FOR_AUTO_CORRECTION);
            if (alarmManager != null) {
                alarmManager.set(1, jCurrentTimeMillis, broadcast);
            }
        }
    }

    private static boolean isLastUpdateAtLeastThisOld(final Context context, final long time) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long oldestUpdateTime = MetadataDbHelper.getOldestUpdateTime(context);
        PrivateLog.log("Last update was " + oldestUpdateTime);
        return oldestUpdateTime + time < jCurrentTimeMillis;
    }

    public static void updateNowIfNotUpdatedInAVeryLongTime(final Context context) {
        if (isLastUpdateAtLeastThisOld(context, VERY_LONG_TIME_MILLIS)) {
            UpdateHandler.tryUpdate(context);
        }
    }

    private static void showStartDownloadingToast(final Context context, @Nonnull final Locale locale) {
        Toast.makeText(context, String.format(context.getString(R.string.toast_downloading_suggestions), locale.getDisplayName()), 1).show();
    }
}
