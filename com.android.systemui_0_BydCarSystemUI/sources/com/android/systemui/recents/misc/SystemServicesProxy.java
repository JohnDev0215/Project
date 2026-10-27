package com.android.systemui.recents.misc;

import android.R;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.AppGlobals;
import android.app.IActivityManager;
import android.app.IActivityTaskManager;
import android.app.WindowConfiguration;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.service.dreams.IDreamManager;
import android.util.MutableBoolean;
import android.view.Display;
import android.view.IWindowManager;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.app.AssistUtils;
import com.android.systemui.Constants;
import com.android.systemui.UiOffloadThread;
import com.android.systemui.recents.LegacyRecentsImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SystemServicesProxy {
    static final String TAG = "SystemServicesProxy";
    private static SystemServicesProxy sSystemServicesProxy;
    AccessibilityManager mAccm;
    ActivityManager mAm;
    AssistUtils mAssistUtils;
    private final Context mContext;
    Display mDisplay;
    int mDummyThumbnailHeight;
    int mDummyThumbnailWidth;
    boolean mIsSafeMode;
    PackageManager mPm;
    String mRecentsPackage;
    private final UiOffloadThread mUiOffloadThread;
    UserManager mUm;
    WindowManager mWm;
    IActivityManager mIam = ActivityManager.getService();
    IActivityTaskManager mIatm = ActivityTaskManager.getService();
    IPackageManager mIpm = AppGlobals.getPackageManager();
    IWindowManager mIwm = WindowManagerGlobal.getWindowManagerService();
    private final IDreamManager mDreamManager = IDreamManager.Stub.asInterface(ServiceManager.checkService("dreams"));
    private int mCurrentUserId = ActivityManager.getCurrentUser();

    public boolean isSystemUser(int userId) {
        return userId == 0;
    }

    private SystemServicesProxy(Context context) {
        this.mContext = context.getApplicationContext();
        this.mAccm = AccessibilityManager.getInstance(context);
        this.mAm = (ActivityManager) context.getSystemService(Constants.PackageInfo.ACTIVITY);
        this.mPm = context.getPackageManager();
        this.mAssistUtils = new AssistUtils(context);
        this.mWm = (WindowManager) context.getSystemService("window");
        this.mUm = UserManager.get(context);
        this.mDisplay = this.mWm.getDefaultDisplay();
        this.mRecentsPackage = context.getPackageName();
        this.mIsSafeMode = this.mPm.isSafeMode();
        Resources resources = context.getResources();
        this.mDummyThumbnailWidth = resources.getDimensionPixelSize(R.dimen.thumbnail_width);
        this.mDummyThumbnailHeight = resources.getDimensionPixelSize(R.dimen.thumbnail_height);
        this.mUiOffloadThread = UiOffloadThread.get();
    }

    public static synchronized SystemServicesProxy getInstance(Context context) {
        if (sSystemServicesProxy == null) {
            sSystemServicesProxy = new SystemServicesProxy(context);
        }
        return sSystemServicesProxy;
    }

    public int getDeviceSmallestWidth() {
        if (this.mDisplay == null) {
            return 0;
        }
        Point point = new Point();
        this.mDisplay.getCurrentSizeRange(point, new Point());
        return point.x;
    }

    public int getCurrentUser() {
        return this.mCurrentUserId;
    }

    public boolean isScreenPinningActive() {
        if (this.mIam == null) {
            return false;
        }
        try {
            return this.mIatm.getLockTaskModeState() == 2;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean isRecentsActivityVisible(MutableBoolean isHomeStackVisible) {
        if (this.mIam == null) {
            return false;
        }
        try {
            List allStackInfos = this.mIatm.getAllStackInfos();
            ActivityManager.StackInfo stackInfo = null;
            ActivityManager.StackInfo stackInfo2 = null;
            ActivityManager.StackInfo stackInfo3 = null;
            for (int i = 0; i < allStackInfos.size(); i++) {
                ActivityManager.StackInfo stackInfo4 = (ActivityManager.StackInfo) allStackInfos.get(i);
                WindowConfiguration windowConfiguration = stackInfo4.configuration.windowConfiguration;
                int activityType = windowConfiguration.getActivityType();
                int windowingMode = windowConfiguration.getWindowingMode();
                if (stackInfo == null && activityType == 2) {
                    stackInfo = stackInfo4;
                } else if (stackInfo2 == null && activityType == 1 && (windowingMode == 1 || windowingMode == 4)) {
                    stackInfo2 = stackInfo4;
                } else if (stackInfo3 == null && activityType == 3) {
                    stackInfo3 = stackInfo4;
                }
            }
            boolean zIsStackNotOccluded = isStackNotOccluded(stackInfo, stackInfo2);
            boolean zIsStackNotOccluded2 = isStackNotOccluded(stackInfo3, stackInfo2);
            if (isHomeStackVisible != null) {
                isHomeStackVisible.value = zIsStackNotOccluded;
            }
            ComponentName componentName = stackInfo3 != null ? stackInfo3.topActivity : null;
            if (zIsStackNotOccluded2 && componentName != null && componentName.getPackageName().equals("com.android.systemui")) {
                return LegacyRecentsImpl.RECENTS_ACTIVITIES.contains(componentName.getClassName());
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isStackNotOccluded(ActivityManager.StackInfo stackInfo, ActivityManager.StackInfo fullscreenStackInfo) {
        boolean z = false;
        boolean z2 = stackInfo == null || stackInfo.visible;
        if (fullscreenStackInfo == null || stackInfo == null) {
            return z2;
        }
        if (fullscreenStackInfo.visible && fullscreenStackInfo.position > stackInfo.position) {
            z = true;
        }
        return z2 & (!z);
    }

    public /* synthetic */ void lambda$startActivityAsUserAsync$0$SystemServicesProxy(Intent intent, ActivityOptions activityOptions) {
        this.mContext.startActivityAsUser(intent, activityOptions != null ? activityOptions.toBundle() : null, UserHandle.CURRENT);
    }

    public void startActivityAsUserAsync(final Intent intent, final ActivityOptions opts) {
        this.mUiOffloadThread.submit(new Runnable() { // from class: com.android.systemui.recents.misc.-$$Lambda$SystemServicesProxy$t59YLCswgWnJLve3Dyd0_pEis0U
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$startActivityAsUserAsync$0$SystemServicesProxy(intent, opts);
            }
        });
    }
}
