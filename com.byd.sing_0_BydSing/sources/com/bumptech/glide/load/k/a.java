package com.bumptech.glide.load.k;

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
import com.bumptech.glide.load.resource.bitmap.h;
import com.bumptech.glide.load.resource.bitmap.l;
import java.io.IOException;

/* JADX INFO: compiled from: ImageDecoderResourceDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> implements f<ImageDecoder.Source, T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final l f3052a = l.a();

    /* JADX INFO: renamed from: com.bumptech.glide.load.k.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ImageDecoderResourceDecoder.java */
    class C0099a implements ImageDecoder.OnHeaderDecodedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3053a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3054b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f3055c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ DecodeFormat f3056d;
        final /* synthetic */ DownsampleStrategy e;
        final /* synthetic */ PreferredColorSpace f;

        /* JADX INFO: renamed from: com.bumptech.glide.load.k.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ImageDecoderResourceDecoder.java */
        class C0100a implements ImageDecoder.OnPartialImageListener {
            C0100a() {
            }

            @Override // android.graphics.ImageDecoder.OnPartialImageListener
            public boolean onPartialImage(ImageDecoder.DecodeException decodeException) {
                return false;
            }
        }

        C0099a(int i, int i2, boolean z, DecodeFormat decodeFormat, DownsampleStrategy downsampleStrategy, PreferredColorSpace preferredColorSpace) {
            this.f3053a = i;
            this.f3054b = i2;
            this.f3055c = z;
            this.f3056d = decodeFormat;
            this.e = downsampleStrategy;
            this.f = preferredColorSpace;
        }

        @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
        @SuppressLint({"Override"})
        public void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
            boolean z = false;
            if (a.this.f3052a.c(this.f3053a, this.f3054b, this.f3055c, false)) {
                imageDecoder.setAllocator(3);
            } else {
                imageDecoder.setAllocator(1);
            }
            if (this.f3056d == DecodeFormat.PREFER_RGB_565) {
                imageDecoder.setMemorySizePolicy(0);
            }
            imageDecoder.setOnPartialImageListener(new C0100a());
            Size size = imageInfo.getSize();
            int width = this.f3053a;
            if (width == Integer.MIN_VALUE) {
                width = size.getWidth();
            }
            int height = this.f3054b;
            if (height == Integer.MIN_VALUE) {
                height = size.getHeight();
            }
            float fB = this.e.b(size.getWidth(), size.getHeight(), width, height);
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
        DecodeFormat decodeFormat = (DecodeFormat) eVar.c(h.f3124a);
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) eVar.c(DownsampleStrategy.h);
        d<Boolean> dVar = h.e;
        return c(source, i, i2, new C0099a(i, i2, eVar.c(dVar) != null && ((Boolean) eVar.c(dVar)).booleanValue(), decodeFormat, downsampleStrategy, (PreferredColorSpace) eVar.c(h.f3125b)));
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final boolean b(ImageDecoder.Source source, e eVar) {
        return true;
    }
}
