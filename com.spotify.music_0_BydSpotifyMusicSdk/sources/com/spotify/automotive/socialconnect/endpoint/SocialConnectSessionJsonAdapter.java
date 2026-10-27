package com.spotify.automotive.socialconnect.endpoint;

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
    private final f<Boolean> booleanAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public SocialConnectSessionJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("is_session_owner", "join_session_uri", "session_id");
        v03.i(bVarA, "of(\"is_session_owner\",\n …ssion_uri\", \"session_id\")");
        this.options = bVarA;
        Class cls = Boolean.TYPE;
        EmptySet emptySet = EmptySet.c;
        f<Boolean> fVarF = oVar.f(cls, emptySet, "isSessionOwner");
        v03.i(fVarF, "moshi.adapter(Boolean::c…,\n      \"isSessionOwner\")");
        this.booleanAdapter = fVarF;
        f<String> fVarF2 = oVar.f(String.class, emptySet, "joinSessionUri");
        v03.i(fVarF2, "moshi.adapter(String::cl…ySet(), \"joinSessionUri\")");
        this.nullableStringAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(42, "GeneratedJsonAdapter(SocialConnectSession)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public SocialConnectSession fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Boolean boolFromJson = null;
        String strFromJson = null;
        boolean z = false;
        boolean z2 = false;
        String strFromJson2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                boolFromJson = this.booleanAdapter.fromJson(jsonReader);
                if (boolFromJson == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("isSessionOwner", "is_session_owner", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"isSessio…s_session_owner\", reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1) {
                strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 2) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z2 = true;
            }
        }
        jsonReader.q();
        SocialConnectSession socialConnectSession = new SocialConnectSession();
        socialConnectSession.isSessionOwner = boolFromJson != null ? boolFromJson.booleanValue() : socialConnectSession.isSessionOwner;
        if (z) {
            socialConnectSession.joinSessionUri = strFromJson2;
        }
        if (z2) {
            socialConnectSession.sessionId = strFromJson;
        }
        return socialConnectSession;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, SocialConnectSession socialConnectSession) {
        v03.j(mVar, "writer");
        if (socialConnectSession == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("is_session_owner");
        u4.w(socialConnectSession.isSessionOwner, this.booleanAdapter, mVar, "join_session_uri");
        this.nullableStringAdapter.toJson(mVar, socialConnectSession.joinSessionUri);
        mVar.J0("session_id");
        this.nullableStringAdapter.toJson(mVar, socialConnectSession.sessionId);
        mVar.X();
    }
}
