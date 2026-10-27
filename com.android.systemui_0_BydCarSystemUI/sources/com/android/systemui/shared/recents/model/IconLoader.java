package com.android.systemui.shared.recents.model;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.util.LruCache;
import com.android.systemui.shared.recents.model.Task;
import com.android.systemui.shared.system.PackageManagerWrapper;

/* JADX INFO: loaded from: classes.dex */
public abstract class IconLoader {
    private static final String TAG = "IconLoader";
    protected final LruCache<ComponentName, ActivityInfo> mActivityInfoCache;
    protected final Context mContext;
    protected final TaskKeyLruCache<Drawable> mIconCache;

    protected abstract Drawable createBadgedDrawable(Drawable icon, int userId, ActivityManager.TaskDescription desc);

    protected abstract Drawable getBadgedActivityIcon(ActivityInfo info, int userId, ActivityManager.TaskDescription desc);

    public abstract Drawable getDefaultIcon(int userId);

    public IconLoader(Context context, TaskKeyLruCache<Drawable> iconCache, LruCache<ComponentName, ActivityInfo> activityInfoCache) {
        this.mContext = context;
        this.mIconCache = iconCache;
        this.mActivityInfoCache = activityInfoCache;
    }

    public ActivityInfo getAndUpdateActivityInfo(Task.TaskKey taskKey) {
        ComponentName component = taskKey.getComponent();
        ActivityInfo activityInfo = PackageManagerWrapper.getInstance().getActivityInfo(component, taskKey.userId);
        if (component != null && activityInfo != null) {
            return activityInfo;
        }
        Log.e(TAG, "Unexpected null component name or activity info: " + component + ", " + activityInfo);
        return null;
    }

    public Drawable getIcon(Task t) {
        Drawable drawable = this.mIconCache.get(t.key);
        if (drawable != null) {
            return drawable;
        }
        Drawable drawableCreateNewIconForTask = createNewIconForTask(t.key, t.taskDescription, true);
        this.mIconCache.put(t.key, drawableCreateNewIconForTask);
        return drawableCreateNewIconForTask;
    }

    public Drawable getAndInvalidateIfModified(Task.TaskKey taskKey, ActivityManager.TaskDescription td, boolean loadIfNotCached) {
        return createNewIconForTask(taskKey, td, false);
    }

    private Drawable createNewIconForTask(Task.TaskKey taskKey, ActivityManager.TaskDescription desc, boolean returnDefault) {
        Drawable badgedActivityIcon;
        int i = taskKey.userId;
        Bitmap inMemoryIcon = desc.getInMemoryIcon();
        if (inMemoryIcon != null) {
            return createDrawableFromBitmap(inMemoryIcon, i, desc);
        }
        if (desc.getIconResource() != 0) {
            try {
                PackageManager packageManager = this.mContext.getPackageManager();
                return createBadgedDrawable(packageManager.getResourcesForApplication(packageManager.getApplicationInfo(taskKey.getPackageName(), 4194304)).getDrawable(desc.getIconResource(), null), i, desc);
            } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
                Log.e(TAG, "Could not find icon drawable from resource", e);
            }
        }
        Bitmap bitmapLoadTaskDescriptionIcon = ActivityManager.TaskDescription.loadTaskDescriptionIcon(desc.getIconFilename(), i);
        if (bitmapLoadTaskDescriptionIcon != null) {
            return createDrawableFromBitmap(bitmapLoadTaskDescriptionIcon, i, desc);
        }
        ActivityInfo andUpdateActivityInfo = getAndUpdateActivityInfo(taskKey);
        if (andUpdateActivityInfo != null && (badgedActivityIcon = getBadgedActivityIcon(andUpdateActivityInfo, i, desc)) != null) {
            return badgedActivityIcon;
        }
        if (returnDefault) {
            return getDefaultIcon(i);
        }
        return null;
    }

    protected Drawable createDrawableFromBitmap(Bitmap icon, int userId, ActivityManager.TaskDescription desc) {
        return createBadgedDrawable(new BitmapDrawable(this.mContext.getResources(), icon), userId, desc);
    }

    public static class DefaultIconLoader extends IconLoader {
        private final BitmapDrawable mDefaultIcon;
        private final IconDrawableFactory mDrawableFactory;

        public DefaultIconLoader(Context context, TaskKeyLruCache<Drawable> iconCache, LruCache<ComponentName, ActivityInfo> activityInfoCache) {
            super(context, iconCache, activityInfoCache);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
            bitmapCreateBitmap.eraseColor(0);
            this.mDefaultIcon = new BitmapDrawable(context.getResources(), bitmapCreateBitmap);
            this.mDrawableFactory = IconDrawableFactory.newInstance(context);
        }

        @Override // com.android.systemui.shared.recents.model.IconLoader
        public Drawable getDefaultIcon(int userId) {
            return this.mDefaultIcon;
        }

        @Override // com.android.systemui.shared.recents.model.IconLoader
        protected Drawable createBadgedDrawable(Drawable icon, int userId, ActivityManager.TaskDescription desc) {
            return userId != UserHandle.myUserId() ? this.mContext.getPackageManager().getUserBadgedIcon(icon, new UserHandle(userId)) : icon;
        }

        @Override // com.android.systemui.shared.recents.model.IconLoader
        protected Drawable getBadgedActivityIcon(ActivityInfo info, int userId, ActivityManager.TaskDescription desc) {
            return this.mDrawableFactory.getBadgedIcon(info, info.applicationInfo, userId);
        }
    }
}
