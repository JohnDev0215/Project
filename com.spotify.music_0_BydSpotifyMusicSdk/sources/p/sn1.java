package p;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;

/* JADX INFO: loaded from: classes.dex */
public final class sn1<T> extends com.squareup.moshi.f<T> {
    private final com.squareup.moshi.f<T> a;

    public sn1(com.squareup.moshi.f<T> fVar) {
        this.a = fVar;
    }

    @Override // com.squareup.moshi.f
    public T fromJson(JsonReader jsonReader) {
        if (jsonReader.H0() != JsonReader.Token.NULL) {
            return this.a.fromJson(jsonReader);
        }
        throw new JsonDataException("Unexpected null at " + jsonReader.J());
    }

    @Override // com.squareup.moshi.f
    public void toJson(com.squareup.moshi.m mVar, T t) {
        if (t != null) {
            this.a.toJson(mVar, t);
        } else {
            throw new JsonDataException("Unexpected null at " + mVar.E0());
        }
    }

    public String toString() {
        return this.a + ".nonNull()";
    }
}
