package com.spotify.voiceassistants.playermodels;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class SearchEndpointRequest {
    private final String interactionId;
    private final ParsedQuery parsedQuery;
    private final SourceDevice sourceDevice;
    private final SourceDevice targetDevice;
    private final String textQuery;
    private final String textQueryLanguage;
    private final String voiceFeatureName;

    public SearchEndpointRequest(@z61(name = "text_query") String str, @z61(name = "text_query_language") String str2, @z61(name = "source_device") SourceDevice sourceDevice, @z61(name = "target_device") SourceDevice sourceDevice2, @z61(name = "parsed_query") ParsedQuery parsedQuery, @z61(name = "voice_feature_name") String str3, String str4) {
        v03.j(str, "textQuery");
        v03.j(str2, "textQueryLanguage");
        v03.j(sourceDevice, "sourceDevice");
        v03.j(sourceDevice2, "targetDevice");
        v03.j(parsedQuery, "parsedQuery");
        v03.j(str3, "voiceFeatureName");
        this.textQuery = str;
        this.textQueryLanguage = str2;
        this.sourceDevice = sourceDevice;
        this.targetDevice = sourceDevice2;
        this.parsedQuery = parsedQuery;
        this.voiceFeatureName = str3;
        this.interactionId = str4;
    }

    public static /* synthetic */ SearchEndpointRequest copy$default(SearchEndpointRequest searchEndpointRequest, String str, String str2, SourceDevice sourceDevice, SourceDevice sourceDevice2, ParsedQuery parsedQuery, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = searchEndpointRequest.textQuery;
        }
        if ((i & 2) != 0) {
            str2 = searchEndpointRequest.textQueryLanguage;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            sourceDevice = searchEndpointRequest.sourceDevice;
        }
        SourceDevice sourceDevice3 = sourceDevice;
        if ((i & 8) != 0) {
            sourceDevice2 = searchEndpointRequest.targetDevice;
        }
        SourceDevice sourceDevice4 = sourceDevice2;
        if ((i & 16) != 0) {
            parsedQuery = searchEndpointRequest.parsedQuery;
        }
        ParsedQuery parsedQuery2 = parsedQuery;
        if ((i & 32) != 0) {
            str3 = searchEndpointRequest.voiceFeatureName;
        }
        String str6 = str3;
        if ((i & 64) != 0) {
            str4 = searchEndpointRequest.interactionId;
        }
        return searchEndpointRequest.copy(str, str5, sourceDevice3, sourceDevice4, parsedQuery2, str6, str4);
    }

    public final String component1() {
        return this.textQuery;
    }

    public final String component2() {
        return this.textQueryLanguage;
    }

    public final SourceDevice component3() {
        return this.sourceDevice;
    }

    public final SourceDevice component4() {
        return this.targetDevice;
    }

    public final ParsedQuery component5() {
        return this.parsedQuery;
    }

    public final String component6() {
        return this.voiceFeatureName;
    }

    public final String component7() {
        return this.interactionId;
    }

    public final SearchEndpointRequest copy(@z61(name = "text_query") String str, @z61(name = "text_query_language") String str2, @z61(name = "source_device") SourceDevice sourceDevice, @z61(name = "target_device") SourceDevice sourceDevice2, @z61(name = "parsed_query") ParsedQuery parsedQuery, @z61(name = "voice_feature_name") String str3, String str4) {
        v03.j(str, "textQuery");
        v03.j(str2, "textQueryLanguage");
        v03.j(sourceDevice, "sourceDevice");
        v03.j(sourceDevice2, "targetDevice");
        v03.j(parsedQuery, "parsedQuery");
        v03.j(str3, "voiceFeatureName");
        return new SearchEndpointRequest(str, str2, sourceDevice, sourceDevice2, parsedQuery, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SearchEndpointRequest)) {
            return false;
        }
        SearchEndpointRequest searchEndpointRequest = (SearchEndpointRequest) obj;
        return v03.b(this.textQuery, searchEndpointRequest.textQuery) && v03.b(this.textQueryLanguage, searchEndpointRequest.textQueryLanguage) && v03.b(this.sourceDevice, searchEndpointRequest.sourceDevice) && v03.b(this.targetDevice, searchEndpointRequest.targetDevice) && v03.b(this.parsedQuery, searchEndpointRequest.parsedQuery) && v03.b(this.voiceFeatureName, searchEndpointRequest.voiceFeatureName) && v03.b(this.interactionId, searchEndpointRequest.interactionId);
    }

    public final String getInteractionId() {
        return this.interactionId;
    }

    public final ParsedQuery getParsedQuery() {
        return this.parsedQuery;
    }

    public final SourceDevice getSourceDevice() {
        return this.sourceDevice;
    }

    public final SourceDevice getTargetDevice() {
        return this.targetDevice;
    }

    public final String getTextQuery() {
        return this.textQuery;
    }

    public final String getTextQueryLanguage() {
        return this.textQueryLanguage;
    }

    public final String getVoiceFeatureName() {
        return this.voiceFeatureName;
    }

    public int hashCode() {
        int iB = hf2.b(this.voiceFeatureName, (this.parsedQuery.hashCode() + ((this.targetDevice.hashCode() + ((this.sourceDevice.hashCode() + hf2.b(this.textQueryLanguage, this.textQuery.hashCode() * 31, 31)) * 31)) * 31)) * 31, 31);
        String str = this.interactionId;
        return iB + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SearchEndpointRequest(textQuery=");
        sb.append(this.textQuery);
        sb.append(", textQueryLanguage=");
        sb.append(this.textQueryLanguage);
        sb.append(", sourceDevice=");
        sb.append(this.sourceDevice);
        sb.append(", targetDevice=");
        sb.append(this.targetDevice);
        sb.append(", parsedQuery=");
        sb.append(this.parsedQuery);
        sb.append(", voiceFeatureName=");
        sb.append(this.voiceFeatureName);
        sb.append(", interactionId=");
        return hf2.d(sb, this.interactionId, ')');
    }

    public /* synthetic */ SearchEndpointRequest(String str, String str2, SourceDevice sourceDevice, SourceDevice sourceDevice2, ParsedQuery parsedQuery, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, sourceDevice, (i & 8) != 0 ? sourceDevice : sourceDevice2, parsedQuery, str3, str4);
    }
}
