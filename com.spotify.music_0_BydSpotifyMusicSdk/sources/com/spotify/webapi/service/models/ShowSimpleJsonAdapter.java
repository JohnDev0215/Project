package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.webapi.service.models.ShowSimple;
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
public final class ShowSimpleJsonAdapter extends f<ShowSimple> {
    private final f<Boolean> nullableBooleanAdapter;
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<ShowSimple.MediaType> nullableMediaTypeAdapter;
    private final f<Pager<Entity>> nullablePagerOfEntityAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public ShowSimpleJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("description", "episodes", "explicit", "href", "id", "images", "media_type", "name", "publisher", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"description\", \"episo…ublisher\", \"uri\", \"type\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "description");
        v03.i(fVarF, "moshi.adapter(String::cl…mptySet(), \"description\")");
        this.nullableStringAdapter = fVarF;
        f<Pager<Entity>> fVarF2 = oVar.f(zo2.j(Pager.class, Entity.class), emptySet, "episodes");
        v03.i(fVarF2, "moshi.adapter(Types.newP…ySet(),\n      \"episodes\")");
        this.nullablePagerOfEntityAdapter = fVarF2;
        f<Boolean> fVarF3 = oVar.f(Boolean.class, emptySet, "explicit");
        v03.i(fVarF3, "moshi.adapter(Boolean::c…, emptySet(), \"explicit\")");
        this.nullableBooleanAdapter = fVarF3;
        f<List<Image>> fVarF4 = oVar.f(zo2.j(List.class, Image.class), emptySet, "images");
        v03.i(fVarF4, "moshi.adapter(Types.newP…ptySet(),\n      \"images\")");
        this.nullableListOfImageAdapter = fVarF4;
        f<ShowSimple.MediaType> fVarF5 = oVar.f(ShowSimple.MediaType.class, emptySet, "mediaType");
        v03.i(fVarF5, "moshi.adapter(ShowSimple… emptySet(), \"mediaType\")");
        this.nullableMediaTypeAdapter = fVarF5;
    }

    public String toString() {
        return u4.b(32, "GeneratedJsonAdapter(ShowSimple)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public ShowSimple fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        Boolean boolFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        List<Image> listFromJson = null;
        ShowSimple.MediaType mediaTypeFromJson = null;
        String strFromJson4 = null;
        String strFromJson5 = null;
        String strFromJson6 = null;
        String strFromJson7 = null;
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
        Pager<Entity> pagerFromJson = null;
        while (jsonReader.hasNext()) {
            String str = strFromJson7;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z = true;
                    continue;
                case 1:
                    pagerFromJson = this.nullablePagerOfEntityAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z2 = true;
                    continue;
                case 2:
                    boolFromJson = this.nullableBooleanAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z3 = true;
                    continue;
                case 3:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z4 = true;
                    continue;
                case 4:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z5 = true;
                    continue;
                case 5:
                    listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z6 = true;
                    continue;
                case 6:
                    mediaTypeFromJson = this.nullableMediaTypeAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z7 = true;
                    continue;
                case 7:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z8 = true;
                    continue;
                case 8:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z9 = true;
                    continue;
                case 9:
                    strFromJson6 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z10 = true;
                    continue;
                case 10:
                    strFromJson7 = this.nullableStringAdapter.fromJson(jsonReader);
                    z11 = true;
                    continue;
            }
            strFromJson7 = str;
        }
        String str2 = strFromJson7;
        jsonReader.q();
        ShowSimple showSimple = new ShowSimple();
        if (z) {
            showSimple.description = strFromJson;
        }
        if (z2) {
            showSimple.episodes = pagerFromJson;
        }
        if (z3) {
            showSimple.explicit = boolFromJson;
        }
        if (z4) {
            showSimple.href = strFromJson2;
        }
        if (z5) {
            showSimple.id = strFromJson3;
        }
        if (z6) {
            showSimple.images = listFromJson;
        }
        if (z7) {
            showSimple.mediaType = mediaTypeFromJson;
        }
        if (z8) {
            showSimple.name = strFromJson4;
        }
        if (z9) {
            showSimple.publisher = strFromJson5;
        }
        if (z10) {
            showSimple.uri = strFromJson6;
        }
        if (z11) {
            showSimple.type = str2;
        }
        return showSimple;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, ShowSimple showSimple) {
        v03.j(mVar, "writer");
        if (showSimple == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("description");
        this.nullableStringAdapter.toJson(mVar, showSimple.description);
        mVar.J0("episodes");
        this.nullablePagerOfEntityAdapter.toJson(mVar, showSimple.episodes);
        mVar.J0("explicit");
        this.nullableBooleanAdapter.toJson(mVar, showSimple.explicit);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, showSimple.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, showSimple.id);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, showSimple.images);
        mVar.J0("media_type");
        this.nullableMediaTypeAdapter.toJson(mVar, showSimple.mediaType);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, showSimple.name);
        mVar.J0("publisher");
        this.nullableStringAdapter.toJson(mVar, showSimple.publisher);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, showSimple.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, showSimple.type);
        mVar.X();
    }
}
