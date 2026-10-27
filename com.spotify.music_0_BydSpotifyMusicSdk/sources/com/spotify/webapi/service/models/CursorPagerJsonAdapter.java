package com.spotify.webapi.service.models;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class CursorPagerJsonAdapter<T> extends f<CursorPager<T>> {
    private final f<Integer> intAdapter;
    private final f<Cursor> nullableCursorAdapter;
    private final f<List<T>> nullableMutableListOfTNullableAnyAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public CursorPagerJsonAdapter(o oVar, Type[] typeArr) {
        v03.j(oVar, "moshi");
        v03.j(typeArr, "types");
        if (typeArr.length != 1) {
            String str = "TypeVariable mismatch: Expecting 1 type for generic type variables [T], but received " + typeArr.length;
            v03.i(str, "StringBuilder().apply(builderAction).toString()");
            throw new IllegalArgumentException(str.toString());
        }
        JsonReader.b bVarA = JsonReader.b.a("cursors", "href", "items", "limit", "next", "total");
        v03.i(bVarA, "of(\"cursors\", \"href\", \"i…\"limit\", \"next\", \"total\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<Cursor> fVarF = oVar.f(Cursor.class, emptySet, "cursors");
        v03.i(fVarF, "moshi.adapter(Cursor::cl…   emptySet(), \"cursors\")");
        this.nullableCursorAdapter = fVarF;
        f<String> fVarF2 = oVar.f(String.class, emptySet, "href");
        v03.i(fVarF2, "moshi.adapter(String::cl…      emptySet(), \"href\")");
        this.nullableStringAdapter = fVarF2;
        f<List<T>> fVarF3 = oVar.f(zo2.j(List.class, typeArr[0]), emptySet, "items");
        v03.i(fVarF3, "moshi.adapter(Types.newP…mptySet(),\n      \"items\")");
        this.nullableMutableListOfTNullableAnyAdapter = fVarF3;
        f<Integer> fVarF4 = oVar.f(Integer.TYPE, emptySet, "limit");
        v03.i(fVarF4, "moshi.adapter(Int::class…ava, emptySet(), \"limit\")");
        this.intAdapter = fVarF4;
    }

    public String toString() {
        return u4.b(33, "GeneratedJsonAdapter(CursorPager)", "StringBuilder(capacity).…builderAction).toString()");
    }

    @Override // com.squareup.moshi.f
    public CursorPager<T> fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Cursor cursorFromJson = null;
        List<T> listFromJson = null;
        Integer numFromJson = null;
        String strFromJson = null;
        Integer numFromJson2 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        String strFromJson2 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    cursorFromJson = this.nullableCursorAdapter.fromJson(jsonReader);
                    z = true;
                    break;
                case 1:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    z2 = true;
                    break;
                case 2:
                    listFromJson = this.nullableMutableListOfTNullableAnyAdapter.fromJson(jsonReader);
                    z3 = true;
                    break;
                case 3:
                    numFromJson = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("limit", "limit", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"limit\", …mit\",\n            reader)");
                        throw jsonDataExceptionW;
                    }
                    break;
                    break;
                case 4:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    z4 = true;
                    break;
                case 5:
                    numFromJson2 = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson2 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("total", "total", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"total\", …tal\",\n            reader)");
                        throw jsonDataExceptionW2;
                    }
                    break;
                    break;
            }
        }
        jsonReader.q();
        CursorPager<T> cursorPager = new CursorPager<>();
        if (z) {
            cursorPager.cursors = cursorFromJson;
        }
        if (z2) {
            cursorPager.href = strFromJson2;
        }
        if (z3) {
            cursorPager.items = listFromJson;
        }
        cursorPager.limit = numFromJson != null ? numFromJson.intValue() : cursorPager.limit;
        if (z4) {
            cursorPager.next = strFromJson;
        }
        cursorPager.total = numFromJson2 != null ? numFromJson2.intValue() : cursorPager.total;
        return cursorPager;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, CursorPager<T> cursorPager) {
        v03.j(mVar, "writer");
        if (cursorPager == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("cursors");
        this.nullableCursorAdapter.toJson(mVar, cursorPager.cursors);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, cursorPager.href);
        mVar.J0("items");
        this.nullableMutableListOfTNullableAnyAdapter.toJson(mVar, cursorPager.items);
        mVar.J0("limit");
        u4.s(cursorPager.limit, this.intAdapter, mVar, "next");
        this.nullableStringAdapter.toJson(mVar, cursorPager.next);
        mVar.J0("total");
        this.intAdapter.toJson(mVar, Integer.valueOf(cursorPager.total));
        mVar.X();
    }
}
