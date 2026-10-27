package y1;

import java.nio.ByteBuffer;
import o1.e;

/* JADX INFO: compiled from: ByteBufferRewinder.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements e<ByteBuffer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ByteBuffer f9959a;

    /* JADX INFO: renamed from: y1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ByteBufferRewinder.java */
    public static class C0178a implements e.a<ByteBuffer> {
        @Override // o1.e.a
        public Class<ByteBuffer> a() {
            return ByteBuffer.class;
        }

        @Override // o1.e.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public e<ByteBuffer> b(ByteBuffer byteBuffer) {
            return new a(byteBuffer);
        }
    }

    public a(ByteBuffer byteBuffer) {
        this.f9959a = byteBuffer;
    }

    @Override // o1.e
    public void b() {
    }

    @Override // o1.e
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ByteBuffer a() {
        this.f9959a.position(0);
        return this.f9959a;
    }
}
