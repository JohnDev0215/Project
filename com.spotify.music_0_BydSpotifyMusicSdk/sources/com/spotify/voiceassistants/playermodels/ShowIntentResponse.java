package com.spotify.voiceassistants.playermodels;

import java.util.List;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class ShowIntentResponse {
    private final List<MetadataItem> alternativeResults;
    private final String requestId;

    /* JADX WARN: Multi-variable type inference failed */
    public ShowIntentResponse(@z61(name = "req_id") String str, @z61(name = "entities") List<? extends MetadataItem> list) {
        v03.j(str, "requestId");
        v03.j(list, "alternativeResults");
        this.requestId = str;
        this.alternativeResults = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ShowIntentResponse copy$default(ShowIntentResponse showIntentResponse, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = showIntentResponse.requestId;
        }
        if ((i & 2) != 0) {
            list = showIntentResponse.alternativeResults;
        }
        return showIntentResponse.copy(str, list);
    }

    public final String component1() {
        return this.requestId;
    }

    public final List<MetadataItem> component2() {
        return this.alternativeResults;
    }

    public final ShowIntentResponse copy(@z61(name = "req_id") String str, @z61(name = "entities") List<? extends MetadataItem> list) {
        v03.j(str, "requestId");
        v03.j(list, "alternativeResults");
        return new ShowIntentResponse(str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShowIntentResponse)) {
            return false;
        }
        ShowIntentResponse showIntentResponse = (ShowIntentResponse) obj;
        return v03.b(this.requestId, showIntentResponse.requestId) && v03.b(this.alternativeResults, showIntentResponse.alternativeResults);
    }

    public final List<MetadataItem> getAlternativeResults() {
        return this.alternativeResults;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public int hashCode() {
        return this.alternativeResults.hashCode() + (this.requestId.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ShowIntentResponse(requestId=");
        sb.append(this.requestId);
        sb.append(", alternativeResults=");
        return hf2.e(sb, this.alternativeResults, ')');
    }
}
