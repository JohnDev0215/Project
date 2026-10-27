package com.android.launcher3.icons;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.os.UserHandle;
import com.android.launcher3.icons.cache.CachingLogic;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherActivtiyCachingLogic implements CachingLogic<LauncherActivityInfo> {
    private final IconCache mCache;

    public LauncherActivtiyCachingLogic(IconCache cache) {
        this.mCache = cache;
    }

    @Override // com.android.launcher3.icons.cache.CachingLogic
    public ComponentName getComponent(LauncherActivityInfo object) {
        return object.getComponentName();
    }

    @Override // com.android.launcher3.icons.cache.CachingLogic
    public UserHandle getUser(LauncherActivityInfo object) {
        return object.getUser();
    }

    @Override // com.android.launcher3.icons.cache.CachingLogic
    public CharSequence getLabel(LauncherActivityInfo object) {
        return object.getLabel();
    }

    @Override // com.android.launcher3.icons.cache.CachingLogic
    public void loadIcon(Context context, LauncherActivityInfo object, BitmapInfo target) {
        LauncherIcons launcherIconsObtain = LauncherIcons.obtain(context);
        launcherIconsObtain.createBadgedIconBitmap(this.mCache.getFullResIcon(object), object.getUser(), object.getApplicationInfo().targetSdkVersion).applyTo(target);
        launcherIconsObtain.recycle();
    }
}
