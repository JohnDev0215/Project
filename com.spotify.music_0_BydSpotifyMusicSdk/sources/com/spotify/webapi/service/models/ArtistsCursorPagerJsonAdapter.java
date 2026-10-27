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
public final class ArtistsCursorPagerJsonAdapter extends f<ArtistsCursorPager> {
    private final f<CursorPager<Artist>> nullableCursorPagerOfArtistAdapter;
    private final JsonReader.b options;

    public ArtistsCursorPagerJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("artists");
        v03.i(bVarA, "of(\"artists\")");
        this.options = bVarA;
        f<CursorPager<Artist>> fVarF = oVar.f(zo2.j(CursorPager.class, Artist.class), EmptySet.c, "artists");
        v03.i(fVarF, "moshi.adapter(Types.newP…   emptySet(), \"artists\")");
        this.nullableCursorPagerOfArtistAdapter = fVarF;
    }

    public String toString() {
        return u4.b(40, "GeneratedJsonAdapter(ArtistsCursorPager)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public ArtistsCursorPager fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        CursorPager<Artist> cursorPagerFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                cursorPagerFromJson = this.nullableCursorPagerOfArtistAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        ArtistsCursorPager artistsCursorPager = new ArtistsCursorPager();
        if (z) {
            artistsCursorPager.artists = cursorPagerFromJson;
        }
        return artistsCursorPager;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, ArtistsCursorPager artistsCursorPager) {
        v03.j(mVar, "writer");
        if (artistsCursorPager == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("artists");
        this.nullableCursorPagerOfArtistAdapter.toJson(mVar, artistsCursorPager.artists);
        mVar.X();
    }
}
