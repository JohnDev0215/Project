package c.b.a;

import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;

/* JADX INFO: compiled from: TypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class q<T> {

    /* JADX INFO: compiled from: TypeAdapter.java */
    public class a extends q<T> {
        public a() {
        }

        @Override // c.b.a.q
        public T b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                return (T) q.this.b(aVar);
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        public void d(c.b.a.v.b bVar, T t) throws IOException {
            if (t == null) {
                bVar.P();
            } else {
                q.this.d(bVar, t);
            }
        }
    }

    public final q<T> a() {
        return new a();
    }

    public abstract T b(c.b.a.v.a aVar) throws IOException;

    public final k c(T t) {
        try {
            c.b.a.t.i.f fVar = new c.b.a.t.i.f();
            d(fVar, t);
            return fVar.f0();
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }

    public abstract void d(c.b.a.v.b bVar, T t) throws IOException;
}
