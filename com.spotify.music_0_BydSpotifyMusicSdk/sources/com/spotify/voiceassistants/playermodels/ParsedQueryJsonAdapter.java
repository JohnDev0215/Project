package com.spotify.voiceassistants.playermodels;

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
public final class ParsedQueryJsonAdapter extends f<ParsedQuery> {
    private volatile Constructor<ParsedQuery> constructorRef;
    private final JsonReader.b options;
    private final f<String> stringAdapter;

    public ParsedQueryJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("intent", "uri");
        v03.i(bVarA, "of(\"intent\", \"uri\")");
        this.options = bVarA;
        f<String> fVarF = oVar.f(String.class, EmptySet.c, "intent");
        v03.i(fVarF, "moshi.adapter(String::cl…ptySet(),\n      \"intent\")");
        this.stringAdapter = fVarF;
    }

    public String toString() {
        return u4.b(33, "GeneratedJsonAdapter(ParsedQuery)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public ParsedQuery fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        String strFromJson2 = null;
        int i = -1;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("intent", "intent", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"intent\",…t\",\n              reader)");
                    throw jsonDataExceptionW;
                }
                i &= -2;
            } else if (iM0 == 1 && (strFromJson2 = this.stringAdapter.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW2 = ds2.w("uri", "uri", jsonReader);
                v03.i(jsonDataExceptionW2, "unexpectedNull(\"uri\", \"uri\", reader)");
                throw jsonDataExceptionW2;
            }
        }
        jsonReader.q();
        if (i == -2) {
            v03.g(strFromJson, "null cannot be cast to non-null type kotlin.String");
            if (strFromJson2 != null) {
                return new ParsedQuery(strFromJson, strFromJson2);
            }
            JsonDataException jsonDataExceptionO = ds2.o("uri", "uri", jsonReader);
            v03.i(jsonDataExceptionO, "missingProperty(\"uri\", \"uri\", reader)");
            throw jsonDataExceptionO;
        }
        Constructor<ParsedQuery> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            declaredConstructor = ParsedQuery.class.getDeclaredConstructor(String.class, String.class, Integer.TYPE, ds2.c);
            this.constructorRef = declaredConstructor;
            v03.i(declaredConstructor, "ParsedQuery::class.java.…his.constructorRef = it }");
        }
        Object[] objArr = new Object[4];
        objArr[0] = strFromJson;
        if (strFromJson2 == null) {
            JsonDataException jsonDataExceptionO2 = ds2.o("uri", "uri", jsonReader);
            v03.i(jsonDataExceptionO2, "missingProperty(\"uri\", \"uri\", reader)");
            throw jsonDataExceptionO2;
        }
        objArr[1] = strFromJson2;
        objArr[2] = Integer.valueOf(i);
        objArr[3] = null;
        ParsedQuery parsedQueryNewInstance = declaredConstructor.newInstance(objArr);
        v03.i(parsedQueryNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return parsedQueryNewInstance;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, ParsedQuery parsedQuery) {
        v03.j(mVar, "writer");
        if (parsedQuery == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("intent");
        this.stringAdapter.toJson(mVar, parsedQuery.getIntent());
        mVar.J0("uri");
        this.stringAdapter.toJson(mVar, parsedQuery.getUri());
        mVar.X();
    }
}
