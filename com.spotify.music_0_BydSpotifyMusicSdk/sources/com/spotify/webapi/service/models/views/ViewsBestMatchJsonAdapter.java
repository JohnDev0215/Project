package com.spotify.webapi.service.models.views;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.webapi.service.models.Image;
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
public final class ViewsBestMatchJsonAdapter extends f<ViewsBestMatch> {
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public ViewsBestMatchJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("href", "id", "images", "name", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"href\", \"id\", \"images…me\",\n      \"uri\", \"type\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "href");
        v03.i(fVarF, "moshi.adapter(String::cl…      emptySet(), \"href\")");
        this.nullableStringAdapter = fVarF;
        f<List<Image>> fVarF2 = oVar.f(zo2.j(List.class, Image.class), emptySet, "images");
        v03.i(fVarF2, "moshi.adapter(Types.newP…ptySet(),\n      \"images\")");
        this.nullableListOfImageAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(36, "GeneratedJsonAdapter(ViewsBestMatch)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public ViewsBestMatch fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        List<Image> listFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        String strFromJson5 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    z = true;
                    break;
                case 1:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    z2 = true;
                    break;
                case 2:
                    listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
                    z3 = true;
                    break;
                case 3:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    z4 = true;
                    break;
                case 4:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    z5 = true;
                    break;
                case 5:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    z6 = true;
                    break;
            }
        }
        jsonReader.q();
        ViewsBestMatch viewsBestMatch = new ViewsBestMatch();
        if (z) {
            viewsBestMatch.href = strFromJson;
        }
        if (z2) {
            viewsBestMatch.id = strFromJson5;
        }
        if (z3) {
            viewsBestMatch.images = listFromJson;
        }
        if (z4) {
            viewsBestMatch.name = strFromJson2;
        }
        if (z5) {
            viewsBestMatch.uri = strFromJson3;
        }
        if (z6) {
            viewsBestMatch.type = strFromJson4;
        }
        return viewsBestMatch;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, ViewsBestMatch viewsBestMatch) {
        v03.j(mVar, "writer");
        if (viewsBestMatch == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, viewsBestMatch.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, viewsBestMatch.id);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, viewsBestMatch.images);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, viewsBestMatch.name);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, viewsBestMatch.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, viewsBestMatch.type);
        mVar.X();
    }
}
