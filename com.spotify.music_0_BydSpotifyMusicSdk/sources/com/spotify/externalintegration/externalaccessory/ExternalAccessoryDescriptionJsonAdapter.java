package com.spotify.externalintegration.externalaccessory;

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
public final class ExternalAccessoryDescriptionJsonAdapter extends f<ExternalAccessoryDescription> {
    public final JsonReader.b a;
    public final f b;
    public volatile Constructor c;

    public ExternalAccessoryDescriptionJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("integration_type", "client_id", "name", "transport_type", "connection_label", "category", "company", "model", "version", "protocol", "sender_id");
        v03.i(bVarA, "of(\"integration_type\", \"… \"protocol\", \"sender_id\")");
        this.a = bVarA;
        f fVarF = oVar.f(String.class, EmptySet.c, "integrationType");
        v03.i(fVarF, "moshi.adapter(String::cl…\n      \"integrationType\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final ExternalAccessoryDescription fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String str = null;
        int i = -1;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.M0(this.a)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    str = (String) this.b.fromJson(jsonReader);
                    if (str == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("integrationType", "integration_type", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"integrat…ntegration_type\", reader)");
                        throw jsonDataExceptionW;
                    }
                    i &= -2;
                    break;
                    break;
                case 1:
                    str2 = (String) this.b.fromJson(jsonReader);
                    if (str2 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("clientId", "client_id", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"clientId…     \"client_id\", reader)");
                        throw jsonDataExceptionW2;
                    }
                    i &= -3;
                    break;
                    break;
                case 2:
                    str3 = (String) this.b.fromJson(jsonReader);
                    if (str3 == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("name", "name", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"name\", \"name\", reader)");
                        throw jsonDataExceptionW3;
                    }
                    i &= -5;
                    break;
                    break;
                case 3:
                    str4 = (String) this.b.fromJson(jsonReader);
                    if (str4 == null) {
                        JsonDataException jsonDataExceptionW4 = ds2.w("transportType", "transport_type", jsonReader);
                        v03.i(jsonDataExceptionW4, "unexpectedNull(\"transpor…\"transport_type\", reader)");
                        throw jsonDataExceptionW4;
                    }
                    i &= -9;
                    break;
                    break;
                case 4:
                    str5 = (String) this.b.fromJson(jsonReader);
                    if (str5 == null) {
                        JsonDataException jsonDataExceptionW5 = ds2.w("connectionLabel", "connection_label", jsonReader);
                        v03.i(jsonDataExceptionW5, "unexpectedNull(\"connecti…onnection_label\", reader)");
                        throw jsonDataExceptionW5;
                    }
                    i &= -17;
                    break;
                    break;
                case 5:
                    str6 = (String) this.b.fromJson(jsonReader);
                    if (str6 == null) {
                        JsonDataException jsonDataExceptionW6 = ds2.w("category", "category", jsonReader);
                        v03.i(jsonDataExceptionW6, "unexpectedNull(\"category…      \"category\", reader)");
                        throw jsonDataExceptionW6;
                    }
                    i &= -33;
                    break;
                    break;
                case 6:
                    str7 = (String) this.b.fromJson(jsonReader);
                    if (str7 == null) {
                        JsonDataException jsonDataExceptionW7 = ds2.w("company", "company", jsonReader);
                        v03.i(jsonDataExceptionW7, "unexpectedNull(\"company\"…       \"company\", reader)");
                        throw jsonDataExceptionW7;
                    }
                    i &= -65;
                    break;
                    break;
                case 7:
                    str8 = (String) this.b.fromJson(jsonReader);
                    if (str8 == null) {
                        JsonDataException jsonDataExceptionW8 = ds2.w("model", "model", jsonReader);
                        v03.i(jsonDataExceptionW8, "unexpectedNull(\"model\", …l\",\n              reader)");
                        throw jsonDataExceptionW8;
                    }
                    i &= -129;
                    break;
                    break;
                case 8:
                    str9 = (String) this.b.fromJson(jsonReader);
                    if (str9 == null) {
                        JsonDataException jsonDataExceptionW9 = ds2.w("version", "version", jsonReader);
                        v03.i(jsonDataExceptionW9, "unexpectedNull(\"version\"…       \"version\", reader)");
                        throw jsonDataExceptionW9;
                    }
                    i &= -257;
                    break;
                    break;
                case 9:
                    str10 = (String) this.b.fromJson(jsonReader);
                    if (str10 == null) {
                        JsonDataException jsonDataExceptionW10 = ds2.w("protocol", "protocol", jsonReader);
                        v03.i(jsonDataExceptionW10, "unexpectedNull(\"protocol…      \"protocol\", reader)");
                        throw jsonDataExceptionW10;
                    }
                    i &= -513;
                    break;
                    break;
                case 10:
                    str11 = (String) this.b.fromJson(jsonReader);
                    if (str11 == null) {
                        JsonDataException jsonDataExceptionW11 = ds2.w("senderId", "sender_id", jsonReader);
                        v03.i(jsonDataExceptionW11, "unexpectedNull(\"senderId…     \"sender_id\", reader)");
                        throw jsonDataExceptionW11;
                    }
                    i &= -1025;
                    break;
                    break;
            }
        }
        jsonReader.q();
        if (i != -2048) {
            Constructor declaredConstructor = this.c;
            if (declaredConstructor == null) {
                declaredConstructor = ExternalAccessoryDescription.class.getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Integer.TYPE, ds2.c);
                this.c = declaredConstructor;
                v03.i(declaredConstructor, "ExternalAccessoryDescrip…his.constructorRef = it }");
            }
            Object objNewInstance = declaredConstructor.newInstance(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, Integer.valueOf(i), null);
            v03.i(objNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
            return (ExternalAccessoryDescription) objNewInstance;
        }
        v03.g(str, "null cannot be cast to non-null type kotlin.String");
        v03.g(str2, "null cannot be cast to non-null type kotlin.String");
        v03.g(str3, "null cannot be cast to non-null type kotlin.String");
        v03.g(str4, "null cannot be cast to non-null type kotlin.String");
        v03.g(str5, "null cannot be cast to non-null type kotlin.String");
        v03.g(str6, "null cannot be cast to non-null type kotlin.String");
        v03.g(str7, "null cannot be cast to non-null type kotlin.String");
        v03.g(str8, "null cannot be cast to non-null type kotlin.String");
        v03.g(str9, "null cannot be cast to non-null type kotlin.String");
        v03.g(str10, "null cannot be cast to non-null type kotlin.String");
        v03.g(str11, "null cannot be cast to non-null type kotlin.String");
        return new ExternalAccessoryDescription(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, ExternalAccessoryDescription externalAccessoryDescription) {
        ExternalAccessoryDescription externalAccessoryDescription2 = externalAccessoryDescription;
        v03.j(mVar, "writer");
        if (externalAccessoryDescription2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("integration_type");
        String str = externalAccessoryDescription2.a;
        f fVar = this.b;
        fVar.toJson(mVar, str);
        mVar.J0("client_id");
        fVar.toJson(mVar, externalAccessoryDescription2.b);
        mVar.J0("name");
        fVar.toJson(mVar, externalAccessoryDescription2.c);
        mVar.J0("transport_type");
        fVar.toJson(mVar, externalAccessoryDescription2.d);
        mVar.J0("connection_label");
        fVar.toJson(mVar, externalAccessoryDescription2.e);
        mVar.J0("category");
        fVar.toJson(mVar, externalAccessoryDescription2.f);
        mVar.J0("company");
        fVar.toJson(mVar, externalAccessoryDescription2.g);
        mVar.J0("model");
        fVar.toJson(mVar, externalAccessoryDescription2.h);
        mVar.J0("version");
        fVar.toJson(mVar, externalAccessoryDescription2.i);
        mVar.J0("protocol");
        fVar.toJson(mVar, externalAccessoryDescription2.j);
        mVar.J0("sender_id");
        fVar.toJson(mVar, externalAccessoryDescription2.k);
        mVar.X();
    }

    public final String toString() {
        return u4.b(50, "GeneratedJsonAdapter(ExternalAccessoryDescription)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
