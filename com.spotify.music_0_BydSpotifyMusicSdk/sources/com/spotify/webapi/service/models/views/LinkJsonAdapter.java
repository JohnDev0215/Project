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
public final class LinkJsonAdapter extends f<Link> {
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public LinkJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("href", "images", "name", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"href\", \"images\", \"name\", \"uri\",\n      \"type\")");
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
        return u4.b(26, "GeneratedJsonAdapter(Link)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Link fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        List<Image> listFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
                z2 = true;
            } else if (iM0 == 2) {
                strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                z3 = true;
            } else if (iM0 == 3) {
                strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                z4 = true;
            } else if (iM0 == 4) {
                strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                z5 = true;
            }
        }
        jsonReader.q();
        Link link = new Link();
        if (z) {
            link.href = strFromJson;
        }
        if (z2) {
            link.images = listFromJson;
        }
        if (z3) {
            link.name = strFromJson2;
        }
        if (z4) {
            link.uri = strFromJson3;
        }
        if (z5) {
            link.type = strFromJson4;
        }
        return link;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Link link) {
        v03.j(mVar, "writer");
        if (link == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, link.href);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, link.images);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, link.name);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, link.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, link.type);
        mVar.X();
    }
}
