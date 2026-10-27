package com.byd.overseaappstore.module.ability.data.rsp;

import com.byd.car.locale.ccTLD;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bñ\u0001\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u001cJ\u0011\u00108\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u0010:\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010;\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u0010<\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010=\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010/J\u000b\u0010>\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010?\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u0010@\u001a\u0004\u0018\u00010\u0018HÆ\u0003¢\u0006\u0002\u00102J\u0011\u0010A\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010G\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010/J\u000b\u0010H\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010J\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010\u001eJþ\u0001\u0010K\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010LJ\u0013\u0010M\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010P\u001a\u00020\u000fHÖ\u0001J\b\u0010Q\u001a\u00020\u0006H\u0016R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0019\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b&\u0010\u001eR\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010$R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b(\u0010!R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010!R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b*\u0010!R\u0015\u0010\u0016\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b+\u0010\u001eR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b,\u0010!R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b-\u0010!R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u00100\u001a\u0004\b.\u0010/R\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\n\n\u0002\u00103\u001a\u0004\b1\u00102R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u00100\u001a\u0004\b4\u0010/R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b5\u0010\u001eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b6\u0010!R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b7\u0010!¨\u0006R"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDetailRsp;", "", "images", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/ImagePathRsp;", "signature", "", "icon", "version", "download", "size", "", "name", "package_name", "ver_code", "", "classification_id", "developer", ccTLD.INDONESIA, "sdk", "updateTime", "introduction", "screen_compatibility", "source_type", "", "extra", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareExtraRsp;", "version_introduction", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Short;Ljava/util/List;Ljava/lang/String;)V", "getClassification_id", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getDeveloper", "()Ljava/lang/String;", "getDownload", "getExtra", "()Ljava/util/List;", "getIcon", "getId", "getImages", "getIntroduction", "getName", "getPackage_name", "getScreen_compatibility", "getSdk", "getSignature", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getSource_type", "()Ljava/lang/Short;", "Ljava/lang/Short;", "getUpdateTime", "getVer_code", "getVersion", "getVersion_introduction", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Short;Ljava/util/List;Ljava/lang/String;)Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDetailRsp;", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SoftwareDetailRsp {

    @Nullable
    private final Integer classification_id;

    @Nullable
    private final String developer;

    @Nullable
    private final String download;

    /* JADX INFO: renamed from: extra, reason: from kotlin metadata and from toString */
    @Nullable
    private final List<SoftwareExtraRsp> version_introduction;

    @Nullable
    private final String icon;

    @Nullable
    private final Integer id;

    @Nullable
    private final List<ImagePathRsp> images;

    @Nullable
    private final String introduction;

    @Nullable
    private final String name;

    @Nullable
    private final String package_name;

    @Nullable
    private final Integer screen_compatibility;

    @Nullable
    private final String sdk;

    @Nullable
    private final String signature;

    @Nullable
    private final Long size;

    @Nullable
    private final Short source_type;

    @Nullable
    private final Long updateTime;

    @Nullable
    private final Integer ver_code;

    @Nullable
    private final String version;

    @Nullable
    private final String version_introduction;

    public SoftwareDetailRsp(@Nullable List<ImagePathRsp> list, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Long l, @Nullable String str5, @Nullable String str6, @Nullable Integer num, @Nullable Integer num2, @Nullable String str7, @Nullable Integer num3, @Nullable String str8, @Nullable Long l2, @Nullable String str9, @Nullable Integer num4, @Nullable Short sh, @Nullable List<SoftwareExtraRsp> list2, @Nullable String str10) {
        this.images = list;
        this.signature = str;
        this.icon = str2;
        this.version = str3;
        this.download = str4;
        this.size = l;
        this.name = str5;
        this.package_name = str6;
        this.ver_code = num;
        this.classification_id = num2;
        this.developer = str7;
        this.id = num3;
        this.sdk = str8;
        this.updateTime = l2;
        this.introduction = str9;
        this.screen_compatibility = num4;
        this.source_type = sh;
        this.version_introduction = list2;
        this.version_introduction = str10;
    }

    public /* synthetic */ SoftwareDetailRsp(List list, String str, String str2, String str3, String str4, Long l, String str5, String str6, Integer num, Integer num2, String str7, Integer num3, String str8, Long l2, String str9, Integer num4, Short sh, List list2, String str10, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? "" : str, (i & 4) != 0 ? "" : str2, (i & 8) != 0 ? "" : str3, (i & 16) != 0 ? "" : str4, (i & 32) != 0 ? 0L : l, (i & 64) != 0 ? "" : str5, (i & 128) != 0 ? "" : str6, (i & 256) != 0 ? 0 : num, (i & 512) != 0 ? 0 : num2, (i & 1024) != 0 ? "" : str7, (i & 2048) != 0 ? 0 : num3, (i & 4096) != 0 ? "" : str8, (i & 8192) != 0 ? 0L : l2, (i & 16384) != 0 ? "" : str9, (32768 & i) != 0 ? 3 : num4, sh, list2, (i & 262144) != 0 ? "" : str10);
    }

    @Nullable
    public final List<ImagePathRsp> component1() {
        return this.images;
    }

    @Nullable
    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Integer getClassification_id() {
        return this.classification_id;
    }

    @Nullable
    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getDeveloper() {
        return this.developer;
    }

    @Nullable
    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    @Nullable
    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getSdk() {
        return this.sdk;
    }

    @Nullable
    /* JADX INFO: renamed from: component14, reason: from getter */
    public final Long getUpdateTime() {
        return this.updateTime;
    }

    @Nullable
    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getIntroduction() {
        return this.introduction;
    }

    @Nullable
    /* JADX INFO: renamed from: component16, reason: from getter */
    public final Integer getScreen_compatibility() {
        return this.screen_compatibility;
    }

    @Nullable
    /* JADX INFO: renamed from: component17, reason: from getter */
    public final Short getSource_type() {
        return this.source_type;
    }

    @Nullable
    public final List<SoftwareExtraRsp> component18() {
        return this.version_introduction;
    }

    @Nullable
    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getVersion_introduction() {
        return this.version_introduction;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSignature() {
        return this.signature;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    @Nullable
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDownload() {
        return this.download;
    }

    @Nullable
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Long getSize() {
        return this.size;
    }

    @Nullable
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getName() {
        return this.name;
    }

    @Nullable
    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPackage_name() {
        return this.package_name;
    }

    @Nullable
    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Integer getVer_code() {
        return this.ver_code;
    }

    @NotNull
    public final SoftwareDetailRsp copy(@Nullable List<ImagePathRsp> images, @Nullable String signature, @Nullable String icon, @Nullable String version, @Nullable String download, @Nullable Long size, @Nullable String name, @Nullable String package_name, @Nullable Integer ver_code, @Nullable Integer classification_id, @Nullable String developer, @Nullable Integer id, @Nullable String sdk, @Nullable Long updateTime, @Nullable String introduction, @Nullable Integer screen_compatibility, @Nullable Short source_type, @Nullable List<SoftwareExtraRsp> extra, @Nullable String version_introduction) {
        return new SoftwareDetailRsp(images, signature, icon, version, download, size, name, package_name, ver_code, classification_id, developer, id, sdk, updateTime, introduction, screen_compatibility, source_type, extra, version_introduction);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SoftwareDetailRsp)) {
            return false;
        }
        SoftwareDetailRsp softwareDetailRsp = (SoftwareDetailRsp) other;
        return Intrinsics.areEqual(this.images, softwareDetailRsp.images) && Intrinsics.areEqual(this.signature, softwareDetailRsp.signature) && Intrinsics.areEqual(this.icon, softwareDetailRsp.icon) && Intrinsics.areEqual(this.version, softwareDetailRsp.version) && Intrinsics.areEqual(this.download, softwareDetailRsp.download) && Intrinsics.areEqual(this.size, softwareDetailRsp.size) && Intrinsics.areEqual(this.name, softwareDetailRsp.name) && Intrinsics.areEqual(this.package_name, softwareDetailRsp.package_name) && Intrinsics.areEqual(this.ver_code, softwareDetailRsp.ver_code) && Intrinsics.areEqual(this.classification_id, softwareDetailRsp.classification_id) && Intrinsics.areEqual(this.developer, softwareDetailRsp.developer) && Intrinsics.areEqual(this.id, softwareDetailRsp.id) && Intrinsics.areEqual(this.sdk, softwareDetailRsp.sdk) && Intrinsics.areEqual(this.updateTime, softwareDetailRsp.updateTime) && Intrinsics.areEqual(this.introduction, softwareDetailRsp.introduction) && Intrinsics.areEqual(this.screen_compatibility, softwareDetailRsp.screen_compatibility) && Intrinsics.areEqual(this.source_type, softwareDetailRsp.source_type) && Intrinsics.areEqual(this.version_introduction, softwareDetailRsp.version_introduction) && Intrinsics.areEqual(this.version_introduction, softwareDetailRsp.version_introduction);
    }

    @Nullable
    public final Integer getClassification_id() {
        return this.classification_id;
    }

    @Nullable
    public final String getDeveloper() {
        return this.developer;
    }

    @Nullable
    public final String getDownload() {
        return this.download;
    }

    @Nullable
    public final List<SoftwareExtraRsp> getExtra() {
        return this.version_introduction;
    }

    @Nullable
    public final String getIcon() {
        return this.icon;
    }

    @Nullable
    public final Integer getId() {
        return this.id;
    }

    @Nullable
    public final List<ImagePathRsp> getImages() {
        return this.images;
    }

    @Nullable
    public final String getIntroduction() {
        return this.introduction;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final String getPackage_name() {
        return this.package_name;
    }

    @Nullable
    public final Integer getScreen_compatibility() {
        return this.screen_compatibility;
    }

    @Nullable
    public final String getSdk() {
        return this.sdk;
    }

    @Nullable
    public final String getSignature() {
        return this.signature;
    }

    @Nullable
    public final Long getSize() {
        return this.size;
    }

    @Nullable
    public final Short getSource_type() {
        return this.source_type;
    }

    @Nullable
    public final Long getUpdateTime() {
        return this.updateTime;
    }

    @Nullable
    public final Integer getVer_code() {
        return this.ver_code;
    }

    @Nullable
    public final String getVersion() {
        return this.version;
    }

    @Nullable
    public final String getVersion_introduction() {
        return this.version_introduction;
    }

    public int hashCode() {
        List<ImagePathRsp> list = this.images;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        String str = this.signature;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.icon;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.version;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.download;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Long l = this.size;
        int iHashCode6 = (iHashCode5 + (l == null ? 0 : l.hashCode())) * 31;
        String str5 = this.name;
        int iHashCode7 = (iHashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.package_name;
        int iHashCode8 = (iHashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Integer num = this.ver_code;
        int iHashCode9 = (iHashCode8 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.classification_id;
        int iHashCode10 = (iHashCode9 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str7 = this.developer;
        int iHashCode11 = (iHashCode10 + (str7 == null ? 0 : str7.hashCode())) * 31;
        Integer num3 = this.id;
        int iHashCode12 = (iHashCode11 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str8 = this.sdk;
        int iHashCode13 = (iHashCode12 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Long l2 = this.updateTime;
        int iHashCode14 = (iHashCode13 + (l2 == null ? 0 : l2.hashCode())) * 31;
        String str9 = this.introduction;
        int iHashCode15 = (iHashCode14 + (str9 == null ? 0 : str9.hashCode())) * 31;
        Integer num4 = this.screen_compatibility;
        int iHashCode16 = (iHashCode15 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Short sh = this.source_type;
        int iHashCode17 = (iHashCode16 + (sh == null ? 0 : sh.hashCode())) * 31;
        List<SoftwareExtraRsp> list2 = this.version_introduction;
        int iHashCode18 = (iHashCode17 + (list2 == null ? 0 : list2.hashCode())) * 31;
        String str10 = this.version_introduction;
        return iHashCode18 + (str10 != null ? str10.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("SoftwareDetailRsp");
        sb.append("(");
        sb.append("id:");
        sb.append(this.id);
        sb.append(",");
        sb.append("name:");
        d.u(sb, this.name, ",", "package_name:");
        d.u(sb, this.package_name, ",", "version:");
        d.u(sb, this.version, ",", "ver_code:");
        sb.append(this.ver_code);
        sb.append(",");
        sb.append("classification_id:");
        sb.append(this.classification_id);
        sb.append(",");
        sb.append("signature:");
        d.u(sb, this.signature, ",", "size:");
        sb.append(this.size);
        sb.append(",");
        sb.append("source_type:");
        sb.append(this.source_type);
        sb.append(",");
        sb.append("updateTimeTip:");
        sb.append(this.updateTime);
        sb.append(",");
        sb.append("version_introduction");
        d.u(sb, this.version_introduction, ",", "extra:");
        sb.append(String.valueOf(this.version_introduction));
        sb.append(")");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(\"SoftwareD…  .append(\")\").toString()");
        return string;
    }
}
