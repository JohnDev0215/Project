package com.spotify.webapi.service.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class FeaturedPlaylistsJsonAdapter extends f<FeaturedPlaylists> {
    private final f<Pager<PlaylistSimple>> nullablePagerOfPlaylistSimpleAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public FeaturedPlaylistsJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("message", "playlists");
        v03.i(bVarA, "of(\"message\", \"playlists\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "message");
        v03.i(fVarF, "moshi.adapter(String::cl…   emptySet(), \"message\")");
        this.nullableStringAdapter = fVarF;
        f<Pager<PlaylistSimple>> fVarF2 = oVar.f(zo2.j(Pager.class, PlaylistSimple.class), emptySet, "playlists");
        v03.i(fVarF2, "moshi.adapter(Types.newP… emptySet(), \"playlists\")");
        this.nullablePagerOfPlaylistSimpleAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(39, "GeneratedJsonAdapter(FeaturedPlaylists)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public FeaturedPlaylists fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        boolean z = false;
        boolean z2 = false;
        Pager<PlaylistSimple> pagerFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                pagerFromJson = this.nullablePagerOfPlaylistSimpleAdapter.fromJson(jsonReader);
                z2 = true;
            }
        }
        jsonReader.q();
        FeaturedPlaylists featuredPlaylists = new FeaturedPlaylists();
        if (z) {
            featuredPlaylists.message = strFromJson;
        }
        if (z2) {
            featuredPlaylists.playlists = pagerFromJson;
        }
        return featuredPlaylists;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, FeaturedPlaylists featuredPlaylists) {
        v03.j(mVar, "writer");
        if (featuredPlaylists == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("message");
        this.nullableStringAdapter.toJson(mVar, featuredPlaylists.message);
        mVar.J0("playlists");
        this.nullablePagerOfPlaylistSimpleAdapter.toJson(mVar, featuredPlaylists.playlists);
        mVar.X();
    }
}
