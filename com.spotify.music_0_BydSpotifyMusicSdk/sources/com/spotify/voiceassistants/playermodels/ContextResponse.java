package com.spotify.voiceassistants.playermodels;

import com.spotify.player.model.Context;
import com.spotify.player.model.PlayOrigin;
import com.spotify.player.model.command.options.PreparePlayOptions;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = false)
public final class ContextResponse implements SearchResponse {
    private final List<MetadataItem> alternativeResults;
    private final Context context;
    private final String error;
    private final MetadataItem feedbackDetails;
    private final String intent;
    private final PreparePlayOptions playOptions;
    private final PlayOrigin playOrigin;
    private final String requestId;
    private final String result;
    private final String viewUri;

    /* JADX WARN: Multi-variable type inference failed */
    public ContextResponse(@z61(name = "req_id") String str, @z61(name = "intent") String str2, @z61(name = "result") String str3, @z61(name = "error") String str4, @z61(name = "context") Context context, @z61(name = "play_options") PreparePlayOptions preparePlayOptions, @z61(name = "play_origin") PlayOrigin playOrigin, @z61(name = "view_uri") String str5, @z61(name = "feedback_details") MetadataItem metadataItem, @z61(name = "alternative_results") List<? extends MetadataItem> list) {
        v03.j(str, "requestId");
        v03.j(list, "alternativeResults");
        this.requestId = str;
        this.intent = str2;
        this.result = str3;
        this.error = str4;
        this.context = context;
        this.playOptions = preparePlayOptions;
        this.playOrigin = playOrigin;
        this.viewUri = str5;
        this.feedbackDetails = metadataItem;
        this.alternativeResults = list;
    }

    public final String component1() {
        return this.requestId;
    }

    public final List<MetadataItem> component10() {
        return this.alternativeResults;
    }

    public final String component2() {
        return this.intent;
    }

    public final String component3() {
        return this.result;
    }

    public final String component4() {
        return this.error;
    }

    public final Context component5() {
        return this.context;
    }

    public final PreparePlayOptions component6() {
        return this.playOptions;
    }

    public final PlayOrigin component7() {
        return this.playOrigin;
    }

    public final String component8() {
        return this.viewUri;
    }

    public final MetadataItem component9() {
        return this.feedbackDetails;
    }

    public final ContextResponse copy(@z61(name = "req_id") String str, @z61(name = "intent") String str2, @z61(name = "result") String str3, @z61(name = "error") String str4, @z61(name = "context") Context context, @z61(name = "play_options") PreparePlayOptions preparePlayOptions, @z61(name = "play_origin") PlayOrigin playOrigin, @z61(name = "view_uri") String str5, @z61(name = "feedback_details") MetadataItem metadataItem, @z61(name = "alternative_results") List<? extends MetadataItem> list) {
        v03.j(str, "requestId");
        v03.j(list, "alternativeResults");
        return new ContextResponse(str, str2, str3, str4, context, preparePlayOptions, playOrigin, str5, metadataItem, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContextResponse)) {
            return false;
        }
        ContextResponse contextResponse = (ContextResponse) obj;
        return v03.b(this.requestId, contextResponse.requestId) && v03.b(this.intent, contextResponse.intent) && v03.b(this.result, contextResponse.result) && v03.b(this.error, contextResponse.error) && v03.b(this.context, contextResponse.context) && v03.b(this.playOptions, contextResponse.playOptions) && v03.b(this.playOrigin, contextResponse.playOrigin) && v03.b(this.viewUri, contextResponse.viewUri) && v03.b(this.feedbackDetails, contextResponse.feedbackDetails) && v03.b(this.alternativeResults, contextResponse.alternativeResults);
    }

    public final List<MetadataItem> getAlternativeResults() {
        return this.alternativeResults;
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getError() {
        return this.error;
    }

    public final MetadataItem getFeedbackDetails() {
        return this.feedbackDetails;
    }

    public final String getIntent() {
        return this.intent;
    }

    public final PreparePlayOptions getPlayOptions() {
        return this.playOptions;
    }

    public final PlayOrigin getPlayOrigin() {
        return this.playOrigin;
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

    public final String getViewUri() {
        return this.viewUri;
    }

    public int hashCode() {
        int iHashCode = this.requestId.hashCode() * 31;
        String str = this.intent;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.result;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.error;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Context context = this.context;
        int iHashCode5 = (iHashCode4 + (context == null ? 0 : context.hashCode())) * 31;
        PreparePlayOptions preparePlayOptions = this.playOptions;
        int iHashCode6 = (iHashCode5 + (preparePlayOptions == null ? 0 : preparePlayOptions.hashCode())) * 31;
        PlayOrigin playOrigin = this.playOrigin;
        int iHashCode7 = (iHashCode6 + (playOrigin == null ? 0 : playOrigin.hashCode())) * 31;
        String str4 = this.viewUri;
        int iHashCode8 = (iHashCode7 + (str4 == null ? 0 : str4.hashCode())) * 31;
        MetadataItem metadataItem = this.feedbackDetails;
        return this.alternativeResults.hashCode() + ((iHashCode8 + (metadataItem != null ? metadataItem.hashCode() : 0)) * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ContextResponse(requestId=");
        sb.append(this.requestId);
        sb.append(", intent=");
        sb.append(this.intent);
        sb.append(", result=");
        sb.append(this.result);
        sb.append(", error=");
        sb.append(this.error);
        sb.append(", context=");
        sb.append(this.context);
        sb.append(", playOptions=");
        sb.append(this.playOptions);
        sb.append(", playOrigin=");
        sb.append(this.playOrigin);
        sb.append(", viewUri=");
        sb.append(this.viewUri);
        sb.append(", feedbackDetails=");
        sb.append(this.feedbackDetails);
        sb.append(", alternativeResults=");
        return hf2.e(sb, this.alternativeResults, ')');
    }

    public ContextResponse(String str, String str2, String str3, String str4, Context context, PreparePlayOptions preparePlayOptions, PlayOrigin playOrigin, String str5, MetadataItem metadataItem, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, context, preparePlayOptions, playOrigin, str5, (i & 256) != 0 ? null : metadataItem, (i & 512) != 0 ? EmptyList.c : list);
    }
}
