package com.android.systemui.screenshot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.concurrent.Executor;
import javax.inject.Inject;

/* JADX INFO: loaded from: classes.dex */
public class DeleteScreenshotReceiver extends BroadcastReceiver {
    private final Executor mBackgroundExecutor;
    private final ScreenshotSmartActions mScreenshotSmartActions;

    @Inject
    public DeleteScreenshotReceiver(ScreenshotSmartActions screenshotSmartActions, Executor backgroundExecutor) {
        this.mScreenshotSmartActions = screenshotSmartActions;
        this.mBackgroundExecutor = backgroundExecutor;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        if (intent.hasExtra("android:screenshot_uri_id")) {
            final Uri uri = Uri.parse(intent.getStringExtra("android:screenshot_uri_id"));
            this.mBackgroundExecutor.execute(new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$DeleteScreenshotReceiver$CEZI5sqJSK54hW_kaFE5KM0z6bY
                @Override // java.lang.Runnable
                public final void run() {
                    context.getContentResolver().delete(uri, null, null);
                }
            });
            if (intent.getBooleanExtra("android:smart_actions_enabled", false)) {
                this.mScreenshotSmartActions.notifyScreenshotAction(context, intent.getStringExtra("android:screenshot_id"), "Delete", false);
            }
        }
    }
}
