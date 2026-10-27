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
public final class ImageJsonAdapter extends f<Image> {
    private volatile Constructor<Image> constructorRef;
    private final f<Integer> intAdapter;
    private final JsonReader.b options;
    private final f<String> stringAdapter;

    public ImageJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("url", "width", "height");
        v03.i(bVarA, "of(\"url\", \"width\", \"height\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "url");
        v03.i(fVarF, "moshi.adapter(String::cl… emptySet(),\n      \"url\")");
        this.stringAdapter = fVarF;
        f<Integer> fVarF2 = oVar.f(Integer.TYPE, emptySet, "width");
        v03.i(fVarF2, "moshi.adapter(Int::class…ava, emptySet(), \"width\")");
        this.intAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(27, "GeneratedJsonAdapter(Image)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Image fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        Integer numFromJson = 0;
        jsonReader.c();
        Integer numFromJson2 = numFromJson;
        int i = -1;
        String strFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("url", "url", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"url\", \"url\", reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1) {
                numFromJson = this.intAdapter.fromJson(jsonReader);
                if (numFromJson == null) {
                    JsonDataException jsonDataExceptionW2 = ds2.w("width", "width", jsonReader);
                    v03.i(jsonDataExceptionW2, "unexpectedNull(\"width\", \"width\", reader)");
                    throw jsonDataExceptionW2;
                }
                i &= -3;
            } else if (iM0 == 2) {
                numFromJson2 = this.intAdapter.fromJson(jsonReader);
                if (numFromJson2 == null) {
                    JsonDataException jsonDataExceptionW3 = ds2.w("height", "height", jsonReader);
                    v03.i(jsonDataExceptionW3, "unexpectedNull(\"height\",…t\",\n              reader)");
                    throw jsonDataExceptionW3;
                }
                i &= -5;
            } else {
                continue;
            }
        }
        jsonReader.q();
        if (i == -7) {
            if (strFromJson != null) {
                return new Image(strFromJson, numFromJson.intValue(), numFromJson2.intValue());
            }
            JsonDataException jsonDataExceptionO = ds2.o("url", "url", jsonReader);
            v03.i(jsonDataExceptionO, "missingProperty(\"url\", \"url\", reader)");
            throw jsonDataExceptionO;
        }
        Constructor<Image> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            Class cls = Integer.TYPE;
            declaredConstructor = Image.class.getDeclaredConstructor(String.class, cls, cls, cls, ds2.c);
            this.constructorRef = declaredConstructor;
            v03.i(declaredConstructor, "Image::class.java.getDec…his.constructorRef = it }");
        }
        Object[] objArr = new Object[5];
        if (strFromJson == null) {
            JsonDataException jsonDataExceptionO2 = ds2.o("url", "url", jsonReader);
            v03.i(jsonDataExceptionO2, "missingProperty(\"url\", \"url\", reader)");
            throw jsonDataExceptionO2;
        }
        objArr[0] = strFromJson;
        objArr[1] = numFromJson;
        objArr[2] = numFromJson2;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = null;
        Image imageNewInstance = declaredConstructor.newInstance(objArr);
        v03.i(imageNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return imageNewInstance;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Image image) {
        v03.j(mVar, "writer");
        if (image == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("url");
        this.stringAdapter.toJson(mVar, image.getUrl());
        mVar.J0("width");
        this.intAdapter.toJson(mVar, Integer.valueOf(image.getWidth()));
        mVar.J0("height");
        this.intAdapter.toJson(mVar, Integer.valueOf(image.getHeight()));
        mVar.X();
    }
}
