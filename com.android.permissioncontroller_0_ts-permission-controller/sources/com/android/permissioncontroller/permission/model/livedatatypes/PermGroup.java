package com.android.permissioncontroller.permission.model.livedatatypes;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PermGroup.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J)\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001e"}, d2 = {"Lcom/android/permissioncontroller/permission/model/livedatatypes/PermGroup;", "", "groupInfo", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermGroupInfo;", "permissionInfos", "", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermInfo;", "(Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermGroupInfo;Ljava/util/Map;)V", "getGroupInfo", "()Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermGroupInfo;", "setGroupInfo", "(Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermGroupInfo;)V", "hasRuntimePermissions", "", "getHasRuntimePermissions", "()Z", "name", "getName", "()Ljava/lang/String;", "getPermissionInfos", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final /* data */ class PermGroup {
    private LightPermGroupInfo groupInfo;
    private final boolean hasRuntimePermissions;
    private final String name;
    private final Map<String, LightPermInfo> permissionInfos;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PermGroup copy$default(PermGroup permGroup, LightPermGroupInfo lightPermGroupInfo, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            lightPermGroupInfo = permGroup.groupInfo;
        }
        if ((i & 2) != 0) {
            map = permGroup.permissionInfos;
        }
        return permGroup.copy(lightPermGroupInfo, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final LightPermGroupInfo getGroupInfo() {
        return this.groupInfo;
    }

    public final Map<String, LightPermInfo> component2() {
        return this.permissionInfos;
    }

    public final PermGroup copy(LightPermGroupInfo groupInfo, Map<String, LightPermInfo> permissionInfos) {
        Intrinsics.checkParameterIsNotNull(groupInfo, "groupInfo");
        Intrinsics.checkParameterIsNotNull(permissionInfos, "permissionInfos");
        return new PermGroup(groupInfo, permissionInfos);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PermGroup)) {
            return false;
        }
        PermGroup permGroup = (PermGroup) other;
        return Intrinsics.areEqual(this.groupInfo, permGroup.groupInfo) && Intrinsics.areEqual(this.permissionInfos, permGroup.permissionInfos);
    }

    public int hashCode() {
        LightPermGroupInfo lightPermGroupInfo = this.groupInfo;
        int iHashCode = (lightPermGroupInfo != null ? lightPermGroupInfo.hashCode() : 0) * 31;
        Map<String, LightPermInfo> map = this.permissionInfos;
        return iHashCode + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "PermGroup(groupInfo=" + this.groupInfo + ", permissionInfos=" + this.permissionInfos + ")";
    }

    public PermGroup(LightPermGroupInfo groupInfo, Map<String, LightPermInfo> permissionInfos) {
        Intrinsics.checkParameterIsNotNull(groupInfo, "groupInfo");
        Intrinsics.checkParameterIsNotNull(permissionInfos, "permissionInfos");
        this.groupInfo = groupInfo;
        this.permissionInfos = permissionInfos;
        this.name = groupInfo.getName();
        this.hasRuntimePermissions = !this.permissionInfos.isEmpty();
    }

    public final LightPermGroupInfo getGroupInfo() {
        return this.groupInfo;
    }

    public final void setGroupInfo(LightPermGroupInfo lightPermGroupInfo) {
        Intrinsics.checkParameterIsNotNull(lightPermGroupInfo, "<set-?>");
        this.groupInfo = lightPermGroupInfo;
    }

    public final Map<String, LightPermInfo> getPermissionInfos() {
        return this.permissionInfos;
    }

    public final String getName() {
        return this.name;
    }

    public final boolean getHasRuntimePermissions() {
        return this.hasRuntimePermissions;
    }
}
