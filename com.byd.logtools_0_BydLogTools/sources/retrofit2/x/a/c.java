package retrofit2.x.a;

import com.google.gson.JsonIOException;
import com.google.gson.d;
import com.google.gson.p;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.h;

/* JADX INFO: compiled from: GsonResponseBodyConverter.java */
/* JADX INFO: loaded from: classes.dex */
final class c<T> implements h<ResponseBody, T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f5352a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final p<T> f5353b;

    c(d dVar, p<T> pVar) {
        this.f5352a = dVar;
        this.f5353b = pVar;
    }

    @Override // retrofit2.h
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public T a(ResponseBody responseBody) throws IOException {
        com.google.gson.stream.a aVarO = this.f5352a.o(responseBody.charStream());
        try {
            T tB = this.f5353b.b(aVarO);
            if (aVarO.x() == JsonToken.END_DOCUMENT) {
                return tB;
            }
            throw new JsonIOException("JSON document was not fully consumed.");
        } finally {
            responseBody.close();
        }
    }
}
