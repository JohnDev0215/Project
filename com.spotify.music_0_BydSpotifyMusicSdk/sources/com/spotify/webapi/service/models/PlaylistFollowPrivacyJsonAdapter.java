package com.spotify.webapi.service.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class PlaylistFollowPrivacyJsonAdapter extends f<PlaylistFollowPrivacy> {
    private final f<Boolean> nullableBooleanAdapter;
    private final JsonReader.b options;

    public PlaylistFollowPrivacyJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("public");
        v03.i(bVarA, "of(\"public\")");
        this.options = bVarA;
        f<Boolean> fVarF = oVar.f(Boolean.class, EmptySet.c, "is_public");
        v03.i(fVarF, "moshi.adapter(Boolean::c… emptySet(), \"is_public\")");
        this.nullableBooleanAdapter = fVarF;
    }

    public String toString() {
        return u4.b(43, "GeneratedJsonAdapter(PlaylistFollowPrivacy)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public PlaylistFollowPrivacy fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Boolean boolFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                boolFromJson = this.nullableBooleanAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        PlaylistFollowPrivacy playlistFollowPrivacy = new PlaylistFollowPrivacy();
        if (z) {
            playlistFollowPrivacy.is_public = boolFromJson;
        }
        return playlistFollowPrivacy;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, PlaylistFollowPrivacy playlistFollowPrivacy) {
        v03.j(mVar, "writer");
        if (playlistFollowPrivacy == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("public");
        this.nullableBooleanAdapter.toJson(mVar, playlistFollowPrivacy.is_public);
        mVar.X();
    }
}
