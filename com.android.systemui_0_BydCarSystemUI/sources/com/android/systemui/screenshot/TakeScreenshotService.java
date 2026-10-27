package com.android.systemui.screenshot;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.UserManager;
import android.util.Log;
import com.android.internal.logging.InstanceId;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.ScreenshotHelper;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.shared.recents.utilities.BitmapUtil;
import com.byd.card.view.broadcast.HomeBroadcastReceiver;
import java.util.function.Consumer;
import javax.inject.Inject;

/* JADX INFO: loaded from: classes.dex */
public class TakeScreenshotService extends Service implements UiEventLogger {
    public static final String CLOSE_SCREEN_SHOT = "com.android.systemui.close_screenshot";
    private static final String TAG = "TakeScreenshotService";
    private final BroadcastReceiver mBroadcastReceiver;
    private Handler mHandler;
    private GlobalScreenshot mScreenshot;
    private UiEventLogger mUiEventLogger;
    private UserManager mUserManager;

    public void log(UiEventLogger.UiEventEnum event) {
    }

    public void log(UiEventLogger.UiEventEnum event, int uid, String packageName) {
    }

    public void logWithInstanceId(UiEventLogger.UiEventEnum event, int uid, String packageName, InstanceId instance) {
    }

    public void logWithInstanceIdAndPosition(UiEventLogger.UiEventEnum event, int uid, String packageName, InstanceId instance, int position) {
    }

    public void logWithPosition(UiEventLogger.UiEventEnum event, int uid, String packageName, int position) {
    }

    /* JADX INFO: renamed from: com.android.systemui.screenshot.TakeScreenshotService$2, reason: invalid class name */
    class AnonymousClass2 extends Handler {
        AnonymousClass2(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            final Messenger messenger = msg.replyTo;
            final Consumer<Uri> consumer = new Consumer() { // from class: com.android.systemui.screenshot.-$$Lambda$TakeScreenshotService$2$-KC8E9QjL7acOe_a3PhzJxO2C9w
                @Override // java.util.function.Consumer
                public final void accept(Object obj) throws RemoteException {
                    messenger.send(Message.obtain(null, 1, (Uri) obj));
                }
            };
            Runnable runnable = new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$TakeScreenshotService$2$ye0Ew_c-JtgMZy0AwmNXJNh9jbg
                @Override // java.lang.Runnable
                public final void run() throws RemoteException {
                    messenger.send(Message.obtain((Handler) null, 2));
                }
            };
            if (!((UserManager) SystemUIApplication.getInstance().getSystemService(UserManager.class)).isUserUnlocked()) {
                Log.w(TakeScreenshotService.TAG, "Skipping screenshot because storage is locked!");
                post(new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$TakeScreenshotService$2$lPxzvyVTgGU-5ADM-bzDmSxtLiE
                    @Override // java.lang.Runnable
                    public final void run() {
                        consumer.accept(null);
                    }
                });
                post(runnable);
                return;
            }
            ScreenshotHelper.ScreenshotRequest screenshotRequest = (ScreenshotHelper.ScreenshotRequest) msg.obj;
            int i = msg.what;
            if (i == 1) {
                TakeScreenshotService.this.mScreenshot.takeScreenshotFullscreen(consumer, runnable);
                return;
            }
            if (i == 2) {
                TakeScreenshotService.this.mScreenshot.takeScreenshotPartial(consumer, runnable);
                return;
            }
            if (i == 3) {
                TakeScreenshotService.this.mScreenshot.handleImageAsScreenshot(BitmapUtil.bundleToHardwareBitmap(screenshotRequest.getBitmapBundle()), screenshotRequest.getBoundsInScreen(), screenshotRequest.getInsets(), screenshotRequest.getTaskId(), screenshotRequest.getUserId(), screenshotRequest.getTopComponent(), consumer, runnable);
                return;
            }
            Log.d(TakeScreenshotService.TAG, "Invalid screenshot option: " + msg.what);
        }
    }

    @Inject
    public TakeScreenshotService(GlobalScreenshot globalScreenshot, UserManager userManager, UiEventLogger uiEventLogger) {
        this.mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.screenshot.TakeScreenshotService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (TakeScreenshotService.this.mScreenshot != null) {
                    String action = intent.getAction();
                    Log.d(TakeScreenshotService.TAG, "onReceive " + action);
                    if (HomeBroadcastReceiver.CLOSE_SYSTEM.equals(action)) {
                        TakeScreenshotService.this.mScreenshot.dismissScreenshot("close system dialogs", false);
                    } else if (TakeScreenshotService.CLOSE_SCREEN_SHOT.equals(action)) {
                        TakeScreenshotService.this.mScreenshot.dismissScreenshot("drag status bar", false);
                    }
                }
            }
        };
        this.mHandler = new AnonymousClass2(Looper.myLooper());
        this.mScreenshot = globalScreenshot;
        this.mUserManager = userManager;
        this.mUiEventLogger = uiEventLogger;
    }

    public TakeScreenshotService() {
        this.mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.screenshot.TakeScreenshotService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (TakeScreenshotService.this.mScreenshot != null) {
                    String action = intent.getAction();
                    Log.d(TakeScreenshotService.TAG, "onReceive " + action);
                    if (HomeBroadcastReceiver.CLOSE_SYSTEM.equals(action)) {
                        TakeScreenshotService.this.mScreenshot.dismissScreenshot("close system dialogs", false);
                    } else if (TakeScreenshotService.CLOSE_SCREEN_SHOT.equals(action)) {
                        TakeScreenshotService.this.mScreenshot.dismissScreenshot("drag status bar", false);
                    }
                }
            }
        };
        this.mHandler = new AnonymousClass2(Looper.myLooper());
        this.mUserManager = (UserManager) SystemUIApplication.getInstance().getSystemService("user");
        if (this.mScreenshot == null) {
            this.mScreenshot = new GlobalScreenshot(SystemUIApplication.getInstance(), this);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(HomeBroadcastReceiver.CLOSE_SYSTEM);
        intentFilter.addAction(CLOSE_SCREEN_SHOT);
        registerReceiver(this.mBroadcastReceiver, intentFilter);
        return new Messenger(this.mHandler).getBinder();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        GlobalScreenshot globalScreenshot = this.mScreenshot;
        if (globalScreenshot != null) {
            globalScreenshot.stopScreenshot();
        }
        unregisterReceiver(this.mBroadcastReceiver);
        return true;
    }
}
