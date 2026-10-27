package com.spotify.webapi.search;

import com.spotify.webapi.service.models.Search;
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
public final class WebApiSearchModel_TrackItemJsonAdapter extends f<WebApiSearchModel$TrackItem> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;
    public final f e;
    public final f f;
    public volatile Constructor g;

    public WebApiSearchModel_TrackItemJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("uri", "name", "explicit", Search.Type.ALBUM, "artists", "tags");
        v03.i(bVarA, "of(\"uri\", \"name\", \"expli…lbum\", \"artists\", \"tags\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(String.class, emptySet, "uri");
        v03.i(fVarF, "moshi.adapter(String::cl… emptySet(),\n      \"uri\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(Boolean.TYPE, emptySet, "isExplicit");
        v03.i(fVarF2, "moshi.adapter(Boolean::c…et(),\n      \"isExplicit\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(WebApiSearchModel$Album.class, emptySet, Search.Type.ALBUM);
        v03.i(fVarF3, "moshi.adapter(WebApiSear…ava, emptySet(), \"album\")");
        this.d = fVarF3;
        f fVarF4 = oVar.f(zo2.j(List.class, WebApiSearchModel$Artist.class), emptySet, "artists");
        v03.i(fVarF4, "moshi.adapter(Types.newP…), emptySet(), \"artists\")");
        this.e = fVarF4;
        f fVarF5 = oVar.f(zo2.j(List.class, String.class), emptySet, "tags");
        v03.i(fVarF5, "moshi.adapter(Types.newP…emptySet(),\n      \"tags\")");
        this.f = fVarF5;
    }

    @Override // com.squareup.moshi.f
    public final WebApiSearchModel$TrackItem fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        Boolean bool = Boolean.FALSE;
        jsonReader.c();
        int i = -1;
        String str = null;
        String str2 = null;
        WebApiSearchModel$Album webApiSearchModel$Album = null;
        List list = null;
        List list2 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.M0(this.a)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    str = (String) this.b.fromJson(jsonReader);
                    if (str == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("uri", "uri", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"uri\", \"uri\", reader)");
                        throw jsonDataExceptionW;
                    }
                    break;
                    break;
                case 1:
                    str2 = (String) this.b.fromJson(jsonReader);
                    if (str2 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("name", "name", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"name\", \"name\",\n            reader)");
                        throw jsonDataExceptionW2;
                    }
                    break;
                    break;
                case 2:
                    bool = (Boolean) this.c.fromJson(jsonReader);
                    if (bool == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("isExplicit", "explicit", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"isExplic…      \"explicit\", reader)");
                        throw jsonDataExceptionW3;
                    }
                    i &= -5;
                    break;
                    break;
                case 3:
                    webApiSearchModel$Album = (WebApiSearchModel$Album) this.d.fromJson(jsonReader);
                    i &= -9;
                    break;
                case 4:
                    list = (List) this.e.fromJson(jsonReader);
                    i &= -17;
                    break;
                case 5:
                    list2 = (List) this.f.fromJson(jsonReader);
                    i &= -33;
                    break;
            }
        }
        jsonReader.q();
        if (i == -61) {
            if (str == null) {
                JsonDataException jsonDataExceptionO = ds2.o("uri", "uri", jsonReader);
                v03.i(jsonDataExceptionO, "missingProperty(\"uri\", \"uri\", reader)");
                throw jsonDataExceptionO;
            }
            if (str2 != null) {
                return new WebApiSearchModel$TrackItem(str, str2, bool.booleanValue(), webApiSearchModel$Album, list, list2);
            }
            JsonDataException jsonDataExceptionO2 = ds2.o("name", "name", jsonReader);
            v03.i(jsonDataExceptionO2, "missingProperty(\"name\", \"name\", reader)");
            throw jsonDataExceptionO2;
        }
        Constructor declaredConstructor = this.g;
        if (declaredConstructor == null) {
            declaredConstructor = WebApiSearchModel$TrackItem.class.getDeclaredConstructor(String.class, String.class, Boolean.TYPE, WebApiSearchModel$Album.class, List.class, List.class, Integer.TYPE, ds2.c);
            this.g = declaredConstructor;
            v03.i(declaredConstructor, "WebApiSearchModel.TrackI…his.constructorRef = it }");
        }
        Object[] objArr = new Object[8];
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
        objArr[3] = webApiSearchModel$Album;
        objArr[4] = list;
        objArr[5] = list2;
        objArr[6] = Integer.valueOf(i);
        objArr[7] = null;
        Object objNewInstance = declaredConstructor.newInstance(objArr);
        v03.i(objNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return (WebApiSearchModel$TrackItem) objNewInstance;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, WebApiSearchModel$TrackItem webApiSearchModel$TrackItem) {
        WebApiSearchModel$TrackItem webApiSearchModel$TrackItem2 = webApiSearchModel$TrackItem;
        v03.j(mVar, "writer");
        if (webApiSearchModel$TrackItem2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("uri");
        String str = webApiSearchModel$TrackItem2.a;
        f fVar = this.b;
        fVar.toJson(mVar, str);
        mVar.J0("name");
        fVar.toJson(mVar, webApiSearchModel$TrackItem2.b);
        mVar.J0("explicit");
        this.c.toJson(mVar, Boolean.valueOf(webApiSearchModel$TrackItem2.c));
        mVar.J0(Search.Type.ALBUM);
        this.d.toJson(mVar, webApiSearchModel$TrackItem2.d);
        mVar.J0("artists");
        this.e.toJson(mVar, webApiSearchModel$TrackItem2.e);
        mVar.J0("tags");
        this.f.toJson(mVar, webApiSearchModel$TrackItem2.f);
        mVar.X();
    }

    public final String toString() {
        return u4.b(49, "GeneratedJsonAdapter(WebApiSearchModel.TrackItem)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
