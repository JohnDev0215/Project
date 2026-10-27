package com.amazon.music.platform.metrics.event;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.platform.PlatformProviderUtilKt;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UIContentViewWrapper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b,\b\u0086\b\u0018\u00002\u00020\u0001:\u00019BÉ\u0001\u0012\b\b\u0002\u0010\n\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u000e\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010!\u001a\u0004\u0018\u00010\b\u0012\b\u0010%\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010'\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b7\u00108J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\u00020\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0016\u0010\rR\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0018\u0010\rR\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u000b\u001a\u0004\b\u001a\u0010\rR\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010\u001eR\u0019\u0010!\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u0019\u0010%\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b%\u0010\u001c\u001a\u0004\b&\u0010\u001eR\u0019\u0010'\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b'\u0010\u001c\u001a\u0004\b(\u0010\u001eR\u0019\u0010)\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b)\u0010\u001c\u001a\u0004\b*\u0010\u001eR\u0019\u0010+\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b+\u0010\u000b\u001a\u0004\b,\u0010\rR\u0019\u0010-\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b-\u0010\u000b\u001a\u0004\b.\u0010\rR\u0019\u0010/\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b/\u0010\u000b\u001a\u0004\b0\u0010\rR\u0019\u00101\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b1\u0010\u000b\u001a\u0004\b2\u0010\rR\u0019\u00103\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b3\u0010\u000b\u001a\u0004\b4\u0010\rR\u0019\u00105\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b5\u0010\u000b\u001a\u0004\b6\u0010\r¨\u0006:"}, d2 = {"Lcom/amazon/music/platform/metrics/event/UIContentViewWrapper;", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "", "toString", "", "hashCode", "", "other", "", "equals", "metricsEventName", "Ljava/lang/String;", "getMetricsEventName", "()Ljava/lang/String;", "", "timeStamp", "J", "getTimeStamp", "()J", "eventVersion", "getEventVersion", "blockRef", "getBlockRef", "contentName", "getContentName", "containerType", "getContainerType", "containerIndex", "Ljava/lang/Long;", "getContainerIndex", "()Ljava/lang/Long;", "numItemsAccessible", "getNumItemsAccessible", "emittedOnView", "Ljava/lang/Boolean;", "getEmittedOnView", "()Ljava/lang/Boolean;", "firstViewableIndex", "getFirstViewableIndex", "lastViewableIndex", "getLastViewableIndex", "impressionTimestamp", "getImpressionTimestamp", ContentUtils.KEY_ACTIVITY_ID, "getActivityId", ContentUtils.KEY_ACTIVITY_ID_TYPE, "getActivityIdType", ContentUtils.KEY_RESPONSE_ID, "getResponseId", ContentUtils.KEY_RESPONSE_ID_TYPE, "getResponseIdType", ContentUtils.KEY_SEARCH_QUERY, "getSearchQuery", "pageType", "getPageType", "<init>", "(Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Builder", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class UIContentViewWrapper implements MetricsWrapper {
    private final String activityId;
    private final String activityIdType;
    private final String blockRef;
    private final Long containerIndex;
    private final String containerType;
    private final String contentName;
    private final Boolean emittedOnView;
    private final long eventVersion;
    private final Long firstViewableIndex;
    private final Long impressionTimestamp;
    private final Long lastViewableIndex;
    private final String metricsEventName;
    private final Long numItemsAccessible;
    private final String pageType;
    private final String responseId;
    private final String responseIdType;
    private final String searchQuery;
    private final long timeStamp;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UIContentViewWrapper)) {
            return false;
        }
        UIContentViewWrapper uIContentViewWrapper = (UIContentViewWrapper) other;
        return Intrinsics.areEqual(getMetricsEventName(), uIContentViewWrapper.getMetricsEventName()) && getTimeStamp() == uIContentViewWrapper.getTimeStamp() && this.eventVersion == uIContentViewWrapper.eventVersion && Intrinsics.areEqual(this.blockRef, uIContentViewWrapper.blockRef) && Intrinsics.areEqual(this.contentName, uIContentViewWrapper.contentName) && Intrinsics.areEqual(this.containerType, uIContentViewWrapper.containerType) && Intrinsics.areEqual(this.containerIndex, uIContentViewWrapper.containerIndex) && Intrinsics.areEqual(this.numItemsAccessible, uIContentViewWrapper.numItemsAccessible) && Intrinsics.areEqual(this.emittedOnView, uIContentViewWrapper.emittedOnView) && Intrinsics.areEqual(this.firstViewableIndex, uIContentViewWrapper.firstViewableIndex) && Intrinsics.areEqual(this.lastViewableIndex, uIContentViewWrapper.lastViewableIndex) && Intrinsics.areEqual(this.impressionTimestamp, uIContentViewWrapper.impressionTimestamp) && Intrinsics.areEqual(this.activityId, uIContentViewWrapper.activityId) && Intrinsics.areEqual(this.activityIdType, uIContentViewWrapper.activityIdType) && Intrinsics.areEqual(this.responseId, uIContentViewWrapper.responseId) && Intrinsics.areEqual(this.responseIdType, uIContentViewWrapper.responseIdType) && Intrinsics.areEqual(this.searchQuery, uIContentViewWrapper.searchQuery) && Intrinsics.areEqual(this.pageType, uIContentViewWrapper.pageType);
    }

    public int hashCode() {
        int iHashCode = ((((getMetricsEventName().hashCode() * 31) + Long.hashCode(getTimeStamp())) * 31) + Long.hashCode(this.eventVersion)) * 31;
        String str = this.blockRef;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.contentName;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.containerType;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Long l = this.containerIndex;
        int iHashCode5 = (iHashCode4 + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.numItemsAccessible;
        int iHashCode6 = (iHashCode5 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Boolean bool = this.emittedOnView;
        int iHashCode7 = (iHashCode6 + (bool == null ? 0 : bool.hashCode())) * 31;
        Long l3 = this.firstViewableIndex;
        int iHashCode8 = (iHashCode7 + (l3 == null ? 0 : l3.hashCode())) * 31;
        Long l4 = this.lastViewableIndex;
        int iHashCode9 = (iHashCode8 + (l4 == null ? 0 : l4.hashCode())) * 31;
        Long l5 = this.impressionTimestamp;
        int iHashCode10 = (iHashCode9 + (l5 == null ? 0 : l5.hashCode())) * 31;
        String str4 = this.activityId;
        int iHashCode11 = (iHashCode10 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.activityIdType;
        int iHashCode12 = (iHashCode11 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.responseId;
        int iHashCode13 = (iHashCode12 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.responseIdType;
        int iHashCode14 = (iHashCode13 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.searchQuery;
        int iHashCode15 = (iHashCode14 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.pageType;
        return iHashCode15 + (str9 != null ? str9.hashCode() : 0);
    }

    public String toString() {
        return "UIContentViewWrapper(metricsEventName=" + getMetricsEventName() + ", timeStamp=" + getTimeStamp() + ", eventVersion=" + this.eventVersion + ", blockRef=" + this.blockRef + ", contentName=" + this.contentName + ", containerType=" + this.containerType + ", containerIndex=" + this.containerIndex + ", numItemsAccessible=" + this.numItemsAccessible + ", emittedOnView=" + this.emittedOnView + ", firstViewableIndex=" + this.firstViewableIndex + ", lastViewableIndex=" + this.lastViewableIndex + ", impressionTimestamp=" + this.impressionTimestamp + ", activityId=" + this.activityId + ", activityIdType=" + this.activityIdType + ", responseId=" + this.responseId + ", responseIdType=" + this.responseIdType + ", searchQuery=" + this.searchQuery + ", pageType=" + this.pageType + ')';
    }

    public UIContentViewWrapper(String metricsEventName, long j, long j2, String str, String str2, String str3, Long l, Long l2, Boolean bool, Long l3, Long l4, Long l5, String str4, String str5, String str6, String str7, String str8, String str9) {
        Intrinsics.checkNotNullParameter(metricsEventName, "metricsEventName");
        this.metricsEventName = metricsEventName;
        this.timeStamp = j;
        this.eventVersion = j2;
        this.blockRef = str;
        this.contentName = str2;
        this.containerType = str3;
        this.containerIndex = l;
        this.numItemsAccessible = l2;
        this.emittedOnView = bool;
        this.firstViewableIndex = l3;
        this.lastViewableIndex = l4;
        this.impressionTimestamp = l5;
        this.activityId = str4;
        this.activityIdType = str5;
        this.responseId = str6;
        this.responseIdType = str7;
        this.searchQuery = str8;
        this.pageType = str9;
        PlatformProviderUtilKt.freeze(this);
    }

    public String getMetricsEventName() {
        return this.metricsEventName;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public final String getBlockRef() {
        return this.blockRef;
    }

    public final String getContentName() {
        return this.contentName;
    }

    public final String getContainerType() {
        return this.containerType;
    }

    public final Long getContainerIndex() {
        return this.containerIndex;
    }

    public final Long getNumItemsAccessible() {
        return this.numItemsAccessible;
    }

    public final Boolean getEmittedOnView() {
        return this.emittedOnView;
    }

    public final Long getImpressionTimestamp() {
        return this.impressionTimestamp;
    }

    public final String getActivityId() {
        return this.activityId;
    }

    public final String getActivityIdType() {
        return this.activityIdType;
    }

    public final String getResponseId() {
        return this.responseId;
    }

    public final String getResponseIdType() {
        return this.responseIdType;
    }

    public final String getSearchQuery() {
        return this.searchQuery;
    }

    public final String getPageType() {
        return this.pageType;
    }

    /* JADX INFO: compiled from: UIContentViewWrapper.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 02\u00020\u0001:\u00010B\u0007¢\u0006\u0004\b.\u0010/J\u0010\u0010\u0004\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0006\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\b\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002J\u0017\u0010\u000b\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\fJ\u0017\u0010\u0011\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0014\u0010\fJ\u0017\u0010\u0016\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0016\u0010\fJ\u0017\u0010\u0017\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0017\u0010\fJ\u0017\u0010\u0019\u001a\u00020\u00002\b\u0010\u0018\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0019\u0010\fJ\u0010\u0010\u001b\u001a\u00020\u00002\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u001d\u001a\u00020\u00002\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u001f\u001a\u00020\u00002\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002J\u0010\u0010!\u001a\u00020\u00002\b\u0010 \u001a\u0004\u0018\u00010\u0002J\u0010\u0010#\u001a\u00020\u00002\b\u0010\"\u001a\u0004\u0018\u00010\u0002J\u0010\u0010%\u001a\u00020\u00002\b\u0010$\u001a\u0004\u0018\u00010\u0002J\u0006\u0010'\u001a\u00020&R\u0016\u0010(\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010\r\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010)R\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010*R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010+R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010,R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010,R\u0018\u0010\n\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010+R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010,R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010+R\u0018\u0010-\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010+R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010+R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010,R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010,R\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010,R\u0018\u0010 \u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010,R\u0018\u0010\"\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010,R\u0018\u0010$\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010,¨\u00061"}, d2 = {"Lcom/amazon/music/platform/metrics/event/UIContentViewWrapper$Builder;", "", "", "blockRef", "withBlockRef", "contentName", "withContentName", "containerType", "withContainerType", "", "containerIndex", "withContainerIndex", "(Ljava/lang/Long;)Lcom/amazon/music/platform/metrics/event/UIContentViewWrapper$Builder;", "eventTime", "withEventTime", "", "emittedOnView", "withEmittedOnView", "(Ljava/lang/Boolean;)Lcom/amazon/music/platform/metrics/event/UIContentViewWrapper$Builder;", "numItemsAccessible", "withNumItemsAccessible", "firstViewableIndex", "withFirstViewableIndex", "withLastViewableIndex", "impressionTimestamp", "withImpressionTimestamp", ContentUtils.KEY_ACTIVITY_ID, "withActivityId", ContentUtils.KEY_ACTIVITY_ID_TYPE, "withActivityIdType", ContentUtils.KEY_RESPONSE_ID, "withResponseId", ContentUtils.KEY_RESPONSE_ID_TYPE, "withResponseIdType", ContentUtils.KEY_SEARCH_QUERY, "withSearchQuery", "pageType", "withPageType", "Lcom/amazon/music/platform/metrics/event/UIContentViewWrapper;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "eventVersion", "J", "Z", "Ljava/lang/Long;", "Ljava/lang/String;", "lastViewableIndex", "<init>", "()V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class Builder {
        private String activityId;
        private String activityIdType;
        private String blockRef;
        private Long containerIndex;
        private String containerType;
        private String contentName;
        private Long firstViewableIndex;
        private Long impressionTimestamp;
        private Long lastViewableIndex;
        private Long numItemsAccessible;
        private String pageType;
        private String responseId;
        private String responseIdType;
        private String searchQuery;
        private long eventVersion = 6;
        private long eventTime = PlatformProviderUtilKt.getCurrentEpochTimeMillis();
        private boolean emittedOnView = true;

        public final Builder withBlockRef(String blockRef) {
            this.blockRef = blockRef;
            return this;
        }

        public final Builder withContentName(String contentName) {
            this.contentName = contentName;
            return this;
        }

        public final Builder withContainerType(String containerType) {
            this.containerType = containerType;
            return this;
        }

        public final Builder withContainerIndex(Long containerIndex) {
            this.containerIndex = containerIndex;
            return this;
        }

        public final Builder withEventTime(Long eventTime) {
            if (eventTime != null) {
                eventTime.longValue();
                this.eventTime = eventTime.longValue();
            }
            return this;
        }

        public final Builder withEmittedOnView(Boolean emittedOnView) {
            if (emittedOnView != null) {
                emittedOnView.booleanValue();
                this.emittedOnView = emittedOnView.booleanValue();
            }
            return this;
        }

        public final Builder withNumItemsAccessible(Long numItemsAccessible) {
            this.numItemsAccessible = numItemsAccessible;
            return this;
        }

        public final Builder withFirstViewableIndex(Long firstViewableIndex) {
            this.firstViewableIndex = firstViewableIndex;
            return this;
        }

        public final Builder withLastViewableIndex(Long firstViewableIndex) {
            this.lastViewableIndex = firstViewableIndex;
            return this;
        }

        public final Builder withImpressionTimestamp(Long impressionTimestamp) {
            this.impressionTimestamp = impressionTimestamp;
            return this;
        }

        public final Builder withActivityId(String activityId) {
            this.activityId = activityId;
            return this;
        }

        public final Builder withActivityIdType(String activityIdType) {
            this.activityIdType = activityIdType;
            return this;
        }

        public final Builder withResponseId(String responseId) {
            this.responseId = responseId;
            return this;
        }

        public final Builder withResponseIdType(String responseIdType) {
            this.responseIdType = responseIdType;
            return this;
        }

        public final Builder withSearchQuery(String searchQuery) {
            this.searchQuery = searchQuery;
            return this;
        }

        public final Builder withPageType(String pageType) {
            this.pageType = pageType;
            return this;
        }

        public final UIContentViewWrapper build() {
            long j = this.eventTime;
            boolean z = this.emittedOnView;
            String str = this.blockRef;
            String str2 = this.contentName;
            Long l = this.containerIndex;
            String str3 = this.containerType;
            Long l2 = this.numItemsAccessible;
            return new UIContentViewWrapper("uiContentView", j, this.eventVersion, str, str2, str3, l, l2, Boolean.valueOf(z), this.firstViewableIndex, this.lastViewableIndex, this.impressionTimestamp, this.activityId, this.activityIdType, this.responseId, this.responseIdType, this.searchQuery, this.pageType);
        }
    }
}
