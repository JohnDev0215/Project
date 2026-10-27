package com.android.systemui.stackdivider;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.launcher3.AppTransitionManagerImpl;
import com.android.systemui.Constants;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.recents.RecentTaskBean;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.ActivityOptionsCompat;
import com.android.systemui.stackdivider.view.OnePixelActivity;
import com.android.systemui.stackdivider.view.SplitScreenEvent;
import com.android.systemui.stackdivider.view.preview.SplitPreviewWindowManager;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.util.SystemUIException;
import com.byd.card.bean.AppInfo;
import com.byd.card.forbidden.BaseForbiddenActivity;
import com.byd.card.forbidden.ForbiddenSplitPrimaryActivity;
import com.byd.card.forbidden.ForbiddenSplitSecondaryActivity;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.BitmapUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class DividerUtils {
    public static final int APP_VIEW_START = 1;
    public static final int DIAL_KEY_START = 4;
    public static final int DIVIDER_TYPE_DOUBLE = 2;
    public static final int DIVIDER_TYPE_NO_APP = 3;
    public static final int DIVIDER_TYPE_SINGLE = 1;
    public static final int GESTURE_SLIDE_EXIT_ORIENTATION_DOWN = 4;
    public static final int GESTURE_SLIDE_EXIT_ORIENTATION_HIDE = 0;
    public static final int GESTURE_SLIDE_EXIT_ORIENTATION_LEFT = 1;
    public static final int GESTURE_SLIDE_EXIT_ORIENTATION_RIGHT = 2;
    public static final int GESTURE_SLIDE_EXIT_ORIENTATION_UP = 3;
    public static final int HOME_START = 0;
    public static final int RECENT_VIEW_START = 2;
    public static final int RECOVER_START = 3;
    public static final int SCENE_HOME_NAVI_MUSIC_PLAY = 3;
    public static final int SCENE_HOME_NAVI_NO_MUSIC_PLAY = 5;
    public static final int SCENE_HOME_NO_NAVI_MUSIC_PLAY = 4;
    public static final int SCENE_MAP_MUSIC_PLAY = 1;
    public static final int SCENE_MUSIC_NAVIGATION = 2;
    public static final int SCENE_OTHER = 0;
    private static final String TAG = "DividerUtils";
    private static DividerUtils instance;
    private boolean isMoreAppExitSplitScreen;
    private int mDividerStatus;
    private LoadAppAsyncTask mLoadAppAsyncTask;
    private int startSplitType = 0;
    private int mNaviStatus = 0;
    private int mMusicPlayState = 0;
    private int mSplitScreenScene = 0;
    private int mGestureSlideExitOrientation = 0;
    private boolean isLauncherSelecting = false;
    private boolean mPowerMode = false;
    private boolean mLauncherReplacedDesktop = false;
    private AppInfo mPrimaryAppInfo = null;
    private AppInfo mSecondaryAppInfo = null;
    private AppInfo mSecondaryAppInfoBeforeAvm = null;
    ArrayList<AppInfo> mSupportSplitScreenApps = new ArrayList<>();
    private final H mHandler = new H(this);

    public static synchronized DividerUtils getInstance() {
        if (instance == null) {
            instance = new DividerUtils();
        }
        return instance;
    }

    private DividerUtils() {
    }

    public void startLoadAppAsyncTask(Context context) {
        if (this.mLoadAppAsyncTask != null) {
            BydLogHelper.logD("startLoadAppAsyncTask mLoadIconAsyncTask is not null ");
            return;
        }
        LoadAppAsyncTask loadAppAsyncTask = new LoadAppAsyncTask(context);
        this.mLoadAppAsyncTask = loadAppAsyncTask;
        loadAppAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public boolean isLauncherReplacedDesktop() {
        return this.mLauncherReplacedDesktop;
    }

    public void setLauncherReplacedDesktop(boolean launcherReplacedDesktop, boolean needReset) {
        BydLogHelper.logD("setLauncherReplacedDesktop launcherReplacedDesktop : " + launcherReplacedDesktop + " , needReset : " + needReset);
        this.mLauncherReplacedDesktop = launcherReplacedDesktop;
        if (needReset) {
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, 500L);
        }
    }

    public void updateLauncherReplacedDesktop() {
        BydLogHelper.logD("updateLauncherReplacedDesktop mLauncherReplacedDesktop : " + this.mLauncherReplacedDesktop);
        if (this.mLauncherReplacedDesktop) {
            this.mLauncherReplacedDesktop = false;
        }
    }

    public void setPrimaryAppInfo(AppInfo primaryAppInfo) {
        this.mPrimaryAppInfo = primaryAppInfo;
    }

    public AppInfo getPrimaryAppInfo() {
        return this.mPrimaryAppInfo;
    }

    public void setSecondaryAppInfo(AppInfo secondaryAppInfo) {
        this.mSecondaryAppInfo = secondaryAppInfo;
    }

    public AppInfo getSecondaryAppInfo() {
        return this.mSecondaryAppInfo;
    }

    public AppInfo getSecondaryAppInfoBeforeAvm() {
        return this.mSecondaryAppInfoBeforeAvm;
    }

    public void setSecondaryAppInfoBeforeAvm(AppInfo mSecondaryAppInfoBeforeAvm) {
        this.mSecondaryAppInfoBeforeAvm = mSecondaryAppInfoBeforeAvm;
    }

    private final class LoadAppAsyncTask extends AsyncTask<Void, Void, Void> {
        Context mContext;

        LoadAppAsyncTask(Context context) {
            this.mContext = context;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voids) {
            BydLogHelper.logD("LoadAppAsyncTask doInBackground");
            DividerUtils dividerUtils = DividerUtils.this;
            dividerUtils.mSupportSplitScreenApps = dividerUtils.getSplitAppInfo(this.mContext);
            DividerUtils.this.notifyAppListChanged();
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            BydLogHelper.logD("LoadAppAsyncTask onPostExecute");
            DividerUtils.this.mLoadAppAsyncTask = null;
        }
    }

    public ArrayList<AppInfo> getSplitAppInfo(Context context) {
        BydLogHelper.logD("start getSplitAppInfo");
        ArrayList<AppInfo> arrayList = new ArrayList<>();
        List<AppInfo> appList = AppUtils.getAppList(context);
        for (int i = 0; i < appList.size(); i++) {
            AppInfo appInfo = appList.get(i);
            int i2 = appInfo.resizeMode;
            BydLogHelper.logD("getSplitAppInfo name : " + appInfo.name + "\npackageName : " + appInfo.packageName + "\nresizeMode : " + i2);
            if (i2 != 0 && i2 != 6 && i2 != 5 && !"com.byd.avm".equals(appInfo.packageName)) {
                Drawable drawableAdapterMultiTheme = BitmapUtils.adapterMultiTheme(appInfo.packageName, null);
                if (drawableAdapterMultiTheme != null) {
                    appInfo.icon = drawableAdapterMultiTheme;
                }
                arrayList.add(appInfo);
            }
        }
        BydLogHelper.logD("end getSplitAppInfo");
        this.mSupportSplitScreenApps.clear();
        this.mSupportSplitScreenApps = arrayList;
        return arrayList;
    }

    public ArrayList<AppInfo> getSupportSplitScreenApps() {
        return this.mSupportSplitScreenApps;
    }

    public boolean addSplitScreenAppInfo(AppInfo appInfo) {
        BydLogHelper.logD("addSplitScreenAppInfo appInfo : " + appInfo);
        int i = appInfo.resizeMode;
        BydLogHelper.logD("addSplitScreenAppInfo appInfo resizeMode : " + i);
        if (i == 0 || i == 6 || i == 5) {
            BydLogHelper.logD("addSplitScreenAppInfo appInfo not support split screen");
            return false;
        }
        if ("com.byd.avm".equals(appInfo.packageName)) {
            return false;
        }
        if (!this.mSupportSplitScreenApps.contains(appInfo)) {
            this.mSupportSplitScreenApps.add(appInfo);
            return true;
        }
        BydLogHelper.logD("addSplitScreenAppInfo appInfo fail");
        return false;
    }

    public boolean removeSplitScreenAppInfo(AppInfo appInfo) {
        BydLogHelper.logD("removeSplitScreenAppInfo appInfo : " + appInfo);
        return this.mSupportSplitScreenApps.remove(appInfo);
    }

    public void notifyAppListChanged() {
        EventBus.getDefault().post(new SplitScreenEvent(5));
    }

    public int getWindowingMode() {
        if (ActivityManagerWrapper.getInstance().getRunningTask() == null) {
            return 1;
        }
        return ActivityManagerWrapper.getInstance().getRunningTask().getConfiguration().windowConfiguration.getWindowingMode();
    }

    public boolean isHorizontalDivision() {
        boolean z = SystemUIApplication.getInstance().getResources().getConfiguration().orientation == 2;
        BydLogHelper.logD("isHorizontalDivision landscape = " + z);
        return z;
    }

    public boolean isAppShowingOtherSide(String str, int windowingMode) {
        try {
            ActivityManager.StackInfo stackInfo = ActivityTaskManager.getService().getStackInfo(windowingMode, 0);
            if (stackInfo != null && stackInfo.taskNames.length > 0) {
                String[] strArr = stackInfo.taskNames;
                BydLogHelper.logD("isAppShowingOtherSide taskNames = " + Arrays.toString(strArr));
                String str2 = strArr[stackInfo.taskNames.length - 1];
                BydLogHelper.logD("isAppShowingOtherSide starting packageName = " + str + " , taskName = " + str2);
                if (str2.startsWith(str)) {
                    return true;
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ActivityManager.RecentTaskInfo> getRecentsTasksList() {
        int currentUserId = ActivityManagerWrapper.getInstance().getCurrentUserId();
        BydLogHelper.logD("userId = " + currentUserId);
        List<ActivityManager.RecentTaskInfo> recentTasks = ActivityManagerWrapper.getInstance().getRecentTasks(ActivityTaskManager.getMaxRecentTasksStatic(), currentUserId);
        ArrayList arrayList = new ArrayList();
        for (ActivityManager.RecentTaskInfo recentTaskInfo : recentTasks) {
            if (recentTaskInfo.displayId == 0) {
                arrayList.add(recentTaskInfo);
            }
        }
        return arrayList;
    }

    public void startActivity(Context context, String packageName, String className, boolean isPrimary) {
        BydLogHelper.logD("startActivity packageName : " + packageName + " , className : " + className + " , isPrimary : " + isPrimary);
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage != null) {
            if (className != null) {
                launchIntentForPackage.setClassName(packageName, className);
            }
            ActivityOptions activityOptions = getActivityOptions(isPrimary);
            BydLogHelper.logD("startActivity makeBasic : " + activityOptions);
            BydLogHelper.logD("startActivity LaunchWindowingMode : " + activityOptions.getLaunchWindowingMode());
            BydLogHelper.logD("startActivity flag = 807403520");
            launchIntentForPackage.setFlags(807403520);
            context.startActivity(launchIntentForPackage, activityOptions.toBundle());
            return;
        }
        BydLogHelper.logW("DividerUtils", "startActivity launchIntentForPackage == null");
    }

    public void startActivity(Context context, Activity activity, ViewGroup viewGroup, View view, Bitmap bitmap, int i, int i2, int i3, int i4, String str, String str2, boolean z) {
        ImageView imageView = new ImageView(context);
        imageView.setBackground(new BitmapDrawable(context.getResources(), bitmap));
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(i, i2);
        BydLogHelper.logD("startActivity x = " + i3 + ", y = " + i4);
        imageView.setX(i3);
        imageView.setY(i4);
        imageView.setLayoutParams(layoutParams);
        ActivityOptions activityLaunchOptions = new AppTransitionManagerImpl(activity, viewGroup, view, imageView, str).getActivityLaunchOptions(i, i2, z ? 3 : 4);
        activityLaunchOptions.setLaunchWindowingMode(z ? 3 : 4);
        activityLaunchOptions.setSplitScreenCreateMode(!z ? 1 : 0);
        context.startActivity(new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").setComponent(new ComponentName(str, str2)).setFlags(270532608), activityLaunchOptions.toBundle());
    }

    public boolean isFirstStartSplitScreen(Context context) {
        int i = Settings.Global.getInt(context.getContentResolver(), "is_first_start_split_screen", 1);
        BydLogHelper.logD("isFirstStartSplitScreen isFirst : " + i);
        if (i != 1) {
            return false;
        }
        BydLogHelper.logD("is first start split screen");
        Settings.Global.putInt(context.getContentResolver(), "is_first_start_split_screen", 0);
        return true;
    }

    public boolean isSupportSplit(String packageName) {
        BydLogHelper.logD("isSupportSplit packageName :" + packageName);
        for (int i = 0; i < this.mSupportSplitScreenApps.size(); i++) {
            String packageName2 = this.mSupportSplitScreenApps.get(i).getPackageName();
            BydLogHelper.logD("isSupportSplit supportSplitScreenPackage :" + packageName2);
            if (packageName.equals(packageName2)) {
                return true;
            }
        }
        return false;
    }

    public void splitScreen(Context context, RecentTaskBean recentTaskBean) {
        try {
            SplitPreviewWindowManager.getInstance(context).executeFullToHalfAnimation(recentTaskBean.getComponentName().getPackageName(), "");
            ActivityManagerWrapper.getInstance().startActivityFromRecents(recentTaskBean.getTaskId(), ActivityOptionsCompat.makeSplitScreenOptions(true));
            this.startSplitType = 2;
            StatusBar statusBar = (StatusBar) SysUiServiceProvider.getComponent(context, StatusBar.class);
            statusBar.updateNaviBarBg(true);
            statusBar.updateStatusBarBgOnSplit(true);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            StatusBar statusBar2 = (StatusBar) SysUiServiceProvider.getComponent(context, StatusBar.class);
            statusBar2.updateNaviBarBg(false);
            statusBar2.updateStatusBarBgOnSplit(false);
        }
    }

    public boolean isShowListActivity(boolean isPrimary) {
        ActivityManager.StackInfo stackInfo;
        try {
            stackInfo = getStackInfo(isPrimary);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (stackInfo != null && stackInfo.visible && stackInfo.topActivity != null) {
            String packageName = stackInfo.topActivity.getPackageName();
            String className = stackInfo.topActivity.getClassName();
            BydLogHelper.logD("isShowListActivity packageName = " + packageName + " , className = " + className);
            String str = isPrimary ? Constants.PackageInfo.PKG_SYSTEM_UI_SPLIT_SCREEN_PRIMARY_CLASS_NAME : Constants.PackageInfo.PKG_SYSTEM_UI_SPLIT_SCREEN_SECONDARY_CLASS_NAME;
            if ("com.android.systemui".equals(packageName)) {
                return str.equals(className);
            }
            return false;
        }
        return false;
    }

    public ActivityOptions getActivityOptions(boolean z) {
        ActivityOptions activityOptionsMakeBasic = ActivityOptions.makeBasic();
        activityOptionsMakeBasic.setLaunchWindowingMode(z ? 3 : 4);
        activityOptionsMakeBasic.setSplitScreenCreateMode(!z ? 1 : 0);
        return activityOptionsMakeBasic;
    }

    public void setDividerStatus() {
        boolean zIsShowListActivity = isShowListActivity(true);
        boolean zIsShowListActivity2 = isShowListActivity(false);
        BydLogHelper.logD("setDividerStatus primaryActivityShowing : " + zIsShowListActivity + " , secondActivityShowing : " + zIsShowListActivity2);
        if (zIsShowListActivity && zIsShowListActivity2) {
            this.mDividerStatus = 3;
        } else if (!zIsShowListActivity && !zIsShowListActivity2) {
            this.mDividerStatus = 2;
        } else {
            this.mDividerStatus = 1;
        }
        BydLogHelper.logD("setDividerStatus mDividerStatus : " + this.mDividerStatus);
    }

    public int getDividerStatus() {
        return this.mDividerStatus;
    }

    public ActivityManager.StackInfo getStackInfo(boolean isPrimary) {
        try {
            return ActivityTaskManager.getService().getStackInfo(isPrimary ? 3 : 4, 0);
        } catch (RemoteException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void startOnePixelActivity(Context context) {
        BydLogHelper.logD("DividerUtils", "startOnePixelActivity");
        Intent intent = new Intent();
        intent.setClassName(context.getPackageName(), OnePixelActivity.class.getName());
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public void startSplitScreenListActivity(Context context, boolean isPrimary) {
        BydLogHelper.logD("DividerUtils", "startSplitScreenListActivity isPrimary : " + isPrimary);
        String str = isPrimary ? Constants.PackageInfo.PKG_SYSTEM_UI_SPLIT_SCREEN_PRIMARY_CLASS_NAME : Constants.PackageInfo.PKG_SYSTEM_UI_SPLIT_SCREEN_SECONDARY_CLASS_NAME;
        Intent intent = new Intent();
        intent.setClassName("com.android.systemui", str);
        Bundle bundle = getActivityOptions(isPrimary).toBundle();
        intent.setFlags(268435456);
        context.startActivity(intent, bundle);
    }

    public boolean containsSupportSplitApp(List<ActivityManager.RecentTaskInfo> list) {
        int i;
        if (list != null) {
            i = 0;
            for (ActivityManager.RecentTaskInfo recentTaskInfo : list) {
                boolean z = recentTaskInfo.supportsSplitScreenMultiWindow;
                BydLogHelper.logD("DividerUtils", "isBothAppList getPackageName : " + recentTaskInfo.realActivity.getPackageName() + " , supportsSplitScreenMultiWindow : " + z);
                if (z) {
                    i++;
                }
                if (i >= 1) {
                    BydLogHelper.logD("DividerUtils", "isBothAppList count : " + i + " return.");
                    return true;
                }
            }
        } else {
            i = 0;
        }
        BydLogHelper.logD("DividerUtils", "isBothAppList count : " + i);
        return false;
    }

    public AppInfo getSplitTask(List<ActivityManager.RecentTaskInfo> list) {
        if (list == null) {
            return null;
        }
        AppInfo appInfo = new AppInfo();
        for (ActivityManager.RecentTaskInfo recentTaskInfo : list) {
            boolean z = recentTaskInfo.supportsSplitScreenMultiWindow;
            BydLogHelper.logD("DividerUtils", "getSplitTask getPackageName : " + recentTaskInfo.realActivity.getPackageName() + " , getClassName : " + recentTaskInfo.realActivity.getClassName() + " , supportsSplitScreenMultiWindow : " + z);
            if (z && appInfo.getTaskId() == -1 && recentTaskInfo.realActivity != null && recentTaskInfo.displayId == 0) {
                BydLogHelper.logD("DividerUtils", "getSplitTask recentTaskInfo.taskId : " + recentTaskInfo.taskId + " , getPackageName : " + recentTaskInfo.realActivity.getPackageName() + " , getClassName : " + recentTaskInfo.realActivity.getClassName());
                appInfo.setTaskId(recentTaskInfo.taskId);
                appInfo.setPackageName(recentTaskInfo.realActivity.getPackageName());
                appInfo.setActivity(recentTaskInfo.realActivity.getClassName());
                BydLogHelper.logD("DividerUtils", "return getSplitTask : " + appInfo);
                return appInfo;
            }
        }
        BydLogHelper.logD("DividerUtils", "return getSplitTask : " + appInfo);
        return appInfo;
    }

    public String getLauncherPackageName(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        return (resolveInfoResolveActivity == null || resolveInfoResolveActivity.activityInfo == null || resolveInfoResolveActivity.activityInfo.packageName.equals("android")) ? "" : resolveInfoResolveActivity.activityInfo.packageName;
    }

    public AppInfo getBackgroundApp(String packageName) {
        try {
            AppInfo appInfo = new AppInfo();
            List filteredTasks = ActivityTaskManager.getService().getFilteredTasks(30, false);
            BydLogHelper.logD("getBackgroundApp tasks =" + filteredTasks);
            for (int i = 0; i < filteredTasks.size(); i++) {
                ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) filteredTasks.get(i);
                if (runningTaskInfo.baseActivity.getPackageName().equals(packageName) && runningTaskInfo.displayId == 0) {
                    appInfo.setTaskId(runningTaskInfo.taskId);
                    appInfo.setPackageName(runningTaskInfo.baseActivity.getPackageName());
                    appInfo.setActivity(runningTaskInfo.baseActivity.getClassName());
                    BydLogHelper.logD("DividerUtils", "return getBackgroundApp appInfo : " + appInfo);
                    return appInfo;
                }
            }
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getStartSplitType() {
        return this.startSplitType;
    }

    public void setStartSplitType(int type) {
        this.startSplitType = type;
    }

    public int getNaviStatus() {
        return this.mNaviStatus;
    }

    public void setNaviStatus(int status) {
        this.mNaviStatus = status;
    }

    public int getMusicPlayState() {
        return this.mMusicPlayState;
    }

    public void setMusicPlayState(int musicPlayState) {
        this.mMusicPlayState = musicPlayState;
    }

    public int getSplitScreenScene() {
        return this.mSplitScreenScene;
    }

    public void setSplitScreenScene(int splitScreenScene) {
        this.mSplitScreenScene = splitScreenScene;
    }

    public int getGestureSlideExitOrientation() {
        return this.mGestureSlideExitOrientation;
    }

    public void setGestureSlideExitOrientation(int gestureSlideExitOrientation) {
        this.mGestureSlideExitOrientation = gestureSlideExitOrientation;
    }

    public boolean isLauncherSelecting() {
        return this.isLauncherSelecting;
    }

    public void setLauncherSelecting(boolean launcherSelecting) {
        this.isLauncherSelecting = launcherSelecting;
    }

    public boolean isPowerMode() {
        return this.mPowerMode;
    }

    public void setPowerMode(boolean mPowerMode) {
        this.mPowerMode = mPowerMode;
    }

    public void reStartSplitScreen(Context context, boolean leftToRight) {
        BydLogHelper.logD("reStartSplitScreen mPrimaryAppInfo : " + this.mPrimaryAppInfo);
        BydLogHelper.logD("reStartSplitScreen mSecondaryAppInfo : " + this.mSecondaryAppInfo);
        AppInfo appInfo = this.mPrimaryAppInfo;
        if (appInfo == null || this.mSecondaryAppInfo == null || appInfo.getPackageName().equals("com.android.systemui") || this.mSecondaryAppInfo.getPackageName().equals("com.android.systemui")) {
            return;
        }
        StatusBar statusBar = (StatusBar) SysUiServiceProvider.getComponent(context, StatusBar.class);
        statusBar.updateNaviBarBg(true);
        statusBar.updateStatusBarBgOnSplit(true);
        String packageName = this.mPrimaryAppInfo.getPackageName();
        int taskId = getTaskId(packageName);
        BydLogHelper.logD("reStartSplitScreen both app packageName : " + packageName + " , primaryTaskId : " + taskId);
        String packageName2 = this.mSecondaryAppInfo.getPackageName();
        String activity = this.mSecondaryAppInfo.getActivity();
        SplitPreviewWindowManager.getInstance(context).executeFullToHalfAnimation(packageName, packageName2, leftToRight);
        EventBus.getDefault().post(new BaseForbiddenActivity.ForbiddenEvent(0));
        BydLogHelper.logD("reStartSplitScreen secondaryPackName = " + packageName2 + " , secondaryClassName : " + activity);
        if (ActivityManagerWrapper.getInstance().setTaskWindowingModeSplitScreenPrimary(taskId)) {
            this.startSplitType = 3;
            Message message = new Message();
            message.what = 2;
            Bundle bundle = new Bundle();
            bundle.putString("packName", packageName2);
            bundle.putString("className", activity);
            message.setData(bundle);
            this.mHandler.removeMessages(2);
            this.mHandler.sendMessage(message);
            return;
        }
        BydLogHelper.logD("reStartSplitScreen both app setTaskWindowingModeSplitScreenPrimary fail");
        statusBar.updateNaviBarBg(false);
        statusBar.updateStatusBarBgOnSplit(false);
    }

    private int getTaskId(String packageName) {
        try {
            List filteredTasks = ActivityTaskManager.getService().getFilteredTasks(30, false);
            for (int i = 0; i < filteredTasks.size(); i++) {
                ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) filteredTasks.get(i);
                if (runningTaskInfo != null && runningTaskInfo.baseActivity != null && runningTaskInfo.baseActivity.getPackageName() != null && runningTaskInfo.displayId == 0) {
                    BydLogHelper.logD("getTaskId getPackageName : " + runningTaskInfo.baseActivity.getPackageName());
                    if (runningTaskInfo.baseActivity.getPackageName().equals(packageName)) {
                        BydLogHelper.logD("getTaskId task id : " + runningTaskInfo.taskId);
                        return runningTaskInfo.taskId;
                    }
                }
            }
            return 0;
        } catch (RemoteException e) {
            throw new SystemUIException(e);
        }
    }

    public static int excludeForbiddenActivity(ActivityManager.StackInfo stackInfo, String[] topTaskName) {
        String[] strArr = stackInfo.taskNames;
        if (strArr.length <= 0) {
            BydLogHelper.logW(TAG, "excludeForbiddenActivity taskNames empty!");
            return -1;
        }
        int[] iArr = stackInfo.taskIds;
        int length = iArr.length - 1;
        String str = strArr[length];
        if (str.contains(ForbiddenSplitPrimaryActivity.class.getSimpleName()) || str.contains(ForbiddenSplitSecondaryActivity.class.getSimpleName())) {
            BydLogHelper.logD(TAG, "currentTop is forbidden, so skip!");
            length--;
        }
        if (length < 0) {
            BydLogHelper.logW(TAG, "excludeForbiddenActivity wrong!");
            return -1;
        }
        if (topTaskName != null && topTaskName.length > 0) {
            topTaskName[0] = strArr[length];
        }
        return iArr[length];
    }

    public boolean isMoreAppExitSplitScreen() {
        return this.isMoreAppExitSplitScreen;
    }

    public void setMoreAppExitSplitScreen(boolean moreAppExitSplitScreen) {
        this.isMoreAppExitSplitScreen = moreAppExitSplitScreen;
    }

    private class H extends Handler {
        public static final int LAUNCHER_REPLACED_DESKTOP = 1;
        public static final int START_ACTIVITY_BOTH_APP = 2;
        WeakReference<DividerUtils> mDividerUtils;

        public H(DividerUtils dividerUtils) {
            this.mDividerUtils = new WeakReference<>(dividerUtils);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (this.mDividerUtils.get() == null) {
                BydLogHelper.logD("handleMessage return");
                return;
            }
            BydLogHelper.logD("handleMessage : " + msg.what);
            if (msg.what == 1) {
                this.mDividerUtils.get().updateLauncherReplacedDesktop();
                return;
            }
            if (msg.what == 2) {
                Bundle data = msg.getData();
                String string = data.getString("packName");
                String string2 = data.getString("className");
                BydLogHelper.logD("packName : " + string + " , className : " + string2);
                this.mDividerUtils.get().startActivity(SystemUIApplication.getInstance(), string, string2, false);
            }
        }
    }
}
