package com.spotify.automotive.auth.model;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class LoginOptionsJsonAdapter extends f<LoginOptions> {
    public final JsonReader.b a;
    public final f b;
    public volatile Constructor c;

    public LoginOptionsJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("bootstrap_required", "auth_only_setting");
        v03.i(bVarA, "of(\"bootstrap_required\",…     \"auth_only_setting\")");
        this.a = bVarA;
        f fVarF = oVar.f(Boolean.TYPE, EmptySet.c, "isBootstrapRequired");
        v03.i(fVarF, "moshi.adapter(Boolean::c…   \"isBootstrapRequired\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final LoginOptions fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        Boolean bool = Boolean.FALSE;
        jsonReader.c();
        Boolean bool2 = null;
        int i = -1;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                bool2 = (Boolean) this.b.fromJson(jsonReader);
                if (bool2 == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("isBootstrapRequired", "bootstrap_required", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"isBootst…tstrap_required\", reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1) {
                bool = (Boolean) this.b.fromJson(jsonReader);
                if (bool == null) {
                    JsonDataException jsonDataExceptionW2 = ds2.w("authOnly", "auth_only_setting", jsonReader);
                    v03.i(jsonDataExceptionW2, "unexpectedNull(\"authOnly…th_only_setting\", reader)");
                    throw jsonDataExceptionW2;
                }
                i &= -3;
            } else {
                continue;
            }
        }
        jsonReader.q();
        if (i == -3) {
            if (bool2 != null) {
                return new LoginOptions(bool2.booleanValue(), bool.booleanValue());
            }
            JsonDataException jsonDataExceptionO = ds2.o("isBootstrapRequired", "bootstrap_required", jsonReader);
            v03.i(jsonDataExceptionO, "missingProperty(\"isBoots…tstrap_required\", reader)");
            throw jsonDataExceptionO;
        }
        Constructor declaredConstructor = this.c;
        if (declaredConstructor == null) {
            Class cls = Boolean.TYPE;
            declaredConstructor = LoginOptions.class.getDeclaredConstructor(cls, cls, Integer.TYPE, ds2.c);
            this.c = declaredConstructor;
            v03.i(declaredConstructor, "LoginOptions::class.java…his.constructorRef = it }");
        }
        Object[] objArr = new Object[4];
        if (bool2 == null) {
            JsonDataException jsonDataExceptionO2 = ds2.o("isBootstrapRequired", "bootstrap_required", jsonReader);
            v03.i(jsonDataExceptionO2, "missingProperty(\"isBoots…tstrap_required\", reader)");
            throw jsonDataExceptionO2;
        }
        objArr[0] = Boolean.valueOf(bool2.booleanValue());
        objArr[1] = bool;
        objArr[2] = Integer.valueOf(i);
        objArr[3] = null;
        Object objNewInstance = declaredConstructor.newInstance(objArr);
        v03.i(objNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return (LoginOptions) objNewInstance;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, LoginOptions loginOptions) {
        LoginOptions loginOptions2 = loginOptions;
        v03.j(mVar, "writer");
        if (loginOptions2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("bootstrap_required");
        Boolean boolValueOf = Boolean.valueOf(loginOptions2.a);
        f fVar = this.b;
        fVar.toJson(mVar, boolValueOf);
        mVar.J0("auth_only_setting");
        fVar.toJson(mVar, Boolean.valueOf(loginOptions2.b));
        mVar.X();
    }

    public final String toString() {
        return u4.b(34, "GeneratedJsonAdapter(LoginOptions)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
