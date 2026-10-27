package com.spotify.playerlimited.player.models;

import com.spotify.connectivity.productstate.RxProductState;
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
public final class LoginResponseJsonAdapter extends f<LoginResponse> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;

    public LoginResponseJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("accessToken", "canonical_phone_number", "challenge_id", "code_length", "expires_in", "identifier_token", "locale", "method", "retry_number", "storedCredentials", "tokenType", RxProductState.Keys.KEY_TYPE, "username");
        v03.i(bVarA, "of(\"accessToken\",\n      …ype\", \"type\", \"username\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(String.class, emptySet, "accessToken");
        v03.i(fVarF, "moshi.adapter(String::cl…mptySet(), \"accessToken\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(Long.TYPE, emptySet, "codeLength");
        v03.i(fVarF2, "moshi.adapter(Long::clas…et(),\n      \"codeLength\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(Integer.TYPE, emptySet, "method");
        v03.i(fVarF3, "moshi.adapter(Int::class…va, emptySet(), \"method\")");
        this.d = fVarF3;
    }

    @Override // com.squareup.moshi.f
    public final LoginResponse fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String str = null;
        String str2 = null;
        Long l = null;
        Long l2 = null;
        String str3 = null;
        String str4 = null;
        Integer num = null;
        Integer num2 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        String str9 = null;
        while (jsonReader.hasNext()) {
            String str10 = str8;
            int iM0 = jsonReader.M0(this.a);
            String str11 = str7;
            f fVar = this.c;
            String str12 = str6;
            f fVar2 = this.d;
            String str13 = str5;
            f fVar3 = this.b;
            switch (iM0) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    str8 = str10;
                    str7 = str11;
                    str6 = str12;
                    str5 = str13;
                    break;
                case 0:
                    str = (String) fVar3.fromJson(jsonReader);
                    str8 = str10;
                    str7 = str11;
                    str6 = str12;
                    z = true;
                    str5 = str13;
                    break;
                case 1:
                    str9 = (String) fVar3.fromJson(jsonReader);
                    str8 = str10;
                    str7 = str11;
                    str6 = str12;
                    z2 = true;
                    str5 = str13;
                    break;
                case 2:
                    str2 = (String) fVar3.fromJson(jsonReader);
                    str8 = str10;
                    str7 = str11;
                    str6 = str12;
                    z3 = true;
                    str5 = str13;
                    break;
                case 3:
                    l = (Long) fVar.fromJson(jsonReader);
                    if (l == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("codeLength", "code_length", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"codeLeng…   \"code_length\", reader)");
                        throw jsonDataExceptionW;
                    }
                    str8 = str10;
                    str7 = str11;
                    str6 = str12;
                    str5 = str13;
                    break;
                case 4:
                    l2 = (Long) fVar.fromJson(jsonReader);
                    if (l2 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("expiresIn", "expires_in", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"expiresI…    \"expires_in\", reader)");
                        throw jsonDataExceptionW2;
                    }
                    str8 = str10;
                    str7 = str11;
                    str6 = str12;
                    str5 = str13;
                    break;
                case 5:
                    str3 = (String) fVar3.fromJson(jsonReader);
                    str8 = str10;
                    str7 = str11;
                    str6 = str12;
                    z4 = true;
                    str5 = str13;
                    break;
                case 6:
                    str4 = (String) fVar3.fromJson(jsonReader);
                    str8 = str10;
                    str7 = str11;
                    str6 = str12;
                    z5 = true;
                    str5 = str13;
                    break;
                case 7:
                    num = (Integer) fVar2.fromJson(jsonReader);
                    if (num == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("method", "method", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"method\",…hod\",\n            reader)");
                        throw jsonDataExceptionW3;
                    }
                    str8 = str10;
                    str7 = str11;
                    str6 = str12;
                    str5 = str13;
                    break;
                case 8:
                    num2 = (Integer) fVar2.fromJson(jsonReader);
                    if (num2 == null) {
                        JsonDataException jsonDataExceptionW4 = ds2.w("retryNumber", "retry_number", jsonReader);
                        v03.i(jsonDataExceptionW4, "unexpectedNull(\"retryNum…  \"retry_number\", reader)");
                        throw jsonDataExceptionW4;
                    }
                    str8 = str10;
                    str7 = str11;
                    str6 = str12;
                    str5 = str13;
                    break;
                case 9:
                    str5 = (String) fVar3.fromJson(jsonReader);
                    str8 = str10;
                    str7 = str11;
                    str6 = str12;
                    z6 = true;
                    break;
                case 10:
                    str6 = (String) fVar3.fromJson(jsonReader);
                    str8 = str10;
                    str7 = str11;
                    z7 = true;
                    str5 = str13;
                    break;
                case 11:
                    str7 = (String) fVar3.fromJson(jsonReader);
                    str8 = str10;
                    str6 = str12;
                    z8 = true;
                    str5 = str13;
                    break;
                case 12:
                    str8 = (String) fVar3.fromJson(jsonReader);
                    str7 = str11;
                    str6 = str12;
                    z9 = true;
                    str5 = str13;
                    break;
                default:
                    str8 = str10;
                    str7 = str11;
                    str6 = str12;
                    str5 = str13;
                    break;
            }
        }
        String str14 = str5;
        String str15 = str6;
        String str16 = str7;
        String str17 = str8;
        jsonReader.q();
        LoginResponse loginResponse = new LoginResponse();
        if (z) {
            loginResponse.f = str;
        }
        if (z2) {
            loginResponse.j = str9;
        }
        if (z3) {
            loginResponse.g = str2;
        }
        loginResponse.i = l != null ? l.longValue() : loginResponse.i;
        loginResponse.k = l2 != null ? l2.longValue() : loginResponse.k;
        if (z4) {
            loginResponse.b = str3;
        }
        if (z5) {
            loginResponse.e = str4;
        }
        loginResponse.h = num != null ? num.intValue() : loginResponse.h;
        loginResponse.m = num2 != null ? num2.intValue() : loginResponse.m;
        if (z6) {
            loginResponse.d = str14;
        }
        if (z7) {
            loginResponse.l = str15;
        }
        if (z8) {
            loginResponse.a = str16;
        }
        if (z9) {
            loginResponse.c = str17;
        }
        return loginResponse;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, LoginResponse loginResponse) {
        LoginResponse loginResponse2 = loginResponse;
        v03.j(mVar, "writer");
        if (loginResponse2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("accessToken");
        String str = loginResponse2.f;
        f fVar = this.b;
        fVar.toJson(mVar, str);
        mVar.J0("canonical_phone_number");
        fVar.toJson(mVar, loginResponse2.j);
        mVar.J0("challenge_id");
        fVar.toJson(mVar, loginResponse2.g);
        mVar.J0("code_length");
        Long lValueOf = Long.valueOf(loginResponse2.i);
        f fVar2 = this.c;
        fVar2.toJson(mVar, lValueOf);
        mVar.J0("expires_in");
        fVar2.toJson(mVar, Long.valueOf(loginResponse2.k));
        mVar.J0("identifier_token");
        fVar.toJson(mVar, loginResponse2.b);
        mVar.J0("locale");
        fVar.toJson(mVar, loginResponse2.e);
        mVar.J0("method");
        Integer numValueOf = Integer.valueOf(loginResponse2.h);
        f fVar3 = this.d;
        fVar3.toJson(mVar, numValueOf);
        mVar.J0("retry_number");
        u4.s(loginResponse2.m, fVar3, mVar, "storedCredentials");
        fVar.toJson(mVar, loginResponse2.d);
        mVar.J0("tokenType");
        fVar.toJson(mVar, loginResponse2.l);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        fVar.toJson(mVar, loginResponse2.a);
        mVar.J0("username");
        fVar.toJson(mVar, loginResponse2.c);
        mVar.X();
    }

    public final String toString() {
        return u4.b(35, "GeneratedJsonAdapter(LoginResponse)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
