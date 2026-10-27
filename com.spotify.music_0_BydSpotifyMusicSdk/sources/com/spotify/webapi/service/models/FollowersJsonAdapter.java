package com.spotify.webapi.service.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class FollowersJsonAdapter extends f<Followers> {
    private final f<Integer> nullableIntAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public FollowersJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("href", "total");
        v03.i(bVarA, "of(\"href\", \"total\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "href");
        v03.i(fVarF, "moshi.adapter(String::cl…      emptySet(), \"href\")");
        this.nullableStringAdapter = fVarF;
        f<Integer> fVarF2 = oVar.f(Integer.class, emptySet, "total");
        v03.i(fVarF2, "moshi.adapter(Int::class…     emptySet(), \"total\")");
        this.nullableIntAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(31, "GeneratedJsonAdapter(Followers)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Followers fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        boolean z = false;
        boolean z2 = false;
        Integer numFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                numFromJson = this.nullableIntAdapter.fromJson(jsonReader);
                z2 = true;
            }
        }
        jsonReader.q();
        Followers followers = new Followers();
        if (z) {
            followers.href = strFromJson;
        }
        if (z2) {
            followers.total = numFromJson;
        }
        return followers;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Followers followers) {
        v03.j(mVar, "writer");
        if (followers == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, followers.href);
        mVar.J0("total");
        this.nullableIntAdapter.toJson(mVar, followers.total);
        mVar.X();
    }
}
