package com.android.systemui.stackdivider;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.bydauto.key.AbsBYDAutoKeyListener;
import android.hardware.bydauto.key.BYDAutoKeyDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.IRotationWatcher;
import android.view.IWindowManager;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.ViewGroup;
import android.view.WindowManagerGlobal;
import android.window.WindowContainerTransaction;
import android.window.WindowOrganizer;
import androidx.core.os.EnvironmentCompat;
import androidx.core.view.GravityCompat;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.SystemUI;
import com.android.systemui.TransactionPool;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.ActivityOptionsCompat;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.stackdivider.DividerView;
import com.android.systemui.stackdivider.SyncTransactionQueue;
import com.android.systemui.stackdivider.view.IndexWindowManager;
import com.android.systemui.stackdivider.view.help.SplitScreenHelpManager;
import com.android.systemui.stackdivider.view.preview.SplitPreviewWindowManager;
import com.android.systemui.stackdivider.view.restart.RestartSplitScreenViewManager;
import com.android.systemui.stackdivider.view.swap.SwapScreenViewManager;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.wm.Util;
import com.android.systemui.util.ToastUtil;
import com.android.systemui.wm.DisplayChangeController;
import com.android.systemui.wm.DisplayController;
import com.android.systemui.wm.DisplayLayout;
import com.android.systemui.wm.SystemWindows;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.Constant;
import com.byd.card.forbidden.ForbiddenManager;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.ts.avm.sdk.AvmManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.inject.Singleton;

/* JADX INFO: loaded from: classes.dex */
@Singleton
public class Divider extends SystemUI implements DividerView.DividerCallbacks, DisplayController.OnDisplaysChangedListener {
    static final boolean DEBUG = true;
    static final int DEFAULT_APP_TRANSITION_DURATION = 336;
    private static final String TAG = "Divider";
    private DisplayController mDisplayController;
    private DividerUtils mDividerUtils;
    private DividerHandler mHandler;
    private IndexWindowManager mIndexWindowManager;
    private RestartSplitScreenViewManager mRestartSplitScreenViewManager;
    private SplitDisplayLayout mRotateSplitLayout;
    private SplitDisplayLayout mSplitLayout;
    private SwapScreenViewManager mSwapScreenViewManager;
    private SystemWindows mSystemWindows;
    TransactionPool mTransactionPool;
    private DividerView mView;
    private DividerWindowManager mWindowManager;
    private WindowManagerProxy mWindowManagerProxy;
    private IWindowManager mWmService;
    private final DividerState mDividerState = new DividerState();
    private boolean mVisible = false;
    private boolean mMinimized = false;
    private final boolean mAdjustedForIme = false;
    private boolean mHomeStackResizable = false;
    private final ArrayList<WeakReference<Consumer<Boolean>>> mDockedStackExistsListeners = new ArrayList<>();
    private final SplitScreenTaskOrganizer mSplits = new SplitScreenTaskOrganizer(this);
    private boolean mIsApplyDismissSplitStatus = false;
    private boolean mIsResized = true;
    private final DisplayChangeController.OnDisplayChangingListener mRotationController = new DisplayChangeController.OnDisplayChangingListener() { // from class: com.android.systemui.stackdivider.-$$Lambda$Divider$MpLf0tnrAmepYXKPHpqg5MDPFzo
        @Override // com.android.systemui.wm.DisplayChangeController.OnDisplayChangingListener
        public final void onRotateDisplay(int i, int i2, int i3, WindowContainerTransaction windowContainerTransaction) {
            this.f$0.lambda$new$0$Divider(i, i2, i3, windowContainerTransaction);
        }
    };
    private final TaskStackChangeListener mActivityRestartListener = new TaskStackChangeListener() { // from class: com.android.systemui.stackdivider.Divider.1
        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public void onActivityRestartAttempt(ActivityManager.RunningTaskInfo task, boolean homeTaskVisible, boolean clearedTask, boolean wasVisible) {
            if (wasVisible && task.configuration.windowConfiguration.getWindowingMode() == 3 && Divider.this.mSplits.isSplitScreenSupported()) {
                BydLogHelper.logD("onActivityRestartAttempt task : " + task + " , homeTaskVisible : " + homeTaskVisible);
                if (Divider.this.isMinimized()) {
                    Divider.this.onUndockingTask(1);
                }
            }
        }

        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public void onTaskStackChanged() {
            BydLogHelper.logD("onTaskStackChanged");
            Divider.this.setPrimaryAppInfo();
            Divider.this.setSecondaryAppInfo();
        }

        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public void onTaskRemoved(int taskId) {
            BydLogHelper.logD("onTaskRemoved taskId : " + taskId + " , isSplitActive : " + Divider.this.isSplitActive());
        }

        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public void onTaskFocusChanged(int taskId, boolean focused) {
            boolean zIsSplitActive = Divider.this.isSplitActive();
            BydLogHelper.logD("onTaskFocusChanged taskId : " + taskId + " , focused : " + focused + " , isSplitActive : " + zIsSplitActive + " , isDismissingSplit : " + Divider.this.isDismissingSplit + " , isSwapping : " + Divider.this.isSwapping());
            if (zIsSplitActive && !Divider.this.isDismissingSplit && Divider.this.mView != null && focused) {
                ActivityManager.StackInfo stackInfo = Divider.this.mDividerUtils.getStackInfo(true);
                ActivityManager.StackInfo stackInfo2 = Divider.this.mDividerUtils.getStackInfo(false);
                BydLogHelper.logD("onTaskFocusChanged primaryStackInfo : " + stackInfo);
                BydLogHelper.logD("onTaskFocusChanged secondaryStackInfo : " + stackInfo2);
                int[] iArr = stackInfo.taskIds;
                int i = iArr[iArr.length - 1];
                int[] iArr2 = stackInfo2.taskIds;
                int i2 = iArr2[iArr2.length - 1];
                BydLogHelper.logD("onTaskFocusChanged primaryTaskId : " + i + " , secondaryTaskId : " + i2);
                if (i == taskId || i2 == taskId) {
                    Divider.this.mDividerUtils.setDividerStatus();
                    Divider.this.setNewFocusStatus();
                }
            }
            if (Divider.this.mRestartSplitScreenViewManager.isShow()) {
                Divider.this.mRestartSplitScreenViewManager.hideView();
            }
        }

        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public void onActivityDismissingDockedStack() {
            BydLogHelper.logD("onActivityDismissingDockedStack");
            ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.getInstance().getRunningTask();
            BydLogHelper.logD("onActivityDismissingDockedStack runningTask : " + runningTask);
            if (runningTask != null) {
                BydLogHelper.logD("onActivityDismissingDockedStack runningTask getPackageName : " + runningTask.baseActivity.getPackageName());
                String packageName = runningTask.baseActivity.getPackageName();
                packageName.hashCode();
                switch (packageName) {
                    case "com.android.car.activityresolver":
                    case "com.android.launcher3":
                    case "com.neusoft.na.navigation":
                    case "com.huawei.maps.auto.app":
                    case "com.android.systemui":
                        break;
                    default:
                        ToastUtil.show(Divider.this.mContext, R.string.dock_non_resizeble_failed_to_dock_text, 0);
                        break;
                }
            }
        }

        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public void onActivityLaunchOnSecondaryDisplayFailed() {
            BydLogHelper.logD("onActivityLaunchOnSecondaryDisplayFailed App does not support launch on secondary displays");
        }

        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public void onActivityForcedResizable(String packageName, int taskId, int reason) {
            BydLogHelper.logD("onActivityForcedResizable packageName : " + packageName + " , taskId : " + taskId + " , reason : " + reason);
        }
    };
    boolean isDismissingSplit = false;

    @Override // com.android.systemui.stackdivider.DividerView.DividerCallbacks
    public void growRecents() {
    }

    @Override // com.android.systemui.SystemUI
    protected void onConfigurationChanged(Configuration newConfig) {
    }

    public /* synthetic */ void lambda$new$0$Divider(int i, int i2, int i3, WindowContainerTransaction windowContainerTransaction) {
        int currentPosition;
        if (!this.mSplits.isSplitScreenSupported() || this.mWindowManagerProxy == null) {
            return;
        }
        this.mIsResized = true;
        WindowContainerTransaction windowContainerTransaction2 = new WindowContainerTransaction();
        SplitDisplayLayout splitDisplayLayout = new SplitDisplayLayout(this.mContext, new DisplayLayout(this.mDisplayController.getDisplayLayout(i)), this.mSplits);
        splitDisplayLayout.rotateTo(i3);
        this.mRotateSplitLayout = splitDisplayLayout;
        if (isDividerVisible()) {
            currentPosition = this.mMinimized ? this.mView.mSnapTargetBeforeMinimized.position : this.mView.getCurrentPosition();
        } else {
            currentPosition = splitDisplayLayout.getSnapAlgorithm().getMiddleTarget().position;
        }
        splitDisplayLayout.resizeSplits(splitDisplayLayout.getSnapAlgorithm().calculateNonDismissingSnapTarget(currentPosition).position, windowContainerTransaction2);
        if (isSplitActive() && this.mHomeStackResizable) {
            WindowManagerProxy.applyHomeTasksMinimized(splitDisplayLayout, this.mSplits.mSecondary.token, windowContainerTransaction2);
        }
        if (this.mWindowManagerProxy.queueSyncTransactionIfWaiting(windowContainerTransaction2)) {
            BydLogHelper.logD(TAG, "Screen rotated while other operations were pending, this may result in some graphical artifacts.");
        } else {
            windowContainerTransaction.merge(windowContainerTransaction2, true);
        }
    }

    public Divider() {
        BydLogHelper.logD(TAG, "new Divider");
    }

    @Override // com.android.systemui.SystemUI
    public void start() {
        BydLogHelper.logD("Divider start");
        this.mDividerUtils = DividerUtils.getInstance();
        putComponent(Divider.class, this);
        this.mWmService = WindowManagerGlobal.getWindowManagerService();
        this.mHandler = new DividerHandler(this);
    }

    @Override // com.android.systemui.SystemUI
    protected void onBootCompleted() {
        this.mIndexWindowManager = (IndexWindowManager) Dependency.get(IndexWindowManager.class);
        this.mTransactionPool = new TransactionPool();
        this.mDisplayController = new DisplayController(this.mContext, this.mHandler, this.mWmService);
        this.mSystemWindows = new SystemWindows(this.mContext, this.mDisplayController, this.mWmService);
        this.mWindowManagerProxy = new WindowManagerProxy(this.mTransactionPool, this.mHandler);
        this.mWindowManager = new DividerWindowManager(this.mSystemWindows);
        this.mDisplayController.addDisplayWindowListener(this);
        this.mSwapScreenViewManager = SwapScreenViewManager.getInstance(this.mContext, this);
        try {
            IWindowManager.Stub.asInterface(ServiceManager.getService("window")).watchRotation(new IRotationWatcher.Stub() { // from class: com.android.systemui.stackdivider.Divider.2
                public void onRotationChanged(int rotation) {
                    BydLogHelper.logD("onRotationChanged rotation is " + rotation + " , isSplitActive : " + Divider.this.isSplitActive());
                    if (Divider.this.isSplitActive()) {
                        Divider.this.mHandler.removeMessages(4);
                        Divider.this.mHandler.sendEmptyMessageDelayed(4, 200L);
                    }
                }
            }, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        RestartSplitScreenViewManager restartSplitScreenViewManager = RestartSplitScreenViewManager.getInstance();
        this.mRestartSplitScreenViewManager = restartSplitScreenViewManager;
        restartSplitScreenViewManager.init(this.mContext);
        AvmManager.getInstance().registerAvmStatusListener(new AvmManager.AvmStatusListener() { // from class: com.android.systemui.stackdivider.-$$Lambda$Divider$UTP83mPwSP98WzKHC18kx1Pz--U
            @Override // com.ts.avm.sdk.AvmManager.AvmStatusListener
            public final void onStatusChanged(int i, String str) {
                this.f$0.lambda$onBootCompleted$2$Divider(i, str);
            }
        });
        BYDAutoKeyDevice.getInstance(this.mContext).registerListener(new AbsBYDAutoKeyListener() { // from class: com.android.systemui.stackdivider.Divider.3
            @Override // android.hardware.bydauto.key.AbsBYDAutoKeyListener
            public void onKeyClick(int keyValue, int click) {
                super.onKeyClick(keyValue, click);
                BydLogHelper.logD("onKeyClick keyValue = " + keyValue + " , click : " + click);
                if (keyValue == 12) {
                    Divider.this.mHandler.removeMessages(8);
                    Divider.this.mHandler.sendEmptyMessageDelayed(8, 50L);
                }
            }
        });
    }

    public /* synthetic */ void lambda$onBootCompleted$2$Divider(final int i, String str) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$Divider$ogkPkt8g7kZccS0o7XeDGEQV6pQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onBootCompleted$1$Divider(i);
            }
        });
    }

    public /* synthetic */ void lambda$onBootCompleted$1$Divider(int i) {
        boolean zIsSplitActive = isSplitActive();
        BydLogHelper.logD("onStatusChanged isSplitActive = " + zIsSplitActive);
        if (zIsSplitActive) {
            BydLogHelper.logD("onStatusChanged code = " + i);
            if (i == 3) {
                AppInfo secondaryAppInfoBeforeAvm = this.mDividerUtils.getSecondaryAppInfoBeforeAvm();
                BydLogHelper.logD("onStatusChanged BACKGROUND_STATE appInfo : " + secondaryAppInfoBeforeAvm);
                if (secondaryAppInfoBeforeAvm == null || secondaryAppInfoBeforeAvm.packageName.equals("com.android.systemui")) {
                    return;
                }
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("packageName", secondaryAppInfoBeforeAvm.getPackageName());
                bundle.putString(Constants.PackageInfo.ACTIVITY, secondaryAppInfoBeforeAvm.getActivity());
                message.setData(bundle);
                message.what = 9;
                this.mHandler.sendMessageDelayed(message, 200L);
            }
        }
    }

    @Override // com.android.systemui.wm.DisplayController.OnDisplaysChangedListener
    public void onDisplayAdded(int displayId) {
        if (displayId != 0) {
            return;
        }
        BydLogHelper.logD("onDisplayAdded displayId " + displayId);
        this.mSplitLayout = new SplitDisplayLayout(this.mDisplayController.getDisplayContext(displayId), this.mDisplayController.getDisplayLayout(displayId), this.mSplits);
        this.mDisplayController.addDisplayChangingController(this.mRotationController);
        if (!ActivityTaskManager.supportsSplitScreenMultiWindow(this.mContext)) {
            removeDivider();
            return;
        }
        try {
            this.mSplits.init();
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            this.mSplitLayout.resizeSplits(this.mSplitLayout.getSnapAlgorithm().getMiddleTarget().position, windowContainerTransaction);
            WindowOrganizer.applyTransaction(windowContainerTransaction);
            ActivityManagerWrapper.getInstance().registerTaskStackListener(this.mActivityRestartListener);
        } catch (Exception e) {
            BydLogHelper.logE(TAG, "Failed to register docked stack listener : " + e);
            removeDivider();
        }
    }

    public void resize(boolean isPrimary) {
        SplitDisplayLayout splitDisplayLayout = this.mSplitLayout;
        if (splitDisplayLayout == null || splitDisplayLayout.mPrimary == null || this.mSplitLayout.mSecondary == null) {
            BydLogHelper.logI(TAG, "resize null!");
            return;
        }
        BydLogHelper.logI(TAG, "resize mIsResized = " + this.mIsResized);
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        this.mSplitLayout.resizeSplits(this.mSplitLayout.getSnapAlgorithm().getMiddleTarget().position);
        if (isPrimary) {
            if (this.mIsResized) {
                Rect rect = this.mSplitLayout.mPrimary;
                rect.right--;
            }
            windowContainerTransaction.setBounds(this.mSplits.mPrimary.token, this.mSplitLayout.mPrimary);
        } else {
            if (this.mIsResized) {
                this.mSplitLayout.mSecondary.left++;
            }
            windowContainerTransaction.setBounds(this.mSplits.mSecondary.token, this.mSplitLayout.mSecondary);
        }
        this.mIsResized = !this.mIsResized;
        WindowOrganizer.applyTransaction(windowContainerTransaction);
    }

    @Override // com.android.systemui.wm.DisplayController.OnDisplaysChangedListener
    public void onDisplayConfigurationChanged(int displayId, Configuration newConfig) {
        if (displayId == 0 && this.mSplits.isSplitScreenSupported()) {
            SplitDisplayLayout splitDisplayLayout = new SplitDisplayLayout(this.mDisplayController.getDisplayContext(displayId), this.mDisplayController.getDisplayLayout(displayId), this.mSplits);
            this.mSplitLayout = splitDisplayLayout;
            if (this.mRotateSplitLayout == null) {
                int i = splitDisplayLayout.getSnapAlgorithm().getMiddleTarget().position;
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                this.mSplitLayout.resizeSplits(i, windowContainerTransaction);
                WindowOrganizer.applyTransaction(windowContainerTransaction);
            } else if (splitDisplayLayout.mDisplayLayout.rotation() == this.mRotateSplitLayout.mDisplayLayout.rotation()) {
                this.mSplitLayout.mPrimary = new Rect(this.mRotateSplitLayout.mPrimary);
                this.mSplitLayout.mSecondary = new Rect(this.mRotateSplitLayout.mSecondary);
                this.mRotateSplitLayout = null;
            }
            BydLogHelper.logD(TAG, "onDisplayConfigurationChanged isSplitActive : " + isSplitActive());
            if (isSplitActive()) {
                update(newConfig);
            }
        }
    }

    Handler getHandler() {
        return this.mHandler;
    }

    public DividerView getView() {
        return this.mView;
    }

    public boolean isMinimized() {
        return this.mMinimized;
    }

    public boolean isHomeStackResizable() {
        return this.mHomeStackResizable;
    }

    public boolean isDividerVisible() {
        DividerView dividerView = this.mView;
        return dividerView != null && dividerView.getVisibility() == 0;
    }

    public boolean isSplitActive() {
        if (this.mSplits.mPrimary != null && this.mSplits.mSecondary != null) {
            BydLogHelper.logD("isSplitActive mPrimary topActivityType : " + this.mSplits.mPrimary.topActivityType + " , mSecondary topActivityType : " + this.mSplits.mSecondary.topActivityType);
        }
        return (this.mSplits.mPrimary == null || this.mSplits.mSecondary == null || (this.mSplits.mPrimary.topActivityType == 0 && this.mSplits.mSecondary.topActivityType == 0)) ? false : true;
    }

    public boolean isDividerViewShow() {
        DividerView dividerView = this.mView;
        return dividerView != null && dividerView.getVisibility() == 0;
    }

    private void addDivider(Configuration configuration) {
        Context displayContext = this.mDisplayController.getDisplayContext(this.mContext.getDisplayId());
        this.mView = (DividerView) LayoutInflater.from(displayContext).inflate(R.layout.docked_stack_divider, (ViewGroup) null);
        DisplayLayout displayLayout = this.mDisplayController.getDisplayLayout(this.mContext.getDisplayId());
        this.mView.injectDependencies(this.mWindowManager, this.mDividerState, this, this.mSplits, this.mSplitLayout, this.mWindowManagerProxy);
        BydLogHelper.logD("addDivider mVisible : " + this.mVisible);
        this.mView.setVisibility(this.mVisible ? 0 : 4);
        this.mView.setMinimizedDockStack(this.mMinimized, this.mHomeStackResizable, null);
        int dimensionPixelSize = displayContext.getResources().getDimensionPixelSize(android.R.dimen.config_viewMaxFlingVelocity);
        boolean z = configuration.orientation == 2;
        int iWidth = z ? dimensionPixelSize : displayLayout.width();
        if (z) {
            dimensionPixelSize = displayLayout.height();
        }
        this.mWindowManager.add(this.mView, iWidth, dimensionPixelSize, this.mContext.getDisplayId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeDivider() {
        BydLogHelper.logD("removeDivider");
        DividerView dividerView = this.mView;
        if (dividerView != null) {
            dividerView.onDividerRemoved();
        }
        this.mWindowManager.remove();
        this.mIndexWindowManager.remove();
    }

    private void update(Configuration configuration) {
        BydLogHelper.logD("update");
        removeDivider();
        addDivider(configuration);
        if (this.mMinimized) {
            this.mView.setMinimizedDockStack(true, this.mHomeStackResizable, null);
            updateTouchable();
        }
        setNewFocusStatus();
    }

    void onTaskVanished() {
        BydLogHelper.logD("onTaskVanished");
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$Divider$UcCzdlyD9nq6WXRfNu1mxHA4zV8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.removeDivider();
            }
        });
    }

    private void updateVisibility(final boolean visible) {
        BydLogHelper.logD("Updating visibility " + this.mVisible + "->" + visible);
        if (this.mVisible != visible) {
            this.mVisible = visible;
            this.mView.setVisibility(visible ? 0 : 4);
            BydLogHelper.logD("mIndexWindowManager updateVisibility visible : " + visible);
            this.mIndexWindowManager.updateVisibility(visible);
            if (visible) {
                this.mView.enterSplitMode(this.mHomeStackResizable);
            } else {
                this.mView.exitSplitMode();
                this.mWindowManagerProxy.runInSync(new SyncTransactionQueue.TransactionRunnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$Divider$gHhBzIPaREMQMOYzM_zcWf8vPQk
                    @Override // com.android.systemui.stackdivider.SyncTransactionQueue.TransactionRunnable
                    public final void runWithTransaction(SurfaceControl.Transaction transaction) {
                        this.f$0.lambda$updateVisibility$3$Divider(transaction);
                    }
                });
            }
            synchronized (this.mDockedStackExistsListeners) {
                this.mDockedStackExistsListeners.removeIf(new Predicate() { // from class: com.android.systemui.stackdivider.-$$Lambda$Divider$80J6R6C-qpeAy-nEKC__3xF7b7Y
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Divider.lambda$updateVisibility$4(visible, (WeakReference) obj);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$updateVisibility$3$Divider(SurfaceControl.Transaction transaction) {
        this.mView.setMinimizedDockStack(false, this.mHomeStackResizable, transaction);
    }

    static /* synthetic */ boolean lambda$updateVisibility$4(boolean z, WeakReference weakReference) {
        Consumer consumer = (Consumer) weakReference.get();
        if (consumer != null) {
            consumer.accept(Boolean.valueOf(z));
        }
        return consumer == null;
    }

    public void setMinimized(final boolean minimized) {
        BydLogHelper.logD("posting ext setMinimized " + minimized + " vis:" + this.mVisible);
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$Divider$rve8DVGvgypZsTFoPLXP8xtj100
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setMinimized$5$Divider(minimized);
            }
        });
    }

    public /* synthetic */ void lambda$setMinimized$5$Divider(boolean z) {
        BydLogHelper.logD("run posted ext setMinimized " + z + " vis:" + this.mVisible);
        if (this.mVisible) {
            setHomeMinimized(z, this.mHomeStackResizable);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setHomeMinimized(final boolean r6, boolean r7) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "setHomeMinimized  min:"
            java.lang.StringBuilder r0 = r0.append(r1)
            boolean r1 = r5.mMinimized
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "->"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r6)
            java.lang.String r2 = " hrsz:"
            java.lang.StringBuilder r0 = r0.append(r2)
            boolean r2 = r5.mHomeStackResizable
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r7)
            java.lang.String r1 = " split:"
            java.lang.StringBuilder r0 = r0.append(r1)
            boolean r1 = r5.isDividerVisible()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.bydux.api.common.tools.log.BydLogHelper.logD(r0)
            android.window.WindowContainerTransaction r0 = new android.window.WindowContainerTransaction
            r0.<init>()
            boolean r1 = r5.mMinimized
            r2 = 0
            r3 = 1
            if (r1 == r6) goto L52
            r1 = r3
            goto L53
        L52:
            r1 = r2
        L53:
            if (r1 == 0) goto L57
            r5.mMinimized = r6
        L57:
            com.android.systemui.stackdivider.SplitScreenTaskOrganizer r6 = r5.mSplits
            android.app.ActivityManager$RunningTaskInfo r6 = r6.mPrimary
            android.window.WindowContainerToken r6 = r6.token
            boolean r4 = r5.mMinimized
            r4 = r4 ^ r3
            r0.setFocusable(r6, r4)
            boolean r6 = r5.mHomeStackResizable
            if (r6 == r7) goto L69
            r6 = r3
            goto L6a
        L69:
            r6 = r2
        L6a:
            if (r6 == 0) goto L80
            r5.mHomeStackResizable = r7
            boolean r6 = r5.isDividerVisible()
            if (r6 == 0) goto L80
            com.android.systemui.stackdivider.SplitDisplayLayout r6 = r5.mSplitLayout
            com.android.systemui.stackdivider.SplitScreenTaskOrganizer r7 = r5.mSplits
            android.app.ActivityManager$RunningTaskInfo r7 = r7.mSecondary
            android.window.WindowContainerToken r7 = r7.token
            com.android.systemui.stackdivider.WindowManagerProxy.applyHomeTasksMinimized(r6, r7, r0)
            goto L81
        L80:
            r2 = r3
        L81:
            com.android.systemui.stackdivider.DividerView r6 = r5.mView
            if (r6 == 0) goto L94
            android.view.Display r6 = r6.getDisplay()
            if (r6 == 0) goto L94
            com.android.systemui.stackdivider.DividerView r6 = r5.mView
            android.view.Display r6 = r6.getDisplay()
            r6.getDisplayId()
        L94:
            r5.updateTouchable()
            if (r2 == 0) goto Lab
            com.android.systemui.stackdivider.SplitScreenTaskOrganizer r6 = r5.mSplits
            com.android.systemui.stackdivider.Divider r6 = r6.mDivider
            com.android.systemui.stackdivider.WindowManagerProxy r6 = r6.getWmProxy()
            boolean r6 = r6.queueSyncTransactionIfWaiting(r0)
            if (r6 != 0) goto Lc7
            android.window.WindowOrganizer.applyTransaction(r0)
            goto Lc7
        Lab:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "setHomeMinimized applySyncTransaction wct : "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            com.bydux.api.common.tools.log.BydLogHelper.logD(r6)
            com.android.systemui.stackdivider.WindowManagerProxy r6 = r5.mWindowManagerProxy
            r6.applySyncTransaction(r0)
        Lc7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.stackdivider.Divider.setHomeMinimized(boolean, boolean):void");
    }

    private void updateTouchable() {
        BydLogHelper.logD("updateTouchable");
        this.mWindowManager.setTouchable(true);
    }

    public void onUndockingTask(int direction) {
        BydLogHelper.logD("onUndockingTask direction : " + direction);
        SplitPreviewWindowManager.getInstance(this.mContext).executeHalfToFullAnimation(this.mDividerUtils.getPrimaryAppInfo().packageName, this.mDividerUtils.getSecondaryAppInfo().packageName, direction == 1);
        DividerView dividerView = this.mView;
        if (dividerView != null) {
            dividerView.onUndockingTask(direction);
            this.isDismissingSplit = true;
            BydLogHelper.logD("DIVIDER onUndockingTask");
        }
    }

    public void onAppTransitionFinished() {
        if (this.mView == null) {
            return;
        }
        BydLogHelper.logD("onAppTransitionFinished");
    }

    @Override // com.android.systemui.stackdivider.DividerView.DividerCallbacks
    public void onDraggingStart() {
        BydLogHelper.logD("onDraggingStart");
    }

    @Override // com.android.systemui.stackdivider.DividerView.DividerCallbacks
    public void onDraggingEnd() {
        BydLogHelper.logD("onDraggingEnd");
    }

    @Override // com.android.systemui.SystemUI
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        pw.print("  mVisible=");
        pw.println(this.mVisible);
        pw.print("  mMinimized=");
        pw.println(this.mMinimized);
        pw.print("  mAdjustedForIme=");
        pw.println(false);
    }

    public void registerInSplitScreenListener(Consumer<Boolean> listener) {
        listener.accept(Boolean.valueOf(isDividerVisible()));
        synchronized (this.mDockedStackExistsListeners) {
            this.mDockedStackExistsListeners.add(new WeakReference<>(listener));
        }
    }

    void startEnterSplit() {
        setPrimaryAppInfo();
        setSecondaryAppInfo();
        this.isDismissingSplit = false;
        BydLogHelper.logD("startEnterSplit");
        sendSplitScreenStatusBroadcast(true);
        update(this.mDisplayController.getDisplayContext(this.mContext.getDisplayId()).getResources().getConfiguration());
        this.mHomeStackResizable = this.mWindowManagerProxy.applyEnterSplit(this.mSplits, this.mSplitLayout);
        notifyDockedStackExistsChanged(true);
        this.mRestartSplitScreenViewManager.hideView();
    }

    public void startDismissSplit() {
        this.mIsResized = true;
        this.isDismissingSplit = true;
        this.mWindowManagerProxy.lambda$dismissOrMaximizeDocked$0$WindowManagerProxy(this.mSplits, this.mSplitLayout, true);
        updateVisibility(false);
        this.mMinimized = false;
        removeDivider();
        BydLogHelper.logD("DIVIDER startDismissSplit");
        sendSplitScreenStatusBroadcast(false);
        notifyDockedStackExistsChanged(false);
        this.mDividerState.mRatioPositionBeforeMinimized = 0.0f;
    }

    public void dismissDockedStack() {
        this.mView.dismissDockedStack();
        BydLogHelper.logD("DIVIDER dismissDockedStack");
    }

    void ensureMinimizedSplit() {
        BydLogHelper.logD("ensureMinimizedSplit");
        BydLogHelper.logD("ensureMinimizedSplit mHomeStackResizable : " + this.mHomeStackResizable);
        BydLogHelper.logD("ensureMinimizedSplit isDividerVisible : " + isDividerVisible());
        if (this.mView == null || isDividerVisible()) {
            return;
        }
        BydLogHelper.logD(" entering split mode with minimized=true");
        updateVisibility(true);
    }

    void ensureNormalSplit() {
        BydLogHelper.logD("ensureNormalSplit");
        if (this.mView != null && !isDividerVisible()) {
            BydLogHelper.logD(" enter split mode unminimized ");
            updateVisibility(true);
        }
        notifyDockedStackExistsChanged(true);
        if (this.mDividerUtils.getStackInfo(true) != null) {
            BydLogHelper.logD("Primary Bounds " + this.mDividerUtils.getStackInfo(true).bounds);
        }
        if (this.mDividerUtils.getStackInfo(false) != null) {
            BydLogHelper.logD("Second Bounds " + this.mDividerUtils.getStackInfo(false).bounds);
        }
    }

    WindowManagerProxy getWmProxy() {
        return this.mWindowManagerProxy;
    }

    public void sendSplitScreenStatusBroadcast(boolean value) {
        BydLogHelper.logD("sendSplitScreenStatusBroadcast value : " + value);
        Intent intent = new Intent("SplitScreenStatus.BroadcastReceiver");
        intent.putExtra(Constants.SPLIT_SCREEN_STATUS_BROADCAST_EXTRA, value);
        this.mContext.sendBroadcast(intent);
    }

    public void setNewFocusStatus() {
        BydLogHelper.logD("setNewFocusStatus");
        this.mHandler.removeMessages(5);
        this.mHandler.sendEmptyMessage(5);
        this.mHandler.removeMessages(2);
        this.mHandler.sendEmptyMessage(2);
        this.mHandler.removeMessages(3);
        this.mHandler.sendEmptyMessage(3);
    }

    public void removeSwapView() {
        SwapScreenViewManager swapScreenViewManager = this.mSwapScreenViewManager;
        if (swapScreenViewManager != null) {
            swapScreenViewManager.removeSwapView();
        }
    }

    public void createSwapView(Bitmap primaryLayerTwoBitmap, Bitmap secondLayerTwoBitmap) {
        if (this.mSwapScreenViewManager != null) {
            BydLogHelper.logD("createSwapView");
            this.mSwapScreenViewManager.createSwapView(this.mContext, primaryLayerTwoBitmap, secondLayerTwoBitmap);
        }
    }

    public boolean isSwapping() {
        return this.mSwapScreenViewManager.isSwapping();
    }

    public boolean isApplyDismissSplitStatus() {
        return this.mIsApplyDismissSplitStatus;
    }

    public void setApplyDismissSplitStatus(boolean status) {
        this.mIsApplyDismissSplitStatus = status;
    }

    private void notifyDockedStackExistsChanged(boolean exists) {
        BydLogHelper.logD("notifyDockedStackExistsChanged exists =" + exists);
        if (!exists) {
            this.mIsApplyDismissSplitStatus = true;
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, 500L);
        }
        if (exists) {
            this.mHandler.removeMessages(2);
            this.mHandler.sendEmptyMessageDelayed(2, 500L);
            this.mHandler.removeMessages(5);
            this.mHandler.sendEmptyMessageDelayed(5, 500L);
        }
        Message message = new Message();
        message.what = 6;
        message.obj = Boolean.valueOf(exists);
        this.mHandler.removeMessages(6);
        this.mHandler.sendMessage(message);
        Message message2 = new Message();
        message2.what = 7;
        message2.obj = Boolean.valueOf(exists);
        this.mHandler.removeMessages(7);
        this.mHandler.sendMessage(message2);
    }

    private static class DividerHandler extends Handler {
        public static final int MSG_DOCKED_STACK_EXISTS_CHANGED = 1;
        public static final int MSG_DO_KEY_CALL = 8;
        public static final int MSG_EXIT_AVM_RESTART_SPLIT = 9;
        public static final int MSG_ROTATION_CHANGED = 4;
        public static final int MSG_UPDATE_EXIT_ORIENTATION = 3;
        public static final int MSG_UPDATE_FOCUS_ORIENTATION = 5;
        public static final int MSG_UPDATE_NAVI_BAR_BG = 6;
        public static final int MSG_UPDATE_SPLIT_BTN_SHOW_HIDE = 2;
        public static final int MSG_UPDATE_STATUS_BAR_BG = 7;
        WeakReference<Divider> mDivider;

        public DividerHandler(Divider divider) {
            this.mDivider = new WeakReference<>(divider);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.mDivider.get() == null) {
            }
            BydLogHelper.logD("handleMessage msg.what =" + msg.what);
            switch (msg.what) {
                case 1:
                    this.mDivider.get().setApplyDismissSplitStatus(false);
                    this.mDivider.get().showRestartSplitScreenView();
                    this.mDivider.get().sendBroadcastToCard("com.byd.action.card.more.app");
                    break;
                case 2:
                    this.mDivider.get().updateSplitBtnShowHide();
                    break;
                case 3:
                    this.mDivider.get().updateExitOrientation();
                    break;
                case 4:
                    this.mDivider.get().updateSplitBtnShowHide();
                    this.mDivider.get().updateExitOrientation();
                    break;
                case 5:
                    boolean zIsSplitActive = this.mDivider.get().isSplitActive();
                    BydLogHelper.logD("handleMessage MSG_UPDATE_FOCUS_ORIENTATION isSplitActive = " + zIsSplitActive);
                    if (zIsSplitActive) {
                        this.mDivider.get().mIndexWindowManager.setNewFocusStatus();
                    }
                    break;
                case 6:
                    this.mDivider.get().updateNaviBarBg(((Boolean) msg.obj).booleanValue());
                    break;
                case 7:
                    this.mDivider.get().updateStatusBarBg(((Boolean) msg.obj).booleanValue());
                    break;
                case 8:
                    BydLogHelper.logD("handleMessage MSG_DO_KEY_CALL");
                    this.mDivider.get().doKeyCall();
                    break;
                case 9:
                    Bundle data = msg.getData();
                    String string = data.getString("packageName");
                    String string2 = data.getString(Constants.PackageInfo.ACTIVITY);
                    BydLogHelper.logD("handleMessage MSG_EXIT_AVM_RESTART_SPLIT packageName : " + string + " , activity : " + string2);
                    this.mDivider.get().restartSp(string, string2);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doKeyCall() {
        final ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.getInstance().getRunningTask();
        if (runningTask != null) {
            String packageName = runningTask.baseActivity.getPackageName();
            String className = runningTask.baseActivity.getClassName();
            boolean z = runningTask.getConfiguration().windowConfiguration.getWindowingMode() == 3 || runningTask.getConfiguration().windowConfiguration.getWindowingMode() == 4;
            BydLogHelper.logD("onKeyClick keyValue 12 PackageName = " + packageName + "\nclassName = " + className + "\nrunningTask = " + runningTask + "\nrunningTask.taskId = " + runningTask.taskId + "\nrunningTask.getWindowingMode = " + runningTask.getConfiguration().windowConfiguration.getWindowingMode() + "\nisSplitActive = " + z);
            if ((packageName.equals("com.neusoft.na.navigation") || packageName.equals(Constant.PackageName.AUTO_MAP_PKG) || packageName.equals("com.huawei.maps.auto.app") || packageName.equals(Constant.PackageName.ZNAV_MAP_PKG) || packageName.equals(Constant.PackageName.T_MAP_PKG)) && !z) {
                this.mDividerUtils.setStartSplitType(4);
                ActivityOptionsCompat.makeSplitScreenOptions(true);
                SplitPreviewWindowManager.getInstance(this.mContext).executeFullToHalfAnimation(packageName, "com.byd.bluetoothcall");
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$Divider$MKPIYPH22ryIZWXKfk2cyB2pQdY
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$doKeyCall$6$Divider(runningTask);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$doKeyCall$6$Divider(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (ActivityManagerWrapper.getInstance().setTaskWindowingModeSplitScreenPrimary(runningTaskInfo.taskId)) {
            this.mDividerUtils.startActivity(this.mContext, "com.byd.bluetoothcall", "com.byd.bluetoothcall.view.activity.MainActivity", false);
            BydLogHelper.logD("onKeyClick keyValue 12 start bluetoothcall");
            SplitScreenHelpManager.getInstance(this.mContext).hideSplitNavView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restartSp(String packageName, String activity) {
        this.mDividerUtils.startActivity(this.mContext, packageName, activity, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSplitBtnShowHide() {
        if (this.mView != null) {
            BydLogHelper.logD("updateSplitBtnShowHide mView getVisibility = " + this.mView.getVisibility());
            if (this.mView.getVisibility() == 0) {
                this.mView.updateSplitBtnShowHide();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateExitOrientation() {
        if (this.mView != null) {
            BydLogHelper.logD("updateExitOrientation mView getVisibility = " + this.mView.getVisibility());
            if (this.mView.getVisibility() == 0) {
                this.mView.updateExitOrientation();
            }
        }
    }

    public void doHomeClick() {
        boolean zIsSplitActive = isSplitActive();
        BydLogHelper.logD("doHomeClick isSplitActive = " + zIsSplitActive + " , isDividerViewShow : " + isDividerViewShow());
        if (zIsSplitActive || isDividerViewShow()) {
            this.mDividerUtils.startOnePixelActivity(this.mContext);
        }
    }

    public boolean isDividerHandleViewMoving() {
        if (isSplitActive()) {
            return this.mView.isDividerHandleViewMoving();
        }
        return false;
    }

    public void setIndexWindowManagerVisibility(boolean visible) {
        boolean zIsSplitActive = isSplitActive();
        BydLogHelper.logD("setIndexWindowManagerVisibility isSplitActive = " + zIsSplitActive + " , visible : " + visible);
        if (!visible) {
            this.mIndexWindowManager.updateVisibility(false);
        } else if (zIsSplitActive) {
            this.mIndexWindowManager.updateVisibility(true);
        }
    }

    public void setGestureSlideExitOrientation(int gestureSlideExitOrientation) {
        this.mDividerUtils.setGestureSlideExitOrientation(gestureSlideExitOrientation);
    }

    public void showRestartSplitScreenView() {
        int[] windowWidthAndHeight = Util.getWindowWidthAndHeight(this.mContext);
        int navigationBarHeight = Util.getNavigationBarHeight(this.mContext);
        int statusBarHeight = Util.getStatusBarHeight(this.mContext);
        int i = windowWidthAndHeight[1];
        int i2 = windowWidthAndHeight[0];
        BydLogHelper.logD("showRestartSplitScreenView screenHeight : " + i + " , screenWidth : " + i2);
        BydLogHelper.logD("showRestartSplitScreenView navigationBarHeight : " + navigationBarHeight + " , statusBarHeight : " + statusBarHeight);
        int gestureSlideExitOrientation = this.mDividerUtils.getGestureSlideExitOrientation();
        if (gestureSlideExitOrientation == 0) {
            return;
        }
        boolean zIsHorizontalDivision = this.mDividerUtils.isHorizontalDivision();
        BydLogHelper.logD("showRestartSplitScreenView orientation = " + gestureSlideExitOrientation + " , isHorizontalDivision = " + zIsHorizontalDivision);
        if (zIsHorizontalDivision) {
            if (gestureSlideExitOrientation == 3) {
                gestureSlideExitOrientation = 1;
            } else if (gestureSlideExitOrientation == 4) {
                gestureSlideExitOrientation = 2;
            }
            this.mDividerUtils.setGestureSlideExitOrientation(gestureSlideExitOrientation);
        } else {
            if (gestureSlideExitOrientation == 1) {
                gestureSlideExitOrientation = 3;
            } else if (gestureSlideExitOrientation == 2) {
                gestureSlideExitOrientation = 4;
            }
            this.mDividerUtils.setGestureSlideExitOrientation(gestureSlideExitOrientation);
        }
        BydLogHelper.logD("showRestartSplitScreenView orientation = " + gestureSlideExitOrientation);
        if (gestureSlideExitOrientation == 1) {
            this.mRestartSplitScreenViewManager.setLocation(0, 0, GravityCompat.START);
            this.mRestartSplitScreenViewManager.showView();
            Settings.System.putInt(this.mContext.getContentResolver(), "show_split_screen_bar_orientation", 1);
            return;
        }
        if (gestureSlideExitOrientation == 2) {
            int dimension = i2 - ((int) this.mContext.getResources().getDimension(R.dimen.dp_64));
            BydLogHelper.logD("showRestartSplitScreenView x : " + dimension);
            this.mRestartSplitScreenViewManager.setLocation(dimension, 0, GravityCompat.START);
            this.mRestartSplitScreenViewManager.showView();
            Settings.System.putInt(this.mContext.getContentResolver(), "show_split_screen_bar_orientation", 2);
            return;
        }
        if (gestureSlideExitOrientation == 3) {
            this.mRestartSplitScreenViewManager.setLocation(0, 0, 48);
            this.mRestartSplitScreenViewManager.showView();
        } else if (gestureSlideExitOrientation == 4) {
            this.mRestartSplitScreenViewManager.setLocation(0, ((i - navigationBarHeight) - statusBarHeight) - ((int) this.mContext.getResources().getDimension(R.dimen.dp_64)), 48);
            this.mRestartSplitScreenViewManager.showView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBroadcastToCard(String action) {
        if (this.mDividerUtils.isMoreAppExitSplitScreen()) {
            BydLogHelper.logD("broadcast:" + action);
            Intent intent = new Intent();
            intent.setAction(action);
            this.mContext.sendBroadcast(intent);
            this.mDividerUtils.setMoreAppExitSplitScreen(false);
        }
    }

    public void updateNaviBarBg(boolean value) {
        ((StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class)).updateNaviBarBg(value);
    }

    public void updateStatusBarBg(boolean value) {
        ((StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class)).updateStatusBarBgOnSplit(value);
    }

    public void updateStatusBarCPAA(boolean isShow) {
        BydLogHelper.logD("updateStatusBarCPAA isShow " + isShow);
        ((StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class)).updateStatusBarCPAA(isShow);
        updateNaviBarBg(isSplitActive());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPrimaryAppInfo() {
        boolean zIsSplitActive = isSplitActive();
        BydLogHelper.logD("setPrimaryAppInfo isSplitActive : " + zIsSplitActive);
        if (zIsSplitActive) {
            ActivityManager.StackInfo stackInfo = this.mDividerUtils.getStackInfo(true);
            BydLogHelper.logD("setPrimaryAppInfo primaryStackInfo : " + stackInfo);
            if (stackInfo != null && stackInfo.taskIds.length > 0) {
                String[] strArr = new String[1];
                int iExcludeForbiddenActivity = DividerUtils.excludeForbiddenActivity(stackInfo, strArr);
                String str = strArr[0];
                BydLogHelper.logD("setPrimaryAppInfo primaryTaskName = " + str);
                if (TextUtils.isEmpty(str) || str.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                    this.mDividerUtils.setPrimaryAppInfo(null);
                    return;
                }
                String[] strArrSplit = str.split("/");
                if (strArrSplit.length > 1) {
                    BydLogHelper.logD("setPrimaryAppInfo primary package name = " + strArrSplit[0] + "\nprimary activity name = " + strArrSplit[1] + "\nprimary TaskId : " + iExcludeForbiddenActivity);
                    AppInfo appInfo = new AppInfo();
                    appInfo.setTaskId(iExcludeForbiddenActivity);
                    appInfo.setPackageName(strArrSplit[0]);
                    appInfo.setActivity(strArrSplit[1]);
                    this.mDividerUtils.setPrimaryAppInfo(appInfo);
                    ForbiddenManager.getInstance().checkSplitWhenStackChange(stackInfo.topActivity, appInfo, true);
                    return;
                }
            }
            this.mDividerUtils.setPrimaryAppInfo(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSecondaryAppInfo() {
        boolean zIsSplitActive = isSplitActive();
        BydLogHelper.logD("setSecondaryAppInfo isSplitActive : " + zIsSplitActive);
        if (zIsSplitActive) {
            ActivityManager.StackInfo stackInfo = this.mDividerUtils.getStackInfo(false);
            BydLogHelper.logD("setSecondaryAppInfo secondaryStackInfo : " + stackInfo);
            if (stackInfo != null && stackInfo.taskIds.length > 0) {
                String[] strArr = new String[1];
                int iExcludeForbiddenActivity = DividerUtils.excludeForbiddenActivity(stackInfo, strArr);
                String str = strArr[0];
                BydLogHelper.logD("setSecondaryAppInfo secondaryTaskName = " + str);
                if (TextUtils.isEmpty(str) || str.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                    this.mDividerUtils.setSecondaryAppInfo(null);
                    return;
                }
                String[] strArrSplit = str.split("/");
                if (strArrSplit.length > 1) {
                    BydLogHelper.logD("setSecondaryAppInfo secondary package name = " + strArrSplit[0] + "\nsecondary activity name = " + strArrSplit[1] + "\nsecondary TaskId : " + iExcludeForbiddenActivity);
                    AppInfo appInfo = new AppInfo();
                    appInfo.setTaskId(iExcludeForbiddenActivity);
                    appInfo.setPackageName(strArrSplit[0]);
                    appInfo.setActivity(strArrSplit[1]);
                    if (strArrSplit[0].equals("com.byd.avm")) {
                        DividerUtils dividerUtils = this.mDividerUtils;
                        dividerUtils.setSecondaryAppInfoBeforeAvm(dividerUtils.getSecondaryAppInfo());
                        return;
                    } else {
                        this.mDividerUtils.setSecondaryAppInfo(appInfo);
                        ForbiddenManager.getInstance().checkSplitWhenStackChange(stackInfo.topActivity, appInfo, false);
                        return;
                    }
                }
            }
            this.mDividerUtils.setSecondaryAppInfo(null);
        }
    }
}
