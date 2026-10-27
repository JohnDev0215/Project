package b.b.a;

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

        @Override // b.b.a.o
        public T b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                return (T) o.this.b(aVar);
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        public void d(b.b.a.t.b bVar, T t) throws IOException {
            if (t == null) {
                bVar.n();
            } else {
                o.this.d(bVar, t);
            }
        }
    }

    public final o<T> a() {
        return new a();
    }

    public abstract T b(b.b.a.t.a aVar) throws IOException;

    public final i c(T t) {
        try {
            b.b.a.r.i.f fVar = new b.b.a.r.i.f();
            d(fVar, t);
            return fVar.D();
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    public abstract void d(b.b.a.t.b bVar, T t) throws IOException;
}
