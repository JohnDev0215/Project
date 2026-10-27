package com.bumptech.glide.load.k.d;

import com.bumptech.glide.load.i.e;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: ByteBufferRewinder.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements e<ByteBuffer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ByteBuffer f3060a;

    /* JADX INFO: renamed from: com.bumptech.glide.load.k.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ByteBufferRewinder.java */
    public static class C0101a implements e.a<ByteBuffer> {
        @Override // com.bumptech.glide.load.i.e.a
        public Class<ByteBuffer> a() {
            return ByteBuffer.class;
        }

        @Override // com.bumptech.glide.load.i.e.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public e<ByteBuffer> b(ByteBuffer byteBuffer) {
            return new a(byteBuffer);
        }
    }

    public a(ByteBuffer byteBuffer) {
        this.f3060a = byteBuffer;
    }

    @Override // com.bumptech.glide.load.i.e
    public void b() {
    }

    @Override // com.bumptech.glide.load.i.e
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ByteBuffer a() {
        this.f3060a.position(0);
        return this.f3060a;
    }
}
