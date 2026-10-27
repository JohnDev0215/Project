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
public final class ArtistsPagerJsonAdapter extends f<ArtistsPager> {
    private final f<Pager<Artist>> nullablePagerOfArtistAdapter;
    private final JsonReader.b options;

    public ArtistsPagerJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("artists");
        v03.i(bVarA, "of(\"artists\")");
        this.options = bVarA;
        f<Pager<Artist>> fVarF = oVar.f(zo2.j(Pager.class, Artist.class), EmptySet.c, "artists");
        v03.i(fVarF, "moshi.adapter(Types.newP…tySet(),\n      \"artists\")");
        this.nullablePagerOfArtistAdapter = fVarF;
    }

    public String toString() {
        return u4.b(34, "GeneratedJsonAdapter(ArtistsPager)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public ArtistsPager fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Pager<Artist> pagerFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                pagerFromJson = this.nullablePagerOfArtistAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        ArtistsPager artistsPager = new ArtistsPager();
        if (z) {
            artistsPager.artists = pagerFromJson;
        }
        return artistsPager;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, ArtistsPager artistsPager) {
        v03.j(mVar, "writer");
        if (artistsPager == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("artists");
        this.nullablePagerOfArtistAdapter.toJson(mVar, artistsPager.artists);
        mVar.X();
    }
}
