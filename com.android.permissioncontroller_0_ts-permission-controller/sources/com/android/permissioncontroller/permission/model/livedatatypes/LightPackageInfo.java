package com.android.permissioncontroller.permission.model.livedatatypes;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.UserHandle;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LightPackageInfo.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004Bg\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\b\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0012\u001a\u00020\f\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\t\u0010&\u001a\u00020\u0006HÆ\u0003J\t\u0010'\u001a\u00020\u0014HÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00060\bHÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\f0\bHÆ\u0003J\t\u0010+\u001a\u00020\fHÆ\u0003J\t\u0010,\u001a\u00020\fHÆ\u0003J\t\u0010-\u001a\u00020\u0010HÆ\u0003J\t\u0010.\u001a\u00020\u0010HÆ\u0003J\t\u0010/\u001a\u00020\fHÆ\u0003J\u007f\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\u0014HÆ\u0001J\u0013\u00101\u001a\u00020\u00102\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0010\u00103\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u000206J\t\u00107\u001a\u00020\fHÖ\u0001J\u0010\u00108\u001a\u0004\u0018\u00010\u00032\u0006\u00105\u001a\u000206J\t\u00109\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0012\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\b8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0011\u0010\u000e\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0017R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017¨\u0006:"}, d2 = {"Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "", "pI", "Landroid/content/pm/PackageInfo;", "(Landroid/content/pm/PackageInfo;)V", "packageName", "", "permissions", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermInfo;", "requestedPermissions", "requestedPermissionsFlags", "", "uid", "targetSdkVersion", "isInstantApp", "", "enabled", "appFlags", "firstInstallTime", "", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;IIZZIJ)V", "getAppFlags", "()I", "getEnabled", "()Z", "getFirstInstallTime", "()J", "grantedPermissions", "getGrantedPermissions", "()Ljava/util/List;", "getPackageName", "()Ljava/lang/String;", "getPermissions", "getRequestedPermissions", "getRequestedPermissionsFlags", "getTargetSdkVersion", "getUid", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "getApplicationInfo", "Landroid/content/pm/ApplicationInfo;", "app", "Landroid/app/Application;", "hashCode", "toPackageInfo", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final /* data */ class LightPackageInfo {
    private final int appFlags;
    private final boolean enabled;
    private final long firstInstallTime;
    private final boolean isInstantApp;
    private final String packageName;
    private final List<LightPermInfo> permissions;
    private final List<String> requestedPermissions;
    private final List<Integer> requestedPermissionsFlags;
    private final int targetSdkVersion;
    private final int uid;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getFirstInstallTime() {
        return this.firstInstallTime;
    }

    public final List<LightPermInfo> component2() {
        return this.permissions;
    }

    public final List<String> component3() {
        return this.requestedPermissions;
    }

    public final List<Integer> component4() {
        return this.requestedPermissionsFlags;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getTargetSdkVersion() {
        return this.targetSdkVersion;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getIsInstantApp() {
        return this.isInstantApp;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getAppFlags() {
        return this.appFlags;
    }

    public final LightPackageInfo copy(String packageName, List<LightPermInfo> permissions, List<String> requestedPermissions, List<Integer> requestedPermissionsFlags, int uid, int targetSdkVersion, boolean isInstantApp, boolean enabled, int appFlags, long firstInstallTime) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(permissions, "permissions");
        Intrinsics.checkParameterIsNotNull(requestedPermissions, "requestedPermissions");
        Intrinsics.checkParameterIsNotNull(requestedPermissionsFlags, "requestedPermissionsFlags");
        return new LightPackageInfo(packageName, permissions, requestedPermissions, requestedPermissionsFlags, uid, targetSdkVersion, isInstantApp, enabled, appFlags, firstInstallTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LightPackageInfo)) {
            return false;
        }
        LightPackageInfo lightPackageInfo = (LightPackageInfo) other;
        return Intrinsics.areEqual(this.packageName, lightPackageInfo.packageName) && Intrinsics.areEqual(this.permissions, lightPackageInfo.permissions) && Intrinsics.areEqual(this.requestedPermissions, lightPackageInfo.requestedPermissions) && Intrinsics.areEqual(this.requestedPermissionsFlags, lightPackageInfo.requestedPermissionsFlags) && this.uid == lightPackageInfo.uid && this.targetSdkVersion == lightPackageInfo.targetSdkVersion && this.isInstantApp == lightPackageInfo.isInstantApp && this.enabled == lightPackageInfo.enabled && this.appFlags == lightPackageInfo.appFlags && this.firstInstallTime == lightPackageInfo.firstInstallTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v7, types: [int] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8, types: [int] */
    /* JADX WARN: Type inference failed for: r2v9 */
    public int hashCode() {
        String str = this.packageName;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        List<LightPermInfo> list = this.permissions;
        int iHashCode2 = (iHashCode + (list != null ? list.hashCode() : 0)) * 31;
        List<String> list2 = this.requestedPermissions;
        int iHashCode3 = (iHashCode2 + (list2 != null ? list2.hashCode() : 0)) * 31;
        List<Integer> list3 = this.requestedPermissionsFlags;
        int iHashCode4 = (((((iHashCode3 + (list3 != null ? list3.hashCode() : 0)) * 31) + Integer.hashCode(this.uid)) * 31) + Integer.hashCode(this.targetSdkVersion)) * 31;
        boolean z = this.isInstantApp;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode4 + r1) * 31;
        boolean z2 = this.enabled;
        return ((((i + (z2 ? 1 : z2)) * 31) + Integer.hashCode(this.appFlags)) * 31) + Long.hashCode(this.firstInstallTime);
    }

    public String toString() {
        return "LightPackageInfo(packageName=" + this.packageName + ", permissions=" + this.permissions + ", requestedPermissions=" + this.requestedPermissions + ", requestedPermissionsFlags=" + this.requestedPermissionsFlags + ", uid=" + this.uid + ", targetSdkVersion=" + this.targetSdkVersion + ", isInstantApp=" + this.isInstantApp + ", enabled=" + this.enabled + ", appFlags=" + this.appFlags + ", firstInstallTime=" + this.firstInstallTime + ")";
    }

    public LightPackageInfo(String packageName, List<LightPermInfo> permissions, List<String> requestedPermissions, List<Integer> requestedPermissionsFlags, int uid, int targetSdkVersion, boolean isInstantApp, boolean enabled, int appFlags, long firstInstallTime) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(permissions, "permissions");
        Intrinsics.checkParameterIsNotNull(requestedPermissions, "requestedPermissions");
        Intrinsics.checkParameterIsNotNull(requestedPermissionsFlags, "requestedPermissionsFlags");
        this.packageName = packageName;
        this.permissions = permissions;
        this.requestedPermissions = requestedPermissions;
        this.requestedPermissionsFlags = requestedPermissionsFlags;
        this.uid = uid;
        this.targetSdkVersion = targetSdkVersion;
        this.isInstantApp = isInstantApp;
        this.enabled = enabled;
        this.appFlags = appFlags;
        this.firstInstallTime = firstInstallTime;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final List<LightPermInfo> getPermissions() {
        return this.permissions;
    }

    public final List<String> getRequestedPermissions() {
        return this.requestedPermissions;
    }

    public final List<Integer> getRequestedPermissionsFlags() {
        return this.requestedPermissionsFlags;
    }

    public final int getUid() {
        return this.uid;
    }

    public final int getTargetSdkVersion() {
        return this.targetSdkVersion;
    }

    public final boolean isInstantApp() {
        return this.isInstantApp;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final int getAppFlags() {
        return this.appFlags;
    }

    public final long getFirstInstallTime() {
        return this.firstInstallTime;
    }

    public LightPackageInfo(PackageInfo pI) {
        List listEmptyList;
        List<Integer> list;
        List list2;
        Intrinsics.checkParameterIsNotNull(pI, "pI");
        String str = pI.packageName;
        Intrinsics.checkExpressionValueIsNotNull(str, "pI.packageName");
        PermissionInfo[] permissionInfoArr = pI.permissions;
        if (permissionInfoArr == null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            Collection destination$iv$iv = new ArrayList(permissionInfoArr.length);
            for (PermissionInfo perm : permissionInfoArr) {
                Intrinsics.checkExpressionValueIsNotNull(perm, "perm");
                destination$iv$iv.add(new LightPermInfo(perm));
            }
            listEmptyList = (List) destination$iv$iv;
        }
        String[] strArr = pI.requestedPermissions;
        List listEmptyList2 = (strArr == null || (list2 = ArraysKt.toList(strArr)) == null) ? CollectionsKt.emptyList() : list2;
        int[] iArr = pI.requestedPermissionsFlags;
        List<Integer> listEmptyList3 = (iArr == null || (list = ArraysKt.toList(iArr)) == null) ? CollectionsKt.emptyList() : list;
        int i = pI.applicationInfo.uid;
        int i2 = pI.applicationInfo.targetSdkVersion;
        ApplicationInfo applicationInfo = pI.applicationInfo;
        Intrinsics.checkExpressionValueIsNotNull(applicationInfo, "pI.applicationInfo");
        this(str, listEmptyList, listEmptyList2, listEmptyList3, i, i2, applicationInfo.isInstantApp(), pI.applicationInfo.enabled, pI.applicationInfo.flags, pI.firstInstallTime);
    }

    public final List<String> getGrantedPermissions() {
        List grantedPermissions = new ArrayList();
        int size = this.requestedPermissions.size();
        for (int i = 0; i < size; i++) {
            if ((this.requestedPermissionsFlags.get(i).intValue() & 2) != 0) {
                grantedPermissions.add(this.requestedPermissions.get(i));
            }
        }
        return grantedPermissions;
    }

    public final ApplicationInfo getApplicationInfo(Application app) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        try {
            Context userContext = Utils.getUserContext(app, UserHandle.getUserHandleForUid(this.uid));
            Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app…getUserHandleForUid(uid))");
            return userContext.getPackageManager().getApplicationInfo(this.packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public final PackageInfo toPackageInfo(Application app) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        try {
            Context userContext = Utils.getUserContext(app, UserHandle.getUserHandleForUid(this.uid));
            Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app…getUserHandleForUid(uid))");
            return userContext.getPackageManager().getPackageInfo(this.packageName, 4096);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }
}
