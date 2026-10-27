package c.b.b;

import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;

/* JADX INFO: compiled from: TypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class o<T> {

    /* JADX INFO: compiled from: TypeAdapter.java */
    public class a extends o<T> {
        public a() {
        }

        @Override // c.b.b.o
        public T b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                return (T) o.this.b(aVar);
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        public void d(c.b.b.t.b bVar, T t) throws IOException {
            if (t == null) {
                bVar.Z();
            } else {
                o.this.d(bVar, t);
            }
        }
    }

    public final o<T> a() {
        return new a();
    }

    public abstract T b(c.b.b.t.a aVar) throws IOException;

    public final i c(T t) {
        try {
            c.b.b.r.i.f fVar = new c.b.b.r.i.f();
            d(fVar, t);
            return fVar.p0();
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }

    public abstract void d(c.b.b.t.b bVar, T t) throws IOException;
}
