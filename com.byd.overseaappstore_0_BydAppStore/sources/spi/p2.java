package spi;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 28)
public final class p2 implements bn<ByteBuffer, Bitmap> {
    public final u1 a = new u1();

    @Override // spi.bn
    public final /* bridge */ /* synthetic */ boolean a(@NonNull ByteBuffer byteBuffer, @NonNull lk lkVar) throws IOException {
        return true;
    }

    @Override // spi.bn
    public final wm<Bitmap> b(@NonNull ByteBuffer byteBuffer, int i, int i2, @NonNull lk lkVar) throws IOException {
        return this.a.b(ImageDecoder.createSource(byteBuffer), i, i2, lkVar);
    }
}
