package com.bumptech.glide.load.j;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.i.d;
import com.bumptech.glide.load.j.n;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: ByteArrayLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class b<Data> implements n<byte[], Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final InterfaceC0096b<Data> f2957a;

    /* JADX INFO: compiled from: ByteArrayLoader.java */
    public static class a implements o<byte[], ByteBuffer> {

        /* JADX INFO: renamed from: com.bumptech.glide.load.j.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ByteArrayLoader.java */
        class C0095a implements InterfaceC0096b<ByteBuffer> {
            C0095a() {
            }

            @Override // com.bumptech.glide.load.j.b.InterfaceC0096b
            public Class<ByteBuffer> a() {
                return ByteBuffer.class;
            }

            @Override // com.bumptech.glide.load.j.b.InterfaceC0096b
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public ByteBuffer b(byte[] bArr) {
                return ByteBuffer.wrap(bArr);
            }
        }

        @Override // com.bumptech.glide.load.j.o
        public n<byte[], ByteBuffer> b(r rVar) {
            return new b(new C0095a());
        }
    }

    /* JADX INFO: renamed from: com.bumptech.glide.load.j.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ByteArrayLoader.java */
    public interface InterfaceC0096b<Data> {
        Class<Data> a();

        Data b(byte[] bArr);
    }

    /* JADX INFO: compiled from: ByteArrayLoader.java */
    private static class c<Data> implements com.bumptech.glide.load.i.d<Data> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final byte[] f2959b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final InterfaceC0096b<Data> f2960c;

        c(byte[] bArr, InterfaceC0096b<Data> interfaceC0096b) {
            this.f2959b = bArr;
            this.f2960c = interfaceC0096b;
        }

        @Override // com.bumptech.glide.load.i.d
        public Class<Data> a() {
            return this.f2960c.a();
        }

        @Override // com.bumptech.glide.load.i.d
        public void b() {
        }

        @Override // com.bumptech.glide.load.i.d
        public void cancel() {
        }

        @Override // com.bumptech.glide.load.i.d
        public DataSource e() {
            return DataSource.LOCAL;
        }

        @Override // com.bumptech.glide.load.i.d
        public void f(Priority priority, d.a<? super Data> aVar) {
            aVar.d(this.f2960c.b(this.f2959b));
        }
    }

    /* JADX INFO: compiled from: ByteArrayLoader.java */
    public static class d implements o<byte[], InputStream> {

        /* JADX INFO: compiled from: ByteArrayLoader.java */
        class a implements InterfaceC0096b<InputStream> {
            a() {
            }

            @Override // com.bumptech.glide.load.j.b.InterfaceC0096b
            public Class<InputStream> a() {
                return InputStream.class;
            }

            @Override // com.bumptech.glide.load.j.b.InterfaceC0096b
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public InputStream b(byte[] bArr) {
                return new ByteArrayInputStream(bArr);
            }
        }

        @Override // com.bumptech.glide.load.j.o
        public n<byte[], InputStream> b(r rVar) {
            return new b(new a());
        }
    }

    public b(InterfaceC0096b<Data> interfaceC0096b) {
        this.f2957a = interfaceC0096b;
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> a(byte[] bArr, int i, int i2, com.bumptech.glide.load.e eVar) {
        return new n.a<>(new com.bumptech.glide.l.b(bArr), new c(bArr, this.f2957a));
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(byte[] bArr) {
        return true;
    }
}
