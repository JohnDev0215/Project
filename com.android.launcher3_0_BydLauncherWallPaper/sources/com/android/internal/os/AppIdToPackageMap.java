package com.android.internal.os;

import android.app.AppGlobals;
import android.content.pm.PackageInfo;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class AppIdToPackageMap {
    private final Map<Integer, String> mAppIdToPackageMap;

    public AppIdToPackageMap(Map<Integer, String> map) {
        this.mAppIdToPackageMap = map;
    }

    public static AppIdToPackageMap getSnapshot() {
        try {
            List<PackageInfo> list = AppGlobals.getPackageManager().getInstalledPackages(794624, 0).getList();
            HashMap map = new HashMap();
            for (PackageInfo packageInfo : list) {
                int i = packageInfo.applicationInfo.uid;
                if (packageInfo.sharedUserId != null && map.containsKey(Integer.valueOf(i))) {
                    map.put(Integer.valueOf(i), "shared:" + packageInfo.sharedUserId);
                } else {
                    map.put(Integer.valueOf(i), packageInfo.packageName);
                }
            }
            return new AppIdToPackageMap(map);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String mapAppId(int i) {
        String str = this.mAppIdToPackageMap.get(Integer.valueOf(i));
        return str == null ? String.valueOf(i) : str;
    }

    public String mapUid(int i) {
        String str = this.mAppIdToPackageMap.get(Integer.valueOf(UserHandle.getAppId(i)));
        String uid = UserHandle.formatUid(i);
        return str == null ? uid : str + '/' + uid;
    }
}
