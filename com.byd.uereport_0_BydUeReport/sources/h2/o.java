package h2;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: ByteBufferCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class o implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o f5291a = new o();

    /* JADX INFO: compiled from: ByteBufferCodec.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public byte[] f5292a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5293b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5294c;

        public ByteBuffer a() {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.f5292a);
            byteBufferWrap.limit(this.f5293b);
            byteBufferWrap.position(this.f5294c);
            return byteBufferWrap;
        }
    }

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        return (T) ((a) aVar.d0(a.class)).a();
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        ByteBuffer byteBuffer = (ByteBuffer) obj;
        byte[] bArrArray = byteBuffer.array();
        d1 d1Var = i0Var.f5241k;
        d1Var.write(123);
        d1Var.E("array");
        d1Var.x(bArrArray);
        d1Var.L(',', "limit", byteBuffer.limit());
        d1Var.L(',', "position", byteBuffer.position());
        d1Var.write(125);
    }

    @Override // g2.t
    public int e() {
        return 14;
    }
}
