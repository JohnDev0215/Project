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
public final class WebApiSearchModel_AlbumItemJsonAdapter extends f<WebApiSearchModel$AlbumItem> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;
    public volatile Constructor e;

    public WebApiSearchModel_AlbumItemJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("uri", "name", "artists", "images");
        v03.i(bVarA, "of(\"uri\", \"name\", \"artists\",\n      \"images\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(String.class, emptySet, "uri");
        v03.i(fVarF, "moshi.adapter(String::cl… emptySet(),\n      \"uri\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(zo2.j(List.class, WebApiSearchModel$Artist.class), emptySet, "artists");
        v03.i(fVarF2, "moshi.adapter(Types.newP…), emptySet(), \"artists\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(zo2.j(List.class, WebApiSearchModel$Image.class), emptySet, "images");
        v03.i(fVarF3, "moshi.adapter(Types.newP…a), emptySet(), \"images\")");
        this.d = fVarF3;
    }

    @Override // com.squareup.moshi.f
    public final WebApiSearchModel$AlbumItem fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        int i = -1;
        String str = null;
        String str2 = null;
        List list = null;
        List list2 = null;
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
                list = (List) this.c.fromJson(jsonReader);
                i &= -5;
            } else if (iM0 == 3) {
                list2 = (List) this.d.fromJson(jsonReader);
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
                return new WebApiSearchModel$AlbumItem(str, str2, list, list2);
            }
            JsonDataException jsonDataExceptionO2 = ds2.o("name", "name", jsonReader);
            v03.i(jsonDataExceptionO2, "missingProperty(\"name\", \"name\", reader)");
            throw jsonDataExceptionO2;
        }
        Constructor declaredConstructor = this.e;
        if (declaredConstructor == null) {
            declaredConstructor = WebApiSearchModel$AlbumItem.class.getDeclaredConstructor(String.class, String.class, List.class, List.class, Integer.TYPE, ds2.c);
            this.e = declaredConstructor;
            v03.i(declaredConstructor, "WebApiSearchModel.AlbumI…his.constructorRef = it }");
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
        objArr[2] = list;
        objArr[3] = list2;
        objArr[4] = Integer.valueOf(i);
        objArr[5] = null;
        Object objNewInstance = declaredConstructor.newInstance(objArr);
        v03.i(objNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return (WebApiSearchModel$AlbumItem) objNewInstance;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, WebApiSearchModel$AlbumItem webApiSearchModel$AlbumItem) {
        WebApiSearchModel$AlbumItem webApiSearchModel$AlbumItem2 = webApiSearchModel$AlbumItem;
        v03.j(mVar, "writer");
        if (webApiSearchModel$AlbumItem2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("uri");
        String str = webApiSearchModel$AlbumItem2.d;
        f fVar = this.b;
        fVar.toJson(mVar, str);
        mVar.J0("name");
        fVar.toJson(mVar, webApiSearchModel$AlbumItem2.e);
        mVar.J0("artists");
        this.c.toJson(mVar, webApiSearchModel$AlbumItem2.f);
        mVar.J0("images");
        this.d.toJson(mVar, webApiSearchModel$AlbumItem2.g);
        mVar.X();
    }

    public final String toString() {
        return u4.b(49, "GeneratedJsonAdapter(WebApiSearchModel.AlbumItem)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
