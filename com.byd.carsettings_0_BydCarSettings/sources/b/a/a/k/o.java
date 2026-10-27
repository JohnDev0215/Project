package b.a.a.k;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: ByteBufferCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class o implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o f390a = new o();

    /* JADX INFO: compiled from: ByteBufferCodec.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public byte[] f391a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f392b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f393c;

        public ByteBuffer a() {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.f391a);
            byteBufferWrap.limit(this.f392b);
            byteBufferWrap.position(this.f393c);
            return byteBufferWrap;
        }
    }

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        return (T) ((a) aVar.N(a.class)).a();
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        ByteBuffer byteBuffer = (ByteBuffer) obj;
        byte[] bArrArray = byteBuffer.array();
        d1 d1Var = i0Var.k;
        d1Var.write(123);
        d1Var.u("array");
        d1Var.q(bArrArray);
        d1Var.x(',', "limit", byteBuffer.limit());
        d1Var.x(',', "position", byteBuffer.position());
        d1Var.write(125);
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 14;
    }
}
