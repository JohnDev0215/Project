package p;

import com.squareup.moshi.JsonReader;

/* JADX INFO: loaded from: classes.dex */
public final class po1<T> extends com.squareup.moshi.f<T> {
    private final com.squareup.moshi.f<T> a;

    public po1(com.squareup.moshi.f<T> fVar) {
        this.a = fVar;
    }

    @Override // com.squareup.moshi.f
    public T fromJson(JsonReader jsonReader) {
        return jsonReader.H0() == JsonReader.Token.NULL ? (T) jsonReader.o0() : this.a.fromJson(jsonReader);
    }

    @Override // com.squareup.moshi.f
    public void toJson(com.squareup.moshi.m mVar, T t) {
        if (t == null) {
            mVar.K0();
        } else {
            this.a.toJson(mVar, t);
        }
    }

    public String toString() {
        return this.a + ".nullSafe()";
    }
}
