package com.android.permissioncontroller.permission.model.livedatatypes;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppPermGroupUiInfo.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0016B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\b¨\u0006\u0017"}, d2 = {"Lcom/android/permissioncontroller/permission/model/livedatatypes/AppPermGroupUiInfo;", "", "shouldShow", "", "permGrantState", "Lcom/android/permissioncontroller/permission/model/livedatatypes/AppPermGroupUiInfo$PermGrantState;", "isSystem", "(ZLcom/android/permissioncontroller/permission/model/livedatatypes/AppPermGroupUiInfo$PermGrantState;Z)V", "()Z", "getPermGrantState", "()Lcom/android/permissioncontroller/permission/model/livedatatypes/AppPermGroupUiInfo$PermGrantState;", "getShouldShow", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "PermGrantState", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final /* data */ class AppPermGroupUiInfo {
    private final boolean isSystem;
    private final PermGrantState permGrantState;
    private final boolean shouldShow;

    public static /* synthetic */ AppPermGroupUiInfo copy$default(AppPermGroupUiInfo appPermGroupUiInfo, boolean z, PermGrantState permGrantState, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = appPermGroupUiInfo.shouldShow;
        }
        if ((i & 2) != 0) {
            permGrantState = appPermGroupUiInfo.permGrantState;
        }
        if ((i & 4) != 0) {
            z2 = appPermGroupUiInfo.isSystem;
        }
        return appPermGroupUiInfo.copy(z, permGrantState, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getShouldShow() {
        return this.shouldShow;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PermGrantState getPermGrantState() {
        return this.permGrantState;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsSystem() {
        return this.isSystem;
    }

    public final AppPermGroupUiInfo copy(boolean shouldShow, PermGrantState permGrantState, boolean isSystem) {
        Intrinsics.checkParameterIsNotNull(permGrantState, "permGrantState");
        return new AppPermGroupUiInfo(shouldShow, permGrantState, isSystem);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppPermGroupUiInfo)) {
            return false;
        }
        AppPermGroupUiInfo appPermGroupUiInfo = (AppPermGroupUiInfo) other;
        return this.shouldShow == appPermGroupUiInfo.shouldShow && Intrinsics.areEqual(this.permGrantState, appPermGroupUiInfo.permGrantState) && this.isSystem == appPermGroupUiInfo.isSystem;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    public int hashCode() {
        boolean z = this.shouldShow;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        PermGrantState permGrantState = this.permGrantState;
        int iHashCode = (i + (permGrantState != null ? permGrantState.hashCode() : 0)) * 31;
        boolean z2 = this.isSystem;
        return iHashCode + (z2 ? 1 : z2);
    }

    public String toString() {
        return "AppPermGroupUiInfo(shouldShow=" + this.shouldShow + ", permGrantState=" + this.permGrantState + ", isSystem=" + this.isSystem + ")";
    }

    public AppPermGroupUiInfo(boolean shouldShow, PermGrantState permGrantState, boolean isSystem) {
        Intrinsics.checkParameterIsNotNull(permGrantState, "permGrantState");
        this.shouldShow = shouldShow;
        this.permGrantState = permGrantState;
        this.isSystem = isSystem;
    }

    public final boolean getShouldShow() {
        return this.shouldShow;
    }

    public final PermGrantState getPermGrantState() {
        return this.permGrantState;
    }

    public final boolean isSystem() {
        return this.isSystem;
    }

    /* JADX INFO: compiled from: AppPermGroupUiInfo.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/android/permissioncontroller/permission/model/livedatatypes/AppPermGroupUiInfo$PermGrantState;", "", "grantState", "", "(Ljava/lang/String;II)V", "PERMS_DENIED", "PERMS_ALLOWED", "PERMS_ALLOWED_FOREGROUND_ONLY", "PERMS_ALLOWED_ALWAYS", "PERMS_ASK", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public enum PermGrantState {
        PERMS_DENIED(0),
        PERMS_ALLOWED(1),
        PERMS_ALLOWED_FOREGROUND_ONLY(2),
        PERMS_ALLOWED_ALWAYS(3),
        PERMS_ASK(4);

        private final int grantState;

        PermGrantState(int grantState) {
            this.grantState = grantState;
        }
    }
}
