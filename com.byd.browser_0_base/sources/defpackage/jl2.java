package defpackage;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: SharedBufferHandle.java */
/* JADX INFO: loaded from: classes3.dex */
public interface jl2 extends tt0 {

    /* JADX INFO: compiled from: SharedBufferHandle.java */
    public static class a extends mn0<a> {
        public static final a c;

        static {
            a aVar = new a();
            aVar.b = true;
            c = aVar;
        }
    }

    void D(ByteBuffer byteBuffer);

    ByteBuffer d(long j);
}
