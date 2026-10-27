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
public final class SavedAlbumJsonAdapter extends f<SavedAlbum> {
    private final f<Album> nullableAlbumAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public SavedAlbumJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("added_at", Search.Type.ALBUM);
        v03.i(bVarA, "of(\"added_at\", \"album\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "added_at");
        v03.i(fVarF, "moshi.adapter(String::cl…  emptySet(), \"added_at\")");
        this.nullableStringAdapter = fVarF;
        f<Album> fVarF2 = oVar.f(Album.class, emptySet, Search.Type.ALBUM);
        v03.i(fVarF2, "moshi.adapter(Album::cla…     emptySet(), \"album\")");
        this.nullableAlbumAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(32, "GeneratedJsonAdapter(SavedAlbum)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public SavedAlbum fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        boolean z = false;
        boolean z2 = false;
        Album albumFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                albumFromJson = this.nullableAlbumAdapter.fromJson(jsonReader);
                z2 = true;
            }
        }
        jsonReader.q();
        SavedAlbum savedAlbum = new SavedAlbum();
        if (z) {
            savedAlbum.added_at = strFromJson;
        }
        if (z2) {
            savedAlbum.album = albumFromJson;
        }
        return savedAlbum;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, SavedAlbum savedAlbum) {
        v03.j(mVar, "writer");
        if (savedAlbum == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("added_at");
        this.nullableStringAdapter.toJson(mVar, savedAlbum.added_at);
        mVar.J0(Search.Type.ALBUM);
        this.nullableAlbumAdapter.toJson(mVar, savedAlbum.album);
        mVar.X();
    }
}
