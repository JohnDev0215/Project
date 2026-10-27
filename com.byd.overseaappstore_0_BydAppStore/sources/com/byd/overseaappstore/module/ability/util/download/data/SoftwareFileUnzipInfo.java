package com.byd.overseaappstore.module.ability.util.download.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.d;
import spi.hi;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0007HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006\""}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareFileUnzipInfo;", "", "primePackageName", "", "packageName", "apkPath", "versionCode", "", "versionName", "signature", "resPath", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getApkPath", "()Ljava/lang/String;", "getPackageName", "getPrimePackageName", "getResPath", "getSignature", "getVersionCode", "()I", "getVersionName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SoftwareFileUnzipInfo {

    @NotNull
    private final String apkPath;

    @NotNull
    private final String packageName;

    @NotNull
    private final String primePackageName;

    @NotNull
    private final String resPath;

    @NotNull
    private final String signature;
    private final int versionCode;

    @NotNull
    private final String versionName;

    public SoftwareFileUnzipInfo(@NotNull String primePackageName, @NotNull String packageName, @NotNull String apkPath, int i, @NotNull String versionName, @NotNull String signature, @NotNull String resPath) {
        Intrinsics.checkNotNullParameter(primePackageName, "primePackageName");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(apkPath, "apkPath");
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(resPath, "resPath");
        this.primePackageName = primePackageName;
        this.packageName = packageName;
        this.apkPath = apkPath;
        this.versionCode = i;
        this.versionName = versionName;
        this.signature = signature;
        this.resPath = resPath;
    }

    public /* synthetic */ SoftwareFileUnzipInfo(String str, String str2, String str3, int i, String str4, String str5, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, i, str4, str5, (i2 & 64) != 0 ? "" : str6);
    }

    public static /* synthetic */ SoftwareFileUnzipInfo copy$default(SoftwareFileUnzipInfo softwareFileUnzipInfo, String str, String str2, String str3, int i, String str4, String str5, String str6, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = softwareFileUnzipInfo.primePackageName;
        }
        if ((i2 & 2) != 0) {
            str2 = softwareFileUnzipInfo.packageName;
        }
        String str7 = str2;
        if ((i2 & 4) != 0) {
            str3 = softwareFileUnzipInfo.apkPath;
        }
        String str8 = str3;
        if ((i2 & 8) != 0) {
            i = softwareFileUnzipInfo.versionCode;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            str4 = softwareFileUnzipInfo.versionName;
        }
        String str9 = str4;
        if ((i2 & 32) != 0) {
            str5 = softwareFileUnzipInfo.signature;
        }
        String str10 = str5;
        if ((i2 & 64) != 0) {
            str6 = softwareFileUnzipInfo.resPath;
        }
        return softwareFileUnzipInfo.copy(str, str7, str8, i3, str9, str10, str6);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPrimePackageName() {
        return this.primePackageName;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getApkPath() {
        return this.apkPath;
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
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getSignature() {
        return this.signature;
    }

    @NotNull
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getResPath() {
        return this.resPath;
    }

    @NotNull
    public final SoftwareFileUnzipInfo copy(@NotNull String primePackageName, @NotNull String packageName, @NotNull String apkPath, int versionCode, @NotNull String versionName, @NotNull String signature, @NotNull String resPath) {
        Intrinsics.checkNotNullParameter(primePackageName, "primePackageName");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(apkPath, "apkPath");
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(resPath, "resPath");
        return new SoftwareFileUnzipInfo(primePackageName, packageName, apkPath, versionCode, versionName, signature, resPath);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SoftwareFileUnzipInfo)) {
            return false;
        }
        SoftwareFileUnzipInfo softwareFileUnzipInfo = (SoftwareFileUnzipInfo) other;
        return Intrinsics.areEqual(this.primePackageName, softwareFileUnzipInfo.primePackageName) && Intrinsics.areEqual(this.packageName, softwareFileUnzipInfo.packageName) && Intrinsics.areEqual(this.apkPath, softwareFileUnzipInfo.apkPath) && this.versionCode == softwareFileUnzipInfo.versionCode && Intrinsics.areEqual(this.versionName, softwareFileUnzipInfo.versionName) && Intrinsics.areEqual(this.signature, softwareFileUnzipInfo.signature) && Intrinsics.areEqual(this.resPath, softwareFileUnzipInfo.resPath);
    }

    @NotNull
    public final String getApkPath() {
        return this.apkPath;
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
    public final String getResPath() {
        return this.resPath;
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
        return this.resPath.hashCode() + xi.b(this.signature, xi.b(this.versionName, (Integer.hashCode(this.versionCode) + xi.b(this.apkPath, xi.b(this.packageName, this.primePackageName.hashCode() * 31, 31), 31)) * 31, 31), 31);
    }

    @NotNull
    public String toString() {
        String str = this.primePackageName;
        String str2 = this.packageName;
        String str3 = this.apkPath;
        int i = this.versionCode;
        String str4 = this.versionName;
        String str5 = this.signature;
        String str6 = this.resPath;
        StringBuilder sbL = d.l("SoftwareFileUnzipInfo(primePackageName=", str, ", packageName=", str2, ", apkPath=");
        sbL.append(str3);
        sbL.append(", versionCode=");
        sbL.append(i);
        sbL.append(", versionName=");
        sbL.append(str4);
        sbL.append(", signature=");
        sbL.append(str5);
        sbL.append(", resPath=");
        return hi.f(sbL, str6, ")");
    }
}
