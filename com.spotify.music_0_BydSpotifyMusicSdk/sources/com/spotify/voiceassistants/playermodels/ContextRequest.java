package com.spotify.voiceassistants.playermodels;

import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class ContextRequest {
    private final String interactionId;
    private final ParsedQuery parsedQuery;
    private final SourceDevice sourceDevice;
    private final String textQuery;
    private final String textQueryLanguage;
    private final String voiceFeatureName;

    public ContextRequest(@z61(name = "text_query") String str, @z61(name = "text_query_language") String str2, @z61(name = "source_device") SourceDevice sourceDevice, @z61(name = "parsed_query") ParsedQuery parsedQuery, @z61(name = "voice_feature_name") String str3, String str4) {
        v03.j(str, "textQuery");
        v03.j(str2, "textQueryLanguage");
        v03.j(sourceDevice, "sourceDevice");
        v03.j(parsedQuery, "parsedQuery");
        v03.j(str3, "voiceFeatureName");
        this.textQuery = str;
        this.textQueryLanguage = str2;
        this.sourceDevice = sourceDevice;
        this.parsedQuery = parsedQuery;
        this.voiceFeatureName = str3;
        this.interactionId = str4;
    }

    public static /* synthetic */ ContextRequest copy$default(ContextRequest contextRequest, String str, String str2, SourceDevice sourceDevice, ParsedQuery parsedQuery, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = contextRequest.textQuery;
        }
        if ((i & 2) != 0) {
            str2 = contextRequest.textQueryLanguage;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            sourceDevice = contextRequest.sourceDevice;
        }
        SourceDevice sourceDevice2 = sourceDevice;
        if ((i & 8) != 0) {
            parsedQuery = contextRequest.parsedQuery;
        }
        ParsedQuery parsedQuery2 = parsedQuery;
        if ((i & 16) != 0) {
            str3 = contextRequest.voiceFeatureName;
        }
        String str6 = str3;
        if ((i & 32) != 0) {
            str4 = contextRequest.interactionId;
        }
        return contextRequest.copy(str, str5, sourceDevice2, parsedQuery2, str6, str4);
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

    public final ParsedQuery component4() {
        return this.parsedQuery;
    }

    public final String component5() {
        return this.voiceFeatureName;
    }

    public final String component6() {
        return this.interactionId;
    }

    public final ContextRequest copy(@z61(name = "text_query") String str, @z61(name = "text_query_language") String str2, @z61(name = "source_device") SourceDevice sourceDevice, @z61(name = "parsed_query") ParsedQuery parsedQuery, @z61(name = "voice_feature_name") String str3, String str4) {
        v03.j(str, "textQuery");
        v03.j(str2, "textQueryLanguage");
        v03.j(sourceDevice, "sourceDevice");
        v03.j(parsedQuery, "parsedQuery");
        v03.j(str3, "voiceFeatureName");
        return new ContextRequest(str, str2, sourceDevice, parsedQuery, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContextRequest)) {
            return false;
        }
        ContextRequest contextRequest = (ContextRequest) obj;
        return v03.b(this.textQuery, contextRequest.textQuery) && v03.b(this.textQueryLanguage, contextRequest.textQueryLanguage) && v03.b(this.sourceDevice, contextRequest.sourceDevice) && v03.b(this.parsedQuery, contextRequest.parsedQuery) && v03.b(this.voiceFeatureName, contextRequest.voiceFeatureName) && v03.b(this.interactionId, contextRequest.interactionId);
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
        int iB = hf2.b(this.voiceFeatureName, (this.parsedQuery.hashCode() + ((this.sourceDevice.hashCode() + hf2.b(this.textQueryLanguage, this.textQuery.hashCode() * 31, 31)) * 31)) * 31, 31);
        String str = this.interactionId;
        return iB + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ContextRequest(textQuery=");
        sb.append(this.textQuery);
        sb.append(", textQueryLanguage=");
        sb.append(this.textQueryLanguage);
        sb.append(", sourceDevice=");
        sb.append(this.sourceDevice);
        sb.append(", parsedQuery=");
        sb.append(this.parsedQuery);
        sb.append(", voiceFeatureName=");
        sb.append(this.voiceFeatureName);
        sb.append(", interactionId=");
        return hf2.d(sb, this.interactionId, ')');
    }
}
