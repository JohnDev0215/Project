package u1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import o1.d;
import u1.n;

/* JADX INFO: compiled from: ByteArrayLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class b<Data> implements n<byte[], Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final InterfaceC0162b<Data> f9568a;

    /* JADX INFO: compiled from: ByteArrayLoader.java */
    public static class a implements o<byte[], ByteBuffer> {

        /* JADX INFO: renamed from: u1.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ByteArrayLoader.java */
        class C0161a implements InterfaceC0162b<ByteBuffer> {
            C0161a() {
            }

            @Override // u1.b.InterfaceC0162b
            public Class<ByteBuffer> a() {
                return ByteBuffer.class;
            }

            @Override // u1.b.InterfaceC0162b
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public ByteBuffer b(byte[] bArr) {
                return ByteBuffer.wrap(bArr);
            }
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<byte[], ByteBuffer> c(r rVar) {
            return new b(new C0161a());
        }
    }

    /* JADX INFO: renamed from: u1.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ByteArrayLoader.java */
    public interface InterfaceC0162b<Data> {
        Class<Data> a();

        Data b(byte[] bArr);
    }

    /* JADX INFO: compiled from: ByteArrayLoader.java */
    private static class c<Data> implements o1.d<Data> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final byte[] f9570e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final InterfaceC0162b<Data> f9571g;

        c(byte[] bArr, InterfaceC0162b<Data> interfaceC0162b) {
            this.f9570e = bArr;
            this.f9571g = interfaceC0162b;
        }

        @Override // o1.d
        public Class<Data> a() {
            return this.f9571g.a();
        }

        @Override // o1.d
        public void b() {
        }

        @Override // o1.d
        public void c(com.bumptech.glide.g gVar, d.a<? super Data> aVar) {
            aVar.f(this.f9571g.b(this.f9570e));
        }

        @Override // o1.d
        public void cancel() {
        }

        @Override // o1.d
        public n1.a e() {
            return n1.a.LOCAL;
        }
    }

    /* JADX INFO: compiled from: ByteArrayLoader.java */
    public static class d implements o<byte[], InputStream> {

        /* JADX INFO: compiled from: ByteArrayLoader.java */
        class a implements InterfaceC0162b<InputStream> {
            a() {
            }

            @Override // u1.b.InterfaceC0162b
            public Class<InputStream> a() {
                return InputStream.class;
            }

            @Override // u1.b.InterfaceC0162b
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public InputStream b(byte[] bArr) {
                return new ByteArrayInputStream(bArr);
            }
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<byte[], InputStream> c(r rVar) {
            return new b(new a());
        }
    }

    public b(InterfaceC0162b<Data> interfaceC0162b) {
        this.f9568a = interfaceC0162b;
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> b(byte[] bArr, int i7, int i8, n1.i iVar) {
        return new n.a<>(new j2.d(bArr), new c(bArr, this.f9568a));
    }

    @Override // u1.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(byte[] bArr) {
        return true;
    }
}
