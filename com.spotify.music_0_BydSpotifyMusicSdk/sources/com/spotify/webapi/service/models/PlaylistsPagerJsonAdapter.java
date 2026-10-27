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
public final class PlaylistsPagerJsonAdapter extends f<PlaylistsPager> {
    private final f<Pager<PlaylistSimple>> nullablePagerOfPlaylistSimpleAdapter;
    private final JsonReader.b options;

    public PlaylistsPagerJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("playlists");
        v03.i(bVarA, "of(\"playlists\")");
        this.options = bVarA;
        f<Pager<PlaylistSimple>> fVarF = oVar.f(zo2.j(Pager.class, PlaylistSimple.class), EmptySet.c, "playlists");
        v03.i(fVarF, "moshi.adapter(Types.newP… emptySet(), \"playlists\")");
        this.nullablePagerOfPlaylistSimpleAdapter = fVarF;
    }

    public String toString() {
        return u4.b(36, "GeneratedJsonAdapter(PlaylistsPager)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public PlaylistsPager fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Pager<PlaylistSimple> pagerFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                pagerFromJson = this.nullablePagerOfPlaylistSimpleAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        PlaylistsPager playlistsPager = new PlaylistsPager();
        if (z) {
            playlistsPager.playlists = pagerFromJson;
        }
        return playlistsPager;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, PlaylistsPager playlistsPager) {
        v03.j(mVar, "writer");
        if (playlistsPager == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("playlists");
        this.nullablePagerOfPlaylistSimpleAdapter.toJson(mVar, playlistsPager.playlists);
        mVar.X();
    }
}
