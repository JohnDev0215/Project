package spi;

import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.util.Log;
import android.util.Size;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.PreferredColorSpace;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 28)
public final class a6 implements ImageDecoder.OnHeaderDecodedListener {
    public final he a = he.a();
    public final int b;
    public final int c;
    public final DecodeFormat d;
    public final DownsampleStrategy e;
    public final boolean f;
    public final PreferredColorSpace g;

    public class a implements ImageDecoder.OnPartialImageListener {
        public a(a6 a6Var) {
        }

        @Override // android.graphics.ImageDecoder.OnPartialImageListener
        public final boolean onPartialImage(@NonNull ImageDecoder.DecodeException decodeException) {
            return false;
        }
    }

    public a6(int i, int i2, @NonNull lk lkVar) {
        this.b = i;
        this.c = i2;
        this.d = (DecodeFormat) lkVar.c(com.bumptech.glide.load.resource.bitmap.a.f);
        this.e = (DownsampleStrategy) lkVar.c(DownsampleStrategy.f);
        jk<Boolean> jkVar = com.bumptech.glide.load.resource.bitmap.a.i;
        this.f = lkVar.c(jkVar) != null && ((Boolean) lkVar.c(jkVar)).booleanValue();
        this.g = (PreferredColorSpace) lkVar.c(com.bumptech.glide.load.resource.bitmap.a.g);
    }

    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
    public final void onHeaderDecoded(@NonNull ImageDecoder imageDecoder, @NonNull ImageDecoder.ImageInfo imageInfo, @NonNull ImageDecoder.Source source) {
        boolean z = false;
        if (this.a.b(this.b, this.c, this.f, false)) {
            imageDecoder.setAllocator(3);
        } else {
            imageDecoder.setAllocator(1);
        }
        if (this.d == DecodeFormat.PREFER_RGB_565) {
            imageDecoder.setMemorySizePolicy(0);
        }
        imageDecoder.setOnPartialImageListener(new a(this));
        Size size = imageInfo.getSize();
        int width = this.b;
        if (width == Integer.MIN_VALUE) {
            width = size.getWidth();
        }
        int height = this.c;
        if (height == Integer.MIN_VALUE) {
            height = size.getHeight();
        }
        float fB = this.e.b(size.getWidth(), size.getHeight(), width, height);
        int iRound = Math.round(size.getWidth() * fB);
        int iRound2 = Math.round(size.getHeight() * fB);
        if (Log.isLoggable("ImageDecoder", 2)) {
            StringBuilder sbK = e.k("Resizing from [");
            sbK.append(size.getWidth());
            sbK.append("x");
            sbK.append(size.getHeight());
            sbK.append("] to [");
            sbK.append(iRound);
            sbK.append("x");
            sbK.append(iRound2);
            sbK.append("] scaleFactor: ");
            sbK.append(fB);
            Log.v("ImageDecoder", sbK.toString());
        }
        imageDecoder.setTargetSize(iRound, iRound2);
        PreferredColorSpace preferredColorSpace = this.g;
        if (preferredColorSpace != null) {
            if (preferredColorSpace == PreferredColorSpace.DISPLAY_P3 && imageInfo.getColorSpace() != null && imageInfo.getColorSpace().isWideGamut()) {
                z = true;
            }
            imageDecoder.setTargetColorSpace(ColorSpace.get(z ? ColorSpace.Named.DISPLAY_P3 : ColorSpace.Named.SRGB));
        }
    }
}
