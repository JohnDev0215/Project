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
public final class UserPrivateJsonAdapter extends f<UserPrivate> {
    private final f<Followers> nullableFollowersAdapter;
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<Map<String, String>> nullableMapOfStringStringAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public UserPrivateJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("birthdate", "country", "email", "product", "display_name", "external_urls", "followers", "href", "id", "images", RxProductState.Keys.KEY_TYPE, "uri");
        v03.i(bVarA, "of(\"birthdate\", \"country…es\", \"type\",\n      \"uri\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "birthdate");
        v03.i(fVarF, "moshi.adapter(String::cl… emptySet(), \"birthdate\")");
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
        return u4.b(33, "GeneratedJsonAdapter(UserPrivate)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public UserPrivate fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        Map<String, String> mapFromJson = null;
        Followers followersFromJson = null;
        String strFromJson5 = null;
        String strFromJson6 = null;
        List<Image> listFromJson = null;
        String strFromJson7 = null;
        String strFromJson8 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        boolean z12 = false;
        String strFromJson9 = null;
        while (jsonReader.hasNext()) {
            String str = strFromJson8;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson8 = str;
                    z = true;
                    continue;
                case 1:
                    strFromJson9 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson8 = str;
                    z2 = true;
                    continue;
                case 2:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson8 = str;
                    z3 = true;
                    continue;
                case 3:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson8 = str;
                    z4 = true;
                    continue;
                case 4:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson8 = str;
                    z5 = true;
                    continue;
                case 5:
                    mapFromJson = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    strFromJson8 = str;
                    z6 = true;
                    continue;
                case 6:
                    followersFromJson = this.nullableFollowersAdapter.fromJson(jsonReader);
                    strFromJson8 = str;
                    z7 = true;
                    continue;
                case 7:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson8 = str;
                    z8 = true;
                    continue;
                case 8:
                    strFromJson6 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson8 = str;
                    z9 = true;
                    continue;
                case 9:
                    listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
                    strFromJson8 = str;
                    z10 = true;
                    continue;
                case 10:
                    strFromJson7 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson8 = str;
                    z11 = true;
                    continue;
                case 11:
                    strFromJson8 = this.nullableStringAdapter.fromJson(jsonReader);
                    z12 = true;
                    continue;
            }
            strFromJson8 = str;
        }
        String str2 = strFromJson8;
        jsonReader.q();
        UserPrivate userPrivate = new UserPrivate();
        if (z) {
            userPrivate.birthdate = strFromJson;
        }
        if (z2) {
            userPrivate.country = strFromJson9;
        }
        if (z3) {
            userPrivate.email = strFromJson2;
        }
        if (z4) {
            userPrivate.product = strFromJson3;
        }
        if (z5) {
            userPrivate.display_name = strFromJson4;
        }
        if (z6) {
            userPrivate.external_urls = mapFromJson;
        }
        if (z7) {
            userPrivate.followers = followersFromJson;
        }
        if (z8) {
            userPrivate.href = strFromJson5;
        }
        if (z9) {
            userPrivate.id = strFromJson6;
        }
        if (z10) {
            userPrivate.images = listFromJson;
        }
        if (z11) {
            userPrivate.type = strFromJson7;
        }
        if (z12) {
            userPrivate.uri = str2;
        }
        return userPrivate;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, UserPrivate userPrivate) {
        v03.j(mVar, "writer");
        if (userPrivate == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("birthdate");
        this.nullableStringAdapter.toJson(mVar, userPrivate.birthdate);
        mVar.J0("country");
        this.nullableStringAdapter.toJson(mVar, userPrivate.country);
        mVar.J0("email");
        this.nullableStringAdapter.toJson(mVar, userPrivate.email);
        mVar.J0("product");
        this.nullableStringAdapter.toJson(mVar, userPrivate.product);
        mVar.J0("display_name");
        this.nullableStringAdapter.toJson(mVar, userPrivate.display_name);
        mVar.J0("external_urls");
        this.nullableMapOfStringStringAdapter.toJson(mVar, userPrivate.external_urls);
        mVar.J0("followers");
        this.nullableFollowersAdapter.toJson(mVar, userPrivate.followers);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, userPrivate.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, userPrivate.id);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, userPrivate.images);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, userPrivate.type);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, userPrivate.uri);
        mVar.X();
    }
}
