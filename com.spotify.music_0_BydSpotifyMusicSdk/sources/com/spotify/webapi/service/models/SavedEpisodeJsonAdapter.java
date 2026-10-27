package com.spotify.webapi.service.models;

import com.spotify.webapi.service.models.Search;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SavedEpisodeJsonAdapter extends f<SavedEpisode> {
    private final f<Episode> nullableEpisodeAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public SavedEpisodeJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("added_at", Search.Type.EPISODE);
        v03.i(bVarA, "of(\"added_at\", \"episode\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "added_at");
        v03.i(fVarF, "moshi.adapter(String::cl…  emptySet(), \"added_at\")");
        this.nullableStringAdapter = fVarF;
        f<Episode> fVarF2 = oVar.f(Episode.class, emptySet, Search.Type.EPISODE);
        v03.i(fVarF2, "moshi.adapter(Episode::c…   emptySet(), \"episode\")");
        this.nullableEpisodeAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(34, "GeneratedJsonAdapter(SavedEpisode)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public SavedEpisode fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        boolean z = false;
        boolean z2 = false;
        Episode episodeFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                episodeFromJson = this.nullableEpisodeAdapter.fromJson(jsonReader);
                z2 = true;
            }
        }
        jsonReader.q();
        SavedEpisode savedEpisode = new SavedEpisode();
        if (z) {
            savedEpisode.added_at = strFromJson;
        }
        if (z2) {
            savedEpisode.episode = episodeFromJson;
        }
        return savedEpisode;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, SavedEpisode savedEpisode) {
        v03.j(mVar, "writer");
        if (savedEpisode == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("added_at");
        this.nullableStringAdapter.toJson(mVar, savedEpisode.added_at);
        mVar.J0(Search.Type.EPISODE);
        this.nullableEpisodeAdapter.toJson(mVar, savedEpisode.episode);
        mVar.X();
    }
}
