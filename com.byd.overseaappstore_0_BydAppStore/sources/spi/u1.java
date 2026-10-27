package spi;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 28)
public final class u1 implements bn<ImageDecoder.Source, Bitmap> {
    public final x1 a = new x1();

    @Override // spi.bn
    public final /* bridge */ /* synthetic */ boolean a(@NonNull ImageDecoder.Source source, @NonNull lk lkVar) throws IOException {
        return true;
    }

    @Override // spi.bn
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final wm<Bitmap> b(@NonNull ImageDecoder.Source source, int i, int i2, @NonNull lk lkVar) throws IOException {
        Bitmap bitmapDecodeBitmap = ImageDecoder.decodeBitmap(source, new a6(i, i2, lkVar));
        if (Log.isLoggable("BitmapImageDecoder", 2)) {
            StringBuilder sbK = e.k("Decoded [");
            sbK.append(bitmapDecodeBitmap.getWidth());
            sbK.append("x");
            sbK.append(bitmapDecodeBitmap.getHeight());
            sbK.append("] for [");
            sbK.append(i);
            sbK.append("x");
            sbK.append(i2);
            sbK.append("]");
            Log.v("BitmapImageDecoder", sbK.toString());
        }
        return new y1(bitmapDecodeBitmap, this.a);
    }
}
