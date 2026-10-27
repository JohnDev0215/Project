package com.bumptech.glide.load.p;

import android.annotation.SuppressLint;
import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.os.Build;
import android.util.Log;
import android.util.Size;
import com.bumptech.glide.load.h;
import com.bumptech.glide.load.j;
import com.bumptech.glide.load.k;
import com.bumptech.glide.load.n.v;
import com.bumptech.glide.load.p.d.i;
import com.bumptech.glide.load.p.d.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> implements k<ImageDecoder.Source, T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final n f2306a = n.a();

    /* JADX INFO: renamed from: com.bumptech.glide.load.p.a$a, reason: collision with other inner class name */
    class C0079a implements ImageDecoder.OnHeaderDecodedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f2307a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f2308b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f2309c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ com.bumptech.glide.load.b f2310d;
        final /* synthetic */ i e;
        final /* synthetic */ j f;

        /* JADX INFO: renamed from: com.bumptech.glide.load.p.a$a$a, reason: collision with other inner class name */
        class C0080a implements ImageDecoder.OnPartialImageListener {
            C0080a() {
            }

            @Override // android.graphics.ImageDecoder.OnPartialImageListener
            public boolean onPartialImage(ImageDecoder.DecodeException decodeException) {
                return false;
            }
        }

        C0079a(int i, int i2, boolean z, com.bumptech.glide.load.b bVar, i iVar, j jVar) {
            this.f2307a = i;
            this.f2308b = i2;
            this.f2309c = z;
            this.f2310d = bVar;
            this.e = iVar;
            this.f = jVar;
        }

        @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
        @SuppressLint({"Override"})
        public void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
            ColorSpace.Named named;
            boolean z = false;
            if (a.this.f2306a.c(this.f2307a, this.f2308b, this.f2309c, false)) {
                imageDecoder.setAllocator(3);
            } else {
                imageDecoder.setAllocator(1);
            }
            if (this.f2310d == com.bumptech.glide.load.b.PREFER_RGB_565) {
                imageDecoder.setMemorySizePolicy(0);
            }
            imageDecoder.setOnPartialImageListener(new C0080a());
            Size size = imageInfo.getSize();
            int width = this.f2307a;
            if (width == Integer.MIN_VALUE) {
                width = size.getWidth();
            }
            int height = this.f2308b;
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
            if (i >= 28) {
                if (this.f == j.DISPLAY_P3 && imageInfo.getColorSpace() != null && imageInfo.getColorSpace().isWideGamut()) {
                    z = true;
                }
                if (z) {
                    named = ColorSpace.Named.DISPLAY_P3;
                }
                imageDecoder.setTargetColorSpace(ColorSpace.get(named));
            }
            if (i < 26) {
                return;
            }
            named = ColorSpace.Named.SRGB;
            imageDecoder.setTargetColorSpace(ColorSpace.get(named));
        }
    }

    protected abstract v<T> c(ImageDecoder.Source source, int i, int i2, ImageDecoder.OnHeaderDecodedListener onHeaderDecodedListener);

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final v<T> a(ImageDecoder.Source source, int i, int i2, com.bumptech.glide.load.i iVar) {
        com.bumptech.glide.load.b bVar = (com.bumptech.glide.load.b) iVar.c(com.bumptech.glide.load.p.d.j.f2338a);
        i iVar2 = (i) iVar.c(i.h);
        h<Boolean> hVar = com.bumptech.glide.load.p.d.j.e;
        return c(source, i, i2, new C0079a(i, i2, iVar.c(hVar) != null && ((Boolean) iVar.c(hVar)).booleanValue(), bVar, iVar2, (j) iVar.c(com.bumptech.glide.load.p.d.j.f2339b)));
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final boolean b(ImageDecoder.Source source, com.bumptech.glide.load.i iVar) {
        return true;
    }
}
