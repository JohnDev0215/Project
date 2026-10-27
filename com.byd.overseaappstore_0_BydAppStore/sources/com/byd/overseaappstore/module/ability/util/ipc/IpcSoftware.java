package com.byd.overseaappstore.module.ability.util.ipc;

import com.byd.car.locale.ccTLD;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003J\t\u0010\u001f\u001a\u00020\fHÆ\u0003JO\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0003HÖ\u0001J\t\u0010%\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011¨\u0006&"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/ipc/IpcSoftware;", "", ccTLD.INDONESIA, "", "name", "", "packageName", "versionCode", "versionTip", "totalSize", "", "sourceType", "", "(ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;JS)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getPackageName", "getSourceType", "()S", "getTotalSize", "()J", "getVersionCode", "getVersionTip", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class IpcSoftware {
    private final int id;

    @NotNull
    private final String name;

    @NotNull
    private final String packageName;
    private final short sourceType;
    private final long totalSize;
    private final int versionCode;

    @NotNull
    private final String versionTip;

    public IpcSoftware(int i, @NotNull String name, @NotNull String packageName, int i2, @NotNull String versionTip, long j, short s) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(versionTip, "versionTip");
        this.id = i;
        this.name = name;
        this.packageName = packageName;
        this.versionCode = i2;
        this.versionTip = versionTip;
        this.totalSize = j;
        this.sourceType = s;
    }

    public /* synthetic */ IpcSoftware(int i, String str, String str2, int i2, String str3, long j, short s, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? "" : str, (i3 & 4) != 0 ? "" : str2, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? "" : str3, (i3 & 32) != 0 ? 0L : j, s);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
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

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getVersionCode() {
        return this.versionCode;
    }

    @NotNull
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getVersionTip() {
        return this.versionTip;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getTotalSize() {
        return this.totalSize;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final short getSourceType() {
        return this.sourceType;
    }

    @NotNull
    public final IpcSoftware copy(int id, @NotNull String name, @NotNull String packageName, int versionCode, @NotNull String versionTip, long totalSize, short sourceType) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(versionTip, "versionTip");
        return new IpcSoftware(id, name, packageName, versionCode, versionTip, totalSize, sourceType);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IpcSoftware)) {
            return false;
        }
        IpcSoftware ipcSoftware = (IpcSoftware) other;
        return this.id == ipcSoftware.id && Intrinsics.areEqual(this.name, ipcSoftware.name) && Intrinsics.areEqual(this.packageName, ipcSoftware.packageName) && this.versionCode == ipcSoftware.versionCode && Intrinsics.areEqual(this.versionTip, ipcSoftware.versionTip) && this.totalSize == ipcSoftware.totalSize && this.sourceType == ipcSoftware.sourceType;
    }

    public final int getId() {
        return this.id;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getPackageName() {
        return this.packageName;
    }

    public final short getSourceType() {
        return this.sourceType;
    }

    public final long getTotalSize() {
        return this.totalSize;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }

    @NotNull
    public final String getVersionTip() {
        return this.versionTip;
    }

    public int hashCode() {
        return Short.hashCode(this.sourceType) + ((Long.hashCode(this.totalSize) + xi.b(this.versionTip, (Integer.hashCode(this.versionCode) + xi.b(this.packageName, xi.b(this.name, Integer.hashCode(this.id) * 31, 31), 31)) * 31, 31)) * 31);
    }

    @NotNull
    public String toString() {
        int i = this.id;
        String str = this.name;
        String str2 = this.packageName;
        int i2 = this.versionCode;
        String str3 = this.versionTip;
        long j = this.totalSize;
        short s = this.sourceType;
        StringBuilder sbH = xi.h("IpcSoftware(id=", i, ", name=", str, ", packageName=");
        sbH.append(str2);
        sbH.append(", versionCode=");
        sbH.append(i2);
        sbH.append(", versionTip=");
        sbH.append(str3);
        sbH.append(", totalSize=");
        sbH.append(j);
        sbH.append(", sourceType=");
        sbH.append((int) s);
        sbH.append(")");
        return sbH.toString();
    }
}
