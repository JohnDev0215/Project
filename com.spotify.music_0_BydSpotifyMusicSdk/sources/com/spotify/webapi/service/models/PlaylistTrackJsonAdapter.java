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
public final class PlaylistTrackJsonAdapter extends f<PlaylistTrack> {
    private final f<Boolean> nullableBooleanAdapter;
    private final f<Entity> nullableEntityAdapter;
    private final f<String> nullableStringAdapter;
    private final f<UserPublic> nullableUserPublicAdapter;
    private final JsonReader.b options;

    public PlaylistTrackJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("added_at", "added_by", Search.Type.TRACK, "is_local");
        v03.i(bVarA, "of(\"added_at\", \"added_by…track\",\n      \"is_local\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "added_at");
        v03.i(fVarF, "moshi.adapter(String::cl…  emptySet(), \"added_at\")");
        this.nullableStringAdapter = fVarF;
        f<UserPublic> fVarF2 = oVar.f(UserPublic.class, emptySet, "added_by");
        v03.i(fVarF2, "moshi.adapter(UserPublic…, emptySet(), \"added_by\")");
        this.nullableUserPublicAdapter = fVarF2;
        f<Entity> fVarF3 = oVar.f(Entity.class, emptySet, "entity");
        v03.i(fVarF3, "moshi.adapter(Entity::cl…    emptySet(), \"entity\")");
        this.nullableEntityAdapter = fVarF3;
        f<Boolean> fVarF4 = oVar.f(Boolean.class, emptySet, "is_local");
        v03.i(fVarF4, "moshi.adapter(Boolean::c…, emptySet(), \"is_local\")");
        this.nullableBooleanAdapter = fVarF4;
    }

    public String toString() {
        return u4.b(35, "GeneratedJsonAdapter(PlaylistTrack)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public PlaylistTrack fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        Entity entityFromJson = null;
        Boolean boolFromJson = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        UserPublic userPublicFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                userPublicFromJson = this.nullableUserPublicAdapter.fromJson(jsonReader);
                z2 = true;
            } else if (iM0 == 2) {
                entityFromJson = this.nullableEntityAdapter.fromJson(jsonReader);
                z3 = true;
            } else if (iM0 == 3) {
                boolFromJson = this.nullableBooleanAdapter.fromJson(jsonReader);
                z4 = true;
            }
        }
        jsonReader.q();
        PlaylistTrack playlistTrack = new PlaylistTrack();
        if (z) {
            playlistTrack.added_at = strFromJson;
        }
        if (z2) {
            playlistTrack.added_by = userPublicFromJson;
        }
        if (z3) {
            playlistTrack.entity = entityFromJson;
        }
        if (z4) {
            playlistTrack.is_local = boolFromJson;
        }
        return playlistTrack;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, PlaylistTrack playlistTrack) {
        v03.j(mVar, "writer");
        if (playlistTrack == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("added_at");
        this.nullableStringAdapter.toJson(mVar, playlistTrack.added_at);
        mVar.J0("added_by");
        this.nullableUserPublicAdapter.toJson(mVar, playlistTrack.added_by);
        mVar.J0(Search.Type.TRACK);
        this.nullableEntityAdapter.toJson(mVar, playlistTrack.entity);
        mVar.J0("is_local");
        this.nullableBooleanAdapter.toJson(mVar, playlistTrack.is_local);
        mVar.X();
    }
}
