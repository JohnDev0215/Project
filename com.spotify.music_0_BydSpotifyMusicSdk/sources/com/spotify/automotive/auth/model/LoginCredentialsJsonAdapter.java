package com.spotify.automotive.auth.model;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class LoginCredentialsJsonAdapter extends f<LoginCredentials> {
    public final JsonReader.b a;
    public final f b;

    public LoginCredentialsJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("authenticationType", "blob", "password", "refreshToken", "username");
        v03.i(bVarA, "of(\"authenticationType\",…efreshToken\", \"username\")");
        this.a = bVarA;
        f fVarF = oVar.f(String.class, EmptySet.c, "authenticationType");
        v03.i(fVarF, "moshi.adapter(String::cl…(), \"authenticationType\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final LoginCredentials fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        String str5 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 != -1) {
                f fVar = this.b;
                if (iM0 == 0) {
                    str = (String) fVar.fromJson(jsonReader);
                    z = true;
                } else if (iM0 == 1) {
                    str5 = (String) fVar.fromJson(jsonReader);
                    z2 = true;
                } else if (iM0 == 2) {
                    str2 = (String) fVar.fromJson(jsonReader);
                    z3 = true;
                } else if (iM0 == 3) {
                    str3 = (String) fVar.fromJson(jsonReader);
                    z4 = true;
                } else if (iM0 == 4) {
                    str4 = (String) fVar.fromJson(jsonReader);
                    z5 = true;
                }
            } else {
                jsonReader.Q0();
                jsonReader.D();
            }
        }
        jsonReader.q();
        LoginCredentials loginCredentials = new LoginCredentials();
        if (z) {
            loginCredentials.a = str;
        }
        if (z2) {
            loginCredentials.c = str5;
        }
        if (z3) {
            loginCredentials.d = str2;
        }
        if (z4) {
            loginCredentials.e = str3;
        }
        if (z5) {
            loginCredentials.b = str4;
        }
        return loginCredentials;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, LoginCredentials loginCredentials) {
        LoginCredentials loginCredentials2 = loginCredentials;
        v03.j(mVar, "writer");
        if (loginCredentials2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("authenticationType");
        String str = loginCredentials2.a;
        f fVar = this.b;
        fVar.toJson(mVar, str);
        mVar.J0("blob");
        fVar.toJson(mVar, loginCredentials2.c);
        mVar.J0("password");
        fVar.toJson(mVar, loginCredentials2.d);
        mVar.J0("refreshToken");
        fVar.toJson(mVar, loginCredentials2.e);
        mVar.J0("username");
        fVar.toJson(mVar, loginCredentials2.b);
        mVar.X();
    }

    public final String toString() {
        return u4.b(38, "GeneratedJsonAdapter(LoginCredentials)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
