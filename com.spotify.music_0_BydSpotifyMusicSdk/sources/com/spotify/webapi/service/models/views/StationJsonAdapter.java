package com.spotify.webapi.service.models.views;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.webapi.service.models.Image;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class StationJsonAdapter extends f<Station> {
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public StationJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("images", "name", "subtitle", "title", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"images\", \"name\", \"su…  \"title\", \"uri\", \"type\")");
        this.options = bVarA;
        ParameterizedType parameterizedTypeJ = zo2.j(List.class, Image.class);
        EmptySet emptySet = EmptySet.c;
        f<List<Image>> fVarF = oVar.f(parameterizedTypeJ, emptySet, "images");
        v03.i(fVarF, "moshi.adapter(Types.newP…ptySet(),\n      \"images\")");
        this.nullableListOfImageAdapter = fVarF;
        f<String> fVarF2 = oVar.f(String.class, emptySet, "name");
        v03.i(fVarF2, "moshi.adapter(String::cl…      emptySet(), \"name\")");
        this.nullableStringAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(29, "GeneratedJsonAdapter(Station)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Station fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List<Image> listFromJson = null;
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
                    listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
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
        Station station = new Station();
        if (z) {
            station.images = listFromJson;
        }
        if (z2) {
            station.name = strFromJson5;
        }
        if (z3) {
            station.subtitle = strFromJson;
        }
        if (z4) {
            station.title = strFromJson2;
        }
        if (z5) {
            station.uri = strFromJson3;
        }
        if (z6) {
            station.type = strFromJson4;
        }
        return station;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Station station) {
        v03.j(mVar, "writer");
        if (station == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, station.images);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, station.name);
        mVar.J0("subtitle");
        this.nullableStringAdapter.toJson(mVar, station.subtitle);
        mVar.J0("title");
        this.nullableStringAdapter.toJson(mVar, station.title);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, station.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, station.type);
        mVar.X();
    }
}
