package com.android.systemui.recents.model;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Trace;
import android.util.LruCache;
import com.android.systemui.recents.model.RecentsTaskLoadPlan;
import com.android.systemui.shared.recents.model.IconLoader;
import com.android.systemui.shared.recents.model.Task;
import com.android.systemui.shared.recents.model.TaskKeyLruCache;
import com.android.systemui.shared.recents.model.ThumbnailData;
import com.android.systemui.shared.system.ActivityManagerWrapper;

/* JADX INFO: loaded from: classes.dex */
public class RecentsTaskLoader {
    private static final String TAG = "RecentsTaskLoader";
    private final LruCache<ComponentName, ActivityInfo> mActivityInfoCache;
    private final TaskKeyLruCache<String> mActivityLabelCache;
    private TaskKeyLruCache.EvictionCallback mClearActivityInfoOnEviction = new TaskKeyLruCache.EvictionCallback() { // from class: com.android.systemui.recents.model.RecentsTaskLoader.1
        @Override // com.android.systemui.shared.recents.model.TaskKeyLruCache.EvictionCallback
        public void onEntryEvicted(Task.TaskKey key) {
            if (key != null) {
                RecentsTaskLoader.this.mActivityInfoCache.remove(key.getComponent());
            }
        }
    };
    private final TaskKeyLruCache<String> mContentDescriptionCache;
    private final TaskKeyLruCache<Drawable> mIconCache;
    private final IconLoader mIconLoader;
    private final int mMaxIconCacheSize;
    private final int mMaxThumbnailCacheSize;
    private int mSvelteLevel;

    public void deleteTaskData(Task.TaskKey taskKey) {
    }

    public RecentsTaskLoader(Context context, int maxThumbnailCacheSize, int maxIconCacheSize, int svelteLevel) {
        this.mMaxThumbnailCacheSize = maxThumbnailCacheSize;
        this.mMaxIconCacheSize = maxIconCacheSize;
        this.mSvelteLevel = svelteLevel;
        int maxRecentTasksStatic = ActivityTaskManager.getMaxRecentTasksStatic();
        TaskKeyLruCache<Drawable> taskKeyLruCache = new TaskKeyLruCache<>(maxIconCacheSize, this.mClearActivityInfoOnEviction);
        this.mIconCache = taskKeyLruCache;
        this.mActivityLabelCache = new TaskKeyLruCache<>(maxRecentTasksStatic, this.mClearActivityInfoOnEviction);
        this.mContentDescriptionCache = new TaskKeyLruCache<>(maxRecentTasksStatic, this.mClearActivityInfoOnEviction);
        LruCache<ComponentName, ActivityInfo> lruCache = new LruCache<>(maxRecentTasksStatic);
        this.mActivityInfoCache = lruCache;
        this.mIconLoader = createNewIconLoader(context, taskKeyLruCache, lruCache);
    }

    protected final IconLoader createNewIconLoader(Context context, TaskKeyLruCache<Drawable> iconCache, LruCache<ComponentName, ActivityInfo> activityInfoCache) {
        return new IconLoader.DefaultIconLoader(context, iconCache, activityInfoCache);
    }

    public synchronized void preloadTasks(RecentsTaskLoadPlan plan, int runningTaskId) {
        preloadTasks(plan, runningTaskId, ActivityManagerWrapper.getInstance().getCurrentUserId());
    }

    public synchronized void preloadTasks(RecentsTaskLoadPlan plan, int runningTaskId, int currentUserId) {
        try {
            Trace.beginSection("preloadPlan");
            plan.preloadPlan(new RecentsTaskLoadPlan.PreloadOptions(), this, runningTaskId, currentUserId);
        } finally {
            Trace.endSection();
        }
    }

    public String getAndUpdateActivityTitle(Task.TaskKey taskKey, ActivityManager.TaskDescription td) {
        if (td != null && td.getLabel() != null) {
            return td.getLabel();
        }
        ActivityInfo andUpdateActivityInfo = getAndUpdateActivityInfo(taskKey);
        return andUpdateActivityInfo != null ? ActivityManagerWrapper.getInstance().getBadgedActivityLabel(andUpdateActivityInfo, taskKey.userId) : "";
    }

    public ActivityInfo getAndUpdateActivityInfo(Task.TaskKey taskKey) {
        return this.mIconLoader.getAndUpdateActivityInfo(taskKey);
    }

    String getAndUpdateContentDescription(Task.TaskKey taskKey, ActivityManager.TaskDescription td) {
        String andInvalidateIfModified = this.mContentDescriptionCache.getAndInvalidateIfModified(taskKey);
        if (andInvalidateIfModified != null) {
            return andInvalidateIfModified;
        }
        ActivityInfo andUpdateActivityInfo = getAndUpdateActivityInfo(taskKey);
        if (andUpdateActivityInfo == null) {
            return "";
        }
        String badgedContentDescription = ActivityManagerWrapper.getInstance().getBadgedContentDescription(andUpdateActivityInfo, taskKey.userId, td);
        if (td == null) {
            this.mContentDescriptionCache.put(taskKey, badgedContentDescription);
        }
        return badgedContentDescription;
    }

    public Drawable getAndUpdateActivityIcon(Task.TaskKey taskKey, ActivityManager.TaskDescription td, boolean loadIfNotCached) {
        return this.mIconLoader.getAndInvalidateIfModified(taskKey, td, loadIfNotCached);
    }

    public synchronized ThumbnailData getAndUpdateThumbnail(Task.TaskKey taskKey, boolean loadIfNotCached, boolean storeInCache) {
        ThumbnailData taskThumbnail = ActivityManagerWrapper.getInstance().getTaskThumbnail(taskKey.id, true);
        if (taskThumbnail.thumbnail != null) {
            return taskThumbnail;
        }
        return null;
    }
}
