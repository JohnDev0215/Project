package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class AlbumCopyrightJsonAdapter extends f<AlbumCopyright> {
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public AlbumCopyrightJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("text", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"text\", \"type\")");
        this.options = bVarA;
        f<String> fVarF = oVar.f(String.class, EmptySet.c, "text");
        v03.i(fVarF, "moshi.adapter(String::cl…      emptySet(), \"text\")");
        this.nullableStringAdapter = fVarF;
    }

    public String toString() {
        return u4.b(36, "GeneratedJsonAdapter(AlbumCopyright)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public AlbumCopyright fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        boolean z = false;
        boolean z2 = false;
        String strFromJson2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                z2 = true;
            }
        }
        jsonReader.q();
        AlbumCopyright albumCopyright = new AlbumCopyright();
        if (z) {
            albumCopyright.text = strFromJson;
        }
        if (z2) {
            albumCopyright.type = strFromJson2;
        }
        return albumCopyright;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, AlbumCopyright albumCopyright) {
        v03.j(mVar, "writer");
        if (albumCopyright == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("text");
        this.nullableStringAdapter.toJson(mVar, albumCopyright.text);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, albumCopyright.type);
        mVar.X();
    }
}
