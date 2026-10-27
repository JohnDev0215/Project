package spi;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 28)
public final class tf implements bn<InputStream, Bitmap> {
    public final u1 a = new u1();

    @Override // spi.bn
    public final /* bridge */ /* synthetic */ boolean a(@NonNull InputStream inputStream, @NonNull lk lkVar) throws IOException {
        return true;
    }

    @Override // spi.bn
    public final wm<Bitmap> b(@NonNull InputStream inputStream, int i, int i2, @NonNull lk lkVar) throws IOException {
        return this.a.b(ImageDecoder.createSource(u2.b(inputStream)), i, i2, lkVar);
    }
}
