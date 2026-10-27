package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SeedJsonAdapter extends f<Seed> {
    private final f<Integer> intAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public SeedJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("afterFilteringSize", "afterRelinkingSize", "href", "id", "initialPoolSize", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"afterFilteringSize\",…initialPoolSize\", \"type\")");
        this.options = bVarA;
        Class cls = Integer.TYPE;
        EmptySet emptySet = EmptySet.c;
        f<Integer> fVarF = oVar.f(cls, emptySet, "afterFilteringSize");
        v03.i(fVarF, "moshi.adapter(Int::class…    \"afterFilteringSize\")");
        this.intAdapter = fVarF;
        f<String> fVarF2 = oVar.f(String.class, emptySet, "href");
        v03.i(fVarF2, "moshi.adapter(String::cl…      emptySet(), \"href\")");
        this.nullableStringAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(26, "GeneratedJsonAdapter(Seed)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Seed fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Integer numFromJson = null;
        String strFromJson = null;
        String strFromJson2 = null;
        Integer numFromJson2 = null;
        String strFromJson3 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        Integer numFromJson3 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    numFromJson = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("afterFilteringSize", "afterFilteringSize", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"afterFil…erFilteringSize\", reader)");
                        throw jsonDataExceptionW;
                    }
                    break;
                    break;
                case 1:
                    numFromJson3 = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson3 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("afterRelinkingSize", "afterRelinkingSize", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"afterRel…erRelinkingSize\", reader)");
                        throw jsonDataExceptionW2;
                    }
                    break;
                    break;
                case 2:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    z = true;
                    break;
                case 3:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    z2 = true;
                    break;
                case 4:
                    numFromJson2 = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson2 == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("initialPoolSize", "initialPoolSize", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"initialP…initialPoolSize\", reader)");
                        throw jsonDataExceptionW3;
                    }
                    break;
                    break;
                case 5:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    z3 = true;
                    break;
            }
        }
        jsonReader.q();
        Seed seed = new Seed();
        seed.afterFilteringSize = numFromJson != null ? numFromJson.intValue() : seed.afterFilteringSize;
        seed.afterRelinkingSize = numFromJson3 != null ? numFromJson3.intValue() : seed.afterRelinkingSize;
        if (z) {
            seed.href = strFromJson;
        }
        if (z2) {
            seed.id = strFromJson2;
        }
        seed.initialPoolSize = numFromJson2 != null ? numFromJson2.intValue() : seed.initialPoolSize;
        if (z3) {
            seed.type = strFromJson3;
        }
        return seed;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Seed seed) {
        v03.j(mVar, "writer");
        if (seed == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("afterFilteringSize");
        u4.s(seed.afterFilteringSize, this.intAdapter, mVar, "afterRelinkingSize");
        u4.s(seed.afterRelinkingSize, this.intAdapter, mVar, "href");
        this.nullableStringAdapter.toJson(mVar, seed.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, seed.id);
        mVar.J0("initialPoolSize");
        u4.s(seed.initialPoolSize, this.intAdapter, mVar, RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, seed.type);
        mVar.X();
    }
}
