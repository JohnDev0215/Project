package com.byd.overseaappstore.module.ability.util.download.data;

import com.byd.car.locale.ccTLD;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\n\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u007f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014¢\u0006\u0002\u0010\u0016J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0012HÆ\u0003J\u0011\u00103\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014HÆ\u0003J\t\u00104\u001a\u00020\u0005HÆ\u0003J\t\u00105\u001a\u00020\u0005HÆ\u0003J\t\u00106\u001a\u00020\u0005HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\nHÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0005HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\u009d\u0001\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014HÆ\u0001J\u0013\u0010=\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010@\u001a\u00020\u0003HÖ\u0001J\t\u0010A\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0019\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001a\"\u0004\b#\u0010$R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0018\"\u0004\b)\u0010*R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0018¨\u0006B"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;", "", ccTLD.INDONESIA, "", "packageName", "", "name", "icon", "downloadUrl", "totalSize", "", "versionCode", "classifyName", "classifyId", "signature", "state", "path", "sourceType", "", "extra", "", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareExtParam;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JILjava/lang/String;ILjava/lang/String;ILjava/lang/String;SLjava/util/List;)V", "getClassifyId", "()I", "getClassifyName", "()Ljava/lang/String;", "getDownloadUrl", "getExtra", "()Ljava/util/List;", "getIcon", "getId", "getName", "getPackageName", "getPath", "setPath", "(Ljava/lang/String;)V", "getSignature", "getSourceType", "()S", "getState", "setState", "(I)V", "getTotalSize", "()J", "getVersionCode", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SoftwareOptParam {
    private final int classifyId;

    @NotNull
    private final String classifyName;

    @NotNull
    private final String downloadUrl;

    @Nullable
    private final List<SoftwareExtParam> extra;

    @NotNull
    private final String icon;
    private final int id;

    @NotNull
    private final String name;

    @NotNull
    private final String packageName;

    @NotNull
    private String path;

    @NotNull
    private final String signature;
    private final short sourceType;
    private int state;
    private final long totalSize;
    private final int versionCode;

    public SoftwareOptParam(int i, @NotNull String packageName, @NotNull String name, @NotNull String icon, @NotNull String downloadUrl, long j, int i2, @NotNull String classifyName, int i3, @NotNull String signature, int i4, @NotNull String path, short s, @Nullable List<SoftwareExtParam> list) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        Intrinsics.checkNotNullParameter(classifyName, "classifyName");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(path, "path");
        this.id = i;
        this.packageName = packageName;
        this.name = name;
        this.icon = icon;
        this.downloadUrl = downloadUrl;
        this.totalSize = j;
        this.versionCode = i2;
        this.classifyName = classifyName;
        this.classifyId = i3;
        this.signature = signature;
        this.state = i4;
        this.path = path;
        this.sourceType = s;
        this.extra = list;
    }

    public /* synthetic */ SoftwareOptParam(int i, String str, String str2, String str3, String str4, long j, int i2, String str5, int i3, String str6, int i4, String str7, short s, List list, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, str3, str4, j, i2, str5, i3, str6, i4, (i5 & 2048) != 0 ? "" : str7, s, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    @NotNull
    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getSignature() {
        return this.signature;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getState() {
        return this.state;
    }

    @NotNull
    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final short getSourceType() {
        return this.sourceType;
    }

    @Nullable
    public final List<SoftwareExtParam> component14() {
        return this.extra;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    @NotNull
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    @NotNull
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDownloadUrl() {
        return this.downloadUrl;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getTotalSize() {
        return this.totalSize;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getVersionCode() {
        return this.versionCode;
    }

    @NotNull
    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getClassifyName() {
        return this.classifyName;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getClassifyId() {
        return this.classifyId;
    }

    @NotNull
    public final SoftwareOptParam copy(int id, @NotNull String packageName, @NotNull String name, @NotNull String icon, @NotNull String downloadUrl, long totalSize, int versionCode, @NotNull String classifyName, int classifyId, @NotNull String signature, int state, @NotNull String path, short sourceType, @Nullable List<SoftwareExtParam> extra) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        Intrinsics.checkNotNullParameter(classifyName, "classifyName");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(path, "path");
        return new SoftwareOptParam(id, packageName, name, icon, downloadUrl, totalSize, versionCode, classifyName, classifyId, signature, state, path, sourceType, extra);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SoftwareOptParam)) {
            return false;
        }
        SoftwareOptParam softwareOptParam = (SoftwareOptParam) other;
        return this.id == softwareOptParam.id && Intrinsics.areEqual(this.packageName, softwareOptParam.packageName) && Intrinsics.areEqual(this.name, softwareOptParam.name) && Intrinsics.areEqual(this.icon, softwareOptParam.icon) && Intrinsics.areEqual(this.downloadUrl, softwareOptParam.downloadUrl) && this.totalSize == softwareOptParam.totalSize && this.versionCode == softwareOptParam.versionCode && Intrinsics.areEqual(this.classifyName, softwareOptParam.classifyName) && this.classifyId == softwareOptParam.classifyId && Intrinsics.areEqual(this.signature, softwareOptParam.signature) && this.state == softwareOptParam.state && Intrinsics.areEqual(this.path, softwareOptParam.path) && this.sourceType == softwareOptParam.sourceType && Intrinsics.areEqual(this.extra, softwareOptParam.extra);
    }

    public final int getClassifyId() {
        return this.classifyId;
    }

    @NotNull
    public final String getClassifyName() {
        return this.classifyName;
    }

    @NotNull
    public final String getDownloadUrl() {
        return this.downloadUrl;
    }

    @Nullable
    public final List<SoftwareExtParam> getExtra() {
        return this.extra;
    }

    @NotNull
    public final String getIcon() {
        return this.icon;
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

    @NotNull
    public final String getPath() {
        return this.path;
    }

    @NotNull
    public final String getSignature() {
        return this.signature;
    }

    public final short getSourceType() {
        return this.sourceType;
    }

    public final int getState() {
        return this.state;
    }

    public final long getTotalSize() {
        return this.totalSize;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }

    public int hashCode() {
        int iHashCode = (Short.hashCode(this.sourceType) + xi.b(this.path, (Integer.hashCode(this.state) + xi.b(this.signature, (Integer.hashCode(this.classifyId) + xi.b(this.classifyName, (Integer.hashCode(this.versionCode) + ((Long.hashCode(this.totalSize) + xi.b(this.downloadUrl, xi.b(this.icon, xi.b(this.name, xi.b(this.packageName, Integer.hashCode(this.id) * 31, 31), 31), 31), 31)) * 31)) * 31, 31)) * 31, 31)) * 31, 31)) * 31;
        List<SoftwareExtParam> list = this.extra;
        return iHashCode + (list == null ? 0 : list.hashCode());
    }

    public final void setPath(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.path = str;
    }

    public final void setState(int i) {
        this.state = i;
    }

    @NotNull
    public String toString() {
        int i = this.id;
        String str = this.packageName;
        String str2 = this.name;
        String str3 = this.icon;
        String str4 = this.downloadUrl;
        long j = this.totalSize;
        int i2 = this.versionCode;
        String str5 = this.classifyName;
        int i3 = this.classifyId;
        String str6 = this.signature;
        int i4 = this.state;
        String str7 = this.path;
        short s = this.sourceType;
        List<SoftwareExtParam> list = this.extra;
        StringBuilder sbH = xi.h("SoftwareOptParam(id=", i, ", packageName=", str, ", name=");
        sbH.append(str2);
        sbH.append(", icon=");
        sbH.append(str3);
        sbH.append(", downloadUrl=");
        sbH.append(str4);
        sbH.append(", totalSize=");
        sbH.append(j);
        sbH.append(", versionCode=");
        sbH.append(i2);
        sbH.append(", classifyName=");
        sbH.append(str5);
        sbH.append(", classifyId=");
        sbH.append(i3);
        sbH.append(", signature=");
        sbH.append(str6);
        sbH.append(", state=");
        sbH.append(i4);
        sbH.append(", path=");
        sbH.append(str7);
        sbH.append(", sourceType=");
        sbH.append((int) s);
        sbH.append(", extra=");
        sbH.append(list);
        sbH.append(")");
        return sbH.toString();
    }
}
