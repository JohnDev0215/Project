package m4;

import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.util.Log;
import android.util.Size;
import e4.EnumC1225a;
import e4.g;
import e4.h;
import e4.i;
import n4.n;
import n4.p;
import n4.v;

/* JADX INFO: loaded from: classes.dex */
public final class b implements ImageDecoder.OnHeaderDecodedListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v f19182a = v.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f19183b;
    public final int c;
    public final EnumC1225a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final n f19184e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f19185f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final i f19186g;

    public b(int i7, int i10, h hVar) {
        this.f19183b = i7;
        this.c = i10;
        this.d = (EnumC1225a) hVar.c(p.f19953f);
        this.f19184e = (n) hVar.c(n.f19950g);
        g gVar = p.f19956i;
        this.f19185f = hVar.c(gVar) != null && ((Boolean) hVar.c(gVar)).booleanValue();
        this.f19186g = (i) hVar.c(p.f19954g);
    }

    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        v vVar = this.f19182a;
        int width = this.f19183b;
        int height = this.c;
        if (vVar.b(width, height, this.f19185f, false)) {
            imageDecoder.setAllocator(3);
        } else {
            imageDecoder.setAllocator(1);
        }
        if (this.d == EnumC1225a.f14427m) {
            imageDecoder.setMemorySizePolicy(0);
        }
        imageDecoder.setOnPartialImageListener(new C1815a());
        Size size = imageInfo.getSize();
        if (width == Integer.MIN_VALUE) {
            width = size.getWidth();
        }
        if (height == Integer.MIN_VALUE) {
            height = size.getHeight();
        }
        float fB = this.f19184e.b(size.getWidth(), size.getHeight(), width, height);
        int iRound = Math.round(size.getWidth() * fB);
        int iRound2 = Math.round(size.getHeight() * fB);
        if (Log.isLoggable("ImageDecoder", 2)) {
            Log.v("ImageDecoder", "Resizing from [" + size.getWidth() + "x" + size.getHeight() + "] to [" + iRound + "x" + iRound2 + "] scaleFactor: " + fB);
        }
        imageDecoder.setTargetSize(iRound, iRound2);
        i iVar = this.f19186g;
        if (iVar != null) {
            imageDecoder.setTargetColorSpace(ColorSpace.get((iVar == i.l && imageInfo.getColorSpace() != null && imageInfo.getColorSpace().isWideGamut()) ? ColorSpace.Named.DISPLAY_P3 : ColorSpace.Named.SRGB));
        }
    }
}
