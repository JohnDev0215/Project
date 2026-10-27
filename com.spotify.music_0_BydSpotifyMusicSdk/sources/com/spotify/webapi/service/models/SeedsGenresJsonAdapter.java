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
public final class SeedsGenresJsonAdapter extends f<SeedsGenres> {
    private final f<List<String>> nullableListOfStringAdapter;
    private final JsonReader.b options;

    public SeedsGenresJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("genres");
        v03.i(bVarA, "of(\"genres\")");
        this.options = bVarA;
        f<List<String>> fVarF = oVar.f(zo2.j(List.class, String.class), EmptySet.c, "genres");
        v03.i(fVarF, "moshi.adapter(Types.newP…ptySet(),\n      \"genres\")");
        this.nullableListOfStringAdapter = fVarF;
    }

    public String toString() {
        return u4.b(33, "GeneratedJsonAdapter(SeedsGenres)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public SeedsGenres fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List<String> listFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                listFromJson = this.nullableListOfStringAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        SeedsGenres seedsGenres = new SeedsGenres();
        if (z) {
            seedsGenres.genres = listFromJson;
        }
        return seedsGenres;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, SeedsGenres seedsGenres) {
        v03.j(mVar, "writer");
        if (seedsGenres == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("genres");
        this.nullableListOfStringAdapter.toJson(mVar, seedsGenres.genres);
        mVar.X();
    }
}
