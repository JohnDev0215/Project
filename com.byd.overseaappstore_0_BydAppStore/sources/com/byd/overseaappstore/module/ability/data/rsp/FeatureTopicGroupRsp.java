package com.byd.overseaappstore.module.ability.data.rsp;

import com.byd.car.locale.ccTLD;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b¢\u0006\u0002\u0010\fJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\u0011\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bHÆ\u0003JV\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\u0005HÖ\u0001R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006\""}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/rsp/FeatureTopicGroupRsp;", "", ccTLD.INDONESIA, "", "title", "", "pictureUrl", "topicTitleList", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/TopicGroupTitleRsp;", "appInfoList", "Lcom/byd/overseaappstore/module/ability/data/rsp/FeatureTopicSoftwareRsp;", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getAppInfoList", "()Ljava/util/List;", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPictureUrl", "()Ljava/lang/String;", "getTitle", "getTopicTitleList", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)Lcom/byd/overseaappstore/module/ability/data/rsp/FeatureTopicGroupRsp;", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class FeatureTopicGroupRsp {

    @Nullable
    private final List<FeatureTopicSoftwareRsp> appInfoList;

    @Nullable
    private final Integer id;

    @Nullable
    private final String pictureUrl;

    @Nullable
    private final String title;

    @Nullable
    private final List<TopicGroupTitleRsp> topicTitleList;

    public FeatureTopicGroupRsp(@Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable List<TopicGroupTitleRsp> list, @Nullable List<FeatureTopicSoftwareRsp> list2) {
        this.id = num;
        this.title = str;
        this.pictureUrl = str2;
        this.topicTitleList = list;
        this.appInfoList = list2;
    }

    public /* synthetic */ FeatureTopicGroupRsp(Integer num, String str, String str2, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, str, str2, list, (i & 16) != 0 ? null : list2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FeatureTopicGroupRsp copy$default(FeatureTopicGroupRsp featureTopicGroupRsp, Integer num, String str, String str2, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = featureTopicGroupRsp.id;
        }
        if ((i & 2) != 0) {
            str = featureTopicGroupRsp.title;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            str2 = featureTopicGroupRsp.pictureUrl;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            list = featureTopicGroupRsp.topicTitleList;
        }
        List list3 = list;
        if ((i & 16) != 0) {
            list2 = featureTopicGroupRsp.appInfoList;
        }
        return featureTopicGroupRsp.copy(num, str3, str4, list3, list2);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPictureUrl() {
        return this.pictureUrl;
    }

    @Nullable
    public final List<TopicGroupTitleRsp> component4() {
        return this.topicTitleList;
    }

    @Nullable
    public final List<FeatureTopicSoftwareRsp> component5() {
        return this.appInfoList;
    }

    @NotNull
    public final FeatureTopicGroupRsp copy(@Nullable Integer id, @Nullable String title, @Nullable String pictureUrl, @Nullable List<TopicGroupTitleRsp> topicTitleList, @Nullable List<FeatureTopicSoftwareRsp> appInfoList) {
        return new FeatureTopicGroupRsp(id, title, pictureUrl, topicTitleList, appInfoList);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FeatureTopicGroupRsp)) {
            return false;
        }
        FeatureTopicGroupRsp featureTopicGroupRsp = (FeatureTopicGroupRsp) other;
        return Intrinsics.areEqual(this.id, featureTopicGroupRsp.id) && Intrinsics.areEqual(this.title, featureTopicGroupRsp.title) && Intrinsics.areEqual(this.pictureUrl, featureTopicGroupRsp.pictureUrl) && Intrinsics.areEqual(this.topicTitleList, featureTopicGroupRsp.topicTitleList) && Intrinsics.areEqual(this.appInfoList, featureTopicGroupRsp.appInfoList);
    }

    @Nullable
    public final List<FeatureTopicSoftwareRsp> getAppInfoList() {
        return this.appInfoList;
    }

    @Nullable
    public final Integer getId() {
        return this.id;
    }

    @Nullable
    public final String getPictureUrl() {
        return this.pictureUrl;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    @Nullable
    public final List<TopicGroupTitleRsp> getTopicTitleList() {
        return this.topicTitleList;
    }

    public int hashCode() {
        Integer num = this.id;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.title;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.pictureUrl;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        List<TopicGroupTitleRsp> list = this.topicTitleList;
        int iHashCode4 = (iHashCode3 + (list == null ? 0 : list.hashCode())) * 31;
        List<FeatureTopicSoftwareRsp> list2 = this.appInfoList;
        return iHashCode4 + (list2 != null ? list2.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "FeatureTopicGroupRsp(id=" + this.id + ", title=" + this.title + ", pictureUrl=" + this.pictureUrl + ", topicTitleList=" + this.topicTitleList + ", appInfoList=" + this.appInfoList + ")";
    }
}
