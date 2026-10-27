package com.android.inputmethod.compat;

import android.content.pm.PackageInfo;

/* JADX INFO: loaded from: classes2.dex */
public class AppWorkaroundsUtils {
    private final boolean mIsBrokenByRecorrection;
    private final PackageInfo mPackageInfo;

    public AppWorkaroundsUtils(final PackageInfo packageInfo) {
        this.mPackageInfo = packageInfo;
        this.mIsBrokenByRecorrection = AppWorkaroundsHelper.evaluateIsBrokenByRecorrection(packageInfo);
    }

    public boolean isBrokenByRecorrection() {
        return this.mIsBrokenByRecorrection;
    }

    public boolean isBeforeJellyBean() {
        PackageInfo packageInfo = this.mPackageInfo;
        return (packageInfo == null || packageInfo.applicationInfo == null || this.mPackageInfo.applicationInfo.targetSdkVersion >= 16) ? false : true;
    }

    public String toString() {
        PackageInfo packageInfo = this.mPackageInfo;
        if (packageInfo == null || packageInfo.applicationInfo == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Target application : ").append(this.mPackageInfo.applicationInfo.name).append("\nPackage : ").append(this.mPackageInfo.applicationInfo.packageName).append("\nTarget app sdk version : ").append(this.mPackageInfo.applicationInfo.targetSdkVersion);
        return sb.toString();
    }
}
