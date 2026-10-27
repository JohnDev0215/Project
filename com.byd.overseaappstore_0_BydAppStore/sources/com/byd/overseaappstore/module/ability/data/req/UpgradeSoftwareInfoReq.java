package com.byd.overseaappstore.module.ability.data.req;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/req/UpgradeSoftwareInfoReq;", "", "package_name", "", "signature", "(Ljava/lang/String;Ljava/lang/String;)V", "getPackage_name", "()Ljava/lang/String;", "getSignature", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class UpgradeSoftwareInfoReq {

    @NotNull
    private final String package_name;

    @NotNull
    private final String signature;

    public UpgradeSoftwareInfoReq(@NotNull String package_name, @NotNull String signature) {
        Intrinsics.checkNotNullParameter(package_name, "package_name");
        Intrinsics.checkNotNullParameter(signature, "signature");
        this.package_name = package_name;
        this.signature = signature;
    }

    public static /* synthetic */ UpgradeSoftwareInfoReq copy$default(UpgradeSoftwareInfoReq upgradeSoftwareInfoReq, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = upgradeSoftwareInfoReq.package_name;
        }
        if ((i & 2) != 0) {
            str2 = upgradeSoftwareInfoReq.signature;
        }
        return upgradeSoftwareInfoReq.copy(str, str2);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPackage_name() {
        return this.package_name;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSignature() {
        return this.signature;
    }

    @NotNull
    public final UpgradeSoftwareInfoReq copy(@NotNull String package_name, @NotNull String signature) {
        Intrinsics.checkNotNullParameter(package_name, "package_name");
        Intrinsics.checkNotNullParameter(signature, "signature");
        return new UpgradeSoftwareInfoReq(package_name, signature);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UpgradeSoftwareInfoReq)) {
            return false;
        }
        UpgradeSoftwareInfoReq upgradeSoftwareInfoReq = (UpgradeSoftwareInfoReq) other;
        return Intrinsics.areEqual(this.package_name, upgradeSoftwareInfoReq.package_name) && Intrinsics.areEqual(this.signature, upgradeSoftwareInfoReq.signature);
    }

    @NotNull
    public final String getPackage_name() {
        return this.package_name;
    }

    @NotNull
    public final String getSignature() {
        return this.signature;
    }

    public int hashCode() {
        return this.signature.hashCode() + (this.package_name.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "UpgradeSoftwareInfoReq(package_name=" + this.package_name + ", signature=" + this.signature + ")";
    }
}
