package u1;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import o1.d;
import u1.n;

/* JADX INFO: compiled from: ByteBufferFileLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements n<File, ByteBuffer> {

    /* JADX INFO: compiled from: ByteBufferFileLoader.java */
    private static final class a implements o1.d<ByteBuffer> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final File f9573e;

        a(File file) {
            this.f9573e = file;
        }

        @Override // o1.d
        public Class<ByteBuffer> a() {
            return ByteBuffer.class;
        }

        @Override // o1.d
        public void b() {
        }

        @Override // o1.d
        public void c(com.bumptech.glide.g gVar, d.a<? super ByteBuffer> aVar) {
            try {
                aVar.f(k2.a.a(this.f9573e));
            } catch (IOException e7) {
                if (Log.isLoggable("ByteBufferFileLoader", 3)) {
                    Log.d("ByteBufferFileLoader", "Failed to obtain ByteBuffer for file", e7);
                }
                aVar.d(e7);
            }
        }

        @Override // o1.d
        public void cancel() {
        }

        @Override // o1.d
        public n1.a e() {
            return n1.a.LOCAL;
        }
    }

    /* JADX INFO: compiled from: ByteBufferFileLoader.java */
    public static class b implements o<File, ByteBuffer> {
        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<File, ByteBuffer> c(r rVar) {
            return new d();
        }
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<ByteBuffer> b(File file, int i7, int i8, n1.i iVar) {
        return new n.a<>(new j2.d(file), new a(file));
    }

    @Override // u1.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(File file) {
        return true;
    }
}
