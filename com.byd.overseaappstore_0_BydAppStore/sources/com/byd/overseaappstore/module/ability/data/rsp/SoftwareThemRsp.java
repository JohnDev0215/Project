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
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0095\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012¢\u0006\u0002\u0010\u0014J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010*\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010+\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010%J\u0011\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u00101\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u00104\u001a\u0004\u0018\u00010\u0005HÆ\u0003J¤\u0001\u00105\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012HÆ\u0001¢\u0006\u0002\u00106J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010:\u001a\u00020\u0003HÖ\u0001J\b\u0010;\u001a\u00020\u0005H\u0016R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001d\u0010\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b'\u0010\u0016R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0019¨\u0006<"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareThemRsp;", "", ccTLD.INDONESIA, "", "package_name", "", "name", "version", "ver_code", "signature", "download", "classification_id", "icon", "size", "", "source_type", "", "extra", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareExtraRsp;", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Short;Ljava/util/List;)V", "getClassification_id", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getDownload", "()Ljava/lang/String;", "getExtra", "()Ljava/util/List;", "getIcon", "getId", "getName", "getPackage_name", "getSignature", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getSource_type", "()Ljava/lang/Short;", "Ljava/lang/Short;", "getVer_code", "getVersion", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Short;Ljava/util/List;)Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareThemRsp;", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SoftwareThemRsp {

    @Nullable
    private final Integer classification_id;

    @Nullable
    private final String download;

    @Nullable
    private final List<SoftwareExtraRsp> extra;

    @Nullable
    private final String icon;

    @Nullable
    private final Integer id;

    @Nullable
    private final String name;

    @Nullable
    private final String package_name;

    @Nullable
    private final String signature;

    @Nullable
    private final Long size;

    @Nullable
    private final Short source_type;

    @Nullable
    private final Integer ver_code;

    @Nullable
    private final String version;

    public SoftwareThemRsp(@Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num2, @Nullable String str4, @Nullable String str5, @Nullable Integer num3, @Nullable String str6, @Nullable Long l, @Nullable Short sh, @Nullable List<SoftwareExtraRsp> list) {
        this.id = num;
        this.package_name = str;
        this.name = str2;
        this.version = str3;
        this.ver_code = num2;
        this.signature = str4;
        this.download = str5;
        this.classification_id = num3;
        this.icon = str6;
        this.size = l;
        this.source_type = sh;
        this.extra = list;
    }

    public /* synthetic */ SoftwareThemRsp(Integer num, String str, String str2, String str3, Integer num2, String str4, String str5, Integer num3, String str6, Long l, Short sh, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0 : num, (i & 2) != 0 ? "" : str, (i & 4) != 0 ? "" : str2, (i & 8) != 0 ? "" : str3, (i & 16) != 0 ? 0 : num2, (i & 32) != 0 ? "" : str4, (i & 64) != 0 ? "" : str5, num3, (i & 256) != 0 ? "" : str6, (i & 512) != 0 ? 0L : l, sh, list);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    @Nullable
    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Long getSize() {
        return this.size;
    }

    @Nullable
    /* JADX INFO: renamed from: component11, reason: from getter */
    public final Short getSource_type() {
        return this.source_type;
    }

    @Nullable
    public final List<SoftwareExtraRsp> component12() {
        return this.extra;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPackage_name() {
        return this.package_name;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    @Nullable
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getVer_code() {
        return this.ver_code;
    }

    @Nullable
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getSignature() {
        return this.signature;
    }

    @Nullable
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getDownload() {
        return this.download;
    }

    @Nullable
    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Integer getClassification_id() {
        return this.classification_id;
    }

    @Nullable
    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    @NotNull
    public final SoftwareThemRsp copy(@Nullable Integer id, @Nullable String package_name, @Nullable String name, @Nullable String version, @Nullable Integer ver_code, @Nullable String signature, @Nullable String download, @Nullable Integer classification_id, @Nullable String icon, @Nullable Long size, @Nullable Short source_type, @Nullable List<SoftwareExtraRsp> extra) {
        return new SoftwareThemRsp(id, package_name, name, version, ver_code, signature, download, classification_id, icon, size, source_type, extra);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SoftwareThemRsp)) {
            return false;
        }
        SoftwareThemRsp softwareThemRsp = (SoftwareThemRsp) other;
        return Intrinsics.areEqual(this.id, softwareThemRsp.id) && Intrinsics.areEqual(this.package_name, softwareThemRsp.package_name) && Intrinsics.areEqual(this.name, softwareThemRsp.name) && Intrinsics.areEqual(this.version, softwareThemRsp.version) && Intrinsics.areEqual(this.ver_code, softwareThemRsp.ver_code) && Intrinsics.areEqual(this.signature, softwareThemRsp.signature) && Intrinsics.areEqual(this.download, softwareThemRsp.download) && Intrinsics.areEqual(this.classification_id, softwareThemRsp.classification_id) && Intrinsics.areEqual(this.icon, softwareThemRsp.icon) && Intrinsics.areEqual(this.size, softwareThemRsp.size) && Intrinsics.areEqual(this.source_type, softwareThemRsp.source_type) && Intrinsics.areEqual(this.extra, softwareThemRsp.extra);
    }

    @Nullable
    public final Integer getClassification_id() {
        return this.classification_id;
    }

    @Nullable
    public final String getDownload() {
        return this.download;
    }

    @Nullable
    public final List<SoftwareExtraRsp> getExtra() {
        return this.extra;
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
    public final String getName() {
        return this.name;
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
    public final Long getSize() {
        return this.size;
    }

    @Nullable
    public final Short getSource_type() {
        return this.source_type;
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
        Integer num = this.id;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.package_name;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.name;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.version;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num2 = this.ver_code;
        int iHashCode5 = (iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str4 = this.signature;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.download;
        int iHashCode7 = (iHashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Integer num3 = this.classification_id;
        int iHashCode8 = (iHashCode7 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str6 = this.icon;
        int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Long l = this.size;
        int iHashCode10 = (iHashCode9 + (l == null ? 0 : l.hashCode())) * 31;
        Short sh = this.source_type;
        int iHashCode11 = (iHashCode10 + (sh == null ? 0 : sh.hashCode())) * 31;
        List<SoftwareExtraRsp> list = this.extra;
        return iHashCode11 + (list != null ? list.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("SoftwareThemRsp");
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
        sb.append("extra:");
        sb.append(String.valueOf(this.extra));
        sb.append(")");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(\"SoftwareT…  .append(\")\").toString()");
        return string;
    }
}
