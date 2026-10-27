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
public final class LinkedTrackJsonAdapter extends f<LinkedTrack> {
    private final f<Map<String, String>> nullableMapOfStringStringAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public LinkedTrackJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("external_urls", "href", "id", RxProductState.Keys.KEY_TYPE, "uri");
        v03.i(bVarA, "of(\"external_urls\", \"hre…id\",\n      \"type\", \"uri\")");
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
        return u4.b(33, "GeneratedJsonAdapter(LinkedTrack)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public LinkedTrack fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Map<String, String> mapFromJson = null;
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        String strFromJson4 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                mapFromJson = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                z2 = true;
            } else if (iM0 == 2) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z3 = true;
            } else if (iM0 == 3) {
                strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                z4 = true;
            } else if (iM0 == 4) {
                strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                z5 = true;
            }
        }
        jsonReader.q();
        LinkedTrack linkedTrack = new LinkedTrack();
        if (z) {
            linkedTrack.external_urls = mapFromJson;
        }
        if (z2) {
            linkedTrack.href = strFromJson4;
        }
        if (z3) {
            linkedTrack.id = strFromJson;
        }
        if (z4) {
            linkedTrack.type = strFromJson2;
        }
        if (z5) {
            linkedTrack.uri = strFromJson3;
        }
        return linkedTrack;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, LinkedTrack linkedTrack) {
        v03.j(mVar, "writer");
        if (linkedTrack == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("external_urls");
        this.nullableMapOfStringStringAdapter.toJson(mVar, linkedTrack.external_urls);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, linkedTrack.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, linkedTrack.id);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, linkedTrack.type);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, linkedTrack.uri);
        mVar.X();
    }
}
