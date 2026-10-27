package com.spotify.automotive.auth.model;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class LoginRequestJsonAdapter extends f<LoginRequest> {
    public final JsonReader.b a;
    public final f b;
    public final f c;

    public LoginRequestJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("credentials", "options");
        v03.i(bVarA, "of(\"credentials\", \"options\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(LoginCredentials.class, emptySet, "credentials");
        v03.i(fVarF, "moshi.adapter(LoginCrede…mptySet(), \"credentials\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(LoginOptions.class, emptySet, "loginOptions");
        v03.i(fVarF2, "moshi.adapter(LoginOptio…ptySet(), \"loginOptions\")");
        this.c = fVarF2;
    }

    @Override // com.squareup.moshi.f
    public final LoginRequest fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        LoginCredentials loginCredentials = null;
        boolean z = false;
        boolean z2 = false;
        LoginOptions loginOptions = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                loginCredentials = (LoginCredentials) this.b.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                loginOptions = (LoginOptions) this.c.fromJson(jsonReader);
                z2 = true;
            }
        }
        jsonReader.q();
        LoginRequest loginRequest = new LoginRequest();
        if (z) {
            loginRequest.a = loginCredentials;
        }
        if (z2) {
            loginRequest.b = loginOptions;
        }
        return loginRequest;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, LoginRequest loginRequest) {
        LoginRequest loginRequest2 = loginRequest;
        v03.j(mVar, "writer");
        if (loginRequest2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("credentials");
        this.b.toJson(mVar, loginRequest2.a);
        mVar.J0("options");
        this.c.toJson(mVar, loginRequest2.b);
        mVar.X();
    }

    public final String toString() {
        return u4.b(34, "GeneratedJsonAdapter(LoginRequest)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
