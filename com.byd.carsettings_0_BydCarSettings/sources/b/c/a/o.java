package b.c.a;

import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;

/* JADX INFO: compiled from: TypeAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class o<T> {

    /* JADX INFO: compiled from: TypeAdapter.java */
    public class a extends o<T> {
        public a() {
        }

        @Override // b.c.a.o
        public T b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                return (T) o.this.b(aVar);
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        public void d(b.c.a.t.b bVar, T t) throws IOException {
            if (t == null) {
                bVar.s();
            } else {
                o.this.d(bVar, t);
            }
        }
    }

    public final o<T> a() {
        return new a();
    }

    public abstract T b(b.c.a.t.a aVar) throws IOException;

    public final i c(T t) {
        try {
            b.c.a.r.i.f fVar = new b.c.a.r.i.f();
            d(fVar, t);
            return fVar.L();
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }

    public abstract void d(b.c.a.t.b bVar, T t) throws IOException;
}
