package com.byd.overseaappstore.module.ability.util.download.data;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.d;
import spi.hi;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareExtParam;", "", "packageName", "", "primePackageName", "signature", "versionCode", "", "versionName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getPackageName", "()Ljava/lang/String;", "getPrimePackageName", "getSignature", "getVersionCode", "()I", "getVersionName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SoftwareExtParam {

    @NotNull
    private final String packageName;

    @NotNull
    private final String primePackageName;

    @NotNull
    private final String signature;
    private final int versionCode;

    @NotNull
    private final String versionName;

    public SoftwareExtParam(@NotNull String packageName, @NotNull String primePackageName, @NotNull String signature, int i, @NotNull String versionName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(primePackageName, "primePackageName");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        this.packageName = packageName;
        this.primePackageName = primePackageName;
        this.signature = signature;
        this.versionCode = i;
        this.versionName = versionName;
    }

    public static /* synthetic */ SoftwareExtParam copy$default(SoftwareExtParam softwareExtParam, String str, String str2, String str3, int i, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = softwareExtParam.packageName;
        }
        if ((i2 & 2) != 0) {
            str2 = softwareExtParam.primePackageName;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            str3 = softwareExtParam.signature;
        }
        String str6 = str3;
        if ((i2 & 8) != 0) {
            i = softwareExtParam.versionCode;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            str4 = softwareExtParam.versionName;
        }
        return softwareExtParam.copy(str, str5, str6, i3, str4);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPrimePackageName() {
        return this.primePackageName;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSignature() {
        return this.signature;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getVersionCode() {
        return this.versionCode;
    }

    @NotNull
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getVersionName() {
        return this.versionName;
    }

    @NotNull
    public final SoftwareExtParam copy(@NotNull String packageName, @NotNull String primePackageName, @NotNull String signature, int versionCode, @NotNull String versionName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(primePackageName, "primePackageName");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        return new SoftwareExtParam(packageName, primePackageName, signature, versionCode, versionName);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SoftwareExtParam)) {
            return false;
        }
        SoftwareExtParam softwareExtParam = (SoftwareExtParam) other;
        return Intrinsics.areEqual(this.packageName, softwareExtParam.packageName) && Intrinsics.areEqual(this.primePackageName, softwareExtParam.primePackageName) && Intrinsics.areEqual(this.signature, softwareExtParam.signature) && this.versionCode == softwareExtParam.versionCode && Intrinsics.areEqual(this.versionName, softwareExtParam.versionName);
    }

    @NotNull
    public final String getPackageName() {
        return this.packageName;
    }

    @NotNull
    public final String getPrimePackageName() {
        return this.primePackageName;
    }

    @NotNull
    public final String getSignature() {
        return this.signature;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }

    @NotNull
    public final String getVersionName() {
        return this.versionName;
    }

    public int hashCode() {
        return this.versionName.hashCode() + ((Integer.hashCode(this.versionCode) + xi.b(this.signature, xi.b(this.primePackageName, this.packageName.hashCode() * 31, 31), 31)) * 31);
    }

    @NotNull
    public String toString() {
        String str = this.packageName;
        String str2 = this.primePackageName;
        String str3 = this.signature;
        int i = this.versionCode;
        String str4 = this.versionName;
        StringBuilder sbL = d.l("SoftwareExtParam(packageName=", str, ", primePackageName=", str2, ", signature=");
        sbL.append(str3);
        sbL.append(", versionCode=");
        sbL.append(i);
        sbL.append(", versionName=");
        return hi.f(sbL, str4, ")");
    }
}
