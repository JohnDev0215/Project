package com.android.systemui.stackdivider;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.GestureManager;
import android.view.MotionEvent;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.stackdivider.view.preview.SplitPreviewWindowManager;
import com.android.systemui.util.GuideUtils;
import com.android.systemui.util.ToastUtil;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.Constant;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SplitScreenController {
    private static final int POINTER_COUNT = 2;
    private static final int SLIDE_DISTANCE = 300;
    private final Context mContext;
    private final Divider mDivider;
    private final DividerUtils mDividerUtils;
    private final GestureManager.GestureChangeListener mGestureChangeListener;
    private String mNaviPackage;
    private final SwapSplitScreen mSwapSplitScreen;
    private float firstPointerX = 0.0f;
    private float firstPointerY = 0.0f;
    private float secondPointerX = 0.0f;
    private float secondPointerY = 0.0f;
    private boolean isDoDoublePointerSlideEvent = false;
    private boolean isDoublePointerSlide = false;

    public SplitScreenController(Context context) {
        this.mNaviPackage = "com.neusoft.na.navigation";
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.mGestureChangeListener = anonymousClass1;
        this.mContext = context;
        this.mDivider = (Divider) SysUiServiceProvider.getComponent(context, Divider.class);
        this.mDividerUtils = DividerUtils.getInstance();
        this.mSwapSplitScreen = SwapSplitScreen.getInstance();
        if (AppUtils.isInstalled(SystemUIApplication.getContext(), "com.huawei.maps.auto.app")) {
            this.mNaviPackage = "com.huawei.maps.auto.app";
        } else if (AppUtils.isInstalled(SystemUIApplication.getContext(), Constant.PackageName.AUTO_MAP_PKG)) {
            this.mNaviPackage = Constant.PackageName.AUTO_MAP_PKG;
        } else if (AppUtils.isInstalled(SystemUIApplication.getContext(), Constant.PackageName.ZNAV_MAP_PKG)) {
            this.mNaviPackage = Constant.PackageName.ZNAV_MAP_PKG;
        } else if (AppUtils.isInstalled(SystemUIApplication.getContext(), Constant.PackageName.T_MAP_PKG)) {
            this.mNaviPackage = Constant.PackageName.T_MAP_PKG;
        }
        try {
            GestureManager gestureManager = (GestureManager) context.getSystemService("gesture");
            if (gestureManager != null) {
                BydLogHelper.logD("GestureManager init");
                gestureManager.subscribeGestureChangeListener(anonymousClass1);
            }
        } catch (Exception e) {
            BydLogHelper.logE("init", e.toString());
            e.printStackTrace();
        }
    }

    private void postSplitOnHomeOther(final int taskId, final ActivityOptions options) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$SplitScreenController$wLKiF7zzRDquMw5IsESN3R_pQPM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$postSplitOnHomeOther$0$SplitScreenController(taskId, options);
            }
        });
    }

    public /* synthetic */ void lambda$postSplitOnHomeOther$0$SplitScreenController(int i, ActivityOptions activityOptions) {
        try {
            if (ActivityManagerWrapper.getInstance().startActivityFromRecents(i, activityOptions)) {
                updateStatusBarOrNaviBar(true);
                this.mDividerUtils.setSplitScreenScene(0);
                this.mDividerUtils.setStartSplitType(0);
                this.mDividerUtils.startSplitScreenListActivity(this.mContext, false);
            } else {
                updateStatusBarOrNaviBar(false);
            }
        } catch (IllegalArgumentException e) {
            updateStatusBarOrNaviBar(false);
            e.printStackTrace();
        }
    }

    private void postSplitOnHome(final int taskId, final int scene, final String packageName, final String className) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$SplitScreenController$o-ljNms8dDYVpCbxZdXW1oycSyA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$postSplitOnHome$1$SplitScreenController(taskId, scene, packageName, className);
            }
        });
    }

    public /* synthetic */ void lambda$postSplitOnHome$1$SplitScreenController(int i, int i2, String str, String str2) {
        try {
            if (ActivityManagerWrapper.getInstance().setTaskWindowingModeSplitScreenPrimary(i)) {
                updateStatusBarOrNaviBar(true);
                this.mDividerUtils.setSplitScreenScene(i2);
                this.mDividerUtils.setStartSplitType(0);
                if (TextUtils.isEmpty(str)) {
                    this.mDividerUtils.startSplitScreenListActivity(this.mContext, false);
                } else {
                    this.mDividerUtils.startActivity(this.mContext, str, str2, false);
                }
                BydLogHelper.logD("postSplitOnHome scene = " + i2);
                return;
            }
            updateStatusBarOrNaviBar(false);
            BydLogHelper.logD("postSplitOnHome Home start fail. scene = " + i2);
        } catch (IllegalArgumentException e) {
            updateStatusBarOrNaviBar(false);
            e.printStackTrace();
        }
    }

    private void postSplitOnTopActivity(final int taskId, final int typeCase, final String packageName, final String className) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$SplitScreenController$4KLxYjsAqcvNUnuIpWTYc5RvKzQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$postSplitOnTopActivity$2$SplitScreenController(taskId, typeCase, packageName, className);
            }
        });
    }

    public /* synthetic */ void lambda$postSplitOnTopActivity$2$SplitScreenController(int i, int i2, String str, String str2) {
        try {
            if (ActivityManagerWrapper.getInstance().setTaskWindowingModeSplitScreenPrimary(i)) {
                updateStatusBarOrNaviBar(true);
                this.mDividerUtils.setStartSplitType(1);
                if (i2 == 2) {
                    this.mDividerUtils.startActivity(this.mContext, str, str2, false);
                    this.mDividerUtils.setSplitScreenScene(2);
                    BydLogHelper.logD("splitPrimaryTask case 2");
                } else if (i2 == 1) {
                    this.mDividerUtils.startActivity(this.mContext, str, str2, false);
                    this.mDividerUtils.setSplitScreenScene(1);
                    BydLogHelper.logD("splitPrimaryTask case 1");
                } else {
                    this.mDividerUtils.startSplitScreenListActivity(this.mContext, false);
                }
            } else {
                updateStatusBarOrNaviBar(false);
                BydLogHelper.logD("setTaskWindowingModeSplitScreenPrimary fail");
            }
        } catch (IllegalArgumentException e) {
            updateStatusBarOrNaviBar(false);
            e.printStackTrace();
        }
    }

    public void splitPrimaryTask() {
        if (this.mDivider.isApplyDismissSplitStatus()) {
            BydLogHelper.logD("splitPrimaryTask isApplyDismissSplitStatus return");
            return;
        }
        final ActivityManager.RunningTaskInfo runningTaskExceptForbidden = ActivityManagerWrapper.getInstance().getRunningTaskExceptForbidden(null);
        int activityType = runningTaskExceptForbidden != null ? runningTaskExceptForbidden.configuration.windowConfiguration.getActivityType() : 0;
        if (runningTaskExceptForbidden != null) {
            BydLogHelper.logD("splitPrimaryTask runningTask getActivityType = " + runningTaskExceptForbidden.configuration.windowConfiguration.getActivityType());
        }
        boolean zIsScreenPinningActive = ActivityManagerWrapper.getInstance().isScreenPinningActive();
        boolean z = activityType == 2 || activityType == 3;
        BydLogHelper.logD("splitPrimaryTask isRunningTaskInHomeOrRecentsStack = " + z + " , activityType : " + activityType);
        BydLogHelper.logD("splitPrimaryTask runningTask = " + runningTaskExceptForbidden);
        BydLogHelper.logD("splitPrimaryTask screenPinningActive = " + zIsScreenPinningActive);
        if (runningTaskExceptForbidden == null || z || zIsScreenPinningActive) {
            if (activityType == 2) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$SplitScreenController$mvvPImw-m8eLROYxGIL4m7Pik7M
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$splitPrimaryTask$4$SplitScreenController();
                    }
                });
                return;
            }
            return;
        }
        final String packageName = runningTaskExceptForbidden.baseActivity.getPackageName();
        BydLogHelper.logD("splitPrimaryTask PackageName = " + packageName + " , className = " + runningTaskExceptForbidden.baseActivity.getClassName());
        boolean z2 = runningTaskExceptForbidden.supportsSplitScreenMultiWindow;
        BydLogHelper.logD("splitPrimaryTask supportsSplitScreenMultiWindow = " + z2);
        if (z2) {
            BydLogHelper.logD("splitPrimaryTask runningTask.taskId = " + runningTaskExceptForbidden.taskId);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$SplitScreenController$ekltb3wl8s7x_gDHhseTLN7_QoU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$splitPrimaryTask$3$SplitScreenController(packageName, runningTaskExceptForbidden);
                }
            });
        } else {
            ToastUtil.show(this.mContext, R.string.dock_non_resizeble_failed_to_dock_text, 0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ void lambda$splitPrimaryTask$3$SplitScreenController(java.lang.String r9, android.app.ActivityManager.RunningTaskInfo r10) {
        /*
            Method dump skipped, instruction units count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.stackdivider.SplitScreenController.lambda$splitPrimaryTask$3$SplitScreenController(java.lang.String, android.app.ActivityManager$RunningTaskInfo):void");
    }

    public /* synthetic */ void lambda$splitPrimaryTask$4$SplitScreenController() {
        String launcherPackageName = this.mDividerUtils.getLauncherPackageName(this.mContext);
        BydLogHelper.logD("splitPrimaryTask launcherPackageName = " + launcherPackageName);
        if (launcherPackageName.equals(this.mNaviPackage)) {
            AppInfo backgroundApp = this.mDividerUtils.getBackgroundApp(this.mNaviPackage);
            if (backgroundApp != null) {
                BydLogHelper.logD("splitPrimaryTask mapTaskInfo = " + backgroundApp);
                try {
                    SplitPreviewWindowManager.getInstance(this.mContext).executeFullToHalfAnimation(backgroundApp.getPackageName(), "");
                    postSplitOnHome(backgroundApp.getTaskId(), 0, null, null);
                    return;
                } catch (IllegalArgumentException e) {
                    updateStatusBarOrNaviBar(false);
                    e.printStackTrace();
                    return;
                }
            }
            BydLogHelper.logD("splitPrimaryTask mapTaskInfo == null");
            updateStatusBarOrNaviBar(false);
            return;
        }
        List<ActivityManager.RecentTaskInfo> recentsTasksList = this.mDividerUtils.getRecentsTasksList();
        BydLogHelper.logD("splitPrimaryTask taskList = " + recentsTasksList);
        if (!recentsTasksList.isEmpty()) {
            AppInfo backgroundApp2 = this.mDividerUtils.getBackgroundApp(this.mNaviPackage);
            AppInfo backgroundApp3 = this.mDividerUtils.getBackgroundApp("com.byd.localmusic");
            BydLogHelper.logD("splitPrimaryTask mapTaskInfo = " + backgroundApp2 + " , NaviStatus : " + this.mDividerUtils.getNaviStatus());
            BydLogHelper.logD("splitPrimaryTask musicTaskInfo = " + backgroundApp3 + " , MusicPlayState : " + this.mDividerUtils.getMusicPlayState());
            if (backgroundApp2 != null && this.mDividerUtils.getNaviStatus() == 1 && backgroundApp3 != null && this.mDividerUtils.getMusicPlayState() == 1) {
                try {
                    SplitPreviewWindowManager.getInstance(this.mContext).executeFullToHalfAnimation(backgroundApp2.getPackageName(), "com.byd.localmusic");
                    postSplitOnHome(backgroundApp2.getTaskId(), 3, backgroundApp3.getPackageName(), backgroundApp3.getActivity());
                    return;
                } catch (IllegalArgumentException e2) {
                    updateStatusBarOrNaviBar(false);
                    e2.printStackTrace();
                    return;
                }
            }
            if (this.mDividerUtils.getNaviStatus() != 1 && backgroundApp3 != null && this.mDividerUtils.getMusicPlayState() == 1) {
                try {
                    SplitPreviewWindowManager.getInstance(this.mContext).executeFullToHalfAnimation(backgroundApp3.getPackageName(), "");
                    postSplitOnHome(backgroundApp3.getTaskId(), 4, null, null);
                    return;
                } catch (IllegalArgumentException e3) {
                    updateStatusBarOrNaviBar(false);
                    e3.printStackTrace();
                    return;
                }
            }
            if (backgroundApp2 != null && this.mDividerUtils.getNaviStatus() == 1 && (backgroundApp3 == null || this.mDividerUtils.getMusicPlayState() != 1)) {
                try {
                    SplitPreviewWindowManager.getInstance(this.mContext).executeFullToHalfAnimation(backgroundApp2.getPackageName(), "");
                    postSplitOnHome(backgroundApp2.getTaskId(), 5, null, null);
                    return;
                } catch (IllegalArgumentException e4) {
                    updateStatusBarOrNaviBar(false);
                    e4.printStackTrace();
                    return;
                }
            }
            ActivityOptions activityOptions = this.mDividerUtils.getActivityOptions(true);
            boolean zContainsSupportSplitApp = this.mDividerUtils.containsSupportSplitApp(recentsTasksList);
            BydLogHelper.logD("splitPrimaryTask isBothAppList = " + zContainsSupportSplitApp);
            if (zContainsSupportSplitApp) {
                AppInfo splitTask = this.mDividerUtils.getSplitTask(recentsTasksList);
                BydLogHelper.logD("splitPrimaryTask splitTaskInfo = " + splitTask);
                if (splitTask != null && splitTask.getTaskId() != -1) {
                    try {
                        SplitPreviewWindowManager.getInstance(this.mContext).executeFullToHalfAnimation(splitTask.getPackageName(), "");
                        postSplitOnHomeOther(splitTask.getTaskId(), activityOptions);
                        return;
                    } catch (Exception e5) {
                        updateStatusBarOrNaviBar(false);
                        e5.printStackTrace();
                        return;
                    }
                }
            }
        } else {
            BydLogHelper.logD("splitPrimaryTask recentTasksList == null");
        }
        SplitPreviewWindowManager.getInstance(this.mContext).executeFullToHalfAnimation("", "");
        updateStatusBarOrNaviBar(true);
        this.mDividerUtils.setSplitScreenScene(0);
        this.mDividerUtils.setStartSplitType(0);
        this.mDividerUtils.startSplitScreenListActivity(this.mContext, true);
        this.mDividerUtils.startSplitScreenListActivity(this.mContext, false);
    }

    private void updateStatusBarOrNaviBar(boolean b) {
        this.mDivider.updateNaviBarBg(b);
        this.mDivider.updateStatusBarBg(b);
    }

    /* JADX INFO: renamed from: com.android.systemui.stackdivider.SplitScreenController$1, reason: invalid class name */
    class AnonymousClass1 implements GestureManager.GestureChangeListener {
        AnonymousClass1() {
        }

        public void onGestureChanged(final MotionEvent motionEvent) {
            if (GuideUtils.isProvisionFinished(SplitScreenController.this.mContext) && GuideUtils.isSetupFinished(SplitScreenController.this.mContext)) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$SplitScreenController$1$tDYBQATZ15AaqdeS6ncsBiYtmeg
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onGestureChanged$0$SplitScreenController$1(motionEvent);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onGestureChanged$0$SplitScreenController$1(MotionEvent motionEvent) {
            SplitScreenController.this.onTouchEvent(motionEvent);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int actionIndex = event.getActionIndex();
        int pointerCount = event.getPointerCount();
        int actionMasked = event.getActionMasked();
        BydLogHelper.logD("onTouchEvent action:" + actionMasked + " , pointerCount:" + pointerCount + " , actionIndex:" + actionIndex);
        if (actionMasked == 1) {
            this.isDoDoublePointerSlideEvent = false;
            this.isDoublePointerSlide = false;
        } else if (actionMasked != 2) {
            if (actionMasked == 5) {
                if (pointerCount == 2) {
                    this.firstPointerX = event.getX(0);
                    this.firstPointerY = event.getY(0);
                    this.secondPointerX = event.getX(1);
                    this.secondPointerY = event.getY(1);
                    this.isDoDoublePointerSlideEvent = false;
                    this.isDoublePointerSlide = true;
                } else {
                    this.isDoublePointerSlide = false;
                }
            }
        } else if (pointerCount == 2) {
            handlerMove(event);
        }
        return true;
    }

    public void handlerMove(MotionEvent event) {
        boolean z = false;
        float x = event.getX(0);
        float y = event.getY(0);
        float f = x - this.firstPointerX;
        float f2 = y - this.firstPointerY;
        float x2 = event.getX(1);
        float y2 = event.getY(1);
        float f3 = x2 - this.secondPointerX;
        float f4 = y2 - this.secondPointerY;
        boolean zIsHorizontalDivision = this.mDividerUtils.isHorizontalDivision();
        BydLogHelper.logD("handlerMove isHorizontalDivision : " + zIsHorizontalDivision);
        BydLogHelper.logD("handlerMove moveFirstX : " + f + " , moveSecondX : " + f3 + " , moveFirstY : " + f2 + " , moveSecondY : " + f4);
        if (f > 300.0f && f3 > 300.0f && zIsHorizontalDivision) {
            BydLogHelper.logD("右滑");
        } else if (f < 0.0f && f3 < 0.0f && Math.abs(f) > 300.0f && Math.abs(f3) > 300.0f && zIsHorizontalDivision) {
            BydLogHelper.logD("左滑");
        } else if (f2 > 300.0f && f4 > 300.0f && !zIsHorizontalDivision) {
            BydLogHelper.logD("下滑");
        } else {
            if (f2 < 0.0f && f4 < 0.0f && Math.abs(f2) > 300.0f && Math.abs(f4) > 300.0f && !zIsHorizontalDivision) {
                BydLogHelper.logD("上滑");
            }
            if (z || this.isDoDoublePointerSlideEvent || !this.isDoublePointerSlide) {
                return;
            }
            this.isDoDoublePointerSlideEvent = true;
            boolean zIsSwapping = this.mDivider.isSwapping();
            int dividerStatus = this.mDividerUtils.getDividerStatus();
            BydLogHelper.logD("isSwapping : " + zIsSwapping + " , isDividerHandleViewMoving : " + this.mDivider.isDividerHandleViewMoving() + " , dividerStatus : " + dividerStatus);
            if (!this.mDivider.isSplitActive() || zIsSwapping || this.mDivider.isDividerHandleViewMoving() || dividerStatus != 2) {
                return;
            }
            this.mSwapSplitScreen.executeSwapView(this.mContext, this.mDivider);
            return;
        }
        z = true;
        if (z) {
        }
    }

    public void setNavPackage(String pkg) {
        this.mNaviPackage = pkg;
    }
}
