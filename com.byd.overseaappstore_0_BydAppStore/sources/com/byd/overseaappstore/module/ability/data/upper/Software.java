package com.byd.overseaappstore.module.ability.data.upper;

import com.byd.car.locale.ccTLD;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDtFixInfo;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareExtParam;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\n\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\b\u0016\u0018\u00002\u00020\u0001BÃ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018\u0012\u0006\u0010\u001a\u001a\u00020\u001b¢\u0006\u0002\u0010\u001cJ\u001a\u00109\u001a\u00020\u00002\b\b\u0002\u0010:\u001a\u00020\u00032\b\b\u0002\u0010;\u001a\u00020\u0003R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0019\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010 R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001eR\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010 R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010 R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b,\u0010 R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001eR\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010 R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010 R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001eR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001eR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010 \"\u0004\b7\u0010\"R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010 ¨\u0006<"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/upper/Software;", "", ccTLD.INDONESIA, "", "name", "", "icon", "packageName", "classifyId", "classifyName", "totalSize", "", "percentCount", "sizeTip", "versionCode", "versionTipRaw", "versionTip", "introduction", "downloadUrl", "signature", "state", "sourceType", "", "extra", "", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareExtParam;", "dtFixInfo", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDtFixInfo;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;JILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ISLjava/util/List;Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDtFixInfo;)V", "getClassifyId", "()I", "getClassifyName", "()Ljava/lang/String;", "setClassifyName", "(Ljava/lang/String;)V", "getDownloadUrl", "getDtFixInfo", "()Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDtFixInfo;", "getExtra", "()Ljava/util/List;", "getIcon", "getId", "getIntroduction", "getName", "getPackageName", "getPercentCount", "getSignature", "getSizeTip", "getSourceType", "()S", "getState", "getTotalSize", "()J", "getVersionCode", "getVersionTip", "setVersionTip", "getVersionTipRaw", "newSoftwareDownloaded", "newPercentCount", "newState", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class Software {
    private final int classifyId;

    @NotNull
    private String classifyName;

    @NotNull
    private final String downloadUrl;

    @NotNull
    private final SoftwareDtFixInfo dtFixInfo;

    @Nullable
    private final List<SoftwareExtParam> extra;

    @NotNull
    private final String icon;
    private final int id;

    @NotNull
    private final String introduction;

    @NotNull
    private final String name;

    @NotNull
    private final String packageName;
    private final int percentCount;

    @NotNull
    private final String signature;

    @NotNull
    private final String sizeTip;
    private final short sourceType;
    private final int state;
    private final long totalSize;
    private final int versionCode;

    @NotNull
    private String versionTip;

    @NotNull
    private final String versionTipRaw;

    public Software(int i, @NotNull String name, @NotNull String icon, @NotNull String packageName, int i2, @NotNull String classifyName, long j, int i3, @NotNull String sizeTip, int i4, @NotNull String versionTipRaw, @NotNull String versionTip, @NotNull String introduction, @NotNull String downloadUrl, @NotNull String signature, int i5, short s, @Nullable List<SoftwareExtParam> list, @NotNull SoftwareDtFixInfo dtFixInfo) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(classifyName, "classifyName");
        Intrinsics.checkNotNullParameter(sizeTip, "sizeTip");
        Intrinsics.checkNotNullParameter(versionTipRaw, "versionTipRaw");
        Intrinsics.checkNotNullParameter(versionTip, "versionTip");
        Intrinsics.checkNotNullParameter(introduction, "introduction");
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(dtFixInfo, "dtFixInfo");
        this.id = i;
        this.name = name;
        this.icon = icon;
        this.packageName = packageName;
        this.classifyId = i2;
        this.classifyName = classifyName;
        this.totalSize = j;
        this.percentCount = i3;
        this.sizeTip = sizeTip;
        this.versionCode = i4;
        this.versionTipRaw = versionTipRaw;
        this.versionTip = versionTip;
        this.introduction = introduction;
        this.downloadUrl = downloadUrl;
        this.signature = signature;
        this.state = i5;
        this.sourceType = s;
        this.extra = list;
        this.dtFixInfo = dtFixInfo;
    }

    public /* synthetic */ Software(int i, String str, String str2, String str3, int i2, String str4, long j, int i3, String str5, int i4, String str6, String str7, String str8, String str9, String str10, int i5, short s, List list, SoftwareDtFixInfo softwareDtFixInfo, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? 0 : i, (i6 & 2) != 0 ? "" : str, (i6 & 4) != 0 ? "" : str2, (i6 & 8) != 0 ? "" : str3, (i6 & 16) != 0 ? 0 : i2, (i6 & 32) != 0 ? "" : str4, (i6 & 64) != 0 ? 0L : j, (i6 & 128) != 0 ? 0 : i3, (i6 & 256) != 0 ? "" : str5, (i6 & 512) != 0 ? 0 : i4, (i6 & 1024) != 0 ? "" : str6, (i6 & 2048) != 0 ? "" : str7, (i6 & 4096) != 0 ? "" : str8, (i6 & 8192) != 0 ? "" : str9, str10, (i6 & 32768) != 0 ? 0 : i5, s, list, softwareDtFixInfo);
    }

    public static /* synthetic */ Software newSoftwareDownloaded$default(Software software, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: newSoftwareDownloaded");
        }
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return software.newSoftwareDownloaded(i, i2);
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

    @NotNull
    public final SoftwareDtFixInfo getDtFixInfo() {
        return this.dtFixInfo;
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
    public final String getIntroduction() {
        return this.introduction;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getPackageName() {
        return this.packageName;
    }

    public final int getPercentCount() {
        return this.percentCount;
    }

    @NotNull
    public final String getSignature() {
        return this.signature;
    }

    @NotNull
    public final String getSizeTip() {
        return this.sizeTip;
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

    @NotNull
    public final String getVersionTip() {
        return this.versionTip;
    }

    @NotNull
    public final String getVersionTipRaw() {
        return this.versionTipRaw;
    }

    @NotNull
    public final Software newSoftwareDownloaded(int newPercentCount, int newState) {
        return new Software(this.id, this.name, this.icon, this.packageName, this.classifyId, this.classifyName, this.totalSize, newPercentCount, this.sizeTip, this.versionCode, this.versionTipRaw, this.versionTip, this.introduction, this.downloadUrl, this.signature, newState, this.sourceType, this.extra, this.dtFixInfo);
    }

    public final void setClassifyName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.classifyName = str;
    }

    public final void setVersionTip(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.versionTip = str;
    }
}
