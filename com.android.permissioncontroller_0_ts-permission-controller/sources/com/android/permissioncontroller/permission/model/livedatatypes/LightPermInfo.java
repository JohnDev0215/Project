package com.android.permissioncontroller.permission.model.livedatatypes;

import android.app.Application;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LightPermInfo.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004BA\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b¢\u0006\u0002\u0010\u000eJ\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u000bHÆ\u0003J\t\u0010\u001d\u001a\u00020\u000bHÆ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÆ\u0003JS\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000bHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u000bHÖ\u0001J\u0010\u0010$\u001a\u0004\u0018\u00010\u00032\u0006\u0010%\u001a\u00020&J\t\u0010'\u001a\u00020\u0006HÖ\u0001R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\r\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012¨\u0006("}, d2 = {"Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermInfo;", "", "permInfo", "Landroid/content/pm/PermissionInfo;", "(Landroid/content/pm/PermissionInfo;)V", "name", "", "packageName", "group", "backgroundPermission", "protection", "", "protectionFlags", "flags", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;III)V", "getBackgroundPermission", "()Ljava/lang/String;", "getFlags", "()I", "getGroup", "getName", "getPackageName", "getProtection", "getProtectionFlags", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toPermissionInfo", "app", "Landroid/app/Application;", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final /* data */ class LightPermInfo {
    private final String backgroundPermission;
    private final int flags;
    private final String group;
    private final String name;
    private final String packageName;
    private final int protection;
    private final int protectionFlags;

    public static /* synthetic */ LightPermInfo copy$default(LightPermInfo lightPermInfo, String str, String str2, String str3, String str4, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = lightPermInfo.name;
        }
        if ((i4 & 2) != 0) {
            str2 = lightPermInfo.packageName;
        }
        String str5 = str2;
        if ((i4 & 4) != 0) {
            str3 = lightPermInfo.group;
        }
        String str6 = str3;
        if ((i4 & 8) != 0) {
            str4 = lightPermInfo.backgroundPermission;
        }
        String str7 = str4;
        if ((i4 & 16) != 0) {
            i = lightPermInfo.protection;
        }
        int i5 = i;
        if ((i4 & 32) != 0) {
            i2 = lightPermInfo.protectionFlags;
        }
        int i6 = i2;
        if ((i4 & 64) != 0) {
            i3 = lightPermInfo.flags;
        }
        return lightPermInfo.copy(str, str5, str6, str7, i5, i6, i3);
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
    public final String getGroup() {
        return this.group;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getBackgroundPermission() {
        return this.backgroundPermission;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getProtection() {
        return this.protection;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getProtectionFlags() {
        return this.protectionFlags;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getFlags() {
        return this.flags;
    }

    public final LightPermInfo copy(String name, String packageName, String group, String backgroundPermission, int protection, int protectionFlags, int flags) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        return new LightPermInfo(name, packageName, group, backgroundPermission, protection, protectionFlags, flags);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LightPermInfo)) {
            return false;
        }
        LightPermInfo lightPermInfo = (LightPermInfo) other;
        return Intrinsics.areEqual(this.name, lightPermInfo.name) && Intrinsics.areEqual(this.packageName, lightPermInfo.packageName) && Intrinsics.areEqual(this.group, lightPermInfo.group) && Intrinsics.areEqual(this.backgroundPermission, lightPermInfo.backgroundPermission) && this.protection == lightPermInfo.protection && this.protectionFlags == lightPermInfo.protectionFlags && this.flags == lightPermInfo.flags;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.packageName;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.group;
        int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.backgroundPermission;
        return ((((((iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + Integer.hashCode(this.protection)) * 31) + Integer.hashCode(this.protectionFlags)) * 31) + Integer.hashCode(this.flags);
    }

    public String toString() {
        return "LightPermInfo(name=" + this.name + ", packageName=" + this.packageName + ", group=" + this.group + ", backgroundPermission=" + this.backgroundPermission + ", protection=" + this.protection + ", protectionFlags=" + this.protectionFlags + ", flags=" + this.flags + ")";
    }

    public LightPermInfo(String name, String packageName, String group, String backgroundPermission, int protection, int protectionFlags, int flags) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        this.name = name;
        this.packageName = packageName;
        this.group = group;
        this.backgroundPermission = backgroundPermission;
        this.protection = protection;
        this.protectionFlags = protectionFlags;
        this.flags = flags;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getGroup() {
        return this.group;
    }

    public final String getBackgroundPermission() {
        return this.backgroundPermission;
    }

    public final int getProtection() {
        return this.protection;
    }

    public final int getProtectionFlags() {
        return this.protectionFlags;
    }

    public final int getFlags() {
        return this.flags;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public LightPermInfo(PermissionInfo permInfo) {
        Intrinsics.checkParameterIsNotNull(permInfo, "permInfo");
        String str = permInfo.name;
        Intrinsics.checkExpressionValueIsNotNull(str, "permInfo.name");
        String str2 = permInfo.packageName;
        Intrinsics.checkExpressionValueIsNotNull(str2, "permInfo.packageName");
        this(str, str2, permInfo.group, permInfo.backgroundPermission, permInfo.getProtection(), permInfo.getProtectionFlags(), permInfo.flags);
    }

    public final PermissionInfo toPermissionInfo(Application app) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        try {
            return app.getPackageManager().getPermissionInfo(this.name, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }
}
