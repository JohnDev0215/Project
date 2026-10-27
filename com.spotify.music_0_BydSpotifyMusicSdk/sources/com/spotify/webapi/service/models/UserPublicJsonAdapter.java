package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.List;
import java.util.Map;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class UserPublicJsonAdapter extends f<UserPublic> {
    private final f<Followers> nullableFollowersAdapter;
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<Map<String, String>> nullableMapOfStringStringAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public UserPublicJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("display_name", "external_urls", "followers", "href", "id", "images", RxProductState.Keys.KEY_TYPE, "uri");
        v03.i(bVarA, "of(\"display_name\", \"exte… \"images\", \"type\", \"uri\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "display_name");
        v03.i(fVarF, "moshi.adapter(String::cl…ptySet(), \"display_name\")");
        this.nullableStringAdapter = fVarF;
        f<Map<String, String>> fVarF2 = oVar.f(zo2.j(Map.class, String.class, String.class), emptySet, "external_urls");
        v03.i(fVarF2, "moshi.adapter(Types.newP…tySet(), \"external_urls\")");
        this.nullableMapOfStringStringAdapter = fVarF2;
        f<Followers> fVarF3 = oVar.f(Followers.class, emptySet, "followers");
        v03.i(fVarF3, "moshi.adapter(Followers:… emptySet(), \"followers\")");
        this.nullableFollowersAdapter = fVarF3;
        f<List<Image>> fVarF4 = oVar.f(zo2.j(List.class, Image.class), emptySet, "images");
        v03.i(fVarF4, "moshi.adapter(Types.newP…ptySet(),\n      \"images\")");
        this.nullableListOfImageAdapter = fVarF4;
    }

    public String toString() {
        return u4.b(32, "GeneratedJsonAdapter(UserPublic)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public UserPublic fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        Followers followersFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        List<Image> listFromJson = null;
        String strFromJson4 = null;
        String strFromJson5 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        Map<String, String> mapFromJson = null;
        while (jsonReader.hasNext()) {
            String str = strFromJson5;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z = true;
                    continue;
                case 1:
                    mapFromJson = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z2 = true;
                    continue;
                case 2:
                    followersFromJson = this.nullableFollowersAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z3 = true;
                    continue;
                case 3:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z4 = true;
                    continue;
                case 4:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z5 = true;
                    continue;
                case 5:
                    listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z6 = true;
                    continue;
                case 6:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z7 = true;
                    continue;
                case 7:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    z8 = true;
                    continue;
            }
            strFromJson5 = str;
        }
        String str2 = strFromJson5;
        jsonReader.q();
        UserPublic userPublic = new UserPublic();
        if (z) {
            userPublic.display_name = strFromJson;
        }
        if (z2) {
            userPublic.external_urls = mapFromJson;
        }
        if (z3) {
            userPublic.followers = followersFromJson;
        }
        if (z4) {
            userPublic.href = strFromJson2;
        }
        if (z5) {
            userPublic.id = strFromJson3;
        }
        if (z6) {
            userPublic.images = listFromJson;
        }
        if (z7) {
            userPublic.type = strFromJson4;
        }
        if (z8) {
            userPublic.uri = str2;
        }
        return userPublic;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, UserPublic userPublic) {
        v03.j(mVar, "writer");
        if (userPublic == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("display_name");
        this.nullableStringAdapter.toJson(mVar, userPublic.display_name);
        mVar.J0("external_urls");
        this.nullableMapOfStringStringAdapter.toJson(mVar, userPublic.external_urls);
        mVar.J0("followers");
        this.nullableFollowersAdapter.toJson(mVar, userPublic.followers);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, userPublic.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, userPublic.id);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, userPublic.images);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, userPublic.type);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, userPublic.uri);
        mVar.X();
    }
}
