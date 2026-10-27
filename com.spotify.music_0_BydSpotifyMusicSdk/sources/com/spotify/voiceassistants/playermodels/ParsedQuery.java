package com.spotify.voiceassistants.playermodels;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class ParsedQuery {
    private final String intent;
    private final String uri;

    public ParsedQuery(@z61(name = "intent") String str, @z61(name = "uri") String str2) {
        v03.j(str, "intent");
        v03.j(str2, "uri");
        this.intent = str;
        this.uri = str2;
    }

    public static /* synthetic */ ParsedQuery copy$default(ParsedQuery parsedQuery, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = parsedQuery.intent;
        }
        if ((i & 2) != 0) {
            str2 = parsedQuery.uri;
        }
        return parsedQuery.copy(str, str2);
    }

    public final String component1() {
        return this.intent;
    }

    public final String component2() {
        return this.uri;
    }

    public final ParsedQuery copy(@z61(name = "intent") String str, @z61(name = "uri") String str2) {
        v03.j(str, "intent");
        v03.j(str2, "uri");
        return new ParsedQuery(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ParsedQuery)) {
            return false;
        }
        ParsedQuery parsedQuery = (ParsedQuery) obj;
        return v03.b(this.intent, parsedQuery.intent) && v03.b(this.uri, parsedQuery.uri);
    }

    public final String getIntent() {
        return this.intent;
    }

    public final String getUri() {
        return this.uri;
    }

    public int hashCode() {
        return this.uri.hashCode() + (this.intent.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ParsedQuery(intent=");
        sb.append(this.intent);
        sb.append(", uri=");
        return hf2.d(sb, this.uri, ')');
    }

    public /* synthetic */ ParsedQuery(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ParsedQueryKt.INTENT_PLAY : str, str2);
    }
}
