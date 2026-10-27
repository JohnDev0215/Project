package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class ArtistSimpleJsonAdapter extends f<ArtistSimple> {
    private final f<Map<String, String>> nullableMapOfStringStringAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public ArtistSimpleJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("external_urls", "href", "id", "name", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"external_urls\", \"hre…   \"name\", \"uri\", \"type\")");
        this.options = bVarA;
        ParameterizedType parameterizedTypeJ = zo2.j(Map.class, String.class, String.class);
        EmptySet emptySet = EmptySet.c;
        f<Map<String, String>> fVarF = oVar.f(parameterizedTypeJ, emptySet, "external_urls");
        v03.i(fVarF, "moshi.adapter(Types.newP…tySet(), \"external_urls\")");
        this.nullableMapOfStringStringAdapter = fVarF;
        f<String> fVarF2 = oVar.f(String.class, emptySet, "href");
        v03.i(fVarF2, "moshi.adapter(String::cl…      emptySet(), \"href\")");
        this.nullableStringAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(34, "GeneratedJsonAdapter(ArtistSimple)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public ArtistSimple fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Map<String, String> mapFromJson = null;
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        String strFromJson5 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    mapFromJson = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    z = true;
                    break;
                case 1:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    z2 = true;
                    break;
                case 2:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    z3 = true;
                    break;
                case 3:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    z4 = true;
                    break;
                case 4:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    z5 = true;
                    break;
                case 5:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    z6 = true;
                    break;
            }
        }
        jsonReader.q();
        ArtistSimple artistSimple = new ArtistSimple();
        if (z) {
            artistSimple.external_urls = mapFromJson;
        }
        if (z2) {
            artistSimple.href = strFromJson5;
        }
        if (z3) {
            artistSimple.id = strFromJson;
        }
        if (z4) {
            artistSimple.name = strFromJson2;
        }
        if (z5) {
            artistSimple.uri = strFromJson3;
        }
        if (z6) {
            artistSimple.type = strFromJson4;
        }
        return artistSimple;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, ArtistSimple artistSimple) {
        v03.j(mVar, "writer");
        if (artistSimple == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("external_urls");
        this.nullableMapOfStringStringAdapter.toJson(mVar, artistSimple.external_urls);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, artistSimple.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, artistSimple.id);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, artistSimple.name);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, artistSimple.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, artistSimple.type);
        mVar.X();
    }
}
