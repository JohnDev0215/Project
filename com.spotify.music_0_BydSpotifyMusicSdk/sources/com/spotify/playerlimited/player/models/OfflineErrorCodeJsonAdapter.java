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
public final class OfflineErrorCodeJsonAdapter extends f<OfflineErrorCode> {
    public final JsonReader.b a;
    public final f b;

    public OfflineErrorCodeJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("code");
        v03.i(bVarA, "of(\"code\")");
        this.a = bVarA;
        f fVarF = oVar.f(Integer.TYPE, EmptySet.c, "code");
        v03.i(fVarF, "moshi.adapter(Int::class.java, emptySet(), \"code\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final OfflineErrorCode fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Integer num = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0 && (num = (Integer) this.b.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW = ds2.w("code", "code", jsonReader);
                v03.i(jsonDataExceptionW, "unexpectedNull(\"code\", \"code\", reader)");
                throw jsonDataExceptionW;
            }
        }
        jsonReader.q();
        OfflineErrorCode offlineErrorCode = new OfflineErrorCode();
        offlineErrorCode.a = num != null ? num.intValue() : offlineErrorCode.a;
        return offlineErrorCode;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, OfflineErrorCode offlineErrorCode) {
        OfflineErrorCode offlineErrorCode2 = offlineErrorCode;
        v03.j(mVar, "writer");
        if (offlineErrorCode2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("code");
        this.b.toJson(mVar, Integer.valueOf(offlineErrorCode2.a));
        mVar.X();
    }

    public final String toString() {
        return u4.b(38, "GeneratedJsonAdapter(OfflineErrorCode)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
