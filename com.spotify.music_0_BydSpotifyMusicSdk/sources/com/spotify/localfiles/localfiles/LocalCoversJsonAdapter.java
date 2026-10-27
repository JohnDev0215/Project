package com.spotify.localfiles.localfiles;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class LocalCoversJsonAdapter extends f<LocalCovers> {
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public LocalCoversJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("default", "small", "large", "xlarge");
        v03.i(bVarA, "of(\"default\", \"small\", \"large\",\n      \"xlarge\")");
        this.options = bVarA;
        f<String> fVarF = oVar.f(String.class, EmptySet.c, "default");
        v03.i(fVarF, "moshi.adapter(String::cl…   emptySet(), \"default\")");
        this.nullableStringAdapter = fVarF;
    }

    public String toString() {
        return u4.b(33, "GeneratedJsonAdapter(LocalCovers)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public LocalCovers fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
            } else if (iM0 == 1) {
                strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
            } else if (iM0 == 2) {
                strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
            } else if (iM0 == 3) {
                strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
            }
        }
        jsonReader.q();
        return new LocalCovers(strFromJson, strFromJson2, strFromJson3, strFromJson4);
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, LocalCovers localCovers) {
        v03.j(mVar, "writer");
        if (localCovers == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("default");
        this.nullableStringAdapter.toJson(mVar, localCovers.getDefault());
        mVar.J0("small");
        this.nullableStringAdapter.toJson(mVar, localCovers.getSmall());
        mVar.J0("large");
        this.nullableStringAdapter.toJson(mVar, localCovers.getLarge());
        mVar.J0("xlarge");
        this.nullableStringAdapter.toJson(mVar, localCovers.getExtraLarge());
        mVar.X();
    }
}
