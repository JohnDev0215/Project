package com.amazon.music.platform.metrics.event;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.PlatformProviderUtilKt;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UIClickWrapper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0010 \n\u0002\u0010$\n\u0002\b\"\b\u0086\b\u0018\u00002\u00020\u0001:\u0001IB½\u0002\u0012\b\b\u0002\u0010\n\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0002\u0012\u001c\b\u0002\u0010)\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020(\u0018\u00010'\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010/\u001a\u00020\u0004\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\bG\u0010HJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\u00020\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0016\u0010\rR\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0018\u0010\rR\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u000b\u001a\u0004\b\u001a\u0010\rR\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u000b\u001a\u0004\b\u001c\u0010\rR\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\rR\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u000b\u001a\u0004\b \u0010\rR\u0019\u0010!\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b!\u0010\u000b\u001a\u0004\b\"\u0010\rR\u0019\u0010#\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\rR\u0019\u0010%\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b%\u0010\u000b\u001a\u0004\b&\u0010\rR+\u0010)\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020(\u0018\u00010'8\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u0019\u0010-\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b-\u0010\u000b\u001a\u0004\b.\u0010\rR\u0017\u0010/\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R\u0019\u00103\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b3\u0010\u000b\u001a\u0004\b4\u0010\rR\u0019\u00105\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b5\u0010\u000b\u001a\u0004\b6\u0010\rR\u0019\u00107\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b7\u0010\u000b\u001a\u0004\b8\u0010\rR\u0019\u00109\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b9\u0010\u000b\u001a\u0004\b:\u0010\rR\u0019\u0010;\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b;\u0010\u000b\u001a\u0004\b<\u0010\rR\u0019\u0010=\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b=\u0010\u000b\u001a\u0004\b>\u0010\rR\u0019\u0010?\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b?\u0010\u000b\u001a\u0004\b@\u0010\rR\u0019\u0010A\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\bA\u0010\u000b\u001a\u0004\bB\u0010\rR\u0019\u0010C\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\bC\u0010\u000b\u001a\u0004\bD\u0010\rR\u0019\u0010E\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\bE\u0010\u000b\u001a\u0004\bF\u0010\r¨\u0006J"}, d2 = {"Lcom/amazon/music/platform/metrics/event/UIClickWrapper;", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "", "toString", "", "hashCode", "", "other", "", "equals", "metricsEventName", "Ljava/lang/String;", "getMetricsEventName", "()Ljava/lang/String;", "", "timeStamp", "J", "getTimeStamp", "()J", "eventVersion", "getEventVersion", "actionType", "getActionType", ImagesContract.URL, "getUrl", "blockRef", "getBlockRef", "interactionType", "getInteractionType", "pageType", "getPageType", "pageSubType", "getPageSubType", "refMarker", "getRefMarker", ContextMappingConstants.ENTITY_ID, "getEntityId", ContextMappingConstants.ENTITY_ID_TYPE, "getEntityIdType", "", "", ContextMappingConstants.CONTENT_INFO, "Ljava/util/List;", "getContentInfo", "()Ljava/util/List;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "getEntityType", "entityPos", "I", "getEntityPos", "()I", "experienceMode", "getExperienceMode", "localContentId", "getLocalContentId", ContentUtils.KEY_ACTIVITY_ID, "getActivityId", ContentUtils.KEY_ACTIVITY_ID_TYPE, "getActivityIdType", ContentUtils.KEY_RESPONSE_ID, "getResponseId", ContentUtils.KEY_RESPONSE_ID_TYPE, "getResponseIdType", ContentUtils.KEY_SEARCH_QUERY, "getSearchQuery", "pageEntityId", "getPageEntityId", "pageEntityIdType", "getPageEntityIdType", "pageEntityType", "getPageEntityType", "<init>", "(Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Builder", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class UIClickWrapper implements MetricsWrapper {
    private final String actionType;
    private final String activityId;
    private final String activityIdType;
    private final String blockRef;
    private final List<Map<String, String>> contentInfo;
    private final String entityId;
    private final String entityIdType;
    private final int entityPos;
    private final String entityType;
    private final long eventVersion;
    private final String experienceMode;
    private final String interactionType;
    private final String localContentId;
    private final String metricsEventName;
    private final String pageEntityId;
    private final String pageEntityIdType;
    private final String pageEntityType;
    private final String pageSubType;
    private final String pageType;
    private final String refMarker;
    private final String responseId;
    private final String responseIdType;
    private final String searchQuery;
    private final long timeStamp;
    private final String url;

    public UIClickWrapper() {
        this(null, 0L, 0L, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, null, null, null, null, null, 33554431, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UIClickWrapper)) {
            return false;
        }
        UIClickWrapper uIClickWrapper = (UIClickWrapper) other;
        return Intrinsics.areEqual(getMetricsEventName(), uIClickWrapper.getMetricsEventName()) && getTimeStamp() == uIClickWrapper.getTimeStamp() && this.eventVersion == uIClickWrapper.eventVersion && Intrinsics.areEqual(this.actionType, uIClickWrapper.actionType) && Intrinsics.areEqual(this.url, uIClickWrapper.url) && Intrinsics.areEqual(this.blockRef, uIClickWrapper.blockRef) && Intrinsics.areEqual(this.interactionType, uIClickWrapper.interactionType) && Intrinsics.areEqual(this.pageType, uIClickWrapper.pageType) && Intrinsics.areEqual(this.pageSubType, uIClickWrapper.pageSubType) && Intrinsics.areEqual(this.refMarker, uIClickWrapper.refMarker) && Intrinsics.areEqual(this.entityId, uIClickWrapper.entityId) && Intrinsics.areEqual(this.entityIdType, uIClickWrapper.entityIdType) && Intrinsics.areEqual(this.contentInfo, uIClickWrapper.contentInfo) && Intrinsics.areEqual(this.entityType, uIClickWrapper.entityType) && this.entityPos == uIClickWrapper.entityPos && Intrinsics.areEqual(this.experienceMode, uIClickWrapper.experienceMode) && Intrinsics.areEqual(this.localContentId, uIClickWrapper.localContentId) && Intrinsics.areEqual(this.activityId, uIClickWrapper.activityId) && Intrinsics.areEqual(this.activityIdType, uIClickWrapper.activityIdType) && Intrinsics.areEqual(this.responseId, uIClickWrapper.responseId) && Intrinsics.areEqual(this.responseIdType, uIClickWrapper.responseIdType) && Intrinsics.areEqual(this.searchQuery, uIClickWrapper.searchQuery) && Intrinsics.areEqual(this.pageEntityId, uIClickWrapper.pageEntityId) && Intrinsics.areEqual(this.pageEntityIdType, uIClickWrapper.pageEntityIdType) && Intrinsics.areEqual(this.pageEntityType, uIClickWrapper.pageEntityType);
    }

    public int hashCode() {
        int iHashCode = ((((getMetricsEventName().hashCode() * 31) + Long.hashCode(getTimeStamp())) * 31) + Long.hashCode(this.eventVersion)) * 31;
        String str = this.actionType;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.url;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.blockRef;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.interactionType;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.pageType;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.pageSubType;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.refMarker;
        int iHashCode8 = (iHashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.entityId;
        int iHashCode9 = (iHashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.entityIdType;
        int iHashCode10 = (iHashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
        List<Map<String, String>> list = this.contentInfo;
        int iHashCode11 = (iHashCode10 + (list == null ? 0 : list.hashCode())) * 31;
        String str10 = this.entityType;
        int iHashCode12 = (((iHashCode11 + (str10 == null ? 0 : str10.hashCode())) * 31) + Integer.hashCode(this.entityPos)) * 31;
        String str11 = this.experienceMode;
        int iHashCode13 = (iHashCode12 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.localContentId;
        int iHashCode14 = (iHashCode13 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.activityId;
        int iHashCode15 = (iHashCode14 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.activityIdType;
        int iHashCode16 = (iHashCode15 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.responseId;
        int iHashCode17 = (iHashCode16 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.responseIdType;
        int iHashCode18 = (iHashCode17 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.searchQuery;
        int iHashCode19 = (iHashCode18 + (str17 == null ? 0 : str17.hashCode())) * 31;
        String str18 = this.pageEntityId;
        int iHashCode20 = (iHashCode19 + (str18 == null ? 0 : str18.hashCode())) * 31;
        String str19 = this.pageEntityIdType;
        int iHashCode21 = (iHashCode20 + (str19 == null ? 0 : str19.hashCode())) * 31;
        String str20 = this.pageEntityType;
        return iHashCode21 + (str20 != null ? str20.hashCode() : 0);
    }

    public String toString() {
        return "UIClickWrapper(metricsEventName=" + getMetricsEventName() + ", timeStamp=" + getTimeStamp() + ", eventVersion=" + this.eventVersion + ", actionType=" + this.actionType + ", url=" + this.url + ", blockRef=" + this.blockRef + ", interactionType=" + this.interactionType + ", pageType=" + this.pageType + ", pageSubType=" + this.pageSubType + ", refMarker=" + this.refMarker + ", entityId=" + this.entityId + ", entityIdType=" + this.entityIdType + ", contentInfo=" + this.contentInfo + ", entityType=" + this.entityType + ", entityPos=" + this.entityPos + ", experienceMode=" + this.experienceMode + ", localContentId=" + this.localContentId + ", activityId=" + this.activityId + ", activityIdType=" + this.activityIdType + ", responseId=" + this.responseId + ", responseIdType=" + this.responseIdType + ", searchQuery=" + this.searchQuery + ", pageEntityId=" + this.pageEntityId + ", pageEntityIdType=" + this.pageEntityIdType + ", pageEntityType=" + this.pageEntityType + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public UIClickWrapper(String metricsEventName, long j, long j2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, List<? extends Map<String, String>> list, String str10, int i, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20) {
        Intrinsics.checkNotNullParameter(metricsEventName, "metricsEventName");
        this.metricsEventName = metricsEventName;
        this.timeStamp = j;
        this.eventVersion = j2;
        this.actionType = str;
        this.url = str2;
        this.blockRef = str3;
        this.interactionType = str4;
        this.pageType = str5;
        this.pageSubType = str6;
        this.refMarker = str7;
        this.entityId = str8;
        this.entityIdType = str9;
        this.contentInfo = list;
        this.entityType = str10;
        this.entityPos = i;
        this.experienceMode = str11;
        this.localContentId = str12;
        this.activityId = str13;
        this.activityIdType = str14;
        this.responseId = str15;
        this.responseIdType = str16;
        this.searchQuery = str17;
        this.pageEntityId = str18;
        this.pageEntityIdType = str19;
        this.pageEntityType = str20;
        PlatformProviderUtilKt.freeze(this);
    }

    public String getMetricsEventName() {
        return this.metricsEventName;
    }

    public /* synthetic */ UIClickWrapper(String str, long j, long j2, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, List list, String str11, int i, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "uiClick" : str, (i2 & 2) != 0 ? PlatformProviderUtilKt.getCurrentEpochTimeMillis() : j, (i2 & 4) != 0 ? 5L : j2, (i2 & 8) != 0 ? null : str2, (i2 & 16) != 0 ? null : str3, (i2 & 32) != 0 ? null : str4, (i2 & 64) != 0 ? null : str5, (i2 & 128) != 0 ? null : str6, (i2 & 256) != 0 ? null : str7, (i2 & 512) != 0 ? null : str8, (i2 & 1024) != 0 ? null : str9, (i2 & 2048) != 0 ? null : str10, (i2 & 4096) != 0 ? null : list, (i2 & 8192) != 0 ? null : str11, (i2 & 16384) != 0 ? -1 : i, (i2 & 32768) != 0 ? null : str12, (i2 & 65536) != 0 ? null : str13, (i2 & 131072) != 0 ? null : str14, (i2 & 262144) != 0 ? null : str15, (i2 & 524288) != 0 ? null : str16, (i2 & 1048576) != 0 ? null : str17, (i2 & 2097152) != 0 ? null : str18, (i2 & 4194304) != 0 ? null : str19, (i2 & 8388608) != 0 ? null : str20, (i2 & 16777216) != 0 ? null : str21);
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public final String getActionType() {
        return this.actionType;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getBlockRef() {
        return this.blockRef;
    }

    public final String getInteractionType() {
        return this.interactionType;
    }

    public final String getPageType() {
        return this.pageType;
    }

    public final String getPageSubType() {
        return this.pageSubType;
    }

    public final String getRefMarker() {
        return this.refMarker;
    }

    public final String getEntityId() {
        return this.entityId;
    }

    public final String getEntityIdType() {
        return this.entityIdType;
    }

    public final List<Map<String, String>> getContentInfo() {
        return this.contentInfo;
    }

    public final String getEntityType() {
        return this.entityType;
    }

    public final int getEntityPos() {
        return this.entityPos;
    }

    public final String getLocalContentId() {
        return this.localContentId;
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

    public final String getPageEntityId() {
        return this.pageEntityId;
    }

    public final String getPageEntityIdType() {
        return this.pageEntityIdType;
    }

    public final String getPageEntityType() {
        return this.pageEntityType;
    }

    /* JADX INFO: compiled from: UIClickWrapper.kt */
    @Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0010$\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0007\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0007¢\u0006\u0004\b=\u0010>J\u0010\u0010\u0004\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0006\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\b\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\n\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\f\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u000e\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0010\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0012\u001a\u00020\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0014\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002J\u0017\u0010\u0017\u001a\u00020\u00002\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u00002\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u001c\u001a\u00020\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002J\"\u0010 \u001a\u00020\u00002\u001a\u0010\u001f\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u001e\u0018\u00010\u001dJ\u0010\u0010\"\u001a\u00020\u00002\b\u0010!\u001a\u0004\u0018\u00010\u0002J\u0010\u0010$\u001a\u00020\u00002\b\u0010#\u001a\u0004\u0018\u00010\u0002J\u0010\u0010&\u001a\u00020\u00002\b\u0010%\u001a\u0004\u0018\u00010\u0002J\u0010\u0010(\u001a\u00020\u00002\b\u0010'\u001a\u0004\u0018\u00010\u0002J\u0010\u0010*\u001a\u00020\u00002\b\u0010)\u001a\u0004\u0018\u00010\u0002J\u0010\u0010,\u001a\u00020\u00002\b\u0010+\u001a\u0004\u0018\u00010\u0002J\u0010\u0010.\u001a\u00020\u00002\b\u0010-\u001a\u0004\u0018\u00010\u0002J\u0010\u00100\u001a\u00020\u00002\b\u0010/\u001a\u0004\u0018\u00010\u0002J\u0010\u00102\u001a\u00020\u00002\b\u00101\u001a\u0004\u0018\u00010\u0002J\u0006\u00104\u001a\u000203R\u0016\u00106\u001a\u0002058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u00108R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u00108R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u00108R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u00108R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u00108R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u00108R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u00108R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u00108R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u00108R&\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u001e098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010:R\u0016\u0010;\u001a\u0002058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u00107R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u00108R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010<R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u00108R\u0018\u0010!\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u00108R\u0018\u0010#\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u00108R\u0018\u0010%\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u00108R\u0018\u0010'\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u00108R\u0018\u0010)\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u00108R\u0018\u0010+\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u00108R\u0018\u0010-\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u00108R\u0018\u0010/\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00108R\u0018\u00101\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00108¨\u0006@"}, d2 = {"Lcom/amazon/music/platform/metrics/event/UIClickWrapper$Builder;", "", "", "actionType", "withActionType", ImagesContract.URL, "withUrl", "blockRef", "withBlockRef", "pageType", "withPageType", "pageSubType", "withPageSubType", "refMarker", "withRefMarker", ContextMappingConstants.ENTITY_ID, "withEntityId", ContextMappingConstants.ENTITY_ID_TYPE, "withEntityIdType", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "withEntityType", "", "entityPos", "withEntityPos", "(Ljava/lang/Integer;)Lcom/amazon/music/platform/metrics/event/UIClickWrapper$Builder;", "interactionType", "withInteractionType", "experienceMode", "withExperienceMode", "", "", ContextMappingConstants.CONTENT_INFO, "withContentInfo", "localContentId", "withLocalContentId", ContentUtils.KEY_ACTIVITY_ID, "withActivityId", ContentUtils.KEY_ACTIVITY_ID_TYPE, "withActivityIdType", ContentUtils.KEY_RESPONSE_ID, "withResponseId", ContentUtils.KEY_RESPONSE_ID_TYPE, "withResponseIdType", ContentUtils.KEY_SEARCH_QUERY, "withSearchQuery", "pageEntityId", "withPageEntityId", "pageEntityIdType", "withPageEntityIdType", "pageEntityType", "withPageEntityType", "Lcom/amazon/music/platform/metrics/event/UIClickWrapper;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "eventVersion", "J", "Ljava/lang/String;", "", "Ljava/util/List;", "eventTime", "I", "<init>", "()V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class Builder {
        private String actionType;
        private String activityId;
        private String activityIdType;
        private String blockRef;
        private String entityId;
        private String entityIdType;
        private String entityType;
        private String experienceMode;
        private String interactionType;
        private String localContentId;
        private String pageEntityId;
        private String pageEntityIdType;
        private String pageEntityType;
        private String pageSubType;
        private String pageType;
        private String refMarker;
        private String responseId;
        private String responseIdType;
        private String searchQuery;
        private String url;
        private long eventVersion = 5;
        private final List<Map<String, String>> contentInfo = new ArrayList();
        private long eventTime = PlatformProviderUtilKt.getCurrentEpochTimeMillis();
        private int entityPos = -1;

        public final Builder withActionType(String actionType) {
            this.actionType = actionType;
            return this;
        }

        public final Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public final Builder withBlockRef(String blockRef) {
            this.blockRef = blockRef;
            return this;
        }

        public final Builder withPageType(String pageType) {
            this.pageType = pageType;
            return this;
        }

        public final Builder withPageSubType(String pageSubType) {
            this.pageSubType = pageSubType;
            return this;
        }

        public final Builder withRefMarker(String refMarker) {
            this.refMarker = refMarker;
            return this;
        }

        public final Builder withEntityId(String entityId) {
            this.entityId = entityId;
            return this;
        }

        public final Builder withEntityIdType(String entityIdType) {
            this.entityIdType = entityIdType;
            return this;
        }

        public final Builder withEntityType(String entityType) {
            this.entityType = entityType;
            return this;
        }

        public final Builder withEntityPos(Integer entityPos) {
            if (entityPos != null) {
                entityPos.intValue();
                this.entityPos = entityPos.intValue();
            }
            return this;
        }

        public final Builder withInteractionType(String interactionType) {
            this.interactionType = interactionType;
            return this;
        }

        public final Builder withExperienceMode(String experienceMode) {
            this.experienceMode = experienceMode;
            return this;
        }

        public final Builder withContentInfo(List<? extends Map<String, String>> contentInfo) {
            if (contentInfo != null) {
                List<Map<String, String>> list = this.contentInfo;
                list.clear();
                list.addAll(contentInfo);
            }
            return this;
        }

        public final Builder withLocalContentId(String localContentId) {
            this.localContentId = localContentId;
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

        public final Builder withPageEntityId(String pageEntityId) {
            this.pageEntityId = pageEntityId;
            return this;
        }

        public final Builder withPageEntityIdType(String pageEntityIdType) {
            this.pageEntityIdType = pageEntityIdType;
            return this;
        }

        public final Builder withPageEntityType(String pageEntityType) {
            this.pageEntityType = pageEntityType;
            return this;
        }

        public final UIClickWrapper build() {
            long j = this.eventTime;
            String str = this.actionType;
            String str2 = this.url;
            String str3 = this.blockRef;
            String str4 = this.interactionType;
            String str5 = this.pageType;
            String str6 = this.pageSubType;
            String str7 = this.refMarker;
            String str8 = this.entityType;
            return new UIClickWrapper("uiClick", j, this.eventVersion, str, str2, str3, str4, str5, str6, str7, this.entityId, this.entityIdType, this.contentInfo, str8, this.entityPos, this.experienceMode, this.localContentId, this.activityId, this.activityIdType, this.responseId, this.responseIdType, this.searchQuery, this.pageEntityId, this.pageEntityIdType, this.pageEntityType);
        }
    }
}
