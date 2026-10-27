package spi;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.data.a;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class t2 implements com.bumptech.glide.load.data.a<ByteBuffer> {
    public final ByteBuffer a;

    public static class a implements a.InterfaceC0016a<ByteBuffer> {
        @Override // com.bumptech.glide.load.data.a.InterfaceC0016a
        @NonNull
        public final Class<ByteBuffer> a() {
            return ByteBuffer.class;
        }

        @Override // com.bumptech.glide.load.data.a.InterfaceC0016a
        @NonNull
        public final com.bumptech.glide.load.data.a<ByteBuffer> b(ByteBuffer byteBuffer) {
            return new t2(byteBuffer);
        }
    }

    public t2(ByteBuffer byteBuffer) {
        this.a = byteBuffer;
    }

    @Override // com.bumptech.glide.load.data.a
    public final void b() {
    }

    @Override // com.bumptech.glide.load.data.a
    @NonNull
    public final ByteBuffer c() throws IOException {
        this.a.position(0);
        return this.a;
    }
}
