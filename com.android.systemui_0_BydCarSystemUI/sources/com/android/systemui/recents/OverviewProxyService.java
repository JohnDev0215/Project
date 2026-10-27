package com.android.systemui.recents;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Insets;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.view.MotionEvent;
import androidx.core.view.InputDeviceCompat;
import com.android.systemui.Dependency;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.settings.CurrentUserTracker;
import com.android.systemui.shared.recents.IOverviewProxy;
import com.android.systemui.shared.recents.ISystemUiProxy;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.stackdivider.Divider;

/* JADX INFO: loaded from: classes.dex */
public class OverviewProxyService extends CurrentUserTracker {
    private static final String ACTION_QUICKSTEP = "android.intent.action.QUICKSTEP_SERVICE";
    private static final long BACKOFF_MILLIS = 1000;
    private static final long DEFERRED_CALLBACK_MILLIS = 5000;
    private static final long MAX_BACKOFF_MILLIS = 600000;
    public static final String TAG_OPS = "OverviewProxyService";
    public static OverviewProxyService mOverviewProxyService;
    private Region mActiveNavBarRegion;
    private boolean mBound;
    private int mConnectionBackoffAttempts;
    private final Runnable mConnectionRunnable;
    private final Context mContext;
    private int mCurrentBoundedUserId;
    private final Runnable mDeferredConnectionCallback;
    private final Handler mHandler;
    private long mInputFocusTransferStartMillis;
    private float mInputFocusTransferStartY;
    private boolean mInputFocusTransferStarted;
    private boolean mIsEnabled;
    private float mNavBarButtonAlpha;
    private int mNavBarMode;
    private IOverviewProxy mOverviewProxy;
    private final ServiceConnection mOverviewServiceConnection;
    private final ComponentName mRecentsComponentName;
    private boolean mSupportsRoundedCornersOnWindows;
    private ISystemUiProxy mSysUiProxy;
    private float mWindowCornerRadius;

    public OverviewProxyService(Context context) {
        super(context);
        this.mConnectionRunnable = new Runnable() { // from class: com.android.systemui.recents.-$$Lambda$OverviewProxyService$5woo0ZUfUI59FZVuoOikLBgk2hQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.internalConnectToCurrentUser();
            }
        };
        this.mCurrentBoundedUserId = -1;
        this.mNavBarMode = 0;
        this.mSysUiProxy = new ISystemUiProxy.Stub() { // from class: com.android.systemui.recents.OverviewProxyService.1
            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void handleImageAsScreenshot(Bitmap screenImage, Rect locationInScreen, Insets visibleInsets, int taskId) {
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void notifySwipeToHomeFinished() {
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void onQuickSwitchToNewTask(int rotation) {
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void startScreenPinning(int taskId) {
                Log.d(OverviewProxyService.TAG_OPS, "startScreenPinning taskId : " + taskId);
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void stopScreenPinning() {
                Log.d(OverviewProxyService.TAG_OPS, "stopScreenPinning");
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void onStatusBarMotionEvent(MotionEvent event) {
                Log.d(OverviewProxyService.TAG_OPS, "onStatusBarMotionEvent event : " + event);
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void onSplitScreenInvoked() {
                Log.d(OverviewProxyService.TAG_OPS, "onSplitScreenInvoked");
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void onOverviewShown(boolean fromHome) {
                Log.d(OverviewProxyService.TAG_OPS, "onOverviewShown fromHome : " + fromHome);
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public Rect getNonMinimizedSplitScreenSecondaryBounds() {
                Log.d(OverviewProxyService.TAG_OPS, "getNonMinimizedSplitScreenSecondaryBounds");
                return new Rect();
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void setNavBarButtonAlpha(float alpha, boolean animate) {
                Log.d(OverviewProxyService.TAG_OPS, "setNavBarButtonAlpha alpha : " + alpha + ", animate : " + animate);
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void setBackButtonAlpha(float alpha, boolean animate) {
                Log.d(OverviewProxyService.TAG_OPS, "setBackButtonAlpha alpha : " + alpha + ", animate : " + animate);
                setNavBarButtonAlpha(alpha, animate);
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void onAssistantProgress(float progress) {
                Log.d(OverviewProxyService.TAG_OPS, "onAssistantProgress progress : " + progress);
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void onAssistantGestureCompletion(float velocity) {
                Log.d(OverviewProxyService.TAG_OPS, "onAssistantGestureCompletion velocity : " + velocity);
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void startAssistant(Bundle bundle) {
                Log.d(OverviewProxyService.TAG_OPS, "startAssistant bundle : " + bundle);
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public Bundle monitorGestureInput(String name, int displayId) {
                Log.d(OverviewProxyService.TAG_OPS, "monitorGestureInput name : " + name + ", displayId : " + displayId);
                return new Bundle();
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void notifyAccessibilityButtonClicked(int displayId) {
                Log.d(OverviewProxyService.TAG_OPS, "notifyAccessibilityButtonClicked displayId : " + displayId);
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void notifyAccessibilityButtonLongClicked() {
                Log.d(OverviewProxyService.TAG_OPS, "notifyAccessibilityButtonLongClicked");
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void setShelfHeight(boolean visible, int shelfHeight) {
                Log.d(OverviewProxyService.TAG_OPS, "setShelfHeight visible : " + visible + ", shelfHeight : " + shelfHeight);
            }

            @Override // com.android.systemui.shared.recents.ISystemUiProxy
            public void setSplitScreenMinimized(boolean minimized) {
                Log.d(OverviewProxyService.TAG_OPS, "setSplitScreenMinimized minimized : " + minimized);
                Divider divider = (Divider) SysUiServiceProvider.getComponent(OverviewProxyService.this.mContext, Divider.class);
                if (divider != null) {
                    divider.setMinimized(minimized);
                }
            }
        };
        this.mOverviewServiceConnection = new ServiceConnection() { // from class: com.android.systemui.recents.OverviewProxyService.2
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(OverviewProxyService.TAG_OPS, "onServiceConnected name : " + name);
                Log.d(OverviewProxyService.TAG_OPS, "Overview proxy service connected");
                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                overviewProxyService.mCurrentBoundedUserId = overviewProxyService.getCurrentUserId();
                OverviewProxyService.this.mOverviewProxy = IOverviewProxy.Stub.asInterface(service);
                Bundle bundle = new Bundle();
                bundle.putBinder(QuickStepContract.KEY_EXTRA_SYSUI_PROXY, OverviewProxyService.this.mSysUiProxy.asBinder());
                bundle.putFloat(QuickStepContract.KEY_EXTRA_WINDOW_CORNER_RADIUS, OverviewProxyService.this.mWindowCornerRadius);
                bundle.putBoolean(QuickStepContract.KEY_EXTRA_SUPPORTS_WINDOW_CORNERS, OverviewProxyService.this.mSupportsRoundedCornersOnWindows);
                try {
                    OverviewProxyService.this.mOverviewProxy.onInitialize(bundle);
                } catch (RemoteException e) {
                    OverviewProxyService.this.mCurrentBoundedUserId = -1;
                    Log.e(OverviewProxyService.TAG_OPS, "Failed to call onInitialize()", e);
                }
            }

            @Override // android.content.ServiceConnection
            public void onNullBinding(ComponentName name) {
                Log.d(OverviewProxyService.TAG_OPS, "onNullBinding Null binding of '" + name + "', try reconnecting");
                OverviewProxyService.this.mCurrentBoundedUserId = -1;
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName name) {
                Log.d(OverviewProxyService.TAG_OPS, "onBindingDied Binding died of '" + name + "', try reconnecting");
                OverviewProxyService.this.mCurrentBoundedUserId = -1;
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                OverviewProxyService.this.mCurrentBoundedUserId = -1;
            }
        };
        this.mDeferredConnectionCallback = new Runnable() { // from class: com.android.systemui.recents.-$$Lambda$OverviewProxyService$BaF-uB_VKFOEOVnRby-e2hfBUy8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$OverviewProxyService();
            }
        };
        Log.d(TAG_OPS, TAG_OPS);
        this.mHandler = (Handler) Dependency.get(Dependency.MAIN_HANDLER);
        this.mContext = context;
        this.mRecentsComponentName = ComponentName.unflattenFromString("com.android.systemui/.recents.RecentsActivity");
        internalConnectToCurrentUser();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void internalConnectToCurrentUser() {
        this.mHandler.removeCallbacks(this.mConnectionRunnable);
        try {
            this.mBound = this.mContext.bindServiceAsUser(new Intent(ACTION_QUICKSTEP).setPackage(this.mRecentsComponentName.getPackageName()), this.mOverviewServiceConnection, InputDeviceCompat.SOURCE_HDMI, UserHandle.of(getCurrentUserId()));
        } catch (SecurityException e) {
            Log.e(TAG_OPS, "Unable to bind because of security error", e);
        }
        if (this.mBound) {
            this.mHandler.postDelayed(this.mDeferredConnectionCallback, DEFERRED_CALLBACK_MILLIS);
        } else {
            retryConnectionWithBackoff();
        }
    }

    public /* synthetic */ void lambda$new$0$OverviewProxyService() {
        Log.w(TAG_OPS, "Binder supposed established connection but actual connection to service timed out, trying again");
        retryConnectionWithBackoff();
    }

    private void retryConnectionWithBackoff() {
        if (this.mHandler.hasCallbacks(this.mConnectionRunnable)) {
            return;
        }
        long jMin = (long) Math.min(Math.scalb(1000.0f, this.mConnectionBackoffAttempts), 600000.0f);
        this.mHandler.postDelayed(this.mConnectionRunnable, jMin);
        this.mConnectionBackoffAttempts++;
        Log.w(TAG_OPS, "Failed to connect on attempt " + this.mConnectionBackoffAttempts + " will try again in " + jMin + "ms");
    }

    @Override // com.android.systemui.settings.CurrentUserTracker
    public void onUserSwitched(int newUserId) {
        Log.e(TAG_OPS, "onUserSwitched");
        this.mConnectionBackoffAttempts = 0;
        internalConnectToCurrentUser();
    }

    public static void initOverviewProxyService(Context context) {
        if (mOverviewProxyService != null) {
            return;
        }
        mOverviewProxyService = new OverviewProxyService(context);
    }

    public void notifySplitScreenBoundsChanged(Rect secondaryWindowBounds, Rect secondaryWindowInsets) {
        try {
            IOverviewProxy iOverviewProxy = this.mOverviewProxy;
            if (iOverviewProxy != null) {
                iOverviewProxy.onSplitScreenSecondaryBoundsChanged(secondaryWindowBounds, secondaryWindowInsets);
            } else {
                Log.e(TAG_OPS, "Failed to get overview proxy for split screen bounds.");
            }
        } catch (RemoteException e) {
            Log.e(TAG_OPS, "Failed to call onSplitScreenSecondaryBoundsChanged()", e);
        }
    }
}
