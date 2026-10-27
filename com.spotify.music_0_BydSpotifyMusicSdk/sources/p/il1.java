package p;

import com.squareup.moshi.JsonReader;

/* JADX INFO: loaded from: classes.dex */
public final class il1 extends com.squareup.moshi.f {
    public final com.squareup.moshi.f a;
    public final String b;

    public il1(com.squareup.moshi.f fVar, String str) {
        v03.j(str, "name");
        this.a = fVar;
        this.b = str;
    }

    @Override // com.squareup.moshi.f
    public final Object fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        JsonReader jsonReaderI0 = jsonReader.I0();
        jsonReaderI0.c();
        boolean zHasNext = jsonReaderI0.hasNext();
        com.squareup.moshi.f fVar = this.a;
        if (zHasNext && v03.b(jsonReaderI0.r0(), this.b)) {
            jsonReaderI0.D();
            if (!jsonReaderI0.hasNext()) {
                jsonReader.c();
                jsonReader.Q0();
                Object objFromJson = fVar.fromJson(jsonReader.E0());
                jsonReader.q();
                return objFromJson;
            }
        }
        return fVar.fromJson(jsonReader);
    }

    @Override // com.squareup.moshi.f
    public final void toJson(com.squareup.moshi.m mVar, Object obj) {
        v03.j(mVar, "writer");
        mVar.i().J0(this.b);
        this.a.toJson(mVar, obj);
        mVar.X();
    }
}
