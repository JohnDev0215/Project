package com.spotify.playerlimited.player.models;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SocialConnectSessionJsonAdapter extends f<SocialConnectSession> {
    public final JsonReader.b a;
    public final f b;
    public final f c;

    public SocialConnectSessionJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("is_session_owner", "join_session_uri", "session_id");
        v03.i(bVarA, "of(\"is_session_owner\",\n …ssion_uri\", \"session_id\")");
        this.a = bVarA;
        Class cls = Boolean.TYPE;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(cls, emptySet, "isHost");
        v03.i(fVarF, "moshi.adapter(Boolean::c…ptySet(),\n      \"isHost\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(String.class, emptySet, "joinSessionUri");
        v03.i(fVarF2, "moshi.adapter(String::cl…ySet(), \"joinSessionUri\")");
        this.c = fVarF2;
    }

    @Override // com.squareup.moshi.f
    public final SocialConnectSession fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Boolean bool = null;
        String str = null;
        boolean z = false;
        boolean z2 = false;
        String str2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 != 0) {
                f fVar = this.c;
                if (iM0 == 1) {
                    str2 = (String) fVar.fromJson(jsonReader);
                    z = true;
                } else if (iM0 == 2) {
                    str = (String) fVar.fromJson(jsonReader);
                    z2 = true;
                }
            } else {
                bool = (Boolean) this.b.fromJson(jsonReader);
                if (bool == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("isHost", "is_session_owner", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"isHost\",…s_session_owner\", reader)");
                    throw jsonDataExceptionW;
                }
            }
        }
        jsonReader.q();
        SocialConnectSession socialConnectSession = new SocialConnectSession();
        socialConnectSession.a = bool != null ? bool.booleanValue() : socialConnectSession.a;
        if (z) {
            socialConnectSession.b = str2;
        }
        if (z2) {
            socialConnectSession.c = str;
        }
        return socialConnectSession;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, SocialConnectSession socialConnectSession) {
        SocialConnectSession socialConnectSession2 = socialConnectSession;
        v03.j(mVar, "writer");
        if (socialConnectSession2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("is_session_owner");
        this.b.toJson(mVar, Boolean.valueOf(socialConnectSession2.a));
        mVar.J0("join_session_uri");
        String str = socialConnectSession2.b;
        f fVar = this.c;
        fVar.toJson(mVar, str);
        mVar.J0("session_id");
        fVar.toJson(mVar, socialConnectSession2.c);
        mVar.X();
    }

    public final String toString() {
        return u4.b(42, "GeneratedJsonAdapter(SocialConnectSession)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
