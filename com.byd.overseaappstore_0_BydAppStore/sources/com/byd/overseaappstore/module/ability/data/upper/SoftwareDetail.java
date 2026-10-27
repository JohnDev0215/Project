package com.byd.overseaappstore.module.ability.data.upper;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BQ\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\u0006\u0010\u000f\u001a\u00020\u0007¢\u0006\u0002\u0010\u0010J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\nHÆ\u0003J\t\u0010(\u001a\u00020\u0007HÆ\u0003J\u0011\u0010)\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0003J\t\u0010*\u001a\u00020\u0007HÆ\u0003Jc\u0010+\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00072\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\b\b\u0002\u0010\u000f\u001a\u00020\u0007HÆ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u00020\nHÖ\u0001J\t\u00100\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u000b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0012\"\u0004\b!\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0012¨\u00061"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/upper/SoftwareDetail;", "", "software", "Lcom/byd/overseaappstore/module/ability/data/upper/Software;", "updateTime", "", "updateTimeTip", "", "description", "screenCompatibility", "", "screenCompatibilityTip", "images", "", "Lcom/byd/overseaappstore/module/ability/data/upper/ImagePath;", "versionDescription", "(Lcom/byd/overseaappstore/module/ability/data/upper/Software;JLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getImages", "()Ljava/util/List;", "getScreenCompatibility", "()I", "getScreenCompatibilityTip", "setScreenCompatibilityTip", "(Ljava/lang/String;)V", "getSoftware", "()Lcom/byd/overseaappstore/module/ability/data/upper/Software;", "getUpdateTime", "()J", "setUpdateTime", "(J)V", "getUpdateTimeTip", "setUpdateTimeTip", "getVersionDescription", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SoftwareDetail {

    @NotNull
    private final String description;

    @Nullable
    private final List<ImagePath> images;
    private final int screenCompatibility;

    @NotNull
    private String screenCompatibilityTip;

    @Nullable
    private final Software software;
    private long updateTime;

    @NotNull
    private String updateTimeTip;

    @NotNull
    private final String versionDescription;

    public SoftwareDetail(@Nullable Software software, long j, @NotNull String updateTimeTip, @NotNull String description, int i, @NotNull String screenCompatibilityTip, @Nullable List<ImagePath> list, @NotNull String versionDescription) {
        Intrinsics.checkNotNullParameter(updateTimeTip, "updateTimeTip");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(screenCompatibilityTip, "screenCompatibilityTip");
        Intrinsics.checkNotNullParameter(versionDescription, "versionDescription");
        this.software = software;
        this.updateTime = j;
        this.updateTimeTip = updateTimeTip;
        this.description = description;
        this.screenCompatibility = i;
        this.screenCompatibilityTip = screenCompatibilityTip;
        this.images = list;
        this.versionDescription = versionDescription;
    }

    public /* synthetic */ SoftwareDetail(Software software, long j, String str, String str2, int i, String str3, List list, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(software, j, str, (i2 & 8) != 0 ? "" : str2, i, str3, list, str4);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Software getSoftware() {
        return this.software;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getUpdateTime() {
        return this.updateTime;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getUpdateTimeTip() {
        return this.updateTimeTip;
    }

    @NotNull
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getScreenCompatibility() {
        return this.screenCompatibility;
    }

    @NotNull
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getScreenCompatibilityTip() {
        return this.screenCompatibilityTip;
    }

    @Nullable
    public final List<ImagePath> component7() {
        return this.images;
    }

    @NotNull
    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getVersionDescription() {
        return this.versionDescription;
    }

    @NotNull
    public final SoftwareDetail copy(@Nullable Software software, long updateTime, @NotNull String updateTimeTip, @NotNull String description, int screenCompatibility, @NotNull String screenCompatibilityTip, @Nullable List<ImagePath> images, @NotNull String versionDescription) {
        Intrinsics.checkNotNullParameter(updateTimeTip, "updateTimeTip");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(screenCompatibilityTip, "screenCompatibilityTip");
        Intrinsics.checkNotNullParameter(versionDescription, "versionDescription");
        return new SoftwareDetail(software, updateTime, updateTimeTip, description, screenCompatibility, screenCompatibilityTip, images, versionDescription);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SoftwareDetail)) {
            return false;
        }
        SoftwareDetail softwareDetail = (SoftwareDetail) other;
        return Intrinsics.areEqual(this.software, softwareDetail.software) && this.updateTime == softwareDetail.updateTime && Intrinsics.areEqual(this.updateTimeTip, softwareDetail.updateTimeTip) && Intrinsics.areEqual(this.description, softwareDetail.description) && this.screenCompatibility == softwareDetail.screenCompatibility && Intrinsics.areEqual(this.screenCompatibilityTip, softwareDetail.screenCompatibilityTip) && Intrinsics.areEqual(this.images, softwareDetail.images) && Intrinsics.areEqual(this.versionDescription, softwareDetail.versionDescription);
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final List<ImagePath> getImages() {
        return this.images;
    }

    public final int getScreenCompatibility() {
        return this.screenCompatibility;
    }

    @NotNull
    public final String getScreenCompatibilityTip() {
        return this.screenCompatibilityTip;
    }

    @Nullable
    public final Software getSoftware() {
        return this.software;
    }

    public final long getUpdateTime() {
        return this.updateTime;
    }

    @NotNull
    public final String getUpdateTimeTip() {
        return this.updateTimeTip;
    }

    @NotNull
    public final String getVersionDescription() {
        return this.versionDescription;
    }

    public int hashCode() {
        Software software = this.software;
        int iB = xi.b(this.screenCompatibilityTip, (Integer.hashCode(this.screenCompatibility) + xi.b(this.description, xi.b(this.updateTimeTip, (Long.hashCode(this.updateTime) + ((software == null ? 0 : software.hashCode()) * 31)) * 31, 31), 31)) * 31, 31);
        List<ImagePath> list = this.images;
        return this.versionDescription.hashCode() + ((iB + (list != null ? list.hashCode() : 0)) * 31);
    }

    public final void setScreenCompatibilityTip(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.screenCompatibilityTip = str;
    }

    public final void setUpdateTime(long j) {
        this.updateTime = j;
    }

    public final void setUpdateTimeTip(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.updateTimeTip = str;
    }

    @NotNull
    public String toString() {
        return "SoftwareDetail(software=" + this.software + ", updateTime=" + this.updateTime + ", updateTimeTip=" + this.updateTimeTip + ", description=" + this.description + ", screenCompatibility=" + this.screenCompatibility + ", screenCompatibilityTip=" + this.screenCompatibilityTip + ", images=" + this.images + ", versionDescription=" + this.versionDescription + ")";
    }
}
