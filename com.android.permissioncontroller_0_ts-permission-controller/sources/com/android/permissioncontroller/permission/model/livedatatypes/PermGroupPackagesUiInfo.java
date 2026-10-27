package com.android.permissioncontroller.permission.model.livedatatypes;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PermGroupPackagesUiInfo.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/android/permissioncontroller/permission/model/livedatatypes/PermGroupPackagesUiInfo;", "", "name", "", "nonSystemTotal", "", "nonSystemGranted", "(Ljava/lang/String;II)V", "getName", "()Ljava/lang/String;", "getNonSystemGranted", "()I", "getNonSystemTotal", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final /* data */ class PermGroupPackagesUiInfo {
    private final String name;
    private final int nonSystemGranted;
    private final int nonSystemTotal;

    public static /* synthetic */ PermGroupPackagesUiInfo copy$default(PermGroupPackagesUiInfo permGroupPackagesUiInfo, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = permGroupPackagesUiInfo.name;
        }
        if ((i3 & 2) != 0) {
            i = permGroupPackagesUiInfo.nonSystemTotal;
        }
        if ((i3 & 4) != 0) {
            i2 = permGroupPackagesUiInfo.nonSystemGranted;
        }
        return permGroupPackagesUiInfo.copy(str, i, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getNonSystemTotal() {
        return this.nonSystemTotal;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getNonSystemGranted() {
        return this.nonSystemGranted;
    }

    public final PermGroupPackagesUiInfo copy(String name, int nonSystemTotal, int nonSystemGranted) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new PermGroupPackagesUiInfo(name, nonSystemTotal, nonSystemGranted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PermGroupPackagesUiInfo)) {
            return false;
        }
        PermGroupPackagesUiInfo permGroupPackagesUiInfo = (PermGroupPackagesUiInfo) other;
        return Intrinsics.areEqual(this.name, permGroupPackagesUiInfo.name) && this.nonSystemTotal == permGroupPackagesUiInfo.nonSystemTotal && this.nonSystemGranted == permGroupPackagesUiInfo.nonSystemGranted;
    }

    public int hashCode() {
        String str = this.name;
        return ((((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.nonSystemTotal)) * 31) + Integer.hashCode(this.nonSystemGranted);
    }

    public String toString() {
        return "PermGroupPackagesUiInfo(name=" + this.name + ", nonSystemTotal=" + this.nonSystemTotal + ", nonSystemGranted=" + this.nonSystemGranted + ")";
    }

    public PermGroupPackagesUiInfo(String name, int nonSystemTotal, int nonSystemGranted) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.nonSystemTotal = nonSystemTotal;
        this.nonSystemGranted = nonSystemGranted;
    }

    public final String getName() {
        return this.name;
    }

    public final int getNonSystemTotal() {
        return this.nonSystemTotal;
    }

    public final int getNonSystemGranted() {
        return this.nonSystemGranted;
    }
}
