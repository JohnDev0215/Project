package com.spotify.playerlimited.player.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SocialConnectSessionUpdateJsonAdapter extends f<SocialConnectSessionUpdate> {
    public final JsonReader.b a;
    public final f b;
    public final f c;

    public SocialConnectSessionUpdateJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("reason", "session");
        v03.i(bVarA, "of(\"reason\", \"session\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(String.class, emptySet, "reason");
        v03.i(fVarF, "moshi.adapter(String::cl…    emptySet(), \"reason\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(SocialConnectSession.class, emptySet, "session");
        v03.i(fVarF2, "moshi.adapter(SocialConn…a, emptySet(), \"session\")");
        this.c = fVarF2;
    }

    @Override // com.squareup.moshi.f
    public final SocialConnectSessionUpdate fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String str = null;
        boolean z = false;
        boolean z2 = false;
        SocialConnectSession socialConnectSession = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                str = (String) this.b.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                socialConnectSession = (SocialConnectSession) this.c.fromJson(jsonReader);
                z2 = true;
            }
        }
        jsonReader.q();
        SocialConnectSessionUpdate socialConnectSessionUpdate = new SocialConnectSessionUpdate();
        if (z) {
            socialConnectSessionUpdate.a = str;
        }
        if (z2) {
            socialConnectSessionUpdate.b = socialConnectSession;
        }
        return socialConnectSessionUpdate;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, SocialConnectSessionUpdate socialConnectSessionUpdate) {
        SocialConnectSessionUpdate socialConnectSessionUpdate2 = socialConnectSessionUpdate;
        v03.j(mVar, "writer");
        if (socialConnectSessionUpdate2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("reason");
        this.b.toJson(mVar, socialConnectSessionUpdate2.a);
        mVar.J0("session");
        this.c.toJson(mVar, socialConnectSessionUpdate2.b);
        mVar.X();
    }

    public final String toString() {
        return u4.b(48, "GeneratedJsonAdapter(SocialConnectSessionUpdate)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
