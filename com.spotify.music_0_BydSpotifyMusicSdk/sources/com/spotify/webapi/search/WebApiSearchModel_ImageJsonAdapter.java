package com.spotify.webapi.search;

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
public final class WebApiSearchModel_ImageJsonAdapter extends f<WebApiSearchModel$Image> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public volatile Constructor d;

    public WebApiSearchModel_ImageJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("url", "height", "width");
        v03.i(bVarA, "of(\"url\", \"height\", \"width\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(String.class, emptySet, "url");
        v03.i(fVarF, "moshi.adapter(String::cl… emptySet(),\n      \"url\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(Integer.class, emptySet, "height");
        v03.i(fVarF2, "moshi.adapter(Int::class…    emptySet(), \"height\")");
        this.c = fVarF2;
    }

    @Override // com.squareup.moshi.f
    public final WebApiSearchModel$Image fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        int i = -1;
        String str = null;
        Integer num = null;
        Integer num2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                str = (String) this.b.fromJson(jsonReader);
                if (str == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("url", "url", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"url\", \"url\", reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1) {
                num = (Integer) this.c.fromJson(jsonReader);
                i &= -3;
            } else if (iM0 == 2) {
                num2 = (Integer) this.c.fromJson(jsonReader);
                i &= -5;
            }
        }
        jsonReader.q();
        if (i == -7) {
            if (str != null) {
                return new WebApiSearchModel$Image(str, num, num2);
            }
            JsonDataException jsonDataExceptionO = ds2.o("url", "url", jsonReader);
            v03.i(jsonDataExceptionO, "missingProperty(\"url\", \"url\", reader)");
            throw jsonDataExceptionO;
        }
        Constructor declaredConstructor = this.d;
        if (declaredConstructor == null) {
            declaredConstructor = WebApiSearchModel$Image.class.getDeclaredConstructor(String.class, Integer.class, Integer.class, Integer.TYPE, ds2.c);
            this.d = declaredConstructor;
            v03.i(declaredConstructor, "WebApiSearchModel.Image:…his.constructorRef = it }");
        }
        Object[] objArr = new Object[5];
        if (str == null) {
            JsonDataException jsonDataExceptionO2 = ds2.o("url", "url", jsonReader);
            v03.i(jsonDataExceptionO2, "missingProperty(\"url\", \"url\", reader)");
            throw jsonDataExceptionO2;
        }
        objArr[0] = str;
        objArr[1] = num;
        objArr[2] = num2;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = null;
        Object objNewInstance = declaredConstructor.newInstance(objArr);
        v03.i(objNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return (WebApiSearchModel$Image) objNewInstance;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, WebApiSearchModel$Image webApiSearchModel$Image) {
        WebApiSearchModel$Image webApiSearchModel$Image2 = webApiSearchModel$Image;
        v03.j(mVar, "writer");
        if (webApiSearchModel$Image2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("url");
        this.b.toJson(mVar, webApiSearchModel$Image2.a);
        mVar.J0("height");
        Integer num = webApiSearchModel$Image2.b;
        f fVar = this.c;
        fVar.toJson(mVar, num);
        mVar.J0("width");
        fVar.toJson(mVar, webApiSearchModel$Image2.c);
        mVar.X();
    }

    public final String toString() {
        return u4.b(45, "GeneratedJsonAdapter(WebApiSearchModel.Image)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
