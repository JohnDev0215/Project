package com.byd.overseaappstore.module.ability.data.upper;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/upper/PermissionDetail;", "", "manifestName", "", "(Ljava/lang/String;)V", "getManifestName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class PermissionDetail {

    @NotNull
    private final String manifestName;

    public PermissionDetail(@NotNull String manifestName) {
        Intrinsics.checkNotNullParameter(manifestName, "manifestName");
        this.manifestName = manifestName;
    }

    public static /* synthetic */ PermissionDetail copy$default(PermissionDetail permissionDetail, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = permissionDetail.manifestName;
        }
        return permissionDetail.copy(str);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getManifestName() {
        return this.manifestName;
    }

    @NotNull
    public final PermissionDetail copy(@NotNull String manifestName) {
        Intrinsics.checkNotNullParameter(manifestName, "manifestName");
        return new PermissionDetail(manifestName);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PermissionDetail) && Intrinsics.areEqual(this.manifestName, ((PermissionDetail) other).manifestName);
    }

    @NotNull
    public final String getManifestName() {
        return this.manifestName;
    }

    public int hashCode() {
        return this.manifestName.hashCode();
    }

    @NotNull
    public String toString() {
        return e.d("PermissionDetail(manifestName=", this.manifestName, ")");
    }
}
