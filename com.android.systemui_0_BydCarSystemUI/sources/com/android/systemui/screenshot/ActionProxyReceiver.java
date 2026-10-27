package com.android.systemui.screenshot;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.statusbar.phone.StatusBar;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.inject.Inject;

/* JADX INFO: loaded from: classes.dex */
public class ActionProxyReceiver extends BroadcastReceiver {
    private static final int CLOSE_WINDOWS_TIMEOUT_MILLIS = 3000;
    private static final String TAG = "ActionProxyReceiver";
    private final ActivityManagerWrapper mActivityManagerWrapper;
    private final ScreenshotSmartActions mScreenshotSmartActions;
    private final StatusBar mStatusBar;

    @Inject
    public ActionProxyReceiver(Optional<StatusBar> statusBar, ActivityManagerWrapper activityManagerWrapper, ScreenshotSmartActions screenshotSmartActions) {
        this.mStatusBar = statusBar.orElse(null);
        this.mActivityManagerWrapper = activityManagerWrapper;
        this.mScreenshotSmartActions = screenshotSmartActions;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        Runnable runnable = new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$ActionProxyReceiver$ukuT0qmNYLdDpxZysWn0ab2Dw18
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onReceive$0$ActionProxyReceiver(intent, context);
            }
        };
        StatusBar statusBar = this.mStatusBar;
        if (statusBar != null) {
            statusBar.executeRunnableDismissingKeyguard(runnable, null, true, true, true);
        } else {
            runnable.run();
        }
        if (intent.getBooleanExtra("android:smart_actions_enabled", false)) {
            this.mScreenshotSmartActions.notifyScreenshotAction(context, intent.getStringExtra("android:screenshot_id"), "android.intent.action.EDIT".equals(intent.getAction()) ? "Edit" : "Share", false);
        }
    }

    public /* synthetic */ void lambda$onReceive$0$ActionProxyReceiver(Intent intent, Context context) {
        try {
            this.mActivityManagerWrapper.closeSystemWindows(StatusBar.SYSTEM_DIALOG_REASON_SCREENSHOT).get(3000L, TimeUnit.MILLISECONDS);
            PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("android:screenshot_action_intent");
            ActivityOptions activityOptionsMakeBasic = ActivityOptions.makeBasic();
            activityOptionsMakeBasic.setDisallowEnterPictureInPictureWhileLaunching(intent.getBooleanExtra("android:screenshot_disallow_enter_pip", false));
            try {
                pendingIntent.send(context, 0, null, null, null, null, activityOptionsMakeBasic.toBundle());
            } catch (PendingIntent.CanceledException e) {
                Log.e(TAG, "Pending intent canceled", e);
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e2) {
            Log.e(TAG, "Unable to share screenshot", e2);
        }
    }
}
