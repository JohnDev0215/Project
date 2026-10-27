package com.byd.overseaappstore.module.ability.data.rsp;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J>\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareExtraRsp;", "", "package_name", "", "signature", "ver_code", "", "version", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getPackage_name", "()Ljava/lang/String;", "getSignature", "getVer_code", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getVersion", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareExtraRsp;", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SoftwareExtraRsp {

    @Nullable
    private final String package_name;

    @Nullable
    private final String signature;

    @Nullable
    private final Integer ver_code;

    @Nullable
    private final String version;

    public SoftwareExtraRsp() {
        this(null, null, null, null, 15, null);
    }

    public SoftwareExtraRsp(@Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3) {
        this.package_name = str;
        this.signature = str2;
        this.ver_code = num;
        this.version = str3;
    }

    public /* synthetic */ SoftwareExtraRsp(String str, String str2, Integer num, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? 0 : num, (i & 8) != 0 ? "" : str3);
    }

    public static /* synthetic */ SoftwareExtraRsp copy$default(SoftwareExtraRsp softwareExtraRsp, String str, String str2, Integer num, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = softwareExtraRsp.package_name;
        }
        if ((i & 2) != 0) {
            str2 = softwareExtraRsp.signature;
        }
        if ((i & 4) != 0) {
            num = softwareExtraRsp.ver_code;
        }
        if ((i & 8) != 0) {
            str3 = softwareExtraRsp.version;
        }
        return softwareExtraRsp.copy(str, str2, num, str3);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPackage_name() {
        return this.package_name;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSignature() {
        return this.signature;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getVer_code() {
        return this.ver_code;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    @NotNull
    public final SoftwareExtraRsp copy(@Nullable String package_name, @Nullable String signature, @Nullable Integer ver_code, @Nullable String version) {
        return new SoftwareExtraRsp(package_name, signature, ver_code, version);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SoftwareExtraRsp)) {
            return false;
        }
        SoftwareExtraRsp softwareExtraRsp = (SoftwareExtraRsp) other;
        return Intrinsics.areEqual(this.package_name, softwareExtraRsp.package_name) && Intrinsics.areEqual(this.signature, softwareExtraRsp.signature) && Intrinsics.areEqual(this.ver_code, softwareExtraRsp.ver_code) && Intrinsics.areEqual(this.version, softwareExtraRsp.version);
    }

    @Nullable
    public final String getPackage_name() {
        return this.package_name;
    }

    @Nullable
    public final String getSignature() {
        return this.signature;
    }

    @Nullable
    public final Integer getVer_code() {
        return this.ver_code;
    }

    @Nullable
    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.package_name;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.signature;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.ver_code;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.version;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        String str = this.package_name;
        String str2 = this.signature;
        Integer num = this.ver_code;
        String str3 = this.version;
        StringBuilder sbL = d.l("SoftwareExtraRsp(package_name=", str, ", signature=", str2, ", ver_code=");
        sbL.append(num);
        sbL.append(", version=");
        sbL.append(str3);
        sbL.append(")");
        return sbL.toString();
    }
}
