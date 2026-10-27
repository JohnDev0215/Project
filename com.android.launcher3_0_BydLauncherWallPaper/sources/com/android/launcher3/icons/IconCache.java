package com.android.launcher3.icons;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.UserHandle;
import android.util.Log;
import com.android.launcher3.AppInfo;
import com.android.launcher3.IconProvider;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.LauncherFiles;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.MainThreadExecutor;
import com.android.launcher3.Utilities;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.icons.ComponentWithLabel;
import com.android.launcher3.icons.cache.BaseIconCache;
import com.android.launcher3.icons.cache.CachingLogic;
import com.android.launcher3.icons.cache.HandlerRunnable;
import com.android.launcher3.model.PackageItemInfo;
import com.android.launcher3.util.InstantAppResolver;
import com.android.launcher3.util.Preconditions;
import java.util.Iterator;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes4.dex */
public class IconCache extends BaseIconCache {
    private static final String TAG = "Launcher.IconCache";
    private final CachingLogic<ComponentWithLabel> mComponentWithLabelCachingLogic;
    private final IconProvider mIconProvider;
    private final InstantAppResolver mInstantAppResolver;
    private final CachingLogic<LauncherActivityInfo> mLauncherActivityInfoCachingLogic;
    private final LauncherAppsCompat mLauncherApps;
    private final MainThreadExecutor mMainThreadExecutor;
    private int mPendingIconRequestCount;
    private final UserManagerCompat mUserManager;

    public interface ItemInfoUpdateReceiver {
        void reapplyItemInfo(ItemInfoWithIcon info);
    }

    static /* synthetic */ LauncherActivityInfo lambda$getTitleAndIcon$1(LauncherActivityInfo launcherActivityInfo) {
        return launcherActivityInfo;
    }

    static /* synthetic */ ComponentWithLabel lambda$getTitleNoCache$3(ComponentWithLabel componentWithLabel) {
        return componentWithLabel;
    }

    static /* synthetic */ LauncherActivityInfo lambda$updateTitleAndIcon$0() {
        return null;
    }

    public IconCache(Context context, InvariantDeviceProfile inv) {
        super(context, LauncherFiles.APP_ICONS_DB, LauncherModel.getWorkerLooper(), inv.fillResIconDpi, inv.iconBitmapSize, true);
        this.mMainThreadExecutor = new MainThreadExecutor();
        this.mPendingIconRequestCount = 0;
        this.mComponentWithLabelCachingLogic = new ComponentWithLabel.ComponentCachingLogic(context);
        this.mLauncherActivityInfoCachingLogic = new LauncherActivtiyCachingLogic(this);
        this.mLauncherApps = LauncherAppsCompat.getInstance(this.mContext);
        this.mUserManager = UserManagerCompat.getInstance(this.mContext);
        this.mInstantAppResolver = InstantAppResolver.newInstance(this.mContext);
        this.mIconProvider = IconProvider.newInstance(context);
    }

    @Override // com.android.launcher3.icons.cache.BaseIconCache
    protected long getSerialNumberForUser(UserHandle user) {
        return this.mUserManager.getSerialNumberForUser(user);
    }

    @Override // com.android.launcher3.icons.cache.BaseIconCache
    protected boolean isInstantApp(ApplicationInfo info) {
        return this.mInstantAppResolver.isInstantApp(info);
    }

    @Override // com.android.launcher3.icons.cache.BaseIconCache
    protected BaseIconFactory getIconFactory() {
        return LauncherIcons.obtain(this.mContext);
    }

    public synchronized void updateIconsForPkg(String packageName, UserHandle user) {
        removeIconsForPkg(packageName, user);
        try {
            PackageInfo packageInfo = this.mPackageManager.getPackageInfo(packageName, 8192);
            long serialNumberForUser = this.mUserManager.getSerialNumberForUser(user);
            Iterator<LauncherActivityInfo> it = this.mLauncherApps.getActivityList(packageName, user).iterator();
            while (it.hasNext()) {
                addIconToDBAndMemCache(it.next(), this.mLauncherActivityInfoCachingLogic, packageInfo, serialNumberForUser, false);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "Package not found", e);
        }
    }

    public IconLoadRequest updateIconInBackground(final ItemInfoUpdateReceiver caller, final ItemInfoWithIcon info) {
        Preconditions.assertUIThread();
        if (this.mPendingIconRequestCount <= 0) {
            LauncherModel.setWorkerPriority(-2);
        }
        this.mPendingIconRequestCount++;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.mWorkerHandler, new Runnable() { // from class: com.android.launcher3.icons.-$$Lambda$IconCache$pZ_BB-NATpnSTVCuNmuPMkIR3Ug
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.onIconRequestEnd();
            }
        }, info, caller);
        Utilities.postAsyncCallback(this.mWorkerHandler, anonymousClass1);
        return anonymousClass1;
    }

    /* JADX INFO: renamed from: com.android.launcher3.icons.IconCache$1, reason: invalid class name */
    class AnonymousClass1 extends IconLoadRequest {
        final /* synthetic */ ItemInfoUpdateReceiver val$caller;
        final /* synthetic */ ItemInfoWithIcon val$info;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Handler handler, Runnable endRunnable, final ItemInfoWithIcon val$info, final ItemInfoUpdateReceiver val$caller) {
            super(handler, endRunnable);
            this.val$info = val$info;
            this.val$caller = val$caller;
        }

        @Override // java.lang.Runnable
        public void run() {
            ItemInfoWithIcon itemInfoWithIcon = this.val$info;
            if ((itemInfoWithIcon instanceof AppInfo) || (itemInfoWithIcon instanceof WorkspaceItemInfo)) {
                IconCache.this.getTitleAndIcon(itemInfoWithIcon, false);
            } else if (itemInfoWithIcon instanceof PackageItemInfo) {
                IconCache.this.getTitleAndIconForApp((PackageItemInfo) itemInfoWithIcon, false);
            }
            MainThreadExecutor mainThreadExecutor = IconCache.this.mMainThreadExecutor;
            final ItemInfoUpdateReceiver itemInfoUpdateReceiver = this.val$caller;
            final ItemInfoWithIcon itemInfoWithIcon2 = this.val$info;
            mainThreadExecutor.execute(new Runnable() { // from class: com.android.launcher3.icons.-$$Lambda$IconCache$1$bLbaP4ZdRWz7scCGoRfNRSdpDgo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$run$0$IconCache$1(itemInfoUpdateReceiver, itemInfoWithIcon2);
                }
            });
        }

        public /* synthetic */ void lambda$run$0$IconCache$1(ItemInfoUpdateReceiver itemInfoUpdateReceiver, ItemInfoWithIcon itemInfoWithIcon) {
            itemInfoUpdateReceiver.reapplyItemInfo(itemInfoWithIcon);
            onEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onIconRequestEnd() {
        int i = this.mPendingIconRequestCount - 1;
        this.mPendingIconRequestCount = i;
        if (i <= 0) {
            LauncherModel.setWorkerPriority(10);
        }
    }

    public synchronized void updateTitleAndIcon(AppInfo application) {
        BaseIconCache.CacheEntry cacheEntryCacheLocked = cacheLocked(application.componentName, application.user, new Supplier() { // from class: com.android.launcher3.icons.-$$Lambda$IconCache$ldrneUJkD38a-lZdcq-gQknl5HM
            @Override // java.util.function.Supplier
            public final Object get() {
                return IconCache.lambda$updateTitleAndIcon$0();
            }
        }, this.mLauncherActivityInfoCachingLogic, false, application.usingLowResIcon());
        if (cacheEntryCacheLocked.icon != null && !isDefaultIcon(cacheEntryCacheLocked.icon, application.user)) {
            applyCacheEntry(cacheEntryCacheLocked, application);
        }
    }

    public synchronized void getTitleAndIcon(ItemInfoWithIcon info, final LauncherActivityInfo activityInfo, boolean useLowResIcon) {
        getTitleAndIcon(info, new Supplier() { // from class: com.android.launcher3.icons.-$$Lambda$IconCache$sGAxDi4sZ5qEv3Sw4BnEof5E5Jk
            @Override // java.util.function.Supplier
            public final Object get() {
                return IconCache.lambda$getTitleAndIcon$1(activityInfo);
            }
        }, false, useLowResIcon);
    }

    public synchronized void getTitleAndIcon(final ItemInfoWithIcon info, boolean useLowResIcon) {
        if (info.getTargetComponent() == null) {
            info.applyFrom(getDefaultIcon(info.user));
            info.title = "";
            info.contentDescription = "";
        } else {
            final Intent intent = info.getIntent();
            getTitleAndIcon(info, new Supplier() { // from class: com.android.launcher3.icons.-$$Lambda$IconCache$sL56DOeTHEbfLG-SMEKkWERZcIA
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.f$0.lambda$getTitleAndIcon$2$IconCache(intent, info);
                }
            }, true, useLowResIcon);
        }
    }

    public /* synthetic */ LauncherActivityInfo lambda$getTitleAndIcon$2$IconCache(Intent intent, ItemInfoWithIcon itemInfoWithIcon) {
        return this.mLauncherApps.resolveActivity(intent, itemInfoWithIcon.user);
    }

    public synchronized String getTitleNoCache(final ComponentWithLabel info) {
        return Utilities.trim(cacheLocked(info.getComponent(), info.getUser(), new Supplier() { // from class: com.android.launcher3.icons.-$$Lambda$IconCache$GoZ77TKAyRSDFtvzBvh1gp3GJW8
            @Override // java.util.function.Supplier
            public final Object get() {
                return IconCache.lambda$getTitleNoCache$3(info);
            }
        }, this.mComponentWithLabelCachingLogic, false, true, false).title);
    }

    private synchronized void getTitleAndIcon(ItemInfoWithIcon infoInOut, Supplier<LauncherActivityInfo> activityInfoProvider, boolean usePkgIcon, boolean useLowResIcon) {
        applyCacheEntry(cacheLocked(infoInOut.getTargetComponent(), infoInOut.user, activityInfoProvider, this.mLauncherActivityInfoCachingLogic, usePkgIcon, useLowResIcon), infoInOut);
    }

    public synchronized void getTitleAndIconForApp(PackageItemInfo infoInOut, boolean useLowResIcon) {
        applyCacheEntry(getEntryForPackageLocked(infoInOut.packageName, infoInOut.user, useLowResIcon), infoInOut);
    }

    protected void applyCacheEntry(BaseIconCache.CacheEntry cacheEntry, ItemInfoWithIcon itemInfoWithIcon) {
        itemInfoWithIcon.title = Utilities.trim(cacheEntry.title);
        itemInfoWithIcon.contentDescription = cacheEntry.contentDescription;
        Bitmap bitmap = cacheEntry.icon;
        BitmapInfo defaultIcon = cacheEntry;
        if (bitmap == null) {
            defaultIcon = getDefaultIcon(itemInfoWithIcon.user);
        }
        itemInfoWithIcon.applyFrom(defaultIcon);
    }

    public Drawable getFullResIcon(LauncherActivityInfo info) {
        return getFullResIcon(info, true);
    }

    public Drawable getFullResIcon(LauncherActivityInfo info, boolean flattenDrawable) {
        return this.mIconProvider.getIcon(info, this.mIconDpi, flattenDrawable);
    }

    @Override // com.android.launcher3.icons.cache.BaseIconCache
    protected String getIconSystemState(String packageName) {
        return this.mIconProvider.getSystemStateForPackage(this.mSystemState, packageName);
    }

    public static abstract class IconLoadRequest extends HandlerRunnable {
        IconLoadRequest(Handler handler, Runnable endRunnable) {
            super(handler, endRunnable);
        }
    }
}
