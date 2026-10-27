package com.android.permissioncontroller.permission.model.livedatatypes;

import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UidSensitivityState.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\u0010\tJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0015\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006HÆ\u0003J/\u0010\u0010\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\bHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0007HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/android/permissioncontroller/permission/model/livedatatypes/UidSensitivityState;", "", "packages", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "permStates", "", "", "", "(Ljava/util/Set;Ljava/util/Map;)V", "getPackages", "()Ljava/util/Set;", "getPermStates", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final /* data */ class UidSensitivityState {
    private final Set<LightPackageInfo> packages;
    private final Map<String, Integer> permStates;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ UidSensitivityState copy$default(UidSensitivityState uidSensitivityState, Set set, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            set = uidSensitivityState.packages;
        }
        if ((i & 2) != 0) {
            map = uidSensitivityState.permStates;
        }
        return uidSensitivityState.copy(set, map);
    }

    public final Set<LightPackageInfo> component1() {
        return this.packages;
    }

    public final Map<String, Integer> component2() {
        return this.permStates;
    }

    public final UidSensitivityState copy(Set<LightPackageInfo> packages, Map<String, Integer> permStates) {
        Intrinsics.checkParameterIsNotNull(packages, "packages");
        Intrinsics.checkParameterIsNotNull(permStates, "permStates");
        return new UidSensitivityState(packages, permStates);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UidSensitivityState)) {
            return false;
        }
        UidSensitivityState uidSensitivityState = (UidSensitivityState) other;
        return Intrinsics.areEqual(this.packages, uidSensitivityState.packages) && Intrinsics.areEqual(this.permStates, uidSensitivityState.permStates);
    }

    public int hashCode() {
        Set<LightPackageInfo> set = this.packages;
        int iHashCode = (set != null ? set.hashCode() : 0) * 31;
        Map<String, Integer> map = this.permStates;
        return iHashCode + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "UidSensitivityState(packages=" + this.packages + ", permStates=" + this.permStates + ")";
    }

    public UidSensitivityState(Set<LightPackageInfo> packages, Map<String, Integer> permStates) {
        Intrinsics.checkParameterIsNotNull(packages, "packages");
        Intrinsics.checkParameterIsNotNull(permStates, "permStates");
        this.packages = packages;
        this.permStates = permStates;
    }

    public final Set<LightPackageInfo> getPackages() {
        return this.packages;
    }

    public final Map<String, Integer> getPermStates() {
        return this.permStates;
    }
}
