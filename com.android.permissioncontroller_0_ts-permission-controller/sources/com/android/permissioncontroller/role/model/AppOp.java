package com.android.permissioncontroller.role.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.android.permissioncontroller.role.utils.PackageUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class AppOp {
    private final Integer mMaxTargetSdkVersion;
    private final int mMode;
    private final String mName;

    public AppOp(String name, Integer maxTargetSdkVersion, int mode) {
        this.mName = name;
        this.mMaxTargetSdkVersion = maxTargetSdkVersion;
        this.mMode = mode;
    }

    public String getName() {
        return this.mName;
    }

    public Integer getMaxTargetSdkVersion() {
        return this.mMaxTargetSdkVersion;
    }

    public int getMode() {
        return this.mMode;
    }

    public boolean grant(String packageName, Context context) {
        if (!checkTargetSdkVersion(packageName, context)) {
            return false;
        }
        return Permissions.setAppOpUidMode(packageName, this.mName, this.mMode, context);
    }

    public boolean revoke(String packageName, Context context) {
        if (!checkTargetSdkVersion(packageName, context)) {
            return false;
        }
        int defaultMode = Permissions.getDefaultAppOpMode(this.mName);
        return Permissions.setAppOpUidMode(packageName, this.mName, defaultMode, context);
    }

    private boolean checkTargetSdkVersion(String packageName, Context context) {
        if (this.mMaxTargetSdkVersion == null) {
            return true;
        }
        ApplicationInfo applicationInfo = PackageUtils.getApplicationInfo(packageName, context);
        return applicationInfo != null && applicationInfo.targetSdkVersion <= this.mMaxTargetSdkVersion.intValue();
    }

    public String toString() {
        return "AppOp{mName='" + this.mName + "', mMaxTargetSdkVersion=" + this.mMaxTargetSdkVersion + ", mMode=" + this.mMode + '}';
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AppOp appOp = (AppOp) object;
        if (this.mMode == appOp.mMode && Objects.equals(this.mName, appOp.mName) && Objects.equals(this.mMaxTargetSdkVersion, appOp.mMaxTargetSdkVersion)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.mName, this.mMaxTargetSdkVersion, Integer.valueOf(this.mMode));
    }
}
