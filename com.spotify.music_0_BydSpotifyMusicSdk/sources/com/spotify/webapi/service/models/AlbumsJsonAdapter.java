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
public final class AlbumsJsonAdapter extends f<Albums> {
    private final f<List<Album>> nullableListOfAlbumAdapter;
    private final JsonReader.b options;

    public AlbumsJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("albums");
        v03.i(bVarA, "of(\"albums\")");
        this.options = bVarA;
        f<List<Album>> fVarF = oVar.f(zo2.j(List.class, Album.class), EmptySet.c, "albums");
        v03.i(fVarF, "moshi.adapter(Types.newP…ptySet(),\n      \"albums\")");
        this.nullableListOfAlbumAdapter = fVarF;
    }

    public String toString() {
        return u4.b(28, "GeneratedJsonAdapter(Albums)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Albums fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List<Album> listFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                listFromJson = this.nullableListOfAlbumAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        Albums albums = new Albums();
        if (z) {
            albums.albums = listFromJson;
        }
        return albums;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Albums albums) {
        v03.j(mVar, "writer");
        if (albums == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("albums");
        this.nullableListOfAlbumAdapter.toJson(mVar, albums.albums);
        mVar.X();
    }
}
