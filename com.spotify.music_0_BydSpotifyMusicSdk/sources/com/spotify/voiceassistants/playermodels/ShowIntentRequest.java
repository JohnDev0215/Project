package com.spotify.voiceassistants.playermodels;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class ShowIntentRequest {
    private final ShowIntentQuery showIntentQuery;
    private final String textQuery;
    private final String textQueryLanguage;
    private final String voiceFeatureName;

    public ShowIntentRequest(@z61(name = "text_query") String str, @z61(name = "text_query_language") String str2, @z61(name = "parsed_query") ShowIntentQuery showIntentQuery, @z61(name = "voice_feature_name") String str3) {
        v03.j(str, "textQuery");
        v03.j(str2, "textQueryLanguage");
        v03.j(str3, "voiceFeatureName");
        this.textQuery = str;
        this.textQueryLanguage = str2;
        this.showIntentQuery = showIntentQuery;
        this.voiceFeatureName = str3;
    }

    public static /* synthetic */ ShowIntentRequest copy$default(ShowIntentRequest showIntentRequest, String str, String str2, ShowIntentQuery showIntentQuery, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = showIntentRequest.textQuery;
        }
        if ((i & 2) != 0) {
            str2 = showIntentRequest.textQueryLanguage;
        }
        if ((i & 4) != 0) {
            showIntentQuery = showIntentRequest.showIntentQuery;
        }
        if ((i & 8) != 0) {
            str3 = showIntentRequest.voiceFeatureName;
        }
        return showIntentRequest.copy(str, str2, showIntentQuery, str3);
    }

    public final String component1() {
        return this.textQuery;
    }

    public final String component2() {
        return this.textQueryLanguage;
    }

    public final ShowIntentQuery component3() {
        return this.showIntentQuery;
    }

    public final String component4() {
        return this.voiceFeatureName;
    }

    public final ShowIntentRequest copy(@z61(name = "text_query") String str, @z61(name = "text_query_language") String str2, @z61(name = "parsed_query") ShowIntentQuery showIntentQuery, @z61(name = "voice_feature_name") String str3) {
        v03.j(str, "textQuery");
        v03.j(str2, "textQueryLanguage");
        v03.j(str3, "voiceFeatureName");
        return new ShowIntentRequest(str, str2, showIntentQuery, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShowIntentRequest)) {
            return false;
        }
        ShowIntentRequest showIntentRequest = (ShowIntentRequest) obj;
        return v03.b(this.textQuery, showIntentRequest.textQuery) && v03.b(this.textQueryLanguage, showIntentRequest.textQueryLanguage) && v03.b(this.showIntentQuery, showIntentRequest.showIntentQuery) && v03.b(this.voiceFeatureName, showIntentRequest.voiceFeatureName);
    }

    public final ShowIntentQuery getShowIntentQuery() {
        return this.showIntentQuery;
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
        int iB = hf2.b(this.textQueryLanguage, this.textQuery.hashCode() * 31, 31);
        ShowIntentQuery showIntentQuery = this.showIntentQuery;
        return this.voiceFeatureName.hashCode() + ((iB + (showIntentQuery == null ? 0 : showIntentQuery.hashCode())) * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ShowIntentRequest(textQuery=");
        sb.append(this.textQuery);
        sb.append(", textQueryLanguage=");
        sb.append(this.textQueryLanguage);
        sb.append(", showIntentQuery=");
        sb.append(this.showIntentQuery);
        sb.append(", voiceFeatureName=");
        return hf2.d(sb, this.voiceFeatureName, ')');
    }

    public /* synthetic */ ShowIntentRequest(String str, String str2, ShowIntentQuery showIntentQuery, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : showIntentQuery, str3);
    }
}
