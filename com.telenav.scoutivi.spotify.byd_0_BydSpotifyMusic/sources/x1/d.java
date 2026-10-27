package x1;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.util.Log;
import java.io.IOException;

/* JADX INFO: compiled from: BitmapImageDecoderResourceDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends w1.a<Bitmap> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final r1.d f9752b = new r1.e();

    @Override // w1.a
    protected q1.v<Bitmap> c(ImageDecoder.Source source, int i7, int i8, ImageDecoder.OnHeaderDecodedListener onHeaderDecodedListener) throws IOException {
        Bitmap bitmapDecodeBitmap = ImageDecoder.decodeBitmap(source, onHeaderDecodedListener);
        if (Log.isLoggable("BitmapImageDecoder", 2)) {
            Log.v("BitmapImageDecoder", "Decoded [" + bitmapDecodeBitmap.getWidth() + "x" + bitmapDecodeBitmap.getHeight() + "] for [" + i7 + "x" + i8 + "]");
        }
        return new e(bitmapDecodeBitmap, this.f9752b);
    }
}
