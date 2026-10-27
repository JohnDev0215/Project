package com.spotify.webapi.service.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.List;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class EpisodesJsonAdapter extends f<Episodes> {
    private final f<List<Episode>> nullableListOfEpisodeAdapter;
    private final JsonReader.b options;

    public EpisodesJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("episodes");
        v03.i(bVarA, "of(\"episodes\")");
        this.options = bVarA;
        f<List<Episode>> fVarF = oVar.f(zo2.j(List.class, Episode.class), EmptySet.c, "episodes");
        v03.i(fVarF, "moshi.adapter(Types.newP…ySet(),\n      \"episodes\")");
        this.nullableListOfEpisodeAdapter = fVarF;
    }

    public String toString() {
        return u4.b(30, "GeneratedJsonAdapter(Episodes)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Episodes fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List<Episode> listFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                listFromJson = this.nullableListOfEpisodeAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        Episodes episodes = new Episodes();
        if (z) {
            episodes.episodes = listFromJson;
        }
        return episodes;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Episodes episodes) {
        v03.j(mVar, "writer");
        if (episodes == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("episodes");
        this.nullableListOfEpisodeAdapter.toJson(mVar, episodes.episodes);
        mVar.X();
    }
}
