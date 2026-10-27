package k6;

import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;

/* JADX INFO: compiled from: TypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class o<T> {

    /* JADX INFO: compiled from: TypeAdapter.java */
    public class a extends o<T> {
        public a() {
        }

        @Override // k6.o
        public T b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                return (T) o.this.b(aVar);
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        public void d(q6.b bVar, T t7) throws IOException {
            if (t7 == null) {
                bVar.B();
            } else {
                o.this.d(bVar, t7);
            }
        }
    }

    public final o<T> a() {
        return new a();
    }

    public abstract T b(q6.a aVar) throws IOException;

    public final i c(T t7) {
        try {
            n6.f fVar = new n6.f();
            d(fVar, t7);
            return fVar.a0();
        } catch (IOException e8) {
            throw new JsonIOException(e8);
        }
    }

    public abstract void d(q6.b bVar, T t7) throws IOException;
}
