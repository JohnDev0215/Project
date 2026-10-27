package com.amazon.music.find.metrics;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.metrics.mts.event.types.ActivityIdType;
import com.amazon.music.metrics.mts.event.types.ResponseIdType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UiEventMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\tHÆ\u0003JE\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/find/metrics/UiEventMetadata;", "", ContentUtils.KEY_SEARCH_QUERY, "", ContentUtils.KEY_ACTIVITY_ID, ContentUtils.KEY_ACTIVITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/ActivityIdType;", ContentUtils.KEY_RESPONSE_ID, ContentUtils.KEY_RESPONSE_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/ResponseIdType;", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/ActivityIdType;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/ResponseIdType;)V", "getActivityId", "()Ljava/lang/String;", "getActivityIdType", "()Lcom/amazon/music/metrics/mts/event/types/ActivityIdType;", "getResponseId", "getResponseIdType", "()Lcom/amazon/music/metrics/mts/event/types/ResponseIdType;", "getSearchQuery", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class UiEventMetadata {
    private final String activityId;
    private final ActivityIdType activityIdType;
    private final String responseId;
    private final ResponseIdType responseIdType;
    private final String searchQuery;

    public UiEventMetadata() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ UiEventMetadata copy$default(UiEventMetadata uiEventMetadata, String str, String str2, ActivityIdType activityIdType, String str3, ResponseIdType responseIdType, int i, Object obj) {
        if ((i & 1) != 0) {
            str = uiEventMetadata.searchQuery;
        }
        if ((i & 2) != 0) {
            str2 = uiEventMetadata.activityId;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            activityIdType = uiEventMetadata.activityIdType;
        }
        ActivityIdType activityIdType2 = activityIdType;
        if ((i & 8) != 0) {
            str3 = uiEventMetadata.responseId;
        }
        String str5 = str3;
        if ((i & 16) != 0) {
            responseIdType = uiEventMetadata.responseIdType;
        }
        return uiEventMetadata.copy(str, str4, activityIdType2, str5, responseIdType);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSearchQuery() {
        return this.searchQuery;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getActivityId() {
        return this.activityId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ActivityIdType getActivityIdType() {
        return this.activityIdType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getResponseId() {
        return this.responseId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ResponseIdType getResponseIdType() {
        return this.responseIdType;
    }

    public final UiEventMetadata copy(String searchQuery, String activityId, ActivityIdType activityIdType, String responseId, ResponseIdType responseIdType) {
        return new UiEventMetadata(searchQuery, activityId, activityIdType, responseId, responseIdType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UiEventMetadata)) {
            return false;
        }
        UiEventMetadata uiEventMetadata = (UiEventMetadata) other;
        return Intrinsics.areEqual(this.searchQuery, uiEventMetadata.searchQuery) && Intrinsics.areEqual(this.activityId, uiEventMetadata.activityId) && this.activityIdType == uiEventMetadata.activityIdType && Intrinsics.areEqual(this.responseId, uiEventMetadata.responseId) && this.responseIdType == uiEventMetadata.responseIdType;
    }

    public int hashCode() {
        String str = this.searchQuery;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.activityId;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        ActivityIdType activityIdType = this.activityIdType;
        int iHashCode3 = (iHashCode2 + (activityIdType == null ? 0 : activityIdType.hashCode())) * 31;
        String str3 = this.responseId;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        ResponseIdType responseIdType = this.responseIdType;
        return iHashCode4 + (responseIdType != null ? responseIdType.hashCode() : 0);
    }

    public String toString() {
        return "UiEventMetadata(searchQuery=" + ((Object) this.searchQuery) + ", activityId=" + ((Object) this.activityId) + ", activityIdType=" + this.activityIdType + ", responseId=" + ((Object) this.responseId) + ", responseIdType=" + this.responseIdType + ')';
    }

    public UiEventMetadata(String str, String str2, ActivityIdType activityIdType, String str3, ResponseIdType responseIdType) {
        this.searchQuery = str;
        this.activityId = str2;
        this.activityIdType = activityIdType;
        this.responseId = str3;
        this.responseIdType = responseIdType;
    }

    public /* synthetic */ UiEventMetadata(String str, String str2, ActivityIdType activityIdType, String str3, ResponseIdType responseIdType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : activityIdType, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : responseIdType);
    }

    public final String getSearchQuery() {
        return this.searchQuery;
    }

    public final String getActivityId() {
        return this.activityId;
    }

    public final ActivityIdType getActivityIdType() {
        return this.activityIdType;
    }

    public final String getResponseId() {
        return this.responseId;
    }

    public final ResponseIdType getResponseIdType() {
        return this.responseIdType;
    }
}
