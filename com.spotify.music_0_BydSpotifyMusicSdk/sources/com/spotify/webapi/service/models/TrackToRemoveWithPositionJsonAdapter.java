package com.spotify.webapi.service.models;

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
public final class TrackToRemoveWithPositionJsonAdapter extends f<TrackToRemoveWithPosition> {
    private final f<List<Integer>> nullableListOfIntAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public TrackToRemoveWithPositionJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("positions", "uri");
        v03.i(bVarA, "of(\"positions\", \"uri\")");
        this.options = bVarA;
        ParameterizedType parameterizedTypeJ = zo2.j(List.class, Integer.class);
        EmptySet emptySet = EmptySet.c;
        f<List<Integer>> fVarF = oVar.f(parameterizedTypeJ, emptySet, "positions");
        v03.i(fVarF, "moshi.adapter(Types.newP… emptySet(), \"positions\")");
        this.nullableListOfIntAdapter = fVarF;
        f<String> fVarF2 = oVar.f(String.class, emptySet, "uri");
        v03.i(fVarF2, "moshi.adapter(String::cl…\n      emptySet(), \"uri\")");
        this.nullableStringAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(47, "GeneratedJsonAdapter(TrackToRemoveWithPosition)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public TrackToRemoveWithPosition fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List<Integer> listFromJson = null;
        boolean z = false;
        boolean z2 = false;
        String strFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                listFromJson = this.nullableListOfIntAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z2 = true;
            }
        }
        jsonReader.q();
        TrackToRemoveWithPosition trackToRemoveWithPosition = new TrackToRemoveWithPosition();
        if (z) {
            trackToRemoveWithPosition.positions = listFromJson;
        }
        if (z2) {
            trackToRemoveWithPosition.uri = strFromJson;
        }
        return trackToRemoveWithPosition;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, TrackToRemoveWithPosition trackToRemoveWithPosition) {
        v03.j(mVar, "writer");
        if (trackToRemoveWithPosition == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("positions");
        this.nullableListOfIntAdapter.toJson(mVar, trackToRemoveWithPosition.positions);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, trackToRemoveWithPosition.uri);
        mVar.X();
    }
}
