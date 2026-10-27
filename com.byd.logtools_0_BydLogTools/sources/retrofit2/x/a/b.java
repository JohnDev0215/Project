package retrofit2.x.a;

import com.google.gson.d;
import com.google.gson.p;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.h;

/* JADX INFO: compiled from: GsonRequestBodyConverter.java */
/* JADX INFO: loaded from: classes.dex */
final class b<T> implements h<T, RequestBody> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final MediaType f5348a = MediaType.get("application/json; charset=UTF-8");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Charset f5349b = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final d f5350c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final p<T> f5351d;

    b(d dVar, p<T> pVar) {
        this.f5350c = dVar;
        this.f5351d = pVar;
    }

    @Override // retrofit2.h
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public RequestBody a(T t) throws IOException {
        Buffer buffer = new Buffer();
        com.google.gson.stream.b bVarP = this.f5350c.p(new OutputStreamWriter(buffer.outputStream(), f5349b));
        this.f5351d.d(bVarP, t);
        bVarP.close();
        return RequestBody.create(f5348a, buffer.readByteString());
    }
}
