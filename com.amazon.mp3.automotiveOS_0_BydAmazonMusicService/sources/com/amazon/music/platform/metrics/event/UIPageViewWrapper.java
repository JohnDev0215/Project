package com.amazon.music.platform.metrics.event;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.PlatformProviderUtilKt;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UIPageViewWrapper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b2\b\u0086\b\u0018\u00002\u00020\u0001:\u0001?B\u0089\u0002\u0012\b\b\u0002\u0010\n\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010'\u001a\u00020\u000e\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b=\u0010>J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\u00020\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0018\u0010\rR\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u000b\u001a\u0004\b\u001a\u0010\rR\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u000b\u001a\u0004\b\u001c\u0010\rR\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\rR\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u000b\u001a\u0004\b \u0010\rR\u0019\u0010!\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b!\u0010\u000b\u001a\u0004\b\"\u0010\rR\u0019\u0010#\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\rR\u0019\u0010%\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b%\u0010\u000b\u001a\u0004\b&\u0010\rR\u0017\u0010'\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b'\u0010\u0010\u001a\u0004\b(\u0010\u0012R\u0019\u0010)\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b)\u0010\u000b\u001a\u0004\b*\u0010\rR\u0019\u0010+\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b+\u0010\u000b\u001a\u0004\b,\u0010\rR\u0019\u0010-\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b-\u0010\u000b\u001a\u0004\b.\u0010\rR\u0019\u0010/\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b/\u0010\u000b\u001a\u0004\b0\u0010\rR\u0019\u00101\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b1\u0010\u000b\u001a\u0004\b2\u0010\rR\u0019\u00103\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b3\u0010\u000b\u001a\u0004\b4\u0010\rR\u0019\u00105\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b5\u0010\u000b\u001a\u0004\b6\u0010\rR\u0019\u00107\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b7\u0010\u000b\u001a\u0004\b8\u0010\rR\u0019\u00109\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b9\u0010\u000b\u001a\u0004\b:\u0010\rR\u0019\u0010;\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b;\u0010\u000b\u001a\u0004\b<\u0010\r¨\u0006@"}, d2 = {"Lcom/amazon/music/platform/metrics/event/UIPageViewWrapper;", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "", "toString", "", "hashCode", "", "other", "", "equals", "metricsEventName", "Ljava/lang/String;", "getMetricsEventName", "()Ljava/lang/String;", "", "timeStamp", "J", "getTimeStamp", "()J", "eventVersion", "Ljava/lang/Long;", "getEventVersion", "()Ljava/lang/Long;", "pageType", "getPageType", "pageSubType", "getPageSubType", "detailPageItemId", "getDetailPageItemId", "detailPageItemIdType", "getDetailPageItemIdType", "externalReference", "getExternalReference", "associateTag", "getAssociateTag", "viewType", "getViewType", "blockRef", "getBlockRef", "loadTime", "getLoadTime", "experienceMode", "getExperienceMode", ContentUtils.KEY_ACTIVITY_ID, "getActivityId", ContentUtils.KEY_ACTIVITY_ID_TYPE, "getActivityIdType", ContentUtils.KEY_RESPONSE_ID, "getResponseId", ContentUtils.KEY_RESPONSE_ID_TYPE, "getResponseIdType", ContentUtils.KEY_SEARCH_QUERY, "getSearchQuery", Splash.PARAMS_LOCALE, "getLocale", ImagesContract.URL, "getUrl", "userAgent", "getUserAgent", "csSessionId", "getCsSessionId", "<init>", "(Ljava/lang/String;JLjava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Builder", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class UIPageViewWrapper implements MetricsWrapper {
    private final String activityId;
    private final String activityIdType;
    private final String associateTag;
    private final String blockRef;
    private final String csSessionId;
    private final String detailPageItemId;
    private final String detailPageItemIdType;
    private final Long eventVersion;
    private final String experienceMode;
    private final String externalReference;
    private final long loadTime;
    private final String locale;
    private final String metricsEventName;
    private final String pageSubType;
    private final String pageType;
    private final String responseId;
    private final String responseIdType;
    private final String searchQuery;
    private final long timeStamp;
    private final String url;
    private final String userAgent;
    private final String viewType;

    public UIPageViewWrapper() {
        this(null, 0L, null, null, null, null, null, null, null, null, null, 0L, null, null, null, null, null, null, null, null, null, null, 4194303, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UIPageViewWrapper)) {
            return false;
        }
        UIPageViewWrapper uIPageViewWrapper = (UIPageViewWrapper) other;
        return Intrinsics.areEqual(getMetricsEventName(), uIPageViewWrapper.getMetricsEventName()) && getTimeStamp() == uIPageViewWrapper.getTimeStamp() && Intrinsics.areEqual(this.eventVersion, uIPageViewWrapper.eventVersion) && Intrinsics.areEqual(this.pageType, uIPageViewWrapper.pageType) && Intrinsics.areEqual(this.pageSubType, uIPageViewWrapper.pageSubType) && Intrinsics.areEqual(this.detailPageItemId, uIPageViewWrapper.detailPageItemId) && Intrinsics.areEqual(this.detailPageItemIdType, uIPageViewWrapper.detailPageItemIdType) && Intrinsics.areEqual(this.externalReference, uIPageViewWrapper.externalReference) && Intrinsics.areEqual(this.associateTag, uIPageViewWrapper.associateTag) && Intrinsics.areEqual(this.viewType, uIPageViewWrapper.viewType) && Intrinsics.areEqual(this.blockRef, uIPageViewWrapper.blockRef) && this.loadTime == uIPageViewWrapper.loadTime && Intrinsics.areEqual(this.experienceMode, uIPageViewWrapper.experienceMode) && Intrinsics.areEqual(this.activityId, uIPageViewWrapper.activityId) && Intrinsics.areEqual(this.activityIdType, uIPageViewWrapper.activityIdType) && Intrinsics.areEqual(this.responseId, uIPageViewWrapper.responseId) && Intrinsics.areEqual(this.responseIdType, uIPageViewWrapper.responseIdType) && Intrinsics.areEqual(this.searchQuery, uIPageViewWrapper.searchQuery) && Intrinsics.areEqual(this.locale, uIPageViewWrapper.locale) && Intrinsics.areEqual(this.url, uIPageViewWrapper.url) && Intrinsics.areEqual(this.userAgent, uIPageViewWrapper.userAgent) && Intrinsics.areEqual(this.csSessionId, uIPageViewWrapper.csSessionId);
    }

    public int hashCode() {
        int iHashCode = ((getMetricsEventName().hashCode() * 31) + Long.hashCode(getTimeStamp())) * 31;
        Long l = this.eventVersion;
        int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
        String str = this.pageType;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.pageSubType;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.detailPageItemId;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.detailPageItemIdType;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.externalReference;
        int iHashCode7 = (iHashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.associateTag;
        int iHashCode8 = (iHashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.viewType;
        int iHashCode9 = (iHashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.blockRef;
        int iHashCode10 = (((iHashCode9 + (str8 == null ? 0 : str8.hashCode())) * 31) + Long.hashCode(this.loadTime)) * 31;
        String str9 = this.experienceMode;
        int iHashCode11 = (iHashCode10 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.activityId;
        int iHashCode12 = (iHashCode11 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.activityIdType;
        int iHashCode13 = (iHashCode12 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.responseId;
        int iHashCode14 = (iHashCode13 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.responseIdType;
        int iHashCode15 = (iHashCode14 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.searchQuery;
        int iHashCode16 = (iHashCode15 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.locale;
        int iHashCode17 = (iHashCode16 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.url;
        int iHashCode18 = (iHashCode17 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.userAgent;
        int iHashCode19 = (iHashCode18 + (str17 == null ? 0 : str17.hashCode())) * 31;
        String str18 = this.csSessionId;
        return iHashCode19 + (str18 != null ? str18.hashCode() : 0);
    }

    public String toString() {
        return "UIPageViewWrapper(metricsEventName=" + getMetricsEventName() + ", timeStamp=" + getTimeStamp() + ", eventVersion=" + this.eventVersion + ", pageType=" + this.pageType + ", pageSubType=" + this.pageSubType + ", detailPageItemId=" + this.detailPageItemId + ", detailPageItemIdType=" + this.detailPageItemIdType + ", externalReference=" + this.externalReference + ", associateTag=" + this.associateTag + ", viewType=" + this.viewType + ", blockRef=" + this.blockRef + ", loadTime=" + this.loadTime + ", experienceMode=" + this.experienceMode + ", activityId=" + this.activityId + ", activityIdType=" + this.activityIdType + ", responseId=" + this.responseId + ", responseIdType=" + this.responseIdType + ", searchQuery=" + this.searchQuery + ", locale=" + this.locale + ", url=" + this.url + ", userAgent=" + this.userAgent + ", csSessionId=" + this.csSessionId + ')';
    }

    public UIPageViewWrapper(String metricsEventName, long j, Long l, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, long j2, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18) {
        Intrinsics.checkNotNullParameter(metricsEventName, "metricsEventName");
        this.metricsEventName = metricsEventName;
        this.timeStamp = j;
        this.eventVersion = l;
        this.pageType = str;
        this.pageSubType = str2;
        this.detailPageItemId = str3;
        this.detailPageItemIdType = str4;
        this.externalReference = str5;
        this.associateTag = str6;
        this.viewType = str7;
        this.blockRef = str8;
        this.loadTime = j2;
        this.experienceMode = str9;
        this.activityId = str10;
        this.activityIdType = str11;
        this.responseId = str12;
        this.responseIdType = str13;
        this.searchQuery = str14;
        this.locale = str15;
        this.url = str16;
        this.userAgent = str17;
        this.csSessionId = str18;
        PlatformProviderUtilKt.freeze(this);
    }

    public String getMetricsEventName() {
        return this.metricsEventName;
    }

    public /* synthetic */ UIPageViewWrapper(String str, long j, Long l, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, long j2, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "uiPageView" : str, (i & 2) != 0 ? PlatformProviderUtilKt.getCurrentEpochTimeMillis() : j, (i & 4) != 0 ? 4L : l, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : str7, (i & 512) != 0 ? null : str8, (i & 1024) != 0 ? null : str9, (i & 2048) != 0 ? 0L : j2, (i & 4096) != 0 ? null : str10, (i & 8192) != 0 ? null : str11, (i & 16384) != 0 ? null : str12, (i & 32768) != 0 ? null : str13, (i & 65536) != 0 ? null : str14, (i & 131072) != 0 ? null : str15, (i & 262144) != 0 ? null : str16, (i & 524288) != 0 ? null : str17, (i & 1048576) != 0 ? null : str18, (i & 2097152) != 0 ? null : str19);
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public final String getPageType() {
        return this.pageType;
    }

    public final String getPageSubType() {
        return this.pageSubType;
    }

    public final String getDetailPageItemId() {
        return this.detailPageItemId;
    }

    public final String getDetailPageItemIdType() {
        return this.detailPageItemIdType;
    }

    public final String getExternalReference() {
        return this.externalReference;
    }

    public final String getAssociateTag() {
        return this.associateTag;
    }

    public final String getViewType() {
        return this.viewType;
    }

    public final String getBlockRef() {
        return this.blockRef;
    }

    public final long getLoadTime() {
        return this.loadTime;
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

    public final String getLocale() {
        return this.locale;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getUserAgent() {
        return this.userAgent;
    }

    public final String getCsSessionId() {
        return this.csSessionId;
    }

    /* JADX INFO: compiled from: UIPageViewWrapper.kt */
    @Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 42\u00020\u0001:\u00014B\u0007¢\u0006\u0004\b2\u00103J\u0010\u0010\u0004\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0006\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\b\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\n\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\f\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002J\u0017\u0010\u000f\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0012\u0010\u0010J\u0010\u0010\u0014\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0016\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0018\u001a\u00020\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u001a\u001a\u00020\u00002\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u001c\u001a\u00020\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u001e\u001a\u00020\u00002\b\u0010\u001d\u001a\u0004\u0018\u00010\u0002J\u0010\u0010 \u001a\u00020\u00002\b\u0010\u001f\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\"\u001a\u00020\u00002\b\u0010!\u001a\u0004\u0018\u00010\u0002J\u0010\u0010$\u001a\u00020\u00002\b\u0010#\u001a\u0004\u0018\u00010\u0002J\u0010\u0010&\u001a\u00020\u00002\b\u0010%\u001a\u0004\u0018\u00010\u0002J\u0010\u0010(\u001a\u00020\u00002\b\u0010'\u001a\u0004\u0018\u00010\u0002J\u0010\u0010*\u001a\u00020\u00002\b\u0010)\u001a\u0004\u0018\u00010\u0002J\u0010\u0010,\u001a\u00020\u00002\b\u0010+\u001a\u0004\u0018\u00010\u0002J\u0006\u0010.\u001a\u00020-R\u0016\u0010\u0011\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010/R\u0016\u00100\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u0010/R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u00101R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u00101R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u00101R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u00101R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u00101R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u00101R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u00101R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u00101R\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010/R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u00101R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u00101R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u00101R\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u00101R\u0018\u0010!\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u00101R\u0018\u0010#\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u00101R\u0018\u0010%\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u00101R\u0018\u0010'\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u00101R\u0018\u0010)\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u00101R\u0018\u0010+\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u00101¨\u00065"}, d2 = {"Lcom/amazon/music/platform/metrics/event/UIPageViewWrapper$Builder;", "", "", "pageType", "withPageType", "pageSubType", "withPageSubType", "associateTag", "withAssociateTag", "viewType", "withViewType", "blockRef", "withBlockRef", "", "loadTime", "withLoadTime", "(Ljava/lang/Long;)Lcom/amazon/music/platform/metrics/event/UIPageViewWrapper$Builder;", "eventTime", "withEventTime", "detailPageItemId", "withDetailPageItemId", "detailPageItemIdType", "withDetailPageItemIdType", "externalReference", "withExternalReference", "experienceMode", "withExperienceMode", ContentUtils.KEY_ACTIVITY_ID, "withActivityId", ContentUtils.KEY_ACTIVITY_ID_TYPE, "withActivityIdType", ContentUtils.KEY_RESPONSE_ID, "withResponseId", ContentUtils.KEY_RESPONSE_ID_TYPE, "withResponseIdType", ContentUtils.KEY_SEARCH_QUERY, "withSearchQuery", Splash.PARAMS_LOCALE, "withLocale", ImagesContract.URL, "withUrl", "userAgent", "withUserAgent", "csSessionId", "withCsSessionId", "Lcom/amazon/music/platform/metrics/event/UIPageViewWrapper;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "J", "eventVersion", "Ljava/lang/String;", "<init>", "()V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class Builder {
        private String activityId;
        private String activityIdType;
        private String associateTag;
        private String blockRef;
        private String csSessionId;
        private String detailPageItemId;
        private String detailPageItemIdType;
        private long eventTime = PlatformProviderUtilKt.getCurrentEpochTimeMillis();
        private long eventVersion = 4;
        private String experienceMode;
        private String externalReference;
        private long loadTime;
        private String locale;
        private String pageSubType;
        private String pageType;
        private String responseId;
        private String responseIdType;
        private String searchQuery;
        private String url;
        private String userAgent;
        private String viewType;

        public final Builder withPageType(String pageType) {
            this.pageType = pageType;
            return this;
        }

        public final Builder withPageSubType(String pageSubType) {
            this.pageSubType = pageSubType;
            return this;
        }

        public final Builder withAssociateTag(String associateTag) {
            this.associateTag = associateTag;
            return this;
        }

        public final Builder withViewType(String viewType) {
            this.viewType = viewType;
            return this;
        }

        public final Builder withBlockRef(String blockRef) {
            this.blockRef = blockRef;
            return this;
        }

        public final Builder withLoadTime(Long loadTime) {
            if (loadTime != null) {
                loadTime.longValue();
                this.loadTime = loadTime.longValue();
            }
            return this;
        }

        public final Builder withEventTime(Long eventTime) {
            if (eventTime != null) {
                eventTime.longValue();
                this.eventTime = eventTime.longValue();
            }
            return this;
        }

        public final Builder withDetailPageItemId(String detailPageItemId) {
            this.detailPageItemId = detailPageItemId;
            return this;
        }

        public final Builder withDetailPageItemIdType(String detailPageItemIdType) {
            this.detailPageItemIdType = detailPageItemIdType;
            return this;
        }

        public final Builder withExternalReference(String externalReference) {
            this.externalReference = externalReference;
            return this;
        }

        public final Builder withExperienceMode(String experienceMode) {
            this.experienceMode = experienceMode;
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

        public final Builder withLocale(String locale) {
            this.locale = locale;
            return this;
        }

        public final Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public final Builder withUserAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public final Builder withCsSessionId(String csSessionId) {
            this.csSessionId = csSessionId;
            return this;
        }

        public final UIPageViewWrapper build() {
            long j = this.eventTime;
            String str = this.pageType;
            String str2 = this.pageSubType;
            String str3 = this.detailPageItemId;
            String str4 = this.detailPageItemIdType;
            String str5 = this.externalReference;
            String str6 = this.associateTag;
            String str7 = this.viewType;
            String str8 = this.blockRef;
            long j2 = this.loadTime;
            long j3 = this.eventVersion;
            return new UIPageViewWrapper("uiPageView", j, Long.valueOf(j3), str, str2, str3, str4, str5, str6, str7, str8, j2, this.experienceMode, this.activityId, this.activityIdType, this.responseId, this.responseIdType, this.searchQuery, this.locale, this.url, this.userAgent, this.csSessionId);
        }
    }
}
