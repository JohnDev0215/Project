package com.byd.overseaappstore.module.ability.data.upper;

import com.byd.car.locale.ccTLD;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b¢\u0006\u0002\u0010\fJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\u0011\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bHÆ\u0003JK\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012¨\u0006$"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/upper/FeatureThemTopicGroup;", "", ccTLD.INDONESIA, "", "title", "", "picUrl", "topicTitleList", "", "Lcom/byd/overseaappstore/module/ability/data/upper/TopicGroupTitle;", "softwareList", "Lcom/byd/overseaappstore/module/ability/data/upper/Software;", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getId", "()I", "getPicUrl", "()Ljava/lang/String;", "getSoftwareList", "()Ljava/util/List;", "setSoftwareList", "(Ljava/util/List;)V", "getTitle", "setTitle", "(Ljava/lang/String;)V", "getTopicTitleList", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class FeatureThemTopicGroup {
    private final int id;

    @NotNull
    private final String picUrl;

    @Nullable
    private List<? extends Software> softwareList;

    @NotNull
    private String title;

    @Nullable
    private final List<TopicGroupTitle> topicTitleList;

    public FeatureThemTopicGroup() {
        this(0, null, null, null, null, 31, null);
    }

    public FeatureThemTopicGroup(int i, @NotNull String title, @NotNull String picUrl, @Nullable List<TopicGroupTitle> list, @Nullable List<? extends Software> list2) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(picUrl, "picUrl");
        this.id = i;
        this.title = title;
        this.picUrl = picUrl;
        this.topicTitleList = list;
        this.softwareList = list2;
    }

    public /* synthetic */ FeatureThemTopicGroup(int i, String str, String str2, List list, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? "" : str, (i2 & 4) == 0 ? str2 : "", (i2 & 8) != 0 ? null : list, (i2 & 16) != 0 ? null : list2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FeatureThemTopicGroup copy$default(FeatureThemTopicGroup featureThemTopicGroup, int i, String str, String str2, List list, List list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = featureThemTopicGroup.id;
        }
        if ((i2 & 2) != 0) {
            str = featureThemTopicGroup.title;
        }
        String str3 = str;
        if ((i2 & 4) != 0) {
            str2 = featureThemTopicGroup.picUrl;
        }
        String str4 = str2;
        if ((i2 & 8) != 0) {
            list = featureThemTopicGroup.topicTitleList;
        }
        List list3 = list;
        if ((i2 & 16) != 0) {
            list2 = featureThemTopicGroup.softwareList;
        }
        return featureThemTopicGroup.copy(i, str3, str4, list3, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPicUrl() {
        return this.picUrl;
    }

    @Nullable
    public final List<TopicGroupTitle> component4() {
        return this.topicTitleList;
    }

    @Nullable
    public final List<Software> component5() {
        return this.softwareList;
    }

    @NotNull
    public final FeatureThemTopicGroup copy(int id, @NotNull String title, @NotNull String picUrl, @Nullable List<TopicGroupTitle> topicTitleList, @Nullable List<? extends Software> softwareList) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(picUrl, "picUrl");
        return new FeatureThemTopicGroup(id, title, picUrl, topicTitleList, softwareList);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FeatureThemTopicGroup)) {
            return false;
        }
        FeatureThemTopicGroup featureThemTopicGroup = (FeatureThemTopicGroup) other;
        return this.id == featureThemTopicGroup.id && Intrinsics.areEqual(this.title, featureThemTopicGroup.title) && Intrinsics.areEqual(this.picUrl, featureThemTopicGroup.picUrl) && Intrinsics.areEqual(this.topicTitleList, featureThemTopicGroup.topicTitleList) && Intrinsics.areEqual(this.softwareList, featureThemTopicGroup.softwareList);
    }

    public final int getId() {
        return this.id;
    }

    @NotNull
    public final String getPicUrl() {
        return this.picUrl;
    }

    @Nullable
    public final List<Software> getSoftwareList() {
        return this.softwareList;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @Nullable
    public final List<TopicGroupTitle> getTopicTitleList() {
        return this.topicTitleList;
    }

    public int hashCode() {
        int iB = xi.b(this.picUrl, xi.b(this.title, Integer.hashCode(this.id) * 31, 31), 31);
        List<TopicGroupTitle> list = this.topicTitleList;
        int iHashCode = (iB + (list == null ? 0 : list.hashCode())) * 31;
        List<? extends Software> list2 = this.softwareList;
        return iHashCode + (list2 != null ? list2.hashCode() : 0);
    }

    public final void setSoftwareList(@Nullable List<? extends Software> list) {
        this.softwareList = list;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    @NotNull
    public String toString() {
        int i = this.id;
        String str = this.title;
        String str2 = this.picUrl;
        List<TopicGroupTitle> list = this.topicTitleList;
        List<? extends Software> list2 = this.softwareList;
        StringBuilder sbH = xi.h("FeatureThemTopicGroup(id=", i, ", title=", str, ", picUrl=");
        sbH.append(str2);
        sbH.append(", topicTitleList=");
        sbH.append(list);
        sbH.append(", softwareList=");
        sbH.append(list2);
        sbH.append(")");
        return sbH.toString();
    }
}
