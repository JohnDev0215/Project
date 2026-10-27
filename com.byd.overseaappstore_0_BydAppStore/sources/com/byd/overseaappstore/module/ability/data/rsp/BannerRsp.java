package com.byd.overseaappstore.module.ability.data.rsp;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\u0010\nJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003JB\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/rsp/BannerRsp;", "", "int", "", "pictureUrl", "", "featuredAdvertiseType", "appInfos", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/BannerAppRsp;", "(Ljava/lang/Integer;Ljava/lang/String;ILjava/util/List;)V", "getAppInfos", "()Ljava/util/List;", "getFeaturedAdvertiseType", "()I", "getInt", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPictureUrl", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;ILjava/util/List;)Lcom/byd/overseaappstore/module/ability/data/rsp/BannerRsp;", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class BannerRsp {

    @Nullable
    private final List<BannerAppRsp> appInfos;
    private final int featuredAdvertiseType;

    @Nullable
    private final Integer int;

    @Nullable
    private final String pictureUrl;

    public BannerRsp(@Nullable Integer num, @Nullable String str, int i, @Nullable List<BannerAppRsp> list) {
        this.int = num;
        this.pictureUrl = str;
        this.featuredAdvertiseType = i;
        this.appInfos = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BannerRsp copy$default(BannerRsp bannerRsp, Integer num, String str, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = bannerRsp.int;
        }
        if ((i2 & 2) != 0) {
            str = bannerRsp.pictureUrl;
        }
        if ((i2 & 4) != 0) {
            i = bannerRsp.featuredAdvertiseType;
        }
        if ((i2 & 8) != 0) {
            list = bannerRsp.appInfos;
        }
        return bannerRsp.copy(num, str, i, list);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getInt() {
        return this.int;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPictureUrl() {
        return this.pictureUrl;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getFeaturedAdvertiseType() {
        return this.featuredAdvertiseType;
    }

    @Nullable
    public final List<BannerAppRsp> component4() {
        return this.appInfos;
    }

    @NotNull
    public final BannerRsp copy(@Nullable Integer num, @Nullable String pictureUrl, int featuredAdvertiseType, @Nullable List<BannerAppRsp> appInfos) {
        return new BannerRsp(num, pictureUrl, featuredAdvertiseType, appInfos);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BannerRsp)) {
            return false;
        }
        BannerRsp bannerRsp = (BannerRsp) other;
        return Intrinsics.areEqual(this.int, bannerRsp.int) && Intrinsics.areEqual(this.pictureUrl, bannerRsp.pictureUrl) && this.featuredAdvertiseType == bannerRsp.featuredAdvertiseType && Intrinsics.areEqual(this.appInfos, bannerRsp.appInfos);
    }

    @Nullable
    public final List<BannerAppRsp> getAppInfos() {
        return this.appInfos;
    }

    public final int getFeaturedAdvertiseType() {
        return this.featuredAdvertiseType;
    }

    @Nullable
    public final Integer getInt() {
        return this.int;
    }

    @Nullable
    public final String getPictureUrl() {
        return this.pictureUrl;
    }

    public int hashCode() {
        Integer num = this.int;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.pictureUrl;
        int iHashCode2 = (Integer.hashCode(this.featuredAdvertiseType) + ((iHashCode + (str == null ? 0 : str.hashCode())) * 31)) * 31;
        List<BannerAppRsp> list = this.appInfos;
        return iHashCode2 + (list != null ? list.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "BannerRsp(int=" + this.int + ", pictureUrl=" + this.pictureUrl + ", featuredAdvertiseType=" + this.featuredAdvertiseType + ", appInfos=" + this.appInfos + ")";
    }
}
