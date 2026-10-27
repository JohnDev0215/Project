package com.spotify.voiceassistants.playermodels;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class ShowIntentQuery {
    private final String intent;
    private final String uri;

    public ShowIntentQuery(@z61(name = "intent") String str, @z61(name = "uri") String str2) {
        v03.j(str, "intent");
        v03.j(str2, "uri");
        this.intent = str;
        this.uri = str2;
    }

    public static /* synthetic */ ShowIntentQuery copy$default(ShowIntentQuery showIntentQuery, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = showIntentQuery.intent;
        }
        if ((i & 2) != 0) {
            str2 = showIntentQuery.uri;
        }
        return showIntentQuery.copy(str, str2);
    }

    public final String component1() {
        return this.intent;
    }

    public final String component2() {
        return this.uri;
    }

    public final ShowIntentQuery copy(@z61(name = "intent") String str, @z61(name = "uri") String str2) {
        v03.j(str, "intent");
        v03.j(str2, "uri");
        return new ShowIntentQuery(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShowIntentQuery)) {
            return false;
        }
        ShowIntentQuery showIntentQuery = (ShowIntentQuery) obj;
        return v03.b(this.intent, showIntentQuery.intent) && v03.b(this.uri, showIntentQuery.uri);
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
        StringBuilder sb = new StringBuilder("ShowIntentQuery(intent=");
        sb.append(this.intent);
        sb.append(", uri=");
        return hf2.d(sb, this.uri, ')');
    }

    public /* synthetic */ ShowIntentQuery(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ShowIntentQueryKt.INTENT_SHOW : str, str2);
    }
}
