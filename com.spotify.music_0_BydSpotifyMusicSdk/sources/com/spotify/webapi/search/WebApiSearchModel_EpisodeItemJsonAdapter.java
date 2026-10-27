package com.spotify.webapi.search;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class WebApiSearchModel_EpisodeItemJsonAdapter extends f<WebApiSearchModel$EpisodeItem> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;
    public volatile Constructor e;

    public WebApiSearchModel_EpisodeItemJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("uri", "name", "explicit", "images");
        v03.i(bVarA, "of(\"uri\", \"name\", \"explicit\",\n      \"images\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(String.class, emptySet, "uri");
        v03.i(fVarF, "moshi.adapter(String::cl… emptySet(),\n      \"uri\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(Boolean.TYPE, emptySet, "isExplicit");
        v03.i(fVarF2, "moshi.adapter(Boolean::c…et(),\n      \"isExplicit\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(zo2.j(List.class, WebApiSearchModel$Image.class), emptySet, "images");
        v03.i(fVarF3, "moshi.adapter(Types.newP…a), emptySet(), \"images\")");
        this.d = fVarF3;
    }

    @Override // com.squareup.moshi.f
    public final WebApiSearchModel$EpisodeItem fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        Boolean bool = Boolean.FALSE;
        jsonReader.c();
        int i = -1;
        String str = null;
        String str2 = null;
        List list = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                str = (String) this.b.fromJson(jsonReader);
                if (str == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("uri", "uri", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"uri\", \"uri\", reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1) {
                str2 = (String) this.b.fromJson(jsonReader);
                if (str2 == null) {
                    JsonDataException jsonDataExceptionW2 = ds2.w("name", "name", jsonReader);
                    v03.i(jsonDataExceptionW2, "unexpectedNull(\"name\", \"name\",\n            reader)");
                    throw jsonDataExceptionW2;
                }
            } else if (iM0 == 2) {
                bool = (Boolean) this.c.fromJson(jsonReader);
                if (bool == null) {
                    JsonDataException jsonDataExceptionW3 = ds2.w("isExplicit", "explicit", jsonReader);
                    v03.i(jsonDataExceptionW3, "unexpectedNull(\"isExplic…      \"explicit\", reader)");
                    throw jsonDataExceptionW3;
                }
                i &= -5;
            } else if (iM0 == 3) {
                list = (List) this.d.fromJson(jsonReader);
                i &= -9;
            }
        }
        jsonReader.q();
        if (i == -13) {
            if (str == null) {
                JsonDataException jsonDataExceptionO = ds2.o("uri", "uri", jsonReader);
                v03.i(jsonDataExceptionO, "missingProperty(\"uri\", \"uri\", reader)");
                throw jsonDataExceptionO;
            }
            if (str2 != null) {
                return new WebApiSearchModel$EpisodeItem(str, str2, bool.booleanValue(), list);
            }
            JsonDataException jsonDataExceptionO2 = ds2.o("name", "name", jsonReader);
            v03.i(jsonDataExceptionO2, "missingProperty(\"name\", \"name\", reader)");
            throw jsonDataExceptionO2;
        }
        Constructor declaredConstructor = this.e;
        if (declaredConstructor == null) {
            declaredConstructor = WebApiSearchModel$EpisodeItem.class.getDeclaredConstructor(String.class, String.class, Boolean.TYPE, List.class, Integer.TYPE, ds2.c);
            this.e = declaredConstructor;
            v03.i(declaredConstructor, "WebApiSearchModel.Episod…his.constructorRef = it }");
        }
        Object[] objArr = new Object[6];
        if (str == null) {
            JsonDataException jsonDataExceptionO3 = ds2.o("uri", "uri", jsonReader);
            v03.i(jsonDataExceptionO3, "missingProperty(\"uri\", \"uri\", reader)");
            throw jsonDataExceptionO3;
        }
        objArr[0] = str;
        if (str2 == null) {
            JsonDataException jsonDataExceptionO4 = ds2.o("name", "name", jsonReader);
            v03.i(jsonDataExceptionO4, "missingProperty(\"name\", \"name\", reader)");
            throw jsonDataExceptionO4;
        }
        objArr[1] = str2;
        objArr[2] = bool;
        objArr[3] = list;
        objArr[4] = Integer.valueOf(i);
        objArr[5] = null;
        Object objNewInstance = declaredConstructor.newInstance(objArr);
        v03.i(objNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return (WebApiSearchModel$EpisodeItem) objNewInstance;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, WebApiSearchModel$EpisodeItem webApiSearchModel$EpisodeItem) {
        WebApiSearchModel$EpisodeItem webApiSearchModel$EpisodeItem2 = webApiSearchModel$EpisodeItem;
        v03.j(mVar, "writer");
        if (webApiSearchModel$EpisodeItem2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("uri");
        String str = webApiSearchModel$EpisodeItem2.a;
        f fVar = this.b;
        fVar.toJson(mVar, str);
        mVar.J0("name");
        fVar.toJson(mVar, webApiSearchModel$EpisodeItem2.b);
        mVar.J0("explicit");
        this.c.toJson(mVar, Boolean.valueOf(webApiSearchModel$EpisodeItem2.c));
        mVar.J0("images");
        this.d.toJson(mVar, webApiSearchModel$EpisodeItem2.d);
        mVar.X();
    }

    public final String toString() {
        return u4.b(51, "GeneratedJsonAdapter(WebApiSearchModel.EpisodeItem)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
