package com.bumptech.glide.load.o;

import android.util.Log;
import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.o.n;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class d implements n<File, ByteBuffer> {

    private static final class a implements com.bumptech.glide.load.data.d<ByteBuffer> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final File f2216b;

        a(File file) {
            this.f2216b = file;
        }

        @Override // com.bumptech.glide.load.data.d
        public Class<ByteBuffer> a() {
            return ByteBuffer.class;
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
        public void e(com.bumptech.glide.g gVar, d.a<? super ByteBuffer> aVar) {
            try {
                aVar.f(com.bumptech.glide.t.a.a(this.f2216b));
            } catch (IOException e) {
                if (Log.isLoggable("ByteBufferFileLoader", 3)) {
                    Log.d("ByteBufferFileLoader", "Failed to obtain ByteBuffer for file", e);
                }
                aVar.d(e);
            }
        }
    }

    public static class b implements o<File, ByteBuffer> {
        @Override // com.bumptech.glide.load.o.o
        public n<File, ByteBuffer> b(r rVar) {
            return new d();
        }
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<ByteBuffer> a(File file, int i, int i2, com.bumptech.glide.load.i iVar) {
        return new n.a<>(new com.bumptech.glide.s.b(file), new a(file));
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(File file) {
        return true;
    }
}
