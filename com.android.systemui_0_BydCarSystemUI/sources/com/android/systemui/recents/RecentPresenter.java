package com.android.systemui.recents;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.base.BasePresenter;
import com.android.systemui.recents.RecentsContract;
import com.android.systemui.recents.model.RecentsTaskLoader;
import com.android.systemui.shared.recents.model.Task;
import com.android.systemui.shared.recents.model.ThumbnailData;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.statusbar.wm.Util;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RecentPresenter extends BasePresenter<RecentsContract.View> implements RecentsContract.Presenter {
    @Override // com.android.systemui.base.BasePresenter, com.android.systemui.base.BaseContract.BasePresenter
    public void initData() {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.recents.RecentPresenter.1
            @Override // java.lang.Runnable
            public void run() {
                RecentPresenter.this.preloadPlan(LegacyRecentsImpl.getTaskLoader());
            }
        }, 50L);
    }

    public void preloadPlan(RecentsTaskLoader loader) {
        ComponentName componentName;
        int i;
        HashMap<String, String> map;
        if (this.mView == 0) {
            Log.w("RecentPresenter", "preloadPlan mView is null");
            return;
        }
        if (((RecentsContract.View) this.mView).getContext() == null) {
            Log.w("RecentPresenter", "preloadPlan getContext is null");
            return;
        }
        HashMap<String, String> launcherList = Util.getLauncherList(((RecentsContract.View) this.mView).getContext());
        ArrayList arrayList = new ArrayList();
        List<ActivityManager.RecentTaskInfo> recentTasks = ActivityManagerWrapper.getInstance().getRecentTasks(ActivityTaskManager.getMaxRecentTasksStatic(), ActivityManagerWrapper.getInstance().getCurrentUserId());
        int size = recentTasks.size();
        BydLogHelper.logD("taskCount:" + size);
        int i2 = 0;
        while (i2 < size) {
            ActivityManager.RecentTaskInfo recentTaskInfo = recentTasks.get(i2);
            if (recentTaskInfo.origActivity != null) {
                componentName = recentTaskInfo.origActivity;
            } else {
                componentName = recentTaskInfo.realActivity;
            }
            if ((launcherList != null && launcherList.size() > 0 && launcherList.containsKey(componentName.getPackageName())) || TextUtils.equals(componentName.getPackageName(), "com.byd.card") || TextUtils.equals(componentName.getPackageName(), "com.android.systemui")) {
                map = launcherList;
                i = i2;
            } else {
                i = i2;
                map = launcherList;
                Task.TaskKey taskKey = new Task.TaskKey(recentTaskInfo.taskId, recentTaskInfo.configuration.windowConfiguration.getWindowingMode(), recentTaskInfo.baseIntent, componentName, recentTaskInfo.userId, recentTaskInfo.lastActiveTime, recentTaskInfo.displayId);
                ActivityInfo andUpdateActivityInfo = loader.getAndUpdateActivityInfo(taskKey);
                if (andUpdateActivityInfo != null) {
                    String andUpdateActivityTitle = loader.getAndUpdateActivityTitle(taskKey, recentTaskInfo.taskDescription);
                    if (!TextUtils.isEmpty(andUpdateActivityTitle)) {
                        new ArrayList().add(Integer.valueOf(recentTaskInfo.taskId));
                        boolean zBooleanValue = Constants.recentShowLockMap.containsKey(componentName.getPackageName()) ? Constants.recentShowLockMap.get(componentName.getPackageName()).booleanValue() : false;
                        if (!Constants.EXCLUDE_PACKAGES.contains(componentName.getPackageName()) && !Constants.MEDIA_APP_PACKAGES.containsValue(componentName.getPackageName())) {
                            RecentTaskBean recentTaskBean = new RecentTaskBean();
                            recentTaskBean.setAppName(andUpdateActivityTitle);
                            recentTaskBean.setTaskId(recentTaskInfo.taskId);
                            recentTaskBean.setShowLock(zBooleanValue);
                            recentTaskBean.setComponentName(componentName);
                            recentTaskBean.setTaskKey(taskKey);
                            recentTaskBean.setProcessName(andUpdateActivityInfo.processName);
                            recentTaskBean.setTaskDescription(recentTaskInfo.taskDescription);
                            if (recentTaskInfo.supportsSplitScreenMultiWindow) {
                                recentTaskBean.setShowSplit(true);
                            } else {
                                recentTaskBean.setShowSplit(false);
                            }
                            arrayList.add(recentTaskBean);
                        }
                    }
                }
                i2 = i + 1;
                launcherList = map;
            }
            i2 = i + 1;
            launcherList = map;
        }
        ((RecentsContract.View) this.mView).refreshList(arrayList);
    }

    private Bitmap changeThumbnailBitmap(ThumbnailData thumbnailData) {
        if (thumbnailData == null || thumbnailData.thumbnail == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        Rect rect = thumbnailData.insets;
        matrix.setTranslate(-rect.top, -rect.left);
        int i = ((RecentsContract.View) this.mView).getContext().getResources().getConfiguration().orientation;
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
}
