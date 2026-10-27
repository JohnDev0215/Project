package spi;

import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import spi.m5;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class n2<Data> implements ti<byte[], Data> {
    public final b<Data> a;

    public static class a implements ui<byte[], ByteBuffer> {

        /* JADX INFO: renamed from: spi.n2$a$a, reason: collision with other inner class name */
        public class C0135a implements b<ByteBuffer> {
            public C0135a(a aVar) {
            }

            @Override // spi.n2.b
            public final Class<ByteBuffer> a() {
                return ByteBuffer.class;
            }

            @Override // spi.n2.b
            public final ByteBuffer b(byte[] bArr) {
                return ByteBuffer.wrap(bArr);
            }
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<byte[], ByteBuffer> c(@NonNull aj ajVar) {
            return new n2(new C0135a(this));
        }
    }

    public interface b<Data> {
        Class<Data> a();

        Data b(byte[] bArr);
    }

    public static class c<Data> implements m5<Data> {
        public final byte[] a;
        public final b<Data> b;

        public c(byte[] bArr, b<Data> bVar) {
            this.a = bArr;
            this.b = bVar;
        }

        @Override // spi.m5
        @NonNull
        public final Class<Data> a() {
            return this.b.a();
        }

        @Override // spi.m5
        public final void b() {
        }

        @Override // spi.m5
        public final void cancel() {
        }

        @Override // spi.m5
        public final void d(@NonNull Priority priority, @NonNull m5.a<? super Data> aVar) {
            aVar.f(this.b.b(this.a));
        }

        @Override // spi.m5
        @NonNull
        public final DataSource e() {
            return DataSource.LOCAL;
        }
    }

    public static class d implements ui<byte[], InputStream> {

        public class a implements b<InputStream> {
            public a(d dVar) {
            }

            @Override // spi.n2.b
            public final Class<InputStream> a() {
                return InputStream.class;
            }

            @Override // spi.n2.b
            public final InputStream b(byte[] bArr) {
                return new ByteArrayInputStream(bArr);
            }
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<byte[], InputStream> c(@NonNull aj ajVar) {
            return new n2(new a(this));
        }
    }

    public n2(b<Data> bVar) {
        this.a = bVar;
    }

    @Override // spi.ti
    public final /* bridge */ /* synthetic */ boolean a(@NonNull byte[] bArr) {
        return true;
    }

    @Override // spi.ti
    public final ti.a b(@NonNull byte[] bArr, int i, int i2, @NonNull lk lkVar) {
        byte[] bArr2 = bArr;
        return new ti.a(new wj(bArr2), new c(bArr2, this.a));
    }
}
