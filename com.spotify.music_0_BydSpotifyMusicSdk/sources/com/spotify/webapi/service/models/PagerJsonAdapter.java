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
public final class PagerJsonAdapter<T> extends f<Pager<T>> {
    private final f<Integer> intAdapter;
    private final f<List<T>> nullableMutableListOfTNullableAnyAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public PagerJsonAdapter(o oVar, Type[] typeArr) {
        v03.j(oVar, "moshi");
        v03.j(typeArr, "types");
        if (typeArr.length != 1) {
            String str = "TypeVariable mismatch: Expecting 1 type for generic type variables [T], but received " + typeArr.length;
            v03.i(str, "StringBuilder().apply(builderAction).toString()");
            throw new IllegalArgumentException(str.toString());
        }
        JsonReader.b bVarA = JsonReader.b.a("href", "items", "limit", "next", "offset", "previous", "total");
        v03.i(bVarA, "of(\"href\", \"items\", \"lim…et\", \"previous\", \"total\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "href");
        v03.i(fVarF, "moshi.adapter(String::cl…      emptySet(), \"href\")");
        this.nullableStringAdapter = fVarF;
        f<List<T>> fVarF2 = oVar.f(zo2.j(List.class, typeArr[0]), emptySet, "items");
        v03.i(fVarF2, "moshi.adapter(Types.newP…mptySet(),\n      \"items\")");
        this.nullableMutableListOfTNullableAnyAdapter = fVarF2;
        f<Integer> fVarF3 = oVar.f(Integer.TYPE, emptySet, "limit");
        v03.i(fVarF3, "moshi.adapter(Int::class…ava, emptySet(), \"limit\")");
        this.intAdapter = fVarF3;
    }

    public String toString() {
        return u4.b(27, "GeneratedJsonAdapter(Pager)", "StringBuilder(capacity).…builderAction).toString()");
    }

    @Override // com.squareup.moshi.f
    public Pager<T> fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        Integer numFromJson = null;
        String strFromJson2 = null;
        Integer numFromJson2 = null;
        String strFromJson3 = null;
        Integer numFromJson3 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        List<T> listFromJson = null;
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
                    listFromJson = this.nullableMutableListOfTNullableAnyAdapter.fromJson(jsonReader);
                    z2 = true;
                    break;
                case 2:
                    numFromJson = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("limit", "limit", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"limit\", …mit\",\n            reader)");
                        throw jsonDataExceptionW;
                    }
                    break;
                    break;
                case 3:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    z3 = true;
                    break;
                case 4:
                    numFromJson2 = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson2 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("offset", "offset", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"offset\",…set\",\n            reader)");
                        throw jsonDataExceptionW2;
                    }
                    break;
                    break;
                case 5:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    z4 = true;
                    break;
                case 6:
                    numFromJson3 = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson3 == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("total", "total", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"total\", …tal\",\n            reader)");
                        throw jsonDataExceptionW3;
                    }
                    break;
                    break;
            }
        }
        jsonReader.q();
        Pager<T> pager = new Pager<>();
        if (z) {
            pager.href = strFromJson;
        }
        if (z2) {
            pager.items = listFromJson;
        }
        pager.limit = numFromJson != null ? numFromJson.intValue() : pager.limit;
        if (z3) {
            pager.next = strFromJson2;
        }
        pager.offset = numFromJson2 != null ? numFromJson2.intValue() : pager.offset;
        if (z4) {
            pager.previous = strFromJson3;
        }
        pager.total = numFromJson3 != null ? numFromJson3.intValue() : pager.total;
        return pager;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Pager<T> pager) {
        v03.j(mVar, "writer");
        if (pager == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, pager.href);
        mVar.J0("items");
        this.nullableMutableListOfTNullableAnyAdapter.toJson(mVar, pager.items);
        mVar.J0("limit");
        u4.s(pager.limit, this.intAdapter, mVar, "next");
        this.nullableStringAdapter.toJson(mVar, pager.next);
        mVar.J0("offset");
        u4.s(pager.offset, this.intAdapter, mVar, "previous");
        this.nullableStringAdapter.toJson(mVar, pager.previous);
        mVar.J0("total");
        this.intAdapter.toJson(mVar, Integer.valueOf(pager.total));
        mVar.X();
    }
}
