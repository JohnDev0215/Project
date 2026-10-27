package com.android.permissioncontroller.permission.model.livedatatypes;

import kotlin.Metadata;

/* JADX INFO: compiled from: PermState.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/android/permissioncontroller/permission/model/livedatatypes/PermState;", "", "permFlags", "", "granted", "", "(IZ)V", "getGranted", "()Z", "getPermFlags", "()I", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final /* data */ class PermState {
    private final boolean granted;
    private final int permFlags;

    public static /* synthetic */ PermState copy$default(PermState permState, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = permState.permFlags;
        }
        if ((i2 & 2) != 0) {
            z = permState.granted;
        }
        return permState.copy(i, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getPermFlags() {
        return this.permFlags;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getGranted() {
        return this.granted;
    }

    public final PermState copy(int permFlags, boolean granted) {
        return new PermState(permFlags, granted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PermState)) {
            return false;
        }
        PermState permState = (PermState) other;
        return this.permFlags == permState.permFlags && this.granted == permState.granted;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    public int hashCode() {
        int iHashCode = Integer.hashCode(this.permFlags) * 31;
        boolean z = this.granted;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "PermState(permFlags=" + this.permFlags + ", granted=" + this.granted + ")";
    }

    public PermState(int permFlags, boolean granted) {
        this.permFlags = permFlags;
        this.granted = granted;
    }

    public final boolean getGranted() {
        return this.granted;
    }

    public final int getPermFlags() {
        return this.permFlags;
    }
}
