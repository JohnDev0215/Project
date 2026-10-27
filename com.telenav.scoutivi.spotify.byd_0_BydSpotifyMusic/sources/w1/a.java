package w1;

import android.annotation.SuppressLint;
import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.util.Log;
import android.util.Size;
import n1.h;
import n1.i;
import n1.j;
import n1.k;
import q1.v;
import x1.l;
import x1.m;
import x1.r;

/* JADX INFO: compiled from: ImageDecoderResourceDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> implements k<ImageDecoder.Source, T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final r f9698a = r.a();

    /* JADX INFO: renamed from: w1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ImageDecoderResourceDecoder.java */
    class C0168a implements ImageDecoder.OnHeaderDecodedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f9699a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f9700b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f9701c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ n1.b f9702d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ l f9703e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ j f9704f;

        /* JADX INFO: renamed from: w1.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ImageDecoderResourceDecoder.java */
        class C0169a implements ImageDecoder.OnPartialImageListener {
            C0169a() {
            }

            @Override // android.graphics.ImageDecoder.OnPartialImageListener
            public boolean onPartialImage(ImageDecoder.DecodeException decodeException) {
                return false;
            }
        }

        C0168a(int i7, int i8, boolean z6, n1.b bVar, l lVar, j jVar) {
            this.f9699a = i7;
            this.f9700b = i8;
            this.f9701c = z6;
            this.f9702d = bVar;
            this.f9703e = lVar;
            this.f9704f = jVar;
        }

        @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
        @SuppressLint({"Override"})
        public void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
            boolean z6 = false;
            if (a.this.f9698a.c(this.f9699a, this.f9700b, this.f9701c, false)) {
                imageDecoder.setAllocator(3);
            } else {
                imageDecoder.setAllocator(1);
            }
            if (this.f9702d == n1.b.PREFER_RGB_565) {
                imageDecoder.setMemorySizePolicy(0);
            }
            imageDecoder.setOnPartialImageListener(new C0169a());
            Size size = imageInfo.getSize();
            int width = this.f9699a;
            if (width == Integer.MIN_VALUE) {
                width = size.getWidth();
            }
            int height = this.f9700b;
            if (height == Integer.MIN_VALUE) {
                height = size.getHeight();
            }
            float fB = this.f9703e.b(size.getWidth(), size.getHeight(), width, height);
            int iRound = Math.round(size.getWidth() * fB);
            int iRound2 = Math.round(size.getHeight() * fB);
            if (Log.isLoggable("ImageDecoder", 2)) {
                Log.v("ImageDecoder", "Resizing from [" + size.getWidth() + "x" + size.getHeight() + "] to [" + iRound + "x" + iRound2 + "] scaleFactor: " + fB);
            }
            imageDecoder.setTargetSize(iRound, iRound2);
            if (this.f9704f == j.DISPLAY_P3 && imageInfo.getColorSpace() != null && imageInfo.getColorSpace().isWideGamut()) {
                z6 = true;
            }
            imageDecoder.setTargetColorSpace(ColorSpace.get(z6 ? ColorSpace.Named.DISPLAY_P3 : ColorSpace.Named.SRGB));
        }
    }

    protected abstract v<T> c(ImageDecoder.Source source, int i7, int i8, ImageDecoder.OnHeaderDecodedListener onHeaderDecodedListener);

    @Override // n1.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final v<T> b(ImageDecoder.Source source, int i7, int i8, i iVar) {
        n1.b bVar = (n1.b) iVar.c(m.f9774f);
        l lVar = (l) iVar.c(l.f9771h);
        h<Boolean> hVar = m.f9778j;
        return c(source, i7, i8, new C0168a(i7, i8, iVar.c(hVar) != null && ((Boolean) iVar.c(hVar)).booleanValue(), bVar, lVar, (j) iVar.c(m.f9775g)));
    }

    @Override // n1.k
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final boolean a(ImageDecoder.Source source, i iVar) {
        return true;
    }
}
