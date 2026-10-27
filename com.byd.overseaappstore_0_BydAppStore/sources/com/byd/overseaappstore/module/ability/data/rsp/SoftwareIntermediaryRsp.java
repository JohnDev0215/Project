package com.byd.overseaappstore.module.ability.data.rsp;

import android.graphics.drawable.Drawable;
import com.byd.car.locale.ccTLD;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\n\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001e\b\u0016\u0018\u00002\u00020\u0001BÛ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0014\u001a\u00020\r\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0005\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0005¢\u0006\u0002\u0010\u001eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0019\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\"R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010 R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\"R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\"R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\"R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010 R\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010 R\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\"R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\"R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0011\u0010\u0016\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b3\u0010 R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u0010\u0014\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b6\u00105R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010 R\u0011\u0010\u001d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\"R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\"¨\u0006:"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareIntermediaryRsp;", "", ccTLD.INDONESIA, "", "name", "", "icon", "iconDrawable", "Landroid/graphics/drawable/Drawable;", "packageName", "classifyId", "classifyName", "totalSize", "", "percentCount", "sizeTip", "versionCode", "versionTip", "introduction", "downloadUrl", "updateTime", "screenCompatibility", "state", "signature", "sourceType", "", "extra", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareExtraRsp;", "versionIntroduction", "(ILjava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;Ljava/lang/String;ILjava/lang/String;JILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;JIILjava/lang/String;SLjava/util/List;Ljava/lang/String;)V", "getClassifyId", "()I", "getClassifyName", "()Ljava/lang/String;", "getDownloadUrl", "getExtra", "()Ljava/util/List;", "getIcon", "getIconDrawable", "()Landroid/graphics/drawable/Drawable;", "getId", "getIntroduction", "getName", "getPackageName", "getPercentCount", "getScreenCompatibility", "getSignature", "getSizeTip", "getSourceType", "()S", "getState", "getTotalSize", "()J", "getUpdateTime", "getVersionCode", "getVersionIntroduction", "getVersionTip", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SoftwareIntermediaryRsp {
    private final int classifyId;

    @NotNull
    private final String classifyName;

    @NotNull
    private final String downloadUrl;

    @Nullable
    private final List<SoftwareExtraRsp> extra;

    @NotNull
    private final String icon;

    @Nullable
    private final Drawable iconDrawable;
    private final int id;

    @NotNull
    private final String introduction;

    @NotNull
    private final String name;

    @NotNull
    private final String packageName;
    private final int percentCount;
    private final int screenCompatibility;

    @NotNull
    private final String signature;

    @NotNull
    private final String sizeTip;
    private final short sourceType;
    private final int state;
    private final long totalSize;
    private final long updateTime;
    private final int versionCode;

    @NotNull
    private final String versionIntroduction;

    @NotNull
    private final String versionTip;

    public SoftwareIntermediaryRsp(int i, @NotNull String name, @NotNull String icon, @Nullable Drawable drawable, @NotNull String packageName, int i2, @NotNull String classifyName, long j, int i3, @NotNull String sizeTip, int i4, @NotNull String versionTip, @NotNull String introduction, @NotNull String downloadUrl, long j2, int i5, int i6, @NotNull String signature, short s, @Nullable List<SoftwareExtraRsp> list, @NotNull String versionIntroduction) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(classifyName, "classifyName");
        Intrinsics.checkNotNullParameter(sizeTip, "sizeTip");
        Intrinsics.checkNotNullParameter(versionTip, "versionTip");
        Intrinsics.checkNotNullParameter(introduction, "introduction");
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(versionIntroduction, "versionIntroduction");
        this.id = i;
        this.name = name;
        this.icon = icon;
        this.iconDrawable = drawable;
        this.packageName = packageName;
        this.classifyId = i2;
        this.classifyName = classifyName;
        this.totalSize = j;
        this.percentCount = i3;
        this.sizeTip = sizeTip;
        this.versionCode = i4;
        this.versionTip = versionTip;
        this.introduction = introduction;
        this.downloadUrl = downloadUrl;
        this.updateTime = j2;
        this.screenCompatibility = i5;
        this.state = i6;
        this.signature = signature;
        this.sourceType = s;
        this.extra = list;
        this.versionIntroduction = versionIntroduction;
    }

    public /* synthetic */ SoftwareIntermediaryRsp(int i, String str, String str2, Drawable drawable, String str3, int i2, String str4, long j, int i3, String str5, int i4, String str6, String str7, String str8, long j2, int i5, int i6, String str9, short s, List list, String str10, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? 0 : i, (i7 & 2) != 0 ? "" : str, (i7 & 4) != 0 ? "" : str2, drawable, (i7 & 16) != 0 ? "" : str3, (i7 & 32) != 0 ? 0 : i2, (i7 & 64) != 0 ? "" : str4, (i7 & 128) != 0 ? 0L : j, (i7 & 256) != 0 ? 0 : i3, (i7 & 512) != 0 ? "" : str5, (i7 & 1024) != 0 ? 0 : i4, (i7 & 2048) != 0 ? "" : str6, (i7 & 4096) != 0 ? "" : str7, (i7 & 8192) != 0 ? "" : str8, (i7 & 16384) != 0 ? 0L : j2, (32768 & i7) != 0 ? 3 : i5, (65536 & i7) == 0 ? i6 : 0, (131072 & i7) != 0 ? "" : str9, s, list, (i7 & 1048576) != 0 ? "" : str10);
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
    public final List<SoftwareExtraRsp> getExtra() {
        return this.extra;
    }

    @NotNull
    public final String getIcon() {
        return this.icon;
    }

    @Nullable
    public final Drawable getIconDrawable() {
        return this.iconDrawable;
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

    public final int getScreenCompatibility() {
        return this.screenCompatibility;
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

    public final long getUpdateTime() {
        return this.updateTime;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }

    @NotNull
    public final String getVersionIntroduction() {
        return this.versionIntroduction;
    }

    @NotNull
    public final String getVersionTip() {
        return this.versionTip;
    }
}
