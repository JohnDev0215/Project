package com.spotify.automotive.httpwebgategson;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.List;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class TokenResponseJsonAdapter extends f<TokenResponse> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;

    public TokenResponseJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("accessToken", "errorCode", "errorDescription", "expiresIn", "refreshToken", "scope", "tokenType");
        v03.i(bVarA, "of(\"accessToken\", \"error…n\", \"scope\", \"tokenType\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(String.class, emptySet, "accessToken");
        v03.i(fVarF, "moshi.adapter(String::cl…mptySet(), \"accessToken\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(Integer.TYPE, emptySet, "errorCode");
        v03.i(fVarF2, "moshi.adapter(Int::class… emptySet(), \"errorCode\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(zo2.j(List.class, String.class), emptySet, "scope");
        v03.i(fVarF3, "moshi.adapter(Types.newP…mptySet(),\n      \"scope\")");
        this.d = fVarF3;
    }

    @Override // com.squareup.moshi.f
    public final TokenResponse fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String str = null;
        String str2 = null;
        Integer num = null;
        String str3 = null;
        List<String> list = null;
        String str4 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        Integer num2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            f fVar = this.c;
            String str5 = str4;
            f fVar2 = this.b;
            switch (iM0) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    str = (String) fVar2.fromJson(jsonReader);
                    str4 = str5;
                    z = true;
                    continue;
                case 1:
                    num2 = (Integer) fVar.fromJson(jsonReader);
                    if (num2 == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("errorCode", "errorCode", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"errorCod…     \"errorCode\", reader)");
                        throw jsonDataExceptionW;
                    }
                    break;
                case 2:
                    str2 = (String) fVar2.fromJson(jsonReader);
                    str4 = str5;
                    z2 = true;
                    continue;
                case 3:
                    num = (Integer) fVar.fromJson(jsonReader);
                    if (num == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("expiresIn", "expiresIn", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"expiresI…     \"expiresIn\", reader)");
                        throw jsonDataExceptionW2;
                    }
                    break;
                case 4:
                    str3 = (String) fVar2.fromJson(jsonReader);
                    str4 = str5;
                    z3 = true;
                    continue;
                case 5:
                    list = (List) this.d.fromJson(jsonReader);
                    str4 = str5;
                    z4 = true;
                    continue;
                case 6:
                    str4 = (String) fVar2.fromJson(jsonReader);
                    z5 = true;
                    continue;
            }
            str4 = str5;
        }
        String str6 = str4;
        jsonReader.q();
        TokenResponse tokenResponse = new TokenResponse();
        if (z) {
            tokenResponse.b = str;
        }
        tokenResponse.f = num2 != null ? num2.intValue() : tokenResponse.f;
        if (z2) {
            tokenResponse.g = str2;
        }
        tokenResponse.c = num != null ? num.intValue() : tokenResponse.c;
        if (z3) {
            tokenResponse.a = str3;
        }
        if (z4) {
            tokenResponse.e = list;
        }
        if (z5) {
            tokenResponse.d = str6;
        }
        return tokenResponse;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, TokenResponse tokenResponse) {
        TokenResponse tokenResponse2 = tokenResponse;
        v03.j(mVar, "writer");
        if (tokenResponse2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("accessToken");
        String str = tokenResponse2.b;
        f fVar = this.b;
        fVar.toJson(mVar, str);
        mVar.J0("errorCode");
        Integer numValueOf = Integer.valueOf(tokenResponse2.f);
        f fVar2 = this.c;
        fVar2.toJson(mVar, numValueOf);
        mVar.J0("errorDescription");
        fVar.toJson(mVar, tokenResponse2.g);
        mVar.J0("expiresIn");
        u4.s(tokenResponse2.c, fVar2, mVar, "refreshToken");
        fVar.toJson(mVar, tokenResponse2.a);
        mVar.J0("scope");
        this.d.toJson(mVar, tokenResponse2.e);
        mVar.J0("tokenType");
        fVar.toJson(mVar, tokenResponse2.d);
        mVar.X();
    }

    public final String toString() {
        return u4.b(35, "GeneratedJsonAdapter(TokenResponse)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
