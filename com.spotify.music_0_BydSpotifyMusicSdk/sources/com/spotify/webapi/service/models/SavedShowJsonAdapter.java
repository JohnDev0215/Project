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
public final class SavedShowJsonAdapter extends f<SavedShow> {
    private final f<ShowSimple> nullableShowSimpleAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public SavedShowJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("added_at", Search.Type.SHOW);
        v03.i(bVarA, "of(\"added_at\", \"show\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "added_at");
        v03.i(fVarF, "moshi.adapter(String::cl…  emptySet(), \"added_at\")");
        this.nullableStringAdapter = fVarF;
        f<ShowSimple> fVarF2 = oVar.f(ShowSimple.class, emptySet, Search.Type.SHOW);
        v03.i(fVarF2, "moshi.adapter(ShowSimple…java, emptySet(), \"show\")");
        this.nullableShowSimpleAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(31, "GeneratedJsonAdapter(SavedShow)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public SavedShow fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        boolean z = false;
        boolean z2 = false;
        ShowSimple showSimpleFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                showSimpleFromJson = this.nullableShowSimpleAdapter.fromJson(jsonReader);
                z2 = true;
            }
        }
        jsonReader.q();
        SavedShow savedShow = new SavedShow();
        if (z) {
            savedShow.added_at = strFromJson;
        }
        if (z2) {
            savedShow.show = showSimpleFromJson;
        }
        return savedShow;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, SavedShow savedShow) {
        v03.j(mVar, "writer");
        if (savedShow == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("added_at");
        this.nullableStringAdapter.toJson(mVar, savedShow.added_at);
        mVar.J0(Search.Type.SHOW);
        this.nullableShowSimpleAdapter.toJson(mVar, savedShow.show);
        mVar.X();
    }
}
