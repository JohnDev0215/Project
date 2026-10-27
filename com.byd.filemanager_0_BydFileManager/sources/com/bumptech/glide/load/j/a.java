package com.bumptech.glide.load.j;

import android.annotation.SuppressLint;
import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.os.Build;
import android.util.Log;
import android.util.Size;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.PreferredColorSpace;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.load.resource.bitmap.n;
import java.io.IOException;

/* JADX INFO: compiled from: ImageDecoderResourceDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> implements f<ImageDecoder.Source, T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final n f3280a = n.a();

    /* JADX INFO: renamed from: com.bumptech.glide.load.j.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ImageDecoderResourceDecoder.java */
    class C0075a implements ImageDecoder.OnHeaderDecodedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3281a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3282b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f3283c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ DecodeFormat f3284d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ DownsampleStrategy f3285e;
        final /* synthetic */ PreferredColorSpace f;

        /* JADX INFO: renamed from: com.bumptech.glide.load.j.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ImageDecoderResourceDecoder.java */
        class C0076a implements ImageDecoder.OnPartialImageListener {
            C0076a() {
            }

            @Override // android.graphics.ImageDecoder.OnPartialImageListener
            public boolean onPartialImage(ImageDecoder.DecodeException decodeException) {
                return false;
            }
        }

        C0075a(int i, int i2, boolean z, DecodeFormat decodeFormat, DownsampleStrategy downsampleStrategy, PreferredColorSpace preferredColorSpace) {
            this.f3281a = i;
            this.f3282b = i2;
            this.f3283c = z;
            this.f3284d = decodeFormat;
            this.f3285e = downsampleStrategy;
            this.f = preferredColorSpace;
        }

        @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
        @SuppressLint({"Override"})
        public void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
            boolean z = false;
            if (a.this.f3280a.c(this.f3281a, this.f3282b, this.f3283c, false)) {
                imageDecoder.setAllocator(3);
            } else {
                imageDecoder.setAllocator(1);
            }
            if (this.f3284d == DecodeFormat.PREFER_RGB_565) {
                imageDecoder.setMemorySizePolicy(0);
            }
            imageDecoder.setOnPartialImageListener(new C0076a());
            Size size = imageInfo.getSize();
            int width = this.f3281a;
            if (width == Integer.MIN_VALUE) {
                width = size.getWidth();
            }
            int height = this.f3282b;
            if (height == Integer.MIN_VALUE) {
                height = size.getHeight();
            }
            float fB = this.f3285e.b(size.getWidth(), size.getHeight(), width, height);
            int iRound = Math.round(size.getWidth() * fB);
            int iRound2 = Math.round(size.getHeight() * fB);
            if (Log.isLoggable("ImageDecoder", 2)) {
                Log.v("ImageDecoder", "Resizing from [" + size.getWidth() + "x" + size.getHeight() + "] to [" + iRound + "x" + iRound2 + "] scaleFactor: " + fB);
            }
            imageDecoder.setTargetSize(iRound, iRound2);
            int i = Build.VERSION.SDK_INT;
            if (i < 28) {
                if (i >= 26) {
                    imageDecoder.setTargetColorSpace(ColorSpace.get(ColorSpace.Named.SRGB));
                }
            } else {
                if (this.f == PreferredColorSpace.DISPLAY_P3 && imageInfo.getColorSpace() != null && imageInfo.getColorSpace().isWideGamut()) {
                    z = true;
                }
                imageDecoder.setTargetColorSpace(ColorSpace.get(z ? ColorSpace.Named.DISPLAY_P3 : ColorSpace.Named.SRGB));
            }
        }
    }

    protected abstract s<T> c(ImageDecoder.Source source, int i, int i2, ImageDecoder.OnHeaderDecodedListener onHeaderDecodedListener) throws IOException;

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final s<T> a(ImageDecoder.Source source, int i, int i2, e eVar) throws IOException {
        DecodeFormat decodeFormat = (DecodeFormat) eVar.c(j.f3360a);
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) eVar.c(DownsampleStrategy.h);
        d<Boolean> dVar = j.f3364e;
        return c(source, i, i2, new C0075a(i, i2, eVar.c(dVar) != null && ((Boolean) eVar.c(dVar)).booleanValue(), decodeFormat, downsampleStrategy, (PreferredColorSpace) eVar.c(j.f3361b)));
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final boolean b(ImageDecoder.Source source, e eVar) {
        return true;
    }
}
