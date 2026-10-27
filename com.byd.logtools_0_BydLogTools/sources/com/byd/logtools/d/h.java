package com.byd.logtools.d;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;
import java.util.List;

/* JADX INFO: compiled from: ToolsUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static h f3173a;

    private h() {
    }

    public static h a() {
        h hVar = f3173a;
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = new h();
        f3173a = hVar2;
        return hVar2;
    }

    public boolean b(Context context, String str) {
        Log.d("ToolsUtil", "isAppInstalled : " + str);
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages == null) {
            return false;
        }
        for (int i = 0; i < installedPackages.size(); i++) {
            Log.d("ToolsUtil", "Package[" + installedPackages.size() + "]:mPackageInfo.");
            String str2 = installedPackages.get(i).packageName;
            if (str2 != null && str2.equals(str)) {
                Log.d("ToolsUtil", "Package[" + str + "]:is installed.");
                return true;
            }
        }
        return false;
    }
}
