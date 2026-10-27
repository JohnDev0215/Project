package com.spotify.playerlimited.player.models;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class StorageStatisticsJsonAdapter extends f<StorageStatistics> {
    public final JsonReader.b a;
    public final f b;

    public StorageStatisticsJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("current_locked_size");
        v03.i(bVarA, "of(\"current_locked_size\")");
        this.a = bVarA;
        f fVarF = oVar.f(Integer.TYPE, EmptySet.c, "currentLockedSize");
        v03.i(fVarF, "moshi.adapter(Int::class…     \"currentLockedSize\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final StorageStatistics fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Integer num = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0 && (num = (Integer) this.b.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW = ds2.w("currentLockedSize", "current_locked_size", jsonReader);
                v03.i(jsonDataExceptionW, "unexpectedNull(\"currentL…ent_locked_size\", reader)");
                throw jsonDataExceptionW;
            }
        }
        jsonReader.q();
        if (num != null) {
            return new StorageStatistics(num.intValue());
        }
        JsonDataException jsonDataExceptionO = ds2.o("currentLockedSize", "current_locked_size", jsonReader);
        v03.i(jsonDataExceptionO, "missingProperty(\"current…ent_locked_size\", reader)");
        throw jsonDataExceptionO;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, StorageStatistics storageStatistics) {
        StorageStatistics storageStatistics2 = storageStatistics;
        v03.j(mVar, "writer");
        if (storageStatistics2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("current_locked_size");
        this.b.toJson(mVar, Integer.valueOf(storageStatistics2.a));
        mVar.X();
    }

    public final String toString() {
        return u4.b(39, "GeneratedJsonAdapter(StorageStatistics)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
