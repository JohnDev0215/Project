package com.spotify.webapi.service.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ImageJsonAdapter extends f<Image> {
    private final f<Integer> nullableIntAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public ImageJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("height", "name", "url", "width");
        v03.i(bVarA, "of(\"height\", \"name\", \"url\", \"width\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<Integer> fVarF = oVar.f(Integer.class, emptySet, "height");
        v03.i(fVarF, "moshi.adapter(Int::class…    emptySet(), \"height\")");
        this.nullableIntAdapter = fVarF;
        f<String> fVarF2 = oVar.f(String.class, emptySet, "name");
        v03.i(fVarF2, "moshi.adapter(String::cl…      emptySet(), \"name\")");
        this.nullableStringAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(27, "GeneratedJsonAdapter(Image)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Image fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Integer numFromJson = null;
        String strFromJson = null;
        Integer numFromJson2 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        String strFromJson2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                numFromJson = this.nullableIntAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                z2 = true;
            } else if (iM0 == 2) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z3 = true;
            } else if (iM0 == 3) {
                numFromJson2 = this.nullableIntAdapter.fromJson(jsonReader);
                z4 = true;
            }
        }
        jsonReader.q();
        Image image = new Image();
        if (z) {
            image.height = numFromJson;
        }
        if (z2) {
            image.name = strFromJson2;
        }
        if (z3) {
            image.url = strFromJson;
        }
        if (z4) {
            image.width = numFromJson2;
        }
        return image;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Image image) {
        v03.j(mVar, "writer");
        if (image == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("height");
        this.nullableIntAdapter.toJson(mVar, image.height);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, image.name);
        mVar.J0("url");
        this.nullableStringAdapter.toJson(mVar, image.url);
        mVar.J0("width");
        this.nullableIntAdapter.toJson(mVar, image.width);
        mVar.X();
    }
}
