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
public final class AlbumsPagerJsonAdapter extends f<AlbumsPager> {
    private final f<Pager<AlbumSimple>> nullablePagerOfAlbumSimpleAdapter;
    private final JsonReader.b options;

    public AlbumsPagerJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("albums");
        v03.i(bVarA, "of(\"albums\")");
        this.options = bVarA;
        f<Pager<AlbumSimple>> fVarF = oVar.f(zo2.j(Pager.class, AlbumSimple.class), EmptySet.c, "albums");
        v03.i(fVarF, "moshi.adapter(Types.newP…    emptySet(), \"albums\")");
        this.nullablePagerOfAlbumSimpleAdapter = fVarF;
    }

    public String toString() {
        return u4.b(33, "GeneratedJsonAdapter(AlbumsPager)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public AlbumsPager fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Pager<AlbumSimple> pagerFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                pagerFromJson = this.nullablePagerOfAlbumSimpleAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        AlbumsPager albumsPager = new AlbumsPager();
        if (z) {
            albumsPager.albums = pagerFromJson;
        }
        return albumsPager;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, AlbumsPager albumsPager) {
        v03.j(mVar, "writer");
        if (albumsPager == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("albums");
        this.nullablePagerOfAlbumSimpleAdapter.toJson(mVar, albumsPager.albums);
        mVar.X();
    }
}
