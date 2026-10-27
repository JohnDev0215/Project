package com.android.launcher3.compat;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.launcher3.LauncherAppWidgetProviderInfo;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.PackageUserKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
class AppWidgetManagerCompatVL extends AppWidgetManagerCompat {
    private final UserManager mUserManager;

    AppWidgetManagerCompatVL(Context context) {
        super(context);
        this.mUserManager = (UserManager) context.getSystemService("user");
    }

    @Override // com.android.launcher3.compat.AppWidgetManagerCompat
    public List<AppWidgetProviderInfo> getAllProviders(PackageUserKey packageUser) {
        if (packageUser == null) {
            ArrayList arrayList = new ArrayList();
            Iterator<UserHandle> it = this.mUserManager.getUserProfiles().iterator();
            while (it.hasNext()) {
                arrayList.addAll(this.mAppWidgetManager.getInstalledProvidersForProfile(it.next()));
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(this.mAppWidgetManager.getInstalledProvidersForProfile(packageUser.mUser));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            if (!((AppWidgetProviderInfo) it2.next()).provider.getPackageName().equals(packageUser.mPackageName)) {
                it2.remove();
            }
        }
        return arrayList2;
    }

    @Override // com.android.launcher3.compat.AppWidgetManagerCompat
    public boolean bindAppWidgetIdIfAllowed(int appWidgetId, AppWidgetProviderInfo info, Bundle options) {
        return this.mAppWidgetManager.bindAppWidgetIdIfAllowed(appWidgetId, info.getProfile(), info.provider, options);
    }

    @Override // com.android.launcher3.compat.AppWidgetManagerCompat
    public LauncherAppWidgetProviderInfo findProvider(ComponentName provider, UserHandle user) {
        for (AppWidgetProviderInfo appWidgetProviderInfo : getAllProviders(new PackageUserKey(provider.getPackageName(), user))) {
            if (appWidgetProviderInfo.provider.equals(provider)) {
                return LauncherAppWidgetProviderInfo.fromProviderInfo(this.mContext, appWidgetProviderInfo);
            }
        }
        return null;
    }

    @Override // com.android.launcher3.compat.AppWidgetManagerCompat
    public HashMap<ComponentKey, AppWidgetProviderInfo> getAllProvidersMap() {
        HashMap<ComponentKey, AppWidgetProviderInfo> map = new HashMap<>();
        for (UserHandle userHandle : this.mUserManager.getUserProfiles()) {
            for (AppWidgetProviderInfo appWidgetProviderInfo : this.mAppWidgetManager.getInstalledProvidersForProfile(userHandle)) {
                map.put(new ComponentKey(appWidgetProviderInfo.provider, userHandle), appWidgetProviderInfo);
            }
        }
        return map;
    }
}
