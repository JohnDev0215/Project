package com.amazon.mp3.voice.wakeword;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.AccountStateCheckActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.activity.util.AccountJobController;
import com.amazon.mp3.service.ServiceUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenWakeLock;
import com.amazon.mp3.voice.onboarding.AlexaOnboardingUtil;
import com.amazon.mp3.voice.onboarding.OnboardingWorkflowManagerFactory;
import com.amazon.music.wakeword.WakeWordController;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class WakeWordHelper {
    private static final String TAG = "WakeWordHelper";
    private static final long STOP_SERVICE_DELAY_MS = TimeUnit.SECONDS.toMillis(20);
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private static boolean stopWakeWordServiceAfterDelay = false;

    public static void resumeWakeWordService(final Activity activity, final boolean startWakeWordDetectionWithService) {
        Context applicationContext = activity.getApplicationContext();
        if (!WakeWordController.isMicrophonePermissionsStillAvailable(applicationContext) || !AmazonApplication.getCapabilities().isWakeWordEnabled(applicationContext)) {
            turnOffWakeWordService(applicationContext);
        } else {
            if (isWakeWordServiceRunning(applicationContext)) {
                return;
            }
            startAccountStateCheckActivity(activity, startWakeWordDetectionWithService);
        }
    }

    public static void turnOnWakeWordService(Activity activity, boolean startWakeWordDetectionWithService) {
        if (new OnboardingWorkflowManagerFactory().createOnboardingWorkflowManager().isOnboardingCompleted()) {
            resumeWakeWordService(activity, startWakeWordDetectionWithService);
        } else {
            AlexaOnboardingUtil.INSTANCE.startOnboardingFlow(activity);
        }
    }

    public static void turnOffWakeWordService(final Context context) {
        ScreenWakeLock.setForceKeepScreenOn(false);
        SettingsUtil.enableWakeWordUserSetting(context, false);
        context.stopService(new Intent(context, (Class<?>) WakeWordService.class));
    }

    public static void stopWakeWordService(final Context context) {
        ScreenWakeLock.setForceKeepScreenOn(false);
        context.stopService(new Intent(context, (Class<?>) WakeWordService.class));
    }

    public static void turnOffWakeWordServiceWithDelay(final Context context) {
        context.sendBroadcast(new Intent("com.amazon.mp3.wakewordcommand.wakeword.user.disabled"));
        stopWakeWordServiceAfterDelay = true;
        sHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.voice.wakeword.WakeWordHelper.1
            @Override // java.lang.Runnable
            public void run() {
                WakeWordHelper.turnOffWakeWordService(context);
                boolean unused = WakeWordHelper.stopWakeWordServiceAfterDelay = false;
            }
        }, STOP_SERVICE_DELAY_MS);
    }

    public static boolean isWakeWordServiceRunning(final Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        if (activityManager == null) {
            Log.warning(TAG, "The Activity manager is null when checking if the WakeWord service is null. Assuming it is not running.");
            return false;
        }
        if (stopWakeWordServiceAfterDelay) {
            sHandler.removeCallbacksAndMessages(null);
            stopWakeWordServiceAfterDelay = false;
            return false;
        }
        Iterator<ActivityManager.RunningServiceInfo> it = activityManager.getRunningServices(Integer.MAX_VALUE).iterator();
        while (it.hasNext()) {
            if (WakeWordService.class.getName().equals(it.next().service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private static void startAccountStateCheckActivity(final Activity activity, final boolean startWakeWordDetectionWithService) {
        final Context applicationContext = activity.getApplicationContext();
        activity.startActivity(AccountStateCheckActivity.getStartIntent(applicationContext, new AccountJobController.JobFinishedCallback() { // from class: com.amazon.mp3.voice.wakeword.WakeWordHelper.2
            @Override // com.amazon.mp3.activity.util.AccountJobController.JobFinishedCallback
            public void onSuccess() {
                WakeWordHelper.startWakeWordService(applicationContext, startWakeWordDetectionWithService);
            }

            @Override // com.amazon.mp3.activity.util.AccountJobController.JobFinishedCallback
            public void onFailure() {
                Log.error(WakeWordHelper.TAG, "Failed to checkImmediate on AccountJobController");
                WakeWordHelper.turnOffWakeWordService(applicationContext);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startWakeWordService(final Context context, final boolean startWakeWordDetectionWithService) {
        Intent intent = new Intent(context, (Class<?>) WakeWordService.class);
        intent.putExtra("start.wakeword.detection.with.service", startWakeWordDetectionWithService);
        intent.putExtra("set.wakeword.controller.is.enabled", true);
        ServiceUtil.startService(context, intent);
    }
}
