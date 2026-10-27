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
public final class RecommendationsJsonAdapter extends f<Recommendations> {
    private final f<List<Seed>> nullableListOfSeedAdapter;
    private final f<List<Track>> nullableListOfTrackAdapter;
    private final JsonReader.b options;

    public RecommendationsJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("seeds", "tracks");
        v03.i(bVarA, "of(\"seeds\", \"tracks\")");
        this.options = bVarA;
        ParameterizedType parameterizedTypeJ = zo2.j(List.class, Seed.class);
        EmptySet emptySet = EmptySet.c;
        f<List<Seed>> fVarF = oVar.f(parameterizedTypeJ, emptySet, "seeds");
        v03.i(fVarF, "moshi.adapter(Types.newP…mptySet(),\n      \"seeds\")");
        this.nullableListOfSeedAdapter = fVarF;
        f<List<Track>> fVarF2 = oVar.f(zo2.j(List.class, Track.class), emptySet, "tracks");
        v03.i(fVarF2, "moshi.adapter(Types.newP…ptySet(),\n      \"tracks\")");
        this.nullableListOfTrackAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(37, "GeneratedJsonAdapter(Recommendations)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Recommendations fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List<Seed> listFromJson = null;
        boolean z = false;
        boolean z2 = false;
        List<Track> listFromJson2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                listFromJson = this.nullableListOfSeedAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                listFromJson2 = this.nullableListOfTrackAdapter.fromJson(jsonReader);
                z2 = true;
            }
        }
        jsonReader.q();
        Recommendations recommendations = new Recommendations();
        if (z) {
            recommendations.seeds = listFromJson;
        }
        if (z2) {
            recommendations.tracks = listFromJson2;
        }
        return recommendations;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Recommendations recommendations) {
        v03.j(mVar, "writer");
        if (recommendations == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("seeds");
        this.nullableListOfSeedAdapter.toJson(mVar, recommendations.seeds);
        mVar.J0("tracks");
        this.nullableListOfTrackAdapter.toJson(mVar, recommendations.tracks);
        mVar.X();
    }
}
