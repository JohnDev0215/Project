package com.android.permissioncontroller.permission.model.livedatatypes;

import android.app.Application;
import android.content.pm.PackageItemInfo;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import com.android.launcher3.icons.cache.BaseIconCache;
import com.android.permissioncontroller.permission.utils.Utils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LightPermGroupInfo.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B5\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\fHÆ\u0003J\t\u0010\u001d\u001a\u00020\fHÆ\u0003J\t\u0010\u001e\u001a\u00020\fHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0010HÆ\u0003JE\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u0010HÆ\u0001J\u0013\u0010!\u001a\u00020\u00102\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\fHÖ\u0001J\u0010\u0010$\u001a\u0004\u0018\u00010\u00032\u0006\u0010%\u001a\u00020&J\t\u0010'\u001a\u00020\tHÖ\u0001R\u0011\u0010\u000e\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018¨\u0006("}, d2 = {"Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermGroupInfo;", "", "pII", "Landroid/content/pm/PackageItemInfo;", "(Landroid/content/pm/PackageItemInfo;)V", "pGI", "Landroid/content/pm/PermissionGroupInfo;", "(Landroid/content/pm/PermissionGroupInfo;)V", "name", "", "packageName", "labelRes", "", BaseIconCache.IconDB.COLUMN_ICON, "descriptionRes", "isSinglePermGroup", "", "(Ljava/lang/String;Ljava/lang/String;IIIZ)V", "getDescriptionRes", "()I", "getIcon", "()Z", "getLabelRes", "getName", "()Ljava/lang/String;", "getPackageName", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toPackageItemInfo", "app", "Landroid/app/Application;", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final /* data */ class LightPermGroupInfo {
    private final int descriptionRes;
    private final int icon;
    private final boolean isSinglePermGroup;
    private final int labelRes;
    private final String name;
    private final String packageName;

    public static /* synthetic */ LightPermGroupInfo copy$default(LightPermGroupInfo lightPermGroupInfo, String str, String str2, int i, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = lightPermGroupInfo.name;
        }
        if ((i4 & 2) != 0) {
            str2 = lightPermGroupInfo.packageName;
        }
        String str3 = str2;
        if ((i4 & 4) != 0) {
            i = lightPermGroupInfo.labelRes;
        }
        int i5 = i;
        if ((i4 & 8) != 0) {
            i2 = lightPermGroupInfo.icon;
        }
        int i6 = i2;
        if ((i4 & 16) != 0) {
            i3 = lightPermGroupInfo.descriptionRes;
        }
        int i7 = i3;
        if ((i4 & 32) != 0) {
            z = lightPermGroupInfo.isSinglePermGroup;
        }
        return lightPermGroupInfo.copy(str, str3, i5, i6, i7, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getLabelRes() {
        return this.labelRes;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getDescriptionRes() {
        return this.descriptionRes;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsSinglePermGroup() {
        return this.isSinglePermGroup;
    }

    public final LightPermGroupInfo copy(String name, String packageName, int labelRes, int icon, int descriptionRes, boolean isSinglePermGroup) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        return new LightPermGroupInfo(name, packageName, labelRes, icon, descriptionRes, isSinglePermGroup);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LightPermGroupInfo)) {
            return false;
        }
        LightPermGroupInfo lightPermGroupInfo = (LightPermGroupInfo) other;
        return Intrinsics.areEqual(this.name, lightPermGroupInfo.name) && Intrinsics.areEqual(this.packageName, lightPermGroupInfo.packageName) && this.labelRes == lightPermGroupInfo.labelRes && this.icon == lightPermGroupInfo.icon && this.descriptionRes == lightPermGroupInfo.descriptionRes && this.isSinglePermGroup == lightPermGroupInfo.isSinglePermGroup;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v9, types: [int] */
    public int hashCode() {
        String str = this.name;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.packageName;
        int iHashCode2 = (((((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.labelRes)) * 31) + Integer.hashCode(this.icon)) * 31) + Integer.hashCode(this.descriptionRes)) * 31;
        boolean z = this.isSinglePermGroup;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode2 + r1;
    }

    public String toString() {
        return "LightPermGroupInfo(name=" + this.name + ", packageName=" + this.packageName + ", labelRes=" + this.labelRes + ", icon=" + this.icon + ", descriptionRes=" + this.descriptionRes + ", isSinglePermGroup=" + this.isSinglePermGroup + ")";
    }

    public LightPermGroupInfo(String name, String packageName, int labelRes, int icon, int descriptionRes, boolean isSinglePermGroup) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        this.name = name;
        this.packageName = packageName;
        this.labelRes = labelRes;
        this.icon = icon;
        this.descriptionRes = descriptionRes;
        this.isSinglePermGroup = isSinglePermGroup;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final int getLabelRes() {
        return this.labelRes;
    }

    public final int getIcon() {
        return this.icon;
    }

    public final int getDescriptionRes() {
        return this.descriptionRes;
    }

    public final boolean isSinglePermGroup() {
        return this.isSinglePermGroup;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public LightPermGroupInfo(PackageItemInfo pII) {
        Intrinsics.checkParameterIsNotNull(pII, "pII");
        String str = pII.name;
        Intrinsics.checkExpressionValueIsNotNull(str, "pII.name");
        String str2 = pII.packageName;
        Intrinsics.checkExpressionValueIsNotNull(str2, "pII.packageName");
        this(str, str2, pII.labelRes, pII.icon, 0, pII instanceof PermissionInfo);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public LightPermGroupInfo(PermissionGroupInfo pGI) {
        Intrinsics.checkParameterIsNotNull(pGI, "pGI");
        String str = pGI.name;
        Intrinsics.checkExpressionValueIsNotNull(str, "pGI.name");
        String str2 = pGI.packageName;
        Intrinsics.checkExpressionValueIsNotNull(str2, "pGI.packageName");
        this(str, str2, pGI.labelRes, pGI.icon, pGI.descriptionRes, pGI instanceof PermissionInfo);
    }

    public final PackageItemInfo toPackageItemInfo(Application app) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        return Utils.getGroupInfo(this.name, app.getApplicationContext());
    }
}
