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
public final class ArtistsJsonAdapter extends f<Artists> {
    private final f<List<Artist>> nullableListOfArtistAdapter;
    private final JsonReader.b options;

    public ArtistsJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("artists");
        v03.i(bVarA, "of(\"artists\")");
        this.options = bVarA;
        f<List<Artist>> fVarF = oVar.f(zo2.j(List.class, Artist.class), EmptySet.c, "artists");
        v03.i(fVarF, "moshi.adapter(Types.newP…tySet(),\n      \"artists\")");
        this.nullableListOfArtistAdapter = fVarF;
    }

    public String toString() {
        return u4.b(29, "GeneratedJsonAdapter(Artists)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Artists fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List<Artist> listFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                listFromJson = this.nullableListOfArtistAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        Artists artists = new Artists();
        if (z) {
            artists.artists = listFromJson;
        }
        return artists;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Artists artists) {
        v03.j(mVar, "writer");
        if (artists == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("artists");
        this.nullableListOfArtistAdapter.toJson(mVar, artists.artists);
        mVar.X();
    }
}
