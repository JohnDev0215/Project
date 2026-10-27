package com.android.systemui.screenshot;

import android.app.ActivityManager;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.Slog;
import com.android.systemui.SystemUIFactory;
import com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.inject.Inject;
import javax.inject.Singleton;

/* JADX INFO: loaded from: classes.dex */
@Singleton
public class ScreenshotSmartActions {
    private static final String TAG = "ScreenshotSmartActions";

    @Inject
    public ScreenshotSmartActions() {
    }

    CompletableFuture<List<Notification.Action>> getSmartActionsFuture(String screenshotId, Uri screenshotUri, Bitmap image, ScreenshotNotificationSmartActionsProvider smartActionsProvider, boolean smartActionsEnabled, UserHandle userHandle) {
        ComponentName componentName;
        if (!smartActionsEnabled) {
            Slog.i(TAG, "Screenshot Intelligence not enabled, returning empty list.");
            return CompletableFuture.completedFuture(Collections.emptyList());
        }
        if (image.getConfig() != Bitmap.Config.HARDWARE) {
            Slog.w(TAG, String.format("Bitmap expected: Hardware, Bitmap found: %s. Returning empty list.", image.getConfig()));
            return CompletableFuture.completedFuture(Collections.emptyList());
        }
        Slog.d(TAG, "Screenshot from user profile: " + userHandle.getIdentifier());
        long jUptimeMillis = SystemClock.uptimeMillis();
        try {
            ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.getInstance().getRunningTask();
            if (runningTask != null && runningTask.topActivity != null) {
                componentName = runningTask.topActivity;
            } else {
                componentName = new ComponentName("", "");
            }
            return smartActionsProvider.getActions(screenshotId, screenshotUri, image, componentName, userHandle);
        } catch (Throwable th) {
            long jUptimeMillis2 = SystemClock.uptimeMillis() - jUptimeMillis;
            CompletableFuture<List<Notification.Action>> completableFutureCompletedFuture = CompletableFuture.completedFuture(Collections.emptyList());
            Slog.e(TAG, "Failed to get future for screenshot notification smart actions.", th);
            notifyScreenshotOp(screenshotId, smartActionsProvider, ScreenshotNotificationSmartActionsProvider.ScreenshotOp.REQUEST_SMART_ACTIONS, ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.ERROR, jUptimeMillis2);
            return completableFutureCompletedFuture;
        }
    }

    List<Notification.Action> getSmartActions(String screenshotId, CompletableFuture<List<Notification.Action>> smartActionsFuture, int timeoutMs, ScreenshotNotificationSmartActionsProvider smartActionsProvider) {
        ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus screenshotOpStatus;
        long jUptimeMillis = SystemClock.uptimeMillis();
        try {
            List<Notification.Action> list = smartActionsFuture.get(timeoutMs, TimeUnit.MILLISECONDS);
            long jUptimeMillis2 = SystemClock.uptimeMillis() - jUptimeMillis;
            Slog.d(TAG, String.format("Got %d smart actions. Wait time: %d ms", Integer.valueOf(list.size()), Long.valueOf(jUptimeMillis2)));
            notifyScreenshotOp(screenshotId, smartActionsProvider, ScreenshotNotificationSmartActionsProvider.ScreenshotOp.WAIT_FOR_SMART_ACTIONS, ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.SUCCESS, jUptimeMillis2);
            return list;
        } catch (Throwable th) {
            long jUptimeMillis3 = SystemClock.uptimeMillis() - jUptimeMillis;
            Slog.e(TAG, String.format("Error getting smart actions. Wait time: %d ms", Long.valueOf(jUptimeMillis3)), th);
            if (th instanceof TimeoutException) {
                screenshotOpStatus = ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.TIMEOUT;
            } else {
                screenshotOpStatus = ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.ERROR;
            }
            notifyScreenshotOp(screenshotId, smartActionsProvider, ScreenshotNotificationSmartActionsProvider.ScreenshotOp.WAIT_FOR_SMART_ACTIONS, screenshotOpStatus, jUptimeMillis3);
            return Collections.emptyList();
        }
    }

    void notifyScreenshotOp(String screenshotId, ScreenshotNotificationSmartActionsProvider smartActionsProvider, ScreenshotNotificationSmartActionsProvider.ScreenshotOp op, ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus status, long durationMs) {
        try {
            smartActionsProvider.notifyOp(screenshotId, op, status, durationMs);
        } catch (Throwable th) {
            Slog.e(TAG, "Error in notifyScreenshotOp: ", th);
        }
    }

    void notifyScreenshotAction(Context context, String screenshotId, String action, boolean isSmartAction) {
        try {
            SystemUIFactory.getInstance().createScreenshotNotificationSmartActionsProvider(context, AsyncTask.THREAD_POOL_EXECUTOR, new Handler()).notifyAction(screenshotId, action, isSmartAction);
        } catch (Throwable th) {
            Slog.e(TAG, "Error in notifyScreenshotAction: ", th);
        }
    }
}
