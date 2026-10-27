package com.android.car.am;

import android.R;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.IActivityManager;
import android.app.IActivityTaskManager;
import android.app.IProcessObserver;
import android.app.Presentation;
import android.app.TaskStackListener;
import android.car.hardware.power.CarPowerManager;
import android.car.user.CarUserManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.HandlerThread;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import com.android.car.CarLocalServices;
import com.android.car.CarLog;
import com.android.car.CarServiceBase;
import com.android.car.CarServiceUtils;
import com.android.car.user.CarUserService;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class FixedActivityService implements CarServiceBase {
    private static final long CRASH_FORGET_INTERVAL_MS = 120000;
    private static final boolean DBG = false;
    private static final int MAX_NUMBER_OF_CONSECUTIVE_CRASH_RETRY = 5;
    private static final long RECHECK_INTERVAL_MS = 500;
    private final Runnable mActivityCheckRunnable;
    private final IActivityManager mAm;
    private final IActivityTaskManager mAtm;
    private final SparseArray<Presentation> mBlockingPresentations;
    private final BroadcastReceiver mBroadcastReceiver;
    private CarPowerManager mCarPowerManager;
    private final CarPowerManager.CarPowerStateListener mCarPowerStateListener;
    private final Context mContext;
    private final DisplayManager mDm;
    private boolean mEventMonitoringActive;
    private final HandlerThread mHandlerThread;
    private final Object mLock;
    private final IProcessObserver mProcessObserver;
    private final SparseArray<RunningActivityInfo> mRunningActivities;
    private final TaskStackListener mTaskStackListener;
    private final UserManager mUm;
    private final CarUserManager.UserLifecycleListener mUserLifecycleListener;

    private static class RunningActivityInfo {
        public final ActivityOptions activityOptions;
        public boolean failureLogged;
        public boolean inBackground;
        public final Intent intent;
        public boolean isVisible;
        public final int userId;
        public long lastLaunchTimeMs = 0;
        public int consecutiveRetries = 0;
        public int taskId = -1;
        public int previousTaskId = -1;

        RunningActivityInfo(Intent intent, ActivityOptions activityOptions, int userId) {
            this.intent = intent;
            this.activityOptions = activityOptions;
            this.userId = userId;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void resetCrashCounterLocked() {
            this.consecutiveRetries = 0;
            this.failureLogged = false;
        }

        public String toString() {
            return "RunningActivityInfo{intent:" + this.intent + ",activityOptions:" + this.activityOptions + ",userId:" + this.userId + ",isVisible:" + this.isVisible + ",lastLaunchTimeMs:" + this.lastLaunchTimeMs + ",consecutiveRetries:" + this.consecutiveRetries + ",taskId:" + this.taskId + "}";
        }
    }

    public /* synthetic */ void lambda$new$0$FixedActivityService(CarUserManager.UserLifecycleEvent event) {
        if (Log.isLoggable(CarLog.TAG_AM, 3)) {
            Log.d(CarLog.TAG_AM, "onEvent(" + event + ")");
        }
        if (2 == event.getEventType()) {
            synchronized (this.mLock) {
                clearRunningActivitiesLocked();
            }
        }
    }

    void handleTaskFocusChanged(int taskId, boolean focused) {
        if (!focused) {
            return;
        }
        synchronized (this.mLock) {
            for (int i = this.mRunningActivities.size() - 1; i >= 0; i--) {
                RunningActivityInfo info = this.mRunningActivities.valueAt(i);
                if (info.taskId == taskId) {
                    moveFocusBackToDefaultDisplay(taskId);
                    return;
                }
            }
        }
    }

    private void moveFocusBackToDefaultDisplay(int taskId) {
        try {
            List<ActivityManager.StackInfo> tasks = this.mAtm.getAllStackInfosOnDisplay(0);
            if (tasks.size() == 0) {
                return;
            }
            ActivityManager.StackInfo topStack = tasks.get(0);
            int topTaskIdInDefaultDisplay = topStack.taskIds[topStack.taskIds.length - 1];
            this.mAtm.setFocusedTask(topTaskIdInDefaultDisplay);
        } catch (RemoteException e) {
            Log.e(CarLog.TAG_AM, "remote exception from ATM", e);
        }
    }

    public /* synthetic */ void lambda$new$2$FixedActivityService(int state) throws Throwable {
        if (state != 6) {
            return;
        }
        synchronized (this.mLock) {
            for (int i = 0; i < this.mRunningActivities.size(); i++) {
                RunningActivityInfo info = this.mRunningActivities.valueAt(i);
                info.resetCrashCounterLocked();
            }
        }
        lambda$new$1$FixedActivityService();
    }

    public FixedActivityService(Context context) {
        this(context, ActivityManager.getService(), ActivityTaskManager.getService(), (UserManager) context.getSystemService(UserManager.class), (DisplayManager) context.getSystemService(DisplayManager.class));
    }

    FixedActivityService(Context context, IActivityManager activityManager, IActivityTaskManager activityTaskManager, UserManager userManager, DisplayManager displayManager) {
        this.mUserLifecycleListener = new CarUserManager.UserLifecycleListener() { // from class: com.android.car.am.-$$Lambda$FixedActivityService$ac3SVal6kHNOEwB3QmsK5V_fVeA
            public final void onEvent(CarUserManager.UserLifecycleEvent userLifecycleEvent) {
                this.f$0.lambda$new$0$FixedActivityService(userLifecycleEvent);
            }
        };
        this.mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.car.am.FixedActivityService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) throws Throwable {
                String action = intent.getAction();
                if ("android.intent.action.PACKAGE_CHANGED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action) || "android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REMOVED".equals(action)) {
                    Uri packageData = intent.getData();
                    if (packageData == null) {
                        Log.w(CarLog.TAG_AM, "null packageData");
                        return;
                    }
                    String packageName = packageData.getSchemeSpecificPart();
                    if (packageName == null) {
                        Log.w(CarLog.TAG_AM, "null packageName");
                        return;
                    }
                    int uid = intent.getIntExtra("android.intent.extra.UID", -1);
                    int userId = UserHandle.getUserId(uid);
                    boolean tryLaunch = false;
                    synchronized (FixedActivityService.this.mLock) {
                        int i = 0;
                        while (true) {
                            if (i >= FixedActivityService.this.mRunningActivities.size()) {
                                break;
                            }
                            RunningActivityInfo info = (RunningActivityInfo) FixedActivityService.this.mRunningActivities.valueAt(i);
                            if (info.userId != userId) {
                                i++;
                            } else {
                                Log.i(CarLog.TAG_AM, "Package changed:" + packageName + ",user:" + userId + ",action:" + action);
                                info.resetCrashCounterLocked();
                                tryLaunch = true;
                                break;
                            }
                        }
                    }
                    if (tryLaunch) {
                        FixedActivityService.this.lambda$new$1$FixedActivityService();
                    }
                }
            }
        };
        this.mTaskStackListener = new TaskStackListener() { // from class: com.android.car.am.FixedActivityService.2
            public void onTaskStackChanged() throws Throwable {
                FixedActivityService.this.lambda$new$1$FixedActivityService();
            }

            public void onTaskCreated(int taskId, ComponentName componentName) throws Throwable {
                FixedActivityService.this.lambda$new$1$FixedActivityService();
            }

            public void onTaskRemoved(int taskId) throws Throwable {
                FixedActivityService.this.lambda$new$1$FixedActivityService();
            }

            public void onTaskMovedToFront(int taskId) throws Throwable {
                FixedActivityService.this.lambda$new$1$FixedActivityService();
            }

            public void onTaskRemovalStarted(int taskId) throws Throwable {
                FixedActivityService.this.lambda$new$1$FixedActivityService();
            }

            public void onTaskFocusChanged(int taskId, boolean focused) {
                FixedActivityService.this.handleTaskFocusChanged(taskId, focused);
            }
        };
        this.mProcessObserver = new IProcessObserver.Stub() { // from class: com.android.car.am.FixedActivityService.3
            public void onForegroundActivitiesChanged(int pid, int uid, boolean foregroundActivities) throws Throwable {
                FixedActivityService.this.lambda$new$1$FixedActivityService();
            }

            public void onForegroundServicesChanged(int pid, int uid, int fgServiceTypes) {
            }

            public void onProcessDied(int pid, int uid) throws Throwable {
                FixedActivityService.this.lambda$new$1$FixedActivityService();
            }
        };
        this.mActivityCheckRunnable = new Runnable() { // from class: com.android.car.am.-$$Lambda$FixedActivityService$UH_iWZ-A3qHTfGF17UJek9SrGDU
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f$0.lambda$new$1$FixedActivityService();
            }
        };
        this.mLock = new Object();
        this.mRunningActivities = new SparseArray<>(1);
        this.mBlockingPresentations = new SparseArray<>(1);
        this.mCarPowerStateListener = new CarPowerManager.CarPowerStateListener() { // from class: com.android.car.am.-$$Lambda$FixedActivityService$Ih3wFrUDc84XRLTSfMkOwKyNN1I
            public final void onStateChanged(int i) throws Throwable {
                this.f$0.lambda$new$2$FixedActivityService(i);
            }
        };
        this.mContext = context;
        this.mAm = activityManager;
        this.mAtm = activityTaskManager;
        this.mUm = userManager;
        this.mDm = displayManager;
        this.mHandlerThread = CarServiceUtils.getHandlerThread(FixedActivityService.class.getSimpleName());
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        stopMonitoringEvents();
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*FixedActivityService*");
        synchronized (this.mLock) {
            writer.println("mRunningActivities:" + this.mRunningActivities + " ,mEventMonitoringActive:" + this.mEventMonitoringActive);
            writer.println("mBlockingPresentations:");
            for (int i = 0; i < this.mBlockingPresentations.size(); i++) {
                Presentation p = this.mBlockingPresentations.valueAt(i);
                if (p != null) {
                    writer.println("display:" + this.mBlockingPresentations.keyAt(i) + " showing:" + p.isShowing());
                }
            }
        }
    }

    private void clearRunningActivitiesLocked() {
        int currentUser = ActivityManager.getCurrentUser();
        for (int i = this.mRunningActivities.size() - 1; i >= 0; i--) {
            RunningActivityInfo info = this.mRunningActivities.valueAt(i);
            if (info == null || info.userId != currentUser) {
                this.mRunningActivities.removeAt(i);
            }
        }
    }

    private void postRecheck(long delayMs) {
        this.mHandlerThread.getThreadHandler().postDelayed(this.mActivityCheckRunnable, delayMs);
    }

    private void startMonitoringEvents() {
        synchronized (this.mLock) {
            if (this.mEventMonitoringActive) {
                return;
            }
            this.mEventMonitoringActive = true;
            CarPowerManager carPowerManager = CarLocalServices.createCarPowerManager(this.mContext);
            this.mCarPowerManager = carPowerManager;
            CarUserService userService = (CarUserService) CarLocalServices.getService(CarUserService.class);
            userService.addUserLifecycleListener(this.mUserLifecycleListener);
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.intent.action.PACKAGE_CHANGED");
            filter.addAction("android.intent.action.PACKAGE_REPLACED");
            filter.addAction("android.intent.action.PACKAGE_ADDED");
            filter.addAction("android.intent.action.PACKAGE_REMOVED");
            filter.addDataScheme("package");
            this.mContext.registerReceiverAsUser(this.mBroadcastReceiver, UserHandle.ALL, filter, null, null);
            try {
                this.mAm.registerTaskStackListener(this.mTaskStackListener);
                this.mAm.registerProcessObserver(this.mProcessObserver);
            } catch (RemoteException e) {
                Log.e(CarLog.TAG_AM, "remote exception from AM", e);
            }
            try {
                carPowerManager.setListener(this.mCarPowerStateListener);
            } catch (Exception e2) {
                Log.e(CarLog.TAG_AM, "Got exception from CarPowerManager", e2);
            }
        }
    }

    private void stopMonitoringEvents() {
        synchronized (this.mLock) {
            if (this.mEventMonitoringActive) {
                this.mEventMonitoringActive = false;
                CarPowerManager carPowerManager = this.mCarPowerManager;
                this.mCarPowerManager = null;
                if (carPowerManager != null) {
                    carPowerManager.clearListener();
                }
                this.mHandlerThread.getThreadHandler().removeCallbacks(this.mActivityCheckRunnable);
                CarUserService userService = (CarUserService) CarLocalServices.getService(CarUserService.class);
                userService.removeUserLifecycleListener(this.mUserLifecycleListener);
                try {
                    this.mAm.unregisterTaskStackListener(this.mTaskStackListener);
                    this.mAm.unregisterProcessObserver(this.mProcessObserver);
                } catch (RemoteException e) {
                    Log.e(CarLog.TAG_AM, "remote exception from AM", e);
                }
                this.mContext.unregisterReceiver(this.mBroadcastReceiver);
            }
        }
    }

    private List<ActivityManager.StackInfo> getStackInfos() {
        try {
            return this.mAm.getAllStackInfos();
        } catch (RemoteException e) {
            Log.e(CarLog.TAG_AM, "remote exception from AM", e);
            return null;
        }
    }

    private boolean launchIfNecessary(int displayId) throws Throwable {
        long now;
        List<ActivityManager.StackInfo> infos = getStackInfos();
        int i = 0;
        if (infos == null) {
            Log.e(CarLog.TAG_AM, "cannot get StackInfo from AM");
            return false;
        }
        long now2 = SystemClock.elapsedRealtime();
        synchronized (this.mLock) {
            try {
                try {
                    try {
                        if (this.mRunningActivities.size() == 0) {
                            return false;
                        }
                        for (int i2 = this.mRunningActivities.size() - 1; i2 >= 0; i2--) {
                            RunningActivityInfo activityInfo = this.mRunningActivities.valueAt(i2);
                            activityInfo.isVisible = false;
                            if (!isUserAllowedToLaunchActivity(activityInfo.userId)) {
                                final int displayIdForActivity = this.mRunningActivities.keyAt(i2);
                                if (activityInfo.taskId != -1) {
                                    Log.i(CarLog.TAG_AM, "Finishing fixed activity on user switching:" + activityInfo);
                                    try {
                                        this.mAm.removeTask(activityInfo.taskId);
                                    } catch (RemoteException e) {
                                        Log.e(CarLog.TAG_AM, "remote exception from AM", e);
                                    }
                                    CarServiceUtils.runOnMain(new Runnable() { // from class: com.android.car.am.-$$Lambda$FixedActivityService$IccnpvAZyshydmCNa9_hpgbLdgE
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            this.f$0.lambda$launchIfNecessary$3$FixedActivityService(displayIdForActivity);
                                        }
                                    });
                                }
                                this.mRunningActivities.removeAt(i2);
                            }
                        }
                        int previousDisplayId = -1;
                        for (ActivityManager.StackInfo stackInfo : infos) {
                            if (stackInfo.displayId != previousDisplayId) {
                                previousDisplayId = stackInfo.displayId;
                                RunningActivityInfo activityInfo2 = this.mRunningActivities.get(stackInfo.displayId);
                                if (activityInfo2 != null) {
                                    int topUserId = stackInfo.taskUserIds[stackInfo.taskUserIds.length - 1];
                                    if (activityInfo2.intent.getComponent().equals(stackInfo.topActivity) && activityInfo2.userId == topUserId && stackInfo.visible) {
                                        activityInfo2.isVisible = true;
                                        activityInfo2.taskId = stackInfo.taskIds[stackInfo.taskIds.length - 1];
                                    } else {
                                        activityInfo2.previousTaskId = stackInfo.taskIds[stackInfo.taskIds.length - 1];
                                        Log.i(CarLog.TAG_AM, "Unmatched top activity will be removed:" + stackInfo.topActivity + " top task id:" + activityInfo2.previousTaskId + " user:" + topUserId + " display:" + stackInfo.displayId);
                                        activityInfo2.inBackground = false;
                                        for (int i3 = 0; i3 < stackInfo.taskIds.length - 1; i3++) {
                                            if (activityInfo2.taskId == stackInfo.taskIds[i3]) {
                                                activityInfo2.inBackground = true;
                                            }
                                        }
                                        if (!activityInfo2.inBackground) {
                                            activityInfo2.taskId = -1;
                                        }
                                    }
                                }
                            }
                        }
                        int i4 = 0;
                        while (i4 < this.mRunningActivities.size()) {
                            try {
                                RunningActivityInfo activityInfo3 = this.mRunningActivities.valueAt(i4);
                                long timeSinceLastLaunchMs = now2 - activityInfo3.lastLaunchTimeMs;
                                if (activityInfo3.isVisible) {
                                    if (timeSinceLastLaunchMs < CRASH_FORGET_INTERVAL_MS) {
                                        now = now2;
                                    } else {
                                        activityInfo3.consecutiveRetries = i;
                                        now = now2;
                                    }
                                } else if (!isComponentAvailable(activityInfo3.intent.getComponent(), activityInfo3.userId)) {
                                    now = now2;
                                } else {
                                    now = now2;
                                    if (activityInfo3.consecutiveRetries <= 0 || timeSinceLastLaunchMs >= RECHECK_INTERVAL_MS) {
                                        try {
                                            if (activityInfo3.consecutiveRetries >= 5) {
                                                if (!activityInfo3.failureLogged) {
                                                    activityInfo3.failureLogged = true;
                                                    Log.w(CarLog.TAG_AM, "Too many relaunch failure of fixed activity:" + activityInfo3);
                                                }
                                            } else {
                                                Log.i(CarLog.TAG_AM, "Launching Activity for fixed mode. Intent:" + activityInfo3.intent + ",userId:" + UserHandle.of(activityInfo3.userId) + ",displayId:" + this.mRunningActivities.keyAt(i4));
                                                if (!activityInfo3.inBackground) {
                                                    activityInfo3.consecutiveRetries++;
                                                }
                                                try {
                                                    postRecheck(RECHECK_INTERVAL_MS);
                                                    postRecheck(CRASH_FORGET_INTERVAL_MS);
                                                    this.mContext.startActivityAsUser(activityInfo3.intent, activityInfo3.activityOptions.toBundle(), UserHandle.of(activityInfo3.userId));
                                                    activityInfo3.isVisible = true;
                                                    activityInfo3.lastLaunchTimeMs = SystemClock.elapsedRealtime();
                                                } catch (Exception e2) {
                                                    Log.w(CarLog.TAG_AM, "Cannot start activity:" + activityInfo3.intent, e2);
                                                }
                                            }
                                        } catch (Throwable th) {
                                            th = th;
                                        }
                                    }
                                }
                                i4++;
                                now2 = now;
                                i = 0;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                        RunningActivityInfo activityInfo4 = this.mRunningActivities.get(displayId);
                        if (activityInfo4 == null) {
                            return false;
                        }
                        return activityInfo4.isVisible;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Throwable th5) {
                th = th5;
            }
            throw th;
        }
    }

    public /* synthetic */ void lambda$launchIfNecessary$3$FixedActivityService(int displayIdForActivity) {
        Display display = this.mDm.getDisplay(displayIdForActivity);
        if (display == null) {
            Log.e(CarLog.TAG_AM, "Display not available, cannot launnch window:" + displayIdForActivity);
            return;
        }
        Presentation p = new Presentation(this.mContext, display, R.style.Theme.Black.NoTitleBar.Fullscreen);
        p.setContentView(com.android.car.R.layout.activity_continuous_blank);
        synchronized (this.mLock) {
            RunningActivityInfo info = this.mRunningActivities.get(displayIdForActivity);
            if (info != null && info.userId == ActivityManager.getCurrentUser()) {
                Log.i(CarLog.TAG_AM, "Do not show Presentation, new req already made");
                return;
            }
            this.mBlockingPresentations.append(displayIdForActivity, p);
            p.getWindow().setType(2008);
            p.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: launchIfNecessary, reason: merged with bridge method [inline-methods] */
    public void lambda$new$1$FixedActivityService() throws Throwable {
        launchIfNecessary(-1);
    }

    private void logComponentNotFound(ComponentName component, int userId, Exception e) {
        Log.e(CarLog.TAG_AM, "Specified Component not found:" + component + " for userid:" + userId, e);
    }

    private boolean isComponentAvailable(ComponentName component, int userId) {
        try {
            PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfoAsUser(component.getPackageName(), 1, userId);
            if (packageInfo == null || packageInfo.activities == null) {
                logComponentNotFound(component, userId, new RuntimeException());
                return false;
            }
            String fullName = component.getClassName();
            String shortName = component.getShortClassName();
            for (ActivityInfo info : packageInfo.activities) {
                if (info.name.equals(fullName) || info.name.equals(shortName)) {
                    return true;
                }
            }
            logComponentNotFound(component, userId, new RuntimeException());
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            logComponentNotFound(component, userId, e);
            return false;
        }
    }

    private boolean isUserAllowedToLaunchActivity(int userId) {
        int currentUser = ActivityManager.getCurrentUser();
        if (userId == currentUser) {
            return true;
        }
        int[] profileIds = this.mUm.getEnabledProfileIds(currentUser);
        if (profileIds == null) {
            return false;
        }
        for (int id : profileIds) {
            if (id == userId) {
                return true;
            }
        }
        return false;
    }

    private boolean isDisplayAllowedForFixedMode(int displayId) {
        if (displayId == 0 || displayId == -1) {
            Log.w(CarLog.TAG_AM, "Target display cannot be used for fixed mode, displayId:" + displayId, new RuntimeException());
            return false;
        }
        return true;
    }

    public boolean startFixedActivityModeForDisplayAndUser(Intent intent, ActivityOptions options, int displayId, int userId) throws Throwable {
        if (!isDisplayAllowedForFixedMode(displayId)) {
            return false;
        }
        if (options == null) {
            Log.e(CarLog.TAG_AM, "startFixedActivityModeForDisplayAndUser, null options");
            return false;
        }
        if (!isUserAllowedToLaunchActivity(userId)) {
            Log.e(CarLog.TAG_AM, "startFixedActivityModeForDisplayAndUser, requested user:" + userId + " cannot launch activity, Intent:" + intent);
            return false;
        }
        ComponentName component = intent.getComponent();
        if (component == null) {
            Log.e(CarLog.TAG_AM, "startFixedActivityModeForDisplayAndUser: No component specified for requested Intent" + intent);
            return false;
        }
        if (!isComponentAvailable(component, userId)) {
            return false;
        }
        boolean startMonitoringEvents = false;
        synchronized (this.mLock) {
            Presentation p = (Presentation) this.mBlockingPresentations.removeReturnOld(displayId);
            if (p != null) {
                p.dismiss();
            }
            if (this.mRunningActivities.size() == 0) {
                startMonitoringEvents = true;
            }
            RunningActivityInfo activityInfo = this.mRunningActivities.get(displayId);
            boolean replaceEntry = true;
            if (activityInfo != null && activityInfo.intent.equals(intent) && options.equals(activityInfo.activityOptions) && userId == activityInfo.userId) {
                replaceEntry = false;
                if (activityInfo.isVisible) {
                    return true;
                }
            }
            if (replaceEntry) {
                this.mRunningActivities.put(displayId, new RunningActivityInfo(intent, options, userId));
            }
            boolean launched = launchIfNecessary(displayId);
            if (!launched) {
                synchronized (this.mLock) {
                    this.mRunningActivities.remove(displayId);
                }
            }
            if (startMonitoringEvents && launched) {
                startMonitoringEvents();
            }
            return launched;
        }
    }

    public void stopFixedActivityMode(int displayId) {
        if (!isDisplayAllowedForFixedMode(displayId)) {
            return;
        }
        boolean stopMonitoringEvents = false;
        synchronized (this.mLock) {
            this.mRunningActivities.remove(displayId);
            if (this.mRunningActivities.size() == 0) {
                stopMonitoringEvents = true;
            }
        }
        if (stopMonitoringEvents) {
            stopMonitoringEvents();
        }
    }
}
