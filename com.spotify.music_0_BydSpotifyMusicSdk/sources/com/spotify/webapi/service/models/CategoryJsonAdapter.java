package com.spotify.webapi.service.models;

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
public final class CategoryJsonAdapter extends f<Category> {
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public CategoryJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("href", "icons", "id", "name");
        v03.i(bVarA, "of(\"href\", \"icons\", \"id\", \"name\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "href");
        v03.i(fVarF, "moshi.adapter(String::cl…      emptySet(), \"href\")");
        this.nullableStringAdapter = fVarF;
        f<List<Image>> fVarF2 = oVar.f(zo2.j(List.class, Image.class), emptySet, "icons");
        v03.i(fVarF2, "moshi.adapter(Types.newP…mptySet(),\n      \"icons\")");
        this.nullableListOfImageAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(30, "GeneratedJsonAdapter(Category)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Category fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
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
            }
        }
        jsonReader.q();
        Category category = new Category();
        if (z) {
            category.href = strFromJson;
        }
        if (z2) {
            category.icons = listFromJson;
        }
        if (z3) {
            category.id = strFromJson2;
        }
        if (z4) {
            category.name = strFromJson3;
        }
        return category;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Category category) {
        v03.j(mVar, "writer");
        if (category == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, category.href);
        mVar.J0("icons");
        this.nullableListOfImageAdapter.toJson(mVar, (List<Image>) category.icons);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, category.id);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, category.name);
        mVar.X();
    }
}
