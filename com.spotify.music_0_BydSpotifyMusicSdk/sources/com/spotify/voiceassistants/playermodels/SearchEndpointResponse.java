package com.spotify.voiceassistants.playermodels;

import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class SearchEndpointResponse implements SearchResponse {
    private final String action;
    private final List<MetadataItem> alternativeResults;
    private final String error;
    private final MetadataItem feedbackDetails;
    private final String feedbackId;
    private final String intent;
    private final String requestId;
    private final String result;

    /* JADX WARN: Multi-variable type inference failed */
    public SearchEndpointResponse(@z61(name = "req_id") String str, @z61(name = "intent") String str2, @z61(name = "error") String str3, @z61(name = "result") String str4, @z61(name = "feedback_details") MetadataItem metadataItem, @z61(name = "action") String str5, @z61(name = "feedback_id") String str6, @z61(name = "alternative_results") List<? extends MetadataItem> list) {
        v03.j(str, "requestId");
        v03.j(str2, "intent");
        v03.j(str4, "result");
        v03.j(str6, "feedbackId");
        v03.j(list, "alternativeResults");
        this.requestId = str;
        this.intent = str2;
        this.error = str3;
        this.result = str4;
        this.feedbackDetails = metadataItem;
        this.action = str5;
        this.feedbackId = str6;
        this.alternativeResults = list;
    }

    public final String component1() {
        return this.requestId;
    }

    public final String component2() {
        return this.intent;
    }

    public final String component3() {
        return this.error;
    }

    public final String component4() {
        return this.result;
    }

    public final MetadataItem component5() {
        return this.feedbackDetails;
    }

    public final String component6() {
        return this.action;
    }

    public final String component7() {
        return this.feedbackId;
    }

    public final List<MetadataItem> component8() {
        return this.alternativeResults;
    }

    public final SearchEndpointResponse copy(@z61(name = "req_id") String str, @z61(name = "intent") String str2, @z61(name = "error") String str3, @z61(name = "result") String str4, @z61(name = "feedback_details") MetadataItem metadataItem, @z61(name = "action") String str5, @z61(name = "feedback_id") String str6, @z61(name = "alternative_results") List<? extends MetadataItem> list) {
        v03.j(str, "requestId");
        v03.j(str2, "intent");
        v03.j(str4, "result");
        v03.j(str6, "feedbackId");
        v03.j(list, "alternativeResults");
        return new SearchEndpointResponse(str, str2, str3, str4, metadataItem, str5, str6, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SearchEndpointResponse)) {
            return false;
        }
        SearchEndpointResponse searchEndpointResponse = (SearchEndpointResponse) obj;
        return v03.b(this.requestId, searchEndpointResponse.requestId) && v03.b(this.intent, searchEndpointResponse.intent) && v03.b(this.error, searchEndpointResponse.error) && v03.b(this.result, searchEndpointResponse.result) && v03.b(this.feedbackDetails, searchEndpointResponse.feedbackDetails) && v03.b(this.action, searchEndpointResponse.action) && v03.b(this.feedbackId, searchEndpointResponse.feedbackId) && v03.b(this.alternativeResults, searchEndpointResponse.alternativeResults);
    }

    public final String getAction() {
        return this.action;
    }

    public final List<MetadataItem> getAlternativeResults() {
        return this.alternativeResults;
    }

    public final String getError() {
        return this.error;
    }

    public final MetadataItem getFeedbackDetails() {
        return this.feedbackDetails;
    }

    public final String getFeedbackId() {
        return this.feedbackId;
    }

    public final String getIntent() {
        return this.intent;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    @Override // com.spotify.voiceassistants.playermodels.SearchResponse
    public List<MetadataItem> getResponseAlternativeResults() {
        return this.alternativeResults;
    }

    @Override // com.spotify.voiceassistants.playermodels.SearchResponse
    public MetadataItem getResponseFeedbackDetails() {
        return this.feedbackDetails;
    }

    public final String getResult() {
        return this.result;
    }

    @Override // com.spotify.voiceassistants.playermodels.SearchResponse
    public String getSearchRequestId() {
        return this.requestId;
    }

    public int hashCode() {
        int iB = hf2.b(this.intent, this.requestId.hashCode() * 31, 31);
        String str = this.error;
        int iB2 = hf2.b(this.result, (iB + (str == null ? 0 : str.hashCode())) * 31, 31);
        MetadataItem metadataItem = this.feedbackDetails;
        int iHashCode = (iB2 + (metadataItem == null ? 0 : metadataItem.hashCode())) * 31;
        String str2 = this.action;
        return this.alternativeResults.hashCode() + hf2.b(this.feedbackId, (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31, 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SearchEndpointResponse(requestId=");
        sb.append(this.requestId);
        sb.append(", intent=");
        sb.append(this.intent);
        sb.append(", error=");
        sb.append(this.error);
        sb.append(", result=");
        sb.append(this.result);
        sb.append(", feedbackDetails=");
        sb.append(this.feedbackDetails);
        sb.append(", action=");
        sb.append(this.action);
        sb.append(", feedbackId=");
        sb.append(this.feedbackId);
        sb.append(", alternativeResults=");
        return hf2.e(sb, this.alternativeResults, ')');
    }

    public SearchEndpointResponse(String str, String str2, String str3, String str4, MetadataItem metadataItem, String str5, String str6, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : str3, str4, (i & 16) != 0 ? null : metadataItem, str5, str6, (i & 128) != 0 ? EmptyList.c : list);
    }
}
