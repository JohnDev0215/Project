package com.bumptech.glide.load.o;

import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.o.n;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class b<Data> implements n<byte[], Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final InterfaceC0076b<Data> f2211a;

    public static class a implements o<byte[], ByteBuffer> {

        /* JADX INFO: renamed from: com.bumptech.glide.load.o.b$a$a, reason: collision with other inner class name */
        class C0075a implements InterfaceC0076b<ByteBuffer> {
            C0075a() {
            }

            @Override // com.bumptech.glide.load.o.b.InterfaceC0076b
            public Class<ByteBuffer> a() {
                return ByteBuffer.class;
            }

            @Override // com.bumptech.glide.load.o.b.InterfaceC0076b
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public ByteBuffer b(byte[] bArr) {
                return ByteBuffer.wrap(bArr);
            }
        }

        @Override // com.bumptech.glide.load.o.o
        public n<byte[], ByteBuffer> b(r rVar) {
            return new b(new C0075a());
        }
    }

    /* JADX INFO: renamed from: com.bumptech.glide.load.o.b$b, reason: collision with other inner class name */
    public interface InterfaceC0076b<Data> {
        Class<Data> a();

        Data b(byte[] bArr);
    }

    private static class c<Data> implements com.bumptech.glide.load.data.d<Data> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final byte[] f2213b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final InterfaceC0076b<Data> f2214c;

        c(byte[] bArr, InterfaceC0076b<Data> interfaceC0076b) {
            this.f2213b = bArr;
            this.f2214c = interfaceC0076b;
        }

        @Override // com.bumptech.glide.load.data.d
        public Class<Data> a() {
            return this.f2214c.a();
        }

        @Override // com.bumptech.glide.load.data.d
        public void b() {
        }

        @Override // com.bumptech.glide.load.data.d
        public com.bumptech.glide.load.a c() {
            return com.bumptech.glide.load.a.LOCAL;
        }

        @Override // com.bumptech.glide.load.data.d
        public void cancel() {
        }

        @Override // com.bumptech.glide.load.data.d
        public void e(com.bumptech.glide.g gVar, d.a<? super Data> aVar) {
            aVar.f(this.f2214c.b(this.f2213b));
        }
    }

    public static class d implements o<byte[], InputStream> {

        class a implements InterfaceC0076b<InputStream> {
            a() {
            }

            @Override // com.bumptech.glide.load.o.b.InterfaceC0076b
            public Class<InputStream> a() {
                return InputStream.class;
            }

            @Override // com.bumptech.glide.load.o.b.InterfaceC0076b
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public InputStream b(byte[] bArr) {
                return new ByteArrayInputStream(bArr);
            }
        }

        @Override // com.bumptech.glide.load.o.o
        public n<byte[], InputStream> b(r rVar) {
            return new b(new a());
        }
    }

    public b(InterfaceC0076b<Data> interfaceC0076b) {
        this.f2211a = interfaceC0076b;
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> a(byte[] bArr, int i, int i2, com.bumptech.glide.load.i iVar) {
        return new n.a<>(new com.bumptech.glide.s.b(bArr), new c(bArr, this.f2211a));
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(byte[] bArr) {
        return true;
    }
}
