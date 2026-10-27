package p4;

import android.graphics.ImageDecoder;
import android.os.Build;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import e4.h;
import e4.j;
import g4.x;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import l5.C1778f;
import l9.l;

/* JADX INFO: renamed from: p4.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2102a implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f20895a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1778f f20896b;

    public /* synthetic */ C2102a(C1778f c1778f, int i7) {
        this.f20895a = i7;
        this.f20896b = c1778f;
    }

    @Override // e4.j
    public final x a(Object obj, int i7, int i10, h hVar) {
        switch (this.f20895a) {
            case 0:
                return C1778f.e(ImageDecoder.createSource((ByteBuffer) obj), i7, i10, hVar);
            default:
                return C1778f.e(ImageDecoder.createSource(A4.c.b((InputStream) obj)), i7, i10, hVar);
        }
    }

    @Override // e4.j
    public final boolean b(Object obj, h hVar) throws IOException {
        switch (this.f20895a) {
            case 0:
                ImageHeaderParser$ImageType imageHeaderParser$ImageTypeS = l.S((ArrayList) this.f20896b.f18638m, (ByteBuffer) obj);
                if (imageHeaderParser$ImageTypeS == ImageHeaderParser$ImageType.ANIMATED_WEBP || (Build.VERSION.SDK_INT >= 31 && imageHeaderParser$ImageTypeS == ImageHeaderParser$ImageType.ANIMATED_AVIF)) {
                }
                break;
            default:
                C1778f c1778f = this.f20896b;
                ImageHeaderParser$ImageType imageHeaderParser$ImageTypeR = l.R((ArrayList) c1778f.f18638m, (InputStream) obj, (F.l) c1778f.f18639n);
                if (imageHeaderParser$ImageTypeR == ImageHeaderParser$ImageType.ANIMATED_WEBP || (Build.VERSION.SDK_INT >= 31 && imageHeaderParser$ImageTypeR == ImageHeaderParser$ImageType.ANIMATED_AVIF)) {
                }
                break;
        }
        return true;
    }
}
