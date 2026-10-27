package com.spotify.webapi.service.models.views;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.webapi.service.models.Entity;
import com.spotify.webapi.service.models.Image;
import com.spotify.webapi.service.models.Pager;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class ViewJsonAdapter extends f<View> {
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<Map<String, Object>> nullableMapOfStringAnyAdapter;
    private final f<Map<String, String>> nullableMapOfStringStringAdapter;
    private final f<Pager<Entity>> nullablePagerOfEntityAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public ViewJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("content", "custom_fields", "external_urls", "href", "id", "images", "name", "rendering", "tag_line", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"content\", \"custom_fi…ing\", \"tag_line\", \"type\")");
        this.options = bVarA;
        ParameterizedType parameterizedTypeJ = zo2.j(Pager.class, Entity.class);
        EmptySet emptySet = EmptySet.c;
        f<Pager<Entity>> fVarF = oVar.f(parameterizedTypeJ, emptySet, "content");
        v03.i(fVarF, "moshi.adapter(Types.newP…tySet(),\n      \"content\")");
        this.nullablePagerOfEntityAdapter = fVarF;
        f<Map<String, Object>> fVarF2 = oVar.f(zo2.j(Map.class, String.class, Object.class), emptySet, "custom_fields");
        v03.i(fVarF2, "moshi.adapter(Types.newP…tySet(), \"custom_fields\")");
        this.nullableMapOfStringAnyAdapter = fVarF2;
        f<Map<String, String>> fVarF3 = oVar.f(zo2.j(Map.class, String.class, String.class), emptySet, "external_urls");
        v03.i(fVarF3, "moshi.adapter(Types.newP…tySet(), \"external_urls\")");
        this.nullableMapOfStringStringAdapter = fVarF3;
        f<String> fVarF4 = oVar.f(String.class, emptySet, "href");
        v03.i(fVarF4, "moshi.adapter(String::cl…      emptySet(), \"href\")");
        this.nullableStringAdapter = fVarF4;
        f<List<Image>> fVarF5 = oVar.f(zo2.j(List.class, Image.class), emptySet, "images");
        v03.i(fVarF5, "moshi.adapter(Types.newP…ptySet(),\n      \"images\")");
        this.nullableListOfImageAdapter = fVarF5;
    }

    public String toString() {
        return u4.b(26, "GeneratedJsonAdapter(View)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public View fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Pager<Entity> pagerFromJson = null;
        Map<String, String> mapFromJson = null;
        String strFromJson = null;
        String strFromJson2 = null;
        List<Image> listFromJson = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        String strFromJson5 = null;
        String strFromJson6 = null;
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
        Map<String, ? extends Object> map = null;
        while (jsonReader.hasNext()) {
            String str = strFromJson6;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    pagerFromJson = this.nullablePagerOfEntityAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z = true;
                    continue;
                case 1:
                    map = (Map) this.nullableMapOfStringAnyAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z2 = true;
                    continue;
                case 2:
                    mapFromJson = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z3 = true;
                    continue;
                case 3:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z4 = true;
                    continue;
                case 4:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z5 = true;
                    continue;
                case 5:
                    listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z6 = true;
                    continue;
                case 6:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z7 = true;
                    continue;
                case 7:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z8 = true;
                    continue;
                case 8:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z9 = true;
                    continue;
                case 9:
                    strFromJson6 = this.nullableStringAdapter.fromJson(jsonReader);
                    z10 = true;
                    continue;
            }
            strFromJson6 = str;
        }
        String str2 = strFromJson6;
        jsonReader.q();
        View view = new View();
        if (z) {
            view.content = pagerFromJson;
        }
        if (z2) {
            view.custom_fields = map;
        }
        if (z3) {
            view.external_urls = mapFromJson;
        }
        if (z4) {
            view.href = strFromJson;
        }
        if (z5) {
            view.id = strFromJson2;
        }
        if (z6) {
            view.images = listFromJson;
        }
        if (z7) {
            view.name = strFromJson3;
        }
        if (z8) {
            view.rendering = strFromJson4;
        }
        if (z9) {
            view.tag_line = strFromJson5;
        }
        if (z10) {
            view.type = str2;
        }
        return view;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, View view) {
        v03.j(mVar, "writer");
        if (view == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("content");
        this.nullablePagerOfEntityAdapter.toJson(mVar, view.content);
        mVar.J0("custom_fields");
        this.nullableMapOfStringAnyAdapter.toJson(mVar, view.custom_fields);
        mVar.J0("external_urls");
        this.nullableMapOfStringStringAdapter.toJson(mVar, view.external_urls);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, view.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, view.id);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, view.images);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, view.name);
        mVar.J0("rendering");
        this.nullableStringAdapter.toJson(mVar, view.rendering);
        mVar.J0("tag_line");
        this.nullableStringAdapter.toJson(mVar, view.tag_line);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, view.type);
        mVar.X();
    }
}
