package com.spotify.webapi.service.models;

import com.spotify.webapi.service.models.views.ViewsBestMatch;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.ParameterizedType;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class SearchJsonAdapter extends f<Search> {
    private final f<Pager<Album>> nullablePagerOfAlbumAdapter;
    private final f<Pager<Artist>> nullablePagerOfArtistAdapter;
    private final f<Pager<Episode>> nullablePagerOfEpisodeAdapter;
    private final f<Pager<Playlist>> nullablePagerOfPlaylistAdapter;
    private final f<Pager<ShowSimple>> nullablePagerOfShowSimpleAdapter;
    private final f<Pager<Track>> nullablePagerOfTrackAdapter;
    private final f<Pager<ViewsBestMatch>> nullablePagerOfViewsBestMatchAdapter;
    private final JsonReader.b options;

    public SearchJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("albums", "artists", "best_match", "episodes", "playlists", "shows", "tracks");
        v03.i(bVarA, "of(\"albums\", \"artists\", …ists\", \"shows\", \"tracks\")");
        this.options = bVarA;
        ParameterizedType parameterizedTypeJ = zo2.j(Pager.class, Album.class);
        EmptySet emptySet = EmptySet.c;
        f<Pager<Album>> fVarF = oVar.f(parameterizedTypeJ, emptySet, "albums");
        v03.i(fVarF, "moshi.adapter(Types.newP…ptySet(),\n      \"albums\")");
        this.nullablePagerOfAlbumAdapter = fVarF;
        f<Pager<Artist>> fVarF2 = oVar.f(zo2.j(Pager.class, Artist.class), emptySet, "artists");
        v03.i(fVarF2, "moshi.adapter(Types.newP…tySet(),\n      \"artists\")");
        this.nullablePagerOfArtistAdapter = fVarF2;
        f<Pager<ViewsBestMatch>> fVarF3 = oVar.f(zo2.j(Pager.class, ViewsBestMatch.class), emptySet, "bestMatch");
        v03.i(fVarF3, "moshi.adapter(Types.newP… emptySet(), \"bestMatch\")");
        this.nullablePagerOfViewsBestMatchAdapter = fVarF3;
        f<Pager<Episode>> fVarF4 = oVar.f(zo2.j(Pager.class, Episode.class), emptySet, "episodes");
        v03.i(fVarF4, "moshi.adapter(Types.newP…ySet(),\n      \"episodes\")");
        this.nullablePagerOfEpisodeAdapter = fVarF4;
        f<Pager<Playlist>> fVarF5 = oVar.f(zo2.j(Pager.class, Playlist.class), emptySet, "playlists");
        v03.i(fVarF5, "moshi.adapter(Types.newP…Set(),\n      \"playlists\")");
        this.nullablePagerOfPlaylistAdapter = fVarF5;
        f<Pager<ShowSimple>> fVarF6 = oVar.f(zo2.j(Pager.class, ShowSimple.class), emptySet, "shows");
        v03.i(fVarF6, "moshi.adapter(Types.newP…     emptySet(), \"shows\")");
        this.nullablePagerOfShowSimpleAdapter = fVarF6;
        f<Pager<Track>> fVarF7 = oVar.f(zo2.j(Pager.class, Track.class), emptySet, "tracks");
        v03.i(fVarF7, "moshi.adapter(Types.newP…ptySet(),\n      \"tracks\")");
        this.nullablePagerOfTrackAdapter = fVarF7;
    }

    public String toString() {
        return u4.b(28, "GeneratedJsonAdapter(Search)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Search fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Pager<Album> pagerFromJson = null;
        Pager<ViewsBestMatch> pagerFromJson2 = null;
        Pager<Episode> pagerFromJson3 = null;
        Pager<Playlist> pagerFromJson4 = null;
        Pager<ShowSimple> pagerFromJson5 = null;
        Pager<Track> pagerFromJson6 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        Pager<Artist> pagerFromJson7 = null;
        while (jsonReader.hasNext()) {
            Pager<Track> pager = pagerFromJson6;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    pagerFromJson = this.nullablePagerOfAlbumAdapter.fromJson(jsonReader);
                    pagerFromJson6 = pager;
                    z = true;
                    continue;
                case 1:
                    pagerFromJson7 = this.nullablePagerOfArtistAdapter.fromJson(jsonReader);
                    pagerFromJson6 = pager;
                    z2 = true;
                    continue;
                case 2:
                    pagerFromJson2 = this.nullablePagerOfViewsBestMatchAdapter.fromJson(jsonReader);
                    pagerFromJson6 = pager;
                    z3 = true;
                    continue;
                case 3:
                    pagerFromJson3 = this.nullablePagerOfEpisodeAdapter.fromJson(jsonReader);
                    pagerFromJson6 = pager;
                    z4 = true;
                    continue;
                case 4:
                    pagerFromJson4 = this.nullablePagerOfPlaylistAdapter.fromJson(jsonReader);
                    pagerFromJson6 = pager;
                    z5 = true;
                    continue;
                case 5:
                    pagerFromJson5 = this.nullablePagerOfShowSimpleAdapter.fromJson(jsonReader);
                    pagerFromJson6 = pager;
                    z6 = true;
                    continue;
                case 6:
                    pagerFromJson6 = this.nullablePagerOfTrackAdapter.fromJson(jsonReader);
                    z7 = true;
                    continue;
            }
            pagerFromJson6 = pager;
        }
        Pager<Track> pager2 = pagerFromJson6;
        jsonReader.q();
        Search search = new Search();
        if (z) {
            search.albums = pagerFromJson;
        }
        if (z2) {
            search.artists = pagerFromJson7;
        }
        if (z3) {
            search.bestMatch = pagerFromJson2;
        }
        if (z4) {
            search.episodes = pagerFromJson3;
        }
        if (z5) {
            search.playlists = pagerFromJson4;
        }
        if (z6) {
            search.shows = pagerFromJson5;
        }
        if (z7) {
            search.tracks = pager2;
        }
        return search;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Search search) {
        v03.j(mVar, "writer");
        if (search == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("albums");
        this.nullablePagerOfAlbumAdapter.toJson(mVar, search.albums);
        mVar.J0("artists");
        this.nullablePagerOfArtistAdapter.toJson(mVar, search.artists);
        mVar.J0("best_match");
        this.nullablePagerOfViewsBestMatchAdapter.toJson(mVar, search.bestMatch);
        mVar.J0("episodes");
        this.nullablePagerOfEpisodeAdapter.toJson(mVar, search.episodes);
        mVar.J0("playlists");
        this.nullablePagerOfPlaylistAdapter.toJson(mVar, search.playlists);
        mVar.J0("shows");
        this.nullablePagerOfShowSimpleAdapter.toJson(mVar, search.shows);
        mVar.J0("tracks");
        this.nullablePagerOfTrackAdapter.toJson(mVar, search.tracks);
        mVar.X();
    }
}
