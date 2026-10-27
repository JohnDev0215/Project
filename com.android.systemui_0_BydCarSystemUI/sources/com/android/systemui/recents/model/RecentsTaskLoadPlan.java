package com.android.systemui.recents.model;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.properties.CarSettings;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import com.android.systemui.Constants;
import com.android.systemui.R;
import com.android.systemui.recents.RecentTaskBean;
import com.android.systemui.shared.recents.model.Task;
import com.android.systemui.shared.recents.model.ThumbnailData;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.statusbar.wm.Util;
import com.android.systemui.util.StyleUtil;
import com.byd.card.bean.Constant;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.skin.loader.SkinManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RecentsTaskLoadPlan {
    private CallBack callBack;
    private final Context mContext;
    private final KeyguardManager mKeyguardManager;
    private List<ActivityManager.RecentTaskInfo> mRawTasks;
    private final PackageManager pm;
    private boolean preLoad;
    private ArrayList<RecentTaskBean> listRecent = new ArrayList<>();
    private final SparseBooleanArray mTmpLockedUsers = new SparseBooleanArray();

    public interface CallBack {
        void onResult(List<RecentTaskBean> list);
    }

    public static class Options {
        public int runningTaskId = -1;
        public boolean loadIcons = true;
        public boolean loadThumbnails = false;
        public boolean onlyLoadForCache = false;
        public boolean onlyLoadPausedActivities = false;
        public int numVisibleTasks = 0;
        public int numVisibleTaskThumbnails = 0;
    }

    public static class PreloadOptions {
        public boolean loadTitles = true;
    }

    public RecentsTaskLoadPlan(Context context) {
        this.mContext = context;
        this.mKeyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        this.pm = context.getPackageManager();
    }

    public void preloadPlan(PreloadOptions opts, RecentsTaskLoader loader, int runningTaskId, int currentUserId) {
        ComponentName componentName;
        int i;
        HashMap<String, String> map;
        int i2;
        HashMap<String, String> launcherList = Util.getLauncherList(this.mContext);
        this.mContext.getResources();
        new ArrayList();
        this.listRecent.clear();
        if (this.mRawTasks == null) {
            this.mRawTasks = ActivityManagerWrapper.getInstance().getRecentTasks(ActivityTaskManager.getMaxRecentTasksStatic(), currentUserId);
        }
        int size = this.mRawTasks.size();
        BydLogHelper.logD("taskCount:" + size);
        int i3 = 0;
        while (i3 < size) {
            ActivityManager.RecentTaskInfo recentTaskInfo = this.mRawTasks.get(i3);
            if (recentTaskInfo.origActivity != null) {
                componentName = recentTaskInfo.origActivity;
            } else {
                componentName = recentTaskInfo.realActivity;
            }
            if ((launcherList != null && launcherList.size() > 0 && launcherList.containsKey(componentName.getPackageName())) || TextUtils.equals(componentName.getPackageName(), "com.byd.card") || TextUtils.equals(componentName.getPackageName(), "com.android.systemui")) {
                map = launcherList;
                i2 = size;
                i = i3;
            } else {
                i = i3;
                map = launcherList;
                i2 = size;
                Task.TaskKey taskKey = new Task.TaskKey(recentTaskInfo.taskId, recentTaskInfo.configuration.windowConfiguration.getWindowingMode(), recentTaskInfo.baseIntent, componentName, recentTaskInfo.userId, recentTaskInfo.lastActiveTime, recentTaskInfo.displayId);
                int i4 = taskKey.id;
                ActivityInfo andUpdateActivityInfo = loader.getAndUpdateActivityInfo(taskKey);
                if (andUpdateActivityInfo != null) {
                    String andUpdateActivityTitle = opts.loadTitles ? loader.getAndUpdateActivityTitle(taskKey, recentTaskInfo.taskDescription) : "";
                    if (opts.loadTitles) {
                        loader.getAndUpdateContentDescription(taskKey, recentTaskInfo.taskDescription);
                    }
                    Drawable themeLogoByPkg = getThemeLogoByPkg(taskKey.getPackageName());
                    if (themeLogoByPkg == null) {
                        themeLogoByPkg = loader.getAndUpdateActivityIcon(taskKey, recentTaskInfo.taskDescription, true);
                    }
                    ThumbnailData andUpdateThumbnail = loader.getAndUpdateThumbnail(taskKey, true, true);
                    if (andUpdateActivityInfo != null) {
                        int i5 = andUpdateActivityInfo.applicationInfo.flags & 1;
                    }
                    if (this.mTmpLockedUsers.indexOfKey(recentTaskInfo.userId) < 0) {
                        this.mTmpLockedUsers.put(recentTaskInfo.userId, this.mKeyguardManager.isDeviceLocked(recentTaskInfo.userId));
                    }
                    this.mTmpLockedUsers.get(recentTaskInfo.userId);
                    BydLogHelper.logD("thumbnail:" + andUpdateThumbnail + ",title:" + andUpdateActivityTitle + ",pkg:" + taskKey.getPackageName());
                    if (!TextUtils.isEmpty(andUpdateActivityTitle) && themeLogoByPkg != null && andUpdateThumbnail != null) {
                        new ArrayList().add(Integer.valueOf(recentTaskInfo.taskId));
                        boolean zBooleanValue = Constants.recentShowLockMap.containsKey(componentName.getPackageName()) ? Constants.recentShowLockMap.get(componentName.getPackageName()).booleanValue() : false;
                        RecentTaskBean recentTaskBean = new RecentTaskBean();
                        recentTaskBean.setAppName(andUpdateActivityTitle);
                        recentTaskBean.setAppLogo(themeLogoByPkg);
                        recentTaskBean.setTaskId(recentTaskInfo.taskId);
                        recentTaskBean.setBitmap(changeThumbnailBitmap(andUpdateThumbnail));
                        recentTaskBean.setShowLock(zBooleanValue);
                        recentTaskBean.setComponentName(componentName);
                        recentTaskBean.setTaskKey(taskKey);
                        if (recentTaskInfo.supportsSplitScreenMultiWindow) {
                            recentTaskBean.setShowSplit(true);
                        } else {
                            recentTaskBean.setShowSplit(false);
                        }
                        this.listRecent.add(recentTaskBean);
                    }
                }
                i3 = i + 1;
                launcherList = map;
                size = i2;
            }
            i3 = i + 1;
            launcherList = map;
            size = i2;
        }
        CallBack callBack = this.callBack;
        if (callBack != null) {
            callBack.onResult(this.listRecent);
        }
    }

    private Drawable getThemeLogoByPkg(String pkg) {
        BydLogHelper.logD("getThemeLogoByPkg: uuid:" + CarSettings.UserTableData.getInt(this.mContext.getContentResolver(), CarSettings.UserTableData.BYD_APP_THEME));
        if (TextUtils.equals("com.byd.carsettings", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_carsettings);
        }
        if (TextUtils.equals("com.byd.hvac", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_airconditioning);
        }
        if (TextUtils.equals("com.byd.vrassistant.xf", pkg) || TextUtils.equals("com.byd.vrsettings", pkg)) {
            if (StyleUtil.isDenza()) {
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_autovoice_deza);
            }
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_autovoice);
        }
        if (TextUtils.equals("com.byd.localmusic", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_mediacenter);
        }
        if (TextUtils.equals("com.byd.bluetoothcall", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_bluetoothcall);
        }
        if (TextUtils.equals("com.byd.mycar", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_mycar);
        }
        if (TextUtils.equals("com.byd.appstartmanagement", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_appstartmanagement);
        }
        if (TextUtils.equals("com.byd.auto_camera", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_auto_camera);
        }
        if (TextUtils.equals("com.byd.auto_photo", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_auto_photo);
        }
        if (TextUtils.equals("com.byd.filemanager", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_filemanager);
        }
        if (TextUtils.equals("com.byd.trafficmonitor", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_trafficmonitor);
        }
        if (TextUtils.equals("com.byd.automultipletheme", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_multipletheme);
        }
        if (TextUtils.equals("com.byd.smarttravel", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_smarttravel);
        }
        if (TextUtils.equals("com.byd.avm", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_avc);
        }
        if (TextUtils.equals("com.byd.dvr", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_dvr);
        }
        if (TextUtils.equals(Constant.PackageName.OVER_SEA_APP_STORE, pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_appstore);
        }
        return null;
    }

    private Bitmap changeThumbnailBitmap(ThumbnailData thumbnailData) {
        if (thumbnailData == null || thumbnailData.thumbnail == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        Rect rect = thumbnailData.insets;
        matrix.setTranslate(-rect.top, -rect.left);
        int i = this.mContext.getResources().getConfiguration().orientation;
        int i2 = thumbnailData.orientation;
        if (i != i2) {
            if (i2 == 1) {
                matrix.setRotate(-90.0f);
            } else {
                matrix.setRotate(90.0f);
            }
        }
        Bitmap bitmap = thumbnailData.thumbnail;
        int width = (bitmap.getWidth() - rect.left) - rect.right;
        int height = thumbnailData.thumbnail.getHeight();
        if (thumbnailData.reducedResolution) {
            return Bitmap.createBitmap(bitmap, rect.left, rect.top / 2, width, (height - (rect.top / 2)) - (rect.bottom / 2), matrix, false);
        }
        return Bitmap.createBitmap(bitmap, rect.left, rect.top, width, (height - rect.top) - rect.bottom, matrix, false);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public boolean hasTasks() {
        ArrayList<RecentTaskBean> arrayList = this.listRecent;
        return arrayList != null && arrayList.size() > 0;
    }
}
