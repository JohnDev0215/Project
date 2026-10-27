package spi;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.resource.bitmap.b;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class o2 implements bn<ByteBuffer, Bitmap> {
    public final com.bumptech.glide.load.resource.bitmap.a a;

    public o2(com.bumptech.glide.load.resource.bitmap.a aVar) {
        this.a = aVar;
    }

    @Override // spi.bn
    public final boolean a(@NonNull ByteBuffer byteBuffer, @NonNull lk lkVar) throws IOException {
        Objects.requireNonNull(this.a);
        return true;
    }

    @Override // spi.bn
    public final wm<Bitmap> b(@NonNull ByteBuffer byteBuffer, int i, int i2, @NonNull lk lkVar) throws IOException {
        com.bumptech.glide.load.resource.bitmap.a aVar = this.a;
        return aVar.a(new b.a(byteBuffer, aVar.d, aVar.c), i, i2, lkVar, com.bumptech.glide.load.resource.bitmap.a.j);
    }
}
