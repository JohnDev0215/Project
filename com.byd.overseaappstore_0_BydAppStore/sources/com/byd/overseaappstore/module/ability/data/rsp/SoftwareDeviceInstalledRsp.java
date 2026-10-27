package com.byd.overseaappstore.module.ability.data.rsp;

import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.d;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\rHÆ\u0003JY\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020\tHÖ\u0001J\t\u0010(\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012¨\u0006)"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDeviceInstalledRsp;", "", "icon", "Landroid/graphics/drawable/Drawable;", "name", "", "packageName", "signature", "versionCode", "", "versionTip", "sourceDir", "size", "", "(Landroid/graphics/drawable/Drawable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;J)V", "getIcon", "()Landroid/graphics/drawable/Drawable;", "getName", "()Ljava/lang/String;", "getPackageName", "getSignature", "getSize", "()J", "getSourceDir", "getVersionCode", "()I", "getVersionTip", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SoftwareDeviceInstalledRsp {

    @NotNull
    private final Drawable icon;

    @NotNull
    private final String name;

    @NotNull
    private final String packageName;

    @NotNull
    private final String signature;
    private final long size;

    /* JADX INFO: renamed from: sourceDir, reason: from kotlin metadata and from toString */
    @NotNull
    private final String versionCode;
    private final int versionCode;

    @NotNull
    private final String versionTip;

    public SoftwareDeviceInstalledRsp(@NotNull Drawable icon, @NotNull String name, @NotNull String packageName, @NotNull String signature, int i, @NotNull String versionTip, @NotNull String sourceDir, long j) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(versionTip, "versionTip");
        Intrinsics.checkNotNullParameter(sourceDir, "sourceDir");
        this.icon = icon;
        this.name = name;
        this.packageName = packageName;
        this.signature = signature;
        this.versionCode = i;
        this.versionTip = versionTip;
        this.versionCode = sourceDir;
        this.size = j;
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Drawable getIcon() {
        return this.icon;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    @NotNull
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getSignature() {
        return this.signature;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getVersionCode() {
        return this.versionCode;
    }

    @NotNull
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getVersionTip() {
        return this.versionTip;
    }

    @NotNull
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getVersionCode() {
        return this.versionCode;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getSize() {
        return this.size;
    }

    @NotNull
    public final SoftwareDeviceInstalledRsp copy(@NotNull Drawable icon, @NotNull String name, @NotNull String packageName, @NotNull String signature, int versionCode, @NotNull String versionTip, @NotNull String sourceDir, long size) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(versionTip, "versionTip");
        Intrinsics.checkNotNullParameter(sourceDir, "sourceDir");
        return new SoftwareDeviceInstalledRsp(icon, name, packageName, signature, versionCode, versionTip, sourceDir, size);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SoftwareDeviceInstalledRsp)) {
            return false;
        }
        SoftwareDeviceInstalledRsp softwareDeviceInstalledRsp = (SoftwareDeviceInstalledRsp) other;
        return Intrinsics.areEqual(this.icon, softwareDeviceInstalledRsp.icon) && Intrinsics.areEqual(this.name, softwareDeviceInstalledRsp.name) && Intrinsics.areEqual(this.packageName, softwareDeviceInstalledRsp.packageName) && Intrinsics.areEqual(this.signature, softwareDeviceInstalledRsp.signature) && this.versionCode == softwareDeviceInstalledRsp.versionCode && Intrinsics.areEqual(this.versionTip, softwareDeviceInstalledRsp.versionTip) && Intrinsics.areEqual(this.versionCode, softwareDeviceInstalledRsp.versionCode) && this.size == softwareDeviceInstalledRsp.size;
    }

    @NotNull
    public final Drawable getIcon() {
        return this.icon;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getPackageName() {
        return this.packageName;
    }

    @NotNull
    public final String getSignature() {
        return this.signature;
    }

    public final long getSize() {
        return this.size;
    }

    @NotNull
    public final String getSourceDir() {
        return this.versionCode;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }

    @NotNull
    public final String getVersionTip() {
        return this.versionTip;
    }

    public int hashCode() {
        return Long.hashCode(this.size) + xi.b(this.versionCode, xi.b(this.versionTip, (Integer.hashCode(this.versionCode) + xi.b(this.signature, xi.b(this.packageName, xi.b(this.name, this.icon.hashCode() * 31, 31), 31), 31)) * 31, 31), 31);
    }

    @NotNull
    public String toString() {
        Drawable drawable = this.icon;
        String str = this.name;
        String str2 = this.packageName;
        String str3 = this.signature;
        int i = this.versionCode;
        String str4 = this.versionTip;
        String str5 = this.versionCode;
        long j = this.size;
        StringBuilder sb = new StringBuilder();
        sb.append("SoftwareDeviceInstalledRsp(icon=");
        sb.append(drawable);
        sb.append(", name=");
        sb.append(str);
        sb.append(", packageName=");
        sb.append(str2);
        sb.append(", signature=");
        sb.append(str3);
        sb.append(", versionCode=");
        d.t(sb, i, ", versionTip=", str4, ", sourceDir=");
        sb.append(str5);
        sb.append(", size=");
        sb.append(j);
        sb.append(")");
        return sb.toString();
    }
}
