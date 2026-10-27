package com.byd.overseaappstore.module.ability.util.ipc;

import com.byd.car.locale.ccTLD;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010\"\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0018Jb\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0003HÖ\u0001J\t\u0010)\u001a\u00020\u0005HÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u001a\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012¨\u0006*"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/ipc/IpcSoftwareRsp;", "", ccTLD.INDONESIA, "", "name", "", "version", "ver_code", "package_name", "size", "", "source_type", "", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Short;)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getName", "()Ljava/lang/String;", "getPackage_name", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getSource_type", "()Ljava/lang/Short;", "Ljava/lang/Short;", "getVer_code", "getVersion", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Short;)Lcom/byd/overseaappstore/module/ability/util/ipc/IpcSoftwareRsp;", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class IpcSoftwareRsp {

    @Nullable
    private final Integer id;

    @Nullable
    private final String name;

    @Nullable
    private final String package_name;

    @Nullable
    private final Long size;

    @Nullable
    private final Short source_type;

    @Nullable
    private final Integer ver_code;

    @Nullable
    private final String version;

    public IpcSoftwareRsp(@Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable Integer num2, @Nullable String str3, @Nullable Long l, @Nullable Short sh) {
        this.id = num;
        this.name = str;
        this.version = str2;
        this.ver_code = num2;
        this.package_name = str3;
        this.size = l;
        this.source_type = sh;
    }

    public /* synthetic */ IpcSoftwareRsp(Integer num, String str, String str2, Integer num2, String str3, Long l, Short sh, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, str, str2, num2, str3, (i & 32) != 0 ? 0L : l, sh);
    }

    public static /* synthetic */ IpcSoftwareRsp copy$default(IpcSoftwareRsp ipcSoftwareRsp, Integer num, String str, String str2, Integer num2, String str3, Long l, Short sh, int i, Object obj) {
        if ((i & 1) != 0) {
            num = ipcSoftwareRsp.id;
        }
        if ((i & 2) != 0) {
            str = ipcSoftwareRsp.name;
        }
        String str4 = str;
        if ((i & 4) != 0) {
            str2 = ipcSoftwareRsp.version;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            num2 = ipcSoftwareRsp.ver_code;
        }
        Integer num3 = num2;
        if ((i & 16) != 0) {
            str3 = ipcSoftwareRsp.package_name;
        }
        String str6 = str3;
        if ((i & 32) != 0) {
            l = ipcSoftwareRsp.size;
        }
        Long l2 = l;
        if ((i & 64) != 0) {
            sh = ipcSoftwareRsp.source_type;
        }
        return ipcSoftwareRsp.copy(num, str4, str5, num3, str6, l2, sh);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getVer_code() {
        return this.ver_code;
    }

    @Nullable
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPackage_name() {
        return this.package_name;
    }

    @Nullable
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Long getSize() {
        return this.size;
    }

    @Nullable
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Short getSource_type() {
        return this.source_type;
    }

    @NotNull
    public final IpcSoftwareRsp copy(@Nullable Integer id, @Nullable String name, @Nullable String version, @Nullable Integer ver_code, @Nullable String package_name, @Nullable Long size, @Nullable Short source_type) {
        return new IpcSoftwareRsp(id, name, version, ver_code, package_name, size, source_type);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IpcSoftwareRsp)) {
            return false;
        }
        IpcSoftwareRsp ipcSoftwareRsp = (IpcSoftwareRsp) other;
        return Intrinsics.areEqual(this.id, ipcSoftwareRsp.id) && Intrinsics.areEqual(this.name, ipcSoftwareRsp.name) && Intrinsics.areEqual(this.version, ipcSoftwareRsp.version) && Intrinsics.areEqual(this.ver_code, ipcSoftwareRsp.ver_code) && Intrinsics.areEqual(this.package_name, ipcSoftwareRsp.package_name) && Intrinsics.areEqual(this.size, ipcSoftwareRsp.size) && Intrinsics.areEqual(this.source_type, ipcSoftwareRsp.source_type);
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
        String str = this.name;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.version;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num2 = this.ver_code;
        int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str3 = this.package_name;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Long l = this.size;
        int iHashCode6 = (iHashCode5 + (l == null ? 0 : l.hashCode())) * 31;
        Short sh = this.source_type;
        return iHashCode6 + (sh != null ? sh.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "IpcSoftwareRsp(id=" + this.id + ", name=" + this.name + ", version=" + this.version + ", ver_code=" + this.ver_code + ", package_name=" + this.package_name + ", size=" + this.size + ", source_type=" + this.source_type + ")";
    }
}
